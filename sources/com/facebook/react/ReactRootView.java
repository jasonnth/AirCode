package com.facebook.react;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.UIManagerModule;

public class ReactRootView extends SizeMonitoringFrameLayout implements RootView {
    private CustomGlobalLayoutListener mCustomGlobalLayoutListener;
    /* access modifiers changed from: private */
    public boolean mIsAttachedToInstance = false;
    private String mJSModuleName;
    private final JSTouchDispatcher mJSTouchDispatcher = new JSTouchDispatcher(this);
    private Bundle mLaunchOptions;
    /* access modifiers changed from: private */
    public ReactInstanceManager mReactInstanceManager;
    private ReactRootViewEventListener mRootViewEventListener;
    private int mRootViewTag;
    private boolean mWasMeasured = false;

    private class CustomGlobalLayoutListener implements OnGlobalLayoutListener {
        private int mDeviceRotation = 0;
        private int mKeyboardHeight = 0;
        private final int mMinKeyboardHeightDetected = ((int) PixelUtil.toPixelFromDIP(60.0f));
        private final Rect mVisibleViewArea = new Rect();

        CustomGlobalLayoutListener() {
        }

        public void onGlobalLayout() {
            if (ReactRootView.this.mReactInstanceManager != null && ReactRootView.this.mIsAttachedToInstance && ReactRootView.this.mReactInstanceManager.getCurrentReactContext() != null) {
                checkForKeyboardEvents();
                checkForDeviceOrientationChanges();
            }
        }

