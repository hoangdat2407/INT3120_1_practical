package com.example.flightsearch.ui.screens.flight_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.MockData
import com.example.flightsearch.ui.screens.search.AirportRow
import com.example.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun FlightRow(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    departureAirportCode: String,
    departureAirportName: String,
    destinationAirportCode: String,
    destinationAirportName: String,
    onFavoriteClick: (String, String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Departure",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 16.dp, bottom = 2.dp)
                )
                AirportRow(code = departureAirportCode, name = departureAirportName)

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Arrival",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 16.dp, bottom = 2.dp)
                )
                AirportRow(code = destinationAirportCode, name = destinationAirportName)
            }

            IconButton(
                onClick = { onFavoriteClick(departureAirportCode, destinationAirportCode) },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.outline,
                    contentDescription = if (isFavorite) "Unfavorite" else "Favorite"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlightRowPreview() {
    val mock = MockData
    FlightSearchTheme {
        FlightRow(
            isFavorite = true,
            departureAirportCode = mock.airports[1].code,
            departureAirportName = mock.airports[1].name,
            destinationAirportCode = mock.airports[2].code,
            destinationAirportName = mock.airports[2].name,
            onFavoriteClick = { _, _ -> }
        )
    }
}
