package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PreBookingQuestion;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManageListingPreBookingAddQuestionsAdapter extends ManageListingAdapter {
    private StandardRowEpoxyModel_ customQuestionRow;
    @State
    ArrayList<String> customQuestions = new ArrayList<>();
    private final List<ToggleActionRowEpoxyModel_> standardQuestionToggleRows = new ArrayList();
    @State
    ArrayList<Boolean> standardQuestionsChecked = new ArrayList<>();

    public ManageListingPreBookingAddQuestionsAdapter(ManageListingDataController controller) {
        super(controller);
        enableDiffing();
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_prebooking_add_questions_title).captionRes(C7368R.string.manage_listing_prebooking_questions_subitle));
        List<PreBookingQuestion> standardQuestions = controller.getListing().getBookingStandardQuestions();
        if (!ListUtils.isEmpty((Collection<?>) standardQuestions)) {
            for (int i = 0; i < standardQuestions.size(); i++) {
                int iFinal = i;
                PreBookingQuestion question = (PreBookingQuestion) standardQuestions.get(i);
                this.standardQuestionsChecked.add(Boolean.valueOf(question.isChecked()));
                this.standardQuestionToggleRows.add(new ToggleActionRowEpoxyModel_().title(question.getQuestion()).enabled(true).checked(question.isChecked()).checkedChangedlistener(ManageListingPreBookingAddQuestionsAdapter$$Lambda$1.lambdaFactory$(this, iFinal)));
            }
        }
        this.customQuestionRow = new StandardRowEpoxyModel_().titleRes(C7368R.string.manage_listing_prebooking_add_questions_custom_question).clickListener(ManageListingPreBookingAddQuestionsAdapter$$Lambda$2.lambdaFactory$(controller)).actionText(C7368R.string.edit);
        updateModels();
        addModels((Collection<? extends EpoxyModel<?>>) this.standardQuestionToggleRows);
        addModel(this.customQuestionRow);
        controller.addListener(this);
    }

    public void dataUpdated() {
        updateModels();
        notifyModelsChanged();
    }

    public void dataLoading(boolean loading) {
    }

    public boolean isUpdated(Listing listing) {
        List<String> listingCustomQuestions = listing.getBookingCustomQuestions();
        if (!ListUtils.areSameSizes(listingCustomQuestions, this.customQuestions)) {
            return true;
        }
        for (int i = 0; i < this.customQuestions.size(); i++) {
            if (!((String) listingCustomQuestions.get(i)).equals(this.customQuestions.get(i))) {
                return true;
            }
        }
        List<PreBookingQuestion> standardQuestions = listing.getBookingStandardQuestions();
        if (!ListUtils.areSameSizes(standardQuestions, this.standardQuestionsChecked)) {
            return true;
        }
        for (int i2 = 0; i2 < this.standardQuestionsChecked.size(); i2++) {
            if (((PreBookingQuestion) standardQuestions.get(i2)).isChecked() != ((Boolean) this.standardQuestionsChecked.get(i2)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private void updateModels() {
        List<String> customQuestions2 = this.controller.getListing().getBookingCustomQuestions();
        this.customQuestions.clear();
        if (customQuestions2 != null) {
            this.customQuestions.addAll(customQuestions2);
        }
        this.customQuestionRow.subtitle((CharSequence) ListUtils.isEmpty((Collection<?>) customQuestions2) ? "" : TextUtils.join("\n", customQuestions2));
        List<PreBookingQuestion> standardQuestions = this.controller.getListing().getBookingStandardQuestions();
        if (!ListUtils.isEmpty((Collection<?>) standardQuestions)) {
            for (int i = 0; i < standardQuestions.size(); i++) {
                boolean isChecked = ((PreBookingQuestion) standardQuestions.get(i)).isChecked();
                ((ToggleActionRowEpoxyModel_) this.standardQuestionToggleRows.get(i)).checked(isChecked);
                this.standardQuestionsChecked.set(i, Boolean.valueOf(isChecked));
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        if (savedInstanceState != null) {
            updateModels();
            notifyModelsChanged();
        }
    }

    public List<String> getBookingCustomQuestions() {
        return new ArrayList(this.customQuestions);
    }

    public List<PreBookingQuestion> getBookingStandardQuestions() {
        List<PreBookingQuestion> currentQuestions = new ArrayList<>();
        List<PreBookingQuestion> bookingStandardQuestions = this.controller.getListing().getBookingStandardQuestions();
        for (int i = 0; i < bookingStandardQuestions.size(); i++) {
            PreBookingQuestion question = (PreBookingQuestion) bookingStandardQuestions.get(i);
            PreBookingQuestion currentQuestion = new PreBookingQuestion();
            currentQuestion.setQuestion(question.getQuestion());
            currentQuestion.setType(question.getType());
            currentQuestion.setChecked(((Boolean) this.standardQuestionsChecked.get(i)).booleanValue());
            currentQuestions.add(currentQuestion);
        }
        return currentQuestions;
    }

    public void setRowsEnabled(boolean rowsEnabled) {
        this.customQuestionRow.enabled(rowsEnabled);
        for (int i = 0; i < this.standardQuestionToggleRows.size(); i++) {
            ((ToggleActionRowEpoxyModel_) this.standardQuestionToggleRows.get(i)).enabled(rowsEnabled);
        }
    }
}
