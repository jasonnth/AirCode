package p004bo.app;

import com.appboy.enums.ErrorType;
import com.appboy.models.ResponseError;
import com.appboy.support.AppboyLogger;
import java.net.URI;
import java.util.Map;

/* renamed from: bo.app.cy */
public final class C0428cy implements Runnable {

    /* renamed from: a */
    private static final String f308a = AppboyLogger.getAppboyLogTag(C0428cy.class);

    /* renamed from: b */
    private final C0415cm f309b;

    /* renamed from: c */
    private final C0343ac f310c;

    /* renamed from: d */
    private final C0343ac f311d;

    /* renamed from: e */
    private final Map<String, String> f312e;

    /* renamed from: f */
    private final C0431d f313f;

    public C0428cy(C0415cm cmVar, C0470e eVar, C0431d dVar, C0343ac acVar, C0343ac acVar2) {
        this.f309b = cmVar;
        this.f310c = acVar;
        this.f311d = acVar2;
        this.f312e = eVar.mo6703a();
        this.f313f = dVar;
    }

    public void run() {
        try {
            C0417co a = m401a();
            if (a != null) {
                this.f309b.mo6910a(this.f311d, (C0392bu) null);
                this.f310c.mo6736a(new C0411ck(this.f309b, a), C0411ck.class);
                this.f309b.mo6909a(this.f310c);
                return;
            }
            AppboyLogger.m1739w(f308a, "Request response was null, failing task.");
            this.f309b.mo6911a(this.f311d, new ResponseError(ErrorType.UNRECOGNIZED_ERROR, "An error occurred during request processing, resulting in no valid response being received. Check the error log for more details."));
            this.f310c.mo6736a(new C0410cj(this.f309b), C0410cj.class);
        } catch (Exception e) {
            AppboyLogger.m1740w(f308a, "Experienced exception processing request response. Failing task.", e);
        }
    }

    /* renamed from: a */
    private C0417co m401a() {
        URI a = C0457dq.m524a(this.f309b.mo6916b());
        switch (this.f309b.mo6908a()) {
            case GET:
                return new C0417co(this.f313f.mo6883a(a, this.f312e));
            default:
                AppboyLogger.m1739w(f308a, String.format("Received a PlaceIQ request with an unknown Http verb: [%s]", new Object[]{this.f309b.mo6908a()}));
                return null;
        }
    }
}
