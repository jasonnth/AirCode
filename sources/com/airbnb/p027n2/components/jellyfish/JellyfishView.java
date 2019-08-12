package com.airbnb.p027n2.components.jellyfish;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.os.AsyncTask;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.utils.GammaEvaluator;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* renamed from: com.airbnb.n2.components.jellyfish.JellyfishView */
public class JellyfishView extends FrameLayout {
    private static final int[] BABU_PALETTE = {R.color.n2_jellyfish_babu_c1, R.color.n2_jellyfish_babu_c2, R.color.n2_jellyfish_babu_c3, R.color.n2_jellyfish_babu_c4};
    private static final int BABU_PALETTE_BG = R.color.n2_jellyfish_babu_bg;
    private static final int[] HACKBERRY_PALETTE = {R.color.n2_jellyfish_hackberry_c1, R.color.n2_jellyfish_hackberry_c2, R.color.n2_jellyfish_hackberry_c3, R.color.n2_jellyfish_hackberry_c4};
    private static final int HACKBERRY_PALETTE_BG = R.color.n2_jellyfish_hackberry_bg;
    private static final int[] RAUSCH_PALETTE = {R.color.n2_jellyfish_rausch_c1, R.color.n2_jellyfish_rausch_c2, R.color.n2_jellyfish_rausch_c3, R.color.n2_jellyfish_rausch_c4};
    private static final int RAUSCH_PALETTE_BG = R.color.n2_jellyfish_rausch_bg;
    /* access modifiers changed from: private */
    public static WeakReference<Bitmap> cachedBitmap;
    private static boolean forceDisableMovement = false;
    private boolean animateNextLayout;
    private int[] animatingPalette;
    private final Runnable animationTickRunnable;
    /* access modifiers changed from: private */
    public final List<JellyfishValueAnimator> animators;
    int backgroundColor;
    private float batteryPercentage;
    private final ValueAnimator fadeInAnimator;
    /* access modifiers changed from: private */
    public Bitmap gradientBitmap;
    private AsyncTask<?, ?, ?> gradientBitmapLoadTask;
    private boolean hasTimedOut;
    private final Interpolator interpolator;
    private boolean isAnimating;
    private boolean isCharging;
    private long lastVelocityUpdateTime;
    int oldBackgroundColor;
    private int[] oldPalette;
    private int[] palette;
    /* access modifiers changed from: private */
    public ValueAnimator paletteAnimator;
    private Random random;
    private float[] targetX;
    private float[] targetY;
    private final Runnable timeOutRunnable;
    private float timeScale;
    private float[] velocityX;
    private float[] velocityY;

    static /* synthetic */ void lambda$new$0(JellyfishView jellyfishView) {
        jellyfishView.hasTimedOut = true;
        jellyfishView.startOrStopAnimationIfNeeded();
    }

    public JellyfishView(Context context) {
        super(context);
        this.interpolator = new FastOutSlowInInterpolator();
        this.palette = null;
        this.oldPalette = null;
        this.animatingPalette = null;
        this.animators = new ArrayList();
        this.timeOutRunnable = JellyfishView$$Lambda$1.lambdaFactory$(this);
        this.animationTickRunnable = new Runnable() {
            public void run() {
                for (JellyfishValueAnimator animator : JellyfishView.this.animators) {
                    animator.tick();
                }
                JellyfishView.this.updateTranslation();
                JellyfishView.this.postDelayed(this, 83);
            }
        };
        this.fadeInAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(300);
        this.timeScale = 1.0f;
        this.isAnimating = false;
        init(null);
    }

