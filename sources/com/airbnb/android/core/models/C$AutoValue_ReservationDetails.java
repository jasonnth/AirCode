package com.airbnb.android.core.models;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import java.util.List;

/* renamed from: com.airbnb.android.core.models.$AutoValue_ReservationDetails reason: invalid class name */
abstract class C$AutoValue_ReservationDetails extends ReservationDetails {
    private final Boolean agreedToHouseRules;
    private final String businessTripNote;
    private final AirDate checkIn;
    private final String checkInHour;
    private final AirDate checkOut;
    private final String confirmationCode;
    private final Boolean confirmedEmailAddress;
    private final Boolean confirmedPhoneNumber;
    private final String currency;
    private final String fxCopy;
    private final Long guestId;
    private final List<GuestIdentity> identifications;
    private final Boolean isBringingPets;
    private final Boolean isBusinessTravelPaymentMethod;
    private final Boolean isCheckInTimeRequired;
    private final Boolean isMessageHostRequired;
    private final Long listingId;
    private final String messageToHost;
    private final Integer numberOfAdults;
    private final Integer numberOfChildren;
    private final Integer numberOfInfants;
    private final OldPaymentInstrument paymentInstrument;
    private final Boolean providedGovernmentId;
    private final Boolean requiresIdentifications;
    private final Boolean requiresVerifications;
    private final Long reservationId;
    private final Long specialOfferId;
    private final Integer totalPrice;
    private final TripPurpose tripPurpose;
    private final TripType tripType;
    private final Boolean usesIdentityFlow;

    /* renamed from: com.airbnb.android.core.models.$AutoValue_ReservationDetails$Builder */
    static final class Builder extends com.airbnb.android.core.models.ReservationDetails.Builder {
        private Boolean agreedToHouseRules;
        private String businessTripNote;
        private AirDate checkIn;
        private String checkInHour;
        private AirDate checkOut;
        private String confirmationCode;
        private Boolean confirmedEmailAddress;
        private Boolean confirmedPhoneNumber;
        private String currency;
        private String fxCopy;
        private Long guestId;
        private List<GuestIdentity> identifications;
        private Boolean isBringingPets;
        private Boolean isBusinessTravelPaymentMethod;
        private Boolean isCheckInTimeRequired;
        private Boolean isMessageHostRequired;
        private Long listingId;
        private String messageToHost;
        private Integer numberOfAdults;
        private Integer numberOfChildren;
        private Integer numberOfInfants;
        private OldPaymentInstrument paymentInstrument;
        private Boolean providedGovernmentId;
        private Boolean requiresIdentifications;
        private Boolean requiresVerifications;
        private Long reservationId;
        private Long specialOfferId;
        private Integer totalPrice;
        private TripPurpose tripPurpose;
        private TripType tripType;
        private Boolean usesIdentityFlow;

        Builder() {
        }

