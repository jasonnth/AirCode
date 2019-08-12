package com.airbnb.android.core.cancellation;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.CancellationReason;

final class AutoValue_CancellationData extends C$AutoValue_CancellationData {
    public static final Creator<AutoValue_CancellationData> CREATOR = new Creator<AutoValue_CancellationData>() {
        public AutoValue_CancellationData createFromParcel(Parcel in) {
            String str;
            String str2;
            boolean z;
            boolean z2;
            CancellationReason cancellationReason;
            String str3;
            String str4;
            String str5;
            String str6;
            boolean z3 = true;
            String str7 = null;
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
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            if (in.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (in.readInt() == 0) {
                cancellationReason = CancellationReason.valueOf(in.readString());
            } else {
                cancellationReason = null;
            }
            if (in.readInt() == 0) {
                str3 = in.readString();
            } else {
                str3 = null;
            }
            if (in.readInt() == 0) {
                str4 = in.readString();
            } else {
                str4 = null;
            }
            if (in.readInt() == 0) {
                str5 = in.readString();
            } else {
                str5 = null;
            }
            if (in.readInt() != 1) {
                z3 = false;
            }
            if (in.readInt() == 0) {
                str6 = in.readString();
            } else {
                str6 = null;
            }
            if (in.readInt() == 0) {
                str7 = in.readString();
            }
            return new AutoValue_CancellationData(str, str2, z, z2, cancellationReason, str3, str4, str5, z3, str6, str7);
        }

        public AutoValue_CancellationData[] newArray(int size) {
            return new AutoValue_CancellationData[size];
        }
    };

    AutoValue_CancellationData(String confirmationCode, String policyKey, boolean isHost, boolean isRetracting, CancellationReason cancellationReason, String otherReason, String message, String refundAmount, boolean isPositiveRefund, String paymentProvider, String paymentAccountPostfix) {
        super(confirmationCode, policyKey, isHost, isRetracting, cancellationReason, otherReason, message, refundAmount, isPositiveRefund, paymentProvider, paymentAccountPostfix);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2;
        int i3;
        if (confirmationCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(confirmationCode());
        }
        if (policyKey() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(policyKey());
        }
        if (isHost()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (isRetracting()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        dest.writeInt(i2);
        if (cancellationReason() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(cancellationReason().name());
        }
        if (otherReason() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(otherReason());
        }
        if (message() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(message());
        }
        if (refundAmount() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(refundAmount());
        }
        if (isPositiveRefund()) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        dest.writeInt(i3);
        if (paymentProvider() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(paymentProvider());
        }
        if (paymentAccountPostfix() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(paymentAccountPostfix());
    }

    public int describeContents() {
        return 0;
    }
}
