package com.mparticle.internal;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import org.json.JSONArray;

@TargetApi(19)
/* renamed from: com.mparticle.internal.d */
public class C4606d {
    /* renamed from: a */
    public static void m2232a(JSONArray jSONArray, int i) {
        if (VERSION.SDK_INT >= 19) {
            jSONArray.remove(i);
        }
    }
}
