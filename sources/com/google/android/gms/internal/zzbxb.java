package com.google.android.gms.internal;

public class zzbxb {
    private final byte[] zzcuJ = new byte[256];
    private int zzcuK;
    private int zzcuL;

    public zzbxb(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.zzcuJ[i] = (byte) i;
        }
        byte b = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            b = (b + this.zzcuJ[i2] + bArr[i2 % bArr.length]) & 255;
            byte b2 = this.zzcuJ[i2];
            this.zzcuJ[i2] = this.zzcuJ[b];
            this.zzcuJ[b] = b2;
        }
        this.zzcuK = 0;
        this.zzcuL = 0;
    }

    public void zzab(byte[] bArr) {
        int i = this.zzcuK;
        int i2 = this.zzcuL;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.zzcuJ[i]) & 255;
            byte b = this.zzcuJ[i];
            this.zzcuJ[i] = this.zzcuJ[i2];
            this.zzcuJ[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.zzcuJ[(this.zzcuJ[i] + this.zzcuJ[i2]) & 255]);
        }
        this.zzcuK = i;
        this.zzcuL = i2;
    }
}
