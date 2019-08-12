package com.google.android.exoplayer.extractor.mp3;

final class ConstantBitrateSeeker implements Seeker {
    private final int bitrate;
    private final long durationUs;
    private final long firstFramePosition;

    public ConstantBitrateSeeker(long firstFramePosition2, int bitrate2, long inputLength) {
        long j = -1;
        this.firstFramePosition = firstFramePosition2;
        this.bitrate = bitrate2;
        if (inputLength != -1) {
            j = getTimeUs(inputLength);
        }
        this.durationUs = j;
    }

    public boolean isSeekable() {
        return this.durationUs != -1;
    }

    public long getPosition(long timeUs) {
        if (this.durationUs == -1) {
            return 0;
        }
        return this.firstFramePosition + ((((long) this.bitrate) * timeUs) / 8000000);
    }

    public long getTimeUs(long position) {
        return ((Math.max(0, position - this.firstFramePosition) * 1000000) * 8) / ((long) this.bitrate);
    }

    public long getDurationUs() {
        return this.durationUs;
    }
}
