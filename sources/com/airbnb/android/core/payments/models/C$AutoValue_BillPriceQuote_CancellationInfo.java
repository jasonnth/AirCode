package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.payments.models.BillPriceQuote.CancellationInfo;
import java.util.List;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BillPriceQuote_CancellationInfo reason: invalid class name */
abstract class C$AutoValue_BillPriceQuote_CancellationInfo extends CancellationInfo {
    private final List<String> subtitles;
    private final String title;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BillPriceQuote_CancellationInfo$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.BillPriceQuote.CancellationInfo.Builder {
        private List<String> subtitles;
        private String title;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.CancellationInfo.Builder title(String title2) {
            if (title2 == null) {
                throw new NullPointerException("Null title");
            }
            this.title = title2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BillPriceQuote.CancellationInfo.Builder subtitles(List<String> subtitles2) {
            if (subtitles2 == null) {
                throw new NullPointerException("Null subtitles");
            }
            this.subtitles = subtitles2;
            return this;
        }

        public CancellationInfo build() {
            String missing = "";
            if (this.title == null) {
                missing = missing + " title";
            }
            if (this.subtitles == null) {
                missing = missing + " subtitles";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BillPriceQuote_CancellationInfo(this.title, this.subtitles);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BillPriceQuote_CancellationInfo(String title2, List<String> subtitles2) {
        if (title2 == null) {
            throw new NullPointerException("Null title");
        }
        this.title = title2;
        if (subtitles2 == null) {
            throw new NullPointerException("Null subtitles");
        }
        this.subtitles = subtitles2;
    }

    public String title() {
        return this.title;
    }

    public List<String> subtitles() {
        return this.subtitles;
    }

    public String toString() {
        return "CancellationInfo{title=" + this.title + ", subtitles=" + this.subtitles + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CancellationInfo)) {
            return false;
        }
        CancellationInfo that = (CancellationInfo) o;
        if (!this.title.equals(that.title()) || !this.subtitles.equals(that.subtitles())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.title.hashCode()) * 1000003) ^ this.subtitles.hashCode();
    }
}
