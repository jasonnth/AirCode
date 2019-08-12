package p004bo.app;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: bo.app.fy */
public class C0532fy implements C0530fw {
    /* renamed from: a */
    public String mo7100a(String str) {
        return new BigInteger(m761a(str.getBytes())).abs().toString(36);
    }

    /* renamed from: a */
    private byte[] m761a(byte[] bArr) {
        boolean z = false;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            C0599hn.m1061a((Throwable) e);
            return z;
        }
    }
}
