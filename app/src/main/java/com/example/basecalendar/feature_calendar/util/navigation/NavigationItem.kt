package com.example.basecalendar.feature_calendar.util.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    @DrawableRes
    val icon: Int,
    val route: String
)
