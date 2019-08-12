package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzw;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;
import p005cn.jpush.android.JPushConstants.PushService;

@zzme
public class zzge {
    private Context mContext = null;
    private boolean zzFx;
    private String zzFy;
    private Map<String, String> zzFz;
    private String zzwd = null;

    public zzge(Context context, String str) {
        this.mContext = context;
        this.zzwd = str;
        this.zzFx = ((Boolean) zzgd.zzBZ.get()).booleanValue();
        this.zzFy = (String) zzgd.zzCa.get();
        this.zzFz = new LinkedHashMap();
        this.zzFz.put("s", "gmob_sdk");
        this.zzFz.put("v", "3");
        this.zzFz.put("os", VERSION.RELEASE);
        this.zzFz.put("sdk", VERSION.SDK);
        this.zzFz.put("device", zzw.zzcM().zzkN());
        this.zzFz.put(PushService.PARAM_APP, context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        this.zzFz.put("is_lite_sdk", zzw.zzcM().zzU(context) ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Future zzA = zzw.zzcV().zzA(this.mContext);
        try {
            zzA.get();
            this.zzFz.put("network_coarse", Integer.toString(((zzni) zzA.get()).zzUQ));
            this.zzFz.put("network_fine", Integer.toString(((zzni) zzA.get()).zzUR));
        } catch (Exception e) {
            zzw.zzcQ().zza((Throwable) e, "CsiConfiguration.CsiConfiguration");
        }
    }

    /* access modifiers changed from: 0000 */
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: 0000 */
    public String zzdA() {
        return this.zzwd;
    }

    /* access modifiers changed from: 0000 */
    public boolean zzfu() {
        return this.zzFx;
    }

    /* access modifiers changed from: 0000 */
    public String zzfv() {
        return this.zzFy;
    }

    /* access modifiers changed from: 0000 */
    public Map<String, String> zzfw() {
        return this.zzFz;
    }
}
