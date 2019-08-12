package org.jmrtd.lds;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.tlv.TLVOutputStream;
import org.jmrtd.p321io.SplittableInputStream;

public abstract class DataGroup extends AbstractLDSFile {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -4761360877353069639L;
    private int dataGroupLength;
    private int dataGroupTag;

    /* access modifiers changed from: protected */
    public abstract void readContent(InputStream inputStream) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void writeContent(OutputStream outputStream) throws IOException;

    public /* bridge */ /* synthetic */ byte[] getEncoded() {
        return super.getEncoded();
    }

    DataGroup(int i) {
        this.dataGroupTag = i;
    }

    protected DataGroup(int i, InputStream inputStream) throws IOException {
        this.dataGroupTag = i;
        readObject(inputStream);
    }

    /* access modifiers changed from: protected */
    public void readObject(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        if (readTag != this.dataGroupTag) {
            throw new IllegalArgumentException("Was expecting tag " + Integer.toHexString(this.dataGroupTag) + ", found " + Integer.toHexString(readTag));
        }
        this.dataGroupLength = tLVInputStream.readLength();
        readContent(new SplittableInputStream(inputStream, this.dataGroupLength));
    }

    /* access modifiers changed from: protected */
    public void writeObject(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        int tag = getTag();
        if (this.dataGroupTag != tag) {
            this.dataGroupTag = tag;
        }
        tLVOutputStream.writeTag(tag);
        byte[] content = getContent();
        int length = content.length;
        if (this.dataGroupLength != length) {
            this.dataGroupLength = length;
        }
        tLVOutputStream.writeValue(content);
    }

    public String toString() {
        return "DataGroup [" + Integer.toHexString(getTag()) + " (" + getLength() + ")]";
    }

    public int getTag() {
        return this.dataGroupTag;
    }

    private byte[] getContent() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            writeContent(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return null;
        }
    }

    public int getLength() {
        if (this.dataGroupLength <= 0) {
            this.dataGroupLength = getContent().length;
        }
        return this.dataGroupLength;
    }
}
