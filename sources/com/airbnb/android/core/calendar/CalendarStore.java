package com.airbnb.android.core.calendar;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.analytics.CalendarAnalytics;
import com.airbnb.android.core.calendar.CalendarStoreCache.CacheResponseWrapper;
import com.airbnb.android.core.events.AuthStateEvent;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarDay.AvailabilityType;
import com.airbnb.android.core.models.ListingCalendar;
import com.airbnb.android.core.models.NightCount;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CalendarRequest;
import com.airbnb.android.core.requests.CalendarUpdateOperationsRequest;
import com.airbnb.android.core.requests.CalendarUpdateOperationsRequest.CalendarUpdateRequestBuilder;
import com.airbnb.android.core.requests.CalendarUpdateRequestUtil;
import com.airbnb.android.core.requests.NightsCounterRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.CalendarResponse;
import com.airbnb.android.core.responses.CalendarUpdateResponse;
import com.airbnb.android.core.responses.NightsCounterResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.google.common.collect.ImmutableSet;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import p032rx.Observer;

public class CalendarStore {
    /* access modifiers changed from: private */
    public final CalendarStoreCache calendarStoreCache;
    private final CalendarStoreConfig config;

    public CalendarStore(CalendarStoreCache calendarStoreCache2, CalendarStoreConfig config2, Bus bus) {
        this.calendarStoreCache = calendarStoreCache2;
        this.config = config2;
        new Handler(Looper.getMainLooper()).post(CalendarStore$$Lambda$1.lambdaFactory$(this, bus));
    }

    @Subscribe
    public void onAuthStatusChanged(AuthStateEvent event) {
        this.calendarStoreCache.clear();
    }

    public void setCacheResetTime(AirDateTime cacheResetTime) {
        this.calendarStoreCache.setCacheResetTime(cacheResetTime);
    }

    public AirDate getMinDate() {
        return this.config.getMinDate();
    }

    public AirDate getMaxDate() {
        return this.config.getMaxDate();
    }

    public void refreshDays(long listingId, AirDate startDate, AirDate endDate) {
        if (startDate.isBefore(getMinDate())) {
            startDate = getMinDate();
        }
        if (endDate.isAfter(getMaxDate())) {
            endDate = getMaxDate();
        }
        getDaysForListingIds(ImmutableSet.m1299of(Long.valueOf(listingId)), startDate, endDate, true, null, false);
    }

    public void getDaysForListingIds(Set<Long> listingIds, AirDate startDate, AirDate endDate, CalendarStoreListener listener) {
        getDaysForListingIds(listingIds, startDate, endDate, false, listener, false);
    }

    public void getDaysForListingId(long listingId, AirDate startDate, AirDate endDate, CalendarStoreListener listener) {
        getDaysForListingIds(ImmutableSet.m1299of(Long.valueOf(listingId)), startDate, endDate, false, listener, false);
    }

    public void getDaysForListingIds(Set<Long> listingIds, AirDate startDate, AirDate endDate, boolean forceReload, CalendarStoreListener listener, boolean includingNeverPublishedListings) {
        Check.argument(this.config.getMinDate().isSameDayOrBefore(startDate));
        Check.argument(this.config.getMaxDate().isSameDayOrAfter(endDate));
        Check.argument(startDate.isSameDayOrBefore(endDate));
        CacheResponseWrapper response = this.calendarStoreCache.retrieveFromCache(listingIds, startDate, endDate, this.config.getCacheTTLMinutes());
        if (forceReload || response.hasMissingOrExpiredDays()) {
            asyncFetchDates(listingIds, startDate, endDate, listener, includingNeverPublishedListings);
        } else {
            listener.onCalendarResponse(response.getCalendarDaysByListingIds(), response.getNightCountsByListingIds(), startDate, endDate);
        }
    }

    public ListingCalendar getListingCalendar(long listingId) {
        return this.calendarStoreCache.getListingCalendar(listingId);
    }

