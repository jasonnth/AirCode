package com.airbnb.android.lib.glide;

import android.content.Context;
import com.airbnb.p027n2.utils.Base64Model;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import java.io.InputStream;

public class Base64ModelLoader implements StreamModelLoader<Base64Model> {

    public static class Factory implements ModelLoaderFactory<Base64Model, InputStream> {
        public ModelLoader<Base64Model, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new Base64ModelLoader();
        }

        public void teardown() {
        }
    }

    public DataFetcher<InputStream> getResourceFetcher(Base64Model model, int width, int height) {
        return new Base64StreamDataFetcher(model);
    }
}
