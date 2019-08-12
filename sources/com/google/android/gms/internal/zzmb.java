package com.google.android.gms.internal;

import android.support.p000v4.util.SimpleArrayMap;
import android.view.View;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.internal.zzlx.zza;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzmb implements zza<zzgx> {
    private final boolean zzRc;

    public zzmb(boolean z) {
        this.zzRc = z;
    }

    private void zza(zzlx zzlx, JSONObject jSONObject, SimpleArrayMap<String, Future<zzgu>> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jSONObject.getString("name"), zzlx.zza(jSONObject, "image_value", this.zzRc));
    }

    private void zza(JSONObject jSONObject, SimpleArrayMap<String, String> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jSONObject.getString("name"), jSONObject.getString("string_value"));
    }

    private <K, V> SimpleArrayMap<K, V> zzc(SimpleArrayMap<K, Future<V>> simpleArrayMap) throws InterruptedException, ExecutionException {
        SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap<>();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= simpleArrayMap.size()) {
                return simpleArrayMap2;
            }
            simpleArrayMap2.put(simpleArrayMap.keyAt(i2), ((Future) simpleArrayMap.valueAt(i2)).get());
            i = i2 + 1;
        }
    }

    /* renamed from: zzd */
    public zzgx zza(zzlx zzlx, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        View view = null;
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        zzqm zzd = zzlx.zzd(jSONObject);
        zzqm zzc = zzlx.zzc(jSONObject, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("type");
            if ("string".equals(string)) {
                zza(jSONObject2, simpleArrayMap2);
            } else if (ContentFrameworkAnalytics.IMAGE.equals(string)) {
                zza(zzlx, jSONObject2, simpleArrayMap);
            } else {
                String str = "Unknown custom asset type: ";
                String valueOf = String.valueOf(string);
                zzpk.zzbh(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
        zzqw zzb = zzlx.zzb(zzc);
        String string2 = jSONObject.getString("custom_template_id");
        SimpleArrayMap zzc2 = zzc(simpleArrayMap);
        zzgs zzgs = (zzgs) zzd.get();
        zzrb zzrb = zzb != null ? zzb.zzlG() : null;
        if (zzb != null) {
            view = zzb.getView();
        }
        return new zzgx(string2, zzc2, simpleArrayMap2, zzgs, zzrb, view);
    }
}