    public void updateCalendarAvailability(long listingId, List<CalendarDay> daysSetToAvailable, List<CalendarDay> daysSetToUnavailable, CalendarUpdateListener listener) {
        boolean z;
        if (daysSetToAvailable.size() > 0 || daysSetToUnavailable.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        Check.argument(z);
        List<CalendarDay> allDays = new ArrayList<>();
        allDays.addAll(daysSetToAvailable);
        allDays.addAll(daysSetToUnavailable);
        Collections.sort(allDays, CalendarStore$$Lambda$2.lambdaFactory$());
        List<BaseRequestV2<?>> allCalendarUpdateAvailabilityRequests = new ArrayList<>();
        if (daysSetToAvailable.size() > 0) {
            allCalendarUpdateAvailabilityRequests.add(new CalendarUpdateRequestBuilder().listingId(listingId).dates(CalendarUpdateRequestUtil.calendarDaysToAirDates(daysSetToAvailable)).availability(AvailabilityType.Available).build());
        }
        if (daysSetToUnavailable.size() > 0) {
            allCalendarUpdateAvailabilityRequests.add(new CalendarUpdateRequestBuilder().listingId(listingId).dates(CalendarUpdateRequestUtil.calendarDaysToAirDates(daysSetToUnavailable)).availability(AvailabilityType.Unavailable).build());
        }
        updateCalendarBatchRequest(listingId, allCalendarUpdateAvailabilityRequests, ((CalendarDay) allDays.get(0)).getDate(), ((CalendarDay) allDays.get(allDays.size() - 1)).getDate(), listener);
    }

    public void updateCalendar(long listingId, List<CalendarDay> days, AvailabilityType availability, Integer price, Boolean clearDemandBasedPricingOverride, String notes, CalendarUpdateListener listener, boolean appliedPriceTips) {
        Check.argument(days.size() > 0);
        Check.argument((availability != null || price != null || clearDemandBasedPricingOverride != null || notes != null) || appliedPriceTips);
        Collections.sort(days, CalendarStore$$Lambda$3.lambdaFactory$());
        AirDate startDate = ((CalendarDay) days.get(0)).getDate();
        AirDate endDate = ((CalendarDay) days.get(days.size() - 1)).getDate();
        ArrayList arrayList = new ArrayList();
        arrayList.add(getUpdateCalendarRequest(listingId, days, availability, price, clearDemandBasedPricingOverride, notes, appliedPriceTips));
        updateCalendarBatchRequest(listingId, arrayList, startDate, endDate, listener);
    }

    public void updateCalendar(long listingId, List<CalendarDay> days, AvailabilityType availability, Integer price, Boolean clearDemandBasedPricingOverride, String notes, CalendarUpdateListener listener) {
        updateCalendar(listingId, days, availability, price, clearDemandBasedPricingOverride, notes, listener, false);
    }

    private void updateCalendarBatchRequest(long listingId, List<BaseRequestV2<?>> requests, AirDate startDate, AirDate endDate, CalendarUpdateListener listener) {
        if (!requests.isEmpty()) {
            final long j = listingId;
            final CalendarUpdateListener calendarUpdateListener = listener;
            final AirDate airDate = startDate;
            final AirDate airDate2 = endDate;
            new AirBatchRequest(requests, new NonResubscribableRequestListener<AirBatchResponse>() {
                public void onResponse(AirBatchResponse data) {
                    CalendarAnalytics.trackChangeSuccess(j);
                    Set<Long> listingIds = Collections.singleton(Long.valueOf(j));
                    if (calendarUpdateListener != null) {
                        calendarUpdateListener.onCalendarUpdateSuccess(listingIds, airDate, airDate2);
                    }
                }

                public void onErrorResponse(AirRequestNetworkException e) {
                    CalendarAnalytics.trackChangeFail(j, e.getMessage());
                    if (calendarUpdateListener != null) {
                        calendarUpdateListener.onCalendarError(e);
                    }
                }
            }).execute(NetworkUtil.singleFireExecutor());
        }
    }

    private CalendarUpdateOperationsRequest getUpdateCalendarRequest(final long listingId, List<CalendarDay> days, AvailabilityType availability, Integer price, Boolean clearDemandBasedPricingOverride, String notes, boolean appliedPriceTips) {
        Check.argument(days.size() > 0);
        NonResubscribableRequestListener<CalendarUpdateResponse> updateResponseListener = new SimpleRequestListener<CalendarUpdateResponse>() {
            public void onResponse(CalendarUpdateResponse response) {
                CalendarStore.this.calendarStoreCache.updateDays(listingId, response.getUpdatedDays());
            }
        };
        CalendarUpdateRequestBuilder requestBuilder = new CalendarUpdateRequestBuilder().listingId(listingId).availability(availability).isSmartPricingOn(clearDemandBasedPricingOverride).notes(notes);
        if (appliedPriceTips) {
            requestBuilder.priceToDateMap(CalendarUpdateRequestUtil.groupSimilarSuggestedPricedDates(days));
        } else {
            requestBuilder.dates(CalendarUpdateRequestUtil.calendarDaysToAirDates(days)).price(price);
        }
        CalendarUpdateOperationsRequest calendarUpdateRequest = requestBuilder.build();
        calendarUpdateRequest.withListener((Observer) updateResponseListener);
        return calendarUpdateRequest;
    }

    public void reloadListingCalendarCacheIfMissing(long listingId) {
        if (this.calendarStoreCache.getListingCalendar(listingId) == null) {
            AirDate firstDayOfMonth = AirDate.today().getFirstDayOfMonth();
            getDaysForListingIds(Collections.emptySet(), firstDayOfMonth.plusDays(-1), firstDayOfMonth.plusMonths(1), true, null, false);
        }
    }

    private void asyncFetchDates(Set<Long> listingIds, AirDate startDate, AirDate endDate, CalendarStoreListener listener, boolean includingNeverPublishedListings) {
        NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(CalendarStore$$Lambda$4.lambdaFactory$(this, listingIds, startDate, endDate, listener)).onError(CalendarStore$$Lambda$5.lambdaFactory$(this, listener)).buildWithoutResubscription();
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(CalendarRequest.forMultiCalendar(listingIds, startDate, endDate, includingNeverPublishedListings));
        requests.add(new NightsCounterRequest());
        new AirBatchRequest(requests, batchListener).execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void onSuccessResponse(Set<Long> listingIds, AirDate startDate, AirDate endDate, CalendarStoreListener listener, AirBatchResponse batchResponse) {
        boolean responseForAllListings = listingIds.isEmpty();
        List<ListingCalendar> calendars = ((CalendarResponse) batchResponse.singleResponse(CalendarResponse.class)).calendars;
        List<NightCount> nightCounts = ((NightsCounterResponse) batchResponse.singleResponse(NightsCounterResponse.class)).nightCounts;
        this.calendarStoreCache.updateCalendars(calendars, responseForAllListings);
        this.calendarStoreCache.updateNightsCount(nightCounts, responseForAllListings);
        CacheResponseWrapper dataFromCache = this.calendarStoreCache.retrieveFromCache(listingIds, startDate, endDate, this.config.getCacheTTLMinutes());
        if (listener != null) {
            listener.onCalendarResponse(dataFromCache.getCalendarDaysByListingIds(), dataFromCache.getNightCountsByListingIds(), startDate, endDate);
        }
    }

    /* access modifiers changed from: private */
    public void onError(NetworkException e, CalendarStoreListener listener) {
        if (listener != null) {
            listener.onCalendarError(e);
        }
    }
}
