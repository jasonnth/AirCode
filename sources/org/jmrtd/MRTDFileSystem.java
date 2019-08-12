package org.jmrtd;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import net.p318sf.scuba.smartcards.CardServiceException;
import net.p318sf.scuba.smartcards.FileInfo;
import net.p318sf.scuba.smartcards.FileSystemStructured;
import net.p318sf.scuba.tlv.TLVInputStream;
import org.jmrtd.p321io.FragmentBuffer;
import org.jmrtd.p321io.FragmentBuffer.Fragment;

class MRTDFileSystem implements Serializable, FileSystemStructured {
    static final /* synthetic */ boolean $assertionsDisabled = (!MRTDFileSystem.class.desiredAssertionStatus());
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final int READ_AHEAD_LENGTH = 8;
    private static final long serialVersionUID = -4357282016708205020L;
    private Map<Short, MRTDFileInfo> fileInfos = new HashMap();
    private boolean isSelected = false;
    private short selectedFID = 0;
    private PassportService service;

    static class MRTDFileInfo extends FileInfo implements Serializable {
        private static final long serialVersionUID = 6727369753765119839L;
        private FragmentBuffer buffer;
        private short fid;

        public MRTDFileInfo(short s, int i) {
            this.fid = s;
            this.buffer = new FragmentBuffer(i);
        }

        public byte[] getBuffer() {
            return this.buffer.getBuffer();
        }

        public short getFID() {
            return this.fid;
        }

        public int getFileLength() {
            return this.buffer.getLength();
        }

        public String toString() {
            return Integer.toHexString(this.fid);
        }

        public Fragment getSmallestUnbufferedFragment(int i, int i2) {
            return this.buffer.getSmallestUnbufferedFragment(i, i2);
        }

        public void addFragment(int i, byte[] bArr) {
            this.buffer.addFragment(i, bArr);
        }
    }

    public MRTDFileSystem(PassportService passportService) {
        this.service = passportService;
    }

    public synchronized FileInfo[] getSelectedPath() throws CardServiceException {
        MRTDFileInfo fileInfo;
        fileInfo = getFileInfo();
        return fileInfo == null ? null : new MRTDFileInfo[]{fileInfo};
    }

    public synchronized void selectFile(short s) throws CardServiceException {
        if (this.selectedFID != s) {
            this.selectedFID = s;
            this.isSelected = false;
        }
    }

    public synchronized byte[] readBinary(int i, int i2) throws CardServiceException {
        Object obj;
        Object obj2;
        byte[] bArr;
        boolean z = true;
        synchronized (this) {
            Object obj3 = 0;
            try {
                obj2 = obj3;
                obj = obj3;
                if (this.selectedFID <= 0) {
                    throw new CardServiceException("No file selected");
                }
                if (i <= 32767) {
                    z = false;
                }
                obj2 = obj3;
                obj = obj3;
                if (!this.isSelected) {
                    this.service.sendSelectFile(this.selectedFID);
                    this.isSelected = true;
                }
                MRTDFileInfo hexString = getFileInfo();
                if ($assertionsDisabled || hexString != 0) {
                    obj2 = hexString;
                    obj = hexString;
                    Fragment smallestUnbufferedFragment = hexString.getSmallestUnbufferedFragment(i, i2);
                    if (smallestUnbufferedFragment.getLength() > 0) {
                        hexString.addFragment(smallestUnbufferedFragment.getOffset(), this.service.sendReadBinary(smallestUnbufferedFragment.getOffset(), smallestUnbufferedFragment.getLength(), z));
                    }
                    bArr = new byte[i2];
                    System.arraycopy(hexString.getBuffer(), i, bArr, 0, i2);
                } else {
                    throw new AssertionError();
                }
            } catch (CardServiceException e) {
                StringBuilder append = new StringBuilder().append("Read binary failed on file ");
                if (obj2 == 0) {
                    obj2 = Integer.toHexString(this.selectedFID);
                }
                throw new CardServiceException(append.append(obj2).append(": ").append(e.getMessage()).toString(), e.getSW());
            } catch (Exception e2) {
                StringBuilder append2 = new StringBuilder().append("Read binary failed on file ");
                if (obj == 0) {
                    obj = Integer.toHexString(this.selectedFID);
                }
                throw new CardServiceException(append2.append(obj).toString());
            }
        }
        return bArr;
    }

    private synchronized MRTDFileInfo getFileInfo() throws CardServiceException {
        MRTDFileInfo mRTDFileInfo;
        int length;
        if (this.selectedFID <= 0) {
            throw new CardServiceException("No file selected");
        }
        mRTDFileInfo = (MRTDFileInfo) this.fileInfos.get(Short.valueOf(this.selectedFID));
        if (mRTDFileInfo == null) {
            try {
                if (!this.isSelected) {
                    this.service.sendSelectFile(this.selectedFID);
                    this.isSelected = true;
                }
                byte[] sendReadBinary = this.service.sendReadBinary(0, 8, false);
                if (sendReadBinary == null || sendReadBinary.length != 8) {
                    LOGGER.severe("Something is wrong with prefix, prefix = " + Arrays.toString(sendReadBinary));
                    mRTDFileInfo = null;
                } else {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sendReadBinary);
                    TLVInputStream tLVInputStream = new TLVInputStream(byteArrayInputStream);
                    if (tLVInputStream.readTag() == 66) {
                        length = 36;
                    } else {
                        length = (sendReadBinary.length - byteArrayInputStream.available()) + tLVInputStream.readLength();
                    }
                    mRTDFileInfo = new MRTDFileInfo(this.selectedFID, length);
                    mRTDFileInfo.addFragment(0, sendReadBinary);
                    this.fileInfos.put(Short.valueOf(this.selectedFID), mRTDFileInfo);
                }
            } catch (IOException e) {
                throw new CardServiceException(e.toString() + " getting file info for " + Integer.toHexString(this.selectedFID));
            }
        }
        return mRTDFileInfo;
    }
}
