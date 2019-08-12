package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class WalletFragmentOptions extends zza implements ReflectedParcelable {
    public static final Creator<WalletFragmentOptions> CREATOR = new zzb();
    /* access modifiers changed from: private */
    public int mTheme;
    /* access modifiers changed from: private */
    public int zzaKF;
    /* access modifiers changed from: private */
    public int zzbRw;
    /* access modifiers changed from: private */
    public WalletFragmentStyle zzbSm;

    public final class Builder {
        private Builder() {
        }
    }

    private WalletFragmentOptions() {
        this.zzbRw = 3;
        this.zzbSm = new WalletFragmentStyle();
    }

    WalletFragmentOptions(int i, int i2, WalletFragmentStyle walletFragmentStyle, int i3) {
        this.zzbRw = i;
        this.mTheme = i2;
        this.zzbSm = walletFragmentStyle;
        this.zzaKF = i3;
    }

    public static Builder newBuilder() {
        WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.getClass();
        return new Builder();
    }

    public static WalletFragmentOptions zzc(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WalletFragmentOptions);
        int i = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_appTheme, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_environment, 1);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.WalletFragmentOptions_fragmentStyle, 0);
        int i3 = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_fragmentMode, 1);
        obtainStyledAttributes.recycle();
        WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.mTheme = i;
        walletFragmentOptions.zzbRw = i2;
        walletFragmentOptions.zzbSm = new WalletFragmentStyle().setStyleResourceId(resourceId);
        walletFragmentOptions.zzbSm.zzci(context);
        walletFragmentOptions.zzaKF = i3;
        return walletFragmentOptions;
    }

    public int getEnvironment() {
        return this.zzbRw;
    }

    public WalletFragmentStyle getFragmentStyle() {
        return this.zzbSm;
    }

    public int getMode() {
        return this.zzaKF;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public void zzci(Context context) {
        if (this.zzbSm != null) {
            this.zzbSm.zzci(context);
        }
    }
}
