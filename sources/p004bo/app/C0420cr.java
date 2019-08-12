package p004bo.app;

import com.appboy.enums.ErrorType;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.InAppMessageEvent;
import com.appboy.models.ResponseError;
import com.appboy.support.AppboyLogger;
import java.net.URI;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.cr */
public final class C0420cr implements Runnable {

    /* renamed from: a */
    private static final String f292a = AppboyLogger.getAppboyLogTag(C0420cr.class);

    /* renamed from: b */
    private final C0424cu f293b;

    /* renamed from: c */
    private final C0343ac f294c;

    /* renamed from: d */
    private final C0343ac f295d;

    /* renamed from: e */
    private final Map<String, String> f296e;

    /* renamed from: f */
    private final C0431d f297f;

    /* renamed from: g */
    private final C0444dh f298g;

    /* renamed from: h */
    private final C0448dk f299h;

    /* renamed from: i */
    private final C0375bd f300i;

    public C0420cr(C0424cu cuVar, C0325a aVar, C0431d dVar, C0343ac acVar, C0343ac acVar2, C0444dh dhVar, C0375bd bdVar, C0448dk dkVar) {
        this.f293b = cuVar;
        this.f294c = acVar;
        this.f295d = acVar2;
        this.f296e = aVar.mo6703a();
        this.f297f = dVar;
        this.f298g = dhVar;
        this.f300i = bdVar;
        this.f299h = dkVar;
    }

    public void run() {
        try {
            C0382bk a = m371a();
            if (a != null) {
                mo6921a(a);
                this.f294c.mo6736a(new C0345ae(this.f293b), C0345ae.class);
                return;
            }
            AppboyLogger.m1739w(f292a, "Api response was null, failing task.");
            this.f293b.mo6911a(this.f295d, new ResponseError(ErrorType.UNRECOGNIZED_ERROR, "An error occurred during request processing, resulting in no valid response being received. Check the error log for more details."));
            this.f294c.mo6736a(new C0344ad(this.f293b), C0344ad.class);
        } catch (Exception e) {
            AppboyLogger.m1740w(f292a, "Experienced exception processing API response. Failing task.", e);
        }
    }

    /* renamed from: a */
    private C0382bk m371a() {
        URI a = C0457dq.m524a(this.f293b.mo6916b());
        switch (this.f293b.mo6908a()) {
            case GET:
                return new C0382bk(this.f297f.mo6883a(a, this.f296e), this.f300i);
            case POST:
                JSONObject e = this.f293b.mo6919e();
                if (e != null) {
                    return new C0382bk(this.f297f.mo6884a(a, this.f296e, e), this.f300i);
                }
                AppboyLogger.m1735e(f292a, "Could not parse request parameters for put request to [%s], canceling request.");
                return null;
            default:
                AppboyLogger.m1739w(f292a, String.format("Received a request with an unknown Http verb: [%s]", new Object[]{this.f293b.mo6908a()}));
                return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo6921a(C0382bk bkVar) {
        C0392bu a = bkVar.mo6804a();
        C0393bv b = bkVar.mo6805b();
        ResponseError responseError = b != null ? b.mo6856a() : null;
        if (responseError == null) {
            this.f293b.mo6910a(this.f295d, a);
        } else {
            m373a(responseError);
            this.f293b.mo6911a(this.f295d, responseError);
        }
        if (a != null) {
            m372a(a);
        }
        this.f293b.mo6909a(this.f294c);
    }

    /* renamed from: a */
    private void m372a(C0392bu buVar) {
        String str = (String) this.f296e.get("X-Appboy-User-Identifier");
        if (buVar.mo6846a()) {
            try {
                FeedUpdatedEvent a = this.f298g.mo6956a(buVar.mo6851f(), str);
                if (a != null) {
                    this.f295d.mo6736a(a, FeedUpdatedEvent.class);
                }
            } catch (JSONException e) {
                AppboyLogger.m1739w(f292a, "Unable to update/publish feed.");
            }
        }
        if (buVar.mo6849d()) {
            this.f299h.mo6965a(buVar.mo6854i());
            this.f294c.mo6736a(new C0348ah(buVar.mo6854i()), C0348ah.class);
        }
        if (buVar.mo6847b()) {
            this.f295d.mo6736a(new InAppMessageEvent(buVar.mo6852g(), str), InAppMessageEvent.class);
        }
        if (buVar.mo6850e()) {
            this.f294c.mo6736a(new C0355ao(buVar.mo6855j()), C0355ao.class);
        }
        if (buVar.mo6848c() && (this.f293b instanceof C0433db)) {
            buVar.mo6853h().setExpirationTimestamp(((C0433db) this.f293b).mo6928h());
            this.f295d.mo6736a(new InAppMessageEvent(buVar.mo6853h(), str), InAppMessageEvent.class);
        }
    }

    /* renamed from: a */
    private void m373a(ResponseError responseError) {
        ErrorType type = responseError.getType();
        if (type == ErrorType.NO_DEVICE_IDENTIFIER) {
            AppboyLogger.m1735e(f292a, "No device identifier. This should never happen. Please contact support@appboy.com");
        } else if (type == ErrorType.INVALID_API_KEY) {
            AppboyLogger.m1735e(f292a, "Invalid API key! Please update the API key in the appboy.xml file.");
        } else if (type == ErrorType.UNRECOGNIZED_ERROR) {
            AppboyLogger.m1735e(f292a, "Unrecognized server error: " + responseError.getMessage());
        }
    }
}
