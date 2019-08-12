package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import java.util.ArrayList;
import java.util.List;

final class AutoValue_ReservationDetails extends C$AutoValue_ReservationDetails {
    public static final Creator<AutoValue_ReservationDetails> CREATOR = new Creator<AutoValue_ReservationDetails>() {
        public AutoValue_ReservationDetails createFromParcel(Parcel in) {
            Boolean bool;
            Boolean bool2;
            Boolean bool3;
            Boolean bool4;
            Boolean bool5;
            Boolean bool6;
            Boolean bool7;
            Boolean bool8;
            Boolean bool9;
            Boolean bool10;
            Boolean bool11;
            boolean z;
            Long l = in.readInt() == 0 ? Long.valueOf(in.readLong()) : null;
            String str = in.readInt() == 0 ? in.readString() : null;
            Long l2 = in.readInt() == 0 ? Long.valueOf(in.readLong()) : null;
            Long l3 = in.readInt() == 0 ? Long.valueOf(in.readLong()) : null;
            Long l4 = in.readInt() == 0 ? Long.valueOf(in.readLong()) : null;
            AirDate airDate = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            AirDate airDate2 = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            Integer num = in.readInt() == 0 ? Integer.valueOf(in.readInt()) : null;
            Integer num2 = in.readInt() == 0 ? Integer.valueOf(in.readInt()) : null;
            Integer num3 = in.readInt() == 0 ? Integer.valueOf(in.readInt()) : null;
            if (in.readInt() == 0) {
                bool = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool = null;
            }
            Integer num4 = in.readInt() == 0 ? Integer.valueOf(in.readInt()) : null;
            String str2 = in.readInt() == 0 ? in.readString() : null;
            String str3 = in.readInt() == 0 ? in.readString() : null;
            OldPaymentInstrument oldPaymentInstrument = in.readInt() == 0 ? (OldPaymentInstrument) in.readSerializable() : null;
            String str4 = in.readInt() == 0 ? in.readString() : null;
            String str5 = in.readInt() == 0 ? in.readString() : null;
            TripPurpose tripPurpose = (TripPurpose) in.readParcelable(TripPurpose.class.getClassLoader());
            if (in.readInt() == 0) {
                bool2 = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool2 = null;
            }
            if (in.readInt() == 0) {
                bool3 = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool3 = null;
            }
            ArrayList readArrayList = in.readArrayList(GuestIdentity.class.getClassLoader());
            if (in.readInt() == 0) {
                bool4 = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool4 = null;
            }
            if (in.readInt() == 0) {
                bool5 = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool5 = null;
            }
            if (in.readInt() == 0) {
                bool6 = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool6 = null;
            }
            if (in.readInt() == 0) {
                bool7 = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool7 = null;
            }
            if (in.readInt() == 0) {
                bool8 = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool8 = null;
            }
            TripType tripType = in.readInt() == 0 ? TripType.valueOf(in.readString()) : null;
            String str6 = in.readInt() == 0 ? in.readString() : null;
            if (in.readInt() == 0) {
                bool9 = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool9 = null;
            }
            if (in.readInt() == 0) {
                bool10 = Boolean.valueOf(in.readInt() == 1);
            } else {
                bool10 = null;
            }
            if (in.readInt() == 0) {
                if (in.readInt() == 1) {
                    z = true;
                } else {
                    z = false;
                }
                bool11 = Boolean.valueOf(z);
            } else {
                bool11 = null;
            }
            return new AutoValue_ReservationDetails(l, str, l2, l3, l4, airDate, airDate2, num, num2, num3, bool, num4, str2, str3, oldPaymentInstrument, str4, str5, tripPurpose, bool2, bool3, readArrayList, bool4, bool5, bool6, bool7, bool8, tripType, str6, bool9, bool10, bool11);
        }

        public AutoValue_ReservationDetails[] newArray(int size) {
            return new AutoValue_ReservationDetails[size];
        }
    };

