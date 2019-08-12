package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.tlv.TLVOutputStream;

public class COMFile extends DataGroup {
    private static final int TAG_LIST_TAG = 92;
    private static final int VERSION_LDS_TAG = 24321;
    private static final int VERSION_UNICODE_TAG = 24374;
    private static final long serialVersionUID = 2002455279067170063L;
    private String majorVersionUnicode;
    private String minorVersionUnicode;
    private String releaseLevelUnicode;
    private List<Integer> tagList;
    private String updateLevelLDS;
    private String versionLDS;

    public COMFile(String str, String str2, String str3, String str4, String str5, int[] iArr) {
        super(96);
        initialize(str, str2, str3, str4, str5, iArr);
    }

    public COMFile(String str, String str2, int[] iArr) {
        super(96);
        if (str == null) {
            try {
                throw new IllegalArgumentException("Null versionLDS");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Could not parse version number. " + e.getMessage());
            } catch (IllegalFormatConversionException e2) {
                throw new IllegalArgumentException("Could not parse version number. " + e2.getMessage());
            }
        } else if (str2 == null) {
            throw new IllegalArgumentException("Null versionUnicode");
        } else {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
            if (stringTokenizer.countTokens() != 2) {
                throw new IllegalArgumentException("Could not parse LDS version. Expecting 2 level version number x.y.");
            }
            Integer valueOf = Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken().trim()));
            Integer valueOf2 = Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken().trim()));
            StringTokenizer stringTokenizer2 = new StringTokenizer(str2, ".");
            if (stringTokenizer2.countTokens() != 3) {
                throw new IllegalArgumentException("Could not parse unicode version. Expecting 3 level version number x.y.z.");
            }
            Integer valueOf3 = Integer.valueOf(Integer.parseInt(stringTokenizer2.nextToken().trim()));
            Integer valueOf4 = Integer.valueOf(Integer.parseInt(stringTokenizer2.nextToken().trim()));
            Integer valueOf5 = Integer.valueOf(Integer.parseInt(stringTokenizer2.nextToken().trim()));
            initialize(String.format("%02d", new Object[]{valueOf}), String.format("%02d", new Object[]{valueOf2}), String.format("%02d", new Object[]{valueOf3}), String.format("%02d", new Object[]{valueOf4}), String.format("%02d", new Object[]{valueOf5}), iArr);
        }
    }

    private void initialize(String str, String str2, String str3, String str4, String str5, int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("Null tag list");
        } else if (str == null || str.length() != 2 || str2 == null || str2.length() != 2 || str3 == null || str3.length() != 2 || str4 == null || str4.length() != 2 || str5 == null || str5.length() != 2 || iArr == null) {
            throw new IllegalArgumentException();
        } else {
            this.versionLDS = str;
            this.updateLevelLDS = str2;
            this.majorVersionUnicode = str3;
            this.minorVersionUnicode = str4;
            this.releaseLevelUnicode = str5;
            this.tagList = new ArrayList(iArr.length);
            for (int valueOf : iArr) {
                this.tagList.add(Integer.valueOf(valueOf));
            }
        }
    }

    public COMFile(InputStream inputStream) throws IOException {
        super(96, inputStream);
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        if (readTag != VERSION_LDS_TAG) {
            throw new IllegalArgumentException("Excepected VERSION_LDS_TAG (" + Integer.toHexString(VERSION_LDS_TAG) + "), found " + Integer.toHexString(readTag));
        } else if (tLVInputStream.readLength() != 4) {
            throw new IllegalArgumentException("Wrong length of LDS version object");
        } else {
            byte[] readValue = tLVInputStream.readValue();
            this.versionLDS = new String(readValue, 0, 2);
            this.updateLevelLDS = new String(readValue, 2, 2);
            int readTag2 = tLVInputStream.readTag();
            if (readTag2 != 24374) {
                throw new IllegalArgumentException("Expected VERSION_UNICODE_TAG (" + Integer.toHexString(24374) + "), found " + Integer.toHexString(readTag2));
            } else if (tLVInputStream.readLength() != 6) {
                throw new IllegalArgumentException("Wrong length of LDS version object");
            } else {
                byte[] readValue2 = tLVInputStream.readValue();
                this.majorVersionUnicode = new String(readValue2, 0, 2);
                this.minorVersionUnicode = new String(readValue2, 2, 2);
                this.releaseLevelUnicode = new String(readValue2, 4, 2);
                int readTag3 = tLVInputStream.readTag();
                if (readTag3 != 92) {
                    throw new IllegalArgumentException("Expected TAG_LIST_TAG (" + Integer.toHexString(92) + "), found " + Integer.toHexString(readTag3));
                }
                tLVInputStream.readLength();
                byte[] readValue3 = tLVInputStream.readValue();
                this.tagList = new ArrayList();
                for (byte b : readValue3) {
                    this.tagList.add(Integer.valueOf(b & 255));
                }
            }
        }
    }

    public String getLDSVersion() {
        String str = this.versionLDS + "." + this.updateLevelLDS;
        try {
            int parseInt = Integer.parseInt(this.versionLDS);
            return parseInt + "." + Integer.parseInt(this.updateLevelLDS);
        } catch (NumberFormatException e) {
            return str;
        }
    }

    public String getUnicodeVersion() {
        String str = this.majorVersionUnicode + "." + this.minorVersionUnicode + "." + this.releaseLevelUnicode;
        try {
            int parseInt = Integer.parseInt(this.majorVersionUnicode);
            int parseInt2 = Integer.parseInt(this.minorVersionUnicode);
            return parseInt + "." + parseInt2 + "." + Integer.parseInt(this.releaseLevelUnicode);
        } catch (NumberFormatException e) {
            return str;
        }
    }

    public int[] getTagList() {
        int[] iArr = new int[this.tagList.size()];
        int i = 0;
        for (Integer intValue : this.tagList) {
            int i2 = i + 1;
            iArr[i] = intValue.intValue();
            i = i2;
        }
        return iArr;
    }

    public void insertTag(Integer num) {
        if (!this.tagList.contains(num)) {
            this.tagList.add(num);
            Collections.sort(this.tagList);
        }
    }

    /* access modifiers changed from: protected */
    public void writeContent(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream;
        if (outputStream instanceof TLVOutputStream) {
            tLVOutputStream = (TLVOutputStream) outputStream;
        } else {
            tLVOutputStream = new TLVOutputStream(outputStream);
        }
        tLVOutputStream.writeTag(VERSION_LDS_TAG);
        tLVOutputStream.writeValue((this.versionLDS + this.updateLevelLDS).getBytes());
        tLVOutputStream.writeTag(24374);
        tLVOutputStream.writeValue((this.majorVersionUnicode + this.minorVersionUnicode + this.releaseLevelUnicode).getBytes());
        tLVOutputStream.writeTag(92);
        tLVOutputStream.writeLength(this.tagList.size());
        for (Integer intValue : this.tagList) {
            tLVOutputStream.write((int) (byte) intValue.intValue());
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("COMFile ");
        stringBuffer.append("LDS " + this.versionLDS + "." + this.updateLevelLDS);
        stringBuffer.append(", ");
        stringBuffer.append("Unicode " + this.majorVersionUnicode + "." + this.minorVersionUnicode + "." + this.releaseLevelUnicode);
        stringBuffer.append(", ");
        int i = 0;
        stringBuffer.append("[");
        int size = this.tagList.size();
        Iterator it = this.tagList.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                stringBuffer.append("DG" + LDSFileUtil.lookupDataGroupNumberByTag(((Integer) it.next()).intValue()));
                if (i2 < size - 1) {
                    stringBuffer.append(", ");
                }
                i = i2 + 1;
            } else {
                stringBuffer.append("]");
                return stringBuffer.toString();
            }
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        COMFile cOMFile = (COMFile) obj;
        if (!this.versionLDS.equals(cOMFile.versionLDS) || !this.updateLevelLDS.equals(cOMFile.updateLevelLDS) || !this.majorVersionUnicode.equals(cOMFile.majorVersionUnicode) || !this.minorVersionUnicode.equals(cOMFile.minorVersionUnicode) || !this.releaseLevelUnicode.equals(cOMFile.releaseLevelUnicode) || !this.tagList.equals(cOMFile.tagList)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (this.versionLDS.hashCode() * 3) + (this.updateLevelLDS.hashCode() * 5) + (this.majorVersionUnicode.hashCode() * 7) + (this.minorVersionUnicode.hashCode() * 11) + (this.releaseLevelUnicode.hashCode() * 13) + (this.tagList.hashCode() * 17);
    }
}
