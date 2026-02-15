package com.nour.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nour.core.ui.components.emptyStates.DefaultEmptyState
import com.nour.core.ui.components.lazyLayouts.PaginatedLazyColumn
import com.nour.ui.components.MovieCard
import com.nour.ui.model.MoviesListScreenEvents
import com.nour.ui.model.MoviesListScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListScreenRoot(
    state: MoviesListScreenState,
    onEvent: (MoviesListScreenEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val isCollapsed = remember { derivedStateOf { scrollBehavior.state.collapsedFraction > 0.5 } }

    val topAppBarElementColor = if (isCollapsed.value) {
        Color.Transparent
    } else {
        MaterialTheme.colorScheme.onPrimary
    }

    val topAppBarTextSize = (22 + (28 - 22) * (1 - scrollBehavior.state.collapsedFraction)).sp

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Popular Movies",
                        fontSize = topAppBarTextSize
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = topAppBarElementColor,
                    titleContentColor = topAppBarElementColor,
                    actionIconContentColor = topAppBarElementColor
                ),
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        when {
            state.isLoading.not() && state.movies.isEmpty() -> {
                DefaultEmptyState()
            }

            else -> {
                PaginatedLazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding()),
                    items = state.movies,
                    keySelector = { it.id },
                    listState = listState,
                    isAppending = state.isAppending,
                    isEndReached = state.endReached,
                    contentPadding = PaddingValues(14.dp),
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
