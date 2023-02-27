package com.loureiro.msu.geoquiz2

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean, var answered: Boolean = false)
//Constructor takes three arguments an int and two booleans