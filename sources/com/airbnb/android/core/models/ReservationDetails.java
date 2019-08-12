package com.airbnb.android.core.models;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Parcelable;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.ListUtils;
import java.util.Collection;
import java.util.List;

public abstract class ReservationDetails implements Parcelable {
    public static final String EXTRA_CHECK_IN = "extra_check_in";
    public static final String EXTRA_CHECK_OUT = "extra_check_out";
    public static final String EXTRA_GUEST_FILTER_DATA = "extra_guest_filter_data";
    public static final String EXTRA_SPECIAL_OFFER_ID = "extra_special_offer_id";
    public static final String EXTRA_TRIP_PURPOSE = "extra_trip_purpose";

    public static abstract class Builder {
        public abstract Builder agreedToHouseRules(Boolean bool);

        public abstract ReservationDetails build();

        public abstract Builder businessTripNote(String str);

        public abstract Builder checkIn(AirDate airDate);

        public abstract Builder checkInHour(String str);

        public abstract Builder checkOut(AirDate airDate);

        public abstract Builder confirmationCode(String str);

        public abstract Builder confirmedEmailAddress(Boolean bool);

        public abstract Builder confirmedPhoneNumber(Boolean bool);

        public abstract Builder currency(String str);

        public abstract Builder fxCopy(String str);

        public abstract Builder guestId(Long l);

        public abstract Builder identifications(List<GuestIdentity> list);

        public abstract Builder isBringingPets(Boolean bool);

        public abstract Builder isBusinessTravelPaymentMethod(Boolean bool);

        public abstract Builder isCheckInTimeRequired(Boolean bool);

        public abstract Builder isMessageHostRequired(Boolean bool);

        public abstract Builder listingId(Long l);

        public abstract Builder messageToHost(String str);

        public abstract Builder numberOfAdults(Integer num);

        public abstract Builder numberOfChildren(Integer num);

        public abstract Builder numberOfInfants(Integer num);

        public abstract Builder paymentInstrument(OldPaymentInstrument oldPaymentInstrument);

        public abstract Builder providedGovernmentId(Boolean bool);

        public abstract Builder requiresIdentifications(Boolean bool);

        public abstract Builder requiresVerifications(Boolean bool);

        public abstract Builder reservationId(Long l);

        public abstract Builder specialOfferId(Long l);

        public abstract Builder totalPrice(Integer num);

        public abstract Builder tripPurpose(TripPurpose tripPurpose);

        public abstract Builder tripType(TripType tripType);

        public abstract Builder usesIdentityFlow(Boolean bool);

        public Builder guestDetails(GuestDetails guestDetails) {
            numberOfAdults(Integer.valueOf(guestDetails.getNumberOfAdults()));
            numberOfChildren(Integer.valueOf(guestDetails.getNumberOfChildren()));
            numberOfInfants(Integer.valueOf(guestDetails.getNumberOfInfants()));
            isBringingPets(Boolean.valueOf(guestDetails.isBringingPets()));
            return this;
        }

        public Builder reservation(Reservation reservation) {
            reservationId(Long.valueOf(reservation.getId()));
            confirmationCode(reservation.getConfirmationCode());
            checkIn(reservation.getCheckIn());
            checkOut(reservation.getCheckOut());
            totalPrice(Integer.valueOf(reservation.getPricingQuote().getTotalPrice().getAmount().intValue()));
            currency(reservation.getPricingQuote().getTotalPrice().getCurrency());
            requiresIdentifications(Boolean.valueOf(reservation.isGuestIdentificationsRequired()));
            isCheckInTimeRequired(Boolean.valueOf(reservation.isCheckInTimeRequired()));
            return this;
        }
    }

    public enum TripType {
        BusinessVerified,
        BusinessUnverified,
        PersonalVerified,
        PersonalUnverified
    }

    public abstract Boolean agreedToHouseRules();

    public abstract String businessTripNote();

    public abstract AirDate checkIn();

    public abstract String checkInHour();

