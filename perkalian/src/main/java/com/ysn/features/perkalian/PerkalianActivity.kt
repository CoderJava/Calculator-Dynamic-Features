package com.ysn.features.perkalian

import android.os.Bundle
import com.ysn.calculatordynamicfeatures.base.BaseSplitActivity
import kotlinx.android.synthetic.main.activity_perkalian.*

class PerkalianActivity : BaseSplitActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perkalian)
        button_hitung_activity_perkalian.setOnClickListener {
            val bilangan1 = edit_text_bilangan_1_activity_perkalian.text.toString().toInt()
            val bilangan2 = edit_text_bilangan_2_activity_perkalian.text.toString().toInt()
            val result = bilangan1 * bilangan2
            text_view_result_activity_perkalian.text = result.toString()
        }
    }
}
