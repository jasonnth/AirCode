package com.airbnb.android.listing.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.MicroSectionHeader;

public class HouseRulesLegalInfoFragment extends AirFragment {
    @BindView
    MicroSectionHeader infantsTitle;
    @BindView
    MicroSectionHeader petsTitle;
    @BindView
    AirToolbar toolbar;

    public static HouseRulesLegalInfoFragment newInstance() {
        return new HouseRulesLegalInfoFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7213R.layout.fragment_house_rules_legal_info, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.infantsTitle.setPaddingTop(0);
        this.petsTitle.setPaddingTopRes(C7213R.dimen.n2_vertical_padding_tiny);
        return view;
    }

    @OnClick
    public void onInfantsLearnMoreClicked() {
        startActivity(HelpCenterIntents.intentForHelpCenterArticle(getContext(), HelpCenterArticle.INFANTS_HOUSE_RULES_LEGAL_INFO).toIntent());
    }

    @OnClick
    public void onPetsLearnMoreClicked() {
        startActivity(HelpCenterIntents.intentForHelpCenterArticle(getContext(), HelpCenterArticle.ASSISTANCE_ANIMALS_LEGAL_INFO).toIntent());
    }
}
