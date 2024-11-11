package com.example.infoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.infoapp.ui.theme.InfoAppTheme
import com.example.infoapp.ui_components.InfoScreen
import com.example.infoapp.ui_components.MainScreen
import com.example.infoapp.untils.ListItem
import com.example.infoapp.untils.Routes
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var item: ListItem? = null

            InfoAppTheme {
                val systemUiController = rememberSystemUiController()
                val darkTheme = true // або ваша логіка для визначення теми

                if (darkTheme) {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent, // фон статус-бара
                        darkIcons = false // іконки світлі (як для темного фону)
                    )
                } else {
                    systemUiController.setSystemBarsColor(
                        color = Color.White, // фон статус-бара
                        darkIcons = true // іконки темні (як для світлого фону)
                    )
                }

                NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
                    composable(Routes.MAIN_SCREEN) {
                        MainScreen() { listItem ->
                            item = listItem
                            navController.navigate(Routes.INFO_SCREEN)
                        }
                    }

                    composable(Routes.INFO_SCREEN) {
                        InfoScreen(item = item?: ListItem(null, "", "", "", "", false))
                    }
                }

            }
        }
    }
}



