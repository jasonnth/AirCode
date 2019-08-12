package com.airbnb.android.lib.glide;

import android.content.Context;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreApplicationFacade;
import com.airbnb.p027n2.utils.Base64Model;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpGlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader.Factory;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import java.io.InputStream;

public class AirGlideModule extends OkHttpGlideModule {
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        builder.setMemoryCache(new LruResourceCache(CacheSizeCalculator.getMemoryCacheSize()));
        builder.setBitmapPool(new LruBitmapPool(CacheSizeCalculator.getBitmapPoolSize()));
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, CacheSizeCalculator.MAX_DISK_CACHE_SIZE));
        super.applyOptions(context, builder);
    }

    public void registerComponents(Context context, Glide glide) {
        super.registerComponents(context, glide);
        CoreApplicationFacade application = CoreApplication.instance(context);
        if (!application.isTestApplication()) {
            glide.register(GlideUrl.class, InputStream.class, new Factory(application.component().okHttp()));
        }
        glide.register(Base64Model.class, InputStream.class, new Base64ModelLoader.Factory());
    }
}