        private void checkForKeyboardEvents() {
            ReactRootView.this.getRootView().getWindowVisibleDisplayFrame(this.mVisibleViewArea);
            int heightDiff = DisplayMetricsHolder.getWindowDisplayMetrics().heightPixels - this.mVisibleViewArea.bottom;
            if (this.mKeyboardHeight != heightDiff && heightDiff > this.mMinKeyboardHeightDetected) {
                this.mKeyboardHeight = heightDiff;
                WritableMap params = Arguments.createMap();
                WritableMap coordinates = Arguments.createMap();
                coordinates.putDouble("screenY", (double) PixelUtil.toDIPFromPixel((float) this.mVisibleViewArea.bottom));
                coordinates.putDouble("screenX", (double) PixelUtil.toDIPFromPixel((float) this.mVisibleViewArea.left));
                coordinates.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.mVisibleViewArea.width()));
                coordinates.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.mKeyboardHeight));
                params.putMap("endCoordinates", coordinates);
                sendEvent("keyboardDidShow", params);
            } else if (this.mKeyboardHeight != 0 && heightDiff <= this.mMinKeyboardHeightDetected) {
                this.mKeyboardHeight = 0;
                sendEvent("keyboardDidHide", null);
            }
        }

        private void checkForDeviceOrientationChanges() {
            int rotation = ((WindowManager) ReactRootView.this.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
            if (this.mDeviceRotation != rotation) {
                this.mDeviceRotation = rotation;
                DisplayMetricsHolder.initDisplayMetrics(ReactRootView.this.getContext());
                emitUpdateDimensionsEvent();
                emitOrientationChanged(rotation);
            }
        }

        private void emitOrientationChanged(int newRotation) {
            String name;
            double rotationDegrees;
            boolean isLandscape = false;
            switch (newRotation) {
                case 0:
                    name = "portrait-primary";
                    rotationDegrees = 0.0d;
                    break;
                case 1:
                    name = "landscape-primary";
                    rotationDegrees = -90.0d;
                    isLandscape = true;
                    break;
                case 2:
                    name = "portrait-secondary";
                    rotationDegrees = 180.0d;
                    break;
                case 3:
                    name = "landscape-secondary";
                    rotationDegrees = 90.0d;
                    isLandscape = true;
                    break;
                default:
                    return;
            }
            WritableMap map = Arguments.createMap();
            map.putString("name", name);
            map.putDouble("rotationDegrees", rotationDegrees);
            map.putBoolean("isLandscape", isLandscape);
            sendEvent("namedOrientationDidChange", map);
        }

        private void emitUpdateDimensionsEvent() {
            DisplayMetrics windowDisplayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
            DisplayMetrics screenDisplayMetrics = DisplayMetricsHolder.getScreenDisplayMetrics();
            WritableMap windowDisplayMetricsMap = Arguments.createMap();
            windowDisplayMetricsMap.putInt("width", windowDisplayMetrics.widthPixels);
            windowDisplayMetricsMap.putInt("height", windowDisplayMetrics.heightPixels);
            windowDisplayMetricsMap.putDouble("scale", (double) windowDisplayMetrics.density);
            windowDisplayMetricsMap.putDouble("fontScale", (double) windowDisplayMetrics.scaledDensity);
            windowDisplayMetricsMap.putDouble("densityDpi", (double) windowDisplayMetrics.densityDpi);
            WritableMap screenDisplayMetricsMap = Arguments.createMap();
            screenDisplayMetricsMap.putInt("width", screenDisplayMetrics.widthPixels);
            screenDisplayMetricsMap.putInt("height", screenDisplayMetrics.heightPixels);
            screenDisplayMetricsMap.putDouble("scale", (double) screenDisplayMetrics.density);
            screenDisplayMetricsMap.putDouble("fontScale", (double) screenDisplayMetrics.scaledDensity);
            screenDisplayMetricsMap.putDouble("densityDpi", (double) screenDisplayMetrics.densityDpi);
            WritableMap dimensionsMap = Arguments.createMap();
            dimensionsMap.putMap("windowPhysicalPixels", windowDisplayMetricsMap);
            dimensionsMap.putMap("screenPhysicalPixels", screenDisplayMetricsMap);
            sendEvent("didUpdateDimensions", dimensionsMap);
        }

        private void sendEvent(String eventName, WritableMap params) {
            if (ReactRootView.this.mReactInstanceManager != null) {
                ((RCTDeviceEventEmitter) ReactRootView.this.mReactInstanceManager.getCurrentReactContext().getJSModule(RCTDeviceEventEmitter.class)).emit(eventName, params);
            }
        }
    }

    public interface ReactRootViewEventListener {
        void onAttachedToReactInstance(ReactRootView reactRootView);
    }

    public ReactRootView(Context context) {
        super(context);
    }

    public ReactRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactRootView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        this.mWasMeasured = true;
        if (this.mReactInstanceManager != null && !this.mIsAttachedToInstance) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    ReactRootView.this.attachToReactInstanceManager();
                }
            });
        }
    }

    public void onChildStartedNativeGesture(MotionEvent androidEvent) {
        if (this.mReactInstanceManager == null || !this.mIsAttachedToInstance || this.mReactInstanceManager.getCurrentReactContext() == null) {
            FLog.m1847w(ReactConstants.TAG, "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        this.mJSTouchDispatcher.onChildStartedNativeGesture(androidEvent, ((UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher());
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        dispatchJSTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        dispatchJSTouchEvent(ev);
        super.onTouchEvent(ev);
        return true;
    }

    private void dispatchJSTouchEvent(MotionEvent event) {
        if (this.mReactInstanceManager == null || !this.mIsAttachedToInstance || this.mReactInstanceManager.getCurrentReactContext() == null) {
            FLog.m1847w(ReactConstants.TAG, "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        this.mJSTouchDispatcher.handleTouchEvent(event, ((UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher());
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsAttachedToInstance) {
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsAttachedToInstance) {
            getViewTreeObserver().removeOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String moduleName) {
        startReactApplication(reactInstanceManager, moduleName, null);
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String moduleName, Bundle launchOptions) {
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(this.mReactInstanceManager == null, "This root view has already been attached to a catalyst instance manager");
        this.mReactInstanceManager = reactInstanceManager;
        this.mJSModuleName = moduleName;
        this.mLaunchOptions = launchOptions;
        if (!this.mReactInstanceManager.hasStartedCreatingInitialContext()) {
            this.mReactInstanceManager.createReactContextInBackground();
        }
        if (this.mWasMeasured) {
            attachToReactInstanceManager();
        }
    }

    public void unmountReactApplication() {
        if (this.mReactInstanceManager != null && this.mIsAttachedToInstance) {
            this.mReactInstanceManager.detachRootView(this);
            this.mIsAttachedToInstance = false;
        }
    }

    public void onAttachedToReactInstance() {
        if (this.mRootViewEventListener != null) {
            this.mRootViewEventListener.onAttachedToReactInstance(this);
        }
    }

    public void setEventListener(ReactRootViewEventListener eventListener) {
        this.mRootViewEventListener = eventListener;
    }

    /* access modifiers changed from: 0000 */
    public String getJSModuleName() {
        return (String) Assertions.assertNotNull(this.mJSModuleName);
    }

    /* access modifiers changed from: 0000 */
    public Bundle getLaunchOptions() {
        return this.mLaunchOptions;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void simulateAttachForTesting() {
        this.mIsAttachedToInstance = true;
        this.mWasMeasured = true;
    }

    private CustomGlobalLayoutListener getCustomGlobalLayoutListener() {
        if (this.mCustomGlobalLayoutListener == null) {
            this.mCustomGlobalLayoutListener = new CustomGlobalLayoutListener();
        }
        return this.mCustomGlobalLayoutListener;
    }

    /* access modifiers changed from: private */
    public void attachToReactInstanceManager() {
        if (!this.mIsAttachedToInstance) {
            this.mIsAttachedToInstance = true;
            ((ReactInstanceManager) Assertions.assertNotNull(this.mReactInstanceManager)).attachMeasuredRootView(this);
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        Assertions.assertCondition(!this.mIsAttachedToInstance, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }

    public int getRootViewTag() {
        return this.mRootViewTag;
    }

    public void setRootViewTag(int rootViewTag) {
        this.mRootViewTag = rootViewTag;
    }
}
