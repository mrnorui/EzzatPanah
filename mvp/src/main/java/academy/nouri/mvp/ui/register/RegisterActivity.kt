package academy.nouri.mvp.ui.register

import academy.nouri.mvp.R
import academy.nouri.mvp.data.model.register.BodyRegister
import academy.nouri.mvp.data.model.register.ResponseRegister
import academy.nouri.mvp.databinding.ActivityRegisterBinding
import academy.nouri.mvp.databinding.ActivitySplashBinding
import academy.nouri.mvp.ui.base.BaseActivity
import academy.nouri.mvp.ui.main.MainActivity
import academy.nouri.mvp.utils.Constants
import academy.nouri.mvp.utils.isNetworkAvailable
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import nouri.`in`.goodprefslib.GoodPrefs

class RegisterActivity : BaseActivity(), RegisterContracts.View {
    //Binding
    private lateinit var binding: ActivityRegisterBinding

    //Presenter
    private val presenter by lazy { RegisterPresenter(this) }

    //Other
    private lateinit var userBody: BodyRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Click
            loginSubmitBtn.setOnClickListener {
                val name = loginNameEdt.text.toString()
                val email = loginEmailEdt.text.toString()
                val password = loginPasswordEdt.text.toString()
                userBody = BodyRegister(name, email, password)
                presenter.callUserRegister(userBody)
            }
        }
    }

    override fun loadUserInfo(data: ResponseRegister) {
        GoodPrefs.getInstance().saveString(Constants.USER_TOKEN, data.email)
    }

    override fun gotoMainPage() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    override fun checkNetworkConnection(): Boolean {
        return isNetworkAvailable()
    }

    override fun networkConnectionError() {
        Toast.makeText(this, "Check your internet", Toast.LENGTH_SHORT).show()
    }

    override fun responseError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.loginSubmitLoading.visibility = View.VISIBLE
        binding.loginSubmitBtn.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        binding.loginSubmitLoading.visibility = View.GONE
        binding.loginSubmitBtn.visibility = View.VISIBLE
    }
}