package com.paypal.android.sdk.onetouch.core.metadata;

import android.location.Location;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.g */
public final class C4681g {

    /* renamed from: A */
    public String f3928A;

    /* renamed from: B */
    public Boolean f3929B;

    /* renamed from: C */
    public String f3930C;

    /* renamed from: D */
    public String f3931D;

    /* renamed from: E */
    public Boolean f3932E;

    /* renamed from: F */
    public String f3933F;

    /* renamed from: G */
    public String f3934G;

    /* renamed from: H */
    public long f3935H;

    /* renamed from: I */
    public long f3936I;

    /* renamed from: J */
    public String f3937J;

    /* renamed from: K */
    public Boolean f3938K;

    /* renamed from: L */
    public Integer f3939L;

    /* renamed from: M */
    public int f3940M = -1;

    /* renamed from: N */
    public int f3941N = -1;

    /* renamed from: O */
    public String f3942O;

    /* renamed from: P */
    public int f3943P = -1;

    /* renamed from: Q */
    public String f3944Q;

    /* renamed from: R */
    public Boolean f3945R;

    /* renamed from: S */
    public Boolean f3946S;

    /* renamed from: T */
    public String f3947T;

    /* renamed from: U */
    public long f3948U;

    /* renamed from: V */
    public long f3949V;

    /* renamed from: W */
    public String f3950W;

    /* renamed from: X */
    public String f3951X;

    /* renamed from: Y */
    public String f3952Y;

    /* renamed from: Z */
    public String f3953Z;

    /* renamed from: a */
    public String f3954a;

    /* renamed from: aa */
    public String f3955aa;

    /* renamed from: ab */
    public String f3956ab;

    /* renamed from: ac */
    public String f3957ac;

    /* renamed from: ad */
    public String f3958ad;

    /* renamed from: ae */
    public String f3959ae;

    /* renamed from: af */
    public List<String> f3960af;

    /* renamed from: ag */
    public Map<String, Object> f3961ag;

    /* renamed from: aj */
    private String f3962aj = "full";

    /* renamed from: b */
    public String f3963b;

    /* renamed from: c */
    public String f3964c;

    /* renamed from: d */
    public int f3965d = -1;

    /* renamed from: e */
    public String f3966e;

    /* renamed from: f */
    public int f3967f = -1;

    /* renamed from: g */
    public String f3968g;

    /* renamed from: h */
    public String f3969h;

    /* renamed from: i */
    public String f3970i;

    /* renamed from: j */
    public String f3971j;

    /* renamed from: k */
    public String f3972k;

    /* renamed from: l */
    public String f3973l;

    /* renamed from: m */
    public String f3974m;

    /* renamed from: n */
    public long f3975n = -1;

    /* renamed from: o */
    public String f3976o;

    /* renamed from: p */
    public List<String> f3977p;

    /* renamed from: q */
    public List<String> f3978q;

    /* renamed from: r */
    public String f3979r;

    /* renamed from: s */
    public String f3980s;

    /* renamed from: t */
    public String f3981t;

    /* renamed from: u */
    public Location f3982u;

    /* renamed from: v */
    public int f3983v = -1;

    /* renamed from: w */
    public String f3984w;

    /* renamed from: x */
    public String f3985x = InternalLogger.EVENT_PARAM_SDK_ANDROID;

    /* renamed from: y */
    public String f3986y;

    /* renamed from: z */
    public String f3987z;

