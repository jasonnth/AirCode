package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.tlv.TLVOutputStream;

public class DG1File extends DataGroup {
    private static final short MRZ_INFO_TAG = 24351;
    private static final long serialVersionUID = 5091606125728809058L;
    private MRZInfo mrzInfo;

    public DG1File(MRZInfo mRZInfo) {
        super(97);
        this.mrzInfo = mRZInfo;
    }

    public DG1File(InputStream inputStream) throws IOException {
        super(97, inputStream);
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        tLVInputStream.skipToTag(24351);
        this.mrzInfo = new MRZInfo(tLVInputStream, tLVInputStream.readLength());
    }

    public MRZInfo getMRZInfo() {
        return this.mrzInfo;
    }

    public String toString() {
        return "DG1File " + this.mrzInfo.toString().replaceAll("\n", "").trim();
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        return this.mrzInfo.equals(((DG1File) obj).mrzInfo);
    }

    public int hashCode() {
        return (this.mrzInfo.hashCode() * 3) + 57;
    }

    /* access modifiers changed from: protected */
    public void writeContent(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(24351);
        tLVOutputStream.writeValue(this.mrzInfo.getEncoded());
    }
}
