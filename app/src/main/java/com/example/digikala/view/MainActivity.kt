@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package com.example.digikala.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.digikala.R
import com.example.digikala.data.dataSource.local.ShoppingCardEntity
import com.example.digikala.data.dataSource.local.UserEntity
import com.example.digikala.data.models.category.CategoriesData
import com.example.digikala.data.models.category.Children
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
import com.example.digikala.data.models.product.AttributeInformationData
import com.example.digikala.data.models.product.DetailSection
import com.example.digikala.data.models.product.LatestComment
import com.example.digikala.data.models.product.LatestQuestion
import com.example.digikala.data.models.product.Price
import com.example.digikala.data.models.product.ProductBadge2
import com.example.digikala.data.models.product.ProductPageData
import com.example.digikala.data.models.search.ProductsItem
import com.example.digikala.data.models.search.SearchData
import com.example.digikala.data.models.search.SearchFilter
import com.example.digikala.databinding.ProductInfoSectionBinding
import com.example.digikala.ui.theme.BackgroundMenuItemSelected
import com.example.digikala.ui.theme.DarkGreen
import com.example.digikala.ui.theme.DigikalaTheme
import com.example.digikala.ui.theme.Green
import com.example.digikala.ui.theme.IconColor
import com.example.digikala.ui.theme.IconsUnSelected
import com.example.digikala.ui.theme.LightBlue
import com.example.digikala.ui.theme.LightGrayColor
import com.example.digikala.ui.theme.MenuBackground
import com.example.digikala.ui.theme.MenuItemColor
import com.example.digikala.ui.theme.MenuItems
import com.example.digikala.ui.theme.PrimaryColor
import com.example.digikala.ui.theme.StarColor
import com.example.digikala.ui.theme.White
import com.example.digikala.utils.BottomNavigationItem
import com.example.digikala.utils.Const
import com.example.digikala.utils.ConvertNumbers
import com.example.digikala.utils.LocalProvider
import com.example.digikala.utils.MyCustomFont
import com.example.digikala.utils.NetworkState
import com.example.digikala.utils.RegistrationState
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val productViewModel: ProductViewModel by viewModel()
    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private val searchViewModel: SearchViewModel by viewModel()
    private val shoppingCardViewModel: ShoppingCardViewModel by viewModel()
    private val profileViewModel: ProfileViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigikalaTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalProvider.LocalProductViewModel provides productViewModel,
                    LocalProvider.LocalNavController provides navController,
                    LocalProvider.LocalCategoriesViewModel provides categoriesViewModel,
                    LocalProvider.LocalSearchViewModel provides searchViewModel,
                    LocalProvider.LocalShoppingCardViewModel provides shoppingCardViewModel,
                    LocalProvider.LocalProfileViewModel provides profileViewModel
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        containerColor = Color.White
                    ) { innerPadding ->
                        BaseStructure(
                            modifier = Modifier.padding(innerPadding), homeViewModel
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseStructure(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {
    val navController = LocalProvider.LocalNavController.current
    val context = LocalContext.current

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            modifier = Modifier
                .background(White)
                .padding(top = 14.dp),
            bottomBar = { BottomNavigationBar(navController, context = context) },
            topBar = {
                val route = currentRoute(navController)

                when (route) {
                    Const.HOME -> {
                        Column {
                            Spacer(modifier = Modifier.height(40.dp))
                            searchBox()
                        }
                    }

                    Const.PRODUCT_DETAILS -> {
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
                    }

                    Const.TECHNICAL_INFORMATION -> {
                        TopAppBar(
                            title = { Text(Const.TECHNICAL_INFORMATION) },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(Icons.Default.ArrowForward, contentDescription = "Back Icon")
                                }
                            }
                        )
                    }

                    "${Const.SHOW_MORE}/{query}" -> {
                        TopAppBar(
                            title = { Text(Const.SHOW_MORE) },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(Icons.Default.ArrowForward, contentDescription = "Back Icon")
                                }
                            }
                        )
                    }

                    "${Const.SEARCH}/{query}" -> {
                        Column {
                            Spacer(modifier = Modifier.height(40.dp))

                            searchBoxInSearchPage(
//                onSearchStarted = { }
                            )
                        }
                    }

                    Const.PROFILE -> {
                        TopAppBar(
                            title = { Text("") },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
//                                    Icon(Icons.Default.Close, contentDescription = "Close Icon")
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Const.HOME,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Const.HOME) { HomePage(homeViewModel) }
                composable(
                    "${Const.SHOW_MORE}/{query}",
                    arguments = listOf(navArgument("query") { type = NavType.StringType })
                ) { backStackEntry ->
                    val query = backStackEntry.arguments?.getString("query")
                    query?.let { ShowMorePage(it) }
                }
                composable(Const.PRODUCT_DETAILS) { ProductDetails() }
                composable(Const.TECHNICAL_INFORMATION) { AttributeInformationPage() }
                composable(Const.CATEGORIES) { CategoriesPage() }
                composable(
                    "${Const.SEARCH}/{query}",
                    arguments = listOf(navArgument("query") { type = NavType.StringType })
                ) { backStackEntry ->
                    val query = backStackEntry.arguments?.getString("query")
                    query?.let { SearchPage(it) }
                }
                composable(Const.SHOPPING_CART) { ShoppingCartPage() }
                composable(Const.PROFILE) { AccountPage() }
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

    // You can keep this logic to hide the bar on certain screens
    if (currentRoute(navController) == Const.PRODUCT_DETAILS ||
        currentRoute(navController) == "${Const.SEARCH}/{query}" ||
        currentRoute(navController) == "${Const.SHOW_MORE}/{query}" ||
        currentRoute(navController) == Const.TECHNICAL_INFORMATION
    ) {
        // Hides the bottom bar on the product details page
        Log.d("MOX", "BottomNavigationBar: " + currentRoute(navController))
    } else {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            Log.i("MOX", "BottomNavigationBar: " + currentDestination)

            BottomNavigationItem().bottomNavigationItem(context = context)
                .forEachIndexed { index, navigationItem ->

                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                        label = {
                            Text(
                                navigationItem.label,
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            unselectedIconColor = Color.DarkGray,
                            selectedTextColor = Color.Black,
                            unselectedTextColor = Color.DarkGray,
                            indicatorColor = MenuItemColor
                        ),
                        onClick = {
                            Log.e("MOX", "BottomNavigationBar: " + navigationItem.route)
                            navController.navigate(navigationItem.route)
                        }
                    )
                }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePage(homeViewModel: HomeViewModel) {
    val homeState by homeViewModel.homeState.collectAsState()

    val data: HomePageData

    if (homeState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = PrimaryColor)
        }
    } else if (homeState.homeData != null) {
        data = homeState.homeData!!

        Box(
            Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                state = homeViewModel.productListState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    ImageSlider(
                        images = listOf(
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/6e26ffb1409146c66323c6922f536e67dc85f749_1753358489.jpg?x-oss-process=image/quality,q_95/format,webp"
                            ),
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/1291440b2c6fdee179ff01d64bafd9b5183db62e_1748860407.gif?x-oss-process=image?x-oss-process=image/format,webp"
                            ),
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/89efab84c23fcff32ff4d5aae5e9e0b0b65966af_1753197330.gif?x-oss-process=image?x-oss-process=image/format,webp"
                            ),
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/be2f58f5e1d8e49f38a053ead001fb745fe189b9_1750085414.jpg?x-oss-process=image/quality,q_95/format,webp"
                            ),
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/b69f5454f14b538e95992a3fa36e39e1081a4375_1753285527.jpg?x-oss-process=image/quality,q_95/format,webp"
                            ),
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/181f9d328d5f30563400d206d2d9a0b24f8894c6_1753272068.gif?x-oss-process=image?x-oss-process=image/format,webp"
                            )
                        ),
                        5000,
                        false
                    )
                }

                item {
                    data.result?.trending?.let {
                        productSliderTrending(
                            it.title,
                            it.products,
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp)
                        )
                    }
                }

                item {
                    RandomProducts(
                        listOf(
                            "https://dkstatics-public.digikala.com/digikala-adservice-banners/f70da287099a22737b5f64e61c22b781ff944a7b_1748439804.jpg?x-oss-process=image/quality,q_95/format,webp",
                            "https://dkstatics-public.digikala.com/digikala-adservice-banners/93fb8ac79eadb3878f703c8e506db69275374b3c_1749648799.jpg?x-oss-process=image/quality,q_95/format,webp",
                            "https://dkms.digikala.com/static/files/3ea4f643.jpg?x-oss-process=image/format,webp",
                            "https://dkstatics-public.digikala.com/digikala-adservice-banners/e02d45093f1821d5be48699f5de8da7b22de4b37_1749281511.jpg?x-oss-process=image/quality,q_95/format,webp"
                        )
                    )
                }

                item {
                    data.result?.selling_and_sales?.let {
                        productSliderSellingAndSales(
                            it.title,
                            it.products
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
                    }
                }

                item {
                    ImageSlider(

                        images = listOf(
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/1d06d8497571cf7277cb015266f88710beddf72d_1752493561.jpg?x-oss-process=image/quality,q_95/format,webp"
                            )
                        ),
                        5000, true
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }

                item {
                    data?.result?.home_1?.let { RowProductList1(it) }

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
                }

                item {
                    data?.result?.home_2?.let { RowProductList2(it) }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }

                item {
                    ImageSlider(

                        images = listOf(
                            MainBanner(
                                "https://dkstatics-public.digikala.com/digikala-adservice-banners/4cdbf0a119a2533845a71579bd76c69e4d71d50c_1753246787.jpg?x-oss-process=image/quality,q_95/format,webp"
                            )
                        ),
                        5000, true
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }

                item {
                    data?.result?.home_3?.let { RowProductList3(it) }

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
                }

                item {
                    data?.result?.home_4?.let { RowProductList4(it) }

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
                }

                item {
                    data?.result?.home_5?.let { RowProductList5(it) }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }

                item {
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
                }

                item {
                    data?.result?.home_6?.let { RowProductList6(it) }

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
                }

                item {
                    data?.result?.home_7?.let { RowProductList7(it) }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }

                item {
                    data?.result?.home_8?.let { RowProductList8(it) }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }
            }
        }
    } else if (homeState.error != null) {

    }
}

