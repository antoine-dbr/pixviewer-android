package dbr.antoine.pixviewer

import com.google.common.io.CharStreams
import org.json.JSONObject
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by antoine on 7/7/17.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
open class FrameworkTestBase {

    @Throws(Exception::class)
    fun loadJsonObject(testClass: Class<*>, filename: String): JSONObject {
        val content = fromFile(testClass, filename)
        return JSONObject(content)
    }

    @Throws(IOException::class)
    private fun fromFile(testClass: Class<*>, fileName: String): String {
        val stream = testClass.classLoader.getResourceAsStream(fileName)
        return CharStreams.toString(InputStreamReader(stream, "UTF-8"))
    }
}