package com.airbnb.android.booking.steps;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.intents.QuickPayActivityIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import icepick.State;

public class QuickPayBookingStep implements ActivityBookingStep {
    private static final int REQUEST_CODE = 850;
    public static final int RESULT_CODE_IDENTITY_ERROR = -100;
    private final BookingController controller;
    @State
    boolean showIdentity;

    public QuickPayBookingStep(BookingController controller2) {
        this.controller = controller2;
    }

    public void restoreInstanceState(Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public int getRequestCode() {
        return 850;
    }

    public void onActivityResult(int resultCode, Intent data) {
        if (this.showIdentity) {
            this.showIdentity = false;
            show(false);
        } else if (resultCode == -1) {
            Reservation reservation = (Reservation) data.getParcelableExtra(QuickPayActivityIntents.RESULT_EXTRA_RESERVATION);
            if (reservation != null) {
                this.controller.setReservation(reservation);
            }
            this.controller.getBookingActivityFacade().getLogger().clickNavigation(this.controller.getReservationDetails(), this.controller.getReservation().isInstantBookable(), C2467P4FlowPage.Quickpay, C2466P4FlowNavigationMethod.NextButton);
            this.controller.showNextStep(this.controller.getDefaultBookingStepLoader());
        } else if (resultCode == 0) {
            if (data != null) {
                BillPriceQuote billPriceQuote = (BillPriceQuote) data.getParcelableExtra(QuickPayActivityIntents.RESULT_EXTRA_BILL_PRICE_QUOTE);
                if (billPriceQuote != null) {
                    this.controller.setPrice(billPriceQuote.getPrice());
                }
            }
            this.controller.showPreviousStep();
        } else if (resultCode == -100) {
            showIdentity();
        }
    }

    private void showIdentity() {
        this.showIdentity = true;
        User verificationUser = this.controller.getVerificationUser();
        boolean shouldUseIdentityFlowForFrozenReservation = IdentityVerificationUtil.shouldUseIdentityFlowForFrozenReservation(this.controller.getReservation(), verificationUser);
        Listing listing = this.controller.getListing();
        this.controller.getBookingActivityFacade().startActivityForResult(IdentityVerificationUtil.verify(this.controller.getContext(), VerificationFlow.FinalizeBookingV2, listing.getPrimaryHost(), verificationUser, listing.getId(), new String[0]), 850);
    }

    public void show(boolean isBack) {
        Listing listing = this.controller.getListing();
        Reservation reservation = this.controller.getReservation();
        ReservationDetails reservationDetails = this.controller.getReservationDetails();
        Builder billingParamBuilder = HomesClientParameters.builder().reservationConfirmationCode(reservation.getConfirmationCode()).messageToHost(reservationDetails.messageToHost()).isBusinessTrip(Boolean.valueOf(reservationDetails.isBusinessTrip())).businessTripNotes(reservationDetails.businessTripNote()).guestIdentities(reservationDetails.identifications()).searchRankingId(this.controller.getMobileSearchSessionId()).p4Steps(this.controller.getP4Steps());
        this.controller.getBookingActivityFacade().startActivityForResult(QuickPayActivityIntents.intentForHomes(this.controller.getContext(), CartItem.builder().thumbnailUrl(listing.getPhoto().getUrlForSize(ImageSize.LandscapeSmall)).title(this.controller.getContext().getString(C0704R.string.room_type_in_city, new Object[]{listing.getRoomType(this.controller.getContext()), listing.getCity()})).description(reservationDetails.getReservationDescription(this.controller.getContext().getResources())).quickPayParameters(billingParamBuilder.build()).build(), reservation), 850);
    }

    public boolean exclude() {
        return false;
    }

    public boolean initialized() {
        return this.controller.getReservation() != null;
    }

    public void onReservationLoaded() {
    }
}
