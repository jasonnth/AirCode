package com.jumio.mrz.impl.smartEngines.swig;

public class MrzEngineSessionHelpers {

    /* renamed from: a */
    private long f3214a;
    protected boolean swigCMemOwn;

    protected MrzEngineSessionHelpers(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3214a = j;
    }

    protected static long getCPtr(MrzEngineSessionHelpers mrzEngineSessionHelpers) {
        if (mrzEngineSessionHelpers == null) {
            return 0;
        }
        return mrzEngineSessionHelpers.f3214a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3214a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzEngineSessionHelpers(this.f3214a);
            }
            this.f3214a = 0;
        }
    }

    public double get_optimal_aspect_ratio() {
        return mrzjniInterfaceJNI.MrzEngineSessionHelpers_get_optimal_aspect_ratio(this.f3214a, this);
    }

    public void set_optimal_aspect_ratio(double d) {
        mrzjniInterfaceJNI.MrzEngineSessionHelpers_set_optimal_aspect_ratio(this.f3214a, this, d);
    }

    public MrzEngineSessionHelpers() {
        this(mrzjniInterfaceJNI.new_MrzEngineSessionHelpers(), true);
    }
}
