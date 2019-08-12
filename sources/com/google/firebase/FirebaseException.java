package com.google.firebase;

import com.google.android.gms.common.internal.zzac;

public class FirebaseException extends Exception {
    @Deprecated
    protected FirebaseException() {
    }

    public FirebaseException(String str) {
        super(zzac.zzh(str, "Detail message must not be empty"));
    }
}
