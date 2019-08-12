package com.airbnb.android.insights;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.util.LongSparseArray;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.calendar.CalendarStoreListener;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Insight.ConversionType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.airbnb.android.core.models.NightCount;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.DemandBasedPricingRequest;
import com.airbnb.android.core.requests.LongTermDiscountsConversionRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import com.airbnb.android.core.responses.LongTermDiscountsConversionResponse;
import com.airbnb.android.core.responses.SmartPromotionCreationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.insights.InsightEpoxyModel.LoadingState;
import com.airbnb.android.utils.BundleBuilder;
import icepick.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p032rx.Observer;

public class InsightsDataController {
    @State
    ArrayList<Listing> allListings;
    @State
    HashMap<Long, ListingLongTermDiscountValues> averagePrices = new HashMap<>();
    @State
    HashMap<Long, HashMap<Integer, ArrayList<CalendarDay>>> calendarDays = new HashMap<>();
    CalendarStore calendarStore;
    @State
    int currentListingOffset = 0;
    @State
    int firstListingPosition;
    @State
    HashMap<String, Long> insightIdToSmartPromoId = new HashMap<>();
    private final InsightsAnalytics insightsAnalytics;
    private final Set<InsightsStateChangeListener> listeners = new HashSet();
    @State
    HashSet<Long> retrievedListingsId = new HashSet<>();
    @State
    boolean singleInsightOnly;

    public interface InsightsStateChangeListener {
        void onStateChange(LoadingState loadingState, Insight insight, boolean z);

        void showSnackbarError(NetworkException networkException);
    }

    public InsightsDataController(Context context, Bundle savedInstanceState, InsightsAnalytics insightsAnalytics2) {
        ((InsightsGraph) CoreApplication.instance(context).component()).inject(this);
        this.insightsAnalytics = insightsAnalytics2;
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
    }

    public void setSingleInsightOnly(boolean singleInsightOnly2) {
        this.singleInsightOnly = singleInsightOnly2;
    }

    public boolean isSingleInsightOnly() {
        return this.singleInsightOnly;
    }

    public void setAllListings(ArrayList<Listing> allListings2) {
        this.allListings = allListings2;
    }

    public ArrayList<Listing> getAllListings() {
        return this.allListings;
    }

    public void setFirstListingPosition(int firstListingPosition2) {
        this.firstListingPosition = firstListingPosition2;
    }

    public int getFirstListingPosition() {
        return this.firstListingPosition;
    }

