package com.google.android.gms.internal;

import com.airbnb.android.core.enums.HelpCenterArticle;
import java.io.IOException;
import java.util.Arrays;
import org.jmrtd.cbeff.ISO781611;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.crypto.tls.CipherSuite;

public interface zzbyo {

    public static final class zza extends zzbyd<zza> implements Cloneable {
        public String[] zzcwZ;
        public String[] zzcxa;
        public int[] zzcxb;
        public long[] zzcxc;
        public long[] zzcxd;

        public zza() {
            zzafD();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (!zzbyh.equals((Object[]) this.zzcwZ, (Object[]) zza.zzcwZ) || !zzbyh.equals((Object[]) this.zzcxa, (Object[]) zza.zzcxa) || !zzbyh.equals(this.zzcxb, zza.zzcxb) || !zzbyh.equals(this.zzcxc, zza.zzcxc) || !zzbyh.equals(this.zzcxd, zza.zzcxd)) {
                return false;
            }
            return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zza.zzcwC == null || zza.zzcwC.isEmpty() : this.zzcwC.equals(zza.zzcwC);
        }

        public int hashCode() {
            return ((this.zzcwC == null || this.zzcwC.isEmpty()) ? 0 : this.zzcwC.hashCode()) + ((((((((((((getClass().getName().hashCode() + 527) * 31) + zzbyh.hashCode((Object[]) this.zzcwZ)) * 31) + zzbyh.hashCode((Object[]) this.zzcxa)) * 31) + zzbyh.hashCode(this.zzcxb)) * 31) + zzbyh.hashCode(this.zzcxc)) * 31) + zzbyh.hashCode(this.zzcxd)) * 31);
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzcwZ != null && this.zzcwZ.length > 0) {
                for (String str : this.zzcwZ) {
                    if (str != null) {
                        zzbyc.zzq(1, str);
                    }
                }
            }
            if (this.zzcxa != null && this.zzcxa.length > 0) {
                for (String str2 : this.zzcxa) {
                    if (str2 != null) {
                        zzbyc.zzq(2, str2);
                    }
                }
            }
            if (this.zzcxb != null && this.zzcxb.length > 0) {
                for (int zzJ : this.zzcxb) {
                    zzbyc.zzJ(3, zzJ);
                }
            }
            if (this.zzcxc != null && this.zzcxc.length > 0) {
                for (long zzb : this.zzcxc) {
                    zzbyc.zzb(4, zzb);
                }
            }
            if (this.zzcxd != null && this.zzcxd.length > 0) {
                for (long zzb2 : this.zzcxd) {
                    zzbyc.zzb(5, zzb2);
                }
            }
            super.zza(zzbyc);
        }

