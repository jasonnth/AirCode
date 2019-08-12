package com.airbnb.lottie;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.provider.Settings.Global;
import android.support.p002v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.BaseSavedState;
import com.airbnb.lottie.LottieComposition.Factory;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class LottieAnimationView extends AppCompatImageView {
    private static final String TAG = LottieAnimationView.class.getSimpleName();
    /* access modifiers changed from: private */
    public static final Map<String, LottieComposition> strongRefCache = new HashMap();
    /* access modifiers changed from: private */
    public static final Map<String, WeakReference<LottieComposition>> weakRefCache = new HashMap();
    private String animationName;
    private boolean autoPlay = false;
    private LottieComposition composition;
    /* access modifiers changed from: private */
    public Cancellable compositionLoader;
    private CacheStrategy defaultCacheStrategy;
    private final OnCompositionLoadedListener loadedListener = new OnCompositionLoadedListener() {
        public void onCompositionLoaded(LottieComposition composition) {
            LottieAnimationView.this.setComposition(composition);
            LottieAnimationView.this.compositionLoader = null;
        }
    };
    private final LottieDrawable lottieDrawable = new LottieDrawable();
    private boolean wasAnimatingWhenDetached = false;

    public enum CacheStrategy {
        None,
        Weak,
        Strong
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        String animationName;
        boolean isAnimating;
        boolean isLooping;
        float progress;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            boolean z;
            boolean z2 = true;
            super(in);
            this.animationName = in.readString();
            this.progress = in.readFloat();
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.isAnimating = z;
            if (in.readInt() != 1) {
                z2 = false;
            }
            this.isLooping = z2;
        }

        public void writeToParcel(Parcel out, int flags) {
            int i;
            int i2 = 1;
            super.writeToParcel(out, flags);
            out.writeString(this.animationName);
            out.writeFloat(this.progress);
            if (this.isAnimating) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeInt(i);
            if (!this.isLooping) {
                i2 = 0;
            }
            out.writeInt(i2);
        }
    }

    public LottieAnimationView(Context context) {
        super(context);
        init(null);
    }

    public LottieAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LottieAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LottieAnimationView);
        String fileName = ta.getString(R.styleable.LottieAnimationView_lottie_fileName);
        if (!isInEditMode() && fileName != null) {
            setAnimation(fileName);
        }
        if (ta.getBoolean(R.styleable.LottieAnimationView_lottie_autoPlay, false)) {
            this.lottieDrawable.playAnimation();
            this.autoPlay = true;
        }
        this.lottieDrawable.loop(ta.getBoolean(R.styleable.LottieAnimationView_lottie_loop, false));
        setImageAssetsFolder(ta.getString(R.styleable.LottieAnimationView_lottie_imageAssetsFolder));
        setProgress(ta.getFloat(R.styleable.LottieAnimationView_lottie_progress, 0.0f));
        enableMergePathsForKitKatAndAbove(ta.getBoolean(R.styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
        this.defaultCacheStrategy = CacheStrategy.values()[ta.getInt(R.styleable.LottieAnimationView_lottie_cacheStrategy, CacheStrategy.None.ordinal())];
        ta.recycle();
        setLayerType(1, null);
        if (VERSION.SDK_INT >= 17 && Global.getFloat(getContext().getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f) {
            this.lottieDrawable.systemAnimationsAreDisabled();
        }
    }

    public void setImageResource(int resId) {
        super.setImageResource(resId);
        recycleBitmaps();
    }

    public void setImageDrawable(Drawable drawable) {
        if (drawable != this.lottieDrawable) {
            recycleBitmaps();
        }
        super.setImageDrawable(drawable);
    }

    public void addColorFilterToContent(String layerName, String contentName, ColorFilter colorFilter) {
        this.lottieDrawable.addColorFilterToContent(layerName, contentName, colorFilter);
    }

    public void addColorFilterToLayer(String layerName, ColorFilter colorFilter) {
        this.lottieDrawable.addColorFilterToLayer(layerName, colorFilter);
    }

    public void addColorFilter(ColorFilter colorFilter) {
        this.lottieDrawable.addColorFilter(colorFilter);
    }

    public void clearColorFilters() {
        this.lottieDrawable.clearColorFilters();
    }

    public void invalidateDrawable(Drawable dr) {
        if (getDrawable() == this.lottieDrawable) {
            super.invalidateDrawable(this.lottieDrawable);
        } else {
            super.invalidateDrawable(dr);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.animationName = this.animationName;
        ss.progress = this.lottieDrawable.getProgress();
        ss.isAnimating = this.lottieDrawable.isAnimating();
        ss.isLooping = this.lottieDrawable.isLooping();
        return ss;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.animationName = ss.animationName;
        if (!TextUtils.isEmpty(this.animationName)) {
            setAnimation(this.animationName);
        }
        setProgress(ss.progress);
        loop(ss.isLooping);
        if (ss.isAnimating) {
            playAnimation();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.autoPlay && this.wasAnimatingWhenDetached) {
            playAnimation();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (isAnimating()) {
            cancelAnimation();
            this.wasAnimatingWhenDetached = true;
        }
        recycleBitmaps();
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: 0000 */
    public void recycleBitmaps() {
        if (this.lottieDrawable != null) {
            this.lottieDrawable.recycleBitmaps();
        }
    }

    public void enableMergePathsForKitKatAndAbove(boolean enable) {
        this.lottieDrawable.enableMergePathsForKitKatAndAbove(enable);
    }

    public void useExperimentalHardwareAcceleration() {
        setLayerType(2, null);
    }

    public void setAnimation(String animationName2) {
        setAnimation(animationName2, this.defaultCacheStrategy);
    }

    public void setAnimation(final String animationName2, final CacheStrategy cacheStrategy) {
        this.animationName = animationName2;
        if (weakRefCache.containsKey(animationName2)) {
            WeakReference<LottieComposition> compRef = (WeakReference) weakRefCache.get(animationName2);
            if (compRef.get() != null) {
                setComposition((LottieComposition) compRef.get());
                return;
            }
        } else if (strongRefCache.containsKey(animationName2)) {
            setComposition((LottieComposition) strongRefCache.get(animationName2));
            return;
        }
        this.animationName = animationName2;
        this.lottieDrawable.cancelAnimation();
        cancelLoaderTask();
        this.compositionLoader = Factory.fromAssetFileName(getContext(), animationName2, new OnCompositionLoadedListener() {
            public void onCompositionLoaded(LottieComposition composition) {
                if (cacheStrategy == CacheStrategy.Strong) {
                    LottieAnimationView.strongRefCache.put(animationName2, composition);
                } else if (cacheStrategy == CacheStrategy.Weak) {
                    LottieAnimationView.weakRefCache.put(animationName2, new WeakReference(composition));
                }
                LottieAnimationView.this.setComposition(composition);
            }
        });
    }

    public void setAnimation(JSONObject json) {
        cancelLoaderTask();
        this.compositionLoader = Factory.fromJson(getResources(), json, this.loadedListener);
    }

    private void cancelLoaderTask() {
        if (this.compositionLoader != null) {
            this.compositionLoader.cancel();
            this.compositionLoader = null;
        }
    }

    public void setComposition(LottieComposition composition2) {
        this.lottieDrawable.setCallback(this);
        if (this.lottieDrawable.setComposition(composition2)) {
            int screenWidth = Utils.getScreenWidth(getContext());
            int screenHeight = Utils.getScreenHeight(getContext());
            int compWidth = composition2.getBounds().width();
            int compHeight = composition2.getBounds().height();
            if (compWidth > screenWidth || compHeight > screenHeight) {
                setScale(Math.min(((float) screenWidth) / ((float) compWidth), ((float) screenHeight) / ((float) compHeight)));
                Log.w("LOTTIE", String.format("Composition larger than the screen %dx%d vs %dx%d. Scaling down.", new Object[]{Integer.valueOf(compWidth), Integer.valueOf(compHeight), Integer.valueOf(screenWidth), Integer.valueOf(screenHeight)}));
            }
            setImageDrawable(null);
            setImageDrawable(this.lottieDrawable);
            this.composition = composition2;
            requestLayout();
        }
    }

    public boolean hasMasks() {
        return this.lottieDrawable.hasMasks();
    }

    public boolean hasMatte() {
        return this.lottieDrawable.hasMatte();
    }

    public void setImageAssetsFolder(String imageAssetsFolder) {
        this.lottieDrawable.setImagesAssetsFolder(imageAssetsFolder);
    }

    public void addAnimatorUpdateListener(AnimatorUpdateListener updateListener) {
        this.lottieDrawable.addAnimatorUpdateListener(updateListener);
    }

    public void removeUpdateListener(AnimatorUpdateListener updateListener) {
        this.lottieDrawable.removeAnimatorUpdateListener(updateListener);
    }

    public void addAnimatorListener(AnimatorListener listener) {
        this.lottieDrawable.addAnimatorListener(listener);
    }

    public void removeAnimatorListener(AnimatorListener listener) {
        this.lottieDrawable.removeAnimatorListener(listener);
    }

    public void loop(boolean loop) {
        this.lottieDrawable.loop(loop);
    }

    public boolean isAnimating() {
        return this.lottieDrawable.isAnimating();
    }

    public void playAnimation() {
        this.lottieDrawable.playAnimation();
    }

    public void resumeAnimation() {
        this.lottieDrawable.resumeAnimation();
    }

    public void reverseAnimation() {
        this.lottieDrawable.reverseAnimation();
    }

    public void resumeReverseAnimation() {
        this.lottieDrawable.resumeReverseAnimation();
    }

    public void setSpeed(float speed) {
        this.lottieDrawable.setSpeed(speed);
    }

    public void setImageAssetDelegate(ImageAssetDelegate assetDelegate) {
        this.lottieDrawable.setImageAssetDelegate(assetDelegate);
    }

    public void setScale(float scale) {
        this.lottieDrawable.setScale(scale);
        if (getDrawable() == this.lottieDrawable) {
            setImageDrawable(null);
            setImageDrawable(this.lottieDrawable);
        }
    }

    public float getScale() {
        return this.lottieDrawable.getScale();
    }

    public void cancelAnimation() {
        this.lottieDrawable.cancelAnimation();
    }

    public void pauseAnimation() {
        float progress = getProgress();
        this.lottieDrawable.cancelAnimation();
        setProgress(progress);
    }

    public void setProgress(float progress) {
        this.lottieDrawable.setProgress(progress);
    }

    public float getProgress() {
        return this.lottieDrawable.getProgress();
    }

    public long getDuration() {
        if (this.composition != null) {
            return this.composition.getDuration();
        }
        return 0;
    }
}
