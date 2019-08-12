package com.airbnb.android.lib.fragments.inbox.threads;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.PrimaryButton;

public class ThreadActionButtonController {
    private final Listener listener;
    private final PrimaryButton respondButton;

    public interface Listener {
        void continueBooking();

        void viewAlterationRequest();

        void viewIdentityVerification();

        void viewListingDetails();

        void viewReservation();

        void writeReview();
    }

    public ThreadActionButtonController(PrimaryButton guestRespondButton, Listener listener2) {
        this.respondButton = guestRespondButton;
        this.listener = listener2;
    }

    public void hideRespondButton() {
        this.respondButton.setVisibility(8);
    }

    public void setupHostRespondButton(Thread thread, boolean isSameUser) {
        if (thread.needsReview()) {
            int i = C0880R.string.button_text_to_write_review;
            Listener listener2 = this.listener;
            listener2.getClass();
            showRespondButton(i, ThreadActionButtonController$$Lambda$1.lambdaFactory$(listener2));
        } else if (thread.hasPendingAlterationRequest()) {
            int i2 = C0880R.string.ro_response_view_alteration_request;
            Listener listener3 = this.listener;
            listener3.getClass();
            showRespondButton(i2, ThreadActionButtonController$$Lambda$2.lambdaFactory$(listener3));
        } else {
            switch (thread.getReservationStatus()) {
                case Pending:
                    int i3 = C0880R.string.ro_response_now_accept_or_decline;
                    Listener listener4 = this.listener;
                    listener4.getClass();
                    showRespondButton(i3, ThreadActionButtonController$$Lambda$3.lambdaFactory$(listener4));
                    return;
                case Inquiry:
                    if (!isSameUser) {
                        int i4 = C0880R.string.ro_response_now_preapprove_or_decline;
                        Listener listener5 = this.listener;
                        listener5.getClass();
                        showRespondButton(i4, ThreadActionButtonController$$Lambda$4.lambdaFactory$(listener5));
                        return;
                    }
                    break;
            }
            this.respondButton.setVisibility(8);
        }
    }

    public void setupGuestRespondButton(Thread thread) {
        this.respondButton.setVisibility(8);
        switch (thread.getReservationStatus()) {
            case Inquiry:
                showRespondButtonForBookIt(true, thread);
                return;
            case Preapproved:
            case SpecialOffer:
                showRespondButtonForSpecialOffer(thread);
                return;
            case Checkpoint:
                showRespondButtonForCheckPoint();
                return;
            case Timedout:
            case Cancelled:
                showRespondButtonForBookIt(false, thread);
                return;
            default:
                if (thread.needsReview()) {
                    int i = C0880R.string.button_text_to_write_review;
                    Listener listener2 = this.listener;
                    listener2.getClass();
                    showRespondButton(i, ThreadActionButtonController$$Lambda$5.lambdaFactory$(listener2));
                    return;
                }
                return;
        }
    }

    private void showRespondButtonForBookIt(boolean canShowInstantBook, Thread thread) {
        if (thread.hasListing() && thread.getStartDate() != null) {
            showRespondButtonForContinueBooking(canShowInstantBook && thread.getListing().isInstantBookable(), thread.getStartDate());
        }
    }

    private void showRespondButtonForSpecialOffer(Thread thread) {
        SpecialOffer specialOffer = thread.getSpecialOffer();
        if (specialOffer != null && specialOffer.getStartDate() != null) {
            showRespondButtonForContinueBooking(false, specialOffer.getStartDate());
        }
    }

    private void showRespondButtonForContinueBooking(boolean showInstantBook, AirDate reservationStartDate) {
        Runnable lambdaFactory$;
        boolean requestIsValid = !AirDate.isInPast(reservationStartDate);
        int bookButtonResId = (!requestIsValid || !showInstantBook) ? C0880R.string.complete_booking : C0880R.string.instant_book;
        if (requestIsValid) {
            Listener listener2 = this.listener;
            listener2.getClass();
            lambdaFactory$ = ThreadActionButtonController$$Lambda$6.lambdaFactory$(listener2);
        } else {
            Listener listener3 = this.listener;
            listener3.getClass();
            lambdaFactory$ = ThreadActionButtonController$$Lambda$7.lambdaFactory$(listener3);
        }
        showRespondButton(bookButtonResId, lambdaFactory$);
    }

    private void showRespondButtonForCheckPoint() {
        int i = C0880R.string.ro_response_verify_id;
        Listener listener2 = this.listener;
        listener2.getClass();
        showRespondButton(i, ThreadActionButtonController$$Lambda$8.lambdaFactory$(listener2));
    }

    private void showRespondButton(int buttonText, Runnable listener2) {
        this.respondButton.setVisibility(0);
        this.respondButton.setText(buttonText);
        this.respondButton.setOnClickListener(ThreadActionButtonController$$Lambda$9.lambdaFactory$(listener2));
    }
}
