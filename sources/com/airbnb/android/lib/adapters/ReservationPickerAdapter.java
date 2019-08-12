package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ImpactMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.viewcomponents.viewmodels.NoProfilePhotoGuestDetailsSummaryEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class ReservationPickerAdapter extends AirEpoxyAdapter {
    private final Context context;
    private final NoProfilePhotoGuestDetailsSummaryEpoxyModel_ guestDetailsEpoxyModel = new NoProfilePhotoGuestDetailsSummaryEpoxyModel_();
    private final Listener listener;
    private final ImpactMarqueeEpoxyModel_ marqueeEpoxyModel = new ImpactMarqueeEpoxyModel_();
    private final SectionHeaderEpoxyModel_ sectionHeaderEpoxyModel = new SectionHeaderEpoxyModel_().titleRes(C0880R.string.reservations);

    public interface Listener {
        void goToReservation(long j);

        void goToReservation(String str);
    }

    public ReservationPickerAdapter(Context context2, Listener listener2, Bundle savedInstanceState) {
        this.context = context2;
        this.listener = listener2;
        onRestoreInstanceState(savedInstanceState);
    }

    public void setReservationsAndInquiry(List<Reservation> reservations, Thread thread) {
        this.models.clear();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeEpoxyModel, this.guestDetailsEpoxyModel, this.sectionHeaderEpoxyModel});
        if (thread.hasActiveInquiry()) {
            this.models.add(threadToModel(thread));
        }
        this.models.addAll(reservationsToModels(reservations));
        setMarquee(reservations, thread);
        setGuestDetails(((Reservation) reservations.get(0)).getGuest(), thread);
        notifyDataSetChanged();
    }

    private void setMarquee(List<Reservation> reservations, Thread thread) {
        int numReservations = reservations.size();
        if (thread.hasActiveInquiry()) {
            numReservations++;
        }
        this.marqueeEpoxyModel.title(this.context.getResources().getQuantityString(C0880R.plurals.x_reservations, numReservations, new Object[]{Integer.valueOf(numReservations)})).backgroundColor(ContextCompat.getColor(this.context, C0880R.color.n2_babu));
    }

    private void setGuestDetails(User guest, Thread thread) {
        this.guestDetailsEpoxyModel.guest(guest).showDivider(false).showNoProfilePhoto(FeatureToggles.hideGuestProfilePhoto(thread.getReservationStatus()));
    }

    private List<StandardRowEpoxyModel_> reservationsToModels(List<Reservation> reservations) {
        return FluentIterable.from((Iterable<E>) reservations).transform(ReservationPickerAdapter$$Lambda$1.lambdaFactory$(this)).toList();
    }

    /* access modifiers changed from: private */
    public StandardRowEpoxyModel_ reservationToModel(Reservation reservation) {
        return new StandardRowEpoxyModel_().title((CharSequence) getReservationTitleString(reservation)).subtitle((CharSequence) getReservationSubtitleString(reservation)).titleMaxLine(2).actionText((CharSequence) this.context.getResources().getString(C0880R.string.view_details)).clickListener(ReservationPickerAdapter$$Lambda$2.lambdaFactory$(this, reservation));
    }

    private StandardRowEpoxyModel_ threadToModel(Thread thread) {
        return new StandardRowEpoxyModel_().title((CharSequence) getThreadTitleString(thread)).subtitle((CharSequence) getThreadSubtitleString(thread)).titleMaxLine(2).actionText((CharSequence) this.context.getResources().getString(C0880R.string.view_details)).clickListener(ReservationPickerAdapter$$Lambda$3.lambdaFactory$(this, thread));
    }

    private String getThreadTitleString(Thread thread) {
        return ReservationStatusDisplay.forHost(thread).getString(this.context) + System.getProperty("line.separator") + thread.getStartDate().getDateSpanString(this.context, thread.getEndDate());
    }

    private String getThreadSubtitleString(Thread thread) {
        return this.context.getResources().getQuantityString(C0880R.plurals.x_guests, thread.getNumberOfGuests(), new Object[]{Integer.valueOf(thread.getNumberOfGuests())}) + System.getProperty("line.separator") + thread.getListing().getName();
    }

    private String getReservationTitleString(Reservation reservation) {
        return ReservationStatusDisplay.forHost(reservation).getString(this.context) + System.getProperty("line.separator") + reservation.getStartDate().getDateSpanString(this.context, reservation.getEndDate());
    }

    private String getReservationSubtitleString(Reservation reservation) {
        return this.context.getResources().getQuantityString(C0880R.plurals.x_guests, reservation.getGuestCount(), new Object[]{Integer.valueOf(reservation.getGuestCount())}) + System.getProperty("line.separator") + reservation.getListing().getName();
    }
}