    public JellyfishView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.interpolator = new FastOutSlowInInterpolator();
        this.palette = null;
        this.oldPalette = null;
        this.animatingPalette = null;
        this.animators = new ArrayList();
        this.timeOutRunnable = JellyfishView$$Lambda$2.lambdaFactory$(this);
        this.animationTickRunnable = new Runnable() {
            public void run() {
                for (JellyfishValueAnimator animator : JellyfishView.this.animators) {
                    animator.tick();
                }
                JellyfishView.this.updateTranslation();
                JellyfishView.this.postDelayed(this, 83);
            }
        };
        this.fadeInAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(300);
        this.timeScale = 1.0f;
        this.isAnimating = false;
        init(attrs);
    }

    public JellyfishView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.interpolator = new FastOutSlowInInterpolator();
        this.palette = null;
        this.oldPalette = null;
        this.animatingPalette = null;
        this.animators = new ArrayList();
        this.timeOutRunnable = JellyfishView$$Lambda$3.lambdaFactory$(this);
        this.animationTickRunnable = new Runnable() {
            public void run() {
                for (JellyfishValueAnimator animator : JellyfishView.this.animators) {
                    animator.tick();
                }
                JellyfishView.this.updateTranslation();
                JellyfishView.this.postDelayed(this, 83);
            }
        };
        this.fadeInAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(300);
        this.timeScale = 1.0f;
        this.isAnimating = false;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        this.random = new Random(System.currentTimeMillis());
        this.batteryPercentage = ViewLibUtils.getBatteryLevel(getContext());
        this.isCharging = ViewLibUtils.getBatteryState(getContext()) == 2;
        this.fadeInAnimator.setInterpolator(this.interpolator);
        if (cachedBitmap == null || cachedBitmap.get() == null) {
            loadOrCreateBitmap();
        } else {
            this.gradientBitmap = (Bitmap) cachedBitmap.get();
        }
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_JellyfishView);
            setPalette(ta.getInt(R.styleable.n2_JellyfishView_n2_palette, 1), false);
            ta.recycle();
        }
    }

    public void setPalette(int palette2) {
        setPalette(palette2, true);
    }

    public void setPalette(int palette2, boolean animate) {
        switch (palette2) {
            case 1:
                setPaletteRes(BABU_PALETTE_BG, BABU_PALETTE, animate);
                return;
            case 2:
                setPaletteRes(RAUSCH_PALETTE_BG, RAUSCH_PALETTE, animate);
                return;
            case 3:
                setPaletteRes(HACKBERRY_PALETTE_BG, HACKBERRY_PALETTE, animate);
                return;
            default:
                return;
        }
    }

    public void setPaletteRes(int backgroundColorRes, int[] paletteRes, boolean animate) {
        int[] palette2 = new int[paletteRes.length];
        for (int i = 0; i < palette2.length; i++) {
            palette2[i] = ContextCompat.getColor(getContext(), paletteRes[i]);
        }
        setPalette(ContextCompat.getColor(getContext(), backgroundColorRes), palette2, animate);
    }

    public void setPalette(int backgroundColor2, int[] palette2, boolean animate) {
        if (this.palette == null) {
            this.targetX = new float[palette2.length];
            this.targetY = new float[palette2.length];
            this.velocityX = new float[palette2.length];
            this.velocityY = new float[palette2.length];
            this.palette = palette2;
            this.backgroundColor = backgroundColor2;
            addChildren();
            setPaletteInternal(backgroundColor2, palette2);
        } else if (palette2.length != this.palette.length) {
            throw new IllegalArgumentException("Palletes must be the same length!");
        } else {
            this.oldPalette = this.palette;
            this.oldBackgroundColor = this.backgroundColor;
            this.backgroundColor = backgroundColor2;
            this.palette = palette2;
            if (this.animatingPalette == null) {
                this.animatingPalette = new int[this.oldPalette.length];
            }
            if (animate) {
                animatePalette();
            } else {
                setPaletteInternal(backgroundColor2, palette2);
            }
        }
    }

    private void animatePalette() {
        if (this.paletteAnimator != null) {
            this.paletteAnimator.cancel();
        } else {
            this.paletteAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.paletteAnimator.setDuration(1500);
            this.paletteAnimator.addUpdateListener(JellyfishView$$Lambda$4.lambdaFactory$(this));
            this.paletteAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    JellyfishView.this.paletteAnimator = null;
                }
            });
        }
        if (this.paletteAnimator != null) {
            this.paletteAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public void onPaletteAnimationUpdate(float progress) {
        for (int i = 0; i < this.animatingPalette.length; i++) {
            this.animatingPalette[i] = GammaEvaluator.evaluate(progress, this.oldPalette[i], this.palette[i]);
        }
        setPaletteInternal(GammaEvaluator.evaluate(progress, this.oldBackgroundColor, this.backgroundColor), this.animatingPalette);
    }

    private void setPaletteInternal(int bg, int[] palette2) {
        setBackgroundColor(bg);
        for (int i = getChildCount() - 1; i >= 0; i--) {
            ((ImageView) getChildAt(i)).setColorFilter(palette2[i], Mode.SRC_IN);
        }
    }

    public void setTimeScale(float timeScale2) {
        this.timeScale = timeScale2;
    }

    private void addChildren() {
        int[] iArr;
        if (this.palette == null) {
            throw new IllegalStateException("You must set a palette first.");
        }
        for (int i : this.palette) {
            ImageView child = new JellyfishImageView(getContext());
            child.setAlpha(0.0f);
            addView(child);
        }
        addGradientsToChildrenIfPossible();
        this.animateNextLayout = true;
    }

    private void addGradientsToChildrenIfPossible() {
        if (this.gradientBitmap != null) {
            for (int i = getChildCount() - 1; i >= 0; i--) {
                ImageView child = (ImageView) getChildAt(i);
                child.setLayoutParams(new LayoutParams(this.gradientBitmap.getWidth(), this.gradientBitmap.getHeight()));
                child.setImageBitmap(this.gradientBitmap);
            }
            this.fadeInAnimator.start();
        }
    }

    public void notifySubtreeAccessibilityStateChanged(View child, View source, int changeType) {
    }

    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (oldw != 0 && oldh != 0 && this.targetX != null) {
            for (int i = 0; i < this.targetX.length; i++) {
                this.targetX[i] = generateRandomFloat(((float) getWidth()) * 0.2f, ((float) getWidth()) * 0.8f);
                this.targetY[i] = generateRandomFloat(((float) getHeight()) * 0.2f, ((float) getHeight()) * 0.8f);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.animateNextLayout && getWidth() != 0 && getHeight() != 0) {
            addAnimationToChildren();
            this.animateNextLayout = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.hasTimedOut = false;
        postDelayed(this.timeOutRunnable, 45000);
        startOrStopAnimationIfNeeded();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.animationTickRunnable);
        removeCallbacks(this.timeOutRunnable);
        if (ViewLibUtils.isAtLeastLollipop()) {
            stopAnimatingLollipop();
        }
        if (this.gradientBitmapLoadTask != null) {
            this.gradientBitmapLoadTask.cancel(true);
        }
        super.onDetachedFromWindow();
    }

    private void addAnimationToChildren() {
        if (this.animators.isEmpty()) {
            for (int i = getChildCount() - 1; i >= 0; i--) {
                View child = getChildAt(i);
                List<JellyfishValueAnimator> list = this.animators;
                Interpolator interpolator2 = this.interpolator;
                Random random2 = this.random;
                child.getClass();
                list.add(new JellyfishValueAnimator(4000, interpolator2, random2, 1.0f, 1.75f, JellyfishView$$Lambda$5.lambdaFactory$(child)));
                List<JellyfishValueAnimator> list2 = this.animators;
                Interpolator interpolator3 = this.interpolator;
                Random random3 = this.random;
                child.getClass();
                list2.add(new JellyfishValueAnimator(4000, interpolator3, random3, 1.0f, 1.75f, JellyfishView$$Lambda$6.lambdaFactory$(child)));
                List<JellyfishValueAnimator> list3 = this.animators;
                Interpolator interpolator4 = this.interpolator;
                Random random4 = this.random;
                child.getClass();
                list3.add(new JellyfishValueAnimator(7000, interpolator4, random4, -30.0f, 30.0f, JellyfishView$$Lambda$7.lambdaFactory$(child)));
                this.animators.add(new JellyfishValueAnimator(4000, this.interpolator, this.random, 0.9f, 1.0f, JellyfishView$$Lambda$8.lambdaFactory$(this, child)));
            }
            this.lastVelocityUpdateTime = System.currentTimeMillis();
            startOrStopAnimationIfNeeded();
        }
    }

    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
        startOrStopAnimationIfNeeded();
    }

    private boolean shouldAnimate() {
        return this.gradientBitmap != null && getAlpha() > 0.01f && !this.hasTimedOut;
    }

    private boolean deviceSupportsAnimations() {
        return !forceDisableMovement && ViewLibUtils.isAtLeastLollipop() && (this.batteryPercentage >= 0.4f || this.isCharging);
    }

    private void startOrStopAnimationIfNeeded() {
        if (this.animators != null && !this.animators.isEmpty() && deviceSupportsAnimations()) {
            startOrStopAnimationIfNeededLollipop();
        }
    }

    @TargetApi(21)
    private void startOrStopAnimationIfNeededLollipop() {
        boolean isAnimating2 = shouldAnimate();
        if (!(this.isAnimating != isAnimating2)) {
            return;
        }
        if (isAnimating2) {
            startAnimatingLollipop();
        } else {
            stopAnimatingLollipop();
        }
    }

    @TargetApi(21)
    private void startAnimatingLollipop() {
        this.isAnimating = true;
        removeCallbacks(this.animationTickRunnable);
        post(this.animationTickRunnable);
    }

    @TargetApi(21)
    private void stopAnimatingLollipop() {
        this.isAnimating = false;
        removeCallbacks(this.animationTickRunnable);
    }

    private float generateRandomFloat(float minValue, float maxValue) {
        return (this.random.nextFloat() * (maxValue - minValue)) + minValue;
    }

    private void loadOrCreateBitmap() {
        if (new File(getContext().getFilesDir() + "/" + "jellyfish_gradient_v1.png").exists()) {
            loadBitmapAsync();
        } else {
            createBitmapAsync();
        }
    }

    private void loadBitmapAsync() {
        this.gradientBitmapLoadTask = new AsyncTask<Integer, Void, Bitmap>() {
            /* access modifiers changed from: protected */
            public Bitmap doInBackground(Integer... integers) {
                boolean z = false;
                Options options = new Options();
                options.inPreferredConfig = Config.ALPHA_8;
                try {
                    return BitmapFactory.decodeStream(new FileInputStream(JellyfishView.this.getContext().getFilesDir() + "/" + "jellyfish_gradient_v1.png"), null, options);
                } catch (FileNotFoundException e) {
                    Log.e(JellyfishView.class.getSimpleName(), "Couldn't find JellyFish image file, even though it should exist...", e);
                    return z;
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Bitmap bitmap) {
                if (bitmap == null) {
                    JellyfishView.this.createBitmapAsync();
                } else {
                    JellyfishView.this.onBitmapLoaded(bitmap);
                }
            }
        }.execute(new Integer[0]);
    }

    /* access modifiers changed from: private */
    public void createBitmapAsync() {
        Point screenSize = ViewLibUtils.getScreenSize(getContext());
        int size = (int) Math.min(((float) Math.max(screenSize.x, screenSize.y)) * 0.8f, 1440.0f);
        new AsyncTask<Integer, Void, Bitmap>() {
            /* access modifiers changed from: protected */
            /* JADX WARNING: Unknown top exception splitter block from list: {B:14:0x0043=Splitter:B:14:0x0043, B:27:0x0067=Splitter:B:27:0x0067} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.graphics.Bitmap doInBackground(java.lang.Integer... r9) {
                /*
                    r8 = this;
                    r4 = 0
                    r4 = r9[r4]
                    int r2 = r4.intValue()
                    java.lang.Class<com.airbnb.n2.components.jellyfish.JellyfishView> r5 = com.airbnb.p027n2.components.jellyfish.JellyfishView.class
                    monitor-enter(r5)
                    java.lang.ref.WeakReference r4 = com.airbnb.p027n2.components.jellyfish.JellyfishView.cachedBitmap     // Catch:{ all -> 0x0068 }
                    if (r4 == 0) goto L_0x001a
                    java.lang.ref.WeakReference r4 = com.airbnb.p027n2.components.jellyfish.JellyfishView.cachedBitmap     // Catch:{ all -> 0x0068 }
                    java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0068 }
                    if (r4 != 0) goto L_0x006b
                L_0x001a:
                    com.airbnb.n2.components.jellyfish.JellyfishView r4 = com.airbnb.p027n2.components.jellyfish.JellyfishView.this     // Catch:{ all -> 0x0068 }
                    android.graphics.Bitmap r0 = r4.createBitmapSync(r2)     // Catch:{ all -> 0x0068 }
                    long r6 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0068 }
                    java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0068 }
                    r1 = 0
                    com.airbnb.n2.components.jellyfish.JellyfishView r4 = com.airbnb.p027n2.components.jellyfish.JellyfishView.this     // Catch:{ FileNotFoundException -> 0x0058, all -> 0x0061 }
                    android.content.Context r4 = r4.getContext()     // Catch:{ FileNotFoundException -> 0x0058, all -> 0x0061 }
                    java.lang.String r6 = "jellyfish_gradient_v1.png"
                    r7 = 0
                    java.io.FileOutputStream r1 = r4.openFileOutput(r6, r7)     // Catch:{ FileNotFoundException -> 0x0058, all -> 0x0061 }
                    android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ FileNotFoundException -> 0x0058, all -> 0x0061 }
                    r6 = 100
                    r0.compress(r4, r6, r1)     // Catch:{ FileNotFoundException -> 0x0058, all -> 0x0061 }
                    if (r1 == 0) goto L_0x0043
                    r1.close()     // Catch:{ IOException -> 0x0076 }
                L_0x0043:
                    com.airbnb.n2.components.jellyfish.JellyfishView r4 = com.airbnb.p027n2.components.jellyfish.JellyfishView.this     // Catch:{ all -> 0x0068 }
                    r4.gradientBitmap = r0     // Catch:{ all -> 0x0068 }
                    java.lang.ref.WeakReference r4 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0068 }
                    com.airbnb.n2.components.jellyfish.JellyfishView r6 = com.airbnb.p027n2.components.jellyfish.JellyfishView.this     // Catch:{ all -> 0x0068 }
                    android.graphics.Bitmap r6 = r6.gradientBitmap     // Catch:{ all -> 0x0068 }
                    r4.<init>(r6)     // Catch:{ all -> 0x0068 }
                    com.airbnb.p027n2.components.jellyfish.JellyfishView.cachedBitmap = r4     // Catch:{ all -> 0x0068 }
                L_0x0056:
                    monitor-exit(r5)     // Catch:{ all -> 0x0068 }
                    return r0
                L_0x0058:
                    r4 = move-exception
                    if (r1 == 0) goto L_0x0043
                    r1.close()     // Catch:{ IOException -> 0x005f }
                    goto L_0x0043
                L_0x005f:
                    r4 = move-exception
                    goto L_0x0043
                L_0x0061:
                    r4 = move-exception
                    if (r1 == 0) goto L_0x0067
                    r1.close()     // Catch:{ IOException -> 0x0078 }
                L_0x0067:
                    throw r4     // Catch:{ all -> 0x0068 }
                L_0x0068:
                    r4 = move-exception
                    monitor-exit(r5)     // Catch:{ all -> 0x0068 }
                    throw r4
                L_0x006b:
                    java.lang.ref.WeakReference r4 = com.airbnb.p027n2.components.jellyfish.JellyfishView.cachedBitmap     // Catch:{ all -> 0x0068 }
                    java.lang.Object r0 = r4.get()     // Catch:{ all -> 0x0068 }
                    android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0     // Catch:{ all -> 0x0068 }
                    goto L_0x0056
                L_0x0076:
                    r4 = move-exception
                    goto L_0x0043
                L_0x0078:
                    r6 = move-exception
                    goto L_0x0067
                */
                throw new UnsupportedOperationException("Method not decompiled: com.airbnb.p027n2.components.jellyfish.JellyfishView.C30474.doInBackground(java.lang.Integer[]):android.graphics.Bitmap");
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Bitmap bitmap) {
                JellyfishView.this.onBitmapLoaded(bitmap);
            }
        }.execute(new Integer[]{Integer.valueOf(size)});
    }

    /* access modifiers changed from: private */
    public Bitmap createBitmapSync(int size) {
        Bitmap bitmap = Bitmap.createBitmap(size, size, Config.ARGB_4444);
        float radius = (((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) / 2.0f) * 0.95f;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getWidth(); j++) {
                int dx = Math.abs(i - (bitmap.getWidth() / 2));
                int dy = Math.abs(j - (bitmap.getHeight() / 2));
                float fraction = ((((float) Math.sqrt((double) ((dx * dx) + (dy * dy)))) / radius) + (this.random.nextFloat() * 0.1f)) - 0.05f;
                if (fraction > 1.0f) {
                    fraction = 1.0f;
                } else if (fraction < 0.15f) {
                    fraction = 0.15f;
                }
                bitmap.setPixel(i, j, Color.argb((int) ((1.0f - fraction) * 255.0f), 255, 255, 255));
            }
        }
        return bitmap;
    }

    /* access modifiers changed from: private */
    public void onBitmapLoaded(Bitmap bitmap) {
        this.gradientBitmap = bitmap;
        cachedBitmap = new WeakReference<>(this.gradientBitmap);
        if (ViewCompat.isAttachedToWindow(this)) {
            addGradientsToChildrenIfPossible();
            startOrStopAnimationIfNeeded();
        }
    }

    /* access modifiers changed from: private */
    public void updateTranslation() {
        if (getWidth() != 0 && getHeight() != 0) {
            long now = System.currentTimeMillis();
            float frameDuration = Math.min(((float) (now - this.lastVelocityUpdateTime)) / 1000.0f, 0.5f);
            this.lastVelocityUpdateTime = now;
            for (int i = getChildCount() - 1; i >= 0; i--) {
                View child = getChildAt(i);
                float cx = child.getTranslationX() + (((float) child.getWidth()) / 2.0f);
                float cy = child.getTranslationY() + (((float) child.getHeight()) / 2.0f);
                float tx = this.targetX[i];
                float ty = this.targetY[i];
                float dx = tx - cx;
                float dy = ty - cy;
                float maxVelocity = (35.0f * this.timeScale) / 2.0f;
                if (tx == 0.0f || ty == 0.0f || Math.abs(dx) < 2.0f * maxVelocity || Math.abs(dy) < 2.0f * maxVelocity) {
                    if (child.getTranslationX() == 0.0f) {
                        child.setTranslationX(generateRandomFloat(((float) getWidth()) * 0.1f, ((float) getWidth()) * 0.9f));
                        child.setTranslationY(generateRandomFloat(((float) getHeight()) * 0.1f, ((float) getHeight()) * 0.9f));
                    }
                    this.targetX[i] = generateRandomFloat(((float) getWidth()) * 0.1f, ((float) getWidth()) * 0.9f);
                    this.targetY[i] = generateRandomFloat(((float) getHeight()) * 0.1f, ((float) getHeight()) * 0.9f);
                } else {
                    float a = ((1.0f / ((float) Math.sqrt((double) ((dx * dx) + (dy * dy))))) * (0.04f * this.timeScale)) / frameDuration;
                    float[] fArr = this.velocityX;
                    fArr[i] = fArr[i] + (dx * a);
                    if (Math.abs(this.velocityX[i]) > maxVelocity) {
                        this.velocityX[i] = Math.signum(this.velocityX[i]) * maxVelocity;
                    }
                    float[] fArr2 = this.velocityY;
                    fArr2[i] = fArr2[i] + (dy * a);
                    if (Math.abs(this.velocityY[i]) > maxVelocity) {
                        this.velocityY[i] = Math.signum(this.velocityY[i]) * maxVelocity;
                    }
                    child.setTranslationX(child.getTranslationX() + this.velocityX[i]);
                    child.setTranslationY(child.getTranslationY() + this.velocityY[i]);
                }
            }
        }
    }
}
