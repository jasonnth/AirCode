package com.google.android.gms.internal;

import com.airbnb.android.identity.AccountVerificationOfflineIdController;
import java.io.IOException;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.PassportService;

public final class zzbyb {
    private final byte[] buffer;
    private int zzcwA = 67108864;
    private int zzcws;
    private int zzcwt;
    private int zzcwu;
    private int zzcwv;
    private int zzcww;
    private int zzcwx = Integer.MAX_VALUE;
    private int zzcwy;
    private int zzcwz = 64;

    private zzbyb(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzcws = i;
        this.zzcwt = i + i2;
        this.zzcwv = i;
    }

    private void zzafj() {
        this.zzcwt += this.zzcwu;
        int i = this.zzcwt;
        if (i > this.zzcwx) {
            this.zzcwu = i - this.zzcwx;
            this.zzcwt -= this.zzcwu;
            return;
        }
        this.zzcwu = 0;
    }

    public static zzbyb zzb(byte[] bArr, int i, int i2) {
        return new zzbyb(bArr, i, i2);
    }

    public static long zzbk(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public int getPosition() {
        return this.zzcwv - this.zzcws;
    }

    public byte[] readBytes() throws IOException {
        int zzaff = zzaff();
        if (zzaff < 0) {
            throw zzbyi.zzafu();
        } else if (zzaff == 0) {
            return zzbym.zzcwW;
        } else {
            if (zzaff > this.zzcwt - this.zzcwv) {
                throw zzbyi.zzaft();
            }
            byte[] bArr = new byte[zzaff];
            System.arraycopy(this.buffer, this.zzcwv, bArr, 0, zzaff);
            this.zzcwv = zzaff + this.zzcwv;
            return bArr;
        }
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(zzafi());
    }

    public String readString() throws IOException {
        int zzaff = zzaff();
        if (zzaff < 0) {
            throw zzbyi.zzafu();
        } else if (zzaff > this.zzcwt - this.zzcwv) {
            throw zzbyi.zzaft();
        } else {
            String str = new String(this.buffer, this.zzcwv, zzaff, zzbyh.UTF_8);
            this.zzcwv = zzaff + this.zzcwv;
            return str;
        }
    }

    public byte[] zzI(int i, int i2) {
        if (i2 == 0) {
            return zzbym.zzcwW;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzcws + i, bArr, 0, i2);
        return bArr;
    }

    public void zza(zzbyj zzbyj) throws IOException {
        int zzaff = zzaff();
        if (this.zzcwy >= this.zzcwz) {
            throw zzbyi.zzafz();
        }
        int zzrf = zzrf(zzaff);
        this.zzcwy++;
        zzbyj.zzb(this);
        zzrc(0);
        this.zzcwy--;
        zzrg(zzrf);
    }

    public int zzaeW() throws IOException {
        if (zzafl()) {
            this.zzcww = 0;
            return 0;
        }
        this.zzcww = zzaff();
        if (this.zzcww != 0) {
            return this.zzcww;
        }
        throw zzbyi.zzafw();
    }

    public void zzaeX() throws IOException {
        int zzaeW;
        do {
            zzaeW = zzaeW();
            if (zzaeW == 0) {
                return;
            }
        } while (zzrd(zzaeW));
    }

    public long zzaeY() throws IOException {
        return zzafg();
    }

    public long zzaeZ() throws IOException {
        return zzafg();
    }

    public int zzafa() throws IOException {
        return zzaff();
    }

    public boolean zzafc() throws IOException {
        return zzaff() != 0;
    }

    public long zzafe() throws IOException {
        return zzbk(zzafg());
    }

    public int zzaff() throws IOException {
        byte zzafm = zzafm();
        if (zzafm >= 0) {
            return zzafm;
        }
        byte b = zzafm & AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY;
        byte zzafm2 = zzafm();
        if (zzafm2 >= 0) {
            return b | (zzafm2 << 7);
        }
        byte b2 = b | ((zzafm2 & AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY) << 7);
        byte zzafm3 = zzafm();
        if (zzafm3 >= 0) {
            return b2 | (zzafm3 << 14);
        }
        byte b3 = b2 | ((zzafm3 & AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY) << 14);
        byte zzafm4 = zzafm();
        if (zzafm4 >= 0) {
            return b3 | (zzafm4 << 21);
        }
        byte b4 = b3 | ((zzafm4 & AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY) << 21);
        byte zzafm5 = zzafm();
        byte b5 = b4 | (zzafm5 << PassportService.SF_CVCA);
        if (zzafm5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (zzafm() >= 0) {
                return b5;
            }
        }
        throw zzbyi.zzafv();
    }

    public long zzafg() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzafm = zzafm();
            j |= ((long) (zzafm & AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY)) << i;
            if ((zzafm & ISOFileInfo.DATA_BYTES1) == 0) {
                return j;
            }
        }
        throw zzbyi.zzafv();
    }

    public int zzafh() throws IOException {
        return (zzafm() & 255) | ((zzafm() & 255) << 8) | ((zzafm() & 255) << 16) | ((zzafm() & 255) << 24);
    }

    public long zzafi() throws IOException {
        byte zzafm = zzafm();
        byte zzafm2 = zzafm();
        return ((((long) zzafm2) & 255) << 8) | (((long) zzafm) & 255) | ((((long) zzafm()) & 255) << 16) | ((((long) zzafm()) & 255) << 24) | ((((long) zzafm()) & 255) << 32) | ((((long) zzafm()) & 255) << 40) | ((((long) zzafm()) & 255) << 48) | ((((long) zzafm()) & 255) << 56);
    }

    public int zzafk() {
        if (this.zzcwx == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzcwx - this.zzcwv;
    }

    public boolean zzafl() {
        return this.zzcwv == this.zzcwt;
    }

    public byte zzafm() throws IOException {
        if (this.zzcwv == this.zzcwt) {
            throw zzbyi.zzaft();
        }
        byte[] bArr = this.buffer;
        int i = this.zzcwv;
        this.zzcwv = i + 1;
        return bArr[i];
    }

    public void zzrc(int i) throws zzbyi {
        if (this.zzcww != i) {
            throw zzbyi.zzafx();
        }
    }

    public boolean zzrd(int i) throws IOException {
        switch (zzbym.zzrw(i)) {
            case 0:
                zzafa();
                return true;
            case 1:
                zzafi();
                return true;
            case 2:
                zzri(zzaff());
                return true;
            case 3:
                zzaeX();
                zzrc(zzbym.zzO(zzbym.zzrx(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                zzafh();
                return true;
            default:
                throw zzbyi.zzafy();
        }
    }

    public int zzrf(int i) throws zzbyi {
        if (i < 0) {
            throw zzbyi.zzafu();
        }
        int i2 = this.zzcwv + i;
        int i3 = this.zzcwx;
        if (i2 > i3) {
            throw zzbyi.zzaft();
        }
        this.zzcwx = i2;
        zzafj();
        return i3;
    }

    public void zzrg(int i) {
        this.zzcwx = i;
        zzafj();
    }

    public void zzrh(int i) {
        if (i > this.zzcwv - this.zzcws) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.zzcwv - this.zzcws));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.zzcwv = this.zzcws + i;
        }
    }

    public void zzri(int i) throws IOException {
        if (i < 0) {
            throw zzbyi.zzafu();
        } else if (this.zzcwv + i > this.zzcwx) {
            zzri(this.zzcwx - this.zzcwv);
            throw zzbyi.zzaft();
        } else if (i <= this.zzcwt - this.zzcwv) {
            this.zzcwv += i;
        } else {
            throw zzbyi.zzaft();
        }
    }
}
