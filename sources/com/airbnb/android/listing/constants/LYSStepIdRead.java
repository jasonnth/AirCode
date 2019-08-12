package com.airbnb.android.listing.constants;

import com.airbnb.android.listing.LYSStep;

public class LYSStepIdRead {
    private static LYSStep stepIdToLYSBasics(String lastFinishedStepId) {
        char c = 65535;
        switch (lastFinishedStepId.hashCode()) {
            case -2100447465:
                if (lastFinishedStepId.equals("BEDROOMS")) {
                    c = 1;
                    break;
                }
                break;
            case -1842863059:
                if (lastFinishedStepId.equals("SPACES")) {
                    c = 6;
                    break;
                }
                break;
            case -1611296843:
                if (lastFinishedStepId.equals("LOCATION")) {
                    c = 4;
                    break;
                }
                break;
            case -1351703305:
                if (lastFinishedStepId.equals("AMENITIES")) {
                    c = 5;
                    break;
                }
                break;
            case -1323092924:
                if (lastFinishedStepId.equals("BED_DETAILS")) {
                    c = 2;
                    break;
                }
                break;
            case 2521307:
                if (lastFinishedStepId.equals("ROOM")) {
                    c = 0;
                    break;
                }
                break;
            case 1960969157:
                if (lastFinishedStepId.equals("BATHROOMS")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return LYSStep.SpaceType;
            case 1:
            case 2:
                return LYSStep.RoomsAndGuests;
            case 3:
                return LYSStep.Bathrooms;
            case 4:
                return LYSStep.Address;
            case 5:
                return LYSStep.Amenities;
            case 6:
                return LYSStep.Spaces;
            default:
                return null;
        }
    }

    private static LYSStep stepIdToLYSMarketing(String lastFinishedStepId) {
        char c = 65535;
        switch (lastFinishedStepId.hashCode()) {
            case -1935704959:
                if (lastFinishedStepId.equals("PHOTOS")) {
                    c = 0;
                    break;
                }
                break;
            case -1672255354:
                if (lastFinishedStepId.equals(LYSStepIdNames.UPDATE_EMAIL)) {
                    c = 5;
                    break;
                }
                break;
            case -1139657850:
                if (lastFinishedStepId.equals("SUMMARY")) {
                    c = 2;
                    break;
                }
                break;
            case -980306980:
                if (lastFinishedStepId.equals(LYSStepIdNames.PROFILE_PHOTO)) {
                    c = 4;
                    break;
                }
                break;
            case -659534496:
                if (lastFinishedStepId.equals("VERIFY_PHONE_NUMBER")) {
                    c = 6;
                    break;
                }
                break;
            case 79833656:
                if (lastFinishedStepId.equals("TITLE")) {
                    c = 3;
                    break;
                }
                break;
            case 1118881944:
                if (lastFinishedStepId.equals(LYSStepIdNames.SUMMARY_HIGHLIGHTS)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return LYSStep.Photos;
            case 2:
                return LYSStep.DescriptionStep;
            case 3:
                return LYSStep.TitleStep;
            case 4:
            case 5:
            case 6:
                return LYSStep.VerificationSteps;
            default:
                return null;
        }
    }

    public static LYSStep stepIdToLYSStepSuperset(String lastFinishedStepId) {
        if (lastFinishedStepId == null) {
            return LYSStep.SpaceType;
        }
        if (stepIdToLYSBasics(lastFinishedStepId) != null) {
            return stepIdToLYSBasics(lastFinishedStepId);
        }
        if (stepIdToLYSMarketing(lastFinishedStepId) != null) {
            return stepIdToLYSMarketing(lastFinishedStepId);
        }
        char c = 65535;
        switch (lastFinishedStepId.hashCode()) {
            case -1448383939:
                if (lastFinishedStepId.equals(LYSStepIdNames.BOOKING_SCENARIOS)) {
                    c = 12;
                    break;
                }
                break;
            case -1262447799:
                if (lastFinishedStepId.equals(LYSStepIdNames.AVAILABILITY_QUESTIONS)) {
                    c = 5;
                    break;
                }
                break;
            case -375950697:
                if (lastFinishedStepId.equals(LYSStepIdNames.REVIEW_HOW_GUESTS_BOOK)) {
                    c = 3;
                    break;
                }
                break;
            case -300256316:
                if (lastFinishedStepId.equals(LYSStepIdNames.CHOOSE_PRICING_MODE)) {
                    c = 9;
                    break;
                }
                break;
            case 7077886:
                if (lastFinishedStepId.equals(LYSStepIdNames.REVIEW_GUEST_REQUIREMENTS)) {
                    c = 2;
                    break;
                }
                break;
            case 49423669:
                if (lastFinishedStepId.equals(LYSStepIdNames.KEEP_CALENDAR_UP_TO_DATE)) {
                    c = 4;
                    break;
                }
                break;
            case 71816119:
                if (lastFinishedStepId.equals("GUEST_REQUIREMENTS")) {
                    c = 0;
                    break;
                }
                break;
            case 76396841:
                if (lastFinishedStepId.equals(LYSStepIdNames.PRICE)) {
                    c = 10;
                    break;
                }
                break;
            case 344736472:
                if (lastFinishedStepId.equals(LYSStepIdNames.HOUSE_RULES)) {
                    c = 1;
                    break;
                }
                break;
            case 604302142:
                if (lastFinishedStepId.equals(LYSStepIdNames.CALENDAR)) {
                    c = 8;
                    break;
                }
                break;
            case 646865086:
                if (lastFinishedStepId.equals("IDENTITY")) {
                    c = 15;
                    break;
                }
                break;
            case 813971196:
                if (lastFinishedStepId.equals(LYSStepIdNames.CALENDAR_SYNC)) {
                    c = 7;
                    break;
                }
                break;
            case 966971577:
                if (lastFinishedStepId.equals("REGISTRATION")) {
                    c = 14;
                    break;
                }
                break;
            case 1728493191:
                if (lastFinishedStepId.equals(LYSStepIdNames.AVAILABILITY_SETTINGS)) {
                    c = 6;
                    break;
                }
                break;
            case 1818112901:
                if (lastFinishedStepId.equals("LOCAL_LAWS")) {
                    c = 13;
                    break;
                }
                break;
            case 1839701902:
                if (lastFinishedStepId.equals(LYSStepIdNames.ADDITIONAL_PRICING)) {
                    c = 11;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return LYSStep.GuestRequirementsStep;
            case 1:
            case 2:
                return LYSStep.HouseRules;
            case 3:
            case 4:
                return LYSStep.HowGuestsBookStep;
            case 5:
                return LYSStep.RentHistoryStep;
            case 6:
            case 7:
                return LYSStep.AvailabilityStep;
            case 8:
                return LYSStep.CalendarStep;
            case 9:
                return LYSStep.SelectPricingType;
            case 10:
                return LYSStep.SetPrice;
            case 11:
                return LYSStep.Discounts;
            case 12:
                return LYSStep.ReviewSettings;
            case 13:
                return LYSStep.LocalLaws;
            case 14:
                return LYSStep.CityRegistration;
            case 15:
                return LYSStep.Identity;
            default:
                return LYSStep.SpaceType;
        }
    }
}
