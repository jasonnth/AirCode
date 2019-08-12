package org.spongycastle.crypto.prng.drbg;

import com.airbnb.android.core.intents.PaidAmenityIntents;
import java.util.Hashtable;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;

public class HashSP800DRBG implements SP80090DRBG {
    private static final int MAX_BITS_REQUEST = 262144;
    private static final byte[] ONE = {1};
    private static final long RESEED_MAX = 140737488355328L;
    private static final Hashtable seedlens = new Hashtable();

    /* renamed from: _C */
    private byte[] f6883_C;

    /* renamed from: _V */
    private byte[] f6884_V;
    private Digest _digest;
    private EntropySource _entropySource;
    private long _reseedCounter;
    private int _securityStrength;
    private int _seedLength;

    static {
        seedlens.put("SHA-1", Integers.valueOf(440));
        seedlens.put("SHA-224", Integers.valueOf(440));
        seedlens.put("SHA-256", Integers.valueOf(440));
        seedlens.put("SHA-512/256", Integers.valueOf(440));
        seedlens.put("SHA-512/224", Integers.valueOf(440));
        seedlens.put("SHA-384", Integers.valueOf(PaidAmenityIntents.ACTIVITY_HOST_AMENITY_LIST));
        seedlens.put("SHA-512", Integers.valueOf(PaidAmenityIntents.ACTIVITY_HOST_AMENITY_LIST));
    }

    public HashSP800DRBG(Digest digest, int securityStrength, EntropySource entropySource, byte[] personalizationString, byte[] nonce) {
        if (securityStrength > Utils.getMaxSecurityStrength(digest)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        } else if (entropySource.entropySize() < securityStrength) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        } else {
            this._digest = digest;
            this._entropySource = entropySource;
            this._securityStrength = securityStrength;
            this._seedLength = ((Integer) seedlens.get(digest.getAlgorithmName())).intValue();
            this.f6884_V = Utils.hash_df(this._digest, Arrays.concatenate(getEntropy(), nonce, personalizationString), this._seedLength);
            byte[] subV = new byte[(this.f6884_V.length + 1)];
            System.arraycopy(this.f6884_V, 0, subV, 1, this.f6884_V.length);
            this.f6883_C = Utils.hash_df(this._digest, subV, this._seedLength);
            this._reseedCounter = 1;
        }
    }

    public int getBlockSize() {
        return this._digest.getDigestSize() * 8;
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
                byte[] newInput = new byte[(this.f6884_V.length + 1 + additionalInput.length)];
                newInput[0] = 2;
                System.arraycopy(this.f6884_V, 0, newInput, 1, this.f6884_V.length);
                System.arraycopy(additionalInput, 0, newInput, this.f6884_V.length + 1, additionalInput.length);
                addTo(this.f6884_V, hash(newInput));
            }
            byte[] rv = hashgen(this.f6884_V, numberOfBits);
            byte[] subH = new byte[(this.f6884_V.length + 1)];
            System.arraycopy(this.f6884_V, 0, subH, 1, this.f6884_V.length);
            subH[0] = 3;
            addTo(this.f6884_V, hash(subH));
            addTo(this.f6884_V, this.f6883_C);
            addTo(this.f6884_V, new byte[]{(byte) ((int) (this._reseedCounter >> 24)), (byte) ((int) (this._reseedCounter >> 16)), (byte) ((int) (this._reseedCounter >> 8)), (byte) ((int) this._reseedCounter)});
            this._reseedCounter++;
            System.arraycopy(rv, 0, output, 0, output.length);
            return numberOfBits;
        }
    }

    private byte[] getEntropy() {
        byte[] entropy = this._entropySource.getEntropy();
        if (entropy.length >= (this._securityStrength + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    private void addTo(byte[] longer, byte[] shorter) {
        byte b;
        byte b2 = 0;
        for (int i = 1; i <= shorter.length; i++) {
            int res = (longer[longer.length - i] & 255) + (shorter[shorter.length - i] & 255) + b2;
            if (res > 255) {
                b2 = 1;
            } else {
                b2 = 0;
            }
            longer[longer.length - i] = (byte) res;
        }
        for (int i2 = shorter.length + 1; i2 <= longer.length; i2++) {
            int res2 = (longer[longer.length - i2] & 255) + b2;
            if (res2 > 255) {
                b = 1;
            } else {
                b = 0;
            }
            longer[longer.length - i2] = (byte) res2;
        }
    }

    public void reseed(byte[] additionalInput) {
        this.f6884_V = Utils.hash_df(this._digest, Arrays.concatenate(ONE, this.f6884_V, getEntropy(), additionalInput), this._seedLength);
        byte[] subV = new byte[(this.f6884_V.length + 1)];
        subV[0] = 0;
        System.arraycopy(this.f6884_V, 0, subV, 1, this.f6884_V.length);
        this.f6883_C = Utils.hash_df(this._digest, subV, this._seedLength);
        this._reseedCounter = 1;
    }

    private byte[] hash(byte[] input) {
        byte[] hash = new byte[this._digest.getDigestSize()];
        doHash(input, hash);
        return hash;
    }

    private void doHash(byte[] input, byte[] output) {
        this._digest.update(input, 0, input.length);
        this._digest.doFinal(output, 0);
    }

    private byte[] hashgen(byte[] input, int lengthInBits) {
        int m = (lengthInBits / 8) / this._digest.getDigestSize();
        byte[] data = new byte[input.length];
        System.arraycopy(input, 0, data, 0, input.length);
        byte[] W = new byte[(lengthInBits / 8)];
        byte[] dig = new byte[this._digest.getDigestSize()];
        for (int i = 0; i <= m; i++) {
            doHash(data, dig);
            System.arraycopy(dig, 0, W, dig.length * i, W.length - (dig.length * i) > dig.length ? dig.length : W.length - (dig.length * i));
            addTo(data, ONE);
        }
        return W;
    }
}
