package jumio.p317nv.core;

import android.content.Context;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.commons.obfuscate.StringObfuscater;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall;
import com.jumio.core.network.ApiCall.DynamicProvider;
import com.jumio.core.network.SimpleApiCall;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.persistence.DataAccess;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.PassportService;

/* renamed from: jumio.nv.core.e */
/* compiled from: FinalizeCall */
public class C4922e extends SimpleApiCall<Void> {

    /* renamed from: a */
    private String f4800a = null;

    public C4922e(Context context, DynamicProvider dynamicProvider, Subscriber<Void> subscriber) {
        super(context, dynamicProvider, subscriber);
    }

    public String getUri() {
        if (this.f4800a == null) {
            InitiateModel initiateModel = (InitiateModel) DataAccess.load(this.context, InitiateModel.class);
            if (initiateModel == null) {
                throw new IllegalStateException("SelectionModel cannot be null!");
            }
            this.f4800a = initiateModel.getJumioScanRef();
        }
        return StringObfuscater.format(new byte[]{38, 90, 19, -37, -45, ISO7816.INS_WRITE_BINARY}, -4723252326649916104L) + this.f4800a + StringObfuscater.format(new byte[]{-33, -92, -126, 38, ISO7816.INS_READ_RECORD_STAMPED, PassportService.SF_CVCA, ISO7816.INS_WRITE_RECORD, -126, 92, ISO7816.INS_MANAGE_CHANNEL, 13, -69, ISOFileInfo.FILE_IDENTIFIER, -103, -14, -112}, 1206204796263429576L);
    }

    public String getMethod() {
        return "PUT";
    }

    /* access modifiers changed from: protected */
    public void responseReceived(int i, String str, long j, String str2) {
        super.responseReceived(i, str, j, str2);
        JumioAnalytics.add(MobileEvents.networkRequest(JumioAnalytics.getSessionId(), getClass().getName(), i, str, j));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Void parseResponse(String str) {
        ApiCall.setTrackingId(null);
        return null;
    }

    /* access modifiers changed from: protected */
    public String getRequest() throws Exception {
        return "";
    }
}
