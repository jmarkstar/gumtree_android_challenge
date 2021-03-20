package com.jmarkstar.gumtree_challenge.common

import okhttp3.mockwebserver.MockResponse
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object UnitTestUtils {

    const val MOCK_SERVER_PORT = 8000

    @Throws(IOException::class)
    fun readFileFromResources(fileName: String): String {
        var inputStream: InputStream? = null
        try {
            inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))

            var theCharNum = reader.read()
            while (theCharNum != -1) {
                builder.append(theCharNum.toChar())
                theCharNum = reader.read()
            }
            return builder.toString()
        } finally {
            inputStream?.close()
        }
    }

    fun mockJsonResponse(
        httpCode: Int,
        jsonFileName: String? = null
    ) = MockResponse()
        .setResponseCode(httpCode)
        .addHeader("Content-Type", "application/json; charset=utf-8")
        .setBody(
            if (jsonFileName != null) {
                readFileFromResources(jsonFileName)
            } else {
                Constants.EMPTY
            }
        )
}
