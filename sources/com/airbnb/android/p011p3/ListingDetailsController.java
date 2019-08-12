package com.airbnb.android.p011p3;

import android.view.View;
import android.widget.ImageView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.models.UrgencyMessageData;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.core.p009p3.interfaces.OnP3DataChangedListener;
import com.airbnb.android.p011p3.models.HomeLayout;
import com.airbnb.p027n2.utils.SnackbarWrapper;

/* renamed from: com.airbnb.android.p3.ListingDetailsController */
public interface ListingDetailsController {
    P3Analytics getAnalytics();

    HomeLayout getMockHomeLayout();

    ReviewsController getReviewsController();

    P3State getState();

    UrgencyMessageData getUrgencyMessage();

    boolean hasGuidebook();

    void launchIdentityForBooking();

    void launchInstantBookOnlyP2();

    void launchP3ForSimilarListing(View view, Listing listing, PricingQuote pricingQuote);

    void launchP3ReviewSearch();

    void launchReportListingFlow();

    void notifyStateChange();

    void onHomeMarqueeImageClicked(ImageView imageView, int i);

    void onItemClick(int i);

    void onReferralCreditClicked(ReferralStatusForMobile referralStatusForMobile);

    void registerP3DataChangedListener(OnP3DataChangedListener onP3DataChangedListener);

    void showErrorSnackbar(int i);

    void showNetworkExceptionSnackbar(NetworkException networkException);

    void showSnackbar(SnackbarWrapper snackbarWrapper);

    void showZenDialog(ZenDialog zenDialog);

    void unregisterP3DataChangedListener(OnP3DataChangedListener onP3DataChangedListener);
}
