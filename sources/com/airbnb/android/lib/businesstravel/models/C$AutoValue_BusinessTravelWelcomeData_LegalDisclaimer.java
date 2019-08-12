package com.airbnb.android.lib.businesstravel.models;

import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.LegalDisclaimer;
import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Link;
import java.util.List;

/* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BusinessTravelWelcomeData_LegalDisclaimer reason: invalid class name */
abstract class C$AutoValue_BusinessTravelWelcomeData_LegalDisclaimer extends LegalDisclaimer {
    private final String fullText;
    private final List<Link> links;

    /* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BusinessTravelWelcomeData_LegalDisclaimer$Builder */
    static final class Builder extends com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.LegalDisclaimer.Builder {
        private String fullText;
        private List<Link> links;

        Builder() {
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.LegalDisclaimer.Builder fullText(String fullText2) {
            if (fullText2 == null) {
                throw new NullPointerException("Null fullText");
            }
            this.fullText = fullText2;
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.LegalDisclaimer.Builder links(List<Link> links2) {
            if (links2 == null) {
                throw new NullPointerException("Null links");
            }
            this.links = links2;
            return this;
        }

        public LegalDisclaimer build() {
            String missing = "";
            if (this.fullText == null) {
                missing = missing + " fullText";
            }
            if (this.links == null) {
                missing = missing + " links";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BusinessTravelWelcomeData_LegalDisclaimer(this.fullText, this.links);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BusinessTravelWelcomeData_LegalDisclaimer(String fullText2, List<Link> links2) {
        if (fullText2 == null) {
            throw new NullPointerException("Null fullText");
        }
        this.fullText = fullText2;
        if (links2 == null) {
            throw new NullPointerException("Null links");
        }
        this.links = links2;
    }

    public String fullText() {
        return this.fullText;
    }

    public List<Link> links() {
        return this.links;
    }

    public String toString() {
        return "LegalDisclaimer{fullText=" + this.fullText + ", links=" + this.links + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LegalDisclaimer)) {
            return false;
        }
        LegalDisclaimer that = (LegalDisclaimer) o;
        if (!this.fullText.equals(that.fullText()) || !this.links.equals(that.links())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.fullText.hashCode()) * 1000003) ^ this.links.hashCode();
    }
}
