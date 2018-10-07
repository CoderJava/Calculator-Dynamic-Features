package com.ysn.calculatordynamicfeatures.ui.main

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.ysn.calculatordynamicfeatures.R
import com.ysn.calculatordynamicfeatures.ui.pengurangan.PenguranganActivity
import com.ysn.calculatordynamicfeatures.ui.penjumlahan.PenjumlahanActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var packageNameModule = ""
    private var dynamicModuleClassName = ""

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }
    private lateinit var manager: SplitInstallManager
    private val listener = SplitInstallStateUpdatedListener { state ->
        val multiInstall = state.moduleNames().size > 1
        state.moduleNames().forEach { name ->
            when (state.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    Log.d(TAG, "DOWNLOADING")
                    updateLoadingState(state, "Downloading $name")
                }
                SplitInstallSessionStatus.INSTALLING -> {
                    Log.d(TAG, "INSTALLING")
                    updateLoadingState(state, "Installing $name")
                }
                SplitInstallSessionStatus.PENDING -> {
                    Log.d(TAG, "PENDING")
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    Log.d(TAG, "INSTALLED")
                    if (progressDialog.isShowing) {
                        showAlertDialog("Another feature successfully installed")
                        progressDialog.dismiss()
                        progressDialog.progress = 0
                        progressDialog.max = 100
                    }
                }
                SplitInstallSessionStatus.FAILED -> {
                    Log.d(TAG, "FAILED")
                    showAlertDialog("Another feature failed installed")
                    progressDialog.dismiss()
                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    Log.d(TAG, "REQUIRES USER CONFIRMATION")
                }
                SplitInstallSessionStatus.CANCELING -> {
                    Log.d(TAG, "CANCELING")
                }
                SplitInstallSessionStatus.CANCELED -> {
                    Log.d(TAG, "CANCELED")
                }
                else -> {
                    Log.d(TAG, "ELSE ${state.status()}")
                }
            }
        }
    }

    private fun showAlertDialog(message: String) {
        AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("Ok") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .show()
    }

    private fun updateLoadingState(state: SplitInstallSessionState?, message: String) {
        progressDialog.max = state!!.totalBytesToDownload().toInt()
        progressDialog.progress = state.bytesDownloaded().toInt()
        progressDialog.setTitle(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = SplitInstallManagerFactory.create(this)
        packageNameModule = packageName
        initListeners()
    }

    override fun onResume() {
        manager.registerListener(listener)
        super.onResume()
    }

    override fun onPause() {
        manager.unregisterListener(listener)
        super.onPause()
    }

    private fun initListeners() {
        button_penjumlahan_activity_main.setOnClickListener(this)
        button_pengurangan_activity_main.setOnClickListener(this)
        button_perkalian_activity_main.setOnClickListener(this)
        button_pembagian_activity_main.setOnClickListener(this)
        button_install_features_activity_main.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.let { it ->
            when (it.id) {
                R.id.button_penjumlahan_activity_main -> {
                    startActivity(Intent(this, PenjumlahanActivity::class.java))
                }
                R.id.button_pengurangan_activity_main -> {
                    startActivity(Intent(this, PenguranganActivity::class.java))
                }
                R.id.button_perkalian_activity_main -> {
                    dynamicModuleClassName = "com.ysn.features.perkalian.PerkalianActivity"
                    Intent().setClassName(packageNameModule, dynamicModuleClassName).also {
                        startActivity(it)
                    }
                }
                R.id.button_pembagian_activity_main -> {
                    dynamicModuleClassName = "com.ysn.features.pembagian.PembagianActivity"
                    Intent().setClassName(packageNameModule, dynamicModuleClassName).also {
                        startActivity(it)
                    }
                }
                R.id.button_install_features_activity_main -> {
                    var isModulePerkalianInstalled = false
                    var isModulePembagianInstalled = false
                    manager.installedModules.toList().forEach {
                        if (it.equals("perkalian", true)) {
                            isModulePerkalianInstalled = true
                        } else if (it.equals("pembagian", true)) {
                            isModulePembagianInstalled = true
                        }
                    }
                    if (!isModulePerkalianInstalled && !isModulePembagianInstalled) {
                        val request = SplitInstallRequest.newBuilder()
                                .addModule("perkalian")
                                .addModule("pembagian")
                                .build()
                        manager.startInstall(request).addOnSuccessListener {
                            progressDialog.isIndeterminate = false
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                            progressDialog.setCancelable(false)
                            progressDialog.show()
                        }
                    } else {
                        showAlertDialog("Another module already installed")
                    }
                }
                else -> {
                    /* nothing to do in here */
                }
            }
        }
    }
}
