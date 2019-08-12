package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.lib.C0880R;

public class CrashDialog extends ZenDialog {
    private static final String MESSAGE = "message";

    public static CrashDialog newInstance(String message) {
        CrashDialog dialog = (CrashDialog) new ZenBuilder(new CrashDialog()).withTitle("Crash detected").withCustomLayout().withSingleButton(C0880R.string.okay, 0, (Fragment) null).create();
        dialog.getArguments().putString("message", message);
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View frame = inflater.inflate(C0880R.layout.crash_dialog_fragment, container, false);
        String message = getArguments().getString("message");
        TextView infoView = (TextView) ButterKnife.findById(frame, C0880R.C0882id.info);
        infoView.setText(message);
        infoView.setOnLongClickListener(CrashDialog$$Lambda$1.lambdaFactory$(this, message));
        setCustomView(frame);
        return view;
    }
}
