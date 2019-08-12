package com.google.android.gms.internal;

import com.facebook.imageutils.JfifUtil;
import java.io.IOException;

public interface zzae {

    public static final class zza extends zzbyd<zza> {
        public zzb zzaK;
        public zzc zzaL;

        public zza() {
            this.zzcwL = -1;
        }

        public static zza zzc(byte[] bArr) throws zzbyi {
            return (zza) zzbyj.zza(new zza(), bArr);
        }

        /* renamed from: zza */
        public zza zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 10:
                        if (this.zzaK == null) {
                            this.zzaK = new zzb();
                        }
                        zzbyb.zza(this.zzaK);
                        continue;
                    case 18:
                        if (this.zzaL == null) {
                            this.zzaL = new zzc();
                        }
                        zzbyb.zza(this.zzaL);
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

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzaK != null) {
                zzbyc.zza(1, (zzbyj) this.zzaK);
            }
            if (this.zzaL != null) {
                zzbyc.zza(2, (zzbyj) this.zzaL);
            }
            super.zza(zzbyc);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaK != null) {
                zzu += zzbyc.zzc(1, (zzbyj) this.zzaK);
            }
            return this.zzaL != null ? zzu + zzbyc.zzc(2, (zzbyj) this.zzaL) : zzu;
        }
    }

    public static final class zzb extends zzbyd<zzb> {
        public Integer zzaM;

        public zzb() {
            this.zzaM = null;
            this.zzcwL = -1;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzaM != null) {
                zzbyc.zzJ(27, this.zzaM.intValue());
            }
            super.zza(zzbyc);
        }

        /* renamed from: zzc */
        public zzb zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case JfifUtil.MARKER_SOI /*216*/:
                        int zzafa = zzbyb.zzafa();
                        switch (zzafa) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                this.zzaM = Integer.valueOf(zzafa);
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
            return this.zzaM != null ? zzu + zzbyc.zzL(27, this.zzaM.intValue()) : zzu;
        }
    }

    public static final class zzc extends zzbyd<zzc> {
        public String zzaN;
        public String zzaO;
        public String zzaP;
        public String zzaQ;
        public String zzaR;

        public zzc() {
            this.zzaN = null;
            this.zzaO = null;
            this.zzaP = null;
            this.zzaQ = null;
            this.zzaR = null;
            this.zzcwL = -1;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzaN != null) {
                zzbyc.zzq(1, this.zzaN);
            }
            if (this.zzaO != null) {
                zzbyc.zzq(2, this.zzaO);
            }
            if (this.zzaP != null) {
                zzbyc.zzq(3, this.zzaP);
            }
            if (this.zzaQ != null) {
                zzbyc.zzq(4, this.zzaQ);
            }
            if (this.zzaR != null) {
                zzbyc.zzq(5, this.zzaR);
            }
            super.zza(zzbyc);
        }

        /* renamed from: zzd */
        public zzc zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 10:
                        this.zzaN = zzbyb.readString();
                        continue;
                    case 18:
                        this.zzaO = zzbyb.readString();
                        continue;
                    case 26:
                        this.zzaP = zzbyb.readString();
                        continue;
                    case 34:
                        this.zzaQ = zzbyb.readString();
                        continue;
                    case 42:
                        this.zzaR = zzbyb.readString();
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
            if (this.zzaN != null) {
                zzu += zzbyc.zzr(1, this.zzaN);
            }
            if (this.zzaO != null) {
                zzu += zzbyc.zzr(2, this.zzaO);
            }
            if (this.zzaP != null) {
                zzu += zzbyc.zzr(3, this.zzaP);
            }
            if (this.zzaQ != null) {
                zzu += zzbyc.zzr(4, this.zzaQ);
            }
            return this.zzaR != null ? zzu + zzbyc.zzr(5, this.zzaR) : zzu;
        }
    }
}
