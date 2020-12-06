import sqlite3
import CompareUsersSvc, UserSvc
from datetime import datetime

class TransferUserSvc:

    def __init__(self, username, password, database, workDir):
        self.username = username
        self.password = password
        self.database = database
        self.workDir = workDir
        self.userSvc = UserSvc.UserSvc()
        self.compareUserSvc = CompareUsersSvc.CompareUsersSvc(username, password, database)

    def transfer_user(self, user, newLock, is_clone_user, like_user, work_order):
        print("transfer_user called")
        filename = self.get_output_file_name(work_order)
        file = open(filename, 'w')
        self.write_to_file(file, """
##
##   TRANSFER USER: %s  to location: %s
##  
        """ % (user, newLock))

        conn = sqlite3.connect("%s.db" % self.database)
        curs = conn.cursor()

        if not self.userSvc.user_exists_in_database(user, curs):
            raise Exception("User: %s not found." % user)

        if is_clone_user and not self.userSvc.user_exists_in_database(like_user, curs):
            raise Exception("Like User: %s not found." % like_user)

        # reset location primary_loc
        self.userSvc.resetPrimaryLocationForUser(user, curs, file)

        userLocations = self.userSvc.getUserLocations(user, curs, file)
        if newLock not in userLocations:
            userLocations.append(newLock) # username, location, primary_loc, curs, file
            self.userSvc.addLocationToUser(user, newLock, True, curs, file)

        # check is clone user option selected
        if is_clone_user:
            likeUserLocs = self.userSvc.getUserLocations(like_user, curs, file)

            for loc in likeUserLocs:
                # copy any locations for like user
                if loc not in userLocations:
                    self.userSvc.addLocationToUser(user, loc, False, curs, file)

            # copy roles for like user
            userRoles = self.userSvc.getUserRoles(user, curs)
            likeUserRoles = self.userSvc.getUserRoles(like_user, curs)

            for role in likeUserRoles:
                if role not in userRoles:
                    self.userSvc.addRoleToUser(user, role, curs, file)
                    userRoles.append(role)

        results = self.compareUserSvc.get_comparison_data(user, like_user)
        results['status'] = "SUCCESS"
        file.flush()
        file.close()
        return results

    def get_output_file_name(self, work_order):
        dt = datetime.now()
        dtStr = dt.strftime("%m.%d.%Y.%H.%M.%S")

        filename = "%s/%s_transfer_user_%s.txt" % (self.workDir, work_order, dtStr)
        return filename

    def write_to_file(self, file, str):
        file.write(str)
        file.flush()


