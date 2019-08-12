package com.airbnb.android.listyourspacedls.adapters;

import android.os.Bundle;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.ToggleActionRow;
import icepick.State;

public class RTBChecklistAdapter extends AirEpoxyAdapter {
    private Controller controller;
    @State
    boolean lessReservationChecked;
    private ToggleActionRowEpoxyModel_ lessReservationToggle = new ToggleActionRowEpoxyModel_().titleRes(C7251R.string.lys_request_to_book_checklist_less_reservation).enabled(true).checkedChangedlistener(RTBChecklistAdapter$$Lambda$3.lambdaFactory$(this));
    @State
    boolean responseTimeChecked;
    private ToggleActionRowEpoxyModel_ responseTimeToggle = new ToggleActionRowEpoxyModel_().titleRes(C7251R.string.lys_request_to_book_checklist_response_time).enabled(true).checkedChangedlistener(RTBChecklistAdapter$$Lambda$1.lambdaFactory$(this));
    @State
    boolean searchFilterChecked;
    private ToggleActionRowEpoxyModel_ searchFilterToggle = new ToggleActionRowEpoxyModel_().titleRes(C7251R.string.lys_request_to_book_checklist_search_filter).enabled(true).checkedChangedlistener(RTBChecklistAdapter$$Lambda$2.lambdaFactory$(this));

    public interface Controller {
        void togglesUpdated();
    }

    public RTBChecklistAdapter(Controller controller2) {
        super(true);
        this.controller = controller2;
        enableDiffing();
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C7251R.string.lys_request_to_book_checklist_title));
        addModel(new StandardRowEpoxyModel_().titleRes(C7251R.string.lys_request_to_book_checklist_subtitle));
        updateModels();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.responseTimeToggle, this.searchFilterToggle, this.lessReservationToggle});
    }

    static /* synthetic */ void lambda$new$0(RTBChecklistAdapter rTBChecklistAdapter, ToggleActionRow toggleActionRow, boolean checked) {
        rTBChecklistAdapter.responseTimeChecked = checked;
        rTBChecklistAdapter.controller.togglesUpdated();
    }

    static /* synthetic */ void lambda$new$1(RTBChecklistAdapter rTBChecklistAdapter, ToggleActionRow toggleActionRow, boolean checked) {
        rTBChecklistAdapter.searchFilterChecked = checked;
        rTBChecklistAdapter.controller.togglesUpdated();
    }

    static /* synthetic */ void lambda$new$2(RTBChecklistAdapter rTBChecklistAdapter, ToggleActionRow toggleActionRow, boolean checked) {
        rTBChecklistAdapter.lessReservationChecked = checked;
        rTBChecklistAdapter.controller.togglesUpdated();
    }

    public boolean areAllChecked() {
        return this.responseTimeChecked && this.searchFilterChecked && this.lessReservationChecked;
    }

    private void updateModels() {
        this.responseTimeToggle.checked(this.responseTimeChecked);
        this.searchFilterToggle.checked(this.searchFilterChecked);
        this.lessReservationToggle.checked(this.lessReservationChecked);
        notifyModelsChanged();
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        if (savedInstanceState != null) {
            updateModels();
        }
    }
}
