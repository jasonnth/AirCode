package com.airbnb.android.core.views;

import com.airbnb.android.core.utils.PhoneUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PhoneNumberInputSheet_MembersInjector implements MembersInjector<PhoneNumberInputSheet> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PhoneNumberInputSheet_MembersInjector.class.desiredAssertionStatus());
    private final Provider<PhoneNumberUtil> phoneNumberUtilProvider;
    private final Provider<PhoneUtil> phoneUtilProvider;

    public PhoneNumberInputSheet_MembersInjector(Provider<PhoneUtil> phoneUtilProvider2, Provider<PhoneNumberUtil> phoneNumberUtilProvider2) {
        if ($assertionsDisabled || phoneUtilProvider2 != null) {
            this.phoneUtilProvider = phoneUtilProvider2;
            if ($assertionsDisabled || phoneNumberUtilProvider2 != null) {
                this.phoneNumberUtilProvider = phoneNumberUtilProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<PhoneNumberInputSheet> create(Provider<PhoneUtil> phoneUtilProvider2, Provider<PhoneNumberUtil> phoneNumberUtilProvider2) {
        return new PhoneNumberInputSheet_MembersInjector(phoneUtilProvider2, phoneNumberUtilProvider2);
    }

    public void injectMembers(PhoneNumberInputSheet instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.phoneUtil = (PhoneUtil) this.phoneUtilProvider.get();
        instance.phoneNumberUtil = (PhoneNumberUtil) this.phoneNumberUtilProvider.get();
    }

    public static void injectPhoneUtil(PhoneNumberInputSheet instance, Provider<PhoneUtil> phoneUtilProvider2) {
        instance.phoneUtil = (PhoneUtil) phoneUtilProvider2.get();
    }

    public static void injectPhoneNumberUtil(PhoneNumberInputSheet instance, Provider<PhoneNumberUtil> phoneNumberUtilProvider2) {
        instance.phoneNumberUtil = (PhoneNumberUtil) phoneNumberUtilProvider2.get();
    }
}
