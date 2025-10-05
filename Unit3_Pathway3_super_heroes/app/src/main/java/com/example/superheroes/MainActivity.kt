package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SuperheroesApp()
                }
            }
        }
    }
}

enum class Team { A, B }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesApp() {
    val heroes = HeroesRepository.heroes


    val defaultAssignments = ArrayList<Int>().apply { val rnd = kotlin.random.Random.Default
        for (i in heroes.indices) {
            add(if (rnd.nextBoolean()) 1 else 0) // 1 = B, 0 = A
        }
    }


    var assignmentsState by rememberSaveable { mutableStateOf(defaultAssignments) }


    var grouped by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.displayLarge
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        floatingActionButton = {
            if (!grouped) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Default.Shuffle, contentDescription = "Group") },
                    text = { Text("Group") },
                    onClick = { grouped = true }
                )
            } else {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Default.Close, contentDescription = "Ungroup") },
                    text = { Text("Ungroup") },
                    onClick = { grouped = false }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        if (!grouped) {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
            ) {
                itemsIndexed(heroes) { index, hero ->
                    HeroCard(
                        hero = hero,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = dimensionResource(id = R.dimen.padding_small)
                            ),
                        team = if (assignmentsState.getOrNull(index) == 1) Team.B else Team.A,
                        onCardClick = {
                            // toggle team for this hero
                            val newList = ArrayList(assignmentsState)
                            val cur = newList[index]
                            newList[index] = if (cur == 0) 1 else 0
                            assignmentsState = newList
                        }
                    )
                }
            }
        } else {

            val groupedA = heroes.withIndex().filter { assignmentsState.getOrNull(it.index) == 0 }
            val groupedB = heroes.withIndex().filter { assignmentsState.getOrNull(it.index) == 1 }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
                verticalAlignment = Alignment.Top
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    TeamHeader(title = "Team A", color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                    LazyColumn {
                        itemsIndexed(groupedA) { _, entry ->
                            val index = entry.index
                            HeroCard(
                                hero = entry.value,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = dimensionResource(id = R.dimen.padding_small)),
                                team = Team.A,
                                onCardClick = {
                                    val newList = ArrayList(assignmentsState)
                                    val cur = newList[index]
                                    newList[index] = if (cur == 0) 1 else 0
                                    assignmentsState = newList
                                }
                            )
                        }
                    }
                }


                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                )


                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    TeamHeader(title = "Team B", color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                    LazyColumn {
                        itemsIndexed(groupedB) { _, entry ->
                            val index = entry.index
                            HeroCard(
                                hero = entry.value,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = dimensionResource(id = R.dimen.padding_small)),
                                team = Team.B,
                                onCardClick = {
                                    val newList = ArrayList(assignmentsState)
                                    val cur = newList[index]
                                    newList[index] = if (cur == 0) 1 else 0
                                    assignmentsState = newList
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TeamHeader(title: String, color: androidx.compose.ui.graphics.Color) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = color.copy(alpha = 0.10f),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner))
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = color
        )
    }
}

@Composable
fun HeroCard(
    hero: Hero,
    modifier: Modifier = Modifier,
    team: Team = Team.A,
    onCardClick: () -> Unit = {}
) {
    val cardColor = MaterialTheme.colorScheme.surfaceVariant

    Card(
        modifier = modifier.clickable { onCardClick() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.card_elevation)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = dimensionResource(id = R.dimen.padding_medium))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = hero.nameRes),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TeamChip(team = team)
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                )
            }

            ImageRight(imageRes = hero.imageRes)
        }
    }
}

@Composable
private fun TeamChip(team: Team) {
    val background = when (team) {
        Team.A -> MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
        Team.B -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f)
    }
    val textColor = when (team) {
        Team.A -> MaterialTheme.colorScheme.primary
        Team.B -> MaterialTheme.colorScheme.secondary
    }
    Surface(
        modifier = Modifier
            .height(28.dp)
            .padding(start = 4.dp),
        shape = RoundedCornerShape(16.dp),
        color = background
    ) {
        Text(
            text = if (team == Team.A) "A" else "B",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            color = textColor
        )
    }
}

@Composable
private fun ImageRight(@DrawableRes imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.image_size))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.image_corner)))
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSplitTeams() {
    SuperheroesTheme {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            // sample preview: show first two heroes split
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    TeamHeader(title = "Team A", color = MaterialTheme.colorScheme.primary)
                    HeroCard(
                        hero = Hero(
                            nameRes = R.string.hero1,
                            descriptionRes = R.string.description1,
                            imageRes = R.drawable.android_superhero1
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        team = Team.A
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    TeamHeader(title = "Team B", color = MaterialTheme.colorScheme.secondary)
                    HeroCard(
                        hero = Hero(
                            nameRes = R.string.hero2,
                            descriptionRes = R.string.description2,
                            imageRes = R.drawable.android_superhero2
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        team = Team.B
                    )
                }
            }
        }
    }
}
