package p004bo.app;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* renamed from: bo.app.fs */
final class C0519fs implements Closeable {

    /* renamed from: a */
    static final Pattern f514a = Pattern.compile("[a-z0-9_-]{1,64}");
    /* access modifiers changed from: private */

    /* renamed from: r */
    public static final OutputStream f515r = new OutputStream() {
        public void write(int b) {
        }
    };

    /* renamed from: b */
    final ThreadPoolExecutor f516b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final File f517c;

    /* renamed from: d */
    private final File f518d;

    /* renamed from: e */
    private final File f519e;

    /* renamed from: f */
    private final File f520f;

    /* renamed from: g */
    private final int f521g;

    /* renamed from: h */
    private long f522h;

    /* renamed from: i */
    private int f523i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public final int f524j;

    /* renamed from: k */
    private long f525k = 0;

    /* renamed from: l */
    private int f526l = 0;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public Writer f527m;

    /* renamed from: n */
    private final LinkedHashMap<String, C0524b> f528n = new LinkedHashMap<>(0, 0.75f, true);
    /* access modifiers changed from: private */

    /* renamed from: o */
    public int f529o;

    /* renamed from: p */
    private long f530p = 0;

    /* renamed from: q */
    private final Callable<Void> f531q = new Callable<Void>() {
        /* renamed from: a */
        public Void call() {
            synchronized (C0519fs.this) {
                if (C0519fs.this.f527m != null) {
                    C0519fs.this.m721h();
                    C0519fs.this.m722i();
                    if (C0519fs.this.m718f()) {
                        C0519fs.this.m714e();
                        C0519fs.this.f529o = 0;
                    }
                }
            }
            return null;
        }
    };

    /* renamed from: bo.app.fs$a */
    public final class C0522a {
        /* access modifiers changed from: private */

        /* renamed from: b */
        public final C0524b f534b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public final boolean[] f535c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public boolean f536d;

        /* renamed from: e */
        private boolean f537e;

        /* renamed from: bo.app.fs$a$a */
        class C0523a extends FilterOutputStream {
            private C0523a(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int oneByte) {
                try {
                    this.out.write(oneByte);
                } catch (IOException e) {
                    C0522a.this.f536d = true;
                }
            }

            public void write(byte[] buffer, int offset, int length) {
                try {
                    this.out.write(buffer, offset, length);
                } catch (IOException e) {
                    C0522a.this.f536d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    C0522a.this.f536d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    C0522a.this.f536d = true;
                }
            }
        }

        private C0522a(C0524b bVar) {
            this.f534b = bVar;
            this.f535c = bVar.f542d ? null : new boolean[C0519fs.this.f524j];
        }

