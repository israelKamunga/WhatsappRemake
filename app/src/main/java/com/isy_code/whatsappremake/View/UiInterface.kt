package com.isy_code.whatsappremake.View

import android.annotation.SuppressLint
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.isy_code.whatsappremake.R
import com.isy_code.whatsappremake.Utils.Screen
import com.isy_code.whatsappremake.Utils.TabRowItem
import com.isy_code.whatsappremake.ui.theme.gray
import com.isy_code.whatsappremake.ui.theme.green
import com.isy_code.whatsappremake.ui.theme.greenDeclined
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun Nav(){
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.screen){
        composable(route = Screen.Home.screen){
            MainScreen(navController)
        }
        composable(route = Screen.ChatScreen.screen){
            MessagesScreen(navController)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Preview(showSystemUi = true)
@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = { FloatingButton() }
    ) {
        Tab(navController)
    }
}

@Composable
fun TopBar(){
    var DropMenuState by remember { mutableStateOf(false) }
    var options = listOf("Settings","Preferences","Log out")
    val offset = DpOffset(x = -10.dp, y = -100.dp)
    TopAppBar(
        title = {
            Text(text = "WhatsAppRemake", overflow = TextOverflow.Ellipsis, softWrap = false, color = Color.White)
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24), contentDescription = "", tint = Color.White)
            }
            IconButton(onClick = { DropMenuState = !DropMenuState }) {
                Icon(painter = painterResource(id = R.drawable.ic_more), contentDescription = "", tint = Color.White)
            }
            DropdownMenu(
                expanded = DropMenuState,
                onDismissRequest = { DropMenuState = !DropMenuState },
                offset = offset
            ) {
                options.forEach{
                    DropdownMenuItem(
                        onClick = {}
                    ) {
                        Text(text = it)
                    }
                }
            }
        },
        backgroundColor = greenDeclined,
        elevation = 0.dp
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tab(navController: NavController){
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    var tabRowItems = listOf(
        "",
        "CHATS",
        "STATUS",
        "CALLS"
    )

    Column(

    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = Color.White
                )
            },
            backgroundColor = greenDeclined
        ) {
            tabRowItems.forEachIndexed { index, item ->
                if(index==0){
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                text = item,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Icon(painter = painterResource(id = R.drawable.ic_camera), contentDescription = "",tint = Color.White)
                        }
                    )
                }else{
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                text = item,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White
                            )
                        }
                    )
                }
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState,
        ) {it->
            when(it){
                1->{
                    Column {
                        ChatScreenContent(navController)
                        ChatScreenContent(navController)
                        ChatScreenContent(navController)
                        ChatScreenContent(navController)
                        ChatScreenContent(navController)
                        ChatScreenContent(navController)
                    }
                }
                2->{
                    Column {
                        StatusComponent()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatScreenContent(navController: NavController){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = { navController.navigate(Screen.ChatScreen.screen) },
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 5.dp, start = 10.dp, top = 5.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.yourname),
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .align(Alignment.CenterStart),
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .height(45.dp)
                .width(290.dp)
                .padding(start = 65.dp)
            ) {
                Text(text = "Programmer",modifier = Modifier.padding(vertical = 5.dp), fontWeight = FontWeight.Bold)
                Text(text = "Hi, Programmer How are you?", modifier = Modifier.align(Alignment.BottomStart), overflow = TextOverflow.Ellipsis, color = gray)
            }
            Box(modifier = Modifier
                .height(45.dp)
                .width(60.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)) {
                Text(text = "12:00",modifier = Modifier
                    .padding(vertical = 5.dp)
                    .align(Alignment.TopEnd))
                Badge(
                    backgroundColor = green,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) { Text("8") }
            }
        }
    }
}

@Composable
fun FloatingButton(){
    FloatingActionButton(
        onClick = { /*TODO*/ },
        backgroundColor = greenDeclined
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_message), contentDescription = "",tint = Color.White)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatusComponent(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = {},
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 5.dp, start = 10.dp, top = 5.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.yourname),
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(55.dp)
                    .align(Alignment.CenterStart)
                    .border(2.dp, green, CircleShape),
                contentScale = ContentScale.Crop,
            )
            Box(modifier = Modifier
                .height(45.dp)
                .width(290.dp)
                .padding(start = 65.dp)
            ) {
                Text(text = "My Status",modifier = Modifier.padding(vertical = 5.dp), fontWeight = FontWeight.Bold)
                Text(text = "Today, 12:30 am", modifier = Modifier.align(Alignment.BottomStart), overflow = TextOverflow.Ellipsis, color = gray)
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = "",
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun MessagesScreen(navController: NavController = rememberNavController()){
    Scaffold(
        topBar = { MessageScreenTopBar(navController) },
        bottomBar = { MessageScreenBottomAppBar() }
    ) {

    }
}

@Composable
fun MessageScreenTopBar(navController: NavController){
    TopAppBar(
        title = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = -20.dp)) {
                    Image(painter = painterResource(id = R.drawable.yourname), contentDescription = "",modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp)
                        .align(CenterVertically), contentScale = ContentScale.Crop)
                    Column(modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()) {
                        Text(text = "Programmer",fontWeight = FontWeight.Bold,color = Color.White, overflow = TextOverflow.Ellipsis, fontSize = 18.sp, softWrap = false, modifier = Modifier.fillMaxWidth())
                        Text(text = "online", fontSize = 12.sp, color = Color.White,modifier = Modifier.padding(bottom = 10.dp))
                    }
                }
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Screen.Home.screen) }) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "",tint = Color.White)
            }
        },
        backgroundColor = greenDeclined,
        actions = {
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_videocam), contentDescription = "", tint = Color.White)
            }
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_phone), contentDescription = "", tint = Color.White)
            }
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_more), contentDescription = "", tint = Color.White)
            }
        }
    )
}

@Composable
fun MessageScreenBottomAppBar(){
    val textFieldValue by remember {
        mutableStateOf("")
    }
    BottomAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = {},
            placeholder = { Text(text = "Message") },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.ic_emoji), contentDescription = "")
                }
            },
            trailingIcon = {
                Row {
                    Icon(painter = painterResource(id = R.drawable.ic_attachment), contentDescription = "")
                    Spacer(modifier = Modifier.size(10.dp))
                    Icon(painter = painterResource(id = R.drawable.ic_camera), contentDescription = "")
                }
            }
        )
        FloatingActionButton(onClick = {}) {
            Icon(painter = painterResource(id = R.drawable.ic_microphone), contentDescription = "",tint = Color.White)
        }

    }
}