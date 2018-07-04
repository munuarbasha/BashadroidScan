package com.bashadroid.scan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.bashadroid.scan.barcodescanner.ScannerActivity
import com.bashadroid.scan.R
import com.google.android.gms.vision.CameraSource




class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val PERMISSION_REQUESTS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scanQR.setOnClickListener {
            if (!allPermissionsGranted()) {
                getRuntimePermissions()
            }else{
                var intent =  Intent(this, ScannerActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getRequiredPermissions(): Array<String?> {
        try {
            val info = this.packageManager
                    .getPackageInfo(this.packageName, PackageManager.GET_PERMISSIONS)
            val ps = info.requestedPermissions
            return if (ps != null && ps.size > 0) {
                ps
            } else {
                arrayOfNulls(0)
            }
        } catch (e: Exception) {
            return arrayOfNulls(0)
        }

    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in getRequiredPermissions()) {
            if (!isPermissionGranted(this, permission.toString())) {
                return false
            }
        }
        return true
    }

    private fun getRuntimePermissions() {
        val allNeededPermissions = ArrayList<String>()
        for (permission in getRequiredPermissions()) {
            if (!isPermissionGranted(this, permission.toString())) {
                allNeededPermissions.add(permission.toString())
            }
        }

        if (!allNeededPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this, allNeededPermissions.toTypedArray(), PERMISSION_REQUESTS)
        }
    }

    private fun isPermissionGranted(context: Context, permission: String): Boolean {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission granted: $permission")
            return true
        }
        Log.i(TAG, "Permission NOT granted: $permission")
        return false
    }
}
