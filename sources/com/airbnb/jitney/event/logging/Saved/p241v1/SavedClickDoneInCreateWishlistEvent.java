package com.airbnb.jitney.event.logging.Saved.p241v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.WishlistPrivacy.p292v1.C2810WishlistPrivacy;
import com.airbnb.jitney.event.logging.WishlistSource.p293v1.C2811WishlistSource;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Saved.v1.SavedClickDoneInCreateWishlistEvent */
public final class SavedClickDoneInCreateWishlistEvent implements Struct {
    public static final Adapter<SavedClickDoneInCreateWishlistEvent, Object> ADAPTER = new SavedClickDoneInCreateWishlistEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final String mobile_search_session_id;
    public final C2451Operation operation;
    public final String schema;
    public final String target;
    public final Long wishlist_id;
    public final List<C2810WishlistPrivacy> wishlist_privacy;
    public final List<C2811WishlistSource> wishlist_source;
    public final Long wishlist_title;

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v1.SavedClickDoneInCreateWishlistEvent$SavedClickDoneInCreateWishlistEventAdapter */
    private static final class SavedClickDoneInCreateWishlistEventAdapter implements Adapter<SavedClickDoneInCreateWishlistEvent, Object> {
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
            protocol.writeFieldBegin("wishlist_source", 5, 15);
            protocol.writeListBegin(16, struct.wishlist_source.size());
            for (C2811WishlistSource item0 : struct.wishlist_source) {
                protocol.writeI32(item0.value);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 6, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item02 : struct.dates) {
                    protocol.writeString(item02);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 7, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("wishlist_title", 8, 10);
            protocol.writeI64(struct.wishlist_title.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_privacy", 9, 15);
            protocol.writeListBegin(16, struct.wishlist_privacy.size());
            for (C2810WishlistPrivacy item03 : struct.wishlist_privacy) {
                protocol.writeI32(item03.value);
            }
            protocol.writeListEnd();
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
        if (!(other instanceof SavedClickDoneInCreateWishlistEvent)) {
            return false;
        }
        SavedClickDoneInCreateWishlistEvent that = (SavedClickDoneInCreateWishlistEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.wishlist_source == that.wishlist_source || this.wishlist_source.equals(that.wishlist_source)) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.wishlist_title == that.wishlist_title || this.wishlist_title.equals(that.wishlist_title)) && ((this.wishlist_privacy == that.wishlist_privacy || this.wishlist_privacy.equals(that.wishlist_privacy)) && ((this.wishlist_id == that.wishlist_id || this.wishlist_id.equals(that.wishlist_id)) && (this.mobile_search_session_id == that.mobile_search_session_id || this.mobile_search_session_id.equals(that.mobile_search_session_id))))))))))))) {
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
        return (((((((((code ^ i) * -2128831035) ^ this.wishlist_title.hashCode()) * -2128831035) ^ this.wishlist_privacy.hashCode()) * -2128831035) ^ this.wishlist_id.hashCode()) * -2128831035) ^ this.mobile_search_session_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SavedClickDoneInCreateWishlistEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", target=" + this.target + ", operation=" + this.operation + ", wishlist_source=" + this.wishlist_source + ", dates=" + this.dates + ", guests=" + this.guests + ", wishlist_title=" + this.wishlist_title + ", wishlist_privacy=" + this.wishlist_privacy + ", wishlist_id=" + this.wishlist_id + ", mobile_search_session_id=" + this.mobile_search_session_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
