package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.ReservationCancellationLogger;
import com.airbnb.android.core.cancellation.host.HostCancellationParams;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.requests.DeleteReservationRequest;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import p032rx.Observer;

public class ReservationCanceledFragment extends AirFragment {
    private static final String ARG_CANCELLATION_PARAMS_KEY = "cancellation_params";
    private static final String ARG_MESSAGE_KEY = "message";
    private static final String ARG_REASON_KEY = "arg_reason";
    private static final String CANCELLATION_INFO_KEY = "cancellation_info";
    private static final String RESERVATION_KEY = "reservation";
    @State
    HostCancellationParams cancellationParams;
    final RequestListener<ReservationResponse> deleteReservationRequestListener = new C0699RL().onResponse(ReservationCanceledFragment$$Lambda$1.lambdaFactory$(this)).onError(ReservationCanceledFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    FrameLayout loader;
    ReservationCancellationLogger logger;
    @BindView
    SheetMarquee marquee;
    @BindView
    AirButton nextButton;
    @State
    Reservation reservation;
    @State
    ReservationCancellationInfo reservationCancellationInfo;

    private ReasonPickerCallback getReasonPickerCallback() {
        Check.state(getActivity() instanceof ReasonPickerCallback);
        return (ReasonPickerCallback) getActivity();
    }

    static /* synthetic */ void lambda$new$0(ReservationCanceledFragment reservationCanceledFragment, ReservationResponse data) {
        reservationCanceledFragment.setLoading(false);
        reservationCanceledFragment.logger.onReasonSelected(reservationCanceledFragment.reservation, ReservationCancellationReason.Canceled);
    }

    static /* synthetic */ void lambda$new$1(ReservationCanceledFragment reservationCanceledFragment, AirRequestNetworkException e) {
        reservationCanceledFragment.setLoading(false);
        NetworkUtil.tryShowErrorWithSnackbar(reservationCanceledFragment.getView(), e);
    }

    public static ReservationCanceledFragment newInstance(Reservation reservation2, ReservationCancellationInfo reservationCancellationInfo2, HostCancellationParams cancellationParams2) {
        return (ReservationCanceledFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReservationCanceledFragment()).putParcelable("reservation", (Parcelable) Check.notNull(reservation2))).putParcelable(CANCELLATION_INFO_KEY, (Parcelable) Check.notNull(reservationCancellationInfo2))).putParcelable(ARG_CANCELLATION_PARAMS_KEY, (Parcelable) Check.notNull(cancellationParams2))).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_reservation_canceled, container, false);
        bindViews(view);
        this.reservation = (Reservation) getArguments().getParcelable("reservation");
        HostCancellationParams cancellationParams2 = (HostCancellationParams) getArguments().getParcelable(ARG_CANCELLATION_PARAMS_KEY);
        setLoading(true);
        new DeleteReservationRequest(this.reservation.getId(), cancellationParams2).withListener((Observer) this.deleteReservationRequestListener).execute(this.requestManager);
        this.reservationCancellationInfo = (ReservationCancellationInfo) getArguments().getParcelable(CANCELLATION_INFO_KEY);
        this.marquee.setTitle(C0880R.string.reservation_canceled_title);
        if (!(this.reservationCancellationInfo == null || this.reservationCancellationInfo.getCancellationFeeInfo() == null)) {
            this.marquee.setSubtitle(getString(C0880R.string.reservation_canceled_subtitle, this.reservationCancellationInfo.getCancellationFeeInfo().getValue()));
        }
        return view;
    }

    private void setLoading(boolean isLoading) {
        ViewUtils.setVisibleIf((View) this.loader, isLoading);
    }

    @OnClick
    public void clickNext() {
        getReasonPickerCallback().onCancelReservationClicked(ReservationCancellationReason.Canceled, false);
    }

    public NavigationTag getNavigationTrackingTag() {
        return ReservationCancellationLogger.getNavigationTag(ReservationCancellationReason.Canceled, false);
    }

    public Strap getNavigationTrackingParams() {
        return Strap.make().mo11639kv("event_data", getNavigationTrackingTag().trackingName).mo11638kv("listing_id", this.reservation.getListing().getId()).mo11638kv("reservation_id", this.reservation.getId());
    }
}
