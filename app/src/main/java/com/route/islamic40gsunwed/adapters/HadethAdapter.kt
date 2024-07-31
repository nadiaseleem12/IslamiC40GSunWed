package com.route.islamic40gsunwed.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.islamic40gsunwed.Hadeth
import com.route.islamic40gsunwed.R
import com.route.islamic40gsunwed.callbacks.OnHadethClickListener

class HadethAdapter(val hadethList: List<Hadeth>) : Adapter<HadethAdapter.HadethViewHolder>() {
    var onHadethClickListener: OnHadethClickListener? = null

    class HadethViewHolder(private val itemHadeth: View) : ViewHolder(itemHadeth) {
        val hadeth: TextView = itemHadeth.findViewById(R.id.hadeth)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadethViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_hadeth, parent, false)
        return HadethViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hadethList.size
    }

    override fun onBindViewHolder(holder: HadethViewHolder, position: Int) {
        val item = hadethList.get(position)
        holder.hadeth.text = item.title
        holder.itemView.setOnClickListener {
            onHadethClickListener?.onHadethClick(item, position)
        }

    }
}