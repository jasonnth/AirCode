package com.airbnb.lottie;

import android.content.res.Resources;
import com.airbnb.lottie.LottieComposition.Factory;
import org.json.JSONObject;

final class JsonCompositionLoader extends CompositionLoader<JSONObject> {
    private final OnCompositionLoadedListener loadedListener;
    private final Resources res;

    JsonCompositionLoader(Resources res2, OnCompositionLoadedListener loadedListener2) {
        this.res = res2;
        this.loadedListener = loadedListener2;
    }

    /* access modifiers changed from: protected */
    public LottieComposition doInBackground(JSONObject... params) {
        return Factory.fromJsonSync(this.res, params[0]);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(LottieComposition composition) {
        this.loadedListener.onCompositionLoaded(composition);
    }
}
