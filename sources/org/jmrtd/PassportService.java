package org.jmrtd;

import com.airbnb.android.managelisting.settings.DatesModalActivity;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import net.p318sf.scuba.smartcards.APDUWrapper;
import net.p318sf.scuba.smartcards.CardFileInputStream;
import net.p318sf.scuba.smartcards.CardService;
import net.p318sf.scuba.smartcards.CardServiceException;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import net.p318sf.scuba.tlv.TLVOutputStream;
import org.jmrtd.cert.CVCAuthorizationTemplate.Role;
import org.jmrtd.cert.CVCPrincipal;
import org.jmrtd.cert.CardVerifiableCertificate;
import org.jmrtd.lds.MRZInfo;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.i18n.LocalizedMessage;
import org.spongycastle.jce.interfaces.ECPrivateKey;

public class PassportService extends PassportApduService implements Serializable {
    private static final int BAC_AUTHENTICATED_STATE = 2;
    private static final Provider BC_PROVIDER = JMRTDSecurityProvider.getBouncyCastleProvider();
    private static final int CA_AUTHENTICATED_STATE = 4;
    public static final short EF_CARD_ACCESS = 284;
    public static final short EF_COM = 286;
    public static final short EF_CVCA = 284;
    public static final short EF_DG1 = 257;
    public static final short EF_DG10 = 266;
    public static final short EF_DG11 = 267;
    public static final short EF_DG12 = 268;
    public static final short EF_DG13 = 269;
    public static final short EF_DG14 = 270;
    public static final short EF_DG15 = 271;
    public static final short EF_DG16 = 272;
    public static final short EF_DG2 = 258;
    public static final short EF_DG3 = 259;
    public static final short EF_DG4 = 260;
    public static final short EF_DG5 = 261;
    public static final short EF_DG6 = 262;
    public static final short EF_DG7 = 263;
    public static final short EF_DG8 = 264;
    public static final short EF_DG9 = 265;
    public static final short EF_SOD = 285;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyMMdd");
    private static final int SESSION_STARTED_STATE = 1;
    private static final int SESSION_STOPPED_STATE = 0;
    public static final byte SF_COM = 30;
    public static final byte SF_CVCA = 28;
    public static final byte SF_DG1 = 1;
    public static final byte SF_DG10 = 10;
    public static final byte SF_DG11 = 11;
    public static final byte SF_DG12 = 12;
    public static final byte SF_DG13 = 13;
    public static final byte SF_DG14 = 14;
    public static final byte SF_DG15 = 15;
    public static final byte SF_DG16 = 16;
    public static final byte SF_DG2 = 2;
    public static final byte SF_DG3 = 3;
    public static final byte SF_DG4 = 4;
    public static final byte SF_DG5 = 5;
    public static final byte SF_DG6 = 6;
    public static final byte SF_DG7 = 7;
    public static final byte SF_DG8 = 8;
    public static final byte SF_DG9 = 9;
    public static final byte SF_SOD = 29;
    private static final int TA_AUTHENTICATED_STATE = 5;
    public static int maxBlockSize = DatesModalActivity.RESULT_CODE;
    private static final long serialVersionUID = 1751933705552226972L;
    private final int TAG_CVCERTIFICATE_SIGNATURE = EACTags.SIGNATURE;

    /* renamed from: fs */
    private MRTDFileSystem f6332fs = new MRTDFileSystem(this);
    protected Random random = new SecureRandom();
    private int state = 0;
    protected SecureMessagingWrapper wrapper;

    public PassportService(CardService cardService) throws CardServiceException {
        super(cardService);
    }

    public void open() throws CardServiceException {
        if (!isOpen()) {
            synchronized (this) {
                super.open();
                this.state = 1;
            }
        }
    }

    public void sendSelectApplet(boolean z) throws CardServiceException {
        if (z) {
            LOGGER.info("DEBUG: wrapper = " + this.wrapper);
            sendSelectApplet(this.wrapper, APPLET_AID);
            return;
        }
        sendSelectApplet(null, APPLET_AID);
    }

    public boolean isOpen() {
        return this.state != 0;
    }

    public synchronized void doBAC(BACKeySpec bACKeySpec) throws CardServiceException {
        try {
            byte[] computeKeySeedForBAC = computeKeySeedForBAC(bACKeySpec);
            doBAC(Util.deriveKey(computeKeySeedForBAC, 1), Util.deriveKey(computeKeySeedForBAC, 2));
        } catch (CardServiceException e) {
            LOGGER.warning("BAC failed for BAC key \"" + bACKeySpec + "\"");
            throw e;
        } catch (GeneralSecurityException e2) {
            throw new CardServiceException(e2.toString());
        }
    }

