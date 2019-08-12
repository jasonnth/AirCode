package com.facebook.accountkit.p029ui;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.accountkit.Tracker;

/* renamed from: com.facebook.accountkit.ui.ActivityHandler */
public abstract class ActivityHandler implements Parcelable {
    protected static final long COMPLETION_UI_DURATION_MS = 2000;
    protected final AccountKitConfiguration configuration;
    protected Tracker tracker;

    public abstract Tracker getLoginTracker(AccountKitActivity accountKitActivity);

    public abstract void onAccountVerifiedComplete(AccountKitActivity accountKitActivity);

    public abstract void onSentCodeComplete(AccountKitActivity accountKitActivity);

    ActivityHandler(AccountKitConfiguration configuration2) {
        this.configuration = configuration2;
    }

    protected ActivityHandler(Parcel parcel) {
        this.configuration = (AccountKitConfiguration) parcel.readParcelable(AccountKitConfiguration.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.configuration, flags);
    }
}
