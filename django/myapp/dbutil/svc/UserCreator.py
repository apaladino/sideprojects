import CreateRequest
import sqlite3
import os

ddl_statements = [
    """
    CREATE TABLE ROLES(
    row_id NUMBER PRIMARY KEY,
    granted_role TEXT,
    user_name TEXT
    );""", """
CREATE TABLE USER(
  user_ID NUMBER PRIMARY KEY,
  user_name TEXT,
  first_name TEXT,
  last_name TEXT,
  DOB TEXT
 );""", """
CREATE TABLE USER_DEFAULTS(
  user_default_id NUMBER PRIMARY KEY,
  user_name TEXT,
  location_id TEXT
  );""",
]

sql_statements = [
   """
insert into USER(user_id, first_name, last_name, DOB) 
	VALUES(1, 'JAMES', 'ANDREWS', '10-01-1963');
""","""
insert into USER(user_id, first_name, last_name, DOB) 
	VALUES(2, 'SALLY', 'YATES', '04-23-1974');
""","""
insert into ROLES (row_id, granted_role, user_name) 
	VALUES (1, 'ROLE_FRONT_DESK', 'ANDREWSJ');
""","""
insert into ROLES (row_id, granted_role, user_name) 
	VALUES (2, 'ROLE_CLERK', 'ANDREWSJ');
""","""
insert into ROLES (row_id, granted_role, user_name) 
	VALUES (3, 'ROLE_SALES', 'ANDREWSJ');
""","""
insert into ROLES (row_id, granted_role, user_name) 
	VALUES (4, 'ROLE_FRONT_DESK', 'YATESS');
""","""
insert into ROLES (row_id, granted_role, user_name) 
	VALUES (5, 'ROLE_CLERK', 'YATESS');
""","""
insert into ROLES (row_id, granted_role, user_name) 
	VALUES (6, 'ROLE_SALES', 'YATESS');
""","""
insert into ROLES (row_id, granted_role, user_name) 
	VALUES (7, 'ROLE_MANAGER', 'YATESS');
""","""
insert into USER_DEFAULTS  (user_default_id, user_name, location_id) 
	VALUES(1, 'ANDREWSJ', 'MAIN');""","""
insert into USER_DEFAULTS (user_default_id, user_name, location_id)
	VALUES(2, 'YATESS', 'MAIN');"""]


class UserCreator:

    def __init__(self):
        self.tables_created = True

    def getUserLocations(self, user, curs):
        sql = "select location_id from USER_DEFAULTS where user_name='%s'" % user
        curs.execute(sql)

        records = []
        for row in curs:
            if type(row) is tuple:
                row = row[0]
            records.append(row)

        return records

    def getUserRoles(self, like_user, curs):
        sql = "select granted_role from ROLES where user_name='%s'" % like_user
        curs.execute(sql)

        records = []
        for row in curs:
            if type(row) is tuple:
                row = row[0]
            print("Row: %s" % row)
            records.append(row)

        print(records)
        return records

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

        likeUserRoles = self.getUserRoles(createRequest.like_user, c)
        likeuserLocs = self.getUserLocations(createRequest.like_user, c)

        # create new user
        self.insertUser(createRequest.username, createRequest.first_name, createRequest.last_name, c)
        conn.commit()
        self.insertUserDefaults(createRequest.username, createRequest.primary_loc, likeuserLocs, c)
        conn.commit()
        self.insertRoles(createRequest.username, likeUserRoles, c)
        conn.commit()

        self.printTableCounts(c)

        # look up the roles and locations for new user
        newUserRoles = self.getUserRoles(createRequest.username, c)
        newUserLocs = self.getUserLocations(createRequest.username, c)

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
