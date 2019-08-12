package com.airbnb.android.core.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SecurityUtil {
    private SecurityUtil() {
    }

    @SuppressLint({"MissingPermission"})
    public static Account[] maybeGetAccounts(Context context) {
        try {
            return AccountManager.get(context).getAccounts();
        } catch (SecurityException e) {
            return new Account[0];
        }
    }

    @SuppressLint({"MissingPermission"})
    public static Account[] maybeGetAndroidAccounts(AccountManager androidAccountManager) {
        try {
            return androidAccountManager.getAccountsByType("com.airbnb.android");
        } catch (SecurityException e) {
            return new Account[0];
        }
    }

    public static List<String> getAccountEmails(Context context) {
        Account[] accounts = maybeGetAccounts(context);
        Set<String> emailSet = new HashSet<>();
        for (Account account : accounts) {
            if (TextUtil.isValidEmail(account.name)) {
                emailSet.add(account.name);
                if (account.name.endsWith("@airbedandbreakfast.com")) {
                    emailSet.add(account.name.replace("@airbedandbreakfast.com", "@airbnb.com"));
                }
            }
        }
        return new ArrayList(emailSet);
    }
}
