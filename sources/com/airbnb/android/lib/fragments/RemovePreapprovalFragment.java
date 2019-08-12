package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.HostReservationObjectJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.requests.WithdrawSpecialOfferRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class RemovePreapprovalFragment extends AirFragment {
    private static final String ARG_PROVIDER = "trip_provider";
    @BindView
    AirButton cancelButton;
    HostReservationObjectJitneyLogger jitneyLogger;
    @BindView
    SheetMarquee marquee;
    @State
    TripInformationProvider provider;
    @BindView
    AirButton removeButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<BaseResponse> withdrawSpecialOfferListener = new C0699RL().onResponse(RemovePreapprovalFragment$$Lambda$1.lambdaFactory$(this)).onError(RemovePreapprovalFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(RemovePreapprovalFragment removePreapprovalFragment, BaseResponse data) {
        removePreapprovalFragment.jitneyLogger.reservationObjectRemovePreapproveConfirmation(removePreapprovalFragment.provider);
        removePreapprovalFragment.removeButton.setState(AirButton.State.Success);
        removePreapprovalFragment.getFragmentManager().popBackStack();
        new SnackbarWrapper().view(removePreapprovalFragment.getView()).body(removePreapprovalFragment.provider.getStatus() == ReservationStatus.Preapproved ? C0880R.string.ro_preapprove_remove_sheet_success : C0880R.string.ro_special_offer_remove_sheet_success).buildAndShow();
    }

    static /* synthetic */ void lambda$new$1(RemovePreapprovalFragment removePreapprovalFragment, AirRequestNetworkException e) {
        removePreapprovalFragment.removeButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(removePreapprovalFragment.getView(), e);
    }

    public static RemovePreapprovalFragment newInstanceForProvider(TripInformationProvider provider2) {
        return (RemovePreapprovalFragment) ((FragmentBundleBuilder) FragmentBundler.make(new RemovePreapprovalFragment()).putParcelable("trip_provider", provider2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_remove_preapproval, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        this.provider = (TripInformationProvider) getArguments().getParcelable("trip_provider");
        setToolbar(this.toolbar);
        setMarquee();
        return view;
    }

    @OnClick
    public void clickAccept() {
        WithdrawSpecialOfferRequest.create(this.provider.getSpecialOffer().getId()).withListener((Observer) this.withdrawSpecialOfferListener).execute(this.requestManager);
        this.removeButton.setState(AirButton.State.Loading);
    }

    @OnClick
    public void clickCancel() {
        getFragmentManager().popBackStack();
    }

    private void setMarquee() {
        boolean isPreapproval;
        SpecialOffer specialOffer = this.provider.getSpecialOffer();
        if (this.provider.getStatus() == ReservationStatus.Preapproved) {
            isPreapproval = true;
        } else {
            isPreapproval = false;
        }
        String guestName = this.provider.getGuestIfPossible().getName();
        this.marquee.setTitle(getString(isPreapproval ? C0880R.string.ro_preapprove_remove_sheet_title : C0880R.string.ro_special_offer_remove_sheet_title, guestName));
        this.marquee.setSubtitle(getString(isPreapproval ? C0880R.string.ro_preapprove_remove_sheet_subtitle : C0880R.string.ro_special_offer_remove_sheet_subtitle, guestName, specialOffer.getListingName(), specialOffer.getStartDate().getDateSpanString(getContext(), specialOffer.getEndDate()), this.mCurrencyHelper.formatNativeCurrency((double) specialOffer.getPriceNative(), true)));
    }
}
