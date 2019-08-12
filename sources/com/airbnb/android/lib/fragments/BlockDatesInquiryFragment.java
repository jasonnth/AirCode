package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.HostReservationObjectJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.responses.SpecialOfferResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.requests.UpdateSpecialOfferRequest;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class BlockDatesInquiryFragment extends AirFragment {
    private static final String ARG_PROVIDER = "trip_provider";
    @BindView
    AirButton blockDatesButton;
    final RequestListener<SpecialOfferResponse> blockDatesListener = new RequestListener<SpecialOfferResponse>() {
        public void onRequestCompleted(boolean succesful) {
            if (succesful) {
                BlockDatesInquiryFragment.this.blockDatesButton.setState(State.Success);
                BlockDatesInquiryFragment.this.handleBlockDates();
                BlockDatesInquiryFragment.this.blockDatesButton.setState(State.Normal);
            }
        }

        public void onResponse(SpecialOfferResponse data) {
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            BlockDatesInquiryFragment.this.blockDatesButton.setState(State.Normal);
            BlockDatesInquiryFragment.this.cancelButton.setEnabled(true);
            if (e instanceof NetworkException) {
                NetworkUtil.tryShowErrorWithSnackbar(BlockDatesInquiryFragment.this.getView(), e);
            } else {
                NetworkUtil.toastGenericNetworkError(BlockDatesInquiryFragment.this.getContext());
            }
        }
    };
    @BindView
    AirButton cancelButton;
    HostReservationObjectJitneyLogger jitneyLogger;
    @BindView
    SheetMarquee marquee;
    @icepick.State
    TripInformationProvider provider;

    public static BlockDatesInquiryFragment newInstanceForProvider(TripInformationProvider provider2) {
        return (BlockDatesInquiryFragment) ((FragmentBundleBuilder) FragmentBundler.make(new BlockDatesInquiryFragment()).putParcelable("trip_provider", provider2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_block_dates_inquiry, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.provider = (TripInformationProvider) getArguments().getParcelable("trip_provider");
        }
        setMarquee();
        return view;
    }

    /* access modifiers changed from: private */
    public void handleBlockDates() {
        getActivity().setResult(11);
        getActivity().finish();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickAccept() {
        if (this.provider.getSpecialOffer() != null) {
            new UpdateSpecialOfferRequest(this.provider.getSpecialOffer().getId(), true).withListener((Observer) this.blockDatesListener).execute(this.requestManager);
            this.blockDatesButton.setState(State.Loading);
            this.cancelButton.setEnabled(false);
            return;
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("special offer incorrect in blockDateInquiry"));
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickCancel() {
        getActivity().finish();
    }

    private void setMarquee() {
        this.marquee.setTitle(getString(C0880R.string.ro_block_dates_sheet_title, this.provider.getGuestIfPossible().getName()));
        this.marquee.setSubtitle(getString(C0880R.string.ro_block_dates_sheet_subtitle));
    }
}
