package com.route.islamic40gsunwed

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.route.islamic40gsunwed.adapters.VersesAdapter

class ChapterContentActivity : AppCompatActivity() {
    var chapterName: String? = null
    var chapterPosition: Int? = null
    lateinit var titleTextView: TextView
    lateinit var backButton: ImageView
    lateinit var versesRecyclerView: RecyclerView
    lateinit var adapter: VersesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_content)
        initParams()
        initViews()
    }

    fun initViews() {
        titleTextView = findViewById(R.id.title_toolbar)
        titleTextView.text = chapterName
        versesRecyclerView = findViewById(R.id.chapter_content_recycler_view)
        readVersesFromFile()
        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    // Folder Assets
    fun readVersesFromFile() {
        val allFileContent = assets.open("${chapterPosition!! + 1}.txt").bufferedReader().use {
            it.readText()
        }
        val list = allFileContent.trim().split("\n")

        adapter = VersesAdapter(list)
        versesRecyclerView.adapter = adapter

        // SharedPreferences   -> Key Value
        // Data Store          ->


        // SharedPreferences
        // Token ->
    }

    fun initParams() {
        chapterName = intent.getStringExtra(Constants.CHAPTER_NAME_EXTRA)
        chapterPosition = intent.getIntExtra(Constants.CHAPTER_POSITION_EXTRA, -1)
    }
}