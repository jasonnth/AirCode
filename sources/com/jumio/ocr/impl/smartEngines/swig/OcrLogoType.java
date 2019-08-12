package com.jumio.ocr.impl.smartEngines.swig;

public final class OcrLogoType {
    public static final OcrLogoType OcrLogoTypeAmericanExpress = new OcrLogoType("OcrLogoTypeAmericanExpress");
    public static final OcrLogoType OcrLogoTypeDinersClub = new OcrLogoType("OcrLogoTypeDinersClub");
    public static final OcrLogoType OcrLogoTypeDiscover = new OcrLogoType("OcrLogoTypeDiscover");
    public static final OcrLogoType OcrLogoTypeMasterCard = new OcrLogoType("OcrLogoTypeMasterCard");
    public static final OcrLogoType OcrLogoTypeUnknown = new OcrLogoType("OcrLogoTypeUnknown", jniInterfaceJNI.OcrLogoTypeUnknown_get());
    public static final OcrLogoType OcrLogoTypeVisa = new OcrLogoType("OcrLogoTypeVisa");
    private static int swigNext = 0;
    private static OcrLogoType[] swigValues = {OcrLogoTypeUnknown, OcrLogoTypeVisa, OcrLogoTypeMasterCard, OcrLogoTypeAmericanExpress, OcrLogoTypeDiscover, OcrLogoTypeDinersClub};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static OcrLogoType swigToEnum(int swigValue2) {
        if (swigValue2 < swigValues.length && swigValue2 >= 0 && swigValues[swigValue2].swigValue == swigValue2) {
            return swigValues[swigValue2];
        }
        for (int i = 0; i < swigValues.length; i++) {
            if (swigValues[i].swigValue == swigValue2) {
                return swigValues[i];
            }
        }
        throw new IllegalArgumentException("No enum " + OcrLogoType.class + " with value " + swigValue2);
    }

    private OcrLogoType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private OcrLogoType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private OcrLogoType(String swigName2, OcrLogoType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
