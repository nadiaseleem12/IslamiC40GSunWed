package com.route.islamic40gsunwed.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.inflate
import com.route.islamic40gsunwed.Chapter
import com.route.islamic40gsunwed.R
import com.route.islamic40gsunwed.callbacks.OnChapterClickListener

class ChapterNamesAdapter(val chapters: List<Chapter>) :
    RecyclerView.Adapter<ChapterNamesAdapter.ChapterNameViewHolder>() {
    //2
    var onChapterClickListener: OnChapterClickListener? = null

    class ChapterNameViewHolder(val itemChapterName: View) : ViewHolder(itemChapterName) {
        val chapterNameText: TextView = itemChapterName.findViewById(R.id.chapter_name_text)
        val chapterPositionText: TextView = itemChapterName.findViewById(R.id.chapter_position_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterNameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_chapter_name, parent, false)
        return ChapterNameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    override fun onBindViewHolder(holder: ChapterNameViewHolder, position: Int) {
        val item = chapters.get(position)
        holder.chapterNameText.text = item.name
        holder.chapterPositionText.text = item.position.toString()
        holder.itemChapterName.setOnClickListener {
            // 3-
            onChapterClickListener?.onChapterClick(item, position)
        }
    }
//
//    fun addChapter(chapter: Chapter) {
//        chapters.add(chapter)
//        notifyItemInserted(itemCount)
//    }
}