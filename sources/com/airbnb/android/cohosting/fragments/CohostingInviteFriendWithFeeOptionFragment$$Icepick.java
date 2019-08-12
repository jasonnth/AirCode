package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendWithFeeOptionFragment;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingInviteFriendWithFeeOptionFragment$$Icepick<T extends CohostingInviteFriendWithFeeOptionFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7808H = new Helper("com.airbnb.android.cohosting.fragments.CohostingInviteFriendWithFeeOptionFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.email = f7808H.getString(state, "email");
            target.message = f7808H.getString(state, "message");
            target.shareEarnings = f7808H.getBoolean(state, "shareEarnings");
            target.feeType = (FeeType) f7808H.getSerializable(state, "feeType");
            target.percentage = f7808H.getInt(state, "percentage");
            target.minimumFee = f7808H.getInt(state, "minimumFee");
            target.fixedFee = f7808H.getInt(state, "fixedFee");
            target.amountCurrency = f7808H.getString(state, "amountCurrency");
            target.includeCleaningFee = f7808H.getBoolean(state, "includeCleaningFee");
            target.listing = (Listing) f7808H.getParcelable(state, "listing");
            target.listingManagers = f7808H.getParcelableArrayList(state, "listingManagers");
            target.cohostInvitations = f7808H.getParcelableArrayList(state, "cohostInvitations");
            target.sourceFlow = (C1958CohostingSourceFlow) f7808H.getSerializable(state, "sourceFlow");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7808H.putString(state, "email", target.email);
        f7808H.putString(state, "message", target.message);
        f7808H.putBoolean(state, "shareEarnings", target.shareEarnings);
        f7808H.putSerializable(state, "feeType", target.feeType);
        f7808H.putInt(state, "percentage", target.percentage);
        f7808H.putInt(state, "minimumFee", target.minimumFee);
        f7808H.putInt(state, "fixedFee", target.fixedFee);
        f7808H.putString(state, "amountCurrency", target.amountCurrency);
        f7808H.putBoolean(state, "includeCleaningFee", target.includeCleaningFee);
        f7808H.putParcelable(state, "listing", target.listing);
        f7808H.putParcelableArrayList(state, "listingManagers", target.listingManagers);
        f7808H.putParcelableArrayList(state, "cohostInvitations", target.cohostInvitations);
        f7808H.putSerializable(state, "sourceFlow", target.sourceFlow);
    }
}
