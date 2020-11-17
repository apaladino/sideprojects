package grails_web

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class UserapiControllerSpec extends Specification implements ControllerUnitTest<UserapiController> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }

    void "test users"() {
        when:
        controller.users()

        then:
        response.text == "[{ name: \"Bob jones\", age: 42 }, { name: \"Sally Baker\", age: 33 }]"
    }
}
