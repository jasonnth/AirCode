package jumio.p317nv.nfc;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: jumio.nv.nfc.dr */
/* compiled from: ISRandomAccessIO */
public class C5077dr implements C5065df {

    /* renamed from: a */
    private InputStream f5528a;

    /* renamed from: b */
    private int f5529b;

    /* renamed from: c */
    private int f5530c;

    /* renamed from: d */
    private byte[] f5531d;

    /* renamed from: e */
    private int f5532e;

    /* renamed from: f */
    private int f5533f;

    /* renamed from: g */
    private boolean f5534g;

    public C5077dr(InputStream inputStream, int i, int i2, int i3) {
        if (i < 0 || i2 <= 0 || i3 <= 0 || inputStream == null) {
            throw new IllegalArgumentException();
        }
        this.f5528a = inputStream;
        if (i < Integer.MAX_VALUE) {
            i++;
        }
        this.f5531d = new byte[i];
        this.f5530c = i2;
        if (i3 < Integer.MAX_VALUE) {
            i3++;
        }
        this.f5529b = i3;
        this.f5533f = 0;
        this.f5532e = 0;
        this.f5534g = false;
    }

    public C5077dr(InputStream inputStream) {
        this(inputStream, 262144, 262144, Integer.MAX_VALUE);
    }

    /* renamed from: h */
    private void m3564h() throws IOException {
        int i = this.f5530c;
        if (this.f5531d.length + i > this.f5529b) {
            i = this.f5529b - this.f5531d.length;
        }
        if (i <= 0) {
            throw new IOException("Reached maximum cache size (" + this.f5529b + ")");
        }
        try {
            byte[] bArr = new byte[(this.f5531d.length + this.f5530c)];
            System.arraycopy(this.f5531d, 0, bArr, 0, this.f5532e);
            this.f5531d = bArr;
        } catch (OutOfMemoryError e) {
            throw new IOException("Out of memory to cache input data");
        }
    }

    /* renamed from: i */
    private void m3565i() throws IOException {
        int read;
        if (this.f5534g) {
            throw new IllegalArgumentException("Already reached EOF");
        }
        int available = this.f5528a.available();
        if (available == 0) {
            available = 1;
        }
        while (this.f5532e + available > this.f5531d.length) {
            m3564h();
        }
        do {
            read = this.f5528a.read(this.f5531d, this.f5532e, available);
            if (read > 0) {
                this.f5532e += read;
                available -= read;
            }
            if (available <= 0) {
                break;
            }
        } while (read > 0);
        if (read <= 0) {
            this.f5534g = true;
            this.f5528a.close();
            this.f5528a = null;
        }
    }

    /* renamed from: e */
    public int mo47123e() throws IOException {
        return this.f5533f;
    }

    /* renamed from: a */
    public void mo47121a(int i) throws IOException {
        if (!this.f5534g || i <= this.f5532e) {
            this.f5533f = i;
            return;
        }
        throw new EOFException();
    }

    /* renamed from: f */
    public int mo47124f() throws IOException {
        while (!this.f5534g) {
            m3565i();
        }
        return this.f5532e;
    }

    /* renamed from: g */
    public int mo47125g() throws IOException {
        if (this.f5533f < this.f5532e) {
            byte[] bArr = this.f5531d;
            int i = this.f5533f;
            this.f5533f = i + 1;
            return bArr[i] & 255;
        }
        while (!this.f5534g && this.f5533f >= this.f5532e) {
            m3565i();
        }
        if (this.f5533f == this.f5532e) {
            throw new EOFException();
        } else if (this.f5533f > this.f5532e) {
            throw new IOException("Position beyond EOF");
        } else {
            byte[] bArr2 = this.f5531d;
            int i2 = this.f5533f;
            this.f5533f = i2 + 1;
            return bArr2[i2] & 255;
        }
    }

    /* renamed from: a */
    public void mo47122a(byte[] bArr, int i, int i2) throws IOException {
        if (this.f5533f + i2 <= this.f5532e) {
            System.arraycopy(this.f5531d, this.f5533f, bArr, i, i2);
            this.f5533f += i2;
            return;
        }
        while (!this.f5534g && this.f5533f + i2 > this.f5532e) {
            m3565i();
        }
        if (this.f5533f + i2 > this.f5532e) {
            throw new EOFException();
        }
        System.arraycopy(this.f5531d, this.f5533f, bArr, i, i2);
        this.f5533f += i2;
    }

