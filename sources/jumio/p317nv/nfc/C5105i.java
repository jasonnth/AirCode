package jumio.p317nv.nfc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.tech.IsoDep;
import com.jumio.commons.utils.IOUtils;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import javax.crypto.Cipher;
import net.p318sf.scuba.smartcards.CardServiceException;
import org.jmrtd.BACKey;
import org.jmrtd.JMRTDSecurityProvider;
import org.jmrtd.PassportService;
import org.jmrtd.lds.DG1File;
import org.jmrtd.lds.FaceImageInfo;
import org.jmrtd.lds.FaceInfo;
import org.jmrtd.lds.ImageInfo;
import org.jmrtd.lds.LDS;
import org.jmrtd.lds.LDSFileUtil;
import org.jmrtd.lds.SODFile;
import org.spongycastle.jce.provider.BouncyCastleProvider;

/* renamed from: jumio.nv.nfc.i */
/* compiled from: NfcPassportReader */
public class C5105i implements C5108k {

    /* renamed from: a */
    static final /* synthetic */ boolean f5581a = (!C5105i.class.desiredAssertionStatus());

    /* renamed from: b */
    private final String f5582b;

    /* renamed from: c */
    private final BACKey f5583c;

    /* renamed from: d */
    private final PassportService f5584d;

    /* renamed from: e */
    private final Provider f5585e = JMRTDSecurityProvider.getInstance();

    /* renamed from: f */
    private final Provider f5586f = JMRTDSecurityProvider.getBouncyCastleProvider();

    /* renamed from: g */
    private final SecureRandom f5587g = new SecureRandom();

    /* renamed from: h */
    private LDS f5588h;

    /* renamed from: i */
    private Cipher f5589i;

    /* renamed from: j */
    private MessageDigest f5590j;

    /* renamed from: k */
    private Signature f5591k;

    /* renamed from: l */
    private transient Signature f5592l;

    /* renamed from: m */
    private transient MessageDigest f5593m;

    /* renamed from: n */
    private Collection<Integer> f5594n;

    /* renamed from: o */
    private boolean f5595o = false;

    /* renamed from: p */
    private IsoDep f5596p;

    public C5105i(PassportService passportService, String str, Date date, Date date2, String str2) throws CardServiceException, GeneralSecurityException {
        m3713i();
        this.f5582b = str2;
        this.f5583c = new BACKey(str, date, date2);
        this.f5584d = passportService;
        this.f5594n = new TreeSet();
        try {
            this.f5590j = MessageDigest.getInstance("SHA1");
            this.f5591k = Signature.getInstance("SHA1WithRSA/ISO9796-2", this.f5586f);
            this.f5589i = Cipher.getInstance("RSA/NONE/NoPadding");
            this.f5592l = Signature.getInstance("SHA256withECDSA", this.f5586f);
            this.f5593m = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            C5100f.m3697b("PassportReader", "error initializing crypto stuff", e);
        }
    }

    /* renamed from: a */
    public C5112o mo47170a() throws CardServiceException {
        C5112o oVar = new C5112o(C5113p.INIT);
        C5100f.m3694a("PassportReader", "passport service created");
        this.f5584d.open();
        C5100f.m3694a("PassportReader", "passport service opened");
        this.f5584d.sendSelectApplet(false);
        C5100f.m3694a("PassportReader", "select applet done");
        oVar.mo47205a(C5114q.SUCCESSFUL);
        this.f5595o = true;
        return oVar;
    }

    /* renamed from: b */
    public C5112o mo47175b() {
        int i = 1000;
        C5100f.m3694a("PassportReader", "performing BAC");
        int timeout = this.f5596p.getTimeout();
        this.f5596p.setTimeout(10000);
        C5112o oVar = new C5112o(C5113p.BAC_CHECK);
        if (this.f5584d.isOpen()) {
            try {
                this.f5584d.doBAC(this.f5583c);
                oVar.mo47205a(C5114q.SUCCESSFUL);
            } catch (CardServiceException e) {
                oVar.mo47206a(C5114q.ERROR, e);
            }
        }
        if (timeout >= 1000) {
            i = timeout;
        }
        this.f5596p.setTimeout(i);
        return oVar;
    }

