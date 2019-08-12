package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.enums.ListingRegistrationInputType;
import com.airbnb.android.core.models.ListingRegistrationAnswer;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.CityRegistrationIconActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.CityRegistrationToggleRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.components.CityRegistrationToggleRow;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

public class CityRegistrationInputGroupAdapter extends AirEpoxyAdapter {
    private final HashMap<ListingRegistrationQuestion, ErrorStateListener> displayedQuestions = Maps.newHashMap();
    private final ListingRegistrationProcessInputGroup inputGroup;
    private final HashMap<String, InlineInputRowEpoxyModel_> inputKeyEpoxyModelMap = new HashMap<>();
    private final Listener listener;
    private final Context mContext;

    public interface ErrorStateListener {
        void setErrorState(boolean z);
    }

    public interface Listener {
        void enableNextButton(boolean z);
    }

    public interface ListenerV2 extends Listener {
        String getDocFileUrl(String str);

        void showDateSelection(ListingRegistrationQuestion listingRegistrationQuestion);

        void showDocUploadReviewWithUrl(ListingRegistrationQuestion listingRegistrationQuestion, String str);

        void showDocumentTypeSelection(ListingRegistrationQuestion listingRegistrationQuestion);

        void showFileDeletionConfirmationDialog(ListingRegistrationQuestion listingRegistrationQuestion);
    }

    public CityRegistrationInputGroupAdapter(ListingRegistrationProcessInputGroup inputGroup2, Listener listener2, Bundle savedInstanceState, Context context) {
        this.listener = listener2;
        this.inputGroup = inputGroup2;
        this.mContext = context;
        onRestoreInstanceState(savedInstanceState);
        DocumentMarqueeEpoxyModel_ documentMarquee = new DocumentMarqueeEpoxyModel_().titleText((CharSequence) inputGroup2.getGroupTitle()).captionText((CharSequence) inputGroup2.getSubtitleString());
        CityRegistrationUtils.addHelpLinkToMarquee(documentMarquee, inputGroup2.getGroupHelpLink());
        addModel(documentMarquee);
        ListingRegistrationQuestion radioQuestion = null;
        for (String questionId : inputGroup2.getRootQuestions()) {
            ListingRegistrationQuestion question = inputGroup2.getQuestionFromInputKey(questionId);
            if (question != null) {
                switch (question.getInputType()) {
                    case Radio:
                        addRadioQuestion(question);
                        radioQuestion = question;
                        break;
                    case Text:
                        addTextQuestion(question);
                        break;
                    case Dropdown:
                        addDropdownQuestion(question);
                        break;
                    case Address:
                        addAddressQuestion(question);
                        break;
                    case FileUpload:
                        addFileUploadQuestion(question);
                        break;
                    case Checkbox:
                        addCheckBoxQuestion(question);
                        break;
                    case DateSelection:
                        addDateSelectionQuestion(question);
                        break;
                }
            }
        }
        if (radioQuestion != null) {
            showHideTextInputWithRadioValue(radioQuestion);
        }
    }

