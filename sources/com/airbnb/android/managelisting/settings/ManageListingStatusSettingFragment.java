package com.airbnb.android.managelisting.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p002v7.app.AlertDialog.Builder;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.ListingDeleteRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.listing.ListedStatus;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.analytics.UnlistAnalytics;
import com.airbnb.android.managelisting.settings.ManageListingStatusAdapter.StatusActionListener;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class ManageListingStatusSettingFragment extends ManageListingBaseFragment {
    /* access modifiers changed from: private */
    public ManageListingStatusAdapter adapter;
    final RequestListener<ListingResponse> deleteListingListener = new C0699RL().onResponse(ManageListingStatusSettingFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingStatusSettingFragment$$Lambda$4.lambdaFactory$(this)).build();
    private final StatusActionListener listener = new StatusActionListener() {
        public void list() {
            if (!ListedStatus.calculate(ManageListingStatusSettingFragment.this.controller.getListing()).equals(ListedStatus.Listed)) {
                ManageListingStatusSettingFragment.this.adapter.setListedLoading();
                UpdateListingRequest.forField(ManageListingStatusSettingFragment.this.controller.getListing().getId(), ListingRequestConstants.JSON_HAS_AVAILABILITY, Boolean.valueOf(true)).withListener((Observer) ManageListingStatusSettingFragment.this.updateListingListener).execute(ManageListingStatusSettingFragment.this.requestManager);
            }
        }

        public void snooze() {
            ManageListingStatusSettingFragment.this.controller.actionExecutor.snoozeListing(0);
        }

        public void unlist() {
            if (!ListedStatus.calculate(ManageListingStatusSettingFragment.this.controller.getListing()).equals(ListedStatus.Unlisted)) {
                UnlistAnalytics.trackViewUnlist(ManageListingStatusSettingFragment.this.controller.getListing());
                ManageListingStatusSettingFragment.this.controller.actionExecutor.unlistReasons();
            }
        }

        public void deactivateListing() {
            new Builder(ManageListingStatusSettingFragment.this.getContext()).setMessage(C7368R.string.manage_listing_status_deactivate_listing_info).setNeutralButton(C7368R.string.cancel, ManageListingStatusSettingFragment$1$$Lambda$1.lambdaFactory$()).setPositiveButton(C7368R.string.manage_listing_status_deactivate_listing_button, ManageListingStatusSettingFragment$1$$Lambda$2.lambdaFactory$(this)).show();
        }

        static /* synthetic */ void lambda$deactivateListing$0(DialogInterface dialog, int id) {
        }

        static /* synthetic */ void lambda$deactivateListing$1(C74501 r4, DialogInterface dialog, int id) {
            ManageListingStatusSettingFragment.this.adapter.setRowsEnabled(false);
            new ListingDeleteRequest(ManageListingStatusSettingFragment.this.controller.getListing().getId(), ManageListingStatusSettingFragment.this.deleteListingListener).execute(ManageListingStatusSettingFragment.this.requestManager);
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingStatusSettingFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingStatusSettingFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingStatusSettingFragment create() {
        return new ManageListingStatusSettingFragment();
    }

    static /* synthetic */ void lambda$new$0(ManageListingStatusSettingFragment manageListingStatusSettingFragment, SimpleListingResponse response) {
        manageListingStatusSettingFragment.controller.setListing(response.listing);
        manageListingStatusSettingFragment.adapter.updateForListing(response.listing);
    }

    static /* synthetic */ void lambda$new$1(ManageListingStatusSettingFragment manageListingStatusSettingFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(manageListingStatusSettingFragment.getView(), e);
        manageListingStatusSettingFragment.adapter.cancelLoading();
    }

    static /* synthetic */ void lambda$new$3(ManageListingStatusSettingFragment manageListingStatusSettingFragment, AirRequestNetworkException error) {
        manageListingStatusSettingFragment.adapter.setRowsEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingStatusSettingFragment.getView(), error);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ManageListingStatusAdapter(getContext(), this.controller.getListing(), this.listener);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public void dataUpdated() {
        this.adapter.updateForListing(this.controller.getListing());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_only, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingStatus;
    }
}
