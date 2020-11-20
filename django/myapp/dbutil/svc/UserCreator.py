import CreateRequest


class UserCreator:

    def create_user_like_existing_user(self, createRequest):
        print("Create_user_like_existing_user request")

        print(createRequest)

        print("User: %s created with matching settings as existing user: %s" % \
              (createRequest.username, createRequest.like_user))


