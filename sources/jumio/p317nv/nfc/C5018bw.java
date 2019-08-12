package jumio.p317nv.nfc;

import java.util.Hashtable;
import org.spongycastle.asn1.eac.CertificateBody;

/* renamed from: jumio.nv.nfc.bw */
/* compiled from: HeaderInfo */
public class C5018bw implements Cloneable {

    /* renamed from: a */
    public C5027i f5156a;

    /* renamed from: b */
    public Hashtable f5157b = new Hashtable();

    /* renamed from: c */
    public Hashtable f5158c = new Hashtable();

    /* renamed from: d */
    public Hashtable f5159d = new Hashtable();

    /* renamed from: e */
    public Hashtable f5160e = new Hashtable();

    /* renamed from: f */
    public Hashtable f5161f = new Hashtable();

    /* renamed from: g */
    public Hashtable f5162g = new Hashtable();

    /* renamed from: h */
    public Hashtable f5163h = new Hashtable();

    /* renamed from: i */
    public C5022d f5164i;

    /* renamed from: j */
    public Hashtable f5165j = new Hashtable();

    /* renamed from: k */
    private int f5166k = 0;

    /* renamed from: jumio.nv.nfc.bw$a */
    /* compiled from: HeaderInfo */
    public class C5019a {

        /* renamed from: a */
        public int f5167a;

        /* renamed from: b */
        public int f5168b;

        /* renamed from: c */
        public int f5169c;

        /* renamed from: d */
        public int f5170d;

        /* renamed from: e */
        public int f5171e;

        /* renamed from: f */
        public int f5172f;

        /* renamed from: g */
        public int f5173g;

        /* renamed from: h */
        public int[] f5174h = new int[1];

        /* renamed from: i */
        public int[] f5175i;

        public C5019a() {
        }
    }

    /* renamed from: jumio.nv.nfc.bw$b */
    /* compiled from: HeaderInfo */
    public class C5020b implements Cloneable {

        /* renamed from: a */
        public int f5177a;

        /* renamed from: b */
        public int f5178b;

        /* renamed from: c */
        public int f5179c;

        /* renamed from: d */
        public int f5180d;

        /* renamed from: e */
        public int f5181e;

        /* renamed from: f */
        public int f5182f;

        /* renamed from: g */
        public int f5183g;

        /* renamed from: h */
        public int f5184h;

        /* renamed from: i */
        public int f5185i;

        /* renamed from: j */
        public int[] f5186j = new int[1];

        /* renamed from: k */
        public int[] f5187k;

        public C5020b() {
        }
    }

    /* renamed from: jumio.nv.nfc.bw$c */
    /* compiled from: HeaderInfo */
    public class C5021c {

        /* renamed from: a */
        public int f5189a;

        /* renamed from: b */
        public int f5190b;

        /* renamed from: c */
        public byte[] f5191c;

        public C5021c() {
        }
    }

    /* renamed from: jumio.nv.nfc.bw$d */
    /* compiled from: HeaderInfo */
    public class C5022d {

        /* renamed from: a */
        public int f5193a;

        /* renamed from: b */
        public int[] f5194b;

        /* renamed from: c */
        public int[] f5195c;

        public C5022d() {
        }
    }

    /* renamed from: jumio.nv.nfc.bw$e */
    /* compiled from: HeaderInfo */
    public class C5023e {

        /* renamed from: a */
        public int f5197a;

        /* renamed from: b */
        public int[] f5198b;

        /* renamed from: c */
        public int[] f5199c;

        /* renamed from: d */
        public int[] f5200d;

        /* renamed from: e */
        public int[] f5201e;

        /* renamed from: f */
        public int[] f5202f;

        /* renamed from: g */
        public int[] f5203g;

        public C5023e() {
        }
    }

    /* renamed from: jumio.nv.nfc.bw$f */
    /* compiled from: HeaderInfo */
    public class C5024f {

        /* renamed from: a */
        public int f5205a;

        /* renamed from: b */
        public int f5206b;

        /* renamed from: c */
        public int f5207c;

        /* renamed from: d */
        public int[][] f5208d;

        /* renamed from: f */
        private int f5210f = -1;

        /* renamed from: g */
        private int f5211g = -1;

        public C5024f() {
        }

        /* renamed from: a */
        public int mo46990a() {
            if (this.f5210f == -1) {
                this.f5210f = this.f5207c & -225;
            }
            return this.f5210f;
        }

        /* renamed from: b */
        public int mo46991b() {
            if (this.f5211g == -1) {
                this.f5211g = (this.f5207c >> 5) & 7;
            }
            return this.f5211g;
        }
    }

    /* renamed from: jumio.nv.nfc.bw$g */
    /* compiled from: HeaderInfo */
    public class C5025g {

        /* renamed from: a */
        public int f5212a;

        /* renamed from: b */
        public int f5213b;

        /* renamed from: c */
        public int[][] f5214c;

        /* renamed from: e */
        private int f5216e = -1;

        /* renamed from: f */
        private int f5217f = -1;

        public C5025g() {
        }

        /* renamed from: a */
        public int mo46992a() {
            if (this.f5216e == -1) {
                this.f5216e = this.f5213b & -225;
            }
            return this.f5216e;
        }

