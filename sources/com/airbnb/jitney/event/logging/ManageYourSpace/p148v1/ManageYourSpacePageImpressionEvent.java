package com.airbnb.jitney.event.logging.ManageYourSpace.p148v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.ManageYourSpaceSectionType.p149v1.C2410ManageYourSpaceSectionType;
import com.airbnb.jitney.event.logging.ManageYourSpaceSubsectionType.p150v1.C2411ManageYourSpaceSubsectionType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ManageYourSpace.v1.ManageYourSpacePageImpressionEvent */
public final class ManageYourSpacePageImpressionEvent implements Struct {
    public static final Adapter<ManageYourSpacePageImpressionEvent, Object> ADAPTER = new ManageYourSpacePageImpressionEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2410ManageYourSpaceSectionType section;
    public final C2411ManageYourSpaceSubsectionType subsection;

    /* renamed from: com.airbnb.jitney.event.logging.ManageYourSpace.v1.ManageYourSpacePageImpressionEvent$ManageYourSpacePageImpressionEventAdapter */
    private static final class ManageYourSpacePageImpressionEventAdapter implements Adapter<ManageYourSpacePageImpressionEvent, Object> {
        private ManageYourSpacePageImpressionEventAdapter() {
        }

        public void write(Protocol protocol, ManageYourSpacePageImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("ManageYourSpacePageImpressionEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.SECTION, 4, 8);
            protocol.writeI32(struct.section.value);
            protocol.writeFieldEnd();
            if (struct.subsection != null) {
                protocol.writeFieldBegin("subsection", 5, 8);
                protocol.writeI32(struct.subsection.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 7, 10);
            protocol.writeI64(struct.listing_id.longValue());
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
        if (!(other instanceof ManageYourSpacePageImpressionEvent)) {
            return false;
        }
        ManageYourSpacePageImpressionEvent that = (ManageYourSpacePageImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.subsection == that.subsection || (this.subsection != null && this.subsection.equals(that.subsection))) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035;
        if (this.subsection != null) {
            i = this.subsection.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ManageYourSpacePageImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", subsection=" + this.subsection + ", operation=" + this.operation + ", listing_id=" + this.listing_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
