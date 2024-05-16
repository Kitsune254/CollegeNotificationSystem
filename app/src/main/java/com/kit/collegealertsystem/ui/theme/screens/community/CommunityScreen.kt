package com.kit.collegealertsystem.ui.theme.screens.community

import FirebaseImage
import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.kit.collegealertsystem.R
import com.kit.collegealertsystem.navigation.ROUTE_ACADEMICS
import com.kit.collegealertsystem.navigation.ROUTE_CLUBS
import com.kit.collegealertsystem.navigation.ROUTE_COMMUNITY
import com.kit.collegealertsystem.navigation.ROUTE_HOME
import com.kit.collegealertsystem.navigation.ROUTE_PROFILE
import com.kit.collegealertsystem.navigation.ROUTE_SPORT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(navController: NavHostController,
                    modifier: Modifier = Modifier,


) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    BackHandler {
        navController.popBackStack()
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader(modifier)
                Divider()
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Home",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 30.sp
                        )
                    },
                    selected = false,
                    onClick = {
                        scope.launch{
                            drawerState.close()
                        }
                        navController.navigate(ROUTE_HOME){
                            popUpTo(0)
                        }
                        //closeDrawer()
                    },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
                    shape = MaterialTheme.shapes.small
                )
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Academics",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 30.sp
                        )
                    },
                    selected = false,
                    onClick = {
                        scope.launch{
                            drawerState.close()
                        }
                        navController.navigate(ROUTE_ACADEMICS){
                            popUpTo(0)
                        }
                        //closeDrawer()
                    },
                    icon = { Icon(imageVector = Icons.Default.Create, contentDescription = null) },
                    shape = MaterialTheme.shapes.small
                )
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Clubs",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 30.sp
                        )
                    },
                    selected = false,
                    onClick = {
                        scope.launch{
                            drawerState.close()
                        }
                        navController.navigate(ROUTE_CLUBS){
                            popUpTo(0)
                        }
                        //closeDrawer()
                    },
                    icon = { Icon(imageVector = Icons.Default.Place, contentDescription = null) },
                    shape = MaterialTheme.shapes.small
                )
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Sports",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 30.sp
                        )
                    },
                    selected = false,
                    onClick = {
                        scope.launch{
                            drawerState.close()
                        }
                        navController.navigate(ROUTE_SPORT){
                            popUpTo(0)
                        }
                        //closeDrawer()
                    },
                    icon = { Icon(imageVector = Icons.Default.Star, contentDescription = null) },
                    shape = MaterialTheme.shapes.small
                )
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Community",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 30.sp
                        )
                    },
                    selected = false,
                    onClick = {
                        scope.launch{
                            drawerState.close()
                        }
                        navController.navigate(ROUTE_COMMUNITY){
                            popUpTo(0)
                        }
                        //closeDrawer()
                    },
                    icon = { Icon(imageVector = Icons.Default.Face, contentDescription = null) },
                    shape = MaterialTheme.shapes.small,


                )
            }
        },
        gesturesEnabled = drawerState.isOpen
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = Color.DarkGray,
//                        titleContentColor = Color.White,
//                        navigationIconContentColor = Color.White
//                    ),
                    title = {
                        Text(text = "Community Service")
                    },
                    navigationIcon = {
                        IconButton(onClick = {scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }}
                        ) {
                            Icon(Icons.Filled.Menu, "menuIcon")
                        }
                    },
                    actions = {
                        IconButton(onClick = {navController.navigate(ROUTE_PROFILE)}
                        ) {
                            Icon(Icons.Filled.AccountCircle, "Profile")
                        }
                    }
//                backgroundColor = MaterialTheme.colors.primary,
//                contentColor = Color.White,
//                elevation = 10.dp
                )

            },
            content = {
                LazyColumn (
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.padding(it)
                ){
                    item {
                        Card(onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth()
                                .fillMaxSize()
                                .size(width = 150.dp, height = 300.dp)
                        ) {
                            Box {
                                FirebaseImage(
                                    imagePath = "images/cleanup.jpg",
                                    modifier = Modifier.fillMaxSize(),
                                )
                                Box {
                                    Text(text = "Clean Up",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(30.dp))

                        Divider(modifier = Modifier.padding(horizontal = 20.dp))

                        Spacer(modifier = Modifier.height(30.dp))

                        Card(onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth()
                                .size(width = 150.dp, height = 300.dp)
                        ) {
                            Box {
                                FirebaseImage(
                                    imagePath = "images/fooddrive.jpg",
                                    modifier = Modifier.fillMaxSize(),
                                )
                                Box {
                                    Text(text = "Food Drive",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))

                        Divider(Modifier.padding(horizontal = 20.dp))

                        Spacer(modifier = Modifier.height(30.dp))

                        Card(onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth()
                                .size(width = 150.dp, height = 300.dp)
                        ) {
                            Box {
                                FirebaseImage(
                                    imagePath = "images/childrenshome.jpg",
                                    modifier = Modifier.fillMaxSize(),
                                )
                                Box {
                                    Text(text = "Children's Shelter",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))

                        Divider(Modifier.padding(horizontal = 20.dp))

                        Spacer(modifier = Modifier.height(30.dp))

                        Card(onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth()
                                .size(width = 150.dp, height = 300.dp)
                        ) {
                            Box {
                                FirebaseImage(
                                    imagePath = "images/health.jpg",
                                    modifier = Modifier.fillMaxSize(),
                                )
                                Box {
                                    Text(text = "Health Awareness",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp
                                    )
                                }
                            }
                        }
//                    Row (
//                        modifier = modifier
//                            //.background(MaterialTheme.colorScheme.secondary)
//                            .padding(
//                                horizontal = 20.dp,
//                                vertical = 20.dp
//                            )
//                            .fillMaxWidth()
//                    ){
//
//
//                    }
                    }
                }
                // Screen content
            }

//            floatingActionButton = {
//                ExtendedFloatingActionButton(
//                    text = { Text("Show drawer") },
//                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
//                    onClick = {
//                        scope.launch {
//                            drawerState.apply {
//                                if (isClosed) open() else close()
//                            }
//                        }
//                    }
//                )
//            }
        )
    }
}

@Composable
fun DrawerHeader(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding()
            .fillMaxWidth()
    ) {

        Image(
            painterResource(id = R.drawable.logo3),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)
                .clickable {}

        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
private fun CommunityPrev() {
    CommunityScreen(rememberNavController())
}