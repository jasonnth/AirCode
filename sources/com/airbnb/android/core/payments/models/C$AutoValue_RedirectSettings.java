package com.airbnb.android.core.payments.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_RedirectSettings reason: invalid class name */
abstract class C$AutoValue_RedirectSettings extends RedirectSettings {
    private final String url;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_RedirectSettings$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.RedirectSettings.Builder {
        private String url;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.RedirectSettings.Builder url(String url2) {
            this.url = url2;
            return this;
        }

        public RedirectSettings build() {
            return new AutoValue_RedirectSettings(this.url);
        }
    }

    C$AutoValue_RedirectSettings(String url2) {
        this.url = url2;
    }

    @JsonProperty("url")
    public String url() {
        return this.url;
    }

    public String toString() {
        return "RedirectSettings{url=" + this.url + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RedirectSettings)) {
            return false;
        }
        RedirectSettings that = (RedirectSettings) o;
        if (this.url != null) {
            return this.url.equals(that.url());
        }
        if (that.url() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.url == null ? 0 : this.url.hashCode());
    }
}
