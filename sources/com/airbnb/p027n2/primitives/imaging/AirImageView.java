package com.airbnb.p027n2.primitives.imaging;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.p002v7.content.res.AppCompatResources;
import android.support.p002v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView.ScaleType;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.n2.R;
import com.airbnb.p027n2.utils.BitmapUtils;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestListener;
import java.util.concurrent.ExecutionException;

/* renamed from: com.airbnb.n2.primitives.imaging.AirImageView */
public class AirImageView extends AppCompatImageView {
    private static final int MAX_PARALLAX_UPDATE_DP = 25;
    public static final float PROPERTY_IMAGE_INV_RATIO = 0.6666667f;
    private static final int SCRIM_ALPHA_FOR_TEXT = 63;
    private boolean alignImageTop;
    private int defaultImageResId;
    private ColorStateList drawableColor;
    private boolean fade;
    private AirImageViewGlideHelper glideHelper;
    private ImageToLoad imageToLoadOnLayout;
    private final Runnable loadImageRunnable;
    private int maxParallaxUpdate;
    private Drawable placeholderDrawable;
    private final Paint scrimPaint;
    private final SizeDeterminer sizeDeterminer;
    private boolean useARGB_8888;

    /* renamed from: com.airbnb.n2.primitives.imaging.AirImageView$ImageToLoad */
    private static class ImageToLoad {
        /* access modifiers changed from: private */
        public final Image image;
        /* access modifiers changed from: private */
        public final RequestListener<String, Bitmap> listener;
        /* access modifiers changed from: private */
        public final BitmapTransformation transformation;

        public ImageToLoad(Image image2, BitmapTransformation transformation2, RequestListener<String, Bitmap> listener2) {
            this.image = image2;
            this.transformation = transformation2;
            this.listener = listener2;
        }
    }

    public AirImageView(Context context) {
        super(context);
        this.scrimPaint = new Paint();
        this.sizeDeterminer = new SizeDeterminer(this);
        this.loadImageRunnable = AirImageView$$Lambda$1.lambdaFactory$(this);
        init(context, null);
    }

