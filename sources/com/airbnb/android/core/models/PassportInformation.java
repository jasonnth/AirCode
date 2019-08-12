package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.core.models.generated.GenPassportInformation;

public class PassportInformation extends GenPassportInformation implements Parcelable, GuestIdentity {
    public static final Creator<PassportInformation> CREATOR = new Creator<PassportInformation>() {
        public PassportInformation[] newArray(int size) {
            return new PassportInformation[size];
        }

        public PassportInformation createFromParcel(Parcel source) {
            PassportInformation object = new PassportInformation();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getIdentityString() {
        return getIdentificationNumber();
    }

    public Type getType() {
        return Type.Passport;
    }

    public void toggleSelected() {
        setSelected(!this.mSelected);
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }

    public String getDisplayText(Context context) {
        return context.getString(C0716R.string.guest_identity_full_name, new Object[]{getGivenNames(), getSurname()});
    }

    public void setBooker(boolean isBooker) {
        this.mBooker = isBooker;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenPassportInformation that = (GenPassportInformation) o;
        if (this.mId != that.getId()) {
            return false;
        }
        if (this.mSurname != null) {
            if (!this.mSurname.equals(that.getSurname())) {
                return false;
            }
        } else if (that.getSurname() != null) {
            return false;
        }
        if (this.mGivenNames != null) {
            if (!this.mGivenNames.equals(that.getGivenNames())) {
                return false;
            }
        } else if (that.getGivenNames() != null) {
            return false;
        }
        if (this.mIdentificationNumber != null) {
            if (!this.mIdentificationNumber.equals(that.getIdentificationNumber())) {
                return false;
            }
        } else if (that.getIdentificationNumber() != null) {
            return false;
        }
        if (this.mLocalizedDateOfBirth != null) {
            z = this.mLocalizedDateOfBirth.equals(that.getLocalizedDateOfBirth());
        } else if (that.getLocalizedDateOfBirth() != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i;
        int i2;
        int i3 = 0;
        if (this.mSurname != null) {
            result = this.mSurname.hashCode();
        } else {
            result = 0;
        }
        int i4 = result * 31;
        if (this.mGivenNames != null) {
            i = this.mGivenNames.hashCode();
        } else {
            i = 0;
        }
        int i5 = (i4 + i) * 31;
        if (this.mIdentificationNumber != null) {
            i2 = this.mIdentificationNumber.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (i5 + i2) * 31;
        if (this.mLocalizedDateOfBirth != null) {
            i3 = this.mLocalizedDateOfBirth.hashCode();
        }
        return ((i6 + i3) * 31) + this.mId;
    }
}
