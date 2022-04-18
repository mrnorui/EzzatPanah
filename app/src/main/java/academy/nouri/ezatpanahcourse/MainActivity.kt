package academy.nouri.ezatpanahcourse

import academy.nouri.ezatpanahcourse.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Other
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Disable views
        binding.mainSubmitBtn.alpha = 0.5f
        binding.mainSubmitBtn.isClickable = false
        binding.mainSubmitBtn.isEnabled = false
        //Click
        binding.mainSubmitBtn.setOnClickListener {
            //Email
            if (binding.mainEmailEdt.text.toString().isEmpty()) {
                binding.mainEmailInp.error = "Email is not empty"
            } else {
                binding.mainEmailInp.error = ""
                email = binding.mainEmailEdt.text.toString()
            }
            //Password
            if (binding.mainPasswordEdt.text.toString().isEmpty()) {
                binding.mainPasswordInp.error = "Password is not empty"
            } else {
                binding.mainPasswordInp.error = ""
                password = binding.mainPasswordEdt.text.toString()
            }
            //Check
            if (email.isNotEmpty() && password.isNotEmpty()) {
                //Start activity
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("USER_EMAIL_KEY", email)
                intent.putExtra("USER_PASSWORD_KEY", password)
                startActivity(intent)
                finish()
            }
        }
        //Accept terms
        binding.mainTermsCheck.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.mainSubmitBtn.alpha = 1.0f
                binding.mainSubmitBtn.isClickable = true
                binding.mainSubmitBtn.isEnabled = true
            } else {
                binding.mainSubmitBtn.alpha = 0.5f
                binding.mainSubmitBtn.isClickable = false
                binding.mainSubmitBtn.isEnabled = false
            }
        }
    }
}