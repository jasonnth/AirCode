package com.airbnb.android.checkin;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.checkin.CheckInActionController.Listener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;

public class CheckInActionFragment extends CheckinBaseFragment {
    private static final String ARG_CHECK_IN_GUIDE = "check_in_guide";
    private CheckInActionController adapterController;
    private final Listener listener = new Listener() {
        public void onContactHostClicked(String phoneNumber) {
            PhoneUtil.showPhoneNumberActionSheet(CheckInActionFragment.this.getContext(), phoneNumber);
        }

        public void onWifiClicked(ListingWirelessInfo wifiInfo) {
            boolean hasPassword = !TextUtils.isEmpty(wifiInfo.getWirelessPassword());
            MiscUtils.copyToClipboard(CheckInActionFragment.this.getContext(), hasPassword ? wifiInfo.getWirelessPassword() : wifiInfo.getWirelessSsid(), hasPassword ? C5618R.string.check_in_action_copy_wifi_password : C5618R.string.check_in_action_copy_wifi_name);
        }
    };
    @BindView
    AirRecyclerView recyclerView;

    public static CheckInActionFragment create(CheckInGuide guide) {
        return (CheckInActionFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CheckInActionFragment()).putParcelable("check_in_guide", guide)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        boolean z = true;
        super.onCreate(savedInstanceState);
        CheckInGuide guide = (CheckInGuide) getArguments().getParcelable("check_in_guide");
        Context context = getContext();
        ListingWirelessInfo wirelessInfo = guide.getWirelessInfo();
        String phone = guide.getPhone();
        if (SanitizeUtils.zeroIfNull(guide.getNotificationStatus()) != 1) {
            z = false;
        }
        this.adapterController = new CheckInActionController(context, wirelessInfo, phone, z, this.listener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5618R.layout.fragment_check_in_guide_step, container, false);
        bindViews(view);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.adapterController);
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CheckInGuideGuestEndAction;
    }
}
