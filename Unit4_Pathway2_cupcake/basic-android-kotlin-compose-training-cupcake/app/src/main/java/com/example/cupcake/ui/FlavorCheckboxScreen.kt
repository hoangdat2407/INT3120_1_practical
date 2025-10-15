package com.example.cupcake.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
@Composable
fun FlavorCheckboxScreen(
    options: List<String>,
    subtotal: String,
    onFlavorsSelected: (List<String>) -> Unit,
    onCancel: () -> Unit
) {
    var selectedFlavors by rememberSaveable { mutableStateOf(setOf<String>()) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            options.forEach { flavor ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = flavor in selectedFlavors,
                        onCheckedChange = { checked ->
                            selectedFlavors = if (checked)
                                selectedFlavors + flavor
                            else
                                selectedFlavors - flavor
                        }
                    )
                    Text(flavor)
                }
            }
            Spacer(Modifier.height(16.dp))
            Text("Tạm tính: $subtotal", style = MaterialTheme.typography.bodyLarge)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = onCancel) { Text("Hủy") }
            Button(
                onClick = { onFlavorsSelected(selectedFlavors.toList()) },
                enabled = selectedFlavors.isNotEmpty()
            ) { Text("Tiếp tục") }
        }
    }
}
