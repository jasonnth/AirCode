package com.airbnb.android.registration;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.utils.BundleBuilder;

public class AccountIdentifierRegistrationFragment extends BaseRegistrationFragment {
    private static final String EMAIL_CLASS_CANONICAL_NAME = EmailRegistrationFragment.class.getCanonicalName();
    private static final String PHONE_CLASS_CANONICAL_NAME = PhoneNumberRegistrationFragment.class.getCanonicalName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1562R.layout.fragment_identifier_registration_base, container, false);
        bindViews(view);
        if (getChildFragmentManager().findFragmentById(C1562R.C1564id.user_name_child_fragment_container) == null) {
            showChildFragment(findInitialFragment(), false);
        }
        return view;
    }

    /* access modifiers changed from: protected */
    public void swapToPhone() {
        showChildFragment(buildFragmentWithClass(PHONE_CLASS_CANONICAL_NAME), false);
    }

    /* access modifiers changed from: protected */
    public void swapToEmail() {
        showChildFragment(buildFragmentWithClass(EMAIL_CLASS_CANONICAL_NAME), false);
    }

    /* access modifiers changed from: protected */
    public void showChildFragment(Fragment fragment, boolean addToBackStack) {
        showChildFragment(fragment, C1562R.C1564id.user_name_child_fragment_container, FragmentTransitionType.FadeInAndOut, addToBackStack, null);
    }

    private void showChildFragment(Fragment fragment, int fragmentContainerId, FragmentTransitionType type, boolean addToBackStack, String tag) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (type.popEnter <= 0 || type.popExit <= 0) {
            ft.setCustomAnimations(type.enter, type.exit);
        } else {
            ft.setCustomAnimations(type.enter, type.exit, type.popEnter, type.popExit);
        }
        if (addToBackStack) {
            if (tag == null) {
                tag = fragment.getTag();
            }
            ft.addToBackStack(tag);
        }
        ft.replace(fragmentContainerId, fragment);
        ft.commit();
    }

    private Fragment findInitialFragment() {
        if (this.dataPassedIn.isSocialSignUp()) {
            return buildFragmentWithClass(EMAIL_CLASS_CANONICAL_NAME);
        }
        if (!PhoneUtil.isPNSignupEnabled()) {
            return buildFragmentWithClass(EMAIL_CLASS_CANONICAL_NAME);
        }
        if (ChinaUtils.isUserInChinaOrPrefersChineseLanguage()) {
            return buildFragmentWithClass(PHONE_CLASS_CANONICAL_NAME);
        }
        return buildFragmentWithClass(EMAIL_CLASS_CANONICAL_NAME);
    }

    private Fragment buildFragmentWithClass(String fragmentClassName) {
        return Fragment.instantiate(getContext(), fragmentClassName, ((BundleBuilder) new BundleBuilder().putParcelable(BaseRegistrationFragment.ARG_ACCOUNT_REG_DATA, this.dataPassedIn)).toBundle());
    }
}
