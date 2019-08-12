package com.facebook.react.modules.fresco;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.common.soloader.SoLoaderShim;
import com.facebook.common.soloader.SoLoaderShim.Handler;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineConfig.Builder;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.common.ModuleDataCleaner.Cleanable;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.facebook.soloader.SoLoader;
import java.util.HashSet;

@ReactModule(name = "FrescoModule")
public class FrescoModule extends ReactContextBaseJavaModule implements Cleanable {
    private static boolean sHasBeenInitialized = false;
    private ImagePipelineConfig mConfig;

    private static class FrescoHandler implements Handler {
        private FrescoHandler() {
        }

        public void loadLibrary(String libraryName) {
            SoLoader.loadLibrary(libraryName);
        }
    }

    public FrescoModule(ReactApplicationContext reactContext) {
        this(reactContext, null);
    }

    public FrescoModule(ReactApplicationContext reactContext, ImagePipelineConfig config) {
        super(reactContext);
        this.mConfig = config;
    }

    public void initialize() {
        super.initialize();
        if (!hasBeenInitialized()) {
            SoLoaderShim.setHandler(new FrescoHandler());
            if (this.mConfig == null) {
                this.mConfig = getDefaultConfig(getReactApplicationContext());
            }
            Fresco.initialize(getReactApplicationContext().getApplicationContext(), this.mConfig);
            sHasBeenInitialized = true;
        } else if (this.mConfig != null) {
            FLog.m1847w(ReactConstants.TAG, "Fresco has already been initialized with a different config. The new Fresco configuration will be ignored!");
        }
        this.mConfig = null;
    }

    public String getName() {
        return "FrescoModule";
    }

    public void clearSensitiveData() {
        Fresco.getImagePipeline().clearCaches();
    }

    public static boolean hasBeenInitialized() {
        return sHasBeenInitialized;
    }

    private static ImagePipelineConfig getDefaultConfig(Context context) {
        return getDefaultConfigBuilder(context).build();
    }

    public static Builder getDefaultConfigBuilder(Context context) {
        HashSet<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new SystraceRequestListener());
        return OkHttpImagePipelineConfigFactory.newBuilder(context.getApplicationContext(), OkHttpClientProvider.getOkHttpClient()).setDownsampleEnabled(false).setRequestListeners(requestListeners);
    }
}
