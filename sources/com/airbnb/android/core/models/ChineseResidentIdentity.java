package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.core.models.generated.GenChineseResidentIdentity;

public class ChineseResidentIdentity extends GenChineseResidentIdentity implements Parcelable, GuestIdentity {
    public static final Creator<ChineseResidentIdentity> CREATOR = new Creator<ChineseResidentIdentity>() {
        public ChineseResidentIdentity[] newArray(int size) {
            return new ChineseResidentIdentity[size];
        }

        public ChineseResidentIdentity createFromParcel(Parcel source) {
            ChineseResidentIdentity object = new ChineseResidentIdentity();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getIdentityString() {
        return String.valueOf(getIdentificationNumber());
    }

    public Type getType() {
        return Type.ChineseNationalID;
    }

    public void toggleSelected() {
        setSelected(!this.mSelected);
    }

    public void setBooker(boolean isBooker) {
        this.mBooker = isBooker;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }

    public String getDisplayText(Context context) {
        return context.getString(C0716R.string.guest_identity_full_name, new Object[]{getGivenNames(), getSurname()});
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenChineseResidentIdentity that = (GenChineseResidentIdentity) o;
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
            z = this.mIdentificationNumber.equals(that.getIdentificationNumber());
        } else if (that.getIdentificationNumber() != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i;
        int i2 = 0;
        if (this.mSurname != null) {
            result = this.mSurname.hashCode();
        } else {
            result = 0;
        }
        int i3 = result * 31;
        if (this.mGivenNames != null) {
            i = this.mGivenNames.hashCode();
        } else {
            i = 0;
        }
        int i4 = (i3 + i) * 31;
        if (this.mIdentificationNumber != null) {
            i2 = this.mIdentificationNumber.hashCode();
        }
        return ((i4 + i2) * 31) + this.mId;
    }
}
