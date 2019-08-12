package com.google.android.exoplayer.util;

public final class FlacUtil {
    public static long extractSampleTimestamp(FlacStreamInfo streamInfo, ParsableByteArray frameData) {
        frameData.skipBytes(4);
        long sampleNumber = frameData.readUTF8EncodedLong();
        if (streamInfo.minBlockSize == streamInfo.maxBlockSize) {
            sampleNumber *= (long) streamInfo.minBlockSize;
        }
        return (1000000 * sampleNumber) / ((long) streamInfo.sampleRate);
    }
}
