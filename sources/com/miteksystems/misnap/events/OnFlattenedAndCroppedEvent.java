package com.miteksystems.misnap.events;

public class OnFlattenedAndCroppedEvent {
    public final int height;
    public final byte[] image;
    public final int width;

    public OnFlattenedAndCroppedEvent(byte[] image2, int outWidth, int outHeight) {
        this.image = image2;
        this.width = outWidth;
        this.height = outHeight;
    }
}
