package com.sefiso.matlatlefuneralpalourapplication.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.data.Initials
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.Locale

class HomeScreenViewModel @ViewModelInject constructor(

) : ViewModel() {

    private val _dice = MutableLiveData<ArrayList<Initials>>(ArrayList())
    val dice: LiveData<ArrayList<Initials>> get() = _dice

    var currentTime = MutableLiveData(SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()).toString())
    var morning = MutableLiveData<Boolean?>()
    var afternoon = MutableLiveData<Boolean?>()
    var evening = MutableLiveData<Boolean?>()
    var night = MutableLiveData<Boolean?>()

    //check for current time and display current part of the day to the user
    fun partOfTheDay() {
        if (currentTime.value!! <= "05:00" && currentTime.value!! <= "11:59") {
            morning.value = true
        } else if (currentTime.value!! >= "12:00" && currentTime.value!! <= "04:59") {
            afternoon.value = true
        } else if (currentTime.value!! >= "17:00" && currentTime.value!! <= "20:59") {
            evening.value = true
        } else {
            night.value = true
        }
    }

}