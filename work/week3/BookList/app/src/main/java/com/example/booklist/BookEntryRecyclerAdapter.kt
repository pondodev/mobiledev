package com.example.booklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.book_display.view.*

class BookEntryRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<BookEntry> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BookEntryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.book_display, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookEntryViewHolder -> holder.bind(items[position])
        }
    }

    fun submitList(bookEntryList: List<BookEntry>) {
        items = bookEntryList
    }

    class BookEntryViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.txtTitle
        val rating = itemView.txtRating
        val image = itemView.imgBook

        fun bind(bookEntry: BookEntry) {
            title.text = "title: " + bookEntry.title
            rating.text = "rating: " + bookEntry.rating.toString()
            image.setImageResource(bookEntry.imgID)
        }
    }
}