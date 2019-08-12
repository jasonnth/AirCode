package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;

public class TextInputSheetFragment extends AirFragment {
    public static final String ARG_BUTTON_TEXT = "arg_button_text";
    public static final String ARG_HINT_TEXT = "arg_hint_text";
    public static final String ARG_INPUT_TEXT = "arg_input_text";
    public static final int REQUEST_CODE_TEXT_INPUT_SHEET = 811;
    public static final String RESULT_EXTRA_INPUT = "result_extra_input";
    @BindView
    EditText inputEditText;

    public static Intent newIntent(Context context, String messageToHost) {
        return newIntent(context, messageToHost, "", context.getString(C0880R.string.save));
    }

    public static Intent newIntent(Context context, String message, String hintText, String buttonText) {
        return TransparentActionBarActivity.intentForFragment(context, ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new TextInputSheetFragment()).putString(ARG_INPUT_TEXT, message)).putString(ARG_HINT_TEXT, hintText)).putString(ARG_BUTTON_TEXT, buttonText)).build());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_sheet_input, container, false);
        bindViews(view);
        setHasOptionsMenu(true);
        this.inputEditText.setText(getArguments().getString(ARG_INPUT_TEXT, ""));
        this.inputEditText.setHint(getArguments().getString(ARG_HINT_TEXT, ""));
        this.inputEditText.requestFocus();
        this.inputEditText.postDelayed(TextInputSheetFragment$$Lambda$1.lambdaFactory$(this), 200);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(TextInputSheetFragment textInputSheetFragment) {
        if (textInputSheetFragment.getActivity() != null) {
            KeyboardUtils.showSoftKeyboard(textInputSheetFragment.getActivity(), textInputSheetFragment.inputEditText);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((TransparentActionBarActivity) getActivity()).getAirToolbar().setTheme(3);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.add(getArguments().getString(ARG_BUTTON_TEXT));
        item.setShowAsAction(2);
        item.setOnMenuItemClickListener(TextInputSheetFragment$$Lambda$2.lambdaFactory$(this));
        super.onCreateOptionsMenu(menu, inflater);
    }

    static /* synthetic */ boolean lambda$onCreateOptionsMenu$1(TextInputSheetFragment textInputSheetFragment, MenuItem menuItem) {
        KeyboardUtils.dismissSoftKeyboard((View) textInputSheetFragment.inputEditText);
        Intent data = new Intent();
        data.putExtra("result_extra_input", textInputSheetFragment.inputEditText.getText().toString());
        textInputSheetFragment.getActivity().setResult(-1, data);
        textInputSheetFragment.getActivity().finish();
        return true;
    }

    public String getInput() {
        return this.inputEditText.getText().toString();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.TextInput;
    }
}
