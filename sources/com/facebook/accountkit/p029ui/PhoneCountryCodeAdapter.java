package com.facebook.accountkit.p029ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.internal.Utility;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.facebook.accountkit.ui.PhoneCountryCodeAdapter */
final class PhoneCountryCodeAdapter extends BaseAdapter implements SpinnerAdapter {
    private final Context context;
    private final PhoneCountryCode[] phoneCountryCodes;
    private final UIManager uiManager;

    /* renamed from: com.facebook.accountkit.ui.PhoneCountryCodeAdapter$CountryCodeSource */
    private enum CountryCodeSource {
        APP_SUPPLIED_DEFAULT_VALUE,
        APP_SUPPLIED_PHONE_NUMBER,
        DEFAULT_VALUE,
        FIRST_VALUE,
        TELEPHONY_SERVICE
    }

    /* renamed from: com.facebook.accountkit.ui.PhoneCountryCodeAdapter$PhoneCountryCode */
    private static final class PhoneCountryCode {
        public final String countryCode;
        public final String countryName;
        public final String emojiFlag;
        public final String isoCode;
        public final long itemId;

        public PhoneCountryCode(String countryCode2, String isoCode2, String countryName2) {
            this.countryCode = countryCode2;
            this.isoCode = isoCode2;
            this.countryName = countryName2;
            String itemIdString = countryCode2.replaceAll("[\\D]", "");
            int count = isoCode2.length();
            for (int i = 0; i < count; i++) {
                itemIdString = itemIdString + Integer.toString(isoCode2.charAt(i));
            }
            this.itemId = Long.valueOf(itemIdString).longValue();
            this.emojiFlag = areFlagsSupported() ? isoCodeToEmojiFlag(isoCode2) : "";
        }

        public String getCountryCodeForDisplay() {
            return String.format("+%s", new Object[]{this.countryCode});
        }

        public String getCountryCodeForDisplayWithFlag() {
            return this.emojiFlag + getCountryCodeForDisplay();
        }

        public String getCountryNameForDisplay() {
            if (this.emojiFlag.isEmpty()) {
                return this.countryName;
            }
            return String.format("%s %s", new Object[]{this.emojiFlag, this.countryName});
        }

        private static String isoCodeToEmojiFlag(String isoCode2) {
            String emoji = new String(Character.toChars((Character.codePointAt(isoCode2, 0) - 65) + 127462)) + new String(Character.toChars((Character.codePointAt(isoCode2, 1) - 65) + 127462));
            return canShowFlagEmoji(emoji) ? emoji : "";
        }

        private static boolean areFlagsSupported() {
            return VERSION.SDK_INT >= 23;
        }

        @TargetApi(23)
        private static boolean canShowFlagEmoji(String flagEmoji) {
            return new Paint().hasGlyph(flagEmoji);
        }
    }

    /* renamed from: com.facebook.accountkit.ui.PhoneCountryCodeAdapter$ValueData */
    public static class ValueData implements Parcelable {
        public static final Creator<ValueData> CREATOR = new Creator<ValueData>() {
            public ValueData createFromParcel(Parcel source) {
                return new ValueData(source);
            }

            public ValueData[] newArray(int size) {
                return new ValueData[size];
            }
        };
        public final String countryCode;
        public final String countryCodeSource;
        public final int position;

        private ValueData(String countryCode2, String countryCodeSource2, int position2) {
            this.countryCode = countryCode2;
            this.countryCodeSource = countryCodeSource2;
            this.position = position2;
        }

