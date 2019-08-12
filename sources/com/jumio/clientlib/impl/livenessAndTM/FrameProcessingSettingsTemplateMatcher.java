package com.jumio.clientlib.impl.livenessAndTM;

public class FrameProcessingSettingsTemplateMatcher {

    /* renamed from: a */
    private long f3165a;
    protected boolean swigCMemOwn;

    protected FrameProcessingSettingsTemplateMatcher(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3165a = j;
    }

    protected static long getCPtr(FrameProcessingSettingsTemplateMatcher frameProcessingSettingsTemplateMatcher) {
        if (frameProcessingSettingsTemplateMatcher == null) {
            return 0;
        }
        return frameProcessingSettingsTemplateMatcher.f3165a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3165a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_FrameProcessingSettingsTemplateMatcher(this.f3165a);
            }
            this.f3165a = 0;
        }
    }

    public FrameProcessingSettingsTemplateMatcher(int i, boolean z, HierarchicalClusteringSettings hierarchicalClusteringSettings, int i2, int i3) {
        this(jniLivenessAndTMJNI.new_FrameProcessingSettingsTemplateMatcher__SWIG_0(i, z, HierarchicalClusteringSettings.getCPtr(hierarchicalClusteringSettings), hierarchicalClusteringSettings, i2, i3), true);
    }

    public FrameProcessingSettingsTemplateMatcher(int i, boolean z, HierarchicalClusteringSettings hierarchicalClusteringSettings, int i2) {
        this(jniLivenessAndTMJNI.new_FrameProcessingSettingsTemplateMatcher__SWIG_1(i, z, HierarchicalClusteringSettings.getCPtr(hierarchicalClusteringSettings), hierarchicalClusteringSettings, i2), true);
    }

    public FrameProcessingSettingsTemplateMatcher(int i, boolean z, HierarchicalClusteringSettings hierarchicalClusteringSettings) {
        this(jniLivenessAndTMJNI.new_FrameProcessingSettingsTemplateMatcher__SWIG_2(i, z, HierarchicalClusteringSettings.getCPtr(hierarchicalClusteringSettings), hierarchicalClusteringSettings), true);
    }

    public FrameProcessingSettingsTemplateMatcher(int i, boolean z) {
        this(jniLivenessAndTMJNI.new_FrameProcessingSettingsTemplateMatcher__SWIG_3(i, z), true);
    }

    public FrameProcessingSettingsTemplateMatcher(int i) {
        this(jniLivenessAndTMJNI.new_FrameProcessingSettingsTemplateMatcher__SWIG_4(i), true);
    }

    public FrameProcessingSettingsTemplateMatcher() {
        this(jniLivenessAndTMJNI.new_FrameProcessingSettingsTemplateMatcher__SWIG_5(), true);
    }

    public int getMaxKeyPointsToDetect() {
        return jniLivenessAndTMJNI.FrameProcessingSettingsTemplateMatcher_getMaxKeyPointsToDetect(this.f3165a, this);
    }

    public boolean getUseClustring() {
        return jniLivenessAndTMJNI.FrameProcessingSettingsTemplateMatcher_getUseClustring(this.f3165a, this);
    }

    public HierarchicalClusteringSettings getClusteringSettings() {
        return new HierarchicalClusteringSettings(jniLivenessAndTMJNI.FrameProcessingSettingsTemplateMatcher_getClusteringSettings(this.f3165a, this), false);
    }

    public int getNumSuccessMatchesToComplete() {
        return jniLivenessAndTMJNI.m1904x9603263e(this.f3165a, this);
    }

    public int getNumImagesToKeep() {
        return jniLivenessAndTMJNI.FrameProcessingSettingsTemplateMatcher_getNumImagesToKeep(this.f3165a, this);
    }
}