        /* renamed from: zzaW */
        public zza zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzbym.zzb(zzbyb, 10);
                        int length = this.zzcwZ == null ? 0 : this.zzcwZ.length;
                        String[] strArr = new String[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzcwZ, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = zzbyb.readString();
                            zzbyb.zzaeW();
                            length++;
                        }
                        strArr[length] = zzbyb.readString();
                        this.zzcwZ = strArr;
                        continue;
                    case 18:
                        int zzb2 = zzbym.zzb(zzbyb, 18);
                        int length2 = this.zzcxa == null ? 0 : this.zzcxa.length;
                        String[] strArr2 = new String[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzcxa, 0, strArr2, 0, length2);
                        }
                        while (length2 < strArr2.length - 1) {
                            strArr2[length2] = zzbyb.readString();
                            zzbyb.zzaeW();
                            length2++;
                        }
                        strArr2[length2] = zzbyb.readString();
                        this.zzcxa = strArr2;
                        continue;
                    case 24:
                        int zzb3 = zzbym.zzb(zzbyb, 24);
                        int length3 = this.zzcxb == null ? 0 : this.zzcxb.length;
                        int[] iArr = new int[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzcxb, 0, iArr, 0, length3);
                        }
                        while (length3 < iArr.length - 1) {
                            iArr[length3] = zzbyb.zzafa();
                            zzbyb.zzaeW();
                            length3++;
                        }
                        iArr[length3] = zzbyb.zzafa();
                        this.zzcxb = iArr;
                        continue;
                    case 26:
                        int zzrf = zzbyb.zzrf(zzbyb.zzaff());
                        int position = zzbyb.getPosition();
                        int i = 0;
                        while (zzbyb.zzafk() > 0) {
                            zzbyb.zzafa();
                            i++;
                        }
                        zzbyb.zzrh(position);
                        int length4 = this.zzcxb == null ? 0 : this.zzcxb.length;
                        int[] iArr2 = new int[(i + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzcxb, 0, iArr2, 0, length4);
                        }
                        while (length4 < iArr2.length) {
                            iArr2[length4] = zzbyb.zzafa();
                            length4++;
                        }
                        this.zzcxb = iArr2;
                        zzbyb.zzrg(zzrf);
                        continue;
                    case 32:
                        int zzb4 = zzbym.zzb(zzbyb, 32);
                        int length5 = this.zzcxc == null ? 0 : this.zzcxc.length;
                        long[] jArr = new long[(zzb4 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzcxc, 0, jArr, 0, length5);
                        }
                        while (length5 < jArr.length - 1) {
                            jArr[length5] = zzbyb.zzaeZ();
                            zzbyb.zzaeW();
                            length5++;
                        }
                        jArr[length5] = zzbyb.zzaeZ();
                        this.zzcxc = jArr;
                        continue;
                    case 34:
                        int zzrf2 = zzbyb.zzrf(zzbyb.zzaff());
                        int position2 = zzbyb.getPosition();
                        int i2 = 0;
                        while (zzbyb.zzafk() > 0) {
                            zzbyb.zzaeZ();
                            i2++;
                        }
                        zzbyb.zzrh(position2);
                        int length6 = this.zzcxc == null ? 0 : this.zzcxc.length;
                        long[] jArr2 = new long[(i2 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzcxc, 0, jArr2, 0, length6);
                        }
                        while (length6 < jArr2.length) {
                            jArr2[length6] = zzbyb.zzaeZ();
                            length6++;
                        }
                        this.zzcxc = jArr2;
                        zzbyb.zzrg(zzrf2);
                        continue;
                    case 40:
                        int zzb5 = zzbym.zzb(zzbyb, 40);
                        int length7 = this.zzcxd == null ? 0 : this.zzcxd.length;
                        long[] jArr3 = new long[(zzb5 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzcxd, 0, jArr3, 0, length7);
                        }
                        while (length7 < jArr3.length - 1) {
                            jArr3[length7] = zzbyb.zzaeZ();
                            zzbyb.zzaeW();
                            length7++;
                        }
                        jArr3[length7] = zzbyb.zzaeZ();
                        this.zzcxd = jArr3;
                        continue;
                    case 42:
                        int zzrf3 = zzbyb.zzrf(zzbyb.zzaff());
                        int position3 = zzbyb.getPosition();
                        int i3 = 0;
                        while (zzbyb.zzafk() > 0) {
                            zzbyb.zzaeZ();
                            i3++;
                        }
                        zzbyb.zzrh(position3);
                        int length8 = this.zzcxd == null ? 0 : this.zzcxd.length;
                        long[] jArr4 = new long[(i3 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzcxd, 0, jArr4, 0, length8);
                        }
                        while (length8 < jArr4.length) {
                            jArr4[length8] = zzbyb.zzaeZ();
                            length8++;
                        }
                        this.zzcxd = jArr4;
                        zzbyb.zzrg(zzrf3);
                        continue;
                    default:
                        if (!super.zza(zzbyb, zzaeW)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zza zzafD() {
            this.zzcwZ = zzbym.EMPTY_STRING_ARRAY;
            this.zzcxa = zzbym.EMPTY_STRING_ARRAY;
            this.zzcxb = zzbym.zzcwQ;
            this.zzcxc = zzbym.zzcwR;
            this.zzcxd = zzbym.zzcwR;
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        /* renamed from: zzafE */
        public zza clone() {
            try {
                zza zza = (zza) super.clone();
                if (this.zzcwZ != null && this.zzcwZ.length > 0) {
                    zza.zzcwZ = (String[]) this.zzcwZ.clone();
                }
                if (this.zzcxa != null && this.zzcxa.length > 0) {
                    zza.zzcxa = (String[]) this.zzcxa.clone();
                }
                if (this.zzcxb != null && this.zzcxb.length > 0) {
                    zza.zzcxb = (int[]) this.zzcxb.clone();
                }
                if (this.zzcxc != null && this.zzcxc.length > 0) {
                    zza.zzcxc = (long[]) this.zzcxc.clone();
                }
                if (this.zzcxd != null && this.zzcxd.length > 0) {
                    zza.zzcxd = (long[]) this.zzcxd.clone();
                }
                return zza;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ zzbyd zzafp() throws CloneNotSupportedException {
            return (zza) clone();
        }

        public /* synthetic */ zzbyj zzafq() throws CloneNotSupportedException {
            return (zza) clone();
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int i;
            int zzu = super.zzu();
            if (this.zzcwZ == null || this.zzcwZ.length <= 0) {
                i = zzu;
            } else {
                int i2 = 0;
                int i3 = 0;
                for (String str : this.zzcwZ) {
                    if (str != null) {
                        i3++;
                        i2 += zzbyc.zzku(str);
                    }
                }
                i = zzu + i2 + (i3 * 1);
            }
            if (this.zzcxa != null && this.zzcxa.length > 0) {
                int i4 = 0;
                int i5 = 0;
                for (String str2 : this.zzcxa) {
                    if (str2 != null) {
                        i5++;
                        i4 += zzbyc.zzku(str2);
                    }
                }
                i = i + i4 + (i5 * 1);
            }
            if (this.zzcxb != null && this.zzcxb.length > 0) {
                int i6 = 0;
                for (int zzrl : this.zzcxb) {
                    i6 += zzbyc.zzrl(zzrl);
                }
                i = i + i6 + (this.zzcxb.length * 1);
            }
            if (this.zzcxc != null && this.zzcxc.length > 0) {
                int i7 = 0;
                for (long zzbq : this.zzcxc) {
                    i7 += zzbyc.zzbq(zzbq);
                }
                i = i + i7 + (this.zzcxc.length * 1);
            }
            if (this.zzcxd == null || this.zzcxd.length <= 0) {
                return i;
            }
            int i8 = 0;
            for (long zzbq2 : this.zzcxd) {
                i8 += zzbyc.zzbq(zzbq2);
            }
            return i + i8 + (this.zzcxd.length * 1);
        }
    }

    public static final class zzb extends zzbyd<zzb> implements Cloneable {
        public String version;
        public int zzbqV;
        public String zzcxe;

        public zzb() {
            zzafF();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzbqV != zzb.zzbqV) {
                return false;
            }
            if (this.zzcxe == null) {
                if (zzb.zzcxe != null) {
                    return false;
                }
            } else if (!this.zzcxe.equals(zzb.zzcxe)) {
                return false;
            }
            if (this.version == null) {
                if (zzb.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzb.version)) {
                return false;
            }
            return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zzb.zzcwC == null || zzb.zzcwC.isEmpty() : this.zzcwC.equals(zzb.zzcwC);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((this.zzcxe == null ? 0 : this.zzcxe.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.zzbqV) * 31)) * 31)) * 31;
            if (this.zzcwC != null && !this.zzcwC.isEmpty()) {
                i = this.zzcwC.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzbqV != 0) {
                zzbyc.zzJ(1, this.zzbqV);
            }
            if (this.zzcxe != null && !this.zzcxe.equals("")) {
                zzbyc.zzq(2, this.zzcxe);
            }
            if (this.version != null && !this.version.equals("")) {
                zzbyc.zzq(3, this.version);
            }
            super.zza(zzbyc);
        }

        /* renamed from: zzaX */
        public zzb zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 8:
                        this.zzbqV = zzbyb.zzafa();
                        continue;
                    case 18:
                        this.zzcxe = zzbyb.readString();
                        continue;
                    case 26:
                        this.version = zzbyb.readString();
                        continue;
                    default:
                        if (!super.zza(zzbyb, zzaeW)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzb zzafF() {
            this.zzbqV = 0;
            this.zzcxe = "";
            this.version = "";
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        /* renamed from: zzafG */
        public zzb clone() {
            try {
                return (zzb) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ zzbyd zzafp() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        public /* synthetic */ zzbyj zzafq() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbqV != 0) {
                zzu += zzbyc.zzL(1, this.zzbqV);
            }
            if (this.zzcxe != null && !this.zzcxe.equals("")) {
                zzu += zzbyc.zzr(2, this.zzcxe);
            }
            return (this.version == null || this.version.equals("")) ? zzu : zzu + zzbyc.zzr(3, this.version);
        }
    }

    public static final class zzc extends zzbyd<zzc> implements Cloneable {
        public byte[] zzcxf;
        public String zzcxg;
        public byte[][] zzcxh;
        public boolean zzcxi;

        public zzc() {
            zzafH();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (!Arrays.equals(this.zzcxf, zzc.zzcxf)) {
                return false;
            }
            if (this.zzcxg == null) {
                if (zzc.zzcxg != null) {
                    return false;
                }
            } else if (!this.zzcxg.equals(zzc.zzcxg)) {
                return false;
            }
            if (!zzbyh.zza(this.zzcxh, zzc.zzcxh) || this.zzcxi != zzc.zzcxi) {
                return false;
            }
            return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zzc.zzcwC == null || zzc.zzcwC.isEmpty() : this.zzcwC.equals(zzc.zzcwC);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzcxi ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE) + (((((this.zzcxg == null ? 0 : this.zzcxg.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzcxf)) * 31)) * 31) + zzbyh.zzb(this.zzcxh)) * 31)) * 31;
            if (this.zzcwC != null && !this.zzcwC.isEmpty()) {
                i = this.zzcwC.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (!Arrays.equals(this.zzcxf, zzbym.zzcwW)) {
                zzbyc.zzb(1, this.zzcxf);
            }
            if (this.zzcxh != null && this.zzcxh.length > 0) {
                for (byte[] bArr : this.zzcxh) {
                    if (bArr != null) {
                        zzbyc.zzb(2, bArr);
                    }
                }
            }
            if (this.zzcxi) {
                zzbyc.zzg(3, this.zzcxi);
            }
            if (this.zzcxg != null && !this.zzcxg.equals("")) {
                zzbyc.zzq(4, this.zzcxg);
            }
            super.zza(zzbyc);
        }

        /* renamed from: zzaY */
        public zzc zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 10:
                        this.zzcxf = zzbyb.readBytes();
                        continue;
                    case 18:
                        int zzb = zzbym.zzb(zzbyb, 18);
                        int length = this.zzcxh == null ? 0 : this.zzcxh.length;
                        byte[][] bArr = new byte[(zzb + length)][];
                        if (length != 0) {
                            System.arraycopy(this.zzcxh, 0, bArr, 0, length);
                        }
                        while (length < bArr.length - 1) {
                            bArr[length] = zzbyb.readBytes();
                            zzbyb.zzaeW();
                            length++;
                        }
                        bArr[length] = zzbyb.readBytes();
                        this.zzcxh = bArr;
                        continue;
                    case 24:
                        this.zzcxi = zzbyb.zzafc();
                        continue;
                    case 34:
                        this.zzcxg = zzbyb.readString();
                        continue;
                    default:
                        if (!super.zza(zzbyb, zzaeW)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzc zzafH() {
            this.zzcxf = zzbym.zzcwW;
            this.zzcxg = "";
            this.zzcxh = zzbym.zzcwV;
            this.zzcxi = false;
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        /* renamed from: zzafI */
        public zzc clone() {
            try {
                zzc zzc = (zzc) super.clone();
                if (this.zzcxh != null && this.zzcxh.length > 0) {
                    zzc.zzcxh = (byte[][]) this.zzcxh.clone();
                }
                return zzc;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ zzbyd zzafp() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        public /* synthetic */ zzbyj zzafq() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (!Arrays.equals(this.zzcxf, zzbym.zzcwW)) {
                zzu += zzbyc.zzc(1, this.zzcxf);
            }
            if (this.zzcxh != null && this.zzcxh.length > 0) {
                int i = 0;
                int i2 = 0;
                for (byte[] bArr : this.zzcxh) {
                    if (bArr != null) {
                        i2++;
                        i += zzbyc.zzaj(bArr);
                    }
                }
                zzu = zzu + i + (i2 * 1);
            }
            if (this.zzcxi) {
                zzu += zzbyc.zzh(3, this.zzcxi);
            }
            return (this.zzcxg == null || this.zzcxg.equals("")) ? zzu : zzu + zzbyc.zzr(4, this.zzcxg);
        }
    }

    public static final class zzd extends zzbyd<zzd> implements Cloneable {
        public String tag;
        public boolean zzced;
        public zzf zzcnt;
        public int[] zzcxA;
        public long zzcxB;
        public long zzcxj;
        public long zzcxk;
        public long zzcxl;
        public int zzcxm;
        public zze[] zzcxn;
        public byte[] zzcxo;
        public zzb zzcxp;
        public byte[] zzcxq;
        public String zzcxr;
        public String zzcxs;
        public zza zzcxt;
        public String zzcxu;
        public long zzcxv;
        public zzc zzcxw;
        public byte[] zzcxx;
        public String zzcxy;
        public int zzcxz;
        public int zzri;

        public zzd() {
            zzafJ();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (this.zzcxj != zzd.zzcxj || this.zzcxk != zzd.zzcxk || this.zzcxl != zzd.zzcxl) {
                return false;
            }
            if (this.tag == null) {
                if (zzd.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(zzd.tag)) {
                return false;
            }
            if (this.zzcxm != zzd.zzcxm || this.zzri != zzd.zzri || this.zzced != zzd.zzced || !zzbyh.equals((Object[]) this.zzcxn, (Object[]) zzd.zzcxn) || !Arrays.equals(this.zzcxo, zzd.zzcxo)) {
                return false;
            }
            if (this.zzcxp == null) {
                if (zzd.zzcxp != null) {
                    return false;
                }
            } else if (!this.zzcxp.equals(zzd.zzcxp)) {
                return false;
            }
            if (!Arrays.equals(this.zzcxq, zzd.zzcxq)) {
                return false;
            }
            if (this.zzcxr == null) {
                if (zzd.zzcxr != null) {
                    return false;
                }
            } else if (!this.zzcxr.equals(zzd.zzcxr)) {
                return false;
            }
            if (this.zzcxs == null) {
                if (zzd.zzcxs != null) {
                    return false;
                }
            } else if (!this.zzcxs.equals(zzd.zzcxs)) {
                return false;
            }
            if (this.zzcxt == null) {
                if (zzd.zzcxt != null) {
                    return false;
                }
            } else if (!this.zzcxt.equals(zzd.zzcxt)) {
                return false;
            }
            if (this.zzcxu == null) {
                if (zzd.zzcxu != null) {
                    return false;
                }
            } else if (!this.zzcxu.equals(zzd.zzcxu)) {
                return false;
            }
            if (this.zzcxv != zzd.zzcxv) {
                return false;
            }
            if (this.zzcxw == null) {
                if (zzd.zzcxw != null) {
                    return false;
                }
            } else if (!this.zzcxw.equals(zzd.zzcxw)) {
                return false;
            }
            if (!Arrays.equals(this.zzcxx, zzd.zzcxx)) {
                return false;
            }
            if (this.zzcxy == null) {
                if (zzd.zzcxy != null) {
                    return false;
                }
            } else if (!this.zzcxy.equals(zzd.zzcxy)) {
                return false;
            }
            if (this.zzcxz != zzd.zzcxz || !zzbyh.equals(this.zzcxA, zzd.zzcxA) || this.zzcxB != zzd.zzcxB) {
                return false;
            }
            if (this.zzcnt == null) {
                if (zzd.zzcnt != null) {
                    return false;
                }
            } else if (!this.zzcnt.equals(zzd.zzcnt)) {
                return false;
            }
            return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zzd.zzcwC == null || zzd.zzcwC.isEmpty() : this.zzcwC.equals(zzd.zzcwC);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzcnt == null ? 0 : this.zzcnt.hashCode()) + (((((((((this.zzcxy == null ? 0 : this.zzcxy.hashCode()) + (((((this.zzcxw == null ? 0 : this.zzcxw.hashCode()) + (((((this.zzcxu == null ? 0 : this.zzcxu.hashCode()) + (((this.zzcxt == null ? 0 : this.zzcxt.hashCode()) + (((this.zzcxs == null ? 0 : this.zzcxs.hashCode()) + (((this.zzcxr == null ? 0 : this.zzcxr.hashCode()) + (((((this.zzcxp == null ? 0 : this.zzcxp.hashCode()) + (((((((this.zzced ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzcxj ^ (this.zzcxj >>> 32)))) * 31) + ((int) (this.zzcxk ^ (this.zzcxk >>> 32)))) * 31) + ((int) (this.zzcxl ^ (this.zzcxl >>> 32)))) * 31)) * 31) + this.zzcxm) * 31) + this.zzri) * 31)) * 31) + zzbyh.hashCode((Object[]) this.zzcxn)) * 31) + Arrays.hashCode(this.zzcxo)) * 31)) * 31) + Arrays.hashCode(this.zzcxq)) * 31)) * 31)) * 31)) * 31)) * 31) + ((int) (this.zzcxv ^ (this.zzcxv >>> 32)))) * 31)) * 31) + Arrays.hashCode(this.zzcxx)) * 31)) * 31) + this.zzcxz) * 31) + zzbyh.hashCode(this.zzcxA)) * 31) + ((int) (this.zzcxB ^ (this.zzcxB >>> 32)))) * 31)) * 31;
            if (this.zzcwC != null && !this.zzcwC.isEmpty()) {
                i = this.zzcwC.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzcxj != 0) {
                zzbyc.zzb(1, this.zzcxj);
            }
            if (this.tag != null && !this.tag.equals("")) {
                zzbyc.zzq(2, this.tag);
            }
            if (this.zzcxn != null && this.zzcxn.length > 0) {
                for (zze zze : this.zzcxn) {
                    if (zze != null) {
                        zzbyc.zza(3, (zzbyj) zze);
                    }
                }
            }
            if (!Arrays.equals(this.zzcxo, zzbym.zzcwW)) {
                zzbyc.zzb(4, this.zzcxo);
            }
            if (!Arrays.equals(this.zzcxq, zzbym.zzcwW)) {
                zzbyc.zzb(6, this.zzcxq);
            }
            if (this.zzcxt != null) {
                zzbyc.zza(7, (zzbyj) this.zzcxt);
            }
            if (this.zzcxr != null && !this.zzcxr.equals("")) {
                zzbyc.zzq(8, this.zzcxr);
            }
            if (this.zzcxp != null) {
                zzbyc.zza(9, (zzbyj) this.zzcxp);
            }
            if (this.zzced) {
                zzbyc.zzg(10, this.zzced);
            }
            if (this.zzcxm != 0) {
                zzbyc.zzJ(11, this.zzcxm);
            }
            if (this.zzri != 0) {
                zzbyc.zzJ(12, this.zzri);
            }
            if (this.zzcxs != null && !this.zzcxs.equals("")) {
                zzbyc.zzq(13, this.zzcxs);
            }
            if (this.zzcxu != null && !this.zzcxu.equals("")) {
                zzbyc.zzq(14, this.zzcxu);
            }
            if (this.zzcxv != 180000) {
                zzbyc.zzd(15, this.zzcxv);
            }
            if (this.zzcxw != null) {
                zzbyc.zza(16, (zzbyj) this.zzcxw);
            }
            if (this.zzcxk != 0) {
                zzbyc.zzb(17, this.zzcxk);
            }
            if (!Arrays.equals(this.zzcxx, zzbym.zzcwW)) {
                zzbyc.zzb(18, this.zzcxx);
            }
            if (this.zzcxz != 0) {
                zzbyc.zzJ(19, this.zzcxz);
            }
            if (this.zzcxA != null && this.zzcxA.length > 0) {
                for (int zzJ : this.zzcxA) {
                    zzbyc.zzJ(20, zzJ);
                }
            }
            if (this.zzcxl != 0) {
                zzbyc.zzb(21, this.zzcxl);
            }
            if (this.zzcxB != 0) {
                zzbyc.zzb(22, this.zzcxB);
            }
            if (this.zzcnt != null) {
                zzbyc.zza(23, (zzbyj) this.zzcnt);
            }
            if (this.zzcxy != null && !this.zzcxy.equals("")) {
                zzbyc.zzq(24, this.zzcxy);
            }
            super.zza(zzbyc);
        }

        /* renamed from: zzaZ */
        public zzd zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 8:
                        this.zzcxj = zzbyb.zzaeZ();
                        continue;
                    case 18:
                        this.tag = zzbyb.readString();
                        continue;
                    case 26:
                        int zzb = zzbym.zzb(zzbyb, 26);
                        int length = this.zzcxn == null ? 0 : this.zzcxn.length;
                        zze[] zzeArr = new zze[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzcxn, 0, zzeArr, 0, length);
                        }
                        while (length < zzeArr.length - 1) {
                            zzeArr[length] = new zze();
                            zzbyb.zza(zzeArr[length]);
                            zzbyb.zzaeW();
                            length++;
                        }
                        zzeArr[length] = new zze();
                        zzbyb.zza(zzeArr[length]);
                        this.zzcxn = zzeArr;
                        continue;
                    case 34:
                        this.zzcxo = zzbyb.readBytes();
                        continue;
                    case 50:
                        this.zzcxq = zzbyb.readBytes();
                        continue;
                    case 58:
                        if (this.zzcxt == null) {
                            this.zzcxt = new zza();
                        }
                        zzbyb.zza(this.zzcxt);
                        continue;
                    case 66:
                        this.zzcxr = zzbyb.readString();
                        continue;
                    case 74:
                        if (this.zzcxp == null) {
                            this.zzcxp = new zzb();
                        }
                        zzbyb.zza(this.zzcxp);
                        continue;
                    case 80:
                        this.zzced = zzbyb.zzafc();
                        continue;
                    case 88:
                        this.zzcxm = zzbyb.zzafa();
                        continue;
                    case 96:
                        this.zzri = zzbyb.zzafa();
                        continue;
                    case 106:
                        this.zzcxs = zzbyb.readString();
                        continue;
                    case 114:
                        this.zzcxu = zzbyb.readString();
                        continue;
                    case EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY /*120*/:
                        this.zzcxv = zzbyb.zzafe();
                        continue;
                    case ISO781611.BIOMETRIC_SUBTYPE_TAG /*130*/:
                        if (this.zzcxw == null) {
                            this.zzcxw = new zzc();
                        }
                        zzbyb.zza(this.zzcxw);
                        continue;
                    case 136:
                        this.zzcxk = zzbyb.zzaeZ();
                        continue;
                    case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA /*146*/:
                        this.zzcxx = zzbyb.readBytes();
                        continue;
                    case CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA /*152*/:
                        int zzafa = zzbyb.zzafa();
                        switch (zzafa) {
                            case 0:
                            case 1:
                            case 2:
                                this.zzcxz = zzafa;
                                break;
                            default:
                                continue;
                        }
                    case 160:
                        int zzb2 = zzbym.zzb(zzbyb, 160);
                        int length2 = this.zzcxA == null ? 0 : this.zzcxA.length;
                        int[] iArr = new int[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzcxA, 0, iArr, 0, length2);
                        }
                        while (length2 < iArr.length - 1) {
                            iArr[length2] = zzbyb.zzafa();
                            zzbyb.zzaeW();
                            length2++;
                        }
                        iArr[length2] = zzbyb.zzafa();
                        this.zzcxA = iArr;
                        continue;
                    case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /*162*/:
                        int zzrf = zzbyb.zzrf(zzbyb.zzaff());
                        int position = zzbyb.getPosition();
                        int i = 0;
                        while (zzbyb.zzafk() > 0) {
                            zzbyb.zzafa();
                            i++;
                        }
                        zzbyb.zzrh(position);
                        int length3 = this.zzcxA == null ? 0 : this.zzcxA.length;
                        int[] iArr2 = new int[(i + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzcxA, 0, iArr2, 0, length3);
                        }
                        while (length3 < iArr2.length) {
                            iArr2[length3] = zzbyb.zzafa();
                            length3++;
                        }
                        this.zzcxA = iArr2;
                        zzbyb.zzrg(zzrf);
                        continue;
                    case 168:
                        this.zzcxl = zzbyb.zzaeZ();
                        continue;
                    case CipherSuite.TLS_PSK_WITH_NULL_SHA256 /*176*/:
                        this.zzcxB = zzbyb.zzaeZ();
                        continue;
                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*186*/:
                        if (this.zzcnt == null) {
                            this.zzcnt = new zzf();
                        }
                        zzbyb.zza(this.zzcnt);
                        continue;
                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /*194*/:
                        this.zzcxy = zzbyb.readString();
                        continue;
                    default:
                        if (!super.zza(zzbyb, zzaeW)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzd zzafJ() {
            this.zzcxj = 0;
            this.zzcxk = 0;
            this.zzcxl = 0;
            this.tag = "";
            this.zzcxm = 0;
            this.zzri = 0;
            this.zzced = false;
            this.zzcxn = zze.zzafL();
            this.zzcxo = zzbym.zzcwW;
            this.zzcxp = null;
            this.zzcxq = zzbym.zzcwW;
            this.zzcxr = "";
            this.zzcxs = "";
            this.zzcxt = null;
            this.zzcxu = "";
            this.zzcxv = 180000;
            this.zzcxw = null;
            this.zzcxx = zzbym.zzcwW;
            this.zzcxy = "";
            this.zzcxz = 0;
            this.zzcxA = zzbym.zzcwQ;
            this.zzcxB = 0;
            this.zzcnt = null;
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        /* renamed from: zzafK */
        public zzd clone() {
            try {
                zzd zzd = (zzd) super.clone();
                if (this.zzcxn != null && this.zzcxn.length > 0) {
                    zzd.zzcxn = new zze[this.zzcxn.length];
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= this.zzcxn.length) {
                            break;
                        }
                        if (this.zzcxn[i2] != null) {
                            zzd.zzcxn[i2] = (zze) this.zzcxn[i2].clone();
                        }
                        i = i2 + 1;
                    }
                }
                if (this.zzcxp != null) {
                    zzd.zzcxp = (zzb) this.zzcxp.clone();
                }
                if (this.zzcxt != null) {
                    zzd.zzcxt = (zza) this.zzcxt.clone();
                }
                if (this.zzcxw != null) {
                    zzd.zzcxw = (zzc) this.zzcxw.clone();
                }
                if (this.zzcxA != null && this.zzcxA.length > 0) {
                    zzd.zzcxA = (int[]) this.zzcxA.clone();
                }
                if (this.zzcnt != null) {
                    zzd.zzcnt = (zzf) this.zzcnt.clone();
                }
                return zzd;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ zzbyd zzafp() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        public /* synthetic */ zzbyj zzafq() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcxj != 0) {
                zzu += zzbyc.zzf(1, this.zzcxj);
            }
            if (this.tag != null && !this.tag.equals("")) {
                zzu += zzbyc.zzr(2, this.tag);
            }
            if (this.zzcxn != null && this.zzcxn.length > 0) {
                int i = zzu;
                for (zze zze : this.zzcxn) {
                    if (zze != null) {
                        i += zzbyc.zzc(3, (zzbyj) zze);
                    }
                }
                zzu = i;
            }
            if (!Arrays.equals(this.zzcxo, zzbym.zzcwW)) {
                zzu += zzbyc.zzc(4, this.zzcxo);
            }
            if (!Arrays.equals(this.zzcxq, zzbym.zzcwW)) {
                zzu += zzbyc.zzc(6, this.zzcxq);
            }
            if (this.zzcxt != null) {
                zzu += zzbyc.zzc(7, (zzbyj) this.zzcxt);
            }
            if (this.zzcxr != null && !this.zzcxr.equals("")) {
                zzu += zzbyc.zzr(8, this.zzcxr);
            }
            if (this.zzcxp != null) {
                zzu += zzbyc.zzc(9, (zzbyj) this.zzcxp);
            }
            if (this.zzced) {
                zzu += zzbyc.zzh(10, this.zzced);
            }
            if (this.zzcxm != 0) {
                zzu += zzbyc.zzL(11, this.zzcxm);
            }
            if (this.zzri != 0) {
                zzu += zzbyc.zzL(12, this.zzri);
            }
            if (this.zzcxs != null && !this.zzcxs.equals("")) {
                zzu += zzbyc.zzr(13, this.zzcxs);
            }
            if (this.zzcxu != null && !this.zzcxu.equals("")) {
                zzu += zzbyc.zzr(14, this.zzcxu);
            }
            if (this.zzcxv != 180000) {
                zzu += zzbyc.zzh(15, this.zzcxv);
            }
            if (this.zzcxw != null) {
                zzu += zzbyc.zzc(16, (zzbyj) this.zzcxw);
            }
            if (this.zzcxk != 0) {
                zzu += zzbyc.zzf(17, this.zzcxk);
            }
            if (!Arrays.equals(this.zzcxx, zzbym.zzcwW)) {
                zzu += zzbyc.zzc(18, this.zzcxx);
            }
            if (this.zzcxz != 0) {
                zzu += zzbyc.zzL(19, this.zzcxz);
            }
            if (this.zzcxA != null && this.zzcxA.length > 0) {
                int i2 = 0;
                for (int zzrl : this.zzcxA) {
                    i2 += zzbyc.zzrl(zzrl);
                }
                zzu = zzu + i2 + (this.zzcxA.length * 2);
            }
            if (this.zzcxl != 0) {
                zzu += zzbyc.zzf(21, this.zzcxl);
            }
            if (this.zzcxB != 0) {
                zzu += zzbyc.zzf(22, this.zzcxB);
            }
            if (this.zzcnt != null) {
                zzu += zzbyc.zzc(23, (zzbyj) this.zzcnt);
            }
            return (this.zzcxy == null || this.zzcxy.equals("")) ? zzu : zzu + zzbyc.zzr(24, this.zzcxy);
        }
    }

    public static final class zze extends zzbyd<zze> implements Cloneable {
        private static volatile zze[] zzcxC;
        public String value;
        public String zzaB;

        public zze() {
            zzafM();
        }

        public static zze[] zzafL() {
            if (zzcxC == null) {
                synchronized (zzbyh.zzcwK) {
                    if (zzcxC == null) {
                        zzcxC = new zze[0];
                    }
                }
            }
            return zzcxC;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.zzaB == null) {
                if (zze.zzaB != null) {
                    return false;
                }
            } else if (!this.zzaB.equals(zze.zzaB)) {
                return false;
            }
            if (this.value == null) {
                if (zze.value != null) {
                    return false;
                }
            } else if (!this.value.equals(zze.value)) {
                return false;
            }
            return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zze.zzcwC == null || zze.zzcwC.isEmpty() : this.zzcwC.equals(zze.zzcwC);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.zzaB == null ? 0 : this.zzaB.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzcwC != null && !this.zzcwC.isEmpty()) {
                i = this.zzcwC.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzaB != null && !this.zzaB.equals("")) {
                zzbyc.zzq(1, this.zzaB);
            }
            if (this.value != null && !this.value.equals("")) {
                zzbyc.zzq(2, this.value);
            }
            super.zza(zzbyc);
        }

        public zze zzafM() {
            this.zzaB = "";
            this.value = "";
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        /* renamed from: zzafN */
        public zze clone() {
            try {
                return (zze) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ zzbyd zzafp() throws CloneNotSupportedException {
            return (zze) clone();
        }

        public /* synthetic */ zzbyj zzafq() throws CloneNotSupportedException {
            return (zze) clone();
        }

        /* renamed from: zzba */
        public zze zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 10:
                        this.zzaB = zzbyb.readString();
                        continue;
                    case 18:
                        this.value = zzbyb.readString();
                        continue;
                    default:
                        if (!super.zza(zzbyb, zzaeW)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaB != null && !this.zzaB.equals("")) {
                zzu += zzbyc.zzr(1, this.zzaB);
            }
            return (this.value == null || this.value.equals("")) ? zzu : zzu + zzbyc.zzr(2, this.value);
        }
    }

    public static final class zzf extends zzbyd<zzf> implements Cloneable {
        public int zzcxD;
        public int zzcxE;

        public zzf() {
            zzafO();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (this.zzcxD == zzf.zzcxD && this.zzcxE == zzf.zzcxE) {
                return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zzf.zzcwC == null || zzf.zzcwC.isEmpty() : this.zzcwC.equals(zzf.zzcwC);
            }
            return false;
        }

        public int hashCode() {
            return ((this.zzcwC == null || this.zzcwC.isEmpty()) ? 0 : this.zzcwC.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + this.zzcxD) * 31) + this.zzcxE) * 31);
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzcxD != -1) {
                zzbyc.zzJ(1, this.zzcxD);
            }
            if (this.zzcxE != 0) {
                zzbyc.zzJ(2, this.zzcxE);
            }
            super.zza(zzbyc);
        }

        public zzf zzafO() {
            this.zzcxD = -1;
            this.zzcxE = 0;
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        /* renamed from: zzafP */
        public zzf clone() {
            try {
                return (zzf) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ zzbyd zzafp() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        public /* synthetic */ zzbyj zzafq() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        /* renamed from: zzbb */
        public zzf zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 8:
                        int zzafa = zzbyb.zzafa();
                        switch (zzafa) {
                            case -1:
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                                this.zzcxD = zzafa;
                                break;
                            default:
                                continue;
                        }
                    case 16:
                        int zzafa2 = zzbyb.zzafa();
                        switch (zzafa2) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 100:
                                this.zzcxE = zzafa2;
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!super.zza(zzbyb, zzaeW)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcxD != -1) {
                zzu += zzbyc.zzL(1, this.zzcxD);
            }
            return this.zzcxE != 0 ? zzu + zzbyc.zzL(2, this.zzcxE) : zzu;
        }
    }
}