    /* renamed from: a */
    private static JSONObject m2432a(Location location) {
        if (location == null) {
            return null;
        }
        try {
            return new JSONObject("{\"lat\":" + location.getLatitude() + ",\"lng\":" + location.getLongitude() + ",\"acc\":" + location.getAccuracy() + ",\"timestamp\":" + location.getTime() + "}");
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    private void m2433a(JSONObject jSONObject) {
        if (this.f3961ag != null) {
            for (Entry entry : this.f3961ag.entrySet()) {
                try {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    C4677ai.m2391a((String) null, (String) null, (Throwable) e);
                }
            }
        }
    }

    /* renamed from: a */
    public final JSONObject mo45417a() {
        Integer num = null;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_guid", this.f3954a);
            jSONObject.put("app_id", this.f3963b);
            jSONObject.put("app_version", this.f3964c);
            jSONObject.put("base_station_id", this.f3965d == -1 ? null : Integer.valueOf(this.f3965d));
            jSONObject.put("bssid", this.f3966e);
            jSONObject.put("bssid_array", this.f3960af == null ? null : new JSONArray(this.f3960af));
            jSONObject.put("cell_id", this.f3967f == -1 ? null : Integer.valueOf(this.f3967f));
            jSONObject.put("comp_version", this.f3968g);
            jSONObject.put("conf_url", this.f3969h);
            jSONObject.put("conf_version", this.f3970i);
            jSONObject.put("conn_type", this.f3971j);
            jSONObject.put("device_id", this.f3972k);
            jSONObject.put("dc_id", this.f3958ad);
            jSONObject.put("device_model", this.f3973l);
            jSONObject.put("device_name", this.f3974m);
            jSONObject.put("device_uptime", this.f3975n == -1 ? null : Long.valueOf(this.f3975n));
            jSONObject.put("ip_addrs", this.f3976o);
            jSONObject.put("ip_addresses", this.f3977p == null ? null : new JSONArray(this.f3977p));
            jSONObject.put("known_apps", this.f3978q == null ? null : new JSONArray(this.f3978q));
            jSONObject.put("linker_id", this.f3979r);
            jSONObject.put("locale_country", this.f3980s);
            jSONObject.put("locale_lang", this.f3981t);
            jSONObject.put("location", m2432a(this.f3982u));
            jSONObject.put("location_area_code", this.f3983v == -1 ? null : Integer.valueOf(this.f3983v));
            jSONObject.put("mac_addrs", this.f3984w);
            jSONObject.put("os_type", this.f3985x);
            jSONObject.put("os_version", this.f3986y);
            jSONObject.put("payload_type", this.f3962aj);
            jSONObject.put("phone_type", this.f3987z);
            jSONObject.put("risk_comp_session_id", this.f3928A);
            jSONObject.put("roaming", this.f3929B);
            jSONObject.put("sim_operator_name", "".equals(this.f3930C) ? null : this.f3930C);
            jSONObject.put("sim_serial_number", this.f3931D);
            jSONObject.put("sms_enabled", this.f3932E);
            jSONObject.put("ssid", this.f3933F);
            jSONObject.put("cdma_network_id", this.f3941N == -1 ? null : Integer.valueOf(this.f3941N));
            jSONObject.put("cdma_system_id", this.f3940M == -1 ? null : Integer.valueOf(this.f3940M));
            jSONObject.put("subscriber_id", this.f3934G);
            jSONObject.put(ErfExperimentsModel.TIMESTAMP, this.f3935H);
            jSONObject.put("total_storage_space", this.f3936I);
            jSONObject.put("tz_name", this.f3937J);
            jSONObject.put("ds", this.f3938K);
            jSONObject.put("tz", this.f3939L);
            jSONObject.put("network_operator", this.f3942O);
            String str = "source_app";
            if (this.f3943P != -1) {
                num = Integer.valueOf(this.f3943P);
            }
            jSONObject.put(str, num);
            jSONObject.put("source_app_version", this.f3944Q);
            jSONObject.put("is_emulator", this.f3945R);
            jSONObject.put("is_rooted", this.f3946S);
            jSONObject.put("pairing_id", this.f3947T);
            jSONObject.put("app_first_install_time", this.f3948U);
            jSONObject.put("app_last_update_time", this.f3949V);
            jSONObject.put(JPushReportInterface.ANDROID_ID, this.f3950W);
            jSONObject.put("serial_number", this.f3952Y);
            jSONObject.put("notif_token", this.f3951X);
            jSONObject.put("bluetooth_mac_addrs", null);
            jSONObject.put("gsf_id", this.f3953Z);
            jSONObject.put("VPN_setting", this.f3956ab);
            jSONObject.put("proxy_setting", this.f3955aa);
            jSONObject.put("c", this.f3957ac);
            jSONObject.put("pm", this.f3959ae);
            m2433a(jSONObject);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    public final JSONObject mo45418a(C4681g gVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("is_emulator", gVar.f3945R);
            jSONObject.put("is_rooted", gVar.f3946S);
            jSONObject.put("app_guid", gVar.f3954a);
            jSONObject.put("risk_comp_session_id", gVar.f3928A);
            jSONObject.put(ErfExperimentsModel.TIMESTAMP, gVar.f3935H);
            jSONObject.put("payload_type", "incremental");
            jSONObject.put("source_app", gVar.f3943P);
            jSONObject.put("pairing_id", gVar.f3947T);
            m2433a(jSONObject);
            if (this.f3963b != null && !this.f3963b.equals(gVar.f3963b)) {
                jSONObject.put("app_id", gVar.f3963b);
            }
            if (this.f3964c != null && !this.f3964c.equals(gVar.f3964c)) {
                jSONObject.put("app_version", gVar.f3964c);
            }
            if (this.f3965d != gVar.f3965d) {
                jSONObject.put("base_station_id", gVar.f3965d);
            }
            if (this.f3966e != null && !this.f3966e.equals(gVar.f3966e)) {
                jSONObject.put("bssid", gVar.f3966e);
            }
            if (this.f3967f != gVar.f3967f) {
                jSONObject.put("cell_id", gVar.f3967f);
            }
            if (this.f3968g != null && !this.f3968g.equals(gVar.f3968g)) {
                jSONObject.put("comp_version", gVar.f3968g);
            }
            if (this.f3970i != null && !this.f3970i.equals(gVar.f3970i)) {
                jSONObject.put("conf_version", gVar.f3970i);
            }
            if (this.f3969h != null && !this.f3969h.equals(gVar.f3969h)) {
                jSONObject.put("conf_url", gVar.f3969h);
            }
            if (this.f3971j != null && !this.f3971j.equals(gVar.f3971j)) {
                jSONObject.put("conn_type", gVar.f3971j);
            }
            if (this.f3972k != null && !this.f3972k.equals(gVar.f3972k)) {
                jSONObject.put("device_id", gVar.f3972k);
            }
            if (this.f3973l != null && !this.f3973l.equals(gVar.f3973l)) {
                jSONObject.put("device_model", gVar.f3973l);
            }
            if (this.f3974m != null && !this.f3974m.equals(gVar.f3974m)) {
                jSONObject.put("device_name", gVar.f3974m);
            }
            if (this.f3975n != gVar.f3975n) {
                jSONObject.put("device_uptime", gVar.f3975n);
            }
            if (this.f3976o != null && !this.f3976o.equals(gVar.f3976o)) {
                jSONObject.put("ip_addrs", gVar.f3976o);
            }
            if (!(this.f3977p == null || gVar.f3977p == null || this.f3977p.toString().equals(gVar.f3977p.toString()))) {
                jSONObject.put("ip_addresses", new JSONArray(gVar.f3977p));
            }
            if (!(this.f3978q == null || gVar.f3978q == null || this.f3978q.toString().equals(gVar.f3978q.toString()))) {
                jSONObject.put("known_apps", new JSONArray(gVar.f3978q));
            }
            if (this.f3979r != null && !this.f3979r.equals(gVar.f3979r)) {
                jSONObject.put("linker_id", gVar.f3979r);
            }
            if (this.f3980s != null && !this.f3980s.equals(gVar.f3980s)) {
                jSONObject.put("locale_country", gVar.f3980s);
            }
            if (this.f3981t != null && !this.f3981t.equals(gVar.f3981t)) {
                jSONObject.put("locale_lang", gVar.f3981t);
            }
            if (!(this.f3982u == null || gVar.f3982u == null || this.f3982u.toString().equals(gVar.f3982u.toString()))) {
                jSONObject.put("location", m2432a(gVar.f3982u));
            }
            if (this.f3983v != gVar.f3983v) {
                jSONObject.put("location_area_code", gVar.f3983v);
            }
            if (this.f3984w != null && !this.f3984w.equals(gVar.f3984w)) {
                jSONObject.put("mac_addrs", gVar.f3984w);
            }
            if (this.f3985x != null && !this.f3985x.equals(gVar.f3985x)) {
                jSONObject.put("os_type", gVar.f3985x);
            }
            if (this.f3986y != null && !this.f3986y.equals(gVar.f3986y)) {
                jSONObject.put("os_version", gVar.f3986y);
            }
            if (this.f3987z != null && !this.f3987z.equals(gVar.f3987z)) {
                jSONObject.put("phone_type", gVar.f3987z);
            }
            if (this.f3929B != null && this.f3929B.equals(gVar.f3929B)) {
                jSONObject.put("roaming", gVar.f3929B);
            }
            if (this.f3930C != null && !this.f3930C.equals(gVar.f3930C)) {
                jSONObject.put("sim_operator_name", gVar.f3930C);
            }
            if (this.f3931D != null && !this.f3931D.equals(gVar.f3931D)) {
                jSONObject.put("sim_serial_number", gVar.f3931D);
            }
            if (this.f3932E != null && this.f3932E.equals(gVar.f3932E)) {
                jSONObject.put("sms_enabled", gVar.f3932E);
            }
            if (this.f3933F != null && !this.f3933F.equals(gVar.f3933F)) {
                jSONObject.put("ssid", gVar.f3933F);
            }
            if (this.f3941N != gVar.f3941N) {
                jSONObject.put("cdma_network_id", gVar.f3941N);
            }
            if (this.f3940M != gVar.f3940M) {
                jSONObject.put("cdma_system_id", gVar.f3940M);
            }
            if (this.f3934G != null && !this.f3934G.equals(gVar.f3934G)) {
                jSONObject.put("subscriber_id", gVar.f3934G);
            }
            if (this.f3936I != gVar.f3936I) {
                jSONObject.put("total_storage_space", gVar.f3936I);
            }
            if (this.f3937J != null && !this.f3937J.equals(gVar.f3937J)) {
                jSONObject.put("tz_name", gVar.f3937J);
            }
            if (this.f3938K != null && !this.f3938K.equals(gVar.f3938K)) {
                jSONObject.put("ds", gVar.f3938K);
            }
            if (this.f3939L != null && !this.f3939L.equals(gVar.f3939L)) {
                jSONObject.put("tz", gVar.f3939L);
            }
            if (this.f3942O != null && !this.f3942O.equals(gVar.f3942O)) {
                jSONObject.put("network_operator", gVar.f3942O);
            }
            if (this.f3944Q != null && !this.f3944Q.equals(gVar.f3944Q)) {
                jSONObject.put("source_app_version", gVar.f3944Q);
            }
            if (this.f3948U != gVar.f3948U) {
                jSONObject.put("app_first_install_time", gVar.f3948U);
            }
            if (this.f3949V != gVar.f3949V) {
                jSONObject.put("app_last_update_time", gVar.f3949V);
            }
            if (this.f3950W != null && !this.f3950W.equals(gVar.f3950W)) {
                jSONObject.put(JPushReportInterface.ANDROID_ID, gVar.f3950W);
            }
            if (this.f3952Y != null && !this.f3952Y.equals(gVar.f3952Y)) {
                jSONObject.put("serial_number", gVar.f3952Y);
            }
            if (this.f3951X != null && !this.f3951X.equals(gVar.f3951X)) {
                jSONObject.put("notif_token", gVar.f3951X);
            }
            if (this.f3953Z != null && !this.f3953Z.equals(gVar.f3953Z)) {
                jSONObject.put("gsf_id", gVar.f3953Z);
            }
            if (this.f3956ab != null && !this.f3956ab.equals(gVar.f3956ab)) {
                jSONObject.put("VPN_setting", gVar.f3956ab);
            }
            if (this.f3955aa != null && !this.f3955aa.equals(gVar.f3955aa)) {
                jSONObject.put("proxy_setting", gVar.f3955aa);
            }
            if (this.f3957ac != null && !this.f3957ac.equals(gVar.f3957ac)) {
                jSONObject.put("c", gVar.f3957ac);
            }
            if (this.f3959ae != null && !this.f3959ae.equals(gVar.f3959ae)) {
                jSONObject.put("pm", gVar.f3959ae);
            }
            if (this.f3960af != null && !this.f3960af.equals(gVar.f3960af)) {
                jSONObject.put("bssid_arr", gVar.f3960af);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
