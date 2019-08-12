package com.google.android.gms.internal;

import com.airbnb.android.core.enums.HelpCenterArticle;
import java.io.IOException;

public interface zzaqn {

    public static final class zza extends zzbyd<zza> {
        public C7701zza[] zzbhg;

        /* renamed from: com.google.android.gms.internal.zzaqn$zza$zza reason: collision with other inner class name */
        public static final class C7701zza extends zzbyd<C7701zza> {
            private static volatile C7701zza[] zzbhh;
            public int viewId;
            public String zzbhi;
            public String zzbhj;

            public C7701zza() {
                zzGZ();
            }

            public static C7701zza[] zzGY() {
                if (zzbhh == null) {
                    synchronized (zzbyh.zzcwK) {
                        if (zzbhh == null) {
                            zzbhh = new C7701zza[0];
                        }
                    }
                }
                return zzbhh;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C7701zza)) {
                    return false;
                }
                C7701zza zza = (C7701zza) obj;
                if (this.zzbhi == null) {
                    if (zza.zzbhi != null) {
                        return false;
                    }
                } else if (!this.zzbhi.equals(zza.zzbhi)) {
                    return false;
                }
                if (this.zzbhj == null) {
                    if (zza.zzbhj != null) {
                        return false;
                    }
                } else if (!this.zzbhj.equals(zza.zzbhj)) {
                    return false;
                }
                if (this.viewId == zza.viewId) {
                    return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zza.zzcwC == null || zza.zzcwC.isEmpty() : this.zzcwC.equals(zza.zzcwC);
                }
                return false;
            }

