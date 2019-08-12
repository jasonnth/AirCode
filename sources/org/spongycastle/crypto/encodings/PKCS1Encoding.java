package org.spongycastle.crypto.encodings;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class PKCS1Encoding implements AsymmetricBlockCipher {
    private static final int HEADER_LENGTH = 10;
    public static final String NOT_STRICT_LENGTH_ENABLED_PROPERTY = "org.spongycastle.pkcs1.not_strict";
    public static final String STRICT_LENGTH_ENABLED_PROPERTY = "org.spongycastle.pkcs1.strict";
    private AsymmetricBlockCipher engine;
    private byte[] fallback = null;
    private boolean forEncryption;
    private boolean forPrivateKey;
    private int pLen = -1;
    private SecureRandom random;
    private boolean useStrictLength;

    public PKCS1Encoding(AsymmetricBlockCipher cipher) {
        this.engine = cipher;
        this.useStrictLength = useStrict();
    }

    public PKCS1Encoding(AsymmetricBlockCipher cipher, int pLen2) {
        this.engine = cipher;
        this.useStrictLength = useStrict();
        this.pLen = pLen2;
    }

    public PKCS1Encoding(AsymmetricBlockCipher cipher, byte[] fallback2) {
        this.engine = cipher;
        this.useStrictLength = useStrict();
        this.fallback = fallback2;
        this.pLen = fallback2.length;
    }

    private boolean useStrict() {
        boolean z = false;
        String strict = (String) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return System.getProperty(PKCS1Encoding.STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        String notStrict = (String) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return System.getProperty(PKCS1Encoding.NOT_STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        if (notStrict == null) {
            if (strict == null || strict.equals("true")) {
                z = true;
            }
            return z;
        } else if (!notStrict.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    public void init(boolean forEncryption2, CipherParameters param) {
        AsymmetricKeyParameter kParam;
        if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.random = rParam.getRandom();
            kParam = (AsymmetricKeyParameter) rParam.getParameters();
        } else {
            kParam = (AsymmetricKeyParameter) param;
            if (!kParam.isPrivate() && forEncryption2) {
                this.random = new SecureRandom();
            }
        }
        this.engine.init(forEncryption2, param);
        this.forPrivateKey = kParam.isPrivate();
        this.forEncryption = forEncryption2;
    }

    public int getInputBlockSize() {
        int baseBlockSize = this.engine.getInputBlockSize();
        if (this.forEncryption) {
            return baseBlockSize - 10;
        }
        return baseBlockSize;
    }

    public int getOutputBlockSize() {
        int baseBlockSize = this.engine.getOutputBlockSize();
        return this.forEncryption ? baseBlockSize : baseBlockSize - 10;
    }

    public byte[] processBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        if (this.forEncryption) {
            return encodeBlock(in, inOff, inLen);
        }
        return decodeBlock(in, inOff, inLen);
    }

    private byte[] encodeBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        if (inLen > getInputBlockSize()) {
            throw new IllegalArgumentException("input data too large");
        }
        byte[] block = new byte[this.engine.getInputBlockSize()];
        if (this.forPrivateKey) {
            block[0] = 1;
            for (int i = 1; i != (block.length - inLen) - 1; i++) {
                block[i] = -1;
            }
        } else {
            this.random.nextBytes(block);
            block[0] = 2;
            for (int i2 = 1; i2 != (block.length - inLen) - 1; i2++) {
                while (block[i2] == 0) {
                    block[i2] = (byte) this.random.nextInt();
                }
            }
        }
        block[(block.length - inLen) - 1] = 0;
        System.arraycopy(in, inOff, block, block.length - inLen, inLen);
        return this.engine.processBlock(block, 0, block.length);
    }

    private static int checkPkcs1Encoding(byte[] encoded, int pLen2) {
        int correct = 0 | (encoded[0] ^ 2);
        int plen = encoded.length - (pLen2 + 1);
        for (int i = 1; i < plen; i++) {
            byte tmp = encoded[i];
            int tmp2 = tmp | (tmp >> 1);
            int tmp3 = tmp2 | (tmp2 >> 2);
            correct |= ((tmp3 | (tmp3 >> 4)) & 1) - 1;
        }
        int correct2 = correct | encoded[encoded.length - (pLen2 + 1)];
        int correct3 = correct2 | (correct2 >> 1);
        int correct4 = correct3 | (correct3 >> 2);
        return (((correct4 | (correct4 >> 4)) & 1) - 1) ^ -1;
    }

    private byte[] decodeBlockOrRandom(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        byte[] random2;
        if (!this.forPrivateKey) {
            throw new InvalidCipherTextException("sorry, this method is only for decryption, not for signing");
        }
        byte[] block = this.engine.processBlock(in, inOff, inLen);
        if (this.fallback == null) {
            random2 = new byte[this.pLen];
            this.random.nextBytes(random2);
        } else {
            random2 = this.fallback;
        }
        if (block.length < getOutputBlockSize()) {
            throw new InvalidCipherTextException("block truncated");
        } else if (!this.useStrictLength || block.length == this.engine.getOutputBlockSize()) {
            int correct = checkPkcs1Encoding(block, this.pLen);
            byte[] result = new byte[this.pLen];
            for (int i = 0; i < this.pLen; i++) {
                result[i] = (byte) ((block[(block.length - this.pLen) + i] & (correct ^ -1)) | (random2[i] & correct));
            }
            return result;
        } else {
            throw new InvalidCipherTextException("block incorrect size");
        }
    }

    private byte[] decodeBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        if (this.pLen != -1) {
            return decodeBlockOrRandom(in, inOff, inLen);
        }
        byte[] block = this.engine.processBlock(in, inOff, inLen);
        if (block.length < getOutputBlockSize()) {
            throw new InvalidCipherTextException("block truncated");
        }
        byte type = block[0];
        if (this.forPrivateKey) {
            if (type != 2) {
                throw new InvalidCipherTextException("unknown block type");
            }
        } else if (type != 1) {
            throw new InvalidCipherTextException("unknown block type");
        }
        if (!this.useStrictLength || block.length == this.engine.getOutputBlockSize()) {
            int start = 1;
            while (start != block.length) {
                byte pad = block[start];
                if (pad == 0) {
                    break;
                } else if (type != 1 || pad == -1) {
                    start++;
                } else {
                    throw new InvalidCipherTextException("block padding incorrect");
                }
            }
            int start2 = start + 1;
            if (start2 > block.length || start2 < 10) {
                throw new InvalidCipherTextException("no data in block");
            }
            byte[] result = new byte[(block.length - start2)];
            System.arraycopy(block, start2, result, 0, result.length);
            return result;
        }
        throw new InvalidCipherTextException("block incorrect size");
    }
}
