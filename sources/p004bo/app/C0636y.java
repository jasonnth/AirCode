package p004bo.app;

import com.facebook.internal.ServerProtocol;

/* renamed from: bo.app.y */
public enum C0636y {
    ANDROID_VERSION("android_version"),
    MODEL("model"),
    DISPLAY(ServerProtocol.DIALOG_PARAM_DISPLAY),
    DEVICE_IDENTIFIERS("device_identifiers"),
    DEVICE_TYPE("type");
    

    /* renamed from: f */
    private final String f935f;

    private C0636y(String str) {
        this.f935f = str;
    }

    /* renamed from: a */
    public String mo7333a() {
        return this.f935f;
    }
}
