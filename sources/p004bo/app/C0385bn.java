package p004bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.bn */
public class C0385bn implements C0387bp, IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f190a = String.format("%s.%s", new Object[]{"Appboy", C0385bn.class.getName()});

    /* renamed from: b */
    private final Set<C0386bo> f191b;

    /* renamed from: c */
    private final C0391bt f192c;

    /* renamed from: d */
    private final C0395bx f193d;

    /* renamed from: e */
    private final double f194e;

    /* renamed from: f */
    private volatile Double f195f;

    /* renamed from: g */
    private final boolean f196g;

    public C0385bn(C0391bt btVar, Double d, Double d2, Set<C0386bo> set, C0395bx bxVar, boolean z) {
        this.f192c = btVar;
        this.f194e = d.doubleValue();
        this.f195f = d2;
        this.f191b = set;
        this.f193d = bxVar;
        this.f196g = z;
    }

    /* renamed from: a */
    public C0391bt mo6814a() {
        return this.f192c;
    }

    /* renamed from: b */
    public void mo6815b() {
        this.f192c.mo6829a(this);
    }

    /* renamed from: c */
    public void mo6816c() {
        this.f192c.mo6834b(this);
    }

    /* renamed from: d */
    public boolean mo6817d() {
        return this.f196g;
    }

    /* renamed from: e */
    public Double mo6818e() {
        return this.f195f;
    }

    /* renamed from: f */
    public C0383bl mo6819f() {
        return new C0383bl(this.f191b);
    }

    /* renamed from: g */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("guid", this.f193d.forJsonPut());
            jSONObject.put("start_time", this.f194e);
            if (!this.f191b.isEmpty()) {
                jSONObject.put("events", C0460ds.m531a(this.f191b));
            }
            if (this.f196g) {
                jSONObject.put("new", true);
            }
            if (this.f195f != null) {
                jSONObject.put("end_time", this.f195f);
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f190a, "Caught exception creating dispatch session Json.", e);
        }
        return jSONObject;
    }

    /* renamed from: h */
    public boolean mo6822h() {
        Set a = mo6819f().mo6806a();
        return !this.f196g && this.f195f == null && (a == null || a.isEmpty());
    }
}
