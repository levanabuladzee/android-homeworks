package abuladze.levan.afinal.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val arrayList: ArrayList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment) = arrayList.add(fragment)

    override fun getItemCount(): Int = arrayList.size

    override fun createFragment(position: Int): Fragment = arrayList[position]

}