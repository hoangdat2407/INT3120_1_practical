package com.example.lemonade

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.saveable.rememberSaveable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {

    var currentStep by rememberSaveable { mutableStateOf(1) }
    var squeezeCount by rememberSaveable { mutableStateOf(0) }
    var isMelted by rememberSaveable { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    DisposableEffect(orientation) {
        if (currentStep == 3 && orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isMelted = true
        }
        onDispose { }
    }

    if (currentStep == 1 && isMelted) {
        isMelted = false
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lemonade", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant),
            color = MaterialTheme.colorScheme.background
        ) {
            val uiState = when {
                currentStep == 1 -> Triple(
                    R.string.tap_lemon_tree,
                    R.drawable.lemon_tree,
                    R.string.lemon_tree_content
                ) to {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }

                currentStep == 2 -> Triple(
                    R.string.tap_lemon,
                    R.drawable.lemon_squeeze,
                    R.string.lemon_content
                ) to {
                    squeezeCount--
                    if (squeezeCount <= 0) currentStep = 3
                }

                currentStep == 3 && isMelted -> Triple(
                    R.string.ice_melted,
                    R.drawable.lemon_melted,
                    R.string.ice_melted_content
                ) to { currentStep = 4 }

                currentStep == 3 -> Triple(
                    R.string.tap_lemonade,
                    R.drawable.lemon_drink,
                    R.string.lemon_content
                ) to { currentStep = 4 }

                else -> Triple(
                    R.string.tap_glass,
                    R.drawable.lemon_restart,
                    R.string.glass_content
                ) to { currentStep = 1 }
            }

            LemonTextAndImage(
                textLabelResourceId = uiState.first.first,
                drawableResourceId = uiState.first.second,
                contentDescriptionResourceId = uiState.first.third,
                onImageClick = uiState.second
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(contentDescriptionResourceId),
                modifier = Modifier
                    .size(
                        width = dimensionResource(R.dimen.button_image_width),
                        height = dimensionResource(R.dimen.button_image_height)
                    )
                    .padding(dimensionResource(R.dimen.button_interior_padding))
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
        Text(
            text = stringResource(textLabelResourceId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}
