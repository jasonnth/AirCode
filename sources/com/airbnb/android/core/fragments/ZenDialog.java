package com.airbnb.android.core.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.FragmentLauncher;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import com.airbnb.p027n2.utils.LinkTouchMovementMethod;
import com.squareup.otto.Bus;

public class ZenDialog extends AirDialogFragment implements FragmentLauncher {
    private static final String ARG_LARGE_HEADER = "large_header";
    private static final String ARG_MATCH_PARENT_WIDTH = "match_parent_width";
    private static final String BODY_HTML_TEXT = "text_html_body";
    private static final String BODY_LINKED_TEXT = "text_body_linked_text";
    private static final String BODY_LINKED_URL = "text_body_linked_url";
    private static final String BODY_TEXT = "text_body";
    private static final String BODY_TEXT_URL = "text_body_url";
    private static final String CUSTOM_LAYOUT = "custom_layout";
    private static final String DUAL_BUTTON_NEGATIVE_REQUEST_CODE = "req_code_dual_negative_button";
    private static final String DUAL_BUTTON_POSITIVE_REQUEST_CODE = "req_code_dual_positive_button";
    private static final String DUAL_LEFT_BUTTON = "dual_left_button";
    private static final String DUAL_RIGHT_BUTTON = "dual_right_button";
    private static final String GRAY_CANCEL_BUTTON = "gray_cancel_button";
    private static final String HAS_BODY_TEXT_SELECTABLE = "has_text_body_selectable";
    private static final String HAS_LAYOUT = "has_layout";
    private static final String HAS_LISTVIEW = "has_listview";
    private static final String HEADER_TITLE = "header_title";
    private static final String LISTVIEW_NO_DIVIDER = "no_list_dividers";
    private static final String SEND_RESULT_ON_CANCEL = "result_on_cancel";
    private static final String SINGLE_BUTTON = "single_button";
    private static final String SINGLE_BUTTON_REQUEST_CODE = "req_code_single_button";
    public static final double WIDTH_PERCENT = 0.8d;
    /* access modifiers changed from: protected */
    public AirbnbAccountManager mAccountManager;
    /* access modifiers changed from: protected */
    public AirbnbApi mAirbnbApi;
    /* access modifiers changed from: protected */
    public Bus mBus;
    protected CurrencyFormatter mCurrencyHelper;
    private FrameLayout mLayoutFrame;
    private Button mSingleButton;
    protected ResourceManager resourceManager;

    public static class ZenBuilder<T extends ZenDialog> {
        private final Context context = CoreApplication.appContext();
        private final Bundle mArgs = new Bundle();
        private final T mDialogFragment;

        public ZenBuilder(T zenDialog) {
            this.mDialogFragment = zenDialog;
        }

        public T create() {
            this.mDialogFragment.setArguments(this.mArgs);
            return this.mDialogFragment;
        }

        public ZenBuilder<T> withTitle(int titleRes) {
            return withTitle(this.context.getString(titleRes));
        }

        public ZenBuilder<T> withTitle(String title) {
            this.mArgs.putString(ZenDialog.HEADER_TITLE, title);
            return this;
        }

        public ZenBuilder<T> withBodyText(int bodyTextRes) {
            return withBodyText(this.context.getString(bodyTextRes));
        }

        public ZenBuilder<T> withBodyHtmlText(int bodyTextRes) {
            this.mArgs.putInt(ZenDialog.BODY_HTML_TEXT, bodyTextRes);
            return this;
        }

        public ZenBuilder<T> withBodyText(String bodyText) {
            this.mArgs.putString(ZenDialog.BODY_TEXT, bodyText);
            return this;
        }

        public ZenBuilder<T> withUrlInBody(String url) {
            this.mArgs.putString(ZenDialog.BODY_TEXT_URL, url);
            return this;
        }

        public ZenBuilder<T> withLinkedText(String linkedText, String url) {
            this.mArgs.putString(ZenDialog.BODY_LINKED_TEXT, linkedText);
            this.mArgs.putString(ZenDialog.BODY_LINKED_URL, url);
            return this;
        }

