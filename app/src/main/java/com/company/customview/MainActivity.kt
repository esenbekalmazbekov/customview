package com.company.customview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.company.customview.add_a_floating_action_button.AddFloatingActionActivity
import com.company.customview.skillbrain.SkillbrainActivity
import com.company.customview.view_outline_provider.ViewOutlineProviderActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listeners()
    }

    private fun listeners() {
        add_a_floating_action_button.setOnClickListener { start<AddFloatingActionActivity>() }
        view_outline_provider.setOnClickListener { start<ViewOutlineProviderActivity>() }
        raywender.setOnClickListener { start<SkillbrainActivity>() }
    }

    private inline fun <reified T> start() {
        startActivity(Intent(this,T::class.java))
    }
}