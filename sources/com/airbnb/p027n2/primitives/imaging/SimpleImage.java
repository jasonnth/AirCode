package com.airbnb.p027n2.primitives.imaging;

/* renamed from: com.airbnb.n2.primitives.imaging.SimpleImage */
public class SimpleImage implements Image {
    private final String base64Preview;

    /* renamed from: id */
    private long f2707id;
    private final String url;

    public SimpleImage(String url2) {
        this(url2, null);
    }

    public SimpleImage(String url2, String base64Preview2) {
        this.f2707id = -1;
        this.url = url2;
        this.base64Preview = base64Preview2;
    }

    public String getUrlForSize(ImageSize size) {
        return this.url;
    }

    public String getBase64Preview() {
        return this.base64Preview;
    }

    public long getId() {
        if (this.f2707id == -1) {
            this.f2707id = (long) this.url.hashCode();
        }
        return this.f2707id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleImage that = (SimpleImage) o;
        if (getId() != that.getId()) {
            return false;
        }
        if (this.base64Preview != null) {
            return this.base64Preview.equals(that.base64Preview);
        }
        if (that.base64Preview != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((int) getId()) * 31) + (this.base64Preview != null ? this.base64Preview.hashCode() : 0);
    }
}
