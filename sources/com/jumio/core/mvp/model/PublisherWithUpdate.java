package com.jumio.core.mvp.model;

import android.os.Handler;
import android.os.Looper;
import com.jumio.core.util.ReflectionUtil;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class PublisherWithUpdate<Update, Result> {
    private List<SubscriberWithUpdate<Update, Result>> mSubscribers = new CopyOnWriteArrayList();

    /* access modifiers changed from: protected */
    public final void publishUpdate(final Update update) {
        for (final SubscriberWithUpdate<Update, Result> s : getSubscribers()) {
            if (s != null) {
                String str = "onUpdate";
                try {
                    Class[] clsArr = new Class[1];
                    clsArr[0] = update != null ? update.getClass() : null;
                    InvokeOnUiThread ann = (InvokeOnUiThread) ReflectionUtil.getMethod(s, str, clsArr).getAnnotation(InvokeOnUiThread.class);
                    if (ann == null || ann.value()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                s.onUpdate(update);
                            }
                        });
                    } else {
                        s.onUpdate(update);
                    }
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                    s.onUpdate(update);
                }
            }
        }
    }

    public boolean subscribe(SubscriberWithUpdate<Update, Result> subscriber) {
        return subscriber != null && !getSubscribers().contains(subscriber) && getSubscribers().add(subscriber);
    }

    /* access modifiers changed from: protected */
    public List<SubscriberWithUpdate<Update, Result>> getSubscribers() {
        return this.mSubscribers;
    }

    public boolean unsubscribe(SubscriberWithUpdate<Update, Result> subscriber) {
        return getSubscribers().remove(subscriber);
    }

    /* access modifiers changed from: protected */
    public final void publishResult(final Result data) {
        for (final Subscriber<Result> s : getSubscribers()) {
            if (s != null) {
                String str = "onResult";
                try {
                    Class[] clsArr = new Class[1];
                    clsArr[0] = data != null ? data.getClass() : null;
                    InvokeOnUiThread ann = (InvokeOnUiThread) ReflectionUtil.getMethod(s, str, clsArr).getAnnotation(InvokeOnUiThread.class);
                    if (ann == null || ann.value()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                s.onResult(data);
                            }
                        });
                    } else {
                        s.onResult(data);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    s.onResult(data);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void publishError(final Throwable error) {
        for (final Subscriber s : getSubscribers()) {
            if (s != null) {
                try {
                    InvokeOnUiThread ann = (InvokeOnUiThread) ReflectionUtil.getMethod(s, "onError", Throwable.class).getAnnotation(InvokeOnUiThread.class);
                    if (ann == null || ann.value()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                s.onError(error);
                            }
                        });
                    } else {
                        s.onError(error);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    s.onError(error);
                }
            }
        }
    }
}
