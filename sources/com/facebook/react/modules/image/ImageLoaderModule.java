package com.facebook.react.modules.image;

import android.net.Uri;
import android.util.SparseArray;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "ImageLoader")
public class ImageLoaderModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final String ERROR_PREFETCH_FAILURE = "E_PREFETCH_FAILURE";
    private final Object mCallerContext;
    private final Object mEnqueuedRequestMonitor;
    private final SparseArray<DataSource<Void>> mEnqueuedRequests;

    public ImageLoaderModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mCallerContext = this;
    }

    public ImageLoaderModule(ReactApplicationContext reactContext, Object callerContext) {
        super(reactContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mCallerContext = callerContext;
    }

    public String getName() {
        return "ImageLoader";
    }

    @ReactMethod
    public void getSize(String uriString, final Promise promise) {
        if (uriString == null || uriString.isEmpty()) {
            promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(uriString)).build(), this.mCallerContext).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
            /* access modifiers changed from: protected */
            public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (dataSource.isFinished()) {
                    CloseableReference<CloseableImage> ref = (CloseableReference) dataSource.getResult();
                    if (ref != null) {
                        try {
                            CloseableImage image = (CloseableImage) ref.get();
                            WritableMap sizes = Arguments.createMap();
                            sizes.putInt("width", image.getWidth());
                            sizes.putInt("height", image.getHeight());
                            promise.resolve(sizes);
                        } catch (Exception e) {
                            promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, (Throwable) e);
                        } finally {
                            CloseableReference.closeSafely(ref);
                        }
                    } else {
                        promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, dataSource.getFailureCause());
            }
        }, CallerThreadExecutor.getInstance());
    }

    @ReactMethod
    public void prefetchImage(String uriString, final int requestId, final Promise promise) {
        if (uriString == null || uriString.isEmpty()) {
            promise.reject(ERROR_INVALID_URI, "Cannot prefetch an image for an empty URI");
            return;
        }
        DataSource<Void> prefetchSource = Fresco.getImagePipeline().prefetchToDiskCache(ImageRequestBuilder.newBuilderWithSource(Uri.parse(uriString)).build(), this.mCallerContext);
        DataSubscriber<Void> prefetchSubscriber = new BaseDataSubscriber<Void>() {
            /* access modifiers changed from: protected */
            public void onNewResultImpl(DataSource<Void> dataSource) {
                if (dataSource.isFinished()) {
                    try {
                        ImageLoaderModule.this.removeRequest(requestId);
                        promise.resolve(Boolean.valueOf(true));
                    } finally {
                        dataSource.close();
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void onFailureImpl(DataSource<Void> dataSource) {
                try {
                    ImageLoaderModule.this.removeRequest(requestId);
                    promise.reject(ImageLoaderModule.ERROR_PREFETCH_FAILURE, dataSource.getFailureCause());
                } finally {
                    dataSource.close();
                }
            }
        };
        registerRequest(requestId, prefetchSource);
        prefetchSource.subscribe(prefetchSubscriber, CallerThreadExecutor.getInstance());
    }

    @ReactMethod
    public void abortRequest(int requestId) {
        DataSource<Void> request = removeRequest(requestId);
        if (request != null) {
            request.close();
        }
    }

    @ReactMethod
    public void queryCache(final ReadableArray uris, final Promise promise) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... params) {
                WritableMap result = Arguments.createMap();
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                for (int i = 0; i < uris.size(); i++) {
                    String uriString = uris.getString(i);
                    Uri uri = Uri.parse(uriString);
                    if (imagePipeline.isInBitmapMemoryCache(uri)) {
                        result.putString(uriString, "memory");
                    } else if (imagePipeline.isInDiskCacheSync(uri)) {
                        result.putString(uriString, "disk");
                    }
                }
                promise.resolve(result);
            }
        }.executeOnExecutor(GuardedAsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void registerRequest(int requestId, DataSource<Void> request) {
        synchronized (this.mEnqueuedRequestMonitor) {
            this.mEnqueuedRequests.put(requestId, request);
        }
    }

    /* access modifiers changed from: private */
    public DataSource<Void> removeRequest(int requestId) {
        DataSource<Void> request;
        synchronized (this.mEnqueuedRequestMonitor) {
            request = (DataSource) this.mEnqueuedRequests.get(requestId);
            this.mEnqueuedRequests.remove(requestId);
        }
        return request;
    }

    public void onHostResume() {
    }

    public void onHostPause() {
    }

    public void onHostDestroy() {
        synchronized (this.mEnqueuedRequestMonitor) {
            int size = this.mEnqueuedRequests.size();
            for (int i = 0; i < size; i++) {
                DataSource<Void> enqueuedRequest = (DataSource) this.mEnqueuedRequests.valueAt(i);
                if (enqueuedRequest != null) {
                    enqueuedRequest.close();
                }
            }
            this.mEnqueuedRequests.clear();
        }
    }
}
