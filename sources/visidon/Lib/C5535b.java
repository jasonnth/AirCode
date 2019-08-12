package visidon.Lib;

import android.content.Context;
import visidon.Lib.VerificationAPI.C5527a;
import visidon.Lib.VerificationAPI.C5528b;
import visidon.Lib.VerificationAPI.C5530e;
import visidon.Lib.VerificationAPI.C5532h;

/* renamed from: visidon.Lib.b */
public final class C5535b {

    /* renamed from: a */
    public Context f7298a;

    /* renamed from: b */
    public int f7299b = 0;

    /* renamed from: c */
    public int f7300c = 0;

    /* renamed from: d */
    public String f7301d;

    /* renamed from: e */
    public C5527a f7302e;

    /* renamed from: f */
    public C5532h f7303f;

    /* renamed from: g */
    public C5530e f7304g;

    /* renamed from: h */
    public C5528b f7305h;

    public C5535b(Context context) {
        this.f7298a = context;
        this.f7301d = context.getFilesDir() + "/database.db";
        this.f7302e = C5527a.VIDEO;
        this.f7303f = C5532h.MEDIUM;
        this.f7304g = C5530e.OFF;
        this.f7305h = C5528b.MANUAL;
    }
}
