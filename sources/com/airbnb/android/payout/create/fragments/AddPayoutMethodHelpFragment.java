package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.models.PayoutFormField;
import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.util.List;

public class AddPayoutMethodHelpFragment extends BaseAddPayoutMethodFragment {
    private AddPayoutMethodHelpEpoxyController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static final class AddPayoutMethodHelpEpoxyController extends TypedAirEpoxyController<List<PayoutFormField>> {
        DocumentMarqueeEpoxyModel_ documentMarqueeModel;

        /* access modifiers changed from: protected */
        public void buildModels(List<PayoutFormField> fields) {
            this.documentMarqueeModel.titleRes(C7552R.string.add_account_info_help_title);
            for (PayoutFormField field : fields) {
                if (field.helperText() != null && !field.confirmField()) {
                    new BasicRowEpoxyModel_().m4351id((long) field.hashCode()).titleText(field.label()).subtitleText(field.helperText()).addTo(this);
                }
            }
        }
    }

    public class AddPayoutMethodHelpEpoxyController_EpoxyHelper extends ControllerHelper<AddPayoutMethodHelpEpoxyController> {
        private final AddPayoutMethodHelpEpoxyController controller;

        public AddPayoutMethodHelpEpoxyController_EpoxyHelper(AddPayoutMethodHelpEpoxyController controller2) {
            this.controller = controller2;
        }

        public void resetAutoModels() {
            this.controller.documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
            this.controller.documentMarqueeModel.m4534id(-1);
            setControllerToStageTo(this.controller.documentMarqueeModel, this.controller);
        }
    }

    public static AddPayoutMethodHelpFragment newInstance() {
        return new AddPayoutMethodHelpFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_payout_recycler_view_with_toolbar, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.epoxyController = new AddPayoutMethodHelpEpoxyController();
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.epoxyController.setData(this.dataController.getPayoutFormManager().getPayoutFormFields());
    }
}