@RequiresApi(Build.VERSION_CODES.P)
@ExperimentalPagerApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(
    images: List<MainBanner>,
    intervalMillis: Long = 5000,
    disableIndicator: Boolean = false
) {
    val pagerState = rememberPagerState(images.size)
    val coroutineScope = rememberCoroutineScope()

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                add(ImageDecoderDecoder.Factory())
            } //  support WebP and GIF
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
fun productSliderTrending(
    titleHeader: String,
    products: List<Product>
) {
    val productViewModel = LocalProvider.LocalProductViewModel.current
    val navController = LocalProvider.LocalNavController.current

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
                fontSize = 14.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
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
                            productViewModel.getProductData(products[i].id)
                            navController.navigate(Const.PRODUCT_DETAILS)
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .background(White)
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
                                    color = Color.Black,
                                    textAlign = TextAlign.Start,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = MyCustomFont,
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
    val productViewModel = LocalProvider.LocalProductViewModel.current
    val navController = LocalProvider.LocalNavController.current

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
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
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
                        onClick = {
                            productViewModel.getProductData(products[i].id)
                            navController.navigate(Const.PRODUCT_DETAILS)
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .background(White)
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
                                    color = Color.Black,
                                    textAlign = TextAlign.Start,
                                    fontFamily = MyCustomFont,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
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


@RequiresApi(Build.VERSION_CODES.P)
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
fun ProductItemInHomePage(
    width: Dp, height: Dp,
    imageRes: String,
    title: String,
    price: Long?,
    discount: Long?,
    oldPrice: Long?,
    id: Long?,
    modifier: Modifier = Modifier
) {

    val productViewModel = LocalProvider.LocalProductViewModel.current
    val navController = LocalProvider.LocalNavController.current

    Card(
        modifier = modifier
            .width(width)
            .height(height)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = {
            id?.let { productViewModel.getProductData(it) }
            navController.navigate(Const.PRODUCT_DETAILS)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(width * 0.75f)  // image width based on page width
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp),
                text = title,
                textAlign = TextAlign.Start,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                fontSize = 12.sp,
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
                            Text(
                                text = ConvertNumbers.convertToPersianDigits("${it}٪"),
                                color = White,
                                fontWeight = FontWeight.Medium,
                                fontFamily = MyCustomFont,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .background(PrimaryColor, shape = RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        oldPrice?.let {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp),
                                text = ConvertNumbers.convertToPersianDigits(
                                    ConvertNumbers.convertRialToToman(it.toString())
                                ),
                                fontSize = 12.sp,
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
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
                    text = "${
                        ConvertNumbers.convertToPersianDigits(
                            ConvertNumbers.convertRialToToman(price.toString())
                        )
                    } تومان ",
                    fontSize = 12.sp,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        }
    }
}


@Composable
fun RowProductList1(result: Home1) {
    val searchViewModel = getSearchViewModelInstance()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = result.title,
                fontFamily = MyCustomFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            val navController = LocalProvider.LocalNavController.current
            Text(
                text = "مشاهده همه",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        showMoreAction(result.title, searchViewModel)
                        navController.navigate(Const.SHOW_MORE + "/${result.title}")
                    }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result.products.size) { product ->
                ProductItemInHomePage(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price?.selling_price,
                    discount = null,
                    oldPrice = result.products.get(product).price?.rrp_price,
                    id = result.products.get(product).id
                )
            }

            item {
                ShowMoreSection(itemWidth, itemHeight, result.title)
            }
        }
    }
}

@Composable
fun RowProductList2(result: Home2) {
    val searchViewModel = getSearchViewModelInstance()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = result.title,
                fontFamily = MyCustomFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            val navController = LocalProvider.LocalNavController.current
            Text(
                text = "مشاهده همه",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.clickable {
                    showMoreAction(result.title, searchViewModel)
                    navController.navigate(Const.SHOW_MORE + "/${result.title}")
                }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result.products.size) { product ->
                ProductItemInHomePage(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = result.products.get(product).price.discount_percent,
                    oldPrice = result.products.get(product).price.rrp_price,
                    id = result.products.get(product).id
                )
            }

            item {
                ShowMoreSection(itemWidth, itemHeight, result.title)
            }
        }
    }
}

@Composable
fun RowProductList3(result: Home3) {
    val searchViewModel = getSearchViewModelInstance()
    var counter = remember { mutableStateOf(1) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = result.title,
                fontFamily = MyCustomFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            val navController = LocalProvider.LocalNavController.current
            Text(
                text = "مشاهده همه",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.clickable {
                    showMoreAction(result.title, searchViewModel)
                    navController.navigate(Const.SHOW_MORE + "/${result.title}")
                }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result.products.size) { product ->
                ProductItemInHomePage(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = result.products.get(product).price.discount_percent,
                    oldPrice = result.products.get(product).price.rrp_price,
                    id = result.products.get(product).id
                )
            }

            item {
                ShowMoreSection(itemWidth, itemHeight, result.title)
            }
        }
    }
}

@Composable
fun RowProductList4(result: Home4) {
    val searchViewModel = getSearchViewModelInstance()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = result.title,
                fontWeight = FontWeight.Medium,
                fontFamily = MyCustomFont,
                fontSize = 14.sp,
            )
            val navController = LocalProvider.LocalNavController.current
            Text(
                text = "مشاهده همه",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.clickable {
                    showMoreAction(result.title, searchViewModel)
                    navController.navigate(Const.SHOW_MORE + "/${result.title}")
                }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result.products.size) { product ->
                ProductItemInHomePage(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price?.selling_price,
                    discount = result.products.get(product).price?.discount_percent,
                    oldPrice = result.products.get(product).price?.rrp_price,
                    id = result.products.get(product).id
                )
            }

            item {
                ShowMoreSection(itemWidth, itemHeight, result.title)
            }
        }
    }
}

@Composable
fun RowProductList5(result: Home5) {
    val searchViewModel = getSearchViewModelInstance()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = result.title,
                fontSize = 14.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium
            )
            val navController = LocalProvider.LocalNavController.current
            Text(
                text = "مشاهده همه",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.clickable {
                    showMoreAction(result.title, searchViewModel)
                    navController.navigate(Const.SHOW_MORE + "/${result.title}")
                }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result.products.size) { product ->
                ProductItemInHomePage(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price?.selling_price,
                    discount = result.products.get(product).price?.discount_percent,
                    oldPrice = result.products.get(product).price?.rrp_price,
                    id = result.products.get(product).id
                )
            }

            item {
                ShowMoreSection(itemWidth, itemHeight, result.title)
            }
        }
    }
}

@Composable
fun RowProductList6(result: Home6) {
    val searchViewModel = getSearchViewModelInstance()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = result.title,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
            val navController = LocalProvider.LocalNavController.current
            Text(
                text = "مشاهده همه",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.clickable {
                    showMoreAction(result.title, searchViewModel)
                    navController.navigate(Const.SHOW_MORE + "/${result.title}")
                }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result.products.size) { product ->
                ProductItemInHomePage(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price.selling_price,
                    discount = result.products.get(product).price.discount_percent,
                    oldPrice = result.products.get(product).price.rrp_price,
                    id = result.products.get(product).id
                )
            }

            item {
                ShowMoreSection(itemWidth, itemHeight, result.title)
            }
        }
    }
}

@Composable
fun RowProductList7(result: Home7) {
    val searchViewModel = getSearchViewModelInstance()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = result.title,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
            val navController = LocalProvider.LocalNavController.current
            Text(
                text = "مشاهده همه",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.clickable {
                    showMoreAction(result.title, searchViewModel)
                    navController.navigate(Const.SHOW_MORE + "/${result.title}")
                }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result.products.size) { product ->
                ProductItemInHomePage(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price?.selling_price,
                    discount = result.products.get(product).price?.discount_percent,
                    oldPrice = result.products.get(product).price?.rrp_price,
                    id = result.products.get(product).id
                )
            }

            item {
                ShowMoreSection(itemWidth, itemHeight, result.title)
            }
        }
    }
}

