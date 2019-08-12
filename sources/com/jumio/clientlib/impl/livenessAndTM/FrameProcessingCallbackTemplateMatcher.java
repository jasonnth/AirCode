package com.jumio.clientlib.impl.livenessAndTM;

public class FrameProcessingCallbackTemplateMatcher {

    /* renamed from: a */
    private long f3164a;
    protected boolean swigCMemOwn;

    protected FrameProcessingCallbackTemplateMatcher(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3164a = j;
    }

    protected static long getCPtr(FrameProcessingCallbackTemplateMatcher frameProcessingCallbackTemplateMatcher) {
        if (frameProcessingCallbackTemplateMatcher == null) {
            return 0;
        }
        return frameProcessingCallbackTemplateMatcher.f3164a;
    }

    public synchronized void delete() {
        if (this.f3164a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
            this.f3164a = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        jniLivenessAndTMJNI.FrameProcessingCallbackTemplateMatcher_change_ownership(this, this.f3164a, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        jniLivenessAndTMJNI.FrameProcessingCallbackTemplateMatcher_change_ownership(this, this.f3164a, true);
    }

    public void intermediateResult(FrameQueue frameQueue, TemplateInfo templateInfo) {
        jniLivenessAndTMJNI.FrameProcessingCallbackTemplateMatcher_intermediateResult(this.f3164a, this, FrameQueue.getCPtr(frameQueue), frameQueue, TemplateInfo.getCPtr(templateInfo), templateInfo);
    }

    public void finalResult(FrameQueue frameQueue, TemplateInfo templateInfo) {
        jniLivenessAndTMJNI.FrameProcessingCallbackTemplateMatcher_finalResult(this.f3164a, this, FrameQueue.getCPtr(frameQueue), frameQueue, TemplateInfo.getCPtr(templateInfo), templateInfo);
    }

    public void noResult(FrameQueue frameQueue) {
        if (getClass() == FrameProcessingCallbackTemplateMatcher.class) {
            jniLivenessAndTMJNI.FrameProcessingCallbackTemplateMatcher_noResult(this.f3164a, this, FrameQueue.getCPtr(frameQueue), frameQueue);
            return;
        }
        jniLivenessAndTMJNI.m1903xe1fc6ff3(this.f3164a, this, FrameQueue.getCPtr(frameQueue), frameQueue);
    }

    public FrameProcessingCallbackTemplateMatcher() {
        this(jniLivenessAndTMJNI.new_FrameProcessingCallbackTemplateMatcher(), true);
        jniLivenessAndTMJNI.FrameProcessingCallbackTemplateMatcher_director_connect(this, this.f3164a, this.swigCMemOwn, true);
    }
}
