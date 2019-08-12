package com.airbnb.jitney.event.logging.WishlistContext.p290v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.WishlistContext.v1.WishlistContext */
public final class C2807WishlistContext implements Struct {
    public static final Adapter<C2807WishlistContext, Object> ADAPTER = new WishlistContextAdapter();
    public final Long album_id;
    public final Long listing_id;
    public final Long place_id;
    public final Long playlist_id;
    public final Long trip_template_id;
    public final Long wishlist_id;

    /* renamed from: com.airbnb.jitney.event.logging.WishlistContext.v1.WishlistContext$WishlistContextAdapter */
    private static final class WishlistContextAdapter implements Adapter<C2807WishlistContext, Object> {
        private WishlistContextAdapter() {
        }

        public void write(Protocol protocol, C2807WishlistContext struct) throws IOException {
            protocol.writeStructBegin("WishlistContext");
            protocol.writeFieldBegin("wishlist_id", 1, 10);
            protocol.writeI64(struct.wishlist_id.longValue());
            protocol.writeFieldEnd();
            if (struct.listing_id != null) {
                protocol.writeFieldBegin("listing_id", 2, 10);
                protocol.writeI64(struct.listing_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.trip_template_id != null) {
                protocol.writeFieldBegin("trip_template_id", 3, 10);
                protocol.writeI64(struct.trip_template_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.album_id != null) {
                protocol.writeFieldBegin("album_id", 4, 10);
                protocol.writeI64(struct.album_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.playlist_id != null) {
                protocol.writeFieldBegin("playlist_id", 5, 10);
                protocol.writeI64(struct.playlist_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.place_id != null) {
                protocol.writeFieldBegin("place_id", 6, 10);
                protocol.writeI64(struct.place_id.longValue());
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
        if (!(other instanceof C2807WishlistContext)) {
            return false;
        }
        C2807WishlistContext that = (C2807WishlistContext) other;
        if ((this.wishlist_id == that.wishlist_id || this.wishlist_id.equals(that.wishlist_id)) && ((this.listing_id == that.listing_id || (this.listing_id != null && this.listing_id.equals(that.listing_id))) && ((this.trip_template_id == that.trip_template_id || (this.trip_template_id != null && this.trip_template_id.equals(that.trip_template_id))) && ((this.album_id == that.album_id || (this.album_id != null && this.album_id.equals(that.album_id))) && (this.playlist_id == that.playlist_id || (this.playlist_id != null && this.playlist_id.equals(that.playlist_id))))))) {
            if (this.place_id == that.place_id) {
                return true;
            }
            if (this.place_id != null && this.place_id.equals(that.place_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ this.wishlist_id.hashCode()) * -2128831035) ^ (this.listing_id == null ? 0 : this.listing_id.hashCode())) * -2128831035) ^ (this.trip_template_id == null ? 0 : this.trip_template_id.hashCode())) * -2128831035) ^ (this.album_id == null ? 0 : this.album_id.hashCode())) * -2128831035) ^ (this.playlist_id == null ? 0 : this.playlist_id.hashCode())) * -2128831035;
        if (this.place_id != null) {
            i = this.place_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "WishlistContext{wishlist_id=" + this.wishlist_id + ", listing_id=" + this.listing_id + ", trip_template_id=" + this.trip_template_id + ", album_id=" + this.album_id + ", playlist_id=" + this.playlist_id + ", place_id=" + this.place_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