@Composable
fun RowProductList8(result: Home8) {
    val searchViewModel = getSearchViewModelInstance()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = result.title,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
            val navController = LocalProvider.LocalNavController.current
            Text(
                text = "مشاهده همه",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.clickable {
                    showMoreAction(result.title, searchViewModel)
                    navController.navigate(Const.SHOW_MORE + "/${result.title}")
                }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result.products.size) { product ->
                ProductItemInHomePage(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = result.products.get(product).images.main,
                    title = result.products.get(product).title_fa,
                    price = result.products.get(product).price?.selling_price!!,
                    discount = result.products.get(product).price?.discount_percent,
                    oldPrice = result.products.get(product).price?.rrp_price,
                    id = result.products.get(product).id
                )
            }

            item {
                ShowMoreSection(itemWidth, itemHeight, result.title)
            }
        }
    }
}

@Composable
fun ShowMoreSection(itemWidth: Dp, itemHeight: Dp, title: String?) {
    val navController = LocalProvider.LocalNavController.current
    val searchViewModel = LocalProvider.LocalSearchViewModel.current

    Card(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = White,
        elevation = 0.dp,
        onClick = {
            title?.let { showMoreAction(it, searchViewModel) }
            navController.navigate(Const.SHOW_MORE + "/$title")
        }
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
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun getSearchViewModelInstance(): SearchViewModel {
    return LocalProvider.LocalSearchViewModel.current
}

fun showMoreAction(title: String, searchViewModel: SearchViewModel) {
    searchViewModel.isLoading = false
    searchViewModel.isLastPage = false
    searchViewModel.currentPage = 1
    searchViewModel.cachedProducts.clear()
    searchViewModel.getSearchData(title)
}

@Composable
fun searchBox() {
    val navController = LocalProvider.LocalNavController.current
    val searchViewModel = LocalProvider.LocalSearchViewModel.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(50.dp)
            .clickable {
                navController.navigate(Const.SEARCH + "/")
                searchViewModel.isLoading = false
                searchViewModel.isLastPage = false
                searchViewModel.currentPage = 1
                searchViewModel.cachedProducts.clear()
            },
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
                tint = White,
                contentDescription = "search"
            )

            Text(
                modifier = Modifier
                    .padding(8.dp),
                textAlign = TextAlign.Right,
                text = "جستجو کالا",
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                style = TextStyle(fontSize = 12.sp, color = White)
            )
        }
    }
}

@Composable
fun ProductDetails() {
    val productViewModel = LocalProvider.LocalProductViewModel.current
    val productData = productViewModel.productData.collectAsState()
    val data: ProductPageData

    when (productData.value) {
        is NetworkState.Uninitialized -> {

        }
        is NetworkState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryColor)
            }
        }

        is NetworkState.Success -> {
            data = (productData.value as NetworkState.Success<ProductPageData>).data
            ProductPageDesign(data)
        }

        is NetworkState.UnSuccess -> {
        }

        is NetworkState.Failure -> {
        }
    }
}

