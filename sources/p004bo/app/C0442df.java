package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.df */
public final class C0442df extends C0434dc<C0399ca> {

    /* renamed from: b */
    private static final String f346b = AppboyLogger.getAppboyLogTag(C0442df.class);

    /* renamed from: a */
    final SharedPreferences f347a;

    /* renamed from: c */
    private C0399ca f348c;

    public C0442df(Context context) {
        this(context, null, null);
    }

    public C0442df(Context context, String str, String str2) {
        this.f348c = null;
        this.f347a = context.getSharedPreferences("com.appboy.storage.device_cache" + StringUtils.getCacheFileSuffix(context, str, str2), 0);
    }

    /* renamed from: c */
    public synchronized void mo6952c() {
        Editor edit = this.f347a.edit();
        edit.remove("cached_device");
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public C0399ca mo6929a() {
        JSONObject a = this.f348c.forJsonPut();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = new JSONObject(this.f347a.getString("cached_device", "{}"));
        } catch (JSONException e) {
            AppboyLogger.m1736e(f346b, "Caught exception confirming and unlocking Json objects.", e);
        }
        JSONObject jSONObject2 = new JSONObject();
        Iterator keys = a.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = a.opt(str);
            Object opt2 = jSONObject.opt(str);
            if ((opt instanceof JSONObject) || (opt instanceof JSONArray)) {
                if (opt2 != null) {
                    try {
                        if (!C0605hs.m1077a(String.valueOf(opt), String.valueOf(opt2), C0606ht.NON_EXTENSIBLE).mo7286b()) {
                        }
                    } catch (JSONException e2) {
                        AppboyLogger.m1734d(f346b, "Caught json exception creating dirty outbound device on a jsonObject value. Returning the whole device.", e2);
                        return this.f348c;
                    }
                }
                jSONObject2.put(str, opt);
            } else if (!opt.equals(opt2)) {
                try {
                    jSONObject2.put(str, opt);
                } catch (JSONException e3) {
                    AppboyLogger.m1736e(f346b, "Caught json exception creating dirty outbound device. Returning the whole device.", e3);
                    return this.f348c;
                }
            }
        }
        try {
            return C0399ca.m321a(jSONObject2);
        } catch (JSONException e4) {
            AppboyLogger.m1734d(f346b, "Caught json exception creating device from json. Returning the whole device.", e4);
            return this.f348c;
        }
    }

    /* renamed from: a */
    public void mo6950a(C0399ca caVar) {
        this.f348c = caVar;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo6930a(C0399ca caVar) {
        if (caVar != null) {
            try {
                JSONObject jSONObject = new JSONObject(this.f347a.getString("cached_device", "{}"));
                JSONObject a = caVar.forJsonPut();
                Editor edit = this.f347a.edit();
                edit.putString("cached_device", C0460ds.m532a(jSONObject, a).toString());
                edit.apply();
            } catch (JSONException e) {
                AppboyLogger.m1734d(f346b, "Caught exception confirming and unlocking device cache.", e);
            }
        }
    }
}
