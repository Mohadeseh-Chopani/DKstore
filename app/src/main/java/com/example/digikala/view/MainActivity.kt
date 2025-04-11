package com.example.digikala.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.digikala.R
import com.example.digikala.data.models.home.Home1
import com.example.digikala.data.models.home.Home2
import com.example.digikala.data.models.home.Home3
import com.example.digikala.data.models.home.Home4
import com.example.digikala.data.models.home.Home5
import com.example.digikala.data.models.home.Home6
import com.example.digikala.data.models.home.Home7
import com.example.digikala.data.models.home.Home8
import com.example.digikala.data.models.home.HomePageData
import com.example.digikala.data.models.home.MainBanner
import com.example.digikala.data.models.home.Product
import com.example.digikala.data.models.home.Product2
import com.example.digikala.databinding.ProductInfoSectionBinding
//import com.example.digikala.data.models.home.Product2
import com.example.digikala.network.StoreApiProvider
import com.example.digikala.ui.theme.BackgroundColor
import com.example.digikala.ui.theme.DarkGreen
import com.example.digikala.ui.theme.Green
import com.example.digikala.ui.theme.LightBlue
import com.example.digikala.ui.theme.LightGrayColor
import com.example.digikala.ui.theme.MenuItemColor
import com.example.digikala.ui.theme.PrimaryColor
import com.example.digikala.ui.theme.StarColor
import com.example.digikala.utils.BottomNavigationItem
import com.example.digikala.utils.Const
import com.example.digikala.utils.NetworkState
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.*
import com.google.android.gms.wallet.button.ButtonConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) { innerPadding ->
                BaseStructure(
                    modifier = Modifier.padding(innerPadding), homeViewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseStructure(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            modifier = Modifier
                .background(BackgroundColor),
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
                }
                if (route == Const.PRODUCT_DETAILS) {
                    TopAppBar(
                        title = { Text("") },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.Default.Close, contentDescription = "close icon")
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
                composable(Const.HOME) { HomePage(navController, homeViewModel) }
                composable(Const.PRODUCT_DETAILS) { ProductDetails(navController) }
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

    if (currentRoute(navController) == Const.PRODUCT_DETAILS) {

        Surface(
            tonalElevation = 4.dp,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("قیمت: ۴۱۹,۹۰۰ تومان", style = MaterialTheme.typography.bodyLarge)
                    Text("٪۵۸ تخفیف", color = Color.Red, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { /* افزودن به سبد خرید */ },
                    colors = androidx.compose.material.ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "افزودن به سبد خرید",
                        color = Color.White
                    )
                }
            }
        }
    } else {
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
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePage(navController: NavController, homeViewModel: HomeViewModel) {
    val scrollState = rememberScrollState()
    val homeData by homeViewModel.homeData.collectAsState()
    val data: HomePageData

    LaunchedEffect(Unit) {
        homeViewModel.getHomeData(StoreApiProvider.API_KEY)
        Log.d("MOX", "HomePage: " + homeData)
    }

    when (homeData) {
        is NetworkState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryColor)
            }
        }

        is NetworkState.Success -> {
            data = (homeData as NetworkState.Success<HomePageData>).data

            Box(
                Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {

                    ImageSlider(
                        images = listOf(
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/71a4767cadf6b16bb0d31bfa2e6e4905c5336622_1741782042.jpg?x-oss-process=image/quality,q_95/format,webp"
                            ),
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/832f867ccd35f749becea69a10edf5f32d0fe144_1742021801.jpg?x-oss-process=image/quality,q_95/format,webp"
                            ),
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/7ae7211fd56c0e9436550260f92748ef8200d8ec_1742030472.jpg?x-oss-process=image/quality,q_95/format,webp"
                            ),
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/8ae2cc7c57f8731c1aa7e7d31ea64c187cb63c9d_1742234352.jpg?x-oss-process=image/quality,q_95/format,webp"
                            ),
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/75e4dc2851ff84eb0f92dbf526d860e50998bae7_1741018587.jpg?x-oss-process=image/quality,q_95/format,webp"
                            )
                        ),
                        5000,
                        false
                    )

                    productSliderTrending(
                        data.result.trending.title,
                        data.result.trending.products,
                        navController
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )

                    RandomProducts(
                        listOf(
                            "https://dkstatics-public.digikala.com/digikala-adservice-banners/a4f76ea14e13253026ffdbe78c548524a9c87dac_1741782852.gif?x-oss-process=image?x-oss-process=image/format,webp",
                            "https://dkstatics-public.digikala.com/digikala-adservice-banners/34c29c4b72b7fb3f3d2ded413f28bd23935df276_1741521446.jpg?x-oss-process=image/quality,q_95/format,webp",
                            "https://dkstatics-public.digikala.com/digikala-adservice-banners/67dfc40d15d97cd7bdc9df70f5db436863182e7b_1741766056.jpg?x-oss-process=image/quality,q_95/format,webp",
                            "https://dkms.digikala.com/static/files/3ea4f643.jpg?x-oss-process=image/format,webp"
                        )
                    )

                    productSliderSellingAndSales(
                        data.result.selling_and_sales.title,
                        data.result.selling_and_sales.products
                    )

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
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/3e2b31e7d697aa2724d85e0fc4b758ab702662e8_1742026574.jpg?x-oss-process=image/quality,q_95/format,webp"
                            )
                        ),
                        5000, true
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )


                    RowProductList1(data.result.home_1)

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

                    RowProductList2(data.result.home_2)

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )

                    ImageSlider(

                        images = listOf(
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/7b5b9b8e19e407f4df329cef5ef2b35700ce7144_1742027906.jpg?x-oss-process=image/quality,q_95/format,webp"
                            )
                        ),
                        5000, true
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )

                    RowProductList3(data.result.home_3)

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


                    RowProductList4(data.result.home_4)

