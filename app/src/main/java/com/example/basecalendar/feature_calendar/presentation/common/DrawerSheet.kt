package com.example.basecalendar.feature_calendar.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.basecalendar.feature_calendar.data.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerSheet(
    navController: NavController,
    screen: String,
    selectedItemIndex: Int,
    scope: CoroutineScope,
    state: DrawerState,
    onChange: (Int) -> Unit
) {
    ModalDrawerSheet {
        Text(
            text = "Calendar",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
        Constants.listOfScreens.forEachIndexed { index, navigationItem ->
            NavigationDrawerItem(
                label = { Text(text = navigationItem.title) },
                selected = (index == selectedItemIndex),
                onClick = {
                    navController.navigate(navigationItem.route) {
                        popUpTo(screen) {
                            inclusive = true
                        }
                    }
                    onChange(index)
                    scope.launch {
                        state.close()
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = navigationItem.icon),
                        contentDescription = navigationItem.title
                    )
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}