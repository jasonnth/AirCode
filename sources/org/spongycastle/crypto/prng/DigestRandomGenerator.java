package org.spongycastle.crypto.prng;

import org.spongycastle.crypto.Digest;

public class DigestRandomGenerator implements RandomGenerator {
    private static long CYCLE_COUNT = 10;
    private Digest digest;
    private byte[] seed;
    private long seedCounter = 1;
    private byte[] state;
    private long stateCounter;

    public DigestRandomGenerator(Digest digest2) {
        this.digest = digest2;
        this.seed = new byte[digest2.getDigestSize()];
        this.state = new byte[digest2.getDigestSize()];
        this.stateCounter = 1;
    }

    public void addSeedMaterial(byte[] inSeed) {
        synchronized (this) {
            digestUpdate(inSeed);
            digestUpdate(this.seed);
            digestDoFinal(this.seed);
        }
    }

    public void addSeedMaterial(long rSeed) {
        synchronized (this) {
            digestAddCounter(rSeed);
            digestUpdate(this.seed);
            digestDoFinal(this.seed);
        }
    }

    public void nextBytes(byte[] bytes) {
        nextBytes(bytes, 0, bytes.length);
    }

    public void nextBytes(byte[] bytes, int start, int len) {
        int stateOff;
        synchronized (this) {
            int stateOff2 = 0;
            try {
                generateState();
                int end = start + len;
                int i = start;
                while (i != end) {
                    if (stateOff2 == this.state.length) {
                        generateState();
                        stateOff = 0;
                    } else {
                        stateOff = stateOff2;
                    }
                    try {
                        stateOff2 = stateOff + 1;
                        bytes[i] = this.state[stateOff];
                        i++;
                    } catch (Throwable th) {
                        th = th;
                        int i2 = stateOff;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    private void cycleSeed() {
        digestUpdate(this.seed);
        long j = this.seedCounter;
        this.seedCounter = 1 + j;
        digestAddCounter(j);
        digestDoFinal(this.seed);
    }

    private void generateState() {
        long j = this.stateCounter;
        this.stateCounter = 1 + j;
        digestAddCounter(j);
        digestUpdate(this.state);
        digestUpdate(this.seed);
        digestDoFinal(this.state);
        if (this.stateCounter % CYCLE_COUNT == 0) {
            cycleSeed();
        }
    }

    private void digestAddCounter(long seed2) {
        for (int i = 0; i != 8; i++) {
            this.digest.update((byte) ((int) seed2));
            seed2 >>>= 8;
        }
    }

    private void digestUpdate(byte[] inSeed) {
        this.digest.update(inSeed, 0, inSeed.length);
    }

    private void digestDoFinal(byte[] result) {
        this.digest.doFinal(result, 0);
    }
}
