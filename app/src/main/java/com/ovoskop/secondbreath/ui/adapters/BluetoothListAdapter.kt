package com.ovoskop.secondbreath.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.ui.holders.BluetoothHolder
import com.ovoskop.secondbreath.utils.Mode
import com.ovoskop.secondbreath.utils.classes.BluetoothListDevice

class BluetoothListAdapter(private val context: Context, private val mode: Mode) : RecyclerView.Adapter<BluetoothHolder>() {
    private var list: List<BluetoothListDevice> = listOf()
    private var listener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothHolder {
        return BluetoothHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_list_blue, parent, false),
                context
        )
    }

    override fun onBindViewHolder(holder: BluetoothHolder, position: Int) {
        holder.bind(list[position], position, mode)
        listener?.apply {
            holder.setOnClickListener(this)
        }
    }

    override fun getItemCount(): Int = list.size

    fun reload(list: List<BluetoothListDevice>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemCLickListener(listener: () -> Unit) {
        this.listener = listener
    }
}