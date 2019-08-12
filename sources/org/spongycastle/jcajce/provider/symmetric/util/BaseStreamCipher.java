package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.jcajce.PKCS12Key;
import org.spongycastle.jcajce.PKCS12KeyWithParameters;
import org.spongycastle.jcajce.provider.symmetric.util.PBE.Util;

public class BaseStreamCipher extends BaseWrapCipher implements PBE {
    private Class[] availableSpecs;
    private StreamCipher cipher;
    private int digest;
    private int ivLength;
    private ParametersWithIV ivParam;
    private int keySizeInBits;
    private String pbeAlgorithm;
    private PBEParameterSpec pbeSpec;

    protected BaseStreamCipher(StreamCipher engine, int ivLength2) {
        this(engine, ivLength2, -1, -1);
    }

    protected BaseStreamCipher(StreamCipher engine, int ivLength2, int keySizeInBits2, int digest2) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.ivLength = 0;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.cipher = engine;
        this.ivLength = ivLength2;
        this.keySizeInBits = keySizeInBits2;
        this.digest = digest2;
    }

    /* access modifiers changed from: protected */
    public int engineGetBlockSize() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public byte[] engineGetIV() {
        if (this.ivParam != null) {
            return this.ivParam.getIV();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    /* access modifiers changed from: protected */
    public int engineGetOutputSize(int inputLen) {
        return inputLen;
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams != null || this.pbeSpec == null) {
            return this.engineParams;
        }
        try {
            AlgorithmParameters engineParams = createParametersInstance(this.pbeAlgorithm);
            engineParams.init(this.pbeSpec);
            return engineParams;
        } catch (Exception e) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void engineSetMode(String mode) {
        if (!mode.equalsIgnoreCase("ECB")) {
            throw new IllegalArgumentException("can't support mode " + mode);
        }
    }

    /* access modifiers changed from: protected */
    public void engineSetPadding(String padding) throws NoSuchPaddingException {
        if (!padding.equalsIgnoreCase("NoPadding")) {
            throw new NoSuchPaddingException("Padding " + padding + " unknown.");
        }
    }

    /* access modifiers changed from: protected */
    public void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters param;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.engineParams = null;
        if (!(key instanceof SecretKey)) {
            throw new InvalidKeyException("Key for algorithm " + key.getAlgorithm() + " not suitable for symmetric enryption.");
        }
        if (key instanceof PKCS12Key) {
            PKCS12Key k = (PKCS12Key) key;
            this.pbeSpec = (PBEParameterSpec) params;
            if ((k instanceof PKCS12KeyWithParameters) && this.pbeSpec == null) {
                this.pbeSpec = new PBEParameterSpec(((PKCS12KeyWithParameters) k).getSalt(), ((PKCS12KeyWithParameters) k).getIterationCount());
            }
            param = Util.makePBEParameters(k.getEncoded(), 2, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
        } else if (key instanceof BCPBEKey) {
            BCPBEKey k2 = (BCPBEKey) key;
            if (k2.getOID() != null) {
                this.pbeAlgorithm = k2.getOID().getId();
            } else {
                this.pbeAlgorithm = k2.getAlgorithm();
            }
            if (k2.getParam() != null) {
                param = k2.getParam();
                this.pbeSpec = new PBEParameterSpec(k2.getSalt(), k2.getIterationCount());
            } else if (params instanceof PBEParameterSpec) {
                param = Util.makePBEParameters(k2, params, this.cipher.getAlgorithmName());
                this.pbeSpec = (PBEParameterSpec) params;
            } else {
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
            }
            if (k2.getIvSize() != 0) {
                this.ivParam = (ParametersWithIV) param;
            }
        } else if (params == null) {
            if (this.digest > 0) {
                throw new InvalidKeyException("Algorithm requires a PBE key");
            }
            param = new KeyParameter(key.getEncoded());
        } else if (params instanceof IvParameterSpec) {
            param = new ParametersWithIV(new KeyParameter(key.getEncoded()), ((IvParameterSpec) params).getIV());
            this.ivParam = (ParametersWithIV) param;
        } else {
            throw new InvalidAlgorithmParameterException("unknown parameter type.");
        }
        if (this.ivLength != 0 && !(param instanceof ParametersWithIV)) {
            SecureRandom ivRandom = random;
            if (ivRandom == null) {
                ivRandom = new SecureRandom();
            }
            if (opmode == 1 || opmode == 3) {
                byte[] iv = new byte[this.ivLength];
                ivRandom.nextBytes(iv);
                ParametersWithIV parametersWithIV = new ParametersWithIV(param, iv);
                this.ivParam = parametersWithIV;
                param = parametersWithIV;
            } else {
                throw new InvalidAlgorithmParameterException("no IV set when one expected");
            }
        }
        switch (opmode) {
            case 1:
            case 3:
                this.cipher.init(true, param);
                return;
            case 2:
            case 4:
                this.cipher.init(false, param);
                return;
            default:
                try {
                    throw new InvalidParameterException("unknown opmode " + opmode + " passed");
                } catch (Exception e) {
                    throw new InvalidKeyException(e.getMessage());
                }
        }
        throw new InvalidKeyException(e.getMessage());
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
        engineInit(opmode, key, paramSpec, random);
        this.engineParams = params;
    }

    /* access modifiers changed from: protected */
    public void engineInit(int opmode, Key key, SecureRandom random) throws InvalidKeyException {
        try {
            engineInit(opmode, key, (AlgorithmParameterSpec) null, random);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        byte[] out = new byte[inputLen];
        this.cipher.processBytes(input, inputOffset, inputLen, out, 0);
        return out;
    }

    /* access modifiers changed from: protected */
    public int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException {
        if (outputOffset + inputLen > output.length) {
            throw new ShortBufferException("output buffer too short for input.");
        }
        try {
            this.cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
            return inputLen;
        } catch (DataLengthException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen) {
        if (inputLen != 0) {
            byte[] out = engineUpdate(input, inputOffset, inputLen);
            this.cipher.reset();
            return out;
        }
        this.cipher.reset();
        return new byte[0];
    }

    /* access modifiers changed from: protected */
    public int engineDoFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException {
        if (outputOffset + inputLen > output.length) {
            throw new ShortBufferException("output buffer too short for input.");
        }
        if (inputLen != 0) {
            this.cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
        }
        this.cipher.reset();
        return inputLen;
    }
}
