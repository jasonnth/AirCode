package p004bo.app;

import java.math.BigDecimal;
import java.math.RoundingMode;

/* renamed from: bo.app.dm */
public final class C0452dm {

    /* renamed from: a */
    private static final BigDecimal f379a = new BigDecimal("100");

    /* renamed from: a */
    public static BigDecimal m512a(BigDecimal bigDecimal) {
        return bigDecimal.setScale(2, RoundingMode.HALF_UP);
    }
}