    public abstract AirDate checkOut();

    public abstract String confirmationCode();

    public abstract Boolean confirmedEmailAddress();

    public abstract Boolean confirmedPhoneNumber();

    public abstract String currency();

    public abstract String fxCopy();

    public abstract Long guestId();

    public abstract List<GuestIdentity> identifications();

    public abstract Boolean isBringingPets();

    public abstract Boolean isBusinessTravelPaymentMethod();

    public abstract Boolean isCheckInTimeRequired();

    public abstract Boolean isMessageHostRequired();

    public abstract Long listingId();

    public abstract String messageToHost();

    public abstract Integer numberOfAdults();

    public abstract Integer numberOfChildren();

    public abstract Integer numberOfInfants();

    public abstract OldPaymentInstrument paymentInstrument();

    public abstract Boolean providedGovernmentId();

    public abstract Boolean requiresIdentifications();

    public abstract Boolean requiresVerifications();

    public abstract Long reservationId();

    public abstract Long specialOfferId();

    public abstract Builder toBuilder();

    public abstract Integer totalPrice();

    public abstract TripPurpose tripPurpose();

    public abstract TripType tripType();

    public abstract Boolean usesIdentityFlow();

    public static Builder builder() {
        return new Builder();
    }

    public static ReservationDetails fromIntent(Intent intent, Listing listing, User guest) {
        int numberOfAdults;
        int numberOfChildren;
        int numberOfInfants;
        boolean bringingPets;
        GuestDetails guestDetails = (GuestDetails) intent.getParcelableExtra(EXTRA_GUEST_FILTER_DATA);
        if (guestDetails != null) {
            numberOfAdults = guestDetails.adultsCount();
            numberOfChildren = guestDetails.childrenCount();
            numberOfInfants = guestDetails.infantsCount();
            bringingPets = guestDetails.isBringingPets();
        } else {
            numberOfAdults = 1;
            numberOfChildren = 0;
            numberOfInfants = 0;
            bringingPets = false;
        }
        long guestId = guest.getId();
        int tripPurposeIndex = intent.getIntExtra(EXTRA_TRIP_PURPOSE, -1);
        TripPurpose tripPurpose = tripPurposeIndex == -1 ? TripPurpose.Other : TripPurpose.values()[tripPurposeIndex];
        long specialOfferId = intent.getLongExtra(EXTRA_SPECIAL_OFFER_ID, -1);
        return builder().listingId(Long.valueOf(listing.getId())).guestId(Long.valueOf(guestId)).specialOfferId(specialOfferId != -1 ? Long.valueOf(specialOfferId) : null).checkIn((AirDate) intent.getParcelableExtra(EXTRA_CHECK_IN)).checkOut((AirDate) intent.getParcelableExtra(EXTRA_CHECK_OUT)).numberOfAdults(Integer.valueOf(numberOfAdults)).numberOfChildren(Integer.valueOf(numberOfChildren)).numberOfInfants(Integer.valueOf(numberOfInfants)).isBringingPets(Boolean.valueOf(bringingPets)).tripPurpose(tripPurpose).isCheckInTimeRequired(Boolean.valueOf(false)).isMessageHostRequired(Boolean.valueOf(true)).agreedToHouseRules(Boolean.valueOf(false)).build();
    }

    public int numberOfGuests() {
        return numberOfAdults().intValue() + numberOfChildren().intValue();
    }

    public GuestDetails getGuestDetails() {
        return new GuestDetails().adultsCount(numberOfAdults().intValue()).childrenCount(numberOfChildren().intValue()).infantsCount(numberOfInfants().intValue());
    }

    public boolean completedRequiredDetails() {
        return requiredDetailsRemaining() == 0;
    }

