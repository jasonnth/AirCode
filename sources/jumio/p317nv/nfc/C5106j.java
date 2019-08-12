package jumio.p317nv.nfc;

import android.os.AsyncTask;
import com.jumio.commons.utils.StringUtil;
import com.jumio.core.mvp.model.PublisherWithUpdate;
import com.jumio.p311nv.nfc.core.communication.TagAccessSpec;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import net.p318sf.scuba.smartcards.CardServiceException;

/* renamed from: jumio.nv.nfc.j */
/* compiled from: PassportAuthenticator */
public class C5106j extends PublisherWithUpdate<C5112o, C5112o> implements Serializable {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final C5102h f5597a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final String f5598b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final Date f5599c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final Date f5600d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final AtomicBoolean f5601e = new AtomicBoolean(false);
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C5101g f5602f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public String f5603g;

    /* renamed from: h */
    private boolean f5604h;

    /* renamed from: jumio.nv.nfc.j$a */
    /* compiled from: PassportAuthenticator */
    public class C5107a extends AsyncTask<Boolean, C5112o, C5112o> {

        /* renamed from: b */
        private boolean f5606b;

        /* renamed from: c */
        private C5108k f5607c;

        public C5107a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C5112o doInBackground(Boolean... boolArr) {
            boolean z = false;
            this.f5606b = boolArr[0].booleanValue();
            try {
                if (StringUtil.nullOrEmpty(C5106j.this.f5598b) || C5106j.this.f5599c == null || C5106j.this.f5600d == null) {
                    throw new GeneralSecurityException(String.format("Invalid BAC credentials! No.=%s, dob=%s, doe=%s ", new Object[]{C5106j.this.f5598b, C5106j.this.f5599c, C5106j.this.f5600d}));
                }
                this.f5607c = C5106j.this.f5602f.mo47164a(C5106j.this.f5598b, C5106j.this.f5599c, C5106j.this.f5600d, C5106j.this.f5603g);
                C5112o oVar = new C5112o();
                oVar.mo47204a(C5113p.INIT);
                oVar.mo47205a(C5114q.NOT_AVAILABLE);
                try {
                    publishProgress(new C5112o[]{this.f5607c.mo47170a()});
                    oVar.mo47204a(C5113p.BAC_CHECK);
                    oVar = this.f5607c.mo47175b();
                    if (oVar.mo47209d()) {
                        C5106j.this.publishError(oVar);
                        return oVar;
                    }
                    m3743b(new C5112o(C5113p.BAC_CHECK, oVar.mo47207b()));
                    oVar.mo47204a(C5113p.READ_LDS);
                    C5112o a = this.f5607c.mo47173a(this.f5606b);
                    if (oVar.mo47209d()) {
                        C5106j.this.publishError(oVar);
                        return oVar;
                    }
                    if (!oVar.mo47212g()) {
                        a.mo47203a(this.f5607c.mo47181h());
                    }
                    m3743b(a);
                    oVar.mo47204a(C5113p.PASSIVE_AUTH_DSC_CHECK);
                    C5112o d = this.f5607c.mo47177d();
                    try {
                        m3743b(new C5112o(d));
                        Thread.sleep(500);
                        d.mo47204a(C5113p.PASSIVE_AUTH_ROOT_CERT_CHECK);
                        C5100f.m3694a("PassportAuthenticator", "obtain CSCA for " + C5106j.this.f5603g.toUpperCase());
                        m3743b(new C5112o(this.f5607c.mo47172a(C5106j.this.f5597a)));
                        Thread.sleep(500);
                        d.mo47204a(C5113p.PASSIVE_AUTH_HASH_CHECK);
                        List<C5112o> c = this.f5607c.mo47176c();
                        d.mo47205a(C5114q.SUCCESSFUL);
                        d.mo47204a(C5113p.PASSIVE_AUTH_HASH_CHECK);
                        TreeMap treeMap = new TreeMap();
                        for (C5112o oVar2 : c) {
                            if (oVar2.mo47210e() == null) {
                                treeMap.put(Integer.valueOf(0), oVar2.mo47207b());
                            } else {
                                treeMap.put((Integer) oVar2.mo47210e(), oVar2.mo47207b());
                            }
                            z = oVar2.mo47209d();
                        }
                        if (z) {
                            d.mo47205a(C5114q.FAILED);
                        }
                        d.mo47203a(treeMap);
                        m3743b(new C5112o(d));
                        Thread.sleep(500);
                        d.mo47204a(C5113p.ACTIVE_AUTH_CHECK);
                        C5112o e = this.f5607c.mo47178e();
                        m3743b(new C5112o(e));
                        Thread.sleep(500);
                        e.mo47204a(C5113p.ADDITIONAL_DATA);
                        m3743b(new C5112o(this.f5607c.mo47180g()));
                        Thread.sleep(500);
                        e.mo47204a(C5113p.FACE_IMAGE);
                        if (this.f5606b) {
                            e = this.f5607c.mo47179f();
                        } else {
                            e.mo47205a(C5114q.NOT_AVAILABLE);
                            Thread.sleep(500);
                        }
                        m3743b(new C5112o(e));
                        return e;
                    } catch (CardServiceException e2) {
                        e = e2;
                        oVar = d;
                        C5100f.m3695a("PassportAuthenticator", "NFC-related exception occurred", e);
                        oVar.mo47206a(C5114q.ERROR, e);
                        C5106j.this.publishError(oVar);
                        return oVar;
                    } catch (IOException e3) {
                        e = e3;
                        oVar = d;
                        C5100f.m3695a("PassportAuthenticator", "NFC-related exception occurred", e);
                        oVar.mo47206a(C5114q.ERROR, e);
                        C5106j.this.publishError(oVar);
                        return oVar;
                    } catch (Exception e4) {
                        e = e4;
                        oVar = d;
                        C5100f.m3695a("PassportAuthenticator", "general exception occurred: " + e.getClass(), e);
                        oVar.mo47206a(C5114q.ERROR, e);
                        C5106j.this.publishError(oVar);
                        return oVar;
                    }
                } catch (CardServiceException e5) {
                    e = e5;
                } catch (IOException e6) {
                    e = e6;
                } catch (Exception e7) {
                    e = e7;
                    C5100f.m3695a("PassportAuthenticator", "general exception occurred: " + e.getClass(), e);
                    oVar.mo47206a(C5114q.ERROR, e);
                    C5106j.this.publishError(oVar);
                    return oVar;
                }
            } catch (GeneralSecurityException | CardServiceException e8) {
                C5100f.m3697b("PassportAuthenticator", "error initializing PassportAuthenticator", e8);
                C5112o oVar3 = new C5112o(C5113p.INIT, C5114q.ERROR, (Throwable) e8);
                C5106j.this.publishError(oVar3);
                return oVar3;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onProgressUpdate(C5112o... oVarArr) {
            super.onProgressUpdate(oVarArr);
            C5106j.this.publishUpdate(oVarArr[0]);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(C5112o oVar) {
            super.onPostExecute(oVar);
            C5106j.this.f5601e.set(false);
            C5106j.this.publishResult(oVar);
        }

        /* renamed from: b */
        private void m3743b(C5112o oVar) throws CardServiceException {
            if (oVar.mo47212g()) {
                throw new CardServiceException(oVar.toString());
            }
            publishProgress(new C5112o[]{oVar});
        }
    }

    public C5106j(C5102h hVar, C5101g gVar, TagAccessSpec tagAccessSpec) {
        this.f5597a = hVar;
        this.f5602f = gVar;
        this.f5598b = tagAccessSpec.getPassportNumber();
        this.f5599c = tagAccessSpec.getDateOfBirth();
        this.f5600d = tagAccessSpec.getDateOfExpiry();
        this.f5603g = tagAccessSpec.getCountryIso3();
        this.f5604h = tagAccessSpec.shouldDownloadFaceImage();
    }

    /* renamed from: a */
    public boolean mo47182a() {
        return this.f5601e.get();
    }

    /* renamed from: b */
    public void mo47183b() {
        if (this.f5601e.compareAndSet(false, true)) {
            new C5107a().execute(new Boolean[]{Boolean.valueOf(this.f5604h)});
        }
    }
}
