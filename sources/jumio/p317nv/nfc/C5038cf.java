package jumio.p317nv.nfc;

import com.facebook.common.util.UriUtil;
import com.facebook.react.uimanager.ViewProps;
import java.lang.reflect.Array;
import java.util.Vector;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* renamed from: jumio.nv.nfc.cf */
/* compiled from: Decoder */
public class C5038cf {

    /* renamed from: e */
    private static final char[] f5395e = {'B', 'C', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'Q', 'M', 'H', 'I'};

    /* renamed from: f */
    private static final String[][] f5396f = {new String[]{UriUtil.LOCAL_RESOURCE_SCHEME, "<resolution level index>", "", null}, new String[]{"rate", "<decoding rate in bpp>", "", "-1"}, new String[]{"nbytes", "<decoding rate in bytes>", "", "-1"}, new String[]{"parsing", null, "", ViewProps.f3131ON}, new String[]{"ncb_quit", "<max number of code blocks>", "", "-1"}, new String[]{"l_quit", "<max number of layers>", "", "-1"}, new String[]{"m_quit", "<max number of bit planes>", "", "-1"}, new String[]{"poc_quit", null, "", "off"}, new String[]{"comp_transf", null, "", ViewProps.f3131ON}, new String[]{"nocolorspace", null, "", "off"}};

    /* renamed from: a */
    private C5140y f5397a = null;

    /* renamed from: b */
    private int f5398b;

    /* renamed from: c */
    private C5079dt f5399c;

    /* renamed from: d */
    private C5018bw f5400d;

    public C5038cf(C5079dt dtVar) {
        this.f5399c = dtVar;
    }

