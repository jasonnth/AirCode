package com.jumio.clientlib.impl.livenessAndTM;

public class TemplateInfo {

    /* renamed from: a */
    private long f3195a;
    protected boolean swigCMemOwn;

    protected TemplateInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3195a = j;
    }

    protected static long getCPtr(TemplateInfo templateInfo) {
        if (templateInfo == null) {
            return 0;
        }
        return templateInfo.f3195a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3195a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_TemplateInfo(this.f3195a);
            }
            this.f3195a = 0;
        }
    }

    public TemplateInfo() {
        this(jniLivenessAndTMJNI.new_TemplateInfo__SWIG_0(), true);
    }

    public TemplateInfo(int i, String str, String str2, String str3, String str4, TemplatePolygon templatePolygon, int i2, double d) {
        this(jniLivenessAndTMJNI.new_TemplateInfo__SWIG_1(i, str, str2, str3, str4, TemplatePolygon.getCPtr(templatePolygon), templatePolygon, i2, d), true);
    }

    public String getDocumentType() {
        return jniLivenessAndTMJNI.TemplateInfo_getDocumentType(this.f3195a, this);
    }

    public void setDocumentType(String str) {
        jniLivenessAndTMJNI.TemplateInfo_setDocumentType(this.f3195a, this, str);
    }

    public String getCountry() {
        return jniLivenessAndTMJNI.TemplateInfo_getCountry(this.f3195a, this);
    }

    public void setCountry(String str) {
        jniLivenessAndTMJNI.TemplateInfo_setCountry(this.f3195a, this, str);
    }

    public String getState() {
        return jniLivenessAndTMJNI.TemplateInfo_getState(this.f3195a, this);
    }

    public void setState(String str) {
        jniLivenessAndTMJNI.TemplateInfo_setState(this.f3195a, this, str);
    }

    public String getRegion() {
        return jniLivenessAndTMJNI.TemplateInfo_getRegion(this.f3195a, this);
    }

    public void setRegion(String str) {
        jniLivenessAndTMJNI.TemplateInfo_setRegion(this.f3195a, this, str);
    }

    public TemplatePolygon getPolygon() {
        return new TemplatePolygon(jniLivenessAndTMJNI.TemplateInfo_getPolygon(this.f3195a, this), false);
    }

    public void setPolygon(TemplatePolygon templatePolygon) {
        jniLivenessAndTMJNI.TemplateInfo_setPolygon(this.f3195a, this, TemplatePolygon.getCPtr(templatePolygon), templatePolygon);
    }

    public int getTemplateWidth() {
        return jniLivenessAndTMJNI.TemplateInfo_getTemplateWidth(this.f3195a, this);
    }

    public void setTemplateWidth(int i) {
        jniLivenessAndTMJNI.TemplateInfo_setTemplateWidth(this.f3195a, this, i);
    }

    public int getTemplateHeight() {
        return jniLivenessAndTMJNI.TemplateInfo_getTemplateHeight(this.f3195a, this);
    }

    public void setTemplateHeight(int i) {
        jniLivenessAndTMJNI.TemplateInfo_setTemplateHeight(this.f3195a, this, i);
    }

    public int getFrameIndex() {
        return jniLivenessAndTMJNI.TemplateInfo_getFrameIndex(this.f3195a, this);
    }

    public void setFrameIndex(int i) {
        jniLivenessAndTMJNI.TemplateInfo_setFrameIndex(this.f3195a, this, i);
    }

    public int getMatchesCount() {
        return jniLivenessAndTMJNI.TemplateInfo_getMatchesCount(this.f3195a, this);
    }

    public void setMatchesCount(int i) {
        jniLivenessAndTMJNI.TemplateInfo_setMatchesCount(this.f3195a, this, i);
    }

    public double getTransformError() {
        return jniLivenessAndTMJNI.TemplateInfo_getTransformError(this.f3195a, this);
    }

    public void setTransformError(double d) {
        jniLivenessAndTMJNI.TemplateInfo_setTransformError(this.f3195a, this, d);
    }
}
