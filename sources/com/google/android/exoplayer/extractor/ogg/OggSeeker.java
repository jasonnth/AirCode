package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ogg.OggUtil.PageHeader;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;

final class OggSeeker {
    private long audioDataLength = -1;
    private final ParsableByteArray headerArray = new ParsableByteArray(282);
    private final PageHeader pageHeader = new PageHeader();
    private long totalSamples;

    OggSeeker() {
    }

    public void setup(long audioDataLength2, long totalSamples2) {
        Assertions.checkArgument(audioDataLength2 > 0 && totalSamples2 > 0);
        this.audioDataLength = audioDataLength2;
        this.totalSamples = totalSamples2;
    }

    public long getNextSeekPosition(long targetGranule, ExtractorInput input) throws IOException, InterruptedException {
        Assertions.checkState((this.audioDataLength == -1 || this.totalSamples == 0) ? false : true);
        OggUtil.populatePageHeader(input, this.pageHeader, this.headerArray, false);
        long granuleDistance = targetGranule - this.pageHeader.granulePosition;
        if (granuleDistance <= 0 || granuleDistance > 72000) {
            return (input.getPosition() - ((long) ((granuleDistance <= 0 ? 2 : 1) * (this.pageHeader.headerSize + this.pageHeader.bodySize)))) + ((this.audioDataLength * granuleDistance) / this.totalSamples);
        }
        input.resetPeekPosition();
        return -1;
    }
}
