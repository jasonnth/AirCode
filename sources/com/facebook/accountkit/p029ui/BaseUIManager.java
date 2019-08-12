package com.facebook.accountkit.p029ui;

import android.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.p029ui.UIManager.UIManagerListener;

/* renamed from: com.facebook.accountkit.ui.BaseUIManager */
public class BaseUIManager implements Parcelable, UIManager {
    public static final Creator<BaseUIManager> CREATOR = new Creator<BaseUIManager>() {
        public BaseUIManager createFromParcel(Parcel source) {
            return new BaseUIManager(source);
        }

        public BaseUIManager[] newArray(int size) {
            return new BaseUIManager[size];
        }
    };
    public static final int THEME_ID_NOT_SET = -1;
    private Fragment bodyFragment;
    private LoginFlowState flowState;
    private Fragment footerFragment;
    private Fragment headerFragment;
    protected UIManagerListener listener;
    private int themeId;

    public BaseUIManager(int themeId2) {
        this.themeId = themeId2;
        this.flowState = LoginFlowState.NONE;
    }

    protected BaseUIManager(Parcel source) {
        this.themeId = source.readInt();
        this.flowState = LoginFlowState.values()[source.readInt()];
    }

    public int getThemeId() {
        return this.themeId;
    }

    public void setThemeId(int themeId2) {
        this.themeId = themeId2;
    }

    /* access modifiers changed from: protected */
    public void updateFlowState(LoginFlowState state) {
        if (this.flowState != state) {
            this.flowState = state;
            this.headerFragment = null;
            this.bodyFragment = null;
            this.footerFragment = null;
        }
    }

    public Fragment getBodyFragment(LoginFlowState state) {
        updateFlowState(state);
        if (this.bodyFragment != null) {
            return this.bodyFragment;
        }
        this.bodyFragment = getDefaultBodyFragment(this, this.flowState);
        return this.bodyFragment;
    }

    static Fragment getDefaultBodyFragment(UIManager uiManager, LoginFlowState flowState2) {
        switch (flowState2) {
            case ACCOUNT_VERIFIED:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_sent_code_center);
            case CONFIRM_ACCOUNT_VERIFIED:
                return StaticContentFragmentFactory.create(uiManager, flowState2);
            case CODE_INPUT:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_confirmation_code_center);
            case EMAIL_INPUT:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_email_login_center);
            case EMAIL_VERIFY:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_email_verify_center);
            case ERROR:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_error_center);
            case PHONE_NUMBER_INPUT:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_phone_login_center);
            case SENDING_CODE:
            case CONFIRM_INSTANT_VERIFICATION_LOGIN:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_sending_code_center);
            case SENT_CODE:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_sent_code_center);
            case VERIFIED:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_verified_code_center);
            case VERIFYING_CODE:
                return StaticContentFragmentFactory.create(uiManager, flowState2, C3354R.layout.com_accountkit_fragment_verifying_code_center);
            default:
                return StaticContentFragmentFactory.create(uiManager, flowState2);
        }
    }

    public ButtonType getButtonType(LoginFlowState state) {
        updateFlowState(state);
        return null;
    }

    public Fragment getFooterFragment(LoginFlowState state) {
        updateFlowState(state);
        if (this.footerFragment != null) {
            return this.footerFragment;
        }
        this.footerFragment = getDefaultFooterFragment(this);
        return this.footerFragment;
    }

    static Fragment getDefaultFooterFragment(UIManager uiManager) {
        return TitleFragmentFactory.create(uiManager);
    }

    public Fragment getHeaderFragment(LoginFlowState state) {
        updateFlowState(state);
        return this.headerFragment;
    }

    static Fragment getDefaultHeaderFragment(UIManager uiManager, LoginFlowState state, LoginType loginType) {
        int titleResourceId;
        switch (state) {
            case ACCOUNT_VERIFIED:
                titleResourceId = C3354R.string.com_accountkit_account_verified;
                break;
            case CONFIRM_ACCOUNT_VERIFIED:
            case CONFIRM_INSTANT_VERIFICATION_LOGIN:
                titleResourceId = C3354R.string.com_accountkit_account_verified;
                break;
            case CODE_INPUT:
                titleResourceId = C3354R.string.com_accountkit_confirmation_code_title;
                break;
            case EMAIL_INPUT:
                titleResourceId = C3354R.string.com_accountkit_email_login_title;
                break;
            case EMAIL_VERIFY:
                titleResourceId = C3354R.string.com_accountkit_email_verify_title;
                break;
            case ERROR:
                switch (loginType) {
                    case PHONE:
                        titleResourceId = C3354R.string.com_accountkit_phone_error_title;
                        break;
                    default:
                        titleResourceId = C3354R.string.com_accountkit_error_title;
                        break;
                }
            case PHONE_NUMBER_INPUT:
                titleResourceId = C3354R.string.com_accountkit_phone_login_title;
                break;
            case SENDING_CODE:
                switch (loginType) {
                    case PHONE:
                        titleResourceId = C3354R.string.com_accountkit_phone_loading_title;
                        break;
                    case EMAIL:
                        titleResourceId = C3354R.string.com_accountkit_email_loading_title;
                        break;
                    default:
                        throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.UNEXPECTED_STATE);
                }
            case SENT_CODE:
                titleResourceId = C3354R.string.com_accountkit_sent_title;
                break;
            case VERIFIED:
                titleResourceId = C3354R.string.com_accountkit_success_title;
                break;
            case VERIFYING_CODE:
                titleResourceId = C3354R.string.com_accountkit_verify_title;
                break;
            case RESEND:
                titleResourceId = C3354R.string.com_accountkit_resend_title;
                break;
            default:
                titleResourceId = -1;
                break;
        }
        if (titleResourceId > -1) {
            return TitleFragmentFactory.create(uiManager, titleResourceId, new String[0]);
        }
        return TitleFragmentFactory.create(uiManager);
    }

    public TextPosition getTextPosition(LoginFlowState state) {
        updateFlowState(state);
        return TextPosition.BELOW_BODY;
    }

    public void setUIManagerListener(UIManagerListener listener2) {
        this.listener = listener2;
    }

    public void onError(AccountKitError error) {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.themeId);
        dest.writeInt(this.flowState.ordinal());
    }
}
