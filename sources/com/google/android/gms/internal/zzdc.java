package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzw;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzme
public class zzdc {
    private final Object zzrJ = new Object();
    private int zzxE;
    private List<zzdb> zzxF = new LinkedList();

    public boolean zza(zzdb zzdb) {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzxF.contains(zzdb);
        }
        return z;
    }

    public boolean zzb(zzdb zzdb) {
        synchronized (this.zzrJ) {
            Iterator it = this.zzxF.iterator();
            while (it.hasNext()) {
                zzdb zzdb2 = (zzdb) it.next();
                if (!((Boolean) zzgd.zzCi.get()).booleanValue() || zzw.zzcQ().zzkg()) {
                    if (((Boolean) zzgd.zzCk.get()).booleanValue() && !zzw.zzcQ().zzkh() && zzdb != zzdb2 && zzdb2.zzec().equals(zzdb.zzec())) {
                        it.remove();
                        return true;
                    }
                } else if (zzdb != zzdb2 && zzdb2.zzea().equals(zzdb.zzea())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public void zzc(zzdb zzdb) {
        synchronized (this.zzrJ) {
            if (this.zzxF.size() >= 10) {
                zzpk.zzbf("Queue is full, current size = " + this.zzxF.size());
                this.zzxF.remove(0);
            }
            int i = this.zzxE;
            this.zzxE = i + 1;
            zzdb.zzn(i);
            this.zzxF.add(zzdb);
        }
    }

    public zzdb zzei() {
        int i;
        zzdb zzdb;
        int i2;
        zzdb zzdb2 = null;
        int i3 = 0;
        synchronized (this.zzrJ) {
            if (this.zzxF.size() == 0) {
                zzpk.zzbf("Queue empty");
                return null;
            } else if (this.zzxF.size() >= 2) {
                int i4 = Integer.MIN_VALUE;
                int i5 = 0;
                for (zzdb zzdb3 : this.zzxF) {
                    int score = zzdb3.getScore();
                    if (score > i4) {
                        i2 = score;
                        zzdb = zzdb3;
                        i = i5;
                    } else {
                        i = i3;
                        zzdb = zzdb2;
                        i2 = i4;
                    }
                    i5++;
                    i4 = i2;
                    zzdb2 = zzdb;
                    i3 = i;
                }
                this.zzxF.remove(i3);
                return zzdb2;
            } else {
                zzdb zzdb4 = (zzdb) this.zzxF.get(0);
                zzdb4.zzed();
                return zzdb4;
            }
        }
    }
}
