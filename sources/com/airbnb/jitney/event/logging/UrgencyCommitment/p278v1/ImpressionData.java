package com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.ImpressionData */
public final class ImpressionData implements Struct {
    public static final Adapter<ImpressionData, Builder> ADAPTER = new ImpressionDataAdapter();
    public final String body_variation;
    public final String context_variation;
    public final String headline_variation;
    public final String icon;
    public final String mario_group_name;
    public final Long mario_version;
    public final String message_type;

    /* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.ImpressionData$Builder */
    public static final class Builder implements StructBuilder<ImpressionData> {
        /* access modifiers changed from: private */
        public String body_variation;
        /* access modifiers changed from: private */
        public String context_variation;
        /* access modifiers changed from: private */
        public String headline_variation;
        /* access modifiers changed from: private */
        public String icon;
        /* access modifiers changed from: private */
        public String mario_group_name;
        /* access modifiers changed from: private */
        public Long mario_version;
        /* access modifiers changed from: private */
        public String message_type;

        public Builder headline_variation(String headline_variation2) {
            this.headline_variation = headline_variation2;
            return this;
        }

        public Builder body_variation(String body_variation2) {
            this.body_variation = body_variation2;
            return this;
        }

        public Builder context_variation(String context_variation2) {
            this.context_variation = context_variation2;
            return this;
        }

        public Builder message_type(String message_type2) {
            this.message_type = message_type2;
            return this;
        }

        public ImpressionData build() {
            return new ImpressionData(this);
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.ImpressionData$ImpressionDataAdapter */
    private static final class ImpressionDataAdapter implements Adapter<ImpressionData, Builder> {
        private ImpressionDataAdapter() {
        }

        public void write(Protocol protocol, ImpressionData struct) throws IOException {
            protocol.writeStructBegin("ImpressionData");
            if (struct.headline_variation != null) {
                protocol.writeFieldBegin("headline_variation", 1, PassportService.SF_DG11);
                protocol.writeString(struct.headline_variation);
                protocol.writeFieldEnd();
            }
            if (struct.body_variation != null) {
                protocol.writeFieldBegin("body_variation", 2, PassportService.SF_DG11);
                protocol.writeString(struct.body_variation);
                protocol.writeFieldEnd();
            }
            if (struct.context_variation != null) {
                protocol.writeFieldBegin("context_variation", 3, PassportService.SF_DG11);
                protocol.writeString(struct.context_variation);
                protocol.writeFieldEnd();
            }
            if (struct.icon != null) {
                protocol.writeFieldBegin("icon", 4, PassportService.SF_DG11);
                protocol.writeString(struct.icon);
                protocol.writeFieldEnd();
            }
            if (struct.message_type != null) {
                protocol.writeFieldBegin("message_type", 5, PassportService.SF_DG11);
                protocol.writeString(struct.message_type);
                protocol.writeFieldEnd();
            }
            if (struct.mario_version != null) {
                protocol.writeFieldBegin("mario_version", 6, 10);
                protocol.writeI64(struct.mario_version.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.mario_group_name != null) {
                protocol.writeFieldBegin("mario_group_name", 7, PassportService.SF_DG11);
                protocol.writeString(struct.mario_group_name);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ImpressionData(Builder builder) {
        this.headline_variation = builder.headline_variation;
        this.body_variation = builder.body_variation;
        this.context_variation = builder.context_variation;
        this.icon = builder.icon;
        this.message_type = builder.message_type;
        this.mario_version = builder.mario_version;
        this.mario_group_name = builder.mario_group_name;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ImpressionData)) {
            return false;
        }
        ImpressionData that = (ImpressionData) other;
        if ((this.headline_variation == that.headline_variation || (this.headline_variation != null && this.headline_variation.equals(that.headline_variation))) && ((this.body_variation == that.body_variation || (this.body_variation != null && this.body_variation.equals(that.body_variation))) && ((this.context_variation == that.context_variation || (this.context_variation != null && this.context_variation.equals(that.context_variation))) && ((this.icon == that.icon || (this.icon != null && this.icon.equals(that.icon))) && ((this.message_type == that.message_type || (this.message_type != null && this.message_type.equals(that.message_type))) && (this.mario_version == that.mario_version || (this.mario_version != null && this.mario_version.equals(that.mario_version)))))))) {
            if (this.mario_group_name == that.mario_group_name) {
                return true;
            }
            if (this.mario_group_name != null && this.mario_group_name.equals(that.mario_group_name)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((16777619 ^ (this.headline_variation == null ? 0 : this.headline_variation.hashCode())) * -2128831035) ^ (this.body_variation == null ? 0 : this.body_variation.hashCode())) * -2128831035) ^ (this.context_variation == null ? 0 : this.context_variation.hashCode())) * -2128831035) ^ (this.icon == null ? 0 : this.icon.hashCode())) * -2128831035) ^ (this.message_type == null ? 0 : this.message_type.hashCode())) * -2128831035) ^ (this.mario_version == null ? 0 : this.mario_version.hashCode())) * -2128831035;
        if (this.mario_group_name != null) {
            i = this.mario_group_name.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ImpressionData{headline_variation=" + this.headline_variation + ", body_variation=" + this.body_variation + ", context_variation=" + this.context_variation + ", icon=" + this.icon + ", message_type=" + this.message_type + ", mario_version=" + this.mario_version + ", mario_group_name=" + this.mario_group_name + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
