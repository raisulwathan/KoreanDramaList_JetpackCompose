package com.example.submission.ui.screen.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submission.R
import com.example.submission.di.Injection
import com.example.submission.model.Serial
import com.example.submission.ui.ViewModelFactory
import com.example.submission.ui.common.UiState
import com.example.submission.ui.components.FavoriteItem

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedSerialFavorite()
            }
            is UiState.Success -> {
                FavoriteContent(uiState.data)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    anime: List<Serial>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = stringResource(R.string.menu_favorite),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            items(anime) { item ->
                FavoriteItem(
                    animeId = item.id,
                    image = item.image,
                    title = item.title,
                    rate = item.rate,
                    isFavorite = item.isFav
                )
                Divider()
            }
        }
    }
}