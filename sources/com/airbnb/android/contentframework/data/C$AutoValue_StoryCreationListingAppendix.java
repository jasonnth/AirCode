package com.airbnb.android.contentframework.data;

/* renamed from: com.airbnb.android.contentframework.data.$AutoValue_StoryCreationListingAppendix reason: invalid class name */
abstract class C$AutoValue_StoryCreationListingAppendix extends StoryCreationListingAppendix {
    private final long listingId;
    private final float rating;
    private final String subtitle;
    private final String thumbnailUrl;
    private final String title;

    C$AutoValue_StoryCreationListingAppendix(String thumbnailUrl2, String title2, String subtitle2, float rating2, long listingId2) {
        if (thumbnailUrl2 == null) {
            throw new NullPointerException("Null thumbnailUrl");
        }
        this.thumbnailUrl = thumbnailUrl2;
        if (title2 == null) {
            throw new NullPointerException("Null title");
        }
        this.title = title2;
        if (subtitle2 == null) {
            throw new NullPointerException("Null subtitle");
        }
        this.subtitle = subtitle2;
        this.rating = rating2;
        this.listingId = listingId2;
    }

    public String thumbnailUrl() {
        return this.thumbnailUrl;
    }

    public String title() {
        return this.title;
    }

    public String subtitle() {
        return this.subtitle;
    }

    public float rating() {
        return this.rating;
    }

    public long listingId() {
        return this.listingId;
    }

    public String toString() {
        return "StoryCreationListingAppendix{thumbnailUrl=" + this.thumbnailUrl + ", title=" + this.title + ", subtitle=" + this.subtitle + ", rating=" + this.rating + ", listingId=" + this.listingId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof StoryCreationListingAppendix)) {
            return false;
        }
        StoryCreationListingAppendix that = (StoryCreationListingAppendix) o;
        if (!this.thumbnailUrl.equals(that.thumbnailUrl()) || !this.title.equals(that.title()) || !this.subtitle.equals(that.subtitle()) || Float.floatToIntBits(this.rating) != Float.floatToIntBits(that.rating()) || this.listingId != that.listingId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (((long) (((((((((1 * 1000003) ^ this.thumbnailUrl.hashCode()) * 1000003) ^ this.title.hashCode()) * 1000003) ^ this.subtitle.hashCode()) * 1000003) ^ Float.floatToIntBits(this.rating)) * 1000003)) ^ ((this.listingId >>> 32) ^ this.listingId));
    }
}