@Composable
fun ProductPageDesign(data: ProductPageData) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item(key = "slider") {
                ProductPageSlider(data)
            }
            item(key = "information") {
                ProductInformation(
                    data,
                    moveOnItemClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(index = 3)
                        }
                    }
                )
            }
        }

        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            data
        )
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier, data: ProductPageData) {
    val shoppingCardViewModel = LocalProvider.LocalShoppingCardViewModel.current
    val profileViewModel = LocalProvider.LocalProfileViewModel.current
    val navController = LocalProvider.LocalNavController.current
    val isProductInCart by shoppingCardViewModel.isProductInCart.collectAsState()
    var buttomText = remember { mutableStateOf("افزودن به سبد خرید") }
    val isLogin = profileViewModel.isUserLoggedIn.collectAsState()
    val user by profileViewModel.userData.collectAsState()


    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        shoppingCardViewModel.checkIfProductIsInCart(data.result.product.id)
    }

    data.result?.product?.product_badges?.let {
        LaunchedEffect(Unit) {
            while (true) {
                delay(3000L)
                currentIndex = (currentIndex + 1) % (data.result.product.product_badges.size)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                val payloadData = data.result.product.product_badges?.get(currentIndex)?.payload
                payloadData?.let {
                    val colorInt = ColorUtils.fromInt(android.graphics.Color.parseColor(payloadData?.text_color))
                    Text(
                        text = payloadData?.text.toString(),
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Normal,
                        color = colorInt,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                }

                val shoppingCardEntity = user?.phoneNumber?.let {
                    ShoppingCardEntity(
                        productId = data.result.product.id,
                        products = data.result.product,
                        userId = it,
                        count = 1
                    )
                }



                Button(
                    onClick = {
                        if (isProductInCart) {
                            navController.navigate(Const.SHOPPING_CART)
                        } else {
                            if (isLogin.value == true) {
                                shoppingCardEntity?.let { shoppingCardViewModel.addProductToDatabase(it) }
                            } else {
                                navController.currentBackStackEntry?.savedStateHandle?.set("from_menu", "accountPage")
                                navController.navigate(Const.PROFILE)
                                {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(45.dp)
                ) {
                    if (isProductInCart) {
                        buttomText.value = "برو به سبد خرید"
                    } else {
                        buttomText.value = "افزودن به سبد خرید"
                    }
                    Text(
                        text = buttomText.value,
                        color = White,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Column {
                if (data.result.product.price.rrp_price != data.result.product.price.selling_price) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = ConvertNumbers.convertToPersianDigits("${data.result.product.price.discount_percent}٪"),
                            color = White,
                            fontWeight = FontWeight.Medium,
                            fontFamily = MyCustomFont,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .background(PrimaryColor, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = ConvertNumbers.convertToPersianDigits(
                                ConvertNumbers.convertRialToToman(data.result.product.price.rrp_price.toString())
                            ),
                            fontWeight = FontWeight.Normal,
                            fontFamily = MyCustomFont,
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${
                            ConvertNumbers.convertToPersianDigits(
                                ConvertNumbers.convertRialToToman(data.result.product.price.selling_price.toString())
                            )
                        } تومان ",
                        fontWeight = FontWeight.Normal,
                        fontFamily = MyCustomFont,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${
                            ConvertNumbers.convertToPersianDigits(
                                ConvertNumbers.convertRialToToman(data.result.product.price.selling_price.toString())
                            )
                        } تومان ",
                        fontWeight = FontWeight.Normal,
                        fontFamily = MyCustomFont,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

object ColorUtils {
    fun fromInt(colorInt: Int): Color {
        return Color(colorInt)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductPageSlider(sliderData: ProductPageData) {
    val images = mutableListOf(sliderData.result.product.images.main)
    images.addAll(sliderData.result.product.images.image_list)

    val pagerState = rememberPagerState(initialPage = 0)

    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        state = pagerState,
        count = images.size
    ) { page ->
        Image(
            painter = rememberAsyncImagePainter(images[page]),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 8.dp, end = 20.dp, start = 20.dp),
            contentDescription = null,
        )
    }

    HorizontalPagerIndicator(
        pagerState = pagerState,
        activeColor = Color.Red,
        inactiveColor = Color.LightGray,
        indicatorWidth = 6.dp,
        indicatorHeight = 6.dp,
        indicatorShape = RoundedCornerShape(3.dp)
    )
}

@Composable
fun ProductInformation(productInformation: ProductPageData, moveOnItemClick: () -> Unit) {

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
                    text = productInformation.result.product.brand.title_fa,
                    color = LightBlue,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 4.dp),
                    text = "/",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )

                Text(
                    text = productInformation.result.product.category_title,
                    color = LightBlue,
                    textAlign = TextAlign.Right,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }

            Text(
                text = productInformation.result.product.title_fa,
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(horizontal = 18.dp)
            )

            ProductActionsRow(
                stars = productInformation.result.product.variants.get(0).seller.stars,
                ratingCount = productInformation.result.product.rating.count,
                commentCount = productInformation?.result?.product?.comments?.count,
                questionCount = productInformation?.result?.product?.questions?.count,
                onItemClick = moveOnItemClick
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 5.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                if (productInformation.result.product.review?.attributes != null) {
                    items(productInformation.result.product.review.attributes.size) {
                        val data = productInformation.result.product.review.attributes
                        ProductSpecificationsButton(data.get(it).title, data.get(it).values.get(0))

                    }
                }
            }

            val data = productInformation.result.product.variants.get(0).color
            if (data != null) ProductColorList(productInformation)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(LightGrayColor)
            )

            ProductInfoXmlView(productInformation)

            SimilarProducts(productInformation)

            ProductDetailsScreen(productInformation)
        }
    }
}

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun ProductActionsRow(
    stars: Double,
    ratingCount: Double,
    commentCount: Int?,
    questionCount: Int?,
    onItemClick: () -> Unit
) {
    val itemList = mutableListOf<@Composable () -> Unit>()

    itemList.add {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, contentDescription = null, tint = StarColor)
            Text(
                text = ConvertNumbers.convertToPersianDigits(stars.toString()),
                modifier = Modifier.padding(horizontal = 8.dp),
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "(امتیاز ${formatNumberToPersian(ratingCount)} خریدار)",
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
    }

    commentCount?.takeIf { it > 0 }?.let {
        itemList.add {
            UserActionButton("${formatNumberToPersian(it.toDouble())} دیدگاه ها", onClick = onItemClick)
        }
    }

    questionCount?.takeIf { it > 0 }?.let {
        itemList.add {
            UserActionButton("${formatNumberToPersian(it.toDouble())} پرسش و پاسخ", onClick = onItemClick)
        }
    }

    val arrangement = if (itemList.size <= 3) Arrangement.Start else Arrangement.SpaceEvenly

    AnimatedContent(targetState = itemList.size, label = "lazyRowAnimation") {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 18.dp),
            horizontalArrangement = arrangement,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(itemList.size) { index ->
                itemList[index]()
            }
        }
    }
}


fun formatNumberToPersian(value: Double): String {
    val decimalFormat = DecimalFormat("#.#", DecimalFormatSymbols(Locale("fa", "IR")))
    return decimalFormat.format(value)
}

@Composable
fun UserActionButton(title: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val boxColor = if (isPressed) Color.Gray else LightGrayColor

    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .background(boxColor, shape = RoundedCornerShape(15.dp))
            .padding(horizontal = 4.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text(
                title,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
        }
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun ProductColorList(colorData: ProductPageData) {
    val lastColor = rememberSaveable() { mutableStateOf("") }

    val variants = colorData.result.product.variants

    val filteredVariants = variants
        .filter { it.color.title_fa != lastColor.value }
        .distinctBy { it.color.title_fa }


    Log.d("MOX", "ProductColorList: " + filteredVariants.size)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 20.dp)
    ) {
        Row {
            Text(
                text = "رنگ:",
                fontSize = 14.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = filteredVariants.get(0).color.title_fa,
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal
            )
        }

        LazyRow(
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            items(filteredVariants.size) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(15.dp)),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                                .size(22.dp)
                                .border(1.dp, color = Color.LightGray, shape = CircleShape)
                                .background(
                                    color = Color(android.graphics.Color.parseColor(filteredVariants[it].color.hex_code)),
                                    shape = CircleShape
                                )
                        )
//                        lastColor.value = colorData.result.product.variants[it].color.title_fa
                        Text(
                            modifier = Modifier
                                .padding(end = 12.dp),
                            text = lastColor.value,
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Medium
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
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(bottom = 2.dp),
                fontSize = 12.sp,
                color = Color.DarkGray
            )

            Text(
                feature,
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Composable
fun ProductInfoXmlView(sellerData: ProductPageData) {
    val productViewModel = LocalProvider.LocalProductViewModel.current
    val data = sellerData.result.product.variants.get(0)
    var productInfoBinding: ProductInfoSectionBinding? = null
    val navController = LocalProvider.LocalNavController.current

    AndroidView(
        factory = { context ->
            productInfoBinding = ProductInfoSectionBinding.inflate(LayoutInflater.from(context))
            productInfoBinding!!.root

        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        productInfoBinding?.apply {
            sellerName.text = "فروشنده:  ${data.seller.title_fa}"
            customerSatisfaction.text = "${data.seller.grade.label}"
            val colorInt = android.graphics.Color.parseColor(data.seller.grade.color)
            customerSatisfaction.setTextColor(colorInt)
            spacialService.text = data.warranty.title_fa
            availableInStock.text = data.shipment_methods.description

            sendProduct.setContent {
                Column(modifier = Modifier.fillMaxWidth()) {
                    data.shipment_methods.providers.forEach { provider ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(provider.image),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = provider.title,
                                fontSize = 12.sp,
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray,
                            )
                        }
                    }
                }
            }

            digiclubScore.text = "${data.digiclub.point} امتیاز در دیجی کلاب دریافت می کنید "

            if (sellerData.result.product.digiplus.services == null) {
                digiplusConst.visibility = View.GONE
            } else {
                digiplusServices.setContent {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        sellerData.result.product.digiplus.service_list.forEach { provider ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 4.dp)
                            ) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = provider.title,
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    fontFamily = MyCustomFont,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }

            technicalBox.setContent {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) {
                            productViewModel.getAttributeData(sellerData.result.product.id)
                            navController.navigate(Const.TECHNICAL_INFORMATION)
                        }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "مشخصات فنی",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Normal
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SimilarProducts(similarProductsData: ProductPageData) {
    val data = similarProductsData.result.recommendation
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f

    data?.related_products?.size?.let {
        LazyRow {
            items(it) { product ->
                SimilarProductItemInProductPage(
                    width = itemWidth,
                    height = itemHeight,
                    imageRes = data?.related_products?.get(product)?.images?.main,
                    title = data?.related_products?.get(product)?.title_fa,
                    price = data?.related_products?.get(product)?.price?.selling_price,
                    discount = data?.related_products?.get(product)?.price?.discount_percent,
                    oldPrice = data?.related_products?.get(product)?.price?.rrp_price,
                    badge = data?.related_products?.get(product)?.product_badge
                )
            }
        }
    }
}

@Composable
fun SimilarProductItemInProductPage(
    width: Dp, height: Dp,
    imageRes: String?,
    title: String?,
    price: Long?,
    discount: Int?,
    oldPrice: Long?,
    badge: ProductBadge2?,
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
                .background(White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(width * 0.75f)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp),
                text = title.toString(),
                textAlign = TextAlign.Start,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                fontSize = 12.sp,
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
                    Image(
                        painter = rememberAsyncImagePainter(
                            badge?.icon
                        ),
                        contentDescription = null
                    )

                    Text(
                        text = badge?.text.toString(),
                        color = ColorUtils.fromInt(
                            android.graphics.Color.parseColor(
                                badge?.text_color
                            )
                        )
                    )

                    Text(
                        text = badge?.text ?: "",
                        color = Color(
                            android.graphics.Color.parseColor(
                                badge?.text_color ?: "#000000"
                            )
                        )
                    )
                }
            }


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
                                .background(PrimaryColor, shape = RoundedCornerShape(6.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = ConvertNumbers.convertToPersianDigits(it.toString() + "%"),
                                color = White,
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }

                    oldPrice?.let {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 6.dp),
                            text = ConvertNumbers.convertToPersianDigits(
                                ConvertNumbers.convertRialToToman(it.toString())
                            ),
                            fontSize = 12.sp,
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Normal,
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
                text = "${
                    ConvertNumbers.convertToPersianDigits(
                        ConvertNumbers.convertRialToToman(price.toString())
                    )
                } تومان ",
                fontSize = 12.sp,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun ProductDetailsScreen(questionAndCommentData: ProductPageData) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.30f
    val itemHeight = screenWidth * 0.50f

    val questions = questionAndCommentData.result.product.questions
    val comments = questionAndCommentData.result.product.comments

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {


            comments?.latest_comments?.size?.let {
                SectionTitle(title = "دیدگاه‌ها")

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(it) { index ->
                        comments.latest_comments.get(index).let { ReviewItem(it) }
                    }


                    comments?.latest_comments?.let {
                        if (comments?.count!! > comments?.latest_comments?.size!!) {
                            item {
                                ShowMoreSection(itemWidth, itemHeight, null)
                            }
                        }
                    }
                }
            }


            questions?.latest_questions?.size?.let {
                SectionTitle(title = "پرسش و پاسخ")

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                )
                {
                    items(it) { index ->
                        QuestionItem(questions.latest_questions.get(index))
                    }

                    questions?.latest_questions?.let {
                        if (questions?.count!! > questions?.latest_questions?.size!!) {
                            item {
                                ShowMoreSection(itemWidth, itemHeight, null)
                            }
                        }
                    }
                }
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}

@Composable
fun ReviewItem(review: LatestComment) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .padding(horizontal = 8.dp)
            .border(0.2.dp, Color.LightGray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = review.user_name,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
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
                            textAlign = TextAlign.Center,
                            fontFamily = MyCustomFont,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(review.rate.toInt()) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = StarColor)
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = review.body,
                    textAlign = TextAlign.Right,
                    fontSize = 12.sp,
                    maxLines = 3,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Text(
                text = review.created_at,
                color = Color.Gray,
                fontSize = 12.sp,
                textAlign = TextAlign.Right,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun QuestionItem(question: LatestQuestion) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .width(280.dp)
            .height(200.dp)
            .padding(bottom = 20.dp)
            .padding(horizontal = 4.dp)
            .background(color = White, shape = RoundedCornerShape(8.dp))
            .border(0.2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
        elevation = 2.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.question_icon),
                        contentDescription = "icon_title"
                    )
                    Text(
                        text = question.text,
                        textAlign = TextAlign.Right,
                        fontSize = 12.sp,
                        maxLines = 3,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }

                if (question.last_answer?.text != null) {
                    Text(
                        text = "پاسخ: ${question.last_answer?.text}",
                        textAlign = TextAlign.Right,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        maxLines = 2,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Text(
                text = question.created_at,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Right,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 14.dp)
            )
        }
    }
}

@Composable
fun AttributeInformationPage() {
    val productViewModel = LocalProvider.LocalProductViewModel.current
    val attributeData = productViewModel.attributeInformation.collectAsState()

    when (attributeData.value) {
        is NetworkState.Uninitialized -> {

        }

        is NetworkState.Loading -> {

        }

        is NetworkState.Success -> {
            val data = (attributeData.value as NetworkState.Success<AttributeInformationData>).data
            LazyColumn {
                items(data.result.size) {
                    AttributesDesign(data.result.get(it))
                }
            }
        }

        is NetworkState.UnSuccess -> {

        }

        is NetworkState.Failure -> {

        }
    }
}

@Composable
fun AttributesDesign(attributes: DetailSection) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = attributes.title,
            fontSize = 18.sp,
            color = Color.Black,
            fontFamily = MyCustomFont,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Right
        )

        attributes?.attributes?.forEach { item ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Right
                    )

                    Text(
                        text = item.values.toString(),
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Right
                    )
                }
                Divider(
                    color = Color.LightGray,
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}


//@Preview
@Composable
fun PreviewProductInfoScreen() {
//    ProductInfoXmlView()
}


@Composable
fun CategoriesPage() {
    val categoriesViewModel = LocalProvider.LocalCategoriesViewModel.current
    val categoriesData = categoriesViewModel.categoriesData.collectAsState()

    LaunchedEffect(Unit) {
        categoriesViewModel.getCategoriesData()
    }

    when (categoriesData.value) {
        is NetworkState.Uninitialized -> {

        }

        is NetworkState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryColor)
            }
        }

        is NetworkState.Success -> {
            val data = (categoriesData.value as NetworkState.Success<CategoriesData>).data
//            Log.e("MOX", "CategoriesPageDesign: " +data.icons)
            data?.let {
                CategoriesPageDesign(it)
            }
        }

        is NetworkState.UnSuccess -> {

        }

        is NetworkState.Failure -> {

        }
    }
}

