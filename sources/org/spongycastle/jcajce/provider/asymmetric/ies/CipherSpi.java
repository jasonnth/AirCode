package org.spongycastle.jcajce.provider.asymmetric.ies;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.agreement.DHBasicAgreement;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.engines.IESEngine;
import org.spongycastle.crypto.engines.OldIESEngine;
import org.spongycastle.crypto.generators.KDF2BytesGenerator;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.IESParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.DHUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.interfaces.ECPrivateKey;
import org.spongycastle.jce.interfaces.IESKey;
import org.spongycastle.jce.spec.IESParameterSpec;

public class CipherSpi extends javax.crypto.CipherSpi {
    private Class[] availableSpecs = {IESParameterSpec.class};
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private IESEngine cipher;
    private AlgorithmParameters engineParam = null;
    private IESParameterSpec engineParams = null;
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private int state = -1;

    public static class IES extends CipherSpi {
        public IES() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest())));
        }
    }

    public static class OldIES extends CipherSpi {
        public OldIES() {
            super(new OldIESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest())));
        }
    }

    public CipherSpi(IESEngine engine) {
        this.cipher = engine;
    }

    /* access modifiers changed from: protected */
    public int engineGetBlockSize() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public byte[] engineGetIV() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int engineGetKeySize(Key key) {
        if (!(key instanceof IESKey)) {
            throw new IllegalArgumentException("must be passed IE key");
        }
        IESKey ieKey = (IESKey) key;
        if (ieKey.getPrivate() instanceof DHPrivateKey) {
            return ((DHPrivateKey) ieKey.getPrivate()).getX().bitLength();
        }
        if (ieKey.getPrivate() instanceof ECPrivateKey) {
            return ((ECPrivateKey) ieKey.getPrivate()).getD().bitLength();
        }
        throw new IllegalArgumentException("not an IE key!");
    }

    /* access modifiers changed from: protected */
    public int engineGetOutputSize(int inputLen) {
        if (this.state == 1 || this.state == 3) {
            return this.buffer.size() + inputLen + 20;
        }
        if (this.state == 2 || this.state == 4) {
            return (this.buffer.size() + inputLen) - 20;
        }
        throw new IllegalStateException("cipher not initialised");
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParam == null && this.engineParams != null) {
            try {
                this.engineParam = this.helper.createAlgorithmParameters("IES");
                this.engineParam.init(this.engineParams);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.engineParam;
    }

    /* access modifiers changed from: protected */
    public void engineSetMode(String mode) {
        throw new IllegalArgumentException("can't support mode " + mode);
    }

    /* access modifiers changed from: protected */
    public void engineSetPadding(String padding) throws NoSuchPaddingException {
        throw new NoSuchPaddingException(padding + " unavailable with RSA.");
    }

    /* access modifiers changed from: protected */
    public void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters pubKey;
        CipherParameters privKey;
        if (!(key instanceof IESKey)) {
            throw new InvalidKeyException("must be passed IES key");
        }
        if (params == null && (opmode == 1 || opmode == 3)) {
            byte[] d = new byte[16];
            byte[] e = new byte[16];
            if (random == null) {
                random = new SecureRandom();
            }
            random.nextBytes(d);
            random.nextBytes(e);
            params = new IESParameterSpec(d, e, 128);
        } else if (!(params instanceof IESParameterSpec)) {
            throw new InvalidAlgorithmParameterException("must be passed IES parameters");
        }
        IESKey ieKey = (IESKey) key;
        if (ieKey.getPublic() instanceof DHPublicKey) {
            pubKey = DHUtil.generatePublicKeyParameter(ieKey.getPublic());
            privKey = DHUtil.generatePrivateKeyParameter(ieKey.getPrivate());
        } else {
            pubKey = ECUtil.generatePublicKeyParameter(ieKey.getPublic());
            privKey = ECUtil.generatePrivateKeyParameter(ieKey.getPrivate());
        }
        this.engineParams = (IESParameterSpec) params;
        IESParameters p = new IESParameters(this.engineParams.getDerivationV(), this.engineParams.getEncodingV(), this.engineParams.getMacKeySize());
        this.state = opmode;
        this.buffer.reset();
        switch (opmode) {
            case 1:
            case 3:
                this.cipher.init(true, privKey, pubKey, p);
                return;
            case 2:
            case 4:
                this.cipher.init(false, privKey, pubKey, p);
                return;
            default:
                System.out.println("eeek!");
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec paramSpec = null;
        if (params != null) {
            int i = 0;
            while (i != this.availableSpecs.length) {
                try {
                    paramSpec = params.getParameterSpec(this.availableSpecs[i]);
                    break;
                } catch (Exception e) {
                    i++;
                }
            }
            if (paramSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + params.toString());
            }
        }
        this.engineParam = params;
        engineInit(opmode, key, paramSpec, random);
    }

    /* access modifiers changed from: protected */
    public void engineInit(int opmode, Key key, SecureRandom random) throws InvalidKeyException {
        if (opmode == 1 || opmode == 3) {
            try {
                engineInit(opmode, key, (AlgorithmParameterSpec) null, random);
                return;
            } catch (InvalidAlgorithmParameterException e) {
            }
        }
        throw new IllegalArgumentException("can't handle null parameter spec in IES");
    }

    /* access modifiers changed from: protected */
    public byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        this.buffer.write(input, inputOffset, inputLen);
        return null;
    }

    /* access modifiers changed from: protected */
    public int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) {
        this.buffer.write(input, inputOffset, inputLen);
        return 0;
    }

    /* access modifiers changed from: protected */
    public byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
        if (inputLen != 0) {
            this.buffer.write(input, inputOffset, inputLen);
        }
        try {
            byte[] buf = this.buffer.toByteArray();
            this.buffer.reset();
            return this.cipher.processBlock(buf, 0, buf.length);
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public int engineDoFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws IllegalBlockSizeException, BadPaddingException {
        if (inputLen != 0) {
            this.buffer.write(input, inputOffset, inputLen);
        }
        try {
            byte[] buf = this.buffer.toByteArray();
            this.buffer.reset();
            byte[] buf2 = this.cipher.processBlock(buf, 0, buf.length);
            System.arraycopy(buf2, 0, output, outputOffset, buf2.length);
            return buf2.length;
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        }
    }
}
