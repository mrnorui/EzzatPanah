package academy.nouri.ezatpanahcourse.container

import academy.nouri.ezatpanahcourse.databinding.ActivityContainerBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout

class ContainerActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityContainerBinding

    //Adapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Init views
        binding.apply {
            //Tab items
            containerTabLayout.addTab(containerTabLayout.newTab().setText("Home"))
            containerTabLayout.addTab(containerTabLayout.newTab().setText("Shop"))
            containerTabLayout.addTab(containerTabLayout.newTab().setText("Profile"))
            containerTabLayout.tabGravity = TabLayout.GRAVITY_FILL
            //ViewPager
            viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, containerTabLayout.tabCount)
            containerViewpager.adapter = viewPagerAdapter
            //Select item
            containerTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab) {
                    containerViewpager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}

            })
        }
    }
}