    /* access modifiers changed from: private */
    public void showHideTextInputWithRadioValue(ListingRegistrationQuestion radioQuestion) {
        String showHideQuestionInputKey = null;
        boolean isShown = false;
        Iterator it = radioQuestion.getAnswers().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ListingRegistrationAnswer answer = (ListingRegistrationAnswer) it.next();
            if (answer.getNextQuestionKey() != null) {
                showHideQuestionInputKey = answer.getNextQuestionKey();
                isShown = CityRegistrationUtils.equals(answer.getValue(), radioQuestion.getInputAnswer());
                break;
            }
        }
        if (showHideQuestionInputKey != null && this.inputKeyEpoxyModelMap.containsKey(showHideQuestionInputKey)) {
            InlineInputRowEpoxyModel_ row = (InlineInputRowEpoxyModel_) this.inputKeyEpoxyModelMap.get(showHideQuestionInputKey);
            row.show(isShown);
            if (!isShown) {
                this.inputGroup.getQuestionFromInputKey(showHideQuestionInputKey).setInputAnswer(null);
                row.input(null);
            }
        }
    }

    private void addRadioQuestion(final ListingRegistrationQuestion question) {
        RadioRowModelManager radioRowModelManager = new RadioRowModelManager(new com.airbnb.android.core.utils.RadioRowModelManager.Listener<String>() {
            public void onValueSelected(String value) {
                question.setInputAnswer(value);
                CityRegistrationInputGroupAdapter.this.showHideTextInputWithRadioValue(question);
                CityRegistrationInputGroupAdapter.this.validateInputsWithUpdateErrorState(false);
            }

            public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
                CityRegistrationInputGroupAdapter.this.notifyModelChanged(model);
            }
        });
        for (ListingRegistrationAnswer answer : question.getAnswers()) {
            radioRowModelManager.addRow(buildRadioRow(answer), answer.getValue());
        }
        if (!TextUtils.isEmpty(question.getInputAnswer())) {
            radioRowModelManager.setSelectedValue(question.getInputAnswer());
        }
        addModels(radioRowModelManager.getModels());
        this.displayedQuestions.put(question, null);
    }

    private ToggleActionRowEpoxyModel_ buildRadioRow(ListingRegistrationAnswer answer) {
        ToggleActionRowEpoxyModel_ row = new ToggleActionRowEpoxyModel_().title(answer.getText());
        if (answer.getExplanationText() != null && answer.getExplanationText().size() > 0) {
            row.subtitle(CityRegistrationUtils.textFromLines(answer.getExplanationText(), false));
        }
        return row;
    }

    private void addAddressQuestion(ListingRegistrationQuestion question) {
    }

    private void addTextQuestion(ListingRegistrationQuestion question) {
        InlineInputRowEpoxyModel_ input = new InlineInputRowEpoxyModel_().title(question.getQuestionText()).input(question.getInputAnswer());
        question.getClass();
        InlineInputRowEpoxyModel_ inputRow = input.inputChangedListener(CityRegistrationInputGroupAdapter$$Lambda$1.lambdaFactory$(question));
        addModel(inputRow);
        this.displayedQuestions.put(question, CityRegistrationInputGroupAdapter$$Lambda$2.lambdaFactory$(this, inputRow));
        this.inputKeyEpoxyModelMap.put(question.getInputKey(), inputRow);
    }

    static /* synthetic */ void lambda$addTextQuestion$0(CityRegistrationInputGroupAdapter cityRegistrationInputGroupAdapter, InlineInputRowEpoxyModel_ inputRow, boolean v) {
        inputRow.showError(v);
        cityRegistrationInputGroupAdapter.notifyModelChanged(inputRow);
    }

    private void addDropdownQuestion(ListingRegistrationQuestion question) {
    }

    private void addCheckBoxQuestion(ListingRegistrationQuestion question) {
        TreeSet<String> checkedSet = new TreeSet<>();
        if (question.getInputAnswer() != null) {
            for (String checked : question.getInputAnswer().split(",")) {
                checkedSet.add(checked);
            }
        }
        for (ListingRegistrationAnswer answer : question.getAnswers()) {
            CityRegistrationToggleRowEpoxyModel_ row = new CityRegistrationToggleRowEpoxyModel_().title(answer.getText()).showDivider(true).checked(checkedSet.contains(answer.getValue()));
            row.checkChangedListener(CityRegistrationInputGroupAdapter$$Lambda$3.lambdaFactory$(checkedSet, answer, question, row));
            if (answer.getExplanationText() != null && answer.getExplanationText().size() > 0) {
                row.subtitle(CityRegistrationUtils.textFromLines(answer.getExplanationText(), false));
            }
            addModel(row);
        }
    }

    static /* synthetic */ void lambda$addCheckBoxQuestion$1(TreeSet checkedSet, ListingRegistrationAnswer answer, ListingRegistrationQuestion question, CityRegistrationToggleRowEpoxyModel_ row, CityRegistrationToggleRow toggleRow, boolean checked) {
        if (checked) {
            checkedSet.add(answer.getValue());
        } else {
            checkedSet.remove(answer.getValue());
        }
        StringBuilder csv = new StringBuilder();
        Iterator it = checkedSet.iterator();
        while (it.hasNext()) {
            String currentAnswers = (String) it.next();
            if (csv.length() > 0) {
                csv.append(",");
            }
            csv.append(currentAnswers);
        }
        question.setInputAnswer(csv.toString());
        row.checked(checked);
    }

    private void addFileUploadQuestion(ListingRegistrationQuestion question) {
        if (this.listener instanceof ListenerV2) {
            String docUrl = ((ListenerV2) this.listener).getDocFileUrl(question.getInputKey());
            ListingRegistrationAnswer answer = null;
            if (question.getInputAnswer() != null) {
                answer = question.getAnswer(question.getInputAnswer());
            }
            if (docUrl == null) {
                StandardRowEpoxyModel_ standardRow = new StandardRowEpoxyModel_().title((CharSequence) question.getQuestionText()).subtitle((CharSequence) answer == null ? question.getQuestionTextShort() : answer.getText()).actionText(C7213R.string.add).showDivider(true).actionText(docUrl == null ? C7213R.string.add : C7213R.string.edit).clickListener(CityRegistrationInputGroupAdapter$$Lambda$4.lambdaFactory$(this, question));
                addModel(standardRow);
                this.displayedQuestions.put(question, CityRegistrationInputGroupAdapter$$Lambda$5.lambdaFactory$(this, standardRow));
                return;
            }
            addModel(new CityRegistrationIconActionRowEpoxyModel_().title(question.getQuestionText()).subtitle(new AirTextBuilder(this.mContext).appendBold(String.format(this.mContext.getResources().getString(C7213R.string.cityregistration_uploaded_document_confirmation), new Object[]{answer.getText()})).build()).actionRes(C7213R.string.remove).iconDrawableRes(C7213R.C7214drawable.n2_ic_radio_button_selected).clickListener(CityRegistrationInputGroupAdapter$$Lambda$6.lambdaFactory$(this, question)));
        }
    }

    private void addDateSelectionQuestion(ListingRegistrationQuestion question) {
        InlineInputRowEpoxyModel_ dateSelectionRow = new InlineInputRowEpoxyModel_().title(question.getQuestionText()).input(question.getInputAnswer()).hintRes(C7213R.string.cityregistration_date_input_placeholder).inputType(16).clickListener(CityRegistrationInputGroupAdapter$$Lambda$7.lambdaFactory$(this, question));
        addModel(dateSelectionRow);
        this.displayedQuestions.put(question, CityRegistrationInputGroupAdapter$$Lambda$8.lambdaFactory$(this, dateSelectionRow));
        this.inputKeyEpoxyModelMap.put(question.getInputKey(), dateSelectionRow);
    }

    static /* synthetic */ void lambda$addDateSelectionQuestion$6(CityRegistrationInputGroupAdapter cityRegistrationInputGroupAdapter, InlineInputRowEpoxyModel_ dateSelectionRow, boolean v) {
        dateSelectionRow.showError(v);
        cityRegistrationInputGroupAdapter.notifyModelChanged(dateSelectionRow);
    }

    public boolean validateInputsWithUpdateErrorState(boolean shouldUpdateErrorState) {
        boolean areAllInputsValid = true;
        boolean enableNextButton = true;
        for (Entry<ListingRegistrationQuestion, ErrorStateListener> entry : this.displayedQuestions.entrySet()) {
            ListingRegistrationQuestion question = (ListingRegistrationQuestion) entry.getKey();
            ErrorStateListener errorStateListener = (ErrorStateListener) entry.getValue();
            boolean isValidQuestion = validateQuestion(question);
            if (shouldUpdateErrorState && errorStateListener != null) {
                errorStateListener.setErrorState(!isValidQuestion);
            }
            if (!isValidQuestion) {
                areAllInputsValid = false;
                if (question.getInputType() == ListingRegistrationInputType.Radio) {
                    enableNextButton = false;
                }
            }
            if (question.getInputType() == ListingRegistrationInputType.FileUpload) {
                if (!enableNextButton || question.getInputAnswer() == null) {
                    enableNextButton = false;
                } else {
                    enableNextButton = true;
                }
            }
        }
        this.listener.enableNextButton(enableNextButton);
        return areAllInputsValid;
    }

    private boolean validateQuestion(ListingRegistrationQuestion question) {
        if (this.inputKeyEpoxyModelMap.containsKey(question.getInputKey()) && !((InlineInputRowEpoxyModel_) this.inputKeyEpoxyModelMap.get(question.getInputKey())).isShown()) {
            return true;
        }
        return !question.isIsRequired().booleanValue() || !TextUtils.isEmpty(question.getInputAnswer());
    }
}
