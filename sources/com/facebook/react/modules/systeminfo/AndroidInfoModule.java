package com.facebook.react.modules.systeminfo;

import android.os.Build.VERSION;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = "AndroidConstants")
public class AndroidInfoModule extends BaseJavaModule {
    public String getName() {
        return "AndroidConstants";
    }

    public Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap<>();
        constants.put("Version", Integer.valueOf(VERSION.SDK_INT));
        constants.put("ServerHost", AndroidInfoHelpers.getServerHost());
        return constants;
    }
}
