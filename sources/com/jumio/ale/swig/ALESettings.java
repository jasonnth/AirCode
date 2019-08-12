package com.jumio.ale.swig;

public class ALESettings {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ALESettings(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ALESettings obj) {
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
                aleEngineJNI.delete_ALESettings(this.swigCPtr);
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
        aleEngineJNI.ALESettings_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        aleEngineJNI.ALESettings_change_ownership(this, this.swigCPtr, true);
    }

    public ALESettings() {
        this(aleEngineJNI.new_ALESettings(), true);
        aleEngineJNI.ALESettings_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    public void setDirectory(String directory) {
        aleEngineJNI.ALESettings_setDirectory(this.swigCPtr, this, directory);
    }

    public String getDirectory() {
        return aleEngineJNI.ALESettings_getDirectory(this.swigCPtr, this);
    }

    public void setKeyID(String keyID) {
        aleEngineJNI.ALESettings_setKeyID(this.swigCPtr, this, keyID);
    }

    public String getKeyID() {
        return aleEngineJNI.ALESettings_getKeyID(this.swigCPtr, this);
    }

    public void setPublicKey(String publicKey) {
        aleEngineJNI.ALESettings_setPublicKey(this.swigCPtr, this, publicKey);
    }

    public String getPublicKey() {
        return aleEngineJNI.ALESettings_getPublicKey(this.swigCPtr, this);
    }
}
