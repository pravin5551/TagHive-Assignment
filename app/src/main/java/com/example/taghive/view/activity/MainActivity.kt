package com.example.taghive.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.taghive.R
import com.example.taghive.databinding.ActivityMainBinding
import com.example.taghive.domain.util.addFragment
import com.example.taghive.view.base.BaseActivity
import com.example.taghive.view.fragment.AllCryptoFragment
import com.example.taghive.view.fragment.DetailedCryptoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val fragment = AllCryptoFragment()
        addFragment(layout_container.id, fragment)

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(layout_container.id)
        if (fragment is AllCryptoFragment) {
            finish()
        } else {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportFragmentManager.popBackStackImmediate()
            } else {
                finish()
            }
        }
    }
}