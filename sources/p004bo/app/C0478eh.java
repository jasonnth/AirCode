package p004bo.app;

import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyLogger;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.eh */
public class C0478eh implements C0474ed {

    /* renamed from: a */
    private static final String f434a = AppboyLogger.getAppboyLogTag(C0478eh.class);

    /* renamed from: b */
    private C0492eu f435b;

    /* renamed from: c */
    private String f436c;

    /* renamed from: d */
    private int f437d;

    /* renamed from: e */
    private Object f438e;

    C0478eh(C0492eu euVar, String str, int i) {
        this.f435b = euVar;
        this.f436c = str;
        this.f437d = i;
    }

    protected C0478eh(JSONObject jSONObject) {
        this((C0492eu) C0460ds.m528a(jSONObject, "property_type", C0492eu.class, C0492eu.UNKNOWN), jSONObject.getString("property_key"), jSONObject.getInt("comparator"));
        if (!jSONObject.has("property_value")) {
            return;
        }
        if (this.f435b.equals(C0492eu.STRING)) {
            this.f438e = jSONObject.getString("property_value");
        } else if (this.f435b.equals(C0492eu.BOOLEAN)) {
            this.f438e = Boolean.valueOf(jSONObject.getBoolean("property_value"));
        } else if (this.f435b.equals(C0492eu.NUMBER)) {
            this.f438e = Double.valueOf(jSONObject.getDouble("property_value"));
        } else if (this.f435b.equals(C0492eu.DATE)) {
            this.f438e = Long.valueOf(jSONObject.getLong("property_value"));
        }
    }

    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        if (!(exVar instanceof C0496ey)) {
            return false;
        }
        AppboyProperties f = ((C0496ey) exVar).mo7052f();
        Object obj = null;
        if (f != null) {
            try {
                obj = f.forJsonPut().opt(this.f436c);
            } catch (Exception e) {
                AppboyLogger.m1736e(f434a, "Caught exception checking property filter condition.", e);
                return false;
            }
        }
        if (obj == null) {
            return this.f437d == 12 || this.f437d == 17 || this.f437d == 2;
        } else if (this.f437d == 11) {
            return true;
        } else {
            if (this.f437d == 12) {
                return false;
            }
            switch (this.f435b) {
                case STRING:
                    return m599c(obj);
                case BOOLEAN:
                    return m598b(obj);
                case DATE:
                    return m597a(obj, exVar.mo7049c());
                case NUMBER:
                    return m596a(obj);
                default:
                    return false;
            }
        }
    }

    /* renamed from: a */
    private boolean m597a(Object obj, long j) {
        boolean z;
        Date date = null;
        if (obj instanceof String) {
            date = C0455dp.m521a((String) obj, C0628q.LONG);
        }
        if (date == null) {
            if (this.f437d == 2) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
        long a = C0455dp.m516a(date);
        long longValue = ((Number) this.f438e).longValue();
        switch (this.f437d) {
            case 1:
                if (a != longValue) {
                    return false;
                }
                return true;
            case 2:
                if (a == longValue) {
                    return false;
                }
                return true;
            case 3:
                if (a <= longValue) {
                    return false;
                }
                return true;
            case 4:
                if (a < j - longValue) {
                    return false;
                }
                return true;
            case 5:
                if (a >= longValue) {
                    return false;
                }
                return true;
            case 6:
                if (a > j - longValue) {
                    return false;
                }
                return true;
            case 15:
                if (a >= longValue + j) {
                    return false;
                }
                return true;
            case 16:
                if (a <= longValue + j) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    /* renamed from: a */
    private boolean m596a(Object obj) {
        boolean z;
        if ((obj instanceof Integer) || (obj instanceof Double)) {
            double doubleValue = ((Number) obj).doubleValue();
            double doubleValue2 = ((Number) this.f438e).doubleValue();
            switch (this.f437d) {
                case 1:
                    if (doubleValue != doubleValue2) {
                        return false;
                    }
                    return true;
                case 2:
                    if (doubleValue == doubleValue2) {
                        return false;
                    }
                    return true;
                case 3:
                    if (doubleValue <= doubleValue2) {
                        return false;
                    }
                    return true;
                case 5:
                    if (doubleValue >= doubleValue2) {
                        return false;
                    }
                    return true;
                default:
                    return false;
            }
        } else {
            if (this.f437d == 2) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
    }

    /* renamed from: b */
    private boolean m598b(Object obj) {
        if (obj instanceof Boolean) {
            switch (this.f437d) {
                case 1:
                    return obj.equals(this.f438e);
                case 2:
                    if (obj.equals(this.f438e)) {
                        return false;
                    }
                    return true;
                default:
                    return false;
            }
        } else if (this.f437d == 2) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: c */
    private boolean m599c(Object obj) {
        boolean z;
        if (!(obj instanceof String)) {
            if (this.f437d == 2 || this.f437d == 17) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
        switch (this.f437d) {
            case 1:
                return obj.equals(this.f438e);
            case 2:
                if (obj.equals(this.f438e)) {
                    return false;
                }
                return true;
            case 10:
                return ((String) obj).matches((String) this.f438e);
            case 17:
                if (((String) obj).matches((String) this.f438e)) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!this.f435b.equals(C0492eu.UNKNOWN)) {
                jSONObject.put("property_type", this.f435b.toString());
            }
            jSONObject.put("property_key", this.f436c);
            jSONObject.put("comparator", this.f437d);
            jSONObject.put("property_value", this.f438e);
        } catch (JSONException e) {
            AppboyLogger.m1736e(f434a, "Caught exception creating property filter Json.", e);
        }
        return jSONObject;
    }
}
