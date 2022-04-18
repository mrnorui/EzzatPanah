package academy.nouri.mvp.ui.splash

import academy.nouri.mvp.BuildConfig
import android.os.Bundle
import academy.nouri.mvp.databinding.ActivitySplashBinding
import academy.nouri.mvp.ui.base.BaseActivity
import academy.nouri.mvp.ui.main.MainActivity
import academy.nouri.mvp.ui.register.RegisterActivity
import academy.nouri.mvp.utils.Constants
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import nouri.`in`.goodprefslib.GoodPrefs

class SplashActivity : BaseActivity() {
    //Binding
    private lateinit var binding: ActivitySplashBinding

    //User token
    private val userToken by lazy {
        GoodPrefs.getInstance().getString(Constants.USER_TOKEN, "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Version
        binding.appVersionTxt.text = BuildConfig.VERSION_NAME
        //Check user state
        Handler(Looper.getMainLooper()).postDelayed({
            if (userToken.isNotEmpty()) {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            } else {
                Intent(this, RegisterActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }, 2000)
    }
}