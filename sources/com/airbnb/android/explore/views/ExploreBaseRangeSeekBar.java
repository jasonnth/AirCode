package com.airbnb.android.explore.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.p002v7.content.res.AppCompatResources;
import android.support.p002v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import com.airbnb.android.explore.C0857R;
import java.lang.Number;
import java.math.BigDecimal;

public class ExploreBaseRangeSeekBar<T extends Number> extends AppCompatImageView {
    public static final int ACTION_POINTER_INDEX_MASK = 65280;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    public static final int ACTION_POINTER_UP = 6;
    public static final int INVALID_POINTER_ID = 255;
    private T absoluteMaxValue;
    private double absoluteMaxValuePrim;
    private final T absoluteMinValue;
    private final double absoluteMinValuePrim;
    public int backgroundColor;
    private float lineHeight;
    private OnRangeSeekBarChangeListener<T> listener;
    private int mActivePointerId;
    public int mColor;
    private float mDownMotionX;
    private boolean mIsDragging;
    private int mScaledTouchSlop;
    float mTouchProgressOffset;
    protected double normalizedMaxValue;
    protected double normalizedMinValue;
    private boolean notifyWhileDragging;
    private final NumberType numberType;
    private float padding;
    protected final Paint paint;
    protected Thumb pressedThumb;
    private float pressedThumbHalfHeight;
    protected float pressedThumbHalfWidth;
    protected Bitmap pressedThumbImage;
    private float pressedThumbWidth;
    private float thumbHalfHeight;
    protected float thumbHalfWidth;
    protected Bitmap thumbImage;
    private float thumbWidth;

    private enum NumberType {
        LONG,
        DOUBLE,
        INTEGER,
        FLOAT,
        SHORT,
        BYTE,
        BIG_DECIMAL;

        public static <E extends Number> NumberType fromNumber(E value) throws IllegalArgumentException {
            if (value instanceof Long) {
                return LONG;
            }
            if (value instanceof Double) {
                return DOUBLE;
            }
            if (value instanceof Integer) {
                return INTEGER;
            }
            if (value instanceof Float) {
                return FLOAT;
            }
            if (value instanceof Short) {
                return SHORT;
            }
            if (value instanceof Byte) {
                return BYTE;
            }
            if (value instanceof BigDecimal) {
                return BIG_DECIMAL;
            }
            throw new IllegalArgumentException("Number class '" + value.getClass().getName() + "' is not supported");
        }

        public Number toNumber(double value) {
            switch (this) {
                case LONG:
                    return Long.valueOf((long) value);
                case DOUBLE:
                    return Double.valueOf(value);
                case INTEGER:
                    return Integer.valueOf((int) value);
                case FLOAT:
                    return new Float(value);
                case SHORT:
                    return Short.valueOf((short) ((int) value));
                case BYTE:
                    return Byte.valueOf((byte) ((int) value));
                case BIG_DECIMAL:
                    return new BigDecimal(value);
                default:
                    throw new InstantiationError("can't convert " + this + " to a Number object");
            }
        }
    }

    public interface OnRangeSeekBarChangeListener<T> {
        void onRangeSeekBarValuesChanged(ExploreBaseRangeSeekBar<?> exploreBaseRangeSeekBar, T t, T t2, boolean z);
    }

    protected enum Thumb {
        MIN,
        MAX
    }

