package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class TlsStreamCipher implements TlsCipher {
    protected TlsContext context;
    protected StreamCipher decryptCipher;
    protected StreamCipher encryptCipher;
    protected TlsMac readMac;
    protected boolean usesNonce;
    protected TlsMac writeMac;

    /* JADX WARNING: type inference failed for: r14v0, types: [org.spongycastle.crypto.params.KeyParameter] */
    /* JADX WARNING: type inference failed for: r18v0 */
    /* JADX WARNING: type inference failed for: r15v0 */
    /* JADX WARNING: type inference failed for: r16v0 */
    /* JADX WARNING: type inference failed for: r19v0 */
    /* JADX WARNING: type inference failed for: r19v1 */
    /* JADX WARNING: type inference failed for: r16v1, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r15v1 */
    /* JADX WARNING: type inference failed for: r18v2 */
    /* JADX WARNING: type inference failed for: r15v2, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r0v13, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r1v8, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARNING: type inference failed for: r15v3, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARNING: type inference failed for: r18v4 */
    /* JADX WARNING: type inference failed for: r15v4 */
    /* JADX WARNING: type inference failed for: r16v2 */
    /* JADX WARNING: type inference failed for: r19v2 */
    /* JADX WARNING: type inference failed for: r15v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 11 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TlsStreamCipher(org.spongycastle.crypto.tls.TlsContext r24, org.spongycastle.crypto.StreamCipher r25, org.spongycastle.crypto.StreamCipher r26, org.spongycastle.crypto.Digest r27, org.spongycastle.crypto.Digest r28, int r29, boolean r30) throws java.io.IOException {
        /*
            r23 = this;
            r23.<init>()
            boolean r20 = r24.isServer()
            r0 = r24
            r1 = r23
            r1.context = r0
            r0 = r30
            r1 = r23
            r1.usesNonce = r0
            r0 = r25
            r1 = r23
            r1.encryptCipher = r0
            r0 = r26
            r1 = r23
            r1.decryptCipher = r0
            int r4 = r29 * 2
            int r5 = r27.getDigestSize()
            int r4 = r4 + r5
            int r5 = r28.getDigestSize()
            int r21 = r4 + r5
            r0 = r24
            r1 = r21
            byte[] r6 = org.spongycastle.crypto.tls.TlsUtils.calculateKeyBlock(r0, r1)
            r7 = 0
            org.spongycastle.crypto.tls.TlsMac r3 = new org.spongycastle.crypto.tls.TlsMac
            int r8 = r27.getDigestSize()
            r4 = r24
            r5 = r27
            r3.<init>(r4, r5, r6, r7, r8)
            int r4 = r27.getDigestSize()
            int r7 = r7 + r4
            org.spongycastle.crypto.tls.TlsMac r8 = new org.spongycastle.crypto.tls.TlsMac
            int r13 = r28.getDigestSize()
            r9 = r24
            r10 = r28
            r11 = r6
            r12 = r7
            r8.<init>(r9, r10, r11, r12, r13)
            int r4 = r28.getDigestSize()
            int r7 = r7 + r4
            org.spongycastle.crypto.params.KeyParameter r14 = new org.spongycastle.crypto.params.KeyParameter
            r0 = r29
            r14.<init>(r6, r7, r0)
            int r7 = r7 + r29
            org.spongycastle.crypto.params.KeyParameter r22 = new org.spongycastle.crypto.params.KeyParameter
            r0 = r22
            r1 = r29
            r0.<init>(r6, r7, r1)
            int r7 = r7 + r29
            r0 = r21
            if (r7 == r0) goto L_0x007b
            org.spongycastle.crypto.tls.TlsFatalAlert r4 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r5 = 80
            r4.<init>(r5)
            throw r4
        L_0x007b:
            if (r20 == 0) goto L_0x00c3
            r0 = r23
            r0.writeMac = r8
            r0 = r23
            r0.readMac = r3
            r0 = r26
            r1 = r23
            r1.encryptCipher = r0
            r0 = r25
            r1 = r23
            r1.decryptCipher = r0
            r18 = r22
            r15 = r14
            r16 = r15
            r19 = r18
        L_0x0098:
            if (r30 == 0) goto L_0x00e0
            r4 = 8
            byte[] r0 = new byte[r4]
            r17 = r0
            org.spongycastle.crypto.params.ParametersWithIV r18 = new org.spongycastle.crypto.params.ParametersWithIV
            r0 = r18
            r1 = r19
            r2 = r17
            r0.<init>(r1, r2)
            org.spongycastle.crypto.params.ParametersWithIV r15 = new org.spongycastle.crypto.params.ParametersWithIV
            r15.<init>(r16, r17)
        L_0x00b0:
            r0 = r23
            org.spongycastle.crypto.StreamCipher r4 = r0.encryptCipher
            r5 = 1
            r0 = r18
            r4.init(r5, r0)
            r0 = r23
            org.spongycastle.crypto.StreamCipher r4 = r0.decryptCipher
            r5 = 0
            r4.init(r5, r15)
            return
        L_0x00c3:
            r0 = r23
            r0.writeMac = r3
            r0 = r23
            r0.readMac = r8
            r0 = r25
            r1 = r23
            r1.encryptCipher = r0
            r0 = r26
            r1 = r23
            r1.decryptCipher = r0
            r18 = r14
            r15 = r22
            r16 = r15
            r19 = r18
            goto L_0x0098
        L_0x00e0:
            r15 = r16
            r18 = r19
            goto L_0x00b0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsStreamCipher.<init>(org.spongycastle.crypto.tls.TlsContext, org.spongycastle.crypto.StreamCipher, org.spongycastle.crypto.StreamCipher, org.spongycastle.crypto.Digest, org.spongycastle.crypto.Digest, int, boolean):void");
    }

    public int getPlaintextLimit(int ciphertextLimit) {
        return ciphertextLimit - this.writeMac.getSize();
    }

    public byte[] encodePlaintext(long seqNo, short type, byte[] plaintext, int offset, int len) {
        if (this.usesNonce) {
            updateIV(this.encryptCipher, true, seqNo);
        }
        byte[] outBuf = new byte[(this.writeMac.getSize() + len)];
        this.encryptCipher.processBytes(plaintext, offset, len, outBuf, 0);
        byte[] mac = this.writeMac.calculateMac(seqNo, type, plaintext, offset, len);
        this.encryptCipher.processBytes(mac, 0, mac.length, outBuf, len);
        return outBuf;
    }

    public byte[] decodeCiphertext(long seqNo, short type, byte[] ciphertext, int offset, int len) throws IOException {
        if (this.usesNonce) {
            updateIV(this.decryptCipher, false, seqNo);
        }
        int macSize = this.readMac.getSize();
        if (len < macSize) {
            throw new TlsFatalAlert(50);
        }
        int plaintextLength = len - macSize;
        byte[] deciphered = new byte[len];
        this.decryptCipher.processBytes(ciphertext, offset, len, deciphered, 0);
        checkMAC(seqNo, type, deciphered, plaintextLength, len, deciphered, 0, plaintextLength);
        return Arrays.copyOfRange(deciphered, 0, plaintextLength);
    }

    /* access modifiers changed from: protected */
    public void checkMAC(long seqNo, short type, byte[] recBuf, int recStart, int recEnd, byte[] calcBuf, int calcOff, int calcLen) throws IOException {
        if (!Arrays.constantTimeAreEqual(Arrays.copyOfRange(recBuf, recStart, recEnd), this.readMac.calculateMac(seqNo, type, calcBuf, calcOff, calcLen))) {
            throw new TlsFatalAlert(20);
        }
    }

    /* access modifiers changed from: protected */
    public void updateIV(StreamCipher cipher, boolean forEncryption, long seqNo) {
        byte[] nonce = new byte[8];
        TlsUtils.writeUint64(seqNo, nonce, 0);
        cipher.init(forEncryption, new ParametersWithIV(null, nonce));
    }
}
