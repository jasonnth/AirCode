package org.spongycastle.jcajce.provider.asymmetric.p330ec;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.KeyEncoder;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.agreement.ECDHBasicAgreement;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.IESEngine;
import org.spongycastle.crypto.engines.OldIESEngine;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.spongycastle.crypto.generators.KDF2BytesGenerator;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.IESWithCipherParameters;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.parsers.ECIESPublicKeyParser;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.IESUtil;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.interfaces.ECKey;
import org.spongycastle.jce.interfaces.IESKey;
import org.spongycastle.jce.spec.IESParameterSpec;
import org.spongycastle.util.Strings;

/* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher */
public class IESCipher extends CipherSpi {
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private boolean dhaesMode = false;
    private IESEngine engine;
    private AlgorithmParameters engineParam = null;
    private IESParameterSpec engineSpec = null;
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private int ivLength;
    private AsymmetricKeyParameter key;
    private AsymmetricKeyParameter otherKeyParameter = null;
    private SecureRandom random;
    private int state = -1;

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIES */
    public static class ECIES extends IESCipher {
        public ECIES() {
            super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest())));
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAES */
    public static class ECIESwithAES extends ECIESwithCipher {
        public ECIESwithAES() {
            super(new AESEngine());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAESCBC */
    public static class ECIESwithAESCBC extends ECIESwithCipher {
        public ECIESwithAESCBC() {
            super(new CBCBlockCipher(new AESEngine()), 16);
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithCipher */
    public static class ECIESwithCipher extends IESCipher {
        public ECIESwithCipher(BlockCipher cipher) {
            super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(cipher)));
        }

        public ECIESwithCipher(BlockCipher cipher, int ivLength) {
            super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(cipher)), ivLength);
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESede */
    public static class ECIESwithDESede extends ECIESwithCipher {
        public ECIESwithDESede() {
            super(new DESedeEngine());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESedeCBC */
    public static class ECIESwithDESedeCBC extends ECIESwithCipher {
        public ECIESwithDESedeCBC() {
            super(new CBCBlockCipher(new DESedeEngine()), 8);
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$OldECIES */
    public static class OldECIES extends IESCipher {
        public OldECIES() {
            super(new OldIESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest())));
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$OldECIESwithAES */
    public static class OldECIESwithAES extends OldECIESwithCipher {
        public OldECIESwithAES() {
            super(new AESEngine());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$OldECIESwithAESCBC */
    public static class OldECIESwithAESCBC extends OldECIESwithCipher {
        public OldECIESwithAESCBC() {
            super(new CBCBlockCipher(new AESEngine()), 16);
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$OldECIESwithCipher */
    public static class OldECIESwithCipher extends IESCipher {
        public OldECIESwithCipher(BlockCipher baseCipher) {
            super(new OldIESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(baseCipher)));
        }

        public OldECIESwithCipher(BlockCipher baseCipher, int ivLength) {
            super(new OldIESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(baseCipher)), ivLength);
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$OldECIESwithDESede */
    public static class OldECIESwithDESede extends OldECIESwithCipher {
        public OldECIESwithDESede() {
            super(new DESedeEngine());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$OldECIESwithDESedeCBC */
    public static class OldECIESwithDESedeCBC extends OldECIESwithCipher {
        public OldECIESwithDESedeCBC() {
            super(new CBCBlockCipher(new DESedeEngine()), 8);
        }
    }

    public IESCipher(IESEngine engine2) {
        this.engine = engine2;
        this.ivLength = 0;
    }

    public IESCipher(IESEngine engine2, int ivLength2) {
        this.engine = engine2;
        this.ivLength = ivLength2;
    }

    public int engineGetBlockSize() {
        if (this.engine.getCipher() != null) {
            return this.engine.getCipher().getBlockSize();
        }
        return 0;
    }

    public int engineGetKeySize(Key key2) {
        if (key2 instanceof ECKey) {
            return ((ECKey) key2).getParameters().getCurve().getFieldSize();
        }
        throw new IllegalArgumentException("not an EC key");
    }

    public byte[] engineGetIV() {
        return null;
    }

    public AlgorithmParameters engineGetParameters() {
        if (this.engineParam == null && this.engineSpec != null) {
            try {
                this.engineParam = this.helper.createAlgorithmParameters("IES");
                this.engineParam.init(this.engineSpec);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.engineParam;
    }

    public void engineSetMode(String mode) throws NoSuchAlgorithmException {
        String modeName = Strings.toUpperCase(mode);
        if (modeName.equals("NONE")) {
            this.dhaesMode = false;
        } else if (modeName.equals("DHAES")) {
            this.dhaesMode = true;
        } else {
            throw new IllegalArgumentException("can't support mode " + mode);
        }
    }

    public int engineGetOutputSize(int inputLen) {
        int len2;
        int len3;
        if (this.key == null) {
            throw new IllegalStateException("cipher not initialised");
        }
        int len1 = this.engine.getMac().getMacSize();
        if (this.otherKeyParameter == null) {
            len2 = (((((ECKeyParameters) this.key).getParameters().getCurve().getFieldSize() + 7) * 2) / 8) + 1;
        } else {
            len2 = 0;
        }
        if (this.engine.getCipher() == null) {
            len3 = inputLen;
        } else if (this.state == 1 || this.state == 3) {
            len3 = this.engine.getCipher().getOutputSize(inputLen);
        } else if (this.state == 2 || this.state == 4) {
            len3 = this.engine.getCipher().getOutputSize((inputLen - len1) - len2);
        } else {
            throw new IllegalStateException("cipher not initialised");
        }
        if (this.state == 1 || this.state == 3) {
            return this.buffer.size() + len1 + len2 + len3;
        }
        if (this.state == 2 || this.state == 4) {
            return ((this.buffer.size() - len1) - len2) + len3;
        }
        throw new IllegalStateException("cipher not initialised");
    }

    public void engineSetPadding(String padding) throws NoSuchPaddingException {
        String paddingName = Strings.toUpperCase(padding);
        if (!paddingName.equals("NOPADDING") && !paddingName.equals("PKCS5PADDING") && !paddingName.equals("PKCS7PADDING")) {
            throw new NoSuchPaddingException("padding not available with IESCipher");
        }
    }

    public void engineInit(int opmode, Key key2, AlgorithmParameters params, SecureRandom random2) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec paramSpec = null;
        if (params != null) {
            try {
                paramSpec = params.getParameterSpec(IESParameterSpec.class);
            } catch (Exception e) {
                throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + e.toString());
            }
        }
        this.engineParam = params;
        engineInit(opmode, key2, paramSpec, random2);
    }

    public void engineInit(int opmode, Key key2, AlgorithmParameterSpec engineSpec2, SecureRandom random2) throws InvalidAlgorithmParameterException, InvalidKeyException {
        this.otherKeyParameter = null;
        if (engineSpec2 == null) {
            this.engineSpec = IESUtil.guessParameterSpec(this.engine.getCipher());
        } else if (engineSpec2 instanceof IESParameterSpec) {
            this.engineSpec = (IESParameterSpec) engineSpec2;
        } else {
            throw new InvalidAlgorithmParameterException("must be passed IES parameters");
        }
        byte[] nonce = this.engineSpec.getNonce();
        if (nonce != null) {
            if (this.ivLength == 0) {
                throw new InvalidAlgorithmParameterException("NONCE present in IES Parameters when none required");
            } else if (nonce.length != this.ivLength) {
                throw new InvalidAlgorithmParameterException("NONCE in IES Parameters needs to be " + this.ivLength + " bytes long");
            }
        }
        if (opmode == 1 || opmode == 3) {
            if (key2 instanceof PublicKey) {
                this.key = ECUtil.generatePublicKeyParameter((PublicKey) key2);
            } else if (key2 instanceof IESKey) {
                IESKey ieKey = (IESKey) key2;
                this.key = ECUtil.generatePublicKeyParameter(ieKey.getPublic());
                this.otherKeyParameter = ECUtil.generatePrivateKeyParameter(ieKey.getPrivate());
            } else {
                throw new InvalidKeyException("must be passed recipient's public EC key for encryption");
            }
        } else if (opmode != 2 && opmode != 4) {
            throw new InvalidKeyException("must be passed EC key");
        } else if (key2 instanceof PrivateKey) {
            this.key = ECUtil.generatePrivateKeyParameter((PrivateKey) key2);
        } else if (key2 instanceof IESKey) {
            IESKey ieKey2 = (IESKey) key2;
            this.otherKeyParameter = ECUtil.generatePublicKeyParameter(ieKey2.getPublic());
            this.key = ECUtil.generatePrivateKeyParameter(ieKey2.getPrivate());
        } else {
            throw new InvalidKeyException("must be passed recipient's private EC key for decryption");
        }
        this.random = random2;
        this.state = opmode;
        this.buffer.reset();
    }

    public void engineInit(int opmode, Key key2, SecureRandom random2) throws InvalidKeyException {
        try {
            engineInit(opmode, key2, (AlgorithmParameterSpec) null, random2);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException("can't handle supplied parameter spec");
        }
    }

    public byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        this.buffer.write(input, inputOffset, inputLen);
        return null;
    }

    public int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) {
        this.buffer.write(input, inputOffset, inputLen);
        return 0;
    }

    public byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
        if (inputLen != 0) {
            this.buffer.write(input, inputOffset, inputLen);
        }
        byte[] in = this.buffer.toByteArray();
        this.buffer.reset();
        CipherParameters params = new IESWithCipherParameters(this.engineSpec.getDerivationV(), this.engineSpec.getEncodingV(), this.engineSpec.getMacKeySize(), this.engineSpec.getCipherKeySize());
        if (this.engineSpec.getNonce() != null) {
            params = new ParametersWithIV(params, this.engineSpec.getNonce());
        }
        ECDomainParameters ecParams = ((ECKeyParameters) this.key).getParameters();
        if (this.otherKeyParameter != null) {
            try {
                if (this.state == 1 || this.state == 3) {
                    this.engine.init(true, this.otherKeyParameter, this.key, params);
                } else {
                    this.engine.init(false, this.key, this.otherKeyParameter, params);
                }
                return this.engine.processBlock(in, 0, in.length);
            } catch (Exception e) {
                throw new BadPaddingException(e.getMessage());
            }
        } else if (this.state == 1 || this.state == 3) {
            ECKeyPairGenerator gen = new ECKeyPairGenerator();
            gen.init(new ECKeyGenerationParameters(ecParams, this.random));
            final boolean usePointCompression = this.engineSpec.getPointCompression();
            try {
                this.engine.init(this.key, params, new EphemeralKeyPairGenerator(gen, new KeyEncoder() {
                    public byte[] getEncoded(AsymmetricKeyParameter keyParameter) {
                        return ((ECPublicKeyParameters) keyParameter).getQ().getEncoded(usePointCompression);
                    }
                }));
                return this.engine.processBlock(in, 0, in.length);
            } catch (Exception e2) {
                throw new BadPaddingException(e2.getMessage());
            }
        } else if (this.state == 2 || this.state == 4) {
            try {
                this.engine.init(this.key, params, (KeyParser) new ECIESPublicKeyParser(ecParams));
                return this.engine.processBlock(in, 0, in.length);
            } catch (InvalidCipherTextException e3) {
                throw new BadPaddingException(e3.getMessage());
            }
        } else {
            throw new IllegalStateException("cipher not initialised");
        }
    }

    public int engineDoFinal(byte[] input, int inputOffset, int inputLength, byte[] output, int outputOffset) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        byte[] buf = engineDoFinal(input, inputOffset, inputLength);
        System.arraycopy(buf, 0, output, outputOffset, buf.length);
        return buf.length;
    }
}
