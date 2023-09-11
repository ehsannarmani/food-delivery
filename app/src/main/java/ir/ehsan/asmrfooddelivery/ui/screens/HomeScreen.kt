package ir.ehsan.asmrfooddelivery.ui.screens

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ir.ehsan.asmrfooddelivery.R
import ir.ehsan.asmrfooddelivery.ui.components.TabLayout
import ir.ehsan.asmrfooddelivery.ui.theme.ubuntuFont

data class Food(
    val name: String,
    @DrawableRes val image: Int,
    val type: FoodType,
    val liked: Boolean = false,
    val price: Int = (10..100).random()
)

enum class FoodType {
    Meal, Side, Snack
}

val foods = listOf(
    Food(
        name = "Meal 1",
        image = R.drawable.meal_1,
        type = FoodType.Meal
    ),
    Food(
        name = "Meal 2",
        image = R.drawable.meal_2,
        type = FoodType.Meal
    ),
    Food(
        name = "Meal 3",
        image = R.drawable.meal_3,
        type = FoodType.Meal
    ),
    Food(
        name = "Meal 4",
        image = R.drawable.meal_4,
        type = FoodType.Meal
    ),
    Food(
        name = "Meal 5",
        image = R.drawable.meal_5,
        type = FoodType.Meal
    ),
    Food(
        name = "Meal 6",
        image = R.drawable.meal_6,
        type = FoodType.Meal
    ),
    Food(
        name = "Side 1",
        image = R.drawable.sides_1,
        type = FoodType.Side
    ),
    Food(
        name = "Side 2",
        image = R.drawable.sides_2,
        type = FoodType.Side
    ),
    Food(
        name = "Side 3",
        image = R.drawable.sides_3,
        type = FoodType.Side
    ),
    Food(
        name = "Side 4",
        image = R.drawable.sides_4,
        type = FoodType.Side
    ),
    Food(
        name = "Side 5",
        image = R.drawable.sides_5,
        type = FoodType.Side
    ),
    Food(
        name = "Side 6",
        image = R.drawable.sides_6,
        type = FoodType.Side
    ),
    Food(
        name = "Snack 1",
        image = R.drawable.snacks_1,
        type = FoodType.Snack
    ),
    Food(
        name = "Snack 2",
        image = R.drawable.snacks_2,
        type = FoodType.Snack
    ),
    Food(
        name = "Snack 3",
        image = R.drawable.snacks_3,
        type = FoodType.Snack
    ),
    Food(
        name = "Snack 4",
        image = R.drawable.snacks_4,
        type = FoodType.Snack
    ),
    Food(
        name = "Snack 5",
        image = R.drawable.snacks_5,
        type = FoodType.Snack
    ),
    Food(
        name = "Snack 6",
        image = R.drawable.snacks_6,
        type = FoodType.Snack
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val uiController = rememberSystemUiController()
    uiController.isStatusBarVisible = false

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Our Menu", fontFamily = ubuntuFont)
            },
            navigationIcon = {
                Row {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                }
            }
        )
    }) { paddings ->
        Column(modifier = Modifier.padding(paddings)) {
            val selectedFoodType = remember {
                mutableIntStateOf(0)
            }
            val foodsState = remember {
                mutableStateListOf(*(foods + foods).toTypedArray())
            }
            val onLikeChange: (Food) -> Unit = {
                foodsState[foodsState.indexOf(it)] =
                    foodsState[foodsState.indexOf(it)].copy(liked = !it.liked)
            }
            Spacer(modifier = Modifier.height(16.dp))
            TabLayout(
                items = listOf(
                    "Meals" to {
                        Foods(
                            items = foodsState.filter { it.type == FoodType.Meal },
                            onLikeChange = onLikeChange,
                            onTap = {
                                navController.navigate("food")
                            }
                        )
                    },
                    "Sides" to {
                        Foods(
                            items = foodsState.filter { it.type == FoodType.Side },
                            onLikeChange = onLikeChange,
                            onTap = {
                                navController.navigate("food")
                            }
                        )
                    },
                    "Snacks" to {
                        Foods(
                            items = foodsState.filter { it.type == FoodType.Snack },
                            onLikeChange = onLikeChange,
                            onTap = {
                                navController.navigate("food")
                            }
                        )
                    },
                ),
                selectedIndex = selectedFoodType.intValue,
                onTabClick = {
                    selectedFoodType.intValue = it
                },
                textHeight = 30.dp,
                indicatorPadding = PaddingValues(horizontal = 10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Foods(items: List<Food>, onLikeChange: (Food) -> Unit, onTap: (Food) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff1f1f1))
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(items) { index, food ->
                Card(
                    onClick = {
                        onTap(food)
                    },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Image(
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    onLikeChange(food)
                                },
                            painter = painterResource(
                                id =
                                if (food.liked) R.drawable.ic_like else R.drawable.ic_unlike
                            ),
                            contentDescription = null
                        )
                    }
                    Column(modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            modifier= Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            painter = painterResource(id = food.image),
                            contentDescription = food.name,
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = food.name, fontSize = 15.sp, color = Color(0xff383838))
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "${food.price}$")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}