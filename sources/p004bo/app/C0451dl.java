package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.appboy.enums.Gender;
import com.appboy.enums.Month;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.dl */
public class C0451dl extends C0434dc<C0405cf> {

    /* renamed from: a */
    private static final String f375a = AppboyLogger.getAppboyLogTag(C0451dl.class);

    /* renamed from: b */
    private static final Set<String> f376b = new HashSet(Arrays.asList(new String[]{"twitter", "facebook", "ab_install_attribution"}));

    /* renamed from: c */
    private final SharedPreferences f377c;

    /* renamed from: d */
    private final C0448dk f378d;

    public C0451dl(Context context, C0448dk dkVar) {
        this(context, null, null, dkVar);
    }

    public C0451dl(Context context, String str, String str2, C0448dk dkVar) {
        this.f377c = context.getSharedPreferences("com.appboy.storage.usercache" + StringUtils.getCacheFileSuffix(context, str, str2), 0);
        this.f378d = dkVar;
    }

    /* renamed from: a */
    public synchronized void mo6983a(String str) {
        Editor edit = this.f377c.edit();
        edit.putString(CohostingConstants.FIRST_NAME_FIELD, str);
        edit.apply();
    }

    /* renamed from: b */
    public synchronized void mo6987b(String str) {
        Editor edit = this.f377c.edit();
        edit.putString("last_name", str);
        edit.apply();
    }

    /* renamed from: c */
    public synchronized boolean mo6989c(String str) {
        boolean z = false;
        synchronized (this) {
            if (str != null) {
                str = str.trim();
            }
            if (str == null || ValidationUtils.isValidEmailAddress(str)) {
                Editor edit = this.f377c.edit();
                edit.putString("email", str);
                edit.apply();
                z = true;
            } else {
                AppboyLogger.m1739w(f375a, String.format("Email address is not valid: %s", new Object[]{str}));
            }
        }
        return z;
    }

    /* renamed from: a */
    public synchronized void mo6982a(Gender gender) {
        Editor edit = this.f377c.edit();
        if (gender == null) {
            edit.putString("gender", null);
        } else {
            edit.putString("gender", gender.forJsonPut());
        }
        edit.apply();
    }

    /* renamed from: a */
    public synchronized boolean mo6984a(int i, Month month, int i2) {
        boolean z;
        if (month == null) {
            AppboyLogger.m1739w(f375a, "Month cannot be null.");
            z = false;
        } else {
            String a = C0455dp.m517a(C0455dp.m518a(i, month.getValue(), i2), C0628q.SHORT);
            Editor edit = this.f377c.edit();
            edit.putString("dob", a);
            edit.apply();
            z = true;
        }
        return z;
    }

    /* renamed from: d */
    public synchronized void mo6990d(String str) {
        Editor edit = this.f377c.edit();
        edit.putString("country", str);
        edit.apply();
    }

    /* renamed from: e */
    public synchronized void mo6991e(String str) {
        Editor edit = this.f377c.edit();
        edit.putString("home_city", str);
        edit.apply();
    }

    /* renamed from: f */
    public synchronized boolean mo6992f(String str) {
        boolean z = false;
        synchronized (this) {
            if (str != null) {
                str = str.trim();
            }
            if (str == null || ValidationUtils.isValidPhoneNumber(str)) {
                Editor edit = this.f377c.edit();
                edit.putString("phone", str);
                edit.apply();
                z = true;
            } else {
                AppboyLogger.m1739w(f375a, String.format("Phone number contains invalid characters (allowed are digits, spaces, or any of the following +.-()): %s", new Object[]{str}));
            }
        }
        return z;
    }

    /* renamed from: g */
    public synchronized void mo6993g(String str) {
        Editor edit = this.f377c.edit();
        edit.putString("image_url", str);
        edit.apply();
    }

    /* renamed from: h */
    public synchronized void mo6994h(String str) {
        Editor edit = this.f377c.edit();
        if (StringUtils.isNullOrBlank(str)) {
            edit.remove("piqid");
        } else {
            edit.putString("piqid", str);
        }
        edit.apply();
    }

