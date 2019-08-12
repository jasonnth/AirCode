package p004bo.app;

import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: bo.app.gn */
public class C0561gn extends FilterInputStream {
    public C0561gn(InputStream inputStream) {
        super(inputStream);
    }

    public long skip(long n) {
        long j = 0;
        while (j < n) {
            long skip = this.in.skip(n - j);
            if (skip == 0) {
                if (read() < 0) {
                    break;
                }
                skip = 1;
            }
            j = skip + j;
        }
        return j;
    }
}
