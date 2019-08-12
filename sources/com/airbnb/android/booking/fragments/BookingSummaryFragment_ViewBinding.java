package com.airbnb.android.booking.fragments;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.MicroRow;
import com.airbnb.p027n2.components.PriceSummary;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.RangeDisplay;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;

public class BookingSummaryFragment_ViewBinding implements Unbinder {
    private BookingSummaryFragment target;
    private View view2131755485;
    private View view2131755486;
    private View view2131755487;
    private View view2131755488;
    private View view2131755491;
    private View view2131755493;
    private View view2131755494;
    private View view2131755495;
    private View view2131755496;
    private View view2131755497;
    private View view2131755498;
    private View view2131755499;
    private View view2131755500;
    private View view2131755503;

    public BookingSummaryFragment_ViewBinding(final BookingSummaryFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C0704R.C0706id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.loadingOverlay = Utils.findRequiredView(source, C0704R.C0706id.loading_overlay, "field 'loadingOverlay'");
        target2.contentContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0704R.C0706id.content_container, "field 'contentContainer'", LinearLayout.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0704R.C0706id.reservation_details_marquee, "field 'marquee'", DocumentMarquee.class);
        target2.listingNameMicroRow = (MicroRow) Utils.findRequiredViewAsType(source, C0704R.C0706id.listing_name_micro_row, "field 'listingNameMicroRow'", MicroRow.class);
        target2.listingDetailsSummary = (UserDetailsActionRow) Utils.findRequiredViewAsType(source, C0704R.C0706id.listing_details_summary, "field 'listingDetailsSummary'", UserDetailsActionRow.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.date_range_display, "field 'dateRangeDisplay' and method 'clickDateSelection'");
        target2.dateRangeDisplay = (RangeDisplay) Utils.castView(view, C0704R.C0706id.date_range_display, "field 'dateRangeDisplay'", RangeDisplay.class);
        this.view2131755485 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickDateSelection();
            }
        });
        View view2 = Utils.findRequiredView(source, C0704R.C0706id.nights_standard_row, "field 'nightsStandardRow' and method 'clickNightsRow'");
        target2.nightsStandardRow = (StandardRow) Utils.castView(view2, C0704R.C0706id.nights_standard_row, "field 'nightsStandardRow'", StandardRow.class);
        this.view2131755486 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickNightsRow();
            }
        });
        View view3 = Utils.findRequiredView(source, C0704R.C0706id.guests_standard_row, "field 'guestsStandardRow' and method 'clickGuestDetails'");
        target2.guestsStandardRow = (StandardRow) Utils.castView(view3, C0704R.C0706id.guests_standard_row, "field 'guestsStandardRow'", StandardRow.class);
        this.view2131755487 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickGuestDetails();
            }
        });
        View view4 = Utils.findRequiredView(source, C0704R.C0706id.price_summary, "field 'priceSummary' and method 'clickPriceBreakdown'");
        target2.priceSummary = (PriceSummary) Utils.castView(view4, C0704R.C0706id.price_summary, "field 'priceSummary'", PriceSummary.class);
        this.view2131755488 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickPriceBreakdown();
            }
        });
        target2.fullyRefundableRow = (StandardRow) Utils.findRequiredViewAsType(source, C0704R.C0706id.fully_refundable_row, "field 'fullyRefundableRow'", StandardRow.class);
        target2.fxTextRow = (TextRow) Utils.findRequiredViewAsType(source, C0704R.C0706id.fx_text_row, "field 'fxTextRow'", TextRow.class);
        View view5 = Utils.findRequiredView(source, C0704R.C0706id.coupon_standard_row, "field 'couponStandardRow' and method 'clickCouponCode'");
        target2.couponStandardRow = (StandardRow) Utils.castView(view5, C0704R.C0706id.coupon_standard_row, "field 'couponStandardRow'", StandardRow.class);
        this.view2131755491 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickCouponCode();
            }
        });
        View view6 = Utils.findRequiredView(source, C0704R.C0706id.payment_standard_row, "field 'paymentStandardRow' and method 'clickPaymentOptions'");
        target2.paymentStandardRow = (StandardRow) Utils.castView(view6, C0704R.C0706id.payment_standard_row, "field 'paymentStandardRow'", StandardRow.class);
        this.view2131755494 = view6;
        view6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickPaymentOptions();
            }
        });
        View view7 = Utils.findRequiredView(source, C0704R.C0706id.arrival_details_standard_row, "field 'arrivalDetailsStandardRow' and method 'clickArrivalDetails'");
        target2.arrivalDetailsStandardRow = (StandardRow) Utils.castView(view7, C0704R.C0706id.arrival_details_standard_row, "field 'arrivalDetailsStandardRow'", StandardRow.class);
        this.view2131755495 = view7;
        view7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickArrivalDetails();
            }
        });
        View view8 = Utils.findRequiredView(source, C0704R.C0706id.host_message_standard_row, "field 'hostMessageStandardRow' and method 'clickTripPurpose'");
        target2.hostMessageStandardRow = (StandardRow) Utils.castView(view8, C0704R.C0706id.host_message_standard_row, "field 'hostMessageStandardRow'", StandardRow.class);
        this.view2131755496 = view8;
        view8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickTripPurpose();
            }
        });
        View view9 = Utils.findRequiredView(source, C0704R.C0706id.house_rules_standard_row, "field 'houseRulesStandardRow' and method 'clickHouseRules'");
        target2.houseRulesStandardRow = (StandardRow) Utils.castView(view9, C0704R.C0706id.house_rules_standard_row, "field 'houseRulesStandardRow'", StandardRow.class);
        this.view2131755497 = view9;
        view9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickHouseRules();
            }
        });
        View view10 = Utils.findRequiredView(source, C0704R.C0706id.phone_number_standard_row, "field 'phoneNumberStandardRow' and method 'clickPhoneNumber'");
        target2.phoneNumberStandardRow = (StandardRow) Utils.castView(view10, C0704R.C0706id.phone_number_standard_row, "field 'phoneNumberStandardRow'", StandardRow.class);
        this.view2131755499 = view10;
        view10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickPhoneNumber();
            }
        });
        View view11 = Utils.findRequiredView(source, C0704R.C0706id.email_address_standard_row, "field 'emailAddressStandardRow' and method 'clickEmailAddress'");
        target2.emailAddressStandardRow = (StandardRow) Utils.castView(view11, C0704R.C0706id.email_address_standard_row, "field 'emailAddressStandardRow'", StandardRow.class);
        this.view2131755500 = view11;
        view11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickEmailAddress();
            }
        });
        View view12 = Utils.findRequiredView(source, C0704R.C0706id.government_id_standard_row, "field 'governmentIdStandardRow' and method 'clickGovernmentId'");
        target2.governmentIdStandardRow = (StandardRow) Utils.castView(view12, C0704R.C0706id.government_id_standard_row, "field 'governmentIdStandardRow'", StandardRow.class);
        this.view2131755493 = view12;
        view12.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickGovernmentId();
            }
        });
        target2.termsTextRow = (TextRow) Utils.findRequiredViewAsType(source, C0704R.C0706id.terms_text_row, "field 'termsTextRow'", TextRow.class);
        View view13 = Utils.findRequiredView(source, C0704R.C0706id.confirm_and_pay_primary_button, "field 'confirmAndPayPrimaryButton' and method 'clickSubmitButton'");
        target2.confirmAndPayPrimaryButton = (PrimaryButton) Utils.castView(view13, C0704R.C0706id.confirm_and_pay_primary_button, "field 'confirmAndPayPrimaryButton'", PrimaryButton.class);
        this.view2131755503 = view13;
        view13.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickSubmitButton();
            }
        });
        View view14 = Utils.findRequiredView(source, C0704R.C0706id.guest_identifications_standard_row, "field 'guestIdentificationsRow' and method 'clickGuestIdentifications'");
        target2.guestIdentificationsRow = (StandardRow) Utils.castView(view14, C0704R.C0706id.guest_identifications_standard_row, "field 'guestIdentificationsRow'", StandardRow.class);
        this.view2131755498 = view14;
        view14.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickGuestIdentifications();
            }
        });
        target2.stepsToBookContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0704R.C0706id.steps_to_book_container, "field 'stepsToBookContainer'", LinearLayout.class);
        target2.termsLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C0704R.C0706id.p4_terms_and_conditions_layout, "field 'termsLayout'", CoordinatorLayout.class);
    }

    public void unbind() {
        BookingSummaryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.scrollView = null;
        target2.loadingOverlay = null;
        target2.contentContainer = null;
        target2.marquee = null;
        target2.listingNameMicroRow = null;
        target2.listingDetailsSummary = null;
        target2.dateRangeDisplay = null;
        target2.nightsStandardRow = null;
        target2.guestsStandardRow = null;
        target2.priceSummary = null;
        target2.fullyRefundableRow = null;
        target2.fxTextRow = null;
        target2.couponStandardRow = null;
        target2.paymentStandardRow = null;
        target2.arrivalDetailsStandardRow = null;
        target2.hostMessageStandardRow = null;
        target2.houseRulesStandardRow = null;
        target2.phoneNumberStandardRow = null;
        target2.emailAddressStandardRow = null;
        target2.governmentIdStandardRow = null;
        target2.termsTextRow = null;
        target2.confirmAndPayPrimaryButton = null;
        target2.guestIdentificationsRow = null;
        target2.stepsToBookContainer = null;
        target2.termsLayout = null;
        this.view2131755485.setOnClickListener(null);
        this.view2131755485 = null;
        this.view2131755486.setOnClickListener(null);
        this.view2131755486 = null;
        this.view2131755487.setOnClickListener(null);
        this.view2131755487 = null;
        this.view2131755488.setOnClickListener(null);
        this.view2131755488 = null;
        this.view2131755491.setOnClickListener(null);
        this.view2131755491 = null;
        this.view2131755494.setOnClickListener(null);
        this.view2131755494 = null;
        this.view2131755495.setOnClickListener(null);
        this.view2131755495 = null;
        this.view2131755496.setOnClickListener(null);
        this.view2131755496 = null;
        this.view2131755497.setOnClickListener(null);
        this.view2131755497 = null;
        this.view2131755499.setOnClickListener(null);
        this.view2131755499 = null;
        this.view2131755500.setOnClickListener(null);
        this.view2131755500 = null;
        this.view2131755493.setOnClickListener(null);
        this.view2131755493 = null;
        this.view2131755503.setOnClickListener(null);
        this.view2131755503 = null;
        this.view2131755498.setOnClickListener(null);
        this.view2131755498 = null;
    }
}
