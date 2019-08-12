package p004bo.app;

import java.io.InputStream;

/* renamed from: bo.app.gl */
public class C0558gl extends InputStream {

    /* renamed from: a */
    private final InputStream f726a;

    /* renamed from: b */
    private final int f727b;

    public C0558gl(InputStream inputStream, int i) {
        this.f726a = inputStream;
        this.f727b = i;
    }

    public int available() {
        return this.f727b;
    }

    public void close() {
        this.f726a.close();
    }

    public void mark(int readLimit) {
        this.f726a.mark(readLimit);
    }

    public int read() {
        return this.f726a.read();
    }

    public int read(byte[] buffer) {
        return this.f726a.read(buffer);
    }

    public int read(byte[] buffer, int byteOffset, int byteCount) {
        return this.f726a.read(buffer, byteOffset, byteCount);
    }

    public void reset() {
        this.f726a.reset();
    }

    public long skip(long byteCount) {
        return this.f726a.skip(byteCount);
    }

    public boolean markSupported() {
        return this.f726a.markSupported();
    }
}
