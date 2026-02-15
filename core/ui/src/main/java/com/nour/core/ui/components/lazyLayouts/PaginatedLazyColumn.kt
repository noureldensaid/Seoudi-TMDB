package com.nour.core.ui.components.lazyLayouts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(FlowPreview::class)
@Composable
fun <T> PaginatedLazyColumn(
    modifier: Modifier = Modifier,
    items: ImmutableList<T>,
    keySelector: (T) -> Any,
    listState: LazyListState,
    isAppending: Boolean,
    isEndReached: Boolean,
    hasFooter: Boolean = true,
    onLoadMore: () -> Unit,
    onRefresh: () -> Unit,
    itemContent: @Composable (item: T, index: Int) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    prefetchDistance: Int = 3,
) {
    var isRefreshing by remember { mutableStateOf(false) }

    // Derived state to determine when to load more items
    val shouldLoadMore = remember {
        derivedStateOf {
            val total = listState.layoutInfo.totalItemsCount
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            lastVisible >= (total - prefetchDistance) &&
                    !isEndReached &&
                    !isAppending &&
                    !isRefreshing // avoid load-more while refreshing
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .filter { it }
            .collect { onLoadMore() }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier
    ) {
        LazyColumn(
            state = listState,
            contentPadding = contentPadding,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            itemsIndexed(
                items = items,
                key = { _, item -> keySelector(item) }
            ) { index, item ->
                itemContent(item,index)
                if (index == items.lastIndex) {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }

            if (hasFooter) {
                item {
                    AnimatedVisibility(isEndReached.not() && isAppending) {
                        LoadingFooter()
                    }
                }
                item {
                    AnimatedVisibility(isEndReached) {
                        EndReachedFooter()
                    }
                }
            }
        }
    }
}