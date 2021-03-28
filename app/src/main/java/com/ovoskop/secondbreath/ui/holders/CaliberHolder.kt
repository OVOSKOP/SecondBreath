package com.ovoskop.secondbreath.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.ui.views.CheckedButtonView
import com.ovoskop.secondbreath.utils.classes.Caliber

class CaliberHolder(itemView: CheckedButtonView) : RecyclerView.ViewHolder(itemView) {

    val item = itemView

    fun onBind(caliber: Caliber, listener: () -> Unit) {
        item.title = caliber.name
        item.isChecked = caliber.check

        item.setOnCheckClickListener {
            listener()
        }
    }

}