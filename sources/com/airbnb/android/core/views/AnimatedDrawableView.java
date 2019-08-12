package com.airbnb.android.core.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.Strap;

public abstract class AnimatedDrawableView extends FrameLayout {
    public static final int DEFAULT_FRAMERATE = 36;
    /* access modifiers changed from: private */
    public Options bitmapOptions;
    /* access modifiers changed from: private */
    public int frameIndex;
    /* access modifiers changed from: private */
    public boolean hasOomOccurredInLastWeek;
    @BindView
    ImageView imageView;
    private boolean isAttachedToWindow;
    private boolean isRunning;
    final Runnable mRunnable;
    MemoryUtils memoryUtils;
    private boolean runOnAttached;
    /* access modifiers changed from: private */
    public Bitmap spinnerImage;

    /* access modifiers changed from: protected */
    public abstract int[] getImageResources();

    public AnimatedDrawableView(Context context) {
        this(context, null);
    }

    public AnimatedDrawableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatedDrawableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mRunnable = new Runnable() {
            private boolean inBitmapFailReported = false;

            public void run() {
                AnimatedDrawableView.this.removeCallbacks(this);
                if (!AnimatedDrawableView.this.shouldNotRun()) {
                    if (!AnimatedDrawableView.this.hasOomOccurredInLastWeek) {
                        AnimatedDrawableView.this.postDelayed(this, (long) (1000 / AnimatedDrawableView.this.getFrameRate()));
                    }
                    AnimatedDrawableView.this.frameIndex = (AnimatedDrawableView.this.frameIndex + 1) % AnimatedDrawableView.this.getImageResources().length;
                    if (AnimatedDrawableView.this.spinnerImage != null) {
                        AnimatedDrawableView.this.bitmapOptions.inBitmap = AnimatedDrawableView.this.spinnerImage;
                    }
                    try {
                        AnimatedDrawableView.this.spinnerImage = BitmapFactory.decodeResource(AnimatedDrawableView.this.getResources(), AnimatedDrawableView.this.getImageResources()[AnimatedDrawableView.this.frameIndex], AnimatedDrawableView.this.bitmapOptions);
                    } catch (IllegalArgumentException e) {
                        try {
                            if (!this.inBitmapFailReported) {
                                AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv(AirbnbConstants.IN_BITMAP_FAILED, "true").mo11639kv("action", "animated_drawable_view"));
                                this.inBitmapFailReported = true;
                            }
                            AnimatedDrawableView.this.bitmapOptions.inBitmap = null;
                            AnimatedDrawableView.this.spinnerImage = BitmapFactory.decodeResource(AnimatedDrawableView.this.getResources(), AnimatedDrawableView.this.getImageResources()[AnimatedDrawableView.this.frameIndex], AnimatedDrawableView.this.bitmapOptions);
                        } catch (OutOfMemoryError e2) {
                            AnimatedDrawableView.this.handleOom();
                        }
                    }
                    AnimatedDrawableView.this.imageView.setImageBitmap(AnimatedDrawableView.this.spinnerImage);
                }
            }
        };
        if (!isInEditMode()) {
            ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
            init(context);
        }
    }

    private void init(Context ctx) {
        ButterKnife.bind(this, LayoutInflater.from(ctx).inflate(C0716R.layout.spinner, this, true));
        int padding = getPadding();
        setPadding(padding, padding, padding, padding);
        setBackgroundResource(getBackgroundResource());
        initBitmapOptions();
        this.hasOomOccurredInLastWeek = this.memoryUtils.hasOomOccurredInLastWeek();
    }

    private void initBitmapOptions() {
        this.bitmapOptions = new Options();
        this.bitmapOptions.inMutable = true;
        if (!AndroidVersion.isAtLeastKitKat()) {
            this.bitmapOptions.inSampleSize = 1;
        }
        this.bitmapOptions.inPreferredConfig = Config.ARGB_8888;
    }

    /* access modifiers changed from: protected */
    public int getPadding() {
        return getContext().getResources().getDimensionPixelSize(C0716R.dimen.gutter_padding);
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResource() {
        return C0716R.C0717drawable.bg_transparent;
    }

    public void startAnimation() {
        if (!isAttached()) {
            this.isRunning = false;
            this.runOnAttached = true;
            return;
        }
        setVisibility(0);
        removeCallbacks(this.mRunnable);
        if (this.hasOomOccurredInLastWeek) {
            this.isRunning = false;
            this.imageView.setImageResource(getFallbackResource());
            return;
        }
        this.isRunning = true;
        this.mRunnable.run();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (this.runOnAttached) {
            this.runOnAttached = false;
            startAnimation();
        }
    }

    public void stopAnimation() {
        this.runOnAttached = false;
        this.isRunning = false;
        removeCallbacks(this.mRunnable);
        setVisibility(8);
        this.spinnerImage = null;
        this.bitmapOptions.inBitmap = null;
        this.imageView.setImageDrawable(null);
    }

    /* access modifiers changed from: private */
    public boolean shouldNotRun() {
        return !this.isRunning || !isAttached();
    }

    public void handleOom() {
        this.memoryUtils.handleCaughtOOM("animated_drawable_view");
        this.hasOomOccurredInLastWeek = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        stopAnimation();
    }

    private boolean isAttached() {
        return this.isAttachedToWindow;
    }

    /* access modifiers changed from: protected */
    public int getFrameRate() {
        return 36;
    }

    /* access modifiers changed from: protected */
    public int getFallbackResource() {
        return 0;
    }
}
