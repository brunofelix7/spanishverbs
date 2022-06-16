package me.brunofelix.spanishverbs.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import me.brunofelix.spanishverbs.data.json.ConjugationJson
import me.brunofelix.spanishverbs.data.json.RootJson
import me.brunofelix.spanishverbs.extensions.getJsonFromAssets
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class VerbDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: VerbDao
    private lateinit var context: Context
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext

        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.verbDao()

        gson = Gson()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertTest() = runBlockingTest {
        var verb: Verb? = null
        val json = context.getJsonFromAssets("_test.json")

        if (json.isNotEmpty()) {
            val verbsList = gson.fromJson(json, Array<RootJson>::class.java).asList()

            for (item in verbsList) {
                val name = item.verbo
                val gerund = item.progresivo.presente.yo.replace("estoy ", "")
                val conjugation = ConjugationJson(
                    item.perfecto, item.imperativo, item.indicativo,
                    item.progresivo, item.subjuntivo, item.perfectoSubjuntivo)

                verb = Verb(0, name, gerund, gson.toJson(conjugation), true)
            }
        }

        dao.insert(verb!!)

        val verbInserted = dao.findById(1)

        assertThat(verbInserted?.id).isEqualTo(1)
    }
}