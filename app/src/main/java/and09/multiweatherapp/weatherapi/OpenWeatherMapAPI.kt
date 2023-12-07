package and09.multiweatherapp.weatherapi

import and09.multiweatherapp.HttpRequest
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URLEncoder

class OpenWeatherMapAPI private constructor(queryString: String) : WeatherAPI {
    private val weatherdata: JSONObject

    companion object {
        private const val API_KEY = "bc61e79ee95470488ae349db149ac65c"
        private const val BASE_URL =
            "https://api.openweathermap.org/data/2.5/weather?lang=de&APPID=$API_KEY&"

        @FromLocationName
        @Throws(IOException::class, JSONException::class)
        fun fromLocationName(locationName: String?):
                WeatherAPI {
            return OpenWeatherMapAPI(
                "q=" +
                        URLEncoder.encode(locationName, "UTF-8")
            )
        }

        @Throws(IOException::class, JSONException::class)
        fun fromLatLon(lat: Double, lon: Double): WeatherAPI {
            return OpenWeatherMapAPI("lat=$lat&lon=$lon")
        }
    }

    init {
        val result = HttpRequest.request(BASE_URL + queryString)
        weatherdata = JSONObject(result)
        println(weatherdata.toString()) // Debug!
    }

    @get:Throws(JSONException::class)
    override val temperature: Int
        get() = (weatherdata.getJSONObject("main")
            .getDouble("temp") - 273.15).toInt()

    @get:Throws(JSONException::class)
    override val description: String
        get() = weatherdata.getJSONArray("weather")
            .getJSONObject(0)
            .getString("description")

    @get:Throws(JSONException::class)
    override val iconUrl: String
        get() = "https://openweathermap.org/img/w/${
            weatherdata
                .getJSONArray("weather").getJSONObject(0)
                .getString("icon")
        }.png"

    @get:Throws(JSONException::class)
    override val location: String
        get() = weatherdata.getString("name")

    override val providerUrl: String
        get() = "https://www.openweathermap.org"

}