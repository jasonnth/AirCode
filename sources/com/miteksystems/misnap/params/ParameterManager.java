package com.miteksystems.misnap.params;

import android.os.Build.VERSION;
import com.miteksystems.misnap.p312a.C4544a;
import com.miteksystems.misnap.p312a.C4551e;
import com.miteksystems.misnap.p312a.C4552f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParameterManager {
    private static final int BASE_16 = 16;
    private static final int MAX_LEN_DOC_TYPE_OVERLAY_LABEL = 40;
    private static final int MAX_LEN_MT_LICENSE_KEY = 255;
    private static final int MAX_LEN_PDF417_STRING = 600;
    private int mAllowScreenshots = 0;
    private int mAngleThreshold = 150;
    private int mAutoFocusMode = 3;
    private int mBarCodeDirections = 1;
    private int[] mBarCodeScanRegion = MiSnapApiConstants.DEFAULT_BARCODE_SCANNING_REGION;
    private int mBarCodeTypes = 64;
    private int mBrightnessThreshold = 330;
    private int mCaptureMode = 2;
    private int mContrastThreshold = 600;
    private int mCornerConfidence = 600;
    private int mCreditCardGuideColor = MiSnapApiConstants.DEFAULT_CREDIT_CARD_GUIDE_COLOR;
    private boolean mCreditCardRequireCVV = false;
    private boolean mCreditCardRequireExpiry = false;
    private boolean mCreditCardSuppressConfirmScreen = true;
    private boolean mFlattenAndCrop = false;
    private int mImageHorizontalPixels = MiSnapApiConstants.DEFAULT_IMAGE_HORIZONTAL_PIXELS;
    private int mImageQuality = 50;
    private String mJobName = "";
    private String mMDVersion = "";
    private String mMIPVersion = "";
    private int mMaxBrightnessThreshold = MiSnapApiConstants.DEFAULT_MAX_BRIGHTNESS;
    public JSONObject mMiSnapChangedValues;
    private int mMiSnapForcedFocusDelay = 10000;
    private int mMiSnapLockView = 0;
    public String mMiSnapParameters;
    private int mMicrConfidence = 0;
    private int mMinPadding = 7;
    private int mNoGlareThreshold = 0;
    private int mSharpnessThreshold = 600;
    private String mShortDescription = "";
    private int mSolidBackgroundThreshold = MiSnapApiConstants.DEFAULT_SOLID_BACKGROUND;
    private String mTestScienceMode = "";
    private String mTestScienceSessionName = "";
    private String mTextCheckBackPrompt = MiSnapApiConstants.DEFAULT_TEXT_CHECK_BACK_PROMPT;
    private String mTextCheckFrontPrompt = MiSnapApiConstants.DEFAULT_TEXT_CHECK_FRONT_PROMPT;
    private int mTorchMode = 1;
    private int mUseFrontCamera;
    private int mUsePortraitOrientation = 0;
    private int mViewfinderMinHorizontalFill = 700;

    public int verifyThem(String str, int i) {
        boolean z;
        C4552f aVar;
        int i2 = SDKConstants.RESULT_SUCCESS;
        this.mMiSnapChangedValues = new JSONObject();
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.mMiSnapParameters = str;
            try {
                if (!jSONObject.has(MiSnapAPI.MiSnapDocumentType)) {
                    return SDKConstants.ERROR_PROCESSING_JOB_PARAMETERS;
                }
                this.mJobName = jSONObject.getString(MiSnapAPI.MiSnapDocumentType);
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapUseFrontCamera)) {
                        this.mUseFrontCamera = jSONObject.getInt(MiSnapAPI.MiSnapUseFrontCamera);
                        this.mUseFrontCamera = validateRange(this.mUseFrontCamera, 0, 1, MiSnapAPI.MiSnapUseFrontCamera);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.mUseFrontCamera = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapUseFrontCamera, this.mUseFrontCamera);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapCaptureMode)) {
                        this.mCaptureMode = jSONObject.getInt(MiSnapAPI.MiSnapCaptureMode);
                        if (this.mCaptureMode <= 0) {
                            this.mCaptureMode = 1;
                            addToChangedValues(MiSnapAPI.MiSnapCaptureMode, this.mCaptureMode);
                        } else if (this.mCaptureMode > 2) {
                            this.mCaptureMode = 2;
                            addToChangedValues(MiSnapAPI.MiSnapCaptureMode, this.mCaptureMode);
                        }
                    }
                    if (this.mUseFrontCamera == 1) {
                        aVar = new C4551e();
                    } else {
                        aVar = new C4544a();
                    }
                    if (VERSION.SDK_INT < aVar.mo44338g() || this.mJobName.equalsIgnoreCase(MiSnapApiConstants.PARAMETER_DOCTYPE_VEHICLE_IDENTIFICATION_NUMBER)) {
                        this.mCaptureMode = 1;
                        addToChangedValues(MiSnapAPI.MiSnapCaptureMode, this.mCaptureMode);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    this.mCaptureMode = 2;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapCaptureMode, this.mCaptureMode);
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapViewfinderMinHorizontalFill)) {
                        this.mViewfinderMinHorizontalFill = jSONObject.getInt(MiSnapAPI.MiSnapViewfinderMinHorizontalFill);
                    } else {
                        this.mViewfinderMinHorizontalFill = getDocSpecificMinHorizontalFill(this.mJobName);
                    }
                    this.mViewfinderMinHorizontalFill = validateRange(this.mViewfinderMinHorizontalFill, 400, 1000, MiSnapAPI.MiSnapViewfinderMinHorizontalFill);
                } catch (Exception e5) {
                    e5.printStackTrace();
                    this.mViewfinderMinHorizontalFill = 400;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapViewfinderMinHorizontalFill, this.mViewfinderMinHorizontalFill);
                    } catch (JSONException e6) {
                        e6.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapMinPadding)) {
                        this.mMinPadding = jSONObject.getInt(MiSnapAPI.MiSnapMinPadding);
                        this.mMinPadding = validateRange(this.mMinPadding, 0, 30, MiSnapAPI.MiSnapMinPadding);
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                    this.mMinPadding = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapMinPadding, this.mMinPadding);
                    } catch (JSONException e8) {
                        e8.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapImageQuality)) {
                        this.mImageQuality = jSONObject.getInt(MiSnapAPI.MiSnapImageQuality);
                    } else {
                        this.mImageQuality = getDocSpecificImageQuality(this.mJobName);
                    }
                    this.mImageQuality = validateRange(this.mImageQuality, 0, 100, MiSnapAPI.MiSnapImageQuality);
                } catch (Exception e9) {
                    e9.printStackTrace();
                    this.mImageQuality = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapImageQuality, this.mImageQuality);
                    } catch (JSONException e10) {
                        e10.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapBrightness)) {
                        this.mBrightnessThreshold = jSONObject.getInt(MiSnapAPI.MiSnapBrightness);
                    } else {
                        this.mBrightnessThreshold = getDocSpecificMinBrightness(this.mJobName);
                    }
                    this.mBrightnessThreshold = validateRange(this.mBrightnessThreshold, 0, 1000, MiSnapAPI.MiSnapBrightness);
                } catch (Exception e11) {
                    e11.printStackTrace();
                    this.mBrightnessThreshold = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapBrightness, this.mBrightnessThreshold);
                    } catch (JSONException e12) {
                        e12.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapMaxBrightness)) {
                        this.mMaxBrightnessThreshold = jSONObject.getInt(MiSnapAPI.MiSnapMaxBrightness);
                        this.mMaxBrightnessThreshold = validateRange(this.mMaxBrightnessThreshold, 0, 1000, MiSnapAPI.MiSnapMaxBrightness);
                    }
                } catch (Exception e13) {
                    e13.printStackTrace();
                    this.mMaxBrightnessThreshold = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapMaxBrightness, this.mMaxBrightnessThreshold);
                    } catch (JSONException e14) {
                        e14.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapSharpness)) {
                        this.mSharpnessThreshold = jSONObject.getInt(MiSnapAPI.MiSnapSharpness);
                    } else {
                        this.mSharpnessThreshold = getDocSpecificSharpness(this.mJobName, i);
                    }
                    this.mSharpnessThreshold = validateRange(this.mSharpnessThreshold, 0, 1000, MiSnapAPI.MiSnapSharpness);
                } catch (Exception e15) {
                    e15.printStackTrace();
                    this.mSharpnessThreshold = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapSharpness, this.mSharpnessThreshold);
                    } catch (JSONException e16) {
                        e16.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapAngle)) {
                        this.mAngleThreshold = jSONObject.getInt(MiSnapAPI.MiSnapAngle);
                    } else {
                        this.mAngleThreshold = getDocSpecificAngle(this.mJobName);
                    }
                    this.mAngleThreshold = validateRange(this.mAngleThreshold, 2, 1000, MiSnapAPI.MiSnapAngle);
                } catch (Exception e17) {
                    e17.printStackTrace();
                    this.mAngleThreshold = 2;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapAngle, this.mAngleThreshold);
                    } catch (JSONException e18) {
                        e18.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapContrast)) {
                        this.mContrastThreshold = jSONObject.getInt(MiSnapAPI.MiSnapContrast);
                    } else {
                        this.mContrastThreshold = getDocSpecificContrast(this.mJobName);
                    }
                    this.mContrastThreshold = validateRange(this.mContrastThreshold, 0, 1000, MiSnapAPI.MiSnapContrast);
                } catch (Exception e19) {
                    e19.printStackTrace();
                    this.mContrastThreshold = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapContrast, this.mContrastThreshold);
                    } catch (JSONException e20) {
                        e20.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapSolidBackground)) {
                        this.mSolidBackgroundThreshold = jSONObject.getInt(MiSnapAPI.MiSnapSolidBackground);
                    } else {
                        this.mSolidBackgroundThreshold = getDocSpecificSolidBackground(this.mJobName);
                    }
                    this.mSolidBackgroundThreshold = validateRange(this.mSolidBackgroundThreshold, 0, 1000, MiSnapAPI.MiSnapAngle);
                } catch (Exception e21) {
                    e21.printStackTrace();
                    this.mSolidBackgroundThreshold = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapSolidBackground, this.mSolidBackgroundThreshold);
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapNoGlare)) {
                        this.mNoGlareThreshold = jSONObject.getInt(MiSnapAPI.MiSnapNoGlare);
                    } else {
                        this.mNoGlareThreshold = getDocSpecificGlare(this.mJobName);
                    }
                    this.mNoGlareThreshold = validateRange(this.mNoGlareThreshold, 0, 1000, MiSnapAPI.MiSnapNoGlare);
                } catch (Exception e23) {
                    e23.printStackTrace();
                    this.mNoGlareThreshold = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapNoGlare, this.mNoGlareThreshold);
                    } catch (JSONException e24) {
                        e24.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapShortDescription)) {
                        this.mShortDescription = jSONObject.getString(MiSnapAPI.MiSnapShortDescription);
                    }
                } catch (Exception e25) {
                    e25.printStackTrace();
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapTorchMode)) {
                        this.mTorchMode = jSONObject.getInt(MiSnapAPI.MiSnapTorchMode);
                        this.mTorchMode = validateRange(this.mTorchMode, 0, 2, MiSnapAPI.MiSnapTorchMode);
                    }
                } catch (Exception e26) {
                    e26.printStackTrace();
                    this.mTorchMode = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapTorchMode, this.mTorchMode);
                    } catch (JSONException e27) {
                        e27.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapFocusMode)) {
                        this.mAutoFocusMode = jSONObject.getInt(MiSnapAPI.MiSnapFocusMode);
                        this.mAutoFocusMode = validateRange(this.mAutoFocusMode, 1, 3, MiSnapAPI.MiSnapFocusMode);
                    }
                } catch (Exception e28) {
                    e28.printStackTrace();
                    this.mAutoFocusMode = 1;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapFocusMode, this.mAutoFocusMode);
                    } catch (JSONException e29) {
                        e29.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapForcedFocusDelay)) {
                        this.mMiSnapForcedFocusDelay = jSONObject.getInt(MiSnapAPI.MiSnapForcedFocusDelay);
                        this.mMiSnapForcedFocusDelay = validateRange(this.mMiSnapForcedFocusDelay, 0, MiSnapApiConstants.CAMERA_FORCE_FOCUS_START_TIMEOUT_MAX_VALUE, MiSnapAPI.MiSnapForcedFocusDelay);
                    }
                } catch (Exception e30) {
                    e30.printStackTrace();
                    this.mMiSnapForcedFocusDelay = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapForcedFocusDelay, this.mMiSnapForcedFocusDelay);
                    } catch (JSONException e31) {
                        e31.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapLockView)) {
                        this.mMiSnapLockView = jSONObject.getInt(MiSnapAPI.MiSnapLockView);
                        this.mMiSnapLockView = validateRange(this.mMiSnapLockView, 0, 1, MiSnapAPI.MiSnapLockView);
                    }
                } catch (Exception e32) {
                    e32.printStackTrace();
                    this.mMiSnapLockView = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapLockView, this.mMiSnapLockView);
                    } catch (JSONException e33) {
                        e33.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapCornerConfidence)) {
                        this.mCornerConfidence = jSONObject.getInt(MiSnapAPI.MiSnapCornerConfidence);
                    } else {
                        this.mCornerConfidence = getDocSpecificCornerConfidence(this.mJobName);
                    }
                    this.mCornerConfidence = validateRange(this.mCornerConfidence, 0, 1000, MiSnapAPI.MiSnapCornerConfidence);
                } catch (Exception e34) {
                    e34.printStackTrace();
                    this.mCornerConfidence = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapCornerConfidence, this.mCornerConfidence);
                    } catch (JSONException e35) {
                        e35.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapMICRConfidence)) {
                        this.mMicrConfidence = jSONObject.getInt(MiSnapAPI.MiSnapMICRConfidence);
                    } else {
                        this.mMicrConfidence = getDocSpecificMicrConfidence(this.mJobName);
                    }
                    this.mMicrConfidence = validateRange(this.mMicrConfidence, 0, 1000, MiSnapAPI.MiSnapMICRConfidence);
                } catch (Exception e36) {
                    e36.printStackTrace();
                    this.mMicrConfidence = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapMICRConfidence, this.mMicrConfidence);
                    } catch (JSONException e37) {
                        e37.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapUsePortraitOrientation)) {
                        this.mUsePortraitOrientation = jSONObject.getInt(MiSnapAPI.MiSnapUsePortraitOrientation);
                        this.mUsePortraitOrientation = validateRange(this.mUsePortraitOrientation, 0, 1, MiSnapAPI.MiSnapUsePortraitOrientation);
                    }
                } catch (Exception e38) {
                    e38.printStackTrace();
                    this.mUsePortraitOrientation = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapUsePortraitOrientation, this.mUsePortraitOrientation);
                    } catch (JSONException e39) {
                        e39.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapAllowScreenshots)) {
                        this.mAllowScreenshots = jSONObject.getInt(MiSnapAPI.MiSnapAllowScreenshots);
                        this.mAllowScreenshots = validateRange(this.mAllowScreenshots, 0, 1, MiSnapAPI.MiSnapAllowScreenshots);
                    }
                } catch (Exception e40) {
                    e40.printStackTrace();
                    this.mAllowScreenshots = 0;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapAllowScreenshots, this.mAllowScreenshots);
                    } catch (JSONException e41) {
                        e41.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapMaxImageHeightAndWidth)) {
                        this.mImageHorizontalPixels = jSONObject.getInt(MiSnapAPI.MiSnapMaxImageHeightAndWidth);
                        this.mImageHorizontalPixels = validateRange(this.mImageHorizontalPixels, 300, MiSnapApiConstants.IMAGE_HORIZONTAL_PIXELS_PARAM_MAX_VALUE, MiSnapAPI.MiSnapMaxImageHeightAndWidth);
                    }
                } catch (Exception e42) {
                    e42.printStackTrace();
                    this.mImageHorizontalPixels = 300;
                    try {
                        addToChangedValues(MiSnapAPI.MiSnapMaxImageHeightAndWidth, this.mImageHorizontalPixels);
                    } catch (JSONException e43) {
                        e43.printStackTrace();
                    }
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapTextCheckFrontPrompt)) {
                        this.mTextCheckFrontPrompt = jSONObject.getString(MiSnapAPI.MiSnapTextCheckFrontPrompt);
                    }
                    if (jSONObject.has(MiSnapAPI.MiSnapTextCheckBackPrompt)) {
                        this.mTextCheckBackPrompt = jSONObject.getString(MiSnapAPI.MiSnapTextCheckBackPrompt);
                    }
                    if (this.mTextCheckFrontPrompt.length() > 40) {
                        this.mTextCheckFrontPrompt = this.mTextCheckFrontPrompt.substring(0, 40);
                    }
                    if (this.mTextCheckBackPrompt.length() > 40) {
                        this.mTextCheckBackPrompt = this.mTextCheckBackPrompt.substring(0, 40);
                    }
                } catch (Exception e44) {
                    e44.printStackTrace();
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MiSnapFlattenAndCrop)) {
                        if (jSONObject.getInt(MiSnapAPI.MiSnapFlattenAndCrop) != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        this.mFlattenAndCrop = z;
                    }
                } catch (Exception e45) {
                    e45.printStackTrace();
                }
                try {
                    if (jSONObject.has(MiSnapAPI.MIPVersion)) {
                        this.mMIPVersion = jSONObject.getString(MiSnapAPI.MIPVersion);
                    }
                    if (jSONObject.has(MiSnapAPI.MDVersion)) {
                        this.mMDVersion = jSONObject.getString(MiSnapAPI.MDVersion);
                    }
                } catch (Exception e46) {
                    e46.printStackTrace();
                }
                try {
                    if (jSONObject.has(MiSnapAPI.CreditCardGuideColor)) {
                        String string = jSONObject.getString(MiSnapAPI.CreditCardGuideColor);
                        if (string.startsWith("0x")) {
                            string = string.substring(2);
                        }
                        this.mCreditCardGuideColor = Integer.parseInt(string, 16);
                    }
                } catch (Exception e47) {
                    e47.printStackTrace();
                }
                try {
                    if (jSONObject.has(MiSnapAPI.CreditCardRequireCVV)) {
                        this.mCreditCardRequireCVV = jSONObject.getBoolean(MiSnapAPI.CreditCardRequireCVV);
                    }
                } catch (JSONException e48) {
                    e48.printStackTrace();
                }
                try {
                    if (jSONObject.has(MiSnapAPI.CreditCardRequireExpiry)) {
                        this.mCreditCardRequireExpiry = jSONObject.getBoolean(MiSnapAPI.CreditCardRequireExpiry);
                    }
                } catch (JSONException e49) {
                    e49.printStackTrace();
                }
                try {
                    if (jSONObject.has(MiSnapAPI.CreditCardSuppressConfirmScreen)) {
                        this.mCreditCardSuppressConfirmScreen = jSONObject.getBoolean(MiSnapAPI.CreditCardSuppressConfirmScreen);
                    }
                } catch (JSONException e50) {
                    e50.printStackTrace();
                }
                try {
                    if (this.mJobName.equals(MiSnapApiConstants.PARAMETER_DOCTYPE_BARCODES)) {
                        if (jSONObject.has(MiSnapAPI.BarCodeTypes)) {
                            this.mBarCodeTypes = jSONObject.getInt(MiSnapAPI.BarCodeTypes);
                        }
                    } else if (this.mJobName.equals("PDF417")) {
                        this.mBarCodeTypes = 64;
                    }
                } catch (JSONException e51) {
                    e51.printStackTrace();
                }
                try {
                    if (jSONObject.has(MiSnapAPI.BarCodeDirections)) {
                        this.mBarCodeDirections = jSONObject.getInt(MiSnapAPI.BarCodeDirections);
                    }
                } catch (JSONException e52) {
                    e52.printStackTrace();
                }
                try {
                    if (jSONObject.has(MiSnapAPI.BarCodeScanRegion)) {
                        JSONArray jSONArray = jSONObject.getJSONArray(MiSnapAPI.BarCodeScanRegion);
                        if (jSONArray != null) {
                            this.mBarCodeScanRegion = new int[4];
                            int min = Math.min(4, jSONArray.length());
                            for (int i3 = 0; i3 < min; i3++) {
                                this.mBarCodeScanRegion[i3] = jSONArray.getInt(i3);
                            }
                        }
                    }
                } catch (JSONException e53) {
                    e53.printStackTrace();
                }
                try {
                    if (jSONObject.has(SDKConstants.EXTRA_TEST_SCIENCE_MODE)) {
                        this.mTestScienceMode = jSONObject.getString(SDKConstants.EXTRA_TEST_SCIENCE_MODE);
                    }
                } catch (Exception e54) {
                }
                try {
                    if (!jSONObject.has(SDKConstants.EXTRA_TEST_SCIENCE_SESSION_NAME)) {
                        return i2;
                    }
                    this.mTestScienceSessionName = jSONObject.getString(SDKConstants.EXTRA_TEST_SCIENCE_SESSION_NAME);
                    return i2;
                } catch (Exception e55) {
                    return i2;
                }
            } catch (Exception e56) {
                e56.printStackTrace();
                return SDKConstants.ERROR_PROCESSING_JOB_PARAMETERS;
            }
        } catch (Exception e57) {
            e57.printStackTrace();
            return SDKConstants.ERROR_PROCESSING_JOB_PARAMETERS;
        }
    }

    private void addToChangedValues(String str, int i) {
        this.mMiSnapChangedValues.put(str, String.valueOf(i));
    }

    private int validateRange(int i, int i2, int i3, String str) {
        boolean z = true;
        if (i >= i2) {
            if (i > i3) {
                i2 = i3;
            } else {
                z = false;
                i2 = i;
            }
        }
        if (z) {
            try {
                addToChangedValues(str, i2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i2;
    }

    public static int getDocSpecificSharpness(String str, int i) {
        int i2 = 550;
        if (str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_FRONT) || str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_AUTOMATIC_CHECK_HANDLING)) {
            i2 = 600;
        } else if (!str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_BACK)) {
            if (!str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_AUTO_INSURANCE) && !str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_BILL_PAY) && !str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_W2_FORM)) {
                if (str.contains(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
                    i2 = 650;
                } else {
                    str.contains("PASSPORT");
                }
            }
            i2 = 600;
        } else if (i >= SDKConstants.TABLET_MIN_SCREEN_SIZE_INCHES) {
        }
        if (VERSION.SDK_INT <= 9) {
            return (int) (((float) i2) * 0.4f);
        }
        if (VERSION.SDK_INT <= 10) {
            return (int) (((float) i2) * 0.5f);
        }
        if (VERSION.SDK_INT < 11) {
            return (int) (((float) i2) * 0.6f);
        }
        if (VERSION.SDK_INT < 14) {
            return (int) (((float) i2) * 0.8f);
        }
        return i2;
    }

    public static int getDocSpecificAngle(String str) {
        if (str != null && str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
        }
        return 150;
    }

    public static int getDocSpecificContrast(String str) {
        if (str == null) {
            return 600;
        }
        if (str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
            return 800;
        }
        if (str.startsWith("PASSPORT")) {
            return 650;
        }
        return 600;
    }

    public static int getDocSpecificSolidBackground(String str) {
        if (str == null) {
            return MiSnapApiConstants.DEFAULT_SOLID_BACKGROUND;
        }
        if (str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
            return MiSnapApiConstants.DEFAULT_SOLID_BACKGROUND_DRIVER_LICENSE;
        }
        if (str.startsWith("PASSPORT")) {
            return 800;
        }
        return MiSnapApiConstants.DEFAULT_SOLID_BACKGROUND;
    }

    public static int getDocSpecificMinHorizontalFill(String str) {
        if (str == null) {
            return 700;
        }
        if (str.startsWith("PASSPORT")) {
            return MiSnapApiConstants.DEFAULT_MIN_HORIZ_FILL_PASSPORT;
        }
        if (str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
            return 650;
        }
        return 700;
    }

    public static int getDocSpecificImageQuality(String str) {
        if (str == null) {
            return 50;
        }
        if (str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
            return 60;
        }
        if (str.startsWith("PASSPORT")) {
            return 60;
        }
        return 50;
    }

    public static int getDocSpecificMinBrightness(String str) {
        if (str == null || str.startsWith("Check") || str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_AUTOMATIC_CHECK_HANDLING)) {
            return 330;
        }
        if (str.startsWith("PASSPORT")) {
            return 200;
        }
        if (str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
        }
        return 330;
    }

    public static int getDocSpecificGlare(String str) {
        if (str.startsWith("PASSPORT") || str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
            return 1000;
        }
        return 0;
    }

    public static int getDocSpecificCornerConfidence(String str) {
        if (str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_FRONT) || str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_BACK) || str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_AUTOMATIC_CHECK_HANDLING)) {
            return 600;
        }
        if (str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE)) {
            return MiSnapApiConstants.DEFAULT_CORNER_CONFIDENCE_DRIVER_LICENSE;
        }
        str.startsWith("PASSPORT");
        return 600;
    }

    public static int getDocSpecificMicrConfidence(String str) {
        if (str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_FRONT) || str.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_AUTOMATIC_CHECK_HANDLING)) {
            return 450;
        }
        if (str.startsWith("PASSPORT")) {
            return MiSnapApiConstants.DEFAULT_MICR_CONFIDENCE_FOR_PASSPORT;
        }
        return 0;
    }

    public String getmJobName() {
        return this.mJobName;
    }

    public int getmCaptureMode() {
        return this.mCaptureMode;
    }

    public void setmCaptureMode(int i) {
        this.mCaptureMode = i;
    }

    public boolean isCurrentModeVideo() {
        return getmCaptureMode() == 2;
    }

    public int getmImageHorizontalPixels() {
        return this.mImageHorizontalPixels;
    }

    public int getmMiSnapViewfinderMinHorizontalFill() {
        return this.mViewfinderMinHorizontalFill;
    }

    public int getMinPadding() {
        return this.mMinPadding;
    }

    public int getmImageQuality() {
        return this.mImageQuality;
    }

    public int getmBrightnessThreshold() {
        return this.mBrightnessThreshold;
    }

    public int getmContrastThreshold() {
        return this.mContrastThreshold;
    }

    public int getmSolidBackgroundThreshold() {
        return this.mSolidBackgroundThreshold;
    }

    public int getmMaxBrightnessThreshold() {
        return this.mMaxBrightnessThreshold;
    }

    public int getmSharpnessThreshold() {
        return this.mSharpnessThreshold;
    }

    public int getmAngleThreshold() {
        return this.mAngleThreshold;
    }

    public int getmNoGlareThreshold() {
        return this.mNoGlareThreshold;
    }

    public String getmShortDescription() {
        return this.mShortDescription;
    }

    public int getmTorchMode() {
        return this.mTorchMode;
    }

    public int getmFocusMode() {
        return this.mAutoFocusMode;
    }

    public int getmMiSnapForcedFocusDelay() {
        return this.mMiSnapForcedFocusDelay;
    }

    public int getmMiSnapLockView() {
        return this.mMiSnapLockView;
    }

    public int getCornerConfidence() {
        return this.mCornerConfidence;
    }

    public int getMicrConfidence() {
        return this.mMicrConfidence;
    }

    public int getmAllowScreenshots() {
        return this.mAllowScreenshots;
    }

    public int getUseFrontCamera() {
        return this.mUseFrontCamera;
    }

    public int getUsePortraitOrientation() {
        return this.mUsePortraitOrientation;
    }

    public String getTextCheckBackPrompt() {
        return this.mTextCheckBackPrompt;
    }

    public String getTextCheckFrontPrompt() {
        return this.mTextCheckFrontPrompt;
    }

    public String getMIPVersion() {
        return this.mMIPVersion;
    }

    public String getMDVersion() {
        return this.mMDVersion;
    }

    public boolean useDefaultCheckFrontText() {
        return this.mTextCheckFrontPrompt.equals(MiSnapApiConstants.DEFAULT_TEXT_CHECK_FRONT_PROMPT);
    }

    public boolean useDefaultCheckBackText() {
        return this.mTextCheckBackPrompt.equals(MiSnapApiConstants.DEFAULT_TEXT_CHECK_BACK_PROMPT);
    }

    public boolean isCheckFront() {
        return this.mJobName != null && (this.mJobName.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_FRONT) || this.mJobName.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_AUTOMATIC_CHECK_HANDLING));
    }

    public boolean isCheckBack() {
        return this.mJobName != null && this.mJobName.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_BACK);
    }

    public boolean isCheck() {
        return this.mJobName != null && (this.mJobName.startsWith("Check") || this.mJobName.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_AUTOMATIC_CHECK_HANDLING));
    }

    public boolean isPassport() {
        return this.mJobName != null && this.mJobName.startsWith("PASSPORT");
    }

    public boolean isLicense() {
        return this.mJobName != null && this.mJobName.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE);
    }

    public boolean isFlattenAndCropEnabled() {
        return false;
    }

    public int getCreditCardGuideColor() {
        return this.mCreditCardGuideColor;
    }

    public boolean getCreditCardRequireCVV() {
        return this.mCreditCardRequireCVV;
    }

    public boolean getCreditCardRequireExpiry() {
        return this.mCreditCardRequireExpiry;
    }

    public boolean getCreditCardSuppressConfirmScreen() {
        return this.mCreditCardSuppressConfirmScreen;
    }

    public int getBarCodeTypes() {
        return this.mBarCodeTypes;
    }

    public int getBarCodeDirections() {
        return this.mBarCodeDirections;
    }

    public int[] getBarCodeScanRegion() {
        return this.mBarCodeScanRegion;
    }

    public boolean isTestScienceMode() {
        return !"".equals(this.mTestScienceMode);
    }

    public boolean isTestScienceCaptureMode() {
        return this.mTestScienceMode.equals(SDKConstants.TEST_SCIENCE_MODE_CAPTURE);
    }

    public boolean isTestScienceReplayMode() {
        return this.mTestScienceMode.equals(SDKConstants.TEST_SCIENCE_MODE_REPLAY);
    }

    public boolean isTestScienceOneDrawableMode() {
        return this.mTestScienceMode.equals(SDKConstants.TEST_SCIENCE_MODE_ONE_DRAWABLE);
    }

    public String getTestScienceSessionName() {
        return this.mTestScienceSessionName;
    }
}
