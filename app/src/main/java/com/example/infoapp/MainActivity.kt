package com.example.infoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.infoapp.ui.theme.InfoAppTheme
import com.example.infoapp.ui.theme.MainBlack
import com.example.infoapp.ui_components.DrawerMenu
import com.example.infoapp.ui_components.MainTopBar
import com.example.infoapp.untils.DrawerEvents
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            var topBarTitle by remember {
                mutableStateOf("Training center")
            }
            val coroutineScope = rememberCoroutineScope()

            InfoAppTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        DrawerMenu() { event ->
                            when(event) {
                                is DrawerEvents.OnItemClick -> {
                                    topBarTitle = event.title
                                }
                            }
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            MainTopBar(title = topBarTitle, drawerState)
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(MainBlack)
                        ) {
//                            Image(painter = painterResource(id = R.drawable.drawer_header_background),
//                                contentDescription = "Background Image",
//                                modifier = Modifier.fillMaxSize(),
//                                contentScale = ContentScale.Fit,
//                                alpha = 0.8f
//                            )
                        }

                    }
                }
            }
        }
    }
}