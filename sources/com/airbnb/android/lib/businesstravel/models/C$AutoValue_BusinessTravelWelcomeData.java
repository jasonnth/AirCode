package com.airbnb.android.lib.businesstravel.models;

import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.LegalDisclaimer;

/* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BusinessTravelWelcomeData reason: invalid class name */
abstract class C$AutoValue_BusinessTravelWelcomeData extends BusinessTravelWelcomeData {
    private final String buttonText;
    private final LegalDisclaimer legalDisclaimer;
    private final String marqueeBody;
    private final String marqueeImageUrl;
    private final String marqueeTitle;

    /* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BusinessTravelWelcomeData$Builder */
    static final class Builder extends com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Builder {
        private String buttonText;
        private LegalDisclaimer legalDisclaimer;
        private String marqueeBody;
        private String marqueeImageUrl;
        private String marqueeTitle;

        Builder() {
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Builder marqueeTitle(String marqueeTitle2) {
            if (marqueeTitle2 == null) {
                throw new NullPointerException("Null marqueeTitle");
            }
            this.marqueeTitle = marqueeTitle2;
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Builder marqueeBody(String marqueeBody2) {
            if (marqueeBody2 == null) {
                throw new NullPointerException("Null marqueeBody");
            }
            this.marqueeBody = marqueeBody2;
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Builder buttonText(String buttonText2) {
            if (buttonText2 == null) {
                throw new NullPointerException("Null buttonText");
            }
            this.buttonText = buttonText2;
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Builder marqueeImageUrl(String marqueeImageUrl2) {
            if (marqueeImageUrl2 == null) {
                throw new NullPointerException("Null marqueeImageUrl");
            }
            this.marqueeImageUrl = marqueeImageUrl2;
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Builder legalDisclaimer(LegalDisclaimer legalDisclaimer2) {
            if (legalDisclaimer2 == null) {
                throw new NullPointerException("Null legalDisclaimer");
            }
            this.legalDisclaimer = legalDisclaimer2;
            return this;
        }

        public BusinessTravelWelcomeData build() {
            String missing = "";
            if (this.marqueeTitle == null) {
                missing = missing + " marqueeTitle";
            }
            if (this.marqueeBody == null) {
                missing = missing + " marqueeBody";
            }
            if (this.buttonText == null) {
                missing = missing + " buttonText";
            }
            if (this.marqueeImageUrl == null) {
                missing = missing + " marqueeImageUrl";
            }
            if (this.legalDisclaimer == null) {
                missing = missing + " legalDisclaimer";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BusinessTravelWelcomeData(this.marqueeTitle, this.marqueeBody, this.buttonText, this.marqueeImageUrl, this.legalDisclaimer);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BusinessTravelWelcomeData(String marqueeTitle2, String marqueeBody2, String buttonText2, String marqueeImageUrl2, LegalDisclaimer legalDisclaimer2) {
        if (marqueeTitle2 == null) {
            throw new NullPointerException("Null marqueeTitle");
        }
        this.marqueeTitle = marqueeTitle2;
        if (marqueeBody2 == null) {
            throw new NullPointerException("Null marqueeBody");
        }
        this.marqueeBody = marqueeBody2;
        if (buttonText2 == null) {
            throw new NullPointerException("Null buttonText");
        }
        this.buttonText = buttonText2;
        if (marqueeImageUrl2 == null) {
            throw new NullPointerException("Null marqueeImageUrl");
        }
        this.marqueeImageUrl = marqueeImageUrl2;
        if (legalDisclaimer2 == null) {
            throw new NullPointerException("Null legalDisclaimer");
        }
        this.legalDisclaimer = legalDisclaimer2;
    }

    public String marqueeTitle() {
        return this.marqueeTitle;
    }

    public String marqueeBody() {
        return this.marqueeBody;
    }

    public String buttonText() {
        return this.buttonText;
    }

    public String marqueeImageUrl() {
        return this.marqueeImageUrl;
    }

    public LegalDisclaimer legalDisclaimer() {
        return this.legalDisclaimer;
    }

    public String toString() {
        return "BusinessTravelWelcomeData{marqueeTitle=" + this.marqueeTitle + ", marqueeBody=" + this.marqueeBody + ", buttonText=" + this.buttonText + ", marqueeImageUrl=" + this.marqueeImageUrl + ", legalDisclaimer=" + this.legalDisclaimer + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BusinessTravelWelcomeData)) {
            return false;
        }
        BusinessTravelWelcomeData that = (BusinessTravelWelcomeData) o;
        if (!this.marqueeTitle.equals(that.marqueeTitle()) || !this.marqueeBody.equals(that.marqueeBody()) || !this.buttonText.equals(that.buttonText()) || !this.marqueeImageUrl.equals(that.marqueeImageUrl()) || !this.legalDisclaimer.equals(that.legalDisclaimer())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((1 * 1000003) ^ this.marqueeTitle.hashCode()) * 1000003) ^ this.marqueeBody.hashCode()) * 1000003) ^ this.buttonText.hashCode()) * 1000003) ^ this.marqueeImageUrl.hashCode()) * 1000003) ^ this.legalDisclaimer.hashCode();
    }
}
