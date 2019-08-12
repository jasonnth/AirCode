package com.miteksystems.misnap.natives;

public class MiSnapScience {

    /* renamed from: a */
    public static boolean f3675a;

    public native void Analyze(byte[] bArr, int i, int i2, int i3, int i4, int[] iArr);

    static {
        try {
            System.loadLibrary("coreflow");
            f3675a = true;
        } catch (UnsatisfiedLinkError e) {
            f3675a = false;
        }
    }
}
