package and09.multiweatherapp.ui.dashboard

import and09.multiweatherapp.R
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class DashboardFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}