package com.airbnb.lottie;

import android.os.AsyncTask;

abstract class CompositionLoader<Params> extends AsyncTask<Params, Void, LottieComposition> implements Cancellable {
    CompositionLoader() {
    }

    public void cancel() {
        cancel(true);
    }
}
