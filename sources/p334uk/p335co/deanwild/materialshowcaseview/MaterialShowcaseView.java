package p334uk.p335co.deanwild.materialshowcaseview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import p334uk.p335co.deanwild.materialshowcaseview.IAnimationFactory.AnimationEndListener;
import p334uk.p335co.deanwild.materialshowcaseview.IAnimationFactory.AnimationStartListener;
import p334uk.p335co.deanwild.materialshowcaseview.shape.CircleShape;
import p334uk.p335co.deanwild.materialshowcaseview.shape.NoShape;
import p334uk.p335co.deanwild.materialshowcaseview.shape.RectangleShape;
import p334uk.p335co.deanwild.materialshowcaseview.shape.Shape;
import p334uk.p335co.deanwild.materialshowcaseview.target.Target;
import p334uk.p335co.deanwild.materialshowcaseview.target.ViewTarget;

/* renamed from: uk.co.deanwild.materialshowcaseview.MaterialShowcaseView */
public class MaterialShowcaseView extends FrameLayout implements OnClickListener, OnTouchListener {
    protected static final PorterDuffXfermode CLEAR_PORTER_DUFF_XFER_MODE = new PorterDuffXfermode(Mode.CLEAR);
    private View contentView;
    private AnimationFactory mAnimationFactory;
    private Bitmap mBitmap;
    private int mBottomMargin = 0;
    private Canvas mCanvas;
    private int mContentBottomMargin;
    private View mContentBox;
    private TextView mContentTextView;
    private int mContentTopMargin;
    private long mDelayInMillis = 0;
    private IDetachedListener mDetachedListener;
    private View mDismissButton;
    private boolean mDismissOnTargetTouch = true;
    private boolean mDismissOnTouch = false;
    private Paint mEraser;
    private long mFadeDurationInMillis = 300;
    private int mGravity;
    private Handler mHandler;
    private UpdateOnGlobalLayout mLayoutListener;
    private List<IShowcaseListener> mListeners;
    private int mMaskColour;
    private int mOldHeight;
    private int mOldWidth;
    private PrefsManager mPrefsManager;
    private boolean mRenderOverNav = false;
    /* access modifiers changed from: private */
    public Shape mShape;
    private int mShapePadding = 10;
    /* access modifiers changed from: private */
    public boolean mShouldAnimate = true;
    private boolean mShouldRender = false;
    private boolean mSingleUse = false;
    /* access modifiers changed from: private */
    public Target mTarget;
    private boolean mTargetTouchable = false;
    private TextView mTitleTextView;
    private boolean mWasDismissed = false;
    private int mXPosition;
    private int mYPosition;

    /* renamed from: uk.co.deanwild.materialshowcaseview.MaterialShowcaseView$Builder */
    public static class Builder {
        private final Activity activity;
        private boolean fullWidth = false;
        private int shapeType = 0;
        protected MaterialShowcaseView showcaseView;

        public Builder(Activity activity2) {
            this.activity = activity2;
            init(activity2);
        }

        /* access modifiers changed from: protected */
        public void init(Activity activity2) {
            this.showcaseView = new MaterialShowcaseView(activity2);
        }

        public Builder setTarget(View target) {
            this.showcaseView.setTarget(new ViewTarget(target));
            return this;
        }

        public Builder setDismissText(CharSequence dismissText) {
            this.showcaseView.setDismissText(dismissText);
            return this;
        }

        public Builder setTitleText(CharSequence text) {
            this.showcaseView.setTitleText(text);
            return this;
        }

        public Builder setTargetTouchable(boolean targetTouchable) {
            this.showcaseView.setTargetTouchable(targetTouchable);
            return this;
        }

        public Builder setDismissOnTargetTouch(boolean dismissOnTargetTouch) {
            this.showcaseView.setDismissOnTargetTouch(dismissOnTargetTouch);
            return this;
        }

        public Builder setDismissOnTouch(boolean dismissOnTouch) {
            this.showcaseView.setDismissOnTouch(dismissOnTouch);
            return this;
        }

        public Builder setMaskColour(int maskColour) {
            this.showcaseView.setMaskColour(maskColour);
            return this;
        }

        public Builder setShapePadding(int padding) {
            this.showcaseView.setShapePadding(padding);
            return this;
        }

