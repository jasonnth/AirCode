package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class zzub extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Creator<zzub> CREATOR = new zzuc();
    public final String name;
    public final int weight;
    public final String zzahB;
    public final boolean zzahC;
    public final boolean zzahD;
    public final String zzahE;
    public final zztv[] zzahF;
    final int[] zzahG;
    public final String zzahH;

    public static final class zza {
        private final String mName;
        private String zzahI;
        private boolean zzahJ;
        private int zzahK = 1;
        private boolean zzahL;
        private final List<zztv> zzahM = new ArrayList();
        private BitSet zzahN;
        private String zzahO;

        public zza(String str) {
            this.mName = str;
        }

        public zza zzY(boolean z) {
            this.zzahJ = z;
            return this;
        }

        public zza zzZ(boolean z) {
            this.zzahL = z;
            return this;
        }

        public zza zzaQ(int i) {
            if (this.zzahN == null) {
                this.zzahN = new BitSet();
            }
            this.zzahN.set(i);
            return this;
        }

        public zza zzcn(String str) {
            this.zzahI = str;
            return this;
        }

        public zza zzco(String str) {
            this.zzahO = str;
            return this;
        }

        public zzub zzqH() {
            int[] iArr;
            int i = 0;
            if (this.zzahN != null) {
                iArr = new int[this.zzahN.cardinality()];
                int nextSetBit = this.zzahN.nextSetBit(0);
                while (nextSetBit >= 0) {
                    int i2 = i + 1;
                    iArr[i] = nextSetBit;
                    nextSetBit = this.zzahN.nextSetBit(nextSetBit + 1);
                    i = i2;
                }
            } else {
                iArr = null;
            }
            return new zzub(this.mName, this.zzahI, this.zzahJ, this.zzahK, this.zzahL, null, (zztv[]) this.zzahM.toArray(new zztv[this.zzahM.size()]), iArr, this.zzahO);
        }
    }

    zzub(String str, String str2, boolean z, int i, boolean z2, String str3, zztv[] zztvArr, int[] iArr, String str4) {
        this.name = str;
        this.zzahB = str2;
        this.zzahC = z;
        this.weight = i;
        this.zzahD = z2;
        this.zzahE = str3;
        this.zzahF = zztvArr;
        this.zzahG = iArr;
        this.zzahH = str4;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzuc.zza(this, parcel, i);
    }
}
