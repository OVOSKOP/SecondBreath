package com.ovoskop.secondbreath.ui.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.utils.classes.Exercise

class ExerciseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image: ImageView = itemView.findViewById(R.id.image_card)
    private val name: TextView = itemView.findViewById(R.id.name_card)
    private val amount: TextView = itemView.findViewById(R.id.amount_card)
    private val status: View = itemView.findViewById(R.id.is_status)
    private val statusImage: ImageView = itemView.findViewById(R.id.image_status)

    fun bind(exercise: Exercise) {
        image.setImageResource(exercise.resImage)
        name.text = exercise.name
        amount.text = exercise.amount

        exercise.isComplete?.apply {
            status.visibility = View.VISIBLE

            if (this) {
                status.setBackgroundResource(R.drawable.ic_completed)
                statusImage.setImageResource(R.drawable.ic_completed_icon)
            } else {
                status.setBackgroundResource(R.drawable.ic_not_completed)
                statusImage.setImageResource(R.drawable.ic_not)
            }
        }
    }


}