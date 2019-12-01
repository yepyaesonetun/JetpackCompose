package com.prime.ypst.jetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Context
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.engine.geometry.Shape
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.Image
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myApp()
        }
    }
}

@Composable
fun hotNews() {
    val image = +imageResource(R.mipmap.canary)

    Column(
        crossAxisSize = LayoutSize.Expand,
        modifier = Spacing(8.dp)
    ) {

        Container(expanded = true, height = 200.dp) {
            Clip(shape = RoundedCornerShape(4.dp)) {
                DrawImage(image = image)
            }

        }
        HeightSpacer(height = 16.dp)

        Text(
            text = "Android Studio 4 (Canary 4.2)",
            style = (+themeTextStyle { h6 }).withOpacity(0.8f)
        )
        Text(
            text = "Android studio 4.0 is available on Canary Channel. Preview builds give you early access to new features in all aspects of the IDE, plus early versions of other tools such as the Android Emulator and platform SDK previews.",
            style = (+themeTextStyle { body2 }).withOpacity(0.6f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "3 Nov 2019",
            style = TextStyle(
                fontStyle = androidx.ui.text.font.FontStyle.Italic,
                fontSize = 10.sp
            ),
            modifier = Spacing(0.dp, 4.dp, 0.dp, 4.dp)
        )

    }
}

@Composable
fun createList() {

    val image = +imageResource(R.mipmap.canary)
    val context = +ambient(ContextAmbient)

    VerticalScroller {
        Column(
            crossAxisSize = LayoutSize.Expand,
            modifier = Spacing(8.dp)
        ) {
            (0..10).forEachIndexed { index, i ->
                listItem(
                    context = context,
                    index = index,
                    imagePath = image,
                    newsTitle = "Hello",
                    newsDescpt = "Descpt",
                    publishedDate = "1 Nov 2019"
                )
            }
        }

    }
}

@Composable
fun listItem(
    context: Context,
    index: Int,
    imagePath: Image,
    newsTitle: String,
    newsDescpt: String,
    publishedDate: String
) {

    Padding(padding = 4.dp) {

        Column(
            crossAxisSize = LayoutSize.Expand,
            modifier = Spacing(8.dp)
        ) {

            Container(expanded = true, height = 200.dp) {
                Clip(shape = RoundedCornerShape(4.dp)) {
                    DrawImage(image = imagePath)
                }

            }
            HeightSpacer(height = 16.dp)

            Text(
                text = "A$newsTitle $index",
                style = (+themeTextStyle { h6 }).withOpacity(0.8f)
            )
            Text(
                text = newsDescpt,
                style = (+themeTextStyle { body2 }).withOpacity(0.6f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            FlexRow(crossAxisAlignment = CrossAxisAlignment.Center, block = {
                expanded(1.0f) {
                    Text(
                        text = publishedDate,
                        style = TextStyle(
                            fontStyle = androidx.ui.text.font.FontStyle.Italic,
                            fontSize = 10.sp
                        ),
                        modifier = Spacing(0.dp, 4.dp, 0.dp, 4.dp)
                    )
                }
                inflexible {
                    Button(text = "See Details", onClick = {
                        Toast.makeText(context, "Clicked $index", Toast.LENGTH_LONG).show()
                    }, style = TextButtonStyle(contentColor = Color.Magenta))
                }
            })


        }
    }
}

fun TextButtonStyle(
    shape: Shape = +themeShape { button },
    contentColor: Color? = Color(R.color.colorAccent)
) = ButtonStyle(
    color = Color.Transparent,
    shape = shape,
    paddings = EdgeInsets(16.dp),
    textStyle = TextStyle(color = contentColor),
    rippleColor = contentColor
)

@Preview
@Composable
fun DefaultPreview() {
    myApp()
}

@Composable
fun appBar(title: String) {
    TopAppBar(title = {
        Text(text = title)
    })
}

@Composable
fun myApp() {
    MaterialTheme {
        FlexColumn(block = {
            inflexible {
                appBar(title = "Hello Jetpack Compose")
            }
            expanded(1.0f) {
                createList()
            }
        })

    }
}