        private ValueData(Parcel parcel) {
            this.countryCode = parcel.readString();
            this.countryCodeSource = parcel.readString();
            this.position = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.countryCode);
            dest.writeString(this.countryCodeSource);
            dest.writeInt(this.position);
        }
    }

    public PhoneCountryCodeAdapter(Context context2, UIManager uiManager2, String[] blacklist, String[] whitelist) {
        this.context = context2;
        this.uiManager = uiManager2;
        this.phoneCountryCodes = getAllPhoneCountryCodes(context2, blacklist, whitelist);
    }

    public int getCount() {
        return this.phoneCountryCodes.length;
    }

    public ValueData getInitialValue(PhoneNumber initialPhoneNumber, String defaultCountryCode) {
        String countryCodeSource = null;
        String countryCode = null;
        int position = -1;
        if (initialPhoneNumber != null) {
            countryCodeSource = CountryCodeSource.APP_SUPPLIED_PHONE_NUMBER.name();
            int length = this.phoneCountryCodes.length;
            countryCode = initialPhoneNumber.getCountryCode();
            String countryCodeIso = initialPhoneNumber.getCountryCodeIso();
            if (countryCodeIso != null) {
                position = getIndexOfCountryCode(countryCodeIso);
            } else {
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (countryCode.equalsIgnoreCase(this.phoneCountryCodes[i].countryCode)) {
                        position = i;
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        if (position == -1) {
            countryCodeSource = CountryCodeSource.APP_SUPPLIED_DEFAULT_VALUE.name();
            countryCode = defaultCountryCode;
            position = getIndexOfCountryCode(countryCode);
        }
        if (position == -1) {
            countryCodeSource = CountryCodeSource.TELEPHONY_SERVICE.name();
            countryCode = Utility.getCurrentCountry(this.context);
            position = getIndexOfCountryCode(countryCode);
        }
        if (position == -1) {
            countryCodeSource = CountryCodeSource.DEFAULT_VALUE.name();
            countryCode = "US";
            position = getIndexOfCountryCode(countryCode);
        }
        if (position == -1) {
            countryCodeSource = CountryCodeSource.FIRST_VALUE.name();
            countryCode = this.phoneCountryCodes[0].countryCode;
            position = 0;
        }
        return new ValueData(countryCode, countryCodeSource, position);
    }

    public Object getItem(int position) {
        PhoneCountryCode countryCode = this.phoneCountryCodes[position];
        return new ValueData(countryCode.countryCode, countryCode.isoCode, position);
    }

    public long getItemId(int position) {
        return this.phoneCountryCodes[position].itemId;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(this.context, C3354R.layout.com_accountkit_phone_country_code_item_layout, null);
        } else {
            view = convertView;
        }
        PhoneCountryCode phoneCountryCode = this.phoneCountryCodes[position];
        TextView countryCodeTextView = (TextView) view.findViewById(C3354R.C3356id.country_code);
        ((TextView) view.findViewById(C3354R.C3356id.label)).setText(phoneCountryCode.getCountryNameForDisplay());
        countryCodeTextView.setText(phoneCountryCode.getCountryCodeForDisplay());
        return view;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(this.context, C3354R.layout.com_accountkit_phone_country_code_layout, null);
        } else {
            view = convertView;
        }
        TextView countryCodeTextView = (TextView) view.findViewById(C3354R.C3356id.country_code);
        countryCodeTextView.setText(this.phoneCountryCodes[position].getCountryCodeForDisplayWithFlag());
        if (!ViewUtility.useLegacy(this.uiManager)) {
            countryCodeTextView.setTextColor(((SkinManager) this.uiManager).getTextColor());
        }
        return view;
    }

    private int getIndexOfCountryCode(String countryCode) {
        if (Utility.isNullOrEmpty(countryCode)) {
            return -1;
        }
        int length = this.phoneCountryCodes.length;
        for (int i = 0; i < length; i++) {
            if (countryCode.equalsIgnoreCase(this.phoneCountryCodes[i].isoCode)) {
                return i;
            }
        }
        return -1;
    }

    private static PhoneCountryCode[] getAllPhoneCountryCodes(Context context2, String[] blacklist, String[] whitelist) {
        Set<String> clientBlacklisted;
        String[] resources = context2.getResources().getStringArray(C3354R.array.com_accountkit_phone_country_codes);
        ArrayList<PhoneCountryCode> phoneCountryCodeList = new ArrayList<>();
        Set<String> clientWhitelisted = whitelist != null ? new HashSet<>(Arrays.asList(whitelist)) : null;
        if (blacklist == null || blacklist.length <= 0) {
            clientBlacklisted = new HashSet<>();
        } else {
            clientBlacklisted = new HashSet<>(Arrays.asList(blacklist));
        }
        for (String resource : resources) {
            String[] components = resource.split(":", 3);
            if (!clientBlacklisted.contains(components[1]) && (clientWhitelisted == null || clientWhitelisted.contains(components[1]))) {
                phoneCountryCodeList.add(new PhoneCountryCode(components[0], components[1], components[2]));
            }
        }
        final Collator collator = Collator.getInstance(Resources.getSystem().getConfiguration().locale);
        collator.setStrength(0);
        Collections.sort(phoneCountryCodeList, new Comparator<PhoneCountryCode>() {
            public int compare(PhoneCountryCode code1, PhoneCountryCode code2) {
                return collator.compare(code1.countryName, code2.countryName);
            }
        });
        PhoneCountryCode[] phoneCountryCodes2 = new PhoneCountryCode[phoneCountryCodeList.size()];
        phoneCountryCodeList.toArray(phoneCountryCodes2);
        return phoneCountryCodes2;
    }
}
