package com.airbnb.android.react;

import com.airbnb.android.core.utils.ExternalAppUtils;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;

public class WeChatModule extends VersionedReactModuleBase {
    private static final int VERSION = 1;

    WeChatModule(ReactApplicationContext reactContext) {
        super(reactContext, 1);
    }

    public String getName() {
        return "WeChat";
    }

    @ReactMethod
    public void isWeChatInstalled(Promise promise) {
        promise.resolve(Boolean.valueOf(ExternalAppUtils.isWechatInstalled(getReactApplicationContext())));
    }

    @ReactMethod
    public void isWeChatAppSupportApi(Promise promise) {
        promise.resolve(Boolean.valueOf(true));
    }
}