            public int hashCode() {
                int i = 0;
                int hashCode = ((((this.zzbhj == null ? 0 : this.zzbhj.hashCode()) + (((this.zzbhi == null ? 0 : this.zzbhi.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31) + this.viewId) * 31;
                if (this.zzcwC != null && !this.zzcwC.isEmpty()) {
                    i = this.zzcwC.hashCode();
                }
                return hashCode + i;
            }

            /* renamed from: zzC */
            public C7701zza zzb(zzbyb zzbyb) throws IOException {
                while (true) {
                    int zzaeW = zzbyb.zzaeW();
                    switch (zzaeW) {
                        case 0:
                            break;
                        case 10:
                            this.zzbhi = zzbyb.readString();
                            continue;
                        case 18:
                            this.zzbhj = zzbyb.readString();
                            continue;
                        case 24:
                            this.viewId = zzbyb.zzafa();
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

            public C7701zza zzGZ() {
                this.zzbhi = "";
                this.zzbhj = "";
                this.viewId = 0;
                this.zzcwC = null;
                this.zzcwL = -1;
                return this;
            }

            public void zza(zzbyc zzbyc) throws IOException {
                if (this.zzbhi != null && !this.zzbhi.equals("")) {
                    zzbyc.zzq(1, this.zzbhi);
                }
                if (this.zzbhj != null && !this.zzbhj.equals("")) {
                    zzbyc.zzq(2, this.zzbhj);
                }
                if (this.viewId != 0) {
                    zzbyc.zzJ(3, this.viewId);
                }
                super.zza(zzbyc);
            }

            /* access modifiers changed from: protected */
            public int zzu() {
                int zzu = super.zzu();
                if (this.zzbhi != null && !this.zzbhi.equals("")) {
                    zzu += zzbyc.zzr(1, this.zzbhi);
                }
                if (this.zzbhj != null && !this.zzbhj.equals("")) {
                    zzu += zzbyc.zzr(2, this.zzbhj);
                }
                return this.viewId != 0 ? zzu + zzbyc.zzL(3, this.viewId) : zzu;
            }
        }

        public zza() {
            zzGX();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (zzbyh.equals((Object[]) this.zzbhg, (Object[]) zza.zzbhg)) {
                return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zza.zzcwC == null || zza.zzcwC.isEmpty() : this.zzcwC.equals(zza.zzcwC);
            }
            return false;
        }

        public int hashCode() {
            return ((this.zzcwC == null || this.zzcwC.isEmpty()) ? 0 : this.zzcwC.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzbyh.hashCode((Object[]) this.zzbhg)) * 31);
        }

        /* renamed from: zzB */
        public zza zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzbym.zzb(zzbyb, 10);
                        int length = this.zzbhg == null ? 0 : this.zzbhg.length;
                        C7701zza[] zzaArr = new C7701zza[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbhg, 0, zzaArr, 0, length);
                        }
                        while (length < zzaArr.length - 1) {
                            zzaArr[length] = new C7701zza();
                            zzbyb.zza(zzaArr[length]);
                            zzbyb.zzaeW();
                            length++;
                        }
                        zzaArr[length] = new C7701zza();
                        zzbyb.zza(zzaArr[length]);
                        this.zzbhg = zzaArr;
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

        public zza zzGX() {
            this.zzbhg = C7701zza.zzGY();
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzbhg != null && this.zzbhg.length > 0) {
                for (C7701zza zza : this.zzbhg) {
                    if (zza != null) {
                        zzbyc.zza(1, (zzbyj) zza);
                    }
                }
            }
            super.zza(zzbyc);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbhg != null && this.zzbhg.length > 0) {
                for (C7701zza zza : this.zzbhg) {
                    if (zza != null) {
                        zzu += zzbyc.zzc(1, (zzbyj) zza);
                    }
                }
            }
            return zzu;
        }
    }

    public static final class zzb extends zzbyd<zzb> {
        private static volatile zzb[] zzbhk;
        public String name;
        public zzd zzbhl;

        public zzb() {
            zzHb();
        }

        public static zzb[] zzHa() {
            if (zzbhk == null) {
                synchronized (zzbyh.zzcwK) {
                    if (zzbhk == null) {
                        zzbhk = new zzb[0];
                    }
                }
            }
            return zzbhk;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.name == null) {
                if (zzb.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzb.name)) {
                return false;
            }
            if (this.zzbhl == null) {
                if (zzb.zzbhl != null) {
                    return false;
                }
            } else if (!this.zzbhl.equals(zzb.zzbhl)) {
                return false;
            }
            return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zzb.zzcwC == null || zzb.zzcwC.isEmpty() : this.zzcwC.equals(zzb.zzcwC);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbhl == null ? 0 : this.zzbhl.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzcwC != null && !this.zzcwC.isEmpty()) {
                i = this.zzcwC.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzD */
        public zzb zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzbyb.readString();
                        continue;
                    case 18:
                        if (this.zzbhl == null) {
                            this.zzbhl = new zzd();
                        }
                        zzbyb.zza(this.zzbhl);
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

        public zzb zzHb() {
            this.name = "";
            this.zzbhl = null;
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.name != null && !this.name.equals("")) {
                zzbyc.zzq(1, this.name);
            }
            if (this.zzbhl != null) {
                zzbyc.zza(2, (zzbyj) this.zzbhl);
            }
            super.zza(zzbyc);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.name != null && !this.name.equals("")) {
                zzu += zzbyc.zzr(1, this.name);
            }
            return this.zzbhl != null ? zzu + zzbyc.zzc(2, (zzbyj) this.zzbhl) : zzu;
        }
    }

    public static final class zzc extends zzbyd<zzc> {
        public String type;
        public zzb[] zzbhm;

        public zzc() {
            zzHc();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.type == null) {
                if (zzc.type != null) {
                    return false;
                }
            } else if (!this.type.equals(zzc.type)) {
                return false;
            }
            if (zzbyh.equals((Object[]) this.zzbhm, (Object[]) zzc.zzbhm)) {
                return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zzc.zzcwC == null || zzc.zzcwC.isEmpty() : this.zzcwC.equals(zzc.zzcwC);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.type == null ? 0 : this.type.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzbyh.hashCode((Object[]) this.zzbhm)) * 31;
            if (this.zzcwC != null && !this.zzcwC.isEmpty()) {
                i = this.zzcwC.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzE */
        public zzc zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 10:
                        this.type = zzbyb.readString();
                        continue;
                    case 18:
                        int zzb = zzbym.zzb(zzbyb, 18);
                        int length = this.zzbhm == null ? 0 : this.zzbhm.length;
                        zzb[] zzbArr = new zzb[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbhm, 0, zzbArr, 0, length);
                        }
                        while (length < zzbArr.length - 1) {
                            zzbArr[length] = new zzb();
                            zzbyb.zza(zzbArr[length]);
                            zzbyb.zzaeW();
                            length++;
                        }
                        zzbArr[length] = new zzb();
                        zzbyb.zza(zzbArr[length]);
                        this.zzbhm = zzbArr;
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

        public zzc zzHc() {
            this.type = "";
            this.zzbhm = zzb.zzHa();
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.type != null && !this.type.equals("")) {
                zzbyc.zzq(1, this.type);
            }
            if (this.zzbhm != null && this.zzbhm.length > 0) {
                for (zzb zzb : this.zzbhm) {
                    if (zzb != null) {
                        zzbyc.zza(2, (zzbyj) zzb);
                    }
                }
            }
            super.zza(zzbyc);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.type != null && !this.type.equals("")) {
                zzu += zzbyc.zzr(1, this.type);
            }
            if (this.zzbhm == null || this.zzbhm.length <= 0) {
                return zzu;
            }
            int i = zzu;
            for (zzb zzb : this.zzbhm) {
                if (zzb != null) {
                    i += zzbyc.zzc(2, (zzbyj) zzb);
                }
            }
            return i;
        }
    }

    public static final class zzd extends zzbyd<zzd> {
        public String zzaGV;
        public boolean zzbhn;
        public long zzbho;
        public double zzbhp;
        public zzc zzbhq;

        public zzd() {
            zzHd();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (this.zzbhn != zzd.zzbhn) {
                return false;
            }
            if (this.zzaGV == null) {
                if (zzd.zzaGV != null) {
                    return false;
                }
            } else if (!this.zzaGV.equals(zzd.zzaGV)) {
                return false;
            }
            if (this.zzbho != zzd.zzbho || Double.doubleToLongBits(this.zzbhp) != Double.doubleToLongBits(zzd.zzbhp)) {
                return false;
            }
            if (this.zzbhq == null) {
                if (zzd.zzbhq != null) {
                    return false;
                }
            } else if (!this.zzbhq.equals(zzd.zzbhq)) {
                return false;
            }
            return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zzd.zzcwC == null || zzd.zzcwC.isEmpty() : this.zzcwC.equals(zzd.zzcwC);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (((this.zzaGV == null ? 0 : this.zzaGV.hashCode()) + (((this.zzbhn ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31) + ((int) (this.zzbho ^ (this.zzbho >>> 32)));
            long doubleToLongBits = Double.doubleToLongBits(this.zzbhp);
            int hashCode2 = ((this.zzbhq == null ? 0 : this.zzbhq.hashCode()) + (((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31)) * 31;
            if (this.zzcwC != null && !this.zzcwC.isEmpty()) {
                i = this.zzcwC.hashCode();
            }
            return hashCode2 + i;
        }

        /* renamed from: zzF */
        public zzd zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 8:
                        this.zzbhn = zzbyb.zzafc();
                        continue;
                    case 18:
                        this.zzaGV = zzbyb.readString();
                        continue;
                    case 24:
                        this.zzbho = zzbyb.zzaeZ();
                        continue;
                    case 33:
                        this.zzbhp = zzbyb.readDouble();
                        continue;
                    case 42:
                        if (this.zzbhq == null) {
                            this.zzbhq = new zzc();
                        }
                        zzbyb.zza(this.zzbhq);
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

        public zzd zzHd() {
            this.zzbhn = false;
            this.zzaGV = "";
            this.zzbho = 0;
            this.zzbhp = 0.0d;
            this.zzbhq = null;
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzbhn) {
                zzbyc.zzg(1, this.zzbhn);
            }
            if (this.zzaGV != null && !this.zzaGV.equals("")) {
                zzbyc.zzq(2, this.zzaGV);
            }
            if (this.zzbho != 0) {
                zzbyc.zzb(3, this.zzbho);
            }
            if (Double.doubleToLongBits(this.zzbhp) != Double.doubleToLongBits(0.0d)) {
                zzbyc.zza(4, this.zzbhp);
            }
            if (this.zzbhq != null) {
                zzbyc.zza(5, (zzbyj) this.zzbhq);
            }
            super.zza(zzbyc);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbhn) {
                zzu += zzbyc.zzh(1, this.zzbhn);
            }
            if (this.zzaGV != null && !this.zzaGV.equals("")) {
                zzu += zzbyc.zzr(2, this.zzaGV);
            }
            if (this.zzbho != 0) {
                zzu += zzbyc.zzf(3, this.zzbho);
            }
            if (Double.doubleToLongBits(this.zzbhp) != Double.doubleToLongBits(0.0d)) {
                zzu += zzbyc.zzb(4, this.zzbhp);
            }
            return this.zzbhq != null ? zzu + zzbyc.zzc(5, (zzbyj) this.zzbhq) : zzu;
        }
    }
}
