package com.airbnb.p027n2.primitives.imaging;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.airbnb.n2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import java.util.Random;

/* renamed from: com.airbnb.n2.primitives.imaging.HaloImageView */
public class HaloImageView extends AirImageView {
    private int borderColor;
    private float borderThickness;
    private GlideCircleTransform circleTransform;
    private final int[] defaultHaloImages = {R.drawable.n2_empty_profile_halo_large_rausch, R.drawable.n2_empty_profile_halo_large_kazan, R.drawable.n2_empty_profile_halo_large_lima, R.drawable.n2_empty_profile_halo_large_beach, R.drawable.n2_empty_profile_halo_large_babu};
    private int defaultImageRes;
    private Paint mBorderPaint;
    private boolean mCenterAbove;
    private Paint mSelectorPaint;
    private final Random random = new Random();

    public HaloImageView(Context context) {
        super(context);
        init(null);
    }

    public HaloImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HaloImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        float dimension;
        this.borderThickness = 0.0f;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_HaloImageView);
            boolean drawBorder = a.getBoolean(R.styleable.n2_HaloImageView_n2_drawBorder, false);
            this.borderColor = a.getColor(R.styleable.n2_HaloImageView_n2_borderColor, 0);
            this.borderThickness = a.getDimension(R.styleable.n2_HaloImageView_n2_borderThickness, 0.0f);
            this.mCenterAbove = a.getBoolean(R.styleable.n2_HaloImageView_n2_centerAbove, false);
            boolean drawSelector = a.getBoolean(R.styleable.n2_HaloImageView_n2_drawSelector, false);
            this.defaultImageRes = a.getResourceId(R.styleable.n2_HaloImageView_n2_placeholder, -1);
            a.recycle();
            if (drawBorder) {
                this.mBorderPaint = new Paint(1);
                this.mBorderPaint.setColor(this.borderColor);
                this.mBorderPaint.setStyle(Style.STROKE);
                this.mBorderPaint.setStrokeWidth(this.borderThickness);
            }
            if (drawSelector) {
                this.mSelectorPaint = new Paint(1);
                this.mSelectorPaint.setColor(getResources().getColor(R.color.n2_halo_image_view_pressed));
                this.mSelectorPaint.setStyle(Style.STROKE);
                Paint paint = this.mSelectorPaint;
                if (this.borderThickness >= 1.0f) {
                    dimension = this.borderThickness;
                } else {
                    dimension = getResources().getDimension(R.dimen.n2_halo_border_thickness);
                }
                paint.setStrokeWidth(dimension);
            }
        }
        setFadeEnabled(false);
        setPlaceholderResId(getDefaultImage());
        if (isInEditMode()) {
            setImageDefault();
        } else {
            this.circleTransform = new GlideCircleTransform(getContext(), (int) (this.borderThickness / 2.0f));
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        String simpleName;
        super.onAttachedToWindow();
        if (this.mCenterAbove) {
            ViewParent parent = getParent();
            if (parent instanceof RelativeLayout) {
                LayoutParams params = (LayoutParams) getLayoutParams();
                params.setMargins(params.leftMargin, (-params.height) / 2, params.rightMargin, params.bottomMargin);
                setLayoutParams(params);
                return;
            }
            StringBuilder append = new StringBuilder().append("HaloImageView centerAbove can only be used with a relative layout, not ");
            if (parent == null) {
                simpleName = "null";
            } else {
                simpleName = parent.getClass().getSimpleName();
            }
            throw new IllegalStateException(append.append(simpleName).toString());
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            super.onDraw(canvas);
        } else if (getDrawable() != null) {
            super.onDraw(canvas);
            if (isPressed() && this.mSelectorPaint != null) {
                drawCircle(canvas, this.mSelectorPaint);
            } else if (this.mBorderPaint != null) {
                drawCircle(canvas, this.mBorderPaint);
            }
        }
    }

    private void drawCircle(Canvas canvas, Paint paint) {
        if (!isInEditMode()) {
            int w = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int h = (getHeight() - getPaddingBottom()) - getPaddingTop();
            canvas.drawCircle(((float) (getPaddingLeft() + w + getPaddingRight())) * 0.5f, ((float) (getPaddingTop() + h + getPaddingBottom())) * 0.5f, (((float) Math.min(w, h)) * 0.5f) - (paint.getStrokeWidth() * 0.5f), paint);
        }
    }

    public void setBorder(int color, float thickness) {
        this.mBorderPaint = new Paint(1);
        this.mBorderPaint.setColor(color);
        this.mBorderPaint.setStyle(Style.STROKE);
        this.mBorderPaint.setStrokeWidth(thickness);
        invalidate();
    }

    public void removeBorder() {
        this.mBorderPaint = null;
        invalidate();
    }

    private int getDefaultImage() {
        return this.defaultImageRes > 0 ? this.defaultImageRes : this.defaultHaloImages[this.random.nextInt(this.defaultHaloImages.length)];
    }

    public void setImageDefault() {
        setImageResource(getDefaultImage());
    }

    public void setImageUrl(String url, RequestListener<String, Bitmap> listener) {
        setImageUrlWithTransformation(url, this.circleTransform, listener);
    }

    public void setImage(Image image) {
        setImage(image, this.circleTransform, null);
    }

    public void setImageUri(Uri uri) {
        Glide.with(getContext()).load(uri).bitmapTransform(new CenterCrop(getContext()), this.circleTransform).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(this);
    }
}
