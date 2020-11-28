

class CompareUsersSvc:

    def __init__(self, user, passwd, database):
        self.username = user
        self.password = passwd
        self.database = database


    def get_comparison_data(self, user1, user2):
        print("get_comparison_data called. user1: %s, user2: %s" % (user1, user2))

        results = {}
        user1Map = {}
        user2Map = {}
        # lookup personal details for user 1
        profile1 = {
            'user_id': 1,
            'user_name': user1,
            'first_name': 'Peter',
            'last_name': 'Smith',
            'dob': '3/12/1968',
            'address': '10 Sally Lane, Cleveland OH 88442'
        }
        user1Map['profile'] = profile1

        roles1 = ['ROLE_FRONT_CLERK',
                  'ROLE_PHONE_OPERATOR',
                  'ROLE_CASH_DRAWER',
                  'ROLE_FILE_CLERK',
                  'ROLE_PRODUCT_MGR',
                  'ROLE_BRANCH_MGR',
                  'ROLE_TAX_AUDIT',
                  'ROLE_UTILITIES_MGR',
                  'ROLE_ADMINISTRATOR',
                  'ROLE_SYSTEM_ADMIN']
        user1Map['roles'] = roles1

        locations1 = ['MAIN OFFICE',
                      'SWEETWATER BRANCH',
                      'SOUTH BEND BRANCH',
                      'SPRINGFIELD BRANCH']
        user1Map['offices']= locations1

        # lookup data for user two
        profile2 = {
            'user_id': 2,
            'user_name': user2,
            'first_name': 'Sarah',
            'last_name': 'Bigguns',
            'dob': '12/05/1984',
            'address': '511 Southern Ave. Albany NY 57572'
        }
        user2Map['profile'] = profile2

        roles2 = ['ROLE_FRONT_CLERK',
                  'ROLE_PHONE_OPERATOR',
                  'ROLE_CASH_DRAWER',
                  'ROLE_FILE_CLERK',
                  'ROLE_PRODUCT_MGR']
        user2Map['roles'] = roles2

        locations2 = ['MAIN OFFICE',
                      'SWEETWATER BRANCH',
                      'SPRINGFIELD BRANCH',
                      'CORP HEADQUARTERS',
                      'WINSTON BRANCH',
                      'YONKERS BRANCH']
        user2Map['offices']= locations2

        results['user1'] = user1Map
        results['user2'] = user2Map

        return results