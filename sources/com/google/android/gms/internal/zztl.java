package com.google.android.gms.internal;

import android.app.Activity;
import java.util.Map;

public class zztl implements zzsn {
    public Map<String, String> zzagT;

    public String zzcd(String str) {
        String str2 = (String) this.zzagT.get(str);
        return str2 != null ? str2 : str;
    }

    public String zzr(Activity activity) {
        return zzcd(activity.getClass().getCanonicalName());
    }
}
