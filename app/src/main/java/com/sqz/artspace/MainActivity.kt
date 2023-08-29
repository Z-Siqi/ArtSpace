package com.sqz.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sqz.artspace.ui.theme.ArtSpaceTheme
import java.util.Timer
import kotlin.concurrent.schedule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
internal fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    var pictureNumber by remember { mutableStateOf(1) }
    val showPicture = pictureNumber
    val showText = pictureNumber

    if (pictureNumber == 0) {
        Timer().schedule(120L) {
            if (pictureNumber == 0) {
                pictureNumber = 10
            }
        }
    } else if (pictureNumber == 11) {
        Timer().schedule(100L) {
            if (pictureNumber == 11) {
                pictureNumber = 1
            }
        }
    } else if (pictureNumber >= 12) {
        pictureNumber = 12
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .height(100.dp)
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .padding(bottom = 20.dp)
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .shadow(elevation = 5.dp, RoundedCornerShape(3.dp))
                .border(
                    30.dp,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    RoundedCornerShape(3.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            BoxWithConstraints {
                if ((maxHeight > 700.dp) && (maxWidth > 1100.dp)) {
                    ArtSpaceImage(
                        modifier = modifier
                            .height(580.dp)
                            .width(480.dp),
                        showPicture
                    )
                } else if ((maxHeight > 700.dp) && (maxWidth > 390.dp)) {
                    ArtSpaceImage(
                        modifier = modifier
                            .height(450.dp)
                            .width(320.dp),
                        showPicture
                    )
                } else if ((maxHeight > 290.dp) && (maxWidth > 510.dp)) {
                    ArtSpaceImage(
                        modifier = modifier
                            .height(360.dp)
                            .width(480.dp),
                        showPicture
                    )
                } else {
                    ArtSpaceImage(
                        modifier = modifier
                            .height(380.dp)
                            .width(270.dp),
                        showPicture
                    )
                }
            }
        }
        BoxWithConstraints {
            if (maxHeight > 300.dp) {
                Spacer(modifier = modifier.height(50.dp))
            } else if (maxHeight > 200.dp) {
                Spacer(modifier = modifier.height(20.dp))
            }
        }
        if ((pictureNumber <= 11) && (pictureNumber >= 0)) {
            Box(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .width(240.dp)
                    .height(80.dp)
                    .shadow(elevation = 1.dp)
                    .padding(start = 10.dp, top = 5.dp),
                contentAlignment = Alignment.TopCenter
            ) { ArtSpaceText(showText) }
        } else {
            Box(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.errorContainer)
                    .width(300.dp)
                    .height(140.dp)
                    .padding(start = 10.dp, top = 5.dp),
                contentAlignment = Alignment.TopCenter,
            ) { ArtSpaceText(showText) }
        }
    }
    ChangePictureButton(
        onClick = { pictureNumber-- },
        text = stringResource(R.string.previous),
        horizontalAlignment = Alignment.Start
    )
    ChangePictureButton(
        onClick = { pictureNumber++ },
        text = stringResource(R.string.next),
        horizontalAlignment = Alignment.End
    )
}

@Composable
fun ArtSpaceImage(modifier: Modifier = Modifier, showPicture: Int) {
    val image = when (showPicture) {
        0 -> R.drawable.picture_10
        1 -> R.drawable.picture_1
        2 -> R.drawable.picture_2
        3 -> R.drawable.picture_3
        4 -> R.drawable.picture_7
        5 -> R.drawable.picture_5
        6 -> R.drawable.picture_6
        7 -> R.drawable.picture_7
        8 -> R.drawable.picture_8
        9 -> R.drawable.picture_9
        10 -> R.drawable.picture_10
        11 -> R.drawable.picture_1
        else -> R.drawable.isiterror
    }
    Image(
        modifier = modifier,
        painter = painterResource(id = image),
        contentDescription = image.toString(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ArtSpaceText(
    showText: Int,
    modifier: Modifier = Modifier
) {
    val texts = when (showText) {
        0 -> DataActivity(R.string.snow_mountain, R.string.siqi_2022)
        1 -> DataActivity(R.string.waterwheel, R.string.siqi_2022)
        2 -> DataActivity(R.string.sea_and_waves, R.string.siqi_2021)
        3 -> DataActivity(R.string.forest, R.string.siqi_2022)
        4 -> DataActivity(R.string.evening_glow, R.string.siqi_2022)
        5 -> DataActivity(R.string.starry_sky, R.string.siqi_2021)
        6 -> DataActivity(R.string.trees_and_leaves, R.string.siqi_2020)
        7 -> DataActivity(R.string.rock_and_stone, R.string.siqi_2023)
        8 -> DataActivity(R.string.snow_and_icicle, R.string.siqi_2022)
        9 -> DataActivity(R.string.trees_and_ocean, R.string.siqi_2021)
        10 -> DataActivity(R.string.snow_mountain, R.string.siqi_2022)
        11 -> DataActivity(R.string.waterwheel, R.string.siqi_2022)
        else -> DataActivity(R.string.isiterror, R.string.easter_egg)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(texts.textNumber),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = modifier.height(3.dp))
        Text(
            text = stringResource(texts.textSmall),
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun ChangePictureButton(
    onClick: () -> Unit,
    text: String,
    horizontalAlignment: Alignment.Horizontal,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = horizontalAlignment
    ) {
        Button(
            modifier = modifier.width(130.dp),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
        ) {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}