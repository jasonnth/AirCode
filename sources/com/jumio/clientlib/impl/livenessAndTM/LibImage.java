package com.jumio.clientlib.impl.livenessAndTM;

public class LibImage {

    /* renamed from: a */
    private long f3184a;

    /* renamed from: b */
    private boolean f3185b;

    protected LibImage(long j, boolean z) {
        this.f3185b = z;
        this.f3184a = j;
    }

    protected static long getCPtr(LibImage libImage) {
        if (libImage == null) {
            return 0;
        }
        return libImage.f3184a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3184a != 0) {
            if (this.f3185b) {
                this.f3185b = false;
                jniLivenessAndTMJNI.delete_LibImage(this.f3184a);
            }
            this.f3184a = 0;
        }
    }

    public LibImage(byte[] bArr, PixelFormatType pixelFormatType, int i, int i2, long j, int i3, int i4, int i5, int i6, boolean z) {
        this(jniLivenessAndTMJNI.new_LibImage__SWIG_0(bArr, pixelFormatType.swigValue(), i, i2, j, i3, i4, i5, i6, z), true);
    }

    public LibImage(byte[] bArr, PixelFormatType pixelFormatType, int i, int i2, long j, int i3, int i4, int i5, int i6) {
        this(jniLivenessAndTMJNI.new_LibImage__SWIG_1(bArr, pixelFormatType.swigValue(), i, i2, j, i3, i4, i5, i6), true);
    }

    public SWIGTYPE_p_unsigned_char getFrameBuffer() {
        long LibImage_getFrameBuffer__SWIG_0 = jniLivenessAndTMJNI.LibImage_getFrameBuffer__SWIG_0(this.f3184a, this);
        if (LibImage_getFrameBuffer__SWIG_0 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(LibImage_getFrameBuffer__SWIG_0, false);
    }

    public void getFrameBuffer(byte[] bArr) {
        jniLivenessAndTMJNI.LibImage_getFrameBuffer__SWIG_1(this.f3184a, this, bArr);
    }

    public PixelFormatType getFormat() {
        return PixelFormatType.swigToEnum(jniLivenessAndTMJNI.LibImage_getFormat(this.f3184a, this));
    }

    public int getWidth() {
        return jniLivenessAndTMJNI.LibImage_getWidth(this.f3184a, this);
    }

    public int getHeight() {
        return jniLivenessAndTMJNI.LibImage_getHeight(this.f3184a, this);
    }

    public long getStride() {
        return jniLivenessAndTMJNI.LibImage_getStride(this.f3184a, this);
    }

    public int getChannels() {
        return jniLivenessAndTMJNI.LibImage_getChannels(this.f3184a, this);
    }

    public int getChannelDepth() {
        return jniLivenessAndTMJNI.LibImage_getChannelDepth(this.f3184a, this);
    }

    public int getFrameIndex() {
        return jniLivenessAndTMJNI.LibImage_getFrameIndex(this.f3184a, this);
    }

    public int getTimeStamp() {
        return jniLivenessAndTMJNI.LibImage_getTimeStamp(this.f3184a, this);
    }
}
