package com.jumio.clientlib.impl.livenessAndTM;

public class HierarchicalClusteringSettings {

    /* renamed from: a */
    private long f3176a;
    protected boolean swigCMemOwn;

    protected HierarchicalClusteringSettings(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3176a = j;
    }

    protected static long getCPtr(HierarchicalClusteringSettings hierarchicalClusteringSettings) {
        if (hierarchicalClusteringSettings == null) {
            return 0;
        }
        return hierarchicalClusteringSettings.f3176a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3176a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_HierarchicalClusteringSettings(this.f3176a);
            }
            this.f3176a = 0;
        }
    }

    public HierarchicalClusteringSettings(int i, int i2, int i3, int i4) {
        this(jniLivenessAndTMJNI.new_HierarchicalClusteringSettings__SWIG_0(i, i2, i3, i4), true);
    }

    public HierarchicalClusteringSettings(int i, int i2, int i3) {
        this(jniLivenessAndTMJNI.new_HierarchicalClusteringSettings__SWIG_1(i, i2, i3), true);
    }

    public HierarchicalClusteringSettings(int i, int i2) {
        this(jniLivenessAndTMJNI.new_HierarchicalClusteringSettings__SWIG_2(i, i2), true);
    }

    public HierarchicalClusteringSettings(int i) {
        this(jniLivenessAndTMJNI.new_HierarchicalClusteringSettings__SWIG_3(i), true);
    }

    public HierarchicalClusteringSettings() {
        this(jniLivenessAndTMJNI.new_HierarchicalClusteringSettings__SWIG_4(), true);
    }

    public int getBranching() {
        return jniLivenessAndTMJNI.HierarchicalClusteringSettings_getBranching(this.f3176a, this);
    }

    public int getTrees() {
        return jniLivenessAndTMJNI.HierarchicalClusteringSettings_getTrees(this.f3176a, this);
    }

    public int getLeafMaxSize() {
        return jniLivenessAndTMJNI.HierarchicalClusteringSettings_getLeafMaxSize(this.f3176a, this);
    }

    public int getMaxChecks() {
        return jniLivenessAndTMJNI.HierarchicalClusteringSettings_getMaxChecks(this.f3176a, this);
    }
}
