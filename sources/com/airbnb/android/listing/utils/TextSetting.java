package com.airbnb.android.listing.utils;

import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.SelectListing;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.listing.C7213R;
import p032rx.functions.Func1;

public enum TextSetting {
    Name(NavigationTag.ManageListingTitle, RequestType.UpdateListing, "name", C7213R.string.manage_listing_setting_name_title, C7213R.string.manage_listing_setting_name_hint, TextSetting$$Lambda$1.lambdaFactory$(), true, 1, 50),
    Summary(NavigationTag.ManageListingSummary, RequestType.UpdateListing, "summary", C7213R.string.manage_listing_setting_summary_title, C7213R.string.manage_listing_setting_summary_hint, TextSetting$$Lambda$2.lambdaFactory$(), false, 1, 500),
    SelectSummary(NavigationTag.ManageListingSelectSummary, RequestType.UpdateSelectListing, "summary", C7213R.string.manage_listing_setting_summary_title, C7213R.string.manage_listing_setting_summary_hint, null, TextSetting$$Lambda$3.lambdaFactory$(), false, 1, 250),
    TheSpace(NavigationTag.ManageListingTheSpace, RequestType.UpdateListing, ListingRequestConstants.JSON_SPACE_KEY, C7213R.string.manage_listing_setting_the_space_title, C7213R.string.manage_listing_setting_the_space_hint, TextSetting$$Lambda$4.lambdaFactory$()),
    GuestAccess(NavigationTag.ManageListingGuestAccess, RequestType.UpdateListing, ListingRequestConstants.JSON_ACCESS_KEY, C7213R.string.manage_listing_setting_guest_access_title, C7213R.string.manage_listing_setting_guest_access_hint, TextSetting$$Lambda$5.lambdaFactory$()),
    InteractionWithGuests(NavigationTag.ManageListingGuestInteraction, RequestType.UpdateListing, ListingRequestConstants.JSON_INTERACTION_KEY, C7213R.string.manage_listing_setting_guest_interaction_title, C7213R.string.manage_listing_setting_guest_interaction_hint, TextSetting$$Lambda$6.lambdaFactory$()),
    OtherThingsToNote(NavigationTag.ManageListingOtherThingsToNote, RequestType.UpdateListing, "notes", C7213R.string.manage_listing_setting_other_things_to_note_title, C7213R.string.manage_listing_setting_other_things_to_note_hint, TextSetting$$Lambda$7.lambdaFactory$()),
    NeighborhoodOverview(NavigationTag.ManageListingNeighborhoodOverview, RequestType.UpdateListing, ListingRequestConstants.JSON_NEIGHBORHOOD_OVERVIEW_KEY, C7213R.string.manage_listing_setting_neighborhood_overview_title, C7213R.string.manage_listing_setting_neighborhood_overview_hint, TextSetting$$Lambda$8.lambdaFactory$()),
    GettingAround(NavigationTag.ManageListingGettingAround, RequestType.UpdateListing, ListingRequestConstants.JSON_TRANSIT_KEY, C7213R.string.manage_listing_setting_getting_around_title, C7213R.string.manage_listing_setting_getting_around_hint, TextSetting$$Lambda$9.lambdaFactory$()),
    PrebookingMessage(NavigationTag.ManageListingPrebookingMessage, RequestType.UpdateListing, ListingRequestConstants.JSON_INSTANT_BOOK_WELCOME_MESSAGE_KEY, C7213R.string.manage_listing_setting_instant_book_welcome_message_title, C7213R.string.manage_listing_setting_instant_book_welcome_message_hint, TextSetting$$Lambda$10.lambdaFactory$(), false, 0, 150),
    HouseManual(NavigationTag.ManageListingHouseManual, RequestType.UpdateListing, ListingRequestConstants.JSON_HOUSE_MANUAL_KEY, C7213R.string.manage_listing_setting_house_manual_title, C7213R.string.manage_listing_setting_house_manual_hint, TextSetting$$Lambda$11.lambdaFactory$()),
    Directions(NavigationTag.ManageListingDirections, RequestType.UpdateListing, ListingRequestConstants.JSON_DIRECTIONS_KEY, C7213R.string.manage_listing_setting_directions_title, C7213R.string.manage_listing_setting_directions_hint, TextSetting$$Lambda$12.lambdaFactory$()),
    ManageListingAdditionalRules(NavigationTag.ManageListingAdditionalRules, RequestType.UpdateListing, ListingRequestConstants.JSON_HOUSE_RULES_KEY, C7213R.string.manage_listing_setting_additional_rules_title, C7213R.string.manage_listing_setting_additional_rules_hint, TextSetting$$Lambda$13.lambdaFactory$()),
    LYSAdditionalRules(NavigationTag.LYSAdditionalHouseRules, RequestType.UpdateListing, ListingRequestConstants.JSON_HOUSE_RULES_KEY, C7213R.string.manage_listing_setting_additional_rules_title, C7213R.string.manage_listing_setting_additional_rules_hint, TextSetting$$Lambda$14.lambdaFactory$()),
    ManageListingPreBookingGreeting(NavigationTag.ManageListingPrebookingMessage, RequestType.UpdateListing, ListingRequestConstants.JSON_INSTANT_BOOK_WELCOME_MESSAGE_KEY, C7213R.string.manage_listing_prebooking_add_greeting_title, C7213R.string.manage_listing_prebooking_add_greeting_hint, TextSetting$$Lambda$15.lambdaFactory$(), false, 0, 150),
    ManageListingPreBookingCustomQuestion(NavigationTag.ManageListingPrebookingAddCustomQuestion, RequestType.UpdateBookingSettings, null, C7213R.string.manage_listing_prebooking_add_custom_question_title, C7213R.string.manage_listing_prebooking_add_custom_question_hint, TextSetting$$Lambda$16.lambdaFactory$());
    