    /* renamed from: c */
    public List<C5112o> mo47176c() throws IOException {
        m3714j();
        Map dataGroupHashes = this.f5588h.getSODFile().getDataGroupHashes();
        ArrayList arrayList = new ArrayList();
        for (Integer intValue : dataGroupHashes.keySet()) {
            int intValue2 = intValue.intValue();
            C5112o a = m3711a(intValue2);
            a.mo47203a(Integer.valueOf(intValue2));
            arrayList.add(a);
        }
        return arrayList;
    }

    /* renamed from: a */
    public C5112o mo47171a(C5032c cVar, boolean z) {
        C5112o a = cVar.mo46961a(this.f5584d, this.f5594n, z);
        this.f5588h = (LDS) a.mo47210e();
        return new C5112o(C5113p.READ_LDS, a.mo47207b());
    }

    /* renamed from: a */
    public C5112o mo47173a(boolean z) throws CardServiceException {
        return mo47171a(new C4994b(), z);
    }

    /* renamed from: d */
    public C5112o mo47177d() {
        m3714j();
        C5112o oVar = new C5112o(C5113p.PASSIVE_AUTH_DSC_CHECK);
        try {
            SODFile sODFile = this.f5588h.getSODFile();
            if (sODFile == null) {
                return new C5112o(C5113p.PASSIVE_AUTH_DSC_CHECK, C5114q.FAILED);
            }
            X509Certificate docSigningCertificate = sODFile.getDocSigningCertificate();
            docSigningCertificate.checkValidity();
            if (sODFile.checkDocSignature(docSigningCertificate)) {
                oVar.mo47205a(C5114q.SUCCESSFUL);
            } else {
                oVar.mo47205a(C5114q.FAILED);
            }
            return oVar;
        } catch (Exception e) {
            C5100f.m3694a("PassportReader", "#### error in Passive Authentication: " + e);
            oVar.mo47206a(C5114q.ERROR, e);
        }
    }

    /* renamed from: a */
    public C5112o mo47172a(C5102h hVar) {
        C5112o oVar = new C5112o(C5113p.PASSIVE_AUTH_ROOT_CERT_CHECK);
        oVar.mo47205a(C5114q.NOT_AVAILABLE);
        for (X509Certificate x509Certificate : hVar.mo47167b(this.f5582b)) {
            C5112o a = m3712a(x509Certificate);
            if (a.mo47208c()) {
                a.mo47203a("SN=" + x509Certificate.getSerialNumber());
                return a;
            }
            oVar = a;
        }
        return oVar;
    }

    /* renamed from: e */
    public C5112o mo47178e() {
        m3714j();
        try {
            return new C5112o(C5113p.ACTIVE_AUTH_CHECK, this.f5588h.getSODFile().getDataGroupHashes().keySet().contains(Integer.valueOf(15)) ? C5114q.SUCCESSFUL : C5114q.NOT_AVAILABLE);
        } catch (IOException e) {
            e.printStackTrace();
            return new C5112o(C5113p.ACTIVE_AUTH_CHECK, C5114q.FAILED);
        }
    }

