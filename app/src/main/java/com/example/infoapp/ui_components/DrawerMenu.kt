package com.example.infoapp.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.infoapp.R
import com.example.infoapp.ui.theme.CardRed
import com.example.infoapp.ui.theme.CardWhite

@Composable
fun DrawerMenu() {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight()
    ) {
        Image(painter = painterResource(id = R.drawable.bg_3),
            contentDescription = "Drawer Background Image",
            modifier = Modifier
                .fillMaxSize(),
            alpha = 0.5f,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Header()
            Body()
        }
    }
}

@Composable
fun Header() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, CardRed)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(painter = painterResource(id = R.drawable.drawer_header_background),
                contentDescription = "Drawer Header Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Training Center",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardRed)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun Body() {
    val list = stringArrayResource(id = R.array.drawer_list)
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(list) { index, item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CardWhite
                ),
            ) {
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                        .padding(10.dp)
                        .wrapContentWidth(),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}






