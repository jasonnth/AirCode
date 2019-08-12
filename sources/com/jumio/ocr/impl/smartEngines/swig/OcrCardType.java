package com.jumio.ocr.impl.smartEngines.swig;

public final class OcrCardType {
    public static final OcrCardType OcrCardTypeAirplus = new OcrCardType("OcrCardTypeAirplus", jniInterfaceJNI.OcrCardTypeAirplus_get());
    public static final OcrCardType OcrCardTypeAmericanExpress = new OcrCardType("OcrCardTypeAmericanExpress", jniInterfaceJNI.OcrCardTypeAmericanExpress_get());
    public static final OcrCardType OcrCardTypeAtosPrivateLabel = new OcrCardType("OcrCardTypeAtosPrivateLabel", jniInterfaceJNI.OcrCardTypeAtosPrivateLabel_get());
    public static final OcrCardType OcrCardTypeAura = new OcrCardType("OcrCardTypeAura", jniInterfaceJNI.OcrCardTypeAura_get());
    public static final OcrCardType OcrCardTypeBpFuelCard = new OcrCardType("OcrCardTypeBpFuelCard", jniInterfaceJNI.OcrCardTypeBpFuelCard_get());
    public static final OcrCardType OcrCardTypeChinaUnionPay = new OcrCardType("OcrCardTypeChinaUnionPay", jniInterfaceJNI.OcrCardTypeChinaUnionPay_get());
    public static final OcrCardType OcrCardTypeChjonesFuelCard = new OcrCardType("OcrCardTypeChjonesFuelCard", jniInterfaceJNI.OcrCardTypeChjonesFuelCard_get());
    public static final OcrCardType OcrCardTypeDinersClubInternational = new OcrCardType("OcrCardTypeDinersClubInternational", jniInterfaceJNI.OcrCardTypeDinersClubInternational_get());
    public static final OcrCardType OcrCardTypeDiscover = new OcrCardType("OcrCardTypeDiscover", jniInterfaceJNI.OcrCardTypeDiscover_get());
    public static final OcrCardType OcrCardTypeElo = new OcrCardType("OcrCardTypeElo", jniInterfaceJNI.OcrCardTypeElo_get());
    public static final OcrCardType OcrCardTypeEuroshellFuelCard = new OcrCardType("OcrCardTypeEuroshellFuelCard", jniInterfaceJNI.OcrCardTypeEuroshellFuelCard_get());
    public static final OcrCardType OcrCardTypeGeCapital = new OcrCardType("OcrCardTypeGeCapital", jniInterfaceJNI.OcrCardTypeGeCapital_get());
    public static final OcrCardType OcrCardTypeHipercard = new OcrCardType("OcrCardTypeHipercard", jniInterfaceJNI.OcrCardTypeHipercard_get());
    public static final OcrCardType OcrCardTypeJcb = new OcrCardType("OcrCardTypeJcb", jniInterfaceJNI.OcrCardTypeJcb_get());
    public static final OcrCardType OcrCardTypeLoyaltyCard = new OcrCardType("OcrCardTypeLoyaltyCard", jniInterfaceJNI.OcrCardTypeLoyaltyCard_get());
    public static final OcrCardType OcrCardTypeLukoilFuelCard = new OcrCardType("OcrCardTypeLukoilFuelCard", jniInterfaceJNI.OcrCardTypeLukoilFuelCard_get());
    public static final OcrCardType OcrCardTypeMaestro = new OcrCardType("OcrCardTypeMaestro", jniInterfaceJNI.OcrCardTypeMaestro_get());
    public static final OcrCardType OcrCardTypeMastercard = new OcrCardType("OcrCardTypeMastercard", jniInterfaceJNI.OcrCardTypeMastercard_get());
    public static final OcrCardType OcrCardTypePhhFuelCard = new OcrCardType("OcrCardTypePhhFuelCard", jniInterfaceJNI.OcrCardTypePhhFuelCard_get());
    public static final OcrCardType OcrCardTypePrivate = new OcrCardType("OcrCardTypePrivate", jniInterfaceJNI.OcrCardTypePrivate_get());
    public static final OcrCardType OcrCardTypePrivateLabelCard = new OcrCardType("OcrCardTypePrivateLabelCard", jniInterfaceJNI.OcrCardTypePrivateLabelCard_get());
    public static final OcrCardType OcrCardTypeRbsGiftCard = new OcrCardType("OcrCardTypeRbsGiftCard", jniInterfaceJNI.OcrCardTypeRbsGiftCard_get());
    public static final OcrCardType OcrCardTypeRedFuelCard = new OcrCardType("OcrCardTypeRedFuelCard", jniInterfaceJNI.OcrCardTypeRedFuelCard_get());
    public static final OcrCardType OcrCardTypeRedLiquidFuelCard = new OcrCardType("OcrCardTypeRedLiquidFuelCard", jniInterfaceJNI.OcrCardTypeRedLiquidFuelCard_get());
    public static final OcrCardType OcrCardTypeRupay = new OcrCardType("OcrCardTypeRupay", jniInterfaceJNI.OcrCardTypeRupay_get());
    public static final OcrCardType OcrCardTypeStarRewards = new OcrCardType("OcrCardTypeStarRewards", jniInterfaceJNI.OcrCardTypeStarRewards_get());
    public static final OcrCardType OcrCardTypeStarbucks = new OcrCardType("OcrCardTypeStarbucks", jniInterfaceJNI.OcrCardTypeStarbucks_get());
    public static final OcrCardType OcrCardTypeUkFuelCard = new OcrCardType("OcrCardTypeUkFuelCard", jniInterfaceJNI.OcrCardTypeUkFuelCard_get());
    public static final OcrCardType OcrCardTypeUnknown = new OcrCardType("OcrCardTypeUnknown", jniInterfaceJNI.OcrCardTypeUnknown_get());
    public static final OcrCardType OcrCardTypeVisa = new OcrCardType("OcrCardTypeVisa", jniInterfaceJNI.OcrCardTypeVisa_get());
    private static int swigNext = 0;
    private static OcrCardType[] swigValues = {OcrCardTypeUnknown, OcrCardTypeAirplus, OcrCardTypeAmericanExpress, OcrCardTypeAtosPrivateLabel, OcrCardTypeAura, OcrCardTypeBpFuelCard, OcrCardTypeChinaUnionPay, OcrCardTypeChjonesFuelCard, OcrCardTypeDinersClubInternational, OcrCardTypeDiscover, OcrCardTypeElo, OcrCardTypeEuroshellFuelCard, OcrCardTypeGeCapital, OcrCardTypeHipercard, OcrCardTypeJcb, OcrCardTypeLoyaltyCard, OcrCardTypeLukoilFuelCard, OcrCardTypeMaestro, OcrCardTypeMastercard, OcrCardTypePhhFuelCard, OcrCardTypePrivate, OcrCardTypePrivateLabelCard, OcrCardTypeRbsGiftCard, OcrCardTypeRedFuelCard, OcrCardTypeRedLiquidFuelCard, OcrCardTypeRupay, OcrCardTypeStarRewards, OcrCardTypeUkFuelCard, OcrCardTypeVisa, OcrCardTypeStarbucks};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static OcrCardType swigToEnum(int swigValue2) {
        if (swigValue2 < swigValues.length && swigValue2 >= 0 && swigValues[swigValue2].swigValue == swigValue2) {
            return swigValues[swigValue2];
        }
        for (int i = 0; i < swigValues.length; i++) {
            if (swigValues[i].swigValue == swigValue2) {
                return swigValues[i];
            }
        }
        throw new IllegalArgumentException("No enum " + OcrCardType.class + " with value " + swigValue2);
    }

    private OcrCardType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private OcrCardType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private OcrCardType(String swigName2, OcrCardType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
