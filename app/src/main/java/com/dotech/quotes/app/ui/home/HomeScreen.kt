package com.dotech.quotes.app.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dotech.quotes.app.Common
import com.dotech.quotes.app.ui.QuotesState
import com.dotech.quotes.app.ui.theme.QuotesTheme
import com.dotech.quotes.app.ui.utils.ErrorBox
import com.dotech.quotes.app.ui.utils.LoadingBox
import com.dotech.quotes.app.ui.utils.generateRandomGradient
import com.dotech.quotes.app.ui.utils.kalamFamily
import com.dotech.quotes.common.Constants
import com.dotech.quotes.domain.models.Quote


@Composable
fun HomeScreen(
    navController: NavController, viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        QuoteCard(viewModel.stateQuote.value,clickOnErrorCard = {viewModel.fetchRandomQuote()})
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp,24.dp,0.dp,0.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Icon(imageVector = Icons.Default.Refresh,
                contentDescription = " Home Icon Refresh",
                modifier = Modifier
                    .clip(MaterialTheme.shapes.large)
                    .clickable { viewModel.fetchRandomQuote() })
        }
    }
}

@Composable
fun QuoteCard(state: QuotesState<Quote>,clickOnErrorCard: ()->Unit) {
    when {
        state.isLoading -> LoadingBox()
        state.error.isNotBlank() -> ErrorBox(state.error, onClick = clickOnErrorCard)
        state.data!=null -> QuoteBox(quote = state.data)
    }
}

@Composable
fun QuoteBox(quote: Quote) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(
                brush = Brush.verticalGradient(
                    generateRandomGradient(),
                    startY = 0.0f,
                    endY = 1500.0F)
            )
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            Text(text = quote.content,
                fontFamily = kalamFamily,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "- ${quote.author}",
                fontFamily = kalamFamily,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                imageVector = Icons.Default.FormatQuote,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .clickable {
                        //Handle click action
                    }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun QuoteBoxPreview() {
    QuotesTheme {
        QuoteCard(
            QuotesState(isLoading = false, data = Constants.getQuote()),
            {}
        )
    }
}