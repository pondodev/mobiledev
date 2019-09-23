package au.edu.swin.sdmd.sunrisekotlin

import android.support.design.circularreveal.CircularRevealWidget
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.date_display.view.*

class DateEntryRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<DateEntry> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        DateEntryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.date_display, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateEntryViewHolder -> holder.bind(items[position])
        }
    }

    fun submitList(dateEntryList: List<DateEntry>) {
        items = dateEntryList
    }

    class DateEntryViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.txtDate
        val sunrise = itemView.txtSunrise
        val sunset = itemView.txtSunset

        fun bind(dateEntry: DateEntry) {
            date.text = "Date: " + dateEntry.date
            sunrise.text = "Sunrise: " + dateEntry.sunrise
            sunset.text = "Sunset: " + dateEntry.sunset
        }
    }
}