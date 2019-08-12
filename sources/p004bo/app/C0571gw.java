package p004bo.app;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: bo.app.gw */
public class C0571gw<E> extends AbstractQueue<E> implements C0568gt<E>, Serializable {

    /* renamed from: a */
    transient C0575c<E> f756a;

    /* renamed from: b */
    transient C0575c<E> f757b;

    /* renamed from: c */
    final ReentrantLock f758c;

    /* renamed from: d */
    private transient int f759d;

    /* renamed from: e */
    private final int f760e;

    /* renamed from: f */
    private final Condition f761f;

    /* renamed from: g */
    private final Condition f762g;

    /* renamed from: bo.app.gw$a */
    abstract class C0573a implements Iterator<E> {

        /* renamed from: a */
        C0575c<E> f763a;

        /* renamed from: b */
        E f764b;

        /* renamed from: d */
        private C0575c<E> f766d;

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public abstract C0575c<E> mo7222a();

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public abstract C0575c<E> mo7223a(C0575c<E> cVar);

        C0573a() {
            ReentrantLock reentrantLock = C0571gw.this.f758c;
            reentrantLock.lock();
            try {
                this.f763a = mo7222a();
                this.f764b = this.f763a == null ? null : this.f763a.f768a;
            } finally {
                reentrantLock.unlock();
            }
        }

