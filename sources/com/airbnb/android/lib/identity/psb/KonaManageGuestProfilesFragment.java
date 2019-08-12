package com.airbnb.android.lib.identity.psb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.controller.BookingController.BookingActivityFacade;
import com.airbnb.android.core.FragmentLauncher;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import com.airbnb.android.core.analytics.PsbAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.booking.BookingActivity;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.fonts.CustomFontSpan;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import java.util.ArrayList;

public class KonaManageGuestProfilesFragment extends AirFragment implements FragmentLauncher, Callbacks {
    private static final String ARG_GUEST_COUNT = "arg_guest_count";
    private static final String ARG_GUEST_IDENTIFICATIONS = "arg_selected_identifications";
    private static final String ARG_IS_P4_REDESIGN = "arg_is_p4_redesign";
    private static final String ARG_P4_STEPS = "arg_p4_steps";
    private static final int REQUEST_CODE_REMOVE_IDENTITY = 1004;
    @BindView
    AirTextView footerNote;
    @State
    ArrayList<GuestIdentity> guestIdentifications;
    private GuestIdentificationAdapter identificationAdapter;
    @State
    GuestIdentity identityToRemove;
    @State
    boolean isP4Redesign;
    @BindView
    BookingNavigationView navigationView;
    @State
    String p4Steps;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    @State
    int totalGuestCount;

