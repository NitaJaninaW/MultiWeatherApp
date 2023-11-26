package and09.multiweatherapp.weatherapi

import and09.multiweatherapp.HttpRequest
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URLEncoder

class WeatherStackAPI private constructor(queryString: String) : WeatherAPI {
    private val weatherdata: JSONObject

    companion object {
        private const val API_KEY = "21988b4eebbef9de814d571ce1c34af4"
        private const val BASE_URL =
            "http://api.weatherstack.com/current?access_key=$API_KEY&"

        @Throws(IOException::class, JSONException::class)
        fun fromLocationName(locationName: String?):
                WeatherAPI {
            return WeatherStackAPI(
                "query=" +
                        URLEncoder.encode(locationName, "UTF-8")
            )
        }

        @Throws(IOException::class, JSONException::class)
        fun fromLatLon(lat: Double, lon: Double): WeatherAPI {
            return WeatherStackAPI("query=$lat,$lon")
        }
    }

    init {
        val result = HttpRequest.request(BASE_URL + queryString)
        weatherdata = JSONObject(result)
        println(weatherdata.toString()) // Debug!
    }

    @get:Throws(JSONException::class)
    override val temperature: Int
        get() = TODO("Not yet implemented")

    @get:Throws(JSONException::class)
    override val description: String
        get() = TODO("Not yet implemented")

    @get:Throws(JSONException::class)
    override val iconUrl: String
        get() = TODO("Not yet implemented")

    @get:Throws(JSONException::class)
    override val location: String
        get() = TODO("Not yet implemented")

    override val providerUrl: String
        get() = TODO("Not yet implemented")
}