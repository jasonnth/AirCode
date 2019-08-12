package p004bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ca */
public final class C0399ca implements C0387bp, IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f233a = AppboyLogger.getAppboyLogTag(C0399ca.class);

    /* renamed from: b */
    private final Integer f234b;

    /* renamed from: c */
    private final String f235c;

    /* renamed from: d */
    private final String f236d;

    /* renamed from: e */
    private final String f237e;

    /* renamed from: f */
    private final String f238f;

    /* renamed from: g */
    private final String f239g;

    /* renamed from: h */
    private String f240h;

    /* renamed from: i */
    private final C0402cc f241i;

    /* renamed from: j */
    private final String f242j;

    /* renamed from: k */
    private final List<C0406cg> f243k;

    /* renamed from: a */
    public static C0399ca m321a(JSONObject jSONObject) {
        C0630s[] values;
        Integer num = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        C0402cc ccVar = null;
        String str7 = null;
        ArrayList arrayList = null;
        for (C0630s sVar : C0630s.values()) {
            switch (sVar) {
                case LOCALE_COUNTRY:
                case LOCALE_LANGUAGE:
                    break;
                case LOCALE:
                    JSONObject optJSONObject = jSONObject.optJSONObject(C0630s.LOCALE.mo7329a());
                    if (optJSONObject == null) {
                        break;
                    } else {
                        str5 = optJSONObject.optString(C0630s.LOCALE_COUNTRY.mo7329a());
                        str4 = optJSONObject.optString(C0630s.LOCALE_LANGUAGE.mo7329a());
                        break;
                    }
                case TIMEZONE:
                    str6 = StringUtils.emptyToNull(jSONObject.optString(C0630s.TIMEZONE.mo7329a()));
                    break;
                case DISPLAY:
                    JSONObject optJSONObject2 = jSONObject.optJSONObject(C0630s.DISPLAY.mo7329a());
                    if (optJSONObject2 == null) {
                        break;
                    } else {
                        ccVar = C0402cc.m326a(optJSONObject2);
                        break;
                    }
                case ANDROID_VERSION:
                    if (!jSONObject.has(C0630s.ANDROID_VERSION.mo7329a())) {
                        break;
                    } else {
                        num = Integer.valueOf(jSONObject.optInt(C0630s.ANDROID_VERSION.mo7329a()));
                        break;
                    }
                case ABI:
                    str = StringUtils.emptyToNull(jSONObject.optString(C0630s.ABI.mo7329a()));
                    break;
                case CARRIER:
                    str2 = StringUtils.emptyToNull(jSONObject.optString(C0630s.CARRIER.mo7329a()));
                    break;
                case MODEL:
                    str3 = StringUtils.emptyToNull(jSONObject.optString(C0630s.MODEL.mo7329a()));
                    break;
                case PUSH_TOKEN:
                    str7 = StringUtils.emptyToNull(jSONObject.optString(C0630s.PUSH_TOKEN.mo7329a()));
                    break;
                case CONNECTED_DEVICES:
                    arrayList = new ArrayList();
                    if (!jSONObject.has(C0630s.CONNECTED_DEVICES.mo7329a())) {
                        break;
                    } else {
                        JSONArray optJSONArray = jSONObject.optJSONArray(C0630s.CONNECTED_DEVICES.mo7329a());
                        if (optJSONArray == null) {
                            break;
                        } else {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                C0406cg a = C0406cg.m338a(optJSONArray.getJSONObject(i));
                                if (a != null) {
                                    arrayList.add(a);
                                }
                            }
                            break;
                        }
                    }
                default:
                    AppboyLogger.m1735e(f233a, String.format("Unknown key encountered in Device createFromJson %s", new Object[]{sVar}));
                    break;
            }
        }
        return new C0399ca(num, str, str2, str3, str4, str5, str6, ccVar, str7, arrayList);
    }

    public C0399ca(Integer num, String str, String str2, String str3, String str4, String str5, String str6, C0402cc ccVar, String str7, List<C0406cg> list) {
        this.f234b = num;
        this.f235c = str;
        this.f236d = str2;
        this.f237e = str3;
        this.f238f = str4;
        this.f239g = str5;
        this.f240h = str6;
        this.f241i = ccVar;
        this.f242j = str7;
        this.f243k = list;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(C0630s.ANDROID_VERSION.mo7329a(), this.f234b);
            jSONObject.putOpt(C0630s.ABI.mo7329a(), this.f235c);
            jSONObject.putOpt(C0630s.CARRIER.mo7329a(), this.f236d);
            jSONObject.putOpt(C0630s.MODEL.mo7329a(), this.f237e);
            jSONObject.putOpt(C0630s.PUSH_TOKEN.mo7329a(), this.f242j);
            if (!(this.f239g == null || this.f238f == null)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.putOpt(C0630s.LOCALE_LANGUAGE.mo7329a(), this.f238f);
                jSONObject2.putOpt(C0630s.LOCALE_COUNTRY.mo7329a(), this.f239g);
                jSONObject.put(C0630s.LOCALE.mo7329a(), jSONObject2);
            }
            if (!StringUtils.isNullOrBlank(this.f240h)) {
                jSONObject.put(C0630s.TIMEZONE.mo7329a(), this.f240h);
            }
            if (this.f241i != null) {
                jSONObject.putOpt(C0630s.DISPLAY.mo7329a(), this.f241i.forJsonPut());
            }
            if (this.f243k != null && !this.f243k.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (C0406cg b : this.f243k) {
                    jSONArray.put(b.forJsonPut());
                }
                jSONObject.put(C0630s.CONNECTED_DEVICES.mo7329a(), jSONArray);
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f233a, "Caught exception creating device Json.", e);
        }
        return jSONObject;
    }

    /* renamed from: h */
    public boolean mo6822h() {
        return forJsonPut().length() == 0;
    }
}
