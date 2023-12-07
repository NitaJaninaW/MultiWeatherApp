package and09.multiweatherapp.ui.home

import and09.multiweatherapp.R
import and09.multiweatherapp.weatherapi.FromLocationName
import and09.multiweatherapp.weatherapi.OpenWeatherMapAPI
import and09.multiweatherapp.weatherapi.SpringWeatherAPI
import and09.multiweatherapp.weatherapi.WeatherAPI
import and09.multiweatherapp.weatherapi.WeatherStackAPI
import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.hasAnnotation


//OBSERVER Design Pattern
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _location: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val _temperature: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val _description: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val _provider: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val _iconBitmap: MutableLiveData<Bitmap> by lazy {
        MutableLiveData<Bitmap>()
    }
    val location: LiveData<String> = _location
    val temperature: LiveData<String> = _temperature
    val description: LiveData<String> = _description
    val provider: LiveData<String> = _provider
    val iconBitmap: LiveData<Bitmap> = _iconBitmap

    fun retrieveWeatherData() {
        CoroutineScope(Dispatchers.Main).launch() {
            var weather: WeatherAPI? = null
            var bitmap: Bitmap? = null
            withContext(Dispatchers.IO) {
                val app = getApplication() as Application
                val prefs = PreferenceManager.getDefaultSharedPreferences(app)
                val locationName =
                    prefs.getString(
                        app.getString(R.string.location_name),
                        "Darmstadt"
                    )
                val providerClassName = prefs.getString(
                    app.getString(R.string.weather_provider), "OpenWeatherMapAPI"
                )
                try {
                    /*weather = when (providerClassName) {
                        "OpenWeatherMapAPI" ->
                            OpenWeatherMapAPI.fromLocationName(locationName)

                        "WeatherStackAPI" ->
                            WeatherStackAPI.fromLocationName(locationName)

                        else -> SpringWeatherAPI.fromLocationName(locationName)
                    }*/
                    val cls =
                        Class.forName(
                            "${WeatherAPI::class.java.`package`.name}.$providerClassName"
                        ).kotlin
                    val func =
                        cls.companionObject?.declaredFunctions?.find { it.hasAnnotation<FromLocationName>() }
                    weather = func?.call(cls.companionObjectInstance, locationName) as WeatherAPI

                    Log.d(javaClass.simpleName, "Temp: ${weather?.temperature}")
                    val iconUrl = URL(weather?.iconUrl) // java.net!
                    val inputStream = iconUrl.openStream()
                    bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream.close()
                } catch (ex: Exception) {
                    Log.e(javaClass.simpleName, ex.toString())
                }
            }
            updateValues(weather, bitmap)
        }
    }

    fun updateValues(weather: WeatherAPI?, bitmap: Bitmap?) {
        _location.value = weather?.location
        _temperature.value = "${weather?.temperature} °C"
        _description.value = weather?.description
        _provider.value = weather?.providerUrl
        _iconBitmap.value = bitmap
    }
}
