package com.example.datetimepickerapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // todo: Simple date picker code
        val simpleDateBtn = findViewById<Button>(R.id.simpleDatePickerBtn)
        val cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                dateFormat(cal.time)
            }
        simpleDateBtn.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
            ).show()
        }
        // todo: Simple date picker code

        // todo: Material date picker code
        val materialDateBtn = findViewById<Button>(R.id.materialDatePickerBtn)
        val materialDatePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()
        materialDatePicker.addOnPositiveButtonClickListener {
            dateFormat(materialDatePicker.selection!!)
        }

        materialDateBtn.setOnClickListener {
            materialDatePicker.show(supportFragmentManager, "material_date_picker")
        }
        // todo: Material date picker code

        // todo: Simple time picker code
        val simpleTimePickBtn = findViewById<Button>(R.id.simpleTimePickerBtn)

        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val simpleTimePicker = TimePickerDialog(
            this, { _, hourOfDay, min ->
                timeFormat(hourOfDay, min)
            }, hour, minute, false
        )

        simpleTimePickBtn.setOnClickListener {
            simpleTimePicker.show()
        }
        // todo: Simple time picker code


        // todo: Material time picker code
        val materialTimePickBtn = findViewById<Button>(R.id.materialTimePickerBtn)
        val materialTimePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setTitleText("Select Time")
                .setHour(hour)
                .setMinute(minute)
                .build()
        materialTimePicker.addOnPositiveButtonClickListener {
            timeFormat(materialTimePicker.hour, materialTimePicker.minute)
        }
        materialTimePickBtn.setOnClickListener {
            materialTimePicker.show(supportFragmentManager, "material_time_picker")
        }
        // todo: Material time picker code
    }

    private fun timeFormat(hour: Int, minute: Int) {
        val modifiedHour = getHourAmPm(hour)
        val amPm = if (hour > 11) "PM" else "AM"
        val numberFormat = DecimalFormat("00")

        val timeTxt = findViewById<TextView>(R.id.timeTxt)
        timeTxt.text = "${numberFormat.format(modifiedHour)}:${numberFormat.format(minute)} $amPm"
    }

    private fun getHourAmPm(hour: Int): Int {
        // return the hour value for AM PM time Format
        var modifiedHour = if (hour > 11) hour - 12 else hour
        if (modifiedHour == 0) {
            modifiedHour = 12
        }
        return modifiedHour
    }


    private fun dateFormat(date: Any) {
        val format1 = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(date)
        val format2 = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(date)
        val format3 = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(date)
        val format4 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
        val format5 = SimpleDateFormat("MMM-dd-yyyy", Locale.getDefault()).format(date)
        val format6 = SimpleDateFormat("MMM/dd/yyyy", Locale.getDefault()).format(date)
        val format7 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
        val format8 = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(date)

        val dateTxt = findViewById<TextView>(R.id.dateTxt)
        dateTxt.text = format1 + "\n" +
                format2 + "\n" +
                format3 + "\n" +
                format4 + "\n" +
                format5 + "\n" +
                format6 + "\n" +
                format7 + "\n" +
                format8 + "\n"
    }


}