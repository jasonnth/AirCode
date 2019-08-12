package com.airbnb.android.core.enums;

import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.facebook.appevents.AppEventsConstants;

public enum CardType {
    Amex("american_express", C0716R.C0717drawable.amex_icon, C0716R.C0717drawable.ccv_front, C0716R.string.amex, 15, "465", 4),
    MC("master", C0716R.C0717drawable.mastercard_icon, C0716R.C0717drawable.ccv_back, C0716R.string.mastercard, 16, "4444", 3),
    Visa("visa", C0716R.C0717drawable.visa_icon, C0716R.C0717drawable.ccv_back, C0716R.string.visa, 16, "4444", 3),
    Discover("discover", C0716R.C0717drawable.discover_icon, C0716R.C0717drawable.ccv_back, C0716R.string.discover, 16, "4444", 3),
    JCB15("jcb", C0716R.C0717drawable.jcb_icon, C0716R.C0717drawable.ccv_back, C0716R.string.jcb, 15, "465", 3),
    JCB16("jcb", C0716R.C0717drawable.jcb_icon, C0716R.C0717drawable.ccv_back, C0716R.string.jcb, 16, "4444", 3),
    Aura("aura", C0716R.C0717drawable.aura_card_icon, C0716R.C0717drawable.ccv_back, C0716R.string.aura, 19, "4444", 3),
    Diners("diners", C0716R.C0717drawable.diners_club_icon, C0716R.C0717drawable.ccv_back, C0716R.string.diners, 14, "4444", 3),
    Elo("elo", C0716R.C0717drawable.elo_card_icon, C0716R.C0717drawable.ccv_back, C0716R.string.elo, 16, "4444", 3),
    Hipercard("hipercard", C0716R.C0717drawable.hipercard_icon, C0716R.C0717drawable.ccv_back, C0716R.string.hipercard, 16, "4444", 3),
    Maestro("maestro", C0716R.C0717drawable.maestro_card_icon, C0716R.C0717drawable.ccv_back, C0716R.string.maestro, 16, "4444", 3),
    Unknown("unknown", C0716R.C0717drawable.creditcard_default_icon, 0, C0716R.string.payment_type_cc, 0, AppEventsConstants.EVENT_PARAM_VALUE_NO, 0);
    
    private final String mCCFormat;
    private final int mCCVImage;
    private final int mCCVLength;
    private final int mIcon;
    private final String mKey;
    private final int mLength;
    private final int mName;

    private CardType(String key, int icon, int ccvImage, int name, int length, String ccFormat, int ccvLength) {
        this.mKey = key;
        this.mIcon = icon;
        this.mCCVImage = ccvImage;
        this.mName = name;
        this.mCCVLength = ccvLength;
        this.mCCFormat = ccFormat;
        this.mLength = length;
    }

    public static CardType getCardType(String key) {
        CardType[] types;
        for (CardType card : values()) {
            if (card.mKey.equals(key)) {
                return card;
            }
        }
        return Unknown;
    }

    public static CardType getCardTypeFromCCNumber(String number) {
        if (!TextUtils.isEmpty(number)) {
            switch (number.charAt(0)) {
                case '1':
                    if (number.length() > 3 && '8' == number.charAt(1) && '0' == number.charAt(2) && '0' == number.charAt(3)) {
                        return JCB15;
                    }
                case '2':
                    if (number.length() > 3 && '1' == number.charAt(1) && '3' == number.charAt(2) && '1' == number.charAt(3)) {
                        return JCB15;
                    }
                case '3':
                    if (number.length() > 1) {
                        switch (number.charAt(1)) {
                            case '4':
                            case '7':
                                return Amex;
                            case '5':
                                return JCB16;
                        }
                    }
                    break;
                case '4':
                    return Visa;
                case '5':
                    if (number.length() > 1) {
                        switch (number.charAt(1)) {
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                                return MC;
                        }
                    }
                    break;
                case '6':
                    if (number.length() > 1) {
                        switch (number.charAt(1)) {
                            case '5':
                                return Discover;
                        }
                    }
                    if (number.length() > 3 && '0' == number.charAt(1) && '1' == number.charAt(2) && '1' == number.charAt(3)) {
                        return Discover;
                    }
            }
        }
        return Unknown;
    }

    private static boolean isValidCCNumber(String cardNumber) {
        if (TextUtils.isEmpty(cardNumber) || cardNumber.length() < 4) {
            return false;
        }
        int sum = 0;
        int n = 0;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int v = Character.getNumericValue(cardNumber.charAt(i));
            if (n % 2 == 1) {
                int v2 = v * 2;
                sum = sum + (v2 / 10) + (v2 % 10);
            } else {
                sum += v;
            }
            n++;
        }
        if (sum % 10 != 0) {
            return false;
        }
        return true;
    }

    public static boolean isValidCCNumber(String ccNumber, CardType mCardType) {
        String ccNumber2 = ccNumber.replaceAll("[^\\d]", "").trim();
        if (mCardType != null) {
            return (ccNumber2.length() == mCardType.mLength || mCardType.mLength == 0) && isValidCCNumber(ccNumber2);
        }
        return isValidCCNumber(ccNumber2);
    }

    public String formatCC(String cc) {
        String cc2 = cc.replaceAll("[^\\d]", "").trim();
        StringBuilder formatted = new StringBuilder();
        int currIndex = 0;
        int i = 0;
        while (true) {
            try {
                if (i < this.mCCFormat.length()) {
                    if (i > 0) {
                        formatted.append(" ");
                    }
                    int num = Integer.parseInt(String.valueOf(this.mCCFormat.charAt(i)));
                    if (cc2.length() < currIndex + num) {
                        formatted.append(cc2.subSequence(currIndex, cc2.length()));
                        break;
                    }
                    formatted.append(cc2.subSequence(currIndex, currIndex + num));
                    currIndex += num;
                    i++;
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                return cc2;
            }
        }
        return formatted.toString().trim();
    }

    public static boolean isSecurityCodeFullLength(String securityCode, CardType cardType) {
        return securityCode.length() == cardType.mCCVLength;
    }

    public static boolean isSecurityCodeTooLong(String securityCode, CardType cardType) {
        return securityCode.length() > cardType.mCCVLength;
    }

    public static boolean isCardNumberFullLength(String cc, CardType cardType) {
        return cc.replaceAll("[^\\d]", "").trim().length() == cardType.mLength;
    }

    public int getName() {
        return this.mName;
    }

    public int getIcon() {
        return this.mIcon;
    }

    public int getCCVLength() {
        return this.mCCVLength;
    }

    public int getCardNumberMaxLength() {
        return this.mLength;
    }

    public String getKey() {
        return this.mKey;
    }
}
