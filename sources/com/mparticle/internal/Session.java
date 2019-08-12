package com.mparticle.internal;

import com.mparticle.MParticle.LogLevel;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

public class Session {

    /* renamed from: a */
    public int f3727a = 0;

    /* renamed from: b */
    public String f3728b = "NO-SESSION";

    /* renamed from: c */
    public long f3729c = 0;

    /* renamed from: d */
    public long f3730d = 0;

    /* renamed from: e */
    public JSONObject f3731e = new JSONObject();

    /* renamed from: f */
    private long f3732f = 0;

    public Session() {
    }

    public Session(Session session) {
        this.f3727a = session.f3727a;
        this.f3728b = session.f3728b;
        this.f3729c = session.f3729c;
        this.f3730d = session.f3730d;
        this.f3732f = session.f3732f;
        try {
            this.f3731e = new JSONObject(session.f3731e.toString());
        } catch (JSONException e) {
        }
    }

    /* renamed from: a */
    public boolean mo44847a() {
        return this.f3729c > 0 && !"NO-SESSION".equals(this.f3728b);
    }

    /* renamed from: b */
    public Session mo44849b() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f3729c = currentTimeMillis;
        this.f3730d = currentTimeMillis;
        this.f3728b = UUID.randomUUID().toString();
        this.f3731e = new JSONObject();
        this.f3727a = 0;
        this.f3732f = 0;
        return this;
    }

    /* renamed from: c */
    public Boolean mo44850c() {
        if (this.f3727a < 1000) {
            this.f3727a++;
            return Boolean.valueOf(true);
        }
        ConfigManager.log(LogLevel.WARNING, "The event limit has been exceeded for this session.");
        return Boolean.valueOf(false);
    }

    /* renamed from: a */
    public boolean mo44848a(int i) {
        return ((long) i) < System.currentTimeMillis() - this.f3730d;
    }

    /* renamed from: d */
    public long mo44851d() {
        long j = this.f3730d - this.f3729c;
        if (j >= 0) {
        }
        return j;
    }

    /* renamed from: e */
    public long mo44852e() {
        return this.f3732f;
    }

    /* renamed from: f */
    public long mo44853f() {
        return mo44851d() - mo44852e();
    }

    /* renamed from: a */
    public void mo44846a(AtomicLong atomicLong, long j) {
        this.f3732f = (j - atomicLong.get()) + this.f3732f;
    }
}
