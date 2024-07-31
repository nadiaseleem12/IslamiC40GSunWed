package com.route.islamic40gsunwed.callbacks

import com.route.islamic40gsunwed.Hadeth

interface OnHadethClickListener {
    fun onHadethClick(hadeth: Hadeth, position: Int)
}