    /* renamed from: a */
    public static String[][] m3426a() {
        return f5396f;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0362, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0363, code lost:
        m3424a(r0.getMessage(), 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x036d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0372, code lost:
        if (r0.getMessage() != null) goto L_0x0374;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0374, code lost:
        m3424a(r0.getMessage(), 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x037e, code lost:
        m3424a("An error has occured during decoding.", 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0385, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x038a, code lost:
        if (r0.getMessage() != null) goto L_0x038c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x038c, code lost:
        m3424a("An uncaught runtime exception has occurred:\n" + r0.getMessage(), 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x03aa, code lost:
        m3424a("An uncaught runtime exception has occurred.", 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x03b2, code lost:
        m3424a("An uncaught exception has occurred.", 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0060, code lost:
        m3424a(r0.getMessage(), 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r1 = new java.lang.StringBuilder().append("Error while reading bit stream header or parsing packets");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0104, code lost:
        if (r0.getMessage() != null) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0106, code lost:
        r0 = ":\n" + r0.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x011e, code lost:
        m3424a(r1.append(r0).toString(), 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x012d, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0131, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0132, code lost:
        r1 = new java.lang.StringBuilder().append("Cannot instantiate bit stream reader");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0142, code lost:
        if (r0.getMessage() != null) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0144, code lost:
        r0 = ":\n" + r0.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x015c, code lost:
        m3424a(r1.append(r0).toString(), 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x016b, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x016f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0170, code lost:
        r1 = new java.lang.StringBuilder().append("Cannot instantiate entropy decoder");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0180, code lost:
        if (r0.getMessage() != null) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0182, code lost:
        r0 = ":\n" + r0.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x019a, code lost:
        m3424a(r1.append(r0).toString(), 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01a9, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01ad, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01ae, code lost:
        r1 = new java.lang.StringBuilder().append("Cannot instantiate roi de-scaler.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01be, code lost:
        if (r0.getMessage() != null) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01c0, code lost:
        r0 = ":\n" + r0.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01d8, code lost:
        m3424a(r1.append(r0).toString(), 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01e7, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01eb, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01ec, code lost:
        r1 = new java.lang.StringBuilder().append("Cannot instantiate dequantizer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01fc, code lost:
        if (r0.getMessage() != null) goto L_0x01fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01fe, code lost:
        r0 = ":\n" + r0.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0216, code lost:
        m3424a(r1.append(r0).toString(), 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0225, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0229, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x022a, code lost:
        r1 = new java.lang.StringBuilder().append("Cannot instantiate inverse wavelet transform");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x023a, code lost:
        if (r0.getMessage() != null) goto L_0x023c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x023c, code lost:
        r0 = ":\n" + r0.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0254, code lost:
        m3424a(r1.append(r0).toString(), 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0263, code lost:
        r0 = "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x036d A[ExcHandler: Error (r0v9 'e' java.lang.Error A[CUSTOM_DECLARE]), Splitter:B:3:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0385 A[ExcHandler: RuntimeException (r0v3 'e' java.lang.RuntimeException A[CUSTOM_DECLARE]), Splitter:B:3:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x03b1 A[ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:3:0x0007] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap mo47073a(byte[] r11) {
        /*
            r10 = this;
            r2 = 0
            r9 = 2
            r6 = 0
            if (r11 != 0) goto L_0x0007
            r0 = r6
        L_0x0006:
            return r0
        L_0x0007:
            jumio.nv.nfc.dt r0 = r10.f5399c     // Catch:{ IllegalArgumentException -> 0x005f, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            char[] r1 = f5395e     // Catch:{ IllegalArgumentException -> 0x005f, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.dt r3 = r10.f5399c     // Catch:{ IllegalArgumentException -> 0x005f, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String[][] r3 = f5396f     // Catch:{ IllegalArgumentException -> 0x005f, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String[] r3 = jumio.p317nv.nfc.C5079dt.m3577a(r3)     // Catch:{ IllegalArgumentException -> 0x005f, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0.mo47134a(r1, r3)     // Catch:{ IllegalArgumentException -> 0x005f, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>(r11)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.dr r0 = new jumio.nv.nfc.dr     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.cr r7 = new jumio.nv.nfc.cr     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r7.<init>(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r7.mo47092a()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            boolean r1 = r7.f5471a     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r1 == 0) goto L_0x0033
            int r1 = r7.mo47098c()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0.mo47121a(r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x0033:
            jumio.nv.nfc.bw r1 = new jumio.nv.nfc.bw     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r10.f5400d = r1     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.cb r1 = new jumio.nv.nfc.cb     // Catch:{ EOFException -> 0x006a }
            jumio.nv.nfc.dt r3 = r10.f5399c     // Catch:{ EOFException -> 0x006a }
            jumio.nv.nfc.bw r4 = r10.f5400d     // Catch:{ EOFException -> 0x006a }
            r1.<init>(r0, r3, r4)     // Catch:{ EOFException -> 0x006a }
            int r4 = r1.mo47053i()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.bw r3 = r10.f5400d     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.bw$i r3 = r3.f5156a     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r3.mo46997c()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.cg r3 = r1.mo47057m()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            int[] r8 = new int[r4]     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x0054:
            if (r2 >= r4) goto L_0x0074
            int r5 = r1.mo47030a(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r8[r2] = r5     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            int r2 = r2 + 1
            goto L_0x0054
        L_0x005f:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1 = 2
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x006a:
            r0 = move-exception
            java.lang.String r0 = "Codestream too short or bad header, unable to decode."
            r1 = 2
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x0074:
            jumio.nv.nfc.dt r2 = r10.f5399c     // Catch:{ IOException -> 0x00f3, IllegalArgumentException -> 0x0131, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r4 = 0
            jumio.nv.nfc.bw r5 = r10.f5400d     // Catch:{ IOException -> 0x00f3, IllegalArgumentException -> 0x0131, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.by r2 = jumio.p317nv.nfc.C5030by.m3322a(r0, r1, r2, r3, r4, r5)     // Catch:{ IOException -> 0x00f3, IllegalArgumentException -> 0x0131, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.dt r4 = r10.f5399c     // Catch:{ IllegalArgumentException -> 0x016f, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.co r4 = r1.mo47031a(r2, r4)     // Catch:{ IllegalArgumentException -> 0x016f, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.dt r5 = r10.f5399c     // Catch:{ IllegalArgumentException -> 0x01ad, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.dp r4 = r1.mo47035a(r4, r5, r3)     // Catch:{ IllegalArgumentException -> 0x01ad, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.dk r4 = r1.mo47034a(r4, r8, r3)     // Catch:{ IllegalArgumentException -> 0x01eb, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.ed r4 = jumio.p317nv.nfc.C5090ed.m3627a(r4, r3)     // Catch:{ IllegalArgumentException -> 0x0229, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            int r2 = r2.mo47021g()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r4.mo47152i(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.da r5 = new jumio.nv.nfc.da     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2 = 0
            r5.<init>(r4, r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.db r2 = new jumio.nv.nfc.db     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.dt r4 = r10.f5399c     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2.<init>(r5, r3, r8, r4)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            boolean r3 = r7.f5471a     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r3 == 0) goto L_0x02e3
            jumio.nv.nfc.dt r3 = r10.f5399c     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r4 = "nocolorspace"
            java.lang.String r3 = r3.mo47131a(r4)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r4 = "off"
            boolean r3 = r3.equals(r4)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r3 == 0) goto L_0x02e3
            jumio.nv.nfc.y r3 = new jumio.nv.nfc.y     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.dt r4 = r10.f5399c     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r3.<init>(r0, r1, r4)     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r10.f5397a = r3     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.y r0 = r10.f5397a     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.cs r0 = r1.mo47041b(r2, r0)     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.y r3 = r10.f5397a     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.cs r0 = r1.mo47047d(r0, r3)     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.y r3 = r10.f5397a     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.cs r0 = r1.mo47044c(r0, r3)     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.y r3 = r10.f5397a     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.cs r0 = r1.mo47032a(r0, r3)     // Catch:{ IllegalArgumentException -> 0x0267, z -> 0x02a5, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x00dc:
            if (r0 != 0) goto L_0x00df
            r0 = r2
        L_0x00df:
            r0.mo46939b()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            jumio.nv.nfc.dd r1 = new jumio.nv.nfc.dd     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2 = 0
            r3 = 1
            r4 = 2
            r1.<init>(r0, r2, r3, r4)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.mo47115c()     // Catch:{ IOException -> 0x02e6 }
            android.graphics.Bitmap r0 = r1.mo47113a()     // Catch:{ IOException -> 0x0324 }
            goto L_0x0006
        L_0x00f3:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "Error while reading bit stream header or parsing packets"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r2 == 0) goto L_0x012d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ":\n"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x011e:
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1 = 4
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x012d:
            java.lang.String r0 = ""
            goto L_0x011e
        L_0x0131:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "Cannot instantiate bit stream reader"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r2 == 0) goto L_0x016b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ":\n"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x015c:
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1 = 2
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x016b:
            java.lang.String r0 = ""
            goto L_0x015c
        L_0x016f:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "Cannot instantiate entropy decoder"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r2 == 0) goto L_0x01a9
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ":\n"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x019a:
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1 = 2
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x01a9:
            java.lang.String r0 = ""
            goto L_0x019a
        L_0x01ad:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "Cannot instantiate roi de-scaler."
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r2 == 0) goto L_0x01e7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ":\n"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x01d8:
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1 = 2
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x01e7:
            java.lang.String r0 = ""
            goto L_0x01d8
        L_0x01eb:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "Cannot instantiate dequantizer"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r2 == 0) goto L_0x0225
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ":\n"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x0216:
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1 = 2
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x0225:
            java.lang.String r0 = ""
            goto L_0x0216
        L_0x0229:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "Cannot instantiate inverse wavelet transform"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r2 == 0) goto L_0x0263
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ":\n"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x0254:
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1 = 2
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x0263:
            java.lang.String r0 = ""
            goto L_0x0254
        L_0x0267:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "Could not instantiate ICC profiler"
            java.lang.StringBuilder r2 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r1 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r1 == 0) goto L_0x02a1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ":\n"
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r1 = r1.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x0292:
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r1 = r1.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2 = 1
            r10.m3425a(r1, r2, r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x02a1:
            java.lang.String r1 = ""
            goto L_0x0292
        L_0x02a5:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "error processing jp2 colorspace information"
            java.lang.StringBuilder r2 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r1 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r1 == 0) goto L_0x02df
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ": "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r1 = r1.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x02d0:
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r1 = r1.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2 = 1
            r10.m3425a(r1, r2, r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x02df:
            java.lang.String r1 = "    "
            goto L_0x02d0
        L_0x02e3:
            r0 = r2
            goto L_0x00dc
        L_0x02e6:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "I/O error while writing output file"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r2 == 0) goto L_0x0320
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ":\n"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x0311:
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1 = 2
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x0320:
            java.lang.String r0 = ""
            goto L_0x0311
        L_0x0324:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = "I/O error while closing output file (data may be corrupted"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            if (r2 == 0) goto L_0x035e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r3 = ":\n"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
        L_0x034f:
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r1 = 2
            r10.m3424a(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0362, Error -> 0x036d, RuntimeException -> 0x0385, Throwable -> 0x03b1 }
            r0 = r6
            goto L_0x0006
        L_0x035e:
            java.lang.String r0 = ""
            goto L_0x034f
        L_0x0362:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            r10.m3424a(r0, r9)
            r0 = r6
            goto L_0x0006
        L_0x036d:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()
            if (r1 == 0) goto L_0x037e
            java.lang.String r0 = r0.getMessage()
            r10.m3424a(r0, r9)
        L_0x037b:
            r0 = r6
            goto L_0x0006
        L_0x037e:
            java.lang.String r0 = "An error has occured during decoding."
            r10.m3424a(r0, r9)
            goto L_0x037b
        L_0x0385:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()
            if (r1 == 0) goto L_0x03aa
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "An uncaught runtime exception has occurred:\n"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            r10.m3424a(r0, r9)
        L_0x03a7:
            r0 = r6
            goto L_0x0006
        L_0x03aa:
            java.lang.String r0 = "An uncaught runtime exception has occurred."
            r10.m3424a(r0, r9)
            goto L_0x03a7
        L_0x03b1:
            r0 = move-exception
            java.lang.String r0 = "An uncaught exception has occurred."
            r10.m3424a(r0, r9)
            r0 = r6
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5038cf.mo47073a(byte[]):android.graphics.Bitmap");
    }

    /* renamed from: a */
    private void m3424a(String str, int i) {
        this.f5398b = i;
    }

    /* renamed from: a */
    private void m3425a(String str, int i, Throwable th) {
        this.f5398b = i;
    }

    /* renamed from: b */
    public static String[][] m3427b() {
        Vector vector = new Vector();
        String[][] f = C5030by.m3323f();
        if (f != null) {
            for (int length = f.length - 1; length >= 0; length--) {
                vector.addElement(f[length]);
            }
        }
        String[][] a = C5047co.m3446a();
        if (a != null) {
            for (int length2 = a.length - 1; length2 >= 0; length2--) {
                vector.addElement(a[length2]);
            }
        }
        String[][] a2 = C5075dp.m3559a();
        if (a2 != null) {
            for (int length3 = a2.length - 1; length3 >= 0; length3--) {
                vector.addElement(a2[length3]);
            }
        }
        String[][] a3 = C5070dk.m3552a();
        if (a3 != null) {
            for (int length4 = a3.length - 1; length4 >= 0; length4--) {
                vector.addElement(a3[length4]);
            }
        }
        String[][] a4 = C5061db.m3525a();
        if (a4 != null) {
            for (int length5 = a4.length - 1; length5 >= 0; length5--) {
                vector.addElement(a4[length5]);
            }
        }
        String[][] o = C5034cb.m3377o();
        if (o != null) {
            for (int length6 = o.length - 1; length6 >= 0; length6--) {
                vector.addElement(o[length6]);
            }
        }
        String[][] a5 = C4984aq.m3190a();
        if (a5 != null) {
            for (int length7 = a5.length - 1; length7 >= 0; length7--) {
                vector.addElement(a5[length7]);
            }
        }
        String[][] a6 = m3426a();
        if (a6 != null) {
            for (int length8 = a6.length - 1; length8 >= 0; length8--) {
                vector.addElement(a6[length8]);
            }
        }
        String[][] strArr = (String[][]) Array.newInstance(String.class, new int[]{vector.size(), 4});
        if (strArr != null) {
            for (int length9 = strArr.length - 1; length9 >= 0; length9--) {
                strArr[length9] = (String[]) vector.elementAt(length9);
            }
        }
        return strArr;
    }
}
