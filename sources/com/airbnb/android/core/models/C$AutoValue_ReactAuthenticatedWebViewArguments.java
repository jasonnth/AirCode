package com.airbnb.android.core.models;

/* renamed from: com.airbnb.android.core.models.$AutoValue_ReactAuthenticatedWebViewArguments reason: invalid class name */
abstract class C$AutoValue_ReactAuthenticatedWebViewArguments extends ReactAuthenticatedWebViewArguments {
    private final String url;

    /* renamed from: com.airbnb.android.core.models.$AutoValue_ReactAuthenticatedWebViewArguments$Builder */
    static final class Builder extends com.airbnb.android.core.models.ReactAuthenticatedWebViewArguments.Builder {
        private String url;

        Builder() {
        }

        public com.airbnb.android.core.models.ReactAuthenticatedWebViewArguments.Builder url(String url2) {
            if (url2 == null) {
                throw new NullPointerException("Null url");
            }
            this.url = url2;
            return this;
        }

        public ReactAuthenticatedWebViewArguments build() {
            String missing = "";
            if (this.url == null) {
                missing = missing + " url";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ReactAuthenticatedWebViewArguments(this.url);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ReactAuthenticatedWebViewArguments(String url2) {
        if (url2 == null) {
            throw new NullPointerException("Null url");
        }
        this.url = url2;
    }

    public String getUrl() {
        return this.url;
    }

    public String toString() {
        return "ReactAuthenticatedWebViewArguments{url=" + this.url + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReactAuthenticatedWebViewArguments)) {
            return false;
        }
        return this.url.equals(((ReactAuthenticatedWebViewArguments) o).getUrl());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.url.hashCode();
    }
}
