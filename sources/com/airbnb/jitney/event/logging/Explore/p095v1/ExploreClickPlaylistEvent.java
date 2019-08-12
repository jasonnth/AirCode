package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PlaylistType.p204v1.C2568PlaylistType;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickPlaylistEvent */
public final class ExploreClickPlaylistEvent implements Struct {
    public static final Adapter<ExploreClickPlaylistEvent, Object> ADAPTER = new ExploreClickPlaylistEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final String location;
    public final C2451Operation operation;
    public final String page;
    public final Long playlist_id;
    public final C2568PlaylistType playlist_type;
    public final Long referred_playlist_id;
    public final String schema;
    public final C2731SearchContext search_context;
    public final C2139ExploreSubtab subtab;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickPlaylistEvent$ExploreClickPlaylistEventAdapter */
    private static final class ExploreClickPlaylistEventAdapter implements Adapter<ExploreClickPlaylistEvent, Object> {
        private ExploreClickPlaylistEventAdapter() {
        }

        public void write(Protocol protocol, ExploreClickPlaylistEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreClickPlaylistEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("playlist_id", 5, 10);
            protocol.writeI64(struct.playlist_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("playlist_type", 6, 8);
            protocol.writeI32(struct.playlist_type.value);
            protocol.writeFieldEnd();
            if (struct.referred_playlist_id != null) {
                protocol.writeFieldBegin("referred_playlist_id", 7, 10);
                protocol.writeI64(struct.referred_playlist_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.search_context != null) {
                protocol.writeFieldBegin("search_context", 8, PassportService.SF_DG12);
                C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
                protocol.writeFieldEnd();
            }
            if (struct.location != null) {
                protocol.writeFieldBegin("location", 9, PassportService.SF_DG11);
                protocol.writeString(struct.location);
                protocol.writeFieldEnd();
            }
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 10, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item0 : struct.dates) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 11, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.subtab != null) {
                protocol.writeFieldBegin("subtab", 12, 8);
                protocol.writeI32(struct.subtab.value);
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
        if (!(other instanceof ExploreClickPlaylistEvent)) {
            return false;
        }
        ExploreClickPlaylistEvent that = (ExploreClickPlaylistEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.playlist_id == that.playlist_id || this.playlist_id.equals(that.playlist_id)) && ((this.playlist_type == that.playlist_type || this.playlist_type.equals(that.playlist_type)) && ((this.referred_playlist_id == that.referred_playlist_id || (this.referred_playlist_id != null && this.referred_playlist_id.equals(that.referred_playlist_id))) && ((this.search_context == that.search_context || (this.search_context != null && this.search_context.equals(that.search_context))) && ((this.location == that.location || (this.location != null && this.location.equals(that.location))) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && (this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests)))))))))))))) {
            if (this.subtab == that.subtab) {
                return true;
            }
            if (this.subtab != null && this.subtab.equals(that.subtab)) {
                return true;
            }
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
        int code = (((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.playlist_id.hashCode()) * -2128831035) ^ this.playlist_type.hashCode()) * -2128831035) ^ (this.referred_playlist_id == null ? 0 : this.referred_playlist_id.hashCode())) * -2128831035) ^ (this.search_context == null ? 0 : this.search_context.hashCode())) * -2128831035) ^ (this.location == null ? 0 : this.location.hashCode())) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035;
        if (this.subtab != null) {
            i = this.subtab.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ExploreClickPlaylistEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", playlist_id=" + this.playlist_id + ", playlist_type=" + this.playlist_type + ", referred_playlist_id=" + this.referred_playlist_id + ", search_context=" + this.search_context + ", location=" + this.location + ", dates=" + this.dates + ", guests=" + this.guests + ", subtab=" + this.subtab + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
