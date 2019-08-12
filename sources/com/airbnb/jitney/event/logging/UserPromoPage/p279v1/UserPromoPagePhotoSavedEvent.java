package com.airbnb.jitney.event.logging.UserPromoPage.p279v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.UserPromoPage.v1.UserPromoPagePhotoSavedEvent */
public final class UserPromoPagePhotoSavedEvent implements Struct {
    public static final Adapter<UserPromoPagePhotoSavedEvent, Object> ADAPTER = new UserPromoPagePhotoSavedEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Long promo_page_picture_id;
    public final String schema;
    public final String section;

    /* renamed from: com.airbnb.jitney.event.logging.UserPromoPage.v1.UserPromoPagePhotoSavedEvent$UserPromoPagePhotoSavedEventAdapter */
    private static final class UserPromoPagePhotoSavedEventAdapter implements Adapter<UserPromoPagePhotoSavedEvent, Object> {
        private UserPromoPagePhotoSavedEventAdapter() {
        }

        public void write(Protocol protocol, UserPromoPagePhotoSavedEvent struct) throws IOException {
            protocol.writeStructBegin("UserPromoPagePhotoSavedEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.SECTION, 4, PassportService.SF_DG11);
            protocol.writeString(struct.section);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("promo_page_picture_id", 6, 10);
            protocol.writeI64(struct.promo_page_picture_id.longValue());
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
        if (!(other instanceof UserPromoPagePhotoSavedEvent)) {
            return false;
        }
        UserPromoPagePhotoSavedEvent that = (UserPromoPagePhotoSavedEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.promo_page_picture_id == that.promo_page_picture_id || this.promo_page_picture_id.equals(that.promo_page_picture_id)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.promo_page_picture_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "UserPromoPagePhotoSavedEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", operation=" + this.operation + ", promo_page_picture_id=" + this.promo_page_picture_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
