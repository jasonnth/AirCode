package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.ReferralContact.Email;
import com.airbnb.android.core.models.ReferralContact.Phone;
import com.airbnb.android.core.models.generated.GenHostReferralsInviteContact;

public class HostReferralsInviteContact extends GenHostReferralsInviteContact {
    public static final Creator<HostReferralsInviteContact> CREATOR = new Creator<HostReferralsInviteContact>() {
        public HostReferralsInviteContact[] newArray(int size) {
            return new HostReferralsInviteContact[size];
        }

        public HostReferralsInviteContact createFromParcel(Parcel source) {
            HostReferralsInviteContact object = new HostReferralsInviteContact();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String INVITATION_SOURCE_EMAIL = "contact_importer";
    private static final String INVITATION_SOURCE_SMS = "sms";

    public HostReferralsInviteContact() {
    }

    public HostReferralsInviteContact(Email email) {
        this.mReferredEmail = email.getValue();
        this.mReferredUserName = email.getContact().getName();
        this.mInvitationSource = INVITATION_SOURCE_EMAIL;
    }

    public HostReferralsInviteContact(Phone phoneNumber) {
        this.mReferredPhone = phoneNumber.getValue();
        this.mReferredUserName = phoneNumber.getContact().getName();
        this.mInvitationSource = INVITATION_SOURCE_SMS;
    }
}