        public ZenBuilder<T> withBodyTextSelectable() {
            this.mArgs.putBoolean(ZenDialog.HAS_BODY_TEXT_SELECTABLE, true);
            return this;
        }

        public ZenBuilder<T> withCustomLayout() {
            this.mArgs.putBoolean(ZenDialog.HAS_LAYOUT, true);
            return this;
        }

        public ZenBuilder<T> setCustomLayout(int layout) {
            this.mArgs.putInt(ZenDialog.CUSTOM_LAYOUT, layout);
            return withCustomLayout();
        }

        public ZenBuilder<T> withListView() {
            return withListView(null);
        }

        public ZenBuilder<T> withListView(Bundle optionalBundle) {
            this.mArgs.putBoolean(ZenDialog.HAS_LISTVIEW, true);
            if (optionalBundle != null) {
                this.mArgs.putAll(optionalBundle);
            }
            return this;
        }

        public ZenBuilder<T> withArguments(Bundle optionalBundle) {
            this.mArgs.putAll(optionalBundle);
            return this;
        }

        public ZenBuilder<T> withoutDividers() {
            if (this.mArgs.getBoolean(ZenDialog.HAS_LISTVIEW)) {
                this.mArgs.putBoolean(ZenDialog.LISTVIEW_NO_DIVIDER, true);
                return this;
            }
            throw new UnsupportedOperationException("you can only call hide dividers if there's a list view");
        }

        public ZenBuilder<T> withSingleButton(int buttonTextRes, int requestCode, Fragment targetFragment) {
            return withSingleButton(this.context.getString(buttonTextRes), requestCode, targetFragment);
        }

        public ZenBuilder<T> withSingleButton(String buttonText, int requestCode, Fragment targetFragment) {
            this.mArgs.putString(ZenDialog.SINGLE_BUTTON, buttonText);
            this.mArgs.putInt(ZenDialog.SINGLE_BUTTON_REQUEST_CODE, requestCode);
            this.mDialogFragment.setTargetFragment(targetFragment, 0);
            return this;
        }

        public ZenBuilder<T> withDualButton(int negativeButtonRes, int requestCodeLeft, int positiveButtonRes, int requestCodeRight, Fragment targetFragment) {
            return withDualButton(this.context.getString(negativeButtonRes), requestCodeLeft, this.context.getString(positiveButtonRes), requestCodeRight, targetFragment);
        }

        public ZenBuilder<T> withDualButton(int negativeButtonRes, int requestCodeLeft, int positiveButtonRes, int requestCodeRight) {
            return withDualButton(negativeButtonRes, requestCodeLeft, positiveButtonRes, requestCodeRight, (Fragment) null);
        }

        public ZenBuilder<T> withDualButton(String negativeButton, int requestCodeLeft, String positiveButton, int requestCodeRight, Fragment targetFragment) {
            this.mArgs.putString(ZenDialog.DUAL_LEFT_BUTTON, negativeButton);
            this.mArgs.putString(ZenDialog.DUAL_RIGHT_BUTTON, positiveButton);
            this.mArgs.putInt(ZenDialog.DUAL_BUTTON_NEGATIVE_REQUEST_CODE, requestCodeLeft);
            this.mArgs.putInt(ZenDialog.DUAL_BUTTON_POSITIVE_REQUEST_CODE, requestCodeRight);
            this.mDialogFragment.setTargetFragment(targetFragment, 0);
            return this;
        }

        public ZenBuilder<T> withCancelButton() {
            this.mArgs.putBoolean(ZenDialog.GRAY_CANCEL_BUTTON, true);
            return this;
        }

        public ZenBuilder<T> withTargetFragment(Fragment targetFragment) {
            this.mDialogFragment.setTargetFragment(targetFragment, 0);
            return this;
        }

        public ZenBuilder<T> withLargeHeaderDesign() {
            this.mArgs.putBoolean(ZenDialog.ARG_LARGE_HEADER, true);
            return this;
        }

