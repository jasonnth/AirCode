package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ch */
/* compiled from: CBlkSizeSpec */
public class C5040ch extends C5013br {

    /* renamed from: a */
    private int f5419a = 0;

    /* renamed from: j */
    private int f5420j = 0;

    public C5040ch(int i, int i2, byte b) {
        super(i, i2, b);
    }

    /* renamed from: a */
    public int mo47074a() {
        return this.f5419a;
    }

    /* renamed from: c */
    public int mo47077c() {
        return this.f5420j;
    }

    /* renamed from: a */
    public int mo47075a(byte b, int i, int i2) {
        Integer[] numArr = null;
        switch (b) {
            case 0:
                numArr = (Integer[]) mo46974b();
                break;
            case 1:
                numArr = (Integer[]) mo46977c(i2);
                break;
            case 2:
                numArr = (Integer[]) mo46979d(i);
                break;
            case 3:
                numArr = (Integer[]) mo46970a(i, i2);
                break;
        }
        return numArr[0].intValue();
    }

    /* renamed from: b */
    public int mo47076b(byte b, int i, int i2) {
        Integer[] numArr = null;
        switch (b) {
            case 0:
                numArr = (Integer[]) mo46974b();
                break;
            case 1:
                numArr = (Integer[]) mo46977c(i2);
                break;
            case 2:
                numArr = (Integer[]) mo46979d(i);
                break;
            case 3:
                numArr = (Integer[]) mo46970a(i, i2);
                break;
        }
        return numArr[1].intValue();
    }

    /* renamed from: a */
    public void mo46973a(Object obj) {
        super.mo46973a(obj);
        m3429a((Integer[]) obj);
    }

    /* renamed from: b */
    public void mo46976b(int i, Object obj) {
        super.mo46976b(i, obj);
        m3429a((Integer[]) obj);
    }

    /* renamed from: a */
    public void mo46972a(int i, Object obj) {
        super.mo46972a(i, obj);
        m3429a((Integer[]) obj);
    }

    /* renamed from: a */
    public void mo46971a(int i, int i2, Object obj) {
        super.mo46971a(i, i2, obj);
        m3429a((Integer[]) obj);
    }

    /* renamed from: a */
    private void m3429a(Integer[] numArr) {
        if (numArr[0].intValue() > this.f5419a) {
            this.f5419a = numArr[0].intValue();
        }
        if (numArr[1].intValue() > this.f5420j) {
            this.f5420j = numArr[1].intValue();
        }
    }
}
