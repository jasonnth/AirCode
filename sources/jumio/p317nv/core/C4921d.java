package jumio.p317nv.core;

import android.content.Context;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.commons.obfuscate.StringObfuscater;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall.DynamicProvider;
import com.jumio.core.network.multipart.MultipartApiCall;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.persistence.DataAccess;
import net.p318sf.scuba.smartcards.ISO7816;
import org.jmrtd.PassportService;
import org.json.JSONObject;

/* renamed from: jumio.nv.core.d */
/* compiled from: ExtractDataCall */
public class C4921d extends MultipartApiCall<DocumentDataModel> {

    /* renamed from: a */
    byte[] f4798a = null;

    /* renamed from: b */
    private SelectionModel f4799b = null;

    public C4921d(Context context, DynamicProvider dynamicProvider, byte[] bArr, Subscriber<DocumentDataModel> subscriber) {
        super(context, dynamicProvider, subscriber);
        this.f4798a = bArr;
    }

    public String getUri() {
        return StringObfuscater.format(new byte[]{9, 80, -55, -97, PassportService.SF_SOD, 60, 56, 65, ISO7816.INS_WRITE_BINARY, 107, 2, -101}, 4399378386366219258L);
    }

    /* access modifiers changed from: protected */
    public void responseReceived(int i, String str, long j, String str2) {
        super.responseReceived(i, str, j, str2);
        JumioAnalytics.add(MobileEvents.networkRequest(JumioAnalytics.getSessionId(), getClass().getName(), i, str, j));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public DocumentDataModel parseResponse(String str) {
        DocumentDataModel documentDataModel = new DocumentDataModel();
        documentDataModel.parseJson(str);
        return documentDataModel;
    }

    /* access modifiers changed from: protected */
    public void prepareData() throws Exception {
        if (this.f4799b == null) {
            this.f4799b = (SelectionModel) DataAccess.load(this.context, SelectionModel.class);
            if (this.f4799b == null) {
                throw new IllegalStateException("SelectionModel cannot be null!");
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("country", this.f4799b.getSelectedCountry().getIsoCode());
        jSONObject.put("idType", this.f4799b.getSelectedDoctype().getId());
        jSONObject.put("imageSide", this.f4799b.getSelectedDoctype().getDocumentScanSide() == ScanSide.FRONT ? "FRONT" : "BACK");
        String jSONObject2 = jSONObject.toString();
        addPart(new String[]{"Content-Disposition: form-data; name=\"data\"", "Content-Type: application/json; charset=UTF-8", String.format("Content-Length: %d", new Object[]{Integer.valueOf(jSONObject2.length())})}, jSONObject2);
        addPart(new String[]{"Content-Disposition: form-data; name=\"fileUpload\"; filename=\"image.jpeg\"", "Content-Type: image/jpeg", String.format("Content-Length: %d", new Object[]{Integer.valueOf(this.f4798a.length)})}, this.f4798a);
    }
}
