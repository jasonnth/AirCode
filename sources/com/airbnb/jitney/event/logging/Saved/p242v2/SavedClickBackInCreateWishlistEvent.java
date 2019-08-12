package com.airbnb.jitney.event.logging.Saved.p242v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Saved.v2.SavedClickBackInCreateWishlistEvent */
public final class SavedClickBackInCreateWishlistEvent implements Struct {
    public static final Adapter<SavedClickBackInCreateWishlistEvent, Object> ADAPTER = new SavedClickBackInCreateWishlistEventAdapter();
    public final Context context;
    public final String event_name;
    public final String mobile_search_session_id;
    public final C2451Operation operation;
    public final String schema;
    public final String target;
    public final C2813WishlistSource wishlist_source;

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v2.SavedClickBackInCreateWishlistEvent$SavedClickBackInCreateWishlistEventAdapter */
    private static final class SavedClickBackInCreateWishlistEventAdapter implements Adapter<SavedClickBackInCreateWishlistEvent, Object> {
        private SavedClickBackInCreateWishlistEventAdapter() {
        }

        public void write(Protocol protocol, SavedClickBackInCreateWishlistEvent struct) throws IOException {
            protocol.writeStructBegin("SavedClickBackInCreateWishlistEvent");
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
            protocol.writeFieldBegin("mobile_search_session_id", 6, PassportService.SF_DG11);
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
        if (!(other instanceof SavedClickBackInCreateWishlistEvent)) {
            return false;
        }
        SavedClickBackInCreateWishlistEvent that = (SavedClickBackInCreateWishlistEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.wishlist_source == that.wishlist_source || this.wishlist_source.equals(that.wishlist_source)) && (this.mobile_search_session_id == that.mobile_search_session_id || this.mobile_search_session_id.equals(that.mobile_search_session_id)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.wishlist_source.hashCode()) * -2128831035) ^ this.mobile_search_session_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SavedClickBackInCreateWishlistEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", target=" + this.target + ", operation=" + this.operation + ", wishlist_source=" + this.wishlist_source + ", mobile_search_session_id=" + this.mobile_search_session_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
