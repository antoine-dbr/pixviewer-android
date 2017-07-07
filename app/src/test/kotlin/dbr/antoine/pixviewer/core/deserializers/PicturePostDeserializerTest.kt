package dbr.antoine.pixviewer.core.deserializers

import dbr.antoine.pixviewer.FrameworkTestBase
import org.amshove.kluent.shouldBeGreaterThan
import org.json.JSONArray
import org.json.JSONException
import org.junit.Before
import org.junit.Test

/**
 * Created by antoine on 7/7/17.
 */

class PicturePostDeserializerTest : FrameworkTestBase() {

    var response: JSONArray = JSONArray()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val json = loadJsonObject(javaClass, "pixabay/results.json")
        response = json.getJSONArray("hits")
    }

    @Test
    fun testParsingSuccess() {
        val pictures = PicturePostDeserializer.from(response)

        pictures.size.shouldBeGreaterThan(1)
    }

    @Test(expected = JSONException::class)
    @Throws(JSONException::class)
    fun testParsingError() {
        response.getJSONObject(0).remove("id")

        PicturePostDeserializer.from(response)
    }
}