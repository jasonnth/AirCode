package com.airbnb.jitney.event.logging.Systems.p263v1;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Systems.v1.SystemsMobileCrashEvent */
public final class SystemsMobileCrashEvent implements Struct {
    public static final Adapter<SystemsMobileCrashEvent, Builder> ADAPTER = new SystemsMobileCrashEventAdapter();
    public final Context context;
    public final String crash_group;
    public final String crash_report_platform_identifier;
    public final String crash_report_platform_name;
    public final Long crash_time;
    public final String event_name;
    public final String exception_name;
    public final List<String> experiment_assignment_breadcrumbs;
    public final String git_sha;
    public final Boolean is_app_kill;
    public final Boolean is_host_mode;
    public final Long line_number;
    public final Boolean low_memory;
    public final String os_version;
    public final String schema;
    public final String signal;
    public final Long start_time;
    public final String version;
    public final Long version_code;
    public final List<String> view_breadcrumbs;

    /* renamed from: com.airbnb.jitney.event.logging.Systems.v1.SystemsMobileCrashEvent$Builder */
    public static final class Builder implements StructBuilder<SystemsMobileCrashEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String crash_group;
        /* access modifiers changed from: private */
        public String crash_report_platform_identifier;
        /* access modifiers changed from: private */
        public String crash_report_platform_name;
        /* access modifiers changed from: private */
        public Long crash_time;
        /* access modifiers changed from: private */
        public String event_name = "systems_mobile_crash";
        /* access modifiers changed from: private */
        public String exception_name;
        /* access modifiers changed from: private */
        public List<String> experiment_assignment_breadcrumbs;
        /* access modifiers changed from: private */
        public String git_sha;
        /* access modifiers changed from: private */
        public Boolean is_app_kill;
        /* access modifiers changed from: private */
        public Boolean is_host_mode;
        /* access modifiers changed from: private */
        public Long line_number;
        /* access modifiers changed from: private */
        public Boolean low_memory;
        /* access modifiers changed from: private */
        public String os_version;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Systems:SystemsMobileCrashEvent:1.0.0";
        /* access modifiers changed from: private */
        public String signal;
        /* access modifiers changed from: private */
        public Long start_time;
        /* access modifiers changed from: private */
        public String version;
        /* access modifiers changed from: private */
        public Long version_code;
        /* access modifiers changed from: private */
        public List<String> view_breadcrumbs;

        private Builder() {
        }

        public Builder(Context context2, String crash_group2, String version2, Long version_code2, Long start_time2, Long crash_time2, String os_version2) {
            this.context = context2;
            this.crash_group = crash_group2;
            this.version = version2;
            this.version_code = version_code2;
            this.start_time = start_time2;
            this.crash_time = crash_time2;
            this.os_version = os_version2;
        }

        public Builder git_sha(String git_sha2) {
            this.git_sha = git_sha2;
            return this;
        }

        public Builder line_number(Long line_number2) {
            this.line_number = line_number2;
            return this;
        }

        public Builder view_breadcrumbs(List<String> view_breadcrumbs2) {
            this.view_breadcrumbs = view_breadcrumbs2;
            return this;
        }

        public Builder exception_name(String exception_name2) {
            this.exception_name = exception_name2;
            return this;
        }

