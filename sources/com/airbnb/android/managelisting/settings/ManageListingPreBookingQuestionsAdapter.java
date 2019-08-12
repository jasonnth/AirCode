package com.airbnb.android.managelisting.settings;

import android.text.TextUtils;
import com.airbnb.android.core.models.PreBookingQuestion;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManageListingPreBookingQuestionsAdapter extends ManageListingAdapter {
    private final StandardRowEpoxyModel_ greetingRow;
    private final DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.manage_listing_required_trip_information_title).captionRes(C7213R.string.manage_listing_required_trip_information_subtitle);
    private final StandardRowEpoxyModel_ questionsRow;

    public ManageListingPreBookingQuestionsAdapter(ManageListingDataController controller) {
        super(controller);
        enableDiffing();
        this.greetingRow = new StandardRowEpoxyModel_().titleRes(C7368R.string.manage_listing_prebooking_questions_add_greeting).subtitleRes(C7368R.string.manage_listing_prebooking_questions_add_greeting_subtitile).clickListener(ManageListingPreBookingQuestionsAdapter$$Lambda$1.lambdaFactory$(controller)).actionText(C7368R.string.edit);
        this.questionsRow = new StandardRowEpoxyModel_().titleRes(C7368R.string.manage_listing_prebooking_questions_add_questions).subtitleRes(C7368R.string.manage_listing_prebooking_questions_add_questions_subtitile).clickListener(ManageListingPreBookingQuestionsAdapter$$Lambda$2.lambdaFactory$(controller)).actionText(C7368R.string.edit);
        updateModels();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.header, this.greetingRow, this.questionsRow});
        controller.addListener(this);
    }

    private void updateModels() {
        String welcomeMessage = this.controller.getListing().getInstantBookWelcomeMessage();
        if (welcomeMessage == null) {
            this.greetingRow.subtitleRes(C7368R.string.manage_listing_prebooking_questions_add_greeting_subtitile);
        } else {
            this.greetingRow.subtitle((CharSequence) welcomeMessage);
        }
        List<String> questions = new ArrayList<>();
        List<PreBookingQuestion> standardQuestions = this.controller.getListing().getBookingStandardQuestions();
        if (!ListUtils.isEmpty((Collection<?>) standardQuestions)) {
            for (int i = 0; i < standardQuestions.size(); i++) {
                PreBookingQuestion question = (PreBookingQuestion) standardQuestions.get(i);
                if (question.isChecked()) {
                    questions.add(question.getQuestion());
                }
            }
        }
        List<String> customQuestions = this.controller.getListing().getBookingCustomQuestions();
        if (!ListUtils.isEmpty((Collection<?>) customQuestions)) {
            for (int i2 = 0; i2 < customQuestions.size(); i2++) {
                questions.add(customQuestions.get(i2));
            }
        }
        if (questions.size() == 0) {
            this.questionsRow.subtitleRes(C7368R.string.manage_listing_prebooking_questions_add_questions_subtitile);
        } else {
            this.questionsRow.subtitle((CharSequence) TextUtils.join("\n", questions));
        }
    }

    public void dataUpdated() {
        updateModels();
        notifyModelsChanged();
    }

    public void dataLoading(boolean loading) {
    }
}
