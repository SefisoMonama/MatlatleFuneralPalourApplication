package com.sefiso.matlatlefuneralpalourapplication.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sefiso.matlatlefuneralpalourapplication.data.Initials
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.Locale

class HomeScreenViewModel @ViewModelInject constructor(

): ViewModel() {

    private val _dice = MutableLiveData<ArrayList<Initials>>(ArrayList())
    val dice: LiveData<ArrayList<Initials>> get() = _dice

    var time = MutableLiveData(SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date()))
}