        public ZenBuilder<T> withResultOnCancel(int requestCode) {
            if (!this.mDialogFragment.isCancelable()) {
                throw new IllegalArgumentException("this dialog is not cancelable, so the requestCode will never be called!");
            }
            this.mArgs.putInt(ZenDialog.SEND_RESULT_ON_CANCEL, requestCode);
            return this;
        }

        public ZenBuilder<T> setCancelable(boolean cancelable) {
            if (cancelable || this.mArgs.getInt(ZenDialog.SEND_RESULT_ON_CANCEL, 0) == 0) {
                this.mDialogFragment.setCancelable(cancelable);
                return this;
            }
            throw new IllegalArgumentException("you are setting cancelable to false, but this dialog needs to pass back a result on cancel!");
        }

        public ZenBuilder<T> withMatchParentWidth() {
            this.mArgs.putBoolean(ZenDialog.ARG_MATCH_PARENT_WIDTH, true);
            return this;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, C0716R.C0719style.Theme_Airbnb_DialogNoTitle);
        ((CoreGraph) CoreApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        boolean hasLargeHeader = args.getBoolean(ARG_LARGE_HEADER);
        View v = inflater.inflate(C0716R.layout.zen_dialog_material, container, false);
        String title = args.getString(HEADER_TITLE);
        if (title != null) {
            ((ViewStub) ButterKnife.findById(v, hasLargeHeader ? C0716R.C0718id.zen_stub_large_header : C0716R.C0718id.zen_stub_header)).inflate();
            ((TextView) ButterKnife.findById(v, C0716R.C0718id.title)).setText(title);
        }
        String bodyText = args.getString(BODY_TEXT);
        if (bodyText != null) {
            TextView text = (TextView) ((ViewStub) ButterKnife.findById(v, C0716R.C0718id.zen_stub_text)).inflate().findViewById(C0716R.C0718id.text);
            String url = args.getString(BODY_TEXT_URL);
            String linkedText = args.getString(BODY_LINKED_TEXT);
            String linkedURL = args.getString(BODY_LINKED_URL);
            if (url != null) {
                ClickableLinkUtils.setupClickableTextView(text, bodyText, url, C0716R.color.canonical_press_darken, ZenDialog$$Lambda$1.lambdaFactory$(this, url));
            } else if (linkedText == null || linkedURL == null) {
                text.setText(bodyText);
            } else {
                ClickableLinkUtils.setupClickableTextView(text, bodyText, linkedText, C0716R.color.canonical_press_darken, ZenDialog$$Lambda$2.lambdaFactory$(this, linkedURL));
            }
            if (args.getBoolean(HAS_BODY_TEXT_SELECTABLE, false)) {
                text.setTextIsSelectable(true);
            }
        }
        int bodyHtmlRes = args.getInt(BODY_HTML_TEXT);
        if (bodyHtmlRes > 0) {
            TextView text2 = (TextView) ((ViewStub) ButterKnife.findById(v, C0716R.C0718id.zen_stub_text)).inflate().findViewById(C0716R.C0718id.text);
            text2.setText(TextUtil.fromHtmlSafe(getString(bodyHtmlRes)));
            text2.setMovementMethod(LinkTouchMovementMethod.getInstance());
        }
        if (getArguments().getBoolean(HAS_LAYOUT)) {
            this.mLayoutFrame = (FrameLayout) ((ViewStub) ButterKnife.findById(v, C0716R.C0718id.zen_stub_frame)).inflate();
            int layout = getArguments().getInt(CUSTOM_LAYOUT);
            if (layout > 0) {
                LayoutInflater.from(getActivity()).inflate(layout, this.mLayoutFrame, true);
            } else {
                TextView textView = new TextView(getActivity());
                textView.setTextAppearance(getActivity(), C0716R.C0719style.Canonical_Text_C);
                textView.setText("Override onCreateView, call super.onCreateView(), then call setCustomView()");
                this.mLayoutFrame.addView(textView);
            }
        }
        if (getArguments().getBoolean(HAS_LISTVIEW)) {
            if (getListAdapter() == null) {
                throw new UnsupportedOperationException("In order to have a listview, you need to override getListAdapter() and optionally override getItemClickListener()");
            }
            ListView listview = (ListView) ((ViewStub) ButterKnife.findById(v, C0716R.C0718id.zen_stub_listview)).inflate();
            listview.setAdapter(getListAdapter());
            listview.setOnItemClickListener(getItemClickListener());
            if (getArguments().getBoolean(LISTVIEW_NO_DIVIDER)) {
                listview.setDivider(null);
                listview.setDividerHeight(0);
            }
        }
        View buttonView = ButterKnife.findById(v, C0716R.C0718id.btn_layout);
        String singleButtonText = args.getString(SINGLE_BUTTON);
        if (singleButtonText != null) {
            View buttonFrame = ((ViewStub) ButterKnife.findById(v, C0716R.C0718id.zen_stub_single_button)).inflate();
            if (buttonFrame instanceof Button) {
                this.mSingleButton = (Button) buttonFrame;
            } else {
                this.mSingleButton = (Button) buttonFrame.findViewById(C0716R.C0718id.button);
            }
            this.mSingleButton.setText(singleButtonText);
            this.mSingleButton.setOnClickListener(ZenDialog$$Lambda$3.lambdaFactory$(this));
            buttonView.setVisibility(0);
        }
        String leftButtonText = args.getString(DUAL_LEFT_BUTTON);
        String rightButtonText = args.getString(DUAL_RIGHT_BUTTON);
        if (!(leftButtonText == null || rightButtonText == null)) {
            View dualButtons = ((ViewStub) ButterKnife.findById(v, C0716R.C0718id.zen_stub_dual_button)).inflate();
            TextView leftButton = (TextView) ButterKnife.findById(dualButtons, C0716R.C0718id.negative_button);
            leftButton.setText(leftButtonText);
            leftButton.setOnClickListener(ZenDialog$$Lambda$4.lambdaFactory$(this));
            TextView rightButton = (TextView) ButterKnife.findById(dualButtons, C0716R.C0718id.positive_button);
            rightButton.setText(rightButtonText);
            rightButton.setOnClickListener(ZenDialog$$Lambda$5.lambdaFactory$(this));
            buttonView.setVisibility(0);
            LinearLayout dualButtonContainer = (LinearLayout) ButterKnife.findById(dualButtons, C0716R.C0718id.dual_button_container);
            int leftTextSize = (int) leftButton.getPaint().measureText(leftButtonText.toUpperCase());
            int rightTextSize = (int) rightButton.getPaint().measureText(rightButtonText.toUpperCase());
            v.measure(0, 0);
            if (leftTextSize + rightTextSize >= (dualButtonContainer.getMeasuredWidth() - (leftButton.getPaddingLeft() * 2)) - (rightButton.getPaddingRight() * 2)) {
                dualButtonContainer.setOrientation(1);
                dualButtonContainer.removeView(leftButton);
                dualButtonContainer.addView(leftButton);
            }
        }
        if (args.getBoolean(GRAY_CANCEL_BUTTON, false)) {
            ((ViewStub) ButterKnife.findById(v, C0716R.C0718id.zen_stub_cancel_button)).inflate().setOnClickListener(ZenDialog$$Lambda$6.lambdaFactory$(this));
            buttonView.setVisibility(0);
        }
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(true);
        }
        return v;
    }

