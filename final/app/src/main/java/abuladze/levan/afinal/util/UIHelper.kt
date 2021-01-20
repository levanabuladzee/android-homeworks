package abuladze.levan.afinal.util

import abuladze.levan.afinal.R
import android.widget.TextView
import androidx.core.content.ContextCompat

object UIHelper {

    fun showChangePercent(textView: TextView, _change: Double?) {
        var changeValue = _change

        if (_change == null) {
           changeValue = 0.00
        }

        val changePercent = "%.2f%%".format(changeValue).replace(Regex("[()]"), "")

        textView.text = changePercent
        val context = textView.context
        if (changeValue == 0.00) {
            textView.setTextColor(
                    ContextCompat.getColor(context, R.color.default_text_color)
            )
            return
        } else {
            if (changePercent.contains("-")) {
                textView.setTextColor(
                        ContextCompat.getColor(context, R.color.red)
                )
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        null, null,
                        ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_downward_24),
                        null
                )
            } else {
                textView.setTextColor(
                        ContextCompat.getColor(context, R.color.green)
                )
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        null, null,
                        ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_upward_24),
                        null
                )
            }
        }
    }

}