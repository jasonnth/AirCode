package com.jumio.clientlib.impl.livenessAndTM;

public class TemplatePolygon {

    /* renamed from: a */
    private long f3196a;
    protected boolean swigCMemOwn;

    protected TemplatePolygon(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3196a = j;
    }

    protected static long getCPtr(TemplatePolygon templatePolygon) {
        if (templatePolygon == null) {
            return 0;
        }
        return templatePolygon.f3196a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3196a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_TemplatePolygon(this.f3196a);
            }
            this.f3196a = 0;
        }
    }

    public TemplatePolygon() {
        this(jniLivenessAndTMJNI.new_TemplatePolygon__SWIG_0(), true);
    }

    public TemplatePolygon(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this(jniLivenessAndTMJNI.new_TemplatePolygon__SWIG_1(f, f2, f3, f4, f5, f6, f7, f8), true);
    }

    public float getTLx() {
        return jniLivenessAndTMJNI.TemplatePolygon_getTLx(this.f3196a, this);
    }

    public float getTLy() {
        return jniLivenessAndTMJNI.TemplatePolygon_getTLy(this.f3196a, this);
    }

    public float getTRx() {
        return jniLivenessAndTMJNI.TemplatePolygon_getTRx(this.f3196a, this);
    }

    public float getTRy() {
        return jniLivenessAndTMJNI.TemplatePolygon_getTRy(this.f3196a, this);
    }

    public float getBRx() {
        return jniLivenessAndTMJNI.TemplatePolygon_getBRx(this.f3196a, this);
    }

    public float getBRy() {
        return jniLivenessAndTMJNI.TemplatePolygon_getBRy(this.f3196a, this);
    }

    public float getBLx() {
        return jniLivenessAndTMJNI.TemplatePolygon_getBLx(this.f3196a, this);
    }

    public float getBLy() {
        return jniLivenessAndTMJNI.TemplatePolygon_getBLy(this.f3196a, this);
    }

    public void setTLx(float f) {
        jniLivenessAndTMJNI.TemplatePolygon_setTLx(this.f3196a, this, f);
    }

    public void setTLy(float f) {
        jniLivenessAndTMJNI.TemplatePolygon_setTLy(this.f3196a, this, f);
    }

    public void setTRx(float f) {
        jniLivenessAndTMJNI.TemplatePolygon_setTRx(this.f3196a, this, f);
    }

    public void setTRy(float f) {
        jniLivenessAndTMJNI.TemplatePolygon_setTRy(this.f3196a, this, f);
    }

    public void setBRx(float f) {
        jniLivenessAndTMJNI.TemplatePolygon_setBRx(this.f3196a, this, f);
    }

    public void setBRy(float f) {
        jniLivenessAndTMJNI.TemplatePolygon_setBRy(this.f3196a, this, f);
    }

    public void setBLx(float f) {
        jniLivenessAndTMJNI.TemplatePolygon_setBLx(this.f3196a, this, f);
    }

    public void setBLy(float f) {
        jniLivenessAndTMJNI.TemplatePolygon_setBLy(this.f3196a, this, f);
    }
}