    public static Fragment forSelectedIdentifications(ArrayList<GuestIdentity> guestIdentifications2, int totalGuestCount2, boolean isP4Redesign2, String p4Steps2) {
        return ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new KonaManageGuestProfilesFragment()).putParcelableArrayList(ARG_GUEST_IDENTIFICATIONS, guestIdentifications2)).putInt(ARG_GUEST_COUNT, totalGuestCount2)).putBoolean(ARG_IS_P4_REDESIGN, isP4Redesign2)).putString("arg_p4_steps", p4Steps2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_manage_identifications_v2, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.totalGuestCount = getArguments().getInt(ARG_GUEST_COUNT);
            this.guestIdentifications = getArguments().getParcelableArrayList(ARG_GUEST_IDENTIFICATIONS);
            this.isP4Redesign = getArguments().getBoolean(ARG_IS_P4_REDESIGN);
            this.p4Steps = getArguments().getString("arg_p4_steps");
        }
        if (this.guestIdentifications == null) {
            this.guestIdentifications = new ArrayList<>();
        }
        this.toolbar.setNavigationIcon(this.isP4Redesign ? 1 : 2);
        this.toolbar.setTheme(3);
        setToolbar(this.toolbar);
        this.identificationAdapter = new GuestIdentificationAdapter(this.guestIdentifications, this.totalGuestCount, this, getActivity(), this.p4Steps);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.identificationAdapter);
        refreshIdentificationsViews();
        setupFooterNote();
        if (this.isP4Redesign) {
            setUpNavButton();
        }
        return view;
    }

    public Bundle getDummyArguments() {
        return ((BundleBuilder) new BundleBuilder().putParcelableArrayList(ARG_GUEST_IDENTIFICATIONS, new ArrayList())).toBundle();
    }

    private void refreshIdentificationsViews() {
        this.identificationAdapter.setIdentifications(this.guestIdentifications);
        this.recyclerView.scrollToPosition(this.identificationAdapter.getItemCount() - 1);
    }

    private void setupFooterNote() {
        String linkText = getString(C0880R.string.contact_host_terms_html_link, getString(C0880R.string.radical_transparency_learn_more));
        ClickableLinkUtils.setupClickableTextView((TextView) this.footerNote, getString(C0880R.string.p4_china_disclaimer, linkText), KonaManageGuestProfilesFragment$$Lambda$1.lambdaFactory$(this), C0880R.color.canonical_press_darken, false);
    }

    public void onAddBookerIdentificationClick() {
        logAddDeleteClick(true);
        showSelectGuestProfile(true);
    }

    private void logAddDeleteClick(boolean isAdd) {
        if (this.isP4Redesign) {
            BookingController controller = getController();
            BookingJitneyLogger bookingJitneyLogger = controller.getBookingActivityFacade().getLogger();
            ReservationDetails reservationDetails = controller.getReservationDetails();
            Reservation reservation = controller.getReservation();
            if (isAdd) {
                bookingJitneyLogger.chinaGuestAddInfo(reservationDetails, reservation.isInstantBookable());
            } else {
                bookingJitneyLogger.chinaGuestDeleteInfo(reservationDetails, reservation.isInstantBookable());
            }
        }
    }

    public void onAddIdentityClick() {
        logAddDeleteClick(true);
        showSelectGuestProfile(false);
    }

    private void showSelectGuestProfile(boolean isBookerIdentification) {
        showModal(KonaSelectGuestProfileFragment.forSelectProfiles(this.guestIdentifications, isBookerIdentification, this.isP4Redesign), C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true);
    }

    public void onIdentityClick(GuestIdentity identity, boolean isBooker) {
        this.identityToRemove = identity;
        ZenDialog.builder().withTitle(C0880R.string.remove_identification_dialog_title).withBodyText(getString(C0880R.string.remove_identification_dialog_body, identity.getGivenNames())).withDualButton(C0880R.string.cancel, 0, C0880R.string.p4_remove, 1004, (Fragment) this).create().show(getFragmentManager(), "remove_identity");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean resultOk;
        if (resultCode == -1) {
            resultOk = true;
        } else {
            resultOk = false;
        }
        switch (requestCode) {
            case 1004:
                if (resultOk) {
                    logAddDeleteClick(false);
                    Check.state(this.guestIdentifications.remove(this.identityToRemove), "can not remove unknown identity: " + this.identityToRemove.getIdentityString());
                    refreshIdentificationsViews();
                }
                this.identityToRemove = null;
                break;
        }
        if (resultOk) {
            updateReservationDetails();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateReservationDetails() {
        Activity activity = getActivity();
        if (this.isP4Redesign) {
            BookingController controller = getController();
            controller.setReservationDetails(controller.getReservationDetails().toBuilder().identifications(this.guestIdentifications).build());
            PsbAnalytics.trackManageIdentitiesDoneClick(this.guestIdentifications.size());
            return;
        }
        ((BookingActivity) activity).doneWithGuestIdentifications(this.guestIdentifications);
    }

    public void addNewGuestIdentification(GuestIdentity identification, boolean isBooker) {
        Check.notNull(identification);
        identification.setBooker(isBooker);
        this.guestIdentifications.remove(identification);
        this.guestIdentifications.add(identification);
        refreshIdentificationsViews();
        updateReservationDetails();
        getChildFragmentManager().popBackStack();
    }

    public void setUpNavButton() {
        this.navigationView.setVisibility(0);
        this.navigationView.setButtonText(C0880R.string.next);
        this.navigationView.setSeePricingDetailsText(C0880R.string.p4_see_details);
        setNavText();
        BookingController controller = getController();
        this.navigationView.setPriceDetailsOnClickListener(KonaManageGuestProfilesFragment$$Lambda$2.lambdaFactory$(controller));
        this.navigationView.setButtonOnClickListener(KonaManageGuestProfilesFragment$$Lambda$3.lambdaFactory$(this, controller));
    }

    static /* synthetic */ void lambda$setUpNavButton$3(KonaManageGuestProfilesFragment konaManageGuestProfilesFragment, BookingController controller, View v) {
        if (controller.getReservationDetails().completedGuestIdentifications()) {
            controller.getBookingActivityFacade().getLogger().clickNavigation(controller.getReservationDetails(), controller.getReservation().isInstantBookable(), C2467P4FlowPage.ChinaGuestProfiles, C2466P4FlowNavigationMethod.NextButton);
            controller.showNextStep(BookingController.getBookingStepLoader(konaManageGuestProfilesFragment.navigationView));
            return;
        }
        new SnackbarWrapper().view(konaManageGuestProfilesFragment.getView()).duration(0).action(konaManageGuestProfilesFragment.getString(C0880R.string.p4_add_guest_profile), KonaManageGuestProfilesFragment$$Lambda$4.lambdaFactory$(konaManageGuestProfilesFragment)).body(C0880R.string.p4_required_guest_identifications).buildAndShow();
    }

    private BookingController getController() {
        return ((BookingActivityFacade) getActivity()).getController();
    }

    private void setNavText() {
        BookingController controller = getController();
        ReservationDetails reservationDetails = controller.getReservationDetails();
        Price price = controller.getPrice();
        Integer stayDuration = Integer.valueOf(reservationDetails.checkIn().getDaysUntil(reservationDetails.checkOut()));
        String priceFormattedForDisplay = price.getTotal().formattedForDisplay();
        String priceDetailsText = getResources().getQuantityString(C0704R.plurals.x_nights_for_price, stayDuration.intValue(), new Object[]{priceFormattedForDisplay, stayDuration});
        int priceStartIndex = priceDetailsText.indexOf(priceFormattedForDisplay);
        SpannableStringBuilder builder = new SpannableStringBuilder().append(priceDetailsText);
        builder.setSpan(new CustomFontSpan(getContext(), Font.CircularBold), priceStartIndex, priceFormattedForDisplay.length() + priceStartIndex, 0);
        this.navigationView.setPricingDetailsText(builder);
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(this.isP4Redesign);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ChinaGuestProfiles;
    }

    public boolean onBackPressed() {
        if (getChildFragmentManager().findFragmentById(C0880R.C0882id.modal_container) == null) {
            return false;
        }
        BookingController controller = getController();
        controller.getBookingActivityFacade().getLogger().clickNavigation(controller.getReservationDetails(), controller.getReservation().isInstantBookable(), C2467P4FlowPage.ChinaGuestProfiles, C2466P4FlowNavigationMethod.BackButton);
        getChildFragmentManager().popBackStack();
        return true;
    }
}
