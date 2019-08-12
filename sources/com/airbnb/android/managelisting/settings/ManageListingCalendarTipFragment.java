package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;

public class ManageListingCalendarTipFragment extends ManageListingBaseFragment {
    @BindView
    AirButton button;
    @BindView
    DocumentMarquee marquee;
    @BindView
    SimpleTextRow textRow;
    @BindView
    AirToolbar toolbar;

    public static ManageListingCalendarTipFragment create() {
        return new ManageListingCalendarTipFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_calendar_tip, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.textRow.setText((CharSequence) getCalendarTipText());
        return view;
    }

    private SpannableString getCalendarTipText() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        appendSetting(builder, C7368R.string.manage_listing_availability_settings_advance_notice_title, C7368R.string.manage_listing_availability_settings_advance_notice_info_long);
        appendSetting(builder, C7368R.string.manage_listing_availability_settings_prep_time_title, C7368R.string.manage_listing_availability_settings_prep_time_info_long);
        appendSetting(builder, C7368R.string.manage_listing_availability_settings_future_reservations_title, C7368R.string.f10086x5f233384);
        return new SpannableString(builder);
    }

    private void appendSetting(SpannableStringBuilder builder, int titleRes, int infoRes) {
        builder.append(SpannableUtils.makeBoldedString(titleRes, getContext())).append("\n").append(getString(infoRes)).append("\n\n");
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onCustomize() {
        getActivity().getSupportFragmentManager().popBackStack();
        this.controller.actionExecutor.availabilityRules();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingCalendarTip;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
