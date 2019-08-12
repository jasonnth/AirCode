package org.spongycastle.jce.provider;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.engines.DESEngine;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.TwofishEngine;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CFBBlockCipher;
import org.spongycastle.crypto.modes.CTSBlockCipher;
import org.spongycastle.crypto.modes.OFBBlockCipher;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Strings;

public class BrokenJCEBlockCipher implements BrokenPBE {
    private Class[] availableSpecs = {IvParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class};
    private BufferedBlockCipher cipher;
    private AlgorithmParameters engineParams = null;
    private int ivLength = 0;
    private ParametersWithIV ivParam;
    private int pbeHash = 1;
    private int pbeIvSize;
    private int pbeKeySize;
    private int pbeType = 2;

    public static class BrokePBEWithMD5AndDES extends BrokenJCEBlockCipher {
        public BrokePBEWithMD5AndDES() {
            CBCBlockCipher cBCBlockCipher = new CBCBlockCipher(new DESEngine());
            super(cBCBlockCipher, 0, 0, 64, 64);
        }
    }

    public static class BrokePBEWithSHA1AndDES extends BrokenJCEBlockCipher {
        public BrokePBEWithSHA1AndDES() {
            CBCBlockCipher cBCBlockCipher = new CBCBlockCipher(new DESEngine());
            super(cBCBlockCipher, 0, 1, 64, 64);
        }
    }

    public static class BrokePBEWithSHAAndDES2Key extends BrokenJCEBlockCipher {
        public BrokePBEWithSHAAndDES2Key() {
            CBCBlockCipher cBCBlockCipher = new CBCBlockCipher(new DESedeEngine());
            super(cBCBlockCipher, 2, 1, 128, 64);
        }
    }

    public static class BrokePBEWithSHAAndDES3Key extends BrokenJCEBlockCipher {
        public BrokePBEWithSHAAndDES3Key() {
            CBCBlockCipher cBCBlockCipher = new CBCBlockCipher(new DESedeEngine());
            super(cBCBlockCipher, 2, 1, 192, 64);
        }
    }

    public static class OldPBEWithSHAAndDES3Key extends BrokenJCEBlockCipher {
        public OldPBEWithSHAAndDES3Key() {
            CBCBlockCipher cBCBlockCipher = new CBCBlockCipher(new DESedeEngine());
            super(cBCBlockCipher, 3, 1, 192, 64);
        }
    }

    public static class OldPBEWithSHAAndTwofish extends BrokenJCEBlockCipher {
        public OldPBEWithSHAAndTwofish() {
            CBCBlockCipher cBCBlockCipher = new CBCBlockCipher(new TwofishEngine());
            super(cBCBlockCipher, 3, 1, 256, 128);
        }
    }

    protected BrokenJCEBlockCipher(BlockCipher engine) {
        this.cipher = new PaddedBufferedBlockCipher(engine);
    }

    protected BrokenJCEBlockCipher(BlockCipher engine, int pbeType2, int pbeHash2, int pbeKeySize2, int pbeIvSize2) {
        this.cipher = new PaddedBufferedBlockCipher(engine);
        this.pbeType = pbeType2;
        this.pbeHash = pbeHash2;
        this.pbeKeySize = pbeKeySize2;
        this.pbeIvSize = pbeIvSize2;
    }

    /* access modifiers changed from: protected */
    public int engineGetBlockSize() {
        return this.cipher.getBlockSize();
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
        return key.getEncoded().length;
    }