    public void setSmartPromoId(String insightId, long smartPromoId) {
        this.insightIdToSmartPromoId.put(insightId, Long.valueOf(smartPromoId));
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void addRetrievedListingInsights(long listingId) {
        this.retrievedListingsId.add(Long.valueOf(listingId));
    }

    public boolean hasRetrievedListingInsights(long listingId) {
        return this.retrievedListingsId.contains(Long.valueOf(listingId));
    }

    public void addListener(InsightsStateChangeListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(InsightsStateChangeListener listener) {
        this.listeners.remove(listener);
    }

    /* access modifiers changed from: private */
    public void addCalendarDays(ArrayList<CalendarDay> days, int month, long listingId) {
        if (!this.calendarDays.containsKey(Long.valueOf(listingId))) {
            this.calendarDays.put(Long.valueOf(listingId), new HashMap());
        }
        ((HashMap) this.calendarDays.get(Long.valueOf(listingId))).put(Integer.valueOf(month), days);
    }

    public ArrayList<CalendarDay> getCalendarDays(int month, long listingId) {
        return (ArrayList) ((HashMap) this.calendarDays.get(Long.valueOf(listingId))).get(Integer.valueOf(month));
    }

    public boolean hasCalendarDays(int month, long listingId) {
        return this.calendarDays.containsKey(Long.valueOf(listingId)) && ((HashMap) this.calendarDays.get(Long.valueOf(listingId))).containsKey(Integer.valueOf(month));
    }

    public ListingLongTermDiscountValues getAveragePrices(long listingId) {
        return (ListingLongTermDiscountValues) this.averagePrices.get(Long.valueOf(listingId));
    }

    public void fetchCalendarDays(Insight insight, boolean updateInsight) {
        final long listingId = insight.getListingId();
        final AirDate month = insight.getConversionPayload().getMonth();
        final boolean z = updateInsight;
        final Insight insight2 = insight;
        C65471 r0 = new CalendarStoreListener() {
            public void onResponse(LongSparseArray<CalendarDays> calendarDaysByListingId, LongSparseArray<NightCount> longSparseArray, AirDate startDate, AirDate endDate) {
                InsightsDataController.this.addCalendarDays(new ArrayList(((CalendarDays) calendarDaysByListingId.get(listingId)).getCalendarDays()), month.getMonthOfYear(), listingId);
                if (z) {
                    InsightsDataController.this.sendUpdateRequest(insight2);
                } else {
                    InsightsDataController.this.notifyChange(LoadingState.DEFAULT, insight2);
                }
            }

            public void onError(NetworkException e) {
                InsightsDataController.this.handleResponseOnError(e, LoadingState.DONE, insight2);
            }
        };
        r0.setEnabled(true);
        AirDate firstDay = month.getFirstDayOfMonth();
        AirDate today = AirDate.today();
        if (today.isAfter(month.getFirstDayOfMonth())) {
            firstDay = today;
        }
        this.calendarStore.getDaysForListingId(listingId, firstDay, month.getLastDayOfMonth(), r0);
    }

    public void fetchPricingControl(final Insight insight, final boolean sendUpdateRequest) {
        DemandBasedPricingRequest.forFetch(insight.getListingId()).withListener((Observer) new SimpleRequestListener<DemandBasedPricingResponse>() {
            public void onResponse(DemandBasedPricingResponse response) {
                insight.setDynamicPricingControl(response.getPricingControl());
                if (sendUpdateRequest) {
                    InsightsDataController.this.sendUpdateRequest(insight);
                } else {
                    InsightsDataController.this.notifyChange(LoadingState.DEFAULT, insight);
                }
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                InsightsDataController.this.handleResponseOnError(e, LoadingState.DEFAULT, insight);
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    public void fetchPricingControlAndAveragePrices(final Insight insight) {
        final long listingId = insight.getListingId();
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        LongTermDiscountsConversionRequest averagePricesRequest = (LongTermDiscountsConversionRequest) new LongTermDiscountsConversionRequest(listingId, 1.0d, 1.0d).skipCache();
        requests.add((DemandBasedPricingRequest) DemandBasedPricingRequest.forFetch(listingId).skipCache());
        requests.add(averagePricesRequest);
        new AirBatchRequest(requests, new SimpleRequestListener<AirBatchResponse>() {
            public void onResponse(AirBatchResponse response) {
                ListingLongTermDiscountValues averagePrices = ((LongTermDiscountsConversionResponse) response.singleResponse(LongTermDiscountsConversionResponse.class)).values;
                insight.setDynamicPricingControl(((DemandBasedPricingResponse) response.singleResponse(DemandBasedPricingResponse.class)).getPricingControl());
                InsightsDataController.this.averagePrices.put(Long.valueOf(listingId), averagePrices);
                InsightsDataController.this.notifyChange(LoadingState.DEFAULT, insight);
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                InsightsDataController.this.handleResponseOnError(e, LoadingState.DEFAULT, insight);
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    private void notifyChange(LoadingState newState, Insight insight, boolean hasError) {
        for (InsightsStateChangeListener listener : this.listeners) {
            listener.onStateChange(newState, insight, hasError);
        }
    }

    /* access modifiers changed from: private */
    public void notifyChange(LoadingState newState, Insight insight) {
        notifyChange(newState, insight, false);
    }

    public void notifyNetworkError(NetworkException e) {
        for (InsightsStateChangeListener listener : this.listeners) {
            listener.showSnackbarError(e);
        }
    }

    private Bundle getInsightBundle(Insight insight, boolean fromUndo) {
        BundleBuilder builder = new BundleBuilder();
        if (insight.getStoryConversionType() == ConversionType.SetPricingTipForMonth) {
            builder.putParcelableArrayList(InsightHelper.ARG_CALENDAR_DAY, getCalendarDays(insight.getConversionPayload().getMonth().getMonthOfYear(), insight.getListingId()));
        }
        if (fromUndo && insight.getStoryConversionType() == ConversionType.SetSmartPromotion) {
            builder.putLong(InsightHelper.ARG_SMART_PROMO_ID, ((Long) this.insightIdToSmartPromoId.get(insight.getStoryId())).longValue());
        }
        return builder.toBundle();
    }

    public void sendUndoRequest(final Insight insight) {
        notifyChange(LoadingState.UNDO_ACTION_LOADING, insight);
        this.insightsAnalytics.trackUndo(insight);
        InsightHelper.getUndoRequestForStory(insight, getInsightBundle(insight, true)).withListener((Observer) new NonResubscribableRequestListener<BaseResponse>() {
            public void onResponse(BaseResponse data) {
                InsightsDataController.this.bustCalendarCache(insight);
                InsightsDataController.this.notifyChange(LoadingState.DEFAULT, insight);
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                InsightsDataController.this.handleResponseOnError(e, LoadingState.DONE, insight);
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    public void handleUpdateRequest(Insight insight) {
        notifyChange(LoadingState.PRIMARY_ACTION_LOADING, insight);
        long listingId = insight.getListingId();
        if (insight.getStoryConversionType() == ConversionType.SetSmartPricingMinPrice && insight.getDynamicPricingControl() == null) {
            fetchPricingControl(insight, true);
        } else if (insight.getStoryConversionType() != ConversionType.SetPricingTipForMonth || hasCalendarDays(insight.getConversionPayload().getMonth().getMonthOfYear(), listingId)) {
            sendUpdateRequest(insight);
        } else {
            fetchCalendarDays(insight, true);
        }
    }

    /* access modifiers changed from: private */
    public void handleResponseOnError(NetworkException e, LoadingState loadingState, Insight insight) {
        notifyNetworkError(e);
        notifyChange(loadingState, insight, true);
    }

    /* access modifiers changed from: private */
    public void sendUpdateRequest(final Insight insight) {
        this.insightsAnalytics.trackConversion(insight);
        InsightHelper.getUpdateRequestForStory(insight, getInsightBundle(insight, false)).withListener((Observer) new NonResubscribableRequestListener<BaseResponse>() {
            public void onResponse(BaseResponse data) {
                if (insight.getStoryConversionType() == ConversionType.SetSmartPromotion) {
                    InsightsDataController.this.setSmartPromoId(insight.getStoryId(), ((SmartPromotionCreationResponse) data).getSmartPromotion().getPromotionId());
                }
                InsightsDataController.this.bustCalendarCache(insight);
                InsightsDataController.this.notifyChange(LoadingState.DONE, insight);
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                InsightsDataController.this.handleResponseOnError(e, LoadingState.DEFAULT, insight);
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    public Listing getNextListing() {
        int nextListingIndex = ((this.firstListingPosition + this.currentListingOffset) + 1) % this.allListings.size();
        if (nextListingIndex == this.firstListingPosition) {
            return null;
        }
        this.currentListingOffset++;
        return (Listing) this.allListings.get(nextListingIndex);
    }

    /* access modifiers changed from: private */
    public void bustCalendarCache(Insight insight) {
        ConversionType type = insight.getStoryConversionType();
        if (type == ConversionType.SetBasePrice || type == ConversionType.SetPricingTipForMonth) {
            this.calendarStore.setCacheResetTime(AirDateTime.now());
        }
    }
}
