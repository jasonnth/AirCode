package p003at.grabner.circleprogress;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import jumio.p317nv.nfc.C4966a;

/* renamed from: at.grabner.circleprogress.CircleProgressView */
public class CircleProgressView extends View {
    private RectF mActualTextBounds = new RectF();
    double mAnimationDuration = 900.0d;
    Handler mAnimationHandler = new AnimationHandler(this);
    AnimationState mAnimationState = AnimationState.IDLE;
    AnimationStateChangedListener mAnimationStateChangedListener;
    private int mBackgroundCircleColor = 0;
    private Paint mBackgroundCirclePaint = new Paint();
    private int mBarColorStandard = -16738680;
    private int[] mBarColors = {this.mBarColorStandard};
    private Paint mBarPaint = new Paint();
    private Paint mBarSpinnerPaint = new Paint();
    private Cap mBarStrokeCap = Cap.BUTT;
    private int mBarWidth = 40;
    private int mBlockCount = 18;
    private float mBlockDegree = (360.0f / ((float) this.mBlockCount));
    private float mBlockScale = 0.9f;
    private float mBlockScaleDegree = (this.mBlockDegree * this.mBlockScale);
    private PointF mCenter;
    private RectF mCircleBounds = new RectF();
    private RectF mCircleInnerContour = new RectF();
    private RectF mCircleOuterContour = new RectF();
    private int mCircleRadius = 80;
    private Bitmap mClippingBitmap;
    private int mContourColor = -1442840576;
    private Paint mContourPaint = new Paint();
    private float mContourSize = 1.0f;
    float mCurrentSpinnerDegreeValue = 0.0f;
    float mCurrentValue = 42.0f;
    int mDelayMillis = 15;
    boolean mDrawBarWhileSpinning;
    private RectF mInnerCircleBound = new RectF();
    private boolean mIsAutoColorEnabled = false;
    private boolean mIsAutoTextSize;
    private int mLayoutHeight = 0;
    private int mLayoutWidth = 0;
    private Paint mMaskPaint = new Paint(1);
    float mMaxValue = 100.0f;
    private RectF mOuterTextBounds = new RectF();
    private int mPaddingBottom = 5;
    private int mPaddingLeft = 5;
    private int mPaddingRight = 5;
    private int mPaddingTop = 5;
    private float mRelativeUniteSize = 0.3f;
    private int mRimColor = -1434201911;
    private Paint mRimPaint = new Paint();
    private int mRimWidth = 40;
    private boolean mSeekModeEnabled = false;
    private boolean mShowBlock = false;
    private boolean mShowTextWhileSpinning = false;
    private boolean mShowUnit = false;
    float mSpinSpeed = 2.8f;
    private int mSpinnerColor = this.mBarColorStandard;
    private Cap mSpinnerStrokeCap = Cap.BUTT;
    float mSpinningBarLengthCurrent = 0.0f;
    float mSpinningBarLengthOrig = 42.0f;
    private int mStartAngle = MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS;
    private String mText = "";
    private int mTextColor = AirMapInterface.CIRCLE_BORDER_COLOR;
    private int mTextLength;
    private TextMode mTextMode = TextMode.PERCENT;
    private Paint mTextPaint = new Paint();
    private float mTextScale = 1.0f;
    private int mTextSize = 10;
    private int mTouchEventCount;
    private String mUnit = "";
    private RectF mUnitBounds = new RectF();
    private int mUnitColor = AirMapInterface.CIRCLE_BORDER_COLOR;
    private float mUnitScale = 1.0f;
    private int mUnitSize = 10;
    private Paint mUnitTextPaint = new Paint();
    float mValueFrom = 0.0f;
    float mValueTo = 0.0f;