    /* access modifiers changed from: protected */
    public int engineGetOutputSize(int inputLen) {
        return this.cipher.getOutputSize(inputLen);
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null && this.ivParam != null) {
            String name = this.cipher.getUnderlyingCipher().getAlgorithmName();
            if (name.indexOf(47) >= 0) {
                name = name.substring(0, name.indexOf(47));
            }
            try {
                this.engineParams = AlgorithmParameters.getInstance(name, BouncyCastleProvider.PROVIDER_NAME);
                this.engineParams.init(this.ivParam.getIV());
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.engineParams;
    }

    /* access modifiers changed from: protected */
    public void engineSetMode(String mode) {
        String modeName = Strings.toUpperCase(mode);
        if (modeName.equals("ECB")) {
            this.ivLength = 0;
            this.cipher = new PaddedBufferedBlockCipher(this.cipher.getUnderlyingCipher());
        } else if (modeName.equals("CBC")) {
            this.ivLength = this.cipher.getUnderlyingCipher().getBlockSize();
            this.cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(this.cipher.getUnderlyingCipher()));
        } else if (modeName.startsWith("OFB")) {
            this.ivLength = this.cipher.getUnderlyingCipher().getBlockSize();
            if (modeName.length() != 3) {
                this.cipher = new PaddedBufferedBlockCipher(new OFBBlockCipher(this.cipher.getUnderlyingCipher(), Integer.parseInt(modeName.substring(3))));
                return;
            }
            this.cipher = new PaddedBufferedBlockCipher(new OFBBlockCipher(this.cipher.getUnderlyingCipher(), this.cipher.getBlockSize() * 8));
        } else if (modeName.startsWith("CFB")) {
            this.ivLength = this.cipher.getUnderlyingCipher().getBlockSize();
            if (modeName.length() != 3) {
                this.cipher = new PaddedBufferedBlockCipher(new CFBBlockCipher(this.cipher.getUnderlyingCipher(), Integer.parseInt(modeName.substring(3))));
                return;
            }
            this.cipher = new PaddedBufferedBlockCipher(new CFBBlockCipher(this.cipher.getUnderlyingCipher(), this.cipher.getBlockSize() * 8));
        } else {
            throw new IllegalArgumentException("can't support mode " + mode);
        }
    }

    /* access modifiers changed from: protected */
    public void engineSetPadding(String padding) throws NoSuchPaddingException {
        String paddingName = Strings.toUpperCase(padding);
        if (paddingName.equals("NOPADDING")) {
            this.cipher = new BufferedBlockCipher(this.cipher.getUnderlyingCipher());
        } else if (paddingName.equals("PKCS5PADDING") || paddingName.equals("PKCS7PADDING") || paddingName.equals("ISO10126PADDING")) {
            this.cipher = new PaddedBufferedBlockCipher(this.cipher.getUnderlyingCipher());
        } else if (paddingName.equals("WITHCTS")) {
            this.cipher = new CTSBlockCipher(this.cipher.getUnderlyingCipher());
        } else {
            throw new NoSuchPaddingException("Padding " + padding + " unknown.");
        }
    }

