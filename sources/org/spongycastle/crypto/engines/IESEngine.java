package org.spongycastle.crypto.engines;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.EphemeralKeyPair;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.IESParameters;
import org.spongycastle.crypto.params.IESWithCipherParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Pack;

public class IESEngine {

    /* renamed from: IV */
    private byte[] f6679IV;

    /* renamed from: V */
    byte[] f6680V;
    BasicAgreement agree;
    BufferedBlockCipher cipher;
    boolean forEncryption;
    DerivationFunction kdf;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
    Mac mac;
    byte[] macBuf;
    IESParameters param;
    CipherParameters privParam;
    CipherParameters pubParam;

    public IESEngine(BasicAgreement agree2, DerivationFunction kdf2, Mac mac2) {
        this.agree = agree2;
        this.kdf = kdf2;
        this.mac = mac2;
        this.macBuf = new byte[mac2.getMacSize()];
        this.cipher = null;
    }

    public IESEngine(BasicAgreement agree2, DerivationFunction kdf2, Mac mac2, BufferedBlockCipher cipher2) {
        this.agree = agree2;
        this.kdf = kdf2;
        this.mac = mac2;
        this.macBuf = new byte[mac2.getMacSize()];
        this.cipher = cipher2;
    }

    public void init(boolean forEncryption2, CipherParameters privParam2, CipherParameters pubParam2, CipherParameters params) {
        this.forEncryption = forEncryption2;
        this.privParam = privParam2;
        this.pubParam = pubParam2;
        this.f6680V = new byte[0];
        extractParams(params);
    }

    public void init(AsymmetricKeyParameter publicKey, CipherParameters params, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.forEncryption = true;
        this.pubParam = publicKey;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(params);
    }

    public void init(AsymmetricKeyParameter privateKey, CipherParameters params, KeyParser publicKeyParser) {
        this.forEncryption = false;
        this.privParam = privateKey;
        this.keyParser = publicKeyParser;
        extractParams(params);
    }

    private void extractParams(CipherParameters params) {
        if (params instanceof ParametersWithIV) {
            this.f6679IV = ((ParametersWithIV) params).getIV();
            this.param = (IESParameters) ((ParametersWithIV) params).getParameters();
            return;
        }
        this.f6679IV = null;
        this.param = (IESParameters) params;
    }

    public BufferedBlockCipher getCipher() {
        return this.cipher;
    }

    public Mac getMac() {
        return this.mac;
    }

