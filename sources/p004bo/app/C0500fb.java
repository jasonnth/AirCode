package p004bo.app;

import com.appboy.models.outgoing.AppboyProperties;
import com.mparticle.commerce.Product;

/* renamed from: bo.app.fb */
public class C0500fb extends C0504ff {

    /* renamed from: a */
    private String f470a;

    public C0500fb(String str, AppboyProperties appboyProperties, C0386bo boVar) {
        super(appboyProperties, boVar);
        this.f470a = str;
    }

    /* renamed from: a */
    public String mo7055a() {
        return this.f470a;
    }

    /* renamed from: b */
    public String mo7048b() {
        return Product.PURCHASE;
    }
}
