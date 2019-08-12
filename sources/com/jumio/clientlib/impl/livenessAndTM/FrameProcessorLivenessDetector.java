package com.jumio.clientlib.impl.livenessAndTM;

public class FrameProcessorLivenessDetector extends FrameProcessor {

    /* renamed from: a */
    private long f3168a;

    /* renamed from: b */
    private boolean f3169b;

    protected FrameProcessorLivenessDetector(long j, boolean z) {
        super(jniLivenessAndTMJNI.FrameProcessorLivenessDetector_SWIGSmartPtrUpcast(j), true);
        this.f3169b = z;
        this.f3168a = j;
    }

    protected static long getCPtr(FrameProcessorLivenessDetector frameProcessorLivenessDetector) {
        if (frameProcessorLivenessDetector == null) {
            return 0;
        }
        return frameProcessorLivenessDetector.f3168a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3168a != 0) {
            if (this.f3169b) {
                this.f3169b = false;
                jniLivenessAndTMJNI.delete_FrameProcessorLivenessDetector(this.f3168a);
            }
            this.f3168a = 0;
        }
        super.delete();
    }

    public FrameProcessorLivenessDetector(LivenessDetectorCallback livenessDetectorCallback, String str) throws Exception {
        this(jniLivenessAndTMJNI.new_FrameProcessorLivenessDetector(LivenessDetectorCallback.getCPtr(livenessDetectorCallback), livenessDetectorCallback, str), true);
    }

    public void pushFrame(LibImage libImage, ImageOrientation imageOrientation) throws Exception {
        jniLivenessAndTMJNI.FrameProcessorLivenessDetector_pushFrame(this.f3168a, this, LibImage.getCPtr(libImage), libImage, imageOrientation.swigValue());
    }
}