        /* renamed from: b */
        public int mo46993b() {
            if (this.f5217f == -1) {
                this.f5217f = (this.f5213b >> 5) & 7;
            }
            return this.f5217f;
        }
    }

    /* renamed from: jumio.nv.nfc.bw$h */
    /* compiled from: HeaderInfo */
    public class C5026h {

        /* renamed from: a */
        public int f5218a;

        /* renamed from: b */
        public int f5219b;

        /* renamed from: c */
        public int f5220c;

        /* renamed from: d */
        public int f5221d;

        public C5026h() {
        }
    }

    /* renamed from: jumio.nv.nfc.bw$i */
    /* compiled from: HeaderInfo */
    public class C5027i implements Cloneable {

        /* renamed from: a */
        public int f5223a;

        /* renamed from: b */
        public int f5224b;

        /* renamed from: c */
        public int f5225c;

        /* renamed from: d */
        public int f5226d;

        /* renamed from: e */
        public int f5227e;

        /* renamed from: f */
        public int f5228f;

        /* renamed from: g */
        public int f5229g;

        /* renamed from: h */
        public int f5230h;

        /* renamed from: i */
        public int f5231i;

        /* renamed from: j */
        public int f5232j;

        /* renamed from: k */
        public int f5233k;

        /* renamed from: l */
        public int[] f5234l;

        /* renamed from: m */
        public int[] f5235m;

        /* renamed from: n */
        public int[] f5236n;

        /* renamed from: p */
        private int[] f5238p = null;

        /* renamed from: q */
        private int f5239q = -1;

        /* renamed from: r */
        private int[] f5240r = null;

        /* renamed from: s */
        private int f5241s = -1;

        /* renamed from: t */
        private int f5242t = -1;

        /* renamed from: u */
        private boolean[] f5243u = null;

        /* renamed from: v */
        private int[] f5244v = null;

        public C5027i() {
        }

        /* renamed from: a */
        public int mo46994a() {
            if (this.f5238p == null) {
                this.f5238p = new int[this.f5233k];
                for (int i = 0; i < this.f5233k; i++) {
                    this.f5238p[i] = (int) (Math.ceil(((double) this.f5225c) / ((double) this.f5235m[i])) - Math.ceil(((double) this.f5227e) / ((double) this.f5235m[i])));
                }
            }
            if (this.f5239q == -1) {
                for (int i2 = 0; i2 < this.f5233k; i2++) {
                    if (this.f5238p[i2] > this.f5239q) {
                        this.f5239q = this.f5238p[i2];
                    }
                }
            }
            return this.f5239q;
        }

        /* renamed from: b */
        public int mo46996b() {
            if (this.f5240r == null) {
                this.f5240r = new int[this.f5233k];
                for (int i = 0; i < this.f5233k; i++) {
                    this.f5240r[i] = (int) (Math.ceil(((double) this.f5226d) / ((double) this.f5236n[i])) - Math.ceil(((double) this.f5228f) / ((double) this.f5236n[i])));
                }
            }
            if (this.f5241s == -1) {
                for (int i2 = 0; i2 < this.f5233k; i2++) {
                    if (this.f5240r[i2] != this.f5241s) {
                        this.f5241s = this.f5240r[i2];
                    }
                }
            }
            return this.f5241s;
        }

        /* renamed from: c */
        public int mo46997c() {
            if (this.f5242t == -1) {
                this.f5242t = ((((this.f5225c - this.f5231i) + this.f5229g) - 1) / this.f5229g) * ((((this.f5226d - this.f5232j) + this.f5230h) - 1) / this.f5230h);
            }
            return this.f5242t;
        }

        /* renamed from: a */
        public int mo46995a(int i) {
            if (this.f5244v == null) {
                this.f5244v = new int[this.f5233k];
                for (int i2 = 0; i2 < this.f5233k; i2++) {
                    this.f5244v[i2] = (this.f5234l[i2] & CertificateBody.profileType) + 1;
                }
            }
            return this.f5244v[i];
        }
    }

    /* renamed from: jumio.nv.nfc.bw$j */
    /* compiled from: HeaderInfo */
    public class C5028j {

        /* renamed from: a */
        public int f5245a;

        /* renamed from: b */
        public int f5246b;

        /* renamed from: c */
        public int f5247c;

        /* renamed from: d */
        public int f5248d;

        /* renamed from: e */
        public int f5249e;

        public C5028j() {
        }
    }

    /* renamed from: a */
    public C5027i mo46980a() {
        return new C5027i();
    }

    /* renamed from: b */
    public C5028j mo46981b() {
        return new C5028j();
    }

    /* renamed from: c */
    public C5020b mo46982c() {
        return new C5020b();
    }

    /* renamed from: d */
    public C5019a mo46983d() {
        return new C5019a();
    }

    /* renamed from: e */
    public C5026h mo46984e() {
        return new C5026h();
    }

    /* renamed from: f */
    public C5025g mo46985f() {
        return new C5025g();
    }

    /* renamed from: g */
    public C5024f mo46986g() {
        return new C5024f();
    }

    /* renamed from: h */
    public C5023e mo46987h() {
        return new C5023e();
    }

    /* renamed from: i */
    public C5022d mo46988i() {
        return new C5022d();
    }

    /* renamed from: j */
    public C5021c mo46989j() {
        this.f5166k++;
        return new C5021c();
    }
}
