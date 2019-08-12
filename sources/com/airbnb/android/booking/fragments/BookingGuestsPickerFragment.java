package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.guestpicker.GuestsPickerView;
import com.airbnb.android.core.views.guestpicker.GuestsPickerView.OnValueChangeListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.P4FlowGuestSheetMethod.p170v1.C2464P4FlowGuestSheetMethod;
import com.airbnb.jitney.event.logging.P4FlowGuestSheetSection.p171v1.C2465P4FlowGuestSheetSection;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.jitney.event.logging.ToggleMethod.p268v1.C2759ToggleMethod;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

public class BookingGuestsPickerFragment extends BookingV2BaseFragment {
    private static final String ARG_GUEST_DATA = "arg_guest_data";
    private static final String ARG_LISTING = "arg_listing";
    private static final String ARG_SHOW_BLOCK_IB_WARNING = "arg_show_block_ib_warning";
    private static final String ARG_SOURCE_TAG = "arg_source_tag";
    private static final int BOOKING_ADULT_COUNT_MIN = 1;
    public static final String TAG = BookingGuestsPickerFragment.class.getSimpleName();
    @BindView
    FixedActionFooter continueButton;
    @BindView
    GuestsPickerView guestsPickerView;
    private final OnValueChangeListener listener = new OnValueChangeListener() {
        public OnValueChangedListener getAdultChangedListener() {
            return BookingGuestsPickerFragment.this.getChangedListener(C2465P4FlowGuestSheetSection.Adults);
        }

        public OnValueChangedListener getChildChangedListener() {
            return BookingGuestsPickerFragment.this.getChangedListener(C2465P4FlowGuestSheetSection.Children);
        }

        public OnValueChangedListener getInfantChangedListener() {
            return BookingGuestsPickerFragment.this.getChangedListener(C2465P4FlowGuestSheetSection.Infants);
        }

        public OnCheckedChangeListener getPetStatusChangedListener() {
            return BookingGuestsPickerFragment$1$$Lambda$1.lambdaFactory$(this);
        }

        static /* synthetic */ void lambda$getPetStatusChangedListener$0(C55591 r5, SwitchRowInterface switchRowInterface, boolean isChecked) {
            BookingController controller = BookingGuestsPickerFragment.this.getController();
            controller.getBookingActivityFacade().getLogger().guestSheetTogglePets(controller.getReservationDetails(), controller.getReservation() == null ? false : controller.getReservation().isInstantBookable(), isChecked ? C2759ToggleMethod.Toggle : C2759ToggleMethod.Untoggle);
        }
    };
    @BindView
    DocumentMarquee marquee;
    @BindView
    BookingNavigationView navView;
    @BindView
    AirToolbar toolbar;

    public static class BookingGuestsPickerFragmentBuilder {
        private final GuestDetails guestDetails;
        private Listing listing;
        private boolean showBlockInstantBookWarning = false;
        private final String sourceTag;

        public BookingGuestsPickerFragmentBuilder(GuestDetails guestDetails2, String sourceTag2) {
            this.guestDetails = guestDetails2;
            this.sourceTag = sourceTag2;
        }

        public BookingGuestsPickerFragmentBuilder setListing(Listing listing2) {
            this.listing = listing2;
            return this;
        }

        public BookingGuestsPickerFragmentBuilder setShowBlockInstantBookWarning(boolean showBlockInstantBookWarning2) {
            this.showBlockInstantBookWarning = showBlockInstantBookWarning2;
            return this;
        }

