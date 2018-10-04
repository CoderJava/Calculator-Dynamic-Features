package com.ysn.calculatordynamicfeatures.ui.penjumlahan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.ysn.calculatordynamicfeatures.R
import kotlinx.android.synthetic.main.activity_penjumlahan.*

class PenjumlahanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penjumlahan)
        button_hitung_activity_penjumlahan.setOnClickListener {
            val bilangan1 = edit_text_bilangan_1_activity_penjumlahan.text.toString().toInt()
            val bilangan2 = edit_text_bilangan_2_activity_penjumlahan.text.toString().toInt()
            val result = bilangan1 + bilangan2
            text_view_result_activity_penjumlahan.text = result.toString()
        }
    }
}
