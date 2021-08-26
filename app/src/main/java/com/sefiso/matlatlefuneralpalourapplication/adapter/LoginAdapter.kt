package com.sefiso.matlatlefuneralpalourapplication


import androidx.fragment.app.*
import java.util.ArrayList

class PagerAdapter(
        fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    private val fragmentList : MutableList<Fragment> = ArrayList()
    private val titleList : MutableList<String> = ArrayList()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}

