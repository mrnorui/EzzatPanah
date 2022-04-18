package academy.nouri.ezatpanahcourse.container

import academy.nouri.ezatpanahcourse.container.fragments.HomeFragment
import academy.nouri.ezatpanahcourse.container.fragments.ProfileFragment
import academy.nouri.ezatpanahcourse.container.fragments.ShopFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager, var totalPages: Int) : FragmentPagerAdapter(fm) {

    override fun getCount() = totalPages

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ShopFragment()
            else -> ProfileFragment()
        }
    }
}