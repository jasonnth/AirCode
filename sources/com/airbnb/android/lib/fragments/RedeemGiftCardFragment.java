package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p002v7.content.res.AppCompatResources;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.GiftCard;
import com.airbnb.android.core.models.GiftCreditTemplate;
import com.airbnb.android.core.requests.ClaimGiftCardForWebLinkRequest;
import com.airbnb.android.core.requests.ClaimGiftCardRequest;
import com.airbnb.android.core.requests.GetGiftCreditTemplatesRequest;
import com.airbnb.android.core.requests.GiftCreditBalanceRequest;
import com.airbnb.android.core.responses.ClaimGiftCardResponse;
import com.airbnb.android.core.responses.GetGiftCreditTemplatesResponse;
import com.airbnb.android.core.responses.GiftCreditBalanceResponse;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.GiftCardsActivity;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.android.utils.AnimationUtils;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.rxgroups.TaggedObserver;
import icepick.State;
import java.util.regex.Pattern;
import p032rx.Observer;

public class RedeemGiftCardFragment extends AirFragment {
    private static final String ARG_WEB_LINK_GIFT_CARD = "web_link_gift_card";
    private static final String physicalGiftCardRedeemCodePattern = "^60395341[0-9]{11}$";
    final RequestListener<GiftCreditBalanceResponse> balanceRequestListener = new C0699RL().onResponse(RedeemGiftCardFragment$$Lambda$6.lambdaFactory$(this)).onError(RedeemGiftCardFragment$$Lambda$7.lambdaFactory$(this)).build();
    final RequestListener<ClaimGiftCardResponse> claimRequestListener = new C0699RL().onResponse(RedeemGiftCardFragment$$Lambda$4.lambdaFactory$(this)).onError(RedeemGiftCardFragment$$Lambda$5.lambdaFactory$(this)).build();
    @State
    protected String formattedGiftCreditBalanceTotal;
    @State
    protected String giftCardCode;
    @BindView
    EditText giftCardCodeInput;
    private final TextWatcher giftCardCodeTextWatcher = TextWatcherUtils.create(RedeemGiftCardFragment$$Lambda$3.lambdaFactory$(this));
    @BindView
    EditText giftCardPinInput;
    @State
    protected int giftCreditAmountRedeemed;
    @BindView
    AirImageView giftRedeemImg;
    @BindView
    StickyButton redeemContinueBtn;
    final RequestListener<GetGiftCreditTemplatesResponse> templateRequestListener = new C0699RL().onResponse(RedeemGiftCardFragment$$Lambda$1.lambdaFactory$(this)).onError(RedeemGiftCardFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    protected GiftCard webLinkGiftCard;

    public static RedeemGiftCardFragment newInstance() {
        return new RedeemGiftCardFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(C0880R.layout.fragment_redeem_gift_card, container, false);
        bindViews(contentView);
        if (savedInstanceState == null) {
            fetchGiftCreditTemplates();
        }
        initializeGiftCardCodeInput();
        if (getArguments() != null) {
            this.webLinkGiftCard = (GiftCard) getArguments().getParcelable("web_link_gift_card");
            this.giftCardCodeInput.setText(this.webLinkGiftCard.code());
        }
        return contentView;
    }

    private void fetchGiftCreditTemplates() {
        showLoader(true);
        GetGiftCreditTemplatesRequest templateRequest = new GetGiftCreditTemplatesRequest("Gift Credit Launch v2", 1);
        templateRequest.withListener((Observer) this.templateRequestListener);
        templateRequest.execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$0(RedeemGiftCardFragment redeemGiftCardFragment, GetGiftCreditTemplatesResponse data) {
        redeemGiftCardFragment.showLoader(false);
        redeemGiftCardFragment.giftRedeemImg.setImageUrlWithRoundedCorners(((GiftCreditTemplate) data.giftCreditTemplates.get(0)).getMainUrl());
    }

    static /* synthetic */ void lambda$new$1(RedeemGiftCardFragment redeemGiftCardFragment, AirRequestNetworkException e) {
        redeemGiftCardFragment.showLoader(false);
        Toast.makeText(redeemGiftCardFragment.getActivity(), C0880R.string.gift_card_template_fetch_error, 0).show();
        redeemGiftCardFragment.giftRedeemImg.setBackground(AppCompatResources.getDrawable(redeemGiftCardFragment.getContext(), C0880R.C0881drawable.refer_a_friend));
    }

    private void initializeGiftCardCodeInput() {
        this.giftCardCodeInput.addTextChangedListener(this.giftCardCodeTextWatcher);
        this.giftCardCodeInput.setOnFocusChangeListener(RedeemGiftCardFragment$$Lambda$8.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$initializeGiftCardCodeInput$2(RedeemGiftCardFragment redeemGiftCardFragment, View v, boolean hasFocus) {
        if (hasFocus) {
            AnimationUtils.fadeOut(redeemGiftCardFragment.giftRedeemImg, 250);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.gift_credit_menu_options, menu);
        menu.findItem(C0880R.C0882id.menu_gift_credit_help).setOnMenuItemClickListener(RedeemGiftCardFragment$$Lambda$9.lambdaFactory$(this));
    }

    public void onResume() {
        super.onResume();
        ((AirActivity) getActivity()).setupActionBar(C0880R.string.gift_card_redeem_title, new Object[0]);
        if (this.requestManager.hasRequest((BaseRequestListener<T>) this.claimRequestListener, ClaimGiftCardRequest.class)) {
            this.requestManager.resubscribe((TaggedObserver<T>) this.claimRequestListener, ClaimGiftCardRequest.class);
        } else if (this.requestManager.hasRequest((BaseRequestListener<T>) this.claimRequestListener, ClaimGiftCardForWebLinkRequest.class)) {
            this.requestManager.resubscribe((TaggedObserver<T>) this.claimRequestListener, ClaimGiftCardForWebLinkRequest.class);
        }
    }

    static /* synthetic */ void lambda$new$4(RedeemGiftCardFragment redeemGiftCardFragment, String s) {
        redeemGiftCardFragment.giftCardCode = s.trim();
        ViewUtils.setVisibleIf((View) redeemGiftCardFragment.giftCardPinInput, Pattern.matches(physicalGiftCardRedeemCodePattern, redeemGiftCardFragment.giftCardCode));
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void onGiftCardRedeemContinue() {
        ClaimGiftCardRequest request;
        showLoader(true);
        if (this.webLinkGiftCard != null) {
            request = new ClaimGiftCardForWebLinkRequest(this.giftCardCode, this.webLinkGiftCard.verificationToken(), this.claimRequestListener);
        } else {
            request = new ClaimGiftCardRequest(this.giftCardCode, this.claimRequestListener);
        }
        this.requestManager.executeOrResubscribe(request, this.claimRequestListener);
    }

    static /* synthetic */ void lambda$new$5(RedeemGiftCardFragment redeemGiftCardFragment, ClaimGiftCardResponse response) {
        redeemGiftCardFragment.showLoader(false);
        redeemGiftCardFragment.giftCreditAmountRedeemed = response.giftCredit.getAmount();
        redeemGiftCardFragment.requestGiftCreditBalance();
    }

    static /* synthetic */ void lambda$new$6(RedeemGiftCardFragment redeemGiftCardFragment, AirRequestNetworkException e) {
        redeemGiftCardFragment.showLoader(false);
        Toast.makeText(redeemGiftCardFragment.getActivity(), C0880R.string.gift_card_claim_error, 0).show();
    }

    private void requestGiftCreditBalance() {
        GiftCreditBalanceRequest balanceRequest = new GiftCreditBalanceRequest(this.mAccountManager.getCurrentUser().getId());
        balanceRequest.withListener((Observer) this.balanceRequestListener);
        balanceRequest.execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$7(RedeemGiftCardFragment redeemGiftCardFragment, GiftCreditBalanceResponse response) {
        redeemGiftCardFragment.formattedGiftCreditBalanceTotal = response.giftCreditBalance.getFormattedBalance();
        ((GiftCardsActivity) redeemGiftCardFragment.getActivity()).showCompleteGiftCardRedeemFragment(redeemGiftCardFragment.formattedGiftCreditBalanceTotal);
    }
}
