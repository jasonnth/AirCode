package com.jumio.ocr.impl.smartEngines.swig;

public class OcrEngineSettings {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrEngineSettings(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrEngineSettings obj) {
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
                jniInterfaceJNI.delete_OcrEngineSettings(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrEngineSettings() {
        this(jniInterfaceJNI.new_OcrEngineSettings__SWIG_0(), true);
    }

    public OcrEngineSettings(OcrEngineSettings copy) {
        this(jniInterfaceJNI.new_OcrEngineSettings__SWIG_1(getCPtr(copy), copy), true);
    }

    public int getNumberOfQuadranglesToProcess() {
        return jniInterfaceJNI.OcrEngineSettings_getNumberOfQuadranglesToProcess(this.swigCPtr, this);
    }

    public OcrQuadrangleVector getPreDetectedQuadrangles() {
        return new OcrQuadrangleVector(jniInterfaceJNI.OcrEngineSettings_getPreDetectedQuadrangles(this.swigCPtr, this), false);
    }

    public boolean getNumberRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getNumberRecognitionFlag(this.swigCPtr, this);
    }

    public boolean getExpiryRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getExpiryRecognitionFlag(this.swigCPtr, this);
    }

    public boolean getNameRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getNameRecognitionFlag(this.swigCPtr, this);
    }

    public boolean getUkSortCodeAccountNumberRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getUkSortCodeAccountNumberRecognitionFlag(this.swigCPtr, this);
    }

    public boolean getAmexCVVRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getAmexCVVRecognitionFlag(this.swigCPtr, this);
    }

    public boolean getSbcodeRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getSbcodeRecognitionFlag(this.swigCPtr, this);
    }

    public boolean getNumberContextCorrectionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getNumberContextCorrectionFlag(this.swigCPtr, this);
    }

    public boolean getExpiryContextCorrectionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getExpiryContextCorrectionFlag(this.swigCPtr, this);
    }

    public boolean getNameContextCorrectionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getNameContextCorrectionFlag(this.swigCPtr, this);
    }

    public boolean getUkSortCodeAccountNumberContextCorrectionFlag() {
        return jniInterfaceJNI.m2050x71b585de(this.swigCPtr, this);
    }

    public boolean getRawSecondLineRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getRawSecondLineRecognitionFlag(this.swigCPtr, this);
    }

    public boolean getUseAdvAcceptDictFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getUseAdvAcceptDictFlag(this.swigCPtr, this);
    }

    public boolean getCheckChinaUnionPayLuhnCodeFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getCheckChinaUnionPayLuhnCodeFlag(this.swigCPtr, this);
    }

    public boolean getCheckStarbucksFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getCheckStarbucksFlag(this.swigCPtr, this);
    }

    public boolean getCheckUmpFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getCheckUmpFlag(this.swigCPtr, this);
    }

    public boolean getUpturnedCardDetectionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getUpturnedCardDetectionFlag(this.swigCPtr, this);
    }

    public int getFocusThreshold() {
        return jniInterfaceJNI.OcrEngineSettings_getFocusThreshold(this.swigCPtr, this);
    }

    public int getIntensityThreshold() {
        return jniInterfaceJNI.OcrEngineSettings_getIntensityThreshold(this.swigCPtr, this);
    }

    public boolean getLogoAnalysisFlag() {
        return jniInterfaceJNI.OcrEngineSettings_getLogoAnalysisFlag(this.swigCPtr, this);
    }

    public void setNumberOfQuadranglesToProcess(int value) {
        jniInterfaceJNI.OcrEngineSettings_setNumberOfQuadranglesToProcess(this.swigCPtr, this, value);
    }

    public void setPreDetectedQuadrangles(OcrQuadrangleVector value) {
        jniInterfaceJNI.OcrEngineSettings_setPreDetectedQuadrangles(this.swigCPtr, this, OcrQuadrangleVector.getCPtr(value), value);
    }

    public void setNumberRecognitionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setNumberRecognitionFlag(this.swigCPtr, this, value);
    }

    public void setExpiryRecognitionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setExpiryRecognitionFlag(this.swigCPtr, this, value);
    }

    public void setNameRecognitionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setNameRecognitionFlag(this.swigCPtr, this, value);
    }

    public void setNumberContextCorrectionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setNumberContextCorrectionFlag(this.swigCPtr, this, value);
    }

    public void setExpiryContextCorrectionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setExpiryContextCorrectionFlag(this.swigCPtr, this, value);
    }

    public void setNameContextCorrectionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setNameContextCorrectionFlag(this.swigCPtr, this, value);
    }

    public void setUkSortCodeAccountNumberRecognitionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setUkSortCodeAccountNumberRecognitionFlag(this.swigCPtr, this, value);
    }

    public void setUkSortCodeAccountNumberContextCorrectionFlag(boolean value) {
        jniInterfaceJNI.m2053xf36f50ea(this.swigCPtr, this, value);
    }

    public void setAmexCVVRecognitionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setAmexCVVRecognitionFlag(this.swigCPtr, this, value);
    }

    public void setSbcodeRecognitionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setSbcodeRecognitionFlag(this.swigCPtr, this, value);
    }

    public void setRawSecondLineRecognitionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setRawSecondLineRecognitionFlag(this.swigCPtr, this, value);
    }

    public void setUseAdvAcceptDictFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setUseAdvAcceptDictFlag(this.swigCPtr, this, value);
    }

    public void setCheckChinaUnionPayLuhnCodeFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setCheckChinaUnionPayLuhnCodeFlag(this.swigCPtr, this, value);
    }

    public void setCheckStarbucksFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setCheckStarbucksFlag(this.swigCPtr, this, value);
    }

    public void setCheckUmpFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setCheckUmpFlag(this.swigCPtr, this, value);
    }

    public void setUpturnedCardDetectionFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setUpturnedCardDetectionFlag(this.swigCPtr, this, value);
    }

    public void setFocusThreshold(int value) {
        jniInterfaceJNI.OcrEngineSettings_setFocusThreshold(this.swigCPtr, this, value);
    }

    public void setIntensityThreshold(int value) {
        jniInterfaceJNI.OcrEngineSettings_setIntensityThreshold(this.swigCPtr, this, value);
    }

    public void setLogoAnalysisFlag(boolean value) {
        jniInterfaceJNI.OcrEngineSettings_setLogoAnalysisFlag(this.swigCPtr, this, value);
    }

    public boolean isOverridenNumberOfQuadranglesToProcess() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenNumberOfQuadranglesToProcess(this.swigCPtr, this);
    }

    public boolean isOverridenPreDetectedQuadrangles() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenPreDetectedQuadrangles(this.swigCPtr, this);
    }

    public boolean isOverridenNumberRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenNumberRecognitionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenExpiryRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenExpiryRecognitionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenNameRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenNameRecognitionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenAmexCVVRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenAmexCVVRecognitionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenSbcodeRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenSbcodeRecognitionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenNumberContextCorrectionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenNumberContextCorrectionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenExpiryContextCorrectionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenExpiryContextCorrectionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenNameContextCorrectionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenNameContextCorrectionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenUkSortCodeAccountNumberRecognitionFlag() {
        return jniInterfaceJNI.m2052x7121692a(this.swigCPtr, this);
    }

    public boolean isOverridenUkSortCodeAccountNumberContextCorrectionFlag() {
        return jniInterfaceJNI.m2051x7fa7d7a0(this.swigCPtr, this);
    }

    public boolean isOverridenRawSecondLineRecognitionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenRawSecondLineRecognitionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenUseAdvAcceptDictFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenUseAdvAcceptDictFlag(this.swigCPtr, this);
    }

    public boolean isOverridenCheckChinaUnionPayLuhnCodeFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenCheckChinaUnionPayLuhnCodeFlag(this.swigCPtr, this);
    }

    public boolean isOverridenCheckStarbucksFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenCheckStarbucksFlag(this.swigCPtr, this);
    }

    public boolean isOverridenCheckUmpFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenCheckUmpFlag(this.swigCPtr, this);
    }

    public boolean isOverridenUpturnedCardDetectionFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenUpturnedCardDetectionFlag(this.swigCPtr, this);
    }

    public boolean isOverridenFocusThreshold() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenFocusThreshold(this.swigCPtr, this);
    }

    public boolean isOverridenIntensityThreshold() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenIntensityThreshold(this.swigCPtr, this);
    }

    public boolean isOverridenLogoAnalysisFlag() {
        return jniInterfaceJNI.OcrEngineSettings_isOverridenLogoAnalysisFlag(this.swigCPtr, this);
    }
}
