package org.jmrtd;

import com.airbnb.android.managelisting.settings.DatesModalActivity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import net.p318sf.scuba.smartcards.APDUEvent;
import net.p318sf.scuba.smartcards.APDUListener;
import net.p318sf.scuba.smartcards.APDUWrapper;
import net.p318sf.scuba.smartcards.CardService;
import net.p318sf.scuba.smartcards.CardServiceException;
import net.p318sf.scuba.smartcards.CommandAPDU;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import net.p318sf.scuba.smartcards.ResponseAPDU;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.util.Hex;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.crypto.tls.CipherSuite;

public class PassportApduService extends CardService {
    protected static final byte[] APPLET_AID = {ISOFileInfo.f6307A0, 0, 0, 2, 71, 16, 1};
    private static final Provider BC_PROVIDER = JMRTDSecurityProvider.getBouncyCastleProvider();
    public static final byte CAN_PACE_KEY_REFERENCE = 2;
    private static final byte INS_PACE_GENERAL_AUTHENTICATE = -122;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final byte MRZ_PACE_KEY_REFERENCE = 1;
    public static final byte PIN_PACE_KEY_REFERENCE = 3;
    public static final byte PUK_PACE_REFERENCE = 4;
    private static final IvParameterSpec ZERO_IV_PARAM_SPEC = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
    private static final long serialVersionUID = 2451509825132976178L;
    private byte[] atr;
    private transient Cipher cipher;
    private transient Mac mac;
    private int plainAPDUCount = 0;
    private Collection<APDUListener> plainTextAPDUListeners = new HashSet();
    private CardService service;

    public PassportApduService(CardService cardService) throws CardServiceException {
        this.service = cardService;
        try {
            this.mac = Mac.getInstance("ISO9797Alg3Mac", BC_PROVIDER);
            this.cipher = Cipher.getInstance("DESede/CBC/NoPadding");
        } catch (GeneralSecurityException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new CardServiceException(e.toString());
        }
    }

    public void open() throws CardServiceException {
        if (!this.service.isOpen()) {
            this.service.open();
        }
        this.atr = this.service.getATR();
    }

    public synchronized boolean isOpen() {
        return this.service.isOpen();
    }

    public synchronized ResponseAPDU transmit(CommandAPDU commandAPDU) throws CardServiceException {
        return this.service.transmit(commandAPDU);
    }

    public byte[] getATR() {
        return this.atr;
    }

    public void close() {
        if (this.service != null) {
            this.service.close();
        }
    }

    public void setService(CardService cardService) {
        this.service = cardService;
    }

    public void addAPDUListener(APDUListener aPDUListener) {
        this.service.addAPDUListener(aPDUListener);
    }

    public void removeAPDUListener(APDUListener aPDUListener) {
        this.service.removeAPDUListener(aPDUListener);
    }

    private ResponseAPDU transmit(APDUWrapper aPDUWrapper, CommandAPDU commandAPDU) throws CardServiceException {
        CommandAPDU commandAPDU2;
        if (aPDUWrapper != null) {
            commandAPDU2 = aPDUWrapper.wrap(commandAPDU);
        } else {
            commandAPDU2 = commandAPDU;
        }
        ResponseAPDU transmit = transmit(commandAPDU2);
        short sw = (short) transmit.getSW();
        if (aPDUWrapper == null) {
            return transmit;
        }
        try {
            if (transmit.getBytes().length == 2) {
                throw new CardServiceException("Exception during transmission of wrapped APDU\nC=" + Hex.bytesToHexString(commandAPDU.getBytes()), sw);
            }
            ResponseAPDU unwrap = aPDUWrapper.unwrap(transmit, transmit.getBytes().length);
            int i = this.plainAPDUCount + 1;
            this.plainAPDUCount = i;
            notifyExchangedPlainTextAPDU(i, commandAPDU, unwrap);
            return unwrap;
        } catch (Exception e) {
            if (e instanceof CardServiceException) {
                throw ((CardServiceException) e);
            }
            throw new CardServiceException("Exception during transmission of wrapped APDU\nC=" + Hex.bytesToHexString(commandAPDU.getBytes()) + "\n" + e.getMessage(), sw);
        } catch (Throwable th) {
            int i2 = this.plainAPDUCount + 1;
            this.plainAPDUCount = i2;
            notifyExchangedPlainTextAPDU(i2, commandAPDU, transmit);
            throw th;
        }
    }

