package com.airbnb.p027n2.browser;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory2;
import android.view.View;
import com.airbnb.p027n2.components.DLSComponent;
import com.airbnb.p027n2.components.DLSComponents;

/* renamed from: com.airbnb.n2.browser.DLSOverlayLayoutInflaterFactory */
public class DLSOverlayLayoutInflaterFactory implements Factory2 {
    private static final String DLS_COMPONENT_PACKAGE_NAME = DLSComponent.class.getPackage().getName();
    private static final OnLongClickListener longClickListener = DLSOverlayLayoutInflaterFactory$$Lambda$3.lambdaFactory$();
    private final AppCompatDelegate delegate;

    /* renamed from: com.airbnb.n2.browser.DLSOverlayLayoutInflaterFactory$OnLongClickListener */
    public interface OnLongClickListener {
        boolean onLongClick(View view, DLSComponent<?> dLSComponent);
    }

    static /* synthetic */ boolean lambda$static$0(View view, DLSComponent component) {
        Context context = view.getContext();
        context.startActivity(DLSComponentBrowserActivity.newIntent(context, component));
        return true;
    }

    public static void setup(AppCompatActivity activity) {
        if (VERSION.SDK_INT >= 18) {
            LayoutInflater.from(activity).setFactory2(new DLSOverlayLayoutInflaterFactory(activity.getDelegate()));
        }
    }

    private DLSOverlayLayoutInflaterFactory(AppCompatDelegate delegate2) {
        this.delegate = delegate2;
    }

    public View onCreateView(String canonicalName, Context context, AttributeSet attrs) {
        return onCreateView(null, canonicalName, context, attrs);
    }

    public View onCreateView(View parent, String canonicalName, Context context, AttributeSet attrs) {
        if (canonicalName.startsWith(DLS_COMPONENT_PACKAGE_NAME)) {
            DLSComponent<?> component = DLSComponents.fromName(canonicalName.substring(canonicalName.lastIndexOf(46) + 1));
            if (component != null) {
                View view = component.createView(context, attrs);
                view.setOnLongClickListener(DLSOverlayLayoutInflaterFactory$$Lambda$1.lambdaFactory$(component));
                addOverlay(component, view);
                return view;
            }
        }
        return this.delegate.createView(parent, canonicalName, context, attrs);
    }

    @TargetApi(18)
    private void addOverlay(DLSComponent<?> component, View view) {
        Drawable drawable = new DLSOverlayDrawable(view.getContext(), component);
        view.addOnLayoutChangeListener(DLSOverlayLayoutInflaterFactory$$Lambda$2.lambdaFactory$(drawable));
        view.getOverlay().add(drawable);
    }
}
