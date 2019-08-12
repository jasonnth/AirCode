package p004bo.app;

import com.facebook.internal.ServerProtocol;

/* renamed from: bo.app.s */
public enum C0630s {
    ANDROID_VERSION("android_version"),
    ABI("abi"),
    CARRIER("carrier"),
    MODEL("model"),
    LOCALE_LANGUAGE("language"),
    LOCALE_COUNTRY("country"),
    LOCALE(AccountKitGraphConstants.PARAMETER_LOCALE),
    TIMEZONE("time_zone"),
    DISPLAY(ServerProtocol.DIALOG_PARAM_DISPLAY),
    PUSH_TOKEN("push_token"),
    CONNECTED_DEVICES("connected_devices");
    

    /* renamed from: l */
    private String f887l;

    private C0630s(String str) {
        this.f887l = str;
    }

    /* renamed from: a */
    public String mo7329a() {
        return this.f887l;
    }
}
