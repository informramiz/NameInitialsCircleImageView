package com.github.ramiz.nameinitialscircleimageview

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.amulyakhare.textdrawable.TextDrawable
import com.github.ramiz.nameinitialscircleimageview.common.LogUtils
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by ramiz on 1/29/18.
 */

class NameInitialsCircleImageView : CircleImageView {
    private val TAG = javaClass.simpleName
    @DimenRes
    private val DEFAULT_TEXT_SIZE_SP = R.dimen.default_text_size //sp
    @DimenRes
    private val DEFAULT_WIDTH_DP = R.dimen.default_width
    @DimenRes
    private val DEFAULT_HEIGHT_DP = R.dimen.default_height
    private val DEFAULT_FONT = Typeface.DEFAULT;

    private var mText: String
    private var mTextSizePixels: Int
    @ColorInt
    private var mTextColor: Int
    private var mWidthPixels: Int
    private var mHeightPixels: Int
    private var mTypeface: Typeface
    @ColorInt
    private var mCircleBackgroundColor: Int


    constructor(context: Context?) : super(context) {
        init(null)
    }

    init {
        mText = ""
        mTextSizePixels = context.resources.getDimensionPixelSize(DEFAULT_TEXT_SIZE_SP)
        mTextColor = ContextCompat.getColor(context, android.R.color.white)
        mWidthPixels = context.resources.getDimensionPixelSize(DEFAULT_WIDTH_DP)
        mHeightPixels = context.resources.getDimensionPixelSize(DEFAULT_HEIGHT_DP)
        mTypeface = DEFAULT_FONT
        mCircleBackgroundColor = ContextCompat.getColor(context, android.R.color.holo_blue_light)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        extractAttributes(attrs)
        updateImageDrawable()
    }

    private fun updateImageDrawable() {
        setImageDrawable(createRoundTextDrawable())
    }

    private fun extractAttributes(attrs: AttributeSet?) {
        attrs ?: return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NameInitialsCircleImageView)
        try {
            mText = typedArray.getString(R.styleable.NameInitialsCircleImageView_text) ?: ""
            mTextSizePixels = typedArray.getDimensionPixelSize(R.styleable.NameInitialsCircleImageView_textSize,
                    context.resources.getDimensionPixelSize(DEFAULT_TEXT_SIZE_SP))
            val customFontName = typedArray.getString(R.styleable.NameInitialsCircleImageView_textFont)
            mTypeface = loadFont(customFontName)
        } finally {
            typedArray.recycle();
        }
    }

    private fun loadFont(fontName: String?): Typeface {
        fontName?: return DEFAULT_FONT
        if (fontName.isEmpty()) return DEFAULT_FONT

        return Typeface.createFromAsset(context.assets, fontName)
    }

    private fun createRoundTextDrawable(): TextDrawable {
        return TextDrawable.builder()
                .beginConfig()
                .textColor(mTextColor)
                .fontSize(mTextSizePixels)
                .useFont(mTypeface)
                .width(mWidthPixels)
                .height(mHeightPixels)
                .endConfig()
                .buildRound(mText, mCircleBackgroundColor);
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        LogUtils.i(TAG, "Measured (width, height)=($w, $h)")
        mWidthPixels = w - paddingLeft - paddingRight
        mHeightPixels = h - paddingTop - paddingBottom
        updateImageDrawable()
    }

    fun setText(text: String) {
        mText = text
        updateImageDrawable()
    }

    fun getText(): String {
        return mText
    }

    /**
     * Sets the background color of the circle
     *
     * @deprecated Use {@link #setCircleBackgroundColor()} instead
     */
    @Deprecated("Use setCircleBackgroundColor() instead", ReplaceWith("this.circleBackgroundColor = color"))
    override fun setFillColor(@ColorInt color: Int) {
        this.circleBackgroundColor = color
    }

    /**
     * Sets the background color of the circle from a color resource
     *
     * @deprecated Use {@link #setCircleBackgroundColorResource()} instead
     */
    @Deprecated("Use setCircleBackgroundColorResource() instead", ReplaceWith("this.setCircleBackgroundColorResource(fillColorRes)"))
    override fun setFillColorResource(@ColorRes fillColorRes: Int) {
        this.setCircleBackgroundColorResource(fillColorRes)
    }

    /**
     * Gets the color of the circular drawable
     * @return color value of the circular drawable background color
     * @deprecated Use {@link #get
     */
    override fun getFillColor(): Int {
        return this.circleBackgroundColor;
    }

    /**
     * Sets the background color of the circle from a color resource
     */
    override fun setCircleBackgroundColorResource(@ColorRes circleBackgroundColor: Int) {
        mCircleBackgroundColor = ContextCompat.getColor(context, circleBackgroundColor)
        updateImageDrawable()
    }

    /**
     * Sets the background color of the circle
     */
    override fun setCircleBackgroundColor(@ColorInt circleBackgroundColor: Int) {
        mCircleBackgroundColor = circleBackgroundColor
        updateImageDrawable()
    }

    override fun getCircleBackgroundColor(): Int {
        return mCircleBackgroundColor;
    }
}