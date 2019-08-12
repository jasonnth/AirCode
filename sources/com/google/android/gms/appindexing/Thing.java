package com.google.android.gms.appindexing;

import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzac;

public class Thing {
    final Bundle zzaib;

    public static class Builder {
        final Bundle zzaic = new Bundle();

        public Thing build() {
            return new Thing(this.zzaic);
        }

        public Builder put(String str, Thing thing) {
            zzac.zzw(str);
            if (thing != null) {
                this.zzaic.putParcelable(str, thing.zzaib);
            }
            return this;
        }

        public Builder put(String str, String str2) {
            zzac.zzw(str);
            if (str2 != null) {
                this.zzaic.putString(str, str2);
            }
            return this;
        }

        public Builder setId(String str) {
            if (str != null) {
                put("id", str);
            }
            return this;
        }

        public Builder setName(String str) {
            zzac.zzw(str);
            put("name", str);
            return this;
        }

        public Builder setUrl(Uri uri) {
            zzac.zzw(uri);
            put("url", uri.toString());
            return this;
        }
    }

    Thing(Bundle bundle) {
        this.zzaib = bundle;
    }

    public Bundle zzqK() {
        return this.zzaib;
    }
}
