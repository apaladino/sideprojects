import os
import sqlite3

import UserSvc


class UserCreator:

    def __init__(self):
        self.tables_created = True
        self.userSvc = UserSvc.UserSvc


    def printTableCounts(self, curs):
        tables = ["USER", "USER_DEFAULTS", "ROLES"]

        for table in tables:
            sql = "select count(*) from %s" % table
            curs.execute(sql)
            for row in curs:
                print("Table %s - count: %s" % (table, row))

    def insertUser(self, user_name, first_name, last_name, curs):
        try:
            maxRowID = curs.execute("select max(user_id) from USER").fetchone()
            maxRowID = maxRowID[0] + 1

            sql = """insert into USER(user_id, user_name, first_name, last_name) 
        VALUES('%s', '%s', '%s', '%s');""" % (maxRowID, user_name, first_name, last_name)

            curs.execute(sql)

        except Exception as e:
            print("Unable to insert user: %s, Error: %s" % (user_name, e.message))
            raise e

    def validateNewUserRoles(self, newUserRoles, likeUserRoles):
        for likeRole in likeUserRoles:
            if likeRole not in newUserRoles:
                raise Exception("New user is missing required role: %s" % likeRole)

    def validateNewUserLocs(selfself, newUserLocs, likeUserLocs):
        for likeLocation in likeUserLocs:
            if likeLocation not in newUserLocs:
                raise Exception("New user is missing required location %s" % likeLocation)


    def insertUserDefaults(self, username, primaryLoc, locations, curs):
        userLocs = [primaryLoc]
        for loc in locations:
            if loc not in userLocs:
                userLocs.append(loc)

        maxUserId = curs.execute("select max(user_id) from USER_DEFAULTS;").fetchone()[0]

        for loc in userLocs:
            maxUserId += 1
            sql = "insert into USER_DEFAULTS (user_id, user_name, location_id) VALUES ( %s, '%s','%s');" \
                % (maxUserId, username, loc)

            curs.execute(sql)
            #break

    def insertRoles(self, username, likeUserRoles, curs):

        maxRowId = curs.execute("select max(row_id) from ROLES").fetchall()[0]
        if type(maxRowId) is tuple:
            maxRowId = maxRowId[0]

        for role in likeUserRoles:
            maxRowId += 1
            sql = "insert into ROLES (row_id, granted_role, user_name) VALUES (%s, '%s', '%s');" \
                % (maxRowId, role, username)
            curs.execute(sql)

    def create_user_like_existing_user(self, createRequest):
        print("Create_user_like_existing_user request")

        print(os.getcwd())

        # Look up roles for like user
        conn = sqlite3.connect("%s.db" % createRequest.sid)
        c = conn.cursor()

        self.printTableCounts(c)

        likeUserRoles = self.userSvc.getUserRoles(createRequest.like_user, c)
        likeuserLocs = self.userSvc.getUserLocations(createRequest.like_user, c)

        # create new user
        self.insertUser(createRequest.username, createRequest.first_name, createRequest.last_name, c)
        conn.commit()
        self.insertUserDefaults(createRequest.username, createRequest.primary_loc, likeuserLocs, c)
        conn.commit()
        self.insertRoles(createRequest.username, likeUserRoles, c)
        conn.commit()

        self.printTableCounts(c)

        # look up the roles and locations for new user
        newUserRoles = self.userSvc.getUserRoles(createRequest.username, c)
        newUserLocs = self.userSvc.getUserLocations(createRequest.username, c)

        self.validateNewUserRoles(newUserRoles, likeUserRoles)
        self.validateNewUserLocs(newUserLocs, likeuserLocs)

        print("User: %s created with matching settings as existing user: %s" % \
              (createRequest.username, createRequest.like_user))

        return {
            'newuser' : createRequest,
            'newuser.locations': newUserLocs,
                'newuser.roles': newUserRoles,
                'likeuser.locations': likeuserLocs,
                'likeuser.roles': likeUserRoles
        }
