package com.airbnb.android.identity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.SheetMarquee;
import icepick.State;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountVerificationIdExpiredFragment extends BaseAccountVerificationFragment {
    private static final String ARG_EXPIRATION_DATE_STRING = "arg_expiration_date_string";
    private static final String ARG_OFFLINE_ID_TYPE = "arg_offline_id_type";
    @State
    GovernmentIdType governmentIdType;
    @BindView
    SheetMarquee sheetMarquee;

    public static AccountVerificationIdExpiredFragment newInstance(Context context, Date expirationDate, GovernmentIdType governmentIdType2) {
        return (AccountVerificationIdExpiredFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AccountVerificationIdExpiredFragment()).putString(ARG_EXPIRATION_DATE_STRING, new SimpleDateFormat(context.getString(C6533R.string.id_expiration_date_format)).format(expirationDate))).putSerializable(ARG_OFFLINE_ID_TYPE, governmentIdType2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_id_expired, container, false);
        bindViews(view);
        this.sheetMarquee.setSubtitle(getString(C6533R.string.account_verification_id_expired_desc, getArguments().getString(ARG_EXPIRATION_DATE_STRING)));
        this.governmentIdType = (GovernmentIdType) getArguments().getSerializable(ARG_OFFLINE_ID_TYPE);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void rescanId() {
        getFragmentManager().popBackStack();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationScanIdErrorExpired;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv("event_data", this.governmentIdType.toString());
    }
}
