package com.jumio.mrz.impl.smartEngines.swig;

public class MrzEngineSessionSettings {

    /* renamed from: a */
    private long f3215a;
    protected boolean swigCMemOwn;

    protected MrzEngineSessionSettings(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3215a = j;
    }

    protected static long getCPtr(MrzEngineSessionSettings mrzEngineSessionSettings) {
        if (mrzEngineSessionSettings == null) {
            return 0;
        }
        return mrzEngineSessionSettings.f3215a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3215a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzEngineSessionSettings(this.f3215a);
            }
            this.f3215a = 0;
        }
    }

    public MrzEngineSessionSettings() {
        this(mrzjniInterfaceJNI.new_MrzEngineSessionSettings(), true);
    }

    public boolean get_should_postprocess() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_should_postprocess(this.f3215a, this);
    }

    public void set_should_postprocess(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_should_postprocess(this.f3215a, this, z);
    }

    public boolean get_should_reject_by_segmentation() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_should_reject_by_segmentation(this.f3215a, this);
    }

    public void set_should_reject_by_segmentation(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_should_reject_by_segmentation(this.f3215a, this, z);
    }

    public boolean get_should_reject_by_focus() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_should_reject_by_focus(this.f3215a, this);
    }

    public void set_should_reject_by_focus(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_should_reject_by_focus(this.f3215a, this, z);
    }

    public boolean get_should_reject_by_output() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_should_reject_by_output(this.f3215a, this);
    }

    public void set_should_reject_by_output(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_should_reject_by_output(this.f3215a, this, z);
    }

    public boolean get_should_segment_in_parallel() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_should_segment_in_parallel(this.f3215a, this);
    }

    public void set_should_segment_in_parallel(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_should_segment_in_parallel(this.f3215a, this, z);
    }

    public boolean get_should_recognize_in_parallel() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_should_recognize_in_parallel(this.f3215a, this);
    }

    public void set_should_recognize_in_parallel(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_should_recognize_in_parallel(this.f3215a, this, z);
    }

    public boolean get_should_segment_best_effort() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_should_segment_best_effort(this.f3215a, this);
    }

    public void set_should_segment_best_effort(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_should_segment_best_effort(this.f3215a, this, z);
    }

    public boolean get_mrp_support_enabled() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_mrp_support_enabled(this.f3215a, this);
    }

    public void set_mrp_support_enabled(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_mrp_support_enabled(this.f3215a, this, z);
    }

    public boolean get_td1_support_enabled() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_td1_support_enabled(this.f3215a, this);
    }

    public void set_td1_support_enabled(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_td1_support_enabled(this.f3215a, this, z);
    }

    public boolean get_td2_support_enabled() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_td2_support_enabled(this.f3215a, this);
    }

    public void set_td2_support_enabled(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_td2_support_enabled(this.f3215a, this, z);
    }

    public boolean get_mrva_support_enabled() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_mrva_support_enabled(this.f3215a, this);
    }

    public void set_mrva_support_enabled(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_mrva_support_enabled(this.f3215a, this, z);
    }

    public boolean get_mrvb_support_enabled() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_mrvb_support_enabled(this.f3215a, this);
    }

    public void set_mrvb_support_enabled(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_mrvb_support_enabled(this.f3215a, this, z);
    }

    public boolean get_mrvrus_support_enabled() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_mrvrus_support_enabled(this.f3215a, this);
    }

    public void set_mrvrus_support_enabled(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_mrvrus_support_enabled(this.f3215a, this, z);
    }

    public boolean get_cnis_support_enabled() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_cnis_support_enabled(this.f3215a, this);
    }

    public void set_cnis_support_enabled(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_cnis_support_enabled(this.f3215a, this, z);
    }

    public boolean get_m3z_support_enabled() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_m3z_support_enabled(this.f3215a, this);
    }

    public void set_m3z_support_enabled(boolean z) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_m3z_support_enabled(this.f3215a, this, z);
    }

    public int get_number_of_threads() {
        return mrzjniInterfaceJNI.MrzEngineSessionSettings_get_number_of_threads(this.f3215a, this);
    }

    public void set_number_of_threads(int i) {
        mrzjniInterfaceJNI.MrzEngineSessionSettings_set_number_of_threads(this.f3215a, this, i);
    }
}
