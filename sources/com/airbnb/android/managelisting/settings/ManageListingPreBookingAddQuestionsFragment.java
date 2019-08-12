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
import com.airbnb.android.core.requests.UpdateBookingSettingsRequest;
import com.airbnb.android.core.responses.BookingSettingsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import p032rx.Observer;

public class ManageListingPreBookingAddQuestionsFragment extends ManageListingBaseFragment {
    @BindView
    FixedDualActionFooter footer;
    private ManageListingPreBookingAddQuestionsAdapter preBookingAddQuestionsAdapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<BookingSettingsResponse> updateBookingSettingsListener = new C0699RL().onResponse(ManageListingPreBookingAddQuestionsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingPreBookingAddQuestionsFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ManageListingPreBookingAddQuestionsFragment manageListingPreBookingAddQuestionsFragment, BookingSettingsResponse response) {
        manageListingPreBookingAddQuestionsFragment.footer.setButtonLoading(false);
        manageListingPreBookingAddQuestionsFragment.controller.setBookingSettings(response.bookingSettings);
        manageListingPreBookingAddQuestionsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$2(ManageListingPreBookingAddQuestionsFragment manageListingPreBookingAddQuestionsFragment, AirRequestNetworkException e) {
        manageListingPreBookingAddQuestionsFragment.footer.setButtonLoading(false);
        manageListingPreBookingAddQuestionsFragment.preBookingAddQuestionsAdapter.setRowsEnabled(true);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(manageListingPreBookingAddQuestionsFragment.getView(), (NetworkException) e, ManageListingPreBookingAddQuestionsFragment$$Lambda$4.lambdaFactory$(manageListingPreBookingAddQuestionsFragment));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.preBookingAddQuestionsAdapter = new ManageListingPreBookingAddQuestionsAdapter(this.controller);
        this.preBookingAddQuestionsAdapter.onRestoreInstanceState(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_footer_bar, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.preBookingAddQuestionsAdapter);
        setupFooter();
        return view;
    }

    private void setupFooter() {
        this.footer.setVisibility(0);
        this.footer.setButtonText(C7368R.string.save);
        this.footer.setButtonOnClickListener(ManageListingPreBookingAddQuestionsFragment$$Lambda$3.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void checkUpdateBookingSettings() {
        if (!canSaveChanges()) {
            getFragmentManager().popBackStack();
            return;
        }
        this.footer.setButtonLoading(true);
        this.preBookingAddQuestionsAdapter.setRowsEnabled(false);
        new UpdateBookingSettingsRequest(this.controller.getListing().getId(), this.preBookingAddQuestionsAdapter.getBookingCustomQuestions(), this.preBookingAddQuestionsAdapter.getBookingStandardQuestions()).withListener((Observer) this.updateBookingSettingsListener).execute(this.requestManager);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.preBookingAddQuestionsAdapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.preBookingAddQuestionsAdapter.isUpdated(this.controller.getListing());
    }
}
