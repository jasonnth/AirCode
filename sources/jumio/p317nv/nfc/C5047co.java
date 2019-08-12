package jumio.p317nv.nfc;

import com.facebook.react.uimanager.ViewProps;

/* renamed from: jumio.nv.nfc.co */
/* compiled from: EntropyDecoder */
public abstract class C5047co extends C5092ef implements C5069dj {

    /* renamed from: b */
    private static final String[][] f5442b = {new String[]{"Cer", "<error detection [on|off]>", "", ViewProps.f3131ON}};

    /* renamed from: a */
    protected C5045cm f5443a;

    public C5047co(C5045cm cmVar) {
        super(cmVar);
        this.f5443a = cmVar;
    }

    /* renamed from: e */
    public C5093eg mo47019e(int i, int i2) {
        return this.f5443a.mo47019e(i, i2);
    }

    /* renamed from: a */
    public static String[][] m3446a() {
        return f5442b;
    }
}
