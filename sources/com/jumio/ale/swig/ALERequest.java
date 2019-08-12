package com.jumio.ale.swig;

public class ALERequest {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ALERequest(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ALERequest obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
            this.swigCPtr = 0;
        }
    }

    public int calculateRequestSize(ALEHeader aleHeader, int inputLen) {
        return aleEngineJNI.ALERequest_calculateRequestSize(this.swigCPtr, this, ALEHeader.getCPtr(aleHeader), aleHeader, inputLen);
    }

    public int calculateRequestInit(ALEHeader aleHeader, int inputLen) {
        return aleEngineJNI.ALERequest_calculateRequestInit(this.swigCPtr, this, ALEHeader.getCPtr(aleHeader), aleHeader, inputLen);
    }

    public int calculateRequestUpdate(int inputLen) {
        return aleEngineJNI.ALERequest_calculateRequestUpdate(this.swigCPtr, this, inputLen);
    }

    public int calculateRequestFinish() {
        return aleEngineJNI.ALERequest_calculateRequestFinish(this.swigCPtr, this);
    }

    public int initRequest(ALEHeader aleHeader, int inputLen, byte[] out, int outPos) throws Exception {
        return aleEngineJNI.ALERequest_initRequest__SWIG_0(this.swigCPtr, this, ALEHeader.getCPtr(aleHeader), aleHeader, inputLen, out, outPos);
    }

    public int initRequest(ALEHeader aleHeader, int inputLen, byte[] out) throws Exception {
        return aleEngineJNI.ALERequest_initRequest__SWIG_1(this.swigCPtr, this, ALEHeader.getCPtr(aleHeader), aleHeader, inputLen, out);
    }

    public int updateRequest(byte[] in, byte[] out, int outPos) throws Exception {
        return aleEngineJNI.ALERequest_updateRequest__SWIG_0(this.swigCPtr, this, in, out, outPos);
    }

    public int updateRequest(byte[] in, byte[] out) throws Exception {
        return aleEngineJNI.ALERequest_updateRequest__SWIG_1(this.swigCPtr, this, in, out);
    }

    public int finishRequest(byte[] out, int outPos) throws Exception {
        return aleEngineJNI.ALERequest_finishRequest__SWIG_0(this.swigCPtr, this, out, outPos);
    }

    public int finishRequest(byte[] out) throws Exception {
        return aleEngineJNI.ALERequest_finishRequest__SWIG_1(this.swigCPtr, this, out);
    }

    public int request(ALEHeader aleHeader, byte[] in, byte[] out, int outPos) throws Exception {
        return aleEngineJNI.ALERequest_request__SWIG_0(this.swigCPtr, this, ALEHeader.getCPtr(aleHeader), aleHeader, in, out, outPos);
    }

    public int request(ALEHeader aleHeader, byte[] in, byte[] out) throws Exception {
        return aleEngineJNI.ALERequest_request__SWIG_1(this.swigCPtr, this, ALEHeader.getCPtr(aleHeader), aleHeader, in, out);
    }

    public int calculateResponseSize(byte[] in) throws Exception {
        return aleEngineJNI.ALERequest_calculateResponseSize(this.swigCPtr, this, in);
    }

    public void initResponse() {
        aleEngineJNI.ALERequest_initResponse(this.swigCPtr, this);
    }

    public int updateResponse(byte[] in, byte[] out, int outPos) throws Exception {
        return aleEngineJNI.ALERequest_updateResponse__SWIG_0(this.swigCPtr, this, in, out, outPos);
    }

    public int updateResponse(byte[] in, byte[] out) throws Exception {
        return aleEngineJNI.ALERequest_updateResponse__SWIG_1(this.swigCPtr, this, in, out);
    }

    public boolean finishResponse() throws Exception {
        return aleEngineJNI.ALERequest_finishResponse(this.swigCPtr, this);
    }

    public int response(byte[] in, byte[] out, int outPos) throws Exception {
        return aleEngineJNI.ALERequest_response__SWIG_0(this.swigCPtr, this, in, out, outPos);
    }

    public int response(byte[] in, byte[] out) throws Exception {
        return aleEngineJNI.ALERequest_response__SWIG_1(this.swigCPtr, this, in, out);
    }

    public boolean isVerified() {
        return aleEngineJNI.ALERequest_isVerified(this.swigCPtr, this);
    }

    public boolean isKeyUpdate() {
        return aleEngineJNI.ALERequest_isKeyUpdate(this.swigCPtr, this);
    }

    public int getErrorCode() {
        return aleEngineJNI.ALERequest_getErrorCode(this.swigCPtr, this);
    }
}
