package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import  androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

//@Composable
//fun ArtSpaceApp() {
//    var currentIndex by remember { mutableStateOf(0) }
//
//    val artworks = listOf(
//        Artwork(R.drawable.art1, R.string.title1, R.string.artist1),
//        Artwork(R.drawable.art2, R.string.title2, R.string.artist2),
//        Artwork(R.drawable.art3, R.string.title3, R.string.artist3)
//    )
//
//    val currentArt = artworks[currentIndex]
//
//    BoxWithConstraints(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        if (maxWidth < 600.dp) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp)
//            ) {
//                ArtDisplay(currentArt)
//                Spacer(modifier = Modifier.height(24.dp))
//                ArtControls(
//                    onPrevious = {
//                        currentIndex = (currentIndex - 1 + artworks.size) % artworks.size
//                    },
//                    onNext = {
//                        currentIndex = (currentIndex + 1) % artworks.size
//                    }
//                )
//            }
//        } else {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceEvenly,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(24.dp)
//            ) {
//                ArtDisplay(currentArt, Modifier.weight(1f))
//                Spacer(modifier = Modifier.width(24.dp))
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier.weight(1f)
//                ) {
//                    Text(
//                        text = stringResource(currentArt.title),
//                        style = MaterialTheme.typography.headlineMedium
//                    )
//                    Text(
//                        text = stringResource(currentArt.artist),
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    ArtControls(
//                        onPrevious = {
//                            currentIndex = (currentIndex - 1 + artworks.size) % artworks.size
//                        },
//                        onNext = {
//                            currentIndex = (currentIndex + 1) % artworks.size
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ArtDisplay(art: Artwork, modifier: Modifier = Modifier) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//    ) {
//        Image(
//            painter = painterResource(art.image),
//            contentDescription = stringResource(art.title),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//                .padding(8.dp)
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
//            text = stringResource(art.title),
//            style = MaterialTheme.typography.titleMedium
//        )
//        Text(
//            text = stringResource(art.artist),
//            style = MaterialTheme.typography.bodyMedium
//        )
//    }
//}
//
//@Composable
//fun ArtControls(onPrevious: () -> Unit, onNext: () -> Unit) {
//    Row(
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Button(onClick = onPrevious) {
//            Text("Previous")
//        }
//        Button(onClick = onNext) {
//            Text("Next")
//        }
//    }
//}

data class Artwork(
    val image: Int,
    val title: Int,
    val artist: Int
)

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun PhonePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}

@Preview(showBackground = true, widthDp = 1000, heightDp = 600)
@Composable
fun TabletPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}

@Composable
fun ArtSpaceApp() {
    var currentIndex by remember { mutableStateOf(0) }

    val artworks = listOf(
        Artwork(R.drawable.art1, R.string.title1, R.string.artist1),
        Artwork(R.drawable.art2, R.string.title2, R.string.artist2),
        Artwork(R.drawable.art3, R.string.title3, R.string.artist3)
    )

    val currentArt = artworks[currentIndex]

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (image, title, artist, buttons) = createRefs()

        Image(
            painter = painterResource(currentArt.image),
            contentDescription = stringResource(currentArt.title),
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(300.dp)
                }
        )

        Text(
            text = stringResource(currentArt.title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(image.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )


        Text(
            text = stringResource(currentArt.artist),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(artist) {
                top.linkTo(title.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(buttons) {
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Button(onClick = {
                currentIndex = (currentIndex - 1 + artworks.size) % artworks.size
            }) {
                Text("Previous")
            }
            Button(onClick = {
                currentIndex = (currentIndex + 1) % artworks.size
            }) {
                Text("Next")
            }
        }
    }
}