    public CircleProgressView(Context context) {
        super(context);
        this.mMaskPaint.setFilterBitmap(false);
        this.mMaskPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        setupPaints();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        if (paddingLeft <= measuredHeight) {
            measuredHeight = paddingLeft;
        }
        setMeasuredDimension(getPaddingLeft() + measuredHeight + getPaddingRight(), measuredHeight + getPaddingTop() + getPaddingBottom());
    }

    private RectF getInnerCircleRect(RectF rectF) {
        float f;
        float f2 = 1.0f;
        float width = (rectF.width() - ((float) ((((double) ((rectF.width() - ((float) Math.max(this.mBarWidth, this.mRimWidth))) - (this.mContourSize * 2.0f))) / 2.0d) * Math.sqrt(2.0d)))) / 2.0f;
        if (isShowUnit()) {
            f = 0.77f;
            f2 = 1.33f;
        } else {
            f = 1.0f;
        }
        return new RectF(rectF.left + (width * f), rectF.top + (width * f2), rectF.right - (f * width), rectF.bottom - (f2 * width));
    }

    private static float calcTextSizeForRect(String str, Paint paint, RectF rectF) {
        Matrix matrix = new Matrix();
        Rect rect = new Rect();
        String replace = str.replace('1', '0');
        paint.getTextBounds(replace, 0, replace.length(), rect);
        matrix.setRectToRect(new RectF(rect), rectF, ScaleToFit.CENTER);
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return paint.getTextSize() * fArr[0];
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mLayoutWidth = i;
        this.mLayoutHeight = i2;
        setupBounds();
        setupBarPaint();
        if (this.mClippingBitmap != null) {
            this.mClippingBitmap = Bitmap.createScaledBitmap(this.mClippingBitmap, getWidth(), getHeight(), false);
        }
        invalidate();
    }

    public void setAnimationStateChangedListener(AnimationStateChangedListener animationStateChangedListener) {
        this.mAnimationStateChangedListener = animationStateChangedListener;
    }

    public int getUnitSize() {
        return this.mUnitSize;
    }

    public void setUnitSize(int i) {
        this.mUnitSize = i;
        this.mUnitTextPaint.setTextSize((float) i);
    }

    public void setSeekModeEnabled(boolean z) {
        this.mSeekModeEnabled = z;
    }

    public Cap getSpinnerStrokeCap() {
        return this.mSpinnerStrokeCap;
    }

    public void setSpinnerStrokeCap(Cap cap) {
        this.mSpinnerStrokeCap = cap;
        this.mBarSpinnerPaint.setStrokeCap(cap);
    }

    public Cap getBarStrokeCap() {
        return this.mBarStrokeCap;
    }

    public void setBarStrokeCap(Cap cap) {
        this.mBarStrokeCap = cap;
        this.mBarPaint.setStrokeCap(cap);
    }

    public int getContourColor() {
        return this.mContourColor;
    }

    public void setContourColor(int i) {
        this.mContourColor = i;
        this.mContourPaint.setColor(i);
    }

    public float getContourSize() {
        return this.mContourSize;
    }

    public void setContourSize(float f) {
        this.mContourSize = f;
        this.mContourPaint.setStrokeWidth(f);
    }

    public void setText(String str) {
        if (str == null) {
            str = "";
        }
        this.mText = str;
        invalidate();
    }

    public void setTextMode(TextMode textMode) {
        this.mTextMode = textMode;
    }

    public String getUnit() {
        return this.mUnit;
    }

    public void setUnit(String str) {
        if (str == null) {
            this.mUnit = "";
        } else {
            this.mUnit = str;
        }
        invalidate();
    }

    private RectF getTextBounds(String str, Paint paint, RectF rectF) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        RectF rectF2 = new RectF();
        rectF2.left = rectF.left + ((rectF.width() - ((float) rect.width())) / 2.0f);
        rectF2.top = rectF.top + ((rectF.height() - ((float) rect.height())) / 2.0f);
        rectF2.right = rectF2.left + ((float) rect.width());
        rectF2.bottom = ((float) rect.height()) + rectF2.top;
        return rectF2;
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public void setTextSize(int i) {
        this.mTextPaint.setTextSize((float) i);
        this.mTextSize = i;
        this.mIsAutoTextSize = false;
    }

