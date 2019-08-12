package com.airbnb.android.lib.cancellation.host;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class CancellationPenaltiesFragment extends AirFragment {
    private static final String ARG_IS_MODAL = "is_modal";
    private static final String ARG_RESERVATION = "reservation";
    private static final String ARG_WAIVE_PENALTIES = "waive_penalties";
    @BindView
    AirButton continueButton;
    @BindView
    StandardRow feeRow;
    @State
    boolean isModal;
    private Listener listener;
    @State
    Reservation reservation;
    @BindView
    DocumentMarquee title;
    @BindView
    AirToolbar toolbar;
    @State
    boolean waivePenalties;

    public interface Listener {
        void onContinueToReasons();
    }

    public static CancellationPenaltiesFragment newInstance(Reservation reservation2, boolean waivePenalties2) {
        return newInstance(reservation2, waivePenalties2, false);
    }

    public static CancellationPenaltiesFragment newModalInstance(Reservation reservation2, boolean waivePenalties2) {
        return newInstance(reservation2, waivePenalties2, true);
    }

    private static CancellationPenaltiesFragment newInstance(Reservation reservation2, boolean waivePenalties2, boolean isModal2) {
        return (CancellationPenaltiesFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CancellationPenaltiesFragment()).putParcelable("reservation", reservation2)).putBoolean(ARG_WAIVE_PENALTIES, waivePenalties2)).putBoolean(ARG_IS_MODAL, isModal2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_cancellation_penalties, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getArguments().getParcelable("reservation");
            this.isModal = getArguments().getBoolean(ARG_IS_MODAL);
            this.waivePenalties = getArguments().getBoolean(ARG_WAIVE_PENALTIES);
        }
        if (this.isModal) {
            this.toolbar.setNavigationIcon(2);
            this.toolbar.setNavigationOnClickListener(CancellationPenaltiesFragment$$Lambda$1.lambdaFactory$(this));
            this.continueButton.setVisibility(8);
        }
        if (this.waivePenalties) {
            this.title.setTitle(C0880R.string.cancellation_section_title_waived);
            this.title.setCaption((CharSequence) null);
        } else {
            this.title.setTitle(C0880R.string.cancellation_section_title);
            this.title.setCaption(C0880R.string.cancellation_warning);
        }
        setupCancellationFeeRow();
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface");
        }
    }

    @OnClick
    public void onClickContinue() {
        this.listener.onContinueToReasons();
    }

    private void setupCancellationFeeRow() {
        int feeNative = this.reservation.getCancellationHostFeeNative();
        if (feeNative <= 0) {
            this.feeRow.setVisibility(8);
            return;
        }
        String cancellationFeeFormatted = this.mCurrencyHelper.formatNativeCurrency((double) feeNative, true);
        this.feeRow.setTitle((CharSequence) getString(C0880R.string.cancellation_fee, cancellationFeeFormatted));
        this.feeRow.setSubtitleText((CharSequence) getString(C0880R.string.cancellation_fee_description, numDaysUntilReservationString(), this.reservation.getGuest().getName()));
    }

    private String numDaysUntilReservationString() {
        int numDays = AirDate.today().getDaysUntil(this.reservation.getStartDate());
        return getResources().getQuantityString(C0880R.plurals.x_days, numDays, new Object[]{Integer.valueOf(numDays)});
    }
}
