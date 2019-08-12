package com.airbnb.android.booking.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;

public class CheckInMessageDialogFragment extends ZenDialog {
    public static final String ARG_MESSAGE = "message";
    public static final int REQUEST_CHECK_IN_MESSAGE = 765;
    @BindView
    EditText messageInput;

    public static CheckInMessageDialogFragment newInstance(String message) {
        CheckInMessageDialogFragment dialog = (CheckInMessageDialogFragment) new ZenBuilder(new CheckInMessageDialogFragment()).withCustomLayout().withTitle(C0704R.string.p4_checkin_message_hint).setCancelable(true).withSingleButton(C0704R.string.okay, (int) REQUEST_CHECK_IN_MESSAGE, (Fragment) null).create();
        dialog.getArguments().putString("message", message);
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setCustomView(inflater.inflate(C0704R.layout.fragment_check_in_dialog, container, false));
        ButterKnife.bind(this, view);
        this.messageInput.setText(getArguments().getString("message"));
        return view;
    }

    /* access modifiers changed from: protected */
    public void clickSingleButton(int requestCodeSingle) {
        Intent data = new Intent();
        data.putExtra("message", this.messageInput.getText().toString());
        sendActivityResult(REQUEST_CHECK_IN_MESSAGE, -1, data);
    }
}
