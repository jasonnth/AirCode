package com.airbnb.android.profile_completion;

import android.content.Context;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.List;

public class ProfileCompletionEpoxyController extends AirEpoxyController {
    private final Context context;
    private final OnClickIncompleteStepListener incompleteStepOnClickListener;
    private final ProfileCompletionManager profileCompletionManager;

    public interface OnClickIncompleteStepListener {
        void onClick(CompletionStep completionStep);
    }

    public ProfileCompletionEpoxyController(Context context2, ProfileCompletionManager profileCompletionManager2, OnClickIncompleteStepListener incompleteStepOnClickListener2) {
        this.context = context2;
        this.profileCompletionManager = profileCompletionManager2;
        this.incompleteStepOnClickListener = incompleteStepOnClickListener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        buildHeaderModel();
        buildStepModels();
    }

    private void buildHeaderModel() {
        String titleText;
        int stepsLeft = this.profileCompletionManager.getIncompleteSteps().size();
        if (stepsLeft > 0) {
            titleText = this.context.getResources().getQuantityString(C7646R.plurals.profile_completion_x_steps_left, stepsLeft, new Object[]{Integer.valueOf(stepsLeft)});
        } else {
            titleText = this.context.getResources().getString(C7646R.string.profile_completion_steps_youre_all_set);
        }
        new SectionHeaderEpoxyModel_().m5556id((CharSequence) "header_model").showDivider(false).title(titleText).addTo(this);
    }

    private void buildStepModels() {
        List<CompletionStep> steps = this.profileCompletionManager.getAllSteps();
        List<CompletionStep> completedSteps = this.profileCompletionManager.getCompletedSteps();
        for (CompletionStep step : steps) {
            StandardRowEpoxyModel_ stepModel = new StandardRowEpoxyModel_().m5604id((CharSequence) step.toString()).title(step.getTitle()).showDivider(true);
            if (completedSteps.contains(step)) {
                stepModel.rowDrawableRes(C7646R.C7647drawable.n2_ic_check_babu);
            } else {
                stepModel.rowDrawableRes(C7646R.C7647drawable.n2_ic_check_inactive);
                stepModel.clickListener(ProfileCompletionEpoxyController$$Lambda$1.lambdaFactory$(this, step));
            }
            stepModel.addTo(this);
        }
    }
}
