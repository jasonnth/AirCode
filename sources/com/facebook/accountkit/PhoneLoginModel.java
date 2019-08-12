package com.facebook.accountkit;

import com.facebook.accountkit.p029ui.NotificationChannel;

public interface PhoneLoginModel extends LoginModel {
    String getConfirmationCode();

    NotificationChannel getNotificationChannel();

    PhoneNumber getPhoneNumber();

    String getPrivacyPolicy();

    long getResendTime();

    String getTermsOfService();
}
