package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.TripsAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.ArrivalDetails;
import com.airbnb.android.core.models.C5990Guidebook;
import com.airbnb.android.core.models.HelpThread;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationAlteration;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.presenters.GuestDetailsPresenter;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ButtonBarEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingDetailsSummaryEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.utils.CalendarHelper;
import com.airbnb.android.lib.viewcomponents.viewmodels.DisplayCardEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import icepick.State;

public class ReservationObjectAdapter extends AirEpoxyAdapter {
    protected AirbnbAccountManager accountManager;
    private final StandardRowEpoxyModel_ addressRow = new StandardRowEpoxyModel_().title(C0880R.string.address).actionText(C0880R.string.directions);
    private final StandardRowEpoxyModel_ alterationPendingRow = new StandardRowEpoxyModel_().title(C0880R.string.alteration_request_ro_header_title);
    private final ButtonBarEpoxyModel_ buttonBar = new ButtonBarEpoxyModel_();
    private final StandardRowEpoxyModel_ cancelRow = new StandardRowEpoxyModel_().title(C0880R.string.cancel_reservation).actionText(C0880R.string.cancel);
    private final StandardRowEpoxyModel_ cancellationPolicyRow = new StandardRowEpoxyModel_().title(C0880R.string.cancellation_policy);
    private final StandardRowEpoxyModel_ checkInRow = new StandardRowEpoxyModel_().title(C0880R.string.check_in);
    private final StandardRowEpoxyModel_ checkOutRow = new StandardRowEpoxyModel_().title(C0880R.string.check_out);
    private final Context context;
    protected CurrencyFormatter currencyFormatter;
    private final StandardRowEpoxyModel_ emailRow = new StandardRowEpoxyModel_().title(C0880R.string.email_host);
    private final StandardRowEpoxyModel_ extraServicesRow = new StandardRowEpoxyModel_().title(C0880R.string.paid_amenities_itinerary_guest_entry_text);
    private final StandardRowEpoxyModel_ guestsRow = new StandardRowEpoxyModel_().title(C0880R.string.guests);
    private final StandardRowEpoxyModel_ guidebookRow = new StandardRowEpoxyModel_().title(C0880R.string.guidebook_view_guidebook).actionText(C0880R.string.see);
    private final StandardRowEpoxyModel_ helpRow = new StandardRowEpoxyModel_().title(C0880R.string.help_center).actionText(C0880R.string.visit);
    private final LinkActionRowEpoxyModel_ helpThreadRow = new LinkActionRowEpoxyModel_().textRes(C0880R.string.help_thread_view_messages);
    private final ListingDetailsSummaryEpoxyModel_ hostRow = new ListingDetailsSummaryEpoxyModel_();
    private final StandardRowEpoxyModel_ houseManualRow = new StandardRowEpoxyModel_().title(C0880R.string.house_manual).actionText(C0880R.string.read);
    private final Listener listener;
    private final DisplayCardEpoxyModel_ listingCard = new DisplayCardEpoxyModel_();
    private final LoadingRowEpoxyModel_ loaderRow = new LoadingRowEpoxyModel_();
    private final DocumentMarqueeEpoxyModel_ marquee = new DocumentMarqueeEpoxyModel_();
    private final StandardRowEpoxyModel_ priceRow = new StandardRowEpoxyModel_().title(C0880R.string.reservation_cost);
    private final StandardRowEpoxyModel_ requestAlterationRow = new StandardRowEpoxyModel_().title(C0880R.string.alter_reservation).actionText(C0880R.string.change);
    private final StandardRowEpoxyModel_ retractRow = new StandardRowEpoxyModel_().title(C0880R.string.cancel_request).actionText(C0880R.string.cancel);
    private final StandardRowEpoxyModel_ shareTripRow = new StandardRowEpoxyModel_().title(C0880R.string.share_your_trip_itinerary_row_title).actionText(C0880R.string.share);
    @State
    protected boolean shouldLogDetails = true;
    private final StandardRowEpoxyModel_ updatePaymentRow = new StandardRowEpoxyModel_().title(C0880R.string.payment_declined).subtitle(C0880R.string.update_payment_information).actionText(C0880R.string.update);
    private final StandardRowEpoxyModel_ wifiRow = new StandardRowEpoxyModel_().title(C0880R.string.wifi).actionText(C0880R.string.see);

    public interface Listener {
        void goToAlterations(Reservation reservation, boolean z);

        void goToCallHost();

        void goToCancel(Reservation reservation);

        void goToCancellationPolicy(String str, Reservation reservation);

        void goToDirections(Reservation reservation);

        void goToExtraServices(Reservation reservation);

        void goToGuidebook(C5990Guidebook guidebook);

        void goToHelpCenter();

        void goToHelpThread(HelpThread helpThread);

        void goToHost(User user);

        void goToHouseManual(String str);

