package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

public class NTRUEncryptionParameters implements Cloneable {

    /* renamed from: N */
    public int f7140N;
    public int bufferLenBits;
    int bufferLenTrits;

    /* renamed from: c */
    public int f7141c;

    /* renamed from: db */
    public int f7142db;

    /* renamed from: df */
    public int f7143df;
    public int df1;
    public int df2;
    public int df3;

    /* renamed from: dg */
    public int f7144dg;
    public int dm0;

    /* renamed from: dr */
    public int f7145dr;
    public int dr1;
    public int dr2;
    public int dr3;
    public boolean fastFp;
    public Digest hashAlg;
    public boolean hashSeed;
    int llen;
    public int maxMsgLenBytes;
    public int minCallsMask;
    public int minCallsR;
    public byte[] oid;
    public int pkLen;
    public int polyType;

    /* renamed from: q */
    public int f7146q;
    public boolean sparse;

    public NTRUEncryptionParameters(int N, int q, int df, int dm02, int db, int c, int minCallsR2, int minCallsMask2, boolean hashSeed2, byte[] oid2, boolean sparse2, boolean fastFp2, Digest hashAlg2) {
        this.f7140N = N;
        this.f7146q = q;
        this.f7143df = df;
        this.f7142db = db;
        this.dm0 = dm02;
        this.f7141c = c;
        this.minCallsR = minCallsR2;
        this.minCallsMask = minCallsMask2;
        this.hashSeed = hashSeed2;
        this.oid = oid2;
        this.sparse = sparse2;
        this.fastFp = fastFp2;
        this.polyType = 0;
        this.hashAlg = hashAlg2;
        init();
    }

    public NTRUEncryptionParameters(int N, int q, int df12, int df22, int df32, int dm02, int db, int c, int minCallsR2, int minCallsMask2, boolean hashSeed2, byte[] oid2, boolean sparse2, boolean fastFp2, Digest hashAlg2) {
        this.f7140N = N;
        this.f7146q = q;
        this.df1 = df12;
        this.df2 = df22;
        this.df3 = df32;
        this.f7142db = db;
        this.dm0 = dm02;
        this.f7141c = c;
        this.minCallsR = minCallsR2;
        this.minCallsMask = minCallsMask2;
        this.hashSeed = hashSeed2;
        this.oid = oid2;
        this.sparse = sparse2;
        this.fastFp = fastFp2;
        this.polyType = 1;
        this.hashAlg = hashAlg2;
        init();
    }

    private void init() {
        this.f7145dr = this.f7143df;
        this.dr1 = this.df1;
        this.dr2 = this.df2;
        this.dr3 = this.df3;
        this.f7144dg = this.f7140N / 3;
        this.llen = 1;
        this.maxMsgLenBytes = (((((this.f7140N * 3) / 2) / 8) - this.llen) - (this.f7142db / 8)) - 1;
        this.bufferLenBits = (((((this.f7140N * 3) / 2) + 7) / 8) * 8) + 1;
        this.bufferLenTrits = this.f7140N - 1;
        this.pkLen = this.f7142db;
    }

