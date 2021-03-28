package com.ovoskop.secondbreath.ui.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.MainActivity
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.ui.adapters.BluetoothListAdapter
import com.ovoskop.secondbreath.utils.App
import com.ovoskop.secondbreath.utils.addOnWindowFocusChangeListener
import com.ovoskop.secondbreath.utils.classes.BluetoothListDevice
import com.ovoskop.secondbreath.utils.dpToPx
import com.ovoskop.secondbreath.utils.hideSystemUI

class DeviceDialog : DialogFragment() {

    private lateinit var devices: RecyclerView

    private lateinit var adapter: BluetoothListAdapter

    private val deviceDemoList = listOf(
            BluetoothListDevice("Registrar 1", "4C:11:AE:D0:7E:0E"),
            BluetoothListDevice("Registrar 1", "4C:11:AE:D0:7E:0E"),
            BluetoothListDevice("Registrar 1", "4C:11:AE:D0:7E:0E")
    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.bluetooth_dialog, container, false)
        adapter = BluetoothListAdapter(requireContext(), (requireContext().applicationContext as App).mode)

        devices = root.findViewById(R.id.devices_list)

        adapter.setOnItemCLickListener {
            dismiss()
        }

        addOnWindowFocusChangeListener {
            hideSystemUI()
        }

        devices.layoutManager = LinearLayoutManager(requireContext())
        devices.adapter = adapter

        dialog?.setCanceledOnTouchOutside(false)

        return root
    }

    override fun onStart() {
        super.onStart()

        hideSystemUI()
        dialog?.window?.setLayout(dpToPx(460f, requireContext()).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            dialog?.window?.setDecorFitsSystemWindows(false)
        }

        adapter.reload(deviceDemoList)
        (requireActivity() as MainActivity).hideSystemUI()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        (requireActivity() as MainActivity).hideSystemUI()
    }
}