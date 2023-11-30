package and09.multiweatherapp.weatherapi

import and09.multiweatherapp.HttpRequest
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URLEncoder
//FACTORY Design Pattern
class SpringWeatherAPI private constructor(queryString: String) : WeatherAPI {
    private val weatherdata: JSONObject

    companion object {
        private const val BASE_URL = "http://192.168.1.105:8080/weather?"

        @Throws(IOException::class, JSONException::class)
        fun fromLocationName(locationName: String?):
                WeatherAPI = SpringWeatherAPI(
            "location=" +
                    URLEncoder.encode(locationName, "UTF-8")
        )


        @Throws(IOException::class, JSONException::class)
        fun fromLatLon(lat: Double, lon: Double):
                WeatherAPI = SpringWeatherAPI("query=$lat,$lon")

    }

    init {
        val result = HttpRequest.request(BASE_URL + queryString)
        weatherdata = JSONObject(result)
        println(weatherdata.toString()) // Debug!
    }

    @get:Throws(JSONException::class)
    override val temperature: Int
        get() = weatherdata.getInt("temperature")

    @get:Throws(JSONException::class)
    override val description: String
        get() = weatherdata.getString("description")

    @get:Throws(JSONException::class)
    override val iconUrl: String
        get() = weatherdata.getString("iconUrl")

    @get:Throws(JSONException::class)
    override val location: String
        get() = weatherdata.getString("locationName")

    @get:Throws(JSONException::class)
    override val providerUrl: String
        get() = weatherdata.getString("providerUrl")

}