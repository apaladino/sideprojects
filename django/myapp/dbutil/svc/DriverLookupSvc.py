import DriverInfo


class DriverLookupSvc:

    """def __init__(self, username, password, database):
        self.username = username
        self.password = password
        self.database = database"""

    def lookup_driver_by_driver_number(self, driver_number):
        """Do some query look up to find the driver,
        but for now just mock it out"""

        personData = {
            'First Name' : 'Ron',
            'Last Name' : 'Toodles',
            'Address': '100 chestnut lane, Boston MA 42331',
            'D.O.B': '07-26-1976',
            'Driver ID': '122446',
            'Driver Number': driver_number,
            'Customer ID': '6699302'
        }

        records = [
            { 'record_id': '1', 'item': 'purchase registration', 'date': '09-12-2001'},
            { 'record_id': '2', 'item': 'insurance inquiry', 'date': '11-01-2002'},
            { 'record_id': '3', 'item': 'motore vehicle violation: speeding', 'date': '11-01-2004'},
            { 'record_id': '4', 'item': 'motore vehicle violation: running stop sign', 'date': '02-15-2008'},
        ]

        return {
            'personData': personData,
            'records': records
        }