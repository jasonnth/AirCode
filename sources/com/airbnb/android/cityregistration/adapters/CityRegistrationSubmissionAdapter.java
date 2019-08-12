package com.airbnb.android.cityregistration.adapters;

import android.text.TextUtils;
import android.view.View;
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
import com.airbnb.android.core.viewcomponents.models.StickyButtonSpaceEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.InfoActionRow;
import com.airbnb.p027n2.components.InfoActionRowModel_;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CityRegistrationSubmissionAdapter extends AirEpoxyAdapter {
    private final SimpleTextRowEpoxyModel_ legalDisclaimerModel = new SimpleTextRowEpoxyModel_();
    private final Listener listener;
    private final DocumentMarqueeEpoxyModel_ marqueeModel = new DocumentMarqueeEpoxyModel_();

    public interface Listener {
        void editAnswersForInputGroup(ListingRegistrationProcessInputGroup listingRegistrationProcessInputGroup);
    }

    public CityRegistrationSubmissionAdapter(ListingRegistrationProcess listingRegistrationProcess, Listener listener2) {
        this.listener = listener2;
        ListingRegistrationContent content = listingRegistrationProcess.getContent();
        ListingRegistrationSubmission openSubmission = listingRegistrationProcess.getOpenSubmission();
        this.marqueeModel.titleText((CharSequence) content.getReviewTitle()).captionText((CharSequence) content.getReviewSubtitle());
        CityRegistrationUtils.addHelpLinkToMarquee(this.marqueeModel, content.getReviewHelpLinkText(), content.getHelpCenterId());
        addModel(this.marqueeModel);
        addModels((Collection<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) listingRegistrationProcess.getInputGroups()).transform(CityRegistrationSubmissionAdapter$$Lambda$1.lambdaFactory$(this)).toList());
        addModels((Collection<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) openSubmission.getAdditionalData()).transform(CityRegistrationSubmissionAdapter$$Lambda$2.lambdaFactory$(this)).toList());
        this.legalDisclaimerModel.text(content.getReviewDisclaimer()).small().show(!TextUtils.isEmpty(content.getReviewDisclaimer()));
        addModel(this.legalDisclaimerModel);
        addModel(new StickyButtonSpaceEpoxyModel_());
    }

    /* access modifiers changed from: private */
    public EpoxyModel<InfoActionRow> buildModelFromInputGroup(ListingRegistrationProcessInputGroup inputGroup) {
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
                    case Checkbox:
                        subtitles.add(question.getCheckmarkSelectedTexts());
                        break;
                    case FileUpload:
                        subtitles.add(question.getAnswerText());
                        break;
                    default:
                        String answer = question.getInputAnswer();
                        if (answer == null) {
                            break;
                        } else {
                            subtitles.add(answer);
                            break;
                        }
                }
            }
        }
        return new InfoActionRowModel_().title((CharSequence) inputGroup.getGroupSummaryTitle()).subtitleText((CharSequence) TextUtils.join("\n", subtitles)).info(C7213R.string.edit).showDivider(true).onClickListener(CityRegistrationSubmissionAdapter$$Lambda$3.lambdaFactory$(this, inputGroup));
    }

    static /* synthetic */ void lambda$buildModelFromInputGroup$0(CityRegistrationSubmissionAdapter cityRegistrationSubmissionAdapter, ListingRegistrationProcessInputGroup inputGroup, View v) {
        if (cityRegistrationSubmissionAdapter.listener instanceof Listener) {
            cityRegistrationSubmissionAdapter.listener.editAnswersForInputGroup(inputGroup);
        }
    }

    private List<String> getDisplayAddress(String str) {
        List<String> subtitles = new ArrayList<>();
        AirAddress address = CityRegistrationUtils.getAirAddressFromString(str);
        if (address != null) {
            subtitles.add(FluentIterable.m1282of(address.streetAddressOne(), address.streetAddressTwo()).filter(CityRegistrationSubmissionAdapter$$Lambda$4.lambdaFactory$()).join(Joiner.m1896on(" ")));
            subtitles.add(String.format("%1$s, %2$s %3$s", new Object[]{address.city(), address.state(), address.postalCode()}));
        }
        return subtitles;
    }

    static /* synthetic */ boolean lambda$getDisplayAddress$1(String v) {
        return !TextUtils.isEmpty(v);
    }

    /* access modifiers changed from: private */
    public EpoxyModel<InfoActionRow> buildSubmissionDataModel(ListingRegistrationSubmissionData submissionData) {
        return new InfoActionRowModel_().title((CharSequence) submissionData.getLabel()).subtitleText((CharSequence) submissionData.getValue()).showDivider(true);
    }
}
