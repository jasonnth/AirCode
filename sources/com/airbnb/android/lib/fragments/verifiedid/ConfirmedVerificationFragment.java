package com.airbnb.android.lib.fragments.verifiedid;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.airbnb.android.lib.C0880R;

public class ConfirmedVerificationFragment extends Fragment {
    private static final String VERIFICATION_HEADER_EXTRA = "verification_header";
    private static final String VERIFICATION_TEXT_EXTRA = "verification_text";

    public static ConfirmedVerificationFragment newInstance(String verificationHeader, String verificationText) {
        ConfirmedVerificationFragment fragment = new ConfirmedVerificationFragment();
        Bundle arguments = new Bundle();
        arguments.putString(VERIFICATION_HEADER_EXTRA, verificationHeader);
        arguments.putString(VERIFICATION_TEXT_EXTRA, verificationText);
        fragment.setArguments(arguments);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_verified_id_confirmed_verification, container, false);
        ((TextView) view.findViewById(C0880R.C0882id.confirmed_verification_header)).setText(getVerificationHeader());
        ((TextView) view.findViewById(C0880R.C0882id.confirmed_verification_description)).setText(getVerificationText());
        return view;
    }

    public String getVerificationHeader() {
        return getArguments().getString(VERIFICATION_HEADER_EXTRA);
    }

    public String getVerificationText() {
        return getArguments().getString(VERIFICATION_TEXT_EXTRA);
    }
}
