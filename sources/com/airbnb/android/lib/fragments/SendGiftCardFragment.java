package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.enums.GiftCreditAmount;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.GiftCreditTemplate;
import com.airbnb.android.core.requests.CreateGiftCardRequest;
import com.airbnb.android.core.requests.GetGiftCreditTemplatesRequest;
import com.airbnb.android.core.responses.CreateGiftCardResponse;
import com.airbnb.android.core.responses.GetGiftCreditTemplatesResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.ItemSpacingDecorator;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.GiftCardsActivity;
import com.airbnb.android.lib.activities.PayForGiftCardActivity;
import com.airbnb.android.lib.views.GroupedCell;
import com.airbnb.android.lib.views.GroupedEditTextContentCell;
import com.airbnb.android.utils.AnimationUtils;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class SendGiftCardFragment extends AirFragment {
    private static final int GIFT_CREDIT_AMOUNT_CELL_ANIMATION_DURATION = 500;
    static final String GIFT_CREDIT_TEMPLATE_CAMPAIGN_NAME = "Gift Credit Launch v2";
    private static final int REQUEST_CODE_PAY_FOR_GIFT_CREDIT = 1953;
    @State
    boolean completedGiftCreditPurchase = false;
    private View contentView;
    final RequestListener<CreateGiftCardResponse> createRequestListener = new C0699RL().onResponse(SendGiftCardFragment$$Lambda$3.lambdaFactory$(this)).onError(SendGiftCardFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    EditText emailInput;
    @State
    int giftAmount = GiftCreditAmount.GiftCreditAmount1.getAmount();
    @BindView
    GroupedCell giftAmount1Cell;
    @BindView
    GroupedCell giftAmount2Cell;
    @BindView
    GroupedCell giftAmount3Cell;
    @BindView
    GroupedCell giftAmount4Cell;
    private List<GroupedCell> giftAmountGroupedCells;
    @BindView
    GroupedEditTextContentCell giftAmountOtherCell;
    @BindView
    Button giftCardCheckoutButton;
    @BindView
    Carousel giftCardsCarousel;
    private GiftCardsAdapter giftCardsPagerAdapter;
    @State
    ArrayList<GiftCreditTemplate> giftCreditTemplates = new ArrayList<>();
    @BindView
    EditText messageInput;
    @BindView
    EditText nameInput;
    @State
    String recipientEmail;
    @State
    String recipientName;
    @BindView
    GroupedEditTextContentCell selectAmountGroupedCell;
    final RequestListener<GetGiftCreditTemplatesResponse> templateRequestListener = new C0699RL().onResponse(SendGiftCardFragment$$Lambda$1.lambdaFactory$(this)).onError(SendGiftCardFragment$$Lambda$2.lambdaFactory$(this)).build();

    static class GiftCardsAdapter extends Adapter<GiftCardViewHolder> {
        private String giftCreditAmount;
        private final List<GiftCreditTemplate> giftCreditTemplates = new ArrayList();

        static class GiftCardViewHolder extends ViewHolder {
            @BindView
            TextView amountText;
            @BindView
            AirImageView imageView;

            GiftCardViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.gift_card_v2, parent, false));
                ButterKnife.bind(this, this.itemView);
            }

            public void bind(GiftCreditTemplate giftCreditTemplate, String amount) {
                this.amountText.setText(amount);
                this.imageView.setImageUrlWithRoundedCorners(giftCreditTemplate.getMainUrl());
            }
        }

        public class GiftCardViewHolder_ViewBinding implements Unbinder {
            private GiftCardViewHolder target;

            public GiftCardViewHolder_ViewBinding(GiftCardViewHolder target2, View source) {
                this.target = target2;
                target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.iv_gift_card_background_v2, "field 'imageView'", AirImageView.class);
                target2.amountText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.tv_gift_card_amount_input, "field 'amountText'", TextView.class);
            }

            public void unbind() {
                GiftCardViewHolder target2 = this.target;
                if (target2 == null) {
                    throw new IllegalStateException("Bindings already cleared.");
                }
                this.target = null;
                target2.imageView = null;
                target2.amountText = null;
            }
        }

        GiftCardsAdapter() {
        }

        /* access modifiers changed from: 0000 */
        public void setGiftCreditTemplates(List<GiftCreditTemplate> giftCreditTemplates2) {
            this.giftCreditTemplates.clear();
            this.giftCreditTemplates.addAll(giftCreditTemplates2);
            notifyDataSetChanged();
        }

        public GiftCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GiftCardViewHolder(parent);
        }

        public void onBindViewHolder(GiftCardViewHolder holder, int position) {
            holder.bind((GiftCreditTemplate) this.giftCreditTemplates.get(position), this.giftCreditAmount);
        }

        public int getItemCount() {
            return this.giftCreditTemplates.size();
        }

        /* access modifiers changed from: 0000 */
        public void setGiftCreditAmount(String giftCreditAmount2) {
            this.giftCreditAmount = giftCreditAmount2;
            notifyDataSetChanged();
        }
    }

    public static SendGiftCardFragment newInstance() {
        return new SendGiftCardFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.contentView = inflater.inflate(C0880R.layout.fragment_send_gift_card, container, false);
        bindViews(this.contentView);
        setHasOptionsMenu(true);
        initializeGiftCardsRecyclerView();
        initializeGiftCreditAmountSelectorGroupedCell();
        setFormattedGiftCreditAmountsInAmountGroupedCells();
        updateAmountDisplayOnCellAndGiftCard();
        if (savedInstanceState == null) {
            fetchGiftCreditTemplates();
        } else {
            this.giftCardsPagerAdapter.setGiftCreditTemplates(this.giftCreditTemplates);
        }
        return this.contentView;
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard(this.contentView);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.gift_credit_menu_options, menu);
        menu.findItem(C0880R.C0882id.menu_gift_credit_help).setOnMenuItemClickListener(SendGiftCardFragment$$Lambda$5.lambdaFactory$(this));
    }

    private void fetchGiftCreditTemplates() {
        showLoader(true);
        GetGiftCreditTemplatesRequest templateRequest = new GetGiftCreditTemplatesRequest(GIFT_CREDIT_TEMPLATE_CAMPAIGN_NAME);
        templateRequest.withListener((Observer) this.templateRequestListener);
        templateRequest.execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$1(SendGiftCardFragment sendGiftCardFragment, GetGiftCreditTemplatesResponse data) {
        sendGiftCardFragment.showLoader(false);
        sendGiftCardFragment.giftCreditTemplates = data.giftCreditTemplates;
        sendGiftCardFragment.giftCardsPagerAdapter.setGiftCreditTemplates(sendGiftCardFragment.giftCreditTemplates);
    }

    static /* synthetic */ void lambda$new$2(SendGiftCardFragment sendGiftCardFragment, AirRequestNetworkException e) {
        sendGiftCardFragment.showLoader(false);
        NetworkUtil.toastGenericNetworkError(sendGiftCardFragment.getActivity());
        sendGiftCardFragment.getActivity().finish();
    }

    private void initializeGiftCreditAmountSelectorGroupedCell() {
        this.giftAmountGroupedCells = new ArrayList();
        this.giftAmountGroupedCells.add(this.giftAmount1Cell);
        this.giftAmountGroupedCells.add(this.giftAmount2Cell);
        this.giftAmountGroupedCells.add(this.giftAmount3Cell);
        this.giftAmountGroupedCells.add(this.giftAmount4Cell);
        this.giftAmountGroupedCells.add(this.giftAmountOtherCell);
        this.selectAmountGroupedCell.addEditTextTextChangedListener(TextWatcherUtils.create(SendGiftCardFragment$$Lambda$6.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$initializeGiftCreditAmountSelectorGroupedCell$3(SendGiftCardFragment sendGiftCardFragment, String s) {
        if (!s.isEmpty()) {
            sendGiftCardFragment.giftAmount = Integer.valueOf(s).intValue();
            sendGiftCardFragment.updateAmountDisplayOnCellAndGiftCard();
        }
    }

    private void initializeGiftCardsRecyclerView() {
        this.giftCardsPagerAdapter = new GiftCardsAdapter();
        this.giftCardsCarousel.setAdapter(this.giftCardsPagerAdapter);
        this.giftCardsCarousel.addItemDecoration(new ItemSpacingDecorator(getActivity(), false, C0880R.dimen.gift_credit_template_spacer));
    }

    /* access modifiers changed from: 0000 */
    @OnFocusChange
    public void onRecipientDetailsFocusChanged(boolean hasFocus) {
        if (this.nameInput != null && this.emailInput != null && !hasFocus) {
            enableSubmitButtonIfFormComplete();
        } else if (this.nameInput == null || this.emailInput == null) {
            KeyboardUtils.dismissSoftKeyboard(this.contentView);
        }
    }

    private void enableSubmitButtonIfFormComplete() {
        this.giftCardCheckoutButton.setEnabled(!this.nameInput.getText().toString().isEmpty() && !this.emailInput.getText().toString().isEmpty() && !this.giftCreditTemplates.isEmpty());
    }

    private void setFormattedGiftCreditAmountsInAmountGroupedCells() {
        for (GiftCreditAmount giftCreditAmount : GiftCreditAmount.giftCreditAmounts) {
            ((GroupedCell) this.contentView.findViewById(giftCreditAmount.getViewId())).setContent((CharSequence) this.mCurrencyHelper.formatNativeCurrency((double) giftCreditAmount.getAmount(), true));
        }
    }

    private void updateAmountDisplayOnCellAndGiftCard() {
        String formattedAmount = this.mCurrencyHelper.formatNativeCurrency((double) this.giftAmount, true);
        this.selectAmountGroupedCell.setContent(formattedAmount);
        this.selectAmountGroupedCell.showEditTextField(false);
        this.giftCardsPagerAdapter.setGiftCreditAmount(formattedAmount);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void displaySelectedGiftAmount(View view) {
        this.giftAmount = GiftCreditAmount.getGiftCreditAmountForResourceId(view.getId()).getAmount();
        updateAmountDisplayOnCellAndGiftCard();
        toggleGiftAmountCellVisibility();
    }

    @OnClick
    public void inputOtherGiftAmount() {
        toggleGiftAmountCellVisibility();
        this.selectAmountGroupedCell.showEditTextField(true);
        this.selectAmountGroupedCell.setContentVisibility(false);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void toggleGiftAmountCellVisibility() {
        if (this.giftAmount1Cell.getVisibility() == 0) {
            for (GroupedCell giftAmountGroupedCell : this.giftAmountGroupedCells) {
                AnimationUtils.fadeOut(giftAmountGroupedCell, 500);
            }
            return;
        }
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
        for (GroupedCell giftAmountGroupedCell2 : this.giftAmountGroupedCells) {
            AnimationUtils.fadeIn(giftAmountGroupedCell2, 500);
        }
    }

    @OnClick
    public void createGiftCard() {
        this.recipientName = this.nameInput.getText().toString();
        this.recipientEmail = this.emailInput.getText().toString();
        String message = this.messageInput.getText().toString();
        showLoader(true);
        new CreateGiftCardRequest(this.recipientName, this.recipientEmail, this.mCurrencyHelper.getLocalCurrencyString(), message, getSelectedTemplate().getId(), this.giftAmount, this.createRequestListener).execute(this.requestManager);
        AirbnbEventLogger.track(GiftCardsActivity.EVENT_GIFT_CARD_CHECKOUT_CLICKED, Strap.make().mo11637kv(GiftCardsActivity.EVENT_DATA_PARAM_GIFT_AMOUNT, this.giftAmount));
    }

    private GiftCreditTemplate getSelectedTemplate() {
        if (!this.giftCreditTemplates.isEmpty()) {
            return (GiftCreditTemplate) this.giftCreditTemplates.get(this.giftCardsCarousel.getClosestPosition());
        }
        throw new IllegalStateException("Templates is empty");
    }

    private void payForGiftCard(long paymentId) {
        startActivityForResult(PayForGiftCardActivity.forGiftCardAmount(getActivity(), paymentId, this.recipientName, this.recipientEmail, this.giftAmount), REQUEST_CODE_PAY_FOR_GIFT_CREDIT);
    }

    static /* synthetic */ void lambda$new$4(SendGiftCardFragment sendGiftCardFragment, CreateGiftCardResponse response) {
        sendGiftCardFragment.showLoader(false);
        sendGiftCardFragment.payForGiftCard(response.giftCreditCheckout.getPaymentId());
    }

    static /* synthetic */ void lambda$new$5(SendGiftCardFragment sendGiftCardFragment, AirRequestNetworkException e) {
        sendGiftCardFragment.showLoader(false);
        NetworkUtil.toastGenericNetworkError(sendGiftCardFragment.getActivity());
    }

    public void onResume() {
        super.onResume();
        ((AirActivity) getActivity()).setupActionBar(C0880R.string.gift_card_send_title, new Object[0]);
        enableSubmitButtonIfFormComplete();
        if (this.completedGiftCreditPurchase) {
            ((GiftCardsActivity) getActivity()).showCompleteGiftCardPurchaseFragment(this.recipientName, this.recipientEmail, this.giftAmount);
            this.completedGiftCreditPurchase = false;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            this.recipientName = data.getStringExtra("user_name");
            this.recipientEmail = data.getStringExtra(PayForGiftCardActivity.EXTRA_USER_EMAIL);
            this.giftAmount = data.getIntExtra(PayForGiftCardActivity.EXTRA_GIFT_AMOUNT, 0);
            this.completedGiftCreditPurchase = true;
        }
    }
}
