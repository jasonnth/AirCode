package com.jumio.ocr.impl.smartEngines.swig;

import com.jumio.ocr.impl.smartEngines.swig.ResultAcceptorInterfaceSettings.FieldName;

public class ResultAcceptorInterface {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ResultAcceptorInterface(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ResultAcceptorInterface obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniInterfaceJNI.delete_ResultAcceptorInterface(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        jniInterfaceJNI.ResultAcceptorInterface_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        jniInterfaceJNI.ResultAcceptorInterface_change_ownership(this, this.swigCPtr, true);
    }

    public void accept(FieldName fieldName, OcrCharStringVector value, int quadrangleIndex, int totalQuadrangles, ResultAcceptorInterfaceSettings acceptorSettings, boolean toAcceptFlag) {
        if (getClass() == ResultAcceptorInterface.class) {
            jniInterfaceJNI.ResultAcceptorInterface_accept(this.swigCPtr, this, fieldName.swigValue(), OcrCharStringVector.getCPtr(value), value, quadrangleIndex, totalQuadrangles, ResultAcceptorInterfaceSettings.getCPtr(acceptorSettings), acceptorSettings, toAcceptFlag);
            return;
        }
        jniInterfaceJNI.m2054x6812f25(this.swigCPtr, this, fieldName.swigValue(), OcrCharStringVector.getCPtr(value), value, quadrangleIndex, totalQuadrangles, ResultAcceptorInterfaceSettings.getCPtr(acceptorSettings), acceptorSettings, toAcceptFlag);
    }

    public ResultAcceptorInterface() {
        this(jniInterfaceJNI.new_ResultAcceptorInterface(), true);
        jniInterfaceJNI.ResultAcceptorInterface_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
