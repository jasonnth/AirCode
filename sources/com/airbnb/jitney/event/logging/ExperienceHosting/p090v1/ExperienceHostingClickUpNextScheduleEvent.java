package com.airbnb.jitney.event.logging.ExperienceHosting.p090v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickUpNextScheduleEvent */
public final class ExperienceHostingClickUpNextScheduleEvent implements Struct {
    public static final Adapter<ExperienceHostingClickUpNextScheduleEvent, Object> ADAPTER = new ExperienceHostingClickUpNextScheduleEventAdapter();
    public final String confirmation_code;
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long experience_id;
    public final Long guests;
    public final C2451Operation operation;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickUpNextScheduleEvent$ExperienceHostingClickUpNextScheduleEventAdapter */
    private static final class ExperienceHostingClickUpNextScheduleEventAdapter implements Adapter<ExperienceHostingClickUpNextScheduleEvent, Object> {
        private ExperienceHostingClickUpNextScheduleEventAdapter() {
        }

        public void write(Protocol protocol, ExperienceHostingClickUpNextScheduleEvent struct) throws IOException {
            protocol.writeStructBegin("ExperienceHostingClickUpNextScheduleEvent");
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
            if (struct.experience_id != null) {
                protocol.writeFieldBegin("experience_id", 4, 10);
                protocol.writeI64(struct.experience_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.confirmation_code != null) {
                protocol.writeFieldBegin("confirmation_code", 5, PassportService.SF_DG11);
                protocol.writeString(struct.confirmation_code);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 7, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
            for (String item0 : struct.dates) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 8, 10);
            protocol.writeI64(struct.guests.longValue());
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
        if (!(other instanceof ExperienceHostingClickUpNextScheduleEvent)) {
            return false;
        }
        ExperienceHostingClickUpNextScheduleEvent that = (ExperienceHostingClickUpNextScheduleEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.experience_id == that.experience_id || (this.experience_id != null && this.experience_id.equals(that.experience_id))) && ((this.confirmation_code == that.confirmation_code || (this.confirmation_code != null && this.confirmation_code.equals(that.confirmation_code))) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.dates == that.dates || this.dates.equals(that.dates)) && (this.guests == that.guests || this.guests.equals(that.guests)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ (this.experience_id == null ? 0 : this.experience_id.hashCode())) * -2128831035;
        if (this.confirmation_code != null) {
            i = this.confirmation_code.hashCode();
        }
        return (((((((code ^ i) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.dates.hashCode()) * -2128831035) ^ this.guests.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperienceHostingClickUpNextScheduleEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", experience_id=" + this.experience_id + ", confirmation_code=" + this.confirmation_code + ", operation=" + this.operation + ", dates=" + this.dates + ", guests=" + this.guests + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
