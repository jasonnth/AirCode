package com.facebook.react.views.drawer;

import android.os.Build.VERSION;
import android.support.p000v4.widget.DrawerLayout;
import android.support.p000v4.widget.DrawerLayout.DrawerListener;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.drawer.events.DrawerClosedEvent;
import com.facebook.react.views.drawer.events.DrawerOpenedEvent;
import com.facebook.react.views.drawer.events.DrawerSlideEvent;
import com.facebook.react.views.drawer.events.DrawerStateChangedEvent;
import java.util.Map;

@ReactModule(name = "AndroidDrawerLayout")
public class ReactDrawerLayoutManager extends ViewGroupManager<ReactDrawerLayout> {
    public static final int CLOSE_DRAWER = 2;
    public static final int OPEN_DRAWER = 1;
    protected static final String REACT_CLASS = "AndroidDrawerLayout";

    public static class DrawerEventEmitter implements DrawerListener {
        private final DrawerLayout mDrawerLayout;
        private final EventDispatcher mEventDispatcher;

        public DrawerEventEmitter(DrawerLayout drawerLayout, EventDispatcher eventDispatcher) {
            this.mDrawerLayout = drawerLayout;
            this.mEventDispatcher = eventDispatcher;
        }

        public void onDrawerSlide(View view, float v) {
            this.mEventDispatcher.dispatchEvent(new DrawerSlideEvent(this.mDrawerLayout.getId(), v));
        }

        public void onDrawerOpened(View view) {
            this.mEventDispatcher.dispatchEvent(new DrawerOpenedEvent(this.mDrawerLayout.getId()));
        }

        public void onDrawerClosed(View view) {
            this.mEventDispatcher.dispatchEvent(new DrawerClosedEvent(this.mDrawerLayout.getId()));
        }

        public void onDrawerStateChanged(int i) {
            this.mEventDispatcher.dispatchEvent(new DrawerStateChangedEvent(this.mDrawerLayout.getId(), i));
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext reactContext, ReactDrawerLayout view) {
        view.setDrawerListener(new DrawerEventEmitter(view, ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher()));
    }

    /* access modifiers changed from: protected */
    public ReactDrawerLayout createViewInstance(ThemedReactContext context) {
        return new ReactDrawerLayout(context);
    }

    @ReactProp(defaultInt = 8388611, name = "drawerPosition")
    public void setDrawerPosition(ReactDrawerLayout view, int drawerPosition) {
        if (8388611 == drawerPosition || 8388613 == drawerPosition) {
            view.setDrawerPosition(drawerPosition);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Unknown drawerPosition " + drawerPosition);
    }

    @ReactProp(defaultFloat = Float.NaN, name = "drawerWidth")
    public void getDrawerWidth(ReactDrawerLayout view, float width) {
        int widthInPx;
        if (Float.isNaN(width)) {
            widthInPx = -1;
        } else {
            widthInPx = Math.round(PixelUtil.toPixelFromDIP(width));
        }
        view.setDrawerWidth(widthInPx);
    }

    @ReactProp(name = "drawerLockMode")
    public void setDrawerLockMode(ReactDrawerLayout view, String drawerLockMode) {
        if (drawerLockMode == null || "unlocked".equals(drawerLockMode)) {
            view.setDrawerLockMode(0);
        } else if ("locked-closed".equals(drawerLockMode)) {
            view.setDrawerLockMode(1);
        } else if ("locked-open".equals(drawerLockMode)) {
            view.setDrawerLockMode(2);
        } else {
            throw new JSApplicationIllegalArgumentException("Unknown drawerLockMode " + drawerLockMode);
        }
    }

    public void setElevation(ReactDrawerLayout view, float elevation) {
        if (VERSION.SDK_INT >= 21) {
            try {
                ReactDrawerLayout.class.getMethod("setDrawerElevation", new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(PixelUtil.toPixelFromDIP(elevation))});
            } catch (Exception ex) {
                FLog.m1848w(ReactConstants.TAG, "setDrawerElevation is not available in this version of the support lib.", (Throwable) ex);
            }
        }
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.m1883of("openDrawer", Integer.valueOf(1), "closeDrawer", Integer.valueOf(2));
    }

    public void receiveCommand(ReactDrawerLayout root, int commandId, ReadableArray args) {
        switch (commandId) {
            case 1:
                root.openDrawer();
                return;
            case 2:
                root.closeDrawer();
                return;
            default:
                return;
        }
    }

    public Map getExportedViewConstants() {
        return MapBuilder.m1882of("DrawerPosition", MapBuilder.m1883of("Left", Integer.valueOf(8388611), "Right", Integer.valueOf(8388613)));
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.m1885of(DrawerSlideEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onDrawerSlide"), DrawerOpenedEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onDrawerOpen"), DrawerClosedEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onDrawerClose"), DrawerStateChangedEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onDrawerStateChanged"));
    }

    public void addView(ReactDrawerLayout parent, View child, int index) {
        if (getChildCount(parent) >= 2) {
            throw new JSApplicationIllegalArgumentException("The Drawer cannot have more than two children");
        } else if (index == 0 || index == 1) {
            parent.addView(child, index);
            parent.setDrawerProperties();
        } else {
            throw new JSApplicationIllegalArgumentException("The only valid indices for drawer's child are 0 or 1. Got " + index + " instead.");
        }
    }
}
