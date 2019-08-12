package com.airbnb.jitney.event.logging.MobileCrash.p151v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.MobileCrash.v1.MobileCrashEvent */
public final class MobileCrashEvent implements Struct {
    public static final Adapter<MobileCrashEvent, Object> ADAPTER = new MobileCrashEventAdapter();
    public final String crash_group;
    public final String crash_report_platform_identifier;
    public final String crash_report_platform_name;
    public final Long crash_time;
    public final String event_name;
    public final String exception_name;
    public final String experiment_assignment_breadcrumbs;
    public final String git_sha;
    public final Boolean is_app_kill;
    public final String line_number;
    public final Boolean low_memory;
    public final String os_version;
    public final String schema;
    public final String signal;
    public final Long start_time;
    public final String version;
    public final Long version_code;
    public final String view_breadcrumbs;

    /* renamed from: com.airbnb.jitney.event.logging.MobileCrash.v1.MobileCrashEvent$MobileCrashEventAdapter */
    private static final class MobileCrashEventAdapter implements Adapter<MobileCrashEvent, Object> {
        private MobileCrashEventAdapter() {
        }

        public void write(Protocol protocol, MobileCrashEvent struct) throws IOException {
            protocol.writeStructBegin("MobileCrashEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("event_name", 1, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("crash_group", 2, PassportService.SF_DG11);
            protocol.writeString(struct.crash_group);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("version", 3, PassportService.SF_DG11);
            protocol.writeString(struct.version);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("version_code", 4, 10);
            protocol.writeI64(struct.version_code.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("start_time", 5, 10);
            protocol.writeI64(struct.start_time.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("crash_time", 6, 10);
            protocol.writeI64(struct.crash_time.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("os_version", 7, PassportService.SF_DG11);
            protocol.writeString(struct.os_version);
            protocol.writeFieldEnd();
            if (struct.git_sha != null) {
                protocol.writeFieldBegin("git_sha", 8, PassportService.SF_DG11);
                protocol.writeString(struct.git_sha);
                protocol.writeFieldEnd();
            }
            if (struct.line_number != null) {
                protocol.writeFieldBegin("line_number", 9, PassportService.SF_DG11);
                protocol.writeString(struct.line_number);
                protocol.writeFieldEnd();
            }
            if (struct.low_memory != null) {
                protocol.writeFieldBegin("low_memory", 10, 2);
                protocol.writeBool(struct.low_memory.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.is_app_kill != null) {
                protocol.writeFieldBegin("is_app_kill", 11, 2);
                protocol.writeBool(struct.is_app_kill.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.crash_report_platform_name != null) {
                protocol.writeFieldBegin("crash_report_platform_name", 12, PassportService.SF_DG11);
                protocol.writeString(struct.crash_report_platform_name);
                protocol.writeFieldEnd();
            }
            if (struct.crash_report_platform_identifier != null) {
                protocol.writeFieldBegin("crash_report_platform_identifier", 13, PassportService.SF_DG11);
                protocol.writeString(struct.crash_report_platform_identifier);
                protocol.writeFieldEnd();
            }
            if (struct.view_breadcrumbs != null) {
                protocol.writeFieldBegin("view_breadcrumbs", 14, PassportService.SF_DG11);
                protocol.writeString(struct.view_breadcrumbs);
                protocol.writeFieldEnd();
            }
            if (struct.experiment_assignment_breadcrumbs != null) {
                protocol.writeFieldBegin("experiment_assignment_breadcrumbs", 15, PassportService.SF_DG11);
                protocol.writeString(struct.experiment_assignment_breadcrumbs);
                protocol.writeFieldEnd();
            }
            if (struct.exception_name != null) {
                protocol.writeFieldBegin("exception_name", 16, PassportService.SF_DG11);
                protocol.writeString(struct.exception_name);
                protocol.writeFieldEnd();
            }
            if (struct.signal != null) {
                protocol.writeFieldBegin("signal", 17, PassportService.SF_DG11);
                protocol.writeString(struct.signal);
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
        if (!(other instanceof MobileCrashEvent)) {
            return false;
        }
        MobileCrashEvent that = (MobileCrashEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.crash_group == that.crash_group || this.crash_group.equals(that.crash_group)) && ((this.version == that.version || this.version.equals(that.version)) && ((this.version_code == that.version_code || this.version_code.equals(that.version_code)) && ((this.start_time == that.start_time || this.start_time.equals(that.start_time)) && ((this.crash_time == that.crash_time || this.crash_time.equals(that.crash_time)) && ((this.os_version == that.os_version || this.os_version.equals(that.os_version)) && ((this.git_sha == that.git_sha || (this.git_sha != null && this.git_sha.equals(that.git_sha))) && ((this.line_number == that.line_number || (this.line_number != null && this.line_number.equals(that.line_number))) && ((this.low_memory == that.low_memory || (this.low_memory != null && this.low_memory.equals(that.low_memory))) && ((this.is_app_kill == that.is_app_kill || (this.is_app_kill != null && this.is_app_kill.equals(that.is_app_kill))) && ((this.crash_report_platform_name == that.crash_report_platform_name || (this.crash_report_platform_name != null && this.crash_report_platform_name.equals(that.crash_report_platform_name))) && ((this.crash_report_platform_identifier == that.crash_report_platform_identifier || (this.crash_report_platform_identifier != null && this.crash_report_platform_identifier.equals(that.crash_report_platform_identifier))) && ((this.view_breadcrumbs == that.view_breadcrumbs || (this.view_breadcrumbs != null && this.view_breadcrumbs.equals(that.view_breadcrumbs))) && ((this.experiment_assignment_breadcrumbs == that.experiment_assignment_breadcrumbs || (this.experiment_assignment_breadcrumbs != null && this.experiment_assignment_breadcrumbs.equals(that.experiment_assignment_breadcrumbs))) && (this.exception_name == that.exception_name || (this.exception_name != null && this.exception_name.equals(that.exception_name))))))))))))))))))) {
            if (this.signal == that.signal) {
                return true;
            }
            if (this.signal != null && this.signal.equals(that.signal)) {
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
        int code = (((((((((((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.crash_group.hashCode()) * -2128831035) ^ this.version.hashCode()) * -2128831035) ^ this.version_code.hashCode()) * -2128831035) ^ this.start_time.hashCode()) * -2128831035) ^ this.crash_time.hashCode()) * -2128831035) ^ this.os_version.hashCode()) * -2128831035) ^ (this.git_sha == null ? 0 : this.git_sha.hashCode())) * -2128831035) ^ (this.line_number == null ? 0 : this.line_number.hashCode())) * -2128831035) ^ (this.low_memory == null ? 0 : this.low_memory.hashCode())) * -2128831035) ^ (this.is_app_kill == null ? 0 : this.is_app_kill.hashCode())) * -2128831035) ^ (this.crash_report_platform_name == null ? 0 : this.crash_report_platform_name.hashCode())) * -2128831035) ^ (this.crash_report_platform_identifier == null ? 0 : this.crash_report_platform_identifier.hashCode())) * -2128831035) ^ (this.view_breadcrumbs == null ? 0 : this.view_breadcrumbs.hashCode())) * -2128831035) ^ (this.experiment_assignment_breadcrumbs == null ? 0 : this.experiment_assignment_breadcrumbs.hashCode())) * -2128831035) ^ (this.exception_name == null ? 0 : this.exception_name.hashCode())) * -2128831035;
        if (this.signal != null) {
            i = this.signal.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "MobileCrashEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", crash_group=" + this.crash_group + ", version=" + this.version + ", version_code=" + this.version_code + ", start_time=" + this.start_time + ", crash_time=" + this.crash_time + ", os_version=" + this.os_version + ", git_sha=" + this.git_sha + ", line_number=" + this.line_number + ", low_memory=" + this.low_memory + ", is_app_kill=" + this.is_app_kill + ", crash_report_platform_name=" + this.crash_report_platform_name + ", crash_report_platform_identifier=" + this.crash_report_platform_identifier + ", view_breadcrumbs=" + this.view_breadcrumbs + ", experiment_assignment_breadcrumbs=" + this.experiment_assignment_breadcrumbs + ", exception_name=" + this.exception_name + ", signal=" + this.signal + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
