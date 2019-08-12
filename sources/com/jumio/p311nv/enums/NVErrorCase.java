package com.jumio.p311nv.enums;

import android.content.Context;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.sdk.exception.JumioErrorCase;
import org.jmrtd.cbeff.ISO781611;
import org.spongycastle.crypto.tls.CipherSuite;
import p005cn.jpush.android.Configs;

/* renamed from: com.jumio.nv.enums.NVErrorCase */
public enum NVErrorCase implements JumioErrorCase {
    REQUEST_SETTINGS_FAILED(100, NVStrings.ERROR_NETWORK_PROBLEMS, true),
    REQUEST_INITIATE_FAILED(110, NVStrings.ERROR_NETWORK_PROBLEMS, true),
    REQUEST_ADD_PART_FAILED(ISO781611.BIOMETRIC_SUBTYPE_TAG, NVStrings.ERROR_NETWORK_PROBLEMS, true),
    REQUEST_DATA_FAILED(CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA, NVStrings.ERROR_NETWORK_PROBLEMS, true),
    REQUEST_EXTRACT_DATA_FAILED(150, NVStrings.ERROR_NETWORK_PROBLEMS, true),
    REQUEST_FINALIZE_FAILED(160, NVStrings.ERROR_NETWORK_PROBLEMS, true),
    CERTIFICATE_ERROR(200, NVStrings.ERROR_AUTH_FAILED, false),
    AUTH_FAILED(210, NVStrings.ERROR_AUTH_FAILED, false),
    INVALID_CREDENTIALS(220, NVStrings.ERROR_AUTH_FAILED, false),
    DEVICE_IS_OFFLINE(230, NVStrings.ERROR_DEVICE_IS_OFFLINE, true),
    OCR_LOADING_FAILED(240, NVStrings.ERROR_STARTUP_ERROR, false),
    CANCEL_TYPE_USER(250, NVStrings.ERROR_CANCELLED_BY_USER, false),
    NO_CAMERA_CONNECTION(Configs.BUILD_ID_BASE, NVStrings.ERROR_NO_CAMERA_CONNECTION, false),
    ALE_KEY_NOT_VALID(280, NVStrings.ERROR_CERTIFICATE_NOT_VALID_ANYMORE, false);
    
    protected int code;
    protected String message;
    protected boolean retry;

    private NVErrorCase(int i, String str, boolean z) {
        this.code = i;
        this.message = str;
        this.retry = z;
    }

    public static NVErrorCase fromCode(int i) {
        switch (i) {
            case 100:
                return REQUEST_SETTINGS_FAILED;
            case 110:
                return REQUEST_INITIATE_FAILED;
            case ISO781611.BIOMETRIC_SUBTYPE_TAG /*130*/:
                return REQUEST_ADD_PART_FAILED;
            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA /*140*/:
                return REQUEST_DATA_FAILED;
            case 150:
                return REQUEST_EXTRACT_DATA_FAILED;
            case 160:
                return REQUEST_FINALIZE_FAILED;
            case 200:
                return CERTIFICATE_ERROR;
            case 210:
                return AUTH_FAILED;
            case 220:
                return INVALID_CREDENTIALS;
            case 230:
                return DEVICE_IS_OFFLINE;
            case 240:
                return OCR_LOADING_FAILED;
            case 250:
                return CANCEL_TYPE_USER;
            case Configs.BUILD_ID_BASE /*260*/:
                return NO_CAMERA_CONNECTION;
            default:
                return null;
        }
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public String localizedMessage(Context context) {
        return NVStrings.getExternalString(context, this.message);
    }

    public boolean retry() {
        return this.retry;
    }
}
