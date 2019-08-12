package org.jmrtd.lds;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jmrtd.cert.CVCPrincipal;

public class CVCAFile extends AbstractLDSFile {
    public static final byte CAR_TAG = 66;
    public static final int LENGTH = 36;
    private static final long serialVersionUID = -1100904058684365703L;
    private String altCaReference;
    private String caReference;
    private short fid;

    public /* bridge */ /* synthetic */ byte[] getEncoded() {
        return super.getEncoded();
    }

    public CVCAFile(InputStream inputStream) throws IOException {
        this(284, inputStream);
    }

    public CVCAFile(short s, InputStream inputStream) throws IOException {
        this.caReference = null;
        this.altCaReference = null;
        this.fid = s;
        readObject(inputStream);
    }

    public CVCAFile(String str, String str2) {
        this(284, str, str2);
    }

    public CVCAFile(short s, String str, String str2) {
        this.caReference = null;
        this.altCaReference = null;
        if (str == null || str.length() > 16 || (str2 != null && str2.length() > 16)) {
            throw new IllegalArgumentException();
        }
        this.fid = s;
        this.caReference = str;
        this.altCaReference = str2;
    }

    public CVCAFile(short s, String str) {
        this(s, str, null);
    }

    public short getFID() {
        return this.fid;
    }

    /* access modifiers changed from: protected */
    public void readObject(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int read = dataInputStream.read();
        if (read != 66) {
            throw new IllegalArgumentException("Wrong tag, expected " + Integer.toHexString(66) + ", found " + Integer.toHexString(read));
        }
        int read2 = dataInputStream.read();
        if (read2 > 16) {
            throw new IllegalArgumentException("Wrong length.");
        }
        byte[] bArr = new byte[read2];
        dataInputStream.readFully(bArr);
        this.caReference = new String(bArr);
        int read3 = dataInputStream.read();
        if (read3 != 0) {
            if (read3 != 66) {
                throw new IllegalArgumentException("Wrong tag.");
            }
            int read4 = dataInputStream.read();
            if (read4 > 16) {
                throw new IllegalArgumentException("Wrong length.");
            }
            byte[] bArr2 = new byte[read4];
            dataInputStream.readFully(bArr2);
            this.altCaReference = new String(bArr2);
            read3 = dataInputStream.read();
        }
        while (read3 != -1) {
            if (read3 != 0) {
                throw new IllegalArgumentException("Bad file padding.");
            }
            read3 = dataInputStream.read();
        }
    }

    /* access modifiers changed from: protected */
    public void writeObject(OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[36];
        bArr[0] = CAR_TAG;
        bArr[1] = (byte) this.caReference.length();
        System.arraycopy(this.caReference.getBytes(), 0, bArr, 2, bArr[1]);
        if (this.altCaReference != null) {
            int i = bArr[1] + 2;
            bArr[i] = CAR_TAG;
            bArr[i + 1] = (byte) this.altCaReference.length();
            System.arraycopy(this.altCaReference.getBytes(), 0, bArr, i + 2, bArr[i + 1]);
        }
        outputStream.write(bArr);
    }

    public CVCPrincipal getCAReference() {
        if (this.caReference == null) {
            return null;
        }
        return new CVCPrincipal(this.caReference);
    }

    public CVCPrincipal getAltCAReference() {
        if (this.altCaReference == null) {
            return null;
        }
        return new CVCPrincipal(this.altCaReference);
    }

    public String toString() {
        return "CA reference: \"" + this.caReference + "\"" + (this.altCaReference != null ? ", Alternative CA reference: " + this.altCaReference : "");
    }

    public boolean equals(Object obj) {
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        CVCAFile cVCAFile = (CVCAFile) obj;
        if (!this.caReference.equals(cVCAFile.caReference)) {
            return false;
        }
        if ((this.altCaReference != null || cVCAFile.altCaReference != null) && (this.altCaReference == null || !this.altCaReference.equals(cVCAFile.altCaReference))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.altCaReference != null ? this.altCaReference.hashCode() * 13 : 0) + (this.caReference.hashCode() * 11) + 5;
    }

    public int getLength() {
        return 36;
    }
}
