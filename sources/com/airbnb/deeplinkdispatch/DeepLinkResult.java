package com.airbnb.deeplinkdispatch;

public final class DeepLinkResult {
    private final String error;
    private final boolean successful;
    private final String uriString;

    public DeepLinkResult(boolean successful2, String uriString2, String error2) {
        this.successful = successful2;
        this.uriString = uriString2;
        this.error = error2;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeepLinkResult that = (DeepLinkResult) o;
        if (this.successful != that.successful) {
            return false;
        }
        if (this.uriString != null) {
            if (!this.uriString.equals(that.uriString)) {
                return false;
            }
        } else if (that.uriString != null) {
            return false;
        }
        if (this.error != null) {
            z = this.error.equals(that.error);
        } else if (that.error != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i;
        int i2 = 0;
        if (this.successful) {
            result = 1;
        } else {
            result = 0;
        }
        int i3 = result * 31;
        if (this.uriString != null) {
            i = this.uriString.hashCode();
        } else {
            i = 0;
        }
        int i4 = (i3 + i) * 31;
        if (this.error != null) {
            i2 = this.error.hashCode();
        }
        return i4 + i2;
    }

    public String toString() {
        return "DeepLinkResult{successful=" + this.successful + ", uriString=" + this.uriString + ", error='" + this.error + '\'' + '}';
    }
}
