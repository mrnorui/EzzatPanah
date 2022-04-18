package academy.nouri.ezatpanahcourse

import academy.nouri.ezatpanahcourse.databinding.ActivityDialogBinding
import academy.nouri.ezatpanahcourse.utils.KEY_INFO
import academy.nouri.ezatpanahcourse.utils.SHARED_FILENAME
import academy.nouri.ezatpanahcourse.utils.showToast
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class DialogActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Init view
        binding.apply {
            //Show dialog
            showDialog.setOnClickListener {
                showDialog("نمایش متن از ورودی دکمه")
            }
        }
    }

    private fun showDialog(text: String) {
        val dialog = Dialog(this@DialogActivity)
        dialog.setContentView(R.layout.dialog_info)
        dialog.setCancelable(false)

        val dialogTitle = dialog.findViewById<TextView>(R.id.dialogTitle)
        val dialogCancel = dialog.findViewById<Button>(R.id.dialogCancel)
        val dialogExit = dialog.findViewById<Button>(R.id.dialogExit)
        val dialogEdt = dialog.findViewById<EditText>(R.id.dialogEdt)
        val dialogSetData = dialog.findViewById<Button>(R.id.dialogSetData)
        //Views
        dialogTitle.text = text
        //Dismiss
        dialogCancel.setOnClickListener { dialog.dismiss() }
        //Exit
        dialogExit.setOnClickListener { finish() }
        //Set data
        dialogSetData.setOnClickListener {
            binding.showInfo.text = dialogEdt.text.toString()
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onBackPressed() {
        showDialog("آیا مطمئن هستید؟")
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}