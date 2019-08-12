package p004bo.app;

import android.net.Uri;
import com.appboy.Appboy;
import com.appboy.enums.ErrorType;
import com.appboy.models.ResponseError;
import com.appboy.support.AppboyLogger;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.cq */
public abstract class C0419cq extends C0430cz implements C0424cu {

    /* renamed from: b */
    private static final String f289b = AppboyLogger.getAppboyLogTag(C0419cq.class);

    /* renamed from: c */
    private C0404ce f290c;

    /* renamed from: d */
    private C0401cb f291d;

    protected C0419cq(Uri uri, Map<String, String> map) {
        super(uri, map);
    }

    /* renamed from: b */
    public Uri mo6916b() {
        return Appboy.getAppboyApiEndpoint(this.f315a);
    }

    /* renamed from: a */
    public void mo6915a(C0404ce ceVar) {
        this.f290c = ceVar;
    }

    /* renamed from: c */
    public C0404ce mo6917c() {
        return this.f290c;
    }

    /* renamed from: a */
    public void mo6914a(C0401cb cbVar) {
        this.f291d = cbVar;
    }

    /* renamed from: d */
    public C0401cb mo6918d() {
        return this.f291d;
    }

    /* renamed from: a */
    public void mo6911a(C0343ac acVar, ResponseError responseError) {
        ErrorType type = responseError.getType();
        if (type == ErrorType.REQUIRED_FIELD_MISSING) {
            AppboyLogger.m1735e(f289b, String.format("Required Field Missing: %s", new Object[]{responseError.getMessage()}));
        } else if (type == ErrorType.BAD_INPUT) {
            AppboyLogger.m1735e(f289b, String.format("Bad Input: %s", new Object[]{responseError.getMessage()}));
        } else {
            AppboyLogger.m1735e(f289b, String.format("Error %s occurred while executing Appboy request: %s", new Object[]{type.toString(), responseError.getMessage()}));
        }
    }

    /* renamed from: e */
    public JSONObject mo6919e() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.f290c != null) {
                jSONObject.put("extras", this.f290c.forJsonPut());
            }
            if (this.f291d == null) {
                return jSONObject;
            }
            jSONObject.put("environment", this.f291d.forJsonPut());
            return jSONObject;
        } catch (JSONException e) {
            AppboyLogger.m1740w(f289b, "Experienced JSONException while retrieving parameters. Returning null.", e);
            return null;
        }
    }

    /* renamed from: f */
    public boolean mo6920f() {
        return this.f290c == null || this.f290c.mo6822h();
    }

    /* renamed from: a */
    public void mo6909a(C0343ac acVar) {
        if (this.f290c != null) {
            C0405cf c = this.f290c.mo6895c();
            C0399ca b = this.f290c.mo6894b();
            if (c != null) {
                acVar.mo6736a(new C0347ag(c), C0347ag.class);
            }
            if (b != null) {
                acVar.mo6736a(new C0346af(b), C0346af.class);
            }
        }
    }
}
