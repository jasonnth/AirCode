package jumio.p317nv.nfc;

import java.util.HashMap;

/* renamed from: jumio.nv.nfc.r */
/* compiled from: MockConfig */
public class C5115r extends HashMap<C5113p, C5112o> {
    /* renamed from: a */
    public C5112o get(Object obj) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (C5112o) super.get(obj);
    }
}
