package com.jumio.core.overlay;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.ViewGroup;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;

public interface Overlay {
    public static final int BORDER_STROKE_WIDTH_IN_DP = 2;

    void addViews(ViewGroup viewGroup);

    void calculate(DocumentScanMode documentScanMode, int i, int i2);

    void doDraw(Canvas canvas);

    Rect getOverlayBounds();

    void prepareDraw(ScanSide scanSide, boolean z, boolean z2);

    void setVisible(int i);

    void update(ExtractionUpdate extractionUpdate);
}
