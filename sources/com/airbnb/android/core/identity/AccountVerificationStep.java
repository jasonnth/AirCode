package com.airbnb.android.core.identity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;

public enum AccountVerificationStep implements Parcelable {
    ProfilePhoto,
    Phone,
    Email,
    OfflineId,
    Selfie,
    SelfieCapture,
    PhotoReview,
    DeviceNotSupported;
    
    public static final Creator<AccountVerificationStep> CREATOR = null;

    static {
        CREATOR = new Creator<AccountVerificationStep>() {
            public AccountVerificationStep createFromParcel(Parcel source) {
                return AccountVerificationStep.values()[source.readInt()];
            }

            public AccountVerificationStep[] newArray(int size) {
                return new AccountVerificationStep[size];
            }
        };
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    public String toAccountVerificationString() {
        switch (this) {
            case ProfilePhoto:
                return AccountVerification.PHOTO;
            case Phone:
                return "phone";
            case Email:
                return "email";
            case OfflineId:
                return AccountVerification.SCANID;
            case Selfie:
                return AccountVerification.SELFIE;
            default:
                throw new IllegalStateException("Can not handle this step: " + name());
        }
    }

    public Page toPage() {
        switch (this) {
            case ProfilePhoto:
                return Page.provide_photo_upload;
            case Phone:
                return Page.phone_entry;
            case Email:
                return Page.email_entry;
            case OfflineId:
                return Page.id_intro;
            case Selfie:
                return Page.selfie_only_intro;
            case SelfieCapture:
                return Page.selfie_camera;
            case PhotoReview:
                return Page.review_your_photos;
            case DeviceNotSupported:
                return Page.device_not_supported;
            default:
                return null;
        }
    }

    public IdentityVerificationType toIdentityVerificationType() {
        switch (this) {
            case ProfilePhoto:
                return IdentityVerificationType.PHOTO_WITH_FACE;
            case Phone:
                return IdentityVerificationType.PHONE_NUMBER;
            case Email:
                return IdentityVerificationType.EMAIL;
            case OfflineId:
                return IdentityVerificationType.GOVERNMENT_ID;
            case Selfie:
                return IdentityVerificationType.SELFIE;
            case SelfieCapture:
                return IdentityVerificationType.SELFIE;
            case PhotoReview:
                return IdentityVerificationType.SELFIE;
            default:
                return null;
        }
    }

    public static AccountVerificationStep toAccountVerificationStep(String verification) {
        char c = 65535;
        switch (verification.hashCode()) {
            case -906020504:
                if (verification.equals(AccountVerification.SELFIE)) {
                    c = 4;
                    break;
                }
                break;
            case 96619420:
                if (verification.equals("email")) {
                    c = 2;
                    break;
                }
                break;
            case 106642798:
                if (verification.equals("phone")) {
                    c = 1;
                    break;
                }
                break;
            case 822216233:
                if (verification.equals(AccountVerification.SCANID)) {
                    c = 3;
                    break;
                }
                break;
            case 1714556009:
                if (verification.equals(AccountVerification.PHOTO)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return ProfilePhoto;
            case 1:
                return Phone;
            case 2:
                return Email;
            case 3:
                return OfflineId;
            case 4:
                return Selfie;
            default:
                return null;
        }
    }
}
