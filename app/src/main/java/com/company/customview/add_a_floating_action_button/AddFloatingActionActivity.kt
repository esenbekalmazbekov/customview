package com.company.customview.add_a_floating_action_button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.company.customview.R
import kotlinx.android.synthetic.main.activity_add_floating_action.*

class AddFloatingActionActivity : AppCompatActivity() {
    private var isShow = true
    private var isExtended = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_floating_action)

        listeners()
    }

    private fun listeners() {
        show_hide.setOnClickListener {
            if (isShow) {
                isShow = false
                fab.hide()
            } else {
                isShow = true
                fab.show()
            }
        }

        extended_fab.setOnClickListener {
            if (isExtended) {
                isExtended = false
                extended_fab.shrink()
            }else {
                isExtended = true
                extended_fab.extend()
            }
        }
    }
}