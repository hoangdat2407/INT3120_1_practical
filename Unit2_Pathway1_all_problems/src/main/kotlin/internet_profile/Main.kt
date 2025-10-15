package org.example.internet_profile

class Main {
}
fun main() {
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        // Fill in code
        print("Name: $name\nAge: $age\nLike to play $hobby. ")
        if (referrer != null) {
            print("Has a referrer named ${referrer.name}")
            if (referrer.hobby != null) {
                print(", who likes to play ${referrer.hobby}.\n")
            }
            else print(".")
        }
        else print("Doesn't have a referrer.\n")
    }
}