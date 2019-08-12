package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.CheckinTimeSelectionOptions;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationAlteration;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.ReviewRatingsAsGuest;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.presenters.GuestDetailsPresenter;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ButtonBarEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ImpactMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.hostcalendar.viewmodels.HostCalendarReservationEpoxyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.utils.CalendarHelper;
import com.airbnb.android.lib.viewcomponents.viewmodels.NoProfilePhotoGuestDetailsSummaryEpoxyModel_;
import com.airbnb.android.lib.viewcomponents.viewmodels.StarRatingSummaryEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class HostReservationObjectAdapter extends AirEpoxyAdapter {
    protected AirbnbAccountManager accountManager;
    private final StandardRowEpoxyModel_ alterationPendingRow = new StandardRowEpoxyModel_().title(C0880R.string.alteration_request_ro_header_title);
    private final StandardRowEpoxyModel_ alterationRow = new StandardRowEpoxyModel_().title(C0880R.string.alter_reservation);
    private final ButtonBarEpoxyModel_ buttonBar = new ButtonBarEpoxyModel_();
    CalendarStore calendarStore;
    private final StandardRowEpoxyModel_ cancellationRow = new StandardRowEpoxyModel_().title(C0880R.string.cancel_reservation);
    private final StandardRowEpoxyModel_ checkInRow = new StandardRowEpoxyModel_().title(C0880R.string.check_in);
    private final StandardRowEpoxyModel_ checkOutRow = new StandardRowEpoxyModel_().title(C0880R.string.check_out);
    private final Context context;
    protected CurrencyFormatter currencyFormatter;
    private final StandardRowEpoxyModel_ emailRow = new StandardRowEpoxyModel_().title(C0880R.string.ro_email_guest);
    private final StandardRowEpoxyModel_ extraServicesRow = new StandardRowEpoxyModel_();
    private final StandardRowEpoxyModel_ firstMessageRow = new StandardRowEpoxyModel_();
    private final StandardRowEpoxyModel_ guestDetailsRow = new StandardRowEpoxyModel_().title(C0880R.string.guests);
    private final NoProfilePhotoGuestDetailsSummaryEpoxyModel_ guestDetailsSummary = new NoProfilePhotoGuestDetailsSummaryEpoxyModel_();
    private final LinkActionRowEpoxyModel_ guestPreviousReviewsRow = new LinkActionRowEpoxyModel_();
    private final LinkActionRowEpoxyModel_ guestRatingsIbUpsellRow = new LinkActionRowEpoxyModel_().textRes(C0880R.string.guest_ratings_help_cta);
    private final StandardRowEpoxyModel_ guestRatingsInfoRow = new StandardRowEpoxyModel_().title(C0880R.string.guest_ratings_info_text);
    private final StarRatingSummaryEpoxyModel_ guestRatingsRow = new StarRatingSummaryEpoxyModel_();
    private final StandardRowEpoxyModel_ guestReviewRow = new StandardRowEpoxyModel_().actionText(C0880R.string.view);
    private final StandardRowEpoxyModel_ helpRow = new StandardRowEpoxyModel_().title(C0880R.string.ro_help_action);
    private final StandardRowEpoxyModel_ hostReviewRow = new StandardRowEpoxyModel_().actionText(C0880R.string.view);
    private final HostCalendarReservationEpoxyModel_ inlineCalendarRow = new HostCalendarReservationEpoxyModel_();
    private final LinkActionRowEpoxyModel_ linkCalendarRow = new LinkActionRowEpoxyModel_().textRes(C0880R.string.ro_view_full_calendar);
    private final Listener listener;
    private final ImpactMarqueeEpoxyModel_ marquee = new ImpactMarqueeEpoxyModel_();
    private final StandardRowEpoxyModel_ payoutRow = new StandardRowEpoxyModel_().title(C0880R.string.total_payout);
    private final StandardRowEpoxyModel_ removePreapprovalRow = new StandardRowEpoxyModel_();
    private final StandardRowEpoxyModel_ resolutionCenterRow = new StandardRowEpoxyModel_();
    SharedPrefsHelper sharedPrefsHelper;
    private final StandardRowEpoxyModel_ specialOfferRow = new StandardRowEpoxyModel_().title(C0880R.string.send_offer);
    private final StandardRowEpoxyModel_ specialStatusRow = new StandardRowEpoxyModel_();
    private final StandardRowEpoxyModel_ writeReviewRow = new StandardRowEpoxyModel_().title(C0880R.string.ro_pending_review_title);

    public interface Listener {
        void goToAcceptFlow();

        void goToAcceptOrDeclineFlow();

        void goToAddExtraServices(long j);

        void goToAlterationFlow();

        void goToCalendar();

        void goToCallGuest();

        void goToCancellationFlow();

        void goToDeclineInquiryFlow();

        void goToDeclineRequestFlow();

        void goToEmailGuest();

        void goToExportCalendar();

        void goToGuestProfile();

        void goToGuestRatingsHelpFlow();

        void goToGuestRatingsModal(User user);

        void goToGuestReviewsModal(ReviewsMode reviewsMode);

        void goToHelp();

        void goToMessageThread();

        void goToPayoutBreakdown(Price price, Listing listing);

        void goToPreapproveFlow();

        void goToRemovePreapproval();

        void goToResolutionCenter(String str);

        void goToReview(Review review);

        void goToSpecialOfferFlow();

        void goToViewExtraServiceOrder();
    }

    public static boolean shouldShowInlineCalendar(ReservationStatus status) {
        return status.matchesAny(ReservationStatus.Pending, ReservationStatus.Inquiry, ReservationStatus.Preapproved, ReservationStatus.SpecialOffer);
    }

    public HostReservationObjectAdapter(Context context2, Listener listener2, Bundle savedInstanceState) {
        super(true);
        this.context = context2;
        this.listener = listener2;
        ((AirbnbGraph) AirbnbApplication.instance(context2).component()).inject(this);
        onRestoreInstanceState(savedInstanceState);
        initCalendarRow();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marquee, this.specialStatusRow, this.writeReviewRow, this.guestReviewRow, this.hostReviewRow, this.alterationPendingRow, this.extraServicesRow, this.guestDetailsSummary, this.guestRatingsRow, this.guestRatingsIbUpsellRow, this.guestRatingsInfoRow, this.guestPreviousReviewsRow, this.buttonBar, this.firstMessageRow, this.checkInRow, this.checkOutRow, this.guestDetailsRow, this.inlineCalendarRow, this.linkCalendarRow, this.specialOfferRow, this.payoutRow, this.emailRow, this.removePreapprovalRow, this.resolutionCenterRow, this.helpRow, this.alterationRow, this.cancellationRow});
    }

    public void setModels(TripInformationProvider provider) {
        setMarquee(provider);
        setSpecialStatusRow(provider);
        setReviewRows(provider);
        setExtraServicesRow(provider);
        setGuestDetails(provider);
        setButtonBar(provider);
        setFirstMessageRow(provider);
        setCheckInOutRows(provider);
        setGuestDetailsRow(provider);
        setGuestRatings(provider);
        setGuestPreviousReviewsRow(provider);
        setSpecialOfferRow(provider);
        setPayoutRow(provider);
        setCancellationRow(provider);
        setSpecialOfferRow(provider);
        setPayoutRow(provider);
        setRemovePreapprovalRow(provider);
        setResolutionCenter(provider);
        setHelpRow(provider);
        setEmailRow(provider);
        setAlterationRow(provider);
        setCalendarRow(provider);
        notifyDataSetChanged();
    }

    public void updateWithCalendarDays(TripInformationProvider provider) {
        setCalendarRow(provider);
        notifyModelChanged(this.inlineCalendarRow);
    }

    private void setMarquee(TripInformationProvider provider) {
        StringBuilder subtitle = new StringBuilder().append(GuestDetailsPresenter.formatGuestsString(this.context, provider.getGuestDetails(), provider.getGuestCount())).append(" · ").append(getTripString(provider));
        if (provider.getPrimaryHost().getListingsCount() > 1) {
            subtitle.append(System.getProperty("line.separator")).append(provider.getListing().getName());
        }
        this.marquee.title(getMarqueeTitle(provider)).subtitle(subtitle.toString()).backgroundColor(ContextCompat.getColor(this.context, C0880R.color.n2_babu));
    }

    private String getMarqueeTitle(TripInformationProvider provider) {
        if (provider.hasReservation()) {
            Reservation reservation = provider.getReservation();
            String guestName = provider.getGuestIfPossible().getFirstName();
            if (reservation.isCurrent()) {
                return this.context.getString(C0880R.string.ro_user_stay, new Object[]{guestName});
            } else if (reservation.getHostReview() != null && reservation.getHostReview().isPending()) {
                return this.context.getString(C0880R.string.ro_review_screen_title, new Object[]{guestName});
            }
        }
        return ReservationStatusDisplay.forHost(provider).getString(this.context);
    }

    private String getTripString(TripInformationProvider provider) {
        if (!provider.hasReservation() || !provider.getReservation().isCurrent()) {
            return this.context.getResources().getQuantityString(C0880R.plurals.x_nights, provider.getReservedNightsCount(), new Object[]{Integer.valueOf(provider.getReservedNightsCount())}) + " · " + provider.getHostTotalPriceFormatted(this.currencyFormatter);
        }
        Reservation reservation = provider.getReservation();
        SimpleDateFormat hourOnlyFormat = new SimpleDateFormat(this.context.getString(C0880R.string.hour_and_meridiem), Locale.getDefault());
        if (reservation.isCheckInToday()) {
            return this.context.getString(C0880R.string.ro_check_in_today, new Object[]{reservation.getCheckinTime().format(hourOnlyFormat).toLowerCase()});
        } else if (!reservation.isCheckOutToday()) {
            return this.context.getString(C0880R.string.ro_on_trip);
        } else {
            return this.context.getString(C0880R.string.ro_check_out_today, new Object[]{reservation.getCheckoutTime().format(hourOnlyFormat).toLowerCase()});
        }
    }

    private void setSpecialStatusRow(TripInformationProvider provider) {
        String guestName = provider.getGuestIfPossible().getName();
        if (provider.hasReservation()) {
            if (provider.getStatus() == ReservationStatus.Timedout) {
                this.specialStatusRow.title((CharSequence) this.context.getString(C0880R.string.ro_expired_request_title, new Object[]{guestName})).subtitle((CharSequence) this.context.getString(C0880R.string.ro_expired_request_subtitle, new Object[]{guestName})).show(true);
                return;
            } else if (provider.getStatus() == ReservationStatus.Denied) {
                this.specialStatusRow.title((CharSequence) this.context.getString(C0880R.string.ro_declined_request_title)).subtitle((CharSequence) this.context.getString(C0880R.string.ro_special_offer_suggestion)).show(true);
                return;
            } else if (provider.getReservation().isCancelled()) {
                this.specialStatusRow.title((CharSequence) this.context.getString(C0880R.string.ro_canceled_reservation_title)).subtitle((CharSequence) this.context.getString(C0880R.string.ro_special_offer_suggestion)).show(true);
                return;
            }
        } else if (provider.getSpecialOffer() != null) {
            if (!provider.getSpecialOffer().isPreApproval()) {
                if (provider.getSpecialOffer().isExpired()) {
                    this.specialStatusRow.title((CharSequence) this.context.getString(C0880R.string.ro_expired_special_offer_title)).subtitle((CharSequence) this.context.getString(C0880R.string.ro_expired_special_offer_subtitle, new Object[]{guestName, guestName})).show(true);
                    return;
                }
                this.specialStatusRow.title((CharSequence) this.context.getString(C0880R.string.ro_special_offer_row_title, new Object[]{provider.getHostSubtotalFormatted()})).subtitle((CharSequence) this.context.getString(C0880R.string.ro_special_offer_row_subtitle, new Object[]{provider.getGuestIfPossible().getName()})).show(true);
                return;
            } else if (provider.getSpecialOffer().isExpired()) {
                this.specialStatusRow.title((CharSequence) this.context.getString(C0880R.string.ro_expired_preapproval_title)).subtitle((CharSequence) this.context.getString(C0880R.string.ro_expired_preapproval_subtitle, new Object[]{guestName, guestName})).show(true);
                return;
            }
        }
        this.specialStatusRow.show(false);
    }

    private void setReviewRows(TripInformationProvider provider) {
        this.writeReviewRow.hide();
        this.guestReviewRow.hide();
        this.hostReviewRow.hide();
        if (provider.hasReservation() && provider.getReservation().isAccepted() && provider.getReservation().hasEnded()) {
            Reservation reservation = provider.getReservation();
            Review hostReview = reservation.getHostReview();
            if (hostReview == null) {
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Reservation status is accepted and has ended but no review: " + reservation.getId()));
            } else if (!hostReview.isPending()) {
                setupShowReviewRows(hostReview, reservation.getGuestReview());
            } else {
                this.writeReviewRow.subtitle((CharSequence) getWriteReviewString(hostReview)).show();
            }
        }
    }

    private void setupShowReviewRows(Review hostReview, Review guestReview) {
        if (hostReview.isSubmitted()) {
            this.hostReviewRow.title(C0880R.string.ro_review_title_from_you).subtitle((CharSequence) hostReview.getPublicFeedback()).clickListener(HostReservationObjectAdapter$$Lambda$1.lambdaFactory$(this, hostReview)).show();
        }
        if (hostReview.isTwinCompleted() && guestReview != null) {
            this.guestReviewRow.title((CharSequence) this.context.getString(C0880R.string.ro_review_title_from_other, new Object[]{guestReview.getAuthor().getName()})).subtitle((CharSequence) guestReview.getPublicFeedback()).clickListener(HostReservationObjectAdapter$$Lambda$2.lambdaFactory$(this, guestReview)).show();
        }
    }

    private String getWriteReviewString(Review hostReview) {
        boolean pendingHostReview;
        boolean pendingGuestReview;
        User guest = hostReview.getRecipient();
        if (!hostReview.isSubmitted()) {
            pendingHostReview = true;
        } else {
            pendingHostReview = false;
        }
        if (!hostReview.isTwinCompleted()) {
            pendingGuestReview = true;
        } else {
            pendingGuestReview = false;
        }
        int daysUntilExpiration = AirDateTime.now().daysUntil(hostReview.getExpirationTime());
        if (pendingGuestReview && pendingHostReview) {
            return this.context.getString(C0880R.string.ro_pending_review_from_host_and_guest, new Object[]{Integer.valueOf(daysUntilExpiration)});
        } else if (pendingGuestReview) {
            return this.context.getString(C0880R.string.ro_pending_review_from_guest, new Object[]{guest.getName(), Integer.valueOf(daysUntilExpiration)});
        } else if (pendingHostReview) {
            return this.context.getString(C0880R.string.ro_pending_review_from_host, new Object[]{guest.getName(), Integer.valueOf(14)});
        } else {
            throw new IllegalStateException("Invalid state when both reviews completed");
        }
    }

    private void setGuestDetails(TripInformationProvider provider) {
        this.guestDetailsSummary.guest(provider.getGuestIfPossible()).clickListener(HostReservationObjectAdapter$$Lambda$3.lambdaFactory$(this)).showNoProfilePhoto(FeatureToggles.hideGuestProfilePhoto(provider.getStatus())).hideReviewsText(FeatureToggles.showReviewModalContent(provider.getListing().isInstantBookEnabled()));
    }

    private void setGuestRatings(TripInformationProvider provider) {
        User guest = provider.getGuestIfPossible();
        Reservation reservation = provider.hasReservation() ? provider.getReservation() : null;
        Listing listing = provider.getListing();
        this.guestRatingsRow.hide();
        this.guestRatingsIbUpsellRow.hide();
        this.guestRatingsInfoRow.hide();
        this.guestPreviousReviewsRow.hide();
        if (reservation != null && guest != null && FeatureToggles.showGuestReviewRatings()) {
            ReviewRatingsAsGuest reviewRatingsAsGuest = guest.getReviewRatingsAsGuest();
            boolean showGuestRatings = reservation.isInstantBookEnabledAtBooking() && reviewRatingsAsGuest != null && reviewRatingsAsGuest.getOverall().getReviewsCount() > 0;
            boolean showGuestReviews = reservation.isInstantBookEnabledAtBooking() && guest.getReviewsCountAsGuest() > 0;
            boolean showGuestRatingsIbUpsell = !this.sharedPrefsHelper.hasClickedGuestRatingsIbUpsell() && !reservation.isInstantBookEnabledAtBooking() && !listing.isInstantBookEnabled();
            boolean showGuestRatingsInfo = !reservation.isInstantBookEnabledAtBooking() && listing.isInstantBookEnabled();
            if (showGuestRatings) {
                this.guestRatingsRow.starRating(reviewRatingsAsGuest.getOverall().getAverageRating()).reviewsCount(guest.getReviewsCountAsGuest()).clickListener(HostReservationObjectAdapter$$Lambda$4.lambdaFactory$(this, guest)).minNumReviewsToShowStars(Integer.valueOf(1)).show();
                this.guestDetailsSummary.hideReviewsText(true);
            } else if (showGuestReviews) {
                this.guestPreviousReviewsRow.text(this.context.getResources().getQuantityString(C0880R.plurals.reviews, guest.getReviewsCountAsGuest(), new Object[]{Integer.valueOf(guest.getReviewsCountAsGuest())})).clickListener(HostReservationObjectAdapter$$Lambda$5.lambdaFactory$(this)).show();
                this.guestDetailsSummary.hideReviewsText(true);
            } else if (showGuestRatingsIbUpsell) {
                this.guestRatingsIbUpsellRow.clickListener(HostReservationObjectAdapter$$Lambda$6.lambdaFactory$(this)).show();
            } else if (showGuestRatingsInfo) {
                this.guestRatingsInfoRow.titleMaxLine(5).show();
            }
        }
    }

    static /* synthetic */ void lambda$setGuestRatings$5(HostReservationObjectAdapter hostReservationObjectAdapter, View v) {
        hostReservationObjectAdapter.sharedPrefsHelper.setHasClickedGuestRatingsIbUpsell(true);
        hostReservationObjectAdapter.listener.goToGuestRatingsHelpFlow();
    }

    private void setGuestPreviousReviewsRow(TripInformationProvider provider) {
        User guest = provider.getGuestIfPossible();
        if (guest != null && FeatureToggles.showReviewModalContent(provider.getListing().isInstantBookEnabled())) {
            this.guestPreviousReviewsRow.text(this.context.getResources().getQuantityString(C0880R.plurals.reviews, guest.getRevieweeCount(), new Object[]{Integer.valueOf(guest.getRevieweeCount())})).clickListener(HostReservationObjectAdapter$$Lambda$7.lambdaFactory$(this)).show();
        }
    }

    private void setExtraServicesRow(TripInformationProvider provider) {
        this.extraServicesRow.hide();
        if (Trebuchet.launch(TrebuchetKeys.PAID_AMENITIES_HOST, false) && provider.hasReservation()) {
            if (provider.getReservation().hasPaidAmenityOrders()) {
                this.extraServicesRow.title(C0880R.string.paid_amenities_itinerary_host_entry_view_service_title_text).actionText(C0880R.string.view).clickListener(HostReservationObjectAdapter$$Lambda$8.lambdaFactory$(this)).show();
            } else {
                this.extraServicesRow.title(C0880R.string.paid_amenities_itinerary_host_entry_add_service_title_text).subtitle(C0880R.string.paid_amenities_itinerary_host_entry_add_service_subtitle_text).actionText(C0880R.string.add).clickListener(HostReservationObjectAdapter$$Lambda$9.lambdaFactory$(this, provider)).show();
            }
        }
    }

    private void setButtonBar(TripInformationProvider provider) {
        this.buttonBar.clearButtons();
        this.buttonBar.addButton(C0880R.string.chat, C0880R.C0881drawable.icon_line_message, HostReservationObjectAdapter$$Lambda$10.lambdaFactory$(this));
        ReservationStatus status = provider.getStatus();
        if (status == ReservationStatus.Inquiry) {
            this.buttonBar.addButton(C0880R.string.ro_response_pre_approve, C0880R.C0881drawable.icon_line_accept, HostReservationObjectAdapter$$Lambda$11.lambdaFactory$(this));
            this.buttonBar.addButton(C0880R.string.decline, C0880R.C0881drawable.icon_line_decline, HostReservationObjectAdapter$$Lambda$12.lambdaFactory$(this));
        }
        if (status == ReservationStatus.Pending) {
            if (FeatureToggles.shouldShowDecisionCriteriaMessage()) {
                this.buttonBar.addButton(C0880R.string.ro_response_now_accept_or_decline, C0880R.C0881drawable.icon_line_respond, HostReservationObjectAdapter$$Lambda$13.lambdaFactory$(this));
            } else {
                this.buttonBar.addButton(C0880R.string.accept, C0880R.C0881drawable.icon_line_accept, HostReservationObjectAdapter$$Lambda$14.lambdaFactory$(this));
                this.buttonBar.addButton(C0880R.string.decline, C0880R.C0881drawable.icon_line_decline, HostReservationObjectAdapter$$Lambda$15.lambdaFactory$(this));
            }
        }
        if (provider.getGuestIfPossible().hasPhoneNumber()) {
            this.buttonBar.addButton(C0880R.string.call, C0880R.C0881drawable.icon_line_phone, HostReservationObjectAdapter$$Lambda$16.lambdaFactory$(this));
        }
        if (status.matchesAny(ReservationStatus.Preapproved, ReservationStatus.Cancelled, ReservationStatus.NotPossible, ReservationStatus.Timedout, ReservationStatus.Denied)) {
            this.buttonBar.addButton(C0880R.string.special_offer, C0880R.C0881drawable.icon_line_special_offer, HostReservationObjectAdapter$$Lambda$17.lambdaFactory$(this));
        }
    }

    private void setFirstMessageRow(TripInformationProvider provider) {
        if (provider.requiresResponse() && provider.getFirstPost() != null) {
            this.firstMessageRow.title((CharSequence) provider.getFirstPost().getMessage()).subtitle((CharSequence) provider.getFirstPost().getCreatedAt().getTimeAgoText(this.context)).titleMaxLine(15).show();
        } else {
            this.firstMessageRow.hide();
        }
    }

    private void setGuestDetailsRow(TripInformationProvider provider) {
        GuestDetails details = provider.getGuestDetails();
        if (details != null) {
            this.guestDetailsRow.subtitle((CharSequence) GuestDetailsPresenter.formatDetailedGuestsString(this.context, details, provider.getGuestCount()));
            return;
        }
        this.guestDetailsRow.hide();
    }

    private void setCheckInOutRows(TripInformationProvider provider) {
        String flexibleTime = this.context.getString(C0880R.string.flexible_time);
        String checkInSubtitle = null;
        String checkOutSubtitle = null;
        if (provider.hasReservation()) {
            Reservation reservation = provider.getReservation();
            if (reservation.hasArrivalTime()) {
                CheckinTimeSelectionOptions from = reservation.getArrivalDetails().getGuestCheckinTimeFrom();
                CheckinTimeSelectionOptions to = reservation.getArrivalDetails().getGuestCheckinTimeTo();
                if (!CheckinTimeSelectionOptions.NOT_SELECTED_FORMATTED_HOUR.equals(from.getFormattedHour()) && !CheckinTimeSelectionOptions.NOT_SELECTED_FORMATTED_HOUR.equals(to.getFormattedHour())) {
                    checkInSubtitle = this.context.getString(C0880R.string.guests_check_in_window, new Object[]{from.getLocalizedHourString(), to.getLocalizedHourString()});
                }
            } else {
                Integer checkIn = provider.getListing().getCheckInTime();
                checkInSubtitle = checkIn == null ? flexibleTime : CalendarHelper.formatHour(checkIn.intValue());
            }
            Integer checkOut = provider.getListing().getCheckOutTime();
            checkOutSubtitle = checkOut == null ? flexibleTime : CalendarHelper.formatHour(checkOut.intValue());
        }
        String dateFormat = this.context.getString(provider.getStartDate().getYear() == AirDate.today().getYear() ? C0880R.string.md_with_abbr_day_name : C0880R.string.mdy_with_abbr_day_name);
        this.checkInRow.placeholderText((CharSequence) provider.getStartDate().formatDate(dateFormat)).subtitle((CharSequence) checkInSubtitle).clickListener(provider.hasReservation() ? HostReservationObjectAdapter$$Lambda$18.lambdaFactory$(this) : null);
        this.checkOutRow.placeholderText((CharSequence) provider.getEndDate().formatDate(dateFormat)).subtitle((CharSequence) checkOutSubtitle).clickListener(provider.hasReservation() ? HostReservationObjectAdapter$$Lambda$19.lambdaFactory$(this) : null);
    }

    private void setSpecialOfferRow(TripInformationProvider provider) {
        this.specialOfferRow.clickListener(HostReservationObjectAdapter$$Lambda$20.lambdaFactory$(this)).show(ReservationStatus.Inquiry == provider.getStatus());
    }

    private void setPayoutRow(TripInformationProvider provider) {
        boolean shouldShowPayoutRow = true;
        ReservationStatus status = provider.getStatus();
        SpannableStringBuilder builder = SpannableStringBuilder.valueOf(this.context.getString(status.matchesAny(ReservationStatus.Denied, ReservationStatus.Cancelled, ReservationStatus.Timedout, ReservationStatus.NotPossible) ? C0880R.string.original_payout : C0880R.string.total_payout));
        if (status.matchesAny(ReservationStatus.Message, ReservationStatus.Unknown, ReservationStatus.New)) {
            shouldShowPayoutRow = false;
        }
        this.payoutRow.title((CharSequence) builder).actionText((CharSequence) provider.getHostTotalPriceFormatted(this.currencyFormatter)).clickListener(HostReservationObjectAdapter$$Lambda$21.lambdaFactory$(this, provider)).show(shouldShowPayoutRow);
    }

    private void setRemovePreapprovalRow(TripInformationProvider provider) {
        switch (provider.getStatus()) {
            case Preapproved:
                this.removePreapprovalRow.title(C0880R.string.ro_remove_preapproval).clickListener(HostReservationObjectAdapter$$Lambda$22.lambdaFactory$(this)).show();
                return;
            case SpecialOffer:
                this.removePreapprovalRow.title(C0880R.string.ro_remove_special_offer).clickListener(HostReservationObjectAdapter$$Lambda$23.lambdaFactory$(this)).show();
                return;
            default:
                this.removePreapprovalRow.hide();
                return;
        }
    }

    private void setResolutionCenter(TripInformationProvider provider) {
        if (!provider.hasReservation() || (!provider.getReservation().isAccepted() && !provider.getReservation().isCancelled())) {
            this.resolutionCenterRow.hide();
            return;
        }
        this.resolutionCenterRow.title(FeatureToggles.showResolutionCenterNewCopy() ? C0880R.string.ro_send_or_request_money : C0880R.string.ro_resolution_center).clickListener(HostReservationObjectAdapter$$Lambda$24.lambdaFactory$(this, provider.getReservation().getConfirmationCode())).show();
    }

    private void setHelpRow(TripInformationProvider provider) {
        String subtitle = null;
        if (provider.hasReservation()) {
            subtitle = this.context.getString(C0880R.string.ro_help_reservation_number, new Object[]{provider.getReservation().getConfirmationCode()});
        }
        this.helpRow.subtitle((CharSequence) subtitle).clickListener(HostReservationObjectAdapter$$Lambda$25.lambdaFactory$(this)).show();
    }

    private void setCancellationRow(TripInformationProvider provider) {
        this.cancellationRow.clickListener(HostReservationObjectAdapter$$Lambda$26.lambdaFactory$(this)).show(provider.hasReservation() && provider.getReservation().isCancelableByUser(this.accountManager.getCurrentUser()));
    }

    private void initCalendarRow() {
        this.inlineCalendarRow.hide();
        this.linkCalendarRow.clickListener(HostReservationObjectAdapter$$Lambda$27.lambdaFactory$(this)).show();
    }

    private void setCalendarRow(TripInformationProvider provider) {
        OnClickListener clickListener = HostReservationObjectAdapter$$Lambda$28.lambdaFactory$(this);
        if (provider.getCalendarDays() == null || !shouldShowInlineCalendar(provider.getStatus())) {
            this.inlineCalendarRow.hide();
        } else {
            this.inlineCalendarRow.clickListener(clickListener).calendarDays(provider.getCalendarDays()).requestEndDate(provider.getEndDate()).requestStartDate(provider.getStartDate()).guestPhotoUrl(provider.getGuestPhotoUrl()).hideGuestProfilePhoto(FeatureToggles.hideGuestProfilePhoto(provider.getStatus())).profilePlaceholderText(provider.getGuestIfPossible().getHiddenProfileName()).show();
        }
    }

    private void setEmailRow(TripInformationProvider provider) {
        String address = provider.getGuestIfPossible().getEmailAddress();
        this.emailRow.clickListener(HostReservationObjectAdapter$$Lambda$29.lambdaFactory$(this)).show(provider.hasReservation() && provider.getReservation().isAccepted() && address != null && !TextUtils.isEmpty(address));
    }

    private void setAlterationRow(TripInformationProvider provider) {
        String message;
        boolean shouldShowAlteration = true;
        ReservationAlteration firstPendingAlteration = provider.hasReservation() ? provider.getReservation().getFirstPendingAlteration() : null;
        if (firstPendingAlteration == null) {
            this.alterationPendingRow.hide();
        } else {
            if (firstPendingAlteration.isInitiatedByGuest()) {
                message = this.context.getString(C0880R.string.alteration_request_ro_header_message_respond, new Object[]{provider.getReservation().getGuest().getFirstName()});
            } else {
                message = this.context.getString(C0880R.string.alteration_request_ro_header_message_host);
            }
            this.alterationPendingRow.subtitle((CharSequence) message).clickListener(HostReservationObjectAdapter$$Lambda$30.lambdaFactory$(this)).actionText(C0880R.string.view).show();
        }
        if (!provider.hasReservation() || !provider.getReservation().isAlterable() || provider.getReservation().getFirstPendingAlteration() != null) {
            shouldShowAlteration = false;
        }
        this.alterationRow.clickListener(HostReservationObjectAdapter$$Lambda$31.lambdaFactory$(this)).show(shouldShowAlteration);
    }
}
