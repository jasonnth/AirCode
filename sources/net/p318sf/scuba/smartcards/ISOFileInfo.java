package net.p318sf.scuba.smartcards;

import java.math.BigInteger;
import net.p318sf.scuba.util.Hex;
import org.jmrtd.cbeff.ISO781611;

/* renamed from: net.sf.scuba.smartcards.ISOFileInfo */
public class ISOFileInfo extends FileInfo {

    /* renamed from: A0 */
    public static final byte f6307A0 = -96;

    /* renamed from: A1 */
    public static final byte f6308A1 = -95;

    /* renamed from: A2 */
    public static final byte f6309A2 = -94;

    /* renamed from: A5 */
    public static final byte f6310A5 = -91;

    /* renamed from: AB */
    public static final byte f6311AB = -85;

    /* renamed from: AC */
    public static final byte f6312AC = -84;
    public static final byte CHANNEL_SECURITY = -114;
    public static final byte DATA_BYTES1 = Byte.MIN_VALUE;
    public static final byte DATA_BYTES2 = -127;
    public static final byte DF_NAME = -124;
    public static final byte ENV_TEMP_EF = -115;
    public static final byte FCI_BYTE = 111;
    public static final byte FCI_EXT = -121;
    public static final byte FCP_BYTE = 98;
    public static final byte FILE_DESCRIPTOR = -126;
    public static final byte FILE_IDENTIFIER = -125;
    public static final byte FMD_BYTE = 100;
    public static final byte LCS_BYTE = -118;
    public static final byte PROP_INFO = -123;
    public static final byte SECURITY_ATTR_COMPACT = -116;
    public static final byte SECURITY_ATTR_EXP = -117;
    public static final byte SECURITY_ATTR_PROP = -122;
    public static final byte SHORT_EF = -120;

    /* renamed from: a0 */
    byte[] f6313a0 = null;

    /* renamed from: a1 */
    byte[] f6314a1 = null;

    /* renamed from: a2 */
    byte[] f6315a2 = null;

    /* renamed from: a5 */
    byte[] f6316a5 = null;

    /* renamed from: ab */
    byte[] f6317ab = null;

    /* renamed from: ac */
    byte[] f6318ac = null;
    byte channelSecurity = -1;
    byte dataCodingByte = -1;
    byte descriptorByte = -1;
    byte[] dfName = null;
    short envTempEF = -1;
    short fciExt = -1;
    short fid = -1;
    int fileLength = -1;
    int fileLengthFCI = -1;
    byte lcsByte = -1;
    byte mainTag = -1;
    short maxRecordSize = -1;
    short maxRecordsCount = -1;
    byte[] propInfo = null;
    byte[] secAttrCompact = null;
    byte[] secAttrExp = null;
    byte[] secAttrProp = null;
    byte shortEF = -1;

