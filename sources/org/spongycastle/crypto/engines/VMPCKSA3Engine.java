package org.spongycastle.crypto.engines;

public class VMPCKSA3Engine extends VMPCEngine {
    public String getAlgorithmName() {
        return "VMPC-KSA3";
    }

    /* access modifiers changed from: protected */
    public void initKey(byte[] keyBytes, byte[] ivBytes) {
        this.f6721s = 0;
        this.f6719P = new byte[256];
        for (int i = 0; i < 256; i++) {
            this.f6719P[i] = (byte) i;
        }
        for (int m = 0; m < 768; m++) {
            this.f6721s = this.f6719P[(this.f6721s + this.f6719P[m & 255] + keyBytes[m % keyBytes.length]) & 255];
            byte temp = this.f6719P[m & 255];
            this.f6719P[m & 255] = this.f6719P[this.f6721s & 255];
            this.f6719P[this.f6721s & 255] = temp;
        }
        for (int m2 = 0; m2 < 768; m2++) {
            this.f6721s = this.f6719P[(this.f6721s + this.f6719P[m2 & 255] + ivBytes[m2 % ivBytes.length]) & 255];
            byte temp2 = this.f6719P[m2 & 255];
            this.f6719P[m2 & 255] = this.f6719P[this.f6721s & 255];
            this.f6719P[this.f6721s & 255] = temp2;
        }
        for (int m3 = 0; m3 < 768; m3++) {
            this.f6721s = this.f6719P[(this.f6721s + this.f6719P[m3 & 255] + keyBytes[m3 % keyBytes.length]) & 255];
            byte temp3 = this.f6719P[m3 & 255];
            this.f6719P[m3 & 255] = this.f6719P[this.f6721s & 255];
            this.f6719P[this.f6721s & 255] = temp3;
        }
        this.f6720n = 0;
    }
}
