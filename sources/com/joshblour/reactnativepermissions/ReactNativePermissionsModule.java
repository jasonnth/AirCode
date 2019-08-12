package com.joshblour.reactnativepermissions;

import android.content.Intent;
import android.net.Uri;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.PermissionChecker;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.PromiseImpl;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.permissions.PermissionsModule;
import java.util.Locale;
import p005cn.jpush.android.JPushConstants.PushActivity;

public class ReactNativePermissionsModule extends ReactContextBaseJavaModule {
    private final PermissionsModule mPermissionsModule = new PermissionsModule(this.reactContext);
    private final ReactApplicationContext reactContext;

    public enum RNType {
        LOCATION,
        CAMERA,
        MICROPHONE,
        CONTACTS,
        EVENT,
        STORAGE,
        PHOTO
    }

    public ReactNativePermissionsModule(ReactApplicationContext reactContext2) {
        super(reactContext2);
        this.reactContext = reactContext2;
    }

    public String getName() {
        return "ReactNativePermissions";
    }

    @ReactMethod
    public void getPermissionStatus(String permissionString, String nullForiOSCompat, Promise promise) {
        String permission = permissionForString(permissionString);
        if (permission == null) {
            promise.reject("unknown-permission", "ReactNativePermissions: unknown permission type - " + permissionString);
            return;
        }
        switch (PermissionChecker.checkSelfPermission(this.reactContext, permission)) {
            case -2:
                promise.resolve("denied");
                return;
            case -1:
                if (getCurrentActivity() != null) {
                    promise.resolve(ActivityCompat.shouldShowRequestPermissionRationale(getCurrentActivity(), permission) ? "denied" : "undetermined");
                    return;
                } else {
                    promise.resolve("denied");
                    return;
                }
            case 0:
                promise.resolve("authorized");
                return;
            default:
                promise.resolve("undetermined");
                return;
        }
    }

    @ReactMethod
    public void requestPermission(final String permissionString, String nullForiOSCompat, final Promise promise) {
        this.mPermissionsModule.requestPermission(permissionForString(permissionString), new PromiseImpl(new Callback() {
            public void invoke(Object... args) {
                ReactNativePermissionsModule.this.getPermissionStatus(permissionString, "", promise);
            }
        }, new Callback() {
            public void invoke(Object... args) {
            }
        }));
    }

    @ReactMethod
    public void canOpenSettings(Promise promise) {
        promise.resolve(Boolean.valueOf(true));
    }

    @ReactMethod
    public void openSettings() {
        Intent i = new Intent();
        i.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        i.addCategory(PushActivity.CATEGORY_1);
        i.setData(Uri.parse("package:" + this.reactContext.getPackageName()));
        i.addFlags(268435456);
        i.addFlags(1073741824);
        i.addFlags(8388608);
        this.reactContext.startActivity(i);
    }

    private String permissionForString(String permission) {
        switch (RNType.valueOf(permission.toUpperCase(Locale.ENGLISH))) {
            case LOCATION:
                return "android.permission.ACCESS_FINE_LOCATION";
            case CAMERA:
                return "android.permission.CAMERA";
            case MICROPHONE:
                return "android.permission.RECORD_AUDIO";
            case CONTACTS:
                return "android.permission.READ_CONTACTS";
            case EVENT:
                return "android.permission.READ_CALENDAR";
            case STORAGE:
            case PHOTO:
                return "android.permission.READ_EXTERNAL_STORAGE";
            default:
                return null;
        }
    }
}
