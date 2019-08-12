package com.google.android.exoplayer.upstream;

public interface BandwidthMeter extends TransferListener {

    public interface EventListener {
        void onBandwidthSample(int i, long j, long j2);
    }

    long getBitrateEstimate();
}
