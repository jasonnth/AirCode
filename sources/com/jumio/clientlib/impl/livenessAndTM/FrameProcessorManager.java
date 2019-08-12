package com.jumio.clientlib.impl.livenessAndTM;

public class FrameProcessorManager {

    /* renamed from: a */
    private long f3170a;

    /* renamed from: b */
    private boolean f3171b;

    protected FrameProcessorManager(long j, boolean z) {
        this.f3171b = z;
        this.f3170a = j;
    }

    protected static long getCPtr(FrameProcessorManager frameProcessorManager) {
        if (frameProcessorManager == null) {
            return 0;
        }
        return frameProcessorManager.f3170a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3170a != 0) {
            if (this.f3171b) {
                this.f3171b = false;
                jniLivenessAndTMJNI.delete_FrameProcessorManager(this.f3170a);
            }
            this.f3170a = 0;
        }
    }

    public FrameProcessorManager(SWIGTYPE_p_FrameProcessorPtr sWIGTYPE_p_FrameProcessorPtr) {
        this(jniLivenessAndTMJNI.new_FrameProcessorManager__SWIG_0(SWIGTYPE_p_FrameProcessorPtr.getCPtr(sWIGTYPE_p_FrameProcessorPtr)), true);
    }

    public FrameProcessorManager(SWIGTYPE_p_FrameProcessorPtr sWIGTYPE_p_FrameProcessorPtr, SWIGTYPE_p_FrameProcessorPtr sWIGTYPE_p_FrameProcessorPtr2) {
        this(jniLivenessAndTMJNI.new_FrameProcessorManager__SWIG_1(SWIGTYPE_p_FrameProcessorPtr.getCPtr(sWIGTYPE_p_FrameProcessorPtr), SWIGTYPE_p_FrameProcessorPtr.getCPtr(sWIGTYPE_p_FrameProcessorPtr2)), true);
    }

    public FrameProcessorManager(SWIGTYPE_p_FrameProcessorPtr sWIGTYPE_p_FrameProcessorPtr, SWIGTYPE_p_FrameProcessorPtr sWIGTYPE_p_FrameProcessorPtr2, SWIGTYPE_p_FrameProcessorPtr sWIGTYPE_p_FrameProcessorPtr3) {
        this(jniLivenessAndTMJNI.new_FrameProcessorManager__SWIG_2(SWIGTYPE_p_FrameProcessorPtr.getCPtr(sWIGTYPE_p_FrameProcessorPtr), SWIGTYPE_p_FrameProcessorPtr.getCPtr(sWIGTYPE_p_FrameProcessorPtr2), SWIGTYPE_p_FrameProcessorPtr.getCPtr(sWIGTYPE_p_FrameProcessorPtr3)), true);
    }

    public void pushFrame(byte[] bArr, long j, int i, int i2, int i3, int i4, int i5, PixelFormatType pixelFormatType, int i6) {
        jniLivenessAndTMJNI.FrameProcessorManager_pushFrame(this.f3170a, this, bArr, j, i, i2, i3, i4, i5, pixelFormatType.swigValue(), i6);
    }

    public void reset() {
        jniLivenessAndTMJNI.FrameProcessorManager_reset(this.f3170a, this);
    }
}
