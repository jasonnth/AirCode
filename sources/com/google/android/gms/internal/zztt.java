package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;

public class zztt extends zza {
    public static final Creator<zztt> CREATOR = new zztu();
    public static final int zzahk = Integer.parseInt("-1");
    private static final zzub zzahl = new zzub.zza("SsbContext").zzY(true).zzcn("blob").zzqH();
    public final String zzahm;
    final zzub zzahn;
    public final int zzaho;
    public final byte[] zzahp;

    public zztt(String str, zzub zzub) {
        this(str, zzub, zzahk, null);
    }

    zztt(String str, zzub zzub, int i, byte[] bArr) {
        zzac.zzb(i == zzahk || zzua.zzaP(i) != null, (Object) "Invalid section type " + i);
        this.zzahm = str;
        this.zzahn = zzub;
        this.zzaho = i;
        this.zzahp = bArr;
        String zzqF = zzqF();
        if (zzqF != null) {
            throw new IllegalArgumentException(zzqF);
        }
    }

    public zztt(String str, zzub zzub, String str2) {
        this(str, zzub, zzua.zzcm(str2), null);
    }

    public zztt(byte[] bArr, zzub zzub) {
        this(null, zzub, zzahk, bArr);
    }

    public static zztt zzl(byte[] bArr) {
        return new zztt(bArr, zzahl);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zztu.zza(this, parcel, i);
    }

    public String zzqF() {
        if (this.zzaho != zzahk && zzua.zzaP(this.zzaho) == null) {
            return "Invalid section type " + this.zzaho;
        } else if (this.zzahm == null || this.zzahp == null) {
            return null;
        } else {
            return "Both content and blobContent set";
        }
    }
}
