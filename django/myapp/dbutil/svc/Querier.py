import DriverInfo


class Querier:

    def __init__(self, username, password, database):
        self.username = username
        self.password = password
        self.database = database


    def find_driver_info(self, driver_number):
        """Do some query look up to find the driver,
        but for now just mock it out"""

        d1 = DriverInfo("DriverID1", "Driver_Num1", "Bob Toodles", "Cust_ID1", "address 1")
        d2 = DriverInfo("DriverID2", "Driver_Num2", "Bob Toodles", "Cust_ID2", "address 2")
        d3 = DriverInfo("DriverID3", "Driver_Num3", "Bob Toodles", "Cust_ID3", "address 3")
        return [d1, d2, d3]