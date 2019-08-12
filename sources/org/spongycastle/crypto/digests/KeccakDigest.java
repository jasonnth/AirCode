package org.spongycastle.crypto.digests;

import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;

public class KeccakDigest implements ExtendedDigest {
    private static int[] KeccakRhoOffsets = keccakInitializeRhoOffsets();
    private static long[] KeccakRoundConstants = keccakInitializeRoundConstants();

    /* renamed from: C */
    long[] f6503C;
    protected int bitsAvailableForSqueezing;
    protected int bitsInQueue;
    long[] chiC;
    protected byte[] chunk;
    protected byte[] dataQueue;
    protected int fixedOutputLength;
    protected byte[] oneByte;
    protected int rate;
    protected boolean squeezing;
    protected byte[] state;
    long[] tempA;

    private static long[] keccakInitializeRoundConstants() {
        long[] keccakRoundConstants = new long[24];
        byte[] LFSRstate = {1};
        for (int i = 0; i < 24; i++) {
            keccakRoundConstants[i] = 0;
            for (int j = 0; j < 7; j++) {
                int bitPosition = (1 << j) - 1;
                if (LFSR86540(LFSRstate)) {
                    keccakRoundConstants[i] = keccakRoundConstants[i] ^ (1 << bitPosition);
                }
            }
        }
        return keccakRoundConstants;
    }

    private static boolean LFSR86540(byte[] LFSR) {
        boolean result;
        if ((LFSR[0] & 1) != 0) {
            result = true;
        } else {
            result = false;
        }
        if ((LFSR[0] & ISOFileInfo.DATA_BYTES1) != 0) {
            LFSR[0] = (byte) ((LFSR[0] << 1) ^ 113);
        } else {
            LFSR[0] = (byte) (LFSR[0] << 1);
        }
        return result;
    }

    private static int[] keccakInitializeRhoOffsets() {
        int[] keccakRhoOffsets = new int[25];
        keccakRhoOffsets[0] = 0;
        int x = 1;
        int y = 0;
        for (int t = 0; t < 24; t++) {
            keccakRhoOffsets[(x % 5) + ((y % 5) * 5)] = (((t + 1) * (t + 2)) / 2) % 64;
            x = ((x * 0) + (y * 1)) % 5;
            y = ((x * 2) + (y * 3)) % 5;
        }
        return keccakRhoOffsets;
    }

    private void clearDataQueueSection(int off, int len) {
        for (int i = off; i != off + len; i++) {
            this.dataQueue[i] = 0;
        }
    }

    public KeccakDigest() {
        this(288);
    }

    public KeccakDigest(int bitLength) {
        this.state = new byte[200];
        this.dataQueue = new byte[192];
        this.f6503C = new long[5];
        this.tempA = new long[25];
        this.chiC = new long[5];
        init(bitLength);
    }

    public KeccakDigest(KeccakDigest source) {
        this.state = new byte[200];
        this.dataQueue = new byte[192];
        this.f6503C = new long[5];
        this.tempA = new long[25];
        this.chiC = new long[5];
        System.arraycopy(source.state, 0, this.state, 0, source.state.length);
        System.arraycopy(source.dataQueue, 0, this.dataQueue, 0, source.dataQueue.length);
        this.rate = source.rate;
        this.bitsInQueue = source.bitsInQueue;
        this.fixedOutputLength = source.fixedOutputLength;
        this.squeezing = source.squeezing;
        this.bitsAvailableForSqueezing = source.bitsAvailableForSqueezing;
        this.chunk = Arrays.clone(source.chunk);
        this.oneByte = Arrays.clone(source.oneByte);
    }

    public String getAlgorithmName() {
        return "Keccak-" + this.fixedOutputLength;
    }

    public int getDigestSize() {
        return this.fixedOutputLength / 8;
    }

    public void update(byte in) {
        this.oneByte[0] = in;
        absorb(this.oneByte, 0, 8);
    }

    public void update(byte[] in, int inOff, int len) {
        absorb(in, inOff, ((long) len) * 8);
    }

    public int doFinal(byte[] out, int outOff) {
        squeeze(out, outOff, (long) this.fixedOutputLength);
        reset();
        return getDigestSize();
    }

    /* access modifiers changed from: protected */
    public int doFinal(byte[] out, int outOff, byte partialByte, int partialBits) {
        if (partialBits > 0) {
            this.oneByte[0] = partialByte;
            absorb(this.oneByte, 0, (long) partialBits);
        }
        squeeze(out, outOff, (long) this.fixedOutputLength);
        reset();
        return getDigestSize();
    }

    public void reset() {
        init(this.fixedOutputLength);
    }

    public int getByteLength() {
        return this.rate / 8;
    }

