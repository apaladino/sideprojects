import sqlite3

class UserSvc:

    def user_exists_in_database(self, username, curs):
        sql = "select user_id from USER where user_name = '%s'" % username

        curs.execute(sql)
        records = []
        for row in curs:
            if type(row) is tuple:
                row = row[0]
            records.append(row)

        return len(records) > 0


    def resetPrimaryLocationForUser(self, username, curs):
        print("reset primary location")



    def addLocationToUser(self, username, location, primary_loc, curs):
        """maxUserId = curs.execute("select max(user_id) from USER_DEFAULTS;").fetchone()[0]
        maxUserId += 1

        sql = "insert into USER_DEFAULTS (user_id, user_name, location_id) VALUES ( %s, '%s','%s')" \
                  % (maxUserId, username, location)
        curs.execute(sql)"""
        print("add location to user")


    def getUserLocations(self, user, curs):
        sql = "select location_id from USER_DEFAULTS where user_name='%s'" % user
        curs.execute(sql)

        records = []
        for row in curs:
            if type(row) is tuple:
                row = row[0]
            records.append(row)

        return records

    def getUserRoles(self, user, curs):
        sql = "select granted_role from ROLES where user_name='%s'" % user
        curs.execute(sql)

        records = []
        for row in curs:
            if type(row) is tuple:
                row = row[0]
            print("Row: %s" % row)
            records.append(row)

        print(records)
        return records
