package com.airbnb.android.booking.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.steps.ActivityBookingStep;
import com.airbnb.android.booking.steps.ArrivalDetailsBookingStep;
import com.airbnb.android.booking.steps.BookingStep;
import com.airbnb.android.booking.steps.ContactHostBookingStep;
import com.airbnb.android.booking.steps.GuestCountBookingStep;
import com.airbnb.android.booking.steps.HouseRulesBookingStep;
import com.airbnb.android.booking.steps.IdentityBookingStep;
import com.airbnb.android.booking.steps.ManageGuestDetailsBookingStep;
import com.airbnb.android.booking.steps.PostBookingStep;
import com.airbnb.android.booking.steps.QuickPayBookingStep;
import com.airbnb.android.booking.steps.ReviewBookingStep;
import com.airbnb.android.booking.steps.TripPurposeBookingStep;
import com.airbnb.android.booking.steps.VerifiedIdBookingStep;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.bugsnag.android.Severity;
import icepick.State;
import java.util.HashMap;
import java.util.Map;

public class BookingController {
    private final ActivityBookingStep[] activityBookingSteps = {this.identityBookingStep, this.quickPayBookingStep};
    private final ArrivalDetailsBookingStep arrivalDetailsBookingStep = new ArrivalDetailsBookingStep(this);
    private final BookingActivityFacade bookingActivityFacade;
    private final BookingStep[] bookingSteps = {this.guestCountBookingStep, this.reviewBookingStep, this.arrivalDetailsBookingStep, this.manageGuestDetailsBookingStep, this.tripPurposeBookingStep, this.identityBookingStep, this.houseRulesBookingStep, this.contactHostBookingStep, this.quickPayBookingStep, this.verifiedIdBookingStep, this.postBookingStep};
    private final ContactHostBookingStep contactHostBookingStep = new ContactHostBookingStep(this);
    private final Context context;
    @State
    int currentStep;
    private final GuestCountBookingStep guestCountBookingStep = new GuestCountBookingStep(this);
    @State
    String hostMessage;
    private final HouseRulesBookingStep houseRulesBookingStep = new HouseRulesBookingStep(this);
    private final IdentityBookingStep identityBookingStep = new IdentityBookingStep(this);
    @State
    Listing listing;
    private final ManageGuestDetailsBookingStep manageGuestDetailsBookingStep = new ManageGuestDetailsBookingStep(this);
    @State
    String mobileSearchSessionId;
    @State
    boolean navForward;
    private final PostBookingStep postBookingStep = new PostBookingStep(this);
    @State
    Price price;
    private final QuickPayBookingStep quickPayBookingStep = new QuickPayBookingStep(this);
    private final Map<Integer, ActivityBookingStep> requestCodeActivityStepMap = new HashMap();
    private final RequestManager requestManager;
    @State
    Reservation reservation;
    @State
    ReservationDetails reservationDetails;
    private final ReviewBookingStep reviewBookingStep = new ReviewBookingStep(this);
    @State
    int stepCounter;
    @State
    int totalValidSteps;
    private final TripPurposeBookingStep tripPurposeBookingStep = new TripPurposeBookingStep(this);
    private final VerifiedIdBookingStep verifiedIdBookingStep = new VerifiedIdBookingStep(this);

    public interface BookingActivityFacade {
        void finishCancelled();

        void finishOK();

        AirbnbAccountManager getAccountManager();

        BookingController getController();

        IdentityJitneyLogger getIdentityJitneyLogger();

        BookingJitneyLogger getLogger();

        RequestManager getRequestManager();

        void hideLoader();

        boolean isVerifiedBusinessTraveler();

        void removeP4LoadTracker();

        void showFragment(Fragment fragment, FragmentTransitionType fragmentTransitionType);

        void showGuestIdentifications(boolean z, String str);

        void showLoader();

        void showPriceDetails();

        void startActivity(Intent intent);

        void startActivityForResult(Intent intent, int i);

        void trackP4LoadEnd(Strap strap);

        void trackP4LoadStart(long j);
    }

    public interface BookingStepLoader {
        void hideLoader();

        void showLoader();
    }

    public BookingController(Context context2, BookingActivityFacade bookingActivityFacade2, RequestManager requestManager2, Bundle savedState) {
        this.context = context2;
        this.bookingActivityFacade = bookingActivityFacade2;
        this.requestManager = requestManager2;
        this.navForward = true;
        this.stepCounter = 0;
        this.totalValidSteps = 0;
        IcepickWrapper.restoreInstanceState(this, savedState);
        initBookingSteps(savedState);
    }