@Composable
fun CategoriesPageDesign(categoriesData: CategoriesData) {

    var selectedCategory by rememberSaveable { mutableStateOf<Int?>(1) }

    val icons: List<Int> = listOf(
        R.drawable.mobileicon,
        R.drawable.electronic_icon,
        R.drawable.electronic_icon,
        R.drawable.home_kitchen_icon,
        R.drawable.cat_home_electronic,
        R.drawable.beauty,
        R.drawable.vehicle,
        R.drawable.tools,
        R.drawable.fashion,
        R.drawable.cat_jewelry,
        R.drawable.cat_health,
        R.drawable.book_stationary,
        R.drawable.sport_out_door,
        R.drawable.gift_cart,
        R.drawable.fresh,
        R.drawable.kids_toy,
        R.drawable.native_business
    )

    val currentSelectedMainCategory = remember(selectedCategory) {
        selectedCategory?.let { id ->
            categoriesData.result.find { it.id == id }
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.25f)
                        .background(color = MenuBackground),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(categoriesData.result.size) { index ->
                        val category = categoriesData.result[index]
                        val rowIndexForIcon = category.row_number - 1

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedCategory = category.id
                                    Log.i("MOX", "on click item: " + category.id)
                                }
                                .background(
                                    color = if (selectedCategory == category.id) BackgroundMenuItemSelected else Color.Transparent
                                ),

                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (rowIndexForIcon in icons.indices) {
                                Image(
                                    painter = painterResource(id = icons[rowIndexForIcon]),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(if (selectedCategory == category.id) PrimaryColor else IconsUnSelected),
                                    modifier = Modifier
                                        .padding(top = 6.dp)
                                )
                            }

                            Text(
                                text = category.title,
                                fontSize = 12.sp,
                                color = if (selectedCategory == category.id) PrimaryColor else MenuItems,
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, bottom = 6.dp)
                                    .padding(horizontal = 6.dp)
                            )
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 12.dp, horizontal = 12.dp)
                ) {
                    item {
                        Row {
                            Text(
                                text = "همه محصولات ${currentSelectedMainCategory?.title ?: "نامعلوم"}",
                                fontSize = 14.sp,
                                color = LightBlue,
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Medium
                            )

                            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null, tint = LightBlue)
                        }
                    }
                    val childrenOfSelectedCategory = currentSelectedMainCategory?.children

                    childrenOfSelectedCategory?.let { children ->
                        items(children.size) { itemIndex ->
                            children.getOrNull(itemIndex)?.let { childItem ->
                                Log.i("MOX", "CategoriesPageDesign: " + childItem.title)
                                ExpandableMenuItem(childItem.title, childItem)
                            }
                        }
                    } ?: run {
                        item {
                            Text(
                                text = "هیچ زیر دسته‌بندی برای این گروه وجود ندارد.",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandableMenuItem(title: String, itemData: Children) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")
    val searchViewModel = LocalProvider.LocalSearchViewModel.current
    val navController = LocalProvider.LocalNavController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (!itemData.children.isNullOrEmpty()) {
                    expanded = !expanded
                }
            }
            .padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal
            )
            if (!itemData.children.isNullOrEmpty()) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    modifier = Modifier.rotate(rotation)
                )
            }
        }

        AnimatedVisibility(visible = expanded) {
            if (!itemData.children.isNullOrEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(itemData.children.size) { index ->
                        val grandChild = itemData.children.getOrNull(index)
                        Box(
                            modifier = Modifier
                                .background(color = Color.Transparent)
                                .clickable {
                                    Log.d("MOX", "Clicked on grandchild: ${grandChild?.title}")
                                }
                        ) {
                            grandChild?.let {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            Log.d("MOX", "Clicked on grandchild: ${it.title}")
                                            searchViewModel.isLoading = false
                                            searchViewModel.isLastPage = false
                                            searchViewModel.currentPage = 1
                                            searchViewModel.cachedProducts.clear()
                                            navController.navigate("${Const.SEARCH}/${grandChild.title}")
                                            grandChild.title?.let { searchViewModel.getSearchData(it) }
                                        }
                                        .padding(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(64.dp)
                                            .background(color = White)
                                            .border(width = 1.dp, color = Color.LightGray)
                                            .padding(4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(it.image),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(60.dp)
                                                .background(Color.Transparent)
                                        )
                                    }
                                    Text(
                                        text = it.title,
                                        textAlign = TextAlign.Center,
                                        fontSize = 12.sp,
                                        fontFamily = MyCustomFont,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun searchBoxInSearchPage() {
    val searchViewModel = LocalProvider.LocalSearchViewModel.current
    val navController = LocalProvider.LocalNavController.current
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.ArrowForward, contentDescription = "Back",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(28.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                contentAlignment = Alignment.Center
            ) {
                CustomOutlinedTextField(
                    onSearch = { query ->
                        coroutineScope.launch {
//                        onSearchStarted()
                            searchViewModel.isLoading = false
                            searchViewModel.isLastPage = false
                            searchViewModel.currentPage = 1
                            searchViewModel.cachedProducts.clear()
                            searchViewModel.getSearchData(query)
                        }
                    },
                    "جستجو در همه کالاها",
                    Const.searchType.MAIN_SEARCH_BOX
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchPage(query: String) {
    val searchViewModel = LocalProvider.LocalSearchViewModel.current
    val data by searchViewModel.searchData.collectAsState()
    var hasSearched by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val showBottomLoader = remember { mutableStateOf(false) }
    val filters by searchViewModel.filters.collectAsState()

    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItem =
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = listState.layoutInfo.totalItemsCount
            totalItems > 0 && lastVisibleItem >= totalItems - 4
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (
            shouldLoadMore &&
            !searchViewModel.isLoading &&
            !searchViewModel.isLastPage
        ) {
            searchViewModel.getSearchData(searchViewModel.searchText)
        }
    }


    LaunchedEffect(data) {
        when (data) {
            is NetworkState.Uninitialized -> {

            }
            is NetworkState.Loading -> {
                if (searchViewModel.currentPage > 1) {
                    showBottomLoader.value = true
                } else {

                }
            }

            is NetworkState.Success,
            is NetworkState.Failure,
            is NetworkState.UnSuccess -> {
                showBottomLoader.value = false
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyRow(Modifier.padding(top = 12.dp)) {
            filters?.let {

                item {
                    filterItemDesign(
                        SearchFilter("filters", "فیلتر", null)
                    )
                }

                items(it.size) { index ->
                    filterItemDesign(it.get(index))
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            searchBoxInSearchPage(
////                onSearchStarted = { }
//            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            val products = if (searchViewModel.cachedProducts.isNotEmpty()) {
                searchViewModel.cachedProducts
            } else if (data is NetworkState.Success) {
                (data as NetworkState.Success<SearchData>).data.result?.products
            } else {
                emptyList()
            }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 54.dp)
            ) {
                products
                    ?.filterIsInstance<ProductsItem>()
                    ?.let { filteredProducts ->
                        items(filteredProducts.size) { index ->
                            val product = filteredProducts[index]
                            SearchItemDesign(product)
                        }
                    }


                if (showBottomLoader.value) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = PrimaryColor)
                        }
                    }
                }
            }
        }

        if (data is NetworkState.Loading && searchViewModel.currentPage == 1) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryColor)
            }
        }
    }
    LaunchedEffect(Unit) {
        if (searchViewModel.cachedProducts.isEmpty()) {
            if (query != "") {
                searchViewModel.getSearchData(query)
            }
        }
    }
}

@Preview
@Composable
fun filterItemDesign(item: SearchFilter) {
    var openBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .background(color = White, shape = RoundedCornerShape(10.dp))
            .border(0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            .clickable {
                openBottomSheet = true
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Log.d("MOX", "title: " + item.title)
        Log.i("MOX", "type: " + item.type)

        item?.title?.let {
            Text(
                text = it,
                fontFamily = MyCustomFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    .then(
                        if (item.type != "switch") {
                            Modifier.padding(end = 4.dp)
                        } else {
                            Modifier.padding(end = 8.dp)
                        }
                    )
            )
        }

        if (item.type != "switch") {
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 4.dp)
            )
        }
    }

    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = sheetState
        ) {
            item.options?.let { options ->
                for (children in options) {
                    choiceFilterCheckBoxType(item)
                }
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun choiceFilterCheckBoxType(items: SearchFilter) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            items.title?.let {
                Text(
                    text = it,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            Icon(Icons.Default.Close, contentDescription = null)
        }

        CustomOutlinedTextField(
            onSearch = { query ->

            },
            "جستجودر${items.title}",
            Const.searchType.FILTERS_SEARCH_BOX
        )

        LazyColumn {
            items.options?.size?.let {size ->
                items(size) {index ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Checkbox(
                            checked = items.options.get(index).isSelected.value,
                            onCheckedChange = { items.options.get(index).isSelected.value = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = PrimaryColor,
                                uncheckedColor = Color.Gray,
                                checkmarkColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        items.options?.get(index)?.let { it1 ->
                            Text(
                                text = it1?.titleFa!!,
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomOutlinedTextField(onSearch: ((String) -> Unit)?, searchDescription: String, useCase: String) {
    var searchText by remember { mutableStateOf("") }
    val searchViewModel = LocalProvider.LocalSearchViewModel.current

    val colors: TextFieldColors = when (useCase) {
        Const.searchType.MAIN_SEARCH_BOX -> {
            TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.DarkGray,
                cursorColor = Color.Blue,
                focusedBorderColor = PrimaryColor,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = PrimaryColor,
                unfocusedLabelColor = Color.Gray,
                placeholderColor = Color.DarkGray
            )
        }

        Const.searchType.FILTERS_SEARCH_BOX -> {
            TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.DarkGray,
                cursorColor = Color.Blue,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                placeholderColor = Color.DarkGray
            )
        }

        else -> {
            TextFieldDefaults.outlinedTextFieldColors()
        }
    }


    OutlinedTextField(
        value = searchText,
        onValueChange = { newText -> searchText = newText },
        label = { Text(searchDescription) },
        singleLine = true,
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearch?.let { it(searchText) }
                    },
                    modifier = Modifier
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Clear Text")
                }
            }
        }
    )
}

@Composable
fun SearchItemDesign(product: ProductsItem) {
    val productViewModel = LocalProvider.LocalProductViewModel.current
    val navController = LocalProvider.LocalNavController.current

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth * 0.23f
    val itemHeight = screenWidth * 0.28f

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(itemHeight + 40.dp)
            .clickable {
                productViewModel.getProductData(product.id)
                navController.navigate(Const.PRODUCT_DETAILS)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(itemWidth)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.images.main),
                    contentDescription = null,
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(5.dp))
                )

                product.default_variant?.color?.hex_code?.let {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(
                                    color = Color(android.graphics.Color.parseColor(it)),

                                    )
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Text(
                    text = product.title_fa,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107)
                        )
                        product.default_variant?.seller?.stars?.let {
                            Text(
                                text = formatNumberToPersian(it),
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        if (product.price.rrp_price != null &&
                            product.price.rrp_price != product.price.selling_price
                        ) {
                            Text(
                                text = ConvertNumbers.convertToPersianDigits(
                                    ConvertNumbers.convertRialToToman(product.price.rrp_price.toString())
                                ),
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    textDecoration = TextDecoration.LineThrough,
                                    color = Color.Gray
                                ),
                                fontSize = 12.sp
                            )
                        }

                        Text(
                            text = "${
                                ConvertNumbers.convertToPersianDigits(
                                    ConvertNumbers.convertRialToToman(product.price.selling_price.toString())
                                )
                            } تومان ",
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    }

                    if (product.price.discount_percent != 0) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 14.dp, vertical = 4.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = ConvertNumbers.convertToPersianDigits("${product.price.discount_percent}٪"),
                                color = White,
                                fontWeight = FontWeight.Medium,
                                fontFamily = MyCustomFont,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .background(
                                        PrimaryColor,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ShowMorePage(query: String) {
    val searchViewModel = LocalProvider.LocalSearchViewModel.current
    val searchData by searchViewModel.searchData.collectAsState()
    val gridState = rememberLazyGridState()

    val showBottomLoader = remember { mutableStateOf(false) }

    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItem = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = gridState.layoutInfo.totalItemsCount
            totalItems > 0 && lastVisibleItem >= totalItems - 4
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore && !searchViewModel.isLoading && !searchViewModel.isLastPage) {
            searchViewModel.getSearchData(query)
        }
    }

    LaunchedEffect(searchData) {
        when (searchData) {
            is NetworkState.Uninitialized -> {

            }
            is NetworkState.Loading -> {
                if (searchViewModel.currentPage > 1) {
                    showBottomLoader.value = true
                }
            }

            is NetworkState.Success,
            is NetworkState.Failure,
            is NetworkState.UnSuccess -> {
                showBottomLoader.value = false
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        val products = if (searchViewModel.cachedProducts.isNotEmpty()) {
            searchViewModel.cachedProducts
        } else if (searchData is NetworkState.Success) {
            (searchData as NetworkState.Success<SearchData>).data.result?.products
        } else {
            emptyList()
        }

        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            products?.size?.let {
                items(it) { index ->
                    showMoreItem(products[index])
                }
            }

            if (showBottomLoader.value) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PrimaryColor)
                    }
                }
            }
        }

        if (searchData is NetworkState.Loading && searchViewModel.currentPage == 1) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryColor)
            }
        }
    }

    LaunchedEffect(Unit) {
        if (searchViewModel.cachedProducts.isEmpty()) {
            searchViewModel.getSearchData(query)
        }
    }
}