        public MaterialShowcaseView build() {
            if (this.showcaseView.mShape == null) {
                switch (this.shapeType) {
                    case 0:
                        this.showcaseView.setShape(new CircleShape(this.showcaseView.mTarget));
                        break;
                    case 1:
                        this.showcaseView.setShape(new RectangleShape(this.showcaseView.mTarget.getBounds(), this.fullWidth));
                        break;
                    case 2:
                        this.showcaseView.setShape(new NoShape());
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported shape type: " + this.shapeType);
                }
            }
            return this.showcaseView;
        }

        public MaterialShowcaseView show() {
            build().show(this.activity);
            return this.showcaseView;
        }
    }

    /* renamed from: uk.co.deanwild.materialshowcaseview.MaterialShowcaseView$UpdateOnGlobalLayout */
    private class UpdateOnGlobalLayout implements OnGlobalLayoutListener {
        private UpdateOnGlobalLayout() {
        }

        public void onGlobalLayout() {
            MaterialShowcaseView.this.setTarget(MaterialShowcaseView.this.mTarget);
        }
    }

    public MaterialShowcaseView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        setWillNotDraw(false);
        this.mAnimationFactory = new AnimationFactory();
        this.mListeners = new ArrayList();
        this.mLayoutListener = new UpdateOnGlobalLayout();
        getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
        setOnTouchListener(this);
        this.mMaskColour = Color.parseColor("#dd335075");
        setVisibility(4);
        this.contentView = LayoutInflater.from(getContext()).inflate(getLayoutFile(), this, true);
        this.mContentBox = this.contentView.findViewById(getContentBoxViewId());
        this.mTitleTextView = (TextView) this.contentView.findViewById(getTitleViewId());
        this.mDismissButton = this.contentView.findViewById(getButtonViewId());
        this.mDismissButton.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public int getContentBoxViewId() {
        return R.id.content_box;
    }

    /* access modifiers changed from: protected */
    public int getTitleViewId() {
        return R.id.tv_title;
    }

    /* access modifiers changed from: protected */
    public int getContentViewId() {
        return R.id.tv_content;
    }

    /* access modifiers changed from: protected */
    public int getButtonViewId() {
        return R.id.tv_dismiss;
    }

