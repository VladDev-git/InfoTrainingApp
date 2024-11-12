package com.example.infoapp.ui_components

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.infoapp.MainViewModel
import com.example.infoapp.ui.theme.CardGray
import com.example.infoapp.ui.theme.CardRed
import com.example.infoapp.ui.theme.FavRed
import com.example.infoapp.untils.ListItem

@Composable
fun MainListItem(
    mainViewModel: MainViewModel = hiltViewModel(),
    item: ListItem,
    onClick: (ListItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(10.dp)
            .clickable {
                onClick(item)
            },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, CardRed),
        colors = CardDefaults.cardColors(
            containerColor = CardGray
        )
    ) {
        ConstraintLayout (
            modifier = Modifier
                .fillMaxSize(),
        ) {
            val (image, text, favoriteButton) = createRefs()

            AssetImage(imageName = item.imageName,
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                text = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardRed)
                    .padding(10.dp)
                    .constrainAs(text) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            IconButton(
                onClick = {
                    mainViewModel.insertItem(
                        item.copy(isFav = !item.isFav)
                    )
                },
                modifier = Modifier
                    .constrainAs(favoriteButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = if (item.isFav) FavRed else Color.Black
                )
            }
        }
    }
}

@Composable
fun AssetImage(imageName: String, contentDescription: String, modifier: Modifier) {
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(imageName)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}