package com.ovoskop.secondbreath.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.ui.holders.ExerciseHolder
import com.ovoskop.secondbreath.utils.classes.Exercise

class ExerciseAdapter : RecyclerView.Adapter<ExerciseHolder>() {
    private var list: List<Exercise> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        return ExerciseHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_action, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun reload(list: List<Exercise>) {
        this.list = list
        notifyDataSetChanged()
    }
}