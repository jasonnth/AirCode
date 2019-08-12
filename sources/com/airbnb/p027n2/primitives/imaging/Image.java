package com.airbnb.p027n2.primitives.imaging;

/* renamed from: com.airbnb.n2.primitives.imaging.Image */
public interface Image {
    boolean equals(Object obj);

    String getBase64Preview();

    long getId();

    String getUrlForSize(ImageSize imageSize);

    int hashCode();
}
