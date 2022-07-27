package com.example.taghive.view.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.taghive.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.kaopiz.kprogresshud.KProgressHUD

open class BaseActivity : AppCompatActivity() {

    private val viewModel: BaseViewModel by viewModel()
    lateinit var progressDialog: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )
             super.onCreate(savedInstanceState)

        progressDialog = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(getString(R.string.loading))
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)


        viewModel.getloading().observe(this, Observer {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::progressDialog.isInitialized) {
            progressDialog.dismiss()
        }
    }

    override fun onStop() {
        super.onStop()
        if (::progressDialog.isInitialized) {
            progressDialog.dismiss()
        }
    }
}