package com.airbnb.android.listyourspacedls.adapters;

import android.view.View;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LabeledSectionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.epoxy.EpoxyModel;

public class HowGuestsBookAdapter extends AirEpoxyAdapter {
    private static final int SPACER_HEIGHT = 128;
    private LabeledSectionRowEpoxyModel_ hostProtectionModel;
    private LabeledSectionRowEpoxyModel_ howToBookModel;
    private final Listener listener;

    public interface Listener {
        void setIsInstantBook(boolean z);
    }

    public HowGuestsBookAdapter(Listener listener2, boolean instantBookOn) {
        this.listener = listener2;
        enableDiffing();
        setupModels(instantBookOn);
    }

    private void setupModels(boolean instantBookOn) {
        DocumentMarqueeEpoxyModel_ titleModel = new DocumentMarqueeEpoxyModel_().titleRes(C7251R.string.lys_how_guests_book_title);
        LabeledSectionRowEpoxyModel_ searchModel = new LabeledSectionRowEpoxyModel_().labelText("1").titleRes(C7251R.string.lys_how_guests_book_search_title).bodyRes(C7251R.string.lys_how_guests_book_search_description);
        this.howToBookModel = new LabeledSectionRowEpoxyModel_().labelText("2");
        LabeledSectionRowEpoxyModel_ confirmationModel = new LabeledSectionRowEpoxyModel_().labelText("3").titleRes(C7251R.string.lys_how_guests_book_get_confirmation_title).bodyRes(C7251R.string.lys_how_guests_book_get_confirmation_description);
        this.hostProtectionModel = new LabeledSectionRowEpoxyModel_().labelIconRes(C7251R.C7252drawable.ic_shield);
        updateModels(instantBookOn);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{titleModel, makeSpacer(128), searchModel, makeSpacer(128), this.howToBookModel, makeSpacer(128), confirmationModel});
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{makeSpacer(64), new SubsectionDividerEpoxyModel_(), makeSpacer(64), this.hostProtectionModel});
        addModel(makeSpacer(128));
    }

    public void updateModels(boolean instantBookOn) {
        this.howToBookModel.titleRes(instantBookOn ? C7251R.string.lys_how_guests_book_instantly_title : C7251R.string.lys_how_guests_book_request_to_book_title).bodyRes(instantBookOn ? C7251R.string.lys_how_guests_book_instantly_description : C7251R.string.lys_how_guests_book_request_to_book_description).actionRes(instantBookOn ? C7251R.string.lys_how_guests_book_request_to_book : C7251R.string.lys_how_guests_book_instant_book).clickListener(HowGuestsBookAdapter$$Lambda$1.lambdaFactory$(this, instantBookOn));
        this.hostProtectionModel.titleRes(instantBookOn ? C7251R.string.lys_how_guests_book_uhp_title : C7251R.string.lys_how_guests_book_uhp_upsell_title).bodyRes(instantBookOn ? C7251R.string.lys_how_guests_book_uhp_description : C7251R.string.lys_how_guests_book_uhp_upsell_description).actionRes(instantBookOn ? 0 : C7251R.string.lys_how_guests_book_get_uhp_cta).clickListener(HowGuestsBookAdapter$$Lambda$2.lambdaFactory$(this, instantBookOn));
        notifyModelsChanged();
    }

    static /* synthetic */ void lambda$updateModels$0(HowGuestsBookAdapter howGuestsBookAdapter, boolean instantBookOn, View v) {
        howGuestsBookAdapter.listener.setIsInstantBook(!instantBookOn);
    }

    static /* synthetic */ void lambda$updateModels$1(HowGuestsBookAdapter howGuestsBookAdapter, boolean instantBookOn, View v) {
        howGuestsBookAdapter.listener.setIsInstantBook(!instantBookOn);
    }

    private ListSpacerEpoxyModel_ makeSpacer(int spacerHeight) {
        return new ListSpacerEpoxyModel_().spaceHeight(spacerHeight);
    }
}
