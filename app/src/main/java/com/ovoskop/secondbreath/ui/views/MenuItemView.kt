package com.ovoskop.secondbreath.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.ovoskop.secondbreath.R

class MenuItemView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val item: LinearLayout
    private val imageView: ImageView
    private val titleView: TextView

    @DrawableRes var image: Int = R.drawable.ic_settings
        set(value) {
            field = value

            imageView.setImageResource(value)
        }

    var title: String = "Настройки"
        set(value) {
            field = value

            titleView.text = value
        }

    init {
        val root = inflate(context, R.layout.ic_item_menu, this)

        item = root.findViewById(R.id.menu_item)
        imageView = root.findViewById(R.id.image_menu_item)
        titleView = root.findViewById(R.id.title_menu_item)

        context.obtainStyledAttributes(attrs,
            R.styleable.MenuItemView, 0, 0)
            .apply {
                try {
                    image = getResourceId(R.styleable.MenuItemView_image, R.drawable.ic_settings)
                    title = getString(R.styleable.MenuItemView_title) ?: "Настройки"
                } finally { }
            }
            .recycle()
    }

    fun setOnItemClickListener(listener: () -> Unit) {
        item.setOnClickListener {
            listener()
        }
    }

}