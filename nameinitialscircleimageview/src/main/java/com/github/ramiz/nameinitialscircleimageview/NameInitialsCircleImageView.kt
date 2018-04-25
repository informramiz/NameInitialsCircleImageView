package com.github.ramiz.nameinitialscircleimageview

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.FontRes
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import com.amulyakhare.textdrawable.TextDrawable
import com.github.ramiz.nameinitialscircleimageview.common.LogUtils
import com.github.ramiz.nameinitialscircleimageview.common.imagedownloader.ImageDownloaderSingleton
import de.hdodenhof.circleimageview.CircleImageView

/**
 * A circle ImageView that can show circular image with letters
 * and circle images
 * Created by ramiz on 1/29/18.
 */

class NameInitialsCircleImageView : CircleImageView {
    private val TAG = javaClass.simpleName
    companion object {
        @DimenRes
        private val DEFAULT_TEXT_SIZE_SP = R.dimen.default_text_size //sp
        @ColorRes
        private val DEFAULT_TEXT_COLOR = android.R.color.white
        @DimenRes
        private val DEFAULT_WIDTH_DP = R.dimen.default_width
        @DimenRes
        private val DEFAULT_HEIGHT_DP = R.dimen.default_height
        private val DEFAULT_FONT = Typeface.DEFAULT
        @ColorRes
        private val DEFAULLT_CIRCLE_BACKGROUND_COLOR = android.R.color.holo_blue_light
        private val DEFAULT_COLOR_GENERATOR = MaterialColorGenerator()
    }

    private var mTextSizePixels: Int
    private var mWidthPixels: Int
    private var mHeightPixels: Int

    private var mText: String
    private var mTypeface: Typeface
    @ColorInt
    private var mTextColor: Int
    @ColorInt
    private var mCircleBackgroundColor: Int
    private var mImageUrl: String? = null
    private var mColorGenerator: ColorGenerator

    constructor(context: Context?) : super(context) {
        init(null)
    }

    init {
        mText = "RR"
        mTextSizePixels = context.resources.getDimensionPixelSize(DEFAULT_TEXT_SIZE_SP)
        mTextColor = ContextCompat.getColor(context, DEFAULT_TEXT_COLOR)
        mWidthPixels = context.resources.getDimensionPixelSize(DEFAULT_WIDTH_DP)
        mHeightPixels = context.resources.getDimensionPixelSize(DEFAULT_HEIGHT_DP)
        mTypeface = DEFAULT_FONT
        mColorGenerator = DEFAULT_COLOR_GENERATOR
//        mCircleBackgroundColor = ContextCompat.getColor(context, DEFAULLT_CIRCLE_BACKGROUND_COLOR)
        mCircleBackgroundColor = mColorGenerator.generateColor(mText)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        extractAttributes(attrs)
        updateImageDrawable()
    }

    private fun updateImageDrawable() {
        val url = mImageUrl
        val textDrawable = createRoundTextDrawable()
        if (url == null || url.isEmpty()) {
            setImageDrawable(textDrawable)
        } else {
            ImageDownloaderSingleton.getImageDownloader(context).downloadImage(context, url, this, textDrawable);
        }
    }

    private fun extractAttributes(attrs: AttributeSet?) {
        attrs ?: return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NameInitialsCircleImageView)
        try {
            mText = typedArray.getString(R.styleable.NameInitialsCircleImageView_text) ?: ""
            mTextSizePixels = typedArray.getDimensionPixelSize(R.styleable.NameInitialsCircleImageView_textSize,
                    context.resources.getDimensionPixelSize(DEFAULT_TEXT_SIZE_SP))

            mCircleBackgroundColor = typedArray.getColor(R.styleable.NameInitialsCircleImageView_circleBackgroundColor,
                    mColorGenerator.generateColor(mText))
            //check if there is font id specified by the developer
            if (typedArray.hasValue(R.styleable.NameInitialsCircleImageView_textFont)) {
                //fetch the resource id and load the font
                @FontRes val fontResId  = typedArray.getResourceId(R.styleable.NameInitialsCircleImageView_textFont, -1)
                val typeface = ResourcesCompat.getFont(context, fontResId)
                if (typeface != null) {
                    mTypeface = typeface
                }
            }
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
        mWidthPixels = w - paddingLeft - paddingRight
        mHeightPixels = h - paddingTop - paddingBottom
        updateImageDrawable()
    }

    /**
     * Sets the background color of the circle
     *
     * @deprecated Use {@link #setCircleBackgroundColor()} instead
     */
    @Deprecated("Use setImageInfo() instead", ReplaceWith("this.setImageInfo(imageInfo)"))
    override fun setFillColor(@ColorInt color: Int) {
        this.circleBackgroundColor = color
    }

