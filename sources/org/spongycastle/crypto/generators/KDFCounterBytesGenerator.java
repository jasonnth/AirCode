package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.MacDerivationFunction;
import org.spongycastle.crypto.params.KDFCounterParameters;
import org.spongycastle.crypto.params.KeyParameter;

public class KDFCounterBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private byte[] fixedInputDataCtrPrefix;
    private byte[] fixedInputData_afterCtr;
    private int generatedBytes;

    /* renamed from: h */
    private final int f6730h;
    private byte[] ios;

    /* renamed from: k */
    private byte[] f6731k = new byte[this.f6730h];
    private int maxSizeExcl;
    private final Mac prf;

    public KDFCounterBytesGenerator(Mac prf2) {
        this.prf = prf2;
        this.f6730h = prf2.getMacSize();
    }

    public void init(DerivationParameters param) {
        int intValue;
        if (!(param instanceof KDFCounterParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFCounterParameters kdfParams = (KDFCounterParameters) param;
        this.prf.init(new KeyParameter(kdfParams.getKI()));
        this.fixedInputDataCtrPrefix = kdfParams.getFixedInputDataCounterPrefix();
        this.fixedInputData_afterCtr = kdfParams.getFixedInputDataCounterSuffix();
        int r = kdfParams.getR();
        this.ios = new byte[(r / 8)];
        BigInteger maxSize = TWO.pow(r).multiply(BigInteger.valueOf((long) this.f6730h));
        if (maxSize.compareTo(INTEGER_MAX) == 1) {
            intValue = Integer.MAX_VALUE;
        } else {
            intValue = maxSize.intValue();
        }
        this.maxSizeExcl = intValue;
        this.generatedBytes = 0;
    }

    public Mac getMac() {
        return this.prf;
    }

    public int generateBytes(byte[] out, int outOff, int len) throws DataLengthException, IllegalArgumentException {
        int generatedBytesAfter = this.generatedBytes + len;
        if (generatedBytesAfter < 0 || generatedBytesAfter >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (this.generatedBytes % this.f6730h == 0) {
            generateNext();
        }
        int toGenerate = len;
        int posInK = this.generatedBytes % this.f6730h;
        int toCopy = Math.min(this.f6730h - (this.generatedBytes % this.f6730h), toGenerate);
        System.arraycopy(this.f6731k, posInK, out, outOff, toCopy);
        this.generatedBytes += toCopy;
        int toGenerate2 = toGenerate - toCopy;
        int outOff2 = outOff + toCopy;
        while (toGenerate2 > 0) {
            generateNext();
            int toCopy2 = Math.min(this.f6730h, toGenerate2);
            System.arraycopy(this.f6731k, 0, out, outOff2, toCopy2);
            this.generatedBytes += toCopy2;
            toGenerate2 -= toCopy2;
            outOff2 += toCopy2;
        }
        return len;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001e, code lost:
        r5.ios[r5.ios.length - 3] = (byte) (r0 >>> 16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002a, code lost:
        r5.ios[r5.ios.length - 2] = (byte) (r0 >>> 8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0036, code lost:
        r5.ios[r5.ios.length - 1] = (byte) r0;
        r5.prf.update(r5.fixedInputDataCtrPrefix, 0, r5.fixedInputDataCtrPrefix.length);
        r5.prf.update(r5.ios, 0, r5.ios.length);
        r5.prf.update(r5.fixedInputData_afterCtr, 0, r5.fixedInputData_afterCtr.length);
        r5.prf.doFinal(r5.f6731k, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0065, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generateNext() {
        /*
            r5 = this;
            r4 = 0
            int r1 = r5.generatedBytes
            int r2 = r5.f6730h
            int r1 = r1 / r2
            int r0 = r1 + 1
            byte[] r1 = r5.ios
            int r1 = r1.length
            switch(r1) {
                case 1: goto L_0x0036;
                case 2: goto L_0x002a;
                case 3: goto L_0x001e;
                case 4: goto L_0x0017;
                default: goto L_0x000e;
            }
        L_0x000e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Unsupported size of counter i"
            r1.<init>(r2)
            throw r1
        L_0x0017:
            byte[] r1 = r5.ios
            int r2 = r0 >>> 24
            byte r2 = (byte) r2
            r1[r4] = r2
        L_0x001e:
            byte[] r1 = r5.ios
            byte[] r2 = r5.ios
            int r2 = r2.length
            int r2 = r2 + -3
            int r3 = r0 >>> 16
            byte r3 = (byte) r3
            r1[r2] = r3
        L_0x002a:
            byte[] r1 = r5.ios
            byte[] r2 = r5.ios
            int r2 = r2.length
            int r2 = r2 + -2
            int r3 = r0 >>> 8
            byte r3 = (byte) r3
            r1[r2] = r3
        L_0x0036:
            byte[] r1 = r5.ios
            byte[] r2 = r5.ios
            int r2 = r2.length
            int r2 = r2 + -1
            byte r3 = (byte) r0
            r1[r2] = r3
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.fixedInputDataCtrPrefix
            byte[] r3 = r5.fixedInputDataCtrPrefix
            int r3 = r3.length
            r1.update(r2, r4, r3)
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.ios
            byte[] r3 = r5.ios
            int r3 = r3.length
            r1.update(r2, r4, r3)
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.fixedInputData_afterCtr
            byte[] r3 = r5.fixedInputData_afterCtr
            int r3 = r3.length
            r1.update(r2, r4, r3)
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.f6731k
            r1.doFinal(r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.generators.KDFCounterBytesGenerator.generateNext():void");
    }
}
