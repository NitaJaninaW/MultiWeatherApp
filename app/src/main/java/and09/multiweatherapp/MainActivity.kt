package and09.multiweatherapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import and09.multiweatherapp.databinding.ActivityMainBinding
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val prefsChangedListener = object :
        SharedPreferences.OnSharedPreferenceChangeListener {
        override fun onSharedPreferenceChanged(
            prefs: SharedPreferences?,
            key: String?
        ) {
            println("prefs changed, key: $key")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        prefs.registerOnSharedPreferenceChangeListener(prefsChangedListener)
    }
}