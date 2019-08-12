package com.airbnb.android.lib.cancellation.host;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.TextInputSheetFragment;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class CancellationUncomfortableBehaviorFragment extends AirFragment {
    @BindView
    PrimaryButton continueButton;
    @BindView
    AirButton editButton;
    @BindView
    LinkActionRow editRow;
    private Listener listener;
    @State
    String message;
    @BindView
    TextView reportMessageView;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        Strap getLoggingParams();

        void onConfirmUncomfortableBehavior(String str);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_cancellation_uncomfortable_behavior, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (isMessageValid(this.message)) {
            showSubmitUI();
        }
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface");
        }
    }

    @OnClick
    public void onClickEditRow() {
        showEditSheet();
    }

    @OnClick
    public void oClickEditButton() {
        showEditSheet();
    }

    @OnClick
    public void onClickContinueButton() {
        this.listener.onConfirmUncomfortableBehavior(this.message);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 811) {
            String input = data.getStringExtra("result_extra_input");
            if (isMessageValid(input)) {
                this.message = input;
                showSubmitUI();
            }
        }
    }

    private void showEditSheet() {
        startActivityForResult(TextInputSheetFragment.newIntent(getContext(), this.message), TextInputSheetFragment.REQUEST_CODE_TEXT_INPUT_SHEET);
    }

    private boolean isMessageValid(String s) {
        return s != null && !TextUtils.isEmpty(s.trim());
    }

    private void showSubmitUI() {
        this.reportMessageView.setText(this.message);
        this.editButton.setVisibility(8);
        this.continueButton.setVisibility(0);
        this.editRow.setVisibility(0);
        this.reportMessageView.setVisibility(0);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostCancellationUncomfortableGuest;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mix(this.listener.getLoggingParams());
    }
}