        /* renamed from: b */
        private C0575c<E> m963b(C0575c<E> cVar) {
            while (true) {
                C0575c<E> a = mo7223a(cVar);
                if (a == null) {
                    return null;
                }
                if (a.f768a != null) {
                    return a;
                }
                if (a == cVar) {
                    return mo7222a();
                }
                cVar = a;
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public void mo7224b() {
            ReentrantLock reentrantLock = C0571gw.this.f758c;
            reentrantLock.lock();
            try {
                this.f763a = m963b(this.f763a);
                this.f764b = this.f763a == null ? null : this.f763a.f768a;
            } finally {
                reentrantLock.unlock();
            }
        }

        public boolean hasNext() {
            return this.f763a != null;
        }

        public E next() {
            if (this.f763a == null) {
                throw new NoSuchElementException();
            }
            this.f766d = this.f763a;
            E e = this.f764b;
            mo7224b();
            return e;
        }

        public void remove() {
            C0575c<E> cVar = this.f766d;
            if (cVar == null) {
                throw new IllegalStateException();
            }
            this.f766d = null;
            ReentrantLock reentrantLock = C0571gw.this.f758c;
            reentrantLock.lock();
            try {
                if (cVar.f768a != null) {
                    C0571gw.this.mo7192a(cVar);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* renamed from: bo.app.gw$b */
    class C0574b extends C0573a {
        private C0574b() {
            super();
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0575c<E> mo7222a() {
            return C0571gw.this.f756a;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0575c<E> mo7223a(C0575c<E> cVar) {
            return cVar.f770c;
        }
    }

    /* renamed from: bo.app.gw$c */
    static final class C0575c<E> {

        /* renamed from: a */
        E f768a;

        /* renamed from: b */
        C0575c<E> f769b;

        /* renamed from: c */
        C0575c<E> f770c;

        C0575c(E e) {
            this.f768a = e;
        }
    }

    public C0571gw() {
        this(Integer.MAX_VALUE);
    }

    public C0571gw(int i) {
        this.f758c = new ReentrantLock();
        this.f761f = this.f758c.newCondition();
        this.f762g = this.f758c.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.f760e = i;
    }

    /* renamed from: b */
    private boolean m946b(C0575c<E> cVar) {
        if (this.f759d >= this.f760e) {
            return false;
        }
        C0575c<E> cVar2 = this.f756a;
        cVar.f770c = cVar2;
        this.f756a = cVar;
        if (this.f757b == null) {
            this.f757b = cVar;
        } else {
            cVar2.f769b = cVar;
        }
        this.f759d++;
        this.f761f.signal();
        return true;
    }

    /* renamed from: c */
    private boolean m947c(C0575c<E> cVar) {
        if (this.f759d >= this.f760e) {
            return false;
        }
        C0575c<E> cVar2 = this.f757b;
        cVar.f769b = cVar2;
        this.f757b = cVar;
        if (this.f756a == null) {
            this.f756a = cVar;
        } else {
            cVar2.f770c = cVar;
        }
        this.f759d++;
        this.f761f.signal();
        return true;
    }

    /* renamed from: f */
    private E m948f() {
        C0575c<E> cVar = this.f756a;
        if (cVar == null) {
            return null;
        }
        C0575c<E> cVar2 = cVar.f770c;
        E e = cVar.f768a;
        cVar.f768a = null;
        cVar.f770c = cVar;
        this.f756a = cVar2;
        if (cVar2 == null) {
            this.f757b = null;
        } else {
            cVar2.f769b = null;
        }
        this.f759d--;
        this.f762g.signal();
        return e;
    }

    /* renamed from: g */
    private E m949g() {
        C0575c<E> cVar = this.f757b;
        if (cVar == null) {
            return null;
        }
        C0575c<E> cVar2 = cVar.f769b;
        E e = cVar.f768a;
        cVar.f768a = null;
        cVar.f769b = cVar;
        this.f757b = cVar2;
        if (cVar2 == null) {
            this.f756a = null;
        } else {
            cVar2.f770c = null;
        }
        this.f759d--;
        this.f762g.signal();
        return e;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7192a(C0575c<E> cVar) {
        C0575c<E> cVar2 = cVar.f769b;
        C0575c<E> cVar3 = cVar.f770c;
        if (cVar2 == null) {
            m948f();
        } else if (cVar3 == null) {
            m949g();
        } else {
            cVar2.f770c = cVar3;
            cVar3.f769b = cVar2;
            cVar.f768a = null;
            this.f759d--;
            this.f762g.signal();
        }
    }

    /* renamed from: a */
    public void mo7193a(E e) {
        if (!mo7199c(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    /* renamed from: b */
    public boolean mo7197b(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0575c cVar = new C0575c(e);
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            return m946b(cVar);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* renamed from: c */
    public boolean mo7199c(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0575c cVar = new C0575c(e);
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            return m947c(cVar);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* renamed from: d */
    public void mo7203d(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0575c cVar = new C0575c(e);
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        while (!m947c(cVar)) {
            try {
                this.f762g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* renamed from: a */
    public boolean mo7194a(E e, long j, TimeUnit timeUnit) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0575c cVar = new C0575c(e);
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lockInterruptibly();
        while (!m947c(cVar)) {
            try {
                if (nanos <= 0) {
                    return false;
                }
                nanos = this.f762g.awaitNanos(nanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    /* renamed from: a */
    public E mo7190a() {
        E b = mo7196b();
        if (b != null) {
            return b;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: b */
    public E mo7196b() {
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            return m948f();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* renamed from: c */
    public E mo7198c() {
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        while (true) {
            try {
                E f = m948f();
                if (f != null) {
                    return f;
                }
                this.f761f.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* renamed from: a */
    public E mo7191a(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lockInterruptibly();
        while (true) {
            try {
                long j2 = nanos;
                E f = m948f();
                if (f != null) {
                    reentrantLock.unlock();
                    return f;
                } else if (j2 <= 0) {
                    return null;
                } else {
                    nanos = this.f761f.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* renamed from: d */
    public E mo7202d() {
        E e = mo7206e();
        if (e != null) {
            return e;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: e */
    public E mo7206e() {
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            return this.f756a == null ? null : this.f756a.f768a;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* renamed from: e */
    public boolean mo7207e(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            for (C0575c<E> cVar = this.f756a; cVar != null; cVar = cVar.f770c) {
                if (obj.equals(cVar.f768a)) {
                    mo7192a(cVar);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        mo7193a(e);
        return true;
    }

    public boolean offer(E e) {
        return mo7199c(e);
    }

    public void put(E e) {
        mo7203d(e);
    }

    public boolean offer(E e, long timeout, TimeUnit unit) {
        return mo7194a(e, timeout, unit);
    }

    public E remove() {
        return mo7190a();
    }

    public E poll() {
        return mo7196b();
    }

    public E take() {
        return mo7198c();
    }

    public E poll(long timeout, TimeUnit unit) {
        return mo7191a(timeout, unit);
    }

    public E element() {
        return mo7202d();
    }

    public E peek() {
        return mo7206e();
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            return this.f760e - this.f759d;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int drainTo(Collection<? super E> c) {
        return drainTo(c, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> c, int maxElements) {
        if (c == null) {
            throw new NullPointerException();
        } else if (c == this) {
            throw new IllegalArgumentException();
        } else {
            ReentrantLock reentrantLock = this.f758c;
            reentrantLock.lock();
            try {
                int min = Math.min(maxElements, this.f759d);
                for (int i = 0; i < min; i++) {
                    c.add(this.f756a.f768a);
                    m948f();
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean remove(Object o) {
        return mo7207e(o);
    }

    public int size() {
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            return this.f759d;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            for (C0575c<E> cVar = this.f756a; cVar != null; cVar = cVar.f770c) {
                if (o.equals(cVar.f768a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.f759d];
            int i = 0;
            C0575c<E> cVar = this.f756a;
            while (cVar != null) {
                int i2 = i + 1;
                objArr[i] = cVar.f768a;
                cVar = cVar.f770c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=T[], code=java.lang.Object[], for r7v0, types: [T[], java.lang.Object[], java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T[] toArray(java.lang.Object[] r7) {
        /*
            r6 = this;
            java.util.concurrent.locks.ReentrantLock r4 = r6.f758c
            r4.lock()
            int r1 = r7.length     // Catch:{ all -> 0x0037 }
            int r2 = r6.f759d     // Catch:{ all -> 0x0037 }
            if (r1 >= r2) goto L_0x001e
            java.lang.Class r1 = r7.getClass()     // Catch:{ all -> 0x0037 }
            java.lang.Class r1 = r1.getComponentType()     // Catch:{ all -> 0x0037 }
            int r2 = r6.f759d     // Catch:{ all -> 0x0037 }
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r2)     // Catch:{ all -> 0x0037 }
            java.lang.Object[] r1 = (java.lang.Object[]) r1     // Catch:{ all -> 0x0037 }
            r0 = r1
            java.lang.Object[] r0 = (java.lang.Object[]) r0     // Catch:{ all -> 0x0037 }
            r7 = r0
        L_0x001e:
            r2 = 0
            bo.app.gw$c<E> r1 = r6.f756a     // Catch:{ all -> 0x0037 }
        L_0x0021:
            if (r1 == 0) goto L_0x002d
            int r3 = r2 + 1
            E r5 = r1.f768a     // Catch:{ all -> 0x0037 }
            r7[r2] = r5     // Catch:{ all -> 0x0037 }
            bo.app.gw$c<E> r1 = r1.f770c     // Catch:{ all -> 0x0037 }
            r2 = r3
            goto L_0x0021
        L_0x002d:
            int r1 = r7.length     // Catch:{ all -> 0x0037 }
            if (r1 <= r2) goto L_0x0033
            r1 = 0
            r7[r2] = r1     // Catch:{ all -> 0x0037 }
        L_0x0033:
            r4.unlock()
            return r7
        L_0x0037:
            r1 = move-exception
            r4.unlock()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0571gw.toArray(java.lang.Object[]):java.lang.Object[]");
    }

    public String toString() {
        String sb;
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            C0575c<E> cVar = this.f756a;
            if (cVar == null) {
                sb = "[]";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append('[');
                while (true) {
                    C0575c<E> cVar2 = cVar;
                    E e = cVar2.f768a;
                    if (e == this) {
                        e = "(this Collection)";
                    }
                    sb2.append(e);
                    cVar = cVar2.f770c;
                    if (cVar == null) {
                        break;
                    }
                    sb2.append(',').append(' ');
                }
                sb = sb2.append(']').toString();
                reentrantLock.unlock();
            }
            return sb;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.f758c;
        reentrantLock.lock();
        try {
            C0575c<E> cVar = this.f756a;
            while (cVar != null) {
                cVar.f768a = null;
                C0575c<E> cVar2 = cVar.f770c;
                cVar.f769b = null;
                cVar.f770c = null;
                cVar = cVar2;
            }
            this.f757b = null;
            this.f756a = null;
            this.f759d = 0;
            this.f762g.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> iterator() {
        return new C0574b();
    }
}
