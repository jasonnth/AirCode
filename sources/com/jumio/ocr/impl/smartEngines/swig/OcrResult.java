package com.jumio.ocr.impl.smartEngines.swig;

public class OcrResult {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrResult(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrResult obj) {
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
                jniInterfaceJNI.delete_OcrResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrResult(OcrField a_creditCardNumber, OcrField a_creditCardName, OcrField a_creditCardExpiry, OcrField a_ukSortCode, OcrField a_ukAccountNumber, OcrField a_rawSecondLine, OcrField a_amexCVV, OcrField a_sbcode, OcrQuadrangle a_creditCardQuadrangle, boolean a_shouldBeDiscardedFlag, boolean a_upturnedCardFlag, OcrLogoType a_logoType, OcrCardType a_cardType, boolean a_umpCardFlag, boolean a_stbCardFlag) {
        this(jniInterfaceJNI.new_OcrResult(OcrField.getCPtr(a_creditCardNumber), a_creditCardNumber, OcrField.getCPtr(a_creditCardName), a_creditCardName, OcrField.getCPtr(a_creditCardExpiry), a_creditCardExpiry, OcrField.getCPtr(a_ukSortCode), a_ukSortCode, OcrField.getCPtr(a_ukAccountNumber), a_ukAccountNumber, OcrField.getCPtr(a_rawSecondLine), a_rawSecondLine, OcrField.getCPtr(a_amexCVV), a_amexCVV, OcrField.getCPtr(a_sbcode), a_sbcode, OcrQuadrangle.getCPtr(a_creditCardQuadrangle), a_creditCardQuadrangle, a_shouldBeDiscardedFlag, a_upturnedCardFlag, a_logoType.swigValue(), a_cardType.swigValue(), a_umpCardFlag, a_stbCardFlag), true);
    }

    public OcrField getCardNumber() {
        return new OcrField(jniInterfaceJNI.OcrResult_getCardNumber(this.swigCPtr, this), false);
    }

    public OcrField getCardExpiry() {
        return new OcrField(jniInterfaceJNI.OcrResult_getCardExpiry(this.swigCPtr, this), false);
    }

    public OcrField getCardName() {
        return new OcrField(jniInterfaceJNI.OcrResult_getCardName(this.swigCPtr, this), false);
    }

    public OcrField getUkSortCode() {
        return new OcrField(jniInterfaceJNI.OcrResult_getUkSortCode(this.swigCPtr, this), false);
    }

    public OcrField getUkAccountNumber() {
        return new OcrField(jniInterfaceJNI.OcrResult_getUkAccountNumber(this.swigCPtr, this), false);
    }

    public OcrField getRawSecondLine() {
        return new OcrField(jniInterfaceJNI.OcrResult_getRawSecondLine(this.swigCPtr, this), false);
    }

    public OcrField getAmexCVV() {
        return new OcrField(jniInterfaceJNI.OcrResult_getAmexCVV(this.swigCPtr, this), false);
    }

    public OcrField getSbCode() {
        return new OcrField(jniInterfaceJNI.OcrResult_getSbCode(this.swigCPtr, this), false);
    }

    public OcrQuadrangle getCardQuadrangle() {
        return new OcrQuadrangle(jniInterfaceJNI.OcrResult_getCardQuadrangle(this.swigCPtr, this), false);
    }

    public int getOrientation() {
        return jniInterfaceJNI.OcrResult_getOrientation(this.swigCPtr, this);
    }

    public boolean getShouldBeDiscardedFlag() {
        return jniInterfaceJNI.OcrResult_getShouldBeDiscardedFlag(this.swigCPtr, this);
    }

    public boolean getUpturnedCardFlag() {
        return jniInterfaceJNI.OcrResult_getUpturnedCardFlag(this.swigCPtr, this);
    }

    public OcrLogoType getLogoType() {
        return OcrLogoType.swigToEnum(jniInterfaceJNI.OcrResult_getLogoType(this.swigCPtr, this));
    }

    public OcrCardType getCardType() {
        return OcrCardType.swigToEnum(jniInterfaceJNI.OcrResult_getCardType(this.swigCPtr, this));
    }

    public boolean getUmpCardFlag() {
        return jniInterfaceJNI.OcrResult_getUmpCardFlag(this.swigCPtr, this);
    }

    public boolean getStbCardFlag() {
        return jniInterfaceJNI.OcrResult_getStbCardFlag(this.swigCPtr, this);
    }
}
