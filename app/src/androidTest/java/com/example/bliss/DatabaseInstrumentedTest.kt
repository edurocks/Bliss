package com.example.bliss

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bliss.database.AppDatabase
import com.example.bliss.database.dao.BlissDao
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.database.entity.UserAvatar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DatabaseInstrumentedTest {

    private lateinit var blissDao : BlissDao
    private lateinit var database: AppDatabase

    @Before
    fun setUp() {
        createDb()
    }

    private fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        blissDao = database.blissDao()
    }

    @Test
    @Throws(Exception::class)
    fun insertAvatarAndVerifyIfExists() {
        val userAvatar = UserAvatar()
        userAvatar.id = 1
        userAvatar.name = "blissapps"
        userAvatar.url = "avatars.githubusercontent.com/u/22355468?v=4"

        runBlocking {
            blissDao.insertAvatar(userAvatar)
            val byName = blissDao.getAvatar("blissapps")
            assertThat(byName, equalTo(userAvatar))
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteAvatarAndVerifyIfWasDeleted() {
        val userAvatar = UserAvatar()
        userAvatar.id = 1
        userAvatar.name = "blissapps"
        userAvatar.url = "avatars.githubusercontent.com/u/22355468?v=4"

        runBlocking {
            blissDao.deleteAvatar(userAvatar)
            val byName = blissDao.getAvatar("blissapps")
            assertThat(byName, equalTo(null))
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertEmojiAndVerifyIfUrlExists() {
        val listEmojis = arrayListOf<EmojiEntity>()
        val emojis = EmojiEntity()
        emojis.url = "github.githubassets.com/images/icons/emoji/unicode/1f44d.png?v8"

        listEmojis.add(emojis)

        runBlocking {
            blissDao.insertEmojis(listEmojis)
            val emojiUrl = blissDao.getEmojis()
            assertThat(emojiUrl[0].url, equalTo("github.githubassets.com/images/icons/emoji/unicode/1f44d.png?v8"))

        }
    }

    @Test
    @Throws(Exception::class)
    fun verifyUserAvatarExists() {
        val listUserAvatar = arrayListOf<UserAvatar>()
        val userAvatar = UserAvatar(1,"edurocks",
            "github.githubassets.com/images/icons/emoji/unicode/1f44d.png?v8" )
        val anotherUserAvatar = UserAvatar(2,"blissapps",
            "avatars.githubusercontent.com/u/223156?v=4" )

        listUserAvatar.add(userAvatar)
        listUserAvatar.add(anotherUserAvatar)

        runBlocking {
            val result = blissDao.getAllAvatars()
            assertThat(result, notNullValue(null))
            assertThat(result.isEmpty(), `is`(true))
        }
    }

    @After
    fun tearDown() {
        closeDb()
    }

    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }
}