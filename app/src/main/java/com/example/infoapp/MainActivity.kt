package com.example.infoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.infoapp.ui.theme.InfoAppTheme
import com.example.infoapp.ui_components.DrawerMenu
import com.example.infoapp.ui_components.MainTopBar

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val topBarTitle by remember {
                mutableStateOf("Training center")
            }
            //val coroutineScope = rememberCoroutineScope()

            InfoAppTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        DrawerMenu()
                    }
                ) {
                    Scaffold(
                        topBar = {
                            MainTopBar(title = topBarTitle, drawerState)
                        }
                    ) {

                    }
                }
            }
        }
    }
}