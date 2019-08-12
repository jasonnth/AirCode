package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.HostReservationObjectJitneyLogger;
import com.airbnb.android.core.analytics.TripsAnalytics;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.requests.InquiryRequest;
import com.airbnb.android.core.requests.UpdateMessageThreadRequest;
import com.airbnb.android.core.responses.InquiryResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import p032rx.Observer;

public class PreapproveInquiryFragment extends AirFragment {
    private static final String ARG_PROVIDER = "trip_provider";
    private static final String ARG_SK_CANCELLATION = "sk_cancellation_policy";
    @BindView
    AirButton cancelButton;
    final RequestListener<InquiryResponse> inquiryListener = new C0699RL().onResponse(PreapproveInquiryFragment$$Lambda$3.lambdaFactory$(this)).onError(PreapproveInquiryFragment$$Lambda$4.lambdaFactory$(this)).build();
    HostReservationObjectJitneyLogger jitneyLogger;
    @BindView
    SheetMarquee marquee;
    @BindView
    AirButton preapproveButton;
    @State
    TripInformationProvider provider;
    final RequestListener<BaseResponse> updateInquiryListener = new C0699RL().onResponse(PreapproveInquiryFragment$$Lambda$1.lambdaFactory$(this)).onError(PreapproveInquiryFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(PreapproveInquiryFragment preapproveInquiryFragment, BaseResponse data) {
        preapproveInquiryFragment.jitneyLogger.reservationObjectPreapproveConfirmation(preapproveInquiryFragment.provider);
        preapproveInquiryFragment.handlePreApprove();
    }

    /* access modifiers changed from: private */
    public void handleError(NetworkException error) {
        this.preapproveButton.setState(AirButton.State.Normal);
        this.cancelButton.setEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), error);
    }

    public static PreapproveInquiryFragment newInstanceForProvider(TripInformationProvider provider2) {
        return (PreapproveInquiryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PreapproveInquiryFragment()).putParcelable("trip_provider", provider2)).putBoolean(ARG_SK_CANCELLATION, false)).build();
    }

    public static PreapproveInquiryFragment newInstanceForProvider(TripInformationProvider provider2, boolean hostAgreedSouthKoreanPreapproval) {
        return (PreapproveInquiryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PreapproveInquiryFragment()).putParcelable("trip_provider", provider2)).putBoolean(ARG_SK_CANCELLATION, hostAgreedSouthKoreanPreapproval)).build();
    }

    /* access modifiers changed from: private */
    public void handleUpdatedInquiry(TripInformationProvider udpatedProvider) {
        this.preapproveButton.setState(AirButton.State.Normal);
        AirFragment fragment = BlockDatesInquiryFragment.newInstanceForProvider(udpatedProvider);
        getFragmentManager().beginTransaction().setCustomAnimations(C0880R.anim.fragment_enter, C0880R.anim.fragment_exit, C0880R.anim.fragment_enter_pop, C0880R.anim.fragment_exit_pop).replace(C0880R.C0882id.content_container, fragment).addToBackStack(fragment.getTag()).commit();
    }

    private void handlePreApprove() {
        if ((this.provider.getListing().isInstantBookEnabled() || this.provider.getListing().isInstantBookable()) && FeatureToggles.preapproveBlockDates()) {
            long threadId = this.provider.getThreadId();
            TripsAnalytics.trackInquiryLoad(threadId);
            new InquiryRequest(threadId).withListener((Observer) this.inquiryListener).execute(this.requestManager);
            return;
        }
        this.preapproveButton.setState(AirButton.State.Normal);
        getActivity().setResult(10);
        getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_preapprove_inquiry, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        if (savedInstanceState == null) {
            this.provider = (TripInformationProvider) getArguments().getParcelable("trip_provider");
        }
        setMarquee();
        return view;
    }

    @OnClick
    public void clickAccept() {
        UpdateMessageThreadRequest.forPreApproveOrDecline(this.provider.getThreadId(), this.provider.getListing().getId(), null, -1, false, this.updateInquiryListener, getArguments().getBoolean(ARG_SK_CANCELLATION)).withListener(this.updateInquiryListener).execute(this.requestManager);
        this.preapproveButton.setState(AirButton.State.Loading);
        this.cancelButton.setEnabled(false);
    }

    @OnClick
    public void clickCancel() {
        getActivity().setResult(0);
        getActivity().finish();
    }

    private void setMarquee() {
        this.marquee.setTitle(getString(C0880R.string.ro_preapprove_sheet_title, this.provider.getGuestIfPossible().getName(), getResources().getQuantityString(C0880R.plurals.x_nights, this.provider.getReservedNightsCount(), new Object[]{Integer.valueOf(this.provider.getReservedNightsCount())})));
        this.marquee.setSubtitle(getString(C0880R.string.ro_preapprove_sheet_subtitle));
    }
}
