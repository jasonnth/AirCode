package com.airbnb.android.lib.fragments.unlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import butterknife.OnClick;
import butterknife.Optional;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.UnlistActivity;
import com.airbnb.android.lib.analytics.LegacyUnlistAnalytics;
import com.airbnb.android.lib.enums.LegacyUnlistReason;
import com.airbnb.rxgroups.TaggedObserver;
import icepick.State;

public abstract class BaseSnoozeListingFragment extends AirFragment {
    private static final String DIALOG_LOADING = "loading_dialog";
    public static final String RESULT_EXTRA_DELETED = "result_listing_deleted";
    public static final String RESULT_EXTRA_LISTING = "result_extra_listing";
    public static final int UNLIST_OR_SNOOZE_LOADING_DURATION = 3000;
    @State
    Listing listing;
    final RequestListener<SimpleListingResponse> listingUpdateRequestListener = new RequestListener<SimpleListingResponse>() {
        public void onResponse(SimpleListingResponse response) {
            BaseSnoozeListingFragment.this.listing = response.listing;
            BaseSnoozeListingFragment.this.getProgressFragment().onProgressComplete(BaseSnoozeListingFragment.this.getProgressCompleteTitle(), 0, C0880R.C0881drawable.icon_complete, 3000);
        }

        public void onErrorResponse(AirRequestNetworkException error) {
            Toast.makeText(BaseSnoozeListingFragment.this.getActivity(), C0880R.string.listing_listing_failed, 0).show();
            BaseSnoozeListingFragment.this.hideLoadingDialog();
        }
    };
    @State
    int loadingTitle;
    @State
    int progressCompleteTitle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.listing = ((UnlistActivity) getActivity()).getListing();
        }
    }

    public void onResume() {
        super.onResume();
        ((UnlistActivity) getActivity()).setupActionBar(getTitle(), new Object[0]);
        if (this.requestManager.hasRequest((BaseRequestListener<T>) this.listingUpdateRequestListener, UpdateListingRequest.class)) {
            showLoadingDialog();
            this.requestManager.resubscribe((TaggedObserver<T>) this.listingUpdateRequestListener, UpdateListingRequest.class);
        }
    }

    /* access modifiers changed from: protected */
    public LegacyUnlistReason getUnlistReason() {
        return LegacyUnlistReason.NoLongerHaveAccess;
    }

    /* access modifiers changed from: protected */
    public int getLoadingTitle() {
        return this.loadingTitle;
    }

    /* access modifiers changed from: protected */
    public void setLoadingTitle(int loadingTitle2) {
        this.loadingTitle = loadingTitle2;
    }

    /* access modifiers changed from: protected */
    public int getTitle() {
        return C0880R.string.unlist;
    }

    /* access modifiers changed from: protected */
    public int getProgressCompleteTitle() {
        return this.progressCompleteTitle;
    }

    /* access modifiers changed from: protected */
    public void setProgressCompeleteTitle(int progressCompleteTitle2) {
        this.progressCompleteTitle = progressCompleteTitle2;
    }

    /* access modifiers changed from: protected */
    public void executeOrReattachListingUpdateRequest(BaseRequest<SimpleListingResponse> request) {
        showLoadingDialog();
        request.withListener(this.listingUpdateRequestListener);
        this.requestManager.executeOrResubscribe(request, this.listingUpdateRequestListener);
    }

    /* access modifiers changed from: protected */
    public void listListing() {
        setLoadingTitle(C0880R.string.confirm_listing);
        setProgressCompeleteTitle(C0880R.string.list_action_successful);
        setListingAsAvailable(true);
    }

    /* access modifiers changed from: protected */
    @OnClick
    @Optional
    public void unlistListing() {
        LegacyUnlistAnalytics.trackSubmitUnlistWithUnlistReason(this.listing, getUnlistReason());
        processUnlistListing();
    }

    /* access modifiers changed from: protected */
    public void processUnlistListing() {
        setLoadingTitle(C0880R.string.confirm_unlisting);
        setProgressCompeleteTitle(C0880R.string.unlist_action_successful);
        setListingAsAvailable(false);
    }

    private void setListingAsAvailable(boolean isAvailable) {
        executeOrReattachListingUpdateRequest(UpdateListingRequest.forField(this.listing.getId(), ListingRequestConstants.JSON_HAS_AVAILABILITY, Boolean.valueOf(isAvailable)));
    }

    /* access modifiers changed from: protected */
    public void snoozeListing(AirDate snoozeStartDate, AirDate snoozeEndDate) {
        LegacyUnlistAnalytics.trackSubmitSnooze(this.listing, snoozeStartDate, snoozeEndDate);
        setLoadingTitle(C0880R.string.confirm_snoozing);
        setProgressCompeleteTitle(C0880R.string.snooze_action_successful);
        executeOrReattachListingUpdateRequest(UpdateListingRequest.forLegacySnooze(this.listing.getId(), snoozeStartDate, snoozeEndDate));
    }

    /* access modifiers changed from: protected */
    public void showLoadingDialog() {
        if (getProgressFragment() == null) {
            ProgressDialogFragment progressFragment = ProgressDialogFragment.newInstance(getContext(), getLoadingTitle(), 0);
            progressFragment.setCancelable(false);
            progressFragment.setTargetFragment(this, 401);
            progressFragment.show(getFragmentManager(), DIALOG_LOADING);
        }
    }

    /* access modifiers changed from: protected */
    public void hideLoadingDialog() {
        ProgressDialogFragment progressFragment = getProgressFragment();
        if (progressFragment != null) {
            progressFragment.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public ProgressDialogFragment getProgressFragment() {
        return (ProgressDialogFragment) getFragmentManager().findFragmentByTag(DIALOG_LOADING);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 401) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(RESULT_EXTRA_LISTING, this.listing);
            Activity unlistActivity = getActivity();
            unlistActivity.setResult(-1, resultIntent);
            unlistActivity.finish();
        }
    }
}
