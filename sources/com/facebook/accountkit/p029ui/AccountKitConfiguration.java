package com.facebook.accountkit.p029ui;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.p029ui.AccountKitActivity.ResponseType;
import com.facebook.accountkit.p029ui.AccountKitActivity.TitleType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/* renamed from: com.facebook.accountkit.ui.AccountKitConfiguration */
public final class AccountKitConfiguration implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        public AccountKitConfiguration createFromParcel(Parcel in) {
            return new AccountKitConfiguration(in);
        }

        public AccountKitConfiguration[] newArray(int size) {
            return new AccountKitConfiguration[size];
        }
    };
    static final String TAG = AccountKitConfiguration.class.getSimpleName();
    private final String defaultCountryCode;
    private final String initialAuthState;
    private final String initialEmail;
    private final PhoneNumber initialPhoneNumber;
    private final LoginType loginType;
    private final LinkedHashSet<NotificationChannel> notificationChannels;
    private final boolean readPhoneStateEnabled;
    private final boolean receiveSMSEnabled;
    private final ResponseType responseType;
    private final String[] smsBlacklist;
    private final String[] smsWhitelist;
    private final UIManager uiManager;

    /* renamed from: com.facebook.accountkit.ui.AccountKitConfiguration$AccountKitConfigurationBuilder */
    public static class AccountKitConfigurationBuilder {
        private String defaultCountryCode;
        private String initialAuthState;
        private String initialEmail;
        private PhoneNumber initialPhoneNumber;
        private LoginType loginType;
        private final LinkedHashSet<NotificationChannel> notificationChannels = new LinkedHashSet<>(NotificationChannel.values().length);
        private boolean readPhoneStateEnabled = true;
        private boolean receiveSMSEnabled = true;
        private ResponseType responseType;
        private String[] smsBlacklist;
        private String[] smsWhitelist;
        @Deprecated
        private int theme = -1;
        private UIManagerStub uiManager;

        public AccountKitConfigurationBuilder(LoginType loginType2, ResponseType responseType2) {
            this.notificationChannels.add(NotificationChannel.FACEBOOK);
            this.notificationChannels.add(NotificationChannel.VOICE_CALLBACK);
            this.loginType = loginType2;
            this.responseType = responseType2;
        }

        public AccountKitConfigurationBuilder setAdvancedUIManager(AdvancedUIManager advancedUIManager) {
            this.uiManager = advancedUIManager;
            this.theme = -1;
            return this;
        }

        public AccountKitConfigurationBuilder setUIManager(UIManager uiManager2) {
            this.uiManager = uiManager2;
            this.theme = -1;
            return this;
        }

        public AccountKitConfigurationBuilder setDefaultCountryCode(String defaultCountryCode2) {
            this.defaultCountryCode = defaultCountryCode2;
            return this;
        }

        public AccountKitConfigurationBuilder setFacebookNotificationsEnabled(boolean facebookNotificationsEnabled) {
            if (!facebookNotificationsEnabled) {
                this.notificationChannels.remove(NotificationChannel.FACEBOOK);
            } else if (!this.notificationChannels.contains(NotificationChannel.FACEBOOK)) {
                this.notificationChannels.add(NotificationChannel.FACEBOOK);
            }
            return this;
        }

        public AccountKitConfigurationBuilder setVoiceCallbackNotificationsEnabled(boolean voiceCallbackNotificationsEnabled) {
            if (!voiceCallbackNotificationsEnabled) {
                this.notificationChannels.remove(NotificationChannel.VOICE_CALLBACK);
            } else if (!this.notificationChannels.contains(NotificationChannel.VOICE_CALLBACK)) {
                this.notificationChannels.add(NotificationChannel.VOICE_CALLBACK);
            }
            return this;
        }

        public AccountKitConfigurationBuilder setInitialAuthState(String initialAuthState2) {
            this.initialAuthState = initialAuthState2;
            return this;
        }

        public AccountKitConfigurationBuilder setInitialEmail(String initialEmail2) {
            this.initialEmail = initialEmail2;
            return this;
        }

        public AccountKitConfigurationBuilder setInitialPhoneNumber(PhoneNumber initialPhoneNumber2) {
            this.initialPhoneNumber = initialPhoneNumber2;
            return this;
        }

        public AccountKitConfigurationBuilder setReadPhoneStateEnabled(boolean readPhoneStateEnabled2) {
            this.readPhoneStateEnabled = readPhoneStateEnabled2;
            return this;
        }

        public AccountKitConfigurationBuilder setReceiveSMS(boolean receiveSMSEnabled2) {
            this.receiveSMSEnabled = receiveSMSEnabled2;
            return this;
        }

        public AccountKitConfigurationBuilder setSMSBlacklist(String[] smsBlacklist2) {
            this.smsBlacklist = smsBlacklist2;
            return this;
        }

        public AccountKitConfigurationBuilder setSMSWhitelist(String[] smsWhitelist2) {
            this.smsWhitelist = smsWhitelist2;
            return this;
        }

        public AccountKitConfigurationBuilder setTheme(int theme2) {
            this.theme = theme2;
            return this;
        }

        public AccountKitConfigurationBuilder setTitleType(TitleType titleType) {
            return this;
        }

        public AccountKitConfiguration build() {
            if (this.uiManager == null) {
                this.uiManager = new ThemeUIManager(this.theme);
            } else if (this.theme != -1 && (this.uiManager instanceof SkinManager)) {
                ((UIManager) this.uiManager).setThemeId(this.theme);
            }
            if (this.uiManager instanceof AdvancedUIManager) {
                this.uiManager = new AdvancedUIManagerWrapper((AdvancedUIManager) this.uiManager, this.theme);
            }
            return new AccountKitConfiguration((UIManager) this.uiManager, this.defaultCountryCode, this.notificationChannels, this.initialAuthState, this.initialEmail, this.initialPhoneNumber, this.loginType, this.readPhoneStateEnabled, this.receiveSMSEnabled, this.responseType, this.smsBlacklist, this.smsWhitelist);
        }
    }

    private AccountKitConfiguration(UIManager uiManager2, String defaultCountryCode2, LinkedHashSet<NotificationChannel> notificationChannels2, String initialAuthState2, String initialEmail2, PhoneNumber initialPhoneNumber2, LoginType loginType2, boolean readPhoneStateEnabled2, boolean receiveSMSEnabled2, ResponseType responseType2, String[] smsBlacklist2, String[] smsWhitelist2) {
        this.notificationChannels = new LinkedHashSet<>(NotificationChannel.values().length);
        this.initialAuthState = initialAuthState2;
        this.defaultCountryCode = defaultCountryCode2;
        this.initialEmail = initialEmail2;
        this.notificationChannels.addAll(notificationChannels2);
        this.uiManager = uiManager2;
        this.loginType = loginType2;
        this.initialPhoneNumber = initialPhoneNumber2;
        this.readPhoneStateEnabled = readPhoneStateEnabled2;
        this.receiveSMSEnabled = receiveSMSEnabled2;
        this.responseType = responseType2;
        this.smsBlacklist = smsBlacklist2;
        this.smsWhitelist = smsWhitelist2;
    }

    @Deprecated
    public AdvancedUIManager getAdvancedUIManager() {
        if (this.uiManager instanceof AdvancedUIManagerWrapper) {
            return ((AdvancedUIManagerWrapper) this.uiManager).getAdvancedUIManager();
        }
        return null;
    }

    public UIManager getUIManager() {
        return this.uiManager;
    }

    public String getDefaultCountryCode() {
        return this.defaultCountryCode;
    }

    public List<NotificationChannel> getNotificationChannels() {
        return Collections.unmodifiableList(new ArrayList(this.notificationChannels));
    }

    public boolean areFacebookNotificationsEnabled() {
        return getNotificationChannels().contains(NotificationChannel.FACEBOOK);
    }

    public String getInitialAuthState() {
        return this.initialAuthState;
    }

    public String getInitialEmail() {
        return this.initialEmail;
    }

    public PhoneNumber getInitialPhoneNumber() {
        return this.initialPhoneNumber;
    }

    public LoginType getLoginType() {
        return this.loginType;
    }

    public boolean isReadPhoneStateEnabled() {
        return this.readPhoneStateEnabled;
    }

    public boolean isReceiveSMSEnabled() {
        return this.receiveSMSEnabled;
    }

    public ResponseType getResponseType() {
        return this.responseType;
    }

    public String[] getSmsBlacklist() {
        return this.smsBlacklist;
    }

    public String[] getSmsWhitelist() {
        return this.smsWhitelist;
    }

    @Deprecated
    public int getTheme() {
        return this.uiManager.getThemeId();
    }

    @Deprecated
    public TitleType getTitleType() {
        return null;
    }

    private AccountKitConfiguration(Parcel parcel) {
        boolean z = true;
        this.notificationChannels = new LinkedHashSet<>(NotificationChannel.values().length);
        this.uiManager = (UIManager) parcel.readParcelable(UIManager.class.getClassLoader());
        this.defaultCountryCode = parcel.readString();
        this.notificationChannels.clear();
        for (int channel : parcel.createIntArray()) {
            this.notificationChannels.add(NotificationChannel.values()[channel]);
        }
        this.initialAuthState = parcel.readString();
        this.initialEmail = parcel.readString();
        this.initialPhoneNumber = (PhoneNumber) parcel.readParcelable(PhoneNumber.class.getClassLoader());
        this.loginType = LoginType.valueOf(parcel.readString());
        this.readPhoneStateEnabled = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.receiveSMSEnabled = z;
        this.responseType = ResponseType.valueOf(parcel.readString());
        this.smsBlacklist = parcel.createStringArray();
        this.smsWhitelist = parcel.createStringArray();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeParcelable(this.uiManager, flags);
        dest.writeString(this.defaultCountryCode);
        NotificationChannel[] arr = new NotificationChannel[this.notificationChannels.size()];
        this.notificationChannels.toArray(arr);
        int[] channels = new int[arr.length];
        for (int i2 = 0; i2 < arr.length; i2++) {
            channels[i2] = arr[i2].ordinal();
        }
        dest.writeIntArray(channels);
        dest.writeString(this.initialAuthState);
        dest.writeString(this.initialEmail);
        dest.writeParcelable(this.initialPhoneNumber, flags);
        dest.writeString(this.loginType.name());
        dest.writeByte((byte) (this.readPhoneStateEnabled ? 1 : 0));
        if (!this.receiveSMSEnabled) {
            i = 0;
        }
        dest.writeByte((byte) i);
        dest.writeString(this.responseType.name());
        dest.writeStringArray(this.smsBlacklist);
        dest.writeStringArray(this.smsWhitelist);
    }
}
