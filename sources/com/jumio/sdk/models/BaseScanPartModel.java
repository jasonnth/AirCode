package com.jumio.sdk.models;

import com.jumio.commons.camera.ImageData;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.StaticModel;

public abstract class BaseScanPartModel implements StaticModel {
    private DocumentScanMode mScanMode;
    private ImageData mScannedImage;
    private ScanSide mSideToScan;

    public BaseScanPartModel(ScanSide sideToScan, DocumentScanMode scanMode) {
        this.mSideToScan = sideToScan;
        this.mScanMode = scanMode;
    }

    public ImageData getScannedImage() {
        if (this.mScannedImage == null) {
            this.mScannedImage = new ImageData();
        }
        return this.mScannedImage;
    }

    public void setScannedImage(ImageData scannedImage) {
        this.mScannedImage = scannedImage;
    }

    public ScanSide getSideToScan() {
        return this.mSideToScan;
    }

    public DocumentScanMode getScanMode() {
        return this.mScanMode;
    }

    public void setScanMode(DocumentScanMode scanMode) {
        this.mScanMode = scanMode;
    }
}
