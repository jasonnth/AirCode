package net.p318sf.scuba.smartcards;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* renamed from: net.sf.scuba.smartcards.CardFileInputStream */
public class CardFileInputStream extends InputStream {
    private final byte[] buffer;
    private int bufferLength;
    private int fileLength;

    /* renamed from: fs */
    private FileSystemStructured f6303fs;
    private int markedOffset;
    private int offsetBufferInFile;
    private int offsetInBuffer;
    private FileInfo[] path;

    public CardFileInputStream(int i, FileSystemStructured fileSystemStructured) throws CardServiceException {
        this.f6303fs = fileSystemStructured;
        synchronized (fileSystemStructured) {
            FileInfo[] selectedPath = fileSystemStructured.getSelectedPath();
            if (selectedPath == null || selectedPath.length < 1) {
                throw new CardServiceException("No valid file selected, path = " + Arrays.toString(selectedPath));
            }
            this.path = new FileInfo[selectedPath.length];
            System.arraycopy(selectedPath, 0, this.path, 0, selectedPath.length);
            this.fileLength = selectedPath[selectedPath.length - 1].getFileLength();
            this.buffer = new byte[i];
            this.bufferLength = 0;
            this.offsetBufferInFile = 0;
            this.offsetInBuffer = 0;
            this.markedOffset = -1;
        }
    }

    private int fillBufferFromFile(FileInfo[] fileInfoArr, int i, int i2) throws CardServiceException {
        int length;
        synchronized (this.f6303fs) {
            if (i2 > this.buffer.length) {
                throw new IllegalArgumentException("length too big");
            }
            if (!Arrays.equals(this.f6303fs.getSelectedPath(), fileInfoArr)) {
                for (FileInfo fid : fileInfoArr) {
                    this.f6303fs.selectFile(fid.getFID());
                }
            }
            byte[] readBinary = this.f6303fs.readBinary(i, i2);
            System.arraycopy(readBinary, 0, this.buffer, 0, readBinary.length);
            length = readBinary.length;
        }
        return length;
    }

    public synchronized int available() {
        return this.bufferLength - this.offsetInBuffer;
    }

    public int getLength() {
        return this.fileLength;
    }

    public int getPostion() {
        return this.offsetBufferInFile + this.offsetInBuffer;
    }

    public void mark(int i) {
        synchronized (this.f6303fs) {
            this.markedOffset = this.offsetBufferInFile + this.offsetInBuffer;
        }
    }

    public boolean markSupported() {
        synchronized (this.f6303fs) {
        }
        return true;
    }

    public int read() throws IOException {
        byte b;
        synchronized (this.f6303fs) {
            try {
                if (!Arrays.equals(this.path, this.f6303fs.getSelectedPath())) {
                    for (FileInfo fid : this.path) {
                        this.f6303fs.selectFile(fid.getFID());
                    }
                }
                int i = this.offsetBufferInFile + this.offsetInBuffer;
                if (i >= this.fileLength) {
                    b = -1;
                } else {
                    if (this.offsetInBuffer >= this.bufferLength) {
                        int i2 = this.offsetBufferInFile + this.bufferLength;
                        int fillBufferFromFile = fillBufferFromFile(this.path, i2, Math.min(this.buffer.length, this.fileLength - i));
                        this.offsetBufferInFile = i2;
                        this.offsetInBuffer = 0;
                        this.bufferLength = fillBufferFromFile;
                    }
                    b = this.buffer[this.offsetInBuffer] & 255;
                    this.offsetInBuffer++;
                }
            } catch (CardServiceException e) {
                throw new IOException(e.toString());
            } catch (Exception e2) {
                throw new IOException("DEBUG: Unexpected Exception: " + e2.getMessage());
            } catch (CardServiceException e3) {
                e3.printStackTrace();
                throw new IOException(e3.getMessage());
            }
        }
        return b;
    }

    public void reset() throws IOException {
        synchronized (this.f6303fs) {
            if (this.markedOffset < 0) {
                throw new IOException("Mark not set");
            }
            this.offsetBufferInFile = this.markedOffset;
            this.offsetInBuffer = 0;
            this.bufferLength = 0;
        }
    }

    public long skip(long j) {
        synchronized (this.f6303fs) {
            if (j < ((long) (this.bufferLength - this.offsetInBuffer))) {
                this.offsetInBuffer = (int) (((long) this.offsetInBuffer) + j);
            } else {
                this.offsetBufferInFile = (int) (((long) (this.offsetBufferInFile + this.offsetInBuffer)) + j);
                this.offsetInBuffer = 0;
                this.bufferLength = 0;
                int i = this.offsetBufferInFile + this.offsetInBuffer;
            }
        }
        return j;
    }
}
