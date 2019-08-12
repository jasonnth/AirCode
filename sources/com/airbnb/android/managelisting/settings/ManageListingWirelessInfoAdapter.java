package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.models.ListingWirelessInfo.WirelessTypes;
import com.airbnb.android.core.requests.WirelessInfoRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.Strap;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Objects;
import icepick.State;
import org.spongycastle.crypto.tls.CipherSuite;

public class ManageListingWirelessInfoAdapter extends AirEpoxyAdapter {
    @State
    boolean enabled = true;
    private final OnValidInputListener listener;
    @State
    String wifiPassword;
    @State
    String wifiSsid;
    private final InlineInputRowEpoxyModel_ wirelessPasswordInput;
    private final InlineInputRowEpoxyModel_ wirelessSsidInput;

    public interface OnValidInputListener {
        void onInputChanged(boolean z);
    }

    public ManageListingWirelessInfoAdapter(Context context, Listing listing, Bundle savedInstanceState, OnValidInputListener listener2) {
        onRestoreInstanceState(savedInstanceState);
        enableDiffing();
        this.listener = listener2;
        ListingWirelessInfo wirelessInfo = listing.getWirelessInfo();
        if (savedInstanceState == null && wirelessInfo != null) {
            this.wifiSsid = wirelessInfo.getWirelessSsid();
            this.wifiPassword = wirelessInfo.getWirelessPassword();
        }
        DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_wireless_info_title);
        this.wirelessSsidInput = new InlineInputRowEpoxyModel_().input(this.wifiSsid).titleRes(C7368R.string.manage_listing_wireless_info_network_name).inputType(524288).focusChangeListener(ManageListingWirelessInfoAdapter$$Lambda$1.lambdaFactory$(this)).inputChangedListener(ManageListingWirelessInfoAdapter$$Lambda$2.lambdaFactory$(this));
        String deviceSsidName = NetworkUtil.getWifiSsid(context);
        if (!TextUtils.isEmpty(deviceSsidName)) {
            this.wirelessSsidInput.tip(context.getString(C7368R.string.manage_listing_wireless_info_network_name_tip, new Object[]{deviceSsidName})).tipMaxLine(1).tipValue(deviceSsidName).autoHideTipOnInputChange(true);
        }
        this.wirelessPasswordInput = new InlineInputRowEpoxyModel_().input(this.wifiPassword).titleRes(C7368R.string.manage_listing_wireless_info_network_password).inputType(CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA).inputChangedListener(ManageListingWirelessInfoAdapter$$Lambda$3.lambdaFactory$(this));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{header, this.wirelessSsidInput, this.wirelessPasswordInput});
    }

    static /* synthetic */ void lambda$new$1(ManageListingWirelessInfoAdapter manageListingWirelessInfoAdapter, String value) {
        manageListingWirelessInfoAdapter.wifiSsid = value;
        manageListingWirelessInfoAdapter.notifyListener();
    }

    static /* synthetic */ void lambda$new$2(ManageListingWirelessInfoAdapter manageListingWirelessInfoAdapter, String input) {
        manageListingWirelessInfoAdapter.wifiPassword = input;
        manageListingWirelessInfoAdapter.notifyListener();
    }

    public void setEnabled(boolean enabled2) {
        this.enabled = enabled2;
        this.wirelessSsidInput.enabled(enabled2);
        this.wirelessPasswordInput.enabled(enabled2);
        notifyModelsChanged();
    }

    public boolean hasEmptySsid() {
        return TextUtils.isEmpty(this.wifiSsid);
    }

    public boolean hasChanged(Listing listing) {
        ListingWirelessInfo wirelessInfo = listing.getWirelessInfo();
        if (wirelessInfo == null) {
            if (!TextUtils.isEmpty(this.wifiSsid) || !TextUtils.isEmpty(this.wifiPassword)) {
                return true;
            }
            return false;
        } else if (!Objects.equal(this.wifiSsid, wirelessInfo.getWirelessSsid()) || !Objects.equal(this.wifiPassword, wirelessInfo.getWirelessPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public Strap getUpdatedWifiInfo() {
        return Strap.make().mo11639kv(WirelessInfoRequest.JSON_SSID_KEY, this.wifiSsid).mo11639kv(WirelessInfoRequest.JSON_PASSWORD_KEY, this.wifiPassword).mo11639kv(WirelessInfoRequest.JSON_TYPE_KEY, WirelessTypes.UNKNOWN.type);
    }

    /* access modifiers changed from: private */
    public void handleOnWifiNameFocusChanged(boolean hasFocus) {
        if (this.enabled && !hasFocus && TextUtils.isEmpty(this.wifiSsid)) {
            this.wirelessPasswordInput.input(null);
            notifyModelChanged(this.wirelessPasswordInput);
        }
    }

    private void notifyListener() {
        this.listener.onInputChanged(!TextUtils.isEmpty(this.wifiSsid) || TextUtils.isEmpty(this.wifiPassword));
    }
}
