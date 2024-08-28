package com.dotech.quotes.app.ui.quotes

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dotech.quotes.R
import com.dotech.quotes.app.ui.QuotesState
import com.dotech.quotes.app.ui.theme.QuotesTheme
import com.dotech.quotes.app.ui.utils.ErrorBox
import com.dotech.quotes.app.ui.utils.LoadingBox
import com.dotech.quotes.app.ui.utils.rememberRandomSampleImageUrl
import com.dotech.quotes.app.ui.utils.shareQuote
import com.dotech.quotes.common.Constants
import com.dotech.quotes.domain.models.ListQuotes
import com.dotech.quotes.domain.models.Quote

@Composable
fun QuotesScreen(
    navController: NavController,
    quotesViewModel: QuotesViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        val listQuotesState = quotesViewModel.quotesList.value
//        val listQuotesState = QuotesState(isLoading = false, data = Constants.getListQuotes(), error = "")
        Spacer(modifier = Modifier.height(8.dp))
        QuotesPager(listQuotesState)
    }
}

@Composable
fun QuotesPager(state: QuotesState<ListQuotes>) {
    when {
        state.isLoading -> LoadingBox()
        state.error.isNotBlank() -> ErrorBox(state.error,{})
        state.data != null -> HorizontalPagerWithFadeTransition(listQuotes = state.data)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithFadeTransition(modifier: Modifier = Modifier, listQuotes: ListQuotes) {
    val pagerState = rememberPagerState(pageCount = { listQuotes.count })
    HorizontalPager(
        state = pagerState,
        beyondBoundsPageCount = 2,
        modifier = modifier
            .fillMaxWidth()
            .height(800.dp)
            .padding(16.dp)
    ) { page ->
        QuotePageUI(
            pagerState = pagerState, page = page, list = listQuotes.quotes
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuotePageUI(
    modifier: Modifier = Modifier, pagerState: PagerState, page: Int, list: List<Quote>
) {
    Card(
        modifier = modifier.clickable { },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .alpha(0.6f),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(rememberRandomSampleImageUrl(width = 1200))
                        .placeholder(R.drawable.placeholder).allowHardware(false)
                        .error(R.drawable.placeholder_error).build(),
                    contentDescription = stringResource(id = R.string.app_name)
                )

                Column(
                    modifier = Modifier
                        .padding(30.dp, 70.dp, 30.dp, 25.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            shadow = Shadow(
                                color = Color.Black, offset = Offset(0.0f, 0.0f), blurRadius = 10f
                            )
                        ),
                        color = Color.White, text = list[page].content.uppercase()
                    )
                    Spacer(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth()
                    )
                    Text(text = list[page].author,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = FontFamily.SansSerif, shadow = Shadow(
                                color = Color.Black, offset = Offset(0.0f, 0.0f), blurRadius = 25f
                            )
                        ),
                        color = Color.White)
                }
                ShareQuoteButton(page,list)
            }
        }
    }
}

@Composable
fun ShareQuoteButton(page: Int, list: List<Quote>) {
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()
        .clip(shape = CircleShape)
        .padding(16.dp)){
        OutlinedButton(onClick = {shareQuote(
            quote = list[page],
            context = context
        )},
            modifier = Modifier
                .size(55.dp)
                .align(Alignment.BottomCenter),
            contentPadding = PaddingValues(0.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp,Color.White),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
        ) {
            Icon(imageVector = Icons.Default.Share, contentDescription = "Content Share")
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun QuotesScreenPreview() {
    QuotesTheme {
        QuotesScreen(navController = rememberNavController())
    }
}