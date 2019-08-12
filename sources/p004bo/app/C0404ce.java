package p004bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ce */
public final class C0404ce implements C0387bp, IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f262a = AppboyLogger.getAppboyLogTag(C0404ce.class);

    /* renamed from: b */
    private final List<C0385bn> f263b;

    /* renamed from: c */
    private final C0399ca f264c;

    /* renamed from: d */
    private final C0405cf f265d;

    public C0404ce(List<C0385bn> list, C0399ca caVar, C0405cf cfVar) {
        this.f263b = list;
        this.f264c = caVar;
        this.f265d = cfVar;
    }

    /* renamed from: a */
    public List<C0385bn> mo6893a() {
        return this.f263b;
    }

    /* renamed from: b */
    public C0399ca mo6894b() {
        return this.f264c;
    }

    /* renamed from: c */
    public C0405cf mo6895c() {
        return this.f265d;
    }

    /* renamed from: d */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.f263b != null && !this.f263b.isEmpty()) {
                jSONObject.put("sessions", C0460ds.m531a(this.f263b));
            }
            if (this.f264c != null) {
                jSONObject.put("device", this.f264c.forJsonPut());
            }
            if (this.f265d != null) {
                jSONObject.put("user", this.f265d.forJsonPut());
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f262a, "Caught exception creating outbound extras Json.", e);
        }
        return jSONObject;
    }

    /* renamed from: h */
    public boolean mo6822h() {
        ArrayList<C0387bp> arrayList = new ArrayList<>();
        if (this.f263b != null) {
            arrayList.addAll(this.f263b);
        }
        arrayList.add(this.f264c);
        arrayList.add(this.f265d);
        for (C0387bp bpVar : arrayList) {
            if (bpVar != null && !bpVar.mo6822h()) {
                return false;
            }
        }
        return true;
    }
}