    /**
     * Sets the background color of the circle from a color resource
     *
     * @deprecated Use {@link #setCircleBackgroundColorResource()} instead
     */
    @Deprecated("Use setImageInfo() instead", ReplaceWith("this.setImageInfo(imageInfo)"))
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
     *
     */
    @Deprecated("Use setImageInfo() instead", ReplaceWith("this.setImageInfo(imageInfo)"))
    override fun setCircleBackgroundColorResource(@ColorRes circleBackgroundColor: Int) {
        mCircleBackgroundColor = ContextCompat.getColor(context, circleBackgroundColor)
        updateImageDrawable()
    }

    /**
     * Sets the background color of the circle
     */
    @Deprecated("Use setImageInfo() instead", ReplaceWith("this.setImageInfo(imageInfo)"))
    override fun setCircleBackgroundColor(@ColorInt circleBackgroundColor: Int) {
        mCircleBackgroundColor = circleBackgroundColor
        updateImageDrawable()
    }

    override fun getCircleBackgroundColor(): Int {
        return mCircleBackgroundColor
    }

    fun setImageInfo(imageInfo: ImageInfo) {
        this.mText = imageInfo.text
        this.mTextColor = ContextCompat.getColor(context, imageInfo.textColorRes)
        this.mImageUrl = imageInfo.imageUrl
        //load font if specified
        if (imageInfo.fontResId != null) {
            val typeface = ResourcesCompat.getFont(context, imageInfo.fontResId)
            if (typeface != null) {
                mTypeface = typeface;
            }
        }

        this.mColorGenerator = imageInfo.colorGenerator
        //load or generate color
        if (imageInfo.circleBackgroundColorRes != null) {
            LogUtils.i(TAG, "background color is not null")
            this.mCircleBackgroundColor = ContextCompat.getColor(context, imageInfo.circleBackgroundColorRes)
        } else{
            this.mCircleBackgroundColor = mColorGenerator.generateColor(mText)
        }

        if (imageInfo.invalidateImageCache && imageInfo.imageUrl != null && !imageInfo.imageUrl.isEmpty()) {
            ImageDownloaderSingleton.getImageDownloader(context).invalidateImage(context, imageInfo.imageUrl)
        }

        updateImageDrawable()
    }

    class ImageInfo(builder: Builder) {
        internal val text: String
        @FontRes
        internal val fontResId: Int?
        @ColorRes
        internal val textColorRes: Int
        @ColorRes
        internal val circleBackgroundColorRes: Int?
        internal val imageUrl: String?
        internal val colorGenerator: ColorGenerator
        internal val invalidateImageCache: Boolean

        init {
            this.text = builder.text
            this.fontResId = builder.fontResId
            this.textColorRes = builder.textColorRes
            this.circleBackgroundColorRes = builder.circleBackgroundColorRes
            this.imageUrl = builder.imageUrl
            this.colorGenerator = builder.colorGenerator
            this.invalidateImageCache = builder.invalidateImageCache
        }

        class Builder(internal var text: String) {
            @FontRes
            internal var fontResId: Int? = null
            @ColorRes
            internal var textColorRes: Int = NameInitialsCircleImageView.DEFAULT_TEXT_COLOR
            @ColorRes
            internal var circleBackgroundColorRes: Int? = null
            internal var colorGenerator: ColorGenerator = NameInitialsCircleImageView.DEFAULT_COLOR_GENERATOR
            internal var imageUrl: String? = null
            internal var invalidateImageCache: Boolean = false

            fun setText(text: String): Builder {
                this.text = text
                return this
            }

            fun setTextFont(@FontRes fontResId: Int?): Builder {
                this.fontResId = fontResId
                return this
            }

            fun setTextColor(@ColorRes textColorRes: Int): Builder {
                this.textColorRes = textColorRes
                return this
            }

            fun setCircleBackgroundColorRes(@ColorRes circleBackgroundColorRes: Int): Builder {
                this.circleBackgroundColorRes = circleBackgroundColorRes
                return this
            }

            fun setImageUrl(imageUrl: String?): Builder {
                this.imageUrl = imageUrl
                return this
            }

            fun setColorGenerator(colorGenerator: ColorGenerator): Builder {
                this.colorGenerator = colorGenerator;
                return this;
            }

            fun setInvalidateImageCache(invalidate: Boolean): Builder {
                this.invalidateImageCache = invalidate
                return this
            }

            fun build(): ImageInfo {
                return ImageInfo(this)
            }
        }
    }
}