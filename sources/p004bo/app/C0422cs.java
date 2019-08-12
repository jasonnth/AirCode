package p004bo.app;

import android.net.Uri;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.cs */
public final class C0422cs extends C0419cq {

    /* renamed from: b */
    private static final String f302b = AppboyLogger.getAppboyLogTag(C0422cs.class);

    /* renamed from: c */
    private final List<C0634w> f303c = new ArrayList();

    public C0422cs(String str, C0629r... rVarArr) {
        super(Uri.parse(str + "data"), null);
        List asList = Arrays.asList(rVarArr);
        if (asList.contains(C0629r.FEED)) {
            this.f303c.add(C0634w.FEED);
        }
        if (asList.contains(C0629r.INAPP)) {
            this.f303c.add(C0634w.INAPP);
        }
        if (asList.contains(C0629r.TRIGGERS)) {
            this.f303c.add(C0634w.TRIGGERS);
        }
    }

    /* renamed from: a */
    public C0632u mo6908a() {
        return C0632u.POST;
    }

    /* renamed from: a */
    public void mo6910a(C0343ac acVar, C0392bu buVar) {
    }

    /* renamed from: f */
    public boolean mo6920f() {
        return m375h() && super.mo6920f();
    }

    /* renamed from: h */
    private boolean m375h() {
        return this.f303c == null || this.f303c.size() == 0;
    }

    /* renamed from: e */
    public JSONObject mo6919e() {
        JSONObject e = super.mo6919e();
        if (e == null) {
            return null;
        }
        try {
            e.put("only_respond_with", C0460ds.m531a(this.f303c));
            return e;
        } catch (JSONException e2) {
            AppboyLogger.m1740w(f302b, "Experienced JSONException while retrieving parameters. Returning null.", e2);
            return null;
        }
    }
}
