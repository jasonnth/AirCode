package org.spongycastle.crypto.prng;

public class ReversedWindowGenerator implements RandomGenerator {
    private final RandomGenerator generator;
    private byte[] window;
    private int windowCount;

    public ReversedWindowGenerator(RandomGenerator generator2, int windowSize) {
        if (generator2 == null) {
            throw new IllegalArgumentException("generator cannot be null");
        } else if (windowSize < 2) {
            throw new IllegalArgumentException("windowSize must be at least 2");
        } else {
            this.generator = generator2;
            this.window = new byte[windowSize];
        }
    }

    public void addSeedMaterial(byte[] seed) {
        synchronized (this) {
            this.windowCount = 0;
            this.generator.addSeedMaterial(seed);
        }
    }

    public void addSeedMaterial(long seed) {
        synchronized (this) {
            this.windowCount = 0;
            this.generator.addSeedMaterial(seed);
        }
    }

    public void nextBytes(byte[] bytes) {
        doNextBytes(bytes, 0, bytes.length);
    }

    public void nextBytes(byte[] bytes, int start, int len) {
        doNextBytes(bytes, start, len);
    }

    private void doNextBytes(byte[] bytes, int start, int len) {
        synchronized (this) {
            int done = 0;
            while (done < len) {
                try {
                    if (this.windowCount < 1) {
                        this.generator.nextBytes(this.window, 0, this.window.length);
                        this.windowCount = this.window.length;
                    }
                    int done2 = done + 1;
                    int i = start + done;
                    try {
                        byte[] bArr = this.window;
                        int i2 = this.windowCount - 1;
                        this.windowCount = i2;
                        bytes[i] = bArr[i2];
                        done = done2;
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    int i3 = done;
                    throw th;
                }
            }
        }
    }
}