    public ISOFileInfo(byte[] bArr) throws CardServiceException {
        int i;
        int i2 = 0;
        if (bArr.length != 0) {
            if (bArr[0] == 111 || bArr[0] == 98 || bArr[0] == 100) {
                this.mainTag = bArr[0];
                byte[] bArr2 = new byte[bArr[1]];
                System.arraycopy(bArr, 2, bArr2, 0, bArr[1]);
                while (i2 < bArr2.length) {
                    try {
                        int i3 = i2 + 1;
                        byte b = bArr2[i2];
                        int i4 = i3 + 1;
                        byte b2 = bArr2[i3];
                        byte[] bArr3 = new byte[b2];
                        System.arraycopy(bArr2, i4, bArr3, 0, b2);
                        i2 = i4 + b2;
                        switch (b) {
                            case Byte.MIN_VALUE:
                                this.fileLength = new BigInteger(bArr3).abs().intValue();
                                break;
                            case -127:
                                checkLen(b2, 2);
                                this.fileLengthFCI = new BigInteger(bArr3).intValue();
                                break;
                            case -126:
                                checkLen(b2, 1, 6);
                                this.descriptorByte = bArr3[0];
                                if (1 == bArr3.length) {
                                    break;
                                } else {
                                    this.dataCodingByte = bArr3[1];
                                    if (2 != bArr3.length) {
                                        if (bArr3.length == 3) {
                                            i = 3;
                                            this.maxRecordSize = (short) bArr3[2];
                                        } else {
                                            i = 4;
                                            this.maxRecordSize = new BigInteger(new byte[]{bArr3[2], bArr3[3]}).shortValue();
                                        }
                                        if (i != bArr3.length) {
                                            if (bArr3.length != 5) {
                                                int i5 = i + 1;
                                                int i6 = i5 + 1;
                                                this.maxRecordsCount = new BigInteger(new byte[]{bArr3[i], bArr3[i5]}).shortValue();
                                                break;
                                            } else {
                                                int i7 = i + 1;
                                                this.maxRecordsCount = (short) bArr3[i];
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            case -125:
                                checkLen(b2, 2);
                                this.fid = new BigInteger(bArr3).shortValue();
                                break;
                            case -124:
                                checkLen(b2, 0, 16);
                                this.dfName = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.dfName, 0, bArr3.length);
                                break;
                            case -123:
                                this.propInfo = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.propInfo, 0, bArr3.length);
                                break;
                            case -122:
                                this.secAttrProp = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.secAttrProp, 0, bArr3.length);
                                break;
                            case -121:
                                checkLen(b2, 2);
                                this.fciExt = new BigInteger(bArr3).shortValue();
                                break;
                            case -120:
                                checkLen(b2, 0, 1);
                                if (b2 != 0) {
                                    this.shortEF = bArr3[0];
                                    break;
                                } else {
                                    this.shortEF = 0;
                                    break;
                                }
                            case -118:
                                checkLen(b2, 1);
                                this.lcsByte = bArr3[0];
                                break;
                            case -117:
                                this.secAttrExp = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.secAttrExp, 0, bArr3.length);
                                break;
                            case -116:
                                this.secAttrCompact = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.secAttrCompact, 0, bArr3.length);
                                break;
                            case -115:
                                checkLen(b2, 2);
                                this.envTempEF = new BigInteger(bArr3).shortValue();
                                break;
                            case -114:
                                checkLen(b2, 1);
                                this.channelSecurity = bArr3[0];
                                break;
                            case -96:
                                this.f6313a0 = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.f6313a0, 0, bArr3.length);
                                break;
                            case ISO781611.BIOMETRIC_HEADER_TEMPLATE_BASE_TAG /*-95*/:
                                this.f6314a1 = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.f6314a1, 0, bArr3.length);
                                break;
                            case -94:
                                this.f6315a2 = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.f6315a2, 0, bArr3.length);
                                break;
                            case -91:
                                this.f6316a5 = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.f6316a5, 0, bArr3.length);
                                break;
                            case -85:
                                this.f6317ab = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.f6317ab, 0, bArr3.length);
                                break;
                            case -84:
                                this.f6318ac = new byte[bArr3.length];
                                System.arraycopy(bArr3, 0, this.f6318ac, 0, bArr3.length);
                                break;
                            default:
                                throw new CardServiceException("Malformed FCI: unrecognized tag.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new CardServiceException("Malformed FCI.");
                    }
                }
                return;
            }
            throw new CardServiceException("Malformed FCI data");
        }
    }

    private static byte[] catArray(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private static void checkLen(int i, int i2) throws CardServiceException {
        if (i != i2) {
            throw new CardServiceException("Malformed FCI.");
        }
    }

    private static void checkLen(int i, int i2, int i3) throws CardServiceException {
        if (i < i2 || i > i3) {
            throw new CardServiceException("Malformed FCI.");
        }
    }

    private static byte[] getArray(byte b, byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length + 2)];
        bArr2[0] = b;
        bArr2[1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        return bArr2;
    }

    public short getFID() {
        return this.fid;
    }

    public int getFileLength() {
        return this.fileLength;
    }

    public byte[] getFormatted() {
        byte[] bArr = new byte[0];
        if (this.mainTag == -1) {
            return bArr;
        }
        if (this.fileLength != -1) {
            bArr = catArray(bArr, getArray(DATA_BYTES1, Hex.hexStringToBytes(Hex.shortToHexString((short) this.fileLength))));
        }
        if (this.fileLengthFCI != -1) {
            bArr = catArray(bArr, getArray(DATA_BYTES2, Hex.hexStringToBytes(Hex.shortToHexString((short) this.fileLengthFCI))));
        }
        if (this.descriptorByte != -1) {
            byte[] bArr2 = {this.descriptorByte};
            byte[] bArr3 = new byte[0];
            if (this.dataCodingByte != -1) {
                bArr3 = new byte[]{this.dataCodingByte};
            }
            byte[] bArr4 = new byte[0];
            if (this.maxRecordSize != -1) {
                String shortToHexString = this.maxRecordSize <= 256 ? this.maxRecordsCount == -1 ? Hex.byteToHexString((byte) this.maxRecordSize) : Hex.shortToHexString(this.maxRecordSize) : Hex.shortToHexString(this.maxRecordSize);
                bArr4 = Hex.hexStringToBytes(shortToHexString);
            }
            byte[] bArr5 = new byte[0];
            if (this.maxRecordsCount != -1) {
                bArr5 = Hex.hexStringToBytes(this.maxRecordsCount <= 256 ? Hex.byteToHexString((byte) this.maxRecordsCount) : Hex.shortToHexString(this.maxRecordsCount));
            }
            bArr = catArray(bArr, getArray(-126, catArray(catArray(catArray(bArr2, bArr3), bArr4), bArr5)));
        }
        if (this.fid != -1) {
            bArr = catArray(bArr, getArray(FILE_IDENTIFIER, Hex.hexStringToBytes(Hex.shortToHexString(this.fid))));
        }
        if (this.dfName != null) {
            bArr = catArray(bArr, getArray(-124, this.dfName));
        }
        if (this.propInfo != null) {
            bArr = catArray(bArr, getArray(PROP_INFO, this.propInfo));
        }
        if (this.secAttrProp != null) {
            bArr = catArray(bArr, getArray(-122, this.secAttrProp));
        }
        if (this.fciExt != -1) {
            bArr = catArray(bArr, getArray(FCI_EXT, Hex.hexStringToBytes(Hex.shortToHexString(this.fciExt))));
        }
        if (this.shortEF != -1) {
            bArr = catArray(bArr, getArray(-120, this.shortEF == 0 ? new byte[0] : new byte[]{this.shortEF}));
        }
        if (this.lcsByte != -1) {
            bArr = catArray(bArr, getArray(LCS_BYTE, new byte[]{this.lcsByte}));
        }
        if (this.secAttrExp != null) {
            bArr = catArray(bArr, getArray(SECURITY_ATTR_EXP, this.secAttrExp));
        }
        if (this.secAttrCompact != null) {
            bArr = catArray(bArr, getArray(SECURITY_ATTR_COMPACT, this.secAttrCompact));
        }
        if (this.envTempEF != -1) {
            bArr = catArray(bArr, getArray(ENV_TEMP_EF, Hex.hexStringToBytes(Hex.shortToHexString(this.envTempEF))));
        }
        if (this.channelSecurity != -1) {
            bArr = catArray(bArr, getArray(CHANNEL_SECURITY, new byte[]{this.channelSecurity}));
        }
        if (this.f6313a0 != null) {
            bArr = catArray(bArr, getArray(f6307A0, this.f6313a0));
        }
        if (this.f6314a1 != null) {
            bArr = catArray(bArr, getArray(f6308A1, this.f6314a1));
        }
        if (this.f6315a2 != null) {
            bArr = catArray(bArr, getArray(-94, this.f6315a2));
        }
        if (this.f6316a5 != null) {
            bArr = catArray(bArr, getArray(f6310A5, this.f6316a5));
        }
        if (this.f6317ab != null) {
            bArr = catArray(bArr, getArray(f6311AB, this.f6317ab));
        }
        if (this.f6318ac != null) {
            bArr = catArray(bArr, getArray(-84, this.f6318ac));
        }
        return getArray(this.mainTag, bArr);
    }

    public String toString() {
        return "Length: " + this.fileLength + "\n" + "Length FCI: " + this.fileLengthFCI + "\n" + "Desc byte: " + this.descriptorByte + "\n" + "Data byte: " + this.dataCodingByte + "\n" + "Record size: " + this.maxRecordSize + "\n" + "Record count: " + this.maxRecordsCount + "\n" + "FID: " + Hex.shortToHexString(this.fid) + "\n" + "DF name: " + Hex.bytesToHexString(this.dfName) + "\n" + "propInfo: " + Hex.bytesToHexString(this.propInfo) + "\n" + "secAttrProp: " + Hex.bytesToHexString(this.secAttrProp) + "\n" + "secAttrExp: " + Hex.bytesToHexString(this.secAttrExp) + "\n" + "secAttrComp: " + Hex.bytesToHexString(this.secAttrCompact) + "\n" + "FCI ext: " + Hex.shortToHexString(this.fciExt) + "\n" + "EF env temp: " + Hex.shortToHexString(this.envTempEF) + "\n" + "Short EF: " + Hex.byteToHexString(this.shortEF) + "\n" + "LCS byte: " + Hex.byteToHexString(this.lcsByte) + "\n" + "Channel sec: " + Hex.byteToHexString(this.channelSecurity) + "\n" + "a0: " + Hex.bytesToHexString(this.f6313a0) + "\n" + "a1: " + Hex.bytesToHexString(this.f6314a1) + "\n" + "a2: " + Hex.bytesToHexString(this.f6315a2) + "\n" + "a5: " + Hex.bytesToHexString(this.f6316a5) + "\n" + "ab: " + Hex.bytesToHexString(this.f6317ab) + "\n" + "ac: " + Hex.bytesToHexString(this.f6318ac) + "\n";
    }
}