@Composable
fun showMoreItem(allProducts: ProductsItem) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val productViewModel = LocalProvider.LocalProductViewModel.current
    val navController = LocalProvider.LocalNavController.current
    val itemWidth = screenWidth * 0.45f
    val itemHeight = screenWidth * 0.70f
    Card(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        onClick = {
            allProducts.id?.let { productViewModel.getProductData(it) }
            navController.navigate(Const.PRODUCT_DETAILS)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(vertical = 6.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(allProducts.images.main),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .size(itemWidth * 0.75f)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 6.dp),
                text = allProducts.title_fa,
                textAlign = TextAlign.Start,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Start),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (allProducts.price.rrp_price != allProducts.price.selling_price) {
                        allProducts.price.discount_percent?.let {
                            Text(
                                text = ConvertNumbers.convertToPersianDigits("${allProducts.price.discount_percent}٪"),
                                color = White,
                                fontWeight = FontWeight.Medium,
                                fontFamily = MyCustomFont,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .background(PrimaryColor, shape = RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        allProducts.price.rrp_price?.let {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp),
                                text = ConvertNumbers.convertToPersianDigits(
                                    ConvertNumbers.convertRialToToman(allProducts.price.rrp_price.toString())
                                ),
                                fontSize = 12.sp,
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
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
                        .align(Alignment.End)
                        .padding(horizontal = 8.dp),
                    text = "${
                        ConvertNumbers.convertToPersianDigits(
                            ConvertNumbers.convertRialToToman(allProducts.price.selling_price.toString())
                        )
                    } تومان ",
                    fontSize = 12.sp,
                    fontFamily = MyCustomFont,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun ShoppingCartPage() {
    val shoppingCardViewModel = LocalProvider.LocalShoppingCardViewModel.current
    val shoppingCardData by shoppingCardViewModel.shoppingCartItems.collectAsState()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "سبد خرید شما",
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Right,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 8.dp)
                )

                Spacer(
                    Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                )

                LazyColumn {
                    items(shoppingCardData.size) {
                        productItemInshoppingCard(shoppingCardData, it)
                    }

                    item {
                        finalReceiptBoxInShoppingCard(
                            2,
                            shoppingCardData
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }

            ShoppingCardPageBottomBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                shoppingCardData
            )

        }
    }
}

@Composable
fun finalReceiptBoxInShoppingCard(countProduct: Int, data: List<ShoppingCardEntity>) {
    var totalPriceWithProfit: Long = 0
    var totalTakhfif: Long = 0
    var totalPrice: Long = 0

    for (item in data) {
        totalTakhfif += (item.products.price.rrp_price - item.products.price.selling_price)
        totalPrice += item.products.price.rrp_price

    }

    totalPriceWithProfit = totalPrice - totalTakhfif


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (totalPrice.toInt() != 0) {
            SummaryRow(
                title = "(${ConvertNumbers.convertToPersianDigits(data.size.toString())}) قیمت کالاها",
                amount = "${
                    ConvertNumbers.convertToPersianDigits(
                        ConvertNumbers.convertRialToToman(ConvertNumbers.calculatePrices(data).totalPrice.toString())
                    )
                } تومان ",
                amountColor = Color.Black
            )
        }

        if (totalPriceWithProfit.toInt() != 0) {
            SummaryRow(
                title = "جمع سبد خرید",
                amount = "${
                    ConvertNumbers.convertToPersianDigits(
                        ConvertNumbers.convertRialToToman(ConvertNumbers.calculatePrices(data).totalPriceWithProfit.toString())
                    )
                } تومان ",
                amountColor = Color.Black
            )
        }

        if (totalTakhfif.toInt() != 0) {
            SummaryRow(
                title = "سود شما از خرید",
                amount = "${
                    ConvertNumbers.convertToPersianDigits(
                        ConvertNumbers.convertRialToToman(ConvertNumbers.calculatePrices(data).totalTakhfif.toString())
                    )
                } تومان ",
                amountColor = DarkGreen
            )
        }
    }
}

