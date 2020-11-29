
class LookupCustomerSvc:
    def __init__(self, username, password, database):
        self.username = username
        self.password = password
        self.database = database

    def get_driver_data(self, customer_id):

        driver = {
            'Driver ID': 1,
            'First Name': 'Allison',
            'Middle Name': 'Susan',
            'Last Name': 'Crawley',
            'D.O.B': '04/06/1976',
        }
        driverTbl = {
            'table_name': 'Driver',
            'records': [driver],
            'keys': driver.keys()
        }

        driverHistory = [
            {
                'Driver ID': 1,
                'Event Type': 'TEST',
                'Brnach': 'SOUTH BEND',
                'Date': '01/01/2001'
            },
            {
                'Driver ID': 1,
                'Event Type': 'LIC ISSUE',
                'Brnach': 'SOUTH BEND',
                'Date': '03/05/2001'
            },
            {
                'Driver ID': 1,
                'Event Type': 'LIC RENEW',
                'Brnach': 'MAIN',
                'Date': '03/05/2002'
            },
        ]
        driverHistoryTbl = {
            'table_name': 'Driver History',
            'records': driverHistory,
            'keys': driverHistory[0].keys()
        }

        return [driverTbl, driverHistoryTbl]

    def get_customer_date(self, customer_id):

        person= {
            'Customer ID': 1,
            'First Name': 'Allison',
            'Middle Name': 'Susan',
            'Last Name': 'Crowley',
            'D.O.B.' : '04/05/1976'
        }

        personTbl = {
            'table_name': 'PERSON',
            'records': [person],
            'keys': person.keys()
        }

        addresses = [{
            'Address ID': 1,
            'Street 1': '45 Congress Street',
            'Street 2': 'Apt 3',
            'City': 'Portland',
            'State': 'ME',
            'Zip': '04201'
        },
        {
            'Address ID': 2,
            'Street 1': '123 Forrest Ave',
            'Street 2': '',
            'City': 'South Portland',
            'State': 'ME',
            'Zip': '04221'
        }]
        addressTbl = {
            'table_name': 'ADDRESS',
            'records': addresses,
            'keys': addresses[0].keys()
        }

        customer = {
            'Customer ID': 1,
            'First Name': 'Allyson',
            'Middle Name': 'Susan',
            'Last Name': 'Crowley',
            'Create Date': '01/23/2002'
        }
        customerTbl = {
            'table_name': 'CUSTOMER',
            'records': [customer],
            'keys': customer.keys()
        }

        return [customerTbl, personTbl, addressTbl]