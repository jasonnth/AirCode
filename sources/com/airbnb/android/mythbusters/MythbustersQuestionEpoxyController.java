package com.airbnb.android.mythbusters;

import android.content.Context;
import com.airbnb.android.core.viewcomponents.models.KickerMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;

public class MythbustersQuestionEpoxyController extends TypedAirEpoxyController<TrueFalseQuestion> {
    private final Context context;
    KickerMarqueeEpoxyModel_ kickerMarquee;
    TextRowEpoxyModel_ textRow;
    TrueFalseButtonRowEpoxyModel_ trueFalseButtonRow;

    public MythbustersQuestionEpoxyController(Context context2, TrueFalseQuestion question) {
        this.context = context2;
        setData(question);
    }

    /* access modifiers changed from: protected */
    public void buildModels(TrueFalseQuestion question) {
        this.kickerMarquee.kickerText(this.context.getString(C7485R.string.mythbusters_question_kicker, new Object[]{Integer.valueOf(1), Integer.valueOf(5)})).titleText("True or False?").addTo(this);
        this.textRow.text(question.getQuestion()).addTo(this);
        this.trueFalseButtonRow.addTo(this);
    }
}
