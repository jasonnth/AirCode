package com.jumio.core.mvp.model;

import android.os.Handler;
import android.os.Looper;
import com.jumio.core.util.ReflectionUtil;
import java.util.ArrayList;
import java.util.List;

public class Publisher<Result> {
    private List<Subscriber<Result>> mSubscribers = new ArrayList();

    public boolean add(Subscriber<Result> subscriber) {
        return !getSubscribers().contains(subscriber) && getSubscribers().add(subscriber);
    }

    /* access modifiers changed from: protected */
    public List<Subscriber<Result>> getSubscribers() {
        return this.mSubscribers;
    }

    public boolean remove(Subscriber<Result> subscriber) {
        return getSubscribers().remove(subscriber);
    }

    /* access modifiers changed from: protected */
    public final void publishResult(final Result data) {
        for (final Subscriber<Result> subscriber : getSubscribers()) {
            if (subscriber != null) {
                String str = "onResult";
                try {
                    Class[] clsArr = new Class[1];
                    clsArr[0] = data != null ? data.getClass() : null;
                    InvokeOnUiThread invokeOnUiThread = (InvokeOnUiThread) ReflectionUtil.getMethod(subscriber, str, clsArr).getAnnotation(InvokeOnUiThread.class);
                    if (invokeOnUiThread == null || invokeOnUiThread.value()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                subscriber.onResult(data);
                            }
                        });
                    } else {
                        subscriber.onResult(data);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    subscriber.onResult(data);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void publishError(final Throwable error) {
        for (final Subscriber subscriber : getSubscribers()) {
            if (subscriber != null) {
                try {
                    InvokeOnUiThread ann = (InvokeOnUiThread) ReflectionUtil.getMethod(subscriber, "onError", Throwable.class).getAnnotation(InvokeOnUiThread.class);
                    if (ann == null || ann.value()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                subscriber.onError(error);
                            }
                        });
                    } else {
                        subscriber.onError(error);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    subscriber.onError(error);
                }
            }
        }
    }
}
