package com.airbnb.jitney.event.logging.Saved.p242v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.Vertical.p281v1.C2786Vertical;
import com.airbnb.jitney.event.logging.VoteMethod.p289v1.C2806VoteMethod;
import com.airbnb.jitney.event.logging.WishlistView.p296v1.C2814WishlistView;
import com.airbnb.jitney.event.logging.WishlistedItemType.p297v1.C2815WishlistedItemType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Saved.v2.SavedClickVoteWishlistEvent */
public final class SavedClickVoteWishlistEvent implements Struct {
    public static final Adapter<SavedClickVoteWishlistEvent, Object> ADAPTER = new SavedClickVoteWishlistEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;
    public final String target;
    public final C2786Vertical vertical;
    public final C2806VoteMethod vote_method;
    public final Long wishlist_id;
    public final C2814WishlistView wishlist_view;
    public final Long wishlisted_item_id;
    public final C2815WishlistedItemType wishlisted_item_type;

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v2.SavedClickVoteWishlistEvent$SavedClickVoteWishlistEventAdapter */
    private static final class SavedClickVoteWishlistEventAdapter implements Adapter<SavedClickVoteWishlistEvent, Object> {
        private SavedClickVoteWishlistEventAdapter() {
        }

        public void write(Protocol protocol, SavedClickVoteWishlistEvent struct) throws IOException {
            protocol.writeStructBegin("SavedClickVoteWishlistEvent");
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
            protocol.writeFieldBegin("vertical", 3, 8);
            protocol.writeI32(struct.vertical.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 5, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_view", 6, 8);
            protocol.writeI32(struct.wishlist_view.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("vote_method", 7, 8);
            protocol.writeI32(struct.vote_method.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlisted_item_type", 8, 8);
            protocol.writeI32(struct.wishlisted_item_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlisted_item_id", 9, 10);
            protocol.writeI64(struct.wishlisted_item_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_id", 10, 10);
            protocol.writeI64(struct.wishlist_id.longValue());
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
        if (!(other instanceof SavedClickVoteWishlistEvent)) {
            return false;
        }
        SavedClickVoteWishlistEvent that = (SavedClickVoteWishlistEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.vertical == that.vertical || this.vertical.equals(that.vertical)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.wishlist_view == that.wishlist_view || this.wishlist_view.equals(that.wishlist_view)) && ((this.vote_method == that.vote_method || this.vote_method.equals(that.vote_method)) && ((this.wishlisted_item_type == that.wishlisted_item_type || this.wishlisted_item_type.equals(that.wishlisted_item_type)) && ((this.wishlisted_item_id == that.wishlisted_item_id || this.wishlisted_item_id.equals(that.wishlisted_item_id)) && (this.wishlist_id == that.wishlist_id || this.wishlist_id.equals(that.wishlist_id)))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.vertical.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.wishlist_view.hashCode()) * -2128831035) ^ this.vote_method.hashCode()) * -2128831035) ^ this.wishlisted_item_type.hashCode()) * -2128831035) ^ this.wishlisted_item_id.hashCode()) * -2128831035) ^ this.wishlist_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SavedClickVoteWishlistEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", vertical=" + this.vertical + ", operation=" + this.operation + ", target=" + this.target + ", wishlist_view=" + this.wishlist_view + ", vote_method=" + this.vote_method + ", wishlisted_item_type=" + this.wishlisted_item_type + ", wishlisted_item_id=" + this.wishlisted_item_id + ", wishlist_id=" + this.wishlist_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
