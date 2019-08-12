package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.MacDerivationFunction;
import org.spongycastle.crypto.params.KDFDoublePipelineIterationParameters;
import org.spongycastle.crypto.params.KeyParameter;

public class KDFDoublePipelineIterationBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    /* renamed from: a */
    private byte[] f6732a = new byte[this.f6733h];
    private byte[] fixedInputData;
    private int generatedBytes;

    /* renamed from: h */
    private final int f6733h;
    private byte[] ios;

    /* renamed from: k */
    private byte[] f6734k = new byte[this.f6733h];
    private int maxSizeExcl;
    private final Mac prf;
    private boolean useCounter;

    public KDFDoublePipelineIterationBytesGenerator(Mac prf2) {
        this.prf = prf2;
        this.f6733h = prf2.getMacSize();
    }

    public void init(DerivationParameters params) {
        int i = Integer.MAX_VALUE;
        if (!(params instanceof KDFDoublePipelineIterationParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFDoublePipelineIterationParameters dpiParams = (KDFDoublePipelineIterationParameters) params;
        this.prf.init(new KeyParameter(dpiParams.getKI()));
        this.fixedInputData = dpiParams.getFixedInputData();
        int r = dpiParams.getR();
        this.ios = new byte[(r / 8)];
        if (dpiParams.useCounter()) {
            BigInteger maxSize = TWO.pow(r).multiply(BigInteger.valueOf((long) this.f6733h));
            if (maxSize.compareTo(INTEGER_MAX) != 1) {
                i = maxSize.intValue();
            }
            this.maxSizeExcl = i;
        } else {
            this.maxSizeExcl = Integer.MAX_VALUE;
        }
        this.useCounter = dpiParams.useCounter();
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
        if (this.generatedBytes % this.f6733h == 0) {
            generateNext();
        }
        int toGenerate = len;
        int posInK = this.generatedBytes % this.f6733h;
        int toCopy = Math.min(this.f6733h - (this.generatedBytes % this.f6733h), toGenerate);
        System.arraycopy(this.f6734k, posInK, out, outOff, toCopy);
        this.generatedBytes += toCopy;
        int toGenerate2 = toGenerate - toCopy;
        int outOff2 = outOff + toCopy;
        while (toGenerate2 > 0) {
            generateNext();
            int toCopy2 = Math.min(this.f6733h, toGenerate2);
            System.arraycopy(this.f6734k, 0, out, outOff2, toCopy2);
            this.generatedBytes += toCopy2;
            toGenerate2 -= toCopy2;
            outOff2 += toCopy2;
        }
        return len;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0053, code lost:
        r5.ios[r5.ios.length - 3] = (byte) (r0 >>> 16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005f, code lost:
        r5.ios[r5.ios.length - 2] = (byte) (r0 >>> 8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x006b, code lost:
        r5.ios[r5.ios.length - 1] = (byte) r0;
        r5.prf.update(r5.ios, 0, r5.ios.length);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generateNext() {
        /*
            r5 = this;
            r4 = 0
            int r1 = r5.generatedBytes
            if (r1 != 0) goto L_0x003a
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.fixedInputData
            byte[] r3 = r5.fixedInputData
            int r3 = r3.length
            r1.update(r2, r4, r3)
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.f6732a
            r1.doFinal(r2, r4)
        L_0x0016:
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.f6732a
            byte[] r3 = r5.f6732a
            int r3 = r3.length
            r1.update(r2, r4, r3)
            boolean r1 = r5.useCounter
            if (r1 == 0) goto L_0x007f
            int r1 = r5.generatedBytes
            int r2 = r5.f6733h
            int r1 = r1 / r2
            int r0 = r1 + 1
            byte[] r1 = r5.ios
            int r1 = r1.length
            switch(r1) {
                case 1: goto L_0x006b;
                case 2: goto L_0x005f;
                case 3: goto L_0x0053;
                case 4: goto L_0x004c;
                default: goto L_0x0031;
            }
        L_0x0031:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Unsupported size of counter i"
            r1.<init>(r2)
            throw r1
        L_0x003a:
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.f6732a
            byte[] r3 = r5.f6732a
            int r3 = r3.length
            r1.update(r2, r4, r3)
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.f6732a
            r1.doFinal(r2, r4)
            goto L_0x0016
        L_0x004c:
            byte[] r1 = r5.ios
            int r2 = r0 >>> 24
            byte r2 = (byte) r2
            r1[r4] = r2
        L_0x0053:
            byte[] r1 = r5.ios
            byte[] r2 = r5.ios
            int r2 = r2.length
            int r2 = r2 + -3
            int r3 = r0 >>> 16
            byte r3 = (byte) r3
            r1[r2] = r3
        L_0x005f:
            byte[] r1 = r5.ios
            byte[] r2 = r5.ios
            int r2 = r2.length
            int r2 = r2 + -2
            int r3 = r0 >>> 8
            byte r3 = (byte) r3
            r1[r2] = r3
        L_0x006b:
            byte[] r1 = r5.ios
            byte[] r2 = r5.ios
            int r2 = r2.length
            int r2 = r2 + -1
            byte r3 = (byte) r0
            r1[r2] = r3
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.ios
            byte[] r3 = r5.ios
            int r3 = r3.length
            r1.update(r2, r4, r3)
        L_0x007f:
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.fixedInputData
            byte[] r3 = r5.fixedInputData
            int r3 = r3.length
            r1.update(r2, r4, r3)
            org.spongycastle.crypto.Mac r1 = r5.prf
            byte[] r2 = r5.f6734k
            r1.doFinal(r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.generators.KDFDoublePipelineIterationBytesGenerator.generateNext():void");
    }
}
