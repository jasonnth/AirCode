package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import org.spongycastle.asn1.eac.CertificateBody;

public final class zzbyc {
    private final ByteBuffer zzcwB;

    public static class zza extends IOException {
        zza(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    private zzbyc(ByteBuffer byteBuffer) {
        this.zzcwB = byteBuffer;
        this.zzcwB.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzbyc(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzL(int i, int i2) {
        return zzro(i) + zzrl(i2);
    }

    private static int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = i;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i3) < 65536) {
                        throw new IllegalArgumentException("Unpaired surrogate at index " + i3);
                    }
                    i3++;
                }
            }
            i3++;
        }
        return i2;
    }

    private static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int length = charSequence.length();
        int i4 = 0;
        int i5 = i + i2;
        while (i4 < length && i4 + i < i5) {
            char charAt = charSequence.charAt(i4);
            if (charAt >= 128) {
                break;
            }
            bArr[i + i4] = (byte) charAt;
            i4++;
        }
        if (i4 == length) {
            return i + length;
        }
        int i6 = i + i4;
        while (i4 < length) {
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 < 128 && i6 < i5) {
                i3 = i6 + 1;
                bArr[i6] = (byte) charAt2;
            } else if (charAt2 < 2048 && i6 <= i5 - 2) {
                int i7 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 6) | 960);
                i3 = i7 + 1;
                bArr[i7] = (byte) ((charAt2 & '?') | 128);
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i6 <= i5 - 3) {
                int i8 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 12) | 480);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i3 = i9 + 1;
                bArr[i9] = (byte) ((charAt2 & '?') | 128);
            } else if (i6 <= i5 - 4) {
                if (i4 + 1 != charSequence.length()) {
                    i4++;
                    char charAt3 = charSequence.charAt(i4);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i10 = i6 + 1;
                        bArr[i6] = (byte) ((codePoint >>> 18) | 240);
                        int i11 = i10 + 1;
                        bArr[i10] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i3 = i12 + 1;
                        bArr[i12] = (byte) ((codePoint & 63) | 128);
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i4 - 1));
            } else {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i6);
            }
            i4++;
            i6 = i3;
        }
        return i6;
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            zzb(charSequence, byteBuffer);
        }
    }

    public static zzbyc zzah(byte[] bArr) {
        return zzc(bArr, 0, bArr.length);
    }

    public static int zzaj(byte[] bArr) {
        return zzrq(bArr.length) + bArr.length;
    }

    public static int zzb(int i, double d) {
        return zzro(i) + 8;
    }

    public static int zzb(int i, zzbyj zzbyj) {
        return (zzro(i) * 2) + zzd(zzbyj);
    }

    private static int zzb(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = i;
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt >= 2048) {
                i3 += zza(charSequence, i2);
                break;
            }
            i2++;
            i3 = ((127 - charAt) >>> 31) + i3;
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i3) + 4294967296L));
    }

    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                byteBuffer.put((byte) charAt);
            } else if (charAt < 2048) {
                byteBuffer.put((byte) ((charAt >>> 6) | 960));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else if (charAt < 55296 || 57343 < charAt) {
                byteBuffer.put((byte) ((charAt >>> 12) | 480));
                byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else {
                if (i + 1 != charSequence.length()) {
                    i++;
                    char charAt2 = charSequence.charAt(i);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int codePoint = Character.toCodePoint(charAt, charAt2);
                        byteBuffer.put((byte) ((codePoint >>> 18) | 240));
                        byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((codePoint & 63) | 128));
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
            }
            i++;
        }
    }

    public static int zzbp(long j) {
        return zzbt(j);
    }

    public static int zzbq(long j) {
        return zzbt(j);
    }

    public static int zzbr(long j) {
        return zzbt(zzbv(j));
    }

    public static int zzbt(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        return (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public static long zzbv(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzc(int i, zzbyj zzbyj) {
        return zzro(i) + zze(zzbyj);
    }

    public static int zzc(int i, byte[] bArr) {
        return zzro(i) + zzaj(bArr);
    }

    public static zzbyc zzc(byte[] bArr, int i, int i2) {
        return new zzbyc(bArr, i, i2);
    }

    public static int zzd(zzbyj zzbyj) {
        return zzbyj.zzafB();
    }

    public static int zze(int i, long j) {
        return zzro(i) + zzbp(j);
    }

    public static int zze(zzbyj zzbyj) {
        int zzafB = zzbyj.zzafB();
        return zzafB + zzrq(zzafB);
    }

    public static int zzf(int i, long j) {
        return zzro(i) + zzbq(j);
    }

    public static int zzh(int i, long j) {
        return zzro(i) + zzbr(j);
    }

    public static int zzh(int i, boolean z) {
        return zzro(i) + 1;
    }

    public static int zzku(String str) {
        int zzb = zzb((CharSequence) str);
        return zzb + zzrq(zzb);
    }

    public static int zzr(int i, String str) {
        return zzro(i) + zzku(str);
    }

    public static int zzrl(int i) {
        if (i >= 0) {
            return zzrq(i);
        }
        return 10;
    }

    public static int zzro(int i) {
        return zzrq(zzbym.zzO(i, 0));
    }

    public static int zzrq(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (-268435456 & i) == 0 ? 4 : 5;
    }

    public void zzJ(int i, int i2) throws IOException {
        zzN(i, 0);
        zzrj(i2);
    }

    public void zzN(int i, int i2) throws IOException {
        zzrp(zzbym.zzO(i, i2));
    }

    public void zza(int i, double d) throws IOException {
        zzN(i, 1);
        zzn(d);
    }

    public void zza(int i, long j) throws IOException {
        zzN(i, 0);
        zzbl(j);
    }

    public void zza(int i, zzbyj zzbyj) throws IOException {
        zzN(i, 2);
        zzc(zzbyj);
    }

    public int zzafn() {
        return this.zzcwB.remaining();
    }

    public void zzafo() {
        if (zzafn() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void zzai(byte[] bArr) throws IOException {
        zzrp(bArr.length);
        zzak(bArr);
    }

    public void zzak(byte[] bArr) throws IOException {
        zzd(bArr, 0, bArr.length);
    }

    public void zzb(int i, long j) throws IOException {
        zzN(i, 0);
        zzbm(j);
    }

    public void zzb(int i, byte[] bArr) throws IOException {
        zzN(i, 2);
        zzai(bArr);
    }

    public void zzb(zzbyj zzbyj) throws IOException {
        zzbyj.zza(this);
    }

    public void zzbl(long j) throws IOException {
        zzbs(j);
    }

    public void zzbm(long j) throws IOException {
        zzbs(j);
    }

    public void zzbo(long j) throws IOException {
        zzbs(zzbv(j));
    }

    public void zzbr(boolean z) throws IOException {
        zzrn(z ? 1 : 0);
    }

    public void zzbs(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzrn((((int) j) & CertificateBody.profileType) | 128);
            j >>>= 7;
        }
        zzrn((int) j);
    }

    public void zzbu(long j) throws IOException {
        if (this.zzcwB.remaining() < 8) {
            throw new zza(this.zzcwB.position(), this.zzcwB.limit());
        }
        this.zzcwB.putLong(j);
    }

    public void zzc(byte b) throws IOException {
        if (!this.zzcwB.hasRemaining()) {
            throw new zza(this.zzcwB.position(), this.zzcwB.limit());
        }
        this.zzcwB.put(b);
    }

    public void zzc(zzbyj zzbyj) throws IOException {
        zzrp(zzbyj.zzafA());
        zzbyj.zza(this);
    }

    public void zzd(int i, long j) throws IOException {
        zzN(i, 0);
        zzbo(j);
    }

    public void zzd(byte[] bArr, int i, int i2) throws IOException {
        if (this.zzcwB.remaining() >= i2) {
            this.zzcwB.put(bArr, i, i2);
            return;
        }
        throw new zza(this.zzcwB.position(), this.zzcwB.limit());
    }

    public void zzg(int i, boolean z) throws IOException {
        zzN(i, 0);
        zzbr(z);
    }

    public void zzkt(String str) throws IOException {
        try {
            int zzrq = zzrq(str.length());
            if (zzrq == zzrq(str.length() * 3)) {
                int position = this.zzcwB.position();
                if (this.zzcwB.remaining() < zzrq) {
                    throw new zza(zzrq + position, this.zzcwB.limit());
                }
                this.zzcwB.position(position + zzrq);
                zza((CharSequence) str, this.zzcwB);
                int position2 = this.zzcwB.position();
                this.zzcwB.position(position);
                zzrp((position2 - position) - zzrq);
                this.zzcwB.position(position2);
                return;
            }
            zzrp(zzb((CharSequence) str));
            zza((CharSequence) str, this.zzcwB);
        } catch (BufferOverflowException e) {
            zza zza2 = new zza(this.zzcwB.position(), this.zzcwB.limit());
            zza2.initCause(e);
            throw zza2;
        }
    }

    public void zzn(double d) throws IOException {
        zzbu(Double.doubleToLongBits(d));
    }

    public void zzq(int i, String str) throws IOException {
        zzN(i, 2);
        zzkt(str);
    }

    public void zzrj(int i) throws IOException {
        if (i >= 0) {
            zzrp(i);
        } else {
            zzbs((long) i);
        }
    }

    public void zzrn(int i) throws IOException {
        zzc((byte) i);
    }

    public void zzrp(int i) throws IOException {
        while ((i & -128) != 0) {
            zzrn((i & CertificateBody.profileType) | 128);
            i >>>= 7;
        }
        zzrn(i);
    }
}
