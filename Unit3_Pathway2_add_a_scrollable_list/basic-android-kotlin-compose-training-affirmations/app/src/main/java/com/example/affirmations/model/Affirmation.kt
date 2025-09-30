package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * @StringRes: note to res/values/strings.xml
 * @DrawableRes: note to res/drawable or res/mipmap
 * */
data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)