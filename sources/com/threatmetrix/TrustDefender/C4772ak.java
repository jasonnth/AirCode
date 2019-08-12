package com.threatmetrix.TrustDefender;

/* renamed from: com.threatmetrix.TrustDefender.ak */
final class C4772ak {

    /* renamed from: a */
    private long f4228a = 0;

    /* renamed from: b */
    private long f4229b = 0;

    /* renamed from: c */
    private String f4230c = "";

    /* renamed from: d */
    private int f4231d = 0;

    C4772ak() {
    }

    /* renamed from: a */
    public final long mo45971a() {
        return this.f4228a;
    }

    /* renamed from: a */
    public final void mo45973a(long m_enabledOptions) {
        this.f4228a = m_enabledOptions;
    }

    /* renamed from: b */
    public final long mo45976b() {
        return this.f4229b;
    }

    /* renamed from: b */
    public final void mo45977b(long m_disabledOptions) {
        this.f4229b = m_disabledOptions;
    }

    /* renamed from: c */
    public final String mo45978c() {
        return this.f4230c;
    }

    /* renamed from: a */
    public final void mo45974a(String m_sdkVersion) {
        this.f4230c = m_sdkVersion;
    }

    /* renamed from: a */
    public final boolean mo45975a(long enabledOptions, long disabledOptions, String sdkVersion, int quietPeriod) {
        return (enabledOptions == this.f4228a && disabledOptions == this.f4229b && sdkVersion.equals(this.f4230c) && quietPeriod == this.f4231d) ? false : true;
    }

    /* renamed from: d */
    public final int mo45979d() {
        return this.f4231d;
    }

    /* renamed from: a */
    public final void mo45972a(int m_quietPeriod) {
        this.f4231d = m_quietPeriod;
    }
}
