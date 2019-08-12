package com.airbnb.android.cityregistration.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.ListingRegistrationAnswer;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import java.util.Collection;

public class CityRegistrationDocTypeSelectionAdapter extends AirEpoxyAdapter {
    Context context;
    Listener listener;
    ListingRegistrationQuestion question;

    public interface Listener {
        void getDocPhoto(String str);
    }

    public CityRegistrationDocTypeSelectionAdapter(Context context2, final Listener listener2, final ListingRegistrationQuestion question2, Bundle savedInstanceState) {
        CharSequence charSequence;
        onRestoreInstanceState(savedInstanceState);
        this.context = context2;
        this.listener = listener2;
        this.question = question2;
        DocumentMarqueeEpoxyModel_ id = new DocumentMarqueeEpoxyModel_().m4539id(Double.valueOf(Math.random()));
        if (ListUtils.isEmpty((Collection<?>) question2.getQuestionSubtitles())) {
            charSequence = "";
        } else {
            charSequence = (CharSequence) question2.getQuestionSubtitles().get(0);
        }
        addModel(id.titleText(charSequence));
        RadioRowModelManager radioRowModelManager = new RadioRowModelManager(new com.airbnb.android.core.utils.RadioRowModelManager.Listener<String>() {
            public void onValueSelected(String value) {
                question2.setInputAnswer(value);
                listener2.getDocPhoto(value);
            }

            public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
                CityRegistrationDocTypeSelectionAdapter.this.notifyModelChanged(model);
            }
        });
        for (ListingRegistrationAnswer answer : question2.getAnswers()) {
            radioRowModelManager.addRow(buildRadioRow(answer), answer.getValue());
        }
        addModels(radioRowModelManager.getModels());
    }

    private ToggleActionRowEpoxyModel_ buildRadioRow(ListingRegistrationAnswer answer) {
        return new ToggleActionRowEpoxyModel_().m5703id(Double.valueOf(Math.random())).title(answer.getText()).subtitle(CityRegistrationUtils.textFromLines(answer.getExplanationText(), false));
    }
}
