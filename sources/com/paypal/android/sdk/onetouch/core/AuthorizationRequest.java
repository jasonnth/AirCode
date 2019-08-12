package com.paypal.android.sdk.onetouch.core;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Base64;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.braintreepayments.api.Json;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.base.DeviceInspector;
import com.paypal.android.sdk.onetouch.core.config.ConfigEndpoint;
import com.paypal.android.sdk.onetouch.core.config.OAuth2Recipe;
import com.paypal.android.sdk.onetouch.core.config.OtcConfiguration;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.encryption.EncryptionUtils;
import com.paypal.android.sdk.onetouch.core.encryption.OtcCrypto;
import com.paypal.android.sdk.onetouch.core.enums.Protocol;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import com.paypal.android.sdk.onetouch.core.enums.ResponseType;
import com.paypal.android.sdk.onetouch.core.exception.BrowserSwitchException;
import com.paypal.android.sdk.onetouch.core.exception.InvalidEncryptionDataException;
import com.paypal.android.sdk.onetouch.core.exception.ResponseParsingException;
import com.paypal.android.sdk.onetouch.core.fpti.TrackingPoint;
import com.paypal.android.sdk.onetouch.core.network.EnvironmentManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthorizationRequest extends Request<AuthorizationRequest> implements Parcelable {
    public static final Creator<AuthorizationRequest> CREATOR = new Creator<AuthorizationRequest>() {
        public AuthorizationRequest[] newArray(int size) {
            return new AuthorizationRequest[size];
        }

        public AuthorizationRequest createFromParcel(Parcel source) {
            return new AuthorizationRequest(source);
        }
    };
    private final Pattern WHITESPACE_PATTERN;
    private final HashMap<String, String> mAdditionalPayloadAttributes;
    private final byte[] mEncryptionKey;
    private final String mMsgGuid;
    private final OtcCrypto mOtcCrypto;
    private String mPrivacyUrl;
    private final HashSet<String> mScopes;
    private String mUserAgreementUrl;

    private class RFC3339DateFormat extends SimpleDateFormat {
        RFC3339DateFormat() {
            super("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        }
    }

    public AuthorizationRequest(Context context) {
        this.WHITESPACE_PATTERN = Pattern.compile("\\s");
        this.mOtcCrypto = new OtcCrypto();
        clientMetadataId(PayPalOneTouchCore.getClientMetadataId(context));
        this.mMsgGuid = UUID.randomUUID().toString();
        this.mEncryptionKey = this.mOtcCrypto.generateRandom256BitKey();
        this.mAdditionalPayloadAttributes = new HashMap<>();
        this.mScopes = new HashSet<>();
    }

    public AuthorizationRequest withAdditionalPayloadAttribute(String key, String value) {
        this.mAdditionalPayloadAttributes.put(key, value);
        return this;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getAdditionalPayloadAttributes() {
        return new HashMap(this.mAdditionalPayloadAttributes);
    }

    public AuthorizationRequest withScopeValue(String scopeValue) {
        if (this.WHITESPACE_PATTERN.matcher(scopeValue).find()) {
            throw new IllegalArgumentException("scopes must be provided individually, with no whitespace");
        }
        this.mScopes.add(scopeValue);
        return this;
    }

    private Set<String> getScopes() {
        return new HashSet(this.mScopes);
    }

    public String getScopeString() {
        return TextUtils.join(" ", getScopes());
    }

    public AuthorizationRequest privacyUrl(String privacyUrl) {
        this.mPrivacyUrl = privacyUrl;
        return this;
    }

    public String getPrivacyUrl() {
        return this.mPrivacyUrl;
    }

    public AuthorizationRequest userAgreementUrl(String userAgreementUrl) {
        this.mUserAgreementUrl = userAgreementUrl;
        return this;
    }

    public String getUserAgreementUrl() {
        return this.mUserAgreementUrl;
    }

    public String getBrowserSwitchUrl(Context context, OtcConfiguration config) throws CertificateException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, JSONException, BadPaddingException, InvalidEncryptionDataException, InvalidKeyException {
        ConfigEndpoint configEndpoint = config.getBrowserOauth2Config(getScopes()).getEndpoint(getEnvironment());
        X509Certificate cert = EncryptionUtils.getX509CertificateFromBase64String(configEndpoint.certificate);
        return configEndpoint.url + "?payload=" + URLEncoder.encode(buildPayload(context, cert), "utf-8") + "&payloadEnc=" + URLEncoder.encode(buildPayloadEnc(cert), "utf-8") + "&x-source=" + context.getPackageName() + "&x-success=" + getSuccessUrl() + "&x-cancel=" + getCancelUrl();
    }

    public Recipe getBrowserSwitchRecipe(OtcConfiguration config) {
        return config.getBrowserOauth2Config(getScopes());
    }

    private boolean isValidResponse(String msgGUID) {
        return this.mMsgGuid.equals(msgGUID);
    }

    private String buildPayloadEnc(Certificate cert) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidEncryptionDataException, InvalidKeyException, JSONException {
        return Base64.encodeToString(this.mOtcCrypto.encryptRSAData(getJsonObjectToEncrypt().toString().getBytes(), cert), 2);
    }

    private JSONObject getJsonObjectToEncrypt() throws JSONException {
        JSONObject payloadEnc = new JSONObject();
        payloadEnc.put(ErfExperimentsModel.TIMESTAMP, new RFC3339DateFormat().format(new Date()));
        payloadEnc.put("msg_GUID", this.mMsgGuid);
        payloadEnc.put("sym_key", EncryptionUtils.byteArrayToHexString(this.mEncryptionKey));
        String deviceName = DeviceInspector.getDeviceName();
        payloadEnc.put("device_name", deviceName.substring(0, Math.min(deviceName.length(), 30)));
        return payloadEnc;
    }

    private String buildPayload(Context context, X509Certificate cert) {
        JSONObject payload = new JSONObject();
        try {
            payload.put("version", 3);
            payload.put("client_id", getClientId());
            payload.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, DeviceInspector.getApplicationInfoName(context));
            payload.put("environment", getEnvironment());
            payload.put("environment_url", EnvironmentManager.getEnvironmentUrl(getEnvironment()));
            payload.put("scope", getScopeString());
            payload.put(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, "code");
            payload.put("privacy_url", getPrivacyUrl());
            payload.put("agreement_url", getUserAgreementUrl());
            payload.put("client_metadata_id", getClientMetadataId());
            payload.put("key_id", cert.getSerialNumber());
            payload.put("android_chrome_available", isChromeAvailable(context));
            for (Entry<String, String> entry : this.mAdditionalPayloadAttributes.entrySet()) {
                payload.put((String) entry.getKey(), entry.getValue());
            }
            return Base64.encodeToString(payload.toString().getBytes(), 2);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isChromeAvailable(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.paypal.com"));
        intent.setPackage("com.android.chrome");
        return intent.resolveActivity(context.getPackageManager()) != null;
    }

    public Result parseBrowserResponse(ContextInspector contextInspector, Uri uri) {
        JSONObject payload;
        String status = uri.getLastPathSegment();
        String payloadEnc = uri.getQueryParameter("payloadEnc");
        try {
            payload = new JSONObject(new String(Base64.decode(uri.getQueryParameter("payload"), 0)));
        } catch (IllegalArgumentException | NullPointerException | JSONException e) {
            payload = new JSONObject();
        }
        if (Uri.parse(getSuccessUrl()).getLastPathSegment().equals(status)) {
            if (!payload.has("msg_GUID")) {
                return new Result((Throwable) new ResponseParsingException("Response incomplete"));
            }
            if (TextUtils.isEmpty(payloadEnc) || !isValidResponse(Json.optString(payload, "msg_GUID", ""))) {
                return new Result((Throwable) new ResponseParsingException("Response invalid"));
            }
            try {
                JSONObject decryptedPayloadEnc = getDecryptedPayload(payloadEnc);
                String error = Json.optString(payload, "error", "");
                if (TextUtils.isEmpty(error) || "null".equals(error)) {
                    return new Result(Json.optString(payload, "environment", ""), ResponseType.authorization_code, new JSONObject().put("code", decryptedPayloadEnc.getString("payment_code")), decryptedPayloadEnc.getString("email"));
                }
                return new Result((Throwable) new BrowserSwitchException(error));
            } catch (InvalidEncryptionDataException | IllegalArgumentException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | JSONException e2) {
                return new Result((Throwable) new ResponseParsingException((Throwable) e2));
            }
        } else if (!Uri.parse(getCancelUrl()).getLastPathSegment().equals(status)) {
            return new Result((Throwable) new ResponseParsingException("Response uri invalid"));
        } else {
            String error2 = Json.optString(payload, "error", "");
            if (TextUtils.isEmpty(error2) || "null".equals(error2)) {
                return new Result();
            }
            return new Result((Throwable) new BrowserSwitchException(error2));
        }
    }

    public boolean validateV1V2Response(ContextInspector contextInspector, Bundle extras) {
        return true;
    }

    public Recipe getRecipeToExecute(Context context, OtcConfiguration config) {
        for (OAuth2Recipe recipe : config.getOauth2Recipes()) {
            if (recipe.isValidForScopes(getScopes())) {
                if (RequestTarget.wallet == recipe.getTarget()) {
                    if (recipe.isValidAppTarget(context)) {
                        return recipe;
                    }
                } else if (RequestTarget.browser == recipe.getTarget()) {
                    try {
                        if (recipe.isValidBrowserTarget(context, getBrowserSwitchUrl(context, config))) {
                            return recipe;
                        }
                    } catch (InvalidEncryptionDataException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | CertificateException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | JSONException e) {
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    public void trackFpti(Context context, TrackingPoint trackingPoint, Protocol protocol) {
        Map<String, String> fptiDataBundle = new HashMap<>();
        fptiDataBundle.put("clid", getClientId());
        PayPalOneTouchCore.getFptiManager(context).trackFpti(trackingPoint, getEnvironment(), fptiDataBundle, protocol);
    }

    private JSONObject getDecryptedPayload(String payloadEnc) throws IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, BadPaddingException, InvalidEncryptionDataException, JSONException, IllegalArgumentException {
        return new JSONObject(new String(new OtcCrypto().decryptAESCTRData(Base64.decode(payloadEnc, 0), this.mEncryptionKey)));
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mPrivacyUrl);
        dest.writeString(this.mUserAgreementUrl);
        dest.writeSerializable(this.mScopes);
        dest.writeSerializable(this.mAdditionalPayloadAttributes);
        dest.writeString(this.mMsgGuid);
        dest.writeInt(this.mEncryptionKey.length);
        dest.writeByteArray(this.mEncryptionKey);
    }

    private AuthorizationRequest(Parcel source) {
        super(source);
        this.WHITESPACE_PATTERN = Pattern.compile("\\s");
        this.mOtcCrypto = new OtcCrypto();
        this.mPrivacyUrl = source.readString();
        this.mUserAgreementUrl = source.readString();
        this.mScopes = (HashSet) source.readSerializable();
        this.mAdditionalPayloadAttributes = (HashMap) source.readSerializable();
        this.mMsgGuid = source.readString();
        this.mEncryptionKey = new byte[source.readInt()];
        source.readByteArray(this.mEncryptionKey);
    }
}
