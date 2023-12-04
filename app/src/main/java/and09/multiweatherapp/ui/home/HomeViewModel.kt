package and09.multiweatherapp.ui.home

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//OBSERVER Design Pattern
class HomeViewModel : ViewModel() {

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
}
