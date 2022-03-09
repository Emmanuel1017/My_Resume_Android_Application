

package com.emmanuel.emmanuelkorircv

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.Px

/** HighlightView is a view with stroke highlighting via onClickListener. */
class HighlightView(
  context: Context,
  attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

  private val bodyView = LinearLayout(context)
  private val strokeView = View(context)

  var highlighting: Boolean by highlightViewProperty(false)

  @get:Px
  var highlightThickness: Int by highlightViewProperty(dp2Px(0))

  @get:ColorInt
  var highlightColor: Int by highlightViewProperty(accentColor())

  @get:FloatRange(from = 0.0, to = 1.0)
  var highlightAlpha: Float by highlightViewProperty(1.0f)

  var radius: Float by highlightViewProperty(dp2Px(5).toFloat())

  var radiusArray: FloatArray? by highlightViewProperty(null)

  @get:Px
  var padding: Int by highlightViewProperty(dp2Px(0))

  @get:ColorInt
  var color: Int by highlightViewProperty(accentColor())

  @get:ColorInt
  var colorGradientStart: Int by highlightViewProperty(NO_COLOR)

  @get:ColorInt
  var colorGradientCenter: Int by highlightViewProperty(NO_COLOR)

  @get:ColorInt
  var colorGradientEnd: Int by highlightViewProperty(NO_COLOR)

  var highlight: Drawable? by highlightViewProperty(null)

  var orientation: ProgressViewOrientation by highlightViewProperty(ProgressViewOrientation.HORIZONTAL)

  var onProgressClickListener: OnProgressClickListener? = null

  init {
    addView(bodyView)
    addView(strokeView)
    strokeView.setOnClickListener {
      highlighting = highlighting.not()
      onProgressClickListener?.onClickProgress(highlighting)
    }
  }

  fun updateHighlightView() {
    updateBodyView()
    updateStrokeView()
    updateHighlighting()
  }

  private fun updateBodyView() {
    bodyView.background = if (colorGradientStart != NO_COLOR && colorGradientEnd != NO_COLOR) {
      var gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT
      if (orientation == ProgressViewOrientation.VERTICAL) {
        gradientOrientation = GradientDrawable.Orientation.TOP_BOTTOM
      }
      GradientDrawable(
        gradientOrientation,
        intArrayOf(colorGradientStart, colorGradientCenter, colorGradientEnd)
          .filter { it != NO_COLOR }.toIntArray()
      ).apply {
        applyRadius(this)
      }
    } else if (highlight == null) {
      GradientDrawable().apply {
        setColor(this@HighlightView.color)
        applyRadius(this)
      }
    } else {
      highlight
    }
    bodyView.applyMargin()
  }

  private fun updateStrokeView() {
    strokeView.background = GradientDrawable().apply {
      setColor(Color.TRANSPARENT)
      setStroke(highlightThickness, highlightColor)
      applyRadius(this)
    }
    strokeView.applyMargin()
  }

  private fun updateHighlighting() {
    if (highlighting) {
      strokeView.alpha = highlightAlpha
    } else {
      strokeView.alpha = 0f
    }
  }

  private fun View.applyMargin() {
    (layoutParams as MarginLayoutParams).setMargins(padding, padding, padding, padding)
  }

  private fun applyRadius(gradientDrawable: GradientDrawable) {
    if (radiusArray != null) {
      gradientDrawable.cornerRadii = radiusArray
    } else {
      gradientDrawable.cornerRadius = radius
    }
  }
}
