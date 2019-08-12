package com.jumio.clientlib.impl.livenessAndTM;

public class FrameQueue {

    /* renamed from: a */
    private long f3174a;
    protected boolean swigCMemOwn;

    protected FrameQueue(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3174a = j;
    }

    protected static long getCPtr(FrameQueue frameQueue) {
        if (frameQueue == null) {
            return 0;
        }
        return frameQueue.f3174a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3174a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_FrameQueue(this.f3174a);
            }
            this.f3174a = 0;
        }
    }

    public FrameQueue(long j) {
        this(jniLivenessAndTMJNI.new_FrameQueue(j), true);
    }

    public void pushFrame(LibImage libImage) {
        jniLivenessAndTMJNI.FrameQueue_pushFrame(this.f3174a, this, LibImage.getCPtr(libImage), libImage);
    }

    public LibImage lastFrame() {
        long FrameQueue_lastFrame = jniLivenessAndTMJNI.FrameQueue_lastFrame(this.f3174a, this);
        if (FrameQueue_lastFrame == 0) {
            return null;
        }
        return new LibImage(FrameQueue_lastFrame, true);
    }

    public LibImage getFrameByID(int i) {
        long FrameQueue_getFrameByID = jniLivenessAndTMJNI.FrameQueue_getFrameByID(this.f3174a, this, i);
        if (FrameQueue_getFrameByID == 0) {
            return null;
        }
        return new LibImage(FrameQueue_getFrameByID, true);
    }

    public boolean removeFrameByID(int i) {
        return jniLivenessAndTMJNI.FrameQueue_removeFrameByID(this.f3174a, this, i);
    }

    public void getFramesInRange(int i, int i2, FramesVector framesVector) {
        jniLivenessAndTMJNI.FrameQueue_getFramesInRange(this.f3174a, this, i, i2, FramesVector.getCPtr(framesVector), framesVector);
    }

    public void getAllFrames(FramesVector framesVector) {
        jniLivenessAndTMJNI.FrameQueue_getAllFrames(this.f3174a, this, FramesVector.getCPtr(framesVector), framesVector);
    }

    public long size() {
        return jniLivenessAndTMJNI.FrameQueue_size(this.f3174a, this);
    }

    public void clear() {
        jniLivenessAndTMJNI.FrameQueue_clear(this.f3174a, this);
    }
}
