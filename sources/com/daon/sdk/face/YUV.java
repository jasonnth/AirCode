package com.daon.sdk.face;

public class YUV {

    /* renamed from: a */
    private byte[] f3033a = null;

    /* renamed from: b */
    private int f3034b = 0;

    /* renamed from: c */
    private int f3035c = 0;

    public YUV(byte[] yuv, int width, int height) {
        this.f3033a = yuv;
        this.f3034b = width;
        this.f3035c = height;
    }

    public int getWidth() {
        return this.f3034b;
    }

    public int getHeight() {
        return this.f3035c;
    }

    public byte[] getData() {
        return this.f3033a;
    }
}
