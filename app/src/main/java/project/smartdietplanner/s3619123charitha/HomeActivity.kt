package project.smartdietplanner.s3619123charitha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.smartdietplanner.s3619123charitha.ui.theme.SmartDietPlanner


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DietHomeScreen()
        }

    }
}

// COLORS

val DietGreen = Color(0xFF31966D)
val DietBackground = Color(0xFFF5F8F5)
val LightGreen = Color(0xFFE5F3EA)
val DietText = Color(0xFF26352D)
val GrayText = Color(0xFF858D87)

// MEAL MODEL

data class Meal(
    val name: String,
    val food: String,
    val calories: Int
)

// SAMPLE MEAL DATA

val sampleMeals = listOf(
    Meal("Breakfast", "Oatmeal and Banana", 300),
    Meal("Lunch", "Rice and Vegetable Curry", 450),
    Meal("Dinner", "Chapati and Dal", 0)
)

// MAIN SCREEN

@Composable
fun DietHomeScreen(
    meals: List<Meal> = sampleMeals,
    onProfileClick: () -> Unit = {},
    onPlanMealsClick: () -> Unit = {},
    onAddFoodClick: (String) -> Unit = {},
    onBmiClick: () -> Unit = {},
    onFoodClick: () -> Unit = {},
    onMealPlanClick: () -> Unit = {},
    onHomeClick: () -> Unit = {}
) {

    val calorieTarget = 2000

    val caloriesConsumed = meals.sumOf { it.calories }

    val caloriesRemaining =
        (calorieTarget - caloriesConsumed).coerceAtLeast(0)

    Scaffold(
        containerColor = DietBackground,

        bottomBar = {

            NavigationBar(
                containerColor = Color.White
            ) {

                NavigationBarItem(
                    selected = true,
                    onClick = onHomeClick,
                    icon = {
                        Icon(Icons.Default.Home, null)
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onFoodClick,
                    icon = {
                        Icon(Icons.Default.Restaurant, null)
                    },
                    label = {
                        Text("Food")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onMealPlanClick,
                    icon = {
                        Icon(Icons.Default.CalendarMonth, null)
                    },
                    label = {
                        Text("Meal Plan")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onProfileClick,
                    icon = {
                        Icon(Icons.Default.Person, null)
                    },
                    label = {
                        Text("Profile")
                    }
                )
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            // TOP BAR

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        "Smart Diet Planner",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = DietGreen
                    )

                    IconButton(
                        onClick = onProfileClick
                    ) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Profile",
                            tint = DietGreen,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    "Hello, User! 👋",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = DietText
                )

                Text(
                    "Let's make healthy choices today.",
                    fontSize = 13.sp,
                    color = GrayText
                )
            }

            // CALORIE SUMMARY

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            "Daily Calorie Target",
                            fontSize = 15.sp,
                            color = DietText,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            "$calorieTarget kcal",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = DietGreen
                        )

                        Spacer(Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {
                                Text(
                                    "Consumed",
                                    color = GrayText,
                                    fontSize = 12.sp
                                )

                                Text(
                                    "$caloriesConsumed kcal",
                                    fontWeight = FontWeight.Bold,
                                    color = DietText
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    "Remaining",
                                    color = GrayText,
                                    fontSize = 12.sp
                                )

                                Text(
                                    "$caloriesRemaining kcal",
                                    fontWeight = FontWeight.Bold,
                                    color = DietGreen
                                )
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        LinearProgressIndicator(
                            progress = {
                                (caloriesConsumed.toFloat() /
                                        calorieTarget).coerceIn(0f, 1f)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = DietGreen,
                            trackColor = LightGreen
                        )
                    }
                }
            }

            // MEALS TITLE

            item {

                Text(
                    "Today's Meals",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DietText
                )
            }

            // MEAL CARDS

            items(3) { index ->

                val meal = meals.getOrNull(index)
                    ?: listOf("Breakfast", "Lunch", "Dinner")[index].let {
                        Meal(it, "", 0)
                    }

                MealCard(
                    meal = meal,
                    onAddFood = {
                        onAddFoodClick(meal.name)
                    }
                )
            }

            // PLAN MEALS BUTTON

            item {

                Button(
                    onClick = onPlanMealsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DietGreen
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {

                    Icon(
                        Icons.Default.RestaurantMenu,
                        contentDescription = null
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        "Plan Meals",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // BMI SECTION

            item {

                Text(
                    "Your BMI",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DietText
                )

                BmiCard(
                    onClick = onBmiClick
                )
            }
        }
    }
}

// MEAL CARD

@Composable
fun MealCard(
    meal: Meal,
    onAddFood: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                Icons.Default.Restaurant,
                contentDescription = null,
                tint = DietGreen,
                modifier = Modifier.size(30.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    meal.name,
                    fontWeight = FontWeight.Bold,
                    color = DietText
                )

                if (meal.food.isNotBlank()) {

                    Text(
                        meal.food,
                        fontSize = 12.sp,
                        color = GrayText
                    )

                    Text(
                        "${meal.calories} kcal",
                        fontSize = 12.sp,
                        color = DietGreen,
                        fontWeight = FontWeight.SemiBold
                    )

                } else {

                    Text(
                        "No food added yet",
                        fontSize = 12.sp,
                        color = GrayText
                    )
                }
            }

            TextButton(
                onClick = onAddFood
            ) {

                Text(
                    if (meal.food.isBlank()) "Add Food" else "Edit",
                    color = DietGreen
                )
            }
        }
    }
}

// BMI CARD

@Composable
fun BmiCard(
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGreen
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        "Current BMI",
                        fontSize = 13.sp,
                        color = GrayText
                    )

                    Text(
                        "22.5",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = DietGreen
                    )

                    Text(
                        "Normal",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DietText
                    )
                }

                Icon(
                    Icons.Default.MonitorWeight,
                    contentDescription = null,
                    tint = DietGreen,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {

                Text("Calculate BMI")
            }
        }
    }
}

// PREVIEW

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DietHomePreview() {

    MaterialTheme {
        DietHomeScreen()
    }
}