    public int requiredDetailsRemaining() {
        int remainingSteps = 0;
        if (paymentInstrument() == null) {
            remainingSteps = 0 + 1;
        }
        if (TextUtils.isEmpty(messageToHost()) && isMessageHostRequired().booleanValue()) {
            remainingSteps++;
        }
        if (!agreedToHouseRules().booleanValue()) {
            remainingSteps++;
        }
        if (TextUtils.isEmpty(checkInHour()) && isCheckInTimeRequired().booleanValue()) {
            remainingSteps++;
        }
        if (!completedGuestIdentifications()) {
            remainingSteps++;
        }
        Boolean confirmedEmailAddress = confirmedEmailAddress();
        if (confirmedEmailAddress != null && !confirmedEmailAddress.booleanValue()) {
            remainingSteps++;
        }
        Boolean confirmedPhoneNumber = confirmedPhoneNumber();
        if (confirmedPhoneNumber != null && !confirmedPhoneNumber.booleanValue()) {
            remainingSteps++;
        }
        Boolean providedGovernmentId = providedGovernmentId();
        if (providedGovernmentId == null || providedGovernmentId.booleanValue()) {
            return remainingSteps;
        }
        return remainingSteps + 1;
    }

    public boolean isVerifiedBusinessTrip() {
        return tripType() == TripType.BusinessVerified;
    }

    public boolean isBusinessTrip() {
        return tripType() == TripType.BusinessVerified || tripType() == TripType.BusinessUnverified;
    }

    public boolean completedGuestIdentifications() {
        boolean identificationsRequired;
        if (requiresIdentifications() == null || !requiresIdentifications().booleanValue()) {
            identificationsRequired = false;
        } else {
            identificationsRequired = true;
        }
        if (!identificationsRequired) {
            return true;
        }
        if (ListUtils.isEmpty((Collection<?>) identifications())) {
            return false;
        }
        if (identifications().size() < 1) {
            return false;
        }
        return true;
    }

    public ParcelStrap toBasicAnalyticsStrap(String mobileSearchSessionId) {
        return ParcelStrap.make().mo9946kv("id_reservation", String.valueOf(reservationId())).mo9946kv("mobile_search_session_id", mobileSearchSessionId);
    }

    public ParcelStrap toFullAnalyticsStrap(String mobileSearchSessionId) {
        return toBasicAnalyticsStrap(mobileSearchSessionId).mo9946kv("id_listing", String.valueOf(listingId())).mo9946kv("ds_checkin", checkIn().getIsoDateString()).mo9946kv("ds_checkout", checkOut().getIsoDateString()).mo9945kv("n_nights", checkIn().getDaysUntil(checkOut())).mo9945kv("n_adults", numberOfAdults().intValue()).mo9945kv("n_infants", numberOfInfants().intValue()).mo9945kv("n_children", numberOfChildren().intValue()).mo9947kv("pet_toggle", isBringingPets().booleanValue()).mo9946kv("id_payment_instrument", getPaymentInstrumentIdForAnalytics()).mo9946kv("checkin_window", checkInHour()).mo9945kv(EditPriceFragment.RESULT_PRICE, totalPrice().intValue()).mo9946kv(AirbnbConstants.PREFS_CURRENCY, currency());
    }

    private String getPaymentInstrumentIdForAnalytics() {
        if (paymentInstrument() == null || paymentInstrument().getId() == -1) {
            return "null";
        }
        return String.valueOf(paymentInstrument().getId());
    }

    public String getReservationDescription(Resources resources) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getDateRangeDescription(resources) + ", ");
        stringBuilder.append(getGuestCountDescription(resources));
        return stringBuilder.toString();
    }

    private String getDateRangeDescription(Resources resources) {
        String dateFormat = resources.getString(C0716R.string.date_name_format);
        return resources.getString(C0716R.string.p4_date_range, new Object[]{checkIn().formatDate(dateFormat), checkOut().formatDate(dateFormat)});
    }

    private String getGuestCountDescription(Resources resources) {
        int guestCount = getGuestDetails().totalGuestCount();
        return resources.getQuantityString(C0716R.plurals.x_guests, guestCount, new Object[]{Integer.valueOf(guestCount)});
    }
}
