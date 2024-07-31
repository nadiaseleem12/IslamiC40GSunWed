package com.route.islamic40gsunwed

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.route.islamic40gsunwed.adapters.VersesAdapter

class HadethContentActivity : AppCompatActivity() {
    var title: String? = null
    var description: String? = null
    lateinit var titleTextView: TextView
    lateinit var versesRecyclerView: RecyclerView
    lateinit var adapter: VersesAdapter
    lateinit var backButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadeth_content)
        initParams()
        initViews()
    }

    fun initViews() {
        versesRecyclerView = findViewById(R.id.hadeth_content_recycler_view)
        titleTextView = findViewById(R.id.title_toolbar)
        titleTextView.text = title
        adapter = VersesAdapter(description?.split("\n"))
        versesRecyclerView.adapter = adapter
        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }


    fun initParams() {
        title = intent.getStringExtra(Constants.HADETH_TITLE_EXTRA)
        description = intent.getStringExtra(Constants.HADETH_DESC_EXTRA)
    }
}