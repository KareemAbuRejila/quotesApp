package com.dotech.quotes.app.ui.about

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dotech.quotes.app.ui.theme.QuotesTheme

@Composable
fun AboutScreen(
    navController : NavController
){
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            modifier = Modifier
                .size(50.dp),
            imageVector = Icons.Default.Android,
            contentDescription = "android icon",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(text = "Welcome to Quote App!", style = MaterialTheme.typography.headlineSmall)
        Text(text = "by KARIM ABURJEILA", style = MaterialTheme.typography.labelSmall)
            Button(onClick = {
                Toast.makeText(context, "Opening the Source code link", Toast.LENGTH_SHORT).show()
                uriHandler.openUri("https://github.com/KareemAbuRejila/")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Text(text = "Source Code")
            }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            optionItem(Icons.Default.Home,"Home")
            optionItem(Icons.Default.Mail,"Mail")
            optionItem(Icons.Default.Info,"Info")
        }
    }
}
@Composable
fun optionItem(icon:ImageVector, name: String){
    Row {
        Icon(imageVector = icon, contentDescription = name)
        Text(text = name)
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "${name}Icon")
    }
}

@Preview
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AboutScreenPreview(){
    QuotesTheme {
        AboutScreen(navController = rememberNavController())
    }
}