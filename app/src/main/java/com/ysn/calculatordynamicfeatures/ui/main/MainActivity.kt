package com.ysn.calculatordynamicfeatures.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ysn.calculatordynamicfeatures.R
import com.ysn.calculatordynamicfeatures.ui.pengurangan.PenguranganActivity
import com.ysn.calculatordynamicfeatures.ui.penjumlahan.PenjumlahanActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    private fun initListeners() {
        button_penjumlahan_activity_main.setOnClickListener(this)
        button_pengurangan_activity_main.setOnClickListener(this)
        button_perkalian_activity_main.setOnClickListener(this)
        button_pembagian_activity_main.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                R.id.button_penjumlahan_activity_main -> {
                    startActivity(Intent(this, PenjumlahanActivity::class.java))
                }
                R.id.button_pengurangan_activity_main -> {
                    startActivity(Intent(this, PenguranganActivity::class.java))
                }
                R.id.button_perkalian_activity_main -> {
                    // TODO: do something in here
                }
                R.id.button_pembagian_activity_main -> {
                    // TODO: do something in here
                }
                else -> {
                    /* nothing to do in here */
                }
            }
        }
    }
}
