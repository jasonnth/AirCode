package org.spongycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.KeyEncapsulation;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECMultiplier;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.FixedPointCombMultiplier;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

public class ECIESKeyEncapsulation implements KeyEncapsulation {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private boolean CofactorMode;
    private boolean OldCofactorMode;
    private boolean SingleHashMode;
    private DerivationFunction kdf;
    private ECKeyParameters key;
    private SecureRandom rnd;

    public ECIESKeyEncapsulation(DerivationFunction kdf2, SecureRandom rnd2) {
        this.kdf = kdf2;
        this.rnd = rnd2;
        this.CofactorMode = false;
        this.OldCofactorMode = false;
        this.SingleHashMode = false;
    }

    public ECIESKeyEncapsulation(DerivationFunction kdf2, SecureRandom rnd2, boolean cofactorMode, boolean oldCofactorMode, boolean singleHashMode) {
        this.kdf = kdf2;
        this.rnd = rnd2;
        this.CofactorMode = cofactorMode;
        this.OldCofactorMode = oldCofactorMode;
        this.SingleHashMode = singleHashMode;
    }

    public void init(CipherParameters key2) throws IllegalArgumentException {
        if (!(key2 instanceof ECKeyParameters)) {
            throw new IllegalArgumentException("EC key required");
        }
        this.key = (ECKeyParameters) key2;
    }

    public CipherParameters encrypt(byte[] out, int outOff, int keyLen) throws IllegalArgumentException {
        BigInteger rPrime;
        if (!(this.key instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("Public key required for encryption");
        }
        ECPublicKeyParameters ecPubKey = (ECPublicKeyParameters) this.key;
        ECDomainParameters ecParams = ecPubKey.getParameters();
        ECCurve curve = ecParams.getCurve();
        BigInteger n = ecParams.getN();
        BigInteger h = ecParams.getH();
        BigInteger r = BigIntegers.createRandomInRange(ONE, n, this.rnd);
        if (this.CofactorMode) {
            rPrime = r.multiply(h).mod(n);
        } else {
            rPrime = r;
        }
        ECPoint[] ghTilde = {createBasePointMultiplier().multiply(ecParams.getG(), r), ecPubKey.getQ().multiply(rPrime)};
        curve.normalizeAll(ghTilde);
        ECPoint gTilde = ghTilde[0];
        ECPoint hTilde = ghTilde[1];
        byte[] C = gTilde.getEncoded(false);
        System.arraycopy(C, 0, out, outOff, C.length);
        return deriveKey(keyLen, C, hTilde.getAffineXCoord().getEncoded());
    }

    public CipherParameters encrypt(byte[] out, int keyLen) {
        return encrypt(out, 0, keyLen);
    }

    public CipherParameters decrypt(byte[] in, int inOff, int inLen, int keyLen) throws IllegalArgumentException {
        if (!(this.key instanceof ECPrivateKeyParameters)) {
            throw new IllegalArgumentException("Private key required for encryption");
        }
        ECPrivateKeyParameters ecPrivKey = (ECPrivateKeyParameters) this.key;
        ECDomainParameters ecParams = ecPrivKey.getParameters();
        ECCurve curve = ecParams.getCurve();
        BigInteger n = ecParams.getN();
        BigInteger h = ecParams.getH();
        byte[] C = new byte[inLen];
        System.arraycopy(in, inOff, C, 0, inLen);
        ECPoint gHat = curve.decodePoint(C);
        if (this.CofactorMode || this.OldCofactorMode) {
            gHat = gHat.multiply(h);
        }
        BigInteger xHat = ecPrivKey.getD();
        if (this.CofactorMode) {
            xHat = xHat.multiply(h.modInverse(n)).mod(n);
        }
        return deriveKey(keyLen, C, gHat.multiply(xHat).normalize().getAffineXCoord().getEncoded());
    }

    public CipherParameters decrypt(byte[] in, int keyLen) {
        return decrypt(in, 0, in.length, keyLen);
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    /* access modifiers changed from: protected */
    public KeyParameter deriveKey(int keyLen, byte[] C, byte[] PEH) {
        byte[] kdfInput = PEH;
        if (this.SingleHashMode) {
            kdfInput = Arrays.concatenate(C, PEH);
            Arrays.fill(PEH, 0);
        }
        try {
            this.kdf.init(new KDFParameters(kdfInput, null));
            byte[] K = new byte[keyLen];
            this.kdf.generateBytes(K, 0, K.length);
            return new KeyParameter(K);
        } finally {
            Arrays.fill(kdfInput, 0);
        }
    }
}
