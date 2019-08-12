package com.jumio.ale.swig;

public class ALECore {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ALECore(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ALECore obj) {
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
                aleEngineJNI.delete_ALECore(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public ALECore(ALESettings aleSettings) {
        this(aleEngineJNI.new_ALECore(ALESettings.getCPtr(aleSettings), aleSettings), true);
    }

    public ALERequest createRequest() throws Exception {
        long cPtr = aleEngineJNI.ALECore_createRequest(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new ALERequest(cPtr, false);
    }

    public void destroyRequest(ALERequest request) {
        aleEngineJNI.ALECore_destroyRequest(this.swigCPtr, this, ALERequest.getCPtr(request), request);
    }
}
