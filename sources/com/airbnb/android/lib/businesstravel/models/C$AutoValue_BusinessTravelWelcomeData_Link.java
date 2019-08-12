package com.airbnb.android.lib.businesstravel.models;

import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Link;

/* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BusinessTravelWelcomeData_Link reason: invalid class name */
abstract class C$AutoValue_BusinessTravelWelcomeData_Link extends Link {
    private final String text;
    private final String url;

    /* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BusinessTravelWelcomeData_Link$Builder */
    static final class Builder extends com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Link.Builder {
        private String text;
        private String url;

        Builder() {
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Link.Builder text(String text2) {
            if (text2 == null) {
                throw new NullPointerException("Null text");
            }
            this.text = text2;
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Link.Builder url(String url2) {
            if (url2 == null) {
                throw new NullPointerException("Null url");
            }
            this.url = url2;
            return this;
        }

        public Link build() {
            String missing = "";
            if (this.text == null) {
                missing = missing + " text";
            }
            if (this.url == null) {
                missing = missing + " url";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BusinessTravelWelcomeData_Link(this.text, this.url);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BusinessTravelWelcomeData_Link(String text2, String url2) {
        if (text2 == null) {
            throw new NullPointerException("Null text");
        }
        this.text = text2;
        if (url2 == null) {
            throw new NullPointerException("Null url");
        }
        this.url = url2;
    }

    public String text() {
        return this.text;
    }

    public String url() {
        return this.url;
    }

    public String toString() {
        return "Link{text=" + this.text + ", url=" + this.url + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Link)) {
            return false;
        }
        Link that = (Link) o;
        if (!this.text.equals(that.text()) || !this.url.equals(that.url())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.text.hashCode()) * 1000003) ^ this.url.hashCode();
    }
}
