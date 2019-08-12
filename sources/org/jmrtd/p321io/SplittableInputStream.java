package org.jmrtd.p321io;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.jmrtd.p321io.InputStreamBuffer.SubInputStream;

/* renamed from: org.jmrtd.io.SplittableInputStream */
public class SplittableInputStream extends InputStream {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private SubInputStream carrier = this.inputStreamBuffer.getInputStream();
    public InputStreamBuffer inputStreamBuffer;

    public SplittableInputStream(InputStream inputStream, int i) {
        this.inputStreamBuffer = new InputStreamBuffer(inputStream, i);
    }

    public void updateFrom(SplittableInputStream splittableInputStream) {
        this.inputStreamBuffer.updateFrom(splittableInputStream.inputStreamBuffer);
    }

    public InputStream getInputStream(int i) {
        try {
            SubInputStream inputStream = this.inputStreamBuffer.getInputStream();
            long j = 0;
            while (j < ((long) i)) {
                j += inputStream.skip(((long) i) - j);
            }
            return inputStream;
        } catch (IOException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }

    public int getPosition() {
        return this.carrier.getPosition();
    }

    public int read() throws IOException {
        return this.carrier.read();
    }

    public long skip(long j) throws IOException {
        return this.carrier.skip(j);
    }

    public int available() throws IOException {
        return this.carrier.available();
    }

    public void close() throws IOException {
        this.carrier.close();
    }

    public synchronized void mark(int i) {
        this.carrier.mark(i);
    }

    public synchronized void reset() throws IOException {
        this.carrier.reset();
    }

    public boolean markSupported() {
        return this.carrier.markSupported();
    }

    public int getLength() {
        return this.inputStreamBuffer.getLength();
    }

    public int getBytesBuffered() {
        return this.inputStreamBuffer.getBytesBuffered();
    }
}
