package visidon.Lib;

import android.graphics.Rect;
import visidon.Lib.VerificationAPI.C5533i;

/* renamed from: visidon.Lib.a */
public final class C5534a {

    /* renamed from: a */
    public Rect f7291a = new Rect(0, 0, 0, 0);

    /* renamed from: b */
    public int f7292b = 0;

    /* renamed from: c */
    public int f7293c = 0;

    /* renamed from: d */
    private int f7294d = 0;

    /* renamed from: e */
    private int f7295e = 0;

    /* renamed from: f */
    private int f7296f = 0;

    /* renamed from: g */
    private int f7297g = 0;

    public C5534a(int[] iArr) {
        C5533i iVar = C5533i.ERROR;
        C5533i iVar2 = C5533i.ERROR;
        if (iArr != null) {
            if (iArr.length == 2) {
                C5533i.values();
                C5533i.values();
            } else if (iArr.length == 10) {
                C5533i.values();
                C5533i.values();
                this.f7292b = iArr[2];
                this.f7293c = iArr[3];
                this.f7294d = iArr[6];
                this.f7296f = iArr[7];
                this.f7295e = this.f7294d + iArr[8];
                this.f7297g = this.f7296f + iArr[9];
                this.f7291a = new Rect(this.f7294d, this.f7296f, this.f7295e, this.f7297g);
            }
        }
    }
}
