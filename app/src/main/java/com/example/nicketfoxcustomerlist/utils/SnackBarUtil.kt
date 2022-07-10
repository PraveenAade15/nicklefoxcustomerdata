package com.example.nicketfoxcustomerlist.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.nicketfoxcustomerlist.R
import com.google.android.material.snackbar.Snackbar

class SnackBarUtil(var mContext: Context, var mView: View?, var mStirng: String) {
    protected fun displaySnackBar() {
        if (mView != null) {
            val snackbar = Snackbar.make(mView!!, mStirng, Snackbar.LENGTH_SHORT)
            val snackBarView = snackbar.view
            snackBarView.setBackgroundColor(mContext.resources.getColor(R.color.black))
            snackbar.setActionTextColor(Color.GREEN)
            val layout = snackbar.view as Snackbar.SnackbarLayout
            layout.setPadding(0, 0, 0, 0) //set padding to 0
            val textView =
                snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
            //textView.setSingleLine(true);
            textView.setTextColor(mContext.resources.getColor(R.color.white))
            snackbar.show()
            snackbar.setAction("Ok") { snackbar.dismiss() }
            snackbar.show()
        }
    }

    init {
        displaySnackBar()
    }
}