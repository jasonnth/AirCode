package com.google.android.gms.internal;

import com.google.android.gms.common.util.zzf;
import java.util.Map;

@zzme
public class zzik implements zzid {
    static final Map<String, Integer> zzIq = zzf.zza("resize", Integer.valueOf(1), "playVideo", Integer.valueOf(2), "storePicture", Integer.valueOf(3), "createCalendarEvent", Integer.valueOf(4), "setOrientationProperties", Integer.valueOf(5), "closeResizedAd", Integer.valueOf(6));
    private final com.google.android.gms.ads.internal.zzf zzIo;
    private final zzkr zzIp;

    public zzik(com.google.android.gms.ads.internal.zzf zzf, zzkr zzkr) {
        this.zzIo = zzf;
        this.zzIp = zzkr;
    }

    public void zza(zzqw zzqw, Map<String, String> map) {
        int intValue = ((Integer) zzIq.get((String) map.get("a"))).intValue();
        if (intValue == 5 || this.zzIo == null || this.zzIo.zzcd()) {
            switch (intValue) {
                case 1:
                    this.zzIp.execute(map);
                    return;
                case 3:
                    new zzkt(zzqw, map).execute();
                    return;
                case 4:
                    new zzkq(zzqw, map).execute();
                    return;
                case 5:
                    new zzks(zzqw, map).execute();
                    return;
                case 6:
                    this.zzIp.zzr(true);
                    return;
                default:
                    zzpk.zzbg("Unknown MRAID command called.");
                    return;
            }
        } else {
            this.zzIo.zzx(null);
        }
    }
}