    /* JADX WARNING: type inference failed for: r10v1 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void engineInit(int r14, java.security.Key r15, java.security.spec.AlgorithmParameterSpec r16, java.security.SecureRandom r17) throws java.security.InvalidKeyException, java.security.InvalidAlgorithmParameterException {
        /*
            r13 = this;
            boolean r1 = r15 instanceof org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey
            if (r1 == 0) goto L_0x005d
            r1 = r15
            org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey r1 = (org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey) r1
            int r3 = r13.pbeType
            int r4 = r13.pbeHash
            org.spongycastle.crypto.BufferedBlockCipher r2 = r13.cipher
            org.spongycastle.crypto.BlockCipher r2 = r2.getUnderlyingCipher()
            java.lang.String r5 = r2.getAlgorithmName()
            int r6 = r13.pbeKeySize
            int r7 = r13.pbeIvSize
            r2 = r16
            org.spongycastle.crypto.CipherParameters r9 = org.spongycastle.jce.provider.BrokenPBE.Util.makePBEParameters(r1, r2, r3, r4, r5, r6, r7)
            int r1 = r13.pbeIvSize
            if (r1 == 0) goto L_0x0028
            r1 = r9
            org.spongycastle.crypto.params.ParametersWithIV r1 = (org.spongycastle.crypto.params.ParametersWithIV) r1
            r13.ivParam = r1
        L_0x0028:
            int r1 = r13.ivLength
            if (r1 == 0) goto L_0x0051
            boolean r1 = r9 instanceof org.spongycastle.crypto.params.ParametersWithIV
            if (r1 != 0) goto L_0x0051
            if (r17 != 0) goto L_0x0037
            java.security.SecureRandom r17 = new java.security.SecureRandom
            r17.<init>()
        L_0x0037:
            r1 = 1
            if (r14 == r1) goto L_0x003d
            r1 = 3
            if (r14 != r1) goto L_0x0117
        L_0x003d:
            int r1 = r13.ivLength
            byte[] r8 = new byte[r1]
            r0 = r17
            r0.nextBytes(r8)
            org.spongycastle.crypto.params.ParametersWithIV r10 = new org.spongycastle.crypto.params.ParametersWithIV
            r10.<init>(r9, r8)
            r1 = r10
            org.spongycastle.crypto.params.ParametersWithIV r1 = (org.spongycastle.crypto.params.ParametersWithIV) r1
            r13.ivParam = r1
            r9 = r10
        L_0x0051:
            switch(r14) {
                case 1: goto L_0x0120;
                case 2: goto L_0x0128;
                case 3: goto L_0x0120;
                case 4: goto L_0x0128;
                default: goto L_0x0054;
            }
        L_0x0054:
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.String r2 = "eeek!"
            r1.println(r2)
        L_0x005c:
            return
        L_0x005d:
            if (r16 != 0) goto L_0x0069
            org.spongycastle.crypto.params.KeyParameter r9 = new org.spongycastle.crypto.params.KeyParameter
            byte[] r1 = r15.getEncoded()
            r9.<init>(r1)
            goto L_0x0028
        L_0x0069:
            r0 = r16
            boolean r1 = r0 instanceof javax.crypto.spec.IvParameterSpec
            if (r1 == 0) goto L_0x0097
            int r1 = r13.ivLength
            if (r1 == 0) goto L_0x008d
            org.spongycastle.crypto.params.ParametersWithIV r9 = new org.spongycastle.crypto.params.ParametersWithIV
            org.spongycastle.crypto.params.KeyParameter r1 = new org.spongycastle.crypto.params.KeyParameter
            byte[] r2 = r15.getEncoded()
            r1.<init>(r2)
            javax.crypto.spec.IvParameterSpec r16 = (javax.crypto.spec.IvParameterSpec) r16
            byte[] r2 = r16.getIV()
            r9.<init>(r1, r2)
            r1 = r9
            org.spongycastle.crypto.params.ParametersWithIV r1 = (org.spongycastle.crypto.params.ParametersWithIV) r1
            r13.ivParam = r1
            goto L_0x0028
        L_0x008d:
            org.spongycastle.crypto.params.KeyParameter r9 = new org.spongycastle.crypto.params.KeyParameter
            byte[] r1 = r15.getEncoded()
            r9.<init>(r1)
            goto L_0x0028
        L_0x0097:
            r0 = r16
            boolean r1 = r0 instanceof javax.crypto.spec.RC2ParameterSpec
            if (r1 == 0) goto L_0x00cb
            r11 = r16
            javax.crypto.spec.RC2ParameterSpec r11 = (javax.crypto.spec.RC2ParameterSpec) r11
            org.spongycastle.crypto.params.RC2Parameters r9 = new org.spongycastle.crypto.params.RC2Parameters
            byte[] r1 = r15.getEncoded()
            javax.crypto.spec.RC2ParameterSpec r16 = (javax.crypto.spec.RC2ParameterSpec) r16
            int r2 = r16.getEffectiveKeyBits()
            r9.<init>(r1, r2)
            byte[] r1 = r11.getIV()
            if (r1 == 0) goto L_0x0028
            int r1 = r13.ivLength
            if (r1 == 0) goto L_0x0028
            org.spongycastle.crypto.params.ParametersWithIV r10 = new org.spongycastle.crypto.params.ParametersWithIV
            byte[] r1 = r11.getIV()
            r10.<init>(r9, r1)
            r1 = r10
            org.spongycastle.crypto.params.ParametersWithIV r1 = (org.spongycastle.crypto.params.ParametersWithIV) r1
            r13.ivParam = r1
        L_0x00c8:
            r9 = r10
            goto L_0x0028
        L_0x00cb:
            r0 = r16
            boolean r1 = r0 instanceof javax.crypto.spec.RC5ParameterSpec
            if (r1 == 0) goto L_0x010e
            r12 = r16
            javax.crypto.spec.RC5ParameterSpec r12 = (javax.crypto.spec.RC5ParameterSpec) r12
            org.spongycastle.crypto.params.RC5Parameters r9 = new org.spongycastle.crypto.params.RC5Parameters
            byte[] r1 = r15.getEncoded()
            javax.crypto.spec.RC5ParameterSpec r16 = (javax.crypto.spec.RC5ParameterSpec) r16
            int r2 = r16.getRounds()
            r9.<init>(r1, r2)
            int r1 = r12.getWordSize()
            r2 = 32
            if (r1 == r2) goto L_0x00f5
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "can only accept RC5 word size 32 (at the moment...)"
            r1.<init>(r2)
            throw r1
        L_0x00f5:
            byte[] r1 = r12.getIV()
            if (r1 == 0) goto L_0x0028
            int r1 = r13.ivLength
            if (r1 == 0) goto L_0x0028
            org.spongycastle.crypto.params.ParametersWithIV r10 = new org.spongycastle.crypto.params.ParametersWithIV
            byte[] r1 = r12.getIV()
            r10.<init>(r9, r1)
            r1 = r10
            org.spongycastle.crypto.params.ParametersWithIV r1 = (org.spongycastle.crypto.params.ParametersWithIV) r1
            r13.ivParam = r1
            goto L_0x00c8
        L_0x010e:
            java.security.InvalidAlgorithmParameterException r1 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "unknown parameter type."
            r1.<init>(r2)
            throw r1
        L_0x0117:
            java.security.InvalidAlgorithmParameterException r1 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r2 = "no IV set when one expected"
            r1.<init>(r2)
            throw r1
        L_0x0120:
            org.spongycastle.crypto.BufferedBlockCipher r1 = r13.cipher
            r2 = 1
            r1.init(r2, r9)
            goto L_0x005c
        L_0x0128:
            org.spongycastle.crypto.BufferedBlockCipher r1 = r13.cipher
            r2 = 0
            r1.init(r2, r9)
            goto L_0x005c
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.BrokenJCEBlockCipher.engineInit(int, java.security.Key, java.security.spec.AlgorithmParameterSpec, java.security.SecureRandom):void");
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
        this.engineParams = params;
        engineInit(opmode, key, paramSpec, random);
    }

    /* access modifiers changed from: protected */
    public void engineInit(int opmode, Key key, SecureRandom random) throws InvalidKeyException {
        try {
            engineInit(opmode, key, (AlgorithmParameterSpec) null, random);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        int length = this.cipher.getUpdateOutputSize(inputLen);
        if (length > 0) {
            byte[] out = new byte[length];
            this.cipher.processBytes(input, inputOffset, inputLen, out, 0);
            return out;
        }
        this.cipher.processBytes(input, inputOffset, inputLen, null, 0);
        return null;
    }

    /* access modifiers changed from: protected */
    public int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) {
        return this.cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
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
            byte[] out = new byte[len2];
            System.arraycopy(tmp, 0, out, 0, len2);
            return out;
        } catch (DataLengthException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        } catch (InvalidCipherTextException e2) {
            throw new BadPaddingException(e2.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public int engineDoFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws IllegalBlockSizeException, BadPaddingException {
        int len = 0;
        if (inputLen != 0) {
            len = this.cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
        }
        try {
            return this.cipher.doFinal(output, outputOffset + len) + len;
        } catch (DataLengthException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        } catch (InvalidCipherTextException e2) {
            throw new BadPaddingException(e2.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        byte[] encoded = key.getEncoded();
        if (encoded == null) {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
        try {
            return engineDoFinal(encoded, 0, encoded.length);
        } catch (BadPaddingException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm, int wrappedKeyType) throws InvalidKeyException {
        try {
            byte[] encoded = engineDoFinal(wrappedKey, 0, wrappedKey.length);
            if (wrappedKeyType == 3) {
                return new SecretKeySpec(encoded, wrappedKeyAlgorithm);
            }
            try {
                KeyFactory kf = KeyFactory.getInstance(wrappedKeyAlgorithm, BouncyCastleProvider.PROVIDER_NAME);
                if (wrappedKeyType == 1) {
                    return kf.generatePublic(new X509EncodedKeySpec(encoded));
                }
                if (wrappedKeyType == 2) {
                    return kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
                }
                throw new InvalidKeyException("Unknown key type " + wrappedKeyType);
            } catch (NoSuchProviderException e) {
                throw new InvalidKeyException("Unknown key type " + e.getMessage());
            } catch (NoSuchAlgorithmException e2) {
                throw new InvalidKeyException("Unknown key type " + e2.getMessage());
            } catch (InvalidKeySpecException e22) {
                throw new InvalidKeyException("Unknown key type " + e22.getMessage());
            }
        } catch (BadPaddingException e3) {
            throw new InvalidKeyException(e3.getMessage());
        } catch (IllegalBlockSizeException e23) {
            throw new InvalidKeyException(e23.getMessage());
        }
    }
}
