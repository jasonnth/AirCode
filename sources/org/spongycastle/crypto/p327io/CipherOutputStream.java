package org.spongycastle.crypto.p327io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.modes.AEADBlockCipher;

/* renamed from: org.spongycastle.crypto.io.CipherOutputStream */
public class CipherOutputStream extends FilterOutputStream {
    private AEADBlockCipher aeadBlockCipher;
    private byte[] buf;
    private BufferedBlockCipher bufferedBlockCipher;
    private final byte[] oneByte = new byte[1];
    private StreamCipher streamCipher;

    public CipherOutputStream(OutputStream os, BufferedBlockCipher cipher) {
        super(os);
        this.bufferedBlockCipher = cipher;
    }

    public CipherOutputStream(OutputStream os, StreamCipher cipher) {
        super(os);
        this.streamCipher = cipher;
    }

    public CipherOutputStream(OutputStream os, AEADBlockCipher cipher) {
        super(os);
        this.aeadBlockCipher = cipher;
    }

    public void write(int b) throws IOException {
        this.oneByte[0] = (byte) b;
        if (this.streamCipher != null) {
            this.out.write(this.streamCipher.returnByte((byte) b));
        } else {
            write(this.oneByte, 0, 1);
        }
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        ensureCapacity(len, false);
        if (this.bufferedBlockCipher != null) {
            int outLen = this.bufferedBlockCipher.processBytes(b, off, len, this.buf, 0);
            if (outLen != 0) {
                this.out.write(this.buf, 0, outLen);
            }
        } else if (this.aeadBlockCipher != null) {
            int outLen2 = this.aeadBlockCipher.processBytes(b, off, len, this.buf, 0);
            if (outLen2 != 0) {
                this.out.write(this.buf, 0, outLen2);
            }
        } else {
            this.streamCipher.processBytes(b, off, len, this.buf, 0);
            this.out.write(this.buf, 0, len);
        }
    }

    private void ensureCapacity(int updateSize, boolean finalOutput) {
        int bufLen = updateSize;
        if (finalOutput) {
            if (this.bufferedBlockCipher != null) {
                bufLen = this.bufferedBlockCipher.getOutputSize(updateSize);
            } else if (this.aeadBlockCipher != null) {
                bufLen = this.aeadBlockCipher.getOutputSize(updateSize);
            }
        } else if (this.bufferedBlockCipher != null) {
            bufLen = this.bufferedBlockCipher.getUpdateOutputSize(updateSize);
        } else if (this.aeadBlockCipher != null) {
            bufLen = this.aeadBlockCipher.getUpdateOutputSize(updateSize);
        }
        if (this.buf == null || this.buf.length < bufLen) {
            this.buf = new byte[bufLen];
        }
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void close() throws IOException {
        ensureCapacity(0, true);
        IOException error = null;
        try {
            if (this.bufferedBlockCipher != null) {
                int outLen = this.bufferedBlockCipher.doFinal(this.buf, 0);
                if (outLen != 0) {
                    this.out.write(this.buf, 0, outLen);
                }
            } else if (this.aeadBlockCipher != null) {
                int outLen2 = this.aeadBlockCipher.doFinal(this.buf, 0);
                if (outLen2 != 0) {
                    this.out.write(this.buf, 0, outLen2);
                }
            } else if (this.streamCipher != null) {
                this.streamCipher.reset();
            }
        } catch (InvalidCipherTextException e) {
            error = new InvalidCipherTextIOException("Error finalising cipher data", e);
        } catch (Exception e2) {
            error = new CipherIOException("Error closing stream: ", e2);
        }
        try {
            flush();
            this.out.close();
        } catch (IOException e3) {
            if (error == null) {
                error = e3;
            }
        }
        if (error != null) {
            throw error;
        }
    }
}
