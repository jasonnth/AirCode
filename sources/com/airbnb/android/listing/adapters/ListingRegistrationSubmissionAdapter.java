package com.airbnb.android.listing.adapters;

import android.text.TextUtils;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.core.models.ListingRegistrationSubmission;
import com.airbnb.android.core.models.ListingRegistrationSubmissionData;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StickyButtonSpaceEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.StandardRow;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListingRegistrationSubmissionAdapter extends AirEpoxyAdapter {
    private final SimpleTextRowEpoxyModel_ legalDisclaimerModel = new SimpleTextRowEpoxyModel_();
    private final DocumentMarqueeEpoxyModel_ marqueeModel = new DocumentMarqueeEpoxyModel_();

    public ListingRegistrationSubmissionAdapter(ListingRegistrationProcess listingRegistrationProcess) {
        ListingRegistrationContent content = listingRegistrationProcess.getContent();
        ListingRegistrationSubmission openSubmission = listingRegistrationProcess.getOpenSubmission();
        this.marqueeModel.titleText((CharSequence) content.getReviewTitle()).captionText((CharSequence) content.getReviewSubtitle());
        CityRegistrationUtils.addHelpLinkToMarquee(this.marqueeModel, content.getReviewHelpLinkText(), content.getHelpCenterId());
        addModel(this.marqueeModel);
        addModels((Collection<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) listingRegistrationProcess.getInputGroups()).transform(ListingRegistrationSubmissionAdapter$$Lambda$1.lambdaFactory$(this)).toList());
        addModels((Collection<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) openSubmission.getAdditionalData()).transform(ListingRegistrationSubmissionAdapter$$Lambda$2.lambdaFactory$(this)).toList());
        this.legalDisclaimerModel.text(content.getReviewDisclaimer()).small().show(!TextUtils.isEmpty(content.getReviewDisclaimer()));
        addModel(this.legalDisclaimerModel);
        addModel(new StickyButtonSpaceEpoxyModel_());
    }

    /* access modifiers changed from: private */
    public EpoxyModel<StandardRow> buildModelFromInputGroup(ListingRegistrationProcessInputGroup inputGroup) {
        List<String> subtitles = new ArrayList<>();
        for (String questionKey : inputGroup.getRootQuestions()) {
            ListingRegistrationQuestion question = inputGroup.getQuestionFromInputKey(questionKey);
            if (question != null) {
                switch (question.getInputType()) {
                    case Radio:
                        subtitles.add(question.getRadioSelectedText());
                        break;
                    case Address:
                        subtitles.addAll(getDisplayAddress(question.getInputAnswer()));
                        break;
                    default:
                        subtitles.add(question.getInputAnswer());
                        break;
                }
            }
        }
        return new StandardRowEpoxyModel_().title((CharSequence) inputGroup.getGroupSummaryTitle()).subtitle((CharSequence) TextUtils.join("\n", subtitles));
    }

    private List<String> getDisplayAddress(String str) {
        List<String> subtitles = new ArrayList<>();
        AirAddress address = CityRegistrationUtils.getAirAddressFromString(str);
        if (address != null) {
            subtitles.add(FluentIterable.m1282of(address.streetAddressOne(), address.streetAddressTwo()).filter(ListingRegistrationSubmissionAdapter$$Lambda$3.lambdaFactory$()).join(Joiner.m1896on(" ")));
            subtitles.add(String.format("%1$s, %2$s %3$s", new Object[]{address.city(), address.state(), address.postalCode()}));
        }
        return subtitles;
    }

    static /* synthetic */ boolean lambda$getDisplayAddress$0(String v) {
        return !TextUtils.isEmpty(v);
    }

    /* access modifiers changed from: private */
    public EpoxyModel<StandardRow> buildSubmissionDataModel(ListingRegistrationSubmissionData submissionData) {
        return new StandardRowEpoxyModel_().title((CharSequence) submissionData.getLabel()).subtitle((CharSequence) submissionData.getValue());
    }
}
