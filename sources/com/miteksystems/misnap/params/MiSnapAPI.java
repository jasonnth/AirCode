package com.miteksystems.misnap.params;

import android.content.Context;

public class MiSnapAPI {
    public static final String AppVersion = "AppVersion";
    public static final int BARCODE_ALL = -1;
    public static final int BARCODE_AZTEC_CODE = 128;
    public static final int BARCODE_CODABAR = 1024;
    public static final int BARCODE_CODE_128 = 32;
    public static final int BARCODE_CODE_25 = 256;
    public static final int BARCODE_CODE_39 = 8;
    public static final int BARCODE_CODE_93 = 512;
    public static final int BARCODE_DATA_MATRIX = 2;
    public static final int BARCODE_EAN_OR_UPC = 16;
    public static final int BARCODE_PDF417 = 64;
    public static final int BARCODE_QR_CODE = 1;
    public static final int BARCODE_SCAN_DIRECTION_AUTODETECT = 8;
    public static final int BARCODE_SCAN_DIRECTION_HORIZONTAL = 1;
    public static final int BARCODE_SCAN_DIRECTION_OMNI = 4;
    public static final int BARCODE_SCAN_DIRECTION_VERTICAL = 2;
    public static final String BarCodeDirections = "BarCodeDirections";
    public static final String BarCodeScanRegion = "BarCodeScanRegion";
    public static final String BarCodeTypes = "BarCodeTypes";
    public static final String CREDIT_CARD_CVV = "cc_cvv";
    public static final String CREDIT_CARD_EXPIRY_MONTH = "cc_expiry_month";
    public static final String CREDIT_CARD_EXPIRY_YEAR = "cc_expiry_year";
    public static final String CREDIT_CARD_FORMATTED_NUMBER = "cc_formatted_number";
    public static final String CREDIT_CARD_NUMBER = "cc_number";
    public static final String CREDIT_CARD_REDACTED_NUMBER = "cc_redacted_number";
    public static final String CREDIT_CARD_TYPE = "cc_type";
    public static final String CreditCardGuideColor = "CreditCardGuideColor";
    public static final String CreditCardRequireCVV = "CreditCardRequireCVV";
    public static final String CreditCardRequireExpiry = "CreditCardRequireExpiry";
    public static final String CreditCardSuppressConfirmScreen = "CreditCardSuppressConfirmScreen";
    public static final String JOB_SETTINGS = "misnap.miteksystems.com.JobSettings";
    public static final String MDVersion = "MDVersion";
    public static final String MIPVersion = "MIPVersion";
    public static final String MiSnapAllowScreenshots = "MiSnapAllowScreenshots";
    public static final String MiSnapAngle = "MiSnapAngle";
    public static final String MiSnapBrightness = "MiSnapBrightness";
    public static final String MiSnapCaptureMode = "MiSnapCaptureMode";
    public static final String MiSnapContrast = "MiSnapContrast";
    public static final String MiSnapCornerConfidence = "MiSnapCornerConfidence";
    public static final String MiSnapDocumentType = "MiSnapDocumentType";
    public static final String MiSnapFlattenAndCrop = "MiSnapFlattenAndCrop";
    public static final String MiSnapFocusMode = "MiSnapFocusMode";
    public static final String MiSnapForcedFocusDelay = "MiSnapForcedFocusDelay";
    public static final String MiSnapImageQuality = "MiSnapImageQuality";
    public static final String MiSnapLockView = "MiSnapLockView";
    public static final String MiSnapMICRConfidence = "MiSnapMICRConfidence";
    public static final String MiSnapMaxBrightness = "MiSnapMaxBrightness";
    public static final String MiSnapMaxImageHeightAndWidth = "MiSnapMaxImageHeightAndWidth";
    public static final String MiSnapMinPadding = "MiSnapMinPadding";
    public static final String MiSnapNoGlare = "MiSnapNoGlare";
    public static final String MiSnapSharpness = "MiSnapSharpness";
    public static final String MiSnapShortDescription = "MiSnapShortDescription";
    public static final String MiSnapSolidBackground = "MiSnapSolidBackground";
    public static final String MiSnapTextCheckBackPrompt = "MiSnapTextCheckBackPrompt";
    public static final String MiSnapTextCheckFrontPrompt = "MiSnapTextCheckFrontPrompt";
    public static final String MiSnapTorchMode = "MiSnapTorchMode";
    public static final String MiSnapUseFrontCamera = "MiSnapUseFrontCamera";
    public static final String MiSnapUsePortraitOrientation = "MiSnapUsePortraitOrientation";
    public static final String MiSnapViewfinderMinHorizontalFill = "MiSnapViewfinderMinHorizontalFill";
    public static final String RESULT_CANCELED = "Cancelled";
    public static final String RESULT_CODE = "com.miteksystems.misnap.ResultCode";
    public static final String RESULT_ERROR_CAMERA_NOT_SUFFICIENT = "RESULT_ERROR_CAMERA_NOT_SUFFICIENT";
    public static final String RESULT_ERROR_CONFIGURING_CAMERA = "RESULT_ERROR_CONFIGURING_CAMERA";
    public static final String RESULT_ERROR_CREATING_CAMERA_VIEW = "RESULT_ERROR_CREATING_CAMERA_VIEW";
    public static final String RESULT_ERROR_INTENT_PARAMETERS = "RESULT_ERROR_INTENT_PARAMETERS";
    public static final String RESULT_ERROR_INVALID_LICENSE_KEY = "RESULT_ERROR_INVALID_LICENSE_KEY";
    public static final String RESULT_ERROR_PREFIX = "RESULT_ERROR";
    public static final String RESULT_ERROR_SDK_STATE_ERROR = "RESULT_ERROR_SDK_STATE_ERROR";
    public static final String RESULT_ERROR_STARTING_CAMERA = "RESULT_ERROR_STARTING_CAMERA";
    public static final String RESULT_ERROR_UPDATING_UI = "RESULT_ERROR_UPDATING_UI";
    public static final String RESULT_IMAGE_QUALITY = "com.miteksystems.misnap.QUALITY";
    public static final String RESULT_MIBI_DATA = "com.miteksystems.misnap.MIBI_DATA";
    public static final String RESULT_ORIGINAL_PIC_HEIGHT = "com.miteksystems.misnap.HEIGHT";
    public static final String RESULT_ORIGINAL_PIC_WIDTH = "com.miteksystems.misnap.WIDTH";
    public static final String RESULT_PDF417_DATA = "com.miteksystems.misnap.PDF417";
    public static final int RESULT_PICTURE_CODE = 3;
    public static final String RESULT_PICTURE_DATA = "com.miteksystems.misnap.PICTURE";
    public static final String RESULT_SUCCESS_CREDIT_CARD = "SuccessCreditCard";
    public static final String RESULT_SUCCESS_PDF417 = "SuccessPDF417";
    public static final String RESULT_SUCCESS_STILL = "SuccessStillCamera";
    public static final String RESULT_SUCCESS_VIDEO = "SuccessVideo";
    public static final String RESULT_WARNINGS = "MiSnapResultWarnings";

    public static String miSnapVersion(Context context) {
        return context.getString(C4578R.string.misnap_versionCode);
    }

    public static String miSnapBuildVersion(Context context) {
        return context.getString(C4578R.string.misnap_buildnumber);
    }
}
