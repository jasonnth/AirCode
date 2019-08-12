package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzaa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class zztp extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Creator<zztp> CREATOR = new zztq();
    public final Account account;
    final zztt[] zzahb;
    public final String zzahc;
    public final boolean zzahd;

    public static class zza {
        private List<zztt> zzahe;
        private String zzahf;
        private boolean zzahg;
        private Account zzahh;

        public zza zzX(boolean z) {
            this.zzahg = z;
            return this;
        }

        public zza zza(zztt zztt) {
            if (this.zzahe == null && zztt != null) {
                this.zzahe = new ArrayList();
            }
            if (zztt != null) {
                this.zzahe.add(zztt);
            }
            return this;
        }

        public zza zzb(Account account) {
            this.zzahh = account;
            return this;
        }

        public zza zzcl(String str) {
            this.zzahf = str;
            return this;
        }

        public zztp zzqE() {
            return new zztp(this.zzahf, this.zzahg, this.zzahh, this.zzahe != null ? (zztt[]) this.zzahe.toArray(new zztt[this.zzahe.size()]) : null);
        }
    }

    zztp(String str, boolean z, Account account2, zztt... zzttArr) {
        this(zzttArr, str, z, account2);
        if (zzttArr != null) {
            BitSet bitSet = new BitSet(zzua.zzqG());
            for (zztt zztt : zzttArr) {
                int i = zztt.zzaho;
                if (i != -1) {
                    if (bitSet.get(i)) {
                        String str2 = "Duplicate global search section type ";
                        String valueOf = String.valueOf(zzua.zzaP(i));
                        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    }
                    bitSet.set(i);
                }
            }
        }
    }

    zztp(zztt[] zzttArr, String str, boolean z, Account account2) {
        this.zzahb = zzttArr;
        this.zzahc = str;
        this.zzahd = z;
        this.account = account2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zztp)) {
            return false;
        }
        zztp zztp = (zztp) obj;
        return zzaa.equal(this.zzahc, zztp.zzahc) && zzaa.equal(Boolean.valueOf(this.zzahd), Boolean.valueOf(zztp.zzahd)) && zzaa.equal(this.account, zztp.account) && Arrays.equals(zzqD(), zztp.zzqD());
    }

    public int hashCode() {
        return zzaa.hashCode(this.zzahc, Boolean.valueOf(this.zzahd), this.account, Integer.valueOf(Arrays.hashCode(this.zzahb)));
    }

    public void writeToParcel(Parcel parcel, int i) {
        zztq.zza(this, parcel, i);
    }

    public zztt[] zzqD() {
        return this.zzahb;
    }
}
