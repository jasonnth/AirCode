package net.p318sf.scuba.smartcards;

import com.facebook.internal.AnalyticsEvents;

/* renamed from: net.sf.scuba.smartcards.CardServiceException */
public class CardServiceException extends Exception {
    public static final int SW_NONE = -1;
    private static final long serialVersionUID = 4489156194716970879L;

    /* renamed from: sw */
    private int f6304sw = -1;

    public CardServiceException(String str) {
        super(str);
    }

    public CardServiceException(String str, int i) {
        super(str);
        this.f6304sw = i;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static String statusWordToString(short s) {
        switch (s) {
            case -28672:
                return "NO ERROR";
            case 25218:
                return "END OF FILE";
            case 25223:
                return "LESS DATA RESPONDED THAN REQUESTED";
            case 26368:
                return "WRONG LENGTH";
            case 26753:
                return "LOGICAL CHANNEL NOT SUPPORTED";
            case 26754:
                return "SECURE MESSAGING NOT SUPPORTED";
            case 26755:
                return "LAST COMMAND EXPECTED";
            case 27010:
                return "SECURITY STATUS NOT SATISFIED";
            case 27011:
                return "FILE INVALID";
            case 27012:
                return "DATA INVALID";
            case 27013:
                return "CONDITIONS NOT SATISFIED";
            case 27014:
                return "COMMAND NOT ALLOWED";
            case 27015:
                return "EXPECTED SM DATA OBJECTS MISSING";
            case 27016:
                return "SM DATA OBJECTS INCORRECT";
            case 27033:
                return "APPLET SELECT FAILED";
            case 27073:
                return "KEY USAGE ERROR";
            case 27264:
                return "WRONG DATA or FILEHEADER INCONSISTENT";
            case 27265:
                return "FUNC NOT SUPPORTED";
            case 27266:
                return "FILE NOT FOUND";
            case 27267:
                return "RECORD NOT FOUND";
            case 27268:
                return "OUT OF MEMORY or FILE FULL";
            case 27270:
                return "INCORRECT P1P2";
            case 27272:
                return "KEY NOT FOUND";
            case 27392:
                return "WRONG P1P2";
            case 27904:
                return "INS NOT SUPPORTED";
            case 28160:
                return "CLA NOT SUPPORTED";
            case 28416:
                return "UNKNOWN";
            case 28671:
                return "CARD TERMINATED";
            default:
                return (s & 65280) == 24832 ? "BYTES REMAINING " + Integer.toString(s & 255) : (s & 65280) == 27648 ? "CORRECT LENGTH " + Integer.toString(s & 255) : (65520 & s) == 25536 ? "NON VOLATILE MEMORY CHANGED COUNT " + Integer.toString(s & 15) : AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    public String getMessage() {
        return this.f6304sw == -1 ? super.getMessage() : super.getMessage() + " (SW = 0x" + Integer.toHexString(this.f6304sw).toUpperCase() + ": " + statusWordToString((short) this.f6304sw) + ")";
    }

    public int getSW() {
        return this.f6304sw;
    }
}
