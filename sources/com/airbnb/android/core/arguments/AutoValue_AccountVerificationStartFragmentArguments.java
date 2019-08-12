package com.airbnb.android.core.arguments;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.User;
import java.util.ArrayList;
import java.util.List;

final class AutoValue_AccountVerificationStartFragmentArguments extends C$AutoValue_AccountVerificationStartFragmentArguments {
    public static final Creator<AutoValue_AccountVerificationStartFragmentArguments> CREATOR = new Creator<AutoValue_AccountVerificationStartFragmentArguments>() {
        public AutoValue_AccountVerificationStartFragmentArguments createFromParcel(Parcel in) {
            Boolean bool;
            String str;
            VerificationFlow valueOf = VerificationFlow.valueOf(in.readString());
            ArrayList readArrayList = in.readArrayList(AccountVerification.class.getClassLoader());
            User user = (User) in.readParcelable(User.class.getClassLoader());
            User user2 = (User) in.readParcelable(User.class.getClassLoader());
            long readLong = in.readLong();
            String str2 = in.readInt() == 0 ? in.readString() : null;
            String str3 = in.readInt() == 0 ? in.readString() : null;
            GovernmentIdResult governmentIdResult = (GovernmentIdResult) in.readParcelable(GovernmentIdResult.class.getClassLoader());
            boolean z = in.readInt() == 1;
            if (in.readInt() == 0) {
                bool = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool = null;
            }
            ArrayList readArrayList2 = in.readArrayList(String.class.getClassLoader());
            String str4 = in.readInt() == 0 ? in.readString() : null;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            return new AutoValue_AccountVerificationStartFragmentArguments(valueOf, readArrayList, user, user2, readLong, str2, str3, governmentIdResult, z, bool, readArrayList2, str4, str);
        }

        public AutoValue_AccountVerificationStartFragmentArguments[] newArray(int size) {
            return new AutoValue_AccountVerificationStartFragmentArguments[size];
        }
    };

    AutoValue_AccountVerificationStartFragmentArguments(VerificationFlow verificationFlow, List<AccountVerification> incompleteVerifications, User host, User verificationUser, long listingId, String firstVerificationStep, String phoneVerificationCode, GovernmentIdResult governmentIdResult, boolean isMoveToLastStep, Boolean isIdentityOnP4, ArrayList<String> selfiePhotoFilePaths, String p4Steps, String reservationFrozenReason) {
        super(verificationFlow, incompleteVerifications, host, verificationUser, listingId, firstVerificationStep, phoneVerificationCode, governmentIdResult, isMoveToLastStep, isIdentityOnP4, selfiePhotoFilePaths, p4Steps, reservationFrozenReason);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        dest.writeString(verificationFlow().name());
        dest.writeList(incompleteVerifications());
        dest.writeParcelable(host(), flags);
        dest.writeParcelable(verificationUser(), flags);
        dest.writeLong(listingId());
        if (firstVerificationStep() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(firstVerificationStep());
        }
        if (phoneVerificationCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(phoneVerificationCode());
        }
        dest.writeParcelable(governmentIdResult(), flags);
        if (isMoveToLastStep()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (isIdentityOnP4() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(isIdentityOnP4().booleanValue() ? 1 : 0);
        }
        dest.writeList(selfiePhotoFilePaths());
        if (p4Steps() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(p4Steps());
        }
        if (reservationFrozenReason() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(reservationFrozenReason());
    }

    public int describeContents() {
        return 0;
    }
}