    public void setAutoTextSize(boolean z) {
        this.mIsAutoTextSize = z;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public void setPaddingTop(int i) {
        this.mPaddingTop = i;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public void setPaddingBottom(int i) {
        this.mPaddingBottom = i;
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public void setPaddingLeft(int i) {
        this.mPaddingLeft = i;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public void setPaddingRight(int i) {
        this.mPaddingRight = i;
    }

    public int getCircleRadius() {
        return this.mCircleRadius;
    }

    public boolean isShowUnit() {
        return this.mShowUnit;
    }

    public void setShowUnit(boolean z) {
        if (z != this.mShowUnit) {
            this.mShowUnit = z;
            this.mTextLength = 0;
            this.mOuterTextBounds = getInnerCircleRect(this.mCircleBounds);
            invalidate();
        }
    }

    public float getUnitScale() {
        return this.mUnitScale;
    }

    public void setUnitScale(float f) {
        this.mUnitScale = f;
    }

    public float getTextScale() {
        return this.mTextScale;
    }

    public void setTextScale(float f) {
        this.mTextScale = f;
    }

    public void setSpinningBarLength(float f) {
        this.mSpinningBarLengthOrig = f;
        this.mSpinningBarLengthCurrent = f;
    }

    public int getBarWidth() {
        return this.mBarWidth;
    }

    public void setBarWidth(int i) {
        this.mBarWidth = i;
        this.mBarPaint.setStrokeWidth((float) i);
        this.mBarSpinnerPaint.setStrokeWidth((float) i);
    }

    public int[] getBarColors() {
        return this.mBarColors;
    }

    public void setBarColor(int... iArr) {
        this.mBarColors = iArr;
        if (this.mBarColors.length > 1) {
            this.mBarPaint.setShader(new SweepGradient(this.mCircleBounds.centerX(), this.mCircleBounds.centerY(), this.mBarColors, null));
            Matrix matrix = new Matrix();
            this.mBarPaint.getShader().getLocalMatrix(matrix);
            matrix.postTranslate(-this.mCircleBounds.centerX(), -this.mCircleBounds.centerY());
            matrix.postRotate((float) this.mStartAngle);
            matrix.postTranslate(this.mCircleBounds.centerX(), this.mCircleBounds.centerY());
            this.mBarPaint.getShader().setLocalMatrix(matrix);
            this.mBarPaint.setColor(iArr[0]);
        } else if (this.mBarColors.length == 0) {
            this.mBarPaint.setColor(this.mBarColors[0]);
            this.mBarPaint.setShader(null);
        } else {
            this.mBarPaint.setColor(this.mBarColorStandard);
            this.mBarPaint.setShader(null);
        }
    }

    public void setSpinBarColor(int i) {
        this.mSpinnerColor = i;
        this.mBarSpinnerPaint.setColor(this.mSpinnerColor);
    }

    public int getBackgroundCircleColor() {
        return this.mBackgroundCirclePaint.getColor();
    }

    public void setFillCircleColor(int i) {
        this.mBackgroundCircleColor = i;
        this.mBackgroundCirclePaint.setColor(i);
    }

    public int getRimColor() {
        return this.mRimColor;
    }

    public void setRimColor(int i) {
        this.mRimColor = i;
        this.mRimPaint.setColor(i);
    }

    public Shader getRimShader() {
        return this.mRimPaint.getShader();
    }

    public void setRimShader(Shader shader) {
        this.mRimPaint.setShader(shader);
    }

    private int getTextColor(double d) {
        int i;
        int i2 = 1;
        if (this.mBarColors.length > 1) {
            double maxValue = (1.0d / getMaxValue()) * d;
            int floor = (int) Math.floor(((double) (this.mBarColors.length - 1)) * maxValue);
            int i3 = floor + 1;
            if (floor < 0) {
                i = 0;
            } else if (i3 >= this.mBarColors.length) {
                i = this.mBarColors.length - 2;
                i2 = this.mBarColors.length - 1;
            } else {
                i2 = i3;
                i = floor;
            }
            return ColorUtils.getRGBGradient(this.mBarColors[i], this.mBarColors[i2], (float) (1.0d - ((((double) (this.mBarColors.length - 1)) * maxValue) % 1.0d)));
        } else if (this.mBarColors.length == 1) {
            return this.mBarColors[0];
        } else {
            return AirMapInterface.CIRCLE_BORDER_COLOR;
        }
    }

    public double getMaxValue() {
        return (double) this.mMaxValue;
    }

    public void setMaxValue(float f) {
        this.mMaxValue = f;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public void setTextColor(int i) {
        this.mTextColor = i;
        this.mTextPaint.setColor(i);
    }

    public void setAutoTextColor(boolean z) {
        this.mIsAutoColorEnabled = z;
    }

    public void setUnitColor(int i) {
        this.mUnitColor = i;
        this.mUnitTextPaint.setColor(i);
        this.mIsAutoColorEnabled = false;
    }

    public float getSpinSpeed() {
        return this.mSpinSpeed;
    }

    public void setSpinSpeed(float f) {
        this.mSpinSpeed = f;
    }

    public int getRimWidth() {
        return this.mRimWidth;
    }

    public void setRimWidth(int i) {
        this.mRimWidth = i;
        this.mRimPaint.setStrokeWidth((float) i);
    }

    public int getDelayMillis() {
        return this.mDelayMillis;
    }

    public void setDelayMillis(int i) {
        this.mDelayMillis = i;
    }

    @TargetApi(11)
    public void setClippingBitmap(Bitmap bitmap) {
        if (getWidth() <= 0 || getHeight() <= 0) {
            this.mClippingBitmap = bitmap;
        } else {
            this.mClippingBitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), false);
        }
        if (this.mClippingBitmap == null) {
            setLayerType(2, null);
        } else {
            setLayerType(1, null);
        }
    }

    public void setTextTypeface(Typeface typeface) {
        this.mTextPaint.setTypeface(typeface);
    }

    public void setUnitTextTypeface(Typeface typeface) {
        this.mUnitTextPaint.setTypeface(typeface);
    }

    public float getRelativeUniteSize() {
        return this.mRelativeUniteSize;
    }

    public void setRelativeUniteSize(float f) {
        this.mRelativeUniteSize = f;
    }

    public void setShowTextWhileSpinning(boolean z) {
        this.mShowTextWhileSpinning = z;
    }

    public int getStartAngle() {
        return this.mStartAngle;
    }

    public void setStartAngle(int i) {
        this.mStartAngle = (int) normalizeAngle((float) i);
    }

    public void setShowBlock(boolean z) {
        this.mShowBlock = z;
    }

    public void setBlockCount(int i) {
        if (i > 1) {
            this.mShowBlock = true;
            this.mBlockCount = i;
            this.mBlockDegree = 360.0f / ((float) i);
            this.mBlockScaleDegree = this.mBlockDegree * this.mBlockScale;
            return;
        }
        this.mShowBlock = false;
    }

    public void setBlockScale(float f) {
        if (f >= 0.0f && f <= 1.0f) {
            this.mBlockScale = f;
            this.mBlockScaleDegree = this.mBlockDegree * f;
        }
    }

    public int getBlockCount() {
        return this.mBlockCount;
    }

    public float getBlockScale() {
        return this.mBlockScale;
    }

    public void setupPaints() {
        setupBarPaint();
        setupBarSpinnerPaint();
        setupContourPaint();
        setupUnitTextPaint();
        setupTextPaint();
        setupBackgroundCirclePaint();
        setupRimPaint();
    }

    private void setupContourPaint() {
        this.mContourPaint.setColor(this.mContourColor);
        this.mContourPaint.setAntiAlias(true);
        this.mContourPaint.setStyle(Style.STROKE);
        this.mContourPaint.setStrokeWidth(this.mContourSize);
    }

    private void setupUnitTextPaint() {
        this.mUnitTextPaint.setStyle(Style.FILL);
        this.mUnitTextPaint.setAntiAlias(true);
    }

    private void setupTextPaint() {
        this.mTextPaint.setSubpixelText(true);
        this.mTextPaint.setLinearText(true);
        this.mTextPaint.setTypeface(Typeface.MONOSPACE);
        this.mTextPaint.setColor(this.mTextColor);
        this.mTextPaint.setStyle(Style.FILL);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize((float) this.mTextSize);
    }

    private void setupBackgroundCirclePaint() {
        this.mBackgroundCirclePaint.setColor(this.mBackgroundCircleColor);
        this.mBackgroundCirclePaint.setAntiAlias(true);
        this.mBackgroundCirclePaint.setStyle(Style.FILL);
    }

    private void setupRimPaint() {
        this.mRimPaint.setColor(this.mRimColor);
        this.mRimPaint.setAntiAlias(true);
        this.mRimPaint.setStyle(Style.STROKE);
        this.mRimPaint.setStrokeWidth((float) this.mRimWidth);
    }

    private void setupBarSpinnerPaint() {
        this.mBarSpinnerPaint.setAntiAlias(true);
        this.mBarSpinnerPaint.setStrokeCap(this.mSpinnerStrokeCap);
        this.mBarSpinnerPaint.setStyle(Style.STROKE);
        this.mBarSpinnerPaint.setStrokeWidth((float) this.mBarWidth);
        this.mBarSpinnerPaint.setColor(this.mSpinnerColor);
    }

    private void setupBarPaint() {
        if (this.mBarColors.length > 1) {
            this.mBarPaint.setShader(new SweepGradient(this.mCircleBounds.centerX(), this.mCircleBounds.centerY(), this.mBarColors, null));
            Matrix matrix = new Matrix();
            this.mBarPaint.getShader().getLocalMatrix(matrix);
            matrix.postTranslate(-this.mCircleBounds.centerX(), -this.mCircleBounds.centerY());
            matrix.postRotate((float) this.mStartAngle);
            matrix.postTranslate(this.mCircleBounds.centerX(), this.mCircleBounds.centerY());
            this.mBarPaint.getShader().setLocalMatrix(matrix);
        } else {
            this.mBarPaint.setColor(this.mBarColors[0]);
            this.mBarPaint.setShader(null);
        }
        this.mBarPaint.setAntiAlias(true);
        this.mBarPaint.setStrokeCap(this.mBarStrokeCap);
        this.mBarPaint.setStyle(Style.STROKE);
        this.mBarPaint.setStrokeWidth((float) this.mBarWidth);
    }

    private void setupBounds() {
        int min = Math.min(this.mLayoutWidth, this.mLayoutHeight);
        int i = this.mLayoutWidth - min;
        int i2 = this.mLayoutHeight - min;
        this.mPaddingTop = getPaddingTop() + (i2 / 2);
        this.mPaddingBottom = (i2 / 2) + getPaddingBottom();
        this.mPaddingLeft = getPaddingLeft() + (i / 2);
        this.mPaddingRight = getPaddingRight() + (i / 2);
        int width = getWidth();
        int height = getHeight();
        this.mCircleBounds = new RectF((float) (this.mPaddingLeft + this.mBarWidth), (float) (this.mPaddingTop + this.mBarWidth), (float) ((width - this.mPaddingRight) - this.mBarWidth), (float) ((height - this.mPaddingBottom) - this.mBarWidth));
        this.mInnerCircleBound = new RectF(((float) this.mPaddingLeft) + (((float) this.mBarWidth) * 1.5f), ((float) this.mPaddingTop) + (((float) this.mBarWidth) * 1.5f), ((float) (width - this.mPaddingRight)) - (((float) this.mBarWidth) * 1.5f), ((float) (height - this.mPaddingBottom)) - (((float) this.mBarWidth) * 1.5f));
        this.mOuterTextBounds = getInnerCircleRect(this.mCircleBounds);
        this.mCircleInnerContour = new RectF(this.mCircleBounds.left + (((float) this.mRimWidth) / 2.0f) + (this.mContourSize / 2.0f), this.mCircleBounds.top + (((float) this.mRimWidth) / 2.0f) + (this.mContourSize / 2.0f), (this.mCircleBounds.right - (((float) this.mRimWidth) / 2.0f)) - (this.mContourSize / 2.0f), (this.mCircleBounds.bottom - (((float) this.mRimWidth) / 2.0f)) - (this.mContourSize / 2.0f));
        this.mCircleOuterContour = new RectF((this.mCircleBounds.left - (((float) this.mRimWidth) / 2.0f)) - (this.mContourSize / 2.0f), (this.mCircleBounds.top - (((float) this.mRimWidth) / 2.0f)) - (this.mContourSize / 2.0f), this.mCircleBounds.right + (((float) this.mRimWidth) / 2.0f) + (this.mContourSize / 2.0f), this.mCircleBounds.bottom + (((float) this.mRimWidth) / 2.0f) + (this.mContourSize / 2.0f));
        this.mCircleRadius = ((((width - this.mPaddingRight) - this.mBarWidth) / 2) - this.mBarWidth) + 1;
        this.mCenter = new PointF(this.mCircleBounds.centerX(), this.mCircleBounds.centerY());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = (360.0f / this.mMaxValue) * this.mCurrentValue;
        if (this.mBackgroundCircleColor != 0) {
            canvas.drawArc(this.mInnerCircleBound, 360.0f, 360.0f, false, this.mBackgroundCirclePaint);
        }
        if (this.mRimWidth > 0) {
            if (!this.mShowBlock) {
                canvas.drawArc(this.mCircleBounds, 360.0f, 360.0f, false, this.mRimPaint);
            } else {
                drawBlocks(canvas, this.mCircleBounds, (float) this.mStartAngle, 360.0f, false, this.mRimPaint);
            }
        }
        if (this.mContourSize > 0.0f) {
            canvas.drawArc(this.mCircleOuterContour, 360.0f, 360.0f, false, this.mContourPaint);
            canvas.drawArc(this.mCircleInnerContour, 360.0f, 360.0f, false, this.mContourPaint);
        }
        if (this.mAnimationState == AnimationState.SPINNING || this.mAnimationState == AnimationState.END_SPINNING) {
            drawSpinner(canvas);
            if (this.mShowTextWhileSpinning) {
                drawTextWithUnit(canvas);
            }
        } else if (this.mAnimationState == AnimationState.END_SPINNING_START_ANIMATING) {
            drawSpinner(canvas);
            if (this.mDrawBarWhileSpinning) {
                drawBar(canvas, f);
                drawTextWithUnit(canvas);
            } else if (this.mShowTextWhileSpinning) {
                drawTextWithUnit(canvas);
            }
        } else {
            drawBar(canvas, f);
            drawTextWithUnit(canvas);
        }
        if (this.mClippingBitmap != null) {
            canvas.drawBitmap(this.mClippingBitmap, 0.0f, 0.0f, this.mMaskPaint);
        }
    }

    private void drawSpinner(Canvas canvas) {
        if (this.mSpinningBarLengthCurrent < 0.0f) {
            this.mSpinningBarLengthCurrent = 1.0f;
        }
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.mCircleBounds, (((float) this.mStartAngle) + this.mCurrentSpinnerDegreeValue) - this.mSpinningBarLengthCurrent, this.mSpinningBarLengthCurrent, false, this.mBarSpinnerPaint);
    }

    private void drawBar(Canvas canvas, float f) {
        if (!this.mShowBlock) {
            canvas.drawArc(this.mCircleBounds, (float) this.mStartAngle, f, false, this.mBarPaint);
            return;
        }
        drawBlocks(canvas, this.mCircleBounds, (float) this.mStartAngle, f, false, this.mBarPaint);
    }

    private void drawBlocks(Canvas canvas, RectF rectF, float f, float f2, boolean z, Paint paint) {
        float f3 = 0.0f;
        while (true) {
            float f4 = f3;
            if (f4 < f2) {
                canvas.drawArc(rectF, f + f4, Math.min(this.mBlockScaleDegree, f2 - f4), z, paint);
                f3 = this.mBlockDegree + f4;
            } else {
                return;
            }
        }
    }

    private void drawTextWithUnit(Canvas canvas) {
        String valueOf;
        boolean z = false;
        if (this.mIsAutoColorEnabled) {
            this.mTextPaint.setColor(getTextColor((double) this.mCurrentValue));
        }
        switch (this.mTextMode) {
            case PERCENT:
                valueOf = String.valueOf(Math.round((100.0f / this.mMaxValue) * this.mCurrentValue));
                break;
            case VALUE:
                valueOf = String.valueOf(Math.round(this.mCurrentValue));
                break;
            default:
                if (this.mText == null) {
                    valueOf = "";
                    break;
                } else {
                    valueOf = this.mText;
                    break;
                }
        }
        if (this.mIsAutoTextSize) {
            if (this.mTextLength != valueOf.length()) {
                this.mTextLength = valueOf.length();
                if (this.mTextLength == 1) {
                    this.mOuterTextBounds = new RectF(this.mOuterTextBounds.left + (this.mOuterTextBounds.width() * 0.1f), this.mOuterTextBounds.top, this.mOuterTextBounds.right - (this.mOuterTextBounds.width() * 0.1f), this.mOuterTextBounds.bottom);
                } else {
                    this.mOuterTextBounds = getInnerCircleRect(this.mCircleBounds);
                }
                RectF rectF = this.mOuterTextBounds;
                if (this.mShowUnit) {
                    rectF = new RectF(this.mOuterTextBounds.left, this.mOuterTextBounds.top, this.mOuterTextBounds.right - ((this.mOuterTextBounds.width() * this.mRelativeUniteSize) * 1.03f), this.mOuterTextBounds.bottom);
                }
                this.mTextPaint.setTextSize(calcTextSizeForRect(valueOf, this.mTextPaint, rectF) * this.mTextScale);
                this.mActualTextBounds = getTextBounds(valueOf, this.mTextPaint, rectF);
                z = true;
            }
        } else if (this.mTextLength != valueOf.length()) {
            this.mTextLength = valueOf.length();
            this.mTextPaint.setTextSize((float) this.mTextSize);
            RectF textBounds = getTextBounds(valueOf, this.mTextPaint, this.mCircleBounds);
            this.mOuterTextBounds = textBounds;
            this.mActualTextBounds = textBounds;
            if (this.mShowUnit) {
                this.mUnitTextPaint.setTextSize((float) this.mUnitSize);
                this.mUnitBounds = getTextBounds(this.mUnit, this.mUnitTextPaint, this.mCircleBounds);
                float width = (this.mInnerCircleBound.width() * 0.05f) / 2.0f;
                this.mOuterTextBounds = new RectF((this.mOuterTextBounds.left - (this.mUnitBounds.width() / 2.0f)) - width, this.mOuterTextBounds.top, this.mOuterTextBounds.right + (this.mUnitBounds.width() / 2.0f) + width, this.mOuterTextBounds.bottom);
                this.mActualTextBounds.offset((-(this.mUnitBounds.width() / 2.0f)) - width, 0.0f);
            }
            z = true;
        }
        canvas.drawText(valueOf, this.mActualTextBounds.left - (this.mTextPaint.getTextSize() * 0.09f), this.mActualTextBounds.bottom, this.mTextPaint);
        if (this.mShowUnit) {
            if (this.mIsAutoColorEnabled) {
                this.mUnitTextPaint.setColor(getTextColor((double) this.mCurrentValue));
            }
            if (z) {
                if (this.mIsAutoTextSize) {
                    this.mUnitBounds = new RectF(this.mOuterTextBounds.left + (this.mOuterTextBounds.width() * (1.0f - this.mRelativeUniteSize) * 1.03f), this.mOuterTextBounds.top, this.mOuterTextBounds.right, this.mOuterTextBounds.bottom);
                    this.mUnitTextPaint.setTextSize(calcTextSizeForRect(this.mUnit, this.mUnitTextPaint, this.mUnitBounds) * this.mUnitScale);
                    this.mUnitBounds = getTextBounds(this.mUnit, this.mUnitTextPaint, this.mUnitBounds);
                } else {
                    float width2 = this.mInnerCircleBound.width() * 0.05f;
                    this.mUnitTextPaint.setTextSize((float) this.mUnitSize);
                    this.mUnitBounds = getTextBounds(this.mUnit, this.mUnitTextPaint, this.mCircleBounds);
                    this.mUnitBounds.offset(width2 + (this.mActualTextBounds.right - this.mUnitBounds.left), 0.0f);
                }
                this.mUnitBounds.offset(0.0f, this.mActualTextBounds.top - this.mUnitBounds.top);
            }
            canvas.drawText(this.mUnit, this.mUnitBounds.left, this.mUnitBounds.bottom, this.mUnitTextPaint);
        }
    }

    public void setValue(float f) {
        Message message = new Message();
        message.what = C4966a.SET_VALUE.ordinal();
        message.obj = new float[]{f, f};
        this.mAnimationHandler.sendMessage(message);
    }

    public void setValueAnimated(float f, long j) {
        this.mAnimationDuration = (double) j;
        Message message = new Message();
        message.what = C4966a.SET_VALUE_ANIMATED.ordinal();
        message.obj = new float[]{this.mCurrentValue, f};
        this.mAnimationHandler.sendMessage(message);
    }

    public void setValueAnimated(float f) {
        this.mAnimationDuration = 1200.0d;
        Message message = new Message();
        message.what = C4966a.SET_VALUE_ANIMATED.ordinal();
        message.obj = new float[]{this.mCurrentValue, f};
        this.mAnimationHandler.sendMessage(message);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mSeekModeEnabled) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
            case 1:
                this.mTouchEventCount = 0;
                setValueAnimated(normalizeAngle((float) Math.round(calcRotationAngleInDegrees(this.mCenter, new PointF(motionEvent.getX(), motionEvent.getY())) - ((double) this.mStartAngle))) * (this.mMaxValue / 360.0f), 800);
                return true;
            case 2:
                this.mTouchEventCount++;
                if (this.mTouchEventCount <= 5) {
                    return false;
                }
                setValue(normalizeAngle((float) Math.round(calcRotationAngleInDegrees(this.mCenter, new PointF(motionEvent.getX(), motionEvent.getY())) - ((double) this.mStartAngle))) * (this.mMaxValue / 360.0f));
                return true;
            case 3:
                this.mTouchEventCount = 0;
                return false;
            default:
                return super.onTouchEvent(motionEvent);
        }
    }

    public static float normalizeAngle(float f) {
        return ((f % 360.0f) + 360.0f) % 360.0f;
    }

    public static double calcRotationAngleInDegrees(PointF pointF, PointF pointF2) {
        double degrees = Math.toDegrees(Math.atan2((double) (pointF2.y - pointF.y), (double) (pointF2.x - pointF.x)));
        if (degrees < 0.0d) {
            return degrees + 360.0d;
        }
        return degrees;
    }
}
