package com.moviesList.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moviesList.ui.components.MovieCard
import com.moviesList.ui.model.MoviesListScreenEvents
import com.moviesList.ui.model.MoviesListScreenState
import com.nour.core.ui.components.emptyStates.DefaultEmptyState
import com.nour.core.ui.components.lazyLayouts.PaginatedLazyColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListScreenRoot(
    state: MoviesListScreenState,
    onEvent: (MoviesListScreenEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        when {
            state.isLoading.not() && state.movies.isEmpty() -> {
                DefaultEmptyState(
                    modifier = Modifier.padding(paddingValues),
                )
            }
            else -> {
                PaginatedLazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.navigationBars),
                    items = state.movies,
                    keySelector = { it.id },
                    listState = lazyListState,
                    isAppending = state.isAppending,
                    isEndReached = state.endReached,
                    contentPadding = PaddingValues(
                        vertical = 14.dp,
                        horizontal = 14.dp
                    ),
                    onLoadMore = {
                        onEvent(MoviesListScreenEvents.OnLoadMore)
                    },
                    itemContent = { movie, index ->
                        MovieCard(
                            index = index,
                            movie = movie,
                            onClick = {
                                onEvent(MoviesListScreenEvents.OnNavigateToDetails(movie.id))
                            },
                        )
                    },
                    onRefresh = {
                        onEvent(MoviesListScreenEvents.OnRefresh)
                    },
                )
            }
        }
    }
}
