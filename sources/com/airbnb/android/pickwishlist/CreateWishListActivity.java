package com.airbnb.android.pickwishlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.presenters.WishListPresenter;
import com.airbnb.android.core.requests.CreateWishListRequest;
import com.airbnb.android.core.responses.WishListResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.pickwishlist.PickWishListComponent.Builder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.RadioRowManager;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class CreateWishListActivity extends SolitAirActivity {
    private static final String KEY_WISHLISTABLE_DATA = "key_wishlistable_data";
    private static final long MIN_MILLIS_BETWEEN_KEYBOARD_DISMISS = 1000;
    AppRaterController appRaterController;
    @BindView
    AirButton createButton;
    final RequestListener<WishListResponse> createWishListRequestListener = new C0699RL().onResponse(CreateWishListActivity$$Lambda$4.lambdaFactory$(this)).onError(CreateWishListActivity$$Lambda$5.lambdaFactory$(this)).build();
    private WishListableData data;
    private long lastKeyboardDismissTime;
    @BindView
    DocumentMarquee marquee;
    @BindView
    InlineInputRow nameInput;
    private final TextWatcher nameTextWatcher = TextWatcherUtils.createEmptyWatcher(CreateWishListActivity$$Lambda$1.lambdaFactory$(this));
    @BindView
    ToggleActionRow privateToggle;
    @State
    boolean privateWishList = false;
    @BindView
    ToggleActionRow publicToggle;
    private RadioRowManager<Boolean> radioRowManager;
    @BindView
    VerboseScrollView scrollView;
    @BindView
    AirToolbar toolbar;
    WishListLogger wlLogger;

    public static Intent intent(Context context, WishListableData data2) {
        return new Intent(context, CreateWishListActivity.class).putExtra("key_wishlistable_data", data2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((PickWishListBindings) CoreApplication.instance(this).componentProvider()).pickWishListComponentProvider().get()).build().inject(this);
        setContentView(C7614R.layout.activity_create_wish_list);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        this.data = (WishListableData) getIntent().getParcelableExtra("key_wishlistable_data");
        initNameInput(savedInstanceState);
        this.scrollView.setOnScrollListener(CreateWishListActivity$$Lambda$6.lambdaFactory$(this));
        if (this.data.hasDates() && Experiments.showImprovedWishListFiltersUx()) {
            this.marquee.setCaption((CharSequence) WishListPresenter.formatDatesAndGuestCount(this, this.data));
        }
        this.radioRowManager = new RadioRowManager<>(CreateWishListActivity$$Lambda$7.lambdaFactory$(this));
        this.radioRowManager.addToggleActionRow(this.publicToggle, Boolean.valueOf(false));
        this.radioRowManager.addToggleActionRow(this.privateToggle, Boolean.valueOf(true));
        this.radioRowManager.setCurrentValue(Boolean.valueOf(this.privateWishList));
    }

    static /* synthetic */ void lambda$onCreate$0(CreateWishListActivity createWishListActivity, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - createWishListActivity.lastKeyboardDismissTime > 1000) {
            KeyboardUtils.dismissSoftKeyboard((View) createWishListActivity.scrollView);
            createWishListActivity.lastKeyboardDismissTime = currentTime;
        }
    }

    static /* synthetic */ void lambda$onCreate$1(CreateWishListActivity createWishListActivity, Boolean privateWishList2) {
        createWishListActivity.privateWishList = privateWishList2.booleanValue();
        KeyboardUtils.dismissSoftKeyboard((Activity) createWishListActivity);
    }

    private void initNameInput(Bundle savedInstanceState) {
        EditText editText = this.nameInput.getEditText();
        editText.setSelectAllOnFocus(true);
        String suggestedName = this.data.suggestedWishListName();
        editText.setHint(suggestedName);
        if (savedInstanceState == null) {
            editText.setText(suggestedName);
        }
        editText.addTextChangedListener(this.nameTextWatcher);
        editText.setSingleLine();
        editText.setImeOptions(6);
        editText.setOnFocusChangeListener(CreateWishListActivity$$Lambda$8.lambdaFactory$(this));
        editText.setOnEditorActionListener(CreateWishListActivity$$Lambda$9.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$initNameInput$2(CreateWishListActivity createWishListActivity, View v, boolean hasFocus) {
        if (!hasFocus) {
            KeyboardUtils.dismissSoftKeyboard((Activity) createWishListActivity);
        }
    }

    static /* synthetic */ boolean lambda$initNameInput$3(CreateWishListActivity createWishListActivity, TextView v, int actionId, KeyEvent event) {
        if (!KeyboardUtils.isEnterOrDone(actionId, event)) {
            return false;
        }
        createWishListActivity.publicToggle.requestFocus();
        KeyboardUtils.dismissSoftKeyboard((View) createWishListActivity.nameInput);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    private boolean hasExistingWishListWithName(String wishListName) {
        for (WishList wishList : this.wishListManager.getWishLists()) {
            if (wishListName.equalsIgnoreCase(wishList.getName())) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ void lambda$new$4(CreateWishListActivity createWishListActivity, boolean empty) {
        createWishListActivity.createButton.setEnabled(!empty);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onCreateClicked() {
        if (this.createButton.getState() == AirButton.State.Normal) {
            String name = this.nameInput.getInputText().trim();
            if (hasExistingWishListWithName(name)) {
                new SnackbarWrapper().title(getResources().getString(C7614R.string.wishlist_name_already_taken), true).view(this.nameInput).duration(0).buildAndShow();
                return;
            }
            KeyboardUtils.dismissSoftKeyboard((Activity) this);
            this.createButton.setState(AirButton.State.Loading);
            new CreateWishListRequest(name, this.data, this.privateWishList).withListener((Observer) this.createWishListRequestListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$6(CreateWishListActivity createWishListActivity, WishListResponse response) {
        createWishListActivity.appRaterController.incrementSignificantEvent();
        createWishListActivity.wishListManager.deleteItemFromAllWishLists(createWishListActivity.data);
        createWishListActivity.wlLogger.clickCreateWishList(createWishListActivity.data, response.wishList);
        createWishListActivity.wishListManager.addNewWishList(response.wishList, createWishListActivity.data);
        createWishListActivity.createButton.setState(AirButton.State.Success);
        createWishListActivity.setResult(-1);
        createWishListActivity.createButton.postDelayed(CreateWishListActivity$$Lambda$10.lambdaFactory$(createWishListActivity), 600);
    }

    static /* synthetic */ void lambda$null$5(CreateWishListActivity createWishListActivity) {
        if (createWishListActivity.isActivityResumed()) {
            createWishListActivity.finish();
        }
    }

    static /* synthetic */ void lambda$new$7(CreateWishListActivity createWishListActivity, AirRequestNetworkException error) {
        createWishListActivity.createButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(createWishListActivity.createButton, error);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.createButton.getState() == AirButton.State.Success && !isFinishing()) {
            finish();
        }
    }

    public void finish() {
        super.finish();
        KeyboardUtils.dismissSoftKeyboard((Activity) this);
    }
}
