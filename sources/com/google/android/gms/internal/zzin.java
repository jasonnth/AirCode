package com.google.android.gms.internal;

import android.text.TextUtils;
import com.airbnb.android.lib.activities.GiftCardsActivity;
import java.util.Map;

@zzme
public class zzin implements zzid {
    private final zza zzIu;

    public interface zza {
        void zzb(zzoo zzoo);

        void zzcn();
    }

    public zzin(zza zza2) {
        this.zzIu = zza2;
    }

    public static void zza(zzqw zzqw, zza zza2) {
        zzqw.zzlv().zza("/reward", (zzid) new zzin(zza2));
    }

    private void zzf(Map<String, String> map) {
        zzoo zzoo;
        try {
            int parseInt = Integer.parseInt((String) map.get(GiftCardsActivity.EVENT_DATA_PARAM_GIFT_AMOUNT));
            String str = (String) map.get("type");
            if (!TextUtils.isEmpty(str)) {
                zzoo = new zzoo(str, parseInt);
                this.zzIu.zzb(zzoo);
            }
        } catch (NumberFormatException e) {
            zzpk.zzc("Unable to parse reward amount.", e);
        }
        zzoo = null;
        this.zzIu.zzb(zzoo);
    }

    private void zzg(Map<String, String> map) {
        this.zzIu.zzcn();
    }

    public void zza(zzqw zzqw, Map<String, String> map) {
        String str = (String) map.get("action");
        if ("grant".equals(str)) {
            zzf(map);
        } else if ("video_start".equals(str)) {
            zzg(map);
        }
    }
}
