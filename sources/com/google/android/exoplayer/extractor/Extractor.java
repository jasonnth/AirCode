package com.google.android.exoplayer.extractor;

import java.io.IOException;

public interface Extractor {
    void init(ExtractorOutput extractorOutput);

    int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException;

    void release();

    void seek();

    boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException;
}
