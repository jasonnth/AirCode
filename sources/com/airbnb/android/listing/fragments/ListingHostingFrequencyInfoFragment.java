package com.airbnb.android.listing.fragments;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequency;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequencyVersion;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;

public class ListingHostingFrequencyInfoFragment extends AirFragment {
    private static final String ARG_FROM_INSIGHTS = "from_insights";
    private static final String ARG_HOSTING_FREQUENCY_VERSION = "hosting_frequency_version";
    @BindView
    SimpleTextRow textRow;
    @BindView
    AirToolbar toolbar;

    public static ListingHostingFrequencyInfoFragment newInstance(DesiredHostingFrequencyVersion frequencyVersion, boolean fromInsights) {
        return (ListingHostingFrequencyInfoFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ListingHostingFrequencyInfoFragment()).putSerializable(ARG_HOSTING_FREQUENCY_VERSION, frequencyVersion)).putBoolean("from_insights", fromInsights)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7213R.layout.fragment_listing_hosting_frequency_info, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (getArguments().getBoolean("from_insights")) {
            this.toolbar.setNavigationOnClickListener(ListingHostingFrequencyInfoFragment$$Lambda$1.lambdaFactory$(this));
        }
        SpannableStringBuilder builder = new SpannableStringBuilder().append(getString(C7213R.string.manage_listing_hosting_frequency_intro));
        for (DesiredHostingFrequency option : DesiredHostingFrequency.valuesForFrequencyVersion((DesiredHostingFrequencyVersion) getArguments().getSerializable(ARG_HOSTING_FREQUENCY_VERSION))) {
            builder.append("\n\n").append(SpannableUtils.makeBoldedString(option.getTitleStringId(), getContext())).append("\n").append(getString(option.getDescriptionStringId()));
        }
        this.textRow.setText((CharSequence) new SpannableString(builder));
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingHostingFrequency;
    }
}