        public SystemsMobileCrashEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.crash_group == null) {
                throw new IllegalStateException("Required field 'crash_group' is missing");
            } else if (this.version == null) {
                throw new IllegalStateException("Required field 'version' is missing");
            } else if (this.version_code == null) {
                throw new IllegalStateException("Required field 'version_code' is missing");
            } else if (this.start_time == null) {
                throw new IllegalStateException("Required field 'start_time' is missing");
            } else if (this.crash_time == null) {
                throw new IllegalStateException("Required field 'crash_time' is missing");
            } else if (this.os_version != null) {
                return new SystemsMobileCrashEvent(this);
            } else {
                throw new IllegalStateException("Required field 'os_version' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Systems.v1.SystemsMobileCrashEvent$SystemsMobileCrashEventAdapter */
    private static final class SystemsMobileCrashEventAdapter implements Adapter<SystemsMobileCrashEvent, Builder> {
        private SystemsMobileCrashEventAdapter() {
        }

        public void write(Protocol protocol, SystemsMobileCrashEvent struct) throws IOException {
            protocol.writeStructBegin("SystemsMobileCrashEvent");
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
            protocol.writeFieldBegin("crash_group", 3, PassportService.SF_DG11);
            protocol.writeString(struct.crash_group);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("version", 4, PassportService.SF_DG11);
            protocol.writeString(struct.version);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("version_code", 5, 10);
            protocol.writeI64(struct.version_code.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("start_time", 6, 10);
            protocol.writeI64(struct.start_time.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("crash_time", 7, 10);
            protocol.writeI64(struct.crash_time.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("os_version", 8, PassportService.SF_DG11);
            protocol.writeString(struct.os_version);
            protocol.writeFieldEnd();
            if (struct.git_sha != null) {
                protocol.writeFieldBegin("git_sha", 9, PassportService.SF_DG11);
                protocol.writeString(struct.git_sha);
                protocol.writeFieldEnd();
            }
            if (struct.line_number != null) {
                protocol.writeFieldBegin("line_number", 10, 10);
                protocol.writeI64(struct.line_number.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.low_memory != null) {
                protocol.writeFieldBegin("low_memory", 11, 2);
                protocol.writeBool(struct.low_memory.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.is_app_kill != null) {
                protocol.writeFieldBegin("is_app_kill", 12, 2);
                protocol.writeBool(struct.is_app_kill.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.crash_report_platform_name != null) {
                protocol.writeFieldBegin("crash_report_platform_name", 13, PassportService.SF_DG11);
                protocol.writeString(struct.crash_report_platform_name);
                protocol.writeFieldEnd();
            }
            if (struct.crash_report_platform_identifier != null) {
                protocol.writeFieldBegin("crash_report_platform_identifier", 14, PassportService.SF_DG11);
                protocol.writeString(struct.crash_report_platform_identifier);
                protocol.writeFieldEnd();
            }
            if (struct.view_breadcrumbs != null) {
                protocol.writeFieldBegin("view_breadcrumbs", 15, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.view_breadcrumbs.size());
                for (String item0 : struct.view_breadcrumbs) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.experiment_assignment_breadcrumbs != null) {
                protocol.writeFieldBegin("experiment_assignment_breadcrumbs", 16, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.experiment_assignment_breadcrumbs.size());
                for (String item02 : struct.experiment_assignment_breadcrumbs) {
                    protocol.writeString(item02);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.exception_name != null) {
                protocol.writeFieldBegin("exception_name", 17, PassportService.SF_DG11);
                protocol.writeString(struct.exception_name);
                protocol.writeFieldEnd();
            }
            if (struct.signal != null) {
                protocol.writeFieldBegin("signal", 18, PassportService.SF_DG11);
                protocol.writeString(struct.signal);
                protocol.writeFieldEnd();
            }
            if (struct.is_host_mode != null) {
                protocol.writeFieldBegin("is_host_mode", 19, 2);
                protocol.writeBool(struct.is_host_mode.booleanValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SystemsMobileCrashEvent(Builder builder) {
        List<String> list = null;
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.crash_group = builder.crash_group;
        this.version = builder.version;
        this.version_code = builder.version_code;
        this.start_time = builder.start_time;
        this.crash_time = builder.crash_time;
        this.os_version = builder.os_version;
        this.git_sha = builder.git_sha;
        this.line_number = builder.line_number;
        this.low_memory = builder.low_memory;
        this.is_app_kill = builder.is_app_kill;
        this.crash_report_platform_name = builder.crash_report_platform_name;
        this.crash_report_platform_identifier = builder.crash_report_platform_identifier;
        this.view_breadcrumbs = builder.view_breadcrumbs == null ? null : Collections.unmodifiableList(builder.view_breadcrumbs);
        if (builder.experiment_assignment_breadcrumbs != null) {
            list = Collections.unmodifiableList(builder.experiment_assignment_breadcrumbs);
        }
        this.experiment_assignment_breadcrumbs = list;
        this.exception_name = builder.exception_name;
        this.signal = builder.signal;
        this.is_host_mode = builder.is_host_mode;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SystemsMobileCrashEvent)) {
            return false;
        }
        SystemsMobileCrashEvent that = (SystemsMobileCrashEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.crash_group == that.crash_group || this.crash_group.equals(that.crash_group)) && ((this.version == that.version || this.version.equals(that.version)) && ((this.version_code == that.version_code || this.version_code.equals(that.version_code)) && ((this.start_time == that.start_time || this.start_time.equals(that.start_time)) && ((this.crash_time == that.crash_time || this.crash_time.equals(that.crash_time)) && ((this.os_version == that.os_version || this.os_version.equals(that.os_version)) && ((this.git_sha == that.git_sha || (this.git_sha != null && this.git_sha.equals(that.git_sha))) && ((this.line_number == that.line_number || (this.line_number != null && this.line_number.equals(that.line_number))) && ((this.low_memory == that.low_memory || (this.low_memory != null && this.low_memory.equals(that.low_memory))) && ((this.is_app_kill == that.is_app_kill || (this.is_app_kill != null && this.is_app_kill.equals(that.is_app_kill))) && ((this.crash_report_platform_name == that.crash_report_platform_name || (this.crash_report_platform_name != null && this.crash_report_platform_name.equals(that.crash_report_platform_name))) && ((this.crash_report_platform_identifier == that.crash_report_platform_identifier || (this.crash_report_platform_identifier != null && this.crash_report_platform_identifier.equals(that.crash_report_platform_identifier))) && ((this.view_breadcrumbs == that.view_breadcrumbs || (this.view_breadcrumbs != null && this.view_breadcrumbs.equals(that.view_breadcrumbs))) && ((this.experiment_assignment_breadcrumbs == that.experiment_assignment_breadcrumbs || (this.experiment_assignment_breadcrumbs != null && this.experiment_assignment_breadcrumbs.equals(that.experiment_assignment_breadcrumbs))) && ((this.exception_name == that.exception_name || (this.exception_name != null && this.exception_name.equals(that.exception_name))) && (this.signal == that.signal || (this.signal != null && this.signal.equals(that.signal))))))))))))))))))))) {
            if (this.is_host_mode == that.is_host_mode) {
                return true;
            }
            if (this.is_host_mode != null && this.is_host_mode.equals(that.is_host_mode)) {
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
        int code = (((((((((((((((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.crash_group.hashCode()) * -2128831035) ^ this.version.hashCode()) * -2128831035) ^ this.version_code.hashCode()) * -2128831035) ^ this.start_time.hashCode()) * -2128831035) ^ this.crash_time.hashCode()) * -2128831035) ^ this.os_version.hashCode()) * -2128831035) ^ (this.git_sha == null ? 0 : this.git_sha.hashCode())) * -2128831035) ^ (this.line_number == null ? 0 : this.line_number.hashCode())) * -2128831035) ^ (this.low_memory == null ? 0 : this.low_memory.hashCode())) * -2128831035) ^ (this.is_app_kill == null ? 0 : this.is_app_kill.hashCode())) * -2128831035) ^ (this.crash_report_platform_name == null ? 0 : this.crash_report_platform_name.hashCode())) * -2128831035) ^ (this.crash_report_platform_identifier == null ? 0 : this.crash_report_platform_identifier.hashCode())) * -2128831035) ^ (this.view_breadcrumbs == null ? 0 : this.view_breadcrumbs.hashCode())) * -2128831035) ^ (this.experiment_assignment_breadcrumbs == null ? 0 : this.experiment_assignment_breadcrumbs.hashCode())) * -2128831035) ^ (this.exception_name == null ? 0 : this.exception_name.hashCode())) * -2128831035) ^ (this.signal == null ? 0 : this.signal.hashCode())) * -2128831035;
        if (this.is_host_mode != null) {
            i = this.is_host_mode.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SystemsMobileCrashEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", crash_group=" + this.crash_group + ", version=" + this.version + ", version_code=" + this.version_code + ", start_time=" + this.start_time + ", crash_time=" + this.crash_time + ", os_version=" + this.os_version + ", git_sha=" + this.git_sha + ", line_number=" + this.line_number + ", low_memory=" + this.low_memory + ", is_app_kill=" + this.is_app_kill + ", crash_report_platform_name=" + this.crash_report_platform_name + ", crash_report_platform_identifier=" + this.crash_report_platform_identifier + ", view_breadcrumbs=" + this.view_breadcrumbs + ", experiment_assignment_breadcrumbs=" + this.experiment_assignment_breadcrumbs + ", exception_name=" + this.exception_name + ", signal=" + this.signal + ", is_host_mode=" + this.is_host_mode + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
