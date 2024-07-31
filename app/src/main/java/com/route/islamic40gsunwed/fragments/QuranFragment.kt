package com.route.islamic40gsunwed.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.route.islamic40gsunwed.Chapter
import com.route.islamic40gsunwed.ChapterContentActivity
import com.route.islamic40gsunwed.Constants
import com.route.islamic40gsunwed.R
import com.route.islamic40gsunwed.adapters.ChapterNamesAdapter
import com.route.islamic40gsunwed.callbacks.OnChapterClickListener

class QuranFragment : Fragment() {
    lateinit var adapter: ChapterNamesAdapter
    lateinit var chaptersList: MutableList<Chapter>
    lateinit var chaptersRecyclerView: RecyclerView
    lateinit var switchModeButton: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chaptersRecyclerView = view.findViewById(R.id.chapters_recycler_view)
        initList()
        adapter = ChapterNamesAdapter(chaptersList)
        adapter.onChapterClickListener = object : OnChapterClickListener {
            override fun onChapterClick(chapter: Chapter, position: Int) {
                val intent = Intent(requireActivity(), ChapterContentActivity::class.java)
                intent.putExtra(Constants.CHAPTER_NAME_EXTRA, chapter.name)
                intent.putExtra(Constants.CHAPTER_POSITION_EXTRA, position)
                startActivity(intent)
            }

        }
        chaptersRecyclerView.adapter = adapter
        switchModeButton = view.findViewById(R.id.switch_mode_button)
        if (isLight()) {
            switchModeButton.text = "Dark Mode"
        } else {
            switchModeButton.text = "Light Mode"
        }
        switchModeButton.setOnClickListener {
            if (isLight()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchModeButton.text = "Light Mode"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchModeButton.text = "Dark Mode"
            }
        }
    }

    fun isLight(): Boolean {
        Log.e("TAG", "isLight: ${AppCompatDelegate.getDefaultNightMode()}")
        return AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO ||
                AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    fun initList() {
        chaptersList = mutableListOf()
        Constants.chapters.forEachIndexed { index, item ->
            chaptersList.add(Chapter(item, index + 1))
        }
    }
}