package com.route.islamic40gsunwed.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.route.islamic40gsunwed.Constants
import com.route.islamic40gsunwed.Hadeth
import com.route.islamic40gsunwed.HadethContentActivity
import com.route.islamic40gsunwed.R
import com.route.islamic40gsunwed.adapters.HadethAdapter
import com.route.islamic40gsunwed.callbacks.OnHadethClickListener

class HadeethFragment : Fragment() {
    lateinit var hadethRecyclerView: RecyclerView
    lateinit var adapter: HadethAdapter
    lateinit var hadethList: MutableList<Hadeth>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hadeth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    fun initViews(view: View) {
        hadethRecyclerView = view.findViewById(R.id.hadeth_recycler_view)
        readHadethFile()

    }

    private fun readHadethFile() {
        val allFileContent = requireContext().assets.open("ahadeth.txt").bufferedReader().use {
            it.readText()
        }
        val hadethListSplit = allFileContent.trim().split("#")
        hadethList = mutableListOf()
        hadethListSplit.forEach { hadeth ->
            val splittedHadeth = hadeth.trim().split("\n")
            val splittedDescList = splittedHadeth.subList(1, splittedHadeth.size)

            hadethList.add(
                Hadeth(
                    title = splittedHadeth.get(0),
                    splittedDescList.joinToString()
                )
            )
            Log.e("TAG", "readHadethFile: Title = ${splittedHadeth.get(0)} ")
            Log.e("TAG", "readHadethFile: Desc = ${splittedDescList.joinToString()} ")

        }
        adapter = HadethAdapter(hadethList)
        adapter.onHadethClickListener = object : OnHadethClickListener {
            override fun onHadethClick(hadeth: Hadeth, position: Int) {
                val intent = Intent(requireActivity(), HadethContentActivity::class.java)
                intent.putExtra(Constants.HADETH_TITLE_EXTRA, hadeth.title)
                intent.putExtra(Constants.HADETH_DESC_EXTRA, hadeth.description)
                startActivity(intent)
            }
        }
        hadethRecyclerView.adapter = adapter
    }
}