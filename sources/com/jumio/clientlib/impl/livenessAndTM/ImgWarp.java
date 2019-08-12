package com.jumio.clientlib.impl.livenessAndTM;

public class ImgWarp {

    /* renamed from: a */
    private long f3181a;
    protected boolean swigCMemOwn;

    protected ImgWarp(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3181a = j;
    }

    protected static long getCPtr(ImgWarp imgWarp) {
        if (imgWarp == null) {
            return 0;
        }
        return imgWarp.f3181a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3181a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_ImgWarp(this.f3181a);
            }
            this.f3181a = 0;
        }
    }

    public static void warp(byte[] bArr, long j, int i, int i2, PixelFormatType pixelFormatType, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, byte[] bArr2, int i3, int i4) {
        jniLivenessAndTMJNI.ImgWarp_warp(bArr, j, i, i2, pixelFormatType.swigValue(), f, f2, f3, f4, f5, f6, f7, f8, bArr2, i3, i4);
    }

    public ImgWarp() {
        this(jniLivenessAndTMJNI.new_ImgWarp(), true);
    }
}