    private byte[] encryptBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        byte[] K2;
        byte[] C;
        int len;
        if (this.cipher == null) {
            byte[] K1 = new byte[inLen];
            K2 = new byte[(this.param.getMacKeySize() / 8)];
            byte[] K = new byte[(K1.length + K2.length)];
            this.kdf.generateBytes(K, 0, K.length);
            if (this.f6680V.length != 0) {
                System.arraycopy(K, 0, K2, 0, K2.length);
                System.arraycopy(K, K2.length, K1, 0, K1.length);
            } else {
                System.arraycopy(K, 0, K1, 0, K1.length);
                System.arraycopy(K, inLen, K2, 0, K2.length);
            }
            C = new byte[inLen];
            for (int i = 0; i != inLen; i++) {
                C[i] = (byte) (in[inOff + i] ^ K1[i]);
            }
            len = inLen;
        } else {
            byte[] K12 = new byte[(((IESWithCipherParameters) this.param).getCipherKeySize() / 8)];
            K2 = new byte[(this.param.getMacKeySize() / 8)];
            byte[] K3 = new byte[(K12.length + K2.length)];
            this.kdf.generateBytes(K3, 0, K3.length);
            System.arraycopy(K3, 0, K12, 0, K12.length);
            System.arraycopy(K3, K12.length, K2, 0, K2.length);
            if (this.f6679IV != null) {
                this.cipher.init(true, new ParametersWithIV(new KeyParameter(K12), this.f6679IV));
            } else {
                this.cipher.init(true, new KeyParameter(K12));
            }
            C = new byte[this.cipher.getOutputSize(inLen)];
            int len2 = this.cipher.processBytes(in, inOff, inLen, C, 0);
            len = len2 + this.cipher.doFinal(C, len2);
        }
        byte[] P2 = this.param.getEncodingV();
        byte[] L2 = null;
        if (this.f6680V.length != 0) {
            L2 = getLengthTag(P2);
        }
        byte[] T = new byte[this.mac.getMacSize()];
        this.mac.init(new KeyParameter(K2));
        this.mac.update(C, 0, C.length);
        if (P2 != null) {
            this.mac.update(P2, 0, P2.length);
        }
        if (this.f6680V.length != 0) {
            this.mac.update(L2, 0, L2.length);
        }
        this.mac.doFinal(T, 0);
        byte[] Output = new byte[(this.f6680V.length + len + T.length)];
        System.arraycopy(this.f6680V, 0, Output, 0, this.f6680V.length);
        System.arraycopy(C, 0, Output, this.f6680V.length, len);
        System.arraycopy(T, 0, Output, this.f6680V.length + len, T.length);
        return Output;
    }

    private byte[] decryptBlock(byte[] in_enc, int inOff, int inLen) throws InvalidCipherTextException {
        byte[] K2;
        byte[] M;
        int len;
        if (inLen < this.f6680V.length + this.mac.getMacSize()) {
            throw new InvalidCipherTextException("Length of input must be greater than the MAC and V combined");
        }
        if (this.cipher == null) {
            byte[] K1 = new byte[((inLen - this.f6680V.length) - this.mac.getMacSize())];
            K2 = new byte[(this.param.getMacKeySize() / 8)];
            byte[] K = new byte[(K1.length + K2.length)];
            this.kdf.generateBytes(K, 0, K.length);
            if (this.f6680V.length != 0) {
                System.arraycopy(K, 0, K2, 0, K2.length);
                System.arraycopy(K, K2.length, K1, 0, K1.length);
            } else {
                System.arraycopy(K, 0, K1, 0, K1.length);
                System.arraycopy(K, K1.length, K2, 0, K2.length);
            }
            M = new byte[K1.length];
            for (int i = 0; i != K1.length; i++) {
                M[i] = (byte) (in_enc[(this.f6680V.length + inOff) + i] ^ K1[i]);
            }
            len = K1.length;
        } else {
            byte[] K12 = new byte[(((IESWithCipherParameters) this.param).getCipherKeySize() / 8)];
            K2 = new byte[(this.param.getMacKeySize() / 8)];
            byte[] K3 = new byte[(K12.length + K2.length)];
            this.kdf.generateBytes(K3, 0, K3.length);
            System.arraycopy(K3, 0, K12, 0, K12.length);
            System.arraycopy(K3, K12.length, K2, 0, K2.length);
            if (this.f6679IV != null) {
                this.cipher.init(false, new ParametersWithIV(new KeyParameter(K12), this.f6679IV));
            } else {
                this.cipher.init(false, new KeyParameter(K12));
            }
            M = new byte[this.cipher.getOutputSize((inLen - this.f6680V.length) - this.mac.getMacSize())];
            int len2 = this.cipher.processBytes(in_enc, inOff + this.f6680V.length, (inLen - this.f6680V.length) - this.mac.getMacSize(), M, 0);
            len = len2 + this.cipher.doFinal(M, len2);
        }
        byte[] P2 = this.param.getEncodingV();
        byte[] L2 = null;
        if (this.f6680V.length != 0) {
            L2 = getLengthTag(P2);
        }
        int end = inOff + inLen;
        byte[] T1 = Arrays.copyOfRange(in_enc, end - this.mac.getMacSize(), end);
        byte[] T2 = new byte[T1.length];
        this.mac.init(new KeyParameter(K2));
        this.mac.update(in_enc, this.f6680V.length + inOff, (inLen - this.f6680V.length) - T2.length);
        if (P2 != null) {
            this.mac.update(P2, 0, P2.length);
        }
        if (this.f6680V.length != 0) {
            this.mac.update(L2, 0, L2.length);
        }
        this.mac.doFinal(T2, 0);
        if (Arrays.constantTimeAreEqual(T1, T2)) {
            return Arrays.copyOfRange(M, 0, len);
        }
        throw new InvalidCipherTextException("Invalid MAC.");
    }

    public byte[] processBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        byte[] decryptBlock;
        if (this.forEncryption) {
            if (this.keyPairGenerator != null) {
                EphemeralKeyPair ephKeyPair = this.keyPairGenerator.generate();
                this.privParam = ephKeyPair.getKeyPair().getPrivate();
                this.f6680V = ephKeyPair.getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            ByteArrayInputStream bIn = new ByteArrayInputStream(in, inOff, inLen);
            try {
                this.pubParam = this.keyParser.readKey(bIn);
                this.f6680V = Arrays.copyOfRange(in, inOff, inOff + (inLen - bIn.available()));
            } catch (IOException e) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e.getMessage(), e);
            }
        }
        this.agree.init(this.privParam);
        byte[] Z = BigIntegers.asUnsignedByteArray(this.agree.getFieldSize(), this.agree.calculateAgreement(this.pubParam));
        if (this.f6680V.length != 0) {
            byte[] VZ = Arrays.concatenate(this.f6680V, Z);
            Arrays.fill(Z, 0);
            Z = VZ;
        }
        try {
            this.kdf.init(new KDFParameters(Z, this.param.getDerivationV()));
            if (this.forEncryption) {
                decryptBlock = encryptBlock(in, inOff, inLen);
            } else {
                decryptBlock = decryptBlock(in, inOff, inLen);
            }
            return decryptBlock;
        } finally {
            Arrays.fill(Z, 0);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getLengthTag(byte[] p2) {
        byte[] L2 = new byte[8];
        if (p2 != null) {
            Pack.longToBigEndian(((long) p2.length) * 8, L2, 0);
        }
        return L2;
    }
}
