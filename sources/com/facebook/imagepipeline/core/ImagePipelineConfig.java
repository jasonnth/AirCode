package com.facebook.imagepipeline.core;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultEncodedMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.cache.NoOpImageCacheStatsTracker;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImagePipelineConfig {
    private final AnimatedImageFactory mAnimatedImageFactory;
    private final Config mBitmapConfig;
    private final Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    private final CacheKeyFactory mCacheKeyFactory;
    private final Context mContext;
    private final boolean mDecodeMemoryFileEnabled;
    private final boolean mDownsampleEnabled;
    private final Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    private final ExecutorSupplier mExecutorSupplier;
    private final FileCacheFactory mFileCacheFactory;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    private final ImageDecoder mImageDecoder;
    private final ImagePipelineExperiments mImagePipelineExperiments;
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final DiskCacheConfig mMainDiskCacheConfig;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final NetworkFetcher mNetworkFetcher;
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    private final PoolFactory mPoolFactory;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final Set<RequestListener> mRequestListeners;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private final DiskCacheConfig mSmallImageDiskCacheConfig;

    public static class Builder {
        /* access modifiers changed from: private */
        public AnimatedImageFactory mAnimatedImageFactory;
        /* access modifiers changed from: private */
        public Config mBitmapConfig;
        /* access modifiers changed from: private */
        public Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
        /* access modifiers changed from: private */
        public CacheKeyFactory mCacheKeyFactory;
        /* access modifiers changed from: private */
        public final Context mContext;
        /* access modifiers changed from: private */
        public boolean mDecodeMemoryFileEnabled;
        /* access modifiers changed from: private */
        public boolean mDownsampleEnabled;
        /* access modifiers changed from: private */
        public Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
        /* access modifiers changed from: private */
        public ExecutorSupplier mExecutorSupplier;
        /* access modifiers changed from: private */
        public final com.facebook.imagepipeline.core.ImagePipelineExperiments.Builder mExperimentsBuilder;
        /* access modifiers changed from: private */
        public FileCacheFactory mFileCacheFactory;
        /* access modifiers changed from: private */
        public ImageCacheStatsTracker mImageCacheStatsTracker;
        /* access modifiers changed from: private */
        public ImageDecoder mImageDecoder;
        /* access modifiers changed from: private */
        public Supplier<Boolean> mIsPrefetchEnabledSupplier;
        /* access modifiers changed from: private */
        public DiskCacheConfig mMainDiskCacheConfig;
        /* access modifiers changed from: private */
        public MemoryTrimmableRegistry mMemoryTrimmableRegistry;
        /* access modifiers changed from: private */
        public NetworkFetcher mNetworkFetcher;
        /* access modifiers changed from: private */
        public PlatformBitmapFactory mPlatformBitmapFactory;
        /* access modifiers changed from: private */
        public PoolFactory mPoolFactory;
        /* access modifiers changed from: private */
        public ProgressiveJpegConfig mProgressiveJpegConfig;
        /* access modifiers changed from: private */
        public Set<RequestListener> mRequestListeners;
        /* access modifiers changed from: private */
        public boolean mResizeAndRotateEnabledForNetwork;
        /* access modifiers changed from: private */
        public DiskCacheConfig mSmallImageDiskCacheConfig;

        private Builder(Context context) {
            this.mDownsampleEnabled = false;
            this.mResizeAndRotateEnabledForNetwork = true;
            this.mExperimentsBuilder = new com.facebook.imagepipeline.core.ImagePipelineExperiments.Builder(this);
            this.mContext = (Context) Preconditions.checkNotNull(context);
        }

        public Builder setAnimatedImageFactory(AnimatedImageFactory animatedImageFactory) {
            this.mAnimatedImageFactory = animatedImageFactory;
            return this;
        }

        public Builder setBitmapsConfig(Config config) {
            this.mBitmapConfig = config;
            return this;
        }

        public Builder setBitmapMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> bitmapMemoryCacheParamsSupplier) {
            this.mBitmapMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(bitmapMemoryCacheParamsSupplier);
            return this;
        }

        public Builder setCacheKeyFactory(CacheKeyFactory cacheKeyFactory) {
            this.mCacheKeyFactory = cacheKeyFactory;
            return this;
        }

        public Builder setDecodeMemoryFileEnabled(boolean decodeMemoryFileEnabled) {
            this.mDecodeMemoryFileEnabled = decodeMemoryFileEnabled;
            return this;
        }

        public Builder setFileCacheFactory(FileCacheFactory fileCacheFactory) {
            this.mFileCacheFactory = fileCacheFactory;
            return this;
        }

        @Deprecated
        public Builder setDiskStorageFactory(DiskStorageFactory diskStorageFactory) {
            setFileCacheFactory(new DiskStorageCacheFactory(diskStorageFactory));
            return this;
        }

        public boolean isDownsampleEnabled() {
            return this.mDownsampleEnabled;
        }

        public Builder setDownsampleEnabled(boolean downsampleEnabled) {
            this.mDownsampleEnabled = downsampleEnabled;
            return this;
        }

        public Builder setEncodedMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> encodedMemoryCacheParamsSupplier) {
            this.mEncodedMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(encodedMemoryCacheParamsSupplier);
            return this;
        }

        public Builder setExecutorSupplier(ExecutorSupplier executorSupplier) {
            this.mExecutorSupplier = executorSupplier;
            return this;
        }

        public Builder setImageCacheStatsTracker(ImageCacheStatsTracker imageCacheStatsTracker) {
            this.mImageCacheStatsTracker = imageCacheStatsTracker;
            return this;
        }

        public Builder setImageDecoder(ImageDecoder imageDecoder) {
            this.mImageDecoder = imageDecoder;
            return this;
        }

        public Builder setIsPrefetchEnabledSupplier(Supplier<Boolean> isPrefetchEnabledSupplier) {
            this.mIsPrefetchEnabledSupplier = isPrefetchEnabledSupplier;
            return this;
        }

        public Builder setMainDiskCacheConfig(DiskCacheConfig mainDiskCacheConfig) {
            this.mMainDiskCacheConfig = mainDiskCacheConfig;
            return this;
        }

        public Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry memoryTrimmableRegistry) {
            this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
            return this;
        }

        public Builder setNetworkFetcher(NetworkFetcher networkFetcher) {
            this.mNetworkFetcher = networkFetcher;
            return this;
        }

        public Builder setPlatformBitmapFactory(PlatformBitmapFactory platformBitmapFactory) {
            this.mPlatformBitmapFactory = platformBitmapFactory;
            return this;
        }

        public Builder setPoolFactory(PoolFactory poolFactory) {
            this.mPoolFactory = poolFactory;
            return this;
        }

        public Builder setProgressiveJpegConfig(ProgressiveJpegConfig progressiveJpegConfig) {
            this.mProgressiveJpegConfig = progressiveJpegConfig;
            return this;
        }

        public Builder setRequestListeners(Set<RequestListener> requestListeners) {
            this.mRequestListeners = requestListeners;
            return this;
        }

        public Builder setResizeAndRotateEnabledForNetwork(boolean resizeAndRotateEnabledForNetwork) {
            this.mResizeAndRotateEnabledForNetwork = resizeAndRotateEnabledForNetwork;
            return this;
        }

        public Builder setSmallImageDiskCacheConfig(DiskCacheConfig smallImageDiskCacheConfig) {
            this.mSmallImageDiskCacheConfig = smallImageDiskCacheConfig;
            return this;
        }

        public com.facebook.imagepipeline.core.ImagePipelineExperiments.Builder experiment() {
            return this.mExperimentsBuilder;
        }

        public ImagePipelineConfig build() {
            return new ImagePipelineConfig(this);
        }
    }

    private ImagePipelineConfig(Builder builder) {
        Supplier<MemoryCacheParams> access$100;
        Config access$300;
        CacheKeyFactory access$400;
        FileCacheFactory access$600;
        Supplier<MemoryCacheParams> access$800;
        ImageCacheStatsTracker access$900;
        Supplier<Boolean> access$1100;
        DiskCacheConfig access$1200;
        MemoryTrimmableRegistry access$1300;
        NetworkFetcher access$1400;
        PoolFactory access$1600;
        ProgressiveJpegConfig access$1700;
        Set<RequestListener> access$1800;
        DiskCacheConfig access$2000;
        ExecutorSupplier access$2100;
        this.mAnimatedImageFactory = builder.mAnimatedImageFactory;
        if (builder.mBitmapMemoryCacheParamsSupplier == null) {
            access$100 = new DefaultBitmapMemoryCacheParamsSupplier<>((ActivityManager) builder.mContext.getSystemService("activity"));
        } else {
            access$100 = builder.mBitmapMemoryCacheParamsSupplier;
        }
        this.mBitmapMemoryCacheParamsSupplier = access$100;
        if (builder.mBitmapConfig == null) {
            access$300 = Config.ARGB_8888;
        } else {
            access$300 = builder.mBitmapConfig;
        }
        this.mBitmapConfig = access$300;
        if (builder.mCacheKeyFactory == null) {
            access$400 = DefaultCacheKeyFactory.getInstance();
        } else {
            access$400 = builder.mCacheKeyFactory;
        }
        this.mCacheKeyFactory = access$400;
        this.mContext = (Context) Preconditions.checkNotNull(builder.mContext);
        this.mDecodeMemoryFileEnabled = builder.mDecodeMemoryFileEnabled;
        if (builder.mFileCacheFactory == null) {
            access$600 = new DiskStorageCacheFactory(new DynamicDefaultDiskStorageFactory());
        } else {
            access$600 = builder.mFileCacheFactory;
        }
        this.mFileCacheFactory = access$600;
        this.mDownsampleEnabled = builder.mDownsampleEnabled;
        if (builder.mEncodedMemoryCacheParamsSupplier == null) {
            access$800 = new DefaultEncodedMemoryCacheParamsSupplier<>();
        } else {
            access$800 = builder.mEncodedMemoryCacheParamsSupplier;
        }
        this.mEncodedMemoryCacheParamsSupplier = access$800;
        if (builder.mImageCacheStatsTracker == null) {
            access$900 = NoOpImageCacheStatsTracker.getInstance();
        } else {
            access$900 = builder.mImageCacheStatsTracker;
        }
        this.mImageCacheStatsTracker = access$900;
        this.mImageDecoder = builder.mImageDecoder;
        if (builder.mIsPrefetchEnabledSupplier == null) {
            access$1100 = new Supplier<Boolean>() {
                public Boolean get() {
                    return Boolean.valueOf(true);
                }
            };
        } else {
            access$1100 = builder.mIsPrefetchEnabledSupplier;
        }
        this.mIsPrefetchEnabledSupplier = access$1100;
        if (builder.mMainDiskCacheConfig == null) {
            access$1200 = getDefaultMainDiskCacheConfig(builder.mContext);
        } else {
            access$1200 = builder.mMainDiskCacheConfig;
        }
        this.mMainDiskCacheConfig = access$1200;
        if (builder.mMemoryTrimmableRegistry == null) {
            access$1300 = NoOpMemoryTrimmableRegistry.getInstance();
        } else {
            access$1300 = builder.mMemoryTrimmableRegistry;
        }
        this.mMemoryTrimmableRegistry = access$1300;
        if (builder.mNetworkFetcher == null) {
            access$1400 = new HttpUrlConnectionNetworkFetcher();
        } else {
            access$1400 = builder.mNetworkFetcher;
        }
        this.mNetworkFetcher = access$1400;
        this.mPlatformBitmapFactory = builder.mPlatformBitmapFactory;
        if (builder.mPoolFactory == null) {
            access$1600 = new PoolFactory(PoolConfig.newBuilder().build());
        } else {
            access$1600 = builder.mPoolFactory;
        }
        this.mPoolFactory = access$1600;
        if (builder.mProgressiveJpegConfig == null) {
            access$1700 = new SimpleProgressiveJpegConfig();
        } else {
            access$1700 = builder.mProgressiveJpegConfig;
        }
        this.mProgressiveJpegConfig = access$1700;
        if (builder.mRequestListeners == null) {
            access$1800 = new HashSet<>();
        } else {
            access$1800 = builder.mRequestListeners;
        }
        this.mRequestListeners = access$1800;
        this.mResizeAndRotateEnabledForNetwork = builder.mResizeAndRotateEnabledForNetwork;
        if (builder.mSmallImageDiskCacheConfig == null) {
            access$2000 = this.mMainDiskCacheConfig;
        } else {
            access$2000 = builder.mSmallImageDiskCacheConfig;
        }
        this.mSmallImageDiskCacheConfig = access$2000;
        int numCpuBoundThreads = this.mPoolFactory.getFlexByteArrayPoolMaxNumThreads();
        if (builder.mExecutorSupplier == null) {
            access$2100 = new DefaultExecutorSupplier(numCpuBoundThreads);
        } else {
            access$2100 = builder.mExecutorSupplier;
        }
        this.mExecutorSupplier = access$2100;
        this.mImagePipelineExperiments = builder.mExperimentsBuilder.build();
    }

    private static DiskCacheConfig getDefaultMainDiskCacheConfig(Context context) {
        return DiskCacheConfig.newBuilder(context).build();
    }

    public AnimatedImageFactory getAnimatedImageFactory() {
        return this.mAnimatedImageFactory;
    }

    public Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier() {
        return this.mBitmapMemoryCacheParamsSupplier;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean isDecodeFileDescriptorEnabled() {
        return this.mImagePipelineExperiments.isDecodeFileDescriptorEnabled();
    }

    public boolean isDecodeMemoryFileEnabled() {
        return this.mDecodeMemoryFileEnabled;
    }

    public FileCacheFactory getFileCacheFactory() {
        return this.mFileCacheFactory;
    }

    public boolean isDownsampleEnabled() {
        return this.mDownsampleEnabled;
    }

    public boolean isWebpSupportEnabled() {
        return this.mImagePipelineExperiments.isWebpSupportEnabled();
    }

    public Supplier<MemoryCacheParams> getEncodedMemoryCacheParamsSupplier() {
        return this.mEncodedMemoryCacheParamsSupplier;
    }

    public ExecutorSupplier getExecutorSupplier() {
        return this.mExecutorSupplier;
    }

    @Deprecated
    public int getForceSmallCacheThresholdBytes() {
        return this.mImagePipelineExperiments.getForceSmallCacheThresholdBytes();
    }

    public ImageCacheStatsTracker getImageCacheStatsTracker() {
        return this.mImageCacheStatsTracker;
    }

    public ImageDecoder getImageDecoder() {
        return this.mImageDecoder;
    }

    public Supplier<Boolean> getIsPrefetchEnabledSupplier() {
        return this.mIsPrefetchEnabledSupplier;
    }

    public DiskCacheConfig getMainDiskCacheConfig() {
        return this.mMainDiskCacheConfig;
    }

    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }

    public NetworkFetcher getNetworkFetcher() {
        return this.mNetworkFetcher;
    }

    public PlatformBitmapFactory getPlatformBitmapFactory() {
        return this.mPlatformBitmapFactory;
    }

    public PoolFactory getPoolFactory() {
        return this.mPoolFactory;
    }

    public ProgressiveJpegConfig getProgressiveJpegConfig() {
        return this.mProgressiveJpegConfig;
    }

    public Set<RequestListener> getRequestListeners() {
        return Collections.unmodifiableSet(this.mRequestListeners);
    }

    public boolean isResizeAndRotateEnabledForNetwork() {
        return this.mResizeAndRotateEnabledForNetwork;
    }

    public DiskCacheConfig getSmallImageDiskCacheConfig() {
        return this.mSmallImageDiskCacheConfig;
    }

    public ImagePipelineExperiments getExperiments() {
        return this.mImagePipelineExperiments;
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }
}