//                Spacer(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(24.dp)
//                )

//                ImageSlider(
//
//                    images = listOf(
//                        MainBanner(
//                            "https://dkstatics-public.digikala.com/digikala-adservice-banners/832f867ccd35f749becea69a10edf5f32d0fe144_1742021801.jpg?x-oss-process=image/quality,q_95/format,webp"
//                        )
//                    ),
//                    5000, true
//                )
//                Spacer(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(24.dp)
//                )
//
//                RowProductList5(data.result.h)

//                Spacer(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(24.dp)
//                )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .background(LightGrayColor)
                            .padding(top = 10.dp, bottom = 30.dp)
                    )

                    RowProductList5(data.result.home_5)

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )

                    ImageSlider(

                        images = listOf(
                            MainBanner(
                                "https://www.digikala.com/mag/wp-content/uploads/2025/03/Pistachio1.jpg"
                            )
                        ),
                        5000, true
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )

                    RowProductList6(data.result.home_6)

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

                    RowProductList7(data.result.home_7)

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )

                    RowProductList8(data.result.home_8)

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }
            }
        }

        is NetworkState.UnSuccess -> {

        }

        is NetworkState.Failure -> {

        }
    }
    var images = listOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background
    )
}

@ExperimentalPagerApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(images: List<MainBanner>, intervalMillis: Long = 5000, disableIndicator: Boolean = false) {
    val pagerState = rememberPagerState(images.size)
    val coroutineScope = rememberCoroutineScope()

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory()) //  support WebP and GIF
        }
        .build()

    LaunchedEffect(key1 = pagerState.currentPage) {
        if (images.isNotEmpty()) {
            while (true) {
                delay(intervalMillis)
                val nextPage = (pagerState.currentPage + 1) % images.size
                coroutineScope.launch {
                    pagerState.animateScrollToPage(nextPage)
                }
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
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page].image_url)
                        .crossfade(true)
                        .build(),
                    imageLoader = imageLoader
                ),
                contentDescription = "Slider Image",
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(150.dp)
                    .aspectRatio(16f / 9f)
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

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun productSliderTrending(titleHeader: String, products: List<Product>, navController: NavController) {
    val pagerState = rememberPagerState(4)

    Log.i("MOX", "PopularProducts: " + products.size)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 26.dp, 8.dp, 14.dp)
    ) {
        // Header
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .height(38.dp)
                    .width(38.dp)
                    .padding(end = 8.dp),
                painter = painterResource(R.drawable.fire_icon),
                contentDescription = "icon_title"
            )
            Text(
                text = titleHeader,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 22.sp,
                color = Color.Black,
                maxLines = 1,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            count = (products.size / 3) + (if (products.size % 3 == 0) 0 else 1),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(0.dp, 0.dp, 70.dp, 0.dp),
        ) { pageIndex ->
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 2.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val startIndex = pageIndex * 3
                val endIndex = minOf(startIndex + 3, products.size)
                for (i in startIndex until endIndex) {
                    Card(
                        modifier = Modifier
                            .height(100.dp)
                            .width(320.dp)
                            .padding(4.dp)
                            .border(0.2.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                            navController.navigate(Const.PRODUCT_DETAILS) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = products[i].images.main),
                                contentDescription = "productImage",
                                modifier = Modifier.size(70.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 8.dp, end = 8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = products[i].title_fa,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                    textAlign = TextAlign.Start,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun productSliderSellingAndSales(titleHeader: String, products: List<Product2>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 26.dp, 8.dp, 14.dp)
    ) {
        // Header
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .height(38.dp)
                    .width(38.dp)
                    .padding(end = 8.dp),
                painter = painterResource(R.drawable.fire_icon),
                contentDescription = "icon_title"
            )
            Text(
                text = titleHeader,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 22.sp,
                color = Color.Black,
                maxLines = 1,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            count = (products.size / 3) + (if (products.size % 3 == 0) 0 else 1),
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
                val endIndex = minOf(startIndex + 3, products.size)
                for (i in startIndex until endIndex) {
                    Card(
                        modifier = Modifier
                            .height(100.dp)
                            .width(320.dp)
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
                                painter = rememberAsyncImagePainter(model = products[i].images.main),
                                contentDescription = "productImage",
                                modifier = Modifier.size(70.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp, end = 8.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = products[i].title_fa,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                    textAlign = TextAlign.Start,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
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
fun RandomProducts(resultItem: List<String>) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory()) //  support WebP and GIF
        }
        .build()

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
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(resultItem[index])
                                .crossfade(true)
                                .build(),
                            imageLoader = imageLoader
                        ),
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
    imageRes: String,
    title: String,
    price: Long,
    discount: Long?,
    oldPrice: Long?,
    modifier: Modifier = Modifier
) {


    fun convertToPersianDigits(number: String): String {
        val persianDigits = arrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
        val builder = StringBuilder()
        number.forEach { char ->
            if (char.isDigit()) {
                builder.append(persianDigits[char.toString().toInt()])
            } else {
                builder.append(char)
            }
        }
        return builder.toString()
    }

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
                painter = rememberAsyncImagePainter(model = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(width * 0.75f)  // مقیاس تصویر بر اساس عرض آیتم
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp),
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Start),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (oldPrice != price) {
                        discount?.let {
                            Box(
                                modifier = Modifier
                                    .background(Color.Red, shape = RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = convertToPersianDigits(it.toString() + "%"),
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }

                        oldPrice?.let {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp),
                                text = convertToPersianDigits(it.toString()),
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    textDecoration = TextDecoration.LineThrough,
                                    color = Color.Gray
                                )
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = convertToPersianDigits(price.toString()),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        }
    }
}

//val productsList = listOf(
//    Product(R.drawable.ic_launcher_background, "هدفون بلوتوثی سامسونگ Galaxy Buds3 Pro", "10,390,000 تومان"),
//    Product(
//        R.drawable.ic_launcher_background,
//        "هدفون مخصوص بازی بی سیم ریمکس مدل G1",
//        "1,590,000 تومان",
//        "21%",
//        "2,000,000 تومان"
//    )
//)

@Composable
fun RowProductList1(result: Home1) {
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
                text = result.title,
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
            items(result.products.size) { product ->
                Log.d("MOX", "RowProductList1: " + result.products.get(product))
                ProductItem(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = 16,
                    oldPrice = result.products.get(product).price.rrp_price
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
fun RowProductList2(result: Home2) {
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
                text = result.title,
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
            items(result.products.size) { product ->
                ProductItem(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = 16,
                    oldPrice = result.products.get(product).price.rrp_price
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
fun RowProductList3(result: Home3) {
    var counter = remember { mutableStateOf(1) }
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
                text = result.title,
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
            items(result.products.size) { product ->
                ProductItem(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = 16,
                    oldPrice = result.products.get(product).price.rrp_price
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
fun RowProductList4(result: Home4) {
    var counter = remember { mutableStateOf(1) }
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
                text = result.title,
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
            items(result.products.size) { product ->
                ProductItem(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = 16,
                    oldPrice = result.products.get(product).price.rrp_price
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
fun RowProductList5(result: Home5) {
    var counter = remember { mutableStateOf(1) }
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
                text = result.title,
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
            items(result.products.size) { product ->
                ProductItem(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = 16,
                    oldPrice = result.products.get(product).price.rrp_price
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
fun RowProductList6(result: Home6) {
    var counter = remember { mutableStateOf(1) }
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
                text = result.title,
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
            items(result.products.size) { product ->
                ProductItem(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = 16,
                    oldPrice = result.products.get(product).price.rrp_price
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
fun RowProductList7(result: Home7) {
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
                text = result.title,
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
            items(result.products.size) { product ->
                ProductItem(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = 16,
                    oldPrice = result.products.get(product).price.rrp_price
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
fun RowProductList8(result: Home8) {
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
                text = result.title,
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
            items(result.products.size) { product ->
                ProductItem(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price!!,
                    discount = 16,
                    oldPrice = result.products.get(product).price.rrp_price
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

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun ProductDetails(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        item {
            ProductPageSlider()
        }
        item {
            ProductInformation()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductPageSlider(intervalMillis: Long = 8000) {
    val images = listOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background
    )

    val pagerState = rememberPagerState(images.size)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage) {
        if (images.isNotEmpty()) {
            while (true) {
                delay(intervalMillis)
                val nextPage = (pagerState.currentPage + 1) % images.size
                coroutineScope.launch {
                    pagerState.animateScrollToPage(nextPage)
                }
            }
        }
    }

    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        state = pagerState,
        count = images.size
    ) {
        Image(
            painter = painterResource(images[it]),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 8.dp, end = 20.dp, start = 20.dp)
                .shadow(8.dp, shape = RoundedCornerShape(8.dp)),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

    HorizontalPagerIndicator(
        pagerState = pagerState,
        activeColor = Color.Red,
        inactiveColor = Color.LightGray,
        indicatorWidth = 12.dp,
        indicatorHeight = 6.dp,
        indicatorShape = RoundedCornerShape(3.dp)
    )
}

@Composable
fun ProductInformation() {

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 18.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "گوشی موبایل سامسونگ",
                    color = LightBlue,
                    fontSize = 16.sp
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 4.dp),
                    text = "/",
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )

                Text(
                    text = "سامسونگ",
                    color = LightBlue,
                    textAlign = TextAlign.Right,
                    fontSize = 16.sp
                )
            }

            Text(
                "title",
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(horizontal = 18.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                item {
                    Row {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
                        Text(
                            "4",
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .align(Alignment.CenterVertically),
                            fontSize = 14.sp
                        )
                        Text(
                            "(امتیاز 36 خریدار)",
                            color = Color.Gray,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                items(1) {
                    UserActionButton("31 دیدگاه")
                    UserActionButton("4 پرسش و پاسخ")
                    UserActionButton("4 پرسش و پاسخ")
                    UserActionButton("4 پرسش و پاسخ")
                }
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 5.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                items(1) {
                    ProductSpecificationsButton("کاربرد", " محافظت,ضد حساسیت")
                    ProductSpecificationsButton("جنس محفظه نگهدارنده", " پلاستیک")
                    ProductSpecificationsButton("مبدا", "ایران")
                }
            }

            ProductColorList()

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(LightGrayColor)
            )

            ProductInfoXmlView()

            ProductDetailsScreen()
        }
    }
}


@Composable
fun UserActionButton(title: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .background(LightGrayColor, shape = RoundedCornerShape(15.dp))
            .padding(horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text(
                title,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
        }
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun ProductColorList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 20.dp)
    ) {
        Row {
            Text(
                text = "رنگ:",
                fontSize = 19.sp
            )
            Text(
                text = "سفید",
                fontSize = 19.sp
            )
        }

        LazyRow(
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            items(2) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .border(1.dp, color = LightBlue, shape = RoundedCornerShape(15.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AndroidView(
                           factory = {context ->
                               ImageView(context).apply {
                                   setBackgroundColor(R.color.purple_200)
                               }
                           },
                            Modifier.width(24.dp)
                                .height(24.dp)
                                .padding(horizontal = 6.dp, vertical = 8.dp)
                        )

                        Text(
                            modifier = Modifier
                                .padding(end = 12.dp),
                            text = "قرمز"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductSpecificationsButton(title: String, feature: String) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .padding(horizontal = 4.dp)
            .background(LightGrayColor, shape = RoundedCornerShape(7.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text(
                title,
                modifier = Modifier
                    .padding(bottom = 2.dp),
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Text(
                feature,
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = FontFamily.Default
            )
        }
    }
}

@Composable
fun ProductInfoXmlView() {

    var productInfoBinding: ProductInfoSectionBinding? = null

    AndroidView(
        factory = { context ->
            productInfoBinding = ProductInfoSectionBinding.inflate(LayoutInflater.from(context))
            productInfoBinding!!.root
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

    }
}

data class Review(
    val username: String,
    val rating: Int,
    val comment: String,
    val date: String
)

data class Question(
    val question: String,
    val answer: String?,
    val date: String
)

@Composable
fun ProductDetailsScreen() {
    val reviews = listOf(
        Review("کاربر دیجی‌کالا", 5, "کرم عالی برای بچه‌ها", "۳۰ شهریور"),
        Review("زهرا علیزاده", 1, "ناراضی بودم", "۱ خرداد")
    )

    val questions = listOf(
        Question("سلام انقضا؟", "1406/03", "امروز"),
        Question("سلام تاریخ انقضا محصول؟", "سلام وقت بخیر 1406/03", "امروز")
    )

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {


            SectionTitle(title = "دیدگاه‌ها")

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(reviews.size) { index ->
                    ReviewItem(reviews[index])
                }
            }


            SectionTitle(title = "پرسش و پاسخ")

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                items(questions.size) { question ->
                    QuestionItem(questions[question])
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}

@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .padding(horizontal = 8.dp)
            .border(0.2.dp, Color.Gray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = review.username,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .background(Green, shape = RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "خریداران",
                        color = DarkGreen,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(review.rating) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = StarColor)
                }
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            Text(
                text = review.comment,
                textAlign = TextAlign.Right,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            Text(
                text = review.date,
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right
            )
        }
    }
}

@Composable
fun QuestionItem(question: Question) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .width(280.dp)
            .height(150.dp)
            .padding(bottom = 20.dp)
            .padding(horizontal = 4.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Icon(Icons.Default.Star, contentDescription = null)

                Text(
                    text = question.question,
                    textAlign = TextAlign.Right,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
            if (!question.answer.isNullOrEmpty()) {
                Text(
                    text = "پاسخ: ${question.answer}",
                    textAlign = TextAlign.Right,
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            Text(
                text = question.date,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun PreviewProductInfoScreen() {
    ProductInfoXmlView()
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

@OptIn(ExperimentalMaterialNavigationApi::class)
@Preview(showBackground = true)
@Composable
fun Preview() {

    ProductDetails(navController = rememberNavController())
//    ProductDetailsScreen()
//    ProductDetailsScreen()
//    DigikalaTheme {
//    BaseStructure(modifier = Modifier.background(Color.White), homeViewModel)
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