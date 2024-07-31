package com.route.islamic40gsunwed.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.islamic40gsunwed.R

class VersesAdapter(val versesList: List<String>? = null) :
    RecyclerView.Adapter<VersesAdapter.VerseViewHolder>() {

    class VerseViewHolder(private val itemVerseView: View) : ViewHolder(itemVerseView) {
        val verse = itemVerseView.findViewById<TextView>(R.id.verse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_verse, parent, false)
        return VerseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return versesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: VerseViewHolder, position: Int) {
        val item = versesList?.get(position)!!
        holder.verse.text = "$item(${position + 1})"
    }
}
