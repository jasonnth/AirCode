package com.airbnb.android.lib.paidamenities.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.PaidAmenityOrder;
import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import java.lang.reflect.Type;
import java.util.EnumSet;
import java.util.List;

public class UpdatePaidAmenityOrderRequest extends BaseRequestV2<Object> {
    private final PaidAmenityOrder paidAmenityOrder;
    private final Object requestBody;

    private static class CancelAndDeclineRequestBody {
        @JsonProperty("cancel")
        boolean isStatusCancelOrDecline;
        @JsonProperty("cancellation_type")
        int paidAmenityServerStatus;

        private CancelAndDeclineRequestBody(int paidAmenityServerStatus2, boolean isStatusCancelOrDecline2) {
            this.paidAmenityServerStatus = paidAmenityServerStatus2;
            this.isStatusCancelOrDecline = isStatusCancelOrDecline2;
        }
    }

    private static class UpdateContent {
        @JsonProperty("bill_item_token")
        String billItemToken;
        @JsonProperty("status_code")
        int paidAmenityServerStatus;
        @JsonProperty("product_type")
        String productType;

        /* access modifiers changed from: private */
        public static UpdateContent with(int paidAmenityServerStatus2, String productType2, String billItemToken2) {
            return new UpdateContent(paidAmenityServerStatus2, productType2, billItemToken2);
        }

        private UpdateContent(int paidAmenityServerStatus2, String productType2, String billItemToken2) {
            this.paidAmenityServerStatus = paidAmenityServerStatus2;
            this.productType = productType2;
            this.billItemToken = billItemToken2;
        }
    }

    private static class UpdateRequestBody {
        @JsonProperty("updates")
        List<UpdateContent> updates;

        private UpdateRequestBody(UpdateContent... updates2) {
            this.updates = Lists.newArrayList((E[]) updates2);
        }
    }

    public enum PaidAmenityOrderServerStatus {
        STATUS_PENDING(1),
        STATUS_ACCEPTED(2),
        STATUS_DECLINED(3),
        STATUS_CANCELLED_BY_HOST(4),
        STATUS_CANCELLED_BY_GUEST(5),
        STATUS_CANCELLED_BY_ADMIN(6);
        
        private final int statusCode;

        private PaidAmenityOrderServerStatus(int statusCode2) {
            this.statusCode = statusCode2;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public boolean isStatusCancelOrDecline() {
            return EnumSet.of(STATUS_DECLINED, STATUS_CANCELLED_BY_HOST, STATUS_CANCELLED_BY_ADMIN, STATUS_CANCELLED_BY_GUEST).contains(this);
        }
    }

    public static UpdatePaidAmenityOrderRequest forStatus(PaidAmenityOrder paidAmenityOrder2, PaidAmenityOrderServerStatus serverStatus) {
        return new UpdatePaidAmenityOrderRequest(paidAmenityOrder2, serverStatus);
    }

    private UpdatePaidAmenityOrderRequest(PaidAmenityOrder paidAmenityOrder2, PaidAmenityOrderServerStatus serverStatus) {
        this.paidAmenityOrder = paidAmenityOrder2;
        if (serverStatus.isStatusCancelOrDecline()) {
            this.requestBody = new CancelAndDeclineRequestBody(serverStatus.getStatusCode(), serverStatus.isStatusCancelOrDecline());
            return;
        }
        this.requestBody = new UpdateRequestBody(new UpdateContent[]{UpdateContent.with(serverStatus.getStatusCode(), BillProductType.PaidAmenity.getServerKey(), paidAmenityOrder2.getBillItemToken())});
    }

    public Type successResponseType() {
        return Object.class;
    }

    public String getPath() {
        return "bills/" + this.paidAmenityOrder.getBillToken();
    }

    public Object getBody() {
        return this.requestBody;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }
}
