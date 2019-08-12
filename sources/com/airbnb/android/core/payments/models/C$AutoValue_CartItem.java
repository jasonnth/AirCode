package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_CartItem reason: invalid class name */
abstract class C$AutoValue_CartItem extends CartItem {
    private final String description;
    private final QuickPayParameters quickPayParameters;
    private final String thumbnailUrl;
    private final String title;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_CartItem$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.CartItem.Builder {
        private String description;
        private QuickPayParameters quickPayParameters;
        private String thumbnailUrl;
        private String title;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.CartItem.Builder thumbnailUrl(String thumbnailUrl2) {
            this.thumbnailUrl = thumbnailUrl2;
            return this;
        }

        public com.airbnb.android.core.payments.models.CartItem.Builder title(String title2) {
            this.title = title2;
            return this;
        }

        public com.airbnb.android.core.payments.models.CartItem.Builder description(String description2) {
            this.description = description2;
            return this;
        }

        public com.airbnb.android.core.payments.models.CartItem.Builder quickPayParameters(QuickPayParameters quickPayParameters2) {
            this.quickPayParameters = quickPayParameters2;
            return this;
        }

        public CartItem build() {
            return new AutoValue_CartItem(this.thumbnailUrl, this.title, this.description, this.quickPayParameters);
        }
    }

    C$AutoValue_CartItem(String thumbnailUrl2, String title2, String description2, QuickPayParameters quickPayParameters2) {
        this.thumbnailUrl = thumbnailUrl2;
        this.title = title2;
        this.description = description2;
        this.quickPayParameters = quickPayParameters2;
    }

    public String thumbnailUrl() {
        return this.thumbnailUrl;
    }

    public String title() {
        return this.title;
    }

    public String description() {
        return this.description;
    }

    public QuickPayParameters quickPayParameters() {
        return this.quickPayParameters;
    }

    public String toString() {
        return "CartItem{thumbnailUrl=" + this.thumbnailUrl + ", title=" + this.title + ", description=" + this.description + ", quickPayParameters=" + this.quickPayParameters + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CartItem)) {
            return false;
        }
        CartItem that = (CartItem) o;
        if (this.thumbnailUrl != null ? this.thumbnailUrl.equals(that.thumbnailUrl()) : that.thumbnailUrl() == null) {
            if (this.title != null ? this.title.equals(that.title()) : that.title() == null) {
                if (this.description != null ? this.description.equals(that.description()) : that.description() == null) {
                    if (this.quickPayParameters == null) {
                        if (that.quickPayParameters() == null) {
                            return true;
                        }
                    } else if (this.quickPayParameters.equals(that.quickPayParameters())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((1 * 1000003) ^ (this.thumbnailUrl == null ? 0 : this.thumbnailUrl.hashCode())) * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003;
        if (this.quickPayParameters != null) {
            i = this.quickPayParameters.hashCode();
        }
        return h ^ i;
    }
}
