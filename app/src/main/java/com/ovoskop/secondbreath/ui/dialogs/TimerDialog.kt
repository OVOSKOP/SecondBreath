package com.ovoskop.secondbreath.ui.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.ovoskop.secondbreath.MainActivity
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.ui.views.CheckedButtonView
import com.ovoskop.secondbreath.ui.views.MenuItemView
import com.ovoskop.secondbreath.utils.addOnWindowFocusChangeListener
import com.ovoskop.secondbreath.utils.dpToPx
import com.ovoskop.secondbreath.utils.hideSystemUI

class TimerDialog : DialogFragment() {

    private lateinit var counter: TextView
    private var listener: (() -> Unit)? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.timer_dialog, container, false)
        dialog?.setCanceledOnTouchOutside(false)

        addOnWindowFocusChangeListener {
            hideSystemUI()
        }

        counter = root.findViewById(R.id.timer_counter)


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

        val timer = object: CountDownTimer(5200, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                counter.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                listener?.invoke()

                dismiss()
            }
        }
        timer.start()
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