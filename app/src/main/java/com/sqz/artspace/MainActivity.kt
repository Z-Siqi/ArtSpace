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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sqz.artspace.ui.theme.ArtSpaceTheme

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
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    var pictureNumber by remember { mutableStateOf(1) }
    val showPicture = pictureNumber
    val showText = pictureNumber

    if (pictureNumber == 0) {
        pictureNumber = 10
    } else if (pictureNumber == 11) {
        pictureNumber = 1
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
                if ((maxHeight > 800.dp) || (maxWidth > 500.dp)) {
                    ArtSpaceImage(
                        modifier = modifier
                            .height(450.dp)
                            .width(320.dp),
                        showPicture
                    )
                }else{
                    ArtSpaceImage(
                        modifier = modifier
                            .height(380.dp)
                            .width(260.dp),
                        showPicture
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(50.dp))
        if ((pictureNumber <= 11) && (pictureNumber >=0)) {
            Box(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .width(240.dp)
                    .height(80.dp)
                    .padding(start = 10.dp, top = 5.dp),
                contentAlignment = Alignment.TopCenter
            ) { ArtSpaceText(showText) }
        }else{
            Box(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.errorContainer)
                    .width(300.dp)
                    .height(120.dp)
                    .padding(start = 10.dp, top = 5.dp),
                contentAlignment = Alignment.TopCenter
            ) { ArtSpaceText(showText) }
        }
    }
    ChangePictureButton(
        onClick = { pictureNumber-- },
        Text = stringResource(R.string.previous),
        horizontalAlignment = Alignment.Start
    )
    ChangePictureButton(
        onClick = { pictureNumber++ },
        Text = stringResource(R.string.next),
        horizontalAlignment = Alignment.End
    )
}

@Composable
private fun ArtSpaceImage(modifier: Modifier = Modifier, showPicture: Int) {
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
    val textNumber = when (showText) {
        0 -> stringResource(R.string.snow_mountain)
        1 -> stringResource(R.string.waterwheel)
        2 -> stringResource(R.string.sea_and_waves)
        3 -> stringResource(R.string.forest)
        4 -> stringResource(R.string.evening_glow)
        5 -> stringResource(R.string.starry_sky)
        6 -> stringResource(R.string.trees_and_leaves)
        7 -> stringResource(R.string.rock_and_stone)
        8 -> stringResource(R.string.snow_and_icicle)
        9 -> stringResource(R.string.trees_and_ocean)
        10 -> stringResource(R.string.snow_mountain)
        11 -> stringResource(R.string.waterwheel)
        else -> stringResource(R.string.isiterror)
    }
    val textSmall = when (showText) {
        0 -> stringResource(R.string.siqi_2022)
        1 -> stringResource(R.string.siqi_2022)
        2 -> stringResource(R.string.siqi_2021)
        3 -> stringResource(R.string.siqi_2022)
        4 -> stringResource(R.string.siqi_2022)
        5 -> stringResource(R.string.siqi_2021)
        6 -> stringResource(R.string.siqi_2020)
        7 -> stringResource(R.string.siqi_2023)
        8 -> stringResource(R.string.siqi_2022)
        9 -> stringResource(R.string.siqi_2021)
        10 -> stringResource(R.string.siqi_2022)
        11 -> stringResource(R.string.siqi_2022)
        else -> stringResource(R.string.easter_egg)
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
            ) {
        Text(
            text = textNumber,
            fontSize = 20.sp
        )
        Spacer(modifier = modifier.height(3.dp))
        Text(
            text = textSmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ChangePictureButton(
    onClick: () -> Unit,
    Text: String,
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
            Text(text = Text)
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