    static /* synthetic */ void lambda$onCreateView$2(ZenDialog zenDialog, View view) {
        zenDialog.dismiss();
        int requestCodeSingle = zenDialog.getArguments().getInt(SINGLE_BUTTON_REQUEST_CODE);
        if (requestCodeSingle > 0) {
            zenDialog.clickSingleButton(requestCodeSingle);
        }
    }

    static /* synthetic */ void lambda$onCreateView$3(ZenDialog zenDialog, View view) {
        zenDialog.dismiss();
        int requestCodeLeft = zenDialog.getArguments().getInt(DUAL_BUTTON_NEGATIVE_REQUEST_CODE);
        if (requestCodeLeft > 0) {
            zenDialog.clickLeftButton(requestCodeLeft);
        }
    }

    static /* synthetic */ void lambda$onCreateView$4(ZenDialog zenDialog, View view) {
        zenDialog.dismiss();
        int requestCodeRight = zenDialog.getArguments().getInt(DUAL_BUTTON_POSITIVE_REQUEST_CODE);
        if (requestCodeRight > 0) {
            zenDialog.clickRightButton(requestCodeRight);
        }
    }

    public void onStart() {
        super.onStart();
        if (getArguments().getBoolean(ARG_MATCH_PARENT_WIDTH)) {
            setMatchParentWidth();
        }
    }

