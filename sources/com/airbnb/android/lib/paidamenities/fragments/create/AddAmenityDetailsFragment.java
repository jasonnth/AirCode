package com.airbnb.android.lib.paidamenities.fragments.create;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.models.PaidAmenityCategory;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class AddAmenityDetailsFragment extends BaseCreateAmenityFragment {
    public static final String ARG_PREDEFINED_TYPE = "predefined_paid_amenity_type";
    private AddAmenityDetailsAdapter adapter;
    @State
    String amenityDescription;
    @BindView
    AirButton nextButton;
    @State
    PaidAmenityCategory paidAmenityCategory;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public class AddAmenityDetailsAdapter extends AirEpoxyAdapter {
        StandardRowEpoxyModel_ descriptionRowModel = new StandardRowEpoxyModel_();
        SimpleTextRowEpoxyModel_ disclosureTextRowModel = new SimpleTextRowEpoxyModel_();
        DocumentMarqueeEpoxyModel_ documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        StandardRowEpoxyModel_ titleRowModel = new StandardRowEpoxyModel_();

        public AddAmenityDetailsAdapter() {
            this.documentMarqueeModel.titleRes(AddAmenityDetailsFragment.this.paidAmenityCategory.getAmenityServerType().getMarqueeTitleRes());
            this.titleRowModel.titleRes(AddAmenityDetailsFragment.this.paidAmenityCategory.getAmenityServerType().getTitleRes());
            this.descriptionRowModel.title(C0880R.string.paid_amenities_add_details_description).subtitle((CharSequence) AddAmenityDetailsFragment.this.amenityDescription).actionText((CharSequence) TextUtils.isEmpty(AddAmenityDetailsFragment.this.amenityDescription) ? AddAmenityDetailsFragment.this.getString(C0880R.string.paid_amenities_add_details_empty_description_action) : AddAmenityDetailsFragment.this.getString(C0880R.string.paid_amenities_add_details_edit_description)).clickListener(AddAmenityDetailsFragment$AddAmenityDetailsAdapter$$Lambda$1.lambdaFactory$(this));
            this.disclosureTextRowModel.text(AddAmenityDetailsFragment.this.paidAmenityDetails.paidAmenityType().getDisclosureText()).small().showDivider(false);
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.documentMarqueeModel, this.titleRowModel, this.descriptionRowModel, this.disclosureTextRowModel});
        }

        /* access modifiers changed from: private */
        public void updateDescription(String description) {
            this.descriptionRowModel.subtitle((CharSequence) description).actionText(C0880R.string.paid_amenities_add_details_edit_description);
            notifyModelChanged(this.descriptionRowModel);
        }
    }

    public static AddAmenityDetailsFragment newInstance(PaidAmenityCategory paidAmenityCategory2) {
        return (AddAmenityDetailsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new AddAmenityDetailsFragment()).putParcelable(ARG_PREDEFINED_TYPE, paidAmenityCategory2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_add_paid_amenity_details, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.paidAmenityCategory = (PaidAmenityCategory) getArguments().getParcelable(ARG_PREDEFINED_TYPE);
        this.nextButton.setEnabled(areDetailsPopulated());
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.adapter = new AddAmenityDetailsAdapter();
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        this.navigationController.doneWithAddAmenityDetails();
    }

    public void updateDescription(String description) {
        this.amenityDescription = description;
        this.adapter.updateDescription(description);
        this.nextButton.setEnabled(areDetailsPopulated());
    }

    private boolean areDetailsPopulated() {
        return !TextUtils.isEmpty(this.amenityDescription);
    }
}
