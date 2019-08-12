package com.airbnb.android.core.contentproviders;

/* renamed from: com.airbnb.android.core.contentproviders.$AutoValue_ViewedListing reason: invalid class name */
abstract class C$AutoValue_ViewedListing extends ViewedListing {
    private final long listingId;
    private final long timestamp;

    C$AutoValue_ViewedListing(long listingId2, long timestamp2) {
        this.listingId = listingId2;
        this.timestamp = timestamp2;
    }

    public long getListingId() {
        return this.listingId;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String toString() {
        return "ViewedListing{listingId=" + this.listingId + ", timestamp=" + this.timestamp + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewedListing)) {
            return false;
        }
        ViewedListing that = (ViewedListing) o;
        if (this.listingId == that.getListingId() && this.timestamp == that.getTimestamp()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (((long) (((int) (((long) (1 * 1000003)) ^ ((this.listingId >>> 32) ^ this.listingId))) * 1000003)) ^ ((this.timestamp >>> 32) ^ this.timestamp));
    }
}
