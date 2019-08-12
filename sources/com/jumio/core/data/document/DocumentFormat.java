package com.jumio.core.data.document;

public enum DocumentFormat {
    NONE(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d),
    ID1(0.136d, 0.136d, 0.0675d, 0.0675d, 1.585185185185185d, 0.24000000000000002d, 0.09546296296296297d),
    ID2(0.0936d, 0.0936d, 0.0675d, 0.0675d, 1.4189189189189189d, 0.17162162162162162d, 0.08412162162162162d),
    ID3(0.022d, 0.022d, 0.022d, 0.022d, 1.4119318181818181d, 0.2353181818181818d, 0.0d);
    
    private double marginBottom;
    private double overlayBottom;
    private double overlayLeft;
    private double overlayRatio;
    private double overlayRight;
    private double overlayTop;
    private double roiHeight;

    private DocumentFormat(double overlayTop2, double overlayBottom2, double overlayLeft2, double overlayRight2, double overlayRatio2, double roiHeight2, double marginBottom2) {
        this.overlayTop = overlayTop2;
        this.overlayBottom = overlayBottom2;
        this.overlayLeft = overlayLeft2;
        this.overlayRight = overlayRight2;
        this.overlayRatio = overlayRatio2;
        this.roiHeight = roiHeight2;
        this.marginBottom = marginBottom2;
    }

    public double getOverlayTop() {
        return this.overlayTop;
    }

    public double getOverlayBottom() {
        return this.overlayBottom;
    }

    public double getOverlayLeft() {
        return this.overlayLeft;
    }

    public double getOverlayRight() {
        return this.overlayRight;
    }

    public double getOverlayRatio() {
        return this.overlayRatio;
    }

    public double getRoiHeight() {
        return this.roiHeight;
    }

    public double getMarginBottom() {
        return this.marginBottom;
    }

    public int getOverlayLeftInPx(int overlayWidth) {
        return (int) (((double) overlayWidth) * this.overlayLeft);
    }

    public int getOverlayRightInPx(int overlayWidth) {
        return (int) (((double) overlayWidth) * this.overlayRight);
    }

    public int getOverlayTopInPx(int overlayHeight) {
        return (int) (((double) overlayHeight) * this.overlayTop);
    }

    public int getOverlayBottomInPx(int overlayHeight) {
        return (int) (((double) overlayHeight) * this.overlayBottom);
    }

    public int getRoiHeightInPx(int overlayHeight) {
        return (int) (((double) (overlayHeight - (getOverlayTopInPx(overlayHeight) * 2))) * this.roiHeight);
    }

    public int getRoiMarginBottomPx(int overlayHeight) {
        return (int) (((double) (overlayHeight - (getOverlayTopInPx(overlayHeight) * 2))) * this.marginBottom);
    }
}
