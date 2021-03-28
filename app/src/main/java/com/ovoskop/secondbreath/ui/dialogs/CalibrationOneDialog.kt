package com.ovoskop.secondbreath.ui.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.MainActivity
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.ui.adapters.CaliberListAdapter
import com.ovoskop.secondbreath.ui.adapters.ExerciseAdapter
import com.ovoskop.secondbreath.ui.decorators.CaliberListDecorator
import com.ovoskop.secondbreath.ui.decorators.ExerciseListDecorator
import com.ovoskop.secondbreath.ui.views.MenuItemView
import com.ovoskop.secondbreath.utils.addOnWindowFocusChangeListener
import com.ovoskop.secondbreath.utils.classes.Caliber
import com.ovoskop.secondbreath.utils.classes.Exercise
import com.ovoskop.secondbreath.utils.dpToPx
import com.ovoskop.secondbreath.utils.hideSystemUI

class CalibrationOneDialog : DialogFragment() {

    private lateinit var start: Button
    private lateinit var caliberList: RecyclerView

    private var listener: (() -> Unit)? = null

    private val demoList: List<Caliber> = listOf(
            Caliber(
                    "Калибровка 1",
                    true
            ),
            Caliber(
                    "Калибровка 2",
                    false
            ),
            Caliber(
                    "Калибровка 3",
                    false
            ),
            Caliber(
                    "Калибровка 4",
                    false
            ),
            Caliber(
                    "Калибровка 5",
                    false
            ),
            Caliber(
                    "Калибровка 6",
                    false
            ),
    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.set_calibr_dialog, container, false)

        val adapter = CaliberListAdapter(requireContext())
        adapter.setOnItemCLickListener {
            for (caliber in demoList) {
                caliber.check = false
            }

            demoList[it].check = true
            adapter.reload(demoList)
        }

        start = root.findViewById(R.id.start_caliber)
        caliberList = root.findViewById(R.id.caliber_list)

        start.setOnClickListener {
            dismiss()
        }

        caliberList.layoutManager = LinearLayoutManager(requireContext())
        caliberList.adapter = adapter
        caliberList.addItemDecoration(
                CaliberListDecorator(
                        resources.getDimensionPixelOffset(R.dimen.list_padding)
                )
        )

        addOnWindowFocusChangeListener {
            hideSystemUI()
        }

        adapter.reload(demoList)

        return root
    }

    override fun onStart() {
        super.onStart()

        hideSystemUI()
        dialog?.window?.setGravity(Gravity.TOP or Gravity.START)
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
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