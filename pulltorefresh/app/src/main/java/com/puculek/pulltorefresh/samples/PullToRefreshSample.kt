package com.puculek.pulltorefresh.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.puculek.pulltorefresh.SwipeRefreshLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun PullToRefreshPreview() {
    var isRefreshing: Boolean by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var elements by remember { mutableStateOf(listOf(5, 4, 3, 2, 1)) }
    SwipeRefreshLayout(
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            scope.launch {
                delay(1000)
                elements = listOf(elements.size + 1) + elements
                isRefreshing = false
            }

        }
    ) {
        LazyColumn {
            elements.map {
                item {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .background(Color.Yellow)
                            .padding(16.dp)
                    ) {
                        Text("$it", fontSize = 24.sp)
                        Text(
                            text = """
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                            ssed do eiusmod tempor incididunt ut labore et dolore magna aliqua
                        """.trimIndent()
                        )
                    }
                }
            }
        }
    }
}