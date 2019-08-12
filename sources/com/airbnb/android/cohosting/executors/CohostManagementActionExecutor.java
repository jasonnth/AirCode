package com.airbnb.android.cohosting.executors;

import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.core.models.ListingManager;

public interface CohostManagementActionExecutor {
    void choosePaymentType(FeeType feeType, String str);

    void cohostingContractDetails(long j, String str);

    void cohostingServiceIntro();

    void inviteFriend();

    void listingManagerDetails(String str);

    void makePrimaryHost(String str);

    void pendingCohostDetails(long j);

    void removeCohost(ListingManager listingManager);

    void setNotification(String str);

    void shareEarnings(String str);

    void showListingManager(long j);

    void stopShareEarnings(String str);
}
