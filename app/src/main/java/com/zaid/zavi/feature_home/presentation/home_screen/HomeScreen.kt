package com.zaid.zavi.feature_home.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.zaid.zavi.core.utils.AppIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(uiState: HomeScreenUiState) {

    Column(
        modifier = Modifier
//            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppTopBar(
            title = "Home",
            leadingIcon = AppIcons.NavDrawerIcon,
            leadingIconContentDescription = null,
            trailingIcon = AppIcons.WishlistIcon,
            trailingIcon2 = AppIcons.OrderIcon,
            trailingIconContentDescription = null,
            //    onTrailingIconClick = { navController.navigate(Screen.SearchScreen.route) },
            //    onTrailingIconClick2 = { navController.navigate(Screen.SearchScreen.route) },
        )

        val listOfCategories = listOf("All", "Kitchen", "Room", "Washroom")
        var selectedIndex by remember { mutableIntStateOf(0) }
        var selectedCategory by remember { mutableStateOf("All") }

        Spacer(modifier = Modifier.size(5.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
//                .horizontalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.size(15.dp))

            listOfCategories.forEachIndexed { index, value ->
                val isSelected = index == selectedIndex

                val modifier = rememberUpdatedState(
                    if (isSelected) {
                        Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(size = 40.dp)
                            )
                    } else {
                        Modifier
                    }
                )

                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable {
                                selectedIndex = index
                                selectedCategory = value
                            }
                            .padding(vertical = 7.dp, horizontal = 8.dp),
                        text = value,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.ExtraBold.takeIf { isSelected },
                        color = MaterialTheme.colorScheme.primary.takeIf { isSelected }
                            ?: MaterialTheme.colorScheme.onBackground
                    )
                    Box(
                        modifier = modifier.value
                            .height(2.dp)
                            .width(30.dp)
                    )
                }
                Spacer(modifier = Modifier.size(15.dp))
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(-20.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                LazyRow(
                    contentPadding = PaddingValues(start = 15.dp),
                    modifier = Modifier
                        .wrapContentHeight()
                ) {
                    items(count = uiState.products.size) { index ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .width(170.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .background(
                                        color = MaterialTheme.colorScheme.outline.copy(0.05f),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .height(127.dp)
                                        .width(188.dp),
                                    model = uiState.products[index].images[0],
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, end = 8.dp)
                                ) {

                                    Spacer(modifier = Modifier.size(10.dp))

                                    Text(
                                        text = uiState.products[index].name,
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 2
                                    )

                                    Spacer(modifier = Modifier.size(10.dp))

                                    Row(verticalAlignment = Alignment.Bottom) {
                                        Text(
                                            text = "₹ ",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.primary,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = uiState.products[index].price.toString(),
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.onBackground,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(10.dp))
                                }
                            }
                            Image(
                                modifier = Modifier
                                    .size(35.dp)
                                    .align(alignment = Alignment.BottomEnd)
                                    .padding(bottom = 12.dp, end = 5.dp),
                                painter = painterResource(id = AppIcons.UnselectedLikeIcon),
                                contentDescription = ""
                            )
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                    }
                }
            }

            item(span = { GridItemSpan(2) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Popular",
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = "See All",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Gray
                    )
                }
            }

            items(count = uiState.products.size) {

                Box(
                    modifier = Modifier.wrapContentSize().padding(horizontal = 15.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .wrapContentHeight()
                            .widthIn(250.dp)
                            .background(
                                color = MaterialTheme.colorScheme.outline.copy(0.05f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .height(127.dp)
                                .widthIn(250.dp),
                            model = uiState.products[it].images[0],
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 8.dp)
                        ) {

                            Spacer(modifier = Modifier.size(10.dp))

                            Text(
                                text = uiState.products[it].name,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2
                            )

                            Spacer(modifier = Modifier.size(10.dp))

                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = "₹ ",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = uiState.products[it].price.toString(),
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.size(10.dp))
                        }
                    }
                    Image(
                        modifier = Modifier
                            .size(35.dp)
                            .align(alignment = Alignment.BottomEnd)
                            .padding(bottom = 12.dp, end = 5.dp),
                        painter = painterResource(id = AppIcons.UnselectedLikeIcon),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    leadingIcon: Int?,
    leadingIconContentDescription: String?,
    trailingIcon: Int?,
    trailingIcon2: Int?,
    trailingIconContentDescription: String?,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.background),
    //    onTrailingIconClick: () -> Unit = {},
    //    onTrailingIcon2Click: () -> Unit = {},
) {
    TopAppBar(
        title = {
            if (title.isNotBlank()) Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp),
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            if ((trailingIcon != null) && (trailingIcon2 != null)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        painter = painterResource(id = trailingIcon),
                        contentDescription = trailingIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Icon(
                        modifier = Modifier.padding(end = 15.dp),
                        painter = painterResource(id = trailingIcon2),
                        contentDescription = trailingIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        colors = colors,
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(uiState = HomeScreenUiState())
}