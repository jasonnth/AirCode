package com.facebook.react;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings;
import android.support.p000v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionListener;

public class ReactActivityDelegate {
    private static final String REDBOX_PERMISSION_GRANTED_MESSAGE = "Overlay permissions have been granted.";
    private static final String REDBOX_PERMISSION_MESSAGE = "Overlay permissions needs to be granted in order for react native apps to run in dev mode";
    private final int REQUEST_OVERLAY_PERMISSION_CODE = 1111;
    private final Activity mActivity;
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    private final FragmentActivity mFragmentActivity;
    private final String mMainComponentName;
    /* access modifiers changed from: private */
    public PermissionListener mPermissionListener;
    private Callback mPermissionsCallback;
    private ReactRootView mReactRootView;

    public ReactActivityDelegate(Activity activity, String mainComponentName) {
        this.mActivity = activity;
        this.mMainComponentName = mainComponentName;
        this.mFragmentActivity = null;
    }

    public ReactActivityDelegate(FragmentActivity fragmentActivity, String mainComponentName) {
        this.mFragmentActivity = fragmentActivity;
        this.mMainComponentName = mainComponentName;
        this.mActivity = null;
    }

    /* access modifiers changed from: protected */
    public Bundle getLaunchOptions() {
        return null;
    }

    /* access modifiers changed from: protected */
    public ReactRootView createRootView() {
        return new ReactRootView(getContext());
    }

    /* access modifiers changed from: protected */
    public ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getPlainActivity().getApplication()).getReactNativeHost();
    }

    public ReactInstanceManager getReactInstanceManager() {
        return getReactNativeHost().getReactInstanceManager();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        boolean needsOverlayPermission = false;
        if (getReactNativeHost().getUseDeveloperSupport() && VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(getContext())) {
            needsOverlayPermission = true;
            Intent serviceIntent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getContext().getPackageName()));
            FLog.m1847w(ReactConstants.TAG, REDBOX_PERMISSION_MESSAGE);
            Toast.makeText(getContext(), REDBOX_PERMISSION_MESSAGE, 1).show();
            ((Activity) getContext()).startActivityForResult(serviceIntent, 1111);
        }
        if (this.mMainComponentName != null && !needsOverlayPermission) {
            loadApp(this.mMainComponentName);
        }
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    }

    /* access modifiers changed from: protected */
    public void loadApp(String appKey) {
        if (this.mReactRootView != null) {
            throw new IllegalStateException("Cannot loadApp while app is already running.");
        }
        this.mReactRootView = createRootView();
        this.mReactRootView.startReactApplication(getReactNativeHost().getReactInstanceManager(), appKey, getLaunchOptions());
        getPlainActivity().setContentView(this.mReactRootView);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostPause(getPlainActivity());
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostResume(getPlainActivity(), (DefaultHardwareBackBtnHandler) getPlainActivity());
        }
        if (this.mPermissionsCallback != null) {
            this.mPermissionsCallback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mReactRootView != null) {
            this.mReactRootView.unmountReactApplication();
            this.mReactRootView = null;
        }
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostDestroy(getPlainActivity());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onActivityResult(getPlainActivity(), requestCode, resultCode, data);
        } else if (requestCode == 1111 && VERSION.SDK_INT >= 23 && Settings.canDrawOverlays(getContext())) {
            if (this.mMainComponentName != null) {
                loadApp(this.mMainComponentName);
            }
            Toast.makeText(getContext(), REDBOX_PERMISSION_GRANTED_MESSAGE, 1).show();
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (getReactNativeHost().hasInstance() && getReactNativeHost().getUseDeveloperSupport()) {
            if (keyCode == 82) {
                getReactNativeHost().getReactInstanceManager().showDevOptionsDialog();
                return true;
            } else if (((DoubleTapReloadRecognizer) Assertions.assertNotNull(this.mDoubleTapReloadRecognizer)).didDoubleTapR(keyCode, getPlainActivity().getCurrentFocus())) {
                getReactNativeHost().getReactInstanceManager().getDevSupportManager().handleReloadJS();
                return true;
            }
        }
        return false;
    }

    public boolean onBackPressed() {
        if (!getReactNativeHost().hasInstance()) {
            return false;
        }
        getReactNativeHost().getReactInstanceManager().onBackPressed();
        return true;
    }

    public boolean onNewIntent(Intent intent) {
        if (!getReactNativeHost().hasInstance()) {
            return false;
        }
        getReactNativeHost().getReactInstanceManager().onNewIntent(intent);
        return true;
    }

    @TargetApi(23)
    public void requestPermissions(String[] permissions2, int requestCode, PermissionListener listener) {
        this.mPermissionListener = listener;
        getPlainActivity().requestPermissions(permissions2, requestCode);
    }

    public void onRequestPermissionsResult(final int requestCode, final String[] permissions2, final int[] grantResults) {
        this.mPermissionsCallback = new Callback() {
            public void invoke(Object... args) {
                if (ReactActivityDelegate.this.mPermissionListener != null && ReactActivityDelegate.this.mPermissionListener.onRequestPermissionsResult(requestCode, permissions2, grantResults)) {
                    ReactActivityDelegate.this.mPermissionListener = null;
                }
            }
        };
    }

    private Context getContext() {
        if (this.mActivity != null) {
            return this.mActivity;
        }
        return (Context) Assertions.assertNotNull(this.mFragmentActivity);
    }

    private Activity getPlainActivity() {
        return (Activity) getContext();
    }
}
