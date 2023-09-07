package com.example.jetpackcomposetutorial

import com.example.jetpackcomposetutorial.common.data.model.UserData
import com.example.jetpackcomposetutorial.common.data.repository.UserRepository
import com.example.jetpackcomposetutorial.common.presentation.UserViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    val mockRepo = mockk<UserRepository>()
    val viewmodel = UserViewModel(mockRepo)
    val fakeData = UserData(1,
        "first",
        "last",
        "123@gmail.com",
        listOf(),
        listOf()
    )

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun testFetchUserData() {
        Assert.assertEquals(0, viewmodel._userListLivedata.value.size)

        every { mockRepo.fetchUserData() } answers {
            flow {
                emit(listOf(fakeData))
            }
        }
        viewmodel.fetchUserData()
        Assert.assertEquals(1, viewmodel._userListLivedata.value.size)

        every { mockRepo.fetchUserData() } answers {
            flow {
                emit(listOf(fakeData, fakeData))
            }
        }
        viewmodel.fetchUserData()
        Assert.assertEquals(2, viewmodel._userListLivedata.value.size)
    }

    @Test
    fun testGetUserDataById() {
        Assert.assertNull(viewmodel.userLiveData.value)

        viewmodel.getUserDataById("1")
        Assert.assertNull(viewmodel.userLiveData.value)

        every { mockRepo.fetchUserData() } answers {
            flow {
                emit(listOf(fakeData))
            }
        }
        viewmodel.fetchUserData()
        viewmodel.getUserDataById("1")
        Assert.assertNotNull(viewmodel.userLiveData.value)
    }
}