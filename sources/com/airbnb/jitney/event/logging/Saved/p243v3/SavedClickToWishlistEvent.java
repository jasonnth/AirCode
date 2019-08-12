package com.airbnb.jitney.event.logging.Saved.p243v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.WishlistMethod.p291v1.C2809WishlistMethod;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.jitney.event.logging.WishlistedItemType.p297v1.C2815WishlistedItemType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Saved.v3.SavedClickToWishlistEvent */
public final class SavedClickToWishlistEvent implements Struct {
    public static final Adapter<SavedClickToWishlistEvent, Builder> ADAPTER = new SavedClickToWishlistEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final String mobile_search_session_id;
    public final C2451Operation operation;
    public final String schema;
    public final String target;
    public final Long wishlist_id;
    public final C2809WishlistMethod wishlist_method;
    public final C2813WishlistSource wishlist_source;
    public final Long wishlisted_item_id;
    public final C2815WishlistedItemType wishlisted_item_type;

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v3.SavedClickToWishlistEvent$Builder */
    public static final class Builder implements StructBuilder<SavedClickToWishlistEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public List<String> dates;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public String mobile_search_session_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;
        /* access modifiers changed from: private */
        public Long wishlist_id;
        /* access modifiers changed from: private */
        public C2809WishlistMethod wishlist_method;
        /* access modifiers changed from: private */
        public C2813WishlistSource wishlist_source;
        /* access modifiers changed from: private */
        public Long wishlisted_item_id;
        /* access modifiers changed from: private */
        public C2815WishlistedItemType wishlisted_item_type;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Saved:SavedClickToWishlistEvent:3.0.0";
            this.event_name = "saved_click_to_wishlist";
            this.target = "wishlist_button";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C2809WishlistMethod wishlist_method2, C2813WishlistSource wishlist_source2, C2815WishlistedItemType wishlisted_item_type2, Long wishlisted_item_id2, String mobile_search_session_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Saved:SavedClickToWishlistEvent:3.0.0";
            this.event_name = "saved_click_to_wishlist";
            this.context = context2;
            this.target = "wishlist_button";
            this.operation = C2451Operation.Click;
            this.wishlist_method = wishlist_method2;
            this.wishlist_source = wishlist_source2;
            this.wishlisted_item_type = wishlisted_item_type2;
            this.wishlisted_item_id = wishlisted_item_id2;
            this.mobile_search_session_id = mobile_search_session_id2;
        }

        public SavedClickToWishlistEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.wishlist_method == null) {
                throw new IllegalStateException("Required field 'wishlist_method' is missing");
            } else if (this.wishlist_source == null) {
                throw new IllegalStateException("Required field 'wishlist_source' is missing");
            } else if (this.wishlisted_item_type == null) {
                throw new IllegalStateException("Required field 'wishlisted_item_type' is missing");
            } else if (this.wishlisted_item_id == null) {
                throw new IllegalStateException("Required field 'wishlisted_item_id' is missing");
            } else if (this.mobile_search_session_id != null) {
                return new SavedClickToWishlistEvent(this);
            } else {
                throw new IllegalStateException("Required field 'mobile_search_session_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v3.SavedClickToWishlistEvent$SavedClickToWishlistEventAdapter */
    private static final class SavedClickToWishlistEventAdapter implements Adapter<SavedClickToWishlistEvent, Builder> {
        private SavedClickToWishlistEventAdapter() {
        }

        public void write(Protocol protocol, SavedClickToWishlistEvent struct) throws IOException {
            protocol.writeStructBegin("SavedClickToWishlistEvent");
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
            protocol.writeFieldBegin("wishlist_method", 5, 8);
            protocol.writeI32(struct.wishlist_method.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_source", 6, 8);
            protocol.writeI32(struct.wishlist_source.value);
            protocol.writeFieldEnd();
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 7, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item0 : struct.dates) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 8, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("wishlisted_item_type", 9, 8);
            protocol.writeI32(struct.wishlisted_item_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlisted_item_id", 10, 10);
            protocol.writeI64(struct.wishlisted_item_id.longValue());
            protocol.writeFieldEnd();
            if (struct.wishlist_id != null) {
                protocol.writeFieldBegin("wishlist_id", 11, 10);
                protocol.writeI64(struct.wishlist_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("mobile_search_session_id", 12, PassportService.SF_DG11);
            protocol.writeString(struct.mobile_search_session_id);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SavedClickToWishlistEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.target = builder.target;
        this.operation = builder.operation;
        this.wishlist_method = builder.wishlist_method;
        this.wishlist_source = builder.wishlist_source;
        this.dates = builder.dates == null ? null : Collections.unmodifiableList(builder.dates);
        this.guests = builder.guests;
        this.wishlisted_item_type = builder.wishlisted_item_type;
        this.wishlisted_item_id = builder.wishlisted_item_id;
        this.wishlist_id = builder.wishlist_id;
        this.mobile_search_session_id = builder.mobile_search_session_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SavedClickToWishlistEvent)) {
            return false;
        }
        SavedClickToWishlistEvent that = (SavedClickToWishlistEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.wishlist_method == that.wishlist_method || this.wishlist_method.equals(that.wishlist_method)) && ((this.wishlist_source == that.wishlist_source || this.wishlist_source.equals(that.wishlist_source)) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.wishlisted_item_type == that.wishlisted_item_type || this.wishlisted_item_type.equals(that.wishlisted_item_type)) && ((this.wishlisted_item_id == that.wishlisted_item_id || this.wishlisted_item_id.equals(that.wishlisted_item_id)) && ((this.wishlist_id == that.wishlist_id || (this.wishlist_id != null && this.wishlist_id.equals(that.wishlist_id))) && (this.mobile_search_session_id == that.mobile_search_session_id || this.mobile_search_session_id.equals(that.mobile_search_session_id)))))))))))))) {
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
        int code = (((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.wishlist_method.hashCode()) * -2128831035) ^ this.wishlist_source.hashCode()) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ this.wishlisted_item_type.hashCode()) * -2128831035) ^ this.wishlisted_item_id.hashCode()) * -2128831035;
        if (this.wishlist_id != null) {
            i = this.wishlist_id.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.mobile_search_session_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SavedClickToWishlistEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", target=" + this.target + ", operation=" + this.operation + ", wishlist_method=" + this.wishlist_method + ", wishlist_source=" + this.wishlist_source + ", dates=" + this.dates + ", guests=" + this.guests + ", wishlisted_item_type=" + this.wishlisted_item_type + ", wishlisted_item_id=" + this.wishlisted_item_id + ", wishlist_id=" + this.wishlist_id + ", mobile_search_session_id=" + this.mobile_search_session_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
