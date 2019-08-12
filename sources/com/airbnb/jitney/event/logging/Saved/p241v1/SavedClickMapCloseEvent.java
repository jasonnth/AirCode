package com.airbnb.jitney.event.logging.Saved.p241v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Saved.v1.SavedClickMapCloseEvent */
public final class SavedClickMapCloseEvent implements Struct {
    public static final Adapter<SavedClickMapCloseEvent, Builder> ADAPTER = new SavedClickMapCloseEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2139ExploreSubtab subtab;
    public final String target;
    public final Long wishlist_id;

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v1.SavedClickMapCloseEvent$Builder */
    public static final class Builder implements StructBuilder<SavedClickMapCloseEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;
        /* access modifiers changed from: private */
        public String target;
        /* access modifiers changed from: private */
        public Long wishlist_id;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Saved:SavedClickMapCloseEvent:1.0.0";
            this.event_name = "saved_click_map_close";
            this.page = "wishlist_map";
            this.operation = C2451Operation.Click;
            this.target = "close";
        }

        public Builder(Context context2, C2139ExploreSubtab subtab2, Long wishlist_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Saved:SavedClickMapCloseEvent:1.0.0";
            this.event_name = "saved_click_map_close";
            this.context = context2;
            this.page = "wishlist_map";
            this.subtab = subtab2;
            this.operation = C2451Operation.Click;
            this.target = "close";
            this.wishlist_id = wishlist_id2;
        }

        public SavedClickMapCloseEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.wishlist_id != null) {
                return new SavedClickMapCloseEvent(this);
            } else {
                throw new IllegalStateException("Required field 'wishlist_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Saved.v1.SavedClickMapCloseEvent$SavedClickMapCloseEventAdapter */
    private static final class SavedClickMapCloseEventAdapter implements Adapter<SavedClickMapCloseEvent, Builder> {
        private SavedClickMapCloseEventAdapter() {
        }

        public void write(Protocol protocol, SavedClickMapCloseEvent struct) throws IOException {
            protocol.writeStructBegin("SavedClickMapCloseEvent");
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
            protocol.writeFieldBegin("subtab", 4, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 6, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("wishlist_id", 7, 10);
            protocol.writeI64(struct.wishlist_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SavedClickMapCloseEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.subtab = builder.subtab;
        this.operation = builder.operation;
        this.target = builder.target;
        this.wishlist_id = builder.wishlist_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SavedClickMapCloseEvent)) {
            return false;
        }
        SavedClickMapCloseEvent that = (SavedClickMapCloseEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && (this.wishlist_id == that.wishlist_id || this.wishlist_id.equals(that.wishlist_id))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.wishlist_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SavedClickMapCloseEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", subtab=" + this.subtab + ", operation=" + this.operation + ", target=" + this.target + ", wishlist_id=" + this.wishlist_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}