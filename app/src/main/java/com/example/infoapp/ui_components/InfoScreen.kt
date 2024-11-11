package com.example.infoapp.ui_components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.infoapp.ui.theme.CardGray
import com.example.infoapp.untils.ListItem

@Composable
fun InfoScreen(item: ListItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardGray
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AssetImage(
                imageName = item.imageName,
                contentDescription =item.title ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(5.dp)
            )
            HtmlLoader(htmlName = item.htmlName)
        }
    }
}

@Composable
fun HtmlLoader(htmlName: String) {
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open("html/$htmlName")
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    val htmlString = String(buffer)

    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadData(htmlString, "text/html", "UTF-8")
        }
    }, modifier = Modifier.fillMaxSize()
        )
}

