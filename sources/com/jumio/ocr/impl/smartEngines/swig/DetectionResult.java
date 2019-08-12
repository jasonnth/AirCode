package com.jumio.ocr.impl.smartEngines.swig;

public class DetectionResult {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected DetectionResult(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(DetectionResult obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniInterfaceJNI.delete_DetectionResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public DetectionResult() {
        this(jniInterfaceJNI.new_DetectionResult__SWIG_0(), true);
    }

    public DetectionResult(boolean isCardInRoi, boolean isCardImageOfGoodQuality, int cardImageQualityScore, boolean hasTopOfCard, boolean hasBottomOfCard, boolean hasLeftOfCard, boolean hasRightOfCard, OcrPoint focusPointOfInterest, OcrPoint exposurePointOfInterest, boolean flashTurnOn, OcrQuadrangle quadrangle) {
        this(jniInterfaceJNI.new_DetectionResult__SWIG_1(isCardInRoi, isCardImageOfGoodQuality, cardImageQualityScore, hasTopOfCard, hasBottomOfCard, hasLeftOfCard, hasRightOfCard, OcrPoint.getCPtr(focusPointOfInterest), focusPointOfInterest, OcrPoint.getCPtr(exposurePointOfInterest), exposurePointOfInterest, flashTurnOn, OcrQuadrangle.getCPtr(quadrangle), quadrangle), true);
    }

    public boolean isCardInRoi() {
        return jniInterfaceJNI.DetectionResult_isCardInRoi(this.swigCPtr, this);
    }

    public boolean isCardImageOfGoodQuality() {
        return jniInterfaceJNI.DetectionResult_isCardImageOfGoodQuality(this.swigCPtr, this);
    }

    public int getCardImageQualityScore() {
        return jniInterfaceJNI.DetectionResult_getCardImageQualityScore(this.swigCPtr, this);
    }

    public boolean hasTopOfCard() {
        return jniInterfaceJNI.DetectionResult_hasTopOfCard(this.swigCPtr, this);
    }

    public boolean hasBottomOfCard() {
        return jniInterfaceJNI.DetectionResult_hasBottomOfCard(this.swigCPtr, this);
    }

    public boolean hasLeftOfCard() {
        return jniInterfaceJNI.DetectionResult_hasLeftOfCard(this.swigCPtr, this);
    }

    public boolean hasRightOfCard() {
        return jniInterfaceJNI.DetectionResult_hasRightOfCard(this.swigCPtr, this);
    }

    public OcrPoint getFocusPointOfInterest() {
        return new OcrPoint(jniInterfaceJNI.DetectionResult_getFocusPointOfInterest(this.swigCPtr, this), true);
    }

    public OcrPoint getExposurePointOfInterest() {
        return new OcrPoint(jniInterfaceJNI.DetectionResult_getExposurePointOfInterest(this.swigCPtr, this), true);
    }

    public boolean getFlashTurnOn() {
        return jniInterfaceJNI.DetectionResult_getFlashTurnOn(this.swigCPtr, this);
    }

    public OcrQuadrangle getCardQuadrangle() {
        return new OcrQuadrangle(jniInterfaceJNI.DetectionResult_getCardQuadrangle(this.swigCPtr, this), true);
    }
}
