package com.loureiro.msu.geoquiz2

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean)   //Constructor takes two arguments an int and boolean