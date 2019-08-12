package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.airbnb.android.react.ReactVideoView;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.ads.internal.overlay.zzl;
import java.util.Map;
import org.json.JSONObject;

@zzme
public final class zzio implements zzid {
    private boolean zzIv;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            return zzel.zzeT().zzb(context, Integer.parseInt(str2));
        } catch (NumberFormatException e) {
            zzpk.zzbh(new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(str2).length()).append("Could not parse ").append(str).append(" in a video GMSG: ").append(str2).toString());
            return i;
        }
    }

    public void zza(zzqw zzqw, Map<String, String> map) {
        int i;
        int i2;
        String str = (String) map.get("action");
        if (str == null) {
            zzpk.zzbh("Action missing from video GMSG.");
            return;
        }
        if (zzpk.zzak(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String valueOf = String.valueOf(jSONObject.toString());
            zzpk.zzbf(new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(valueOf).length()).append("Video GMSG: ").append(str).append(" ").append(valueOf).toString());
        }
        if ("background".equals(str)) {
            String str2 = (String) map.get(ViewProps.COLOR);
            if (TextUtils.isEmpty(str2)) {
                zzpk.zzbh("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzqw.setBackgroundColor(Color.parseColor(str2));
            } catch (IllegalArgumentException e) {
                zzpk.zzbh("Invalid color parameter in video GMSG.");
            }
        } else {
            zzqv zzlD = zzqw.zzlD();
            if (zzlD == null) {
                zzpk.zzbh("Could not get underlay container for a video GMSG.");
                return;
            }
            boolean equals = "new".equals(str);
            boolean equals2 = ViewProps.POSITION.equals(str);
            if (equals || equals2) {
                Context context = zzqw.getContext();
                int zza = zza(context, map, "x", 0);
                int zza2 = zza(context, map, "y", 0);
                int zza3 = zza(context, map, "w", -1);
                int zza4 = zza(context, map, "h", -1);
                if (((Boolean) zzgd.zzEv.get()).booleanValue()) {
                    i = Math.min(zza3, zzqw.getMeasuredWidth() - zza);
                    zza4 = Math.min(zza4, zzqw.getMeasuredHeight() - zza2);
                } else {
                    i = zza3;
                }
                try {
                    i2 = Integer.parseInt((String) map.get("player"));
                } catch (NumberFormatException e2) {
                    i2 = 0;
                }
                boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                if (!equals || zzlD.zzlo() != null) {
                    zzlD.zze(zza, zza2, i, zza4);
                } else {
                    zzlD.zza(zza, zza2, i, zza4, i2, parseBoolean);
                }
            } else {
                zzl zzlo = zzlD.zzlo();
                if (zzlo == null) {
                    zzl.zzi(zzqw);
                } else if ("click".equals(str)) {
                    Context context2 = zzqw.getContext();
                    int zza5 = zza(context2, map, "x", 0);
                    int zza6 = zza(context2, map, "y", 0);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) zza5, (float) zza6, 0);
                    zzlo.zzf(obtain);
                    obtain.recycle();
                } else if (ReactVideoView.EVENT_PROP_CURRENT_TIME.equals(str)) {
                    String str3 = (String) map.get("time");
                    if (str3 == null) {
                        zzpk.zzbh("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        zzlo.seekTo((int) (Float.parseFloat(str3) * 1000.0f));
                    } catch (NumberFormatException e3) {
                        String str4 = "Could not parse time parameter from currentTime video GMSG: ";
                        String valueOf2 = String.valueOf(str3);
                        zzpk.zzbh(valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4));
                    }
                } else if ("hide".equals(str)) {
                    zzlo.setVisibility(4);
                } else if ("load".equals(str)) {
                    zzlo.zzib();
                } else if ("muted".equals(str)) {
                    if (Boolean.parseBoolean((String) map.get("muted"))) {
                        zzlo.zzhZ();
                    } else {
                        zzlo.zzia();
                    }
                } else if ("pause".equals(str)) {
                    zzlo.pause();
                } else if ("play".equals(str)) {
                    zzlo.play();
                } else if ("show".equals(str)) {
                    zzlo.setVisibility(0);
                } else if ("src".equals(str)) {
                    zzlo.zzaC((String) map.get("src"));
                } else if ("touchMove".equals(str)) {
                    Context context3 = zzqw.getContext();
                    zzlo.zza((float) zza(context3, map, "dx", 0), (float) zza(context3, map, "dy", 0));
                    if (!this.zzIv) {
                        zzqw.zzlt().zzhL();
                        this.zzIv = true;
                    }
                } else if ("volume".equals(str)) {
                    String str5 = (String) map.get("volume");
                    if (str5 == null) {
                        zzpk.zzbh("Level parameter missing from volume video GMSG.");
                        return;
                    }
                    try {
                        zzlo.zzb(Float.parseFloat(str5));
                    } catch (NumberFormatException e4) {
                        String str6 = "Could not parse volume parameter from volume video GMSG: ";
                        String valueOf3 = String.valueOf(str5);
                        zzpk.zzbh(valueOf3.length() != 0 ? str6.concat(valueOf3) : new String(str6));
                    }
                } else if ("watermark".equals(str)) {
                    zzlo.zzic();
                } else {
                    String str7 = "Unknown video action: ";
                    String valueOf4 = String.valueOf(str);
                    zzpk.zzbh(valueOf4.length() != 0 ? str7.concat(valueOf4) : new String(str7));
                }
            }
        }
    }
}
