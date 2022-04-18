package academy.nouri.ezatpanahcourse.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun showToast2(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun RecyclerView.initRecyclerView(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = layoutManager
    this.adapter = adapter
    this.itemAnimator = DefaultItemAnimator()
    this.setHasFixedSize(true)
    this.fitsSystemWindows = true
}

fun Any.moneySeparating(): String {
    return DecimalFormat(",###.##").format(this)
}

fun View.enableDisableView(isEnabled: Boolean, alpha: Float) {
    if (isEnabled) {
        this.alpha = alpha
        this.isClickable = true
        this.isEnabled = true
    } else {
        this.alpha = alpha
        this.isClickable = false
        this.isEnabled = false
    }
}

fun View.showHide(isShown: Boolean) {
    if (isShown) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}