package com.bashadroid.scan.barcodescanner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import android.widget.Toast
import com.bashadroid.lib.barcodescan.BarcodeReader

import com.bashadroid.scan.R
import com.google.android.gms.vision.barcode.Barcode

import timber.log.Timber

class ScannerActivity : AppCompatActivity(), BarcodeReader.BarcodeReaderListener {

    private var barcodeReader: BarcodeReader? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        barcodeReader = supportFragmentManager.findFragmentById(R.id.barcode_scanner) as BarcodeReader
        barcodeReader?.setListener(this)
    }

    override fun onScanned(barcode: Barcode) {
        Timber.d("onScanned: " + barcode.displayValue)
        barcodeReader?.playBeep()
        runOnUiThread { Toast.makeText(this@ScannerActivity, "Barcode: " + barcode.displayValue, Toast.LENGTH_SHORT).show() }
    }

   override fun onScannedMultiple(barcodes: List<Barcode>) {

    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>) {

    }

    override fun onScanError(errorMessage: String) {

    }

    override fun onCameraPermissionDenied() {
        Toast.makeText(this@ScannerActivity, "Camera permission denied!", Toast.LENGTH_LONG).show()

    }
}
