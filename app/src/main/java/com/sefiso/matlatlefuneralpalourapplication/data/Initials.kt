package com.sefiso.matlatlefuneralpalourapplication.data

import com.sefiso.matlatlefuneralpalourapplication.R

class Initials(var name: String) {

    //Get Image Resources
    fun getInitialImageResource(): Int{
        return when(name){
            "A" -> R.drawable.a
            "B" -> R.drawable.b
            "c" -> R.drawable.c
            "D" -> R.drawable.d
            "E" -> R.drawable.e
            "F" -> R.drawable.f
            "G" -> R.drawable.g
            "H" -> R.drawable.h
            "I" -> R.drawable.i
            "J" -> R.drawable.j
            "K" -> R.drawable.k
            "L" -> R.drawable.l
            "M" -> R.drawable.m
            "N" -> R.drawable.n
            "O" -> R.drawable.o
            "P" -> R.drawable.p
            "Q" -> R.drawable.q
            "R" -> R.drawable.r
            "S" -> R.drawable.s
            "T" -> R.drawable.t
            "U" -> R.drawable.u
            "V" -> R.drawable.v
            "W" -> R.drawable.w
            "X" -> R.drawable.x
            "Y" -> R.drawable.y
            "Z" -> R.drawable.z
            else -> R.drawable.a
        }
    }
}