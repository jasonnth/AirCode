package com.airbnb.android.lib.fragments.managelisting;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import java.util.Locale;

public class TooltipDialogFragment extends AirDialogFragment {
    private static final String ARG_BUTTON_TEXT = "buttonText";
    private static final String ARG_EXAMPLES = "examplesId";
    private static final String ARG_EXAMPLE_HEADER = "exampleHeader";
    private static final String ARG_HELP_ID = "helpId";
    private static final String ARG_HELP_TEXT = "helpText";
    private static final String ARG_HTML_TEXT = "htmlHelpText";
    private static final String ARG_TITLE_ID = "titleId";
    private static final String ARG_URL = "url";
    public static final String TAG = TooltipDialogFragment.class.getSimpleName();

    public static TooltipDialogFragment newInstance(int titleId, int helpId) {
        TooltipDialogFragment f = new TooltipDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_ID, titleId);
        args.putInt(ARG_HELP_ID, helpId);
        f.setArguments(args);
        return f;
    }

    public static TooltipDialogFragment newInstance(int titleId, String helpText) {
        TooltipDialogFragment f = new TooltipDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_ID, titleId);
        args.putString(ARG_HELP_TEXT, helpText);
        f.setArguments(args);
        return f;
    }

    public static TooltipDialogFragment newInstance(int titleId, String helpText, int buttonLabel) {
        TooltipDialogFragment fragment = newInstance(titleId, helpText);
        Bundle args = fragment.getArguments();
        args.putInt(ARG_BUTTON_TEXT, buttonLabel);
        fragment.setArguments(args);
        return fragment;
    }

    public static TooltipDialogFragment newInstance(int titleId, String htmlHelpText, String url) {
        TooltipDialogFragment f = new TooltipDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_ID, titleId);
        args.putString(ARG_HTML_TEXT, htmlHelpText);
        if (!TextUtils.isEmpty(url)) {
            args.putString("url", url);
        }
        f.setArguments(args);
        return f;
    }

    public static TooltipDialogFragment newInstance(int titleId, int helpId, int examplesArrayId) {
        TooltipDialogFragment f = newInstance(titleId, helpId);
        Bundle args = f.getArguments();
        args.putInt(ARG_EXAMPLES, examplesArrayId);
        f.setArguments(args);
        return f;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(C0880R.layout.dialog_tooltip, null);
        ((TextView) view.findViewById(C0880R.C0882id.tooltip_title)).setText(getString(getArguments().getInt(ARG_TITLE_ID)));
        TextView helpTextView = (TextView) view.findViewById(C0880R.C0882id.tooltip_help);
        String url = getArguments().getString("url");
        if (url != null) {
            ClickableLinkUtils.setupClickableTextView(helpTextView, getArguments().getString(ARG_HTML_TEXT), C0880R.color.canonical_press_darken, TooltipDialogFragment$$Lambda$1.lambdaFactory$(this, url));
        } else {
            int helpTextId = getArguments().getInt(ARG_HELP_ID, 0);
            if (helpTextId != 0) {
                helpTextView.setText(helpTextId);
            } else if (getArguments().containsKey(ARG_HELP_TEXT)) {
                helpTextView.setText(getArguments().getString(ARG_HELP_TEXT));
            } else {
                helpTextView.setVisibility(8);
            }
        }
        int examplesArrayId = getArguments().getInt(ARG_EXAMPLES);
        TextView exampleHeader = (TextView) view.findViewById(C0880R.C0882id.tooltip_example_header);
        TextView examples = (TextView) view.findViewById(C0880R.C0882id.tooltip_examples);
        if (examplesArrayId == 0) {
            exampleHeader.setVisibility(8);
            examples.setVisibility(8);
        } else {
            String[] examplesStrings = getResources().getStringArray(examplesArrayId);
            StringBuilder sb = new StringBuilder();
            int length = examplesStrings.length;
            int exampleHeaderId = getArguments().getInt(ARG_EXAMPLE_HEADER, 0);
            if (exampleHeaderId != 0) {
                exampleHeader.setText(exampleHeaderId);
            } else {
                exampleHeader.setText(getResources().getQuantityString(C0880R.plurals.examples, length).toUpperCase(Locale.getDefault()));
            }
            int length2 = examplesStrings.length;
            for (int i = 0; i < length2; i++) {
                String examplesString = examplesStrings[i];
                if (length > 1) {
                    sb.append("• ");
                }
                sb.append(examplesString);
                sb.append(10);
            }
            sb.deleteCharAt(sb.length() - 1);
            examples.setText(sb.toString());
        }
        Button dismissButton = (Button) view.findViewById(C0880R.C0882id.dismiss_button);
        dismissButton.setText(getString(getArguments().getInt(ARG_BUTTON_TEXT, C0880R.string.okay)));
        AlertDialog dialog = new Builder(getActivity()).setView(view).setCancelable(true).create();
        dismissButton.setOnClickListener(TooltipDialogFragment$$Lambda$2.lambdaFactory$(this, dialog));
        return dialog;
    }

    static /* synthetic */ void lambda$onCreateDialog$1(TooltipDialogFragment tooltipDialogFragment, AlertDialog dialog, View v) {
        if (tooltipDialogFragment.getTargetFragment() != null) {
            tooltipDialogFragment.getTargetFragment().onActivityResult(tooltipDialogFragment.getTargetRequestCode(), -1, null);
        }
        dialog.dismiss();
    }
}
