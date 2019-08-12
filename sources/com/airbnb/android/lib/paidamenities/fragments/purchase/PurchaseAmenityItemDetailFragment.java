package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.intents.QuickPayActivityIntents;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.clientparameters.PaidAmenityClientParameters;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StepperRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.utils.PaidAmenityUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;
import icepick.State;

public class PurchaseAmenityItemDetailFragment extends BasePurchaseAmenityFragment {
    private static final String ARG_PAID_AMENITY = "paid_amenity";
    private static final int DEFAULT_NUM_SERVINGS = 1;
    private static final int REQUEST_CODE_PURCHASE_PAID_AMENITY = 666;
    public static final String TAG = PurchaseAmenityItemDetailFragment.class.getSimpleName();
    private PurchaseAmenityItemDetailAdapter adapter;
    @State
    int amenityServingsCount = 1;
    @State
    PaidAmenity paidAmenity;
    @BindView
    RecyclerView recyclerView;
    @BindView
    PrimaryButton reviewAndPayButton;
    @BindView
    AirToolbar toolbar;

    public class PurchaseAmenityItemDetailAdapter extends AirEpoxyAdapter {
        private final SimpleTextRowEpoxyModel_ amenityDetailDescRowModel = new SimpleTextRowEpoxyModel_();
        private final SimpleTextRowEpoxyModel_ amenityDisclaimerRowModel = new SimpleTextRowEpoxyModel_();
        private final StepperRowEpoxyModel_ amenityNumServingRowModel = new StepperRowEpoxyModel_();
        private final StandardRowEpoxyModel_ amenityPriceRowModel = new StandardRowEpoxyModel_();
        private final DocumentMarqueeEpoxyModel_ documentMarqueeModel = new DocumentMarqueeEpoxyModel_();

        public PurchaseAmenityItemDetailAdapter() {
            configureDocumentMarquee();
            configureAmenityDetails();
            configureAmenityDisclaimer();
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.documentMarqueeModel, this.amenityPriceRowModel, this.amenityNumServingRowModel, this.amenityDetailDescRowModel});
        }

        private void configureAmenityDisclaimer() {
            this.amenityDisclaimerRowModel.textRes(C0880R.string.purchase_amenities_disclaimer).small().showDivider(false);
        }

        private void configureDocumentMarquee() {
            this.documentMarqueeModel.titleText((CharSequence) PurchaseAmenityItemDetailFragment.this.paidAmenity.getTitle());
        }

        private void configureAmenityDetails() {
            this.amenityPriceRowModel.title((CharSequence) PaidAmenityUtils.getFormattedPrice(PurchaseAmenityItemDetailFragment.this.getContext(), PurchaseAmenityItemDetailFragment.this.paidAmenity.getLocalizedTotalPrice()));
            this.amenityNumServingRowModel.textRes(C0880R.string.purchase_amenities_num_servings_title).minValue(1).value(PurchaseAmenityItemDetailFragment.this.amenityServingsCount).valueChangedListener(C7100x3ece98df.lambdaFactory$(this));
            this.amenityDetailDescRowModel.text(PurchaseAmenityItemDetailFragment.this.paidAmenity.getDescription());
        }

        static /* synthetic */ void lambda$configureAmenityDetails$0(PurchaseAmenityItemDetailAdapter purchaseAmenityItemDetailAdapter, int oldValue, int value) {
            purchaseAmenityItemDetailAdapter.amenityNumServingRowModel.value(value);
            PurchaseAmenityItemDetailFragment.this.amenityServingsCount = value;
        }
    }

    public static PurchaseAmenityItemDetailFragment newInstance(PaidAmenity paidAmenity2) {
        return (PurchaseAmenityItemDetailFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PurchaseAmenityItemDetailFragment()).putParcelable(ARG_PAID_AMENITY, paidAmenity2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_purchase_amenity_item_detail, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.paidAmenity = (PaidAmenity) getArguments().getParcelable(ARG_PAID_AMENITY);
        this.adapter = new PurchaseAmenityItemDetailAdapter();
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickReviewAndPayButton() {
        CartItem cartItem = getCartItem(getPaidAmenityBillingParameters());
        this.paidAmenityJitneyLogger.logGuestAddClickReview();
        startActivityForResult(QuickPayActivityIntents.intentForPaidAmenity(getContext(), cartItem, null), REQUEST_CODE_PURCHASE_PAID_AMENITY);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == REQUEST_CODE_PURCHASE_PAID_AMENITY) {
            this.navigationController.doneWithEditAmenityDetail();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private PaidAmenityClientParameters getPaidAmenityBillingParameters() {
        return PaidAmenityClientParameters.builder().paidAmenityId(this.paidAmenity.getId()).numberOfUnits(this.amenityServingsCount).reservationConfirmationCode(this.confirmationCode).build();
    }

    private CartItem getCartItem(PaidAmenityClientParameters paidAmenityClientParameters) {
        return CartItem.builder().quickPayParameters(paidAmenityClientParameters).title(this.paidAmenity.getTitle()).description(this.paidAmenity.getDescription()).build();
    }
}
