package com.tencent.p313mm.sdk.channel.compatible;

import android.os.Bundle;
import android.util.Log;

/* renamed from: com.tencent.mm.sdk.channel.compatible.IntentUtil */
public class IntentUtil {
    public static String getString(Bundle bundle, String name) {
        if (bundle == null) {
            return null;
        }
        try {
            return bundle.getString(name);
        } catch (Exception e) {
            Log.e("MicroMsg.IntentUtil", "getStringExtra exception:%s" + e.getMessage());
            return null;
        }
    }
}
