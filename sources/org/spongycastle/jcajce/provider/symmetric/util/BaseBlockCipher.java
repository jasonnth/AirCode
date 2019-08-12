package org.spongycastle.jcajce.provider.symmetric.util;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import org.spongycastle.asn1.cms.GCMParameters;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CCMBlockCipher;
import org.spongycastle.crypto.modes.CFBBlockCipher;
import org.spongycastle.crypto.modes.CTSBlockCipher;
import org.spongycastle.crypto.modes.EAXBlockCipher;
import org.spongycastle.crypto.modes.GCFBBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.modes.GOFBBlockCipher;
import org.spongycastle.crypto.modes.OCBBlockCipher;
import org.spongycastle.crypto.modes.OFBBlockCipher;
import org.spongycastle.crypto.modes.OpenPGPCFBBlockCipher;
import org.spongycastle.crypto.modes.PGPCFBBlockCipher;
import org.spongycastle.crypto.modes.SICBlockCipher;
import org.spongycastle.crypto.paddings.BlockCipherPadding;
import org.spongycastle.crypto.paddings.ISO10126d2Padding;
import org.spongycastle.crypto.paddings.ISO7816d4Padding;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.paddings.TBCPadding;
import org.spongycastle.crypto.paddings.X923Padding;
import org.spongycastle.crypto.paddings.ZeroBytePadding;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.jcajce.spec.GOST28147ParameterSpec;
import org.spongycastle.util.Strings;

public class BaseBlockCipher extends BaseWrapCipher implements PBE {
    private static final Class gcmSpecClass = lookup("javax.crypto.spec.GCMParameterSpec");
    private AEADParameters aeadParams;
    private Class[] availableSpecs = {RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, IvParameterSpec.class, PBEParameterSpec.class, GOST28147ParameterSpec.class};
    private BlockCipher baseEngine;
    private GenericBlockCipher cipher;
    private int digest;
    private BlockCipherProvider engineProvider;
    private boolean fixedIv = true;
    private int ivLength = 0;
    private ParametersWithIV ivParam;
    private int keySizeInBits;
    private String modeName = null;
    private boolean padded;
    private String pbeAlgorithm = null;
    private PBEParameterSpec pbeSpec = null;
    private int scheme = -1;

    private static class AEADGenericBlockCipher implements GenericBlockCipher {
        private static final Constructor aeadBadTagConstructor;
        private AEADBlockCipher cipher;

        static {
            Class aeadBadTagClass = BaseBlockCipher.lookup("javax.crypto.AEADBadTagException");
            if (aeadBadTagClass != null) {
                aeadBadTagConstructor = findExceptionConstructor(aeadBadTagClass);
            } else {
                aeadBadTagConstructor = null;
            }
        }

        private static Constructor findExceptionConstructor(Class clazz) {
            try {
                return clazz.getConstructor(new Class[]{String.class});
            } catch (Exception e) {
                return null;
            }
        }

        AEADGenericBlockCipher(AEADBlockCipher cipher2) {
            this.cipher = cipher2;
        }

        public void init(boolean forEncryption, CipherParameters params) throws IllegalArgumentException {
            this.cipher.init(forEncryption, params);
        }

        public String getAlgorithmName() {
            return this.cipher.getUnderlyingCipher().getAlgorithmName();
        }

        public boolean wrapOnNoPadding() {
            return false;
        }

        public BlockCipher getUnderlyingCipher() {
            return this.cipher.getUnderlyingCipher();
        }

        public int getOutputSize(int len) {
            return this.cipher.getOutputSize(len);
        }

        public int getUpdateOutputSize(int len) {
            return this.cipher.getUpdateOutputSize(len);
        }

        public void updateAAD(byte[] input, int offset, int length) {
            this.cipher.processAADBytes(input, offset, length);
        }

        public int processByte(byte in, byte[] out, int outOff) throws DataLengthException {
            return this.cipher.processByte(in, out, outOff);
        }

        public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) throws DataLengthException {
            return this.cipher.processBytes(in, inOff, len, out, outOff);
        }