    /* renamed from: a */
    public short mo47117a() throws IOException {
        if (this.f5533f + 1 >= this.f5532e) {
            return (short) ((mo47125g() << 8) | mo47125g());
        }
        byte[] bArr = this.f5531d;
        int i = this.f5533f;
        this.f5533f = i + 1;
        int i2 = bArr[i] << 8;
        byte[] bArr2 = this.f5531d;
        int i3 = this.f5533f;
        this.f5533f = i3 + 1;
        return (short) (i2 | (bArr2[i3] & 255));
    }

    /* renamed from: b */
    public int mo47118b() throws IOException {
        if (this.f5533f + 1 >= this.f5532e) {
            return (mo47125g() << 8) | mo47125g();
        }
        byte[] bArr = this.f5531d;
        int i = this.f5533f;
        this.f5533f = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.f5531d;
        int i3 = this.f5533f;
        this.f5533f = i3 + 1;
        return i2 | (bArr2[i3] & 255);
    }

    /* renamed from: c */
    public int mo47119c() throws IOException {
        if (this.f5533f + 3 >= this.f5532e) {
            return (mo47125g() << 24) | (mo47125g() << 16) | (mo47125g() << 8) | mo47125g();
        }
        byte[] bArr = this.f5531d;
        int i = this.f5533f;
        this.f5533f = i + 1;
        int i2 = bArr[i] << 24;
        byte[] bArr2 = this.f5531d;
        int i3 = this.f5533f;
        this.f5533f = i3 + 1;
        byte b = i2 | ((bArr2[i3] & 255) << 16);
        byte[] bArr3 = this.f5531d;
        int i4 = this.f5533f;
        this.f5533f = i4 + 1;
        byte b2 = b | ((bArr3[i4] & 255) << 8);
        byte[] bArr4 = this.f5531d;
        int i5 = this.f5533f;
        this.f5533f = i5 + 1;
        return b2 | (bArr4[i5] & 255);
    }

    /* renamed from: d */
    public long mo47120d() throws IOException {
        if (this.f5533f + 7 >= this.f5532e) {
            return (((long) mo47125g()) << 56) | (((long) mo47125g()) << 48) | (((long) mo47125g()) << 40) | (((long) mo47125g()) << 32) | (((long) mo47125g()) << 24) | (((long) mo47125g()) << 16) | (((long) mo47125g()) << 8) | ((long) mo47125g());
        }
        byte[] bArr = this.f5531d;
        int i = this.f5533f;
        this.f5533f = i + 1;
        long j = ((long) bArr[i]) << 56;
        byte[] bArr2 = this.f5531d;
        int i2 = this.f5533f;
        this.f5533f = i2 + 1;
        long j2 = j | (((long) (bArr2[i2] & 255)) << 48);
        byte[] bArr3 = this.f5531d;
        int i3 = this.f5533f;
        this.f5533f = i3 + 1;
        long j3 = j2 | (((long) (bArr3[i3] & 255)) << 40);
        byte[] bArr4 = this.f5531d;
        int i4 = this.f5533f;
        this.f5533f = i4 + 1;
        long j4 = j3 | (((long) (bArr4[i4] & 255)) << 32);
        byte[] bArr5 = this.f5531d;
        int i5 = this.f5533f;
        this.f5533f = i5 + 1;
        long j5 = j4 | (((long) (bArr5[i5] & 255)) << 24);
        byte[] bArr6 = this.f5531d;
        int i6 = this.f5533f;
        this.f5533f = i6 + 1;
        long j6 = j5 | (((long) (bArr6[i6] & 255)) << 16);
        byte[] bArr7 = this.f5531d;
        int i7 = this.f5533f;
        this.f5533f = i7 + 1;
        long j7 = j6 | (((long) (bArr7[i7] & 255)) << 8);
        byte[] bArr8 = this.f5531d;
        int i8 = this.f5533f;
        this.f5533f = i8 + 1;
        return j7 | ((long) (bArr8[i8] & 255));
    }
}
