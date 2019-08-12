package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

@zzme
public class zznx implements RewardItem {
    private final zznt zzVp;

    public zznx(zznt zznt) {
        this.zzVp = zznt;
    }

    public int getAmount() {
        int i = 0;
        if (this.zzVp == null) {
            return i;
        }
        try {
            return this.zzVp.getAmount();
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward getAmount to RewardItem", e);
            return i;
        }
    }

    public String getType() {
        String str = null;
        if (this.zzVp == null) {
            return str;
        }
        try {
            return this.zzVp.getType();
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward getType to RewardItem", e);
            return str;
        }
    }
}
