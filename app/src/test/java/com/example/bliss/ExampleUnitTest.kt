package com.example.bliss

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.database.entity.UserAvatar
import com.example.bliss.repository.BlissRepository
import com.example.bliss.ui.viewModel.BlissViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class ExampleUnitTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private var blissRepository: BlissRepository = mockk()

    private var emojiEntityObserver: Observer<List<EmojiEntity>> = mockk(relaxed = true)

    private var userAvatarObserver: Observer<UserAvatar> = mockk(relaxed = true)

    private var allAvatarsObserver: Observer<List<UserAvatar>> = mockk(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun givenSuccessWhenGetEmojisFromDb() {
        val viewModel = BlissViewModel(blissRepository)
        viewModel.emojisList.observeForever(emojiEntityObserver)

        val listEmojiEntity = arrayListOf<EmojiEntity>()
        listEmojiEntity.add(EmojiEntity("edurocks", "avatars.githubusercontent.com/u/22355468?v=4"))
        listEmojiEntity.add(EmojiEntity("blissapps", "avatars.githubusercontent.com/u/223156?v=4"))

        coEvery { blissRepository.getEmojisFromDb() } returns listEmojiEntity
        viewModel.getEmojiFromDb()
        coVerify { blissRepository.getEmojisFromDb() }
        coVerify { emojiEntityObserver.onChanged(listEmojiEntity) }
    }

    @Test
    fun givenSuccessWhenGetAvatarFromDb() {
        val viewModel = BlissViewModel(blissRepository)
        viewModel.userAvatar.observeForever(userAvatarObserver)

        val userAvatar = UserAvatar(0, "", "blissapps")

        coEvery { blissRepository.getAvatarFromDb("blissapps") } returns userAvatar
        viewModel.getAvatar("blissapps")
        coVerify { blissRepository.getAvatarFromDb("blissapps") }
        coVerify { userAvatarObserver.onChanged(userAvatar) }
    }

    @Test
    fun givenSuccessWhenGetAllAvatarsFromDb() {
        val viewModel = BlissViewModel(blissRepository)
        viewModel.allAvatar.observeForever(allAvatarsObserver)

        val listUserAvatar = arrayListOf<UserAvatar>()
        listUserAvatar.add(UserAvatar(0, "avatars.githubusercontent.com/u/22355468?v=4", "edurocks"))
        listUserAvatar.add(UserAvatar(1, "avatars.githubusercontent.com/u/23483482?v=8","blissapps"))

        coEvery { blissRepository.getAllAvatars() } returns listUserAvatar
        viewModel.getAllAvatarsFromDb()
        coVerify { blissRepository.getAllAvatars() }
        coVerify { allAvatarsObserver.onChanged(listUserAvatar) }
    }

    @Test
    fun givenSuccessWhenDeleteAvatar() {
        val viewModel = BlissViewModel(blissRepository)

        val userAvatar = UserAvatar(0, "", "blissapps")

        coEvery { blissRepository.deleteAvatar(userAvatar) } returns Unit
        viewModel.deleteAvatar(userAvatar)
        coVerify { blissRepository.deleteAvatar(userAvatar) }
    }
}