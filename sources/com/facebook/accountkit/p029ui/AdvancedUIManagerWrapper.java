package com.facebook.accountkit.p029ui;

import android.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.p029ui.UIManager.UIManagerListener;

@Deprecated
/* renamed from: com.facebook.accountkit.ui.AdvancedUIManagerWrapper */
public class AdvancedUIManagerWrapper extends BaseUIManager {
    public static final Creator<AdvancedUIManagerWrapper> CREATOR = new Creator<AdvancedUIManagerWrapper>() {
        public AdvancedUIManagerWrapper createFromParcel(Parcel source) {
            return new AdvancedUIManagerWrapper(source);
        }

        public AdvancedUIManagerWrapper[] newArray(int size) {
            return new AdvancedUIManagerWrapper[size];
        }
    };
    private final AdvancedUIManager advancedUIManager;

    public AdvancedUIManagerWrapper(AdvancedUIManager advancedUIManager2, int themeId) {
        super(themeId);
        this.advancedUIManager = advancedUIManager2;
    }

    public AdvancedUIManagerWrapper(Parcel source) {
        super(source);
        this.advancedUIManager = (AdvancedUIManager) source.readParcelable(getClass().getClassLoader());
    }

    @Deprecated
    public AdvancedUIManager getAdvancedUIManager() {
        return this.advancedUIManager;
    }

    public Fragment getBodyFragment(LoginFlowState state) {
        Fragment fragment = this.advancedUIManager.getBodyFragment(state);
        if (fragment == null) {
            return super.getBodyFragment(state);
        }
        return fragment;
    }

    public ButtonType getButtonType(LoginFlowState state) {
        return this.advancedUIManager.getButtonType(state);
    }

    public Fragment getFooterFragment(LoginFlowState state) {
        Fragment fragment = this.advancedUIManager.getFooterFragment(state);
        if (fragment == null) {
            return super.getFooterFragment(state);
        }
        return fragment;
    }

    public Fragment getHeaderFragment(LoginFlowState state) {
        Fragment fragment = this.advancedUIManager.getHeaderFragment(state);
        if (fragment == null) {
            return super.getHeaderFragment(state);
        }
        return fragment;
    }

    public TextPosition getTextPosition(LoginFlowState state) {
        return this.advancedUIManager.getTextPosition(state);
    }

    public void setUIManagerListener(UIManagerListener listener) {
        throw new RuntimeException("Use setAdvancedUIManagerListener");
    }

    public void onError(AccountKitError error) {
        this.advancedUIManager.onError(error);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.advancedUIManager, flags);
    }
}