    public synchronized void sendSelectApplet(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        CommandAPDU commandAPDU = new CommandAPDU(0, -92, 4, 12, bArr);
        checkStatusWordAfterFileOperation(commandAPDU, transmit(aPDUWrapper, commandAPDU));
    }

    public synchronized void sendSelectFile(short s) throws CardServiceException {
        sendSelectFile(null, s);
    }

    public synchronized void sendSelectFile(APDUWrapper aPDUWrapper, short s) throws CardServiceException {
        CommandAPDU commandAPDU = new CommandAPDU(0, -92, 2, 12, new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)}, 0);
        ResponseAPDU transmit = transmit(aPDUWrapper, commandAPDU);
        if (transmit != null) {
            checkStatusWordAfterFileOperation(commandAPDU, transmit);
        }
    }

    public synchronized byte[] sendReadBinary(short s, int i, boolean z) throws CardServiceException {
        return sendReadBinary(null, s, i, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        if (r12 > 256) goto L_0x0014;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007e A[Catch:{ CardServiceException -> 0x0074 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized byte[] sendReadBinary(net.p318sf.scuba.smartcards.APDUWrapper r10, int r11, int r12, boolean r13) throws net.p318sf.scuba.smartcards.CardServiceException {
        /*
            r9 = this;
            r8 = 1
            r7 = 0
            r6 = 256(0x100, float:3.59E-43)
            monitor-enter(r9)
            if (r12 != 0) goto L_0x000a
            r0 = r7
        L_0x0008:
            monitor-exit(r9)
            return r0
        L_0x000a:
            if (r13 == 0) goto L_0x00e6
            r0 = 128(0x80, float:1.794E-43)
            if (r12 >= r0) goto L_0x0064
            int r12 = r12 + 2
        L_0x0012:
            if (r12 <= r6) goto L_0x00e6
        L_0x0014:
            r0 = 65280(0xff00, float:9.1477E-41)
            r0 = r0 & r11
            int r0 = r0 >> 8
            byte r4 = (byte) r0
            r0 = r11 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            if (r13 == 0) goto L_0x0069
            r1 = 4
            byte[] r5 = new byte[r1]     // Catch:{ all -> 0x0061 }
            r1 = 0
            r2 = 84
            r5[r1] = r2     // Catch:{ all -> 0x0061 }
            r1 = 1
            r2 = 2
            r5[r1] = r2     // Catch:{ all -> 0x0061 }
            r1 = 2
            r5[r1] = r4     // Catch:{ all -> 0x0061 }
            r1 = 3
            r5[r1] = r0     // Catch:{ all -> 0x0061 }
            net.sf.scuba.smartcards.CommandAPDU r0 = new net.sf.scuba.smartcards.CommandAPDU     // Catch:{ all -> 0x0061 }
            r1 = 0
            r2 = -79
            r3 = 0
            r4 = 0
            r0.<init>(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0061 }
        L_0x003c:
            net.sf.scuba.smartcards.ResponseAPDU r2 = r9.transmit(r10, r0)     // Catch:{ CardServiceException -> 0x0074 }
            int r1 = r2.getSW()     // Catch:{ CardServiceException -> 0x00e0 }
            short r1 = (short) r1
            r3 = r1
            r4 = r2
        L_0x0047:
            if (r4 != 0) goto L_0x007e
            r2 = r7
        L_0x004a:
            if (r13 == 0) goto L_0x00e4
            r1 = -28672(0xffffffffffff9000, float:NaN)
            if (r3 != r1) goto L_0x00e4
            r1 = 0
            r5 = 1
            byte r1 = r2[r1]     // Catch:{ all -> 0x0061 }
            r7 = 83
            if (r1 == r7) goto L_0x0083
            net.sf.scuba.smartcards.CardServiceException r0 = new net.sf.scuba.smartcards.CardServiceException     // Catch:{ all -> 0x0061 }
            java.lang.String r1 = "Malformed read binary long response data"
            r0.<init>(r1, r3)     // Catch:{ all -> 0x0061 }
            throw r0     // Catch:{ all -> 0x0061 }
        L_0x0061:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        L_0x0064:
            if (r12 >= r6) goto L_0x0012
            int r12 = r12 + 3
            goto L_0x0012
        L_0x0069:
            net.sf.scuba.smartcards.CommandAPDU r1 = new net.sf.scuba.smartcards.CommandAPDU     // Catch:{ all -> 0x0061 }
            r2 = 0
            r3 = -80
            r5 = r0
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0061 }
            r0 = r1
            goto L_0x003c
        L_0x0074:
            r1 = move-exception
            r2 = r7
        L_0x0076:
            int r1 = r1.getSW()     // Catch:{ all -> 0x0061 }
            short r1 = (short) r1     // Catch:{ all -> 0x0061 }
            r3 = r1
            r4 = r2
            goto L_0x0047
        L_0x007e:
            byte[] r2 = r4.getData()     // Catch:{ all -> 0x0061 }
            goto L_0x004a
        L_0x0083:
            byte r1 = r2[r5]     // Catch:{ all -> 0x0061 }
            r1 = r1 & 128(0x80, float:1.794E-43)
            byte r1 = (byte) r1     // Catch:{ all -> 0x0061 }
            r7 = -128(0xffffffffffffff80, float:NaN)
            if (r1 != r7) goto L_0x00e2
            byte r1 = r2[r5]     // Catch:{ all -> 0x0061 }
            r1 = r1 & 15
            int r1 = r1 + 1
        L_0x0092:
            int r5 = r1 + 1
            int r1 = r2.length     // Catch:{ all -> 0x0061 }
            int r1 = r1 - r5
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x0061 }
            r7 = 0
            int r8 = r1.length     // Catch:{ all -> 0x0061 }
            java.lang.System.arraycopy(r2, r5, r1, r7, r8)     // Catch:{ all -> 0x0061 }
        L_0x009d:
            if (r1 == 0) goto L_0x00a2
            int r2 = r1.length     // Catch:{ all -> 0x0061 }
            if (r2 != 0) goto L_0x00dc
        L_0x00a2:
            java.util.logging.Logger r0 = LOGGER     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
            r2.<init>()     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = "DEBUG: rapduBytes = "
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = java.util.Arrays.toString(r1)     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = ", le = "
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r2 = r2.append(r6)     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = ", sw = "
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0061 }
            java.lang.String r3 = java.lang.Integer.toHexString(r3)     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0061 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0061 }
            r0.warning(r2)     // Catch:{ all -> 0x0061 }
        L_0x00d9:
            r0 = r1
            goto L_0x0008
        L_0x00dc:
            checkStatusWordAfterFileOperation(r0, r4)     // Catch:{ all -> 0x0061 }
            goto L_0x00d9
        L_0x00e0:
            r1 = move-exception
            goto L_0x0076
        L_0x00e2:
            r1 = r8
            goto L_0x0092
        L_0x00e4:
            r1 = r2
            goto L_0x009d
        L_0x00e6:
            r6 = r12
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jmrtd.PassportApduService.sendReadBinary(net.sf.scuba.smartcards.APDUWrapper, int, int, boolean):byte[]");
    }

    public synchronized byte[] sendGetChallenge() throws CardServiceException {
        return sendGetChallenge(null);
    }

    public synchronized byte[] sendGetChallenge(APDUWrapper aPDUWrapper) throws CardServiceException {
        return transmit(aPDUWrapper, new CommandAPDU(0, -124, 0, 0, 8)).getData();
    }

    public synchronized byte[] sendInternalAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        if (bArr != null) {
            if (bArr.length == 8) {
            }
        }
        throw new IllegalArgumentException("rndIFD wrong length");
        return transmit(aPDUWrapper, new CommandAPDU(0, -120, 0, 0, bArr, 256)).getData();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        if (r11.length != 8) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized byte[] sendMutualAuth(byte[] r10, byte[] r11, byte[] r12, javax.crypto.SecretKey r13, javax.crypto.SecretKey r14) throws net.p318sf.scuba.smartcards.CardServiceException {
        /*
            r9 = this;
            r1 = 16
            r8 = 32
            r4 = 8
            monitor-enter(r9)
            if (r10 == 0) goto L_0x000c
            int r0 = r10.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            if (r0 == r4) goto L_0x0023
        L_0x000c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r1 = "rndIFD wrong length"
            r0.<init>(r1)     // Catch:{ GeneralSecurityException -> 0x0015 }
            throw r0     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x0015:
            r0 = move-exception
            net.sf.scuba.smartcards.CardServiceException r1 = new net.sf.scuba.smartcards.CardServiceException     // Catch:{ all -> 0x0020 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0020 }
            r1.<init>(r0)     // Catch:{ all -> 0x0020 }
            throw r1     // Catch:{ all -> 0x0020 }
        L_0x0020:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        L_0x0023:
            if (r11 == 0) goto L_0x0028
            int r0 = r11.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            if (r0 == r4) goto L_0x002c
        L_0x0028:
            r0 = 8
            byte[] r11 = new byte[r0]     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x002c:
            if (r12 == 0) goto L_0x0031
            int r0 = r12.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            if (r0 == r1) goto L_0x003a
        L_0x0031:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r1 = "kIFD wrong length"
            r0.<init>(r1)     // Catch:{ GeneralSecurityException -> 0x0015 }
            throw r0     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x003a:
            if (r13 != 0) goto L_0x0045
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r1 = "kEnc == null"
            r0.<init>(r1)     // Catch:{ GeneralSecurityException -> 0x0015 }
            throw r0     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x0045:
            if (r14 != 0) goto L_0x0050
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r1 = "kMac == null"
            r0.<init>(r1)     // Catch:{ GeneralSecurityException -> 0x0015 }
            throw r0     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x0050:
            javax.crypto.Cipher r0 = r9.cipher     // Catch:{ GeneralSecurityException -> 0x0015 }
            r1 = 1
            javax.crypto.spec.IvParameterSpec r2 = ZERO_IV_PARAM_SPEC     // Catch:{ GeneralSecurityException -> 0x0015 }
            r0.init(r1, r13, r2)     // Catch:{ GeneralSecurityException -> 0x0015 }
            r0 = 32
            byte[] r0 = new byte[r0]     // Catch:{ GeneralSecurityException -> 0x0015 }
            r1 = 0
            r2 = 0
            r3 = 8
            java.lang.System.arraycopy(r10, r1, r0, r2, r3)     // Catch:{ GeneralSecurityException -> 0x0015 }
            r1 = 0
            r2 = 8
            r3 = 8
            java.lang.System.arraycopy(r11, r1, r0, r2, r3)     // Catch:{ GeneralSecurityException -> 0x0015 }
            r1 = 0
            r2 = 16
            r3 = 16
            java.lang.System.arraycopy(r12, r1, r0, r2, r3)     // Catch:{ GeneralSecurityException -> 0x0015 }
            javax.crypto.Cipher r1 = r9.cipher     // Catch:{ GeneralSecurityException -> 0x0015 }
            byte[] r0 = r1.doFinal(r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            int r1 = r0.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            if (r1 == r8) goto L_0x0097
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ GeneralSecurityException -> 0x0015 }
            r2.<init>()     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r3 = "Cryptogram wrong length "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ GeneralSecurityException -> 0x0015 }
            int r0 = r0.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r0 = r0.toString()     // Catch:{ GeneralSecurityException -> 0x0015 }
            r1.<init>(r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            throw r1     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x0097:
            javax.crypto.Mac r1 = r9.mac     // Catch:{ GeneralSecurityException -> 0x0015 }
            r1.init(r14)     // Catch:{ GeneralSecurityException -> 0x0015 }
            javax.crypto.Mac r1 = r9.mac     // Catch:{ GeneralSecurityException -> 0x0015 }
            byte[] r2 = org.jmrtd.Util.padWithMRZ(r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            byte[] r1 = r1.doFinal(r2)     // Catch:{ GeneralSecurityException -> 0x0015 }
            int r2 = r1.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            if (r2 == r4) goto L_0x00b2
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r1 = "MAC wrong length"
            r0.<init>(r1)     // Catch:{ GeneralSecurityException -> 0x0015 }
            throw r0     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x00b2:
            r3 = 0
            r4 = 0
            r2 = 40
            byte[] r5 = new byte[r2]     // Catch:{ GeneralSecurityException -> 0x0015 }
            r2 = 0
            r6 = 0
            r7 = 32
            java.lang.System.arraycopy(r0, r2, r5, r6, r7)     // Catch:{ GeneralSecurityException -> 0x0015 }
            r0 = 0
            r2 = 32
            r6 = 8
            java.lang.System.arraycopy(r1, r0, r5, r2, r6)     // Catch:{ GeneralSecurityException -> 0x0015 }
            r6 = 40
            net.sf.scuba.smartcards.CommandAPDU r0 = new net.sf.scuba.smartcards.CommandAPDU     // Catch:{ GeneralSecurityException -> 0x0015 }
            r1 = 0
            r2 = -126(0xffffffffffffff82, float:NaN)
            r0.<init>(r1, r2, r3, r4, r5, r6)     // Catch:{ GeneralSecurityException -> 0x0015 }
            net.sf.scuba.smartcards.ResponseAPDU r0 = r9.transmit(r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            byte[] r1 = r0.getBytes()     // Catch:{ GeneralSecurityException -> 0x0015 }
            int r0 = r0.getSW()     // Catch:{ GeneralSecurityException -> 0x0015 }
            short r0 = (short) r0     // Catch:{ GeneralSecurityException -> 0x0015 }
            if (r1 != 0) goto L_0x00e9
            net.sf.scuba.smartcards.CardServiceException r1 = new net.sf.scuba.smartcards.CardServiceException     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r2 = "Mutual authentication failed"
            r1.<init>(r2, r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            throw r1     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x00e9:
            r2 = -28672(0xffffffffffff9000, float:NaN)
            if (r0 == r2) goto L_0x0103
            r6 = 0
            net.sf.scuba.smartcards.CommandAPDU r0 = new net.sf.scuba.smartcards.CommandAPDU     // Catch:{ GeneralSecurityException -> 0x0015 }
            r1 = 0
            r2 = -126(0xffffffffffffff82, float:NaN)
            r0.<init>(r1, r2, r3, r4, r5, r6)     // Catch:{ GeneralSecurityException -> 0x0015 }
            net.sf.scuba.smartcards.ResponseAPDU r0 = r9.transmit(r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            byte[] r1 = r0.getBytes()     // Catch:{ GeneralSecurityException -> 0x0015 }
            int r0 = r0.getSW()     // Catch:{ GeneralSecurityException -> 0x0015 }
            short r0 = (short) r0     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x0103:
            int r2 = r1.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            r3 = 42
            if (r2 == r3) goto L_0x0123
            net.sf.scuba.smartcards.CardServiceException r2 = new net.sf.scuba.smartcards.CardServiceException     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ GeneralSecurityException -> 0x0015 }
            r3.<init>()     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r4 = "Mutual authentication failed: expected length: 40 + 2, actual length: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ GeneralSecurityException -> 0x0015 }
            int r1 = r1.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r1 = r1.toString()     // Catch:{ GeneralSecurityException -> 0x0015 }
            r2.<init>(r1, r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            throw r2     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x0123:
            javax.crypto.Cipher r0 = r9.cipher     // Catch:{ GeneralSecurityException -> 0x0015 }
            r2 = 2
            javax.crypto.spec.IvParameterSpec r3 = ZERO_IV_PARAM_SPEC     // Catch:{ GeneralSecurityException -> 0x0015 }
            r0.init(r2, r13, r3)     // Catch:{ GeneralSecurityException -> 0x0015 }
            javax.crypto.Cipher r0 = r9.cipher     // Catch:{ GeneralSecurityException -> 0x0015 }
            r2 = 0
            int r3 = r1.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            int r3 = r3 + -8
            int r3 = r3 + -2
            byte[] r0 = r0.doFinal(r1, r2, r3)     // Catch:{ GeneralSecurityException -> 0x0015 }
            int r1 = r0.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            if (r1 == r8) goto L_0x0155
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ GeneralSecurityException -> 0x0015 }
            r2.<init>()     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r3 = "Cryptogram wrong length "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ GeneralSecurityException -> 0x0015 }
            int r0 = r0.length     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            java.lang.String r0 = r0.toString()     // Catch:{ GeneralSecurityException -> 0x0015 }
            r1.<init>(r0)     // Catch:{ GeneralSecurityException -> 0x0015 }
            throw r1     // Catch:{ GeneralSecurityException -> 0x0015 }
        L_0x0155:
            monitor-exit(r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jmrtd.PassportApduService.sendMutualAuth(byte[], byte[], byte[], javax.crypto.SecretKey, javax.crypto.SecretKey):byte[]");
    }

    public synchronized void sendMutualAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        short sw = (short) transmit(aPDUWrapper, new CommandAPDU(0, -126, 0, 0, bArr)).getSW();
        if (sw != -28672) {
            throw new CardServiceException("Sending External Authenticate failed.", sw);
        }
    }

    public synchronized void sendMSEKAT(APDUWrapper aPDUWrapper, byte[] bArr, byte[] bArr2) throws CardServiceException {
        int i = 0;
        synchronized (this) {
            int length = bArr.length;
            if (bArr2 != null) {
                i = bArr2.length;
            }
            byte[] bArr3 = new byte[(i + length)];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            if (bArr2 != null) {
                System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
            }
            short sw = (short) transmit(aPDUWrapper, new CommandAPDU(0, 34, 65, (int) CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256, bArr3)).getSW();
            if (sw != -28672) {
                throw new CardServiceException("Sending MSE KAT failed", sw);
            }
        }
    }

    public synchronized void sendMSESetDST(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        short sw = (short) transmit(aPDUWrapper, new CommandAPDU(0, 34, 129, (int) CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256, bArr)).getSW();
        if (sw != -28672) {
            throw new CardServiceException("Sending MSE Set DST failed", sw);
        }
    }

    public synchronized void sendMSESetATExtAuth(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        short sw = (short) transmit(aPDUWrapper, new CommandAPDU(0, 34, 129, (int) CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, bArr)).getSW();
        if (sw != -28672) {
            throw new CardServiceException("Sending MSE AT failed", sw);
        }
    }

    public synchronized void sendMSESetATMutualAuth(APDUWrapper aPDUWrapper, String str, int i, byte[] bArr) throws CardServiceException {
        if (str == null) {
            throw new IllegalArgumentException("OID cannot be null");
        }
        try {
            TLVInputStream tLVInputStream = new TLVInputStream(new ByteArrayInputStream(new ASN1ObjectIdentifier(str).getEncoded()));
            tLVInputStream.readTag();
            tLVInputStream.readLength();
            byte[] readValue = tLVInputStream.readValue();
            tLVInputStream.close();
            byte[] wrapDO = Util.wrapDO(ISOFileInfo.DATA_BYTES1, readValue);
            if (i == 1 || i == 2 || i == 3 || i == 4) {
                byte[] wrapDO2 = Util.wrapDO(ISOFileInfo.FILE_IDENTIFIER, new byte[]{(byte) i});
                if (bArr != null) {
                    bArr = Util.wrapDO(-124, bArr);
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(wrapDO);
                byteArrayOutputStream.write(wrapDO2);
                if (bArr != null) {
                    byteArrayOutputStream.write(bArr);
                }
                short sw = (short) transmit(aPDUWrapper, new CommandAPDU(0, 34, (int) CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, (int) CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, byteArrayOutputStream.toByteArray())).getSW();
                if (sw != -28672) {
                    throw new CardServiceException("Sending MSE AT failed", sw);
                }
            } else {
                throw new IllegalArgumentException("Unsupported key type reference (MRZ, CAN, etc), found " + i);
            }
        } catch (IOException e) {
            LOGGER.severe("Error while copying data: " + e.getMessage());
            throw new IllegalStateException("Error while copying data");
        } catch (IOException e2) {
            throw new IllegalArgumentException("Illegal OID: " + str + " (" + e2.getMessage() + ")");
        }
    }

    public synchronized byte[] sendGeneralAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr, boolean z) throws CardServiceException {
        byte[] unwrapDO;
        int i = 0;
        synchronized (this) {
            byte[] wrapDO = Util.wrapDO(124, bArr);
            if (!z) {
                i = 16;
            }
            ResponseAPDU transmit = transmit(aPDUWrapper, new CommandAPDU(i, -122, 0, 0, wrapDO, 256));
            short sw = (short) transmit.getSW();
            if (sw != -28672) {
                throw new CardServiceException("Sending general authenticate failed", sw);
            }
            unwrapDO = Util.unwrapDO(124, transmit.getData());
        }
        return unwrapDO;
    }

    public synchronized void sendPSOExtendedLengthMode(APDUWrapper aPDUWrapper, byte[] bArr, byte[] bArr2) throws CardServiceException {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        short sw = (short) transmit(aPDUWrapper, new CommandAPDU(0, 42, 0, 190, bArr3)).getSW();
        if (sw != -28672) {
            throw new CardServiceException("Sending PSO failed", sw);
        }
    }

    public synchronized void sendPSOChainMode(APDUWrapper aPDUWrapper, byte[] bArr, byte[] bArr2) throws CardServiceException {
        int i;
        int i2 = 0;
        synchronized (this) {
            byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
            int length = bArr3.length;
            if (bArr3.length > 223) {
                int length2 = bArr3.length / DatesModalActivity.RESULT_CODE;
                if (length2 * DatesModalActivity.RESULT_CODE < bArr3.length) {
                    i = length2 + 1;
                } else {
                    i = length2;
                }
                int i3 = 0;
                while (i3 < i - 1) {
                    short sw = (short) transmit(aPDUWrapper, new CommandAPDU(16, 42, 0, 190, bArr3, i2, length)).getSW();
                    if (sw != -28672) {
                        throw new CardServiceException("Sending PSO failed", sw);
                    }
                    length -= DatesModalActivity.RESULT_CODE;
                    i3++;
                    i2 += DatesModalActivity.RESULT_CODE;
                }
            }
            short sw2 = (short) transmit(aPDUWrapper, new CommandAPDU(0, 42, 0, 190, bArr3, i2, length)).getSW();
            if (sw2 != -28672) {
                throw new CardServiceException("Sending PSO failed", sw2);
            }
        }
    }

    public void addPlainTextAPDUListener(APDUListener aPDUListener) {
        if (this.plainTextAPDUListeners != null) {
            this.plainTextAPDUListeners.add(aPDUListener);
        }
    }

    public void removePlainTextAPDUListener(APDUListener aPDUListener) {
        if (this.plainTextAPDUListeners != null) {
            this.plainTextAPDUListeners.add(aPDUListener);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyExchangedPlainTextAPDU(int i, CommandAPDU commandAPDU, ResponseAPDU responseAPDU) {
        if (this.plainTextAPDUListeners != null && this.plainTextAPDUListeners.size() >= 1) {
            APDUEvent aPDUEvent = new APDUEvent(this, "PLAINTEXT", i, commandAPDU, responseAPDU);
            for (APDUListener exchangedAPDU : this.plainTextAPDUListeners) {
                exchangedAPDU.exchangedAPDU(aPDUEvent);
            }
        }
    }

    private static void checkStatusWordAfterFileOperation(CommandAPDU commandAPDU, ResponseAPDU responseAPDU) throws CardServiceException {
        short sw = (short) responseAPDU.getSW();
        String str = "CAPDU = " + Hex.bytesToHexString(commandAPDU.getBytes()) + ", RAPDU = " + Hex.bytesToHexString(responseAPDU.getBytes());
        switch (sw) {
            case -28672:
                return;
            case 27010:
            case 27013:
            case 27014:
                throw new CardServiceException("Access to file denied, " + str, sw);
            case 27266:
                throw new CardServiceException("File not found, " + str, sw);
            default:
                throw new CardServiceException("Error occured, " + str, sw);
        }
    }
}
