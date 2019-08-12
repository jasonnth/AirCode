package com.airbnb.android.mythbusters;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.p000v4.app.Fragment;
import butterknife.ButterKnife;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.mythbusters.TrueFalseQuestion.TrueFalseAnswer;

public class MythbustersActivity extends AirActivity {
    private TrueFalseQuestion questionOne;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C7485R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        this.questionOne = TrueFalseQuestion.builder().question(getString(C7485R.string.mythbusters_question_one)).answerExplanation("").correctAnswer(TrueFalseAnswer.TRUE).build();
        showFragment(MythbustersQuestionFragment.create(this.questionOne));
    }

    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        IcepickWrapper.saveInstanceState(this, outState);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    private void showFragment(Fragment fragment) {
        showFragment(fragment, C7485R.C7487id.content_container, FragmentTransitionType.SlideInFromSide, true, fragment.getClass().getCanonicalName());
    }
}
