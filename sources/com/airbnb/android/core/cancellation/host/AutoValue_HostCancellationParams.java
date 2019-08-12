package com.airbnb.android.core.cancellation.host;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_HostCancellationParams extends C$AutoValue_HostCancellationParams {
    public static final Creator<AutoValue_HostCancellationParams> CREATOR = new Creator<AutoValue_HostCancellationParams>() {
        public AutoValue_HostCancellationParams createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3;
            String str4 = null;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() == 0) {
                str2 = in.readString();
            } else {
                str2 = null;
            }
            if (in.readInt() == 0) {
                str3 = in.readString();
            } else {
                str3 = null;
            }
            if (in.readInt() == 0) {
                str4 = in.readString();
            }
            return new AutoValue_HostCancellationParams(str, str2, str3, str4);
        }

        public AutoValue_HostCancellationParams[] newArray(int size) {
            return new AutoValue_HostCancellationParams[size];
        }
    };

    AutoValue_HostCancellationParams(String reason, String subReason, String message, String additionalInfo) {
        super(reason, subReason, message, additionalInfo);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (reason() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(reason());
        }
        if (subReason() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(subReason());
        }
        if (message() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(message());
        }
        if (additionalInfo() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(additionalInfo());
    }

    public int describeContents() {
        return 0;
    }
}