    public synchronized void doBAC(SecretKey secretKey, SecretKey secretKey2) throws CardServiceException, GeneralSecurityException {
        byte[] sendGetChallenge = sendGetChallenge();
        byte[] bArr = new byte[8];
        this.random.nextBytes(bArr);
        byte[] bArr2 = new byte[16];
        this.random.nextBytes(bArr2);
        byte[] bArr3 = new byte[16];
        System.arraycopy(sendMutualAuth(bArr, sendGetChallenge, bArr2, secretKey, secretKey2), 16, bArr3, 0, 16);
        byte[] bArr4 = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr4[i] = (byte) ((bArr2[i] & 255) ^ (bArr3[i] & 255));
        }
        this.wrapper = new DESedeSecureMessagingWrapper(Util.deriveKey(bArr4, 1), Util.deriveKey(bArr4, 2), Util.computeSendSequenceCounter(sendGetChallenge, bArr));
        this.state = 2;
    }

    public synchronized void sendSelectFile(short s) throws CardServiceException {
        sendSelectFile(this.wrapper, s);
    }

    public synchronized byte[] sendReadBinary(int i, int i2, boolean z) throws CardServiceException {
        return sendReadBinary(this.wrapper, i, i2, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0286, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0293, code lost:
        throw new org.jmrtd.PACEException("PICC side exception in mapping nonce step", r2.getSW());
     */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0286 A[ExcHandler: CardServiceException (r2v12 'e' net.sf.scuba.smartcards.CardServiceException A[CUSTOM_DECLARE]), Splitter:B:38:0x00e9] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void doPACE(org.jmrtd.BACKeySpec r14, java.lang.String r15, java.security.spec.AlgorithmParameterSpec r16) throws org.jmrtd.PACEException {
        /*
            r13 = this;
            r2 = 0
            monitor-enter(r13)
            org.jmrtd.lds.PACEInfo$MappingType r4 = org.jmrtd.lds.PACEInfo.toMappingType(r15)     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = org.jmrtd.lds.PACEInfo.toKeyAgreementAlgorithm(r15)     // Catch:{ all -> 0x0020 }
            java.lang.String r6 = org.jmrtd.lds.PACEInfo.toCipherAlgorithm(r15)     // Catch:{ all -> 0x0020 }
            org.jmrtd.lds.PACEInfo.toDigestAlgorithm(r15)     // Catch:{ all -> 0x0020 }
            int r7 = org.jmrtd.lds.PACEInfo.toKeyLength(r15)     // Catch:{ all -> 0x0020 }
            if (r5 != 0) goto L_0x0023
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0020 }
            java.lang.String r3 = "Unknown agreement algorithm"
            r2.<init>(r3)     // Catch:{ all -> 0x0020 }
            throw r2     // Catch:{ all -> 0x0020 }
        L_0x0020:
            r2 = move-exception
            monitor-exit(r13)
            throw r2
        L_0x0023:
            java.lang.String r3 = "ECDH"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x0020 }
            if (r3 != 0) goto L_0x004f
            java.lang.String r3 = "DH"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x0020 }
            if (r3 != 0) goto L_0x004f
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r3.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = "Unsupported agreement algorithm, expected ECDH or DH, found "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0020 }
            r2.<init>(r3)     // Catch:{ all -> 0x0020 }
            throw r2     // Catch:{ all -> 0x0020 }
        L_0x004f:
            java.lang.String r3 = "ECDH"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x0020 }
            if (r3 == 0) goto L_0x0078
            r0 = r16
            boolean r3 = r0 instanceof java.security.spec.ECParameterSpec     // Catch:{ all -> 0x0020 }
            if (r3 != 0) goto L_0x00a1
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r3.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = "Expected ECParameterSpec for agreement algorithm "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0020 }
            r2.<init>(r3)     // Catch:{ all -> 0x0020 }
            throw r2     // Catch:{ all -> 0x0020 }
        L_0x0078:
            java.lang.String r3 = "DH"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x0020 }
            if (r3 == 0) goto L_0x00a1
            r0 = r16
            boolean r3 = r0 instanceof javax.crypto.spec.DHParameterSpec     // Catch:{ all -> 0x0020 }
            if (r3 != 0) goto L_0x00a1
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r3.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = "Expected DHParameterSpec for agreement algorithm "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0020 }
            r2.<init>(r3)     // Catch:{ all -> 0x0020 }
            throw r2     // Catch:{ all -> 0x0020 }
        L_0x00a1:
            byte[] r3 = computeKeySeedForPACE(r14)     // Catch:{ GeneralSecurityException -> 0x019d }
            r8 = 3
            javax.crypto.SecretKey r3 = org.jmrtd.Util.deriveKey(r3, r6, r7, r8)     // Catch:{ GeneralSecurityException -> 0x019d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ GeneralSecurityException -> 0x019d }
            r8.<init>()     // Catch:{ GeneralSecurityException -> 0x019d }
            java.lang.StringBuilder r8 = r8.append(r6)     // Catch:{ GeneralSecurityException -> 0x019d }
            java.lang.String r9 = "/CBC/NoPadding"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ GeneralSecurityException -> 0x019d }
            java.lang.String r8 = r8.toString()     // Catch:{ GeneralSecurityException -> 0x019d }
            javax.crypto.Cipher r8 = javax.crypto.Cipher.getInstance(r8)     // Catch:{ GeneralSecurityException -> 0x019d }
            r9 = 0
            org.jmrtd.SecureMessagingWrapper r10 = r13.wrapper     // Catch:{ CardServiceException -> 0x01a7 }
            r11 = 1
            r13.sendMSESetATMutualAuth(r10, r15, r11, r9)     // Catch:{ CardServiceException -> 0x01a7 }
            r9 = 0
            byte[] r9 = new byte[r9]     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            org.jmrtd.SecureMessagingWrapper r10 = r13.wrapper     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            r11 = 0
            byte[] r9 = r13.sendGeneralAuthenticate(r10, r9, r11)     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            r10 = -128(0xffffffffffffff80, float:NaN)
            byte[] r9 = org.jmrtd.Util.unwrapDO(r10, r9)     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            r10 = 2
            javax.crypto.spec.IvParameterSpec r11 = new javax.crypto.spec.IvParameterSpec     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            int r12 = r9.length     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            byte[] r12 = new byte[r12]     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            r11.<init>(r12)     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            r8.init(r10, r3, r11)     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            byte[] r8 = r8.doFinal(r9)     // Catch:{ GeneralSecurityException -> 0x01b5, CardServiceException -> 0x01f1 }
            java.security.Provider r3 = BC_PROVIDER     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.security.KeyPairGenerator r3 = java.security.KeyPairGenerator.getInstance(r5, r3)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r0 = r16
            r3.initialize(r0)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.security.KeyPair r3 = r3.generateKeyPair()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.security.PublicKey r9 = r3.getPublic()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.security.PrivateKey r3 = r3.getPrivate()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            javax.crypto.KeyAgreement r10 = javax.crypto.KeyAgreement.getInstance(r5)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r10.init(r3)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            int[] r3 = org.jmrtd.PassportService.C52261.$SwitchMap$org$jmrtd$lds$PACEInfo$MappingType     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            int r11 = r4.ordinal()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r3 = r3[r11]     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            switch(r3) {
                case 1: goto L_0x01ff;
                case 2: goto L_0x0205;
                default: goto L_0x0112;
            }     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
        L_0x0112:
            r3 = r2
        L_0x0113:
            r9 = -127(0xffffffffffffff81, float:NaN)
            byte[] r3 = org.jmrtd.Util.wrapDO(r9, r3)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            org.jmrtd.SecureMessagingWrapper r9 = r13.wrapper     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r11 = 0
            byte[] r3 = r13.sendGeneralAuthenticate(r9, r3, r11)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            int[] r9 = org.jmrtd.PassportService.C52261.$SwitchMap$org$jmrtd$lds$PACEInfo$MappingType     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            int r4 = r4.ordinal()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r4 = r9[r4]     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            switch(r4) {
                case 1: goto L_0x022d;
                case 2: goto L_0x0294;
                default: goto L_0x012b;
            }
        L_0x012b:
            r4 = r2
        L_0x012c:
            java.security.Provider r2 = BC_PROVIDER     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            java.security.KeyPairGenerator r2 = java.security.KeyPairGenerator.getInstance(r5, r2)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            r2.initialize(r4)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            java.security.KeyPair r2 = r2.generateKeyPair()     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            java.security.PublicKey r8 = r2.getPublic()     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            java.security.PrivateKey r2 = r2.getPrivate()     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            java.security.Provider r3 = BC_PROVIDER     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            javax.crypto.KeyAgreement r5 = javax.crypto.KeyAgreement.getInstance(r5, r3)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            r5.init(r2)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            byte[] r2 = org.jmrtd.Util.encodePublicKeyForSmartCard(r8)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            r3 = -125(0xffffffffffffff83, float:NaN)
            byte[] r2 = org.jmrtd.Util.wrapDO(r3, r2)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            org.jmrtd.SecureMessagingWrapper r3 = r13.wrapper     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            r9 = 0
            byte[] r2 = r13.sendGeneralAuthenticate(r3, r2, r9)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            r3 = -124(0xffffffffffffff84, float:NaN)
            byte[] r2 = org.jmrtd.Util.unwrapDO(r3, r2)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            java.security.PublicKey r3 = org.jmrtd.Util.decodePublicKeyFromSmartCard(r2, r4)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            r0 = r3
            java.security.interfaces.ECPublicKey r0 = (java.security.interfaces.ECPublicKey) r0     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            r2 = r0
            r2.getW()     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            org.jmrtd.Util.getPrime(r4)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            boolean r2 = r8.equals(r3)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            if (r2 == 0) goto L_0x029d
            org.jmrtd.PACEException r2 = new org.jmrtd.PACEException     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            java.lang.String r3 = "PCD's public key and PICC's public key are the same in key agreement step!"
            r2.<init>(r3)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            throw r2     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
        L_0x017e:
            r2 = move-exception
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "PCD side exception in key agreement step: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0020 }
            r3.<init>(r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x019d:
            r2 = move-exception
            org.jmrtd.PACEException r2 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.String r3 = "PCD side error in static PACE key derivation step"
            r2.<init>(r3)     // Catch:{ all -> 0x0020 }
            throw r2     // Catch:{ all -> 0x0020 }
        L_0x01a7:
            r2 = move-exception
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = "PICC side error in static PACE key derivation step"
            int r2 = r2.getSW()     // Catch:{ all -> 0x0020 }
            r3.<init>(r4, r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x01b5:
            r2 = move-exception
            java.util.logging.Logger r3 = LOGGER     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "Exception: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0020 }
            r3.severe(r4)     // Catch:{ all -> 0x0020 }
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "PCD side exception in tranceiving nonce step: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0020 }
            r3.<init>(r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x01f1:
            r2 = move-exception
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = "PICC side exception in tranceiving nonce step"
            int r2 = r2.getSW()     // Catch:{ all -> 0x0020 }
            r3.<init>(r4, r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x01ff:
            byte[] r3 = org.jmrtd.Util.encodePublicKeyForSmartCard(r9)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            goto L_0x0113
        L_0x0205:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.String r3 = "IM not yet implemented"
            r2.<init>(r3)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            throw r2     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
        L_0x020e:
            r2 = move-exception
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "PCD side error in mapping nonce step: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0020 }
            r3.<init>(r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x022d:
            r2 = -126(0xffffffffffffff82, float:NaN)
            byte[] r2 = org.jmrtd.Util.unwrapDO(r2, r3)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r0 = r16
            java.security.PublicKey r2 = org.jmrtd.Util.decodePublicKeyFromSmartCard(r2, r0)     // Catch:{ GeneralSecurityException -> 0x024a, CardServiceException -> 0x0286 }
            r3 = 1
            r10.doPhase(r2, r3)     // Catch:{ GeneralSecurityException -> 0x024a, CardServiceException -> 0x0286 }
            byte[] r2 = r10.generateSecret()     // Catch:{ GeneralSecurityException -> 0x024a, CardServiceException -> 0x0286 }
            r0 = r16
            java.security.spec.AlgorithmParameterSpec r2 = org.jmrtd.Util.mapNonceGM(r8, r2, r0)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r4 = r2
            goto L_0x012c
        L_0x024a:
            r2 = move-exception
            java.util.logging.Logger r3 = LOGGER     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r4.<init>()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.String r5 = "Exception: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.String r4 = r4.toString()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r3.severe(r4)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r4.<init>()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.String r5 = "Error during mapping"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.String r2 = r2.toString()     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            r3.<init>(r2)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            throw r3     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
        L_0x0286:
            r2 = move-exception
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = "PICC side exception in mapping nonce step"
            int r2 = r2.getSW()     // Catch:{ all -> 0x0020 }
            r3.<init>(r4, r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x0294:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            java.lang.String r3 = "DEBUG: IM not yet implemented"
            r2.<init>(r3)     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
            throw r2     // Catch:{ GeneralSecurityException -> 0x020e, CardServiceException -> 0x0286 }
        L_0x029d:
            r2 = 1
            r5.doPhase(r3, r2)     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            byte[] r2 = r5.generateSecret()     // Catch:{ IllegalStateException -> 0x017e, GeneralSecurityException -> 0x0329, CardServiceException -> 0x0348 }
            r4 = 1
            javax.crypto.SecretKey r4 = org.jmrtd.Util.deriveKey(r2, r6, r7, r4)     // Catch:{ GeneralSecurityException -> 0x0356 }
            r5 = 2
            javax.crypto.SecretKey r5 = org.jmrtd.Util.deriveKey(r2, r6, r7, r5)     // Catch:{ GeneralSecurityException -> 0x0356 }
            java.util.logging.Logger r2 = LOGGER     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            r7.<init>()     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            java.lang.String r9 = "DEBUG: macKey = ("
            java.lang.StringBuilder r7 = r7.append(r9)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            byte[] r9 = r5.getEncoded()     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            int r9 = r9.length     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            java.lang.StringBuilder r7 = r7.append(r9)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            java.lang.String r9 = ") "
            java.lang.StringBuilder r7 = r7.append(r9)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            byte[] r9 = r5.getEncoded()     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            java.lang.String r9 = net.p318sf.scuba.util.Hex.bytesToHexString(r9)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            java.lang.StringBuilder r7 = r7.append(r9)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            java.lang.String r7 = r7.toString()     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            r2.info(r7)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            byte[] r2 = org.jmrtd.Util.generateAuthenticationToken(r15, r5, r3)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            r3 = -123(0xffffffffffffff85, float:NaN)
            byte[] r2 = org.jmrtd.Util.wrapDO(r3, r2)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            org.jmrtd.SecureMessagingWrapper r3 = r13.wrapper     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            r7 = 1
            byte[] r2 = r13.sendGeneralAuthenticate(r3, r2, r7)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            r3 = -122(0xffffffffffffff86, float:NaN)
            byte[] r2 = org.jmrtd.Util.unwrapDO(r3, r2)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            byte[] r3 = org.jmrtd.Util.generateAuthenticationToken(r15, r5, r8)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            boolean r2 = java.util.Arrays.equals(r3, r2)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            if (r2 != 0) goto L_0x03a0
            java.security.GeneralSecurityException r2 = new java.security.GeneralSecurityException     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            java.lang.String r3 = "PICC authentication token mismatch"
            r2.<init>(r3)     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
            throw r2     // Catch:{ GeneralSecurityException -> 0x030a, CardServiceException -> 0x0392 }
        L_0x030a:
            r2 = move-exception
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "PCD side exception in authentication token generation step: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0020 }
            r3.<init>(r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x0329:
            r2 = move-exception
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "PCD side exception in key agreement step: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0020 }
            r3.<init>(r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x0348:
            r2 = move-exception
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = "PICC side exception in key agreement step"
            int r2 = r2.getSW()     // Catch:{ all -> 0x0020 }
            r3.<init>(r4, r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x0356:
            r2 = move-exception
            java.util.logging.Logger r3 = LOGGER     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "Exception: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0020 }
            r3.severe(r4)     // Catch:{ all -> 0x0020 }
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "Security exception during secure messaging key derivation: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0020 }
            r3.<init>(r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x0392:
            r2 = move-exception
            org.jmrtd.PACEException r3 = new org.jmrtd.PACEException     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = "PICC side exception in authentication token generation step"
            int r2 = r2.getSW()     // Catch:{ all -> 0x0020 }
            r3.<init>(r4, r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x03a0:
            java.lang.String r2 = "DESede"
            boolean r2 = r6.startsWith(r2)     // Catch:{ GeneralSecurityException -> 0x03d1 }
            if (r2 == 0) goto L_0x03ba
            org.jmrtd.DESedeSecureMessagingWrapper r2 = new org.jmrtd.DESedeSecureMessagingWrapper     // Catch:{ GeneralSecurityException -> 0x03d1 }
            r2.<init>(r4, r5)     // Catch:{ GeneralSecurityException -> 0x03d1 }
            r13.wrapper = r2     // Catch:{ GeneralSecurityException -> 0x03d1 }
        L_0x03b0:
            java.util.logging.Logger r2 = LOGGER     // Catch:{ GeneralSecurityException -> 0x03d1 }
            java.lang.String r3 = "DEBUG: Starting secure messaging based on PACE"
            r2.info(r3)     // Catch:{ GeneralSecurityException -> 0x03d1 }
            monitor-exit(r13)
            return
        L_0x03ba:
            java.lang.String r2 = "AES"
            boolean r2 = r6.startsWith(r2)     // Catch:{ GeneralSecurityException -> 0x03d1 }
            if (r2 == 0) goto L_0x03b0
            org.jmrtd.SecureMessagingWrapper r2 = r13.wrapper     // Catch:{ GeneralSecurityException -> 0x03d1 }
            if (r2 != 0) goto L_0x040d
            r2 = 0
        L_0x03c9:
            org.jmrtd.AESSecureMessagingWrapper r6 = new org.jmrtd.AESSecureMessagingWrapper     // Catch:{ GeneralSecurityException -> 0x03d1 }
            r6.<init>(r4, r5, r2)     // Catch:{ GeneralSecurityException -> 0x03d1 }
            r13.wrapper = r6     // Catch:{ GeneralSecurityException -> 0x03d1 }
            goto L_0x03b0
        L_0x03d1:
            r2 = move-exception
            java.util.logging.Logger r3 = LOGGER     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "Exception: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0020 }
            r3.severe(r4)     // Catch:{ all -> 0x0020 }
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r4.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "Security exception in secure messaging establishment: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0020 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0020 }
            r3.<init>(r2)     // Catch:{ all -> 0x0020 }
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x040d:
            org.jmrtd.SecureMessagingWrapper r2 = r13.wrapper     // Catch:{ GeneralSecurityException -> 0x03d1 }
            long r2 = r2.getSendSequenceCounter()     // Catch:{ GeneralSecurityException -> 0x03d1 }
            goto L_0x03c9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jmrtd.PassportService.doPACE(org.jmrtd.BACKeySpec, java.lang.String, java.security.spec.AlgorithmParameterSpec):void");
    }

    public synchronized ChipAuthenticationResult doCA(BigInteger bigInteger, PublicKey publicKey) throws CardServiceException {
        AlgorithmParameterSpec params;
        KeyPair generateKeyPair;
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        if (publicKey == null) {
            throw new IllegalArgumentException("Public key is null");
        }
        try {
            String inferKeyAgreementAlgorithm = Util.inferKeyAgreementAlgorithm(publicKey);
            KeyPairGenerator instance = KeyPairGenerator.getInstance(inferKeyAgreementAlgorithm);
            if ("DH".equals(inferKeyAgreementAlgorithm)) {
                params = ((DHPublicKey) publicKey).getParams();
            } else if ("ECDH".equals(inferKeyAgreementAlgorithm)) {
                params = ((ECPublicKey) publicKey).getParams();
            } else {
                throw new IllegalStateException("Unsupported algorithm \"" + inferKeyAgreementAlgorithm + "\"");
            }
            instance.initialize(params);
            generateKeyPair = instance.generateKeyPair();
            KeyAgreement instance2 = KeyAgreement.getInstance(inferKeyAgreementAlgorithm);
            instance2.init(generateKeyPair.getPrivate());
            instance2.doPhase(publicKey, true);
            byte[] generateSecret = instance2.generateSecret();
            byte[] bArr4 = new byte[0];
            if ("DH".equals(inferKeyAgreementAlgorithm)) {
                byte[] byteArray = ((DHPublicKey) generateKeyPair.getPublic()).getY().toByteArray();
                MessageDigest.getInstance("SHA1");
                bArr2 = byteArray;
                bArr = MessageDigest.getInstance("SHA1").digest(byteArray);
            } else if ("ECDH".equals(inferKeyAgreementAlgorithm)) {
                org.spongycastle.jce.interfaces.ECPublicKey eCPublicKey = (org.spongycastle.jce.interfaces.ECPublicKey) generateKeyPair.getPublic();
                byte[] alignKeyDataToSize = Util.alignKeyDataToSize(Util.i2os(eCPublicKey.getQ().getX().toBigInteger()), eCPublicKey.getParameters().getCurve().getFieldSize() / 8);
                bArr2 = eCPublicKey.getQ().getEncoded();
                bArr = alignKeyDataToSize;
            } else {
                bArr = bArr4;
                bArr2 = null;
            }
            byte[] wrapDO = Util.wrapDO(-111, bArr2);
            if (bigInteger.compareTo(BigInteger.ZERO) >= 0) {
                bArr3 = Util.wrapDO(-124, bigInteger.toByteArray());
            } else {
                bArr3 = null;
            }
            sendMSEKAT(this.wrapper, wrapDO, bArr3);
            this.wrapper = new DESedeSecureMessagingWrapper(Util.deriveKey(generateSecret, 1), Util.deriveKey(generateSecret, 2), 0);
            this.state = 4;
        } catch (GeneralSecurityException e) {
            throw new CardServiceException(e.toString());
        }
        return new ChipAuthenticationResult(bigInteger, publicKey, bArr, generateKeyPair);
    }

    public synchronized TerminalAuthenticationResult doTA(CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, ChipAuthenticationResult chipAuthenticationResult, String str2) throws CardServiceException {
        CVCPrincipal cVCPrincipal2;
        CVCPrincipal authorityReference;
        byte[] sendGetChallenge;
        byte[] bArr;
        if (list != null) {
            try {
                if (list.size() >= 1) {
                    byte[] keyHash = chipAuthenticationResult.getKeyHash();
                    if (keyHash == null) {
                        throw new IllegalArgumentException("CA key hash is null");
                    }
                    CardVerifiableCertificate cardVerifiableCertificate = (CardVerifiableCertificate) list.get(0);
                    if (Role.CVCA.equals(cardVerifiableCertificate.getAuthorizationTemplate().getRole())) {
                        cVCPrincipal2 = cardVerifiableCertificate.getHolderReference();
                        if (cVCPrincipal == null || cVCPrincipal.equals(cVCPrincipal2)) {
                            if (cVCPrincipal != null) {
                                cVCPrincipal2 = cVCPrincipal;
                            }
                            list.remove(0);
                        } else {
                            throw new CardServiceException("First certificate holds wrong authority, found " + cVCPrincipal2.getName() + ", expected " + cVCPrincipal.getName());
                        }
                    } else {
                        cVCPrincipal2 = cVCPrincipal;
                    }
                    authorityReference = cardVerifiableCertificate.getAuthorityReference();
                    if (cVCPrincipal2 == null || cVCPrincipal2.equals(authorityReference)) {
                        if (cVCPrincipal2 != null) {
                            authorityReference = cVCPrincipal2;
                        }
                        CardVerifiableCertificate cardVerifiableCertificate2 = (CardVerifiableCertificate) list.get(list.size() - 1);
                        Role role = cardVerifiableCertificate2.getAuthorizationTemplate().getRole();
                        if (!Role.IS.equals(role)) {
                            throw new CardServiceException("Last certificate in chain (" + cardVerifiableCertificate2.getHolderReference().getName() + ") does not have role IS, but has role " + role);
                        }
                        for (CardVerifiableCertificate cardVerifiableCertificate3 : list) {
                            sendMSESetDST(this.wrapper, Util.wrapDO(ISOFileInfo.FILE_IDENTIFIER, cardVerifiableCertificate3.getAuthorityReference().getName().getBytes(LocalizedMessage.DEFAULT_ENCODING)));
                            byte[] certBodyData = cardVerifiableCertificate3.getCertBodyData();
                            byte[] signature = cardVerifiableCertificate3.getSignature();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            TLVOutputStream tLVOutputStream = new TLVOutputStream(byteArrayOutputStream);
                            tLVOutputStream.writeTag(EACTags.SIGNATURE);
                            tLVOutputStream.writeValue(signature);
                            tLVOutputStream.close();
                            sendPSOExtendedLengthMode(this.wrapper, certBodyData, byteArrayOutputStream.toByteArray());
                        }
                        if (privateKey == null) {
                            throw new CardServiceException("No terminal key");
                        }
                        sendMSESetATExtAuth(this.wrapper, Util.wrapDO(ISOFileInfo.FILE_IDENTIFIER, cardVerifiableCertificate2.getHolderReference().getName().getBytes(LocalizedMessage.DEFAULT_ENCODING)));
                        sendGetChallenge = sendGetChallenge(this.wrapper);
                        byte[] bArr2 = new byte[(str2.length() + 1)];
                        System.arraycopy(str2.getBytes(LocalizedMessage.DEFAULT_ENCODING), 0, bArr2, 0, str2.length());
                        bArr2[bArr2.length - 1] = (byte) MRZInfo.checkDigit(str2);
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        byteArrayOutputStream2.write(bArr2);
                        byteArrayOutputStream2.write(sendGetChallenge);
                        byteArrayOutputStream2.write(keyHash);
                        byteArrayOutputStream2.close();
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        String sigAlgName = cardVerifiableCertificate2.getSigAlgName();
                        if (sigAlgName == null) {
                            throw new IllegalStateException("ERROR: Could not determine signature algorithm for terminal certificate " + cardVerifiableCertificate2.getHolderReference().getName());
                        }
                        Signature instance = Signature.getInstance(sigAlgName);
                        instance.initSign(privateKey);
                        instance.update(byteArray);
                        byte[] sign = instance.sign();
                        if (sigAlgName.toUpperCase().endsWith("ECDSA")) {
                            bArr = Util.getRawECDSASignature(sign, ((ECPrivateKey) privateKey).getParameters().getCurve().getFieldSize() / 8);
                        } else {
                            bArr = sign;
                        }
                        sendMutualAuthenticate(this.wrapper, bArr);
                        this.state = 5;
                    } else {
                        throw new CardServiceException("First certificate not signed by expected CA, found " + authorityReference.getName() + ",  expected " + cVCPrincipal2.getName());
                    }
                }
            } catch (CardServiceException e) {
                throw e;
            } catch (Exception e2) {
                throw new CardServiceException(e2.getMessage());
            } catch (CardServiceException e3) {
                throw e3;
            } catch (Exception e4) {
                throw new CardServiceException(e4.toString());
            }
        }
        throw new IllegalArgumentException("Need at least 1 certificate to perform TA, found: " + list);
        return new TerminalAuthenticationResult(chipAuthenticationResult, authorityReference, list, privateKey, str2, sendGetChallenge);
    }

    public byte[] doAA(PublicKey publicKey, String str, String str2, byte[] bArr) throws CardServiceException {
        if (bArr != null) {
            try {
                if (bArr.length == 8) {
                    return sendInternalAuthenticate(this.wrapper, bArr);
                }
            } catch (IllegalArgumentException e) {
                LOGGER.severe("Exception: " + e.getMessage());
                throw new CardServiceException(e.toString());
            }
        }
        throw new IllegalArgumentException("AA failed: bad challenge");
    }

    public void close() {
        try {
            this.wrapper = null;
            super.close();
        } finally {
            this.state = 0;
        }
    }

    public APDUWrapper getWrapper() {
        return this.wrapper;
    }

    public void setWrapper(SecureMessagingWrapper secureMessagingWrapper) {
        this.wrapper = secureMessagingWrapper;
    }

    public synchronized CardFileInputStream getInputStream(short s) throws CardServiceException {
        CardFileInputStream cardFileInputStream;
        synchronized (this.f6332fs) {
            this.f6332fs.selectFile(s);
            cardFileInputStream = new CardFileInputStream(maxBlockSize, this.f6332fs);
        }
        return cardFileInputStream;
    }

    private static byte[] computeKeySeedForBAC(BACKeySpec bACKeySpec) throws GeneralSecurityException {
        String documentNumber = bACKeySpec.getDocumentNumber();
        String dateOfBirth = bACKeySpec.getDateOfBirth();
        String dateOfExpiry = bACKeySpec.getDateOfExpiry();
        if (dateOfBirth == null || dateOfBirth.length() != 6) {
            throw new IllegalArgumentException("Wrong date format used for date of birth. Expected yyMMdd, found " + dateOfBirth);
        } else if (dateOfExpiry == null || dateOfExpiry.length() != 6) {
            throw new IllegalArgumentException("Wrong date format used for date of expiry. Expected yyMMdd, found " + dateOfExpiry);
        } else if (documentNumber != null) {
            return Util.computeKeySeedForBAC(fixDocumentNumber(documentNumber), dateOfBirth, dateOfExpiry);
        } else {
            throw new IllegalArgumentException("Wrong document number. Found " + documentNumber);
        }
    }

    private static byte[] computeKeySeedForPACE(BACKeySpec bACKeySpec) throws GeneralSecurityException {
        String documentNumber = bACKeySpec.getDocumentNumber();
        String dateOfBirth = bACKeySpec.getDateOfBirth();
        String dateOfExpiry = bACKeySpec.getDateOfExpiry();
        if (dateOfBirth == null || dateOfBirth.length() != 6) {
            throw new IllegalArgumentException("Wrong date format used for date of birth. Expected yyMMdd, found " + dateOfBirth);
        } else if (dateOfExpiry == null || dateOfExpiry.length() != 6) {
            throw new IllegalArgumentException("Wrong date format used for date of expiry. Expected yyMMdd, found " + dateOfExpiry);
        } else if (documentNumber != null) {
            return Util.computeKeySeedForPACE(fixDocumentNumber(documentNumber), dateOfBirth, dateOfExpiry);
        } else {
            throw new IllegalArgumentException("Wrong document number. Found " + documentNumber);
        }
    }

    private static String fixDocumentNumber(String str) {
        String replace = str.replace('<', ' ').trim().replace(' ', '<');
        while (replace.length() < 9) {
            replace = replace + "<";
        }
        return replace;
    }
}
