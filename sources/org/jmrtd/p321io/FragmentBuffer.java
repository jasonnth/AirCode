package org.jmrtd.p321io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: org.jmrtd.io.FragmentBuffer */
public class FragmentBuffer implements Serializable {
    private static final long serialVersionUID = -3510872461790499721L;
    private byte[] buffer;
    private Collection<Fragment> fragments;

    /* renamed from: org.jmrtd.io.FragmentBuffer$Fragment */
    public static class Fragment implements Serializable {
        private static final long serialVersionUID = -3795931618553980328L;
        /* access modifiers changed from: private */
        public int length;
        /* access modifiers changed from: private */
        public int offset;

        public int getOffset() {
            return this.offset;
        }

        public int getLength() {
            return this.length;
        }

        private Fragment(int i, int i2) {
            this.offset = i;
            this.length = i2;
        }

        public static Fragment getInstance(int i, int i2) {
            return new Fragment(i, i2);
        }

        public String toString() {
            return "[" + this.offset + " .. " + ((this.offset + this.length) - 1) + " (" + this.length + ")]";
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!obj.getClass().equals(Fragment.class)) {
                return false;
            }
            Fragment fragment = (Fragment) obj;
            if (!(fragment.offset == this.offset && fragment.length == this.length)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (this.offset * 2) + (this.length * 3) + 5;
        }
    }

    public FragmentBuffer() {
        this(1024);
    }

    public FragmentBuffer(int i) {
        this.buffer = new byte[i];
        this.fragments = new HashSet();
    }

    public synchronized void updateFrom(FragmentBuffer fragmentBuffer) {
        for (Fragment fragment : fragmentBuffer.fragments) {
            addFragment(fragment.offset, fragmentBuffer.buffer, fragment.offset, fragment.length);
        }
    }

    public synchronized void addFragment(int i, byte b) {
        addFragment(i, new byte[]{b});
    }

    public synchronized void addFragment(int i, byte[] bArr) {
        addFragment(i, bArr, 0, bArr.length);
    }

    public synchronized void addFragment(int i, byte[] bArr, int i2, int i3) {
        int i4;
        int i5;
        if (i + i3 > this.buffer.length) {
            setLength(Math.max(i + i3, this.buffer.length) * 2);
        }
        System.arraycopy(bArr, i2, this.buffer, i, i3);
        Iterator it = new ArrayList(this.fragments).iterator();
        int i6 = i3;
        int i7 = i;
        while (true) {
            if (!it.hasNext()) {
                this.fragments.add(Fragment.getInstance(i7, i6));
                break;
            }
            Fragment fragment = (Fragment) it.next();
            if (fragment.getOffset() <= i7 && i7 + i6 <= fragment.getOffset() + fragment.getLength()) {
                break;
            }
            if (fragment.getOffset() <= i7 && i7 <= fragment.getOffset() + fragment.getLength()) {
                int offset = (i6 + i7) - fragment.getOffset();
                int offset2 = fragment.getOffset();
                this.fragments.remove(fragment);
                i4 = offset;
                i5 = offset2;
            } else if (i7 > fragment.getOffset() || fragment.getOffset() + fragment.getLength() > i7 + i6) {
                if (i7 <= fragment.getOffset() && fragment.getOffset() <= i7 + i6) {
                    i6 = (fragment.getOffset() + fragment.getLength()) - i7;
                    this.fragments.remove(fragment);
                }
                i4 = i6;
                i5 = i7;
            } else {
                this.fragments.remove(fragment);
                i4 = i6;
                i5 = i7;
            }
            i7 = i5;
            i6 = i4;
        }
    }

    public synchronized int getPosition() {
        int i = 0;
        synchronized (this) {
            for (int i2 = 0; i2 < this.buffer.length; i2++) {
                if (isCoveredByFragment(i2)) {
                    i = i2 + 1;
                }
            }
        }
        return i;
    }

    public synchronized int getBytesBuffered() {
        int i = 0;
        synchronized (this) {
            for (int i2 = 0; i2 < this.buffer.length; i2++) {
                if (isCoveredByFragment(i2)) {
                    i++;
                }
            }
        }
        return i;
    }

    public synchronized boolean isCoveredByFragment(int i) {
        return isCoveredByFragment(i, 1);
    }

    public synchronized boolean isCoveredByFragment(int i, int i2) {
        boolean z;
        Iterator it = this.fragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            Fragment fragment = (Fragment) it.next();
            int offset = fragment.getOffset();
            int length = fragment.getLength() + fragment.getOffset();
            if (offset <= i && i + i2 <= length) {
                z = true;
                break;
            }
        }
        return z;
    }

    public synchronized int getBufferedLength(int i) {
        int i2;
        int i3 = 0;
        synchronized (this) {
            if (i < this.buffer.length) {
                for (Fragment fragment : this.fragments) {
                    int offset = fragment.getOffset();
                    int length = fragment.getLength() + fragment.getOffset();
                    if (offset <= i && i < length) {
                        i2 = length - i;
                        if (i2 > i3) {
                            i3 = i2;
                        }
                    }
                    i2 = i3;
                    i3 = i2;
                }
            }
        }
        return i3;
    }

    public Collection<Fragment> getFragments() {
        return this.fragments;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public int getLength() {
        return this.buffer.length;
    }

    public synchronized Fragment getSmallestUnbufferedFragment(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        Iterator it = this.fragments.iterator();
        i3 = i2;
        i4 = i;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Fragment fragment = (Fragment) it.next();
            if (fragment.getOffset() <= i4 && i4 + i3 <= fragment.getOffset() + fragment.getLength()) {
                i3 = 0;
                break;
            }
            if (fragment.getOffset() <= i4 && i4 < fragment.getOffset() + fragment.getLength()) {
                int offset = fragment.getOffset() + fragment.getLength();
                i5 = (i4 + i3) - offset;
                i6 = offset;
            } else if (i4 > fragment.getOffset() || fragment.getOffset() + fragment.getLength() > i4 + i3) {
                if (i > fragment.getOffset() || fragment.getOffset() >= i4 + i3) {
                    i5 = i3;
                    i6 = i4;
                } else {
                    i5 = fragment.getOffset() - i4;
                    i6 = i4;
                }
            }
            i4 = i6;
            i3 = i5;
        }
        return Fragment.getInstance(i4, i3);
    }

    public synchronized String toString() {
        return "FragmentBuffer [" + this.buffer.length + ", " + this.fragments + "]";
    }

    public synchronized boolean equals(Object obj) {
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            if (obj != null) {
                if (obj == this) {
                    z2 = true;
                } else if (obj.getClass().equals(FragmentBuffer.class)) {
                    FragmentBuffer fragmentBuffer = (FragmentBuffer) obj;
                    if ((fragmentBuffer.buffer != null || this.buffer == null) && ((fragmentBuffer.buffer == null || this.buffer != null) && ((fragmentBuffer.fragments != null || this.fragments == null) && (fragmentBuffer.fragments == null || this.fragments != null)))) {
                        if (!Arrays.equals(fragmentBuffer.buffer, this.buffer) || !fragmentBuffer.fragments.equals(this.fragments)) {
                            z = false;
                        }
                        z2 = z;
                    }
                }
            }
        }
        return z2;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.buffer) * 3) + (this.fragments.hashCode() * 2) + 7;
    }

    private synchronized void setLength(int i) {
        if (i > this.buffer.length) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, 0, bArr, 0, this.buffer.length);
            this.buffer = bArr;
        }
    }
}
