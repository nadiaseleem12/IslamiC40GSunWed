package com.route.islamic40gsunwed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.route.islamic40gsunwed.Chapter
import com.route.islamic40gsunwed.Constants
import com.route.islamic40gsunwed.R
import com.route.islamic40gsunwed.adapters.ChapterNamesAdapter

class QuranFragment : Fragment() {
    lateinit var adapter: ChapterNamesAdapter
    lateinit var chaptersList: MutableList<Chapter>
    lateinit var chaptersRecyclerView: RecyclerView
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
        chaptersRecyclerView.adapter = adapter
    }

    fun initList() {
        chaptersList = mutableListOf()
        Constants.chapters.forEachIndexed { index, item ->
            chaptersList.add(Chapter(item, index + 1))
        }
    }
}