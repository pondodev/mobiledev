package au.edu.swin.movieratingskotlin

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

/**
 * Displays movie name, rating and votes. A custom icon is generated based on movie name and rating.
 * @author nronald
 * Based on code written by rvasa
 *
 */

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieRecylerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMovies().execute()
    }

    private fun initializeUI(movies: ArrayList<Movie>)
    {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            movieAdapter = MovieRecylerAdapter()
            adapter = movieAdapter
        }

        movieAdapter.submitList(movies)
    }

    internal inner class loadMovies : AsyncTask<Void, Void, ArrayList<Movie>>() {

        lateinit var loadingDialog: ProgressDialog

        override fun onPreExecute() {
            super.onPreExecute()
            loadingDialog = ProgressDialog.show(this@MainActivity, "", "Loading...")
        }

        override fun doInBackground(vararg p0: Void?): ArrayList<Movie> {
            Thread.sleep(3000)
            return Movie.loadFromFile(resources.openRawResource(R.raw.ratings))
        }

        override fun onPostExecute(result: ArrayList<Movie>?) {
            super.onPostExecute(result)
            loadingDialog.dismiss()

            if (result != null)
                initializeUI(result)
            else
                initializeUI(ArrayList())
        }
    }
}