        public BookingGuestsPickerFragment build() {
            return (BookingGuestsPickerFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new BookingGuestsPickerFragment()).putString(BookingGuestsPickerFragment.ARG_SOURCE_TAG, this.sourceTag)).putParcelable(BookingGuestsPickerFragment.ARG_GUEST_DATA, this.guestDetails)).putParcelable(BookingGuestsPickerFragment.ARG_LISTING, this.listing)).putBoolean(BookingGuestsPickerFragment.ARG_SHOW_BLOCK_IB_WARNING, this.showBlockInstantBookWarning)).build();
        }
    }

    /* access modifiers changed from: private */
    public OnValueChangedListener getChangedListener(C2465P4FlowGuestSheetSection section) {
        BookingController controller = getController();
        return BookingGuestsPickerFragment$$Lambda$1.lambdaFactory$(controller.getBookingActivityFacade().getLogger(), controller, section);
    }

    static /* synthetic */ void lambda$getChangedListener$0(BookingJitneyLogger logger, BookingController controller, C2465P4FlowGuestSheetSection section, int oldValue, int newValue) {
        logger.guestSheetSelectGuests(controller.getReservationDetails(), controller.getReservation() == null ? false : controller.getReservation().isInstantBookable(), section, newValue > oldValue ? C2464P4FlowGuestSheetMethod.Increase : C2464P4FlowGuestSheetMethod.Decrease);
    }

    public static BookingGuestsPickerFragment newInstance() {
        return new BookingGuestsPickerFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.reservation == null && getParentFragment() == null) {
            createReservation();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0704R.layout.fragment_booking_guests_picker, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (getParentFragment() != null) {
            this.toolbar.setNavigationIcon(2);
            this.continueButton.setVisibility(0);
            this.navView.setVisibility(8);
        } else {
            this.toolbar.setNavigationIcon(1);
            this.continueButton.setVisibility(8);
            this.navView.setVisibility(0);
            setUpNavButton(this.navView, C0704R.string.next);
            this.navView.setButtonOnClickListener(BookingGuestsPickerFragment$$Lambda$2.lambdaFactory$(this));
        }
        this.guestsPickerView.setGuestData((GuestDetails) getArguments().getParcelable(ARG_GUEST_DATA));
        this.guestsPickerView.setShowBlockInstantBookWarning(getArguments().getBoolean(ARG_SHOW_BLOCK_IB_WARNING, false));
        Listing listing = (Listing) getArguments().getParcelable(ARG_LISTING);
        if (listing != null) {
            this.guestsPickerView.setMaxGuestsCount(listing.getPersonCapacity());
            if (listing.getGuestControls() != null) {
                this.guestsPickerView.setGuestControls(listing.getGuestControls());
            }
        }
        this.marquee.setCaption((CharSequence) this.guestsPickerView.getVerboseMaxGuestsDescription());
        this.guestsPickerView.setMinNumberAdults(1);
        this.guestsPickerView.setListener(this.listener);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(BookingGuestsPickerFragment bookingGuestsPickerFragment, View v) {
        bookingGuestsPickerFragment.logNavigationClick();
        bookingGuestsPickerFragment.navView.showLoader();
        bookingGuestsPickerFragment.onGuestDetailsSaved(bookingGuestsPickerFragment.guestsPickerView.getGuestData());
    }

    @OnClick
    public void onContinueClicked() {
        this.continueButton.setButtonLoading(true);
        getParentBookingV2BaseFragment().onGuestDetailsSaved(this.guestsPickerView.getGuestData());
    }

    public void onPause() {
        this.guestsPickerView.dismissAllSnackBars();
        super.onPause();
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(true).mo11637kv(FindTweenAnalytics.GUESTS, this.guestsPickerView.getNumberAdults()).mo11640kv(FindTweenAnalytics.PETS, this.guestsPickerView.hasPets()).mo11639kv(BaseAnalytics.FROM, getArguments().getString(ARG_SOURCE_TAG));
    }

    public void onUpdateError(NetworkException e) {
        this.navView.hideLoader();
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
    }

    public void onUpdated() {
        getController().showNextStep(BookingController.getBookingStepLoader(this.navView));
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.BookingGuestSheet;
    }

    public C2467P4FlowPage getP4FlowPage() {
        return C2467P4FlowPage.GuestSheet;
    }
}
