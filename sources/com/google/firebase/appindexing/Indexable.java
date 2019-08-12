package com.google.firebase.appindexing;

import com.google.android.gms.internal.zzbyn;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.Thing.zza;

public interface Indexable {

    public static class Builder extends IndexableBuilder<Builder> {
        public Builder() {
            this("Thing");
        }

        public Builder(String str) {
            super(str);
        }
    }

    public interface Metadata {
        public static final zza zzbXk = new Builder().zzVj();

        public static final class Builder {
            private static final zzbyn.zza zzbXl = new zzbyn.zza();
            private boolean zzbXm = zzbXl.zzcwX;
            private String zzbXn = zzbXl.zzcwY;
            private int zzxA = zzbXl.score;

            public zza zzVj() {
                return new zza(this.zzbXm, this.zzxA, this.zzbXn);
            }
        }
    }
}
