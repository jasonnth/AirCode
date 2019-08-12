package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.zzd;
import com.google.android.gms.wallet.wobs.zzf;
import com.google.android.gms.wallet.wobs.zzj;
import com.google.android.gms.wallet.wobs.zzl;
import com.google.android.gms.wallet.wobs.zzn;
import com.google.android.gms.wallet.wobs.zzp;
import java.util.ArrayList;

public final class LoyaltyWalletObject extends zza {
    public static final Creator<LoyaltyWalletObject> CREATOR = new zzm();
    int state;
    String zzaJT;
    String zzbQA;
    String zzbQB;
    String zzbQC;
    String zzbQD;
    String zzbQE;
    String zzbQF;
    String zzbQG;
    ArrayList<zzp> zzbQH;
    zzl zzbQI;
    ArrayList<LatLng> zzbQJ;
    String zzbQK;
    String zzbQL;
    ArrayList<zzd> zzbQM;
    boolean zzbQN;
    ArrayList<zzn> zzbQO;
    ArrayList<zzj> zzbQP;
    ArrayList<zzn> zzbQQ;
    zzf zzbQR;
    String zzbQz;
    String zzkl;

    LoyaltyWalletObject() {
        this.zzbQH = zzb.zzyY();
        this.zzbQJ = zzb.zzyY();
        this.zzbQM = zzb.zzyY();
        this.zzbQO = zzb.zzyY();
        this.zzbQP = zzb.zzyY();
        this.zzbQQ = zzb.zzyY();
    }

    LoyaltyWalletObject(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, ArrayList<zzp> arrayList, zzl zzl, ArrayList<LatLng> arrayList2, String str11, String str12, ArrayList<zzd> arrayList3, boolean z, ArrayList<zzn> arrayList4, ArrayList<zzj> arrayList5, ArrayList<zzn> arrayList6, zzf zzf) {
        this.zzkl = str;
        this.zzbQz = str2;
        this.zzbQA = str3;
        this.zzbQB = str4;
        this.zzaJT = str5;
        this.zzbQC = str6;
        this.zzbQD = str7;
        this.zzbQE = str8;
        this.zzbQF = str9;
        this.zzbQG = str10;
        this.state = i;
        this.zzbQH = arrayList;
        this.zzbQI = zzl;
        this.zzbQJ = arrayList2;
        this.zzbQK = str11;
        this.zzbQL = str12;
        this.zzbQM = arrayList3;
        this.zzbQN = z;
        this.zzbQO = arrayList4;
        this.zzbQP = arrayList5;
        this.zzbQQ = arrayList6;
        this.zzbQR = zzf;
    }

    public String getAccountId() {
        return this.zzbQz;
    }

    public String getAccountName() {
        return this.zzaJT;
    }

    public String getBarcodeAlternateText() {
        return this.zzbQC;
    }

    public String getBarcodeType() {
        return this.zzbQD;
    }

    public String getBarcodeValue() {
        return this.zzbQE;
    }

    public String getId() {
        return this.zzkl;
    }

    public String getIssuerName() {
        return this.zzbQA;
    }

    public String getProgramName() {
        return this.zzbQB;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.zza(this, parcel, i);
    }
}
