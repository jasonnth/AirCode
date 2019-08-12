package com.airbnb.android.cityregistration.adapters;

import android.content.Context;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.core.models.ListingRegistrationContentObject;
import com.airbnb.android.core.models.ListingRegistrationContentSection;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.NumberedSimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import java.util.List;

public class CityRegistrationOverviewAdapter extends CityRegistrationContentSectionAdapter {

    public interface Listener {
        void addExistingLicense();
    }

    public CityRegistrationOverviewAdapter(ListingRegistrationContent content, Listener listener, Context context) {
        if (content.getInitialOverviewSection() != null) {
            ListingRegistrationContentSection initialOverviewSection = content.getInitialOverviewSection();
            DocumentMarqueeEpoxyModel_ documentMarquee = new DocumentMarqueeEpoxyModel_().titleText((CharSequence) initialOverviewSection.getTitle());
            ListingRegistrationContentObject subtitle = (ListingRegistrationContentObject) initialOverviewSection.getSubtitles().get(0);
            if (subtitle != null) {
                documentMarquee.captionText(buildContentText(subtitle, Boolean.valueOf(false), context));
            }
            CityRegistrationUtils.addHelpLinkToMarquee(documentMarquee, subtitle.getLink());
            addModel(documentMarquee);
            addListingRegistrationContentObjects(initialOverviewSection.getBody(), context);
        } else {
            DocumentMarqueeEpoxyModel_ documentMarquee2 = new DocumentMarqueeEpoxyModel_().titleText((CharSequence) content.getIntroTitle()).captionText((CharSequence) content.getIntroSubtitle());
            CityRegistrationUtils.addHelpLinkToMarquee(documentMarquee2, content);
            addModel(documentMarquee2);
            List<String> explanationSteps = content.getExplanationSteps();
            addModel(new SimpleTextRowEpoxyModel_().text((CharSequence) explanationSteps.get(0)).plusAndTightPadding());
            for (int i = 1; i < explanationSteps.size(); i++) {
                addModel(new NumberedSimpleTextRowEpoxyModel_().content((CharSequence) explanationSteps.get(i)).stepNumber(i).tightPadding());
            }
        }
        addModel(new SubsectionDividerEpoxyModel_().layout(C5630R.layout.view_holder_subsection_divider_with_top_padding));
        addModel(new LinkActionRowEpoxyModel_().text(content.getUnregisteredExemptedCallToAction()).clickListener(CityRegistrationOverviewAdapter$$Lambda$1.lambdaFactory$(listener)).showDivider(true));
    }
}
