package com.google.android.exoplayer.hls;

import android.util.SparseArray;
import com.google.android.exoplayer.extractor.p308ts.PtsTimestampAdjuster;

public final class PtsTimestampAdjusterProvider {
    private final SparseArray<PtsTimestampAdjuster> ptsTimestampAdjusters = new SparseArray<>();

    public PtsTimestampAdjuster getAdjuster(boolean isMasterSource, int discontinuitySequence, long startTimeUs) {
        PtsTimestampAdjuster adjuster = (PtsTimestampAdjuster) this.ptsTimestampAdjusters.get(discontinuitySequence);
        if (isMasterSource && adjuster == null) {
            adjuster = new PtsTimestampAdjuster(startTimeUs);
            this.ptsTimestampAdjusters.put(discontinuitySequence, adjuster);
        }
        if (isMasterSource) {
            return adjuster;
        }
        if (adjuster == null || !adjuster.isInitialized()) {
            return null;
        }
        return adjuster;
    }

    public void reset() {
        this.ptsTimestampAdjusters.clear();
    }
}
