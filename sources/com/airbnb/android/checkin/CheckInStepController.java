package com.airbnb.android.checkin;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.checkin.utils.CheckInTextUtils;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.GuideImageMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CheckInStepController extends AirEpoxyController {
    private final Context context;
    GuideImageMarqueeEpoxyModel_ imageMarquee;
    private final Listener listener;
    ListSpacerEpoxyModel_ noteTopSpaceRow;
    private boolean showTranslatedNote;
    private final CheckInStep step;
    SimpleTextRowEpoxyModel_ stepInstructionNote;
    private final boolean supportsTranslate;
    ToolbarSpacerEpoxyModel_ toolbarSpace;
    BasicRowEpoxyModel_ translateRow;

    public interface Listener {
        void onImageSelected();

        void onTranslationSelected(boolean z);
    }

    public CheckInStepController(Context context2, CheckInStep step2, Listener listener2, boolean supportsTranslate2, boolean showTranslatedNote2) {
        this.context = context2;
        this.step = step2;
        this.listener = listener2;
        this.supportsTranslate = supportsTranslate2;
        this.showTranslatedNote = showTranslatedNote2;
        requestModelBuild();
    }

    public void setShowTranslatedNote(boolean showTranslatedNote2) {
        if (this.showTranslatedNote != showTranslatedNote2) {
            this.showTranslatedNote = showTranslatedNote2;
            requestModelBuild();
        }
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        boolean hasImageUrl;
        boolean z = true;
        if (!TextUtils.isEmpty(this.step.getPictureUrl())) {
            hasImageUrl = true;
        } else {
            hasImageUrl = false;
        }
        if (hasImageUrl) {
            this.imageMarquee.imageUrl(this.step.getPictureUrl()).clickListener(CheckInStepController$$Lambda$1.lambdaFactory$(this));
        } else {
            this.toolbarSpace.backgroundRes(C5618R.C5619drawable.n2_scrim_gradient);
        }
        addTranslateRowOrSpace();
        String note = this.showTranslatedNote ? this.step.getLocalizedNote() : this.step.getNote();
        SimpleTextRowEpoxyModel_ layout = this.stepInstructionNote.text(note).linkifyMask(15).layout(C5618R.layout.view_holder_check_in_step_note_row);
        if (TextUtils.isEmpty(note)) {
            z = false;
        }
        layout.addIf(z, (EpoxyController) this);
    }

    /* access modifiers changed from: private */
    public void toggleTranslation() {
        this.showTranslatedNote = !this.showTranslatedNote;
        requestModelBuild();
        this.listener.onTranslationSelected(this.showTranslatedNote);
    }

    private void addTranslateRowOrSpace() {
        if (!this.supportsTranslate) {
            this.noteTopSpaceRow.spaceHeightRes(C5618R.dimen.n2_vertical_padding_small);
        } else {
            this.translateRow.titleRes(this.showTranslatedNote ? C5618R.string.checkin_translate_show_original : C5618R.string.checkin_translate).subtitleText(this.showTranslatedNote ? CheckInTextUtils.getTranslateAttribution(this.context) : null).layout(C5618R.layout.view_holder_check_in_translate_row).showDivider(false).clickListener(CheckInStepController$$Lambda$2.lambdaFactory$(this));
        }
    }
}
