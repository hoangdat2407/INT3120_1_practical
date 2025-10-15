package com.example.cupcake.ui

import androidx.lifecycle.ViewModel
import com.example.cupcake.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_PER_FLAVOR = 0.50
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun setQuantity(numberCupcakes: Int) {
        _uiState.update { current ->
            current.copy(
                quantity = numberCupcakes,
                price = calculatePrice(quantity = numberCupcakes)
            )
        }
    }

    fun setFlavor(selectedFlavors: List<String>) {
        _uiState.update { current ->
            current.copy(
                flavor = selectedFlavors.joinToString(", "),
                price = calculatePrice(flavors = selectedFlavors)
            )
        }
    }

    fun setDate(pickupDate: String) {
        _uiState.update { current ->
            current.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    fun resetOrder() {
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }

    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date,
        flavors: List<String> = _uiState.value.flavor.split(", ").filter { it.isNotEmpty() }
    ): String {
        var total = quantity * PRICE_PER_CUPCAKE
        total += flavors.size * PRICE_PER_FLAVOR
        val options = pickupOptions()
        val index = options.indexOf(pickupDate)
        if (index == 0) total += PRICE_FOR_SAME_DAY_PICKUP
        else if (index > 0) total += index * 0.5
        return NumberFormat.getCurrencyInstance().format(total)
    }

    private fun pickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }
}
