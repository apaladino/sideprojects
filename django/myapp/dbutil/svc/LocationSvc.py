

class LocationSvc:
    def __init__(self, username, password, workDir):
        self.username = username
        self.password = password
        self.workDir = workDir


    def get_locations(self, database):
        locations =[
            ("1", "North Maine"),
            ("2", "Central Maine"),
            ("3", "Southern Maine"),
            ("4", "Boston"),
            ("5", "New York"),
            ("6", "Conway NH"),
            ("7", "Portland Maine"),
            ("8", "Vermont"),
            ("9", "Springfield"),
            ("10", "Washington D.C."),
        ]
        return locations