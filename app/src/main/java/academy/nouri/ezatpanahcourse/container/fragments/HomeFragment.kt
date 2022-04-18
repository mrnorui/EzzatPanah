package academy.nouri.ezatpanahcourse.container.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.nouri.ezatpanahcourse.R
import academy.nouri.ezatpanahcourse.databinding.FragmentHomeBinding
import academy.nouri.ezatpanahcourse.utils.*
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import nouri.`in`.goodprefslib.GoodPrefs
import org.greenrobot.eventbus.EventBus

class HomeFragment() : Fragment() {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Get data
/*        if (GoodPrefs.getInstance().isKeyExists(KEY_USERNAME)) {
            binding.textView.text = GoodPrefs.getInstance().getString(KEY_USERNAME, "")
        }*/

        binding.textView.text = 1000000.moneySeparating()

        binding.button.enableDisableView(true, 1.0f)
        binding.textView.enableDisableView(true, 0.5f)

        //Click
        binding.button.setOnClickListener {
            val userName = binding.editTextTextPersonName.text.toString()
            binding.textView.text = userName
            GoodPrefs.getInstance().saveString(KEY_USERNAME, userName)

            showToast2(requireContext(), userName)
        }
    }
}