package com.airbnb.android.core.host;

import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.enums.ListingStatus;
import com.airbnb.android.core.models.ListingPickerInfo;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.ListingPickerInfoRequest;
import com.airbnb.android.core.responses.ListingPickerInfoResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.google.common.collect.Lists;
import java.util.List;
import p032rx.Observer;

public class ListingPromoController {
    private static final int MAX_ACTIVE_LISTING_COUNT_TO_EXPOSE_UNFINISHED_LISTING = 5;
    private final AirbnbAccountManager accountManager;
    private final List<Listener> listeners = Lists.newArrayList();
    /* access modifiers changed from: private */
    public ListingPickerInfo listing;
    private final NonResubscribableRequestListener<ListingPickerInfoResponse> listingPickerInfoListener = new C0699RL().onResponse(ListingPromoController$$Lambda$1.lambdaFactory$(this)).buildWithoutResubscription();

    public interface Listener {
        void refreshUnfinishedListing();
    }

    static /* synthetic */ boolean lambda$null$0(ListingPickerInfo l) {
        return l.getStatus() == ListingStatus.InProgress;
    }

    public ListingPromoController(AirbnbAccountManager accountManager2) {
        this.accountManager = accountManager2;
    }

    public ListingPickerInfo getListing() {
        return this.listing;
    }

    public void refreshListingsInfo() {
        User user = this.accountManager.getCurrentUser();
        if (user != null) {
            this.listing = null;
            int totalActiveListingCount = user.getListingsCount();
            boolean hasInactiveListings = user.getTotalListingsCount() > totalActiveListingCount;
            if (totalActiveListingCount <= 5 && hasInactiveListings) {
                ListingPickerInfoRequest.forUserId(this.accountManager.getCurrentUserId()).withListener((Observer) this.listingPickerInfoListener).execute(NetworkUtil.singleFireExecutor());
            }
        }
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }
}
