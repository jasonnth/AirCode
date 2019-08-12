package com.google.android.gms.internal;

import com.airbnb.android.core.enums.HelpCenterArticle;
import java.io.IOException;

public interface zzbyn {

    public static final class zza extends zzbyd<zza> {
        public int score;
        public boolean zzcwX;
        public String zzcwY;

        public zza() {
            zzafC();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.zzcwX != zza.zzcwX || this.score != zza.score) {
                return false;
            }
            if (this.zzcwY == null) {
                if (zza.zzcwY != null) {
                    return false;
                }
            } else if (!this.zzcwY.equals(zza.zzcwY)) {
                return false;
            }
            return (this.zzcwC == null || this.zzcwC.isEmpty()) ? zza.zzcwC == null || zza.zzcwC.isEmpty() : this.zzcwC.equals(zza.zzcwC);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzcwY == null ? 0 : this.zzcwY.hashCode()) + (((((this.zzcwX ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + this.score) * 31)) * 31;
            if (this.zzcwC != null && !this.zzcwC.isEmpty()) {
                i = this.zzcwC.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbyc zzbyc) throws IOException {
            if (this.zzcwX) {
                zzbyc.zzg(1, this.zzcwX);
            }
            if (this.score != 0) {
                zzbyc.zzJ(2, this.score);
            }
            if (this.zzcwY != null && !this.zzcwY.equals("")) {
                zzbyc.zzq(3, this.zzcwY);
            }
            super.zza(zzbyc);
        }

        /* renamed from: zzaV */
        public zza zzb(zzbyb zzbyb) throws IOException {
            while (true) {
                int zzaeW = zzbyb.zzaeW();
                switch (zzaeW) {
                    case 0:
                        break;
                    case 8:
                        this.zzcwX = zzbyb.zzafc();
                        continue;
                    case 16:
                        this.score = zzbyb.zzafa();
                        continue;
                    case 26:
                        this.zzcwY = zzbyb.readString();
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

        public zza zzafC() {
            this.zzcwX = false;
            this.score = 0;
            this.zzcwY = "";
            this.zzcwC = null;
            this.zzcwL = -1;
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcwX) {
                zzu += zzbyc.zzh(1, this.zzcwX);
            }
            if (this.score != 0) {
                zzu += zzbyc.zzL(2, this.score);
            }
            return (this.zzcwY == null || this.zzcwY.equals("")) ? zzu : zzu + zzbyc.zzr(3, this.zzcwY);
        }
    }
}
