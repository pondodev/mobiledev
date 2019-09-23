package au.edu.swin.sdmd.sunrisekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_info_view.*
import org.jetbrains.anko.share

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MyViewPagerAdapter(supportFragmentManager)
        createFragments(adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        // set our menu to be our custom one
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    // check which option button was tapped
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menuShare -> share()
        }

        return super.onOptionsItemSelected(item)
    }

    // called when we tap the share option
    private fun share() {
        var city = locationTV.text.toString()
        var sunrise = sunriseTimeTV.text.toString()
        var sunset = sunsetTimeTV.text.toString()
        val data = "City: " + city +
                  " Sunrise: " + sunrise +
                  " Sunset: " + sunset

        share(data)
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
        val canberra = initFragment("Canberra", 35.28, 149.13)

        adapter.addFragment(melbourne, "Melbourne")
        adapter.addFragment(sydney, "Sydney")
        adapter.addFragment(canberra, "Canberra")

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