    private final Func1<SelectListing, String> fetchSelectValue;
    private final Func1<Listing, String> fetchValue;
    public final String fieldKey;
    public final int hintRes;
    public final int maxCharacters;
    public final int minCharacters;
    public final NavigationTag navigationTag;
    public final RequestType requestType;
    public final boolean singleLine;
    public final int titleRes;

    public enum RequestType {
        private static final /* synthetic */ RequestType[] $VALUES = null;
        public static final RequestType UpdateBookingSettings = null;
        public static final RequestType UpdateListing = null;
        public static final RequestType UpdateSelectListing = null;

        private RequestType(String str, int i) {
        }

        public static RequestType valueOf(String name) {
            return (RequestType) Enum.valueOf(RequestType.class, name);
        }

        public static RequestType[] values() {
            return (RequestType[]) $VALUES.clone();
        }

        static {
            UpdateListing = new RequestType("UpdateListing", 0);
            UpdateBookingSettings = new RequestType("UpdateBookingSettings", 1);
            UpdateSelectListing = new RequestType("UpdateSelectListing", 2);
            $VALUES = new RequestType[]{UpdateListing, UpdateBookingSettings, UpdateSelectListing};
        }
    }

    private TextSetting(NavigationTag navigationTag2, RequestType requestType2, String fieldKey2, int titleRes2, int hintRes2, Func1<Listing, String> fetchValue2) {
        this(r13, r14, navigationTag2, requestType2, fieldKey2, titleRes2, hintRes2, fetchValue2, false, 0, -1);
    }

    private TextSetting(NavigationTag navigationTag2, RequestType requestType2, String fieldKey2, int titleRes2, int hintRes2, Func1<Listing, String> fetchValue2, boolean singleLine2, int minCharacters2, int maxCharacters2) {
        this(r14, r15, navigationTag2, requestType2, fieldKey2, titleRes2, hintRes2, fetchValue2, null, singleLine2, minCharacters2, maxCharacters2);
    }

    private TextSetting(NavigationTag navigationTag2, RequestType requestType2, String fieldKey2, int titleRes2, int hintRes2, Func1<Listing, String> fetchValue2, Func1<SelectListing, String> fetchSelectValue2, boolean singleLine2, int minCharacters2, int maxCharacters2) {
        this.navigationTag = navigationTag2;
        this.requestType = requestType2;
        this.fieldKey = fieldKey2;
        this.titleRes = titleRes2;
        this.hintRes = hintRes2;
        this.singleLine = singleLine2;
        this.minCharacters = minCharacters2;
        this.maxCharacters = maxCharacters2;
        this.fetchValue = fetchValue2;
        this.fetchSelectValue = fetchSelectValue2;
    }

    public String getExistingValue(Listing listing) {
        return getExistingValue(listing, null);
    }

    public String getExistingValue(Listing listing, SelectListing selectListing) {
        if (this.fetchValue != null) {
            return (String) this.fetchValue.call(listing);
        }
        if (this.fetchSelectValue == null || selectListing == null) {
            return null;
        }
        return (String) this.fetchSelectValue.call(selectListing);
    }
}
