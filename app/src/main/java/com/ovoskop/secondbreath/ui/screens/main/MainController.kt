package com.ovoskop.secondbreath.ui.screens.main

import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.MainActivity
import com.ovoskop.secondbreath.R
import com.ovoskop.secondbreath.ui.adapters.ExerciseAdapter
import com.ovoskop.secondbreath.ui.decorators.ExerciseListDecorator
import com.ovoskop.secondbreath.ui.dialogs.CalibrationDialog
import com.ovoskop.secondbreath.ui.dialogs.StopDialog
import com.ovoskop.secondbreath.ui.dialogs.TimerDialog
import com.ovoskop.secondbreath.ui.views.MenuItemView
import com.ovoskop.secondbreath.utils.App
import com.ovoskop.secondbreath.utils.Mode
import com.ovoskop.secondbreath.utils.classes.Exercise
import com.ovoskop.secondbreath.utils.dpToPx
import com.ovoskop.secondbreath.utils.hideSystemUI
import pl.pawelkleczkowski.customgauge.CustomGauge

class MainController(private val fragment: Fragment, view: View) {

    private val app: App = (fragment.requireContext().applicationContext as App)
    private val listExercise: RecyclerView = view.findViewById(R.id.list_exercise)
    private val adapter = ExerciseAdapter()
    private val modeMenuBtn: MenuItemView = view.findViewById(R.id.mode_menu)
    private val settingMenuBtn: MenuItemView = view.findViewById(R.id.setting_menu)
    private val profileMenuBtn: MenuItemView = view.findViewById(R.id.profile_menu)
    private val quitMenuBtn: MenuItemView = view.findViewById(R.id.quit_menu)

    private val drawerLayout: DrawerLayout = view.findViewById(R.id.drawer_layout)
    private val openNav: ImageButton = view.findViewById(R.id.open_nav)

    private val progressBreath: CustomGauge = view.findViewById(R.id.progress_breath)
    private val amountBreath: TextView = view.findViewById(R.id.amount_breath)

    private val progressCO: CustomGauge = view.findViewById(R.id.progress_co)
    private val amountCO: TextView = view.findViewById(R.id.amount_co)

    private val progressO: CustomGauge = view.findViewById(R.id.progress_o)
    private val amountO: TextView = view.findViewById(R.id.amount_o)

    private val progressTemp: CustomGauge = view.findViewById(R.id.progress_temp)
    private val amountTemp: TextView = view.findViewById(R.id.amount_temp)

    private val progressPulse: CustomGauge = view.findViewById(R.id.progress_pulse)
    private val amountPulse: TextView = view.findViewById(R.id.amount_pulse)

    private val startView: View = view.findViewById(R.id.start_view)
    private val stopView: View = view.findViewById(R.id.stop_view)

    private val startBtn: Button = view.findViewById(R.id.start_btn)
    private val stopBtn: Button = view.findViewById(R.id.stop_btn)

    private val timeExercise: TextView = view.findViewById(R.id.time_exercise)
    private val exerciseCount: View = view.findViewById(R.id.exercise_count)

    private val shared = fragment.context?.getSharedPreferences(
            "mode", Context.MODE_PRIVATE)

    private var animator: ValueAnimator? = null

    private val demoList: List<Exercise> = listOf(
            Exercise(
                    R.drawable.ic_icon_1,
                    "Бег",
                    "5 минут"
            ),
            Exercise(
                    R.drawable.ic_icon_2,
                    "Прыжки",
                    "25 раз"
            ),
            Exercise(
                    R.drawable.ic_icon_1,
                    "Бег",
                    "5 минут"
            ),
            Exercise(
                    R.drawable.ic_icon_2,
                    "Прыжки",
                    "25 раз"
            )
    )

    init {
        listExercise.layoutManager = LinearLayoutManager(fragment.context, LinearLayoutManager.HORIZONTAL, false)
        listExercise.adapter = adapter
        listExercise.addItemDecoration(
                ExerciseListDecorator(
                        fragment.resources.getDimensionPixelOffset(R.dimen.default_padding)
                )
        )

        openNav.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        startBtn.setOnClickListener {
            val dialog = TimerDialog()
            dialog.setOnDismissListener {
                startView.visibility = View.GONE
                stopView.visibility = View.VISIBLE
            }

            dialog.show(fragment.childFragmentManager, "timer")
        }

        stopBtn.setOnClickListener {
            val dialog = StopDialog()
            dialog.setOnDismissListener {
                startView.visibility = View.VISIBLE
                stopView.visibility = View.GONE

                timeExercise.text = "Время тренировки: 10 Минут 5 Секунд"
                exerciseCount.visibility = View.VISIBLE

                demoList.forEachIndexed { i, exercise ->
                    exercise.isComplete = i == 0

                }
                reloadListExercise(demoList)
            }

            dialog.show(fragment.childFragmentManager, "stop")
        }

        reloadListExercise(demoList)
        setOnMenuItemsClickListener()

        startAnimateProgressBar(progressBreath, amountBreath, 180, 0, 150)
        startAnimateProgressBar(progressCO, amountCO, 200, 0, 150, isMulti = true)
        startAnimateProgressBar(progressO, amountO, 80, 0, 50, isMulti = true)
        startAnimateProgressBar(progressTemp, amountTemp, 370, 200, 401, isPoint = true)
        startAnimateProgressBar(progressPulse, amountPulse, 100, 0, 90)
    }

    private fun reloadListExercise(list: List<Exercise>) {
        adapter.reload(list)
    }

    private fun setOnMenuItemsClickListener() {

        modeMenuBtn.setOnItemClickListener {
            val night: Boolean

            if (app.mode == Mode.DAY) {
                app.mode = Mode.NIGHT
                night = true
            } else {
                app.mode = Mode.DAY
                night = false
            }

            with(shared?.edit()) {
                this?.putBoolean("night", night)

                this?.apply()
            }
        }


        settingMenuBtn.setOnItemClickListener {
            drawerLayout.closeDrawer(Gravity.LEFT)

            val dialog = CalibrationDialog()
            dialog.show(fragment.childFragmentManager, "calibration")
        }


        profileMenuBtn.setOnItemClickListener {

        }


        quitMenuBtn.setOnItemClickListener {

        }

    }

    private fun startAnimateProgressBar(progressBar: CustomGauge, textView: TextView, normalValue: Int, startValue: Int, endValue: Int, isPoint: Boolean = false, isMulti: Boolean = false) {

        animator = ValueAnimator.ofInt(startValue, endValue)
        animator?.duration = 2000
        animator?.addUpdateListener {
            progressBar.value = it.animatedValue as Int
            if (isPoint) {
                textView.text = (((it.animatedValue as Int).toFloat()) / 10).toString()
            } else {
                if (isMulti) {
                    textView.text = ((it.animatedValue as Int) / 10).toString()
                } else {
                    textView.text = it.animatedValue.toString()
                }
            }

            if (it.animatedValue as Int > normalValue) {
                if (fragment.isAdded) {
                    progressBar.strokeColor = ContextCompat.getColor(fragment.requireContext(), R.color.radius_error_bg)
                    progressBar.pointStartColor = ContextCompat.getColor(fragment.requireContext(), R.color.radius_error)
                    progressBar.pointEndColor = ContextCompat.getColor(fragment.requireContext(), R.color.radius_error)
                }
            }
        }
        animator?.start()

    }

    fun destroy() {
        animator?.removeAllUpdateListeners()
        animator?.cancel()
    }
}
