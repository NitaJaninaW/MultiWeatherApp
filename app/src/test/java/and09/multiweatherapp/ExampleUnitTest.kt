package and09.multiweatherapp

import and09.multiweatherapp.weatherapi.OpenWeatherMapAPI
import and09.multiweatherapp.weatherapi.SpringWeatherAPI
import and09.multiweatherapp.weatherapi.WeatherStackAPI
import org.json.JSONException
import org.junit.Test
import java.io.IOException

class ExampleUnitTest {
    @Test
    @Throws(IOException::class, JSONException::class)
    fun openWeatherMap_getResponseFromName() {
        val api = OpenWeatherMapAPI.fromLocationName("Kirchroth")
        println("Temp: ${api.temperature}")
        println("Description: ${api.description}")
        println("Icon: ${api.iconUrl}")
        println("Location: ${api.location}")
        print("Provider:${api.providerUrl}")
    }

    @Test
    @Throws(IOException::class, JSONException::class)
    fun openWeatherMap_getResponseFromLatLon() {
        val api = OpenWeatherMapAPI.fromLatLon(37.77, -122.42)
        println("Temp: ${api.temperature}")
        println("Description: ${api.description}")
        println("Icon: ${api.iconUrl}")
        println("Location: ${api.location}")
        print("Provider:${api.providerUrl}")
    }

    @Test
    @Throws(IOException::class, JSONException::class)
    fun weatherStack_getResponseFromName() {
        val api = WeatherStackAPI.fromLocationName("San Francisco")
        println("Temp: ${api.temperature}")
        println("Description: ${api.description}")
        println("Icon: ${api.iconUrl}")
        println("Location: ${api.location}")
        print("Provider:${api.providerUrl}")
    }

    @Test
    @Throws(IOException::class, JSONException::class)
    fun weatherStack_getResponseFromLatLon() {
        val api = WeatherStackAPI.fromLatLon(25.252, 55.280)
        println("Temp: ${api.temperature}")
        println("Description: ${api.description}")
        println("Icon: ${api.iconUrl}")
        println("Location: ${api.location}")
        print("Provider:${api.providerUrl}")
    }

    @Test
    @Throws(IOException::class, JSONException::class)
    fun springWeatherAPI_getResponseFromName() {
        val api = SpringWeatherAPI.fromLocationName("San Francisco")
        println("Temp: ${api.temperature}")
        println("Description: ${api.description}")
        println("Icon: ${api.iconUrl}")
        println("Location: ${api.location}")
    }

    @Test
    @Throws(IOException::class, JSONException::class)
    fun springWeatherAPI_getResponseFromLatLon() {
        val api = SpringWeatherAPI.fromLatLon(37.77, -122.42)
        println("Temp: ${api.temperature}")
        println("Description: ${api.description}")
        println("Icon: ${api.iconUrl}")
        println("Location: ${api.location}")
    }
}