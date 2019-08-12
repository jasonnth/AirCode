package com.airbnb.android.core.requests;

import android.util.SparseArray;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.CalendarDay.AvailabilityType;
import com.airbnb.android.core.responses.CalendarUpdateResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import retrofit2.Query;

public class CalendarUpdateOperationsRequest extends BaseRequestV2<CalendarUpdateResponse> {
    /* access modifiers changed from: private */
    public final AvailabilityType availability;
    /* access modifiers changed from: private */
    public final List<AirDate> dates;
    /* access modifiers changed from: private */
    public final Boolean isSmartPricingOn;
    /* access modifiers changed from: private */
    public final long listingId;
    /* access modifiers changed from: private */
    public final String notes;
    /* access modifiers changed from: private */
    public final Integer price;
    /* access modifiers changed from: private */
    public final SparseArray<List<AirDate>> priceToDateMap;

    private class Body {
        @JsonProperty
        final long listingId;
        @JsonProperty
        final String method;
        @JsonProperty
        final List<OperationBody> operations;

        private class OperationBody {
            @JsonProperty
            final String availability;
            @JsonProperty
            final Integer dailyPrice;
            @JsonProperty
            final List<AirDate> dates;
            @JsonProperty
            final Boolean demandBasedPricingOverridden;
            @JsonProperty
            final String notes;
            final /* synthetic */ Body this$1;

            private OperationBody(Body body, List<AirDate> dates2, AvailabilityType availabilityType, Integer dailyPrice2, Boolean isSmartPricingOn, String notes2) {
                Boolean bool;
                String str = null;
                this.this$1 = body;
                this.dates = dates2;
                this.dailyPrice = dailyPrice2;
                if (isSmartPricingOn != null) {
                    bool = Boolean.valueOf(!isSmartPricingOn.booleanValue());
                } else {
                    bool = null;
                }
                this.demandBasedPricingOverridden = bool;
                if (availabilityType != null) {
                    str = availabilityType.updateRequestValue;
                }
                this.availability = str;
                this.notes = notes2;
            }
        }

        private Body() {
            this.method = "UPDATE";
            this.listingId = CalendarUpdateOperationsRequest.this.listingId;
            this.operations = CalendarUpdateOperationsRequest.this.priceToDateMap == null ? airDatesToOperations(CalendarUpdateOperationsRequest.this.dates) : priceDateMapToOperations(CalendarUpdateOperationsRequest.this.priceToDateMap);
        }

        private List<OperationBody> airDatesToOperations(List<AirDate> dates) {
            List<OperationBody> operationBodies = new ArrayList<>();
            operationBodies.add(new OperationBody(dates, CalendarUpdateOperationsRequest.this.availability, CalendarUpdateOperationsRequest.this.price == null ? null : CalendarUpdateOperationsRequest.this.price, CalendarUpdateOperationsRequest.this.isSmartPricingOn, CalendarUpdateOperationsRequest.this.notes));
            return operationBodies;
        }

        private List<OperationBody> priceDateMapToOperations(SparseArray<List<AirDate>> similarlyPricedDays) {
            List<OperationBody> operationBodies = new ArrayList<>();
            for (int i = 0; i < similarlyPricedDays.size(); i++) {
                int price = similarlyPricedDays.keyAt(i);
                operationBodies.add(new OperationBody((List) similarlyPricedDays.get(price), CalendarUpdateOperationsRequest.this.availability, Integer.valueOf(price), CalendarUpdateOperationsRequest.this.isSmartPricingOn, CalendarUpdateOperationsRequest.this.notes));
            }
            return operationBodies;
        }
    }

    public static class CalendarUpdateRequestBuilder {
        private AvailabilityType availability;
        private List<AirDate> dates;
        private Boolean isSmartPricingOn;
        private long listingId;
        private String notes;
        private Integer price;
        private SparseArray<List<AirDate>> priceToDateMap;

        public static List<AirDate> dateRangeToList(AirDate startDate, AirDate endDate) {
            List<AirDate> dates2 = new ArrayList<>();
            while (startDate.isBefore(endDate)) {
                dates2.add(startDate);
                startDate = startDate.plusDays(1);
            }
            return dates2;
        }

        public CalendarUpdateRequestBuilder priceToDateMap(SparseArray<List<AirDate>> priceToDateMap2) {
            this.priceToDateMap = priceToDateMap2;
            return this;
        }

        public CalendarUpdateRequestBuilder dates(List<AirDate> dates2) {
            this.dates = dates2;
            return this;
        }

        public CalendarUpdateRequestBuilder isSmartPricingOn(Boolean isSmartPricingOn2) {
            this.isSmartPricingOn = isSmartPricingOn2;
            return this;
        }

        public CalendarUpdateRequestBuilder listingId(long listingId2) {
            this.listingId = listingId2;
            return this;
        }

        public CalendarUpdateRequestBuilder notes(String notes2) {
            this.notes = notes2;
            return this;
        }

        public CalendarUpdateRequestBuilder price(Integer price2) {
            this.price = price2;
            return this;
        }

        public CalendarUpdateRequestBuilder availability(AvailabilityType availability2) {
            this.availability = availability2;
            return this;
        }

        public CalendarUpdateOperationsRequest build() {
            if (this.listingId == 0) {
                throw new IllegalArgumentException("Must specify listing id");
            } else if (this.dates == null && this.priceToDateMap == null) {
                throw new IllegalArgumentException("Must specify either dates or priceToDateMap");
            } else if (this.priceToDateMap == null || (this.price == null && this.dates == null)) {
                return new CalendarUpdateOperationsRequest(this.listingId, this.dates, this.priceToDateMap, this.availability, this.price, this.isSmartPricingOn, this.notes);
            } else {
                throw new IllegalArgumentException("Must specify either a single price and dates or just priceToDateMap");
            }
        }
    }

    private CalendarUpdateOperationsRequest(long listingId2, List<AirDate> dates2, SparseArray<List<AirDate>> priceToDateMap2, AvailabilityType availability2, Integer price2, Boolean isSmartPricingOn2, String notes2) {
        this.dates = dates2;
        this.listingId = listingId2;
        this.isSmartPricingOn = isSmartPricingOn2;
        this.notes = notes2;
        this.availability = availability2;
        this.price = price2;
        this.priceToDateMap = priceToDateMap2;
    }

    public Object getBody() {
        return new Body();
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "host_calendar_detailed");
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getPath() {
        return "calendar_operations";
    }

    public Type successResponseType() {
        return CalendarUpdateResponse.class;
    }

    public AirResponse<CalendarUpdateResponse> transformResponse(AirResponse<CalendarUpdateResponse> response) {
        ((CalendarUpdateResponse) response.body()).updateServerSyncTime();
        return response;
    }
}
