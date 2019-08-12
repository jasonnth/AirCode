package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.IndexPageTracking */
public final class IndexPageTracking implements Struct {
    public static final Adapter<IndexPageTracking, Object> ADAPTER = new IndexPageTrackingAdapter();
    public final IndexPageAction action;
    public final IndexPageType page;
    public final Long target_collection_id;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.IndexPageTracking$IndexPageTrackingAdapter */
    private static final class IndexPageTrackingAdapter implements Adapter<IndexPageTracking, Object> {
        private IndexPageTrackingAdapter() {
        }

        public void write(Protocol protocol, IndexPageTracking struct) throws IOException {
            protocol.writeStructBegin("IndexPageTracking");
            protocol.writeFieldBegin("action", 1, 8);
            protocol.writeI32(struct.action.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 2, 8);
            protocol.writeI32(struct.page.value);
            protocol.writeFieldEnd();
            if (struct.target_collection_id != null) {
                protocol.writeFieldBegin("target_collection_id", 3, 10);
                protocol.writeI64(struct.target_collection_id.longValue());
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
        if (!(other instanceof IndexPageTracking)) {
            return false;
        }
        IndexPageTracking that = (IndexPageTracking) other;
        if ((this.action == that.action || this.action.equals(that.action)) && (this.page == that.page || this.page.equals(that.page))) {
            if (this.target_collection_id == that.target_collection_id) {
                return true;
            }
            if (this.target_collection_id != null && this.target_collection_id.equals(that.target_collection_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((16777619 ^ this.action.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ (this.target_collection_id == null ? 0 : this.target_collection_id.hashCode())) * -2128831035;
    }

    public String toString() {
        return "IndexPageTracking{action=" + this.action + ", page=" + this.page + ", target_collection_id=" + this.target_collection_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
