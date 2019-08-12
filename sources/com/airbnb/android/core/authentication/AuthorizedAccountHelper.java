package com.airbnb.android.core.authentication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.SwitchAccountAnalytics;
import com.airbnb.android.core.models.AuthorizedAccount;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.SecurityUtil;
import com.airbnb.android.utils.AndroidVersion;
import com.bugsnag.android.MetaData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public final class AuthorizedAccountHelper {
    private static final String KEY_AUTHORIZED_ACCOUNTS = "authorized_accounts";
    private static WeakReference<AuthorizedAccountHelper> instance;
    AirbnbAccountManager accountManager;
    AirbnbApi airbnbApi;
    private final AccountManager androidAccountManager = this.accountManager.getManager();
    private final SharedPreferences debugPreferences = this.preferences.getGlobalSharedPreferences();
    AirbnbPreferences preferences;

    private AuthorizedAccountHelper(Context context) {
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
        transitionAuthorizedUsers();
    }

    private void transitionAuthorizedUsers() {
        Iterator it = AuthorizedAccount.getAccounts(this.debugPreferences.getString(KEY_AUTHORIZED_ACCOUNTS, "")).iterator();
        while (it.hasNext()) {
            addAndroidAccountExplicitly((AuthorizedAccount) it.next(), "transition");
        }
    }

    @SuppressLint({"MissingPermission"})
    private void addAndroidAccountExplicitly(AuthorizedAccount account, String addAccountType) {
        try {
            Account androidAccount = new Account(account.getUsername(), "com.airbnb.android");
            this.androidAccountManager.addAccountExplicitly(androidAccount, null, null);
            this.androidAccountManager.setAuthToken(androidAccount, AirbnbAuthenticator.AIRBNB_ACCOUNT_TOKEN_TYPE, account.getAuthToken());
            this.androidAccountManager.setUserData(androidAccount, AirbnbAuthenticator.KEY_PICTURE_URL, account.getPictureUrl());
            this.androidAccountManager.setUserData(androidAccount, "id", Long.toString(account.getId()));
        } catch (SecurityException e) {
            logAccountAddFailed(addAccountType, e);
        }
    }

    @SuppressLint({"MissingPermission"})
    private void logAccountAddFailed(String addAccountType, SecurityException e) {
        try {
            MetaData md = new MetaData();
            md.addToTab("Account Management", "Add Account Type", addAccountType);
            StringBuilder accountList = new StringBuilder();
            for (Account acc : SecurityUtil.maybeGetAndroidAccounts(this.androidAccountManager)) {
                accountList.append(this.androidAccountManager.getUserData(acc, "id"));
                accountList.append(",");
            }
            md.addToTab("Account Management", "AM accounts", accountList.toString());
            accountList.setLength(0);
            Iterator it = AuthorizedAccount.getAccounts(this.debugPreferences.getString(KEY_AUTHORIZED_ACCOUNTS, "")).iterator();
            while (it.hasNext()) {
                accountList.append(((AuthorizedAccount) it.next()).getId());
                accountList.append(",");
            }
            md.addToTab("Account Management", "sharedPrefs accounts", accountList.toString());
            BugsnagWrapper.notify((Throwable) e, md);
        } catch (RuntimeException e2) {
        }
    }

    public static AuthorizedAccountHelper get(Context context) {
        if (instance == null || instance.get() == null) {
            instance = new WeakReference<>(new AuthorizedAccountHelper(context));
        }
        return (AuthorizedAccountHelper) instance.get();
    }

    public ArrayList<AuthorizedAccount> getAuthorizedUsers() {
        return AuthorizedAccount.getAccounts(this.debugPreferences.getString(KEY_AUTHORIZED_ACCOUNTS, ""));
    }

    public void addOrUpdateCurrentUser() {
        boolean needSave;
        User currentUser = this.accountManager.getCurrentUser();
        String token = this.accountManager.getAccessToken();
        if (currentUser != null && !TextUtils.isEmpty(token)) {
            SwitchAccountAnalytics.logLogin(currentUser.getId(), this.preferences.getGlobalSharedPreferences());
            List<AuthorizedAccount> accounts = getAuthorizedUsers();
            AuthorizedAccount existingAccount = null;
            Iterator it = accounts.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AuthorizedAccount account = (AuthorizedAccount) it.next();
                if (currentUser.getId() == account.getId()) {
                    existingAccount = account;
                    break;
                }
            }
            String addAccountType = BaseAnalytics.UPDATE;
            if (existingAccount == null) {
                existingAccount = new AuthorizedAccount(currentUser.getId(), currentUser.getName(), token, currentUser.getPictureUrl());
                accounts.add(existingAccount);
                needSave = true;
                addAccountType = "new";
            } else {
                needSave = existingAccount.updateIfNeeded(token, currentUser.getPictureUrl(), currentUser.getName());
            }
            if (needSave) {
                JSONArray array = new JSONArray();
                for (AuthorizedAccount account2 : accounts) {
                    array.put(account2.toJson());
                }
                save(KEY_AUTHORIZED_ACCOUNTS, array.toString());
                removeAndroidAccount(existingAccount);
                addAndroidAccountExplicitly(existingAccount, addAccountType);
            }
        }
    }

    public void removeCurrentUser() {
        if (this.accountManager.hasCurrentUser()) {
            User currentUser = this.accountManager.getCurrentUser();
            List<AuthorizedAccount> accounts = getAuthorizedUsers();
            AuthorizedAccount accountToRemove = null;
            Iterator it = accounts.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AuthorizedAccount account = (AuthorizedAccount) it.next();
                if (currentUser.getId() == account.getId()) {
                    accountToRemove = account;
                    break;
                }
            }
            if (accountToRemove != null) {
                accounts.remove(accountToRemove);
            }
            JSONArray array = new JSONArray();
            for (AuthorizedAccount account2 : accounts) {
                array.put(account2.toJson());
            }
            save(KEY_AUTHORIZED_ACCOUNTS, array.toString());
            removeAndroidAccount(accountToRemove);
        }
    }

    @SuppressLint({"MissingPermission"})
    private void removeAndroidAccount(AuthorizedAccount accountToRemove) {
        Account[] androidAccounts;
        for (Account account : SecurityUtil.maybeGetAndroidAccounts(this.androidAccountManager)) {
            String androidAccountId = this.androidAccountManager.getUserData(account, "id");
            if (androidAccountId == null) {
                removeAndroidAccount(account);
            } else if (accountToRemove != null && Long.parseLong(androidAccountId) == accountToRemove.getId()) {
                removeAndroidAccount(account);
                return;
            }
        }
    }

    @SuppressLint({"NewApi", "MissingPermission"})
    private void removeAndroidAccount(Account account) {
        this.androidAccountManager.setUserData(account, AirbnbAuthenticator.KEY_PICTURE_URL, null);
        this.androidAccountManager.setUserData(account, "id", null);
        if (AndroidVersion.isAtLeastLollipopMR1()) {
            this.androidAccountManager.removeAccountExplicitly(account);
        } else {
            this.androidAccountManager.removeAccount(account, null, null);
        }
    }

    private void removeAllAndroidAccounts() {
        for (Account account : SecurityUtil.maybeGetAndroidAccounts(this.androidAccountManager)) {
            removeAndroidAccount(account);
        }
    }

    public void removeAllUsers() {
        save(KEY_AUTHORIZED_ACCOUNTS, "");
        removeAllAndroidAccounts();
        if (this.accountManager.hasCurrentUser()) {
            this.airbnbApi.logout();
        }
    }

    private void save(String key, String value) {
        this.debugPreferences.edit().putString(key, value).apply();
    }
}
