package com.lambdaschool.basilsintentsdemonstration

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val location:Location= intent.getSerializableExtra(ListActivity.EXTRA_STRING) as Location

        if (location.locationName!=getString(R.string.empty_location)) {
            edit_text.setText(location.locationName)
            check_box.isChecked=location.haveVisited
        }

        button_save.setOnClickListener {
            val intent = Intent()
            intent.putExtra(ListActivity.EXTRA_STRING, Location(edit_text.text.toString(),check_box.isChecked))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        button_delete.setOnClickListener {
            onBackPressed()
        }
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra(ListActivity.EXTRA_STRING, Location(getString(R.string.empty_location)))
        setResult(Activity.RESULT_CANCELED, intent)
        finish()
    }
}
