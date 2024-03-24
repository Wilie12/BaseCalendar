package com.example.basecalendar.feature_calendar.util.navigation

import androidx.annotation.DrawableRes

data class NavigationItem(
    val title: String,
    @DrawableRes
    val icon: Int,
    val route: String
)