    public AirImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.scrimPaint = new Paint();
        this.sizeDeterminer = new SizeDeterminer(this);
        this.loadImageRunnable = AirImageView$$Lambda$2.lambdaFactory$(this);
        init(context, attrs);
    }

    public AirImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.scrimPaint = new Paint();
        this.sizeDeterminer = new SizeDeterminer(this);
        this.loadImageRunnable = AirImageView$$Lambda$3.lambdaFactory$(this);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_AirImageView);
        this.drawableColor = a.getColorStateList(R.styleable.n2_AirImageView_n2_drawableColor);
        this.fade = a.getBoolean(R.styleable.n2_AirImageView_n2_fade, this.fade);
        this.defaultImageResId = a.getResourceId(R.styleable.n2_AirImageView_n2_placeholder, -1);
        this.useARGB_8888 = a.getBoolean(R.styleable.n2_AirImageView_n2_useARGB8888, false);
        this.alignImageTop = a.getBoolean(R.styleable.n2_AirImageView_n2_alignTop, false);
        if (a.getBoolean(R.styleable.n2_AirImageView_n2_scrimForText, false)) {
            setScrimForText(true);
        } else {
            this.scrimPaint.setAlpha((int) (a.getFloat(R.styleable.n2_AirImageView_n2_scrimAlpha, 0.0f) * 255.0f));
        }
        a.recycle();
        this.maxParallaxUpdate = ViewLibUtils.dpToPx(context, 25.0f);
        mutateDrawableWithColor();
        if (!isInEditMode()) {
            this.glideHelper = new AirImageViewGlideHelper(this, this.sizeDeterminer);
        }
        this.scrimPaint.setColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.scrimPaint.setStyle(Style.FILL);
        this.scrimPaint.setAlpha(0);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        tryLoadPendingImage();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        tryLoadPendingImage();
    }

    private void tryLoadPendingImage() {
        if (this.imageToLoadOnLayout != null) {
            removeCallbacks(this.loadImageRunnable);
            post(this.loadImageRunnable);
        }
    }

    static /* synthetic */ void lambda$new$0(AirImageView airImageView) {
        if (airImageView.imageToLoadOnLayout != null) {
            airImageView.setImage(airImageView.imageToLoadOnLayout.image, airImageView.imageToLoadOnLayout.transformation, airImageView.imageToLoadOnLayout.listener);
        }
    }

    public void setImageUrl(String url) {
        setImageUrl(url, null);
    }

    public void setImageUrl(String url, RequestListener<String, Bitmap> listener) {
        setImage(url, null, listener, null);
    }

    public void setImageUrlWithRoundedCorners(String url) {
        this.glideHelper.fetchImageWithRoundedCorners(url);
    }

    public void setImageUrlWithBlurredPreview(String url, String blurredPreviewData) {
        setImage(url, null, null, blurredPreviewData);
    }

    /* access modifiers changed from: protected */
    public void setImageUrlWithTransformation(String url, BitmapTransformation transformation, RequestListener<String, Bitmap> listener) {
        setImage(url, transformation, listener, null);
    }

    /* access modifiers changed from: protected */
    public void setImage(String url, BitmapTransformation transformation, RequestListener<String, Bitmap> listener, String base64EncodedPreview) {
        if (TextUtils.isEmpty(url)) {
            clear();
        } else {
            setImage(new SimpleImage(url, base64EncodedPreview), transformation, listener);
        }
    }

    public void setImage(Image image) {
        setImage(image, null, null);
    }

    public void setImage(Image image, BitmapTransformation transformation, RequestListener<String, Bitmap> listener) {
        if (image == null) {
            clear();
            return;
        }
        removeCallbacks(this.loadImageRunnable);
        this.imageToLoadOnLayout = null;
        if (this.sizeDeterminer.hasWidthAndHeight()) {
            this.glideHelper.fetchImage(image, transformation, listener);
        } else {
            this.imageToLoadOnLayout = new ImageToLoad(image, transformation, listener);
        }
    }

    public void clear() {
        removeCallbacks(this.loadImageRunnable);
        this.imageToLoadOnLayout = null;
        this.glideHelper.clear();
        setImageDrawable(null);
    }

    public static void getImage(Context context, String imageUrl, AirImageListener listener) {
        AirImageViewGlideHelper.getImage(context, imageUrl, listener);
    }

    public static void getImageBackground(Context context, String url) {
        Glide.with(context).load(url).preload();
    }

    public static Bitmap getImageBlocking(Context context, String url, BitmapTransformation transformation) {
        try {
            BitmapTypeRequest<String> request = Glide.with(context).load(url).asBitmap();
            if (transformation != null) {
                request.transform(transformation);
            }
            return (Bitmap) request.into(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean setFrame(int l, int t, int r, int b) {
        boolean superResult = super.setFrame(l, t, r, b);
        if (this.alignImageTop) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                int drawableWidth = drawable.getIntrinsicWidth();
                float widthScale = ((float) getWidth()) / ((float) drawableWidth);
                float heightScale = ((float) getHeight()) / ((float) drawable.getIntrinsicHeight());
                float scaleToUse = Math.max(heightScale, widthScale);
                Matrix matrix = getImageMatrix();
                matrix.setScale(scaleToUse, scaleToUse, 0.0f, 0.0f);
                if (scaleToUse == heightScale) {
                    matrix.postTranslate((float) (-((((int) (((float) drawableWidth) * scaleToUse)) - getWidth()) / 2)), 0.0f);
                }
                setScaleType(ScaleType.MATRIX);
                setImageMatrix(matrix);
            }
        }
        return superResult;
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mutateDrawableWithColor();
    }

    public void setImageDrawableCompat(int drawableId) {
        setImageDrawable(AppCompatResources.getDrawable(getContext(), drawableId));
    }

    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mutateDrawableWithColor();
    }

    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mutateDrawableWithColor();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.scrimPaint.getAlpha() != 0) {
            canvas.drawRect((float) getScrollX(), (float) getScrollY(), (float) (getWidth() + getScrollX()), (float) (getHeight() + getScrollY()), this.scrimPaint);
        }
    }

    public static Bitmap getBitmapFromCacheOrDrawable(Context context, String url, int drawableRes) {
        Bitmap bitmap = BitmapUtils.getBitmapFromCacheOnly(context, url);
        if (bitmap == null) {
            return BitmapFactory.decodeResource(context.getResources(), drawableRes);
        }
        return bitmap;
    }

    public void updateParallax(boolean mIsVerticalScroll, int extraScrollPixels) {
        Rect location = ViewLibUtils.getViewBounds(this);
        Point screenSizePt = ViewLibUtils.getScreenSize(getContext());
        int pixelsToScroll = (int) ((((float) (mIsVerticalScroll ? location.top : location.left)) / ((float) (mIsVerticalScroll ? screenSizePt.y : screenSizePt.x))) * ((float) extraScrollPixels));
        if (Math.abs(pixelsToScroll) > this.maxParallaxUpdate) {
            pixelsToScroll = pixelsToScroll > 0 ? this.maxParallaxUpdate : -this.maxParallaxUpdate;
        }
        if (mIsVerticalScroll) {
            setScrollY(pixelsToScroll);
        } else {
            setScrollX(pixelsToScroll);
        }
    }

    public void setFadeEnabled(boolean fade2) {
        this.fade = fade2;
    }

    public boolean isFadeEnabled() {
        return this.fade;
    }

    public void setPlaceholderResId(int defaultImage) {
        this.defaultImageResId = defaultImage;
    }

    public int getPlaceholderResId() {
        return this.defaultImageResId;
    }

    public void setPlaceholderDrawable(Drawable placeholderDrawable2) {
        this.placeholderDrawable = placeholderDrawable2;
    }

    public Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public void setScrimForText(boolean enabled) {
        setScrimAlpha(enabled ? 63 : 0);
    }

    public void setScrimAlpha(int alpha) {
        if (this.scrimPaint.getAlpha() != alpha) {
            this.scrimPaint.setAlpha(alpha);
            invalidate();
        }
    }

    private void mutateDrawableWithColor() {
        if (this.drawableColor != null) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                ColorizedDrawable.mutateDrawableWithColor(drawable, this.drawableColor.getDefaultColor());
            }
        }
    }

    public boolean shouldUseARGB8888() {
        return this.useARGB_8888;
    }

    public void setAlignImageTop(boolean alignImageTop2) {
        this.alignImageTop = alignImageTop2;
    }
}
