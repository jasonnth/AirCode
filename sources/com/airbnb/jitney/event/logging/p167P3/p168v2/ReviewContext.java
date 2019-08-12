package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.facebook.react.uimanager.ViewProps;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.ReviewContext */
public final class ReviewContext implements Struct {
    public static final Adapter<ReviewContext, Object> ADAPTER = new ReviewContextAdapter();
    public final Long current_page;
    public final Long max_position;
    public final Long position;
    public final Long review_id;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.ReviewContext$ReviewContextAdapter */
    private static final class ReviewContextAdapter implements Adapter<ReviewContext, Object> {
        private ReviewContextAdapter() {
        }

        public void write(Protocol protocol, ReviewContext struct) throws IOException {
            protocol.writeStructBegin("ReviewContext");
            if (struct.review_id != null) {
                protocol.writeFieldBegin("review_id", 1, 10);
                protocol.writeI64(struct.review_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.position != null) {
                protocol.writeFieldBegin(ViewProps.POSITION, 2, 10);
                protocol.writeI64(struct.position.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.max_position != null) {
                protocol.writeFieldBegin("max_position", 3, 10);
                protocol.writeI64(struct.max_position.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.current_page != null) {
                protocol.writeFieldBegin("current_page", 4, 10);
                protocol.writeI64(struct.current_page.longValue());
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
        if (!(other instanceof ReviewContext)) {
            return false;
        }
        ReviewContext that = (ReviewContext) other;
        if ((this.review_id == that.review_id || (this.review_id != null && this.review_id.equals(that.review_id))) && ((this.position == that.position || (this.position != null && this.position.equals(that.position))) && (this.max_position == that.max_position || (this.max_position != null && this.max_position.equals(that.max_position))))) {
            if (this.current_page == that.current_page) {
                return true;
            }
            if (this.current_page != null && this.current_page.equals(that.current_page)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((16777619 ^ (this.review_id == null ? 0 : this.review_id.hashCode())) * -2128831035) ^ (this.position == null ? 0 : this.position.hashCode())) * -2128831035) ^ (this.max_position == null ? 0 : this.max_position.hashCode())) * -2128831035;
        if (this.current_page != null) {
            i = this.current_page.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ReviewContext{review_id=" + this.review_id + ", position=" + this.position + ", max_position=" + this.max_position + ", current_page=" + this.current_page + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
