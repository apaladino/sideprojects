package grails_web


import spock.lang.Specification

class RomanizerServiceSpec extends Specification {

    RomanizerService service

    def setup() {
        service = new RomanizerService()
    }

    def cleanup() {
    }

    void "test romanizer"() {

        def expectedInput = [4, 5, 10, 11, 21]

        String output = service.romanizeInt(1)
        expect: "output should equal 'IV'"
            output == "IV"

    }
}
