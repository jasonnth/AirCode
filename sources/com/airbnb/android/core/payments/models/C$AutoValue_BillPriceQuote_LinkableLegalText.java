package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.payments.models.BillPriceQuote.Link;
import com.airbnb.android.core.payments.models.BillPriceQuote.LinkableLegalText;
import java.util.List;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BillPriceQuote_LinkableLegalText reason: invalid class name */
abstract class C$AutoValue_BillPriceQuote_LinkableLegalText extends LinkableLegalText {
    private final List<Link> links;
    private final String text;
    private final String title;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BillPriceQuote_LinkableLegalText$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.BillPriceQuote.LinkableLegalText.Builder {
        private List<Link> links;
        private String text;
        private String title;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.LinkableLegalText.Builder title(String title2) {
            if (title2 == null) {
                throw new NullPointerException("Null title");
            }
            this.title = title2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.LinkableLegalText.Builder text(String text2) {
            if (text2 == null) {
                throw new NullPointerException("Null text");
            }
            this.text = text2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.LinkableLegalText.Builder links(List<Link> links2) {
            if (links2 == null) {
                throw new NullPointerException("Null links");
            }
            this.links = links2;
            return this;
        }

        public LinkableLegalText build() {
            String missing = "";
            if (this.title == null) {
                missing = missing + " title";
            }
            if (this.text == null) {
                missing = missing + " text";
            }
            if (this.links == null) {
                missing = missing + " links";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BillPriceQuote_LinkableLegalText(this.title, this.text, this.links);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BillPriceQuote_LinkableLegalText(String title2, String text2, List<Link> links2) {
        if (title2 == null) {
            throw new NullPointerException("Null title");
        }
        this.title = title2;
        if (text2 == null) {
            throw new NullPointerException("Null text");
        }
        this.text = text2;
        if (links2 == null) {
            throw new NullPointerException("Null links");
        }
        this.links = links2;
    }

    public String title() {
        return this.title;
    }

    public String text() {
        return this.text;
    }

    public List<Link> links() {
        return this.links;
    }

    public String toString() {
        return "LinkableLegalText{title=" + this.title + ", text=" + this.text + ", links=" + this.links + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkableLegalText)) {
            return false;
        }
        LinkableLegalText that = (LinkableLegalText) o;
        if (!this.title.equals(that.title()) || !this.text.equals(that.text()) || !this.links.equals(that.links())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.title.hashCode()) * 1000003) ^ this.text.hashCode()) * 1000003) ^ this.links.hashCode();
    }
}
