package com.appboy;

import com.appboy.enums.Gender;
import com.appboy.enums.Month;
import com.appboy.support.AppboyLogger;
import com.appboy.support.ValidationUtils;
import p004bo.app.C0365ax;
import p004bo.app.C0379bh;
import p004bo.app.C0386bo;
import p004bo.app.C0397bz;
import p004bo.app.C0403cd;
import p004bo.app.C0448dk;
import p004bo.app.C0451dl;
import p004bo.app.C0455dp;

public class AppboyUser {

    /* renamed from: a */
    private static final String f2749a = AppboyLogger.getAppboyLogTag(AppboyUser.class);

    /* renamed from: b */
    private final C0451dl f2750b;

    /* renamed from: c */
    private final C0365ax f2751c;

    /* renamed from: d */
    private final C0448dk f2752d;

    /* renamed from: e */
    private final Object f2753e = new Object();

    /* renamed from: f */
    private final C0379bh f2754f;

    /* renamed from: g */
    private volatile String f2755g;

    AppboyUser(C0451dl userCache, C0365ax appboyManager, String userId, C0379bh appboyLocationManager, C0448dk serverConfigStorageProvider) {
        this.f2755g = userId;
        this.f2750b = userCache;
        this.f2751c = appboyManager;
        this.f2754f = appboyLocationManager;
        this.f2752d = serverConfigStorageProvider;
    }

    public boolean setFirstName(String firstName) {
        try {
            this.f2750b.mo6983a(firstName);
            return true;
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set first name to: " + firstName, e);
            return false;
        }
    }

    public boolean setLastName(String lastName) {
        try {
            this.f2750b.mo6987b(lastName);
            return true;
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set last name to: " + lastName, e);
            return false;
        }
    }

    public boolean setEmail(String email) {
        try {
            return this.f2750b.mo6989c(email);
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set email to: " + email, e);
            return false;
        }
    }

    public boolean setGender(Gender gender) {
        try {
            this.f2750b.mo6982a(gender);
            return true;
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set gender to: " + gender, e);
            return false;
        }
    }

    public boolean setDateOfBirth(int year, Month month, int day) {
        boolean z = false;
        try {
            return this.f2750b.mo6984a(year, month, day);
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, String.format("Failed to set date of birth to: %d-%d-%d", new Object[]{Integer.valueOf(year), Integer.valueOf(month.getValue()), Integer.valueOf(day)}), e);
            return z;
        }
    }

    public boolean setCountry(String country) {
        try {
            this.f2750b.mo6990d(country);
            return true;
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set country to: " + country, e);
            return false;
        }
    }

    public boolean setHomeCity(String homeCity) {
        try {
            this.f2750b.mo6991e(homeCity);
            return true;
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set home city to: " + homeCity, e);
            return false;
        }
    }

    public boolean setPhoneNumber(String phoneNumber) {
        try {
            return this.f2750b.mo6992f(phoneNumber);
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set phone number to: " + phoneNumber, e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String key, boolean value) {
        try {
            return this.f2750b.mo6986a(key, (Object) Boolean.valueOf(value));
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set custom boolean attribute " + key + ".", e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String key, int value) {
        try {
            return this.f2750b.mo6986a(key, (Object) Integer.valueOf(value));
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set custom integer attribute " + key + ".", e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String key, float value) {
        try {
            return this.f2750b.mo6986a(key, (Object) Float.valueOf(value));
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set custom float attribute " + key + ".", e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String key, long value) {
        try {
            return this.f2750b.mo6986a(key, (Object) Long.valueOf(value));
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set custom long attribute " + key + ".", e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String key, String value) {
        try {
            return this.f2750b.mo6986a(key, (Object) value);
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set custom string attribute " + key + ".", e);
            return false;
        }
    }

    public boolean addToCustomAttributeArray(String key, String value) {
        try {
            if (!ValidationUtils.isValidCustomAttributeKey(key) || ValidationUtils.isBlacklistedCustomAttributeKey(key, this.f2752d.mo6975i()) || !ValidationUtils.isValidCustomAttributeValue(value)) {
                return false;
            }
            this.f2751c.mo6769a((C0386bo) C0397bz.m300c(ValidationUtils.ensureAppboyFieldLength(key), ValidationUtils.ensureAppboyFieldLength(value)));
            return true;
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to add custom attribute with key '" + key + "'.", e);
            return false;
        }
    }

    public boolean setCustomAttributeArray(String key, String[] values) {
        try {
            if (!ValidationUtils.isValidCustomAttributeKey(key) || ValidationUtils.isBlacklistedCustomAttributeKey(key, this.f2752d.mo6975i())) {
                return false;
            }
            String key2 = ValidationUtils.ensureAppboyFieldLength(key);
            if (values != null) {
                values = ValidationUtils.ensureCustomAttributeArrayValues(values);
            }
            this.f2751c.mo6769a((C0386bo) C0397bz.m288a(key2, values));
            return true;
        } catch (Exception e) {
            AppboyLogger.m1739w(f2749a, "Failed to set custom attribute array with key: '" + key + "'.");
            return false;
        }
    }

    public boolean setCustomUserAttributeToNow(String key) {
        try {
            return this.f2750b.mo6985a(key, C0455dp.m515a());
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set custom attribute " + key + " to now.", e);
            return false;
        }
    }

    public boolean setCustomUserAttributeToSecondsFromEpoch(String key, long secondsFromEpoch) {
        try {
            return this.f2750b.mo6985a(key, secondsFromEpoch);
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set custom attribute " + key + " to " + secondsFromEpoch + " seconds from epoch.", e);
            return false;
        }
    }

    public boolean incrementCustomUserAttribute(String key, int incrementValue) {
        try {
            if (ValidationUtils.isBlacklistedCustomAttributeKey(key, this.f2752d.mo6975i())) {
                return false;
            }
            this.f2751c.mo6769a((C0386bo) C0397bz.m280a(key, incrementValue));
            return true;
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to increment custom attribute " + key + " by " + incrementValue + ".", e);
            return false;
        }
    }

    public boolean unsetCustomUserAttribute(String key) {
        try {
            if (ValidationUtils.isBlacklistedCustomAttributeKey(key, this.f2752d.mo6975i())) {
                return false;
            }
            return this.f2750b.mo6995i(key);
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to unset custom attribute " + key + ".", e);
            return false;
        }
    }

    public boolean setAvatarImageUrl(String avatarImageUrl) {
        try {
            this.f2750b.mo6993g(avatarImageUrl);
            return true;
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to set the avatar image URL.", e);
            return false;
        }
    }

    public String getUserId() {
        String str;
        synchronized (this.f2753e) {
            str = this.f2755g;
        }
        return str;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo27216a(String str) {
        synchronized (this.f2753e) {
            if (this.f2755g.equals("") || this.f2755g.equals(str)) {
                this.f2755g = str;
            } else {
                throw new IllegalArgumentException(String.format("setExternalId can not be used to change the external ID of a UserCache from a non-empty value to a new value. Was: [%s], tried to change to: [%s]", new Object[]{this.f2755g, str}));
            }
        }
    }

    public void setLastKnownLocation(double latitude, double longitude, Double altitude, Double accuracy) {
        try {
            this.f2754f.mo6758a(new C0403cd(latitude, longitude, altitude, accuracy));
        } catch (Exception e) {
            AppboyLogger.m1740w(f2749a, "Failed to manually set location.", e);
        }
    }
}
