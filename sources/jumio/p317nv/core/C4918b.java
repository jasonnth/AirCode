package jumio.p317nv.core;

import android.content.Context;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MobileEvents;
import com.jumio.commons.camera.CameraManager.Size;
import com.jumio.commons.camera.ImageData;
import com.jumio.commons.obfuscate.StringObfuscater;
import com.jumio.commons.remote.exception.UnexpectedResponseException;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall.DynamicProvider;
import com.jumio.core.network.NetworkException;
import com.jumio.core.network.PreconditionNotSatisfiedException;
import com.jumio.core.network.multipart.MultipartApiCall;
import com.jumio.p311nv.models.InitiateModel;
import com.jumio.persistence.DataAccess;
import java.net.SocketTimeoutException;
import java.util.Locale;
import javax.net.ssl.SSLException;
import net.p318sf.scuba.smartcards.ISO7816;
import org.json.JSONObject;

/* renamed from: jumio.nv.core.b */
/* compiled from: AddPartCall */
public class C4918b extends MultipartApiCall<Void> {

    /* renamed from: a */
    private final ScanSide f4791a;

    /* renamed from: b */
    private String f4792b = null;

    /* renamed from: c */
    private ImageData f4793c;

    /* renamed from: d */
    private byte[] f4794d;

    public C4918b(Context context, DynamicProvider dynamicProvider, ScanSide scanSide, ImageData imageData, byte[] bArr, Subscriber<Void> subscriber) {
        super(context, dynamicProvider, subscriber);
        this.f4791a = scanSide;
        this.f4794d = bArr;
        this.f4793c = imageData;
    }

    /* renamed from: a */
    public Void execute() throws SocketTimeoutException, NetworkException, UnexpectedResponseException, SSLException {
        if (this.f4794d != null) {
            return (Void) super.execute();
        }
        throw new PreconditionNotSatisfiedException("image data cannot be null!");
    }

    public String getUri() {
        String classifier = this.f4791a.getClassifier();
        if (this.f4792b == null) {
            InitiateModel initiateModel = (InitiateModel) DataAccess.load(this.context, InitiateModel.class);
            if (initiateModel == null) {
                throw new IllegalStateException("SelectionModel cannot be null!");
            }
            this.f4792b = initiateModel.getJumioScanRef();
        }
        return StringObfuscater.format(new byte[]{-15, ISO7816.INS_PSO, 43, 27, 67, 85}, 983068124891981253L) + this.f4792b + StringObfuscater.format(new byte[]{ISO7816.INS_LOAD_KEY_FILE, -15, -72, -59, ISO7816.INS_PUT_DATA, 20, ISO7816.INS_GET_DATA, ISO7816.INS_DECREASE}, -3512380628637652580L) + classifier;
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
        JSONObject jSONObject = new JSONObject();
        ImageData imageData = this.f4793c;
        String str = null;
        switch (imageData.getOrientationMode()) {
            case PORTRAIT:
                str = "portrait";
                break;
            case INVERTED_PORTRAIT:
                str = "reversePortrait";
                break;
            case LANDSCAPE:
                str = "landscape";
                break;
            case INVERTED_LANDSCAPE:
                str = "reverseLandscape";
                break;
        }
        jSONObject.put("orientationMode", str);
        Size imageSize = imageData.getImageSize();
        jSONObject.put("imageSize", String.format(Locale.GERMAN, "%dx%d", new Object[]{Integer.valueOf(imageSize.width), Integer.valueOf(imageSize.height)}));
        jSONObject.put("flashMode", Boolean.toString(imageData.isFlashMode()));
        jSONObject.put("focusConfidence", Float.toString(imageData.getFocusConfidence()));
        String jSONObject2 = jSONObject.toString();
        addPart(new String[]{"Content-Disposition: form-data; name=\"cameraDeviceDetail\"", "Content-Type: application/json; charset=UTF-8", String.format("Content-Length: %d", new Object[]{Integer.valueOf(jSONObject2.length())})}, jSONObject2);
        addPart(new String[]{"Content-Disposition: form-data; name=\"image\"; filename=\"image.webp\"", "Content-Type: image/webp", String.format("Content-Length: %d", new Object[]{Integer.valueOf(this.f4794d.length)})}, this.f4794d);
    }
}
