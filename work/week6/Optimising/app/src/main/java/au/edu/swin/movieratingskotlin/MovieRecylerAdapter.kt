package au.edu.swin.movieratingskotlin

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.listrow.view.*

class MovieRecylerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listrow, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(items[position])
        }
    }

    fun submitList(movies: List<Movie>) {
        items = movies
    }

    class MovieViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.row_label
        val subtext = itemView.row_subtext
        val icon = itemView.row_icon

        fun bind(movie: Movie) {
            title.text = movie.name
            subtext.text = movie.votes + " votes"
            icon.setImageBitmap(getMovieIcon(movie.name, movie.rating))
        }

        /** Creates a unique movie icon based on name and rating  */
        private fun getMovieIcon(movieName: String, movieRating: String): Bitmap {
            val bgColor = getColor(movieName)
            val b = Bitmap.createBitmap(192, 192, Bitmap.Config.ARGB_8888)
            b.eraseColor(bgColor) // fill bitmap with the color
            val c = Canvas(b)
            val p = Paint()
            p.isAntiAlias = true
            p.color = getTextColor(bgColor)
            p.textSize = 96.0f
            c.drawText(movieRating, 32f, 96f, p)
            return b
        }

        /** Construct a color from a movie name  */
        private fun getColor(name: String): Int {
            val hex = toHexString(name)
            val red = "#" + hex.substring(0, 2)
            val green = "#" + hex.substring(2, 4)
            val blue = "#" + hex.substring(4, 6)
            val alpha = "#" + hex.substring(6, 8)
            return Color.argb(Integer.decode(alpha)!!, Integer.decode(red)!!,
                    Integer.decode(green)!!, Integer.decode(blue)!!)
        }

        /** Given a movie name -- generate a hex value from its hashcode  */
        private fun toHexString(name: String): String {
            val hc = name.hashCode()
            var hex = Integer.toHexString(hc)
            if (hex.length < 8) {
                hex = hex + hex + hex
                hex = hex.substring(0, 8) // use default color value
            }
            return hex
        }

        /** Crude optimization to obtain a contrasting color -- does not work well yet  */
        private fun getTextColor(bg: Int): Int {

            var r = Color.red(bg)
            var g = Color.green(bg)
            var b = Color.blue(bg)
            var hex = Integer.toHexString(r) + Integer.toHexString(g)
            hex += Integer.toHexString(b)

            val cDec = Integer.decode("#$hex")!!
            if (cDec > 0xFFFFFF / 2)
            // go dark for lighter shades
                return Color.rgb(0, 0, 0)
            else {
                r = (r + 128) % 256
                g = (g + 128) % 256
                b = (b + 128) % 256
                return Color.rgb(r, g, b)
            }
        }
    }
}