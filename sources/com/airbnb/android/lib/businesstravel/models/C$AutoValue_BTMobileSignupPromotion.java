package com.airbnb.android.lib.businesstravel.models;

/* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BTMobileSignupPromotion reason: invalid class name */
abstract class C$AutoValue_BTMobileSignupPromotion extends BTMobileSignupPromotion {
    private final String body;
    private final String boldText;
    private final String title;

    /* renamed from: com.airbnb.android.lib.businesstravel.models.$AutoValue_BTMobileSignupPromotion$Builder */
    static final class Builder extends com.airbnb.android.lib.businesstravel.models.BTMobileSignupPromotion.Builder {
        private String body;
        private String boldText;
        private String title;

        Builder() {
        }

        public com.airbnb.android.lib.businesstravel.models.BTMobileSignupPromotion.Builder title(String title2) {
            if (title2 == null) {
                throw new NullPointerException("Null title");
            }
            this.title = title2;
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BTMobileSignupPromotion.Builder body(String body2) {
            if (body2 == null) {
                throw new NullPointerException("Null body");
            }
            this.body = body2;
            return this;
        }

        public com.airbnb.android.lib.businesstravel.models.BTMobileSignupPromotion.Builder boldText(String boldText2) {
            if (boldText2 == null) {
                throw new NullPointerException("Null boldText");
            }
            this.boldText = boldText2;
            return this;
        }

        public BTMobileSignupPromotion build() {
            String missing = "";
            if (this.title == null) {
                missing = missing + " title";
            }
            if (this.body == null) {
                missing = missing + " body";
            }
            if (this.boldText == null) {
                missing = missing + " boldText";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BTMobileSignupPromotion(this.title, this.body, this.boldText);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BTMobileSignupPromotion(String title2, String body2, String boldText2) {
        if (title2 == null) {
            throw new NullPointerException("Null title");
        }
        this.title = title2;
        if (body2 == null) {
            throw new NullPointerException("Null body");
        }
        this.body = body2;
        if (boldText2 == null) {
            throw new NullPointerException("Null boldText");
        }
        this.boldText = boldText2;
    }

    public String title() {
        return this.title;
    }

    public String body() {
        return this.body;
    }

    public String boldText() {
        return this.boldText;
    }

    public String toString() {
        return "BTMobileSignupPromotion{title=" + this.title + ", body=" + this.body + ", boldText=" + this.boldText + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BTMobileSignupPromotion)) {
            return false;
        }
        BTMobileSignupPromotion that = (BTMobileSignupPromotion) o;
        if (!this.title.equals(that.title()) || !this.body.equals(that.body()) || !this.boldText.equals(that.boldText())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.title.hashCode()) * 1000003) ^ this.body.hashCode()) * 1000003) ^ this.boldText.hashCode();
    }
}
