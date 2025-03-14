package com.example.digikala.view

import android.content.Context
import android.graphics.Paint.Align
import android.inputmethodservice.Keyboard.Row
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Shapes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.ui.theme.DigikalaTheme
import com.example.digikala.ui.theme.LightGrayColor
import com.example.digikala.ui.theme.MenuItemColor
import com.example.digikala.ui.theme.PrimaryColor
import com.example.digikala.utils.BottomNavigationItem
import com.example.digikala.utils.Const
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigikalaTheme {
                Scaffold(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize()
                ) { innerPadding ->
                    BaseStructure(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseStructure(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            modifier = Modifier
                .background(Color.White),
            bottomBar = { BottomNavigationBar(navController, context = context) },
            topBar = {
                val route = currentRoute(navController)

                if (route == Const.CATEGORIES) {
                    TopAppBar(
                        title = { Text("صفحه دسته بندی ها") },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back Icon")
                            }
                        }
                    )
                } else {

                    Column {
                        Spacer(modifier = Modifier.height(40.dp))
                        searchBox()
                    }

//                    CenterAlignedTopAppBar(
//                        title = {
//
//                        }
//                        title = {
//                            Text(
//                                text = "فروشگاه",
//                                textAlign = TextAlign.Center
//                            )
//                        },
//                        navigationIcon = {
//                            IconButton(onClick = {
//                                coroutineScope.launch {
////                                    drawerState.open()
//                                }
//                            }) {
////                                Icon(Icons.Default.Menu, contentDescription = "Icon drawer", tint = Color.Black)
//                            }
//                        },
//                        modifier = Modifier.background(Color.Red)
//                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Const.HOME,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Const.HOME) { HomePage(navController) }
                composable(Const.CATEGORIES) { CategoriesPage(navController) }
                composable(Const.SHOPPING_CART) { ShoppingCartPage(navController) }
                composable(Const.PROFILE) { ProfilePage(navController) }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxWidth()

    ) { }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val entry = navController.currentBackStackEntryAsState().value
    return entry?.destination?.route
}

@Composable
fun BottomNavigationBar(navController: NavController, context: Context) {
    var itemSelected by remember { mutableStateOf(0) }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.DarkGray)
    )
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = (Color.White)
    ) {
        BottomNavigationItem().bottomNavigationItem(context = context).forEachIndexed { index, navigationItem ->

            NavigationBarItem(
                selected = index == itemSelected,
                label = {
                    Text(navigationItem.label)
                },
                icon = {
                    Icon(
                        navigationItem.icon,
                        contentDescription = navigationItem.label
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.DarkGray, // Icon color when not selected
                    selectedTextColor = Color.Black, // Label color when selected
                    unselectedTextColor = Color.DarkGray, // Label color when not selected
                    indicatorColor = MenuItemColor // Background color of selected item
                ),
                onClick = {
                    itemSelected = index
                    navController.navigate(navigationItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePage(navController: NavController) {
    val scrollState = rememberScrollState()

    var images = listOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        ImageSlider(
            images = listOf(
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground
            ),
            5000,
            false
        )

        PopularProducts("داغ ترین چند ساعت گذشته")

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        RandomProducts(images)

        PopularProducts("منتخب محصولات تخفیف و حراج")

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(LightGrayColor)
                .padding(top = 10.dp, bottom = 30.dp)
        )

        ImageSlider(

            images = listOf(
                R.drawable.ic_launcher_foreground
            ),
            5000, true
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )


        RowProductList()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(LightGrayColor)
                .padding(top = 10.dp, bottom = 30.dp)
        )

        RowProductList()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        ImageSlider(

            images = listOf(
                R.drawable.ic_launcher_foreground
            ),
            5000, true
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        RowProductList()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(LightGrayColor)
                .padding(top = 10.dp, bottom = 30.dp)
        )


        RowProductList()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        ImageSlider(

            images = listOf(
                R.drawable.ic_launcher_foreground
            ),
            5000, true
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        RowProductList()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(LightGrayColor)
                .padding(top = 10.dp, bottom = 30.dp)
        )

        RowProductList()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        ImageSlider(

            images = listOf(
                R.drawable.ic_launcher_foreground
            ),
            5000, true
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        RowProductList()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(LightGrayColor)
                .padding(top = 10.dp, bottom = 30.dp)
        )

        RowProductList()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
    }
}


@ExperimentalPagerApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(images: List<Int>, intervalMillis: Long = 5000, disableIndicator: Boolean = false) {
    val pagerState = rememberPagerState(images.size)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage) {
        while (true) {
            delay(intervalMillis)
            val nextPage = (pagerState.currentPage + 1) % images.size
            coroutineScope.launch {
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            count = images.size,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(15.dp, 0.dp, 15.dp, 0.dp),
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "Slider Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (!disableIndicator) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.Red,
                inactiveColor = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PopularProducts(titleHeader: String) {
    val pagerState = rememberPagerState(4)

    data class ContentItem(val title: String, val description: String)

    val popularContents = listOf(
        ContentItem("کره حیوانی پاستوریزه شکلی 50 گرم", "توضیحات مربوطه"),
        ContentItem("پوشک بچه هانیز سایز 4 بسته 34 عددی", "توضیحات مربوطه"),
        ContentItem("خامه فرادما یکاه مقدار 200 میلی لیتر", "توضیحات مربوطه"),

        ContentItem("کره حیوانی پاستوریزه شکلی 50 گرم", "توضیحات مربوطه"),
        ContentItem("پوشک بچه هانیز سایز 4 بسته 34 عددی", "توضیحات مربوطه"),
        ContentItem("خامه فرادما یکاه مقدار 200 میلی لیتر", "توضیحات مربوطه"),

        ContentItem("کره حیوانی پاستوریزه شکلی 50 گرم", "توضیحات مربوطه"),
        ContentItem("پوشک بچه هانیز سایز 4 بسته 34 عددی", "توضیحات مربوطه"),
        ContentItem("خامه فرادما یکاه مقدار 200 میلی لیتر", "توضیحات مربوطه"),

        ContentItem("کره حیوانی پاستوریزه شکلی 50 گرم", "توضیحات مربوطه"),
        ContentItem("پوشک بچه هانیز سایز 4 بسته 34 عددی", "توضیحات مربوطه"),
        ContentItem("خامه فرادما یکاه مقدار 200 میلی لیتر", "توضیحات مربوطه"),

        ContentItem("کره حیوانی پاستوریزه شکلی 50 گرم", "توضیحات مربوطه"),
        ContentItem("پوشک بچه هانیز سایز 4 بسته 34 عددی", "توضیحات مربوطه"),
        ContentItem("خامه فرادما یکاه مقدار 200 میلی لیتر", "توضیحات مربوطه"),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 26.dp, 8.dp, 14.dp)
    ) {
        // Header
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp)
                    .padding(end = 8.dp),
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "icon_title"
            )
            Text(
                text = titleHeader,
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            count = (popularContents.size / 3) + (if (popularContents.size % 3 == 0) 0 else 1),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(0.dp, 0.dp, 80.dp, 0.dp),
        ) { pageIndex ->
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 2.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val startIndex = pageIndex * 3
                val endIndex = minOf(startIndex + 3, popularContents.size)
                for (i in startIndex until endIndex) {
                    Card(
                        modifier = Modifier
                            .height(85.dp)
                            .width(280.dp)
                            .padding(4.dp)
                            .border(0.2.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = popularContents[i].title),
                                contentDescription = "productImage",
                                modifier = Modifier.size(70.dp)
                            )
                            Column(
                                modifier = Modifier.padding(start = 8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = popularContents[i].title,
                                    style = TextStyle(fontSize = 14.sp, color = Color.Black)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun RandomProducts(resultItem: List<Int>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(300.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(resultItem.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f)
                        .clip(RoundedCornerShape(15.dp))
                        .padding(2.dp),
                    elevation = 4.dp
                ) {
                    Image(
                        painter = painterResource(resultItem[index]),
                        contentDescription = "bannerImage",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    width: Dp, height: Dp,
    imageRes: Int,
    title: String,
    price: String,
    discount: String?,
    oldPrice: String?,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .width(width)
            .height(height)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(width * 0.75f)  // مقیاس تصویر بر اساس عرض آیتم
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    oldPrice?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall.copy(
                                textDecoration = TextDecoration.LineThrough,
                                color = Color.Gray
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    discount?.let {
                        Box(
                            modifier = Modifier
                                .background(Color.Red, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(text = it, color = Color.White, fontSize = 12.sp)
                        }
                    }
                }
                Text(
                    text = price,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        }
    }
}


// Sample data
data class Product(
    val imageRes: Int,
    val title: String,
    val price: String,
    val discount: String? = null,
    val oldPrice: String? = null
)

val productsList = listOf(
//    Product(R.drawable.ic_launcher_background, "هدفون بلوتوثی سامسونگ Galaxy Buds3 Pro", "10,390,000 تومان"),
    Product(
        R.drawable.ic_launcher_background,
        "هدفون مخصوص بازی بی سیم ریمکس مدل G1",
        "1,590,000 تومان",
        "21%",
        "2,000,000 تومان"
    )
)

@Composable
fun RowProductList() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.68f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "هدفون، هدست و هندزفری",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "مشاهده همه",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                color = PrimaryColor,
                modifier = Modifier.clickable { /* Handle click */ }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(productsList.size) { product ->
                ProductItem(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = productsList.get(product).imageRes,
                    title = productsList.get(product).title,
                    price = productsList.get(product).price,
                    discount = productsList.get(product).discount,
                    oldPrice = productsList.get(product).oldPrice
                )
            }

            item {
                Card(
                    modifier = Modifier
                        .width(itemWidth)
                        .height(itemHeight)
                        .padding(4.dp),
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = Color.White,
                    elevation = 0.dp,
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .border(1.dp, color = Color.Gray, shape = CircleShape)
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.ArrowBack, contentDescription = null)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "مشاهده همه",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        }
                    }
                }
            }


        }

    }
}

@Composable
fun searchBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))
                .background(PrimaryColor)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.Search,
                tint = Color.White,
                contentDescription = "search"
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                textAlign = TextAlign.Right,
                text = "جستجو کالا",
                style = TextStyle(fontSize = 14.sp, color = Color.White)
            )
        }
    }
}


@Composable
fun CategoriesPage(navController: NavController) {
}

@Composable
fun ShoppingCartPage(navController: NavController) {
}

@Composable
fun ProfilePage(navController: NavController) {
}

@Preview(showBackground = true)
@Composable
fun Preview() {
//    DigikalaTheme {
    BaseStructure(modifier = Modifier.background(Color.White))
//        HomePage(navController = rememberNavController())
//    RowProductList()

//        var images = listOf(
//            R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_background
//        )
//        RandomProducts(images)
//    }
}