    private void initBookingSteps(Bundle savedState) {
        ActivityBookingStep[] activityBookingStepArr;
        for (BookingStep bookingStep : this.bookingSteps) {
            bookingStep.restoreInstanceState(savedState);
        }
        for (ActivityBookingStep activityBookingStep : this.activityBookingSteps) {
            this.requestCodeActivityStepMap.put(Integer.valueOf(activityBookingStep.getRequestCode()), activityBookingStep);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
        for (BookingStep bookingStep : this.bookingSteps) {
            bookingStep.onSaveInstanceState(outState);
        }
    }

    public int getTotalBookingSteps(boolean reset) {
        BookingStep[] bookingStepArr;
        this.totalValidSteps = 0;
        this.stepCounter = reset ? 1 : this.stepCounter;
        for (BookingStep step : this.bookingSteps) {
            if (step.initialized() && !step.exclude()) {
                this.totalValidSteps++;
                if (step == this.quickPayBookingStep) {
                    break;
                }
            }
        }
        return this.totalValidSteps;
    }

    public void showCurrentStep() {
        if (this.bookingSteps[this.currentStep].initialized()) {
            if (!this.bookingSteps[this.currentStep].exclude()) {
                this.bookingSteps[this.currentStep].show(!this.navForward);
            } else if (this.navForward) {
                showNextStep(getDefaultBookingStepLoader());
            } else {
                showPreviousStep();
            }
        }
    }

    public void showNextStep(BookingStepLoader loader) {
        this.navForward = true;
        while (this.currentStep < this.bookingSteps.length - 1) {
            this.currentStep++;
            if (!this.bookingSteps[this.currentStep].initialized()) {
                loader.showLoader();
                return;
            } else if (!this.bookingSteps[this.currentStep].exclude()) {
                this.stepCounter++;
                this.bookingSteps[this.currentStep].show(false);
                return;
            }
        }
        this.bookingActivityFacade.finishOK();
    }

    public void showPreviousStep() {
        this.navForward = false;
        while (this.currentStep > 0) {
            this.currentStep--;
            if (this.bookingSteps[this.currentStep].initialized()) {
                if (!this.bookingSteps[this.currentStep].exclude()) {
                    this.stepCounter--;
                    this.bookingSteps[this.currentStep].show(true);
                    return;
                }
            } else {
                return;
            }
        }
        this.bookingActivityFacade.finishCancelled();
    }

    public void bookingStepInitialized(BookingStep bookingStep, boolean showCurrentStep) {
        if (bookingStep == this.bookingSteps[this.currentStep]) {
            getBookingActivityFacade().hideLoader();
            if (showCurrentStep) {
                showCurrentStep();
            }
        }
        if (bookingStep == this.identityBookingStep && this.totalValidSteps != 0) {
            getTotalBookingSteps(false);
        }
    }

    public String getP4Steps() {
        if (this.totalValidSteps == 0) {
            return this.context.getString(C0704R.string.p4_first_step);
        }
        if (this.stepCounter > this.totalValidSteps) {
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Booking Controller - current step is great than total valid steps: curr: " + this.stepCounter + ", total:" + this.totalValidSteps), Severity.WARNING);
            return String.format(this.context.getString(C0704R.string.p4_steps), new Object[]{Integer.valueOf(this.totalValidSteps), Integer.valueOf(this.totalValidSteps)});
        }
        return String.format(this.context.getString(C0704R.string.p4_steps), new Object[]{Integer.valueOf(this.stepCounter), Integer.valueOf(this.totalValidSteps)});
    }

    public Context getContext() {
        return (Context) Check.notNull(this.context);
    }

    public RequestManager getRequestManager() {
        return (RequestManager) Check.notNull(this.requestManager);
    }

    public Listing getListing() {
        return this.listing;
    }

    public void setListing(Listing listing2) {
        this.listing = listing2;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public ReservationDetails getReservationDetails() {
        return this.reservationDetails;
    }

    public Price getPrice() {
        return this.price;
    }

    public void setPrice(Price price2) {
        this.price = price2;
    }

    public String getMobileSearchSessionId() {
        return this.mobileSearchSessionId;
    }

    public String getHostMessage() {
        return this.hostMessage;
    }

    public BookingActivityFacade getBookingActivityFacade() {
        return this.bookingActivityFacade;
    }

    public void setReservation(Reservation reservation2) {
        boolean reservationInitialized;
        if (this.reservation != null || reservation2 == null) {
            reservationInitialized = false;
        } else {
            reservationInitialized = true;
        }
        this.reservation = reservation2;
        if (reservationInitialized) {
            for (BookingStep bookingStep : this.bookingSteps) {
                bookingStep.onReservationLoaded();
            }
        }
    }

    public void fetchIdentity() {
        this.identityBookingStep.fetchIdentityVerificationState();
    }

    public void setReservationDetails(ReservationDetails reservationDetails2) {
        this.reservationDetails = reservationDetails2;
    }

    public void setMobileSearchSessionId(String mobileSearchSessionId2) {
        this.mobileSearchSessionId = mobileSearchSessionId2;
    }

    public void setHostMessage(String hostMessage2) {
        this.hostMessage = hostMessage2;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.requestCodeActivityStepMap.containsKey(Integer.valueOf(requestCode))) {
            ((ActivityBookingStep) this.requestCodeActivityStepMap.get(Integer.valueOf(requestCode))).onActivityResult(resultCode, data);
        }
    }

    public User getVerificationUser() {
        return this.identityBookingStep.getVerificationUser();
    }

    public static BookingStepLoader getBookingStepLoader(final BookingNavigationView navigationView) {
        return new BookingStepLoader() {
            public void showLoader() {
                navigationView.showLoader();
            }

            public void hideLoader() {
                navigationView.hideLoader();
            }
        };
    }

    public BookingStepLoader getDefaultBookingStepLoader() {
        return new BookingStepLoader() {
            public void showLoader() {
                BookingController.this.getBookingActivityFacade().showLoader();
            }

            public void hideLoader() {
                BookingController.this.getBookingActivityFacade().hideLoader();
            }
        };
    }
}
