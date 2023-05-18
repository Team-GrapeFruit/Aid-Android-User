package com.grapefruit.aid_android_user.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.ActivitySeatSelectionBinding
import com.grapefruit.aid_android_user.presentation.viewmodel.SeatSelectionViewModel

class SeatSelectionActivity : AppCompatActivity() {

    lateinit var binding: ActivitySeatSelectionBinding
    private val viewModel by viewModels<SeatSelectionViewModel>()
    var storeId = 0L
    var seatId: Long = 0
    var seatState = 0
    private val tables = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_seat_selection)
        binding.activity = this

        storeId = intent.getLongExtra("storeId", 0)
        Log.d("testt_s",storeId.toString())
        viewModel.seatList(storeId)


        viewModel.seatListResponse.observe(this) {
            with(binding) {
                table.removeAllViews()
                tables.clear()
                for (i in 0..it.singleSeatResponse.lastIndex) {
                    val createTable = createTable(i)
                    tables.add(createTable)
                    table.addView(createTable)
                }
            }
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, QrScanActivity::class.java))
        }

        binding.seatSelectionBtn.setOnClickListener {
            if (seatId != 0L) {
                // 자리 선택하기
                if (seatState == 0) {
                    viewModel.allow(seatId, this@SeatSelectionActivity)
                    // 자리 선택취소하기
                } else {
                    viewModel.cancel(seatId, this@SeatSelectionActivity)
                }
            } else {
                Toast.makeText(this, "자리를 선택해주세요", Toast.LENGTH_SHORT).show()
            }

            startActivity(Intent(this, ChatActivity::class.java))
            intent.putExtra("storeId", storeId)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.seatList(storeId)
    }

    private fun createTable(index: Int): TextView {
        val table = TextView(this)
        val seatList = viewModel.seatListResponse.value?.singleSeatResponse?.get(index)!!
        table.width = widthSize(seatList.customerNum)
        table.height = heightSize(seatList.customerNum)
        table.x = seatList.locationX
        table.y = seatList.locationY
        table.text = getString(R.string.table, seatList.seatNum.toString(), seatList.customerNum.toString())
        table.setTextColor(
            ContextCompat.getColor(
                this@SeatSelectionActivity,
                textColor(seatList.enabled)
            )
        )
        table.gravity = Gravity.CENTER
        table.textSize = 16F
        table.typeface = ResourcesCompat.getFont(this, R.font.pretendard_variable)
        table.setBackgroundResource(background(seatList.enabled))
        table.id = ViewCompat.generateViewId()
        Log.d("table", "${table.id}")
        table.setOnClickListener {
            onClick(table, index)
        }
        return table
    }


    private fun widthSize(customerNum: Long): Int {
        return if (customerNum == 1L) 190
        else 400
    }

    private fun heightSize(customerNum: Long): Int {
        return when (customerNum) {
            1L -> 190
            2L -> 190
            4L -> 400
            6L -> 600
            8L -> 800
            else -> 0
        }
    }

    private fun textColor(enabled: Boolean): Int {
        return if (enabled == true) R.color.gray3
        else R.color.light_pink
    }

    private fun background(enabled: Boolean): Int {
        return if (enabled == true) R.drawable.seat_empty_background
        else R.drawable.seat_use_background
    }

    fun onClick(seat: TextView, index: Int) {
        val seatList = viewModel.seatListResponse.value?.singleSeatResponse?.get(index)!!
        val drawableAConstantState =
            ContextCompat.getDrawable(this, R.drawable.seat_selection_background)?.constantState

        if (seat.background.constantState != drawableAConstantState) {
            // 자리 사용 가능
            if (seatList.enabled == true) {
                seatState = 0
            // 자리 사용 불가능
            } else {
                seatState = 1
            }

            for (i in tables.indices) {
                val table2 = tables[i]
                val seatList2 = viewModel.seatListResponse.value?.singleSeatResponse?.get(i)!!

                if (i != index) {
                    table2.setBackgroundResource(background(seatList2.enabled))
                    table2.setTextColor(ContextCompat.getColor(this@SeatSelectionActivity, textColor(seatList2.enabled)))
                } else {
                    table2.setTextColor(ContextCompat.getColor(this@SeatSelectionActivity, R.color.navy))
                    table2.setBackgroundResource(R.drawable.seat_selection_background)
                }
            }

            seatId = seatList.seatId
        } else {
            // 자리 사용 가능
            if (seatList.enabled == true) {
                seat.setTextColor(ContextCompat.getColor(this, R.color.gray3))
                seat.setBackgroundResource(R.drawable.seat_empty_background)

                seatId = 0
                seatState = 0
            // 자리 사용 불가능
            } else {
                seat.setTextColor(ContextCompat.getColor(this, R.color.light_pink))
                seat.setBackgroundResource(R.drawable.seat_use_background)

                seatId = 0
                seatState = 1
            }
        }
    }
}