package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class CredentialPickerConfig extends zza implements ReflectedParcelable {
    public static final Creator<CredentialPickerConfig> CREATOR = new zzb();
    private final boolean mShowCancelButton;
    final int zzaiI;
    private final boolean zzajn;
    @Deprecated
    private final boolean zzajo;
    private final int zzajp;

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean mShowCancelButton = true;
        /* access modifiers changed from: private */
        public boolean zzajn = false;
        /* access modifiers changed from: private */
        public int zzajq = 1;

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig(this);
        }
    }

    CredentialPickerConfig(int i, boolean z, boolean z2, boolean z3, int i2) {
        int i3 = 3;
        boolean z4 = true;
        this.zzaiI = i;
        this.zzajn = z;
        this.mShowCancelButton = z2;
        if (i < 2) {
            this.zzajo = z3;
            if (!z3) {
                i3 = 1;
            }
            this.zzajp = i3;
            return;
        }
        if (i2 != 3) {
            z4 = false;
        }
        this.zzajo = z4;
        this.zzajp = i2;
    }

    private CredentialPickerConfig(Builder builder) {
        this(2, builder.zzajn, builder.mShowCancelButton, false, builder.zzajq);
    }

    @Deprecated
    public boolean isForNewAccount() {
        return this.zzajp == 3;
    }

    public boolean shouldShowAddAccountButton() {
        return this.zzajn;
    }

    public boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    /* access modifiers changed from: 0000 */
    public int zzqW() {
        return this.zzajp;
    }
}
