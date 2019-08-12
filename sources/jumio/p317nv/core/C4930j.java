package jumio.p317nv.core;

import android.content.Context;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.commons.obfuscate.StringObfuscater;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall;
import com.jumio.core.network.ApiCall.DynamicProvider;
import com.jumio.core.network.SimpleApiCall;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.models.MerchantSettingsModel;
import java.util.Iterator;
import java.util.UUID;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: jumio.nv.core.j */
/* compiled from: SettingsCall */
public class C4930j extends SimpleApiCall<String> {

    /* renamed from: a */
    private String f4813a = "Android/5.4.0";

    /* renamed from: b */
    private MerchantSettingsModel f4814b;

    public C4930j(Context context, DynamicProvider dynamicProvider, MerchantSettingsModel merchantSettingsModel, Subscriber<String> subscriber) {
        super(context, dynamicProvider, subscriber);
        ApiCall.setTrackingId(UUID.randomUUID().toString());
        this.f4814b = merchantSettingsModel;
    }

    /* access modifiers changed from: protected */
    public void responseReceived(int i, String str, long j, String str2) {
        super.responseReceived(i, str, j, str2);
        JumioAnalytics.add(MobileEvents.networkRequest(JumioAnalytics.getSessionId(), getClass().getName(), i, str, j));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String parseResponse(String str) {
        return str;
    }

    /* access modifiers changed from: protected */
    public String getRequest() throws Exception {
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.f4814b.getSupportedDocumentTypes().iterator();
        while (it.hasNext()) {
            jSONArray.put(((NVDocumentType) it.next()).toString());
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("idTypes", jSONArray);
        jSONObject.put("barcodeVersion", this.f4813a);
        return jSONObject.toString();
    }

    public String getUri() {
        return StringObfuscater.format(new byte[]{-22, ISOFileInfo.FCI_EXT, -25, 65, 43, 57, ISOFileInfo.FCP_BYTE, -73}, -3991709031461092330L);
    }
}
