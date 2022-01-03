package com.example.revealanimation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.revealanimation.ui.theme.LightGreen
import com.example.revealanimation.ui.theme.Purple200
import com.example.revealanimation.ui.theme.darkGreen

@Composable
@Preview
fun RatingBar() {
    var selectedTab by remember { mutableStateOf(1) }
    val updateSelectedTab: (Int) -> Unit = { tab ->
        selectedTab = tab
    }

    Row(Modifier.fillMaxWidth()) {
        Surface(elevation = 0.dp) {
            BottomNavigationSlidingIndicator(
                selectedTab = selectedTab,
                updateSelectedTab = updateSelectedTab
            )
        }
    }
}


@Composable
fun BottomNavigationSlidingIndicator(selectedTab: Int, updateSelectedTab: (Int) -> Unit) {
    BoxWithConstraints(
        modifier = Modifier
            .height(93.dp)
            .background(
                color = Color.White
            ),
    ) {
        //Width of screen divided by number of tabs
        val bottomNavTabWidth = maxWidth / 5
        val tabModifier = Modifier.width(bottomNavTabWidth)

        //dp to offset the pill on the selected tab
        val offset by animateDpAsState(
            targetValue = ((bottomNavTabWidth * selectedTab) - ((bottomNavTabWidth / 2) + 39.dp)),
            animationSpec = tween(300)
        )

        //tab indicator
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.Transparent,
            modifier = Modifier
                .size(width = 78.dp, height = 80.dp)
                .offset(x = offset, y = 12.dp)
                .border(2.dp, darkGreen)
                .zIndex(10f)

        ) {

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            BottomTab(
                selected = selectedTab == 1,
                label = "1",
                icon = Icons.Default.Home,
                modifier = tabModifier.weight(1f)
            ) {
                updateSelectedTab(1)
            }
            BottomTab(
                selected = selectedTab == 2,
                label = "2",
                icon = Icons.Default.Search,
                modifier = tabModifier.weight(1f)

            ) {
                updateSelectedTab(2)
            }
            BottomTab(
                selected = selectedTab == 3,
                label = "3",
                icon = Icons.Default.Favorite,
                modifier = tabModifier.weight(1f)

            ) {
                updateSelectedTab(3)
            }
            BottomTab(
                selected = selectedTab == 4,
                label = "4",
                icon = Icons.Default.ShoppingCart,
                modifier = tabModifier.weight(1f)

            ) {
                updateSelectedTab(4)
            }
            BottomTab(
                selected = selectedTab == 5,
                label = "5",
                icon = Icons.Default.Settings,
                modifier = tabModifier.weight(1f)

            ) {
                updateSelectedTab(5)
            }
        }
    }
}

/**
 * Individual Tab
 */
@Composable
fun BottomTab(
    selected: Boolean,
    label: String,
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val iconColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colors.primary
        else Color.White.copy(.8f),
        animationSpec = tween(300)
    )

    val interactionSource = remember { MutableInteractionSource() }
    val ripple =
        rememberRipple(bounded = false, color = MaterialTheme.colors.primaryVariant.copy(.8f))

    Card(
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = true,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            )
            .fillMaxSize(),
        backgroundColor = LightGreen,
        shape = RoundedCornerShape(10.dp)
    ) {


        Text(
            text = label,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}

/**
 * package com.example.revealanimation.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.revealanimation.ui.theme.Purple200

@Composable
@Preview
fun RatingBar() {
var selectedTab by remember { mutableStateOf(1) }
val updateSelectedTab: (Int) -> Unit = { tab ->
selectedTab = tab
}

Row(Modifier.fillMaxWidth()) {
Surface(elevation = 8.dp) {
BottomNavigationSlidingIndicator(
selectedTab = selectedTab,
updateSelectedTab = updateSelectedTab
)
}
}
}


@Composable
fun BottomNavigationSlidingIndicator(selectedTab: Int, updateSelectedTab: (Int) -> Unit) {
BoxWithConstraints(
modifier = Modifier
.height(80.dp)
.background(
color = MaterialTheme.colors.primary
),
) {
//Width of screen divided by number of tabs
val bottomNavTabWidth = maxWidth / 5
val tabModifier = Modifier.width(bottomNavTabWidth)

//dp to offset the pill on the selected tab
val offset by animateDpAsState(
targetValue = ((bottomNavTabWidth * selectedTab) - ((bottomNavTabWidth / 2) + 30.dp)),
animationSpec = tween(300)
)

//tab indicator
Surface(
shape = RoundedCornerShape(20.dp),
color = Color.Transparent,
modifier = Modifier
.size(width = 60.dp, height = 62.dp)
.offset(x = offset, y = 12.dp)
.border(1.dp, Color.Yellow)
) {
Card(backgroundColor = Purple200) {}
}

Row(
modifier = Modifier
.fillMaxWidth()
.padding(top = 12.dp),
horizontalArrangement = Arrangement.spacedBy(4.dp)
) {
BottomTab(
selected = selectedTab == 1,
label = "1",
modifier = tabModifier.weight(1f)
) {
updateSelectedTab(1)
}
BottomTab(
selected = selectedTab == 2,
label = "2",
modifier = tabModifier.weight(1f)
) {
updateSelectedTab(2)
}
BottomTab(
selected = selectedTab == 3,
label = "3",
modifier = tabModifier.weight(1f)
) {
updateSelectedTab(3)
}
BottomTab(
selected = selectedTab == 4,
label = "4",
modifier = tabModifier.weight(1f)
) {
updateSelectedTab(4)
}
BottomTab(
selected = selectedTab == 5,
label = "5",
modifier = tabModifier.weight(1f)
) {
updateSelectedTab(5)
}
}
}
}

/**
 * Individual Tab
*/
@Composable
fun BottomTab(
selected: Boolean,
label: String,
modifier: Modifier,
onClick: () -> Unit
) {

val interactionSource = remember { MutableInteractionSource() }
val ripple =
rememberRipple(bounded = false, color = MaterialTheme.colors.primaryVariant.copy(.8f))

Card(shape = RoundedCornerShape(8.dp), modifier = modifier) {
Column(
modifier = modifier
.selectable(
selected = selected,
onClick = onClick,
enabled = true,
role = Role.Tab,
interactionSource = interactionSource,
indication = ripple
)
.border(1.dp, Color.Red),
horizontalAlignment = Alignment.CenterHorizontally
) {

Text(
text = label,
fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
style = MaterialTheme.typography.body1,
modifier = Modifier.padding(24.dp)
)
}
}
}

 */