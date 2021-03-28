package com.ovoskop.secondbreath.ui.adapters

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.ui.holders.CaliberHolder
import com.ovoskop.secondbreath.ui.views.CheckedButtonView
import com.ovoskop.secondbreath.utils.classes.Caliber

class CaliberListAdapter(private val context: Context) : RecyclerView.Adapter<CaliberHolder>() {
    private var list: List<Caliber> = listOf()
    private var listener: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaliberHolder {
        return CaliberHolder(CheckedButtonView(context, null))
    }

    override fun onBindViewHolder(holder: CaliberHolder, position: Int) {
        holder.onBind(list[position]) {
            listener?.invoke(position)
        }
    }

    override fun getItemCount(): Int = list.size

    fun reload(list: List<Caliber>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemCLickListener(listener: (position: Int) -> Unit) {
        this.listener = listener
    }
}