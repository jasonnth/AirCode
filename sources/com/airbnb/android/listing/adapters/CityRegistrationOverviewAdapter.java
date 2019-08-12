package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.NumberedSimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import java.util.List;

public class CityRegistrationOverviewAdapter extends AirEpoxyAdapter {

    public interface Listener {
        void addExistingLicense();
    }

    public CityRegistrationOverviewAdapter(ListingRegistrationContent content, Listener listener) {
        DocumentMarqueeEpoxyModel_ documentMarquee = new DocumentMarqueeEpoxyModel_().titleText((CharSequence) content.getIntroTitle()).captionText((CharSequence) content.getIntroSubtitle());
        CityRegistrationUtils.addHelpLinkToMarquee(documentMarquee, content);
        addModel(documentMarquee);
        List<String> explanationSteps = content.getExplanationSteps();
        addModel(new SimpleTextRowEpoxyModel_().text((CharSequence) explanationSteps.get(0)).plusAndTightPadding());
        for (int i = 1; i < explanationSteps.size(); i++) {
            NumberedSimpleTextRowEpoxyModel_ step = new NumberedSimpleTextRowEpoxyModel_().content((CharSequence) explanationSteps.get(i)).stepNumber(i);
            if (i == explanationSteps.size() - 1) {
                step.paddingBottom().showDivider(true);
            } else {
                step.tightPadding();
            }
            addModel(step);
        }
        addModel(new LinkActionRowEpoxyModel_().text(content.getUnregisteredExemptedCallToAction()).clickListener(CityRegistrationOverviewAdapter$$Lambda$1.lambdaFactory$(listener)).showDivider(true));
    }
}
