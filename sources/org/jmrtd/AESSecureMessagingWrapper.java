package org.jmrtd;

import com.facebook.soloader.MinElf;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import net.p318sf.scuba.smartcards.CommandAPDU;
import net.p318sf.scuba.smartcards.ResponseAPDU;
import net.p318sf.scuba.tlv.TLVUtil;
import org.spongycastle.asn1.eac.CertificateBody;

public class AESSecureMessagingWrapper extends SecureMessagingWrapper implements Serializable {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 2086301081448345496L;
    private transient Cipher cipher;
    private SecretKey ksEnc;
    private SecretKey ksMac;
    private transient Mac mac;
    private long ssc;
    private transient Cipher sscIVCipher = Cipher.getInstance("AES/ECB/NoPadding");

    public AESSecureMessagingWrapper(SecretKey secretKey, SecretKey secretKey2, long j) throws GeneralSecurityException {
        this.ksEnc = secretKey;
        this.ksMac = secretKey2;
        this.ssc = j;
        this.sscIVCipher.init(1, secretKey);
        this.cipher = Cipher.getInstance("AES/CBC/NoPadding");
        this.mac = Mac.getInstance("AESCMAC");
        this.mac.init(secretKey2);
    }

    public long getSendSequenceCounter() {
        return this.ssc;
    }

    public CommandAPDU wrap(CommandAPDU commandAPDU) {
        try {
            return wrapCommandAPDU(commandAPDU);
        } catch (GeneralSecurityException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalStateException(e.toString());
        } catch (IOException e2) {
            LOGGER.severe("Exception: " + e2.getMessage());
            throw new IllegalStateException(e2.toString());
        }
    }

    public ResponseAPDU unwrap(ResponseAPDU responseAPDU, int i) {
        try {
            byte[] bytes = responseAPDU.getBytes();
            if (bytes.length != 2) {
                return new ResponseAPDU(unwrapResponseAPDU(bytes, i));
            }
            throw new IllegalStateException("Card indicates SM error, SW = " + Integer.toHexString(responseAPDU.getSW() & MinElf.PN_XNUM));
        } catch (GeneralSecurityException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalStateException(e.toString());
        } catch (IOException e2) {
            LOGGER.severe("Exception: " + e2.getMessage());
            throw new IllegalStateException(e2.toString());
        }
    }

    private CommandAPDU wrapCommandAPDU(CommandAPDU commandAPDU) throws GeneralSecurityException, IOException {
        byte[] bArr;
        int nc = commandAPDU.getNc();
        int ne = commandAPDU.getNe();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = {(byte) (commandAPDU.getCLA() | 12), (byte) commandAPDU.getINS(), (byte) commandAPDU.getP1(), (byte) commandAPDU.getP2()};
        byte[] padWithCAN = Util.padWithCAN(bArr2, 16);
        boolean z = ((byte) commandAPDU.getINS()) == -79;
        byte[] bArr3 = new byte[0];
        byte[] bArr4 = new byte[0];
        if (ne > 0) {
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(-105);
            byteArrayOutputStream.write(1);
            byteArrayOutputStream.write((byte) ne);
            bArr4 = byteArrayOutputStream.toByteArray();
        }
        this.ssc++;
        byte[] sSCAsBytes = getSSCAsBytes(this.ssc);
        if (nc > 0) {
            byte[] padWithCAN2 = Util.padWithCAN(commandAPDU.getData(), 16);
            this.cipher.init(1, this.ksEnc, getIV(sSCAsBytes));
            byte[] doFinal = this.cipher.doFinal(padWithCAN2);
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(z ? -123 : -121);
            byteArrayOutputStream.write(TLVUtil.getLengthAsBytes((z ? 0 : 1) + doFinal.length));
            if (!z) {
                byteArrayOutputStream.write(1);
            }
            byteArrayOutputStream.write(doFinal);
            bArr = byteArrayOutputStream.toByteArray();
        } else {
            bArr = bArr3;
        }
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(padWithCAN);
        byteArrayOutputStream.write(bArr);
        byteArrayOutputStream.write(bArr4);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(sSCAsBytes);
        byteArrayOutputStream.write(byteArray);
        byteArrayOutputStream.flush();
        byte[] padWithCAN3 = Util.padWithCAN(byteArrayOutputStream.toByteArray(), 16);
        this.mac.init(this.ksMac);
        byte[] doFinal2 = this.mac.doFinal(padWithCAN3);
        int length = doFinal2.length;
        if (length != 8) {
            length = 8;
        }
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(-114);
        byteArrayOutputStream.write(length);
        byteArrayOutputStream.write(doFinal2, 0, length);
        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(bArr);
        byteArrayOutputStream.write(bArr4);
        byteArrayOutputStream.write(byteArray2);
        return new CommandAPDU(bArr2[0], bArr2[1], bArr2[2], bArr2[3], byteArrayOutputStream.toByteArray(), 256);
    }

