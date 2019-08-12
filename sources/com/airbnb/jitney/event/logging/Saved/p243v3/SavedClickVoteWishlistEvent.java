package com.airbnb.jitney.event.logging.Saved.p243v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.VoteMethod.p289v1.C2806VoteMethod;
import com.airbnb.jitney.event.logging.WishlistView.p296v1.C2814WishlistView;
import com.airbnb.jitney.event.logging.WishlistedItemType.p297v1.C2815WishlistedItemType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Saved.v3.SavedClickVoteWishlistEvent */
public final class SavedClickVoteWishlistEvent implements Struct {
    public static final Adapter<SavedClickVoteWishlistEvent, Builder> ADAPTER = new SavedClickVoteWishlistEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;
    public final String target;
    public final C2806VoteMethod vote_method;
    public final Long wishlist_id;
    public final C2814WishlistView wishlist_view;
    public final Long wishlisted_item_id;
    public final C2815WishlistedItemType wishlisted_item_type;

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v3.SavedClickVoteWishlistEvent$Builder */
    public static final class Builder implements StructBuilder<SavedClickVoteWishlistEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;
        /* access modifiers changed from: private */
        public C2806VoteMethod vote_method;
        /* access modifiers changed from: private */
        public Long wishlist_id;
        /* access modifiers changed from: private */
        public C2814WishlistView wishlist_view;
        /* access modifiers changed from: private */
        public Long wishlisted_item_id;
        /* access modifiers changed from: private */
        public C2815WishlistedItemType wishlisted_item_type;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Saved:SavedClickVoteWishlistEvent:3.0.0";
            this.event_name = "saved_click_vote_wishlist";
            this.operation = C2451Operation.Click;
            this.target = "vote";
        }

        public Builder(Context context2, C2814WishlistView wishlist_view2, C2806VoteMethod vote_method2, C2815WishlistedItemType wishlisted_item_type2, Long wishlisted_item_id2, Long wishlist_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Saved:SavedClickVoteWishlistEvent:3.0.0";
            this.event_name = "saved_click_vote_wishlist";
            this.context = context2;
            this.operation = C2451Operation.Click;
            this.target = "vote";
            this.wishlist_view = wishlist_view2;
            this.vote_method = vote_method2;
            this.wishlisted_item_type = wishlisted_item_type2;
            this.wishlisted_item_id = wishlisted_item_id2;
            this.wishlist_id = wishlist_id2;
        }

        public SavedClickVoteWishlistEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.wishlist_view == null) {
                throw new IllegalStateException("Required field 'wishlist_view' is missing");
            } else if (this.vote_method == null) {
                throw new IllegalStateException("Required field 'vote_method' is missing");
            } else if (this.wishlisted_item_type == null) {
                throw new IllegalStateException("Required field 'wishlisted_item_type' is missing");
            } else if (this.wishlisted_item_id == null) {
                throw new IllegalStateException("Required field 'wishlisted_item_id' is missing");
            } else if (this.wishlist_id != null) {
                return new SavedClickVoteWishlistEvent(this);
            } else {
                throw new IllegalStateException("Required field 'wishlist_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v3.SavedClickVoteWishlistEvent$SavedClickVoteWishlistEventAdapter */
    private static final class SavedClickVoteWishlistEventAdapter implements Adapter<SavedClickVoteWishlistEvent, Builder> {
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 3, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 4, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_view", 5, 8);
            protocol.writeI32(struct.wishlist_view.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("vote_method", 6, 8);
            protocol.writeI32(struct.vote_method.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlisted_item_type", 7, 8);
            protocol.writeI32(struct.wishlisted_item_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlisted_item_id", 8, 10);
            protocol.writeI64(struct.wishlisted_item_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_id", 9, 10);
            protocol.writeI64(struct.wishlist_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SavedClickVoteWishlistEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.operation = builder.operation;
        this.target = builder.target;
        this.wishlist_view = builder.wishlist_view;
        this.vote_method = builder.vote_method;
        this.wishlisted_item_type = builder.wishlisted_item_type;
        this.wishlisted_item_id = builder.wishlisted_item_id;
        this.wishlist_id = builder.wishlist_id;
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.wishlist_view == that.wishlist_view || this.wishlist_view.equals(that.wishlist_view)) && ((this.vote_method == that.vote_method || this.vote_method.equals(that.vote_method)) && ((this.wishlisted_item_type == that.wishlisted_item_type || this.wishlisted_item_type.equals(that.wishlisted_item_type)) && ((this.wishlisted_item_id == that.wishlisted_item_id || this.wishlisted_item_id.equals(that.wishlisted_item_id)) && (this.wishlist_id == that.wishlist_id || this.wishlist_id.equals(that.wishlist_id))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.wishlist_view.hashCode()) * -2128831035) ^ this.vote_method.hashCode()) * -2128831035) ^ this.wishlisted_item_type.hashCode()) * -2128831035) ^ this.wishlisted_item_id.hashCode()) * -2128831035) ^ this.wishlist_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SavedClickVoteWishlistEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", target=" + this.target + ", wishlist_view=" + this.wishlist_view + ", vote_method=" + this.vote_method + ", wishlisted_item_type=" + this.wishlisted_item_type + ", wishlisted_item_id=" + this.wishlisted_item_id + ", wishlist_id=" + this.wishlist_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
