

@file:Suppress("unused")

package com.emmanuel.emmanuelkorircv

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

@DslMarker
internal annotation class TextFormDsl

/** creates an instance of [TextForm] from [TextForm.Builder] using kotlin dsl. */
@TextFormDsl
@JvmSynthetic
inline fun textForm(
  context: Context,
  crossinline block: TextForm.Builder.() -> Unit
): TextForm =
  TextForm.Builder(context).apply(block).build()

/**
 * TextFrom is an attribute class what has some attributes about TextView
 * for customizing popup texts easily.
 */
class TextForm(builder: Builder) {

  val text: CharSequence? = builder.text
  @Px
  val textSize: Float = builder.textSize
  @ColorInt
  val textColor: Int = builder.textColor
  val textStyle: Int = builder.textTypeface
  val textStyleObject: Typeface? = builder.textTypefaceObject

  /** Builder class for [TextForm]. */
  @TextFormDsl
  class Builder(private val context: Context) {
    @JvmField
    @set:JvmSynthetic
    var text: CharSequence? = ""

    @JvmField @Px
    @set:JvmSynthetic
    var textSize: Float = 12f

    @JvmField @ColorInt
    @set:JvmSynthetic
    var textColor: Int = Color.WHITE

    @JvmField
    @set:JvmSynthetic
    var textTypeface: Int = Typeface.NORMAL

    @JvmField
    @set:JvmSynthetic
    var textTypefaceObject: Typeface? = null

    fun setText(value: CharSequence): Builder = apply { this.text = value }
    fun setTextResource(@StringRes value: Int): Builder = apply {
      this.text = context.getString(value)
    }

    fun setTextSize(@Px value: Float): Builder = apply { this.textSize = value }
    fun setTextColor(@ColorInt value: Int): Builder = apply { this.textColor = value }
    fun setTextColorResource(@ColorRes value: Int): Builder = apply {
      this.textColor = ContextCompat.getColor(context, value)
    }

    fun setTextTypeface(value: Int): Builder = apply { this.textTypeface = value }
    fun setTextTypeface(value: Typeface): Builder = apply { this.textTypefaceObject = value }
    fun build(): TextForm = TextForm(this)
  }
}
