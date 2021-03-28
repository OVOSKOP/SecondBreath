package com.ovoskop.secondbreath.ui.holders

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.utils.Mode
import com.ovoskop.secondbreath.utils.classes.BluetoothListDevice

class BluetoothHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val card: View = itemView.findViewById(R.id.item_card)
    private val name: TextView = itemView.findViewById(R.id.name_blue)
    private val mac: TextView = itemView.findViewById(R.id.mac_blue)

    fun bind(device: BluetoothListDevice, position: Int, mode: Mode) {
        if (position % 2 == 1) {
            if (mode == Mode.DAY) {
                card.setBackgroundColor(Color.WHITE)
            } else {
                card.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_list_blue_second_night))
            }
        }

        name.text = device.name
        mac.text = device.mac
    }

    fun setOnClickListener(listener: () -> Unit) {
        card.setOnClickListener {
            listener()
        }
    }

}