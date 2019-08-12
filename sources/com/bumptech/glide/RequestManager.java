package com.bumptech.glide;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.util.Util;
import java.io.InputStream;

public class RequestManager implements LifecycleListener {
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final Glide glide;
    /* access modifiers changed from: private */
    public final Lifecycle lifecycle;
    /* access modifiers changed from: private */
    public DefaultOptions options;
    /* access modifiers changed from: private */
    public final OptionsApplier optionsApplier;
    /* access modifiers changed from: private */
    public final RequestTracker requestTracker;
    private final RequestManagerTreeNode treeNode;

    public interface DefaultOptions {
        <T> void apply(GenericRequestBuilder<T, ?, ?, ?> genericRequestBuilder);
    }

    public final class GenericModelRequest<A, T> {
        /* access modifiers changed from: private */
        public final Class<T> dataClass;
        /* access modifiers changed from: private */
        public final ModelLoader<A, T> modelLoader;

        public final class GenericTypeRequest {
            private final A model;
            private final Class<A> modelClass;
            private final boolean providedModel = true;

            GenericTypeRequest(A model2) {
                this.model = model2;
                this.modelClass = RequestManager.getSafeClass(model2);
            }

            /* renamed from: as */
            public <Z> GenericTranscodeRequest<A, T, Z> mo12478as(Class<Z> resourceClass) {
                GenericTranscodeRequest<A, T, Z> result = (GenericTranscodeRequest) RequestManager.this.optionsApplier.apply(new GenericTranscodeRequest(RequestManager.this.context, RequestManager.this.glide, this.modelClass, GenericModelRequest.this.modelLoader, GenericModelRequest.this.dataClass, resourceClass, RequestManager.this.requestTracker, RequestManager.this.lifecycle, RequestManager.this.optionsApplier));
                if (this.providedModel) {
                    result.load(this.model);
                }
                return result;
            }
        }

        GenericModelRequest(ModelLoader<A, T> modelLoader2, Class<T> dataClass2) {
            this.modelLoader = modelLoader2;
            this.dataClass = dataClass2;
        }

        public GenericTypeRequest load(A model) {
            return new GenericTypeRequest<>(model);
        }
    }

    public final class ImageModelRequest<T> {
        private final ModelLoader<T, InputStream> loader;

        ImageModelRequest(ModelLoader<T, InputStream> loader2) {
            this.loader = loader2;
        }

        public DrawableTypeRequest<T> from(Class<T> modelClass) {
            return (DrawableTypeRequest) RequestManager.this.optionsApplier.apply(new DrawableTypeRequest(modelClass, this.loader, null, RequestManager.this.context, RequestManager.this.glide, RequestManager.this.requestTracker, RequestManager.this.lifecycle, RequestManager.this.optionsApplier));
        }

        public DrawableTypeRequest<T> load(T model) {
            return (DrawableTypeRequest) from(RequestManager.getSafeClass(model)).load(model);
        }
    }

    class OptionsApplier {
        OptionsApplier() {
        }

        public <A, X extends GenericRequestBuilder<A, ?, ?, ?>> X apply(X builder) {
            if (RequestManager.this.options != null) {
                RequestManager.this.options.apply(builder);
            }
            return builder;
        }
    }

    private static class RequestManagerConnectivityListener implements ConnectivityListener {
        private final RequestTracker requestTracker;

        public RequestManagerConnectivityListener(RequestTracker requestTracker2) {
            this.requestTracker = requestTracker2;
        }

        public void onConnectivityChanged(boolean isConnected) {
            if (isConnected) {
                this.requestTracker.restartRequests();
            }
        }
    }

    public RequestManager(Context context2, Lifecycle lifecycle2, RequestManagerTreeNode treeNode2) {
        this(context2, lifecycle2, treeNode2, new RequestTracker(), new ConnectivityMonitorFactory());
    }

    RequestManager(Context context2, final Lifecycle lifecycle2, RequestManagerTreeNode treeNode2, RequestTracker requestTracker2, ConnectivityMonitorFactory factory) {
        this.context = context2.getApplicationContext();
        this.lifecycle = lifecycle2;
        this.treeNode = treeNode2;
        this.requestTracker = requestTracker2;
        this.glide = Glide.get(context2);
        this.optionsApplier = new OptionsApplier();
        ConnectivityMonitor connectivityMonitor = factory.build(context2, new RequestManagerConnectivityListener(requestTracker2));
        if (Util.isOnBackgroundThread()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    lifecycle2.addListener(RequestManager.this);
                }
            });
        } else {
            lifecycle2.addListener(this);
        }
        lifecycle2.addListener(connectivityMonitor);
    }

    public void onTrimMemory(int level) {
        this.glide.trimMemory(level);
    }

    public void onLowMemory() {
        this.glide.clearMemory();
    }

    public void pauseRequests() {
        Util.assertMainThread();
        this.requestTracker.pauseRequests();
    }

    public void resumeRequests() {
        Util.assertMainThread();
        this.requestTracker.resumeRequests();
    }

    public void onStart() {
        resumeRequests();
    }

    public void onStop() {
        pauseRequests();
    }

    public void onDestroy() {
        this.requestTracker.clearRequests();
    }

    public <A, T> GenericModelRequest<A, T> using(ModelLoader<A, T> modelLoader, Class<T> dataClass) {
        return new GenericModelRequest<>(modelLoader, dataClass);
    }

    public <T> ImageModelRequest<T> using(StreamModelLoader<T> modelLoader) {
        return new ImageModelRequest<>(modelLoader);
    }

    public DrawableTypeRequest<String> load(String string) {
        return (DrawableTypeRequest) fromString().load(string);
    }

    public DrawableTypeRequest<String> fromString() {
        return loadGeneric(String.class);
    }

    public DrawableTypeRequest<Uri> load(Uri uri) {
        return (DrawableTypeRequest) fromUri().load(uri);
    }

    public DrawableTypeRequest<Uri> fromUri() {
        return loadGeneric(Uri.class);
    }

    public DrawableTypeRequest<Integer> load(Integer resourceId) {
        return (DrawableTypeRequest) fromResource().load(resourceId);
    }

    public DrawableTypeRequest<Integer> fromResource() {
        return (DrawableTypeRequest) loadGeneric(Integer.class).signature(ApplicationVersionSignature.obtain(this.context));
    }

    public <T> DrawableTypeRequest<T> load(T model) {
        return (DrawableTypeRequest) loadGeneric(getSafeClass(model)).load(model);
    }

    private <T> DrawableTypeRequest<T> loadGeneric(Class<T> modelClass) {
        ModelLoader<T, InputStream> streamModelLoader = Glide.buildStreamModelLoader(modelClass, this.context);
        ModelLoader<T, ParcelFileDescriptor> fileDescriptorModelLoader = Glide.buildFileDescriptorModelLoader(modelClass, this.context);
        if (modelClass != null && streamModelLoader == null && fileDescriptorModelLoader == null) {
            throw new IllegalArgumentException("Unknown type " + modelClass + ". You must provide a Model of a type for" + " which there is a registered ModelLoader, if you are using a custom model, you must first call" + " Glide#register with a ModelLoaderFactory for your custom model class");
        }
        return (DrawableTypeRequest) this.optionsApplier.apply(new DrawableTypeRequest(modelClass, streamModelLoader, fileDescriptorModelLoader, this.context, this.glide, this.requestTracker, this.lifecycle, this.optionsApplier));
    }

    /* access modifiers changed from: private */
    public static <T> Class<T> getSafeClass(T model) {
        if (model != null) {
            return model.getClass();
        }
        return null;
    }
}
