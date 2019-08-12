package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;

@TargetApi(9)
/* renamed from: android.support.v4.view.LayoutInflaterCompatBase */
class LayoutInflaterCompatBase {

    /* renamed from: android.support.v4.view.LayoutInflaterCompatBase$FactoryWrapper */
    static class FactoryWrapper implements Factory {
        final LayoutInflaterFactory mDelegateFactory;

        FactoryWrapper(LayoutInflaterFactory delegateFactory) {
            this.mDelegateFactory = delegateFactory;
        }

        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return this.mDelegateFactory.onCreateView(null, name, context, attrs);
        }

        public String toString() {
            return getClass().getName() + "{" + this.mDelegateFactory + "}";
        }
    }

    static void setFactory(LayoutInflater inflater, LayoutInflaterFactory factory) {
        inflater.setFactory(factory != null ? new FactoryWrapper(factory) : null);
    }

    static LayoutInflaterFactory getFactory(LayoutInflater inflater) {
        Factory factory = inflater.getFactory();
        if (factory instanceof FactoryWrapper) {
            return ((FactoryWrapper) factory).mDelegateFactory;
        }
        return null;
    }
}