    public int getLayoutFile() {
        return R.layout.showcase_content;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mShouldRender) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            if (width > 0 && height > 0) {
                if (this.mBitmap == null || this.mCanvas == null || this.mOldHeight != height || this.mOldWidth != width) {
                    if (this.mBitmap != null) {
                        this.mBitmap.recycle();
                    }
                    this.mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                    this.mCanvas = new Canvas(this.mBitmap);
                }
                this.mOldWidth = width;
                this.mOldHeight = height;
                this.mCanvas.drawColor(0, Mode.CLEAR);
                this.mCanvas.drawColor(this.mMaskColour);
                if (this.mEraser == null) {
                    this.mEraser = new Paint();
                    this.mEraser.setColor(-1);
                    this.mEraser.setXfermode(CLEAR_PORTER_DUFF_XFER_MODE);
                    this.mEraser.setFlags(1);
                }
                this.mShape.draw(this.mCanvas, this.mEraser, this.mXPosition, this.mYPosition, this.mShapePadding);
                drawStroke(this.mCanvas, this.mEraser, this.mShape, this.mXPosition, this.mYPosition, this.mShapePadding);
                canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawStroke(Canvas canvas, Paint eraser, Shape shape, int xPosition, int yPosition, int shapePadding) {
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!this.mWasDismissed && this.mSingleUse && this.mPrefsManager != null) {
            this.mPrefsManager.resetShowcase();
        }
        notifyOnDismissed();
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (this.mDismissOnTouch) {
            hide();
        }
        if (!isTouchOnTargetView(event)) {
            return true;
        }
        if (this.mDismissOnTargetTouch) {
            hide();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isTouchOnTargetView(MotionEvent event) {
        return this.mTargetTouchable && this.mTarget.getBounds().contains((int) event.getX(), (int) event.getY());
    }

    /* access modifiers changed from: private */
    public void notifyOnDisplayed() {
        if (this.mListeners != null) {
            for (IShowcaseListener listener : this.mListeners) {
                listener.onShowcaseDisplayed(this);
            }
        }
    }

    private void notifyOnDismissed() {
        if (this.mListeners != null) {
            for (IShowcaseListener listener : this.mListeners) {
                listener.onShowcaseDismissed(this);
            }
            this.mListeners.clear();
            this.mListeners = null;
        }
        if (this.mDetachedListener != null) {
            this.mDetachedListener.onShowcaseDetached(this, this.mWasDismissed);
        }
    }

    public void onClick(View v) {
        hide();
    }

    public void setTarget(Target target) {
        this.mTarget = target;
        updateDismissButton();
        if (this.mTarget != null) {
            if (!this.mRenderOverNav && VERSION.SDK_INT >= 21) {
                this.mBottomMargin = getSoftButtonsBarSizePort((Activity) getContext());
                LayoutParams contentLP = (LayoutParams) getLayoutParams();
                if (!(contentLP == null || contentLP.bottomMargin == this.mBottomMargin)) {
                    contentLP.bottomMargin = this.mBottomMargin;
                }
            }
            Point targetPoint = this.mTarget.getPoint();
            Rect targetBounds = this.mTarget.getBounds();
            setPosition(targetPoint);
            int height = getMeasuredHeight();
            int midPoint = height / 2;
            int yPos = targetPoint.y;
            int radius = Math.max(targetBounds.height(), targetBounds.width()) / 2;
            if (this.mShape != null) {
                this.mShape.updateTarget(this.mTarget);
                radius = this.mShape.getHeight() / 2;
            }
            if (yPos > midPoint) {
                this.mContentTopMargin = 0;
                this.mContentBottomMargin = (height - yPos) + radius + this.mShapePadding;
                this.mGravity = 80;
            } else {
                this.mContentTopMargin = yPos + radius + this.mShapePadding;
                this.mContentBottomMargin = 0;
                this.mGravity = 48;
            }
        }
        applyLayoutParams();
    }

    private void applyLayoutParams() {
        if (this.mContentBox != null && this.mContentBox.getLayoutParams() != null) {
            LayoutParams contentLP = (LayoutParams) this.mContentBox.getLayoutParams();
            boolean layoutParamsChanged = false;
            if (contentLP.bottomMargin != this.mContentBottomMargin) {
                contentLP.bottomMargin = this.mContentBottomMargin;
                layoutParamsChanged = true;
            }
            if (contentLP.topMargin != this.mContentTopMargin) {
                contentLP.topMargin = this.mContentTopMargin;
                layoutParamsChanged = true;
            }
            if (contentLP.gravity != this.mGravity) {
                contentLP.gravity = this.mGravity;
                layoutParamsChanged = true;
            }
            if (layoutParamsChanged) {
                this.mContentBox.setLayoutParams(contentLP);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setPosition(Point point) {
        setPosition(point.x, point.y);
    }

    /* access modifiers changed from: 0000 */
    public void setPosition(int x, int y) {
        this.mXPosition = x;
        this.mYPosition = y;
    }

    /* access modifiers changed from: private */
    public void setTitleText(CharSequence contentText) {
        if (this.mTitleTextView != null && !contentText.equals("")) {
            if (this.mContentTextView != null) {
                this.mContentTextView.setAlpha(0.5f);
            }
            this.mTitleTextView.setText(contentText);
        }
    }

    private void setContentText(CharSequence contentText) {
        this.mContentTextView = (TextView) this.contentView.findViewById(getContentViewId());
        if (this.mContentTextView != null) {
            this.mContentTextView.setText(contentText);
        }
    }

    /* access modifiers changed from: private */
    public void setDismissText(CharSequence dismissText) {
        if (this.mDismissButton != null) {
            if (this.mDismissButton instanceof TextView) {
                ((TextView) this.mDismissButton).setText(dismissText);
            } else if (this.mDismissButton instanceof Button) {
                ((Button) this.mDismissButton).setText(dismissText);
            } else {
                return;
            }
            updateDismissButton();
        }
    }

    private void setTitleTextColor(int textColour) {
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextColor(textColour);
        }
    }

    private void setContentTextColor(int textColour) {
        if (this.mContentTextView != null) {
            this.mContentTextView.setTextColor(textColour);
        }
    }

    private void setDismissTextColor(int textColour) {
        if (this.mDismissButton != null) {
            if (this.mDismissButton instanceof TextView) {
                ((TextView) this.mDismissButton).setTextColor(textColour);
            } else if (this.mDismissButton instanceof Button) {
                ((TextView) this.mDismissButton).setTextColor(textColour);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setShapePadding(int padding) {
        this.mShapePadding = padding;
    }

    /* access modifiers changed from: private */
    public void setDismissOnTouch(boolean dismissOnTouch) {
        this.mDismissOnTouch = dismissOnTouch;
    }

    private void setShouldRender(boolean shouldRender) {
        this.mShouldRender = shouldRender;
    }

    /* access modifiers changed from: private */
    public void setMaskColour(int maskColour) {
        this.mMaskColour = maskColour;
    }

    private void setDelay(long delayInMillis) {
        this.mDelayInMillis = delayInMillis;
    }

    private void setFadeDuration(long fadeDurationInMillis) {
        this.mFadeDurationInMillis = fadeDurationInMillis;
    }

    /* access modifiers changed from: private */
    public void setTargetTouchable(boolean targetTouchable) {
        this.mTargetTouchable = targetTouchable;
    }

    /* access modifiers changed from: private */
    public void setDismissOnTargetTouch(boolean dismissOnTargetTouch) {
        this.mDismissOnTargetTouch = dismissOnTargetTouch;
    }

    /* access modifiers changed from: 0000 */
    public void setDetachedListener(IDetachedListener detachedListener) {
        this.mDetachedListener = detachedListener;
    }

    public void setShape(Shape mShape2) {
        this.mShape = mShape2;
    }

    public void setConfig(ShowcaseConfig config) {
        setDelay(config.getDelay());
        setFadeDuration(config.getFadeDuration());
        setContentTextColor(config.getContentTextColor());
        setDismissTextColor(config.getDismissTextColor());
        setMaskColour(config.getMaskColor());
        setShape(config.getShape());
        setShapePadding(config.getShapePadding());
        setRenderOverNavigationBar(config.getRenderOverNavigationBar());
    }

    private void updateDismissButton() {
        if (this.mDismissButton != null) {
            CharSequence text = null;
            if (this.mDismissButton instanceof TextView) {
                text = ((TextView) this.mDismissButton).getText();
            } else if (this.mDismissButton instanceof Button) {
                text = ((Button) this.mDismissButton).getText();
            }
            if (TextUtils.isEmpty(text)) {
                this.mDismissButton.setVisibility(8);
            } else {
                this.mDismissButton.setVisibility(0);
            }
        }
    }

    public void removeFromWindow() {
        if (getParent() != null && (getParent() instanceof ViewGroup)) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (this.mBitmap != null) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
        this.mEraser = null;
        this.mAnimationFactory = null;
        this.mCanvas = null;
        this.mHandler = null;
        getViewTreeObserver().removeGlobalOnLayoutListener(this.mLayoutListener);
        this.mLayoutListener = null;
        if (this.mPrefsManager != null) {
            this.mPrefsManager.close();
        }
        this.mPrefsManager = null;
    }

    public boolean show(Activity activity) {
        if (this.mSingleUse) {
            if (this.mPrefsManager.hasFired()) {
                return false;
            }
            this.mPrefsManager.setFired();
        }
        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
        setShouldRender(true);
        this.mHandler = new Handler();
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (MaterialShowcaseView.this.mShouldAnimate) {
                    MaterialShowcaseView.this.fadeIn();
                    return;
                }
                MaterialShowcaseView.this.setVisibility(0);
                MaterialShowcaseView.this.notifyOnDisplayed();
            }
        }, this.mDelayInMillis);
        updateDismissButton();
        return true;
    }

    public void hide() {
        this.mWasDismissed = true;
        if (this.mShouldAnimate) {
            fadeOut();
        } else {
            removeFromWindow();
        }
    }

    public void fadeIn() {
        setVisibility(4);
        this.mAnimationFactory.fadeInView(this, this.mFadeDurationInMillis, new AnimationStartListener() {
            public void onAnimationStart() {
                MaterialShowcaseView.this.setVisibility(0);
                MaterialShowcaseView.this.notifyOnDisplayed();
            }
        });
    }

    public void fadeOut() {
        this.mAnimationFactory.fadeOutView(this, this.mFadeDurationInMillis, new AnimationEndListener() {
            public void onAnimationEnd() {
                MaterialShowcaseView.this.setVisibility(4);
                MaterialShowcaseView.this.removeFromWindow();
            }
        });
    }

    public static int getSoftButtonsBarSizePort(Activity activity) {
        if (VERSION.SDK_INT < 17) {
            return 0;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        }
        return 0;
    }

    private void setRenderOverNavigationBar(boolean mRenderOverNav2) {
        this.mRenderOverNav = mRenderOverNav2;
    }
}
