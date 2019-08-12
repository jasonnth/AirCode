package p315io.branch.referral;

import com.facebook.common.util.UriUtil;

/* renamed from: io.branch.referral.ApkParser */
class ApkParser {
    public static int endDocTag = 1048833;
    public static int endTag = 1048835;
    public static int startTag = 1048834;

    ApkParser() {
    }

    public String decompressXML(byte[] xml) {
        String attrValue;
        int stOff = 36 + (LEW(xml, 16) * 4);
        int xmlTagOff = LEW(xml, 12);
        int ii = xmlTagOff;
        while (true) {
            if (ii >= xml.length - 4) {
                break;
            } else if (LEW(xml, ii) == startTag) {
                xmlTagOff = ii;
                break;
            } else {
                ii += 4;
            }
        }
        int off = xmlTagOff;
        while (true) {
            if (off >= xml.length) {
                break;
            }
            int tag0 = LEW(xml, off);
            if (tag0 == startTag) {
                int numbAttrs = LEW(xml, off + 28);
                off += 36;
                for (int ii2 = 0; ii2 < numbAttrs; ii2++) {
                    int attrNameSi = LEW(xml, off + 4);
                    int attrValueSi = LEW(xml, off + 8);
                    int attrResId = LEW(xml, off + 16);
                    off += 20;
                    if (compXmlString(xml, 36, stOff, attrNameSi).equals("scheme")) {
                        if (attrValueSi != -1) {
                            attrValue = compXmlString(xml, 36, stOff, attrValueSi);
                        } else {
                            attrValue = "resourceID 0x" + Integer.toHexString(attrResId);
                        }
                        if (validURI(attrValue)) {
                            return attrValue;
                        }
                    }
                }
                continue;
            } else if (tag0 == endTag) {
                off += 24;
            } else if (tag0 == endDocTag) {
            }
        }
        return "bnc_no_value";
    }

    private boolean validURI(String value) {
        if (value == null || value.equals(UriUtil.HTTP_SCHEME) || value.equals(UriUtil.HTTPS_SCHEME) || value.equals("geo") || value.equals("*") || value.equals("package") || value.equals("sms") || value.equals("smsto") || value.equals("mms") || value.equals("mmsto") || value.equals("tel") || value.equals("voicemail") || value.equals(UriUtil.LOCAL_FILE_SCHEME) || value.equals("content") || value.equals("mailto")) {
            return false;
        }
        return true;
    }

    public String compXmlString(byte[] xml, int sitOff, int stOff, int strInd) {
        if (strInd < 0) {
            return null;
        }
        return compXmlStringAt(xml, stOff + LEW(xml, (strInd * 4) + sitOff));
    }

    public String compXmlStringAt(byte[] arr, int strOff) {
        byte strLen = ((arr[strOff + 1] << 8) & 65280) | (arr[strOff] & 255);
        byte[] chars = new byte[strLen];
        for (int ii = 0; ii < strLen; ii++) {
            chars[ii] = arr[strOff + 2 + (ii * 2)];
        }
        return new String(chars);
    }

    public int LEW(byte[] arr, int off) {
        return ((arr[off + 3] << 24) & -16777216) | ((arr[off + 2] << 16) & 16711680) | ((arr[off + 1] << 8) & 65280) | (arr[off] & 255);
    }
}
