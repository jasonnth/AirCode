package p004bo.app;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* renamed from: bo.app.fu */
class C0527fu implements Closeable {

    /* renamed from: a */
    private final InputStream f558a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final Charset f559b;

    /* renamed from: c */
    private byte[] f560c;

    /* renamed from: d */
    private int f561d;

    /* renamed from: e */
    private int f562e;

    public C0527fu(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public C0527fu(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (!charset.equals(C0529fv.f564a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        } else {
            this.f558a = inputStream;
            this.f559b = charset;
            this.f560c = new byte[i];
        }
    }

    public void close() {
        synchronized (this.f558a) {
            if (this.f560c != null) {
                this.f560c = null;
                this.f558a.close();
            }
        }
    }

    /* renamed from: a */
    public String mo7097a() {
        int i;
        String byteArrayOutputStream;
        synchronized (this.f558a) {
            if (this.f560c == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.f561d >= this.f562e) {
                m755b();
            }
            int i2 = this.f561d;
            while (true) {
                if (i2 == this.f562e) {
                    C05281 r1 = new ByteArrayOutputStream((this.f562e - this.f561d) + 80) {
                        public String toString() {
                            try {
                                return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + -1] != 13) ? this.count : this.count - 1, C0527fu.this.f559b.name());
                            } catch (UnsupportedEncodingException e) {
                                throw new AssertionError(e);
                            }
                        }
                    };
                    loop1:
                    while (true) {
                        r1.write(this.f560c, this.f561d, this.f562e - this.f561d);
                        this.f562e = -1;
                        m755b();
                        i = this.f561d;
                        while (true) {
                            if (i != this.f562e) {
                                if (this.f560c[i] == 10) {
                                    break loop1;
                                }
                                i++;
                            }
                        }
                    }
                    if (i != this.f561d) {
                        r1.write(this.f560c, this.f561d, i - this.f561d);
                    }
                    this.f561d = i + 1;
                    byteArrayOutputStream = r1.toString();
                } else if (this.f560c[i2] == 10) {
                    byteArrayOutputStream = new String(this.f560c, this.f561d, ((i2 == this.f561d || this.f560c[i2 + -1] != 13) ? i2 : i2 - 1) - this.f561d, this.f559b.name());
                    this.f561d = i2 + 1;
                } else {
                    i2++;
                }
            }
        }
        return byteArrayOutputStream;
    }

    /* renamed from: b */
    private void m755b() {
        int read = this.f558a.read(this.f560c, 0, this.f560c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f561d = 0;
        this.f562e = read;
    }
}
