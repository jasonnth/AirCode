package com.facebook.react.views.drawer;

import android.support.p000v4.widget.DrawerLayout;
import android.support.p000v4.widget.DrawerLayout.LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.NativeGestureUtil;

class ReactDrawerLayout extends DrawerLayout {
    public static final int DEFAULT_DRAWER_WIDTH = -1;
    private int mDrawerPosition = 8388611;
    private int mDrawerWidth = -1;

    public ReactDrawerLayout(ReactContext reactContext) {
        super(reactContext);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!super.onInterceptTouchEvent(ev)) {
            return false;
        }
        NativeGestureUtil.notifyNativeGestureStarted(this, ev);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void openDrawer() {
        openDrawer(this.mDrawerPosition);
    }

    /* access modifiers changed from: 0000 */
    public void closeDrawer() {
        closeDrawer(this.mDrawerPosition);
    }

    /* access modifiers changed from: 0000 */
    public void setDrawerPosition(int drawerPosition) {
        this.mDrawerPosition = drawerPosition;
        setDrawerProperties();
    }

    /* access modifiers changed from: 0000 */
    public void setDrawerWidth(int drawerWidthInPx) {
        this.mDrawerWidth = drawerWidthInPx;
        setDrawerProperties();
    }

    /* access modifiers changed from: 0000 */
    public void setDrawerProperties() {
        if (getChildCount() == 2) {
            View drawerView = getChildAt(1);
            LayoutParams layoutParams = (LayoutParams) drawerView.getLayoutParams();
            layoutParams.gravity = this.mDrawerPosition;
            layoutParams.width = this.mDrawerWidth;
            drawerView.setLayoutParams(layoutParams);
            drawerView.setClickable(true);
        }
    }
}
