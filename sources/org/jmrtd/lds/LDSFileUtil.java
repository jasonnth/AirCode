package org.jmrtd.lds;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jmrtd.PassportService;
import org.spongycastle.crypto.tls.AlertDescription;
import p005cn.jpush.android.Configs;

public class LDSFileUtil {
    public static AbstractLDSFile getLDSFile(short s, InputStream inputStream) throws IOException {
        switch (s) {
            case 257:
                return new DG1File(inputStream);
            case 258:
                return new DG2File(inputStream);
            case 259:
                return new DG3File(inputStream);
            case Configs.BUILD_ID_BASE /*260*/:
                return new DG4File(inputStream);
            case 261:
                return new DG5File(inputStream);
            case 262:
                return new DG6File(inputStream);
            case 263:
                return new DG7File(inputStream);
            case 264:
                throw new IllegalArgumentException("DG8 files are not yet supported");
            case 265:
                throw new IllegalArgumentException("DG9 files are not yet supported");
            case 266:
                throw new IllegalArgumentException("DG10 files are not yet supported");
            case 267:
                return new DG11File(inputStream);
            case 268:
                return new DG12File(inputStream);
            case 269:
                throw new IllegalArgumentException("DG13 files are not yet supported");
            case MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS /*270*/:
                return new DG14File(inputStream);
            case 271:
                return new DG15File(inputStream);
            case HelpCenterArticle.SET_RESERVATION_REQUIREMENTS /*272*/:
                throw new IllegalArgumentException("DG16 files are not yet supported");
            case 284:
                return new CVCAFile(inputStream);
            case 285:
                return new SODFile(inputStream);
            case 286:
                return new COMFile(inputStream);
            default:
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 37);
                try {
                    bufferedInputStream.mark(37);
                    return new CVCAFile(s, (InputStream) bufferedInputStream);
                } catch (Exception e) {
                    bufferedInputStream.reset();
                    throw new NumberFormatException("Unknown file " + Integer.toHexString(s));
                }
        }
    }

    public static short lookupFIDByTag(int i) {
        switch (i) {
            case 96:
                return PassportService.EF_COM;
            case 97:
                return PassportService.EF_DG1;
            case 99:
                return PassportService.EF_DG3;
            case 101:
                return PassportService.EF_DG5;
            case 102:
                return PassportService.EF_DG6;
            case 103:
                return PassportService.EF_DG7;
            case 104:
                return PassportService.EF_DG8;
            case 105:
                return PassportService.EF_DG9;
            case 106:
                return PassportService.EF_DG10;
            case 107:
                return PassportService.EF_DG11;
            case 108:
                return PassportService.EF_DG12;
            case 109:
                return PassportService.EF_DG13;
            case 110:
                return PassportService.EF_DG14;
            case 111:
                return PassportService.EF_DG15;
            case 112:
                return PassportService.EF_DG16;
            case LDSFile.EF_DG2_TAG /*117*/:
                return PassportService.EF_DG2;
            case LDSFile.EF_DG4_TAG /*118*/:
                return PassportService.EF_DG4;
            case LDSFile.EF_SOD_TAG /*119*/:
                return PassportService.EF_SOD;
            default:
                throw new NumberFormatException("Unknown tag " + Integer.toHexString(i));
        }
    }

    public static int lookupDataGroupNumberByTag(int i) {
        switch (i) {
            case 97:
                return 1;
            case 99:
                return 3;
            case 101:
                return 5;
            case 102:
                return 6;
            case 103:
                return 7;
            case 104:
                return 8;
            case 105:
                return 9;
            case 106:
                return 10;
            case 107:
                return 11;
            case 108:
                return 12;
            case 109:
                return 13;
            case 110:
                return 14;
            case 111:
                return 15;
            case 112:
                return 16;
            case LDSFile.EF_DG2_TAG /*117*/:
                return 2;
            case LDSFile.EF_DG4_TAG /*118*/:
                return 4;
            default:
                throw new NumberFormatException("Unknown tag " + Integer.toHexString(i));
        }
    }

    public static int lookupTagByDataGroupNumber(int i) {
        switch (i) {
            case 1:
                return 97;
            case 2:
                return LDSFile.EF_DG2_TAG;
            case 3:
                return 99;
            case 4:
                return LDSFile.EF_DG4_TAG;
            case 5:
                return 101;
            case 6:
                return 102;
            case 7:
                return 103;
            case 8:
                return 104;
            case 9:
                return 105;
            case 10:
                return 106;
            case 11:
                return 107;
            case 12:
                return 108;
            case 13:
                return 109;
            case 14:
                return 110;
            case 15:
                return 111;
            case 16:
                return 112;
            default:
                throw new NumberFormatException("Unknown number " + i);
        }
    }

    public static short lookupFIDByDataGroupNumber(int i) {
        switch (i) {
            case 1:
                return PassportService.EF_DG1;
            case 2:
                return PassportService.EF_DG2;
            case 3:
                return PassportService.EF_DG3;
            case 4:
                return PassportService.EF_DG4;
            case 5:
                return PassportService.EF_DG5;
            case 6:
                return PassportService.EF_DG6;
            case 7:
                return PassportService.EF_DG7;
            case 8:
                return PassportService.EF_DG8;
            case 9:
                return PassportService.EF_DG9;
            case 10:
                return PassportService.EF_DG10;
            case 11:
                return PassportService.EF_DG11;
            case 12:
                return PassportService.EF_DG12;
            case 13:
                return PassportService.EF_DG13;
            case 14:
                return PassportService.EF_DG14;
            case 15:
                return PassportService.EF_DG15;
            case 16:
                return PassportService.EF_DG16;
            default:
                throw new NumberFormatException("Unknown number " + i);
        }
    }

    public static short lookupTagByFID(short s) {
        switch (s) {
            case 257:
                return 97;
            case 258:
                return 117;
            case 259:
                return 99;
            case Configs.BUILD_ID_BASE /*260*/:
                return 118;
            case 261:
                return 101;
            case 262:
                return 102;
            case 263:
                return 103;
            case 264:
                return 104;
            case 265:
                return 105;
            case 266:
                return 106;
            case 267:
                return 107;
            case 268:
                return 108;
            case 269:
                return 109;
            case MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS /*270*/:
                return AlertDescription.unsupported_extension;
            case 271:
                return AlertDescription.certificate_unobtainable;
            case HelpCenterArticle.SET_RESERVATION_REQUIREMENTS /*272*/:
                return AlertDescription.unrecognized_name;
            case 285:
                return 119;
            case 286:
                return 96;
            default:
                throw new NumberFormatException("Unknown fid " + Integer.toHexString(s));
        }
    }

    public static short lookupDataGroupNumberByFID(short s) {
        switch (s) {
            case 257:
                return 1;
            case 258:
                return 2;
            case 259:
                return 3;
            case Configs.BUILD_ID_BASE /*260*/:
                return 4;
            case 261:
                return 5;
            case 262:
                return 6;
            case 263:
                return 7;
            case 264:
                return 8;
            case 265:
                return 9;
            case 266:
                return 10;
            case 267:
                return 11;
            case 268:
                return 12;
            case 269:
                return 13;
            case MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS /*270*/:
                return 14;
            case 271:
                return 15;
            case HelpCenterArticle.SET_RESERVATION_REQUIREMENTS /*272*/:
                return 16;
            default:
                throw new NumberFormatException("Unknown fid " + Integer.toHexString(s));
        }
    }

    public static String lookupFileNameByTag(int i) {
        switch (i) {
            case 96:
                return "EF_COM";
            case 97:
                return "EF_DG1";
            case 99:
                return "EF_DG3";
            case 101:
                return "EF_DG5";
            case 102:
                return "EF_DG6";
            case 103:
                return "EF_DG7";
            case 104:
                return "EF_DG8";
            case 105:
                return "EF_DG9";
            case 106:
                return "EF_DG10";
            case 107:
                return "EF_DG11";
            case 108:
                return "EF_DG12";
            case 109:
                return "EF_DG13";
            case 110:
                return "EF_DG14";
            case 111:
                return "EF_DG15";
            case 112:
                return "EF_DG16";
            case LDSFile.EF_DG2_TAG /*117*/:
                return "EF_DG2";
            case LDSFile.EF_DG4_TAG /*118*/:
                return "EF_DG4";
            case LDSFile.EF_SOD_TAG /*119*/:
                return "EF_SOD";
            default:
                return "File with tag 0x" + Integer.toHexString(i);
        }
    }

    public static String lookupFileNameByFID(int i) {
        switch (i) {
            case 257:
                return "EF_DG1";
            case 258:
                return "EF_DG2";
            case 259:
                return "EF_DG3";
            case Configs.BUILD_ID_BASE /*260*/:
                return "EF_DG4";
            case 261:
                return "EF_DG5";
            case 262:
                return "EF_DG6";
            case 263:
                return "EF_DG7";
            case 264:
                return "EF_DG8";
            case 265:
                return "EF_DG9";
            case 266:
                return "EF_DG10";
            case 267:
                return "EF_DG11";
            case 268:
                return "EF_DG12";
            case 269:
                return "EF_DG13";
            case MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS /*270*/:
                return "EF_DG14";
            case 271:
                return "EF_DG15";
            case HelpCenterArticle.SET_RESERVATION_REQUIREMENTS /*272*/:
                return "EF_DG16";
            case 285:
                return "EF_SOD";
            case 286:
                return "EF_COM";
            default:
                return "File with FID 0x" + Integer.toHexString(i);
        }
    }
}
