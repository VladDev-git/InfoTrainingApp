package com.example.infoapp.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.infoapp.MainViewModel
import com.example.infoapp.ui.theme.MainBlack
import com.example.infoapp.untils.DrawerEvents
import com.example.infoapp.untils.ListItem
import kotlinx.coroutines.launch

@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel(), onClick: (ListItem) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val mainList = mainViewModel.mainList
    var topBarTitle by remember {
        mutableStateOf("Training center")
    }
    val coroutineScope = rememberCoroutineScope()
    mainViewModel.getAllItemsByCategory("")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerMenu() { event ->
                when(event) {
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle = event.title
                        mainViewModel.getAllItemsByCategory(event.title)
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
        ) { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(MainBlack)
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(mainList.value) { item ->
                        MainListItem(item = item) { listItem ->
                            onClick(listItem)
                        }
                    }
                }
            }
        }
    }
}