        /* renamed from: a */
        public OutputStream mo7085a(int i) {
            OutputStream b;
            FileOutputStream fileOutputStream;
            synchronized (C0519fs.this) {
                if (this.f534b.f543e != this) {
                    throw new IllegalStateException();
                }
                if (!this.f534b.f542d) {
                    this.f535c[i] = true;
                }
                File b2 = this.f534b.mo7094b(i);
                try {
                    fileOutputStream = new FileOutputStream(b2);
                } catch (FileNotFoundException e) {
                    C0519fs.this.f517c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b2);
                    } catch (FileNotFoundException e2) {
                        b = C0519fs.f515r;
                    }
                }
                b = new C0523a(fileOutputStream);
            }
            return b;
        }

        /* renamed from: a */
        public void mo7086a() {
            if (this.f536d) {
                C0519fs.this.m703a(this, false);
                C0519fs.this.mo7080c(this.f534b.f540b);
            } else {
                C0519fs.this.m703a(this, true);
            }
            this.f537e = true;
        }

        /* renamed from: b */
        public void mo7087b() {
            C0519fs.this.m703a(this, false);
        }
    }

    /* renamed from: bo.app.fs$b */
    final class C0524b {
        /* access modifiers changed from: private */

        /* renamed from: b */
        public final String f540b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public final long[] f541c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public boolean f542d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public C0522a f543e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public long f544f;

        private C0524b(String str) {
            this.f540b = str;
            this.f541c = new long[C0519fs.this.f524j];
        }

        /* renamed from: a */
        public String mo7093a() {
            StringBuilder sb = new StringBuilder();
            for (long append : this.f541c) {
                sb.append(' ').append(append);
            }
            return sb.toString();
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public void m738a(String[] strArr) {
            if (strArr.length != C0519fs.this.f524j) {
                throw m740b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.f541c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw m740b(strArr);
                }
            }
        }

        /* renamed from: b */
        private IOException m740b(String[] strArr) {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* renamed from: a */
        public File mo7092a(int i) {
            return new File(C0519fs.this.f517c, this.f540b + "" + i);
        }

        /* renamed from: b */
        public File mo7094b(int i) {
            return new File(C0519fs.this.f517c, this.f540b + "" + i + ".tmp");
        }
    }

    /* renamed from: bo.app.fs$c */
    public final class C0525c implements Closeable {

        /* renamed from: b */
        private final String f546b;

        /* renamed from: c */
        private final long f547c;

        /* renamed from: d */
        private File[] f548d;

        /* renamed from: e */
        private final InputStream[] f549e;

        /* renamed from: f */
        private final long[] f550f;

        private C0525c(String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr) {
            this.f546b = str;
            this.f547c = j;
            this.f548d = fileArr;
            this.f549e = inputStreamArr;
            this.f550f = jArr;
        }

        /* renamed from: a */
        public File mo7095a(int i) {
            return this.f548d[i];
        }

        public void close() {
            for (InputStream a : this.f549e) {
                C0529fv.m757a((Closeable) a);
            }
        }
    }

    private C0519fs(File file, int i, int i2, long j, int i3) {
        this.f517c = file;
        this.f521g = i;
        this.f518d = new File(file, "journal");
        this.f519e = new File(file, "journal.tmp");
        this.f520f = new File(file, "journal.bkp");
        this.f524j = i2;
        this.f522h = j;
        this.f523i = i3;
    }

    /* renamed from: a */
    public static C0519fs m701a(File file, int i, int i2, long j, int i3) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    m706a(file2, file3, false);
                }
            }
            C0519fs fsVar = new C0519fs(file, i, i2, j, i3);
            if (fsVar.f518d.exists()) {
                try {
                    fsVar.m709c();
                    fsVar.m711d();
                    fsVar.f527m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fsVar.f518d, true), C0529fv.f564a));
                    return fsVar;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    fsVar.mo7078a();
                }
            }
            file.mkdirs();
            C0519fs fsVar2 = new C0519fs(file, i, i2, j, i3);
            fsVar2.m714e();
            return fsVar2;
        }
    }

    /* renamed from: c */
    private void m709c() {
        int i;
        C0527fu fuVar = new C0527fu(new FileInputStream(this.f518d), C0529fv.f564a);
        try {
            String a = fuVar.mo7097a();
            String a2 = fuVar.mo7097a();
            String a3 = fuVar.mo7097a();
            String a4 = fuVar.mo7097a();
            String a5 = fuVar.mo7097a();
            if (!"libcore.io.DiskLruCache".equals(a) || !"1".equals(a2) || !Integer.toString(this.f521g).equals(a3) || !Integer.toString(this.f524j).equals(a4) || !"".equals(a5)) {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
            i = 0;
            while (true) {
                m712d(fuVar.mo7097a());
                i++;
            }
        } catch (EOFException e) {
            this.f529o = i - this.f528n.size();
            C0529fv.m757a((Closeable) fuVar);
        } catch (Throwable th) {
            C0529fv.m757a((Closeable) fuVar);
            throw th;
        }
    }

    /* renamed from: d */
    private void m712d(String str) {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf != "REMOVE".length() || !str.startsWith("REMOVE")) {
                str2 = substring;
            } else {
                this.f528n.remove(substring);
                return;
            }
        } else {
            str2 = str.substring(i, indexOf2);
        }
        C0524b bVar = (C0524b) this.f528n.get(str2);
        if (bVar == null) {
            bVar = new C0524b(str2);
            this.f528n.put(str2, bVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            bVar.f542d = true;
            bVar.f543e = null;
            bVar.m738a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bVar.f543e = new C0522a(bVar);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    /* renamed from: d */
    private void m711d() {
        m705a(this.f519e);
        Iterator it = this.f528n.values().iterator();
        while (it.hasNext()) {
            C0524b bVar = (C0524b) it.next();
            if (bVar.f543e == null) {
                for (int i = 0; i < this.f524j; i++) {
                    this.f525k += bVar.f541c[i];
                    this.f526l++;
                }
            } else {
                bVar.f543e = null;
                for (int i2 = 0; i2 < this.f524j; i2++) {
                    m705a(bVar.mo7092a(i2));
                    m705a(bVar.mo7094b(i2));
                }
                it.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public synchronized void m714e() {
        if (this.f527m != null) {
            this.f527m.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f519e), C0529fv.f564a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f521g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f524j));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (C0524b bVar : this.f528n.values()) {
                if (bVar.f543e != null) {
                    bufferedWriter.write("DIRTY " + bVar.f540b + 10);
                } else {
                    bufferedWriter.write("CLEAN " + bVar.f540b + bVar.mo7093a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.f518d.exists()) {
                m706a(this.f518d, this.f520f, true);
            }
            m706a(this.f519e, this.f518d, false);
            this.f520f.delete();
            this.f527m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f518d, true), C0529fv.f564a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    /* renamed from: a */
    private static void m705a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    private static void m706a(File file, File file2, boolean z) {
        if (z) {
            m705a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: a */
    public synchronized C0525c mo7077a(String str) {
        C0525c cVar = null;
        synchronized (this) {
            m720g();
            m716e(str);
            C0524b bVar = (C0524b) this.f528n.get(str);
            if (bVar != null) {
                if (bVar.f542d) {
                    File[] fileArr = new File[this.f524j];
                    InputStream[] inputStreamArr = new InputStream[this.f524j];
                    int i = 0;
                    while (i < this.f524j) {
                        try {
                            File a = bVar.mo7092a(i);
                            fileArr[i] = a;
                            inputStreamArr[i] = new FileInputStream(a);
                            i++;
                        } catch (FileNotFoundException e) {
                            int i2 = 0;
                            while (i2 < this.f524j && inputStreamArr[i2] != null) {
                                C0529fv.m757a((Closeable) inputStreamArr[i2]);
                                i2++;
                            }
                        }
                    }
                    this.f529o++;
                    this.f527m.append("READ " + str + 10);
                    if (m718f()) {
                        this.f516b.submit(this.f531q);
                    }
                    cVar = new C0525c(str, bVar.f544f, fileArr, inputStreamArr, bVar.f541c);
                }
            }
        }
        return cVar;
    }

    /* renamed from: b */
    public C0522a mo7079b(String str) {
        return m700a(str, -1);
    }

    /* renamed from: a */
    private synchronized C0522a m700a(String str, long j) {
        C0524b bVar;
        C0522a aVar;
        m720g();
        m716e(str);
        C0524b bVar2 = (C0524b) this.f528n.get(str);
        if (j == -1 || (bVar2 != null && bVar2.f544f == j)) {
            if (bVar2 == null) {
                C0524b bVar3 = new C0524b(str);
                this.f528n.put(str, bVar3);
                bVar = bVar3;
            } else if (bVar2.f543e != null) {
                aVar = null;
            } else {
                bVar = bVar2;
            }
            aVar = new C0522a(bVar);
            bVar.f543e = aVar;
            this.f527m.write("DIRTY " + str + 10);
            this.f527m.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void m703a(C0522a aVar, boolean z) {
        synchronized (this) {
            C0524b a = aVar.f534b;
            if (a.f543e != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.f542d) {
                    int i = 0;
                    while (true) {
                        if (i < this.f524j) {
                            if (!aVar.f535c[i]) {
                                aVar.mo7087b();
                                throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                            } else if (!a.mo7094b(i).exists()) {
                                aVar.mo7087b();
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
            for (int i2 = 0; i2 < this.f524j; i2++) {
                File b = a.mo7094b(i2);
                if (!z) {
                    m705a(b);
                } else if (b.exists()) {
                    File a2 = a.mo7092a(i2);
                    b.renameTo(a2);
                    long j = a.f541c[i2];
                    long length = a2.length();
                    a.f541c[i2] = length;
                    this.f525k = (this.f525k - j) + length;
                    this.f526l++;
                }
            }
            this.f529o++;
            a.f543e = null;
            if (a.f542d || z) {
                a.f542d = true;
                this.f527m.write("CLEAN " + a.f540b + a.mo7093a() + 10);
                if (z) {
                    long j2 = this.f530p;
                    this.f530p = 1 + j2;
                    a.f544f = j2;
                }
            } else {
                this.f528n.remove(a.f540b);
                this.f527m.write("REMOVE " + a.f540b + 10);
            }
            this.f527m.flush();
            if (this.f525k > this.f522h || this.f526l > this.f523i || m718f()) {
                this.f516b.submit(this.f531q);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public boolean m718f() {
        return this.f529o >= 2000 && this.f529o >= this.f528n.size();
    }

    /* renamed from: c */
    public synchronized boolean mo7080c(String str) {
        boolean z;
        int i = 0;
        synchronized (this) {
            m720g();
            m716e(str);
            C0524b bVar = (C0524b) this.f528n.get(str);
            if (bVar == null || bVar.f543e != null) {
                z = false;
            } else {
                while (i < this.f524j) {
                    File a = bVar.mo7092a(i);
                    if (!a.exists() || a.delete()) {
                        this.f525k -= bVar.f541c[i];
                        this.f526l--;
                        bVar.f541c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.f529o++;
                this.f527m.append("REMOVE " + str + 10);
                this.f528n.remove(str);
                if (m718f()) {
                    this.f516b.submit(this.f531q);
                }
                z = true;
            }
        }
        return z;
    }

    /* renamed from: g */
    private void m720g() {
        if (this.f527m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() {
        if (this.f527m != null) {
            Iterator it = new ArrayList(this.f528n.values()).iterator();
            while (it.hasNext()) {
                C0524b bVar = (C0524b) it.next();
                if (bVar.f543e != null) {
                    bVar.f543e.mo7087b();
                }
            }
            m721h();
            m722i();
            this.f527m.close();
            this.f527m = null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public void m721h() {
        while (this.f525k > this.f522h) {
            mo7080c((String) ((Entry) this.f528n.entrySet().iterator().next()).getKey());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m722i() {
        while (this.f526l > this.f523i) {
            mo7080c((String) ((Entry) this.f528n.entrySet().iterator().next()).getKey());
        }
    }

    /* renamed from: a */
    public void mo7078a() {
        close();
        C0529fv.m758a(this.f517c);
    }

    /* renamed from: e */
    private void m716e(String str) {
        if (!f514a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + str + "\"");
        }
    }
}
