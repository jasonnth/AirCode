package com.google.firebase.appindexing.builders;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.Indexable.Metadata;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.Thing;
import com.google.firebase.appindexing.internal.Thing.zza;
import com.google.firebase.appindexing.internal.zzm;
import com.google.firebase.appindexing.internal.zzs;
import com.miteksystems.misnap.params.SDKConstants;
import java.util.Arrays;

public abstract class IndexableBuilder<T extends IndexableBuilder<?>> {
    private String zzE;
    private final String zzUA;
    private final Bundle zzaib = new Bundle();
    private zza zzbXo;

    protected IndexableBuilder(String str) {
        zzac.zzw(str);
        zzac.zzdr(str);
        this.zzUA = str;
    }

    private T zzVk() {
        return this;
    }

    private static <S> S[] zzd(S[] sArr) {
        if (sArr.length < 100) {
            return sArr;
        }
        zzm.zzit("Input Array of elements is too big, cutting off.");
        return Arrays.copyOf(sArr, 100);
    }

    public final Indexable build() {
        return new Thing(new Bundle(this.zzaib), this.zzbXo == null ? Metadata.zzbXk : this.zzbXo, this.zzE, this.zzUA);
    }

    public T put(String str, String... strArr) {
        zzac.zzw(str);
        zzac.zzw(strArr);
        if (strArr.length > 0) {
            int i = 0;
            for (int i2 = 0; i2 < Math.min(strArr.length, 100); i2++) {
                strArr[i] = strArr[i2];
                if (strArr[i2] == null) {
                    zzm.zzit("String at " + i2 + " is null and is ignored by put method.");
                } else {
                    if (strArr[i].length() > 20000) {
                        zzm.zzit("String at " + i2 + " is too long, truncating string.");
                        strArr[i] = zzs.zzE(strArr[i], SDKConstants.CAM_INIT_CAMERA);
                    }
                    i++;
                }
            }
            if (i > 0) {
                this.zzaib.putStringArray(str, (String[]) zzd((String[]) Arrays.copyOfRange(strArr, 0, i)));
            }
        } else {
            zzm.zzit("String array is empty and is ignored by put method.");
        }
        return zzVk();
    }

    public final T setName(String str) {
        zzac.zzw(str);
        return put("name", str);
    }

    public final T setUrl(String str) {
        zzac.zzw(str);
        this.zzE = str;
        return zzVk();
    }
}
