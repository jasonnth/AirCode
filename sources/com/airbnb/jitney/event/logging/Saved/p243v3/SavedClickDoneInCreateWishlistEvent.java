package com.airbnb.jitney.event.logging.Saved.p243v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.WishlistPrivacy.p292v1.C2810WishlistPrivacy;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
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

/* renamed from: com.airbnb.jitney.event.logging.Saved.v3.SavedClickDoneInCreateWishlistEvent */
public final class SavedClickDoneInCreateWishlistEvent implements Struct {
    public static final Adapter<SavedClickDoneInCreateWishlistEvent, Builder> ADAPTER = new SavedClickDoneInCreateWishlistEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final String mobile_search_session_id;
    public final C2451Operation operation;
    public final String schema;
    public final String target;
    public final C2810WishlistPrivacy wishlist_privacy;
    public final C2813WishlistSource wishlist_source;
    public final String wishlist_title;

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v3.SavedClickDoneInCreateWishlistEvent$Builder */
    public static final class Builder implements StructBuilder<SavedClickDoneInCreateWishlistEvent> {
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
        public C2810WishlistPrivacy wishlist_privacy;
        /* access modifiers changed from: private */
        public C2813WishlistSource wishlist_source;
        /* access modifiers changed from: private */
        public String wishlist_title;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Saved:SavedClickDoneInCreateWishlistEvent:3.0.0";
            this.event_name = "saved_click_done_in_create_wishlist";
            this.target = "create_wishlist_done";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C2813WishlistSource wishlist_source2, String wishlist_title2, C2810WishlistPrivacy wishlist_privacy2, String mobile_search_session_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Saved:SavedClickDoneInCreateWishlistEvent:3.0.0";
            this.event_name = "saved_click_done_in_create_wishlist";
            this.context = context2;
            this.target = "create_wishlist_done";
            this.operation = C2451Operation.Click;
            this.wishlist_source = wishlist_source2;
            this.wishlist_title = wishlist_title2;
            this.wishlist_privacy = wishlist_privacy2;
            this.mobile_search_session_id = mobile_search_session_id2;
        }

        public SavedClickDoneInCreateWishlistEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.wishlist_source == null) {
                throw new IllegalStateException("Required field 'wishlist_source' is missing");
            } else if (this.wishlist_title == null) {
                throw new IllegalStateException("Required field 'wishlist_title' is missing");
            } else if (this.wishlist_privacy == null) {
                throw new IllegalStateException("Required field 'wishlist_privacy' is missing");
            } else if (this.mobile_search_session_id != null) {
                return new SavedClickDoneInCreateWishlistEvent(this);
            } else {
                throw new IllegalStateException("Required field 'mobile_search_session_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v3.SavedClickDoneInCreateWishlistEvent$SavedClickDoneInCreateWishlistEventAdapter */
    private static final class SavedClickDoneInCreateWishlistEventAdapter implements Adapter<SavedClickDoneInCreateWishlistEvent, Builder> {
        private SavedClickDoneInCreateWishlistEventAdapter() {
        }

        public void write(Protocol protocol, SavedClickDoneInCreateWishlistEvent struct) throws IOException {
            protocol.writeStructBegin("SavedClickDoneInCreateWishlistEvent");
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
            protocol.writeFieldBegin("wishlist_title", 8, PassportService.SF_DG11);
            protocol.writeString(struct.wishlist_title);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_privacy", 9, 8);
            protocol.writeI32(struct.wishlist_privacy.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mobile_search_session_id", 10, PassportService.SF_DG11);
            protocol.writeString(struct.mobile_search_session_id);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SavedClickDoneInCreateWishlistEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.target = builder.target;
        this.operation = builder.operation;
        this.wishlist_source = builder.wishlist_source;
        this.dates = builder.dates == null ? null : Collections.unmodifiableList(builder.dates);
        this.guests = builder.guests;
        this.wishlist_title = builder.wishlist_title;
        this.wishlist_privacy = builder.wishlist_privacy;
        this.mobile_search_session_id = builder.mobile_search_session_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SavedClickDoneInCreateWishlistEvent)) {
            return false;
        }
        SavedClickDoneInCreateWishlistEvent that = (SavedClickDoneInCreateWishlistEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.wishlist_source == that.wishlist_source || this.wishlist_source.equals(that.wishlist_source)) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.wishlist_title == that.wishlist_title || this.wishlist_title.equals(that.wishlist_title)) && ((this.wishlist_privacy == that.wishlist_privacy || this.wishlist_privacy.equals(that.wishlist_privacy)) && (this.mobile_search_session_id == that.mobile_search_session_id || this.mobile_search_session_id.equals(that.mobile_search_session_id)))))))))))) {
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
        return (((((((code ^ i) * -2128831035) ^ this.wishlist_title.hashCode()) * -2128831035) ^ this.wishlist_privacy.hashCode()) * -2128831035) ^ this.mobile_search_session_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SavedClickDoneInCreateWishlistEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", target=" + this.target + ", operation=" + this.operation + ", wishlist_source=" + this.wishlist_source + ", dates=" + this.dates + ", guests=" + this.guests + ", wishlist_title=" + this.wishlist_title + ", wishlist_privacy=" + this.wishlist_privacy + ", mobile_search_session_id=" + this.mobile_search_session_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
