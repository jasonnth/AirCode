package com.google.firebase.appindexing.builders;

import com.google.android.gms.common.internal.zzac;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.Indexable.Builder;

public final class Indexables {
    public static Indexable newSimple(String str, String str2) {
        zzac.zzw(str);
        zzac.zzw(str2);
        return ((Builder) ((Builder) new Builder().setUrl(str2)).setName(str)).build();
    }
}