    private void init(int bitLength) {
        switch (bitLength) {
            case 128:
                initSponge(1344, 256);
                return;
            case 224:
                initSponge(1152, 448);
                return;
            case 256:
                initSponge(1088, 512);
                return;
            case 288:
                initSponge(1024, 576);
                return;
            case 384:
                initSponge(832, 768);
                return;
            case 512:
                initSponge(576, 1024);
                return;
            default:
                throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
        }
    }

    private void initSponge(int rate2, int capacity) {
        if (rate2 + capacity != 1600) {
            throw new IllegalStateException("rate + capacity != 1600");
        } else if (rate2 <= 0 || rate2 >= 1600 || rate2 % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        } else {
            this.rate = rate2;
            Arrays.fill(this.state, 0);
            Arrays.fill(this.dataQueue, 0);
            this.bitsInQueue = 0;
            this.squeezing = false;
            this.bitsAvailableForSqueezing = 0;
            this.fixedOutputLength = capacity / 2;
            this.chunk = new byte[(rate2 / 8)];
            this.oneByte = new byte[1];
        }
    }

    private void absorbQueue() {
        KeccakAbsorb(this.state, this.dataQueue, this.rate / 8);
        this.bitsInQueue = 0;
    }

    /* access modifiers changed from: protected */
    public void absorb(byte[] data, int off, long databitlen) {
        if (this.bitsInQueue % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue.");
        } else if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing.");
        } else {
            long i = 0;
            while (i < databitlen) {
                if (this.bitsInQueue != 0 || databitlen < ((long) this.rate) || i > databitlen - ((long) this.rate)) {
                    int partialBlock = (int) (databitlen - i);
                    if (this.bitsInQueue + partialBlock > this.rate) {
                        partialBlock = this.rate - this.bitsInQueue;
                    }
                    int partialByte = partialBlock % 8;
                    int partialBlock2 = partialBlock - partialByte;
                    System.arraycopy(data, ((int) (i / 8)) + off, this.dataQueue, this.bitsInQueue / 8, partialBlock2 / 8);
                    this.bitsInQueue += partialBlock2;
                    i += (long) partialBlock2;
                    if (this.bitsInQueue == this.rate) {
                        absorbQueue();
                    }
                    if (partialByte > 0) {
                        this.dataQueue[this.bitsInQueue / 8] = (byte) (data[((int) (i / 8)) + off] & ((1 << partialByte) - 1));
                        this.bitsInQueue += partialByte;
                        i += (long) partialByte;
                    }
                } else {
                    long wholeBlocks = (databitlen - i) / ((long) this.rate);
                    for (long j = 0; j < wholeBlocks; j++) {
                        System.arraycopy(data, (int) (((long) off) + (i / 8) + (((long) this.chunk.length) * j)), this.chunk, 0, this.chunk.length);
                        KeccakAbsorb(this.state, this.chunk, this.chunk.length);
                    }
                    i += ((long) this.rate) * wholeBlocks;
                }
            }
        }
    }

    private void padAndSwitchToSqueezingPhase() {
        if (this.bitsInQueue + 1 == this.rate) {
            byte[] bArr = this.dataQueue;
            int i = this.bitsInQueue / 8;
            bArr[i] = (byte) (bArr[i] | (1 << (this.bitsInQueue % 8)));
            absorbQueue();
            clearDataQueueSection(0, this.rate / 8);
        } else {
            clearDataQueueSection((this.bitsInQueue + 7) / 8, (this.rate / 8) - ((this.bitsInQueue + 7) / 8));
            byte[] bArr2 = this.dataQueue;
            int i2 = this.bitsInQueue / 8;
            bArr2[i2] = (byte) (bArr2[i2] | (1 << (this.bitsInQueue % 8)));
        }
        byte[] bArr3 = this.dataQueue;
        int i3 = (this.rate - 1) / 8;
        bArr3[i3] = (byte) (bArr3[i3] | (1 << ((this.rate - 1) % 8)));
        absorbQueue();
        if (this.rate == 1024) {
            KeccakExtract1024bits(this.state, this.dataQueue);
            this.bitsAvailableForSqueezing = 1024;
        } else {
            KeccakExtract(this.state, this.dataQueue, this.rate / 64);
            this.bitsAvailableForSqueezing = this.rate;
        }
        this.squeezing = true;
    }

    /* access modifiers changed from: protected */
    public void squeeze(byte[] output, int offset, long outputLength) {
        if (!this.squeezing) {
            padAndSwitchToSqueezingPhase();
        }
        if (outputLength % 8 != 0) {
            throw new IllegalStateException("outputLength not a multiple of 8");
        }
        long i = 0;
        while (i < outputLength) {
            if (this.bitsAvailableForSqueezing == 0) {
                keccakPermutation(this.state);
                if (this.rate == 1024) {
                    KeccakExtract1024bits(this.state, this.dataQueue);
                    this.bitsAvailableForSqueezing = 1024;
                } else {
                    KeccakExtract(this.state, this.dataQueue, this.rate / 64);
                    this.bitsAvailableForSqueezing = this.rate;
                }
            }
            int partialBlock = this.bitsAvailableForSqueezing;
            if (((long) partialBlock) > outputLength - i) {
                partialBlock = (int) (outputLength - i);
            }
            System.arraycopy(this.dataQueue, (this.rate - this.bitsAvailableForSqueezing) / 8, output, ((int) (i / 8)) + offset, partialBlock / 8);
            this.bitsAvailableForSqueezing -= partialBlock;
            i += (long) partialBlock;
        }
    }

    private void fromBytesToWords(long[] stateAsWords, byte[] state2) {
        for (int i = 0; i < 25; i++) {
            stateAsWords[i] = 0;
            int index = i * 8;
            for (int j = 0; j < 8; j++) {
                stateAsWords[i] = stateAsWords[i] | ((((long) state2[index + j]) & 255) << (j * 8));
            }
        }
    }

    private void fromWordsToBytes(byte[] state2, long[] stateAsWords) {
        for (int i = 0; i < 25; i++) {
            int index = i * 8;
            for (int j = 0; j < 8; j++) {
                state2[index + j] = (byte) ((int) ((stateAsWords[i] >>> (j * 8)) & 255));
            }
        }
    }

    private void keccakPermutation(byte[] state2) {
        long[] longState = new long[(state2.length / 8)];
        fromBytesToWords(longState, state2);
        keccakPermutationOnWords(longState);
        fromWordsToBytes(state2, longState);
    }

    private void keccakPermutationAfterXor(byte[] state2, byte[] data, int dataLengthInBytes) {
        for (int i = 0; i < dataLengthInBytes; i++) {
            state2[i] = (byte) (state2[i] ^ data[i]);
        }
        keccakPermutation(state2);
    }

    private void keccakPermutationOnWords(long[] state2) {
        for (int i = 0; i < 24; i++) {
            theta(state2);
            rho(state2);
            m3963pi(state2);
            chi(state2);
            iota(state2, i);
        }
    }

    private void theta(long[] A) {
        for (int x = 0; x < 5; x++) {
            this.f6503C[x] = 0;
            for (int y = 0; y < 5; y++) {
                long[] jArr = this.f6503C;
                jArr[x] = jArr[x] ^ A[(y * 5) + x];
            }
        }
        for (int x2 = 0; x2 < 5; x2++) {
            long dX = ((this.f6503C[(x2 + 1) % 5] << 1) ^ (this.f6503C[(x2 + 1) % 5] >>> 63)) ^ this.f6503C[(x2 + 4) % 5];
            for (int y2 = 0; y2 < 5; y2++) {
                int i = (y2 * 5) + x2;
                A[i] = A[i] ^ dX;
            }
        }
    }

    private void rho(long[] A) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                int index = x + (y * 5);
                A[index] = KeccakRhoOffsets[index] != 0 ? (A[index] << KeccakRhoOffsets[index]) ^ (A[index] >>> (64 - KeccakRhoOffsets[index])) : A[index];
            }
        }
    }

    /* renamed from: pi */
    private void m3963pi(long[] A) {
        System.arraycopy(A, 0, this.tempA, 0, this.tempA.length);
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                A[((((x * 2) + (y * 3)) % 5) * 5) + y] = this.tempA[(y * 5) + x];
            }
        }
    }

    private void chi(long[] A) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                this.chiC[x] = A[(y * 5) + x] ^ ((A[((x + 1) % 5) + (y * 5)] ^ -1) & A[((x + 2) % 5) + (y * 5)]);
            }
            for (int x2 = 0; x2 < 5; x2++) {
                A[(y * 5) + x2] = this.chiC[x2];
            }
        }
    }

    private void iota(long[] A, int indexRound) {
        A[0] = A[0] ^ KeccakRoundConstants[indexRound];
    }

    private void KeccakAbsorb(byte[] byteState, byte[] data, int dataInBytes) {
        keccakPermutationAfterXor(byteState, data, dataInBytes);
    }

    private void KeccakExtract1024bits(byte[] byteState, byte[] data) {
        System.arraycopy(byteState, 0, data, 0, 128);
    }

    private void KeccakExtract(byte[] byteState, byte[] data, int laneCount) {
        System.arraycopy(byteState, 0, data, 0, laneCount * 8);
    }
}
