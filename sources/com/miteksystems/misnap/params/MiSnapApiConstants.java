package com.miteksystems.misnap.params;

import android.os.Build.VERSION;

public class MiSnapApiConstants {
    public static final int ALLOW_SCREENSHOTS_PARAM_MAX_VALUE = 1;
    public static final int ALLOW_SCREENSHOTS_PARAM_MIN_VALUE = 0;
    public static final int ANGLE_PARAM_MAX_VALUE = 1000;
    public static final int ANGLE_PARAM_MIN_VALUE = 2;
    public static final int BRIGHTNESS_PARAM_MAX_VALUE = 1000;
    public static final int BRIGHTNESS_PARAM_MIN_VALUE = 0;
    public static final int CAMERA_FORCE_FOCUS_START_TIMEOUT_MAX_VALUE = 45000;
    public static final int CAMERA_FORCE_FOCUS_START_TIMEOUT_MIN_VALUE = 0;
    public static final int CONTRAST_PARAM_MAX_VALUE = 1000;
    public static final int CONTRAST_PARAM_MIN_VALUE = 0;
    public static final int CORNER_CONFIDENCE_PARAM_MAX_VALUE = 1000;
    public static final int CORNER_CONFIDENCE_PARAM_MIN_VALUE = 0;
    public static final int DEFAULT_ALLOW_SCREENSHOTS = 0;
    public static final int DEFAULT_ANGLE_DEGREES_THRESHOLD = 150;
    public static final int DEFAULT_ANGLE_DEGREES_THRESHOLD_DRIVERS_LICENSE = 150;
    public static final int DEFAULT_BARCODE_DIRECTIONS = 1;
    public static final int[] DEFAULT_BARCODE_SCANNING_REGION = {12, 7, 76, 86};
    public static final int DEFAULT_BARCODE_SCANNING_REGION_HEIGHT = 86;
    public static final int DEFAULT_BARCODE_SCANNING_REGION_LEFT = 12;
    public static final int DEFAULT_BARCODE_SCANNING_REGION_TOP = 7;
    public static final int DEFAULT_BARCODE_SCANNING_REGION_WIDTH = 76;
    public static final int DEFAULT_BARCODE_TYPES = 64;
    public static final int DEFAULT_BRIGHTNESS = 330;
    public static final int DEFAULT_BRIGHTNESS_CHECKS = 330;
    public static final int DEFAULT_BRIGHTNESS_DRIVERS_LICENSE = 330;
    public static final int DEFAULT_BRIGHTNESS_PASSPORTS = 200;
    public static final int DEFAULT_CAMERA_FORCE_FOCUS_START_TIMEOUT = 10000;
    public static final int DEFAULT_CAMERA_VIEWFINDER_MIN_H_FILL = 700;
    public static final int DEFAULT_CAPTURE_MODE = 2;
    public static final int DEFAULT_CONTRAST = 600;
    public static final int DEFAULT_CONTRAST_DRIVER_LICENSE = 800;
    public static final int DEFAULT_CONTRAST_PASSPORT = 650;
    public static final int DEFAULT_CORNER_CONFIDENCE_CHECK = 600;
    public static final int DEFAULT_CORNER_CONFIDENCE_DRIVER_LICENSE = 850;
    public static final int DEFAULT_CORNER_CONFIDENCE_DRIVER_PASSPORT = 600;
    public static final int DEFAULT_CORNER_CONFIDENCE_GENERIC = 600;
    public static final int DEFAULT_CREDIT_CARD_GUIDE_COLOR = 1157627903;
    public static final boolean DEFAULT_CREDIT_CARD_REQUIRE_CVV = false;
    public static final boolean DEFAULT_CREDIT_CARD_REQUIRE_EXPIRY = false;
    public static final boolean DEFAULT_CREDIT_CARD_SUPPRESS_CONFIRM_SCREEN = false;
    public static final int DEFAULT_FLATTEN_AND_CROP = 0;
    public static final int DEFAULT_FOCUS_MODE = 3;
    public static final int DEFAULT_IMAGE_HORIZONTAL_PIXELS = 1600;
    public static final int DEFAULT_IMAGE_QUALITY = 50;
    public static final int DEFAULT_IMAGE_QUALITY_DRIVER_LICENSE = 60;
    public static final int DEFAULT_IMAGE_QUALITY_PASSPORT = 60;
    public static final int DEFAULT_MAX_BRIGHTNESS = 820;
    public static final int DEFAULT_MICR_CONFIDENCE_FOR_CHECK_FRONT = 450;
    public static final int DEFAULT_MICR_CONFIDENCE_FOR_DOC_WITHOUT_MICR = 0;
    public static final int DEFAULT_MICR_CONFIDENCE_FOR_PASSPORT = 920;
    public static final int DEFAULT_MIN_HORIZ_FILL_DRIVER_LICENSE = 650;
    public static final int DEFAULT_MIN_HORIZ_FILL_PASSPORT = 560;
    public static final int DEFAULT_MIN_PADDING = 7;
    public static final int DEFAULT_NO_GLARE_CHECK = 0;
    public static final int DEFAULT_NO_GLARE_IDENTITY_DOC = 1000;
    public static final int DEFAULT_SCREEN_ROTATION = 0;
    public static final int DEFAULT_SHARPNESS_AUTO = 600;
    public static final int DEFAULT_SHARPNESS_CHECK_BACK = 550;
    public static final int DEFAULT_SHARPNESS_CHECK_BACK_TABLET = 550;
    public static final int DEFAULT_SHARPNESS_CHECK_FRONT = 600;
    public static final int DEFAULT_SHARPNESS_DRIVERS_LICENSE = 650;
    public static final int DEFAULT_SHARPNESS_GENERIC = 600;
    public static final int DEFAULT_SHARPNESS_PASSPORT = 600;
    public static final int DEFAULT_SHARPNESS_REMITTANCE = 600;
    public static final int DEFAULT_SHARPNESS_W2 = 600;
    public static final int DEFAULT_SOLID_BACKGROUND = 750;
    public static final int DEFAULT_SOLID_BACKGROUND_DRIVER_LICENSE = 950;
    public static final int DEFAULT_SOLID_BACKGROUND_PASSPORT = 800;
    public static final String DEFAULT_TEXT_CHECK_BACK_PROMPT = "MiSnapLocalizedTextCheckBackPrompt";
    public static final String DEFAULT_TEXT_CHECK_FRONT_PROMPT = "MiSnapLocalizedTextCheckFrontPrompt";
    public static final int DEFAULT_TORCH_MODE = 1;
    public static final int DEFAULT_USE_FRONT_CAMERA = 0;
    public static final int DEFAULT_USE_PORTRAIT_ORIENTATION = 0;
    public static final int IMAGE_HORIZONTAL_PIXELS_PARAM_MAX_VALUE = 5500;
    public static final int IMAGE_HORIZONTAL_PIXELS_PARAM_MIN_VALUE = 300;
    public static final int IMAGE_QUALITY_PARAM_MAX_VALUE = 100;
    public static final int IMAGE_QUALITY_PARAM_MIN_VALUE = 0;
    public static final int LOCK_VIEW_PARAM_MAX_VALUE = 1;
    public static final int LOCK_VIEW_PARAM_MIN_VALUE = 0;
    public static final int MAX_BRIGHTNESS_PARAM_MAX_VALUE = 1000;
    public static final int MAX_BRIGHTNESS_PARAM_MIN_VALUE = 0;
    public static final int MICR_CONFIDENCE_PARAM_MAX_VALUE = 1000;
    public static final int MICR_CONFIDENCE_PARAM_MIN_VALUE = 0;
    public static final int MIN_H_FILL_PARAM_MAX_VALUE = 1000;
    public static final int MIN_H_FILL_PARAM_MIN_VALUE = 400;
    public static final int MIN_PADDING_PARAM_MAX_VALUE = 30;
    public static final int MIN_PADDING_PARAM_MIN_VALUE = 0;
    public static final int NO_GLARE_PARAM_MAX_VALUE = 1000;
    public static final int NO_GLARE_PARAM_MIN_VALUE = 0;
    public static final int PARAMETER_CAPTURE_MODE_AUTO = 2;
    public static final int PARAMETER_CAPTURE_MODE_MANUAL = 1;
    public static final String PARAMETER_DOCTYPE_AUTOMATIC_CHECK_HANDLING = "ACH";
    public static final String PARAMETER_DOCTYPE_AUTO_INSURANCE = "AUTO_INSURANCE";
    public static final String PARAMETER_DOCTYPE_BALANCE_TRANSFER = "BALANCE_TRANSFER";
    public static final String PARAMETER_DOCTYPE_BARCODES = "BARCODES";
    public static final String PARAMETER_DOCTYPE_BILL_PAY = "REMITTANCE";
    public static final String PARAMETER_DOCTYPE_BUSINESS_CARD = "BUSINESS_CARD";
    public static final String PARAMETER_DOCTYPE_CAMERA_ONLY = "CAMERA_ONLY";
    public static final String PARAMETER_DOCTYPE_CHECK_BACK = "CheckBack";
    public static final String PARAMETER_DOCTYPE_CHECK_FRONT = "CheckFront";
    public static final String PARAMETER_DOCTYPE_CREDIT_CARD = "CREDIT_CARD";
    public static final String PARAMETER_DOCTYPE_DRIVER_LICENSE = "DRIVER_LICENSE";
    public static final String PARAMETER_DOCTYPE_PASSPORT = "PASSPORT";
    public static final String PARAMETER_DOCTYPE_PDF417 = "PDF417";
    public static final String PARAMETER_DOCTYPE_VEHICLE_IDENTIFICATION_NUMBER = "VIN";
    public static final String PARAMETER_DOCTYPE_W2_FORM = "W2";
    public static final int PARAMETER_FOCUS_MODE_DEVICE_AUTO = 2;
    public static final int PARAMETER_FOCUS_MODE_HYBRID = 3;
    public static final int PARAMETER_FOCUS_MODE_MISNAP_CONTROLLED = 1;
    public static final int PARAMETER_TORCH_MODE_AUTO = 1;
    public static final int PARAMETER_TORCH_MODE_OFF = 0;
    public static final int PARAMETER_TORCH_MODE_ON = 2;
    public static final int SHARPNESS_PARAM_MAX_VALUE = 1000;
    public static final int SHARPNESS_PARAM_MIN_VALUE = 0;
    public static final int SOLID_BACKGROUND_PARAM_MAX_VALUE = 1000;
    public static final int SOLID_BACKGROUND_PARAM_MIN_VALUE = 0;
    public static final int USE_FRONT_CAMERA_PARAM_MAX_VALUE = 1;
    public static final int USE_FRONT_CAMERA_PARAM_MIN_VALUE = 0;
    public static final int USE_PORTRAIT_ORIENTATION_PARAM_MAX_VALUE = 1;
    public static final int USE_PORTRAIT_ORIENTATION_PARAM_MIN_VALUE = 0;

