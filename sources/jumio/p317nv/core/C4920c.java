package jumio.p317nv.core;

import android.content.Context;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.commons.obfuscate.StringObfuscater;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall.DynamicProvider;
import com.jumio.core.network.SimpleApiCall;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import com.jumio.p311nv.data.document.NVMRZFormat;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.p311nv.models.LivenessModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.persistence.DataAccess;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import net.p318sf.scuba.smartcards.ISO7816;
import org.jmrtd.lds.CVCAFile;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: jumio.nv.core.c */
/* compiled from: DataCall */
public class C4920c extends SimpleApiCall<Void> {

    /* renamed from: a */
    private SelectionModel f4796a = null;

    /* renamed from: b */
    private InitiateModel f4797b = null;

    public C4920c(Context context, DynamicProvider dynamicProvider, Subscriber<Void> subscriber) {
        super(context, dynamicProvider, subscriber);
    }

    public String getUri() {
        if (this.f4797b == null) {
            this.f4797b = (InitiateModel) DataAccess.load(this.context, InitiateModel.class);
        }
        if (this.f4797b != null) {
            return StringObfuscater.format(new byte[]{ISO7816.INS_DECREASE, 93, 92, 2, -8, 57}, -7687084684853639532L) + this.f4797b.getJumioScanRef() + StringObfuscater.format(new byte[]{ISO7816.INS_READ_RECORD_STAMPED, 83, CVCAFile.CAR_TAG, 26, -72}, -7668719030812587582L);
        }
        throw new IllegalStateException("InitiateModel cannot be null!");
    }

    /* access modifiers changed from: protected */
    public void responseReceived(int i, String str, long j, String str2) {
        super.responseReceived(i, str, j, str2);
        JumioAnalytics.add(MobileEvents.networkRequest(JumioAnalytics.getSessionId(), getClass().getName(), i, str, j));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Void parseResponse(String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getRequest() throws Exception {
        Object obj;
        Object obj2 = null;
        if (this.f4796a == null) {
            this.f4796a = (SelectionModel) DataAccess.load(this.context, SelectionModel.class);
        }
        if (this.f4796a == null) {
            throw new IllegalStateException("SelectionModel cannot be null!");
        }
        List scanModes = this.f4796a.getUploadModel().getScanModes();
        DocumentDataModel documentData = this.f4796a.getUploadModel().getDocumentData();
        if (documentData == null) {
            publishError(new IllegalArgumentException("DataCall: documentData cannot be null!"));
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        JSONObject jSONObject = new JSONObject();
        if (scanModes.contains(DocumentScanMode.MRP)) {
            m3029a(jSONObject, "mrzFormat", DocumentType.MRZ_FORMAT_MRP);
        }
        if (scanModes.contains(DocumentScanMode.MRV)) {
            m3029a(jSONObject, "mrzFormat", documentData.getMrzData().getFormat() == NVMRZFormat.MRV_A ? DocumentType.MRZ_FORMAT_MRV_A : DocumentType.MRZ_FORMAT_MRV_B);
        }
        if (scanModes.contains(DocumentScanMode.TD1)) {
            m3029a(jSONObject, "mrzFormat", DocumentType.MRZ_FORMAT_TD1);
        }
        if (scanModes.contains(DocumentScanMode.TD2)) {
            m3029a(jSONObject, "mrzFormat", DocumentType.MRZ_FORMAT_TD2);
        }
        if (scanModes.contains(DocumentScanMode.CNIS)) {
            m3029a(jSONObject, "mrzFormat", DocumentType.MRZ_FORMAT_CNIS);
        }
        if (scanModes.contains(DocumentScanMode.CSSN)) {
            m3029a(jSONObject, "thirdPartyOcr", DocumentType.THIRD_PARTY_OCR_FORMAT_CSSN);
        }
        if (scanModes.contains(DocumentScanMode.PDF417)) {
            m3029a(jSONObject, "barcodeFormat", "PDF417");
        }
        NVDocumentType id = this.f4796a.getSelectedDoctype().getId();
        NVDocumentVariant selectedVariant = this.f4796a.getSelectedVariant();
        m3029a(jSONObject, "idType", id.toString());
        m3029a(jSONObject, "documentVariant", selectedVariant.toString());
        String issuingCountry = documentData.getIssuingCountry();
        String str = "issuingCountry";
        if (issuingCountry == null || issuingCountry.length() == 0) {
            issuingCountry = this.f4796a.getSelectedCountry().getIsoCode();
        }
        m3029a(jSONObject, str, issuingCountry);
        if (this.f4796a.isVerificationRequired()) {
            documentData.fillRequest(jSONObject, simpleDateFormat, this.f4796a, this.f4796a.getUploadModel());
            LivenessModel livenessModel = (LivenessModel) DataAccess.load(this.context, LivenessModel.class);
            if (livenessModel != null) {
                obj = livenessModel.getLivenessDetected();
            } else {
                obj = null;
            }
            String str2 = "livenessDetected";
            if (obj != null) {
                obj2 = obj;
            }
            m3029a(jSONObject, str2, String.valueOf(obj2));
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    private void m3029a(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (str2 != null && str2.length() != 0) {
            jSONObject.put(str, str2);
        }
    }
}
