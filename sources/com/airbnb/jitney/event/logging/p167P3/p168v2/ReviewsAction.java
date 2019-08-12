package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.facebook.share.internal.ShareConstants;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.ReviewsAction */
public final class ReviewsAction implements Struct {
    public static final Adapter<ReviewsAction, Object> ADAPTER = new ReviewsActionAdapter();
    public final ReviewsActionType action_type;
    public final ReviewContext review_context;
    public final SectionScrollData review_scroll_data;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.ReviewsAction$ReviewsActionAdapter */
    private static final class ReviewsActionAdapter implements Adapter<ReviewsAction, Object> {
        private ReviewsActionAdapter() {
        }

        public void write(Protocol protocol, ReviewsAction struct) throws IOException {
            protocol.writeStructBegin("ReviewsAction");
            if (struct.action_type != null) {
                protocol.writeFieldBegin(ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE, 1, 8);
                protocol.writeI32(struct.action_type.value);
                protocol.writeFieldEnd();
            }
            if (struct.review_context != null) {
                protocol.writeFieldBegin("review_context", 2, PassportService.SF_DG12);
                ReviewContext.ADAPTER.write(protocol, struct.review_context);
                protocol.writeFieldEnd();
            }
            if (struct.review_scroll_data != null) {
                protocol.writeFieldBegin("review_scroll_data", 3, PassportService.SF_DG12);
                SectionScrollData.ADAPTER.write(protocol, struct.review_scroll_data);
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
        if (!(other instanceof ReviewsAction)) {
            return false;
        }
        ReviewsAction that = (ReviewsAction) other;
        if ((this.action_type == that.action_type || (this.action_type != null && this.action_type.equals(that.action_type))) && (this.review_context == that.review_context || (this.review_context != null && this.review_context.equals(that.review_context)))) {
            if (this.review_scroll_data == that.review_scroll_data) {
                return true;
            }
            if (this.review_scroll_data != null && this.review_scroll_data.equals(that.review_scroll_data)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((16777619 ^ (this.action_type == null ? 0 : this.action_type.hashCode())) * -2128831035) ^ (this.review_context == null ? 0 : this.review_context.hashCode())) * -2128831035;
        if (this.review_scroll_data != null) {
            i = this.review_scroll_data.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ReviewsAction{action_type=" + this.action_type + ", review_context=" + this.review_context + ", review_scroll_data=" + this.review_scroll_data + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
