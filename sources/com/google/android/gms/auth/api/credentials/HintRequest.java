package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;

public final class HintRequest extends zza implements ReflectedParcelable {
    public static final Creator<HintRequest> CREATOR = new zzd();
    final int zzaiI;
    private final boolean zzajA;
    private final String[] zzajs;
    private final boolean zzajv;
    private final String zzajw;
    private final String zzajx;
    private final CredentialPickerConfig zzajy;
    private final boolean zzajz;

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzajA;
        /* access modifiers changed from: private */
        public String[] zzajs;
        /* access modifiers changed from: private */
        public boolean zzajv;
        /* access modifiers changed from: private */
        public String zzajw;
        /* access modifiers changed from: private */
        public String zzajx;
        /* access modifiers changed from: private */
        public CredentialPickerConfig zzajy;
        /* access modifiers changed from: private */
        public boolean zzajz;
    }

    HintRequest(int i, CredentialPickerConfig credentialPickerConfig, boolean z, boolean z2, String[] strArr, boolean z3, String str, String str2) {
        this.zzaiI = i;
        this.zzajy = (CredentialPickerConfig) zzac.zzw(credentialPickerConfig);
        this.zzajz = z;
        this.zzajA = z2;
        this.zzajs = (String[]) zzac.zzw(strArr);
        if (this.zzaiI < 2) {
            this.zzajv = true;
            this.zzajw = null;
            this.zzajx = null;
            return;
        }
        this.zzajv = z3;
        this.zzajw = str;
        this.zzajx = str2;
    }

    private HintRequest(Builder builder) {
        this(2, builder.zzajy, builder.zzajz, builder.zzajA, builder.zzajs, builder.zzajv, builder.zzajw, builder.zzajx);
    }

    public String[] getAccountTypes() {
        return this.zzajs;
    }

    public CredentialPickerConfig getHintPickerConfig() {
        return this.zzajy;
    }

    public String getIdTokenNonce() {
        return this.zzajx;
    }

    public String getServerClientId() {
        return this.zzajw;
    }

    public boolean isEmailAddressIdentifierSupported() {
        return this.zzajz;
    }

    public boolean isIdTokenRequested() {
        return this.zzajv;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }

    public boolean zzqX() {
        return this.zzajA;
    }
}