    public static String getDefaultParameters(String docType) {
        ApiParameterBuilder params = new ApiParameterBuilder();
        params.addParam(MiSnapAPI.MiSnapDocumentType, docType);
        int captureMode = 2;
        if (VERSION.SDK_INT < 14 || docType.equalsIgnoreCase(PARAMETER_DOCTYPE_VEHICLE_IDENTIFICATION_NUMBER)) {
            captureMode = 1;
        }
        params.addParam(MiSnapAPI.MiSnapForcedFocusDelay, 10000);
        params.addParam(MiSnapAPI.MiSnapFocusMode, 3);
        params.addParam(MiSnapAPI.MiSnapCaptureMode, captureMode);
        params.addParam(MiSnapAPI.MiSnapViewfinderMinHorizontalFill, 700);
        params.addParam(MiSnapAPI.MiSnapMinPadding, 7);
        params.addParam(MiSnapAPI.MiSnapBrightness, 330);
        params.addParam(MiSnapAPI.MiSnapMaxBrightness, (int) DEFAULT_MAX_BRIGHTNESS);
        params.addParam(MiSnapAPI.MiSnapAngle, 150);
        params.addParam(MiSnapAPI.MiSnapContrast, 600);
        params.addParam(MiSnapAPI.MiSnapSolidBackground, (int) DEFAULT_SOLID_BACKGROUND);
        params.addParam(MiSnapAPI.MiSnapTorchMode, 1);
        params.addParam(MiSnapAPI.MiSnapLockView, 0);
        params.addParam(MiSnapAPI.MiSnapCornerConfidence, 600);
        params.addParam(MiSnapAPI.MiSnapMICRConfidence, 0);
        params.addParam(MiSnapAPI.MiSnapUsePortraitOrientation, 0);
        params.addParam(MiSnapAPI.MiSnapAllowScreenshots, 0);
        params.addParam(MiSnapAPI.MiSnapUseFrontCamera, 0);
        params.addParam(MiSnapAPI.MiSnapMaxImageHeightAndWidth, (int) DEFAULT_IMAGE_HORIZONTAL_PIXELS);
        params.addParam(MiSnapAPI.MiSnapTextCheckFrontPrompt, DEFAULT_TEXT_CHECK_FRONT_PROMPT);
        params.addParam(MiSnapAPI.MiSnapTextCheckBackPrompt, DEFAULT_TEXT_CHECK_BACK_PROMPT);
        params.addParam(MiSnapAPI.CreditCardGuideColor, Integer.toHexString(DEFAULT_CREDIT_CARD_GUIDE_COLOR));
        params.addParam(MiSnapAPI.CreditCardRequireCVV, false);
        params.addParam(MiSnapAPI.CreditCardRequireExpiry, false);
        params.addParam(MiSnapAPI.CreditCardSuppressConfirmScreen, false);
        params.addParam(MiSnapAPI.BarCodeTypes, 64);
        params.addParam(MiSnapAPI.BarCodeDirections, 1);
        params.addParam(MiSnapAPI.BarCodeScanRegion, DEFAULT_BARCODE_SCANNING_REGION);
        return params.build().toString();
    }
}
