package grailsapplication1

class Domain1 {
    String name   
    int age
    static constraints = {
        name(size: 3..50)
    }
}