@Composable
fun SummaryRow(
    title: String,
    amount: String,
    amountColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontFamily = MyCustomFont,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = amount,
            fontFamily = MyCustomFont,
            fontWeight = FontWeight.Normal,
            color = amountColor,
            textAlign = TextAlign.End
        )
    }
}


@Composable
fun productItemInshoppingCard(data: List<ShoppingCardEntity>, index: Int) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemHeight = screenWidth * 0.74f
    val product = data.get(index).products

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .padding(vertical = 4.dp)
    ) {
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.images.main),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(5.dp))
                )

                product.price.badge?.let {
                    Text(
                        text = it.title,
                        color = PrimaryColor,
                        fontSize = 12.sp,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                    )
                }
            }

            Column(
                Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = product.title_fa,
                    fontSize = 12.sp,
                    fontFamily = MyCustomFont,
                    maxLines = 2,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 4.dp)
                )

                Row(Modifier.padding(top = 8.dp)) {
                    Image(
                        painter = painterResource(R.drawable.guarantee),
                        contentDescription = null
                    )
                    Text(
                        text = product.variants.get(0).warranty.title_fa,
                        fontSize = 12.sp,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                    )
                }

                Row(Modifier.padding(top = 4.dp)) {
                    Image(
                        painter = painterResource(R.drawable.seller),
                        contentDescription = null
                    )
                    Text(
                        text = product.variants.get(0).seller.title_fa,
                        fontSize = 12.sp,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                    )
                }

                product?.variants?.get(0)?.shipment_methods?.providers?.get(0)?.let {
                    Row(Modifier.padding(top = 4.dp)) {
                        Image(
                            painter = painterResource(R.drawable.delivary_express),
                            contentDescription = null
                        )
                        Text(
                            text = it.title,
                            fontSize = 12.sp,
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        )
                    }
                }

                if (product?.variants?.get(0)?.shipment_methods?.providers?.size!! > 1) {
                    product?.variants?.get(0)?.shipment_methods?.providers?.get(1)?.let {
                        Row(Modifier.padding(top = 4.dp)) {
                            Image(
                                painter = painterResource(R.drawable.delivery_today),
                                contentDescription = null
                            )
                            Text(
                                text = it.title,
                                fontSize = 12.sp,
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {

            manageBoxCountOfProductInShoppingCard(product.id)

            priseSectionInShoppingCard(product.price)
        }
    }
}

@Composable
fun manageBoxCountOfProductInShoppingCard(productId: Long) {
    val shoppingCardViewModel = LocalProvider.LocalShoppingCardViewModel.current
    var count by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .border(1.dp, color = Color.LightGray, RoundedCornerShape(5.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                Icons.Default.Add, tint = PrimaryColor, contentDescription = null,
                modifier = Modifier
                    .clickable {
                        count += 1
                        shoppingCardViewModel.updateProductCount(productId, count)
                    }
                    .padding(6.dp)
            )

            Text(
                text = count.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(6.dp)
            )

            Icon(
                painterResource(R.drawable.delete), tint = PrimaryColor, contentDescription = null,
                modifier = Modifier
                    .clickable {
                        shoppingCardViewModel.deleteProductById(productId)
                    }
                    .padding(6.dp)
            )
        }
    }
}

@Composable
fun priseSectionInShoppingCard(price: Price) {
    var takhfif = price.rrp_price - price.selling_price

    Column {
        if (takhfif != 0.toLong()) {
            Row {
                Text(
                    text = ConvertNumbers.convertToPersianDigits(
                        ConvertNumbers.convertRialToToman(takhfif.toString())
                    ),
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = PrimaryColor,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 4.dp)
                )

                Text(
                    text = "تومان تخفیف",
                    fontFamily = MyCustomFont,
                    color = PrimaryColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                )
            }
        }

        Row {
            Text(
                text = ConvertNumbers.convertToPersianDigits(
                    ConvertNumbers.convertRialToToman(price.selling_price.toString())
                ),
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 4.dp)
            )

            Text(
                text = "تومان",
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun ShoppingCardPageBottomBar(modifier: Modifier = Modifier, data: List<ShoppingCardEntity>) {
    val navController = LocalProvider.LocalNavController.current
    val lastPrise: Long = 0

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .border(1.dp, color = LightGrayColor),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

//                val payloadData = data.result.product.product_badges?.get(currentIndex)?.payload
//                payloadData?.let {
//                    val colorInt = ColorUtils.fromInt(android.graphics.Color.parseColor(payloadData?.text_color))
//                    Text(
//                        text = payloadData?.text.toString(),
//                        fontFamily = MyCustomFont,
//                        fontWeight = FontWeight.Normal,
//                        color = colorInt,
//                        fontSize = 14.sp,
//                        modifier = Modifier
//                            .padding(vertical = 8.dp)
//                    )
//                }

                Button(
                    onClick = {
                        navController.navigate(Const.HOME)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(45.dp)
                ) {
                    Text(
                        text = "ادامه فرایند خرید",
                        color = White,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Column {
//                if (data.result.product.price.rrp_price != data.result.product.price.selling_price) {
                Column(verticalArrangement = Arrangement.Center) {
//                        Text(
//                            text = ConvertNumbers.convertToPersianDigits("${data.result.product.price.discount_percent}٪"),
//                            color = White,
//                            fontWeight = FontWeight.Medium,
//                            fontFamily = MyCustomFont,
//                            fontSize = 13.sp,
//                            modifier = Modifier
//                                .background(PrimaryColor, shape = RoundedCornerShape(4.dp))
//                                .padding(horizontal = 6.dp, vertical = 2.dp)
//                        )
//                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "جمع سبد خرید",
                        fontWeight = FontWeight.Normal,
                        fontFamily = MyCustomFont,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
//                    }
                    Spacer(modifier = Modifier.height(4.dp))
//                    Text(
//                        text = "${
//                            ConvertNumbers.convertToPersianDigits(
//                                ConvertNumbers.convertRialToToman("95739310")
//                            )
//                        } تومان ",
//                        fontWeight = FontWeight.Normal,
//                        fontFamily = MyCustomFont,
//                        fontSize = 16.sp,
//                        color = Color.Black
//                    )
//                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${
                            ConvertNumbers.convertToPersianDigits(
                                ConvertNumbers.convertRialToToman(ConvertNumbers.calculatePrices(data).totalPriceWithProfit.toString())
                            )
                        } تومان ",
                        fontWeight = FontWeight.Normal,
                        fontFamily = MyCustomFont,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
//                }
                }
            }
        }
    }
}

@Composable
fun AccountPage() {
    val profileViewModel = LocalProvider.LocalProfileViewModel.current
    val isLoggedIn by profileViewModel.isUserLoggedIn.collectAsState()
    when (isLoggedIn) {
        null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        true -> {
            ProfilePage()
        }

        false -> {
            LoginPage()
        }
    }
}

@Composable
fun ProfilePage() {
    var showEditDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    val profileViewModel = LocalProvider.LocalProfileViewModel.current
    val user = profileViewModel.userData.collectAsState()
    val navController = LocalProvider.LocalNavController.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "پروفایل شما :",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Right
                )

                Row {
                    IconButton(onClick = { showEditDialog = true }) {
                        Icon(Icons.Default.Edit, contentDescription = "ویرایش", tint = IconColor)
                    }
                    IconButton(onClick = {
                        navController.navigate(Const.SHOPPING_CART)
                    }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "سبد خرید", tint = IconColor)
                    }
                    IconButton(onClick = {
                        showLogoutDialog = true
                    }) {
                        Icon(painterResource(R.drawable.logout), contentDescription = "خروج", tint = IconColor)
                    }
                }
            }

            ProfileItem(label = "نام و نام خانوادگی", value = user.value?.name.toString())
            ProfileItem(label = "شماره موبایل", value = user.value?.phoneNumber.toString())
            ProfileItem(label = "رمز عبور", value = user.value?.password.toString())
            ProfileItem(label = "کدملی", value = user.value?.nationalCode.toString())
            ProfileItem(label = "آدرس", value = user.value?.address.toString())

            if (showEditDialog) {
                EditProfileDialog(
                    showDialog = true,
                    onDismiss = { showEditDialog = false },

                    )
            }

            if (showLogoutDialog) {
                LogoutConfirmationDialog(
                    showLogoutDialog,
                    onConfirm = {
                        showLogoutDialog = true
                        profileViewModel.logout()
                        profileViewModel.deleteUser(user.value?.phoneNumber.toString())
                    },
                    onDismiss = { showLogoutDialog = false }
                )
            }
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp,
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (value != "null") {
            Text(
                text = value,
                fontSize = 16.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))
    }
}


