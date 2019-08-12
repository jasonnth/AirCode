package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.lib.C0880R;

public class WifiZenDialogFragment extends ZenDialog {
    private static final String ARG_WIRELESS_INFO = "info";
    private ListingWirelessInfo info;
    @BindView
    TextView tvNetworkName;
    @BindView
    TextView tvNetworkPassword;

    public static WifiZenDialogFragment newInstance(ListingWirelessInfo info2, int connectRequestCode) {
        WifiZenDialogFragment dialog = (WifiZenDialogFragment) new ZenBuilder(new WifiZenDialogFragment()).withTitle(C0880R.string.wifi_info).withCustomLayout().withDualButton(C0880R.string.cancel, 0, C0880R.string.connect, connectRequestCode).create();
        dialog.getArguments().putParcelable(ARG_WIRELESS_INFO, info2);
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setCustomView(inflater.inflate(C0880R.layout.dialog_wifi_info, container, false));
        ButterKnife.bind(this, view);
        this.info = (ListingWirelessInfo) getArguments().getParcelable(ARG_WIRELESS_INFO);
        this.tvNetworkName.setText(this.info.getWirelessSsid());
        this.tvNetworkPassword.setText(this.info.getWirelessPassword());
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void copyNetworkName() {
        MiscUtils.copyToClipboard(getActivity(), this.info.getWirelessSsid());
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void copyNetworkPassword() {
        MiscUtils.copyToClipboard(getActivity(), this.info.getWirelessPassword());
    }
}