    private byte[] unwrapResponseAPDU(byte[] bArr, int i) throws GeneralSecurityException, IOException {
        boolean z = false;
        long j = this.ssc;
        if (bArr != null) {
            try {
                if (bArr.length >= 2 && i >= 2) {
                    this.ssc++;
                    this.cipher.init(2, this.ksEnc, getIV(getSSCAsBytes(this.ssc)));
                    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
                    byte[] bArr2 = new byte[0];
                    byte[] bArr3 = null;
                    short s = 0;
                    while (!z) {
                        switch (dataInputStream.readByte()) {
                            case -123:
                                bArr2 = readDO87(dataInputStream, true);
                                break;
                            case -121:
                                bArr2 = readDO87(dataInputStream, false);
                                break;
                            case -114:
                                bArr3 = readDO8E(dataInputStream);
                                z = true;
                                break;
                            case -103:
                                s = readDO99(dataInputStream);
                                break;
                        }
                    }
                    if (!checkMac(bArr, bArr3)) {
                        throw new IllegalStateException("Invalid MAC");
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byteArrayOutputStream.write(bArr2, 0, bArr2.length);
                    byteArrayOutputStream.write((65280 & s) >> 8);
                    byteArrayOutputStream.write(s & 255);
                    return byteArrayOutputStream.toByteArray();
                }
            } finally {
                if (this.ssc == j) {
                    this.ssc++;
                }
            }
        }
        throw new IllegalArgumentException("Invalid response APDU");
    }

    private byte[] readDO87(DataInputStream dataInputStream, boolean z) throws IOException, GeneralSecurityException {
        int i = 0;
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        if ((readUnsignedByte & 128) == 128) {
            int i2 = readUnsignedByte & CertificateBody.profileType;
            readUnsignedByte = 0;
            while (i < i2) {
                i++;
                readUnsignedByte = dataInputStream.readUnsignedByte() | (readUnsignedByte << 8);
            }
            if (!z && dataInputStream.readUnsignedByte() != 1) {
                throw new IllegalStateException("DO'87 expected 0x01 marker");
            }
        } else if (!z) {
            int readUnsignedByte2 = dataInputStream.readUnsignedByte();
            if (readUnsignedByte2 != 1) {
                throw new IllegalStateException("DO'87 expected 0x01 marker, found " + Integer.toHexString(readUnsignedByte2 & 255));
            }
        }
        if (!z) {
            readUnsignedByte--;
        }
        byte[] bArr = new byte[readUnsignedByte];
        dataInputStream.readFully(bArr);
        return Util.unpad(this.cipher.doFinal(bArr));
    }

    private short readDO99(DataInputStream dataInputStream) throws IOException {
        if (dataInputStream.readUnsignedByte() != 2) {
            throw new IllegalStateException("DO'99 wrong length");
        }
        return (short) (((dataInputStream.readByte() & 255) << 8) | (dataInputStream.readByte() & 255));
    }

    private byte[] readDO8E(DataInputStream dataInputStream) throws IOException, GeneralSecurityException {
        if (dataInputStream.readUnsignedByte() != 8) {
            throw new IllegalStateException("DO'8E wrong length");
        }
        byte[] bArr = new byte[8];
        dataInputStream.readFully(bArr);
        return bArr;
    }

    private boolean checkMac(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return true;
    }

    private IvParameterSpec getIV(byte[] bArr) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return new IvParameterSpec(this.sscIVCipher.doFinal(bArr));
    }

    private byte[] getSSCAsBytes(long j) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeLong(j);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.warning("Exception: " + e.getMessage());
            return null;
        }
    }
}
