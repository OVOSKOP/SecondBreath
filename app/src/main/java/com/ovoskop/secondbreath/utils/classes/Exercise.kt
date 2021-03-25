package com.ovoskop.secondbreath.utils.classes

import androidx.annotation.DrawableRes

data class Exercise(
    @DrawableRes val resImage: Int,
    val name: String,
    val amount: String,
    var isComplete: Boolean? = null
)