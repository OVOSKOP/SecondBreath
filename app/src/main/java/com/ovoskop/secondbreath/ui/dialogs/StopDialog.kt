package com.ovoskop.secondbreath.ui.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ovoskop.secondbreath.MainActivity
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.utils.addOnWindowFocusChangeListener
import com.ovoskop.secondbreath.utils.dpToPx
import com.ovoskop.secondbreath.utils.hideSystemUI

class StopDialog : DialogFragment() {

    private lateinit var positive: Button
    private lateinit var negative: Button

    private var listener: (() -> Unit)? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.alert_dialog, container, false)

        addOnWindowFocusChangeListener {
            hideSystemUI()
        }

        positive = root.findViewById(R.id.positive_btn)
        negative = root.findViewById(R.id.negative_btn)

        positive.setOnClickListener {
            dismiss()

            listener?.invoke()
        }

        negative.setOnClickListener {
            dismiss()
        }


        return root
    }

    override fun onStart() {
        super.onStart()

        hideSystemUI()
        dialog?.window?.setLayout(dpToPx(320f, requireContext()).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            dialog?.window?.setDecorFitsSystemWindows(false)
        }
        (requireActivity() as MainActivity).hideSystemUI()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        (requireActivity() as MainActivity).hideSystemUI()
    }

    fun setOnDismissListener(listener: () -> Unit) {
        this.listener = listener
    }
}