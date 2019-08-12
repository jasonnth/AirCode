package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.List;

public class zztx {

    public static class zzb extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
        public static final Creator<zzb> CREATOR = new zztz();
        public Status zzahw;
        public List<zzud> zzahx;
        @Deprecated
        public String[] zzahy;

        public zzb() {
        }

        zzb(Status status, List<zzud> list, String[] strArr) {
            this.zzahw = status;
            this.zzahx = list;
            this.zzahy = strArr;
        }

        public Status getStatus() {
            return this.zzahw;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zztz.zza(this, parcel, i);
        }
    }

    public static class zza extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Creator<zza> CREATOR = new zzty();
        public final Account zzahr;
        public final boolean zzahs;
        public final boolean zzaht;
        public final boolean zzahu;
        public final String zzahv;

        public zza() {
            this(null, false, false, false, null);
        }

        public zza(Account account, boolean z, boolean z2, boolean z3, String str) {
            this.zzahr = account;
            this.zzahs = z;
            this.zzaht = z2;
            this.zzahu = z3;
            this.zzahv = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzty.zza(this, parcel, i);
        }
    }
}
