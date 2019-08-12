package org.spongycastle.crypto.prng.drbg;

import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.util.Arrays;

public class HMacSP800DRBG implements SP80090DRBG {
    private static final int MAX_BITS_REQUEST = 262144;
    private static final long RESEED_MAX = 140737488355328L;

    /* renamed from: _K */
    private byte[] f6881_K;

    /* renamed from: _V */
    private byte[] f6882_V;
    private EntropySource _entropySource;
    private Mac _hMac;
    private long _reseedCounter;
    private int _securityStrength;

    public HMacSP800DRBG(Mac hMac, int securityStrength, EntropySource entropySource, byte[] personalizationString, byte[] nonce) {
        if (securityStrength > Utils.getMaxSecurityStrength(hMac)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        } else if (entropySource.entropySize() < securityStrength) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        } else {
            this._securityStrength = securityStrength;
            this._entropySource = entropySource;
            this._hMac = hMac;
            byte[] seedMaterial = Arrays.concatenate(getEntropy(), nonce, personalizationString);
            this.f6881_K = new byte[hMac.getMacSize()];
            this.f6882_V = new byte[this.f6881_K.length];
            Arrays.fill(this.f6882_V, 1);
            hmac_DRBG_Update(seedMaterial);
            this._reseedCounter = 1;
        }
    }

    private void hmac_DRBG_Update(byte[] seedMaterial) {
        hmac_DRBG_Update_Func(seedMaterial, 0);
        if (seedMaterial != null) {
            hmac_DRBG_Update_Func(seedMaterial, 1);
        }
    }

    private void hmac_DRBG_Update_Func(byte[] seedMaterial, byte vValue) {
        this._hMac.init(new KeyParameter(this.f6881_K));
        this._hMac.update(this.f6882_V, 0, this.f6882_V.length);
        this._hMac.update(vValue);
        if (seedMaterial != null) {
            this._hMac.update(seedMaterial, 0, seedMaterial.length);
        }
        this._hMac.doFinal(this.f6881_K, 0);
        this._hMac.init(new KeyParameter(this.f6881_K));
        this._hMac.update(this.f6882_V, 0, this.f6882_V.length);
        this._hMac.doFinal(this.f6882_V, 0);
    }

    public int getBlockSize() {
        return this.f6882_V.length * 8;
    }

    public int generate(byte[] output, byte[] additionalInput, boolean predictionResistant) {
        int numberOfBits = output.length * 8;
        if (numberOfBits > 262144) {
            throw new IllegalArgumentException("Number of bits per request limited to 262144");
        } else if (this._reseedCounter > RESEED_MAX) {
            return -1;
        } else {
            if (predictionResistant) {
                reseed(additionalInput);
                additionalInput = null;
            }
            if (additionalInput != null) {
                hmac_DRBG_Update(additionalInput);
            }
            byte[] rv = new byte[output.length];
            int m = output.length / this.f6882_V.length;
            this._hMac.init(new KeyParameter(this.f6881_K));
            for (int i = 0; i < m; i++) {
                this._hMac.update(this.f6882_V, 0, this.f6882_V.length);
                this._hMac.doFinal(this.f6882_V, 0);
                System.arraycopy(this.f6882_V, 0, rv, this.f6882_V.length * i, this.f6882_V.length);
            }
            if (this.f6882_V.length * m < rv.length) {
                this._hMac.update(this.f6882_V, 0, this.f6882_V.length);
                this._hMac.doFinal(this.f6882_V, 0);
                System.arraycopy(this.f6882_V, 0, rv, this.f6882_V.length * m, rv.length - (this.f6882_V.length * m));
            }
            hmac_DRBG_Update(additionalInput);
            this._reseedCounter++;
            System.arraycopy(rv, 0, output, 0, output.length);
            return numberOfBits;
        }
    }

    public void reseed(byte[] additionalInput) {
        hmac_DRBG_Update(Arrays.concatenate(getEntropy(), additionalInput));
        this._reseedCounter = 1;
    }

    private byte[] getEntropy() {
        byte[] entropy = this._entropySource.getEntropy();
        if (entropy.length >= (this._securityStrength + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }
}
