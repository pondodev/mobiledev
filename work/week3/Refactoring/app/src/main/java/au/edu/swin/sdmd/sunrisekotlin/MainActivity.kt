package au.edu.swin.sdmd.sunrisekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.share

class MainActivity : AppCompatActivity() {

    var currentFragment: InfoView = InfoView()

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
            R.id.menuGenerateTable -> generateTable()
        }

        return super.onOptionsItemSelected(item)
    }

    // called when we tap the share option
    private fun share() {
        var city = currentFragment.city
        var sunrise = currentFragment.sunrise
        var sunset = currentFragment.sunset
        val data = "City: " + city +
                  " Sunrise: " + sunrise +
                  " Sunset: " + sunset

        share(data)
    }

    private fun generateTable() {
        startActivity(intentFor<GenerateTable>(
            "city" to currentFragment.city,
            "lat" to currentFragment.lat,
            "lon" to currentFragment.lon
        ))
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

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) { }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                currentFragment = adapter.getRegisteredFragment(p0) as InfoView
            }

            override fun onPageSelected(p0: Int) { }
        })

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()
        private val registeredFragments = SparseArray<Fragment>()

        override fun getItem(p0: Int) = fragmentList[p0]

        override fun getCount() = fragmentList.size

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int) = titleList[position]

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val fragment = super.instantiateItem(container, position) as Fragment
            registeredFragments.put(position, fragment)

            return fragment
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            registeredFragments.remove(position)
            super.destroyItem(container, position, `object`)
        }

        fun getRegisteredFragment(pos: Int) = registeredFragments[pos]
    }
}