    AutoValue_ReservationDetails(Long reservationId, String confirmationCode, Long listingId, Long guestId, Long specialOfferId, AirDate checkIn, AirDate checkOut, Integer numberOfAdults, Integer numberOfChildren, Integer numberOfInfants, Boolean isBringingPets, Integer totalPrice, String currency, String fxCopy, OldPaymentInstrument paymentInstrument, String checkInHour, String messageToHost, TripPurpose tripPurpose, Boolean agreedToHouseRules, Boolean requiresIdentifications, List<GuestIdentity> identifications, Boolean isCheckInTimeRequired, Boolean confirmedPhoneNumber, Boolean confirmedEmailAddress, Boolean providedGovernmentId, Boolean isMessageHostRequired, TripType tripType, String businessTripNote, Boolean isBusinessTravelPaymentMethod, Boolean requiresVerifications, Boolean usesIdentityFlow) {
        super(reservationId, confirmationCode, listingId, guestId, specialOfferId, checkIn, checkOut, numberOfAdults, numberOfChildren, numberOfInfants, isBringingPets, totalPrice, currency, fxCopy, paymentInstrument, checkInHour, messageToHost, tripPurpose, agreedToHouseRules, requiresIdentifications, identifications, isCheckInTimeRequired, confirmedPhoneNumber, confirmedEmailAddress, providedGovernmentId, isMessageHostRequired, tripType, businessTripNote, isBusinessTravelPaymentMethod, requiresVerifications, usesIdentityFlow);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        if (reservationId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeLong(reservationId().longValue());
        }
        if (confirmationCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(confirmationCode());
        }
        if (listingId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeLong(listingId().longValue());
        }
        if (guestId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeLong(guestId().longValue());
        }
        if (specialOfferId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeLong(specialOfferId().longValue());
        }
        dest.writeParcelable(checkIn(), flags);
        dest.writeParcelable(checkOut(), flags);
        if (numberOfAdults() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(numberOfAdults().intValue());
        }
        if (numberOfChildren() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(numberOfChildren().intValue());
        }
        if (numberOfInfants() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(numberOfInfants().intValue());
        }
        if (isBringingPets() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(isBringingPets().booleanValue() ? 1 : 0);
        }
        if (totalPrice() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(totalPrice().intValue());
        }
        if (currency() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(currency());
        }
        if (fxCopy() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(fxCopy());
        }
        if (paymentInstrument() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeSerializable(paymentInstrument());
        }
        if (checkInHour() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(checkInHour());
        }
        if (messageToHost() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(messageToHost());
        }
        dest.writeParcelable(tripPurpose(), flags);
        if (agreedToHouseRules() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(agreedToHouseRules().booleanValue() ? 1 : 0);
        }
        if (requiresIdentifications() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(requiresIdentifications().booleanValue() ? 1 : 0);
        }
        dest.writeList(identifications());
        if (isCheckInTimeRequired() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(isCheckInTimeRequired().booleanValue() ? 1 : 0);
        }
        if (confirmedPhoneNumber() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(confirmedPhoneNumber().booleanValue() ? 1 : 0);
        }
        if (confirmedEmailAddress() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(confirmedEmailAddress().booleanValue() ? 1 : 0);
        }
        if (providedGovernmentId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(providedGovernmentId().booleanValue() ? 1 : 0);
        }
        if (isMessageHostRequired() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(isMessageHostRequired().booleanValue() ? 1 : 0);
        }
        if (tripType() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(tripType().name());
        }
        if (businessTripNote() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(businessTripNote());
        }
        if (isBusinessTravelPaymentMethod() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(isBusinessTravelPaymentMethod().booleanValue() ? 1 : 0);
        }
        if (requiresVerifications() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(requiresVerifications().booleanValue() ? 1 : 0);
        }
        if (usesIdentityFlow() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        if (!usesIdentityFlow().booleanValue()) {
            i = 0;
        }
        dest.writeInt(i);
    }

    public int describeContents() {
        return 0;
    }
}
