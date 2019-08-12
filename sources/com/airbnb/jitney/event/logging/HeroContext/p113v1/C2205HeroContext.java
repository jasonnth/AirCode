package com.airbnb.jitney.event.logging.HeroContext.p113v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.HeroContext.v1.HeroContext */
public final class C2205HeroContext implements Struct {
    public static final Adapter<C2205HeroContext, Builder> ADAPTER = new HeroContextAdapter();
    public final String alert_text;
    public final List<String> button_texts;
    public final Long hero_id;
    public final String hero_type;
    public final Boolean is_timed;
    public final String text;
    public final Long time_scheduled;

    /* renamed from: com.airbnb.jitney.event.logging.HeroContext.v1.HeroContext$Builder */
    public static final class Builder implements StructBuilder<C2205HeroContext> {
        /* access modifiers changed from: private */
        public String alert_text;
        /* access modifiers changed from: private */
        public List<String> button_texts;
        /* access modifiers changed from: private */
        public Long hero_id;
        /* access modifiers changed from: private */
        public String hero_type;
        /* access modifiers changed from: private */
        public Boolean is_timed;
        /* access modifiers changed from: private */
        public String text;
        /* access modifiers changed from: private */
        public Long time_scheduled;

        private Builder() {
        }

        public Builder(Long hero_id2) {
            this.hero_id = hero_id2;
        }

        public Builder text(String text2) {
            this.text = text2;
            return this;
        }

        public Builder alert_text(String alert_text2) {
            this.alert_text = alert_text2;
            return this;
        }

        public Builder button_texts(List<String> button_texts2) {
            this.button_texts = button_texts2;
            return this;
        }

        public Builder is_timed(Boolean is_timed2) {
            this.is_timed = is_timed2;
            return this;
        }

        public Builder time_scheduled(Long time_scheduled2) {
            this.time_scheduled = time_scheduled2;
            return this;
        }

        public Builder hero_type(String hero_type2) {
            this.hero_type = hero_type2;
            return this;
        }

        public C2205HeroContext build() {
            if (this.hero_id != null) {
                return new C2205HeroContext(this);
            }
            throw new IllegalStateException("Required field 'hero_id' is missing");
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.HeroContext.v1.HeroContext$HeroContextAdapter */
    private static final class HeroContextAdapter implements Adapter<C2205HeroContext, Builder> {
        private HeroContextAdapter() {
        }

        public void write(Protocol protocol, C2205HeroContext struct) throws IOException {
            protocol.writeStructBegin("HeroContext");
            protocol.writeFieldBegin("hero_id", 1, 10);
            protocol.writeI64(struct.hero_id.longValue());
            protocol.writeFieldEnd();
            if (struct.text != null) {
                protocol.writeFieldBegin("text", 2, PassportService.SF_DG11);
                protocol.writeString(struct.text);
                protocol.writeFieldEnd();
            }
            if (struct.alert_text != null) {
                protocol.writeFieldBegin("alert_text", 3, PassportService.SF_DG11);
                protocol.writeString(struct.alert_text);
                protocol.writeFieldEnd();
            }
            if (struct.button_texts != null) {
                protocol.writeFieldBegin("button_texts", 4, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.button_texts.size());
                for (String item0 : struct.button_texts) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.is_timed != null) {
                protocol.writeFieldBegin("is_timed", 5, 2);
                protocol.writeBool(struct.is_timed.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.time_scheduled != null) {
                protocol.writeFieldBegin("time_scheduled", 6, 10);
                protocol.writeI64(struct.time_scheduled.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.hero_type != null) {
                protocol.writeFieldBegin("hero_type", 7, PassportService.SF_DG11);
                protocol.writeString(struct.hero_type);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2205HeroContext(Builder builder) {
        this.hero_id = builder.hero_id;
        this.text = builder.text;
        this.alert_text = builder.alert_text;
        this.button_texts = builder.button_texts == null ? null : Collections.unmodifiableList(builder.button_texts);
        this.is_timed = builder.is_timed;
        this.time_scheduled = builder.time_scheduled;
        this.hero_type = builder.hero_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2205HeroContext)) {
            return false;
        }
        C2205HeroContext that = (C2205HeroContext) other;
        if ((this.hero_id == that.hero_id || this.hero_id.equals(that.hero_id)) && ((this.text == that.text || (this.text != null && this.text.equals(that.text))) && ((this.alert_text == that.alert_text || (this.alert_text != null && this.alert_text.equals(that.alert_text))) && ((this.button_texts == that.button_texts || (this.button_texts != null && this.button_texts.equals(that.button_texts))) && ((this.is_timed == that.is_timed || (this.is_timed != null && this.is_timed.equals(that.is_timed))) && (this.time_scheduled == that.time_scheduled || (this.time_scheduled != null && this.time_scheduled.equals(that.time_scheduled)))))))) {
            if (this.hero_type == that.hero_type) {
                return true;
            }
            if (this.hero_type != null && this.hero_type.equals(that.hero_type)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((16777619 ^ this.hero_id.hashCode()) * -2128831035) ^ (this.text == null ? 0 : this.text.hashCode())) * -2128831035) ^ (this.alert_text == null ? 0 : this.alert_text.hashCode())) * -2128831035) ^ (this.button_texts == null ? 0 : this.button_texts.hashCode())) * -2128831035) ^ (this.is_timed == null ? 0 : this.is_timed.hashCode())) * -2128831035) ^ (this.time_scheduled == null ? 0 : this.time_scheduled.hashCode())) * -2128831035;
        if (this.hero_type != null) {
            i = this.hero_type.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "HeroContext{hero_id=" + this.hero_id + ", text=" + this.text + ", alert_text=" + this.alert_text + ", button_texts=" + this.button_texts + ", is_timed=" + this.is_timed + ", time_scheduled=" + this.time_scheduled + ", hero_type=" + this.hero_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
