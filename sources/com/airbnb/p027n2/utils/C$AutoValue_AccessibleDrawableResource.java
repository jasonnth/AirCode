package com.airbnb.p027n2.utils;

/* renamed from: com.airbnb.n2.utils.$AutoValue_AccessibleDrawableResource reason: invalid class name */
abstract class C$AutoValue_AccessibleDrawableResource extends AccessibleDrawableResource {
    private final String contentDescription;
    private final int drawableResource;

    C$AutoValue_AccessibleDrawableResource(int drawableResource2, String contentDescription2) {
        this.drawableResource = drawableResource2;
        if (contentDescription2 == null) {
            throw new NullPointerException("Null contentDescription");
        }
        this.contentDescription = contentDescription2;
    }

    public int drawableResource() {
        return this.drawableResource;
    }

    public String contentDescription() {
        return this.contentDescription;
    }

    public String toString() {
        return "AccessibleDrawableResource{drawableResource=" + this.drawableResource + ", contentDescription=" + this.contentDescription + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccessibleDrawableResource)) {
            return false;
        }
        AccessibleDrawableResource that = (AccessibleDrawableResource) o;
        if (this.drawableResource != that.drawableResource() || !this.contentDescription.equals(that.contentDescription())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.drawableResource) * 1000003) ^ this.contentDescription.hashCode();
    }
}