    public ExploreBaseRangeSeekBar(T absoluteMinValue2, T absoluteMaxValue2, Context context) throws IllegalArgumentException {
        super(context);
        this.paint = new Paint(1);
        this.thumbImage = BitmapFactory.decodeResource(getResources(), C0857R.C0858drawable.explore_range_seek);
        this.pressedThumbImage = BitmapFactory.decodeResource(getResources(), C0857R.C0858drawable.explore_range_seek_pressed);
        this.thumbWidth = (float) this.thumbImage.getWidth();
        this.pressedThumbWidth = (float) this.pressedThumbImage.getWidth();
        this.thumbHalfWidth = this.thumbWidth * 0.5f;
        this.pressedThumbHalfWidth = this.pressedThumbWidth * 0.5f;
        this.thumbHalfHeight = ((float) this.thumbImage.getHeight()) * 0.5f;
        this.pressedThumbHalfHeight = ((float) this.pressedThumbImage.getHeight()) * 0.5f;
        this.lineHeight = 0.6f * this.thumbHalfHeight;
        this.padding = this.thumbHalfWidth;
        this.normalizedMinValue = 0.0d;
        this.normalizedMaxValue = 1.0d;
        this.pressedThumb = null;
        this.notifyWhileDragging = false;
        this.mColor = getResources().getColor(C0857R.color.c_rausch);
        this.backgroundColor = C0857R.color.c_gray_3;
        this.mActivePointerId = 255;
        this.absoluteMinValue = absoluteMinValue2;
        this.absoluteMaxValue = absoluteMaxValue2;
        this.absoluteMinValuePrim = absoluteMinValue2.doubleValue();
        this.absoluteMaxValuePrim = absoluteMaxValue2.doubleValue();
        this.numberType = NumberType.fromNumber(absoluteMinValue2);
        setFocusable(true);
        setFocusableInTouchMode(true);
        init();
    }

    public ExploreBaseRangeSeekBar(T absoluteMinValue2, T absoluteMaxValue2, int lineHeight2, int lineColor, int thumbDrawable, int thumbDrawablePressed, Context context) throws IllegalArgumentException {
        this(absoluteMinValue2, absoluteMaxValue2, context);
        this.lineHeight = (float) lineHeight2;
        this.backgroundColor = lineColor;
        setupThumb(thumbDrawable, thumbDrawablePressed);
    }

    public ExploreBaseRangeSeekBar(T absoluteMinValue2, T absoluteMaxValue2, int thumbDrawable, int thumbDrawablePressed, Context context) throws IllegalArgumentException {
        this(absoluteMinValue2, absoluteMaxValue2, context);
        setupThumb(thumbDrawable, thumbDrawablePressed);
    }

    private void setupThumb(int thumbDrawable, int thumbDrawablePressed) {
        if (thumbDrawable != -1) {
            this.thumbImage = createBitmapForResource(thumbDrawable);
            this.thumbWidth = (float) this.thumbImage.getWidth();
            this.thumbHalfWidth = this.thumbWidth * 0.5f;
            this.thumbHalfHeight = ((float) this.thumbImage.getHeight()) * 0.5f;
            this.padding = this.thumbHalfWidth;
        }
        if (thumbDrawablePressed != -1) {
            this.pressedThumbImage = createBitmapForResource(thumbDrawablePressed);
            this.pressedThumbWidth = (float) this.pressedThumbImage.getWidth();
            this.pressedThumbHalfWidth = this.pressedThumbWidth * 0.5f;
            this.pressedThumbHalfHeight = ((float) this.pressedThumbImage.getHeight()) * 0.5f;
        }
    }

