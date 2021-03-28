package com.ovoskop.secondbreath.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ovoskop.secondbreath.R

class CheckedButtonView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val item: LinearLayout
    private val checkImage: ImageView
    private val titleView: TextView

    var title: String = ""
        set(value) {
            field = value

            titleView.text = value
        }

    var isChecked: Boolean = false
        set(value) {
            field = value

            if (value) {
                checkImage.visibility = VISIBLE
            } else {
                checkImage.visibility = INVISIBLE
            }
        }

    init {
        val root = inflate(context, R.layout.checked_item_list, this)

        this.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        item = root.findViewById(R.id.checked_btn)
        checkImage = root.findViewById(R.id.checked)
        titleView = root.findViewById(R.id.title_check_btn)

        context.obtainStyledAttributes(attrs,
                R.styleable.CheckedButtonView, 0, 0)
                .apply {
                    try {
                        isChecked = getBoolean(R.styleable.CheckedButtonView_checked, false)
                        title = getString(R.styleable.CheckedButtonView_titleBtn) ?: ""
                    } finally { }
                }
                .recycle()

        item.setOnClickListener {
            isChecked = !isChecked
        }
    }

    fun setOnCheckClickListener(listener: () -> Unit) {
        item.setOnClickListener {
            isChecked = !isChecked

            listener()
        }
    }

}