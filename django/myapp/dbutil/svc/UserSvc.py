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


    def resetPrimaryLocationForUser(self, username, curs, file):
        print("reset primary location")
        file.write( """
###
### RESET PRIMARY LOCATION FOR USER: %s""" % username)

        file.write("""SQL:
        update table user_defaults set primary_loc=False
        
        """)
        file.flush()



    def addLocationToUser(self, username, location, primary_loc, curs, file):
        file.write("""
##
##  ADD LOCATION TO USER
##""")
        file.write("""
        User: %s
        Location: %s 
        isPrimaryLocation: %s
        
        """ % ( username, location, primary_loc))

        file.write("""SQL: 
        insert into user_defalts (username, location) values (....)
        """)

        file.flush()
        print("add location to user")


    def getUserLocations(self, user, curs, file):
        file.write("""
##
##  GET USER LOCATIONS FOR USER: %s
##""" % user)
        sql = "select location_id from USER_DEFAULTS where user_name='%s'" % user

        file.write("""SQL:
        %s""" % sql)
        curs.execute(sql)

        file.write("""Locations:
        """)
        records = []
        for row in curs:
            if type(row) is tuple:
                row = row[0]
            records.append(row)
            file.write("%s\n" % row)

        file.flush()
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

    def addRoleToUser(self, user, role, curs, file):
        file.write("""
##
##  ADD ROLE TO USER
##
        
        user: %s
        role: %s
        
        """ % (user, role))

        file.write("""SQL:
        
        insert into role(role, grantee) VALUES( %s, %s)
        
        """ % (role, user))
        file.flush()