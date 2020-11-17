package grails_web

import grails.converters.JSON

class UserapiController {

    def users() {
        User[] users = genMockUsers()

        render users
    }

    private User[] genMockUsers(){
        return [ new User("Bob jones", 42), new User("Sally Baker", 33)]
    }
}
