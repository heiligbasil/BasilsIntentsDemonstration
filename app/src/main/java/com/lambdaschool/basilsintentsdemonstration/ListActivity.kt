package com.lambdaschool.basilsintentsdemonstration

import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_STRING: String = "data"
        val RESULT_INT: Int = 54321
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        text_view_sample_1.setOnClickListener {
            intentGenerator(Location(text_view_sample_1.text.toString(), text_view_sample_1.background != null))
            linear_layout.removeView(it)
        }

        text_view_sample_2.setOnClickListener {
            intentGenerator(Location(text_view_sample_2.text.toString(), text_view_sample_2.background != null))
            linear_layout.removeView(it)
        }

        text_view_sample_3.setOnClickListener {
            intentGenerator(Location(text_view_sample_3.text.toString(), text_view_sample_3.background != null))
            linear_layout.removeView(it)
        }

        button_add_location.setOnClickListener {
            intentGenerator(Location(getString(R.string.empty_location)))
        }
    }

    private fun intentGenerator(location: Location) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra(EXTRA_STRING, location)
        startActivityForResult(intent, RESULT_INT)
    }

    /**
     * Dispatch incoming result to the correct fragment.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == RESULT_INT && resultCode == Activity.RESULT_OK) {
            val location: Location = data?.getSerializableExtra(ListActivity.EXTRA_STRING) as Location

            val textView: TextView = textViewGenerator(location)
            linear_layout.addView(textView)

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun textViewGenerator(location: Location): TextView {
        val textView: TextView = TextView(this)
        textView.textSize = 30f
        textView.text = location.locationName
        if (location.haveVisited) textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
        textView.setOnClickListener {
            intentGenerator(location)
            linear_layout.removeView(it)
        }

        return textView
    }

    /*<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Grand Teton National Park"
        android:textSize="30sp" />

        <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@drawable/strike_through"
        android:text="Empire State Building"
        android:textSize="30sp" />

        <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Mount Rainier National Park"
        android:textSize="30sp" />

        "Grand Teton National Park"
        "Empire State Building"
        "Mount Rainier National Park"*/

}
