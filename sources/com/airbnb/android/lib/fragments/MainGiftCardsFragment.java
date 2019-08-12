package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.GiftCard;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.GiftCardsActivity;
import icepick.State;

public class MainGiftCardsFragment extends AirFragment {
    private static final String ARG_WEB_LINK_GIFT_CARD = "web_link_gift_card";
    @State
    protected String giftBalance;
    @BindView
    Button redeemGiftCredit;
    @BindView
    Button sendGiftCredit;
    @State
    protected GiftCard webLinkGiftCard;

    public static MainGiftCardsFragment newInstance() {
        return new MainGiftCardsFragment();
    }

    public static MainGiftCardsFragment newInstance(GiftCard giftCard) {
        Bundle arguments = new Bundle();
        arguments.putParcelable("web_link_gift_card", giftCard);
        MainGiftCardsFragment newFragment = newInstance();
        newFragment.setArguments(arguments);
        return newFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            this.webLinkGiftCard = (GiftCard) getArguments().getParcelable("web_link_gift_card");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(C0880R.layout.fragment_gift_cards_main, container, false);
        bindViews(contentView);
        return contentView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.gift_credit_main_menu_options, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.menu_gift_credit_terms) {
            return super.onOptionsItemSelected(item);
        }
        startActivity(WebViewIntentBuilder.newBuilder(getActivity(), getString(C0880R.string.tos_gift_credit)).title(C0880R.string.ml_terms).toIntent());
        return true;
    }

    public void onResume() {
        super.onResume();
        ((AirActivity) getActivity()).setupActionBar(C0880R.string.gift_cards, new Object[0]);
    }

    @OnClick
    public void onSendCardClick() {
        ((GiftCardsActivity) getActivity()).startSendGiftCard();
    }

    @OnClick
    public void redeemGiftCard() {
        ((GiftCardsActivity) getActivity()).startRedeemGiftCard();
    }
}
