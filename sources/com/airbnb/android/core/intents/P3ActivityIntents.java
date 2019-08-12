package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.GeneralAnalytics;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.arguments.P3Arguments.Builder;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.deeplinks.WebLink;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.webintent.WebIntentUtil;
import com.mparticle.commerce.Product;

public final class P3ActivityIntents {
    public static final String CHECK_IN_DATE_KEY = "check_in_date";
    public static final String CHECK_OUT_DATE_KEY = "check_out_date";

    private P3ActivityIntents() {
    }

    public static Intent withListing(Context context, Listing listing) {
        return withListing(context, listing, null);
    }

    public static Intent withListing(Context context, Listing listing, String from) {
        return P3Arguments.builder().listing(listing).from(from).toIntent(context);
    }

    public static Intent forDeepLink(Context context, Bundle bundle) {
        AirbnbEventLogger.track(GeneralAnalytics.DeepLinking, GeneralAnalytics.AppOpen, "listing_details_intent");
        return forUri(context, DeepLinkUtils.getParamAsId(bundle, "id", "listing_id"), Uri.parse(bundle.getString("deep_link_uri")));
    }

    @WebLink({"rooms/{deep_link_listing_id}", "listings/{deep_link_listing_id}", "rooms/overview/{deep_link_listing_id}", "reservations/create/{deep_link_listing_id}"})
    public static Intent forWebLink(Context context, Bundle bundle) {
        Long listingId = DeepLinkUtils.getLongParam(bundle, "deep_link_listing_id");
        if (listingId == null) {
            return null;
        }
        return forUri(context, listingId.longValue(), Uri.parse(bundle.getString("deep_link_uri")));
    }

    public static Intent forUri(Context context, long listingId, Uri uri) {
        boolean hasDates = true;
        if (listingId <= 0) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Invalid p3 deeplink without listing id:" + uri));
            Toast.makeText(context, C0716R.string.p3_invalid_deeplink, 1).show();
            return HomeActivityIntents.intentForGuestHome(context);
        }
        Builder builder = P3Arguments.builder().from("deep_link").listingId(listingId);
        AirDate checkIn = WebIntentUtil.parseQueryParamAsDate(uri, "check_in", UpdateReviewRequest.KEY_CHECKIN);
        AirDate checkOut = WebIntentUtil.parseQueryParamAsDate(uri, "check_out", Product.CHECKOUT);
        if (checkIn == null || checkOut == null) {
            hasDates = false;
        }
        if (!hasDates || checkIn.isSameDayOrAfter(checkOut) || checkIn.isBefore(AirDate.today())) {
            checkIn = null;
            checkOut = null;
        }
        builder.guestDetails(WebIntentUtil.getGuestDetailsFromUriParams(uri));
        String firstVerificationStep = uri.getQueryParameter("first_verification_step");
        return builder.checkInDate(checkIn).checkOutDate(checkOut).firstVerificationStep(firstVerificationStep).phoneVerificationCode(uri.getQueryParameter("phone_verification_code")).toIntent(context);
    }

    public static Intent withListingPricingQuoteAndExploreData(Context context, Listing listing, PricingQuote pricingQuote, TopLevelSearchParams searchParams, String searchSessionId, String from) {
        AirDate checkoutDate = searchParams.checkOutDate();
        if (searchParams.checkInDate() != null && searchParams.checkOutDate() == null) {
            checkoutDate = searchParams.checkInDate().plusDays(1);
        }
        return P3Arguments.builder().listing(listing).pricingQuote(pricingQuote).checkInDate(searchParams.checkInDate()).checkOutDate(checkoutDate).guestDetails(searchParams.guestDetails()).searchSessionId(searchSessionId).from(from).toIntent(context);
    }

    public static Intent withListingPricingAndGuests(Context context, Listing listing, PricingQuote pricingQuote, GuestDetails guests, String from) {
        return P3Arguments.builder().listing(listing).pricingQuote(pricingQuote).checkInDate(pricingQuote.getCheckIn()).checkOutDate(pricingQuote.getCheckOut()).guestDetails(guests).from(from).toIntent(context);
    }

    public static Intent withListingAndPricingQuote(Context context, Listing listing, PricingQuote pricingQuote, String from) {
        return P3Arguments.builder().listing(listing).pricingQuote(pricingQuote).from(from).toIntent(context);
    }

    public static Intent withListingId(Context context, long listingId) {
        return withListingId(context, listingId, null);
    }

    public static Intent withListingId(Context context, long listingId, String from) {
        return P3Arguments.builder().listingId(listingId).from(from).toIntent(context);
    }

    public static Intent withListingAndPricingQuoteAndPastReservationData(Context context, Listing listing, PricingQuote pricingQuote, AirDate checkInDate, AirDate checkOutDate, GuestDetails guestDetails) {
        return P3Arguments.builder().listing(listing).pricingQuote(pricingQuote).checkInDate(checkInDate).checkOutDate(checkOutDate).guestDetails(guestDetails).from("guest_recovery").toIntent(context);
    }
}
