package com.facebook.react.modules.permissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.SparseArray;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.util.ArrayList;

@ReactModule(name = "PermissionsAndroid")
public class PermissionsModule extends ReactContextBaseJavaModule implements PermissionListener {
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    private final String DENIED = "denied";
    private final String GRANTED = "granted";
    private final String NEVER_ASK_AGAIN = "never_ask_again";
    private final SparseArray<Callback> mCallbacks = new SparseArray<>();
    private int mRequestCode = 0;

    public PermissionsModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "PermissionsAndroid";
    }

    @ReactMethod
    public void checkPermission(String permission, Promise promise) {
        boolean z = true;
        Context context = getReactApplicationContext().getBaseContext();
        if (VERSION.SDK_INT < 23) {
            if (context.checkPermission(permission, Process.myPid(), Process.myUid()) != 0) {
                z = false;
            }
            promise.resolve(Boolean.valueOf(z));
            return;
        }
        if (context.checkSelfPermission(permission) != 0) {
            z = false;
        }
        promise.resolve(Boolean.valueOf(z));
    }

    @ReactMethod
    public void shouldShowRequestPermissionRationale(String permission, Promise promise) {
        if (VERSION.SDK_INT < 23) {
            promise.resolve(Boolean.valueOf(false));
            return;
        }
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity().shouldShowRequestPermissionRationale(permission)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, (Throwable) e);
        }
    }

    @ReactMethod
    public void requestPermission(final String permission, final Promise promise) {
        boolean z = true;
        Context context = getReactApplicationContext().getBaseContext();
        if (VERSION.SDK_INT < 23) {
            if (context.checkPermission(permission, Process.myPid(), Process.myUid()) != 0) {
                z = false;
            }
            promise.resolve(Boolean.valueOf(z));
        } else if (context.checkSelfPermission(permission) == 0) {
            promise.resolve("granted");
        } else {
            try {
                PermissionAwareActivity activity = getPermissionAwareActivity();
                this.mCallbacks.put(this.mRequestCode, new Callback() {
                    public void invoke(Object... args) {
                        if (args[0][0] == 0) {
                            promise.resolve("granted");
                        } else if (args[1].shouldShowRequestPermissionRationale(permission)) {
                            promise.resolve("denied");
                        } else {
                            promise.resolve("never_ask_again");
                        }
                    }
                });
                activity.requestPermissions(new String[]{permission}, this.mRequestCode, this);
                this.mRequestCode++;
            } catch (IllegalStateException e) {
                promise.reject(ERROR_INVALID_ACTIVITY, (Throwable) e);
            }
        }
    }

    @ReactMethod
    public void requestMultiplePermissions(ReadableArray permissions2, final Promise promise) {
        final WritableMap grantedPermissions = new WritableNativeMap();
        final ArrayList<String> permissionsToCheck = new ArrayList<>();
        int checkedPermissionsCount = 0;
        Context context = getReactApplicationContext().getBaseContext();
        for (int i = 0; i < permissions2.size(); i++) {
            String perm = permissions2.getString(i);
            if (VERSION.SDK_INT < 23) {
                grantedPermissions.putString(perm, context.checkPermission(perm, Process.myPid(), Process.myUid()) == 0 ? "granted" : "denied");
                checkedPermissionsCount++;
            } else if (context.checkSelfPermission(perm) == 0) {
                grantedPermissions.putString(perm, "granted");
                checkedPermissionsCount++;
            } else {
                permissionsToCheck.add(perm);
            }
        }
        if (permissions2.size() == checkedPermissionsCount) {
            promise.resolve(grantedPermissions);
            return;
        }
        try {
            PermissionAwareActivity activity = getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, new Callback() {
                public void invoke(Object... args) {
                    int[] results = args[0];
                    PermissionAwareActivity activity = args[1];
                    for (int j = 0; j < permissionsToCheck.size(); j++) {
                        String permission = (String) permissionsToCheck.get(j);
                        if (results[j] == 0) {
                            grantedPermissions.putString(permission, "granted");
                        } else if (activity.shouldShowRequestPermissionRationale(permission)) {
                            grantedPermissions.putString(permission, "denied");
                        } else {
                            grantedPermissions.putString(permission, "never_ask_again");
                        }
                    }
                    promise.resolve(grantedPermissions);
                }
            });
            activity.requestPermissions((String[]) permissionsToCheck.toArray(new String[0]), this.mRequestCode, this);
            this.mRequestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, (Throwable) e);
        }
    }

    public boolean onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        ((Callback) this.mCallbacks.get(requestCode)).invoke(grantResults, getPermissionAwareActivity());
        this.mCallbacks.remove(requestCode);
        return this.mCallbacks.size() == 0;
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        } else if (activity instanceof PermissionAwareActivity) {
            return (PermissionAwareActivity) activity;
        } else {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
    }
}
