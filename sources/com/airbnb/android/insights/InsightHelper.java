package com.airbnb.android.insights;

import android.os.Bundle;
import android.util.SparseArray;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarDay.AvailabilityType;
import com.airbnb.android.core.models.CalendarDayPriceInfo;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.CalendarUpdateOperationsRequest.CalendarUpdateRequestBuilder;
import com.airbnb.android.core.requests.CreateSmartPromotionRequest;
import com.airbnb.android.core.requests.DeleteSmartPromotionRequest;
import com.airbnb.android.core.requests.DemandBasedPricingRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import java.util.ArrayList;
import java.util.List;

public class InsightHelper {
    public static final String ARG_CALENDAR_DAY = "calendar_day";
    public static final String ARG_SMART_PROMO_ID = "smart_promo_id";

    public static BaseRequestV2 getUpdateRequestForStory(Insight story, Bundle extraInsightInfo) {
        long listingId = story.getListingId();
        switch (story.getStoryConversionType()) {
            case UnblockNightsForDateRange:
                List<AirDate> dateRange = story.getConversionPayload().getDateRange();
                return new CalendarUpdateRequestBuilder().listingId(listingId).dates(CalendarUpdateRequestBuilder.dateRangeToList((AirDate) dateRange.get(0), (AirDate) dateRange.get(1))).availability(AvailabilityType.Available).build();
            case SetSmartPricingMinPrice:
                return DemandBasedPricingRequest.updateMinPrice(story.getConversionPayload().getIntegerValue(), listingId);
            case SetWeeklyDiscount:
                return UpdateListingRequest.forField(listingId, "weekly_price_factor", Double.valueOf(1.0d - (((double) story.getConversionPayload().getIntegerValue()) / 100.0d)));
            case SetPricingTipForMonth:
                return new CalendarUpdateRequestBuilder().listingId(listingId).priceToDateMap(getPriceToDates(extraInsightInfo.getParcelableArrayList(ARG_CALENDAR_DAY), false)).build();
            case SetSmartPromotion:
                return CreateSmartPromotionRequest.forInsight(story);
            case SetBasePrice:
                return UpdateListingRequest.forField(story.getListingId(), ListingRequestConstants.JSON_PRICE_KEY, Integer.valueOf(story.getConversionPayload().getIntegerValue()));
            default:
                throw new UnsupportedOperationException("No request available for this story type: " + story.getStoryConversionType());
        }
    }

    public static BaseRequestV2 getUndoRequestForStory(Insight story, Bundle extraInsightInfo) {
        long listingId = story.getListingId();
        Listing listing = story.getListing();
        switch (story.getStoryConversionType()) {
            case UnblockNightsForDateRange:
                List<AirDate> dateRange = story.getConversionPayload().getDateRange();
                return new CalendarUpdateRequestBuilder().listingId(listingId).dates(CalendarUpdateRequestBuilder.dateRangeToList((AirDate) dateRange.get(0), (AirDate) dateRange.get(1))).availability(AvailabilityType.Available).build();
            case SetSmartPricingMinPrice:
                return DemandBasedPricingRequest.updateMinPrice(story.getDynamicPricingControl().getMinPrice(), listingId);
            case SetWeeklyDiscount:
                return UpdateListingRequest.forField(listingId, "weekly_price_factor", listing.getWeeklyPriceFactor().getFactorValue());
            case SetPricingTipForMonth:
                return new CalendarUpdateRequestBuilder().listingId(listingId).priceToDateMap(getPriceToDates(extraInsightInfo.getParcelableArrayList(ARG_CALENDAR_DAY), true)).build();
            case SetSmartPromotion:
                return DeleteSmartPromotionRequest.forInsight(extraInsightInfo.getLong(ARG_SMART_PROMO_ID));
            case SetBasePrice:
                return UpdateListingRequest.forField(listingId, ListingRequestConstants.JSON_PRICE_KEY, Integer.valueOf(listing.getListingPrice()));
            default:
                throw new UnsupportedOperationException("No undo request available for this story type: " + story.getStoryConversionType());
        }
    }

    private static SparseArray<List<AirDate>> getPriceToDates(List<CalendarDay> calendarDays, boolean isUndo) {
        SparseArray<List<AirDate>> priceToDates = new SparseArray<>();
        for (CalendarDay day : calendarDays) {
            CalendarDayPriceInfo priceInfo = day.getPriceInfo();
            int price = isUndo ? priceInfo.getNativePrice() : priceInfo.getNativeSuggestedPrice();
            List<AirDate> dates = (List) priceToDates.get(price);
            if (dates == null) {
                dates = new ArrayList<>();
                priceToDates.put(price, dates);
            }
            dates.add(day.getDate());
        }
        return priceToDates;
    }
}
