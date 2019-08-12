package com.jumio.mrz.impl.smartEngines.swig;

public class StreamReporterInterface {

    /* renamed from: a */
    private long f3228a;
    protected boolean swigCMemOwn;

    protected StreamReporterInterface(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3228a = j;
    }

    protected static long getCPtr(StreamReporterInterface streamReporterInterface) {
        if (streamReporterInterface == null) {
            return 0;
        }
        return streamReporterInterface.f3228a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3228a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_StreamReporterInterface(this.f3228a);
            }
            this.f3228a = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        mrzjniInterfaceJNI.StreamReporterInterface_change_ownership(this, this.f3228a, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        mrzjniInterfaceJNI.StreamReporterInterface_change_ownership(this, this.f3228a, true);
    }

    public void SymbolRectsFound(MrzRectMatrix mrzRectMatrix) {
        if (getClass() == StreamReporterInterface.class) {
            mrzjniInterfaceJNI.StreamReporterInterface_SymbolRectsFound(this.f3228a, this, MrzRectMatrix.getCPtr(mrzRectMatrix), mrzRectMatrix);
            return;
        }
        mrzjniInterfaceJNI.m1935xfccf7350(this.f3228a, this, MrzRectMatrix.getCPtr(mrzRectMatrix), mrzRectMatrix);
    }

    public void SymbolRectsFoundAfterSnapshotProcessed(MrzRectMatrix mrzRectMatrix) {
        if (getClass() == StreamReporterInterface.class) {
            mrzjniInterfaceJNI.StreamReporterInterface_SymbolRectsFoundAfterSnapshotProcessed(this.f3228a, this, MrzRectMatrix.getCPtr(mrzRectMatrix), mrzRectMatrix);
            return;
        }
        mrzjniInterfaceJNI.m1934xb2444682(this.f3228a, this, MrzRectMatrix.getCPtr(mrzRectMatrix), mrzRectMatrix);
    }

    public void SnapshotRejected() {
        if (getClass() == StreamReporterInterface.class) {
            mrzjniInterfaceJNI.StreamReporterInterface_SnapshotRejected__SWIG_0(this.f3228a, this);
        } else {
            mrzjniInterfaceJNI.m1932x715d2f8c(this.f3228a, this);
        }
    }

    public void SnapshotRejected(String str) {
        if (getClass() == StreamReporterInterface.class) {
            mrzjniInterfaceJNI.StreamReporterInterface_SnapshotRejected__SWIG_1(this.f3228a, this, str);
        } else {
            mrzjniInterfaceJNI.m1933x715d2f8d(this.f3228a, this, str);
        }
    }

    public void SnapshotProcessed(MrzResult mrzResult, boolean z) {
        mrzjniInterfaceJNI.StreamReporterInterface_SnapshotProcessed(this.f3228a, this, MrzResult.getCPtr(mrzResult), mrzResult, z);
    }

    public StreamReporterInterface() {
        this(mrzjniInterfaceJNI.new_StreamReporterInterface(), true);
        mrzjniInterfaceJNI.StreamReporterInterface_director_connect(this, this.f3228a, this.swigCMemOwn, true);
    }
}
