package com.example.jetpackcomposetutorial

import android.content.Context
import android.content.res.AssetManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.jetpackcomposetutorial.common.data.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UserRepositoryTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    val context = InstrumentationRegistry.getInstrumentation().context
    val userRepository = UserRepository(context)

    @Test
    fun testFetchUserData() = runTest {
        Assert.assertTrue(userRepository.fetchUserData().first().isNotEmpty())
    }

    @Test
    fun testFetchUserDataFlow() = runTest {
        Assert.assertTrue(userRepository.fetchUserDataFlow().first().isNotEmpty())
    }
}