    private void setMatchParentWidth() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            if (MiscUtils.isTabletScreen(getContext())) {
                dialog.getWindow().setLayout((int) (((double) ViewUtils.getScreenWidth(getContext())) * 0.8d), -2);
                return;
            }
            dialog.getWindow().setLayout(-1, -2);
        }
    }

    public void onCancel(DialogInterface dialog) {
        int resultOnCancel = getArguments().getInt(SEND_RESULT_ON_CANCEL, 0);
        if (resultOnCancel != 0) {
            sendActivityResult(resultOnCancel, -1, null);
        }
        super.onCancel(dialog);
    }

    /* access modifiers changed from: protected */
    public void sendActivityResult(int requestCode, int resultCode, Intent data) {
        if (getTargetFragment() != null) {
            getTargetFragment().onActivityResult(requestCode, resultCode, data);
        } else if (getActivity() instanceof AirActivity) {
            ((AirActivity) getActivity()).dispatchActivityResult(requestCode, resultCode, data);
        } else {
            throw new IllegalStateException("this zendialog doesnt have a target fragment nor a non-null parent AirActivity");
        }
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void setCustomView(View view) {
        if (this.mLayoutFrame == null) {
            throw new IllegalStateException("Did you call ZenBuilder.withCustomLayout(), and call super.onCreateView() first?");
        }
        this.mLayoutFrame.removeAllViews();
        this.mLayoutFrame.addView(view);
    }

    /* access modifiers changed from: protected */
    public Button getSingleButton() {
        return this.mSingleButton;
    }

    /* access modifiers changed from: protected */
    public void clickSingleButton(int requestCodeSingle) {
        sendActivityResult(requestCodeSingle, -1, null);
    }

    /* access modifiers changed from: protected */
    public void clickLeftButton(int requestCodeLeft) {
        sendActivityResult(requestCodeLeft, -1, null);
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        sendActivityResult(requestCodeRight, -1, null);
    }

    public static ZenBuilder<ZenDialog> builder() {
        return new ZenBuilder<>(new ZenDialog());
    }

    public static ZenDialog createSingleButtonDialog(int body, int button) {
        return setupSingleButtonDialog(button, builder().withBodyText(body));
    }

    public static ZenDialog createSingleButtonDialog(int title, int body, int button) {
        return setupSingleButtonDialog(button, builder().withTitle(title).withBodyText(body));
    }

    public static ZenDialog createSingleButtonDialog(int title, String body, int button) {
        return setupSingleButtonDialog(button, builder().withTitle(title).withBodyText(body));
    }

    public static ZenDialog createSingleButtonDialog(String title, String body, int button) {
        return setupSingleButtonDialog(button, builder().withTitle(title).withBodyText(body));
    }

    public static ZenDialog createNoButtonDialog(int title, String body) {
        return builder().withTitle(title).withBodyText(body).create();
    }

    private static ZenDialog setupSingleButtonDialog(int buttonTextResId, ZenBuilder<ZenDialog> builder) {
        if (buttonTextResId > 0) {
            builder = builder.withSingleButton(buttonTextResId, 0, (Fragment) null);
        }
        return builder.create();
    }

    public void showAllowingStateLoss(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add((Fragment) this, tag);
        ft.commitAllowingStateLoss();
    }

    public Bundle getDummyArguments() {
        return ((BundleBuilder) new BundleBuilder().putBoolean(HAS_LAYOUT, true)).toBundle();
    }

    public boolean shouldShowAsDialog(Context context) {
        return true;
    }
}
