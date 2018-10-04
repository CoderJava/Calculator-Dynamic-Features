package com.ysn.calculatordynamicfeatures.ui.pengurangan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ysn.calculatordynamicfeatures.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pengurangan.*

class PenguranganActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengurangan)
        button_hitung_activity_pengurangan.setOnClickListener {
            val bilangan1 = edit_text_bilangan_1_activity_pengurangan.text.toString().toInt()
            val bilangan2 = edit_text_bilangan_2_activity_pengurangan.text.toString().toInt()
            val result = bilangan1 - bilangan2
            text_view_result_activity_pengurangan.text = result.toString()
        }
    }
}