        private Builder(ReservationDetails source) {
            this.reservationId = source.reservationId();
            this.confirmationCode = source.confirmationCode();
            this.listingId = source.listingId();
            this.guestId = source.guestId();
            this.specialOfferId = source.specialOfferId();
            this.checkIn = source.checkIn();
            this.checkOut = source.checkOut();
            this.numberOfAdults = source.numberOfAdults();
            this.numberOfChildren = source.numberOfChildren();
            this.numberOfInfants = source.numberOfInfants();
            this.isBringingPets = source.isBringingPets();
            this.totalPrice = source.totalPrice();
            this.currency = source.currency();
            this.fxCopy = source.fxCopy();
            this.paymentInstrument = source.paymentInstrument();
            this.checkInHour = source.checkInHour();
            this.messageToHost = source.messageToHost();
            this.tripPurpose = source.tripPurpose();
            this.agreedToHouseRules = source.agreedToHouseRules();
            this.requiresIdentifications = source.requiresIdentifications();
            this.identifications = source.identifications();
            this.isCheckInTimeRequired = source.isCheckInTimeRequired();
            this.confirmedPhoneNumber = source.confirmedPhoneNumber();
            this.confirmedEmailAddress = source.confirmedEmailAddress();
            this.providedGovernmentId = source.providedGovernmentId();
            this.isMessageHostRequired = source.isMessageHostRequired();
            this.tripType = source.tripType();
            this.businessTripNote = source.businessTripNote();
            this.isBusinessTravelPaymentMethod = source.isBusinessTravelPaymentMethod();
            this.requiresVerifications = source.requiresVerifications();
            this.usesIdentityFlow = source.usesIdentityFlow();
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder reservationId(Long reservationId2) {
            this.reservationId = reservationId2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder confirmationCode(String confirmationCode2) {
            this.confirmationCode = confirmationCode2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder listingId(Long listingId2) {
            this.listingId = listingId2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder guestId(Long guestId2) {
            this.guestId = guestId2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder specialOfferId(Long specialOfferId2) {
            this.specialOfferId = specialOfferId2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder checkIn(AirDate checkIn2) {
            this.checkIn = checkIn2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder checkOut(AirDate checkOut2) {
            this.checkOut = checkOut2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder numberOfAdults(Integer numberOfAdults2) {
            this.numberOfAdults = numberOfAdults2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder numberOfChildren(Integer numberOfChildren2) {
            this.numberOfChildren = numberOfChildren2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder numberOfInfants(Integer numberOfInfants2) {
            this.numberOfInfants = numberOfInfants2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder isBringingPets(Boolean isBringingPets2) {
            this.isBringingPets = isBringingPets2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder totalPrice(Integer totalPrice2) {
            this.totalPrice = totalPrice2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder currency(String currency2) {
            this.currency = currency2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder fxCopy(String fxCopy2) {
            this.fxCopy = fxCopy2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder paymentInstrument(OldPaymentInstrument paymentInstrument2) {
            this.paymentInstrument = paymentInstrument2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder checkInHour(String checkInHour2) {
            this.checkInHour = checkInHour2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder messageToHost(String messageToHost2) {
            this.messageToHost = messageToHost2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder tripPurpose(TripPurpose tripPurpose2) {
            this.tripPurpose = tripPurpose2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder agreedToHouseRules(Boolean agreedToHouseRules2) {
            this.agreedToHouseRules = agreedToHouseRules2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder requiresIdentifications(Boolean requiresIdentifications2) {
            this.requiresIdentifications = requiresIdentifications2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder identifications(List<GuestIdentity> identifications2) {
            this.identifications = identifications2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder isCheckInTimeRequired(Boolean isCheckInTimeRequired2) {
            this.isCheckInTimeRequired = isCheckInTimeRequired2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder confirmedPhoneNumber(Boolean confirmedPhoneNumber2) {
            this.confirmedPhoneNumber = confirmedPhoneNumber2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder confirmedEmailAddress(Boolean confirmedEmailAddress2) {
            this.confirmedEmailAddress = confirmedEmailAddress2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder providedGovernmentId(Boolean providedGovernmentId2) {
            this.providedGovernmentId = providedGovernmentId2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder isMessageHostRequired(Boolean isMessageHostRequired2) {
            this.isMessageHostRequired = isMessageHostRequired2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder tripType(TripType tripType2) {
            this.tripType = tripType2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder businessTripNote(String businessTripNote2) {
            this.businessTripNote = businessTripNote2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder isBusinessTravelPaymentMethod(Boolean isBusinessTravelPaymentMethod2) {
            this.isBusinessTravelPaymentMethod = isBusinessTravelPaymentMethod2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder requiresVerifications(Boolean requiresVerifications2) {
            this.requiresVerifications = requiresVerifications2;
            return this;
        }

        public com.airbnb.android.core.models.ReservationDetails.Builder usesIdentityFlow(Boolean usesIdentityFlow2) {
            this.usesIdentityFlow = usesIdentityFlow2;
            return this;
        }

        public ReservationDetails build() {
            return new AutoValue_ReservationDetails(this.reservationId, this.confirmationCode, this.listingId, this.guestId, this.specialOfferId, this.checkIn, this.checkOut, this.numberOfAdults, this.numberOfChildren, this.numberOfInfants, this.isBringingPets, this.totalPrice, this.currency, this.fxCopy, this.paymentInstrument, this.checkInHour, this.messageToHost, this.tripPurpose, this.agreedToHouseRules, this.requiresIdentifications, this.identifications, this.isCheckInTimeRequired, this.confirmedPhoneNumber, this.confirmedEmailAddress, this.providedGovernmentId, this.isMessageHostRequired, this.tripType, this.businessTripNote, this.isBusinessTravelPaymentMethod, this.requiresVerifications, this.usesIdentityFlow);
        }
    }

    C$AutoValue_ReservationDetails(Long reservationId2, String confirmationCode2, Long listingId2, Long guestId2, Long specialOfferId2, AirDate checkIn2, AirDate checkOut2, Integer numberOfAdults2, Integer numberOfChildren2, Integer numberOfInfants2, Boolean isBringingPets2, Integer totalPrice2, String currency2, String fxCopy2, OldPaymentInstrument paymentInstrument2, String checkInHour2, String messageToHost2, TripPurpose tripPurpose2, Boolean agreedToHouseRules2, Boolean requiresIdentifications2, List<GuestIdentity> identifications2, Boolean isCheckInTimeRequired2, Boolean confirmedPhoneNumber2, Boolean confirmedEmailAddress2, Boolean providedGovernmentId2, Boolean isMessageHostRequired2, TripType tripType2, String businessTripNote2, Boolean isBusinessTravelPaymentMethod2, Boolean requiresVerifications2, Boolean usesIdentityFlow2) {
        this.reservationId = reservationId2;
        this.confirmationCode = confirmationCode2;
        this.listingId = listingId2;
        this.guestId = guestId2;
        this.specialOfferId = specialOfferId2;
        this.checkIn = checkIn2;
        this.checkOut = checkOut2;
        this.numberOfAdults = numberOfAdults2;
        this.numberOfChildren = numberOfChildren2;
        this.numberOfInfants = numberOfInfants2;
        this.isBringingPets = isBringingPets2;
        this.totalPrice = totalPrice2;
        this.currency = currency2;
        this.fxCopy = fxCopy2;
        this.paymentInstrument = paymentInstrument2;
        this.checkInHour = checkInHour2;
        this.messageToHost = messageToHost2;
        this.tripPurpose = tripPurpose2;
        this.agreedToHouseRules = agreedToHouseRules2;
        this.requiresIdentifications = requiresIdentifications2;
        this.identifications = identifications2;
        this.isCheckInTimeRequired = isCheckInTimeRequired2;
        this.confirmedPhoneNumber = confirmedPhoneNumber2;
        this.confirmedEmailAddress = confirmedEmailAddress2;
        this.providedGovernmentId = providedGovernmentId2;
        this.isMessageHostRequired = isMessageHostRequired2;
        this.tripType = tripType2;
        this.businessTripNote = businessTripNote2;
        this.isBusinessTravelPaymentMethod = isBusinessTravelPaymentMethod2;
        this.requiresVerifications = requiresVerifications2;
        this.usesIdentityFlow = usesIdentityFlow2;
    }

    public Long reservationId() {
        return this.reservationId;
    }

    public String confirmationCode() {
        return this.confirmationCode;
    }

    public Long listingId() {
        return this.listingId;
    }

    public Long guestId() {
        return this.guestId;
    }

    public Long specialOfferId() {
        return this.specialOfferId;
    }

    public AirDate checkIn() {
        return this.checkIn;
    }

    public AirDate checkOut() {
        return this.checkOut;
    }

    public Integer numberOfAdults() {
        return this.numberOfAdults;
    }

    public Integer numberOfChildren() {
        return this.numberOfChildren;
    }

    public Integer numberOfInfants() {
        return this.numberOfInfants;
    }

    public Boolean isBringingPets() {
        return this.isBringingPets;
    }

    public Integer totalPrice() {
        return this.totalPrice;
    }

    public String currency() {
        return this.currency;
    }

    public String fxCopy() {
        return this.fxCopy;
    }

    public OldPaymentInstrument paymentInstrument() {
        return this.paymentInstrument;
    }

    public String checkInHour() {
        return this.checkInHour;
    }

    public String messageToHost() {
        return this.messageToHost;
    }

    public TripPurpose tripPurpose() {
        return this.tripPurpose;
    }

    public Boolean agreedToHouseRules() {
        return this.agreedToHouseRules;
    }

    public Boolean requiresIdentifications() {
        return this.requiresIdentifications;
    }

    public List<GuestIdentity> identifications() {
        return this.identifications;
    }

    public Boolean isCheckInTimeRequired() {
        return this.isCheckInTimeRequired;
    }

    public Boolean confirmedPhoneNumber() {
        return this.confirmedPhoneNumber;
    }

    public Boolean confirmedEmailAddress() {
        return this.confirmedEmailAddress;
    }

    public Boolean providedGovernmentId() {
        return this.providedGovernmentId;
    }

    public Boolean isMessageHostRequired() {
        return this.isMessageHostRequired;
    }

    public TripType tripType() {
        return this.tripType;
    }

    public String businessTripNote() {
        return this.businessTripNote;
    }

    public Boolean isBusinessTravelPaymentMethod() {
        return this.isBusinessTravelPaymentMethod;
    }

    public Boolean requiresVerifications() {
        return this.requiresVerifications;
    }

    public Boolean usesIdentityFlow() {
        return this.usesIdentityFlow;
    }

    public String toString() {
        return "ReservationDetails{reservationId=" + this.reservationId + ", confirmationCode=" + this.confirmationCode + ", listingId=" + this.listingId + ", guestId=" + this.guestId + ", specialOfferId=" + this.specialOfferId + ", checkIn=" + this.checkIn + ", checkOut=" + this.checkOut + ", numberOfAdults=" + this.numberOfAdults + ", numberOfChildren=" + this.numberOfChildren + ", numberOfInfants=" + this.numberOfInfants + ", isBringingPets=" + this.isBringingPets + ", totalPrice=" + this.totalPrice + ", currency=" + this.currency + ", fxCopy=" + this.fxCopy + ", paymentInstrument=" + this.paymentInstrument + ", checkInHour=" + this.checkInHour + ", messageToHost=" + this.messageToHost + ", tripPurpose=" + this.tripPurpose + ", agreedToHouseRules=" + this.agreedToHouseRules + ", requiresIdentifications=" + this.requiresIdentifications + ", identifications=" + this.identifications + ", isCheckInTimeRequired=" + this.isCheckInTimeRequired + ", confirmedPhoneNumber=" + this.confirmedPhoneNumber + ", confirmedEmailAddress=" + this.confirmedEmailAddress + ", providedGovernmentId=" + this.providedGovernmentId + ", isMessageHostRequired=" + this.isMessageHostRequired + ", tripType=" + this.tripType + ", businessTripNote=" + this.businessTripNote + ", isBusinessTravelPaymentMethod=" + this.isBusinessTravelPaymentMethod + ", requiresVerifications=" + this.requiresVerifications + ", usesIdentityFlow=" + this.usesIdentityFlow + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReservationDetails)) {
            return false;
        }
        ReservationDetails that = (ReservationDetails) o;
        if (this.reservationId != null ? this.reservationId.equals(that.reservationId()) : that.reservationId() == null) {
            if (this.confirmationCode != null ? this.confirmationCode.equals(that.confirmationCode()) : that.confirmationCode() == null) {
                if (this.listingId != null ? this.listingId.equals(that.listingId()) : that.listingId() == null) {
                    if (this.guestId != null ? this.guestId.equals(that.guestId()) : that.guestId() == null) {
                        if (this.specialOfferId != null ? this.specialOfferId.equals(that.specialOfferId()) : that.specialOfferId() == null) {
                            if (this.checkIn != null ? this.checkIn.equals(that.checkIn()) : that.checkIn() == null) {
                                if (this.checkOut != null ? this.checkOut.equals(that.checkOut()) : that.checkOut() == null) {
                                    if (this.numberOfAdults != null ? this.numberOfAdults.equals(that.numberOfAdults()) : that.numberOfAdults() == null) {
                                        if (this.numberOfChildren != null ? this.numberOfChildren.equals(that.numberOfChildren()) : that.numberOfChildren() == null) {
                                            if (this.numberOfInfants != null ? this.numberOfInfants.equals(that.numberOfInfants()) : that.numberOfInfants() == null) {
                                                if (this.isBringingPets != null ? this.isBringingPets.equals(that.isBringingPets()) : that.isBringingPets() == null) {
                                                    if (this.totalPrice != null ? this.totalPrice.equals(that.totalPrice()) : that.totalPrice() == null) {
                                                        if (this.currency != null ? this.currency.equals(that.currency()) : that.currency() == null) {
                                                            if (this.fxCopy != null ? this.fxCopy.equals(that.fxCopy()) : that.fxCopy() == null) {
                                                                if (this.paymentInstrument != null ? this.paymentInstrument.equals(that.paymentInstrument()) : that.paymentInstrument() == null) {
                                                                    if (this.checkInHour != null ? this.checkInHour.equals(that.checkInHour()) : that.checkInHour() == null) {
                                                                        if (this.messageToHost != null ? this.messageToHost.equals(that.messageToHost()) : that.messageToHost() == null) {
                                                                            if (this.tripPurpose != null ? this.tripPurpose.equals(that.tripPurpose()) : that.tripPurpose() == null) {
                                                                                if (this.agreedToHouseRules != null ? this.agreedToHouseRules.equals(that.agreedToHouseRules()) : that.agreedToHouseRules() == null) {
                                                                                    if (this.requiresIdentifications != null ? this.requiresIdentifications.equals(that.requiresIdentifications()) : that.requiresIdentifications() == null) {
                                                                                        if (this.identifications != null ? this.identifications.equals(that.identifications()) : that.identifications() == null) {
                                                                                            if (this.isCheckInTimeRequired != null ? this.isCheckInTimeRequired.equals(that.isCheckInTimeRequired()) : that.isCheckInTimeRequired() == null) {
                                                                                                if (this.confirmedPhoneNumber != null ? this.confirmedPhoneNumber.equals(that.confirmedPhoneNumber()) : that.confirmedPhoneNumber() == null) {
                                                                                                    if (this.confirmedEmailAddress != null ? this.confirmedEmailAddress.equals(that.confirmedEmailAddress()) : that.confirmedEmailAddress() == null) {
                                                                                                        if (this.providedGovernmentId != null ? this.providedGovernmentId.equals(that.providedGovernmentId()) : that.providedGovernmentId() == null) {
                                                                                                            if (this.isMessageHostRequired != null ? this.isMessageHostRequired.equals(that.isMessageHostRequired()) : that.isMessageHostRequired() == null) {
                                                                                                                if (this.tripType != null ? this.tripType.equals(that.tripType()) : that.tripType() == null) {
                                                                                                                    if (this.businessTripNote != null ? this.businessTripNote.equals(that.businessTripNote()) : that.businessTripNote() == null) {
                                                                                                                        if (this.isBusinessTravelPaymentMethod != null ? this.isBusinessTravelPaymentMethod.equals(that.isBusinessTravelPaymentMethod()) : that.isBusinessTravelPaymentMethod() == null) {
                                                                                                                            if (this.requiresVerifications != null ? this.requiresVerifications.equals(that.requiresVerifications()) : that.requiresVerifications() == null) {
                                                                                                                                if (this.usesIdentityFlow == null) {
                                                                                                                                    if (that.usesIdentityFlow() == null) {
                                                                                                                                        return true;
                                                                                                                                    }
                                                                                                                                } else if (this.usesIdentityFlow.equals(that.usesIdentityFlow())) {
                                                                                                                                    return true;
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((1 * 1000003) ^ (this.reservationId == null ? 0 : this.reservationId.hashCode())) * 1000003) ^ (this.confirmationCode == null ? 0 : this.confirmationCode.hashCode())) * 1000003) ^ (this.listingId == null ? 0 : this.listingId.hashCode())) * 1000003) ^ (this.guestId == null ? 0 : this.guestId.hashCode())) * 1000003) ^ (this.specialOfferId == null ? 0 : this.specialOfferId.hashCode())) * 1000003) ^ (this.checkIn == null ? 0 : this.checkIn.hashCode())) * 1000003) ^ (this.checkOut == null ? 0 : this.checkOut.hashCode())) * 1000003) ^ (this.numberOfAdults == null ? 0 : this.numberOfAdults.hashCode())) * 1000003) ^ (this.numberOfChildren == null ? 0 : this.numberOfChildren.hashCode())) * 1000003) ^ (this.numberOfInfants == null ? 0 : this.numberOfInfants.hashCode())) * 1000003) ^ (this.isBringingPets == null ? 0 : this.isBringingPets.hashCode())) * 1000003) ^ (this.totalPrice == null ? 0 : this.totalPrice.hashCode())) * 1000003) ^ (this.currency == null ? 0 : this.currency.hashCode())) * 1000003) ^ (this.fxCopy == null ? 0 : this.fxCopy.hashCode())) * 1000003) ^ (this.paymentInstrument == null ? 0 : this.paymentInstrument.hashCode())) * 1000003) ^ (this.checkInHour == null ? 0 : this.checkInHour.hashCode())) * 1000003) ^ (this.messageToHost == null ? 0 : this.messageToHost.hashCode())) * 1000003) ^ (this.tripPurpose == null ? 0 : this.tripPurpose.hashCode())) * 1000003) ^ (this.agreedToHouseRules == null ? 0 : this.agreedToHouseRules.hashCode())) * 1000003) ^ (this.requiresIdentifications == null ? 0 : this.requiresIdentifications.hashCode())) * 1000003) ^ (this.identifications == null ? 0 : this.identifications.hashCode())) * 1000003) ^ (this.isCheckInTimeRequired == null ? 0 : this.isCheckInTimeRequired.hashCode())) * 1000003) ^ (this.confirmedPhoneNumber == null ? 0 : this.confirmedPhoneNumber.hashCode())) * 1000003) ^ (this.confirmedEmailAddress == null ? 0 : this.confirmedEmailAddress.hashCode())) * 1000003) ^ (this.providedGovernmentId == null ? 0 : this.providedGovernmentId.hashCode())) * 1000003) ^ (this.isMessageHostRequired == null ? 0 : this.isMessageHostRequired.hashCode())) * 1000003) ^ (this.tripType == null ? 0 : this.tripType.hashCode())) * 1000003) ^ (this.businessTripNote == null ? 0 : this.businessTripNote.hashCode())) * 1000003) ^ (this.isBusinessTravelPaymentMethod == null ? 0 : this.isBusinessTravelPaymentMethod.hashCode())) * 1000003) ^ (this.requiresVerifications == null ? 0 : this.requiresVerifications.hashCode())) * 1000003;
        if (this.usesIdentityFlow != null) {
            i = this.usesIdentityFlow.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.core.models.ReservationDetails.Builder toBuilder() {
        return new Builder(this);
    }
}
