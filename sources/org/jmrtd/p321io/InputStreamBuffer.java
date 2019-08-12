package org.jmrtd.p321io;

import java.io.IOException;
import java.io.InputStream;
import org.jmrtd.p321io.FragmentBuffer.Fragment;

/* renamed from: org.jmrtd.io.InputStreamBuffer */
public class InputStreamBuffer {
    /* access modifiers changed from: private */
    public FragmentBuffer buffer;
    /* access modifiers changed from: private */
    public PositionInputStream carrier;

    /* renamed from: org.jmrtd.io.InputStreamBuffer$SubInputStream */
    public class SubInputStream extends InputStream {
        static final /* synthetic */ boolean $assertionsDisabled = (!InputStreamBuffer.class.desiredAssertionStatus());
        private int markedPosition = -1;
        private int position = 0;
        private Object syncObject;

        public SubInputStream(Object obj) {
            this.syncObject = obj;
        }

        public FragmentBuffer getBuffer() {
            return InputStreamBuffer.this.buffer;
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int read() throws java.io.IOException {
            /*
                r5 = this;
                r0 = -1
                java.lang.Object r2 = r5.syncObject
                monitor-enter(r2)
                int r1 = r5.position     // Catch:{ all -> 0x0038 }
                org.jmrtd.io.InputStreamBuffer r3 = org.jmrtd.p321io.InputStreamBuffer.this     // Catch:{ all -> 0x0038 }
                org.jmrtd.io.FragmentBuffer r3 = r3.buffer     // Catch:{ all -> 0x0038 }
                int r3 = r3.getLength()     // Catch:{ all -> 0x0038 }
                if (r1 < r3) goto L_0x0014
                monitor-exit(r2)     // Catch:{ all -> 0x0038 }
            L_0x0013:
                return r0
            L_0x0014:
                org.jmrtd.io.InputStreamBuffer r1 = org.jmrtd.p321io.InputStreamBuffer.this     // Catch:{ all -> 0x0038 }
                org.jmrtd.io.FragmentBuffer r1 = r1.buffer     // Catch:{ all -> 0x0038 }
                int r3 = r5.position     // Catch:{ all -> 0x0038 }
                boolean r1 = r1.isCoveredByFragment(r3)     // Catch:{ all -> 0x0038 }
                if (r1 == 0) goto L_0x003b
                org.jmrtd.io.InputStreamBuffer r0 = org.jmrtd.p321io.InputStreamBuffer.this     // Catch:{ all -> 0x0038 }
                org.jmrtd.io.FragmentBuffer r0 = r0.buffer     // Catch:{ all -> 0x0038 }
                byte[] r0 = r0.getBuffer()     // Catch:{ all -> 0x0038 }
                int r1 = r5.position     // Catch:{ all -> 0x0038 }
                int r3 = r1 + 1
                r5.position = r3     // Catch:{ all -> 0x0038 }
                byte r0 = r0[r1]     // Catch:{ all -> 0x0038 }
                r0 = r0 & 255(0xff, float:3.57E-43)
                monitor-exit(r2)     // Catch:{ all -> 0x0038 }
                goto L_0x0013
            L_0x0038:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0038 }
                throw r0
            L_0x003b:
                org.jmrtd.io.InputStreamBuffer r1 = org.jmrtd.p321io.InputStreamBuffer.this     // Catch:{ all -> 0x0038 }
                org.jmrtd.io.PositionInputStream r1 = r1.carrier     // Catch:{ all -> 0x0038 }
                boolean r1 = r1.markSupported()     // Catch:{ all -> 0x0038 }
                if (r1 == 0) goto L_0x004a
                r5.syncCarrierPosition()     // Catch:{ all -> 0x0038 }
            L_0x004a:
                org.jmrtd.io.InputStreamBuffer r1 = org.jmrtd.p321io.InputStreamBuffer.this     // Catch:{ IOException -> 0x006b }
                org.jmrtd.io.PositionInputStream r1 = r1.carrier     // Catch:{ IOException -> 0x006b }
                int r1 = r1.read()     // Catch:{ IOException -> 0x006b }
                if (r1 >= 0) goto L_0x0058
                monitor-exit(r2)     // Catch:{ all -> 0x0038 }
                goto L_0x0013
            L_0x0058:
                org.jmrtd.io.InputStreamBuffer r0 = org.jmrtd.p321io.InputStreamBuffer.this     // Catch:{ IOException -> 0x006b }
                org.jmrtd.io.FragmentBuffer r0 = r0.buffer     // Catch:{ IOException -> 0x006b }
                int r3 = r5.position     // Catch:{ IOException -> 0x006b }
                int r4 = r3 + 1
                r5.position = r4     // Catch:{ IOException -> 0x006b }
                byte r4 = (byte) r1     // Catch:{ IOException -> 0x006b }
                r0.addFragment(r3, r4)     // Catch:{ IOException -> 0x006b }
                monitor-exit(r2)     // Catch:{ all -> 0x0038 }
                r0 = r1
                goto L_0x0013
            L_0x006b:
                r0 = move-exception
                throw r0     // Catch:{ all -> 0x0038 }
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jmrtd.p321io.InputStreamBuffer.SubInputStream.read():int");
        }

        public int read(byte[] bArr) throws IOException {
            int read;
            synchronized (this.syncObject) {
                read = read(bArr, 0, bArr.length);
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int min;
            synchronized (this.syncObject) {
                if (bArr == null) {
                    throw new NullPointerException();
                }
                if (i >= 0 && i2 >= 0) {
                    if (i2 <= bArr.length - i) {
                        if (i2 == 0) {
                            min = 0;
                        } else {
                            if (i2 > InputStreamBuffer.this.buffer.getLength() - this.position) {
                                i2 = InputStreamBuffer.this.buffer.getLength() - this.position;
                            }
                            if (InputStreamBuffer.this.carrier.markSupported()) {
                                syncCarrierPosition();
                            }
                            if (this.position >= InputStreamBuffer.this.buffer.getLength()) {
                                min = -1;
                            } else {
                                Fragment smallestUnbufferedFragment = InputStreamBuffer.this.buffer.getSmallestUnbufferedFragment(this.position, i2);
                                if (smallestUnbufferedFragment.getLength() > 0) {
                                    int offset = smallestUnbufferedFragment.getOffset() - this.position;
                                    System.arraycopy(InputStreamBuffer.this.buffer.getBuffer(), this.position, bArr, i, offset);
                                    this.position += offset;
                                    int read = InputStreamBuffer.this.carrier.read(bArr, i + offset, smallestUnbufferedFragment.getLength());
                                    InputStreamBuffer.this.buffer.addFragment(smallestUnbufferedFragment.getOffset(), bArr, i, read);
                                    this.position += read;
                                    min = offset + read;
                                } else {
                                    min = Math.min(i2, InputStreamBuffer.this.buffer.getLength() - this.position);
                                    System.arraycopy(InputStreamBuffer.this.buffer.getBuffer(), this.position, bArr, i, min);
                                    this.position += min;
                                }
                            }
                        }
                    }
                }
                throw new IndexOutOfBoundsException();
            }
            return min;
        }

        public long skip(long j) throws IOException {
            long skip;
            synchronized (this.syncObject) {
                int bufferedLength = InputStreamBuffer.this.buffer.getBufferedLength(this.position);
                if (j <= ((long) bufferedLength)) {
                    this.position = (int) (((long) this.position) + j);
                } else if ($assertionsDisabled || ((long) bufferedLength) < j) {
                    this.position += bufferedLength;
                    if (InputStreamBuffer.this.carrier.markSupported()) {
                        syncCarrierPosition();
                        skip = InputStreamBuffer.this.carrier.skip(j - ((long) bufferedLength));
                        this.position += (int) skip;
                    } else {
                        skip = super.skip(j - ((long) bufferedLength));
                    }
                    j = ((long) bufferedLength) + skip;
                } else {
                    throw new AssertionError();
                }
            }
            return j;
        }

        public int available() throws IOException {
            return InputStreamBuffer.this.buffer.getBufferedLength(this.position);
        }

        public void close() throws IOException {
        }

        public synchronized void mark(int i) {
            this.markedPosition = this.position;
        }

        public synchronized void reset() throws IOException {
            if (this.markedPosition < 0) {
                throw new IOException("Invalid reset, was mark() called?");
            }
            this.position = this.markedPosition;
        }

        public boolean markSupported() {
            return true;
        }

        public int getPosition() {
            return this.position;
        }

        private void syncCarrierPosition() throws IOException {
            if (((long) this.position) != InputStreamBuffer.this.carrier.getPosition()) {
                InputStreamBuffer.this.carrier.reset();
                int i = 0;
                while (i < this.position) {
                    i = (int) (InputStreamBuffer.this.carrier.skip((long) (this.position - i)) + ((long) i));
                }
            }
        }
    }

    public InputStreamBuffer(InputStream inputStream, int i) {
        this.carrier = new PositionInputStream(inputStream);
        this.carrier.mark(i);
        this.buffer = new FragmentBuffer(i);
    }

    public void updateFrom(InputStreamBuffer inputStreamBuffer) {
        this.buffer.updateFrom(inputStreamBuffer.buffer);
    }

    public SubInputStream getInputStream() {
        SubInputStream subInputStream;
        synchronized (this.carrier) {
            subInputStream = new SubInputStream(this.carrier);
        }
        return subInputStream;
    }

    public synchronized int getPosition() {
        return this.buffer.getPosition();
    }

    public synchronized int getBytesBuffered() {
        return this.buffer.getBytesBuffered();
    }

    public int getLength() {
        return this.buffer.getLength();
    }

    public String toString() {
        return "InputStreamBuffer [" + this.buffer + "]";
    }
}
