package com.jumio.core;

public class RawImageFrame {
    public static final int RGB = 0;
    public static final int YUV = 1;
    byte[] data;
    int format = 0;
    int height;
    int width;

    public RawImageFrame(byte[] data2, int width2, int height2) {
        this.data = data2;
        this.width = width2;
        this.height = height2;
    }
}
