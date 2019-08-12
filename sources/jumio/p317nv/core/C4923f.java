package jumio.p317nv.core;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.commons.camera.CameraManager;
import com.jumio.commons.log.Log;
import com.jumio.commons.log.LogUtils;
import com.jumio.commons.obfuscate.StringObfuscater;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall.DynamicProvider;
import com.jumio.core.network.SimpleApiCall;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.p311nv.models.MerchantSettingsModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.persistence.DataAccess;
import java.util.ArrayList;
import java.util.Arrays;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: jumio.nv.core.f */
/* compiled from: InitiateCall */
public class C4923f extends SimpleApiCall<String> {

    /* renamed from: a */
    private MerchantSettingsModel f4801a;

    /* renamed from: b */
    private ServerSettingsModel f4802b;

    public C4923f(Context context, DynamicProvider dynamicProvider, MerchantSettingsModel merchantSettingsModel, ServerSettingsModel serverSettingsModel, Subscriber<String> subscriber) {
        super(context, dynamicProvider, subscriber);
        this.f4801a = merchantSettingsModel;
        this.f4802b = serverSettingsModel;
    }

    public String getUri() {
        return StringObfuscater.format(new byte[]{ISOFileInfo.FCI_BYTE, -17, -97, ISOFileInfo.DATA_BYTES2, -78}, 6070354384363082626L);
    }

    /* access modifiers changed from: protected */
    public void responseReceived(int i, String str, long j, String str2) {
        super.responseReceived(i, str, j, str2);
        JumioAnalytics.add(MobileEvents.networkRequest(JumioAnalytics.getSessionId(), getClass().getName(), i, str, j));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String parseResponse(String str) {
        try {
            String string = new JSONObject(str).getString("jumioIdScanReference");
            InitiateModel initiateModel = (InitiateModel) DataAccess.load(this.context, InitiateModel.class);
            if (initiateModel == null) {
                initiateModel = new InitiateModel();
            }
            initiateModel.setJumioScanRef(string);
            if (Log.isFileLoggingActivated()) {
                LogUtils.setSesssionLogFolderName("NV_" + string);
            }
            DataAccess.update(this.context, InitiateModel.class, initiateModel);
            return string;
        } catch (JSONException e) {
            Log.m1930w(this.TAG, "JSON Exception", (Throwable) e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String getRequest() throws Exception {
        ArrayList arrayList;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sdk-platform", InternalLogger.EVENT_PARAM_SDK_ANDROID);
        jSONObject.put("sdk-version", "JMSDK 2.4.0 (0-55)");
        jSONObject.put("manufacturer", Build.MANUFACTURER);
        jSONObject.put("model", Build.MODEL);
        jSONObject.put("software-version", VERSION.RELEASE);
        jSONObject.put("software-build-number", Build.DISPLAY);
        jSONObject.put("kernel-version", System.getProperty("os.version"));
        JSONObject jSONObject2 = new JSONObject();
        if (this.f4801a == null) {
            throw new IllegalStateException("InitiateCall#getRequestBody: model cannot be null!");
        }
        if (!this.f4801a.getMerchantScanReference().equals("")) {
            jSONObject2.put("merchantIdScanReference", this.f4801a.getMerchantScanReference());
        }
        if (!this.f4801a.getMerchantReportingCriteria().equals("")) {
            jSONObject2.put("merchantReportingCriteria", this.f4801a.getMerchantReportingCriteria());
        }
        jSONObject2.put("deviceDetail", jSONObject);
        jSONObject2.put("requireVerification", this.f4802b.isVerificationAllowed() && this.f4801a.requireVerification());
        if (this.f4802b.getEnabledFields() == null || this.f4802b.getEnabledFields().length() == 0) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(Arrays.asList(this.f4802b.getEnabledFields().split(",")));
            if (!CameraManager.hasFrontFacingCamera(this.context) && arrayList2.contains("idFaceMatch")) {
                arrayList2.remove("idFaceMatch");
                arrayList = arrayList2;
            } else if (!this.f4801a.isFaceMatchEnabled() && this.f4801a.isFaceMatchSet()) {
                arrayList2.remove("idFaceMatch");
                arrayList = arrayList2;
            } else if (this.f4801a.isFaceMatchSet() || !arrayList2.contains("idFaceMatch")) {
                if (!arrayList2.contains("idFaceMatch")) {
                    this.f4801a.setFaceMatchEnabled(false);
                }
                arrayList = arrayList2;
            } else {
                this.f4801a.setFaceMatchEnabled(true);
                arrayList = arrayList2;
            }
        }
        if (!this.f4801a.getCustomerId().equals("")) {
            jSONObject2.put("customerId", this.f4801a.getCustomerId());
        }
        if (!this.f4801a.getCallbackUrl().equals("")) {
            jSONObject2.put("callbackUrl", this.f4801a.getCallbackUrl());
        }
        if (!this.f4801a.getAdditionalInformation().equals("")) {
            jSONObject2.put("additionalInformation", this.f4801a.getAdditionalInformation());
        }
        if (!(arrayList == null || arrayList.size() == 0)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append((String) arrayList.get(i));
            }
            jSONObject2.put("enabledFields", sb.toString());
        }
        DataAccess.update(this.context, MerchantSettingsModel.class, this.f4801a);
        return jSONObject2.toString();
    }
}