    private Bitmap createBitmapForResource(int drawableRes) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
        if (bitmap == null) {
            Drawable drawable = AppCompatResources.getDrawable(getContext(), drawableRes);
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_4444);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return bitmap;
    }

    private void init() {
        this.mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public boolean isNotifyWhileDragging() {
        return this.notifyWhileDragging;
    }

    public void setNotifyWhileDragging(boolean flag) {
        this.notifyWhileDragging = flag;
    }

    public T getAbsoluteMinValue() {
        return this.absoluteMinValue;
    }

    public T getAbsoluteMaxValue() {
        return this.absoluteMaxValue;
    }

    public void setAbsoluteMaxValue(T absoluteMaxValue2) {
        this.absoluteMaxValue = absoluteMaxValue2;
        this.absoluteMaxValuePrim = absoluteMaxValue2.doubleValue();
    }

    public T getSelectedMinValue() {
        return normalizedToValue(this.normalizedMinValue);
    }

    public void setSelectedMinValue(T value) {
        if (0.0d == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            setNormalizedMinValue(0.0d);
        } else {
            setNormalizedMinValue(valueToNormalized(value));
        }
    }

    public T getSelectedMaxValue() {
        return normalizedToValue(this.normalizedMaxValue);
    }

    public void setSelectedMaxValue(T value) {
        if (0.0d == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            setNormalizedMaxValue(1.0d);
        } else {
            setNormalizedMaxValue(valueToNormalized(value));
        }
    }

    public void setOnRangeSeekBarChangeListener(OnRangeSeekBarChangeListener<T> listener2) {
        this.listener = listener2;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        switch (event.getAction() & 255) {
            case 0:
                this.mActivePointerId = event.getPointerId(event.getPointerCount() - 1);
                this.mDownMotionX = event.getX(event.findPointerIndex(this.mActivePointerId));
                this.pressedThumb = evalPressedThumb(this.mDownMotionX);
                if (this.pressedThumb != null) {
                    setPressed(true);
                    invalidate();
                    onStartTrackingTouch();
                    trackTouchEvent(event);
                    attemptClaimDrag();
                    break;
                } else {
                    return super.onTouchEvent(event);
                }
            case 1:
                if (this.mIsDragging) {
                    trackTouchEvent(event);
                    onStopTrackingTouch();
                    setPressed(false);
                } else {
                    onStartTrackingTouch();
                    trackTouchEvent(event);
                    onStopTrackingTouch();
                }
                this.pressedThumb = null;
                invalidate();
                if (this.listener != null) {
                    this.listener.onRangeSeekBarValuesChanged(this, getSelectedMinValue(), getSelectedMaxValue(), false);
                    break;
                }
                break;
            case 2:
                if (this.pressedThumb != null) {
                    if (this.mIsDragging) {
                        trackTouchEvent(event);
                    } else if (Math.abs(event.getX(event.findPointerIndex(this.mActivePointerId)) - this.mDownMotionX) > ((float) this.mScaledTouchSlop)) {
                        setPressed(true);
                        invalidate();
                        onStartTrackingTouch();
                        trackTouchEvent(event);
                        attemptClaimDrag();
                    }
                    if (this.notifyWhileDragging && this.listener != null) {
                        this.listener.onRangeSeekBarValuesChanged(this, getSelectedMinValue(), getSelectedMaxValue(), this.mIsDragging);
                        break;
                    }
                }
                break;
            case 3:
                if (this.mIsDragging) {
                    onStopTrackingTouch();
                    setPressed(false);
                }
                invalidate();
                break;
            case 5:
                int index = event.getPointerCount() - 1;
                this.mDownMotionX = event.getX(index);
                this.mActivePointerId = event.getPointerId(index);
                invalidate();
                break;
            case 6:
                onSecondaryPointerUp(event);
                invalidate();
                break;
        }
        return true;
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        int pointerIndex = (ev.getAction() & ACTION_POINTER_INDEX_MASK) >> 8;
        if (ev.getPointerId(pointerIndex) == this.mActivePointerId) {
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            this.mDownMotionX = ev.getX(newPointerIndex);
            this.mActivePointerId = ev.getPointerId(newPointerIndex);
        }
    }

    private void trackTouchEvent(MotionEvent event) {
        float x = event.getX(event.findPointerIndex(this.mActivePointerId));
        if (Thumb.MIN.equals(this.pressedThumb)) {
            setNormalizedMinValue(screenToNormalized(x));
        } else if (Thumb.MAX.equals(this.pressedThumb)) {
            setNormalizedMaxValue(screenToNormalized(x));
        }
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    /* access modifiers changed from: 0000 */
    public void onStartTrackingTouch() {
        this.mIsDragging = true;
    }

    /* access modifiers changed from: 0000 */
    public void onStopTrackingTouch() {
        this.mIsDragging = false;
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 200;
        if (MeasureSpec.getMode(widthMeasureSpec) != 0) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        int height = this.thumbImage.getHeight();
        if (MeasureSpec.getMode(heightMeasureSpec) != 0) {
            height = Math.min(height, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(width, height);
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = new RectF(this.padding, (((float) getHeight()) - this.lineHeight) * 0.5f, ((float) getWidth()) - this.padding, (((float) getHeight()) + this.lineHeight) * 0.5f);
        this.paint.setStyle(Style.FILL);
        this.paint.setColor(getContext().getResources().getColor(this.backgroundColor));
        this.paint.setAntiAlias(true);
        canvas.drawRect(rect, this.paint);
        rect.left = normalizedToScreen(this.normalizedMinValue);
        rect.right = normalizedToScreen(this.normalizedMaxValue);
        this.paint.setColor(this.mColor);
        canvas.drawRect(rect, this.paint);
        drawThumb(normalizedToScreen(this.normalizedMinValue), Thumb.MIN.equals(this.pressedThumb), canvas);
        drawThumb(normalizedToScreen(this.normalizedMaxValue), Thumb.MAX.equals(this.pressedThumb), canvas);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUPER", super.onSaveInstanceState());
        bundle.putDouble("MIN", this.normalizedMinValue);
        bundle.putDouble("MAX", this.normalizedMaxValue);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcel) {
        Bundle bundle = (Bundle) parcel;
        super.onRestoreInstanceState(bundle.getParcelable("SUPER"));
        this.normalizedMinValue = bundle.getDouble("MIN");
        this.normalizedMaxValue = bundle.getDouble("MAX");
    }

    /* access modifiers changed from: protected */
    public void drawThumb(float screenCoord, boolean pressed, Canvas canvas) {
        canvas.drawBitmap((!pressed || this.pressedThumbImage == null) ? this.thumbImage : this.pressedThumbImage, screenCoord - (pressed ? this.pressedThumbHalfWidth : this.thumbHalfWidth), (0.5f * ((float) getHeight())) - (pressed ? this.pressedThumbHalfHeight : this.thumbHalfHeight), this.paint);
    }

    private Thumb evalPressedThumb(float touchX) {
        boolean minThumbPressed = isInThumbRange(touchX, this.normalizedMinValue);
        boolean maxThumbPressed = isInThumbRange(touchX, this.normalizedMaxValue);
        if (minThumbPressed && maxThumbPressed) {
            return touchX / ((float) getWidth()) > 0.5f ? Thumb.MIN : Thumb.MAX;
        }
        if (minThumbPressed) {
            return Thumb.MIN;
        }
        if (maxThumbPressed) {
            return Thumb.MAX;
        }
        return null;
    }

    private boolean isInThumbRange(float touchX, double normalizedThumbValue) {
        return Math.abs(touchX - normalizedToScreen(normalizedThumbValue)) <= this.thumbWidth * 0.75f;
    }

    public void setNormalizedMinValue(double value) {
        this.normalizedMinValue = Math.max(0.0d, Math.min(1.0d, Math.min(value, this.normalizedMaxValue)));
        invalidate();
    }

    public void setNormalizedMaxValue(double value) {
        this.normalizedMaxValue = Math.max(0.0d, Math.min(1.0d, Math.max(value, this.normalizedMinValue)));
        invalidate();
    }

    private T normalizedToValue(double normalized) {
        return this.numberType.toNumber(this.absoluteMinValuePrim + ((this.absoluteMaxValuePrim - this.absoluteMinValuePrim) * normalized));
    }

    private double valueToNormalized(T value) {
        if (0.0d == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            return 0.0d;
        }
        return (value.doubleValue() - this.absoluteMinValuePrim) / (this.absoluteMaxValuePrim - this.absoluteMinValuePrim);
    }

    /* access modifiers changed from: protected */
    public float normalizedToScreen(double normalizedCoord) {
        return (float) (((double) this.padding) + (((double) (((float) getWidth()) - (2.0f * this.padding))) * normalizedCoord));
    }

    private double screenToNormalized(float screenCoord) {
        int width = getWidth();
        if (((float) width) <= this.padding * 2.0f) {
            return 0.0d;
        }
        return Math.min(1.0d, Math.max(0.0d, (double) ((screenCoord - this.padding) / (((float) width) - (this.padding * 2.0f)))));
    }

    public void setColor(int colorResId) {
        this.mColor = getResources().getColor(colorResId);
        invalidate();
    }

    public void setThumbImageResource(int imageResource, int imagePressedResource) {
        this.thumbImage = BitmapFactory.decodeResource(getResources(), imageResource);
        this.pressedThumbImage = BitmapFactory.decodeResource(getResources(), imagePressedResource);
    }

    public int getThumbWidth() {
        return (int) this.thumbWidth;
    }
}
