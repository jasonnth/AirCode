package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import p032rx.Observer;

public class ManageListingInstantBookUpsellFragment extends ManageListingBaseFragment {
    @BindView
    FixedDualActionFooter footer;
    private ManageListingInstantBookUpsellAdapter instantBookUpsellAdapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingInstantBookUpsellFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingInstantBookUpsellFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ManageListingInstantBookUpsellFragment manageListingInstantBookUpsellFragment, SimpleListingResponse response) {
        manageListingInstantBookUpsellFragment.footer.setButtonLoading(false);
        manageListingInstantBookUpsellFragment.controller.setListing(response.listing);
        manageListingInstantBookUpsellFragment.controller.actionExecutor.popToHome();
    }

    static /* synthetic */ void lambda$new$2(ManageListingInstantBookUpsellFragment manageListingInstantBookUpsellFragment, AirRequestNetworkException e) {
        manageListingInstantBookUpsellFragment.footer.setButtonLoading(false);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(manageListingInstantBookUpsellFragment.getView(), (NetworkException) e, ManageListingInstantBookUpsellFragment$$Lambda$5.lambdaFactory$(manageListingInstantBookUpsellFragment));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.instantBookUpsellAdapter = new ManageListingInstantBookUpsellAdapter(this.controller);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_footer_bar, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.instantBookUpsellAdapter);
        setupFooter();
        return view;
    }

    private void setupFooter() {
        this.footer.setVisibility(0);
        this.footer.setButtonText(C7368R.string.manage_listing_turn_on_instant_book);
        this.footer.setButtonOnClickListener(ManageListingInstantBookUpsellFragment$$Lambda$3.lambdaFactory$(this));
        this.footer.setSecondaryButtonText(C7368R.string.cancel);
        this.footer.setSecondaryButtonOnClickListener(ManageListingInstantBookUpsellFragment$$Lambda$4.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void turnOnInstantBook() {
        this.footer.setButtonLoading(true);
        UpdateListingRequest.forField(this.controller.getListing().getId(), ListingRequestConstants.JSON_INSTANT_BOOK_VISIBILITY_KEY, InstantBookingAllowedCategory.EveryOne.serverKey).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