@Composable
fun LogoutConfirmationDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            backgroundColor = White,
            shape = RoundedCornerShape(12.dp),
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = {
                                onDismiss()
                            },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "بستن دیالوگ",
                            )
                        }
//                        Text(
//                            text = "از حساب کاربری خارج می‌شوید؟",
//                            fontFamily = MyCustomFont,
//                            fontWeight = FontWeight.Medium,
//                            modifier = Modifier.align(Alignment.Center)
//                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "با خروج از حساب کاربری، به سبد خرید فعلی‌تان دسترسی نخواهید داشت",
                        textAlign = TextAlign.Center,
                        fontFamily = MyCustomFont,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                onDismiss()
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = PrimaryColor
                            ),
                            border = ButtonDefaults.outlinedBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE91E63))
                            )
                        ) {
                            Text(
                                "انصراف",
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Button(
                            onClick = {
                                onConfirm()
                                onDismiss()
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = PrimaryColor
                            )
                        ) {
                            Text(
                                "خروج از حساب",
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal,
                                color = White

                            )
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {}
        )
    }
}


@SuppressLint("RememberReturnType")
private fun isValidPhoneNumber(phone: String): Boolean {
    return phone.matches(Regex("^09\\d{9}$"))
}

@Composable
fun EditProfileDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    val profileViewModel = LocalProvider.LocalProfileViewModel.current
    val user by profileViewModel.userData.collectAsState()


    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {

            var name by remember { mutableStateOf("") }
            var nationalCode by remember { mutableStateOf("") }
            var address by remember { mutableStateOf("") }

            var nameError by remember { mutableStateOf(false) }
            var nationalCodeError by remember { mutableStateOf(false) }
            var addressError by remember { mutableStateOf(false) }

            if (user?.name != null) {
                name = user?.name.toString()
            }

            if (user?.nationalCode != null) {
                nationalCode = user?.nationalCode.toString()
            }

            if (user?.address != null) {
                address = user?.address.toString()
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, contentDescription = "بستن")
                        }
                        Text(
                            "ویرایش پروفایل",
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            if (it.isNotBlank()) nameError = false
                        },
                        label = {
                            Text(
                                "نام و نام خانوادگی *",
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        isError = nameError,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    if (nameError)
                        Text(
                            "نام و نام خانوادگی الزامی است",
                            color = Color.Red,
                            fontSize = 12.sp,
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Normal
                        )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nationalCode,
                        onValueChange = {
                            nationalCode = it
                            if (it.isNotBlank()) nationalCodeError = false
                        },
                        label = {
                            Text(
                                "کد ملی *",
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        isError = nationalCodeError,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                    if (nationalCodeError)
                        Text(
                            "کد ملی الزامی است",
                            color = Color.Red,
                            fontSize = 12.sp,
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Normal
                        )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = address,
                        onValueChange = {
                            address = it
                            if (it.isNotBlank()) addressError = false
                        },
                        label = {
                            Text(
                                " آدرس *",
                                fontFamily = MyCustomFont,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        isError = addressError,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )
                    if (addressError)
                        Text(
                            " آدرس الزامی است",
                            color = Color.Red,
                            fontSize = 12.sp,
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Normal
                        )

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            nameError = name.isBlank()
                            nationalCodeError = nationalCode.isBlank()
                            addressError = address.isBlank()

                            val hasError = nameError || nationalCodeError || addressError

                            if (!hasError) {
                                println("نام و نام خانوادگی: $name")
                                println("کد ملی: $nationalCode")
                                println("آدرس : $address")
                                profileViewModel.updateUserAccount(
                                    name,
                                    nationalCode,
                                    address,
                                    user?.phoneNumber.toString()
                                )
                                onDismiss()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor)
                    ) {
                        Text(
                            "تأیید",
                            color = White,
                            fontFamily = MyCustomFont,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun LoginPage() {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var phoneNumberErrorText by remember { mutableStateOf<String?>(null) }
    var passwordErrorText by remember { mutableStateOf<String?>(null) }
    val phoneFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val profileViewModel = LocalProvider.LocalProfileViewModel.current
    val context = LocalContext.current
    val registrationState by profileViewModel.registrationState.collectAsState()

    LaunchedEffect(registrationState) {
        when (registrationState) {
            RegistrationState.SUCCESS -> {
                profileViewModel.resetRegistrationState()
            }

            RegistrationState.USER_EXISTS -> {
                Toast.makeText(context, "با این شماره قبلا ثبت نام کرده‌اید!", Toast.LENGTH_LONG).show()
                profileViewModel.resetRegistrationState()
            }

            else -> {
                // برای وضعیت‌های IDLE و LOADING کاری انجام نمی‌دهیم
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.login_logo),
            contentDescription = "Digikala Logo",
            modifier = Modifier
                .height(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "برای ورود و یا ثبت‌نام در دیجی‌کالا شماره موبایل خود را وارد نمایید",
            textAlign = TextAlign.Center,
            fontFamily = MyCustomFont,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
                phoneNumberErrorText = null
            },
            label = {
                Text(
                    "شماره موبایل",
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Normal,
                )
            },
            placeholder = {
                Text(
                    "مثلاً 09123456789",
                    color = Color.Gray,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Normal,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(phoneFocusRequester),
            isError = phoneNumberErrorText != null,
            singleLine = true
        )
        phoneNumberErrorText?.let { error ->
            Text(
                text = error,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(start = 16.dp, top = 4.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordErrorText = null
            },
            label = {
                Text(
                    "رمز عبور",
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Normal,
                )
            },
            placeholder = {
                Text(
                    "رمز عبور خود را وارد کنید",
                    color = Color.Gray,
                    fontFamily = MyCustomFont,
                    fontWeight = FontWeight.Normal,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//            trailingIcon = {
//                IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                    Icon(
//                        imageVector = if (passwordVisible) Icons.Default.Star else Icons.Default.Search,
//                        contentDescription = if (passwordVisible) "مخفی کردن رمز" else "نمایش رمز"
//                    )
//                }
//            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
            isError = passwordErrorText != null,
            singleLine = true
        )
        passwordErrorText?.let { error ->
            Text(
                text = error,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(start = 16.dp, top = 4.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (phoneNumber.isBlank()) {
                    phoneNumberErrorText = "شماره موبایل نمی‌تواند خالی باشد"
                } else if (!isValidPhoneNumber(phoneNumber)) {
                    phoneNumberErrorText = "فرمت شماره موبایل صحیح نیست (مثلاً 09123456789)"
                } else {
                    phoneNumberErrorText = null
                }

                if (password.isBlank()) {
                    passwordErrorText = "رمز عبور نمی‌تواند خالی باشد"
                } else {
                    passwordErrorText = null
                }

                when {
                    phoneNumberErrorText != null -> {
                        phoneFocusRequester.requestFocus()
                        Toast.makeText(context, phoneNumberErrorText, Toast.LENGTH_SHORT).show()
                    }

                    passwordErrorText != null -> {
                        passwordFocusRequester.requestFocus()
                        Toast.makeText(context, passwordErrorText, Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        val user = UserEntity(phoneNumber, password)
                        profileViewModel.addUserToDatabase(user)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor)
        ) {
            Text(
                "ورود به دیجی‌کالا",
                color = White,
                fontFamily = MyCustomFont,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "ورود شما به معنای پذیرش شرایط دیجی‌کالا و قوانین حریم‌خصوصی است",
            fontFamily = MyCustomFont,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Preview(showBackground = true)
@Composable
fun Preview() {
//    ProductDetails()
//    ProductDetailsScreen()
//    ProductDetailsScreen()
//    DigikalaTheme {
//    BaseStructure(modifier = Modifier.background(White), homeViewModel)
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