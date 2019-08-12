package com.threatmetrix.TrustDefender;

import java.util.HashMap;
import java.util.Locale;

/* renamed from: com.threatmetrix.TrustDefender.n */
class C4823n extends HashMap<String, String> {

    /* renamed from: a */
    private static final String f4617a = C4834w.m2892a(C4823n.class);

    /* renamed from: b */
    private int f4618b = 0;

    /* renamed from: c */
    private HashMap<String, Integer> f4619c = new HashMap<>();

    C4823n() {
    }

    /* renamed from: a */
    public final int mo46080a() {
        return this.f4618b;
    }

    /* renamed from: a */
    public final void mo46085a(int postValueLengthLimit) {
        this.f4618b = 255;
    }

    /* renamed from: a */
    public final Integer mo46084a(String key) {
        return (Integer) this.f4619c.get(key);
    }

    /* renamed from: a */
    public final C4823n mo46081a(String name, String value) {
        mo46083a(name, value, false);
        return this;
    }

    /* renamed from: a */
    public final C4823n mo46082a(String name, String value, Integer maxLength) {
        this.f4619c.put(name, maxLength);
        mo46083a(name, value, false);
        return this;
    }

    /* renamed from: a */
    public final C4823n mo46083a(String name, String value, boolean foldToLowercase) {
        if (!foldToLowercase || value == null || value.isEmpty()) {
            put(name, value);
        } else {
            put(name, value.toLowerCase(Locale.US));
        }
        return this;
    }

    /* renamed from: b */
    public final String mo46086b() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (String key : keySet()) {
            String value = (String) get(key);
            if (value != null && !value.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(key);
                Integer length = (Integer) this.f4619c.get(key);
                if (length != null && value.length() > length.intValue()) {
                    value = value.substring(0, length.intValue());
                }
                if (length == null && this.f4618b != 0 && value.length() > this.f4618b) {
                    value = value.substring(0, this.f4618b);
                }
                sb.append("=");
                sb.append(C4770ai.m2622a(value));
            }
        }
        return sb.toString();
    }
}
