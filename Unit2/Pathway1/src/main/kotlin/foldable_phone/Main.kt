package org.example.foldable_phone

class Main {
}
open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}
class FoldablePhone:Phone(false){
    var isFold: Boolean = false
    override fun switchOn() {
        if(isFold) isScreenLightOn = true
        else isScreenLightOn = false
    }
    fun fold() {
        isFold = true

    }

    fun unfold() {
        isFold = false
    }
}