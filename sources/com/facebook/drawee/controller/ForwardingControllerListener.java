package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class ForwardingControllerListener<INFO> implements ControllerListener<INFO> {
    private static final String TAG = "FdingControllerListener";
    private final List<ControllerListener<? super INFO>> mListeners = new ArrayList(2);

    public static <INFO> ForwardingControllerListener<INFO> create() {
        return new ForwardingControllerListener<>();
    }

    /* renamed from: of */
    public static <INFO> ForwardingControllerListener<INFO> m1873of(ControllerListener<? super INFO> listener) {
        ForwardingControllerListener<INFO> forwarder = create();
        forwarder.addListener(listener);
        return forwarder;
    }

    /* renamed from: of */
    public static <INFO> ForwardingControllerListener<INFO> m1874of(ControllerListener<? super INFO> listener1, ControllerListener<? super INFO> listener2) {
        ForwardingControllerListener<INFO> forwarder = create();
        forwarder.addListener(listener1);
        forwarder.addListener(listener2);
        return forwarder;
    }

    public synchronized void addListener(ControllerListener<? super INFO> listener) {
        this.mListeners.add(listener);
    }

    public synchronized void removeListener(ControllerListener<? super INFO> listener) {
        this.mListeners.remove(listener);
    }

    public synchronized void clearListeners() {
        this.mListeners.clear();
    }

    private synchronized void onException(String message, Throwable t) {
        Log.e(TAG, message, t);
    }

    public synchronized void onSubmit(String id, Object callerContext) {
        int numberOfListeners = this.mListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onSubmit(id, callerContext);
            } catch (Exception exception) {
                onException("InternalListener exception in onSubmit", exception);
            }
        }
    }

    public synchronized void onFinalImageSet(String id, INFO imageInfo, Animatable animatable) {
        int numberOfListeners = this.mListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onFinalImageSet(id, imageInfo, animatable);
            } catch (Exception exception) {
                onException("InternalListener exception in onFinalImageSet", exception);
            }
        }
    }

    public void onIntermediateImageSet(String id, INFO imageInfo) {
        int numberOfListeners = this.mListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onIntermediateImageSet(id, imageInfo);
            } catch (Exception exception) {
                onException("InternalListener exception in onIntermediateImageSet", exception);
            }
        }
    }

    public void onIntermediateImageFailed(String id, Throwable throwable) {
        int numberOfListeners = this.mListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onIntermediateImageFailed(id, throwable);
            } catch (Exception exception) {
                onException("InternalListener exception in onIntermediateImageFailed", exception);
            }
        }
    }

    public synchronized void onFailure(String id, Throwable throwable) {
        int numberOfListeners = this.mListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onFailure(id, throwable);
            } catch (Exception exception) {
                onException("InternalListener exception in onFailure", exception);
            }
        }
    }

    public synchronized void onRelease(String id) {
        int numberOfListeners = this.mListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onRelease(id);
            } catch (Exception exception) {
                onException("InternalListener exception in onRelease", exception);
            }
        }
    }
}
