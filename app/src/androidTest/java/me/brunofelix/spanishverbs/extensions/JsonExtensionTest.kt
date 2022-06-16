package me.brunofelix.spanishverbs.extensions

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import me.brunofelix.spanishverbs.data.json.RootJson

import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class JsonExtensionTest {

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun test_isJsonFileExists_returnsNotEmpty() {
        val json = appContext.getJsonFromAssets("A_verbos.json")

        assertThat(json).isNotEmpty()
    }

    @Test
    fun test_isSerializeJsonInObject_returnsNotNull() {
        val json = appContext.getJsonFromAssets("A_verbos.json")
        val gson = Gson()
        val obj = gson.fromJson(json, Array<RootJson>::class.java).asList()

        assertThat(obj).isNotNull()
    }
}