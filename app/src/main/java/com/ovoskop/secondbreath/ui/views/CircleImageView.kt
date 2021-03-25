package com.ovoskop.secondbreath.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.ovoskop.secondbreath.R

class CircleImageView(context: Context, attrs: AttributeSet) : CardView(context, attrs) {

    private val imageView = ImageView(context)

    var image: Int = R.drawable.man
        set(value) {
            field = value

            imageView.setImageResource(value)
        }

    init {
        addView(imageView)

        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        radius = 500f
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            elevation = 0f
        }

        context.obtainStyledAttributes(attrs,
            R.styleable.CircleImageView, 0, 0)
            .apply {
                try {
                    image = getResourceId(R.styleable.CircleImageView_imageView, R.drawable.man)
                } finally { }
            }
            .recycle()
    }


}