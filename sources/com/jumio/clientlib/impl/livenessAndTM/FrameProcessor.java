package com.jumio.clientlib.impl.livenessAndTM;

public class FrameProcessor {

    /* renamed from: a */
    private long f3166a;

    /* renamed from: b */
    private boolean f3167b;

    protected FrameProcessor(long j, boolean z) {
        this.f3167b = z;
        this.f3166a = j;
    }

    protected static long getCPtr(FrameProcessor frameProcessor) {
        if (frameProcessor == null) {
            return 0;
        }
        return frameProcessor.f3166a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3166a != 0) {
            if (this.f3167b) {
                this.f3167b = false;
                jniLivenessAndTMJNI.delete_FrameProcessor(this.f3166a);
            }
            this.f3166a = 0;
        }
    }

    public void pushFrame(LibImage libImage) throws Exception {
        jniLivenessAndTMJNI.FrameProcessor_pushFrame(this.f3166a, this, LibImage.getCPtr(libImage), libImage);
    }

    public void reset() {
        jniLivenessAndTMJNI.FrameProcessor_reset(this.f3166a, this);
    }
}
