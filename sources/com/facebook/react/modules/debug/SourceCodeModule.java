package com.facebook.react.modules.debug;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = "RCTSourceCode")
public class SourceCodeModule extends BaseJavaModule {
    private final String mSourceUrl;

    public SourceCodeModule(String sourceUrl) {
        this.mSourceUrl = sourceUrl;
    }

    public String getName() {
        return "RCTSourceCode";
    }

    public Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap<>();
        constants.put("scriptURL", this.mSourceUrl);
        return constants;
    }
}
