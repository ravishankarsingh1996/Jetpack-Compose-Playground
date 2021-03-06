package de.jensklingenberg.jetpackcomposeplayground.ui.samples.mysamples.layout

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.TextButtonStyle
import androidx.ui.material.surface.Surface
import androidx.ui.material.themeColor
import androidx.ui.material.themeTextStyle
import de.jensklingenberg.jetpackcomposeplayground.Navigator
import de.jensklingenberg.jetpackcomposeplayground.data.mainPagesEntries
import de.jensklingenberg.jetpackcomposeplayground.ui.samples.common.VectorImage

import de.jensklingenberg.jetpackcomposeplayground.ui.samples.R


@Composable
fun AppDrawer(
    closeDrawer: () -> Unit,
    navigator: Navigator
) {

    VerticalScroller {

        Column {
            Column {
                DrawerButton(
                    icon = R.drawable.ic_interests,
                    label = "Home",
                    isSelected = false
                ) {
                    navigator.navigateToHome()
                    closeDrawer()
                }
                mainPagesEntries.forEachIndexed { index, page ->
                    DrawerButton(
                        icon = R.drawable.ic_interests,
                        label = page.title,
                        isSelected = navigator.getIndex().value == index
                    ) {
                        navigator.getIndex().value = index
                        closeDrawer()
                    }

                }
            }
        }


    }
}


@Composable
private fun DrawerButton(
    @DrawableRes icon: Int,
    label: String,
    isSelected: Boolean,
    action: () -> Unit
) {
    val textIconColor = if (isSelected) {
        +themeColor { primary }
    } else {
        (+themeColor { onSurface }).copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        (+themeColor { primary }).copy(alpha = 0.12f)
    } else {
        +themeColor { surface }
    }

    Padding(left = 8.dp, top = 8.dp, right = 8.dp) {
        Surface(
            color = backgroundColor,
            shape = RoundedCornerShape(4.dp)
        ) {
            Button(onClick = action, style = TextButtonStyle()) {
                Row(
                    mainAxisSize = LayoutSize.Expand,
                    crossAxisAlignment = CrossAxisAlignment.Center
                ) {
                    VectorImage(
                        id = icon,
                        tint = textIconColor
                    )
                    WidthSpacer(16.dp)
                    Text(
                        text = label,
                        style = (+themeTextStyle { body2 }).copy(
                            color = textIconColor
                        )
                    )
                }
            }
        }
    }
}