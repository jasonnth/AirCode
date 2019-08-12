package com.google.android.exoplayer.util;

import com.google.android.exoplayer.extractor.SeekMap;

public final class FlacSeekTable {
    /* access modifiers changed from: private */
    public final long[] offsets;
    /* access modifiers changed from: private */
    public final long[] sampleNumbers;

    public static FlacSeekTable parseSeekTable(ParsableByteArray data) {
        data.skipBytes(1);
        int numberOfSeekPoints = data.readUnsignedInt24() / 18;
        long[] sampleNumbers2 = new long[numberOfSeekPoints];
        long[] offsets2 = new long[numberOfSeekPoints];
        for (int i = 0; i < numberOfSeekPoints; i++) {
            sampleNumbers2[i] = data.readLong();
            offsets2[i] = data.readLong();
            data.skipBytes(2);
        }
        return new FlacSeekTable(sampleNumbers2, offsets2);
    }

    private FlacSeekTable(long[] sampleNumbers2, long[] offsets2) {
        this.sampleNumbers = sampleNumbers2;
        this.offsets = offsets2;
    }

    public SeekMap createSeekMap(long firstFrameOffset, long sampleRate) {
        final long j = sampleRate;
        final long j2 = firstFrameOffset;
        return new SeekMap() {
            public boolean isSeekable() {
                return true;
            }

            public long getPosition(long timeUs) {
                return j2 + FlacSeekTable.this.offsets[Util.binarySearchFloor(FlacSeekTable.this.sampleNumbers, (j * timeUs) / 1000000, true, true)];
            }
        };
    }
}
