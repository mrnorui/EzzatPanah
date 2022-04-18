package academy.nouri.ezatpanahcourse.container.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.nouri.ezatpanahcourse.R
import academy.nouri.ezatpanahcourse.databinding.FragmentHomeBinding
import academy.nouri.ezatpanahcourse.databinding.FragmentShopBinding
import academy.nouri.ezatpanahcourse.utils.EventChangeText
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ShopFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onChangeText(event: EventChangeText) {
        if (event.isClosed) {
            binding.shopTxt.text = "متن عوض شد"
        }
    }
}