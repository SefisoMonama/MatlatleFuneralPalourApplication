package com.sefiso.matlatlefuneralpalourapplication

class User {
    var name: String = ""
    var surname: String = ""
    var idNumber: Number = TODO()
    var contactDetails: Number = TODO()
    var email: String = ""
    var password: Char
    var confirmPassword: Char



    fun User(name: String, surname: String, idNumber: Int, contactDetails: Int,email: String, password: Char, confirmPassword: Char){
        this.name = name
        this.surname = surname
        this.idNumber = idNumber
        this.contactDetails = contactDetails
        this.email = email
        this.password = password
        this.confirmPassword = confirmPassword
    }
}