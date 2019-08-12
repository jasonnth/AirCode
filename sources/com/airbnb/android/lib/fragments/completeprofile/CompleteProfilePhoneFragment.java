package com.airbnb.android.lib.fragments.completeprofile;

import android.os.Bundle;
import android.support.p000v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.enums.CompleteProfileType;
import com.airbnb.android.core.requests.UpdatePhoneNumberRequest;
import com.airbnb.android.core.views.LoaderFrame.LoaderFrameDisplay;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.CircleBadgeView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import icepick.State;

public class CompleteProfilePhoneFragment extends CompleteProfileBaseFragment {
    private static final String ARG_EDIT_PHONE = "arg_edit_phone";
    private static final String SAVED_PHONE = "phone";
    @State
    boolean isEditPhoneNumber;
    private PhoneNumber mPhoneNumber;

    public interface PhoneAccountListener {
        void onPhoneAccountUpdateError();

        void onPhoneAccountUpdateSuccess();
    }

    public static CompleteProfilePhoneFragment newInstance(boolean isEditProfile) {
        return (CompleteProfilePhoneFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CompleteProfilePhoneFragment()).putBoolean(ARG_EDIT_PHONE, isEditProfile)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            this.mPhoneNumber = (PhoneNumber) savedInstanceState.getSerializable("phone");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("phone", this.mPhoneNumber);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_complete_profile_phone, container, false);
        getActionBar().setTitle(C0880R.string.title_phone_number);
        ((CircleBadgeView) view.findViewById(C0880R.C0882id.circle_badge_view)).initializeAsInactiveBadge();
        getCompleteProfileActivity().showProgressBar();
        if (savedInstanceState == null) {
            if (getCompleteProfileActivity().getType() == CompleteProfileType.EDIT_PROFILE_VERIFY_PHONE) {
                displayPhoneVerificationCodeEntry(getCompleteProfileActivity().getPhoneNumberToVerify());
            } else {
                displayPhoneNumberEntry();
            }
        }
        if (getArguments() != null) {
            this.isEditPhoneNumber = getArguments().getBoolean(ARG_EDIT_PHONE, false);
        }
        return view;
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
    }

    public void displayPhoneVerificationCodeEntry(String phoneNumberToVerify) {
        showChildFragment(CompleteProfilePhoneCodeChildFragment.newInstance(phoneNumberToVerify));
    }

    public void displayPhoneNumberEntry() {
        FragmentManager fm = getChildFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            showChildFragment(CompleteProfilePhoneChildFragment.newInstance());
        }
    }

    public PhoneNumber getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

    public void updateAccountPhoneNumber(String phoneNumber, PhoneAccountListener listener) {
        makePhoneAccountUpdateRequest(false, phoneNumber, listener);
    }

    public void updateAccountPhoneCode(String phoneCode, PhoneAccountListener listener) {
        makePhoneAccountUpdateRequest(true, phoneCode, listener);
    }

    private void makePhoneAccountUpdateRequest(boolean isVerify, String phoneParam, final PhoneAccountListener listener) {
        ((LoaderFrameDisplay) getActivity()).displayLoaderFrame(true);
        NonResubscribableRequestListener<Object> requestListener = new NonResubscribableRequestListener<Object>() {
            public void onResponse(Object response) {
                if (CompleteProfilePhoneFragment.this.getActivity() != null) {
                    ((LoaderFrameDisplay) CompleteProfilePhoneFragment.this.getActivity()).displayLoaderFrame(false);
                    listener.onPhoneAccountUpdateSuccess();
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                if (CompleteProfilePhoneFragment.this.getActivity() != null) {
                    ((LoaderFrameDisplay) CompleteProfilePhoneFragment.this.getActivity()).displayLoaderFrame(false);
                    listener.onPhoneAccountUpdateError();
                }
            }
        };
        if (isVerify) {
            UpdatePhoneNumberRequest.verifyPhoneNumber(phoneParam, requestListener).execute(this.requestManager);
        } else {
            UpdatePhoneNumberRequest.addPhoneNumber(phoneParam, requestListener).execute(this.requestManager);
        }
    }
}
