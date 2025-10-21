package com.example.marsphotos.rules

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.fake.fake.FakeDataSource
import com.example.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {

    // This rule swaps the Main dispatcher with a Test dispatcher
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest {
            // Initialize the ViewModel with the fake repository
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )

            // The assertion now correctly checks against the expected Success state
            // containing the list of photos from the fake data source.
            // Since the getMarsPhotos() is called in the ViewModel's init block,
            // the state will be updated from Loading to Success. The runTest
            // coroutine builder ensures the code inside it completes before the
            // test finishes.
            assertEquals(
                MarsUiState.Success(FakeDataSource.photosList.size.toString()),
                marsViewModel.marsUiState
            )
        }
}
