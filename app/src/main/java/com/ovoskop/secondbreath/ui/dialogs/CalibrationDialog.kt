package com.ovoskop.secondbreath.ui.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.ovoskop.secondbreath.MainActivity
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.ui.views.CheckedButtonView
import com.ovoskop.secondbreath.ui.views.MenuItemView
import com.ovoskop.secondbreath.utils.addOnWindowFocusChangeListener
import com.ovoskop.secondbreath.utils.dpToPx
import com.ovoskop.secondbreath.utils.hideSystemUI

class CalibrationDialog : DialogFragment() {

    private lateinit var typeBtn: MenuItemView

    private lateinit var device1: MenuItemView
    private lateinit var device2: MenuItemView
    private lateinit var device3: MenuItemView
    private lateinit var device4: MenuItemView

    private val listener = {
        val caliberDialog = CalibrationOneDialog()
        caliberDialog.setOnDismissListener {
            hideSystemUI()
        }

        caliberDialog.show(requireFragmentManager(), "caliber")
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.calibration_dialog, container, false)

        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        typeBtn = root.findViewById(R.id.choose_type)

        device1 = root.findViewById(R.id.device_caliber_1)
        device2 = root.findViewById(R.id.device_caliber_2)
        device3 = root.findViewById(R.id.device_caliber_3)
        device4 = root.findViewById(R.id.device_caliber_4)

        typeBtn.setOnItemClickListener {
            val dialog = TypesDialog()
            dialog.setOnDismissListener {
                hideSystemUI()
            }

            dialog.show(requireFragmentManager(), "types")
        }

        addOnWindowFocusChangeListener {
            hideSystemUI()
        }

        device1.setOnItemClickListener(listener)
        device2.setOnItemClickListener(listener)
        device3.setOnItemClickListener(listener)
        device4.setOnItemClickListener(listener)

        return root
    }

    override fun onStart() {
        super.onStart()

        hideSystemUI()
        dialog?.window?.setGravity(Gravity.TOP or Gravity.START)
        dialog?.window?.setLayout(dpToPx(430f, requireContext()).toInt(), ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            dialog?.window?.setDecorFitsSystemWindows(false)
        }
        (requireActivity() as MainActivity).hideSystemUI()
    }

    override fun onResume() {
        super.onResume()

        hideSystemUI()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        (requireActivity() as MainActivity).hideSystemUI()
    }

}