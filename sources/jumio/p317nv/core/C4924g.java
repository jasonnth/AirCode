package jumio.p317nv.core;

import android.content.Context;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.commons.log.Log;
import com.jumio.commons.obfuscate.StringObfuscater;
import com.jumio.commons.remote.exception.UnexpectedResponseException;
import com.jumio.commons.utils.FileUtil;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall.DynamicProvider;
import com.jumio.core.network.NetworkException;
import com.jumio.core.network.multipart.MultipartApiCall;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.persistence.DataAccess;
import java.io.IOException;
import java.net.SocketTimeoutException;
import javax.net.ssl.SSLException;
import net.p318sf.scuba.smartcards.ISO7816;

/* renamed from: jumio.nv.core.g */
/* compiled from: LivenessCall */
public class C4924g extends MultipartApiCall<Void> {

    /* renamed from: a */
    private String f4803a = null;

    /* renamed from: b */
    private String[] f4804b;

    public C4924g(Context context, DynamicProvider dynamicProvider, String[] strArr, Subscriber<Void> subscriber) {
        super(context, dynamicProvider, subscriber);
        this.f4804b = strArr;
    }

    public String getUri() {
        String str = "LIVENESS";
        if (this.f4803a == null) {
            InitiateModel initiateModel = (InitiateModel) DataAccess.load(this.context, InitiateModel.class);
            if (initiateModel == null) {
                throw new IllegalStateException("SelectionModel cannot be null!");
            }
            this.f4803a = initiateModel.getJumioScanRef();
        }
        return StringObfuscater.format(new byte[]{-15, ISO7816.INS_PSO, 43, 27, 67, 85}, 983068124891981253L) + this.f4803a + StringObfuscater.format(new byte[]{ISO7816.INS_LOAD_KEY_FILE, -15, -72, -59, ISO7816.INS_PUT_DATA, 20, ISO7816.INS_GET_DATA, ISO7816.INS_DECREASE}, -3512380628637652580L) + str;
    }

    /* renamed from: a */
    public Void execute() throws SocketTimeoutException, NetworkException, UnexpectedResponseException, SSLException {
        try {
            super.execute();
        } catch (Exception e) {
            Log.m1919i(this.TAG, "<- call(failed) - mark it as success anyways");
        }
        return null;
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
    public void prepareData() throws Exception {
        for (int i = 0; i < this.f4804b.length; i++) {
            try {
                byte[] readFile = FileUtil.readFile(this.f4804b[i]);
                addPart(new String[]{String.format("Content-Disposition: form-data; name=\"%s_%d\"; filename=\"%s_%d.jpg\"", new Object[]{this.f4803a, Integer.valueOf(i), this.f4803a, Integer.valueOf(i)}), "Content-Type: image/webp", String.format("Content-Length: %d", new Object[]{Integer.valueOf(readFile.length)})}, readFile);
            } catch (IOException e) {
            }
        }
    }
}
