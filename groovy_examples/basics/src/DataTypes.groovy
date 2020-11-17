class DataTypes {

    static void main(String [] args){

        Comparator mc = { a, b -> a == b ? 0 : (a < b ? -1 : 1) }

        def list = [7, 4, 9, -6, -1, 11, 2, 3, -9, 5, -13]
        println list.max(mc)
        println list.min(mc)


    }
}

class Person implements Serializable {
    String name
    Integer age
}