    public NTRUEncryptionParameters(InputStream is) throws IOException {
        DataInputStream dis = new DataInputStream(is);
        this.f7140N = dis.readInt();
        this.f7146q = dis.readInt();
        this.f7143df = dis.readInt();
        this.df1 = dis.readInt();
        this.df2 = dis.readInt();
        this.df3 = dis.readInt();
        this.f7142db = dis.readInt();
        this.dm0 = dis.readInt();
        this.f7141c = dis.readInt();
        this.minCallsR = dis.readInt();
        this.minCallsMask = dis.readInt();
        this.hashSeed = dis.readBoolean();
        this.oid = new byte[3];
        dis.read(this.oid);
        this.sparse = dis.readBoolean();
        this.fastFp = dis.readBoolean();
        this.polyType = dis.read();
        String alg = dis.readUTF();
        if ("SHA-512".equals(alg)) {
            this.hashAlg = new SHA512Digest();
        } else if ("SHA-256".equals(alg)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }

    public NTRUEncryptionParameters clone() {
        if (this.polyType == 0) {
            return new NTRUEncryptionParameters(this.f7140N, this.f7146q, this.f7143df, this.dm0, this.f7142db, this.f7141c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, this.hashAlg);
        }
        return new NTRUEncryptionParameters(this.f7140N, this.f7146q, this.df1, this.df2, this.df3, this.dm0, this.f7142db, this.f7141c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, this.hashAlg);
    }

    public int getMaxMessageLength() {
        return this.maxMsgLenBytes;
    }

    public void writeTo(OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(this.f7140N);
        dos.writeInt(this.f7146q);
        dos.writeInt(this.f7143df);
        dos.writeInt(this.df1);
        dos.writeInt(this.df2);
        dos.writeInt(this.df3);
        dos.writeInt(this.f7142db);
        dos.writeInt(this.dm0);
        dos.writeInt(this.f7141c);
        dos.writeInt(this.minCallsR);
        dos.writeInt(this.minCallsMask);
        dos.writeBoolean(this.hashSeed);
        dos.write(this.oid);
        dos.writeBoolean(this.sparse);
        dos.writeBoolean(this.fastFp);
        dos.write(this.polyType);
        dos.writeUTF(this.hashAlg.getAlgorithmName());
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int hashCode = (((((((((((((((((((((((((((((((((this.f7140N + 31) * 31) + this.bufferLenBits) * 31) + this.bufferLenTrits) * 31) + this.f7141c) * 31) + this.f7142db) * 31) + this.f7143df) * 31) + this.df1) * 31) + this.df2) * 31) + this.df3) * 31) + this.f7144dg) * 31) + this.dm0) * 31) + this.f7145dr) * 31) + this.dr1) * 31) + this.dr2) * 31) + this.dr3) * 31) + (this.fastFp ? 1231 : 1237)) * 31) + (this.hashAlg == null ? 0 : this.hashAlg.getAlgorithmName().hashCode())) * 31;
        if (this.hashSeed) {
            i = 1231;
        } else {
            i = 1237;
        }
        int hashCode2 = (((((((((((((((((hashCode + i) * 31) + this.llen) * 31) + this.maxMsgLenBytes) * 31) + this.minCallsMask) * 31) + this.minCallsR) * 31) + Arrays.hashCode(this.oid)) * 31) + this.pkLen) * 31) + this.polyType) * 31) + this.f7146q) * 31;
        if (!this.sparse) {
            i2 = 1237;
        }
        return hashCode2 + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NTRUEncryptionParameters other = (NTRUEncryptionParameters) obj;
        if (this.f7140N != other.f7140N) {
            return false;
        }
        if (this.bufferLenBits != other.bufferLenBits) {
            return false;
        }
        if (this.bufferLenTrits != other.bufferLenTrits) {
            return false;
        }
        if (this.f7141c != other.f7141c) {
            return false;
        }
        if (this.f7142db != other.f7142db) {
            return false;
        }
        if (this.f7143df != other.f7143df) {
            return false;
        }
        if (this.df1 != other.df1) {
            return false;
        }
        if (this.df2 != other.df2) {
            return false;
        }
        if (this.df3 != other.df3) {
            return false;
        }
        if (this.f7144dg != other.f7144dg) {
            return false;
        }
        if (this.dm0 != other.dm0) {
            return false;
        }
        if (this.f7145dr != other.f7145dr) {
            return false;
        }
        if (this.dr1 != other.dr1) {
            return false;
        }
        if (this.dr2 != other.dr2) {
            return false;
        }
        if (this.dr3 != other.dr3) {
            return false;
        }
        if (this.fastFp != other.fastFp) {
            return false;
        }
        if (this.hashAlg == null) {
            if (other.hashAlg != null) {
                return false;
            }
        } else if (!this.hashAlg.getAlgorithmName().equals(other.hashAlg.getAlgorithmName())) {
            return false;
        }
        if (this.hashSeed != other.hashSeed) {
            return false;
        }
        if (this.llen != other.llen) {
            return false;
        }
        if (this.maxMsgLenBytes != other.maxMsgLenBytes) {
            return false;
        }
        if (this.minCallsMask != other.minCallsMask) {
            return false;
        }
        if (this.minCallsR != other.minCallsR) {
            return false;
        }
        if (!Arrays.equals(this.oid, other.oid)) {
            return false;
        }
        if (this.pkLen != other.pkLen) {
            return false;
        }
        if (this.polyType != other.polyType) {
            return false;
        }
        if (this.f7146q != other.f7146q) {
            return false;
        }
        if (this.sparse != other.sparse) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("EncryptionParameters(N=" + this.f7140N + " q=" + this.f7146q);
        if (this.polyType == 0) {
            output.append(" polyType=SIMPLE df=" + this.f7143df);
        } else {
            output.append(" polyType=PRODUCT df1=" + this.df1 + " df2=" + this.df2 + " df3=" + this.df3);
        }
        output.append(" dm0=" + this.dm0 + " db=" + this.f7142db + " c=" + this.f7141c + " minCallsR=" + this.minCallsR + " minCallsMask=" + this.minCallsMask + " hashSeed=" + this.hashSeed + " hashAlg=" + this.hashAlg + " oid=" + Arrays.toString(this.oid) + " sparse=" + this.sparse + ")");
        return output.toString();
    }
}
