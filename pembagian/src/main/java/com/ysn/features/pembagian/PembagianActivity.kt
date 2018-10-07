package com.ysn.features.pembagian

import android.os.Bundle
import com.ysn.calculatordynamicfeatures.base.BaseSplitActivity
import kotlinx.android.synthetic.main.activity_pembagian.*

class PembagianActivity : BaseSplitActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembagian)
        button_hitung_activity_pembagian.setOnClickListener {
            val bilangan1 = edit_text_bilangan_1_activity_pembagian.text.toString().toInt()
            val bilangan2 = edit_text_bilangan_2_activity_pembagian.text.toString().toInt()
            val result = bilangan1 / bilangan2
            text_view_result_activity_pembagian.text = result.toString()
        }
    }
}
