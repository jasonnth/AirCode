package p004bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.cg */
public class C0406cg implements IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f267a = AppboyLogger.getAppboyLogTag(C0406cg.class);

    /* renamed from: b */
    private final Integer f268b;

    /* renamed from: c */
    private final String f269c;

    /* renamed from: d */
    private final String f270d;

    /* renamed from: e */
    private final C0409ci f271e;

    /* renamed from: f */
    private final C0408ch f272f;

    /* renamed from: a */
    public static C0406cg m338a(JSONObject jSONObject) {
        C0636y[] values;
        C0408ch chVar = null;
        String str = null;
        C0409ci ciVar = null;
        String str2 = null;
        Integer num = null;
        for (C0636y yVar : C0636y.values()) {
            switch (yVar) {
                case DEVICE_IDENTIFIERS:
                    JSONObject optJSONObject = jSONObject.optJSONObject(C0636y.DEVICE_IDENTIFIERS.mo7333a());
                    if (optJSONObject == null) {
                        break;
                    } else {
                        chVar = C0408ch.m341a(optJSONObject);
                        break;
                    }
                case DISPLAY:
                    JSONObject optJSONObject2 = jSONObject.optJSONObject(C0636y.DISPLAY.mo7333a());
                    if (optJSONObject2 == null) {
                        break;
                    } else {
                        ciVar = C0409ci.m344b(optJSONObject2);
                        break;
                    }
                case ANDROID_VERSION:
                    if (!jSONObject.has(C0636y.ANDROID_VERSION.mo7333a())) {
                        break;
                    } else {
                        num = Integer.valueOf(jSONObject.optInt(C0636y.ANDROID_VERSION.mo7333a()));
                        break;
                    }
                case MODEL:
                    str2 = StringUtils.emptyToNull(jSONObject.optString(C0636y.MODEL.mo7333a()));
                    break;
                case DEVICE_TYPE:
                    str = StringUtils.emptyToNull(jSONObject.optString(C0636y.DEVICE_TYPE.mo7333a()));
                    break;
                default:
                    AppboyLogger.m1735e(f267a, String.format("Unknown key encountered in WearDevice createFromJson %s", new Object[]{yVar}));
                    break;
            }
        }
        return new C0406cg(num, str, str2, ciVar, chVar);
    }

    public C0406cg(Integer num, String str, String str2, C0409ci ciVar, C0408ch chVar) {
        this.f268b = num;
        this.f269c = str;
        this.f270d = str2;
        this.f271e = ciVar;
        this.f272f = chVar;
    }

    /* renamed from: a */
    public String mo6898a() {
        return this.f272f.mo6900a();
    }

    /* renamed from: b */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(C0636y.ANDROID_VERSION.mo7333a(), this.f268b);
            jSONObject.putOpt(C0636y.MODEL.mo7333a(), this.f270d);
            jSONObject.putOpt(C0636y.DEVICE_TYPE.mo7333a(), this.f269c);
            if (this.f271e != null) {
                jSONObject.putOpt(C0636y.DISPLAY.mo7333a(), this.f271e.forJsonPut());
            }
            if (this.f272f != null) {
                jSONObject.putOpt(C0636y.DEVICE_IDENTIFIERS.mo7333a(), this.f272f.forJsonPut());
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f267a, "Caught exception creating wear device Json.", e);
        }
        return jSONObject;
    }
}
