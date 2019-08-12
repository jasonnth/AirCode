package com.airbnb.lottie;

import android.content.res.Resources;
import com.airbnb.lottie.LottieComposition.Factory;
import java.io.InputStream;

final class FileCompositionLoader extends CompositionLoader<InputStream> {
    private final OnCompositionLoadedListener loadedListener;
    private final Resources res;

    FileCompositionLoader(Resources res2, OnCompositionLoadedListener loadedListener2) {
        this.res = res2;
        this.loadedListener = loadedListener2;
    }

    /* access modifiers changed from: protected */
    public LottieComposition doInBackground(InputStream... params) {
        return Factory.fromInputStream(this.res, params[0]);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(LottieComposition composition) {
        this.loadedListener.onCompositionLoaded(composition);
    }
}
