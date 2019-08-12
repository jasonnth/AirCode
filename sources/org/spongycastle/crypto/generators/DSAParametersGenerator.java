package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.DSAParameterGenerationParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAValidationParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.encoders.Hex;

public class DSAParametersGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: L */
    private int f6728L;

    /* renamed from: N */
    private int f6729N;
    private int certainty;
    private Digest digest;
    private int iterations;
    private SecureRandom random;
    private int usageIndex;
    private boolean use186_3;

    public DSAParametersGenerator() {
        this(new SHA1Digest());
    }

    public DSAParametersGenerator(Digest digest2) {
        this.digest = digest2;
    }

    public void init(int size, int certainty2, SecureRandom random2) {
        this.f6728L = size;
        this.f6729N = getDefaultN(size);
        this.certainty = certainty2;
        this.iterations = Math.max(getMinimumIterations(this.f6728L), (certainty2 + 1) / 2);
        this.random = random2;
        this.use186_3 = false;
        this.usageIndex = -1;
    }

    public void init(DSAParameterGenerationParameters params) {
        int L = params.getL();
        int N = params.getN();
        if (L < 1024 || L > 3072 || L % 1024 != 0) {
            throw new IllegalArgumentException("L values must be between 1024 and 3072 and a multiple of 1024");
        } else if (L == 1024 && N != 160) {
            throw new IllegalArgumentException("N must be 160 for L = 1024");
        } else if (L == 2048 && N != 224 && N != 256) {
            throw new IllegalArgumentException("N must be 224 or 256 for L = 2048");
        } else if (L == 3072 && N != 256) {
            throw new IllegalArgumentException("N must be 256 for L = 3072");
        } else if (this.digest.getDigestSize() * 8 < N) {
            throw new IllegalStateException("Digest output size too small for value of N");
        } else {
            this.f6728L = L;
            this.f6729N = N;
            this.certainty = params.getCertainty();
            this.iterations = Math.max(getMinimumIterations(L), (this.certainty + 1) / 2);
            this.random = params.getRandom();
            this.use186_3 = true;
            this.usageIndex = params.getUsageIndex();
        }
    }

    public DSAParameters generateParameters() {
        if (this.use186_3) {
            return generateParameters_FIPS186_3();
        }
        return generateParameters_FIPS186_2();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Regions count limit reached
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:695)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
        */
    /* JADX WARNING: CFG modification limit reached, blocks count: 125 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x00be A[LOOP:2: B:7:0x00b5->B:9:0x00be, LOOP_END] */
    private org.spongycastle.crypto.params.DSAParameters generateParameters_FIPS186_2() {
        /*
            r22 = this;
            r19 = 20
            r0 = r19
            byte[] r15 = new byte[r0]
            r19 = 20
            r0 = r19
            byte[] r11 = new byte[r0]
            r19 = 20
            r0 = r19
            byte[] r12 = new byte[r0]
            r19 = 20
            r0 = r19
            byte[] r0 = new byte[r0]
            r16 = r0
            r0 = r22
            int r0 = r0.f6728L
            r19 = r0
            int r19 = r19 + -1
            r0 = r19
            int r8 = r0 / 160
            r0 = r22
            int r0 = r0.f6728L
            r19 = r0
            int r19 = r19 / 8
            r0 = r19
            byte[] r0 = new byte[r0]
            r17 = r0
            r0 = r22
            org.spongycastle.crypto.Digest r0 = r0.digest
            r19 = r0
            r0 = r19
            boolean r0 = r0 instanceof org.spongycastle.crypto.digests.SHA1Digest
            r19 = r0
            if (r19 != 0) goto L_0x0078
            java.lang.IllegalStateException r19 = new java.lang.IllegalStateException
            java.lang.String r20 = "can only use SHA-1 for generating FIPS 186-2 parameters"
            r19.<init>(r20)
            throw r19
        L_0x004b:
            r19 = 0
            byte r20 = r16[r19]
            r20 = r20 | -128(0xffffffffffffff80, float:NaN)
            r0 = r20
            byte r0 = (byte) r0
            r20 = r0
            r16[r19] = r20
            r19 = 19
            byte r20 = r16[r19]
            r20 = r20 | 1
            r0 = r20
            byte r0 = (byte) r0
            r20 = r0
            r16[r19] = r20
            java.math.BigInteger r13 = new java.math.BigInteger
            r19 = 1
            r0 = r19
            r1 = r16
            r13.<init>(r0, r1)
            r0 = r22
            boolean r19 = r0.isProbablePrime(r13)
            if (r19 != 0) goto L_0x00ce
        L_0x0078:
            r0 = r22
            java.security.SecureRandom r0 = r0.random
            r19 = r0
            r0 = r19
            r0.nextBytes(r15)
            r0 = r22
            org.spongycastle.crypto.Digest r0 = r0.digest
            r19 = r0
            r20 = 0
            r0 = r19
            r1 = r20
            hash(r0, r15, r11, r1)
            r19 = 0
            r20 = 0
            int r0 = r15.length
            r21 = r0
            r0 = r19
            r1 = r20
            r2 = r21
            java.lang.System.arraycopy(r15, r0, r12, r1, r2)
            inc(r12)
            r0 = r22
            org.spongycastle.crypto.Digest r0 = r0.digest
            r19 = r0
            r20 = 0
            r0 = r19
            r1 = r20
            hash(r0, r12, r12, r1)
            r6 = 0
        L_0x00b5:
            r0 = r16
            int r0 = r0.length
            r19 = r0
            r0 = r19
            if (r6 == r0) goto L_0x004b
            byte r19 = r11[r6]
            byte r20 = r12[r6]
            r19 = r19 ^ r20
            r0 = r19
            byte r0 = (byte) r0
            r19 = r0
            r16[r6] = r19
            int r6 = r6 + 1
            goto L_0x00b5
        L_0x00ce:
            byte[] r9 = org.spongycastle.util.Arrays.clone(r15)
            inc(r9)
            r4 = 0
        L_0x00d6:
            r19 = 4096(0x1000, float:5.74E-42)
            r0 = r19
            if (r4 >= r0) goto L_0x0078
            r7 = 1
        L_0x00dd:
            if (r7 > r8) goto L_0x0100
            inc(r9)
            r0 = r22
            org.spongycastle.crypto.Digest r0 = r0.digest
            r19 = r0
            r0 = r17
            int r0 = r0.length
            r20 = r0
            int r0 = r11.length
            r21 = r0
            int r21 = r21 * r7
            int r20 = r20 - r21
            r0 = r19
            r1 = r17
            r2 = r20
            hash(r0, r9, r1, r2)
            int r7 = r7 + 1
            goto L_0x00dd
        L_0x0100:
            r0 = r17
            int r0 = r0.length
            r19 = r0
            int r0 = r11.length
            r20 = r0
            int r20 = r20 * r8
            int r14 = r19 - r20
            inc(r9)
            r0 = r22
            org.spongycastle.crypto.Digest r0 = r0.digest
            r19 = r0
            r20 = 0
            r0 = r19
            r1 = r20
            hash(r0, r9, r11, r1)
            int r0 = r11.length
            r19 = r0
            int r19 = r19 - r14
            r20 = 0
            r0 = r19
            r1 = r17
            r2 = r20
            java.lang.System.arraycopy(r11, r0, r1, r2, r14)
            r19 = 0
            byte r20 = r17[r19]
            r20 = r20 | -128(0xffffffffffffff80, float:NaN)
            r0 = r20
            byte r0 = (byte) r0
            r20 = r0
            r17[r19] = r20
            java.math.BigInteger r18 = new java.math.BigInteger
            r19 = 1
            r0 = r18
            r1 = r19
            r2 = r17
            r0.<init>(r1, r2)
            r19 = 1
            r0 = r19
            java.math.BigInteger r19 = r13.shiftLeft(r0)
            java.math.BigInteger r3 = r18.mod(r19)
            java.math.BigInteger r19 = ONE
            r0 = r19
            java.math.BigInteger r19 = r3.subtract(r0)
            java.math.BigInteger r10 = r18.subtract(r19)
            int r19 = r10.bitLength()
            r0 = r22
            int r0 = r0.f6728L
            r20 = r0
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0174
        L_0x0170:
            int r4 = r4 + 1
            goto L_0x00d6
        L_0x0174:
            r0 = r22
            boolean r19 = r0.isProbablePrime(r10)
            if (r19 == 0) goto L_0x0170
            r0 = r22
            java.security.SecureRandom r0 = r0.random
            r19 = r0
            r0 = r19
            java.math.BigInteger r5 = calculateGenerator_FIPS186_2(r10, r13, r0)
            org.spongycastle.crypto.params.DSAParameters r19 = new org.spongycastle.crypto.params.DSAParameters
            org.spongycastle.crypto.params.DSAValidationParameters r20 = new org.spongycastle.crypto.params.DSAValidationParameters
            r0 = r20
            r0.<init>(r15, r4)
            r0 = r19
            r1 = r20
            r0.<init>(r10, r13, r5, r1)
            return r19
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.generators.DSAParametersGenerator.generateParameters_FIPS186_2():org.spongycastle.crypto.params.DSAParameters");
    }

    private static BigInteger calculateGenerator_FIPS186_2(BigInteger p, BigInteger q, SecureRandom r) {
        BigInteger g;
        BigInteger e = p.subtract(ONE).divide(q);
        BigInteger pSub2 = p.subtract(TWO);
        do {
            g = BigIntegers.createRandomInRange(TWO, pSub2, r).modPow(e, p);
        } while (g.bitLength() <= 1);
        return g;
    }

    private DSAParameters generateParameters_FIPS186_3() {
        BigInteger q;
        int counter;
        BigInteger p;
        Digest d = this.digest;
        int outlen = d.getDigestSize() * 8;
        byte[] seed = new byte[(this.f6729N / 8)];
        int n = (this.f6728L - 1) / outlen;
        int i = (this.f6728L - 1) % outlen;
        byte[] w = new byte[(this.f6728L / 8)];
        byte[] output = new byte[d.getDigestSize()];
        loop0:
        while (true) {
            this.random.nextBytes(seed);
            hash(d, seed, output, 0);
            BigInteger bigInteger = new BigInteger(1, output);
            q = bigInteger.mod(ONE.shiftLeft(this.f6729N - 1)).setBit(0).setBit(this.f6729N - 1);
            if (isProbablePrime(q)) {
                byte[] offset = Arrays.clone(seed);
                int counterLimit = this.f6728L * 4;
                counter = 0;
                while (counter < counterLimit) {
                    for (int j = 1; j <= n; j++) {
                        inc(offset);
                        hash(d, offset, w, w.length - (output.length * j));
                    }
                    int remaining = w.length - (output.length * n);
                    inc(offset);
                    hash(d, offset, output, 0);
                    System.arraycopy(output, output.length - remaining, w, 0, remaining);
                    w[0] = (byte) (w[0] | ISOFileInfo.DATA_BYTES1);
                    BigInteger X = new BigInteger(1, w);
                    p = X.subtract(X.mod(q.shiftLeft(1)).subtract(ONE));
                    if (p.bitLength() == this.f6728L && isProbablePrime(p)) {
                        break loop0;
                    }
                    counter++;
                }
                continue;
            }
        }
        if (this.usageIndex >= 0) {
            BigInteger g = calculateGenerator_FIPS186_3_Verifiable(d, p, q, seed, this.usageIndex);
            if (g != null) {
                DSAValidationParameters dSAValidationParameters = new DSAValidationParameters(seed, counter, this.usageIndex);
                DSAParameters dSAParameters = new DSAParameters(p, q, g, dSAValidationParameters);
                return dSAParameters;
            }
        }
        BigInteger g2 = calculateGenerator_FIPS186_3_Unverifiable(p, q, this.random);
        DSAValidationParameters dSAValidationParameters2 = new DSAValidationParameters(seed, counter);
        DSAParameters dSAParameters2 = new DSAParameters(p, q, g2, dSAValidationParameters2);
        return dSAParameters2;
    }

    private boolean isProbablePrime(BigInteger x) {
        return x.isProbablePrime(this.certainty);
    }

    private static BigInteger calculateGenerator_FIPS186_3_Unverifiable(BigInteger p, BigInteger q, SecureRandom r) {
        return calculateGenerator_FIPS186_2(p, q, r);
    }

    private static BigInteger calculateGenerator_FIPS186_3_Verifiable(Digest d, BigInteger p, BigInteger q, byte[] seed, int index) {
        BigInteger e = p.subtract(ONE).divide(q);
        byte[] ggen = Hex.decode("6767656E");
        byte[] U = new byte[(seed.length + ggen.length + 1 + 2)];
        System.arraycopy(seed, 0, U, 0, seed.length);
        System.arraycopy(ggen, 0, U, seed.length, ggen.length);
        U[U.length - 3] = (byte) index;
        byte[] w = new byte[d.getDigestSize()];
        for (int count = 1; count < 65536; count++) {
            inc(U);
            hash(d, U, w, 0);
            BigInteger g = new BigInteger(1, w).modPow(e, p);
            if (g.compareTo(TWO) >= 0) {
                return g;
            }
        }
        return null;
    }

    private static void hash(Digest d, byte[] input, byte[] output, int outputPos) {
        d.update(input, 0, input.length);
        d.doFinal(output, outputPos);
    }

    private static int getDefaultN(int L) {
        return L > 1024 ? 256 : 160;
    }

    private static int getMinimumIterations(int L) {
        if (L <= 1024) {
            return 40;
        }
        return (((L - 1) / 1024) * 8) + 48;
    }

    private static void inc(byte[] buf) {
        int i = buf.length - 1;
        while (i >= 0) {
            byte b = (byte) ((buf[i] + 1) & 255);
            buf[i] = b;
            if (b == 0) {
                i--;
            } else {
                return;
            }
        }
    }
}
