

package com.emmanuel.emmanuelkorircv

import android.util.TypedValue
import android.widget.TextView

/** applies text form attributes to a TextView instance. */
@JvmSynthetic
internal fun TextView.applyTextForm(textForm: TextForm) {
  text = textForm.text
  setTextSize(TypedValue.COMPLEX_UNIT_SP, textForm.textSize)
  setTextColor(textForm.textColor)
  textForm.textStyleObject?.let {
    typeface = textForm.textStyleObject
  } ?: setTypeface(typeface, textForm.textStyle)
}
