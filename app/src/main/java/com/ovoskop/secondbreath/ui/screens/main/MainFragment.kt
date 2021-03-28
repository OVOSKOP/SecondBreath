package com.ovoskop.secondbreath.ui.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ovoskop.secondbreath.R

class MainFragment : Fragment() {

    private lateinit var controller: MainController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        controller = MainController(this, root)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        controller.destroy()
    }

}