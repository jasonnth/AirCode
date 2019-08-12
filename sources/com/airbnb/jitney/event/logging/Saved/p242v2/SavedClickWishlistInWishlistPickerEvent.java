package com.airbnb.jitney.event.logging.Saved.p242v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.WishlistSource.p294v2.C2812WishlistSource;
import com.airbnb.jitney.event.logging.WishlistedItemType.p297v1.C2815WishlistedItemType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Saved.v2.SavedClickWishlistInWishlistPickerEvent */
public final class SavedClickWishlistInWishlistPickerEvent implements Struct {
    public static final Adapter<SavedClickWishlistInWishlistPickerEvent, Object> ADAPTER = new SavedClickWishlistInWishlistPickerEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final String mobile_search_session_id;
    public final C2451Operation operation;
    public final String schema;
    public final String target;
    public final Long wishlist_id;
    public final C2812WishlistSource wishlist_source;
    public final Long wishlisted_item_id;
    public final C2815WishlistedItemType wishlisted_item_type;

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v2.SavedClickWishlistInWishlistPickerEvent$SavedClickWishlistInWishlistPickerEventAdapter */
    private static final class SavedClickWishlistInWishlistPickerEventAdapter implements Adapter<SavedClickWishlistInWishlistPickerEvent, Object> {
        private SavedClickWishlistInWishlistPickerEventAdapter() {
        }

        public void write(Protocol protocol, SavedClickWishlistInWishlistPickerEvent struct) throws IOException {
            protocol.writeStructBegin("SavedClickWishlistInWishlistPickerEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 3, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_source", 5, 8);
            protocol.writeI32(struct.wishlist_source.value);
            protocol.writeFieldEnd();
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 6, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item0 : struct.dates) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 7, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("wishlisted_item_type", 8, 8);
            protocol.writeI32(struct.wishlisted_item_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlisted_item_id", 9, 10);
            protocol.writeI64(struct.wishlisted_item_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_id", 10, 10);
            protocol.writeI64(struct.wishlist_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mobile_search_session_id", 11, PassportService.SF_DG11);
            protocol.writeString(struct.mobile_search_session_id);
            protocol.writeFieldEnd();
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
        if (!(other instanceof SavedClickWishlistInWishlistPickerEvent)) {
            return false;
        }
        SavedClickWishlistInWishlistPickerEvent that = (SavedClickWishlistInWishlistPickerEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.wishlist_source == that.wishlist_source || this.wishlist_source.equals(that.wishlist_source)) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.wishlisted_item_type == that.wishlisted_item_type || this.wishlisted_item_type.equals(that.wishlisted_item_type)) && ((this.wishlisted_item_id == that.wishlisted_item_id || this.wishlisted_item_id.equals(that.wishlisted_item_id)) && ((this.wishlist_id == that.wishlist_id || this.wishlist_id.equals(that.wishlist_id)) && (this.mobile_search_session_id == that.mobile_search_session_id || this.mobile_search_session_id.equals(that.mobile_search_session_id))))))))))))) {
            return true;
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
        int code = (((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.wishlist_source.hashCode()) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035;
        if (this.guests != null) {
            i = this.guests.hashCode();
        }
        return (((((((((code ^ i) * -2128831035) ^ this.wishlisted_item_type.hashCode()) * -2128831035) ^ this.wishlisted_item_id.hashCode()) * -2128831035) ^ this.wishlist_id.hashCode()) * -2128831035) ^ this.mobile_search_session_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SavedClickWishlistInWishlistPickerEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", target=" + this.target + ", operation=" + this.operation + ", wishlist_source=" + this.wishlist_source + ", dates=" + this.dates + ", guests=" + this.guests + ", wishlisted_item_type=" + this.wishlisted_item_type + ", wishlisted_item_id=" + this.wishlisted_item_id + ", wishlist_id=" + this.wishlist_id + ", mobile_search_session_id=" + this.mobile_search_session_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
