package com.example.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var bookEntryAdapter: BookEntryRecyclerAdapter
    private lateinit var data: List<BookEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            bookEntryAdapter = BookEntryRecyclerAdapter()
            adapter = bookEntryAdapter
        }

        data = listOf(
            BookEntry("alens uh oh", 5, R.drawable.alien),
            BookEntry("phines n fer", 2, R.drawable.ferb),
            BookEntry("hey thanks for checking in im still a piece of garbage", 10, R.drawable.trash),
            BookEntry("ù̵̲͓̫͓̇ͣ̒̾ͪ̍̓͐̄͑̀̈͊̓́h̺͚̙̦̝͉̖̯̩̺̭̝͚̽ͫ́͆̓ͣ̽̽̓̎ͬ̋̋͗̅ͯ͠o̴ͧ͒̓̋̇̂̄̇͡҉̶̦̪̹͖͕̮̣̮̝̦̰̣̰͚̤̪͓͝h̸̤̞̱̬̲͖̣̄̑͌̐̓͒̈́̏̊̊́̚ͅ", 1, R.drawable.uhoh),
            BookEntry("...elmo?", 4, R.drawable.why),
            BookEntry("homber but he pikachu", 7, R.drawable.homer),
            BookEntry("um", 6, R.drawable.um),
            BookEntry("this goose bouta drop the hottest mixtape", 7, R.drawable.coolgoose),
            BookEntry("that's kinda wild", 10, R.drawable.tekdek),
            BookEntry(": )", 0, R.drawable.kirby)
        )

        bookEntryAdapter.submitList(data)
    }
}
