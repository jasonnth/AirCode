package com.miteksystems.misnap.events;

public class FlattenAndCropEvent {
    public final int[][] fourCorners;
    public final int height;
    public final byte[] image;
    public final int width;

    public FlattenAndCropEvent(byte[] image2, int width2, int height2, int[][] fourCorners2) {
        this.image = image2;
        this.width = width2;
        this.height = height2;
        this.fourCorners = fourCorners2;
    }
}
