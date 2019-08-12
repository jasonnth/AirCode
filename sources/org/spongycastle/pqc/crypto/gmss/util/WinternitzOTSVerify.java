package org.spongycastle.pqc.crypto.gmss.util;

import org.spongycastle.crypto.Digest;

public class WinternitzOTSVerify {
    private Digest messDigestOTS;

    /* renamed from: w */
    private int f7090w;

    public WinternitzOTSVerify(Digest digest, int w) {
        this.f7090w = w;
        this.messDigestOTS = digest;
    }

    public int getSignatureLength() {
        int mdsize = this.messDigestOTS.getDigestSize();
        int size = ((mdsize << 3) + (this.f7090w - 1)) / this.f7090w;
        return mdsize * (size + (((this.f7090w + getLog((size << this.f7090w) + 1)) - 1) / this.f7090w));
    }

    public byte[] Verify(byte[] message, byte[] signature) {
        int mdsize = this.messDigestOTS.getDigestSize();
        byte[] bArr = new byte[mdsize];
        this.messDigestOTS.update(message, 0, message.length);
        byte[] hash = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(hash, 0);
        int size = ((mdsize << 3) + (this.f7090w - 1)) / this.f7090w;
        int logs = getLog((size << this.f7090w) + 1);
        int testKeySize = mdsize * (size + (((this.f7090w + logs) - 1) / this.f7090w));
        if (testKeySize != signature.length) {
            return null;
        }
        byte[] testKey = new byte[testKeySize];
        int c = 0;
        int counter = 0;
        if (8 % this.f7090w == 0) {
            int d = 8 / this.f7090w;
            int k = (1 << this.f7090w) - 1;
            byte[] hlp = new byte[mdsize];
            for (int i = 0; i < hash.length; i++) {
                for (int j = 0; j < d; j++) {
                    int test = hash[i] & k;
                    c += test;
                    System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                    while (test < k) {
                        this.messDigestOTS.update(hlp, 0, hlp.length);
                        hlp = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(hlp, 0);
                        test++;
                    }
                    System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                    hash[i] = (byte) (hash[i] >>> this.f7090w);
                    counter++;
                }
            }
            int c2 = (size << this.f7090w) - c;
            int i2 = 0;
            while (i2 < logs) {
                System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                for (int test2 = c2 & k; test2 < k; test2++) {
                    this.messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(hlp, 0);
                }
                System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                c2 >>>= this.f7090w;
                counter++;
                i2 += this.f7090w;
            }
        } else if (this.f7090w < 8) {
            int d2 = mdsize / this.f7090w;
            int k2 = (1 << this.f7090w) - 1;
            byte[] hlp2 = new byte[mdsize];
            int ii = 0;
            for (int i3 = 0; i3 < d2; i3++) {
                long big8 = 0;
                for (int j2 = 0; j2 < this.f7090w; j2++) {
                    big8 ^= (long) ((hash[ii] & 255) << (j2 << 3));
                    ii++;
                }
                for (int j3 = 0; j3 < 8; j3++) {
                    int test3 = (int) (((long) k2) & big8);
                    c += test3;
                    System.arraycopy(signature, counter * mdsize, hlp2, 0, mdsize);
                    while (test3 < k2) {
                        this.messDigestOTS.update(hlp2, 0, hlp2.length);
                        hlp2 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(hlp2, 0);
                        test3++;
                    }
                    System.arraycopy(hlp2, 0, testKey, counter * mdsize, mdsize);
                    big8 >>>= this.f7090w;
                    counter++;
                }
            }
            int d3 = mdsize % this.f7090w;
            long big82 = 0;
            for (int j4 = 0; j4 < d3; j4++) {
                big82 ^= (long) ((hash[ii] & 255) << (j4 << 3));
                ii++;
            }
            int d4 = d3 << 3;
            int j5 = 0;
            while (j5 < d4) {
                int test4 = (int) (((long) k2) & big82);
                c += test4;
                System.arraycopy(signature, counter * mdsize, hlp2, 0, mdsize);
                while (test4 < k2) {
                    this.messDigestOTS.update(hlp2, 0, hlp2.length);
                    hlp2 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(hlp2, 0);
                    test4++;
                }
                System.arraycopy(hlp2, 0, testKey, counter * mdsize, mdsize);
                big82 >>>= this.f7090w;
                counter++;
                j5 += this.f7090w;
            }
            int c3 = (size << this.f7090w) - c;
            int i4 = 0;
            while (i4 < logs) {
                System.arraycopy(signature, counter * mdsize, hlp2, 0, mdsize);
                for (int test5 = c3 & k2; test5 < k2; test5++) {
                    this.messDigestOTS.update(hlp2, 0, hlp2.length);
                    hlp2 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(hlp2, 0);
                }
                System.arraycopy(hlp2, 0, testKey, counter * mdsize, mdsize);
                c3 >>>= this.f7090w;
                counter++;
                i4 += this.f7090w;
            }
        } else if (this.f7090w < 57) {
            int d5 = (mdsize << 3) - this.f7090w;
            int k3 = (1 << this.f7090w) - 1;
            byte[] hlp3 = new byte[mdsize];
            int r = 0;
            while (r <= d5) {
                int s = r >>> 3;
                int rest = r % 8;
                r += this.f7090w;
                long big83 = 0;
                int ii2 = 0;
                for (int j6 = s; j6 < ((r + 7) >>> 3); j6++) {
                    big83 ^= (long) ((hash[j6] & 255) << (ii2 << 3));
                    ii2++;
                }
                long test8 = (big83 >>> rest) & ((long) k3);
                c = (int) (((long) c) + test8);
                System.arraycopy(signature, counter * mdsize, hlp3, 0, mdsize);
                while (test8 < ((long) k3)) {
                    this.messDigestOTS.update(hlp3, 0, hlp3.length);
                    hlp3 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(hlp3, 0);
                    test8++;
                }
                System.arraycopy(hlp3, 0, testKey, counter * mdsize, mdsize);
                counter++;
            }
            int s2 = r >>> 3;
            if (s2 < mdsize) {
                int rest2 = r % 8;
                long big84 = 0;
                int ii3 = 0;
                for (int j7 = s2; j7 < mdsize; j7++) {
                    big84 ^= (long) ((hash[j7] & 255) << (ii3 << 3));
                    ii3++;
                }
                long test82 = (big84 >>> rest2) & ((long) k3);
                c = (int) (((long) c) + test82);
                System.arraycopy(signature, counter * mdsize, hlp3, 0, mdsize);
                while (test82 < ((long) k3)) {
                    this.messDigestOTS.update(hlp3, 0, hlp3.length);
                    hlp3 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(hlp3, 0);
                    test82++;
                }
                System.arraycopy(hlp3, 0, testKey, counter * mdsize, mdsize);
                counter++;
            }
            int c4 = (size << this.f7090w) - c;
            int i5 = 0;
            while (i5 < logs) {
                System.arraycopy(signature, counter * mdsize, hlp3, 0, mdsize);
                for (long test83 = (long) (c4 & k3); test83 < ((long) k3); test83++) {
                    this.messDigestOTS.update(hlp3, 0, hlp3.length);
                    hlp3 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(hlp3, 0);
                }
                System.arraycopy(hlp3, 0, testKey, counter * mdsize, mdsize);
                c4 >>>= this.f7090w;
                counter++;
                i5 += this.f7090w;
            }
        }
        byte[] bArr2 = new byte[mdsize];
        this.messDigestOTS.update(testKey, 0, testKey.length);
        byte[] TKey = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(TKey, 0);
        return TKey;
    }

    public int getLog(int intValue) {
        int log = 1;
        int i = 2;
        while (i < intValue) {
            i <<= 1;
            log++;
        }
        return log;
    }
}