    /* renamed from: a */
    public synchronized boolean mo6986a(String str, Object obj) {
        boolean z = false;
        synchronized (this) {
            if (!ValidationUtils.isBlacklistedCustomAttributeKey(str, this.f378d.mo6975i())) {
                if (ValidationUtils.isValidCustomAttributeKey(str)) {
                    String ensureAppboyFieldLength = ValidationUtils.ensureAppboyFieldLength(str);
                    if (obj == null) {
                        AppboyLogger.m1739w(f375a, "Custom attribute value cannot be null.");
                    } else {
                        Editor edit = this.f377c.edit();
                        if (obj instanceof Boolean) {
                            edit.putBoolean(ensureAppboyFieldLength, ((Boolean) obj).booleanValue());
                        } else if (obj instanceof Integer) {
                            edit.putInt(ensureAppboyFieldLength, ((Integer) obj).intValue());
                        } else if (obj instanceof Float) {
                            edit.putFloat(ensureAppboyFieldLength, ((Float) obj).floatValue());
                        } else if (obj instanceof Long) {
                            edit.putLong(ensureAppboyFieldLength, ((Long) obj).longValue());
                        } else if (obj instanceof String) {
                            edit.putString(ensureAppboyFieldLength, ValidationUtils.ensureAppboyFieldLength((String) obj));
                        } else if (obj instanceof Date) {
                            edit.putString(ensureAppboyFieldLength, C0455dp.m517a((Date) obj, C0628q.LONG));
                        } else {
                            AppboyLogger.m1739w(f375a, "Unsupported custom attribute type");
                        }
                        edit.apply();
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public synchronized boolean mo6985a(String str, long j) {
        return mo6986a(str, (Object) C0455dp.m520a(j));
    }

    /* renamed from: i */
    public synchronized boolean mo6995i(String str) {
        boolean z = false;
        synchronized (this) {
            if (str == null) {
                AppboyLogger.m1739w(f375a, "Custom attribute key cannot be null.");
            } else if (ValidationUtils.isValidCustomAttributeKey(str)) {
                Editor edit = this.f377c.edit();
                edit.putString(str, "appboy_null_5a8579f5-079b-4681-a046-0f3c46a4ef58");
                edit.apply();
                z = true;
            }
        }
        return z;
    }

    /* renamed from: c */
    public C0405cf mo6929a() {
        JSONObject jSONObject = new JSONObject(this.f377c.getAll());
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                if (jSONObject.get(str).equals("appboy_null_5a8579f5-079b-4681-a046-0f3c46a4ef58")) {
                    jSONObject.put(str, JSONObject.NULL);
                }
                if (str.equals("piqid") && C0372bb.m148a()) {
                    jSONObject.put("piqid_changed", true);
                }
            } catch (JSONException e) {
                AppboyLogger.m1739w(f375a, String.format("Failed to check outbound json key %s for null placeholders.", new Object[]{str}));
            }
        }
        for (String str2 : f376b) {
            try {
                if (this.f377c.contains(str2)) {
                    jSONObject.put(str2, new JSONObject(this.f377c.getString(str2, "")));
                }
            } catch (JSONException e2) {
                AppboyLogger.m1739w(f375a, String.format("Failed to properly convert [%s] value to OutboundUser for export.", new Object[]{str2}));
            }
        }
        return new C0405cf(jSONObject);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo6930a(C0405cf cfVar) {
        if (cfVar != null) {
            Editor edit = this.f377c.edit();
            JSONObject a = cfVar.forJsonPut();
            Map all = this.f377c.getAll();
            Iterator keys = a.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (all.containsKey(str)) {
                    Object obj = all.get(str);
                    Object opt = a.opt(str);
                    if (opt == null) {
                        if (obj == null) {
                            edit.remove(str);
                        }
                    } else if (opt instanceof JSONObject) {
                        try {
                            if (C0605hs.m1077a(String.valueOf(obj), opt.toString(), C0606ht.NON_EXTENSIBLE).mo7284a()) {
                                edit.remove(str);
                            }
                        } catch (JSONException e) {
                            AppboyLogger.m1736e(f375a, "Caught exception confirming and unlocking Json objects.", e);
                        }
                    } else if (opt.equals(obj)) {
                        edit.remove(str);
                    } else if (opt.equals(JSONObject.NULL)) {
                        edit.remove(str);
                    }
                }
            }
            edit.apply();
        }
    }
}
