package com.jumio.clientlib.impl.livenessAndTM;

public class Uint8Array {

    /* renamed from: a */
    private long f3197a;
    protected boolean swigCMemOwn;

    protected Uint8Array(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3197a = j;
    }

    protected static long getCPtr(Uint8Array uint8Array) {
        if (uint8Array == null) {
            return 0;
        }
        return uint8Array.f3197a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3197a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_Uint8Array(this.f3197a);
            }
            this.f3197a = 0;
        }
    }

    public Uint8Array(int i) {
        this(jniLivenessAndTMJNI.new_Uint8Array(i), true);
    }

    public short getitem(int i) {
        return jniLivenessAndTMJNI.Uint8Array_getitem(this.f3197a, this, i);
    }

    public void setitem(int i, short s) {
        jniLivenessAndTMJNI.Uint8Array_setitem(this.f3197a, this, i, s);
    }

    public SWIGTYPE_p_unsigned_char cast() {
        long Uint8Array_cast = jniLivenessAndTMJNI.Uint8Array_cast(this.f3197a, this);
        if (Uint8Array_cast == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(Uint8Array_cast, false);
    }

    public static Uint8Array frompointer(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        long Uint8Array_frompointer = jniLivenessAndTMJNI.Uint8Array_frompointer(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
        if (Uint8Array_frompointer == 0) {
            return null;
        }
        return new Uint8Array(Uint8Array_frompointer, false);
    }
}
