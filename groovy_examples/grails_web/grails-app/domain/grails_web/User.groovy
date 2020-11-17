package grails_web

class User implements Serializable{

    private String name
    private Integer age

    public User(String n, int a){
        this.name = n
        this.age = a

    }

    public String toString(){
        return "{ name: \"${this.name}\", age: ${this.age} }"
    }

    static constraints = {
    }
}
