package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.FetchAllReservationsRequest;
import com.airbnb.android.core.requests.ThreadRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.FetchAllReservationsResponse;
import com.airbnb.android.core.responses.ThreadResponse;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.HostReservationObjectActivity;
import com.airbnb.android.lib.adapters.ReservationPickerAdapter;
import com.airbnb.android.lib.adapters.ReservationPickerAdapter.Listener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.ViewUtils;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import p032rx.Observer;

public class ReservationPickerFragment extends AirFragment implements Listener {
    private static final String ARG_FALLBACK_CONFIRMATION_CODE = "confirmation_code";
    private static final String ARG_THREAD_ID = "thread_id";
    private static final int REQUEST_CODE_RESERVATION_OBJECT = 0;
    private ReservationPickerAdapter adapter;
    private final NonResubscribableRequestListener<AirBatchResponse> batchResponseRequestListener = new C0699RL().onResponse(ReservationPickerFragment$$Lambda$5.lambdaFactory$(this)).onError(ReservationPickerFragment$$Lambda$6.lambdaFactory$(this)).buildWithoutResubscription();
    private String fallbackReservationConfirmationCode;
    private final NonResubscribableRequestListener<FetchAllReservationsResponse> fetchAllReservationsResponseRequestListener = new C0699RL().onResponse(ReservationPickerFragment$$Lambda$1.lambdaFactory$(this)).onError(ReservationPickerFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    MessagingJitneyLogger jitneyLogger;
    @BindView
    FrameLayout loader;
    @BindView
    RecyclerView recyclerView;
    /* access modifiers changed from: 0000 */
    @State
    public ArrayList<Reservation> reservations;
    /* access modifiers changed from: 0000 */
    @State
    public Thread thread;
    private long threadId;
    private final NonResubscribableRequestListener<ThreadResponse> threadResponseRequestListener = new C0699RL().onResponse(ReservationPickerFragment$$Lambda$3.lambdaFactory$(this)).onError(ReservationPickerFragment$$Lambda$4.lambdaFactory$(this)).buildWithoutResubscription();

    static /* synthetic */ void lambda$new$4(ReservationPickerFragment reservationPickerFragment, AirBatchResponse data) {
        reservationPickerFragment.setLoading(false);
        if (reservationPickerFragment.reservations.isEmpty()) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Went to reservation picker with no reservations."));
            reservationPickerFragment.fallback();
        } else if (reservationPickerFragment.reservations.size() == 1) {
            if (reservationPickerFragment.thread.getActiveInquiry() == null) {
                reservationPickerFragment.gotToOnlyReservation((Reservation) reservationPickerFragment.reservations.get(0));
            } else {
                reservationPickerFragment.adapter.setReservationsAndInquiry(reservationPickerFragment.reservations, reservationPickerFragment.thread);
            }
        } else if (reservationPickerFragment.reservations.size() > 1) {
            reservationPickerFragment.adapter.setReservationsAndInquiry(reservationPickerFragment.reservations, reservationPickerFragment.thread);
        }
    }

    public static ReservationPickerFragment newInstanceForThread(long threadId2, String fallbackReservationConfirmationCode2) {
        return (ReservationPickerFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReservationPickerFragment()).putLong("thread_id", threadId2)).putString("confirmation_code", fallbackReservationConfirmationCode2)).build();
    }

    public static Intent createIntent(Context context, long threadId2, String fallbackReservationConfirmationCode2) {
        return TransparentActionBarActivity.intentForFragment(context, newInstanceForThread(threadId2, fallbackReservationConfirmationCode2));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_reservation_picker, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        this.threadId = getArguments().getLong("thread_id");
        this.fallbackReservationConfirmationCode = getArguments().getString("confirmation_code");
        this.adapter = new ReservationPickerAdapter(getContext(), this, savedInstanceState);
        if (ListUtils.isEmpty((Collection<?>) this.reservations)) {
            loadReservations();
        } else {
            setLoading(false);
            this.adapter.setReservationsAndInquiry(this.reservations, this.thread);
        }
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    private void loadReservations() {
        setLoading(true);
        new AirBatchRequest(Arrays.asList(new BaseRequestV2[]{new ThreadRequest(InboxType.Host, this.threadId, this.jitneyLogger).withListener((Observer) this.threadResponseRequestListener), new FetchAllReservationsRequest(this.threadId, true).withListener((Observer) this.fetchAllReservationsResponseRequestListener)}), this.batchResponseRequestListener).execute(this.requestManager);
    }

    private void setLoading(boolean loading) {
        ViewUtils.setVisibleIf((View) this.loader, loading);
        ViewUtils.setVisibleIf((View) this.recyclerView, !loading);
    }

    public void goToReservation(String confirmationCode) {
        startActivityForResult(HostReservationObjectActivity.intentForConfirmationCode(getContext(), confirmationCode, ROLaunchSource.ReservationPicker), 0);
    }

    public void goToReservation(long threadId2) {
        startActivityForResult(HostReservationObjectActivity.intentForThread(getContext(), threadId2, ROLaunchSource.ReservationPicker), 0);
    }

    private void gotToOnlyReservation(Reservation reservation) {
        getActivity().finish();
        startActivityForResult(HostReservationObjectActivity.intentForReservation(getContext(), reservation, ROLaunchSource.ReservationPicker), 0);
    }

    private boolean hasValidFallbackReservationConfirmationCode() {
        return this.fallbackReservationConfirmationCode != null && !TextUtils.isEmpty(this.fallbackReservationConfirmationCode);
    }

    /* access modifiers changed from: private */
    public void fallback() {
        getActivity().finish();
        if (hasValidFallbackReservationConfirmationCode()) {
            goToReservation(this.fallbackReservationConfirmationCode);
        } else {
            goToReservation(this.threadId);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        loadReservations();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
