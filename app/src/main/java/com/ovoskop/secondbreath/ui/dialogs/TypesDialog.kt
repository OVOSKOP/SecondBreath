package com.ovoskop.secondbreath.ui.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ovoskop.secondbreath.MainActivity
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.ui.views.CheckedButtonView
import com.ovoskop.secondbreath.utils.addOnWindowFocusChangeListener
import com.ovoskop.secondbreath.utils.dpToPx
import com.ovoskop.secondbreath.utils.hideSystemUI

class TypesDialog : DialogFragment() {

    private var listener: (() -> Unit)? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.type_dialog, container, false)

        addOnWindowFocusChangeListener {
            hideSystemUI()
        }

        root.findViewById<View>(R.id.type_one).setOnClickListener { dismiss() }
        root.findViewById<View>(R.id.type_two).setOnClickListener { dismiss() }

        return root
    }

    override fun onStart() {
        super.onStart()

        hideSystemUI()
        dialog?.window?.setLayout(dpToPx(360f, requireContext()).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            dialog?.window?.setDecorFitsSystemWindows(false)
        }
        (requireActivity() as MainActivity).hideSystemUI()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        listener?.invoke()
        (requireActivity() as MainActivity).hideSystemUI()
    }

    fun setOnDismissListener(listener: () -> Unit) {
        this.listener = listener
    }
}