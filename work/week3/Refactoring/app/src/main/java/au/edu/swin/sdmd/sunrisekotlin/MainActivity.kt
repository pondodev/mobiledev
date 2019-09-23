package au.edu.swin.sdmd.sunrisekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MyViewPagerAdapter(supportFragmentManager)
        createFragments(adapter)
    }

    private fun initFragment(city: String, lat: Double, lon: Double): Fragment {
        val bundle = Bundle()
        bundle.putString("city", city)
        bundle.putDouble("lat", lat)
        bundle.putDouble("lon", lon)
        val frag = InfoView()
        frag.arguments = bundle

        return frag
    }

    private fun createFragments(adapter: MyViewPagerAdapter) {
        val melbourne = initFragment("Melbourne", -37.50, 145.01)
        val sydney = initFragment("Sydney", -33.86, 151.20)
        val brisbane = initFragment("Brisbane", 27.46, 153.02)
        val hobart = initFragment("Hobart", 42.88, 147.32)
        val canberra = initFragment("Canberra", 35.28, 149.13)
        val darwin = initFragment("Darwin", 12.46, 130.84)
        val adelaide = initFragment("Adelaide", 34.92, 138.60)
        val perth = initFragment("Perth", 31.95, 115.86)

        adapter.addFragment(melbourne, "Melbourne")
        adapter.addFragment(sydney, "Sydney")
        adapter.addFragment(brisbane, "Brisbane")
        adapter.addFragment(hobart, "Hobart")
        adapter.addFragment(canberra, "Canberra")
        adapter.addFragment(darwin, "Darwin")
        adapter.addFragment(adelaide, "Adelaide")
        adapter.addFragment(perth, "Perth")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()

        override fun getItem(p0: Int) = fragmentList[p0]

        override fun getCount() = fragmentList.size

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int) = titleList[position]
    }
}