    /* renamed from: f */
    public C5112o mo47179f() {
        m3714j();
        C5100f.m3694a("PassportReader", "downloading image");
        C5112o oVar = new C5112o(C5113p.FACE_IMAGE);
        try {
            for (FaceInfo faceImageInfos : this.f5588h.getDG2File().getFaceInfos()) {
                for (FaceImageInfo faceImageInfo : faceImageInfos.getFaceImageInfos()) {
                    int imageLength = faceImageInfo.getImageLength();
                    String mimeType = faceImageInfo.getMimeType();
                    InputStream imageInputStream = faceImageInfo.getImageInputStream();
                    C5100f.m3694a("PassportReader", String.format("Image mime: %s, len = %d", new Object[]{mimeType, Integer.valueOf(imageLength)}));
                    if (mimeType.equals("image/jpeg") || mimeType.equals("image/png")) {
                        Bitmap decodeStream = BitmapFactory.decodeStream(imageInputStream);
                        C5100f.m3694a("PassportReader", "Bitmap decoding " + (decodeStream != null ? "succssful" : "failed"));
                        oVar.mo47205a(decodeStream != null ? C5114q.SUCCESSFUL : C5114q.FAILED);
                        oVar.mo47203a(decodeStream);
                    } else if (mimeType.equals(ImageInfo.JPEG2000_MIME_TYPE) || mimeType.equals("mime/jpx")) {
                        byte[] byteArray = IOUtils.toByteArray(imageInputStream);
                        C5079dt dtVar = new C5079dt();
                        String[][] b = C5038cf.m3427b();
                        for (int length = b.length - 1; length >= 0; length--) {
                            if (b[length][3] != null) {
                                dtVar.put(b[length][0], b[length][3]);
                            }
                        }
                        Bitmap a = new C5038cf(new C5079dt(dtVar)).mo47073a(byteArray);
                        oVar.mo47205a(a != null ? C5114q.SUCCESSFUL : C5114q.FAILED);
                        oVar.mo47203a(a);
                    } else {
                        oVar.mo47205a(C5114q.NOT_AVAILABLE);
                        oVar.mo47203a(mimeType);
                        oVar.mo47205a(C5114q.NOT_AVAILABLE);
                        oVar.mo47203a(mimeType);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            oVar.mo47205a(C5114q.FAILED);
        }
        return oVar;
    }

    /* renamed from: g */
    public C5112o mo47180g() {
        C5100f.m3694a("PassportReader", "read passport details");
        C5112o oVar = new C5112o(C5113p.ADDITIONAL_DATA);
        C5111n nVar = new C5111n();
        try {
            DG1File dG1File = this.f5588h.getDG1File();
            if (dG1File != null) {
                nVar.mo47197a(dG1File.getMRZInfo());
            }
            int[] tagList = this.f5588h.getCOMFile().getTagList();
            ArrayList arrayList = new ArrayList();
            for (int lookupDataGroupNumberByTag : tagList) {
                arrayList.add(Integer.valueOf(LDSFileUtil.lookupDataGroupNumberByTag(lookupDataGroupNumberByTag)));
            }
            if (arrayList.contains(Integer.valueOf(11))) {
                C5100f.m3694a("PassportReader", "read DG11 - personal details");
                nVar.mo47196a(new C5110m(this.f5588h.getDG11File()));
            }
            if (arrayList.contains(Integer.valueOf(12))) {
                C5100f.m3694a("PassportReader", "read DG12 - document details");
                nVar.mo47195a(new C5109l(this.f5588h.getDG12File()));
            }
            oVar.mo47205a(C5114q.SUCCESSFUL);
            oVar.mo47203a(nVar);
        } catch (Exception e) {
            oVar.mo47206a(C5114q.ERROR, e);
        }
        return oVar;
    }

    /* renamed from: h */
    public String mo47181h() {
        m3714j();
        try {
            return this.f5588h.getDG1File().getMRZInfo().toString();
        } catch (IOException e) {
            C5100f.m3697b("PassportReader", "error reading DG1", e);
            return null;
        }
    }

    /* renamed from: a */
    private C5112o m3711a(int i) {
        byte[] bArr;
        InputStream inputStream = null;
        m3714j();
        short lookupFIDByTag = LDSFileUtil.lookupFIDByTag(LDSFileUtil.lookupTagByDataGroupNumber(i));
        try {
            SODFile sODFile = this.f5588h.getSODFile();
            byte[] bArr2 = (byte[]) sODFile.getDataGroupHashes().get(Integer.valueOf(i));
            String digestAlgorithm = sODFile.getDigestAlgorithm();
            try {
                MessageDigest a = m3710a(digestAlgorithm);
                if (lookupFIDByTag == 259 || lookupFIDByTag == 260) {
                    return new C5112o(C5113p.PASSIVE_AUTH_HASH_CHECK, C5114q.NOT_AVAILABLE, new Throwable("Skipping DG" + i + " during HT verification because EAC not yet implemented."));
                }
                try {
                    int length = this.f5588h.getLength(lookupFIDByTag);
                    if (length > 0) {
                        bArr = new byte[length];
                        inputStream = this.f5588h.getInputStream(lookupFIDByTag);
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        dataInputStream.readFully(bArr);
                        IOUtils.closeQuietly(dataInputStream);
                    } else {
                        bArr = null;
                    }
                    if (inputStream == null) {
                        String str = "Skipping DG" + i + " during HT verification because file could not be read.";
                        C5100f.m3694a("PassportReader", str);
                        return new C5112o(C5113p.PASSIVE_AUTH_HASH_CHECK, C5114q.NOT_AVAILABLE, new Throwable(str));
                    }
                    try {
                        boolean equals = Arrays.equals(bArr2, a.digest(bArr));
                        C5100f.m3694a("PassportReader", "hash check for DG" + i + ": " + (equals ? " -- MATCH -- " : " -- MISMATCH -- "));
                        return new C5112o(C5113p.PASSIVE_AUTH_HASH_CHECK, equals ? C5114q.SUCCESSFUL : C5114q.FAILED);
                    } catch (Exception e) {
                        Exception exc = e;
                        C5100f.m3694a("PassportReader", "exception computing hash " + exc);
                        return new C5112o(C5113p.PASSIVE_AUTH_HASH_CHECK, C5114q.ERROR, (Throwable) exc);
                    }
                } catch (Exception e2) {
                    Exception exc2 = e2;
                    C5100f.m3694a("PassportReader", "#### error reading DG" + i + " hash: " + exc2.getMessage());
                    return new C5112o(C5113p.PASSIVE_AUTH_HASH_CHECK, C5114q.ERROR, (Throwable) exc2);
                }
            } catch (NoSuchAlgorithmException e3) {
                C5100f.m3694a("PassportReader", "Unsupported algorithm \"" + digestAlgorithm + "\"");
                return new C5112o(C5113p.PASSIVE_AUTH_HASH_CHECK, C5114q.FAILED, (Throwable) e3);
            }
        } catch (Exception e4) {
            return new C5112o(C5113p.PASSIVE_AUTH_HASH_CHECK, C5114q.ERROR, new Throwable("DG" + i + " failed, could not get stored hash" + e4.getMessage()));
        }
    }

    /* renamed from: a */
    private C5112o m3712a(X509Certificate x509Certificate) {
        m3714j();
        C5112o oVar = new C5112o(C5113p.PASSIVE_AUTH_ROOT_CERT_CHECK);
        try {
            this.f5588h.getSODFile().getDocSigningCertificate().verify(x509Certificate.getPublicKey());
            oVar.mo47205a(C5114q.SUCCESSFUL);
        } catch (Exception e) {
            oVar.mo47206a(C5114q.FAILED, e);
        }
        return oVar;
    }

    /* renamed from: a */
    private MessageDigest m3710a(String str) throws NoSuchAlgorithmException {
        if (Security.getAlgorithms("MessageDigest").contains(str)) {
            return MessageDigest.getInstance(str);
        }
        return MessageDigest.getInstance(str, this.f5586f);
    }

    /* renamed from: i */
    private void m3713i() {
        this.f5586f.put("CertificateFactory.CVC", this.f5585e.get("CertificateFactory.CVC"));
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
        Security.insertProviderAt(this.f5586f, 1);
        Security.addProvider(this.f5585e);
    }

    /* renamed from: j */
    private void m3714j() {
        if (!this.f5595o || this.f5588h == null || this.f5594n.isEmpty()) {
            throw new IllegalStateException("call open() and readLDS() before performing any other operations!");
        }
    }

    /* renamed from: a */
    public void mo47174a(IsoDep isoDep) {
        this.f5596p = isoDep;
    }
}
