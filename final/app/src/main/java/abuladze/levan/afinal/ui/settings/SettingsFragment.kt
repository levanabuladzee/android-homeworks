package abuladze.levan.afinal.ui.settings

import abuladze.levan.afinal.R
import abuladze.levan.afinal.ui.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mActivity = activity as MainActivity
        val darkModeSwitch = view.findViewById<SwitchMaterial>(R.id.settingsDarkModeSwitch)

        val editor = mActivity.sharedPreferences.edit()

        darkModeSwitch.isChecked = mActivity.sharedPreferences.getBoolean("DARK_MODE", false)

        mActivity.model.dataSynced.observe(viewLifecycleOwner, {
            darkModeSwitch.isClickable = it
        })

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                editor?.putBoolean("DARK_MODE", true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                editor?.putBoolean("DARK_MODE", false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            editor?.apply()
        }
    }
}