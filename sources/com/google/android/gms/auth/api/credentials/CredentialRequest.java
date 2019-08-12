package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class CredentialRequest extends zza {
    public static final Creator<CredentialRequest> CREATOR = new zzc();
    final int zzaiI;
    private final boolean zzajr;
    private final String[] zzajs;
    private final CredentialPickerConfig zzajt;
    private final CredentialPickerConfig zzaju;
    private final boolean zzajv;
    private final String zzajw;
    private final String zzajx;

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzajr;
        /* access modifiers changed from: private */
        public String[] zzajs;
        /* access modifiers changed from: private */
        public CredentialPickerConfig zzajt;
        /* access modifiers changed from: private */
        public CredentialPickerConfig zzaju;
        /* access modifiers changed from: private */
        public boolean zzajv = false;
        /* access modifiers changed from: private */
        public String zzajw = null;
        /* access modifiers changed from: private */
        public String zzajx;

        public CredentialRequest build() {
            if (this.zzajs == null) {
                this.zzajs = new String[0];
            }
            if (this.zzajr || this.zzajs.length != 0) {
                return new CredentialRequest(this);
            }
            throw new IllegalStateException("At least one authentication method must be specified");
        }

        public Builder setAccountTypes(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this.zzajs = strArr;
            return this;
        }

        public Builder setPasswordLoginSupported(boolean z) {
            this.zzajr = z;
            return this;
        }
    }

    CredentialRequest(int i, boolean z, String[] strArr, CredentialPickerConfig credentialPickerConfig, CredentialPickerConfig credentialPickerConfig2, boolean z2, String str, String str2) {
        this.zzaiI = i;
        this.zzajr = z;
        this.zzajs = (String[]) zzac.zzw(strArr);
        if (credentialPickerConfig == null) {
            credentialPickerConfig = new com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Builder().build();
        }
        this.zzajt = credentialPickerConfig;
        if (credentialPickerConfig2 == null) {
            credentialPickerConfig2 = new com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Builder().build();
        }
        this.zzaju = credentialPickerConfig2;
        if (i < 3) {
            this.zzajv = true;
            this.zzajw = null;
            this.zzajx = null;
            return;
        }
        this.zzajv = z2;
        this.zzajw = str;
        this.zzajx = str2;
    }

    private CredentialRequest(Builder builder) {
        this(3, builder.zzajr, builder.zzajs, builder.zzajt, builder.zzaju, builder.zzajv, builder.zzajw, builder.zzajx);
    }

    public String[] getAccountTypes() {
        return this.zzajs;
    }

    public Set<String> getAccountTypesSet() {
        return new HashSet(Arrays.asList(this.zzajs));
    }

    public CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzaju;
    }

    public CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzajt;
    }

    public String getIdTokenNonce() {
        return this.zzajx;
    }

    public String getServerClientId() {
        return this.zzajw;
    }

    @Deprecated
    public boolean getSupportsPasswordLogin() {
        return isPasswordLoginSupported();
    }

    public boolean isIdTokenRequested() {
        return this.zzajv;
    }

    public boolean isPasswordLoginSupported() {
        return this.zzajr;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }
}