        public int doFinal(byte[] out, int outOff) throws IllegalStateException, BadPaddingException {
            try {
                return this.cipher.doFinal(out, outOff);
            } catch (InvalidCipherTextException e) {
                if (aeadBadTagConstructor != null) {
                    BadPaddingException aeadBadTag = null;
                    try {
                        aeadBadTag = (BadPaddingException) aeadBadTagConstructor.newInstance(new Object[]{e.getMessage()});
                    } catch (Exception e2) {
                    }
                    if (aeadBadTag != null) {
                        throw aeadBadTag;
                    }
                }
                throw new BadPaddingException(e.getMessage());
            }
        }
    }

    private static class BufferedGenericBlockCipher implements GenericBlockCipher {
        private BufferedBlockCipher cipher;

        BufferedGenericBlockCipher(BufferedBlockCipher cipher2) {
            this.cipher = cipher2;
        }

        BufferedGenericBlockCipher(BlockCipher cipher2) {
            this.cipher = new PaddedBufferedBlockCipher(cipher2);
        }

        BufferedGenericBlockCipher(BlockCipher cipher2, BlockCipherPadding padding) {
            this.cipher = new PaddedBufferedBlockCipher(cipher2, padding);
        }

        public void init(boolean forEncryption, CipherParameters params) throws IllegalArgumentException {
            this.cipher.init(forEncryption, params);
        }

        public boolean wrapOnNoPadding() {
            return !(this.cipher instanceof CTSBlockCipher);
        }

        public String getAlgorithmName() {
            return this.cipher.getUnderlyingCipher().getAlgorithmName();
        }

        public BlockCipher getUnderlyingCipher() {
            return this.cipher.getUnderlyingCipher();
        }

        public int getOutputSize(int len) {
            return this.cipher.getOutputSize(len);
        }

        public int getUpdateOutputSize(int len) {
            return this.cipher.getUpdateOutputSize(len);
        }

        public void updateAAD(byte[] input, int offset, int length) {
            throw new UnsupportedOperationException("AAD is not supported in the current mode.");
        }

        public int processByte(byte in, byte[] out, int outOff) throws DataLengthException {
            return this.cipher.processByte(in, out, outOff);
        }

        public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) throws DataLengthException {
            return this.cipher.processBytes(in, inOff, len, out, outOff);
        }

        public int doFinal(byte[] out, int outOff) throws IllegalStateException, BadPaddingException {
            try {
                return this.cipher.doFinal(out, outOff);
            } catch (InvalidCipherTextException e) {
                throw new BadPaddingException(e.getMessage());
            }
        }
    }

    private interface GenericBlockCipher {
        int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException;

        String getAlgorithmName();

        int getOutputSize(int i);

        BlockCipher getUnderlyingCipher();

        int getUpdateOutputSize(int i);

        void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

        int processByte(byte b, byte[] bArr, int i) throws DataLengthException;

        int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException;

        void updateAAD(byte[] bArr, int i, int i2);

        boolean wrapOnNoPadding();
    }

    /* access modifiers changed from: private */
    public static Class lookup(String className) {
        try {
            return BaseBlockCipher.class.getClassLoader().loadClass(className);
        } catch (Exception e) {
            return null;
        }
    }

    protected BaseBlockCipher(BlockCipher engine) {
        this.baseEngine = engine;
        this.cipher = new BufferedGenericBlockCipher(engine);
    }

    protected BaseBlockCipher(BlockCipher engine, int scheme2, int digest2, int keySizeInBits2, int ivLength2) {
        this.baseEngine = engine;
        this.scheme = scheme2;
        this.digest = digest2;
        this.keySizeInBits = keySizeInBits2;
        this.ivLength = ivLength2;
        this.cipher = new BufferedGenericBlockCipher(engine);
    }

    protected BaseBlockCipher(BlockCipherProvider provider) {
        this.baseEngine = provider.get();
        this.engineProvider = provider;
        this.cipher = new BufferedGenericBlockCipher(provider.get());
    }

    protected BaseBlockCipher(AEADBlockCipher engine) {
        this.baseEngine = engine.getUnderlyingCipher();
        this.ivLength = this.baseEngine.getBlockSize();
        this.cipher = new AEADGenericBlockCipher(engine);
    }

    protected BaseBlockCipher(AEADBlockCipher engine, boolean fixedIv2, int ivLength2) {
        this.baseEngine = engine.getUnderlyingCipher();
        this.fixedIv = fixedIv2;
        this.ivLength = ivLength2;
        this.cipher = new AEADGenericBlockCipher(engine);
    }

    protected BaseBlockCipher(BlockCipher engine, int ivLength2) {
        this.baseEngine = engine;
        this.cipher = new BufferedGenericBlockCipher(engine);
        this.ivLength = ivLength2 / 8;
    }

    protected BaseBlockCipher(BufferedBlockCipher engine, int ivLength2) {
        this.baseEngine = engine.getUnderlyingCipher();
        this.cipher = new BufferedGenericBlockCipher(engine);
        this.ivLength = ivLength2 / 8;
    }

    /* access modifiers changed from: protected */
    public int engineGetBlockSize() {
        return this.baseEngine.getBlockSize();
    }

    /* access modifiers changed from: protected */
    public byte[] engineGetIV() {
        if (this.aeadParams != null) {
            return this.aeadParams.getNonce();
        }
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
        return this.cipher.getOutputSize(inputLen);
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null) {
            if (this.pbeSpec != null) {
                try {
                    this.engineParams = createParametersInstance(this.pbeAlgorithm);
                    this.engineParams.init(this.pbeSpec);
                } catch (Exception e) {
                    return null;
                }
            } else if (this.ivParam != null) {
                String name = this.cipher.getUnderlyingCipher().getAlgorithmName();
                if (name.indexOf(47) >= 0) {
                    name = name.substring(0, name.indexOf(47));
                }
                try {
                    this.engineParams = createParametersInstance(name);
                    this.engineParams.init(this.ivParam.getIV());
                } catch (Exception e2) {
                    throw new RuntimeException(e2.toString());
                }
            } else if (this.aeadParams != null) {
                try {
                    this.engineParams = createParametersInstance("GCM");
                    this.engineParams.init(new GCMParameters(this.aeadParams.getNonce(), this.aeadParams.getMacSize() / 8).getEncoded());
                } catch (Exception e3) {
                    throw new RuntimeException(e3.toString());
                }
            }
        }
        return this.engineParams;
    }

    /* access modifiers changed from: protected */
    public void engineSetMode(String mode) throws NoSuchAlgorithmException {
        this.modeName = Strings.toUpperCase(mode);
        if (this.modeName.equals("ECB")) {
            this.ivLength = 0;
            this.cipher = new BufferedGenericBlockCipher(this.baseEngine);
        } else if (this.modeName.equals("CBC")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher((BlockCipher) new CBCBlockCipher(this.baseEngine));
        } else if (this.modeName.startsWith("OFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.modeName.length() != 3) {
                this.cipher = new BufferedGenericBlockCipher((BlockCipher) new OFBBlockCipher(this.baseEngine, Integer.parseInt(this.modeName.substring(3))));
                return;
            }
            this.cipher = new BufferedGenericBlockCipher((BlockCipher) new OFBBlockCipher(this.baseEngine, this.baseEngine.getBlockSize() * 8));
        } else if (this.modeName.startsWith("CFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.modeName.length() != 3) {
                this.cipher = new BufferedGenericBlockCipher((BlockCipher) new CFBBlockCipher(this.baseEngine, Integer.parseInt(this.modeName.substring(3))));
                return;
            }
            this.cipher = new BufferedGenericBlockCipher((BlockCipher) new CFBBlockCipher(this.baseEngine, this.baseEngine.getBlockSize() * 8));
        } else if (this.modeName.startsWith("PGP")) {
            boolean inlineIV = this.modeName.equalsIgnoreCase("PGPCFBwithIV");
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher((BlockCipher) new PGPCFBBlockCipher(this.baseEngine, inlineIV));
        } else if (this.modeName.equalsIgnoreCase("OpenPGPCFB")) {
            this.ivLength = 0;
            this.cipher = new BufferedGenericBlockCipher((BlockCipher) new OpenPGPCFBBlockCipher(this.baseEngine));
        } else if (this.modeName.startsWith("SIC")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.ivLength < 16) {
                throw new IllegalArgumentException("Warning: SIC-Mode can become a twotime-pad if the blocksize of the cipher is too small. Use a cipher with a block size of at least 128 bits (e.g. AES)");
            }
            this.fixedIv = false;
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(this.baseEngine)));
        } else if (this.modeName.startsWith("CTR")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.fixedIv = false;
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(this.baseEngine)));
        } else if (this.modeName.startsWith("GOFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GOFBBlockCipher(this.baseEngine)));
        } else if (this.modeName.startsWith("GCFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GCFBBlockCipher(this.baseEngine)));
        } else if (this.modeName.startsWith("CTS")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher((BufferedBlockCipher) new CTSBlockCipher(new CBCBlockCipher(this.baseEngine)));
        } else if (this.modeName.startsWith("CCM")) {
            this.ivLength = 13;
            this.cipher = new AEADGenericBlockCipher(new CCMBlockCipher(this.baseEngine));
        } else if (this.modeName.startsWith("OCB")) {
            if (this.engineProvider != null) {
                this.ivLength = 15;
                this.cipher = new AEADGenericBlockCipher(new OCBBlockCipher(this.baseEngine, this.engineProvider.get()));
                return;
            }
            throw new NoSuchAlgorithmException("can't support mode " + mode);
        } else if (this.modeName.startsWith("EAX")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new AEADGenericBlockCipher(new EAXBlockCipher(this.baseEngine));
        } else if (this.modeName.startsWith("GCM")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new AEADGenericBlockCipher(new GCMBlockCipher(this.baseEngine));
        } else {
            throw new NoSuchAlgorithmException("can't support mode " + mode);
        }
    }

    /* access modifiers changed from: protected */
    public void engineSetPadding(String padding) throws NoSuchPaddingException {
        String paddingName = Strings.toUpperCase(padding);
        if (paddingName.equals("NOPADDING")) {
            if (this.cipher.wrapOnNoPadding()) {
                this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(this.cipher.getUnderlyingCipher()));
            }
        } else if (paddingName.equals("WITHCTS")) {
            this.cipher = new BufferedGenericBlockCipher((BufferedBlockCipher) new CTSBlockCipher(this.cipher.getUnderlyingCipher()));
        } else {
            this.padded = true;
            if (isAEADModeName(this.modeName)) {
                throw new NoSuchPaddingException("Only NoPadding can be used with AEAD modes.");
            } else if (paddingName.equals("PKCS5PADDING") || paddingName.equals("PKCS7PADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher());
            } else if (paddingName.equals("ZEROBYTEPADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ZeroBytePadding());
            } else if (paddingName.equals("ISO10126PADDING") || paddingName.equals("ISO10126-2PADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO10126d2Padding());
            } else if (paddingName.equals("X9.23PADDING") || paddingName.equals("X923PADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new X923Padding());
            } else if (paddingName.equals("ISO7816-4PADDING") || paddingName.equals("ISO9797-1PADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO7816d4Padding());
            } else if (paddingName.equals("TBCPADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new TBCPadding());
            } else {
                throw new NoSuchPaddingException("Padding " + padding + " unknown.");
            }
        }
    }

    /* JADX WARNING: type inference failed for: r17v0 */
    /* JADX WARNING: type inference failed for: r0v14 */
    /* JADX WARNING: type inference failed for: r0v15 */
    /* JADX WARNING: type inference failed for: r0v19, types: [org.spongycastle.crypto.params.AEADParameters] */
    /* JADX WARNING: type inference failed for: r0v20, types: [org.spongycastle.crypto.params.AEADParameters] */
    /* JADX WARNING: type inference failed for: r0v22, types: [org.spongycastle.crypto.params.RC5Parameters] */
    /* JADX WARNING: type inference failed for: r0v26 */
    /* JADX WARNING: type inference failed for: r0v27, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARNING: type inference failed for: r1v2, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r17v4 */
    /* JADX WARNING: type inference failed for: r17v5 */
    /* JADX WARNING: type inference failed for: r3v45 */
    /* JADX WARNING: type inference failed for: r0v29, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARNING: type inference failed for: r17v7 */
    /* JADX WARNING: type inference failed for: r0v31, types: [org.spongycastle.crypto.params.RC2Parameters] */
    /* JADX WARNING: type inference failed for: r0v33 */
    /* JADX WARNING: type inference failed for: r0v34, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARNING: type inference failed for: r1v3, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r17v9 */
    /* JADX WARNING: type inference failed for: r17v10 */
    /* JADX WARNING: type inference failed for: r3v60 */
    /* JADX WARNING: type inference failed for: r0v36, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARNING: type inference failed for: r17v12 */
    /* JADX WARNING: type inference failed for: r0v37, types: [org.spongycastle.crypto.params.ParametersWithSBox] */
    /* JADX WARNING: type inference failed for: r0v39 */
    /* JADX WARNING: type inference failed for: r0v40, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARNING: type inference failed for: r1v4, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r17v14 */
    /* JADX WARNING: type inference failed for: r17v15 */
    /* JADX WARNING: type inference failed for: r3v68 */
    /* JADX WARNING: type inference failed for: r0v42, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARNING: type inference failed for: r17v17 */
    /* JADX WARNING: type inference failed for: r17v18 */
    /* JADX WARNING: type inference failed for: r18v6 */
    /* JADX WARNING: type inference failed for: r18v7 */
    /* JADX WARNING: type inference failed for: r17v19 */
    /* JADX WARNING: type inference failed for: r17v20 */
    /* JADX WARNING: type inference failed for: r0v45, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r0v47, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r0v51, types: [org.spongycastle.crypto.params.ParametersWithRandom] */
    /* JADX WARNING: type inference failed for: r1v5, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r0v52 */
    /* JADX WARNING: type inference failed for: r0v53 */
    /* JADX WARNING: type inference failed for: r1v6, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r0v64 */
    /* JADX WARNING: type inference failed for: r0v65, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARNING: type inference failed for: r1v7, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r17v22 */
    /* JADX WARNING: type inference failed for: r17v23 */
    /* JADX WARNING: type inference failed for: r3v98 */
    /* JADX WARNING: type inference failed for: r0v67, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARNING: type inference failed for: r17v25 */
    /* JADX WARNING: type inference failed for: r17v26, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r17v27 */
    /* JADX WARNING: type inference failed for: r0v81 */
    /* JADX WARNING: type inference failed for: r3v112 */
    /* JADX WARNING: type inference failed for: r17v28, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r17v29, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r17v30 */
    /* JADX WARNING: type inference failed for: r0v100, types: [org.spongycastle.crypto.params.KeyParameter] */
    /* JADX WARNING: type inference failed for: r17v32, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r0v108 */
    /* JADX WARNING: type inference failed for: r3v146 */
    /* JADX WARNING: type inference failed for: r17v33, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r17v34 */
    /* JADX WARNING: type inference failed for: r0v117 */
    /* JADX WARNING: type inference failed for: r3v161 */
    /* JADX WARNING: type inference failed for: r17v35, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r17v36, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r0v127 */
    /* JADX WARNING: type inference failed for: r3v170 */
    /* JADX WARNING: type inference failed for: r0v133, types: [org.spongycastle.crypto.params.AEADParameters] */
    /* JADX WARNING: type inference failed for: r17v37 */
    /* JADX WARNING: type inference failed for: r17v38 */
    /* JADX WARNING: type inference failed for: r17v39 */
    /* JADX WARNING: type inference failed for: r0v134, types: [org.spongycastle.crypto.params.ParametersWithRandom] */
    /* JADX WARNING: type inference failed for: r17v40 */
    /* JADX WARNING: type inference failed for: r17v41 */
    /* JADX WARNING: type inference failed for: r17v42 */
    /* JADX WARNING: type inference failed for: r17v43 */
    /* JADX WARNING: type inference failed for: r17v44 */
    /* JADX WARNING: type inference failed for: r0v135, types: [org.spongycastle.crypto.params.KeyParameter] */
    /* JADX WARNING: type inference failed for: r17v45 */
    /* JADX WARNING: type inference failed for: r17v46 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r17v0, names: [param], types: [org.spongycastle.crypto.CipherParameters]
      assigns: [org.spongycastle.crypto.params.ParametersWithSBox, org.spongycastle.crypto.params.RC2Parameters, org.spongycastle.crypto.params.RC5Parameters, org.spongycastle.crypto.params.AEADParameters]
      uses: [?[OBJECT, ARRAY], org.spongycastle.crypto.params.ParametersWithSBox, org.spongycastle.crypto.params.RC2Parameters, org.spongycastle.crypto.params.RC5Parameters, org.spongycastle.crypto.params.AEADParameters]
      mth insns count: 612
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0367 A[SYNTHETIC, Splitter:B:132:0x0367] */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x062a  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x0635 A[Catch:{ Exception -> 0x038a }] */
    /* JADX WARNING: Unknown variable types count: 55 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void engineInit(int r23, java.security.Key r24, java.security.spec.AlgorithmParameterSpec r25, java.security.SecureRandom r26) throws java.security.InvalidKeyException, java.security.InvalidAlgorithmParameterException {
        /*
            r22 = this;
            r3 = 0
            r0 = r22
            r0.pbeSpec = r3
            r3 = 0
            r0 = r22
            r0.pbeAlgorithm = r3
            r3 = 0
            r0 = r22
            r0.engineParams = r3
            r3 = 0
            r0 = r22
            r0.aeadParams = r3
            r0 = r24
            boolean r3 = r0 instanceof javax.crypto.SecretKey
            if (r3 != 0) goto L_0x003f
            java.security.InvalidKeyException r3 = new java.security.InvalidKeyException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Key for algorithm "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = r24.getAlgorithm()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " not suitable for symmetric enryption."
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x003f:
            if (r25 != 0) goto L_0x005b
            r0 = r22
            org.spongycastle.crypto.BlockCipher r3 = r0.baseEngine
            java.lang.String r3 = r3.getAlgorithmName()
            java.lang.String r4 = "RC5-64"
            boolean r3 = r3.startsWith(r4)
            if (r3 == 0) goto L_0x005b
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "RC5 requires an RC5ParametersSpec to be passed in."
            r3.<init>(r4)
            throw r3
        L_0x005b:
            r0 = r22
            int r3 = r0.scheme
            r4 = 2
            if (r3 == r4) goto L_0x0068
            r0 = r24
            boolean r3 = r0 instanceof org.spongycastle.jcajce.PKCS12Key
            if (r3 == 0) goto L_0x0176
        L_0x0068:
            r0 = r24
            javax.crypto.SecretKey r0 = (javax.crypto.SecretKey) r0     // Catch:{ Exception -> 0x00af }
            r14 = r0
            r0 = r25
            boolean r3 = r0 instanceof javax.crypto.spec.PBEParameterSpec
            if (r3 == 0) goto L_0x007b
            r3 = r25
            javax.crypto.spec.PBEParameterSpec r3 = (javax.crypto.spec.PBEParameterSpec) r3
            r0 = r22
            r0.pbeSpec = r3
        L_0x007b:
            boolean r3 = r14 instanceof javax.crypto.interfaces.PBEKey
            if (r3 == 0) goto L_0x009c
            r0 = r22
            javax.crypto.spec.PBEParameterSpec r3 = r0.pbeSpec
            if (r3 != 0) goto L_0x009c
            javax.crypto.spec.PBEParameterSpec r4 = new javax.crypto.spec.PBEParameterSpec
            r3 = r14
            javax.crypto.interfaces.PBEKey r3 = (javax.crypto.interfaces.PBEKey) r3
            byte[] r5 = r3.getSalt()
            r3 = r14
            javax.crypto.interfaces.PBEKey r3 = (javax.crypto.interfaces.PBEKey) r3
            int r3 = r3.getIterationCount()
            r4.<init>(r5, r3)
            r0 = r22
            r0.pbeSpec = r4
        L_0x009c:
            r0 = r22
            javax.crypto.spec.PBEParameterSpec r3 = r0.pbeSpec
            if (r3 != 0) goto L_0x00b9
            boolean r3 = r14 instanceof javax.crypto.interfaces.PBEKey
            if (r3 != 0) goto L_0x00b9
            java.security.InvalidKeyException r3 = new java.security.InvalidKeyException
            java.lang.String r4 = "Algorithm requires a PBE key"
            r3.<init>(r4)
            throw r3
        L_0x00af:
            r10 = move-exception
            java.security.InvalidKeyException r3 = new java.security.InvalidKeyException
            java.lang.String r4 = "PKCS12 requires a SecretKey/PBEKey"
            r3.<init>(r4)
            throw r3
        L_0x00b9:
            r0 = r24
            boolean r3 = r0 instanceof org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey
            if (r3 == 0) goto L_0x0151
            r3 = r24
            org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey r3 = (org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey) r3
            org.spongycastle.crypto.CipherParameters r3 = r3.getParam()
            if (r3 == 0) goto L_0x012d
            r3 = r24
            org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey r3 = (org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey) r3
            org.spongycastle.crypto.CipherParameters r17 = r3.getParam()
        L_0x00d1:
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x00df
            r3 = r17
            org.spongycastle.crypto.params.ParametersWithIV r3 = (org.spongycastle.crypto.params.ParametersWithIV) r3
            r0 = r22
            r0.ivParam = r3
        L_0x00df:
            r0 = r25
            boolean r3 = r0 instanceof javax.crypto.spec.IvParameterSpec
            if (r3 == 0) goto L_0x03c4
            r0 = r22
            int r3 = r0.ivLength
            if (r3 == 0) goto L_0x03a8
            r16 = r25
            javax.crypto.spec.IvParameterSpec r16 = (javax.crypto.spec.IvParameterSpec) r16
            byte[] r3 = r16.getIV()
            int r3 = r3.length
            r0 = r22
            int r4 = r0.ivLength
            if (r3 == r4) goto L_0x02f1
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r3 = r0.cipher
            boolean r3 = r3 instanceof org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.AEADGenericBlockCipher
            if (r3 != 0) goto L_0x02f1
            r0 = r22
            boolean r3 = r0.fixedIv
            if (r3 == 0) goto L_0x02f1
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "IV must be "
            java.lang.StringBuilder r4 = r4.append(r5)
            r0 = r22
            int r5 = r0.ivLength
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " bytes long."
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x012d:
            byte[] r3 = r14.getEncoded()
            r4 = 2
            r0 = r22
            int r5 = r0.digest
            r0 = r22
            int r6 = r0.keySizeInBits
            r0 = r22
            int r7 = r0.ivLength
            int r7 = r7 * 8
            r0 = r22
            javax.crypto.spec.PBEParameterSpec r8 = r0.pbeSpec
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r9 = r0.cipher
            java.lang.String r9 = r9.getAlgorithmName()
            org.spongycastle.crypto.CipherParameters r17 = org.spongycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r3, r4, r5, r6, r7, r8, r9)
            goto L_0x00d1
        L_0x0151:
            byte[] r3 = r14.getEncoded()
            r4 = 2
            r0 = r22
            int r5 = r0.digest
            r0 = r22
            int r6 = r0.keySizeInBits
            r0 = r22
            int r7 = r0.ivLength
            int r7 = r7 * 8
            r0 = r22
            javax.crypto.spec.PBEParameterSpec r8 = r0.pbeSpec
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r9 = r0.cipher
            java.lang.String r9 = r9.getAlgorithmName()
            org.spongycastle.crypto.CipherParameters r17 = org.spongycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r3, r4, r5, r6, r7, r8, r9)
            goto L_0x00d1
        L_0x0176:
            r0 = r24
            boolean r3 = r0 instanceof org.spongycastle.jcajce.PBKDF1Key
            if (r3 == 0) goto L_0x01e2
            r14 = r24
            org.spongycastle.jcajce.PBKDF1Key r14 = (org.spongycastle.jcajce.PBKDF1Key) r14
            r0 = r25
            boolean r3 = r0 instanceof javax.crypto.spec.PBEParameterSpec
            if (r3 == 0) goto L_0x018e
            r3 = r25
            javax.crypto.spec.PBEParameterSpec r3 = (javax.crypto.spec.PBEParameterSpec) r3
            r0 = r22
            r0.pbeSpec = r3
        L_0x018e:
            boolean r3 = r14 instanceof org.spongycastle.jcajce.PBKDF1KeyWithParameters
            if (r3 == 0) goto L_0x01af
            r0 = r22
            javax.crypto.spec.PBEParameterSpec r3 = r0.pbeSpec
            if (r3 != 0) goto L_0x01af
            javax.crypto.spec.PBEParameterSpec r4 = new javax.crypto.spec.PBEParameterSpec
            r3 = r14
            org.spongycastle.jcajce.PBKDF1KeyWithParameters r3 = (org.spongycastle.jcajce.PBKDF1KeyWithParameters) r3
            byte[] r5 = r3.getSalt()
            r3 = r14
            org.spongycastle.jcajce.PBKDF1KeyWithParameters r3 = (org.spongycastle.jcajce.PBKDF1KeyWithParameters) r3
            int r3 = r3.getIterationCount()
            r4.<init>(r5, r3)
            r0 = r22
            r0.pbeSpec = r4
        L_0x01af:
            byte[] r3 = r14.getEncoded()
            r4 = 0
            r0 = r22
            int r5 = r0.digest
            r0 = r22
            int r6 = r0.keySizeInBits
            r0 = r22
            int r7 = r0.ivLength
            int r7 = r7 * 8
            r0 = r22
            javax.crypto.spec.PBEParameterSpec r8 = r0.pbeSpec
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r9 = r0.cipher
            java.lang.String r9 = r9.getAlgorithmName()
            org.spongycastle.crypto.CipherParameters r17 = org.spongycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r3, r4, r5, r6, r7, r8, r9)
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x00df
            r3 = r17
            org.spongycastle.crypto.params.ParametersWithIV r3 = (org.spongycastle.crypto.params.ParametersWithIV) r3
            r0 = r22
            r0.ivParam = r3
            goto L_0x00df
        L_0x01e2:
            r0 = r24
            boolean r3 = r0 instanceof org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey
            if (r3 == 0) goto L_0x0253
            r14 = r24
            org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey r14 = (org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey) r14
            org.spongycastle.asn1.ASN1ObjectIdentifier r3 = r14.getOID()
            if (r3 == 0) goto L_0x0220
            org.spongycastle.asn1.ASN1ObjectIdentifier r3 = r14.getOID()
            java.lang.String r3 = r3.getId()
            r0 = r22
            r0.pbeAlgorithm = r3
        L_0x01fe:
            org.spongycastle.crypto.CipherParameters r3 = r14.getParam()
            if (r3 == 0) goto L_0x0229
            org.spongycastle.crypto.CipherParameters r3 = r14.getParam()
            r0 = r22
            r1 = r25
            org.spongycastle.crypto.CipherParameters r17 = r0.adjustParameters(r1, r3)
        L_0x0210:
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x00df
            r3 = r17
            org.spongycastle.crypto.params.ParametersWithIV r3 = (org.spongycastle.crypto.params.ParametersWithIV) r3
            r0 = r22
            r0.ivParam = r3
            goto L_0x00df
        L_0x0220:
            java.lang.String r3 = r14.getAlgorithm()
            r0 = r22
            r0.pbeAlgorithm = r3
            goto L_0x01fe
        L_0x0229:
            r0 = r25
            boolean r3 = r0 instanceof javax.crypto.spec.PBEParameterSpec
            if (r3 == 0) goto L_0x024a
            r3 = r25
            javax.crypto.spec.PBEParameterSpec r3 = (javax.crypto.spec.PBEParameterSpec) r3
            r0 = r22
            r0.pbeSpec = r3
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r3 = r0.cipher
            org.spongycastle.crypto.BlockCipher r3 = r3.getUnderlyingCipher()
            java.lang.String r3 = r3.getAlgorithmName()
            r0 = r25
            org.spongycastle.crypto.CipherParameters r17 = org.spongycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r14, r0, r3)
            goto L_0x0210
        L_0x024a:
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "PBE requires PBE parameters to be set."
            r3.<init>(r4)
            throw r3
        L_0x0253:
            r0 = r24
            boolean r3 = r0 instanceof javax.crypto.interfaces.PBEKey
            if (r3 == 0) goto L_0x02b6
            r14 = r24
            javax.crypto.interfaces.PBEKey r14 = (javax.crypto.interfaces.PBEKey) r14
            r3 = r25
            javax.crypto.spec.PBEParameterSpec r3 = (javax.crypto.spec.PBEParameterSpec) r3
            r0 = r22
            r0.pbeSpec = r3
            boolean r3 = r14 instanceof org.spongycastle.jcajce.PKCS12KeyWithParameters
            if (r3 == 0) goto L_0x0280
            r0 = r22
            javax.crypto.spec.PBEParameterSpec r3 = r0.pbeSpec
            if (r3 != 0) goto L_0x0280
            javax.crypto.spec.PBEParameterSpec r3 = new javax.crypto.spec.PBEParameterSpec
            byte[] r4 = r14.getSalt()
            int r5 = r14.getIterationCount()
            r3.<init>(r4, r5)
            r0 = r22
            r0.pbeSpec = r3
        L_0x0280:
            byte[] r3 = r14.getEncoded()
            r0 = r22
            int r4 = r0.scheme
            r0 = r22
            int r5 = r0.digest
            r0 = r22
            int r6 = r0.keySizeInBits
            r0 = r22
            int r7 = r0.ivLength
            int r7 = r7 * 8
            r0 = r22
            javax.crypto.spec.PBEParameterSpec r8 = r0.pbeSpec
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r9 = r0.cipher
            java.lang.String r9 = r9.getAlgorithmName()
            org.spongycastle.crypto.CipherParameters r17 = org.spongycastle.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(r3, r4, r5, r6, r7, r8, r9)
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x00df
            r3 = r17
            org.spongycastle.crypto.params.ParametersWithIV r3 = (org.spongycastle.crypto.params.ParametersWithIV) r3
            r0 = r22
            r0.ivParam = r3
            goto L_0x00df
        L_0x02b6:
            r0 = r24
            boolean r3 = r0 instanceof org.spongycastle.jcajce.spec.RepeatedSecretKeySpec
            if (r3 != 0) goto L_0x02ed
            r0 = r22
            int r3 = r0.scheme
            if (r3 == 0) goto L_0x02d7
            r0 = r22
            int r3 = r0.scheme
            r4 = 4
            if (r3 == r4) goto L_0x02d7
            r0 = r22
            int r3 = r0.scheme
            r4 = 1
            if (r3 == r4) goto L_0x02d7
            r0 = r22
            int r3 = r0.scheme
            r4 = 5
            if (r3 != r4) goto L_0x02e0
        L_0x02d7:
            java.security.InvalidKeyException r3 = new java.security.InvalidKeyException
            java.lang.String r4 = "Algorithm requires a PBE key"
            r3.<init>(r4)
            throw r3
        L_0x02e0:
            org.spongycastle.crypto.params.KeyParameter r17 = new org.spongycastle.crypto.params.KeyParameter
            byte[] r3 = r24.getEncoded()
            r0 = r17
            r0.<init>(r3)
            goto L_0x00df
        L_0x02ed:
            r17 = 0
            goto L_0x00df
        L_0x02f1:
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x0397
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            org.spongycastle.crypto.params.ParametersWithIV r17 = (org.spongycastle.crypto.params.ParametersWithIV) r17
            org.spongycastle.crypto.CipherParameters r3 = r17.getParameters()
            byte[] r4 = r16.getIV()
            r0 = r18
            r0.<init>(r3, r4)
            r17 = r18
        L_0x030a:
            r3 = r17
            org.spongycastle.crypto.params.ParametersWithIV r3 = (org.spongycastle.crypto.params.ParametersWithIV) r3
            r0 = r22
            r0.ivParam = r3
        L_0x0312:
            r0 = r22
            int r3 = r0.ivLength
            if (r3 == 0) goto L_0x0644
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r3 != 0) goto L_0x0644
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.AEADParameters
            if (r3 != 0) goto L_0x0644
            r13 = r26
            if (r13 != 0) goto L_0x032d
            java.security.SecureRandom r13 = new java.security.SecureRandom
            r13.<init>()
        L_0x032d:
            r3 = 1
            r0 = r23
            if (r0 == r3) goto L_0x0337
            r3 = 3
            r0 = r23
            if (r0 != r3) goto L_0x060c
        L_0x0337:
            r0 = r22
            int r3 = r0.ivLength
            byte[] r12 = new byte[r3]
            r13.nextBytes(r12)
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            r0 = r18
            r1 = r17
            r0.<init>(r1, r12)
            r3 = r18
            org.spongycastle.crypto.params.ParametersWithIV r3 = (org.spongycastle.crypto.params.ParametersWithIV) r3
            r0 = r22
            r0.ivParam = r3
        L_0x0351:
            if (r26 == 0) goto L_0x0640
            r0 = r22
            boolean r3 = r0.padded
            if (r3 == 0) goto L_0x0640
            org.spongycastle.crypto.params.ParametersWithRandom r17 = new org.spongycastle.crypto.params.ParametersWithRandom
            r0 = r17
            r1 = r18
            r2 = r26
            r0.<init>(r1, r2)
        L_0x0364:
            switch(r23) {
                case 1: goto L_0x062a;
                case 2: goto L_0x0635;
                case 3: goto L_0x062a;
                case 4: goto L_0x0635;
                default: goto L_0x0367;
            }
        L_0x0367:
            java.security.InvalidParameterException r3 = new java.security.InvalidParameterException     // Catch:{ Exception -> 0x038a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x038a }
            r4.<init>()     // Catch:{ Exception -> 0x038a }
            java.lang.String r5 = "unknown opmode "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x038a }
            r0 = r23
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ Exception -> 0x038a }
            java.lang.String r5 = " passed"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x038a }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x038a }
            r3.<init>(r4)     // Catch:{ Exception -> 0x038a }
            throw r3     // Catch:{ Exception -> 0x038a }
        L_0x038a:
            r10 = move-exception
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$1 r3 = new org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$1
            java.lang.String r4 = r10.getMessage()
            r0 = r22
            r3.<init>(r4, r10)
            throw r3
        L_0x0397:
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            byte[] r3 = r16.getIV()
            r0 = r18
            r1 = r17
            r0.<init>(r1, r3)
            r17 = r18
            goto L_0x030a
        L_0x03a8:
            r0 = r22
            java.lang.String r3 = r0.modeName
            if (r3 == 0) goto L_0x0312
            r0 = r22
            java.lang.String r3 = r0.modeName
            java.lang.String r4 = "ECB"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0312
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "ECB mode does not use an IV"
            r3.<init>(r4)
            throw r3
        L_0x03c4:
            r0 = r25
            boolean r3 = r0 instanceof org.spongycastle.jcajce.spec.GOST28147ParameterSpec
            if (r3 == 0) goto L_0x0423
            r11 = r25
            org.spongycastle.jcajce.spec.GOST28147ParameterSpec r11 = (org.spongycastle.jcajce.spec.GOST28147ParameterSpec) r11
            org.spongycastle.crypto.params.ParametersWithSBox r17 = new org.spongycastle.crypto.params.ParametersWithSBox
            org.spongycastle.crypto.params.KeyParameter r3 = new org.spongycastle.crypto.params.KeyParameter
            byte[] r4 = r24.getEncoded()
            r3.<init>(r4)
            org.spongycastle.jcajce.spec.GOST28147ParameterSpec r25 = (org.spongycastle.jcajce.spec.GOST28147ParameterSpec) r25
            byte[] r4 = r25.getSbox()
            r0 = r17
            r0.<init>(r3, r4)
            byte[] r3 = r11.getIV()
            if (r3 == 0) goto L_0x0312
            r0 = r22
            int r3 = r0.ivLength
            if (r3 == 0) goto L_0x0312
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x0413
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            org.spongycastle.crypto.params.ParametersWithIV r17 = (org.spongycastle.crypto.params.ParametersWithIV) r17
            org.spongycastle.crypto.CipherParameters r3 = r17.getParameters()
            byte[] r4 = r11.getIV()
            r0 = r18
            r0.<init>(r3, r4)
            r17 = r18
        L_0x0409:
            r3 = r17
            org.spongycastle.crypto.params.ParametersWithIV r3 = (org.spongycastle.crypto.params.ParametersWithIV) r3
            r0 = r22
            r0.ivParam = r3
            goto L_0x0312
        L_0x0413:
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            byte[] r3 = r11.getIV()
            r0 = r18
            r1 = r17
            r0.<init>(r1, r3)
            r17 = r18
            goto L_0x0409
        L_0x0423:
            r0 = r25
            boolean r3 = r0 instanceof javax.crypto.spec.RC2ParameterSpec
            if (r3 == 0) goto L_0x047d
            r19 = r25
            javax.crypto.spec.RC2ParameterSpec r19 = (javax.crypto.spec.RC2ParameterSpec) r19
            org.spongycastle.crypto.params.RC2Parameters r17 = new org.spongycastle.crypto.params.RC2Parameters
            byte[] r3 = r24.getEncoded()
            javax.crypto.spec.RC2ParameterSpec r25 = (javax.crypto.spec.RC2ParameterSpec) r25
            int r4 = r25.getEffectiveKeyBits()
            r0 = r17
            r0.<init>(r3, r4)
            byte[] r3 = r19.getIV()
            if (r3 == 0) goto L_0x0312
            r0 = r22
            int r3 = r0.ivLength
            if (r3 == 0) goto L_0x0312
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x046d
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            org.spongycastle.crypto.params.ParametersWithIV r17 = (org.spongycastle.crypto.params.ParametersWithIV) r17
            org.spongycastle.crypto.CipherParameters r3 = r17.getParameters()
            byte[] r4 = r19.getIV()
            r0 = r18
            r0.<init>(r3, r4)
            r17 = r18
        L_0x0463:
            r3 = r17
            org.spongycastle.crypto.params.ParametersWithIV r3 = (org.spongycastle.crypto.params.ParametersWithIV) r3
            r0 = r22
            r0.ivParam = r3
            goto L_0x0312
        L_0x046d:
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            byte[] r3 = r19.getIV()
            r0 = r18
            r1 = r17
            r0.<init>(r1, r3)
            r17 = r18
            goto L_0x0463
        L_0x047d:
            r0 = r25
            boolean r3 = r0 instanceof javax.crypto.spec.RC5ParameterSpec
            if (r3 == 0) goto L_0x056d
            r20 = r25
            javax.crypto.spec.RC5ParameterSpec r20 = (javax.crypto.spec.RC5ParameterSpec) r20
            org.spongycastle.crypto.params.RC5Parameters r17 = new org.spongycastle.crypto.params.RC5Parameters
            byte[] r3 = r24.getEncoded()
            javax.crypto.spec.RC5ParameterSpec r25 = (javax.crypto.spec.RC5ParameterSpec) r25
            int r4 = r25.getRounds()
            r0 = r17
            r0.<init>(r3, r4)
            r0 = r22
            org.spongycastle.crypto.BlockCipher r3 = r0.baseEngine
            java.lang.String r3 = r3.getAlgorithmName()
            java.lang.String r4 = "RC5"
            boolean r3 = r3.startsWith(r4)
            if (r3 == 0) goto L_0x0525
            r0 = r22
            org.spongycastle.crypto.BlockCipher r3 = r0.baseEngine
            java.lang.String r3 = r3.getAlgorithmName()
            java.lang.String r4 = "RC5-32"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x04e7
            int r3 = r20.getWordSize()
            r4 = 32
            if (r3 == r4) goto L_0x052e
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "RC5 already set up for a word size of 32 not "
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = r20.getWordSize()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "."
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x04e7:
            r0 = r22
            org.spongycastle.crypto.BlockCipher r3 = r0.baseEngine
            java.lang.String r3 = r3.getAlgorithmName()
            java.lang.String r4 = "RC5-64"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x052e
            int r3 = r20.getWordSize()
            r4 = 64
            if (r3 == r4) goto L_0x052e
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "RC5 already set up for a word size of 64 not "
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = r20.getWordSize()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "."
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x0525:
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "RC5 parameters passed to a cipher that is not RC5."
            r3.<init>(r4)
            throw r3
        L_0x052e:
            byte[] r3 = r20.getIV()
            if (r3 == 0) goto L_0x0312
            r0 = r22
            int r3 = r0.ivLength
            if (r3 == 0) goto L_0x0312
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r3 == 0) goto L_0x055d
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            org.spongycastle.crypto.params.ParametersWithIV r17 = (org.spongycastle.crypto.params.ParametersWithIV) r17
            org.spongycastle.crypto.CipherParameters r3 = r17.getParameters()
            byte[] r4 = r20.getIV()
            r0 = r18
            r0.<init>(r3, r4)
            r17 = r18
        L_0x0553:
            r3 = r17
            org.spongycastle.crypto.params.ParametersWithIV r3 = (org.spongycastle.crypto.params.ParametersWithIV) r3
            r0 = r22
            r0.ivParam = r3
            goto L_0x0312
        L_0x055d:
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            byte[] r3 = r20.getIV()
            r0 = r18
            r1 = r17
            r0.<init>(r1, r3)
            r17 = r18
            goto L_0x0553
        L_0x056d:
            java.lang.Class r3 = gcmSpecClass
            if (r3 == 0) goto L_0x05fb
            java.lang.Class r3 = gcmSpecClass
            r0 = r25
            boolean r3 = r3.isInstance(r0)
            if (r3 == 0) goto L_0x05fb
            r0 = r22
            java.lang.String r3 = r0.modeName
            r0 = r22
            boolean r3 = r0.isAEADModeName(r3)
            if (r3 != 0) goto L_0x0598
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r3 = r0.cipher
            boolean r3 = r3 instanceof org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.AEADGenericBlockCipher
            if (r3 != 0) goto L_0x0598
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "GCMParameterSpec can only be used with AEAD modes."
            r3.<init>(r4)
            throw r3
        L_0x0598:
            java.lang.Class r3 = gcmSpecClass     // Catch:{ Exception -> 0x05f1 }
            java.lang.String r4 = "getTLen"
            r5 = 0
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch:{ Exception -> 0x05f1 }
            java.lang.reflect.Method r21 = r3.getDeclaredMethod(r4, r5)     // Catch:{ Exception -> 0x05f1 }
            java.lang.Class r3 = gcmSpecClass     // Catch:{ Exception -> 0x05f1 }
            java.lang.String r4 = "getIV"
            r5 = 0
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch:{ Exception -> 0x05f1 }
            java.lang.reflect.Method r12 = r3.getDeclaredMethod(r4, r5)     // Catch:{ Exception -> 0x05f1 }
            r0 = r17
            boolean r3 = r0 instanceof org.spongycastle.crypto.params.ParametersWithIV     // Catch:{ Exception -> 0x05f1 }
            if (r3 == 0) goto L_0x05eb
            org.spongycastle.crypto.params.ParametersWithIV r17 = (org.spongycastle.crypto.params.ParametersWithIV) r17     // Catch:{ Exception -> 0x05f1 }
            org.spongycastle.crypto.CipherParameters r15 = r17.getParameters()     // Catch:{ Exception -> 0x05f1 }
            org.spongycastle.crypto.params.KeyParameter r15 = (org.spongycastle.crypto.params.KeyParameter) r15     // Catch:{ Exception -> 0x05f1 }
        L_0x05be:
            org.spongycastle.crypto.params.AEADParameters r17 = new org.spongycastle.crypto.params.AEADParameters     // Catch:{ Exception -> 0x05f1 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x05f1 }
            r0 = r21
            r1 = r25
            java.lang.Object r3 = r0.invoke(r1, r3)     // Catch:{ Exception -> 0x05f1 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ Exception -> 0x05f1 }
            int r4 = r3.intValue()     // Catch:{ Exception -> 0x05f1 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x05f1 }
            r0 = r25
            java.lang.Object r3 = r12.invoke(r0, r3)     // Catch:{ Exception -> 0x05f1 }
            byte[] r3 = (byte[]) r3     // Catch:{ Exception -> 0x05f1 }
            byte[] r3 = (byte[]) r3     // Catch:{ Exception -> 0x05f1 }
            r0 = r17
            r0.<init>(r15, r4, r3)     // Catch:{ Exception -> 0x05f1 }
            r0 = r17
            r1 = r22
            r1.aeadParams = r0     // Catch:{ Exception -> 0x05f1 }
            goto L_0x0312
        L_0x05eb:
            r0 = r17
            org.spongycastle.crypto.params.KeyParameter r0 = (org.spongycastle.crypto.params.KeyParameter) r0     // Catch:{ Exception -> 0x05f1 }
            r15 = r0
            goto L_0x05be
        L_0x05f1:
            r10 = move-exception
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "Cannot process GCMParameterSpec."
            r3.<init>(r4)
            throw r3
        L_0x05fb:
            if (r25 == 0) goto L_0x0312
            r0 = r25
            boolean r3 = r0 instanceof javax.crypto.spec.PBEParameterSpec
            if (r3 != 0) goto L_0x0312
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "unknown parameter type."
            r3.<init>(r4)
            throw r3
        L_0x060c:
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r3 = r0.cipher
            org.spongycastle.crypto.BlockCipher r3 = r3.getUnderlyingCipher()
            java.lang.String r3 = r3.getAlgorithmName()
            java.lang.String r4 = "PGPCFB"
            int r3 = r3.indexOf(r4)
            if (r3 >= 0) goto L_0x0644
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "no IV set when one expected"
            r3.<init>(r4)
            throw r3
        L_0x062a:
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r3 = r0.cipher     // Catch:{ Exception -> 0x038a }
            r4 = 1
            r0 = r17
            r3.init(r4, r0)     // Catch:{ Exception -> 0x038a }
        L_0x0634:
            return
        L_0x0635:
            r0 = r22
            org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher r3 = r0.cipher     // Catch:{ Exception -> 0x038a }
            r4 = 0
            r0 = r17
            r3.init(r4, r0)     // Catch:{ Exception -> 0x038a }
            goto L_0x0634
        L_0x0640:
            r17 = r18
            goto L_0x0364
        L_0x0644:
            r18 = r17
            goto L_0x0351
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.engineInit(int, java.security.Key, java.security.spec.AlgorithmParameterSpec, java.security.SecureRandom):void");
    }

    private CipherParameters adjustParameters(AlgorithmParameterSpec params, CipherParameters param) {
        CipherParameters param2;
        if (param instanceof ParametersWithIV) {
            CipherParameters key = ((ParametersWithIV) param).getParameters();
            if (params instanceof IvParameterSpec) {
                this.ivParam = new ParametersWithIV(key, ((IvParameterSpec) params).getIV());
                return this.ivParam;
            } else if (!(params instanceof GOST28147ParameterSpec)) {
                return param;
            } else {
                GOST28147ParameterSpec gost28147Param = (GOST28147ParameterSpec) params;
                param2 = new ParametersWithSBox(param, gost28147Param.getSbox());
                if (!(gost28147Param.getIV() == null || this.ivLength == 0)) {
                    this.ivParam = new ParametersWithIV(key, gost28147Param.getIV());
                    return this.ivParam;
                }
            }
        } else if (params instanceof IvParameterSpec) {
            this.ivParam = new ParametersWithIV(param, ((IvParameterSpec) params).getIV());
            return this.ivParam;
        } else if (!(params instanceof GOST28147ParameterSpec)) {
            return param;
        } else {
            GOST28147ParameterSpec gost28147Param2 = (GOST28147ParameterSpec) params;
            param2 = new ParametersWithSBox(param, gost28147Param2.getSbox());
            if (!(gost28147Param2.getIV() == null || this.ivLength == 0)) {
                return new ParametersWithIV(param2, gost28147Param2.getIV());
            }
        }
        return param2;
    }

    /* access modifiers changed from: protected */
    public void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec paramSpec = null;
        if (params != null) {
            for (int i = 0; i != this.availableSpecs.length; i++) {
                if (this.availableSpecs[i] != null) {
                    try {
                        paramSpec = params.getParameterSpec(this.availableSpecs[i]);
                        break;
                    } catch (Exception e) {
                    }
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
    public void engineUpdateAAD(byte[] input, int offset, int length) {
        this.cipher.updateAAD(input, offset, length);
    }

    /* access modifiers changed from: protected */
    public void engineUpdateAAD(ByteBuffer bytebuffer) {
        engineUpdateAAD(bytebuffer.array(), bytebuffer.arrayOffset() + bytebuffer.position(), bytebuffer.limit() - bytebuffer.position());
    }

    /* access modifiers changed from: protected */
    public byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        int length = this.cipher.getUpdateOutputSize(inputLen);
        if (length > 0) {
            byte[] out = new byte[length];
            int len = this.cipher.processBytes(input, inputOffset, inputLen, out, 0);
            if (len == 0) {
                return null;
            }
            if (len == out.length) {
                return out;
            }
            byte[] tmp = new byte[len];
            System.arraycopy(out, 0, tmp, 0, len);
            return tmp;
        }
        this.cipher.processBytes(input, inputOffset, inputLen, null, 0);
        return null;
    }

    /* access modifiers changed from: protected */
    public int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException {
        if (this.cipher.getUpdateOutputSize(inputLen) + outputOffset > output.length) {
            throw new ShortBufferException("output buffer too short for input.");
        }
        try {
            return this.cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
        } catch (DataLengthException e) {
            throw new IllegalStateException(e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
        int len = 0;
        byte[] tmp = new byte[engineGetOutputSize(inputLen)];
        if (inputLen != 0) {
            len = this.cipher.processBytes(input, inputOffset, inputLen, tmp, 0);
        }
        try {
            int len2 = len + this.cipher.doFinal(tmp, len);
            if (len2 == tmp.length) {
                return tmp;
            }
            byte[] out = new byte[len2];
            System.arraycopy(tmp, 0, out, 0, len2);
            return out;
        } catch (DataLengthException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public int engineDoFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        int len = 0;
        if (engineGetOutputSize(inputLen) + outputOffset > output.length) {
            throw new ShortBufferException("output buffer too short for input.");
        }
        if (inputLen != 0) {
            try {
                len = this.cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
            } catch (OutputLengthException e) {
                throw new IllegalBlockSizeException(e.getMessage());
            } catch (DataLengthException e2) {
                throw new IllegalBlockSizeException(e2.getMessage());
            }
        }
        return this.cipher.doFinal(output, outputOffset + len) + len;
    }

    private boolean isAEADModeName(String modeName2) {
        return "CCM".equals(modeName2) || "EAX".equals(modeName2) || "GCM".equals(modeName2) || "OCB".equals(modeName2);
    }
}
