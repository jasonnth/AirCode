package com.airbnb.android.lib.identity.psb;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.SheetFlowActivity$$Icepick;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.lib.identity.psb.CreateIdentificationActivity;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class CreateIdentificationActivity$$Icepick<T extends CreateIdentificationActivity> extends SheetFlowActivity$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9599H = new Helper("com.airbnb.android.lib.identity.psb.CreateIdentificationActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.surname = f9599H.getString(state, "surname");
            target.givenNames = f9599H.getString(state, "givenNames");
            target.identificationNumber = f9599H.getString(state, "identificationNumber");
            target.countryCode = f9599H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            target.dateOfExpiry = (AirDate) f9599H.getParcelable(state, "dateOfExpiry");
            target.reservationDetails = (ReservationDetails) f9599H.getParcelable(state, "reservationDetails");
            target.isInstantBookable = f9599H.getBoolean(state, "isInstantBookable");
            target.isP4Redesign = f9599H.getBoolean(state, "isP4Redesign");
            target.identityType = (Type) f9599H.getParcelable(state, "identityType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9599H.putString(state, "surname", target.surname);
        f9599H.putString(state, "givenNames", target.givenNames);
        f9599H.putString(state, "identificationNumber", target.identificationNumber);
        f9599H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
        f9599H.putParcelable(state, "dateOfExpiry", target.dateOfExpiry);
        f9599H.putParcelable(state, "reservationDetails", target.reservationDetails);
        f9599H.putBoolean(state, "isInstantBookable", target.isInstantBookable);
        f9599H.putBoolean(state, "isP4Redesign", target.isP4Redesign);
        f9599H.putParcelable(state, "identityType", target.identityType);
    }
}
