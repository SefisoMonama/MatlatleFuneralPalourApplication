package com.sefiso.matlatlefuneralpalourapplication.data

import java.util.*

class User() {
    var name: String = ""
    var surname: String = ""
    var idNumber: String =""
    var contactDetails: String =""
    var email: String = ""

    fun User() {
    }

    fun User(name: String, surname: String, idNumber: String, contactDetails: String, email: String) {
        this.name = name.capitalize(Locale.ROOT)
        this.surname = surname.capitalize(Locale.ROOT)
        this.idNumber = idNumber
        this.contactDetails = contactDetails
        this.email = email
    }

}