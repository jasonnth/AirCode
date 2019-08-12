package com.google.firebase.appindexing;

import com.google.android.gms.common.internal.zzac;
import com.google.firebase.appindexing.internal.zza;
import com.google.firebase.appindexing.internal.zza.C7828zza;

public interface Action {

    public static class Builder {
        private final String zzbXa;
        private String zzbXb;
        private String zzbXc;
        private String zzbXd;
        private C7828zza zzbXe = Metadata.zzbXg;
        private String zzbXf;

        public Builder(String str) {
            this.zzbXa = str;
        }

        public Action build() {
            zzac.zzb(this.zzbXb, (Object) "setObject is required before calling build().");
            zzac.zzb(this.zzbXc, (Object) "setObject is required before calling build().");
            return new zza(this.zzbXa, this.zzbXb, this.zzbXc, this.zzbXd, this.zzbXe, this.zzbXf);
        }

        public Builder setMetadata(Builder builder) {
            zzac.zzw(builder);
            this.zzbXe = builder.zzVg();
            return this;
        }

        public Builder setObject(String str, String str2) {
            zzac.zzw(str);
            zzac.zzw(str2);
            this.zzbXb = str;
            this.zzbXc = str2;
            return this;
        }
    }

    public interface Metadata {
        public static final C7828zza zzbXg = new Builder().zzVg();

        public static class Builder {
            private boolean zzbXh = true;
            private boolean zzbXi = false;

            public Builder setUpload(boolean z) {
                this.zzbXh = z;
                return this;
            }

            public C7828zza zzVg() {
                return new C7828zza(this.zzbXh, null, null, null, false);
            }
        }
    }
}
