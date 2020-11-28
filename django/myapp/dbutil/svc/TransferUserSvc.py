import sqlite3
import CompareUsersSvc, UserSvc


class TransferUserSvc:

    def __init__(self, username, password, database):
        self.username = username
        self.password = password
        self.database = database
        self.userSvc = UserSvc.UserSvc()
        self.compareUserSvc = CompareUsersSvc.CompareUsersSvc(username, password, database)

    def transfer_user(self, user, newLock, is_clone_user, like_user):
        print("transfer_user called")

        conn = sqlite3.connect("%s.db" % self.database)
        curs = conn.cursor()

        if not self.userSvc.user_exists_in_database(user, curs):
            raise Exception("User: %s not found." % user)

        if is_clone_user and not self.userSvc.user_exists_in_database(like_user, curs):
            raise Exception("Like User: %s not found." % like_user)

        # reset location primary_loc
        self.userSvc.resetPrimaryLocationForUser(user, curs)

        userLocations = self.userSvc.getUserLocations(user, curs)
        if newLock not in userLocations:
            userLocations.append(newLock)
            self.userSvc.addLocationToUser(user, newLock, True, curs)

        # check is clone user option selected
        if is_clone_user:
            likeUserLocs = self.userSvc.getUserLocations(like_user, curs)

            for loc in likeUserLocs:
                # copy any locations for like user
                if loc not in userLocations:
                    self.userSvc.addLocationToUser(user, loc, False, curs)

            # copy roles for like user
            userRoles = self.userSvc.getUserRoles(user, curs)
            likeUserRoles = self.userSvc.getUserRoles(like_user, curs)

            for role in likeUserRoles:
                if role not in userRoles:
                    self.userSvc.addRoleToUser(user, role, curs)
                    userRoles.append(role)

        results = self.compareUserSvc.get_comparison_data(user, like_user)
        results['status'] = "SUCCESS"
        return results
