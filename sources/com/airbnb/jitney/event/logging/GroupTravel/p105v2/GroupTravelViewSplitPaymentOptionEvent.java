package com.airbnb.jitney.event.logging.GroupTravel.p105v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentsContext.p189v1.C2543PaymentsContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.GroupTravel.v2.GroupTravelViewSplitPaymentOptionEvent */
public final class GroupTravelViewSplitPaymentOptionEvent implements Struct {
    public static final Adapter<GroupTravelViewSplitPaymentOptionEvent, Object> ADAPTER = new GroupTravelViewSplitPaymentOptionEventAdapter();
    public final Long adults;
    public final Long bill_item_product_id;
    public final String bill_item_product_type;
    public final String bill_price_quote_token;
    public final Long children;
    public final Context context;
    public final String event_name;
    public final Long guests;
    public final Long infants;
    public final Boolean instant_book;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String page;
    public final C2543PaymentsContext payments_context;
    public final Long reservation2_id;
    public final String reservation_code;
    public final String reservation_price_version_id;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.GroupTravel.v2.GroupTravelViewSplitPaymentOptionEvent$GroupTravelViewSplitPaymentOptionEventAdapter */
    private static final class GroupTravelViewSplitPaymentOptionEventAdapter implements Adapter<GroupTravelViewSplitPaymentOptionEvent, Object> {
        private GroupTravelViewSplitPaymentOptionEventAdapter() {
        }

        public void write(Protocol protocol, GroupTravelViewSplitPaymentOptionEvent struct) throws IOException {
            protocol.writeStructBegin("GroupTravelViewSplitPaymentOptionEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("event_name", 1, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 2, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 3, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("reservation2_id", 5, 10);
            protocol.writeI64(struct.reservation2_id.longValue());
            protocol.writeFieldEnd();
            if (struct.bill_item_product_type != null) {
                protocol.writeFieldBegin("bill_item_product_type", 6, PassportService.SF_DG11);
                protocol.writeString(struct.bill_item_product_type);
                protocol.writeFieldEnd();
            }
            if (struct.bill_item_product_id != null) {
                protocol.writeFieldBegin("bill_item_product_id", 7, 10);
                protocol.writeI64(struct.bill_item_product_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.reservation_code != null) {
                protocol.writeFieldBegin("reservation_code", 8, PassportService.SF_DG11);
                protocol.writeString(struct.reservation_code);
                protocol.writeFieldEnd();
            }
            if (struct.bill_price_quote_token != null) {
                protocol.writeFieldBegin("bill_price_quote_token", 9, PassportService.SF_DG11);
                protocol.writeString(struct.bill_price_quote_token);
                protocol.writeFieldEnd();
            }
            if (struct.reservation_price_version_id != null) {
                protocol.writeFieldBegin("reservation_price_version_id", 10, PassportService.SF_DG11);
                protocol.writeString(struct.reservation_price_version_id);
                protocol.writeFieldEnd();
            }
            if (struct.payments_context != null) {
                protocol.writeFieldBegin("payments_context", 11, PassportService.SF_DG12);
                C2543PaymentsContext.ADAPTER.write(protocol, struct.payments_context);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(ManageListingAnalytics.INSTANT_BOOK, 12, 2);
            protocol.writeBool(struct.instant_book.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 13, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 14, 10);
            protocol.writeI64(struct.guests.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.ADULTS, 15, 10);
            protocol.writeI64(struct.adults.longValue());
            protocol.writeFieldEnd();
            if (struct.children != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.CHILDREN, 16, 10);
                protocol.writeI64(struct.children.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.infants != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.INFANTS, 17, 10);
                protocol.writeI64(struct.infants.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof GroupTravelViewSplitPaymentOptionEvent)) {
            return false;
        }
        GroupTravelViewSplitPaymentOptionEvent that = (GroupTravelViewSplitPaymentOptionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.reservation2_id == that.reservation2_id || this.reservation2_id.equals(that.reservation2_id)) && ((this.bill_item_product_type == that.bill_item_product_type || (this.bill_item_product_type != null && this.bill_item_product_type.equals(that.bill_item_product_type))) && ((this.bill_item_product_id == that.bill_item_product_id || (this.bill_item_product_id != null && this.bill_item_product_id.equals(that.bill_item_product_id))) && ((this.reservation_code == that.reservation_code || (this.reservation_code != null && this.reservation_code.equals(that.reservation_code))) && ((this.bill_price_quote_token == that.bill_price_quote_token || (this.bill_price_quote_token != null && this.bill_price_quote_token.equals(that.bill_price_quote_token))) && ((this.reservation_price_version_id == that.reservation_price_version_id || (this.reservation_price_version_id != null && this.reservation_price_version_id.equals(that.reservation_price_version_id))) && ((this.payments_context == that.payments_context || (this.payments_context != null && this.payments_context.equals(that.payments_context))) && ((this.instant_book == that.instant_book || this.instant_book.equals(that.instant_book)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.guests == that.guests || this.guests.equals(that.guests)) && ((this.adults == that.adults || this.adults.equals(that.adults)) && (this.children == that.children || (this.children != null && this.children.equals(that.children))))))))))))))))))) {
            if (this.infants == that.infants) {
                return true;
            }
            if (this.infants != null && this.infants.equals(that.infants)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.reservation2_id.hashCode()) * -2128831035) ^ (this.bill_item_product_type == null ? 0 : this.bill_item_product_type.hashCode())) * -2128831035) ^ (this.bill_item_product_id == null ? 0 : this.bill_item_product_id.hashCode())) * -2128831035) ^ (this.reservation_code == null ? 0 : this.reservation_code.hashCode())) * -2128831035) ^ (this.bill_price_quote_token == null ? 0 : this.bill_price_quote_token.hashCode())) * -2128831035) ^ (this.reservation_price_version_id == null ? 0 : this.reservation_price_version_id.hashCode())) * -2128831035) ^ (this.payments_context == null ? 0 : this.payments_context.hashCode())) * -2128831035) ^ this.instant_book.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.guests.hashCode()) * -2128831035) ^ this.adults.hashCode()) * -2128831035) ^ (this.children == null ? 0 : this.children.hashCode())) * -2128831035;
        if (this.infants != null) {
            i = this.infants.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "GroupTravelViewSplitPaymentOptionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", reservation2_id=" + this.reservation2_id + ", bill_item_product_type=" + this.bill_item_product_type + ", bill_item_product_id=" + this.bill_item_product_id + ", reservation_code=" + this.reservation_code + ", bill_price_quote_token=" + this.bill_price_quote_token + ", reservation_price_version_id=" + this.reservation_price_version_id + ", payments_context=" + this.payments_context + ", instant_book=" + this.instant_book + ", listing_id=" + this.listing_id + ", guests=" + this.guests + ", adults=" + this.adults + ", children=" + this.children + ", infants=" + this.infants + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
