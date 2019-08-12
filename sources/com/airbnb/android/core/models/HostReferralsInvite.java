package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.ReferralContact.Email;
import com.airbnb.android.core.models.ReferralContact.Phone;
import com.airbnb.android.core.models.generated.GenHostReferralsInvite;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.List;

public class HostReferralsInvite extends GenHostReferralsInvite {
    public static final Creator<HostReferralsInvite> CREATOR = new Creator<HostReferralsInvite>() {
        public HostReferralsInvite[] newArray(int size) {
            return new HostReferralsInvite[size];
        }

        public HostReferralsInvite createFromParcel(Parcel source) {
            HostReferralsInvite object = new HostReferralsInvite();
            object.readFromParcel(source);
            return object;
        }
    };

    public HostReferralsInvite() {
    }

    public HostReferralsInvite(List<Email> emails, List<Phone> phoneNumbers) {
        this.mHostReferralsInviteContacts = new ArrayList();
        this.mHostReferralsInviteContacts.addAll(FluentIterable.from((Iterable<E>) emails).transform(HostReferralsInvite$$Lambda$1.lambdaFactory$()).toList());
        this.mHostReferralsInviteContacts.addAll(FluentIterable.from((Iterable<E>) phoneNumbers).transform(HostReferralsInvite$$Lambda$2.lambdaFactory$()).toList());
        this.mClientType = "android";
    }
}
