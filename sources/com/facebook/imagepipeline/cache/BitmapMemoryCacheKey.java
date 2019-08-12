package com.facebook.imagepipeline.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.common.util.HashCodeUtil;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;

public class BitmapMemoryCacheKey implements CacheKey {
    private final boolean mAutoRotated;
    private final long mCacheTime;
    private final Object mCallerContext;
    private final int mHash;
    private final ImageDecodeOptions mImageDecodeOptions;
    private final CacheKey mPostprocessorCacheKey;
    private final String mPostprocessorName;
    private final ResizeOptions mResizeOptions;
    private final String mSourceString;

    public BitmapMemoryCacheKey(String sourceString, ResizeOptions resizeOptions, boolean autoRotated, ImageDecodeOptions imageDecodeOptions, CacheKey postprocessorCacheKey, String postprocessorName, Object callerContext) {
        this.mSourceString = (String) Preconditions.checkNotNull(sourceString);
        this.mResizeOptions = resizeOptions;
        this.mAutoRotated = autoRotated;
        this.mImageDecodeOptions = imageDecodeOptions;
        this.mPostprocessorCacheKey = postprocessorCacheKey;
        this.mPostprocessorName = postprocessorName;
        this.mHash = HashCodeUtil.hashCode((Object) Integer.valueOf(sourceString.hashCode()), (Object) Integer.valueOf(resizeOptions != null ? resizeOptions.hashCode() : 0), (Object) Integer.valueOf(autoRotated ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode()), (Object) this.mImageDecodeOptions, (Object) this.mPostprocessorCacheKey, (Object) postprocessorName);
        this.mCallerContext = callerContext;
        this.mCacheTime = RealtimeSinceBootClock.get().now();
    }

    public boolean equals(Object o) {
        if (!(o instanceof BitmapMemoryCacheKey)) {
            return false;
        }
        BitmapMemoryCacheKey otherKey = (BitmapMemoryCacheKey) o;
        if (this.mHash != otherKey.mHash || !this.mSourceString.equals(otherKey.mSourceString) || !Objects.equal(this.mResizeOptions, otherKey.mResizeOptions) || this.mAutoRotated != otherKey.mAutoRotated || !Objects.equal(this.mImageDecodeOptions, otherKey.mImageDecodeOptions) || !Objects.equal(this.mPostprocessorCacheKey, otherKey.mPostprocessorCacheKey) || !Objects.equal(this.mPostprocessorName, otherKey.mPostprocessorName)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mHash;
    }

    public boolean containsUri(Uri uri) {
        return getSourceUriString().contains(uri.toString());
    }

    public String getSourceUriString() {
        return this.mSourceString;
    }

    public String getPostprocessorName() {
        return this.mPostprocessorName;
    }

    public String toString() {
        return String.format(null, "%s_%s_%s_%s_%s_%s_%d", new Object[]{this.mSourceString, this.mResizeOptions, Boolean.toString(this.mAutoRotated), this.mImageDecodeOptions, this.mPostprocessorCacheKey, this.mPostprocessorName, Integer.valueOf(this.mHash)});
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public long getInBitmapCacheSince() {
        return this.mCacheTime;
    }
}
