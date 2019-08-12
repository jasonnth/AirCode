package com.airbnb.jitney.event.logging.SearchContext.p249v1;

import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.SearchContext.v1.SearchContext */
public final class C2731SearchContext implements Struct {
    public static final Adapter<C2731SearchContext, Builder> ADAPTER = new SearchContextAdapter();
    public final List<String> dates;
    public final Long guests;
    public final String location;
    public final String mobile_search_session_id;
    public final String search_id;
    public final C2139ExploreSubtab subtab;

    /* renamed from: com.airbnb.jitney.event.logging.SearchContext.v1.SearchContext$Builder */
    public static final class Builder implements StructBuilder<C2731SearchContext> {
        /* access modifiers changed from: private */
        public List<String> dates;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public String location;
        /* access modifiers changed from: private */
        public String mobile_search_session_id;
        /* access modifiers changed from: private */
        public String search_id;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;

        private Builder() {
        }

        public Builder(String search_id2, String mobile_search_session_id2) {
            this.search_id = search_id2;
            this.mobile_search_session_id = mobile_search_session_id2;
        }

        public C2731SearchContext build() {
            if (this.search_id == null) {
                throw new IllegalStateException("Required field 'search_id' is missing");
            } else if (this.mobile_search_session_id != null) {
                return new C2731SearchContext(this);
            } else {
                throw new IllegalStateException("Required field 'mobile_search_session_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.SearchContext.v1.SearchContext$SearchContextAdapter */
    private static final class SearchContextAdapter implements Adapter<C2731SearchContext, Builder> {
        private SearchContextAdapter() {
        }

        public void write(Protocol protocol, C2731SearchContext struct) throws IOException {
            protocol.writeStructBegin("SearchContext");
            protocol.writeFieldBegin(PlacesIntents.INTENT_EXTRA_SEARCH_ID, 1, PassportService.SF_DG11);
            protocol.writeString(struct.search_id);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mobile_search_session_id", 2, PassportService.SF_DG11);
            protocol.writeString(struct.mobile_search_session_id);
            protocol.writeFieldEnd();
            if (struct.location != null) {
                protocol.writeFieldBegin("location", 3, PassportService.SF_DG11);
                protocol.writeString(struct.location);
                protocol.writeFieldEnd();
            }
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 4, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item0 : struct.dates) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 5, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.subtab != null) {
                protocol.writeFieldBegin("subtab", 6, 8);
                protocol.writeI32(struct.subtab.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2731SearchContext(Builder builder) {
        this.search_id = builder.search_id;
        this.mobile_search_session_id = builder.mobile_search_session_id;
        this.location = builder.location;
        this.dates = builder.dates == null ? null : Collections.unmodifiableList(builder.dates);
        this.guests = builder.guests;
        this.subtab = builder.subtab;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2731SearchContext)) {
            return false;
        }
        C2731SearchContext that = (C2731SearchContext) other;
        if ((this.search_id == that.search_id || this.search_id.equals(that.search_id)) && ((this.mobile_search_session_id == that.mobile_search_session_id || this.mobile_search_session_id.equals(that.mobile_search_session_id)) && ((this.location == that.location || (this.location != null && this.location.equals(that.location))) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && (this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))))))) {
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
        int i = 0;
        int code = (((((((((16777619 ^ this.search_id.hashCode()) * -2128831035) ^ this.mobile_search_session_id.hashCode()) * -2128831035) ^ (this.location == null ? 0 : this.location.hashCode())) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035;
        if (this.subtab != null) {
            i = this.subtab.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SearchContext{search_id=" + this.search_id + ", mobile_search_session_id=" + this.mobile_search_session_id + ", location=" + this.location + ", dates=" + this.dates + ", guests=" + this.guests + ", subtab=" + this.subtab + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
