package com.jumio.clientlib.impl.livenessAndTM;

public class FrameProcessorTemplateMatcher extends FrameProcessor {

    /* renamed from: a */
    private long f3172a;

    /* renamed from: b */
    private boolean f3173b;

    protected FrameProcessorTemplateMatcher(long j, boolean z) {
        super(jniLivenessAndTMJNI.FrameProcessorTemplateMatcher_SWIGSmartPtrUpcast(j), true);
        this.f3173b = z;
        this.f3172a = j;
    }

    protected static long getCPtr(FrameProcessorTemplateMatcher frameProcessorTemplateMatcher) {
        if (frameProcessorTemplateMatcher == null) {
            return 0;
        }
        return frameProcessorTemplateMatcher.f3172a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3172a != 0) {
            if (this.f3173b) {
                this.f3173b = false;
                jniLivenessAndTMJNI.delete_FrameProcessorTemplateMatcher(this.f3172a);
            }
            this.f3172a = 0;
        }
        super.delete();
    }

    public FrameProcessorTemplateMatcher(FrameProcessingSettingsTemplateMatcher frameProcessingSettingsTemplateMatcher, FrameProcessingCallbackTemplateMatcher frameProcessingCallbackTemplateMatcher) {
        this(jniLivenessAndTMJNI.new_FrameProcessorTemplateMatcher(FrameProcessingSettingsTemplateMatcher.getCPtr(frameProcessingSettingsTemplateMatcher), frameProcessingSettingsTemplateMatcher, FrameProcessingCallbackTemplateMatcher.getCPtr(frameProcessingCallbackTemplateMatcher), frameProcessingCallbackTemplateMatcher), true);
    }

    public boolean addTemplatesFromBinaryFile(String str) {
        return jniLivenessAndTMJNI.FrameProcessorTemplateMatcher_addTemplatesFromBinaryFile(this.f3172a, this, str);
    }

    public void clearAllTemplates() {
        jniLivenessAndTMJNI.FrameProcessorTemplateMatcher_clearAllTemplates(this.f3172a, this);
    }

    public void discardFrameByID(int i) {
        jniLivenessAndTMJNI.FrameProcessorTemplateMatcher_discardFrameByID(this.f3172a, this, i);
    }

    public boolean detectSingleFrame(LibImage libImage, TemplateInfo templateInfo) {
        return jniLivenessAndTMJNI.FrameProcessorTemplateMatcher_detectSingleFrame(this.f3172a, this, LibImage.getCPtr(libImage), libImage, TemplateInfo.getCPtr(templateInfo), templateInfo);
    }
}
