package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class TextEntryFragment extends AirFragment {
    private static final String ARG_DEFAULT_TEXT = "arg_default_text";
    private static final String ARG_HINT = "arg_hint";
    private static final String ARG_TITLE = "arg_title";
    @BindView
    EditText editText;
    @State
    boolean hasEditedText;
    @BindView
    AirButton saveButton;
    private TextEntryCallback textEntryCallback;
    private final TextWatcher textWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean z = true;
            super.onTextChanged(s, start, before, count);
            TextEntryFragment.this.hasEditedText = true;
            AirButton airButton = TextEntryFragment.this.saveButton;
            if (TextUtils.isEmpty(TextEntryFragment.this.editText.getText().toString())) {
                z = false;
            }
            airButton.setEnabled(z);
        }
    };
    @BindView
    AirToolbar toolbar;

    public interface TextEntryCallback {
        void onSaveText(String str);
    }

    public static TextEntryFragment instanceForText(Integer titleRes, String defaultText, String hint) {
        return (TextEntryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new TextEntryFragment()).putInt(ARG_TITLE, titleRes.intValue())).putString(ARG_HINT, hint)).putString(ARG_DEFAULT_TEXT, defaultText)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.textEntryCallback = (TextEntryCallback) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement TextEntryCallback");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = false;
        View view = inflater.inflate(C0880R.layout.fragment_text_entry, container, false);
        bindViews(view);
        Bundle args = getArguments();
        String title = args.containsKey(ARG_TITLE) ? getString(args.getInt(ARG_TITLE)) : "";
        String defaultText = args.getString(ARG_DEFAULT_TEXT);
        String hint = args.getString(ARG_HINT);
        this.toolbar.setTitle((CharSequence) title);
        setToolbar(this.toolbar);
        if (!this.hasEditedText) {
            this.editText.setText(defaultText);
        }
        this.editText.setHint(hint);
        this.editText.addTextChangedListener(this.textWatcher);
        this.editText.setSelection(this.editText.getText().length());
        this.editText.postDelayed(TextEntryFragment$$Lambda$1.lambdaFactory$(this), 200);
        AirButton airButton = this.saveButton;
        if (!TextUtils.isEmpty(this.editText.getText().toString())) {
            z = true;
        }
        airButton.setEnabled(z);
        return view;
    }

    public void onDestroyView() {
        this.editText.removeTextChangedListener(this.textWatcher);
        KeyboardUtils.dismissSoftKeyboard(getView());
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickSave() {
        String enteredText = this.editText.getText().toString();
        Check.state(!TextUtils.isEmpty(enteredText));
        this.textEntryCallback.onSaveText(enteredText);
    }
}
