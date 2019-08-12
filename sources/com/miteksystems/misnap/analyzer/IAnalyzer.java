package com.miteksystems.misnap.analyzer;

public interface IAnalyzer {
    void analyze(IAnalyzeResponse iAnalyzeResponse, byte[] bArr, int i, int i2, int i3);

    void deinit();

    boolean init();
}
