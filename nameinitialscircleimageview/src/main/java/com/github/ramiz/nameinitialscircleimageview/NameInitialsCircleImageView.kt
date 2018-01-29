package com.github.ramiz.nameinitialscircleimageview

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.support.annotation.DimenRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.amulyakhare.textdrawable.TextDrawable
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
    private var mFillColor: Int

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
        mFillColor = ContextCompat.getColor(context, android.R.color.holo_blue_light)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        extractAttributes(attrs)
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

        setImageDrawable(createRoundTextDrawable())
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
                .buildRound(mText, mFillColor);
    }
}