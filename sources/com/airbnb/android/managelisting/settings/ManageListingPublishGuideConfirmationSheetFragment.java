package com.airbnb.android.managelisting.settings;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.HeroMarquee;

public class ManageListingPublishGuideConfirmationSheetFragment extends ManageListingBaseFragment {
    @BindView
    HeroMarquee heroMarquee;
    @BindView
    AirToolbar toolbar;

    public static Fragment create() {
        return new ManageListingPublishGuideConfirmationSheetFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_hero_marquee_sheet, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        Drawable icon = getResources().getDrawable(C7368R.C7369drawable.n2_ic_entire_place);
        DrawableCompat.setTint(icon, ContextCompat.getColor(getContext(), C7368R.color.n2_white));
        this.heroMarquee.setIcon(icon);
        this.heroMarquee.setTitle(C7368R.string.manage_listing_check_in_guide_publish_confirmation_title);
        this.heroMarquee.setCaption(C7368R.string.manage_listing_check_in_guide_publish_confirmation_caption);
        this.heroMarquee.setFirstButtonText(C7368R.string.done);
        this.heroMarquee.setFirstButtonClickListener(ManageListingPublishGuideConfirmationSheetFragment$$Lambda$1.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonText(C7368R.string.manage_listing_check_in_guide_make_changes_button);
        this.heroMarquee.setSecondButtonClickListener(ManageListingPublishGuideConfirmationSheetFragment$$Lambda$2.lambdaFactory$(this));
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingCheckinGuidePublishConfirmation;
    }
}