        void goToInviteGuests(Reservation reservation);

        void goToListing(Listing listing);

        void goToLocalAttractions(Listing listing);

        void goToMessageThread();

        void goToPriceBreakdown(Reservation reservation);

        void goToRetract(Reservation reservation);

        void goToRules(Listing listing, Reservation reservation);

        void goToShareTrip(Reservation reservation);

        void goToUpdatePayment(Reservation reservation);

        void goToWifi(Reservation reservation, Listing listing);
    }

    public ReservationObjectAdapter(Context context2, Listener listener2, Bundle savedInstanceState) {
        this.context = context2;
        this.listener = listener2;
        ((AirbnbGraph) AirbnbApplication.instance(context2).component()).inject(this);
        onRestoreInstanceState(savedInstanceState);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marquee, this.loaderRow, this.updatePaymentRow, this.alterationPendingRow, this.hostRow, this.helpThreadRow, this.buttonBar, this.checkInRow, this.checkOutRow, this.extraServicesRow, this.guestsRow, this.listingCard, this.addressRow, this.houseManualRow, this.priceRow, this.requestAlterationRow, this.emailRow, this.guidebookRow, this.wifiRow, this.cancellationPolicyRow, this.cancelRow, this.retractRow, this.helpRow, this.shareTripRow});
    }

    public void refreshReservation(TripInformationProvider provider, boolean isLoading, Boolean hasLocalAttractions, HelpThread helpThread) {
        updateModels(provider, isLoading, hasLocalAttractions, helpThread);
        notifyDataSetChanged();
    }

    private void updateModels(TripInformationProvider provider, boolean isLoading, Boolean hasLocalAttractions, HelpThread helpThread) {
        hideAllAfterModel(this.marquee);
        if (isLoading) {
            this.loaderRow.show();
        } else if (provider != null) {
            logRequiredDetails(provider);
            setMarquee(provider);
            setHostRow(provider);
            setHelpThreadRow(helpThread);
            setButtonBar(provider);
            setCheckInOutRows(provider);
            setGuestsRow(provider);
            setListingCard(provider);
            setPriceRow(provider);
            setCancellationPolicyRow(provider);
            setGuidebookRow(provider, hasLocalAttractions);
            setHelpRow();
            if (provider.hasReservation()) {
                Reservation reservation = provider.getReservation();
                setUpdatePaymentRow(reservation);
                setAlterationsRows(reservation);
                setCancelRow(reservation);
                if (reservation.isAccepted()) {
                    Listing listing = reservation.getListing();
                    setAddressRow(reservation);
                    setEmailRow(reservation.getPrimaryHost());
                    setHouseManualRow(listing);
                    setWifiRow(reservation, listing);
                    setShareTripRow(reservation);
                    if (!Trebuchet.launch(TrebuchetKeys.PAID_AMENITIES_GUEST, false)) {
                        return;
                    }
                    if (reservation.getListing().hasPaidAmenities() || reservation.hasPaidAmenityOrders()) {
                        setExtraServiceRow(reservation);
                    }
                }
            }
        }
    }

    private void logRequiredDetails(TripInformationProvider provider) {
        String str = null;
        if (this.shouldLogDetails) {
            Reservation reservation = provider.hasReservation() ? provider.getReservation() : null;
            Listing listing = provider.getListing();
            long listingId = listing != null ? listing.getId() : -1;
            ReservationStatus status = provider.getStatus();
            AirDate startDate = provider.getStartDate();
            AirDate endDate = provider.getEndDate();
            if (listing != null) {
                str = provider.getCancellationPolicyKey();
            }
            TripsAnalytics.trackReservationView(reservation, status, listingId, startDate, endDate, str, provider.getGuestCount());
            this.shouldLogDetails = false;
        }
    }

    private void setMarquee(TripInformationProvider provider) {
        int nightsBooked = provider.getReservedNightsCount();
        String nightsInCityString = this.context.getResources().getQuantityString(C0880R.plurals.x_nights_in_city, nightsBooked, new Object[]{Integer.valueOf(nightsBooked), provider.getListing().getLocalizedCity()});
        String statusString = ReservationStatusDisplay.forGuest(provider).getString(this.context);
        if (provider.hasReservation()) {
            statusString = statusString.concat("\n" + provider.getReservation().getConfirmationCode());
        }
        this.marquee.titleText((CharSequence) nightsInCityString).captionText((CharSequence) statusString);
    }

    private void setHostRow(TripInformationProvider provider) {
        this.hostRow.listing(provider.getListing()).businessReady(provider.getListing().isBusinessTravelReady()).clickListener(ReservationObjectAdapter$$Lambda$1.lambdaFactory$(this, provider)).show();
    }

    private void setHelpThreadRow(HelpThread helpThread) {
        this.helpThreadRow.clickListener(ReservationObjectAdapter$$Lambda$2.lambdaFactory$(this, helpThread)).show(helpThread != null);
    }

    private void setButtonBar(TripInformationProvider provider) {
        this.buttonBar.clearButtons();
        if (!provider.hasReservation() || !provider.getReservation().isSharedItinerary()) {
            this.buttonBar.addButton(C0880R.string.chat, C0880R.C0881drawable.icon_line_message, ReservationObjectAdapter$$Lambda$3.lambdaFactory$(this));
        }
        if (provider.hasReservation() && provider.getReservation().isAccepted()) {
            this.buttonBar.addButton(C0880R.string.call, C0880R.C0881drawable.icon_line_phone, ReservationObjectAdapter$$Lambda$4.lambdaFactory$(this));
        }
        if (provider.getListing().hasHouseRules()) {
            this.buttonBar.addButton(C0880R.string.rules, C0880R.C0881drawable.icon_line_book, ReservationObjectAdapter$$Lambda$5.lambdaFactory$(this, provider));
        }
        this.buttonBar.show();
    }

    static /* synthetic */ void lambda$setButtonBar$4(ReservationObjectAdapter reservationObjectAdapter, TripInformationProvider provider, View v) {
        reservationObjectAdapter.listener.goToRules(provider.getListing(), provider.hasReservation() ? provider.getReservation() : null);
    }

    private void setCheckInOutRows(TripInformationProvider provider) {
        boolean startsThisYear = true;
        String checkInSubtitle = null;
        String checkOutSubtitle = null;
        if (provider.hasReservation()) {
            ArrivalDetails details = provider.getReservation().getArrivalDetails();
            if (!details.isCheckInOptionValid(details.getGuestCheckinTimeFrom()) || !details.isCheckInOptionValid(details.getGuestCheckinTimeTo())) {
                Integer checkIn = provider.getListing().getCheckInTime();
                if (checkIn != null) {
                    checkInSubtitle = checkIn == null ? this.context.getString(C0880R.string.flexible_time) : CalendarHelper.formatHour(checkIn.intValue());
                }
            } else {
                checkInSubtitle = this.context.getString(C0880R.string.guests_check_in_window, new Object[]{details.getGuestCheckinTimeFrom().getLocalizedHourString(), details.getGuestCheckinTimeTo().getLocalizedHourString()});
            }
            Integer checkOut = provider.getListing().getCheckOutTime();
            if (checkOut != null) {
                checkOutSubtitle = checkOut == null ? this.context.getString(C0880R.string.flexible_time) : CalendarHelper.formatHour(checkOut.intValue());
            }
        }
        if (provider.getStartDate().getYear() != AirDate.today().getYear()) {
            startsThisYear = false;
        }
        String dateFormat = this.context.getString(startsThisYear ? C0880R.string.md_with_abbr_day_name : C0880R.string.mdy_with_abbr_day_name);
        this.checkInRow.placeholderText((CharSequence) provider.getStartDate().formatDate(dateFormat)).subtitle((CharSequence) checkInSubtitle).show();
        this.checkOutRow.placeholderText((CharSequence) provider.getEndDate().formatDate(dateFormat)).subtitle((CharSequence) checkOutSubtitle).show();
    }

    private void setExtraServiceRow(Reservation reservation) {
        this.extraServicesRow.clickListener(ReservationObjectAdapter$$Lambda$6.lambdaFactory$(this, reservation)).show();
    }

    private void setGuestsRow(TripInformationProvider provider) {
        if (!provider.hasReservation() || provider.getReservation().isSharedItinerary()) {
            this.guestsRow.actionText((CharSequence) null).clickListener((OnClickListener) null);
        } else {
            this.guestsRow.actionText(C0880R.string.share_itinerary).clickListener(ReservationObjectAdapter$$Lambda$7.lambdaFactory$(this, provider));
        }
        this.guestsRow.subtitle((CharSequence) GuestDetailsPresenter.formatGuestCountLabel(this.context, provider.getGuestDetails(), provider.getGuestCount())).show();
    }

    private void setListingCard(TripInformationProvider provider) {
        Listing listing = provider.getListing();
        this.listingCard.imageUrl(listing.getPictureUrl()).text(listing.getName()).onClickListener(ReservationObjectAdapter$$Lambda$8.lambdaFactory$(this, listing)).show();
    }

    private void setPriceRow(TripInformationProvider provider) {
        this.priceRow.actionText((CharSequence) provider.getGuestTotalPriceFormatted(this.currencyFormatter)).clickListener(provider.hasReservation() ? ReservationObjectAdapter$$Lambda$9.lambdaFactory$(this, provider) : null).show();
    }

    private void setHelpRow() {
        this.helpRow.clickListener(ReservationObjectAdapter$$Lambda$10.lambdaFactory$(this)).show();
    }

    private void setShareTripRow(Reservation reservation) {
        if (!reservation.isUpcoming() && ChinaUtils.isWechatTripSharingEnabled(this.context)) {
            this.shareTripRow.clickListener(ReservationObjectAdapter$$Lambda$11.lambdaFactory$(this, reservation)).show();
        }
    }

    private void setGuidebookRow(TripInformationProvider provider, Boolean hasLocalAttractions) {
        C5990Guidebook guidebook = provider.getListing().getHostGuidebook();
        if (guidebook != null) {
            this.guidebookRow.clickListener(ReservationObjectAdapter$$Lambda$12.lambdaFactory$(this, guidebook)).show();
        } else if (hasLocalAttractions != null && hasLocalAttractions.booleanValue()) {
            this.guidebookRow.clickListener(ReservationObjectAdapter$$Lambda$13.lambdaFactory$(this, provider)).show();
        }
    }

    private void setCancellationPolicyRow(TripInformationProvider provider) {
        if (provider.hasReservation()) {
            this.cancellationPolicyRow.clickListener(ReservationObjectAdapter$$Lambda$14.lambdaFactory$(this, provider.getReservation()));
        } else {
            this.cancellationPolicyRow.clickListener(ReservationObjectAdapter$$Lambda$15.lambdaFactory$(this, provider));
        }
        this.cancellationPolicyRow.actionText((CharSequence) provider.getCancellationPolicy()).show();
    }

    private void setUpdatePaymentRow(Reservation reservation) {
        this.updatePaymentRow.clickListener(ReservationObjectAdapter$$Lambda$16.lambdaFactory$(this, reservation)).show(reservation.isAwaitingPayment());
    }

    private void setAlterationsRows(Reservation reservation) {
        String alterationsMessage;
        ReservationAlteration existingAlteration = reservation.getFirstPendingAlteration();
        if (existingAlteration == null && reservation.isAlterable()) {
            this.requestAlterationRow.clickListener(ReservationObjectAdapter$$Lambda$17.lambdaFactory$(this, reservation)).show();
        } else if (existingAlteration != null && reservation.isAlterationViewable()) {
            if (existingAlteration.isInitiatedByGuest()) {
                alterationsMessage = this.context.getString(C0880R.string.alteration_request_ro_header_message_guest);
            } else {
                alterationsMessage = this.context.getString(C0880R.string.alteration_request_ro_header_message_respond, new Object[]{reservation.getPrimaryHost().getFirstName()});
            }
            this.alterationPendingRow.subtitle((CharSequence) alterationsMessage);
            this.alterationPendingRow.actionText(C0880R.string.view).clickListener(ReservationObjectAdapter$$Lambda$18.lambdaFactory$(this, reservation));
            this.alterationPendingRow.show();
        }
    }

    private void setCancelRow(Reservation reservation) {
        this.cancelRow.clickListener(ReservationObjectAdapter$$Lambda$19.lambdaFactory$(this, reservation));
        this.retractRow.clickListener(ReservationObjectAdapter$$Lambda$20.lambdaFactory$(this, reservation));
        if (!reservation.isCancelableByUser(this.accountManager.getCurrentUser())) {
            this.cancelRow.hide();
            this.retractRow.hide();
        } else if (reservation.isPending() || reservation.isAwaitingPayment()) {
            this.cancelRow.hide();
            this.retractRow.show();
        } else {
            this.cancelRow.show();
            this.retractRow.hide();
        }
    }

    private void setAddressRow(Reservation reservation) {
        this.addressRow.subtitle((CharSequence) reservation.getListing().getAddress()).clickListener(ReservationObjectAdapter$$Lambda$21.lambdaFactory$(this, reservation)).longClickListener(ReservationObjectAdapter$$Lambda$22.lambdaFactory$(this, reservation)).show();
    }

    private void setHouseManualRow(Listing listing) {
        this.houseManualRow.clickListener(ReservationObjectAdapter$$Lambda$23.lambdaFactory$(this, listing)).show(!TextUtils.isEmpty(listing.getHouseManual()));
    }

    private void setWifiRow(Reservation reservation, Listing listing) {
        this.wifiRow.clickListener(ReservationObjectAdapter$$Lambda$24.lambdaFactory$(this, reservation, listing)).show(listing.getWirelessInfo() != null);
    }

    private void setEmailRow(User primaryHost) {
        this.emailRow.clickListener(ReservationObjectAdapter$$Lambda$25.lambdaFactory$(this, primaryHost)).longClickListener(ReservationObjectAdapter$$Lambda$26.lambdaFactory$(this, primaryHost)).show(primaryHost != null && !TextUtils.isEmpty(primaryHost.getEmailAddress()));
    }

    /* access modifiers changed from: private */
    public boolean copyToClipboard(String text) {
        MiscUtils.copyToClipboard(this.context, text);
        return true;
    }
}
