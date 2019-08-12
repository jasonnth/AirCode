package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bq */
/* compiled from: IntegerSpec */
public class C5012bq extends C5013br {

    /* renamed from: a */
    protected static int f5142a = Integer.MAX_VALUE;

    public C5012bq(int i, int i2, byte b) {
        super(i, i2, b);
    }

    /* renamed from: a */
    public int mo46967a() {
        int intValue = ((Integer) this.f5147f).intValue();
        int i = 0;
        while (i < this.f5144c) {
            int i2 = intValue;
            for (int i3 = 0; i3 < this.f5145d; i3++) {
                int intValue2 = ((Integer) mo46975b(i, i3)).intValue();
                if (i2 > intValue2) {
                    i2 = intValue2;
                }
            }
            i++;
            intValue = i2;
        }
        return intValue;
    }

    /* renamed from: a */
    public int mo46968a(int i) {
        int i2 = 0;
        int i3 = f5142a;
        while (true) {
            int i4 = i2;
            if (i4 >= this.f5144c) {
                return i3;
            }
            int intValue = ((Integer) mo46975b(i4, i)).intValue();
            if (i3 > intValue) {
                i3 = intValue;
            }
            i2 = i4 + 1;
        }
    }

    /* renamed from: b */
    public int mo46969b(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f5145d; i3++) {
            int intValue = ((Integer) mo46975b(i, i3)).intValue();
            if (i2 < intValue) {
                i2 = intValue;
            }
        }
        return i2;
    }
}
