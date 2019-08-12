package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

@KeepName
public class CommonWalletObject extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Creator<CommonWalletObject> CREATOR = new zza();
    String name;
    int state;
    String zzbQA;
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
    String zzkl;

    public final class zza {
        private zza() {
        }

        public CommonWalletObject zzUf() {
            return CommonWalletObject.this;
        }

        public zza zzim(String str) {
            CommonWalletObject.this.zzkl = str;
            return this;
        }
    }

    CommonWalletObject() {
        this.zzbQH = zzb.zzyY();
        this.zzbQJ = zzb.zzyY();
        this.zzbQM = zzb.zzyY();
        this.zzbQO = zzb.zzyY();
        this.zzbQP = zzb.zzyY();
        this.zzbQQ = zzb.zzyY();
    }

    CommonWalletObject(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, ArrayList<zzp> arrayList, zzl zzl, ArrayList<LatLng> arrayList2, String str9, String str10, ArrayList<zzd> arrayList3, boolean z, ArrayList<zzn> arrayList4, ArrayList<zzj> arrayList5, ArrayList<zzn> arrayList6) {
        this.zzkl = str;
        this.zzbQG = str2;
        this.name = str3;
        this.zzbQA = str4;
        this.zzbQC = str5;
        this.zzbQD = str6;
        this.zzbQE = str7;
        this.zzbQF = str8;
        this.state = i;
        this.zzbQH = arrayList;
        this.zzbQI = zzl;
        this.zzbQJ = arrayList2;
        this.zzbQK = str9;
        this.zzbQL = str10;
        this.zzbQM = arrayList3;
        this.zzbQN = z;
        this.zzbQO = arrayList4;
        this.zzbQP = arrayList5;
        this.zzbQQ = arrayList6;
    }

    public static zza zzUe() {
        CommonWalletObject commonWalletObject = new CommonWalletObject();
        commonWalletObject.getClass();
        return new zza();
    }

    public String getId() {
        return this.zzkl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }
}
