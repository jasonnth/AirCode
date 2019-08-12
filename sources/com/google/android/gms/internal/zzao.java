package com.google.android.gms.internal;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.google.android.gms.internal.zzag.zzc;
import com.google.android.gms.internal.zzag.zzf;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.spongycastle.i18n.LocalizedMessage;
import p005cn.jpush.android.JPushConstants;

final class zzao {
    static boolean zzlF = false;
    /* access modifiers changed from: private */
    public static MessageDigest zzlG = null;
    private static final Object zzlH = new Object();
    private static final Object zzlI = new Object();
    static CountDownLatch zzlJ = new CountDownLatch(1);

    private static final class zza implements Runnable {
        private zza() {
        }

        public void run() {
            try {
                zzao.zzlG = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
            } finally {
                zzao.zzlJ.countDown();
            }
        }
    }

    static void zzO() {
        synchronized (zzlI) {
            if (!zzlF) {
                zzlF = true;
                new Thread(new zza()).start();
            }
        }
    }

    static MessageDigest zzP() {
        zzO();
        boolean z = false;
        try {
            z = zzlJ.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        if (z && zzlG != null) {
            return zzlG;
        }
        return null;
    }

    private static int zza(boolean z) {
        return z ? 239 : 255;
    }

    static String zza(com.google.android.gms.internal.zzag.zza zza2, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return zza(zzbyj.zzf(zza2), str, z);
    }

    static String zza(String str, String str2, boolean z) {
        return zza(str, str2, z, ((Boolean) zzgd.zzDP.get()).booleanValue());
    }

    static String zza(String str, String str2, boolean z, boolean z2) {
        byte[] zzb = zzb(str, str2, z, z2);
        return zzb != null ? zzam.zza(zzb, true) : Integer.toString(7);
    }

    static String zza(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return zzam.zza(z ? zzb(bArr, str) : zza(bArr, str), true);
    }

    static Vector<byte[]> zza(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + i) - 1) / i;
        Vector vector = new Vector();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * i;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > i ? i3 + i : bArr.length));
                i2++;
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return vector;
    }

    static void zza(String str, byte[] bArr) throws UnsupportedEncodingException {
        if (str.length() > 32) {
            str = str.substring(0, 32);
        }
        new zzbxb(str.getBytes(JPushConstants.ENCODING_UTF_8)).zzab(bArr);
    }

    static byte[] zza(byte[] bArr, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Vector zza2 = zza(bArr, 255);
        if (zza2 == null || zza2.size() == 0) {
            return zzb(zzbyj.zzf(zzb(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)), str);
        }
        zzf zzf = new zzf();
        zzf.zzcA = new byte[zza2.size()][];
        Iterator it = zza2.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            zzf.zzcA[i] = zzb((byte[]) it.next(), str, false);
            i = i2;
        }
        zzf.zzcv = zzh(bArr);
        return zzbyj.zzf(zzf);
    }

    static com.google.android.gms.internal.zzag.zza zzb(long j) {
        com.google.android.gms.internal.zzag.zza zza2 = new com.google.android.gms.internal.zzag.zza();
        zza2.zzbt = Long.valueOf(j);
        return zza2;
    }

    static byte[] zzb(String str, String str2, boolean z, boolean z2) {
        zzc zzc = new zzc();
        try {
            zzc.zzct = str.length() < 3 ? str.getBytes(LocalizedMessage.DEFAULT_ENCODING) : zzam.zza(str, true);
            byte[] bytes = z ? str2.length() < 3 ? str2.getBytes(LocalizedMessage.DEFAULT_ENCODING) : zzam.zza(str2, true) : (str2 == null || str2.length() == 0) ? Integer.toString(5).getBytes(LocalizedMessage.DEFAULT_ENCODING) : zzam.zza(zza(str2.getBytes(LocalizedMessage.DEFAULT_ENCODING), (String) null, z2), true);
            zzc.zzcu = bytes;
            return zzbyj.zzf(zzc);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            return null;
        }
    }

    static byte[] zzb(byte[] bArr, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return zzb(bArr, str, true);
    }

    private static byte[] zzb(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] array;
        int zza2 = zza(z);
        if (bArr.length > zza2) {
            bArr = zzbyj.zzf(zzb(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM));
        }
        if (bArr.length < zza2) {
            byte[] bArr2 = new byte[(zza2 - bArr.length)];
            new SecureRandom().nextBytes(bArr2);
            array = ByteBuffer.allocate(zza2 + 1).put((byte) bArr.length).put(bArr).put(bArr2).array();
        } else {
            array = ByteBuffer.allocate(zza2 + 1).put((byte) bArr.length).put(bArr).array();
        }
        if (z) {
            array = ByteBuffer.allocate(256).put(zzh(array)).put(array).array();
        }
        byte[] bArr3 = new byte[256];
        new zzap().zzb(array, bArr3);
        if (str != null && str.length() > 0) {
            zza(str, bArr3);
        }
        return bArr3;
    }

    public static byte[] zzh(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] digest;
        synchronized (zzlH) {
            MessageDigest zzP = zzP();
            if (zzP == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
            zzP.reset();
            zzP.update(bArr);
            digest = zzlG.digest();
        }
        return digest;
    }
}
