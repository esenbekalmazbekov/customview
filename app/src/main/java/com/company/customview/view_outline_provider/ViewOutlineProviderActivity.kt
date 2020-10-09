package com.company.customview.view_outline_provider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewOutlineProvider
import com.company.customview.R
import kotlinx.android.synthetic.main.activity_view_outline_provider.*

class ViewOutlineProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_outline_provider)

        outline_paddedBounds.setOnClickListener {
        }

    }
}