class CreateRequest:

    def __init__(self, username, first_name, last_name, primary_loc, like_user, sid):
        self.username = username.upper()
        self.first_name = first_name.upper()
        self.last_name = last_name.upper()
        self.primary_loc = primary_loc.upper()
        self.like_user = like_user.upper()
        self.sid = sid
