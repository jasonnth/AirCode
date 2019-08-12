package com.airbnb.android.core.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import icepick.State;

public class EditTextFragment extends AirDialogFragment {
    private static final String ARG_HEADER_SUBTITLE = "arg_header_subtitle";
    private static final String ARG_HEADER_TITLE = "arg_header_title";
    private static final String ARG_HINT = "arg_hint";
    private static final String ARG_MENU_BUTTON_TEXT = "arg_save_button_text";
    private static final String ARG_MESSAGE = "arg_message";
    private static final String ARG_NAVIGATION_TAG = "arg_navigation_tag";
    private static final String ARG_SHOW_HEADER = "arg_show_header";
    private static final String ARG_USER = "arg_user";
    @BindView
    AirEditTextView editText;
    private final Handler handler = new Handler();
    @State
    String headerSubtitle;
    @State
    String headerTitle;
    @BindView
    View headerView;
    @State
    String hint;
    private EditTextFragmentListener listener;
    @State
    int menuButtonText;
    @State
    String message;
    @State
    NavigationTag navigationTag;
    @State
    boolean showHeader;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;
    @BindView
    AirToolbar toolbar;
    @State
    User user;
    @BindView
    HaloImageView userPhoto;

    public static class EditTextFragmentBuilder {
        private String headerSubtitle;
        private String headerTitle;
        private String hint;
        private String message;
        private NavigationTag navigationTag = NavigationTag.TextInput;
        private int saveMenuTitle = C0716R.string.done;
        private boolean showHeader = false;
        private User user;

        public EditTextFragmentBuilder setText(String message2) {
            this.message = message2;
            return this;
        }

        public EditTextFragmentBuilder setHeaderTitle(String headerTitle2) {
            this.headerTitle = headerTitle2;
            return this;
        }

        public EditTextFragmentBuilder setHeaderSubtitle(String headerSubtitle2) {
            this.headerSubtitle = headerSubtitle2;
            return this;
        }

        public EditTextFragmentBuilder setNavigationTrackingTag(NavigationTag navigationTag2) {
            this.navigationTag = navigationTag2;
            return this;
        }

        public EditTextFragmentBuilder setUser(User user2) {
            this.user = user2;
            return this;
        }

        public EditTextFragmentBuilder showHeader(boolean showHeader2) {
            this.showHeader = showHeader2;
            return this;
        }

        public EditTextFragmentBuilder saveMenuText(int saveMenuTitle2) {
            this.saveMenuTitle = saveMenuTitle2;
            return this;
        }

        public EditTextFragmentBuilder setHint(String hint2) {
            this.hint = hint2;
            return this;
        }

        public EditTextFragment build() {
            return (EditTextFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new EditTextFragment()).putString(EditTextFragment.ARG_MESSAGE, this.message)).putString(EditTextFragment.ARG_HEADER_TITLE, this.headerTitle)).putString(EditTextFragment.ARG_HEADER_SUBTITLE, this.headerSubtitle)).putString(EditTextFragment.ARG_HINT, this.hint)).putParcelable(EditTextFragment.ARG_USER, this.user)).putBoolean(EditTextFragment.ARG_SHOW_HEADER, this.showHeader)).putInt(EditTextFragment.ARG_MENU_BUTTON_TEXT, this.saveMenuTitle)).putSerializable(EditTextFragment.ARG_NAVIGATION_TAG, this.navigationTag)).build();
        }
    }

    public interface EditTextFragmentController {
        EditTextFragmentListener getEditTextFragmentListener();
    }

    public interface EditTextFragmentListener {
        void onMessageSaved(String str);
    }

    public void onAttach(Context context) {
        Check.argument(context instanceof EditTextFragmentController);
        super.onAttach(context);
        this.listener = ((EditTextFragmentController) context).getEditTextFragmentListener();
        Check.notNull(this.listener);
    }

    public void onDetach() {
        this.listener = null;
        super.onDetach();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.message = getArguments().getString(ARG_MESSAGE);
            this.user = (User) getArguments().getParcelable(ARG_USER);
            this.headerTitle = getArguments().getString(ARG_HEADER_TITLE);
            this.headerSubtitle = getArguments().getString(ARG_HEADER_SUBTITLE);
            this.hint = getArguments().getString(ARG_HINT);
            this.showHeader = getArguments().getBoolean(ARG_SHOW_HEADER, false);
            this.menuButtonText = getArguments().getInt(ARG_MENU_BUTTON_TEXT);
            this.navigationTag = (NavigationTag) getArguments().getSerializable(ARG_NAVIGATION_TAG);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_edit_text, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (this.showHeader) {
            this.headerView.setVisibility(0);
            this.titleText.setText(this.headerTitle);
            ViewUtils.setGoneIf(this.titleText, TextUtils.isEmpty(this.headerTitle));
            this.subtitleText.setText(this.headerSubtitle);
            ViewUtils.setGoneIf(this.subtitleText, TextUtils.isEmpty(this.headerSubtitle));
            if (this.user != null) {
                this.userPhoto.setImageUrl(this.user.getPictureUrl());
                this.userPhoto.setOnClickListener(EditTextFragment$$Lambda$1.lambdaFactory$(this));
            } else {
                this.userPhoto.setVisibility(8);
            }
        }
        this.editText.setHint(this.hint);
        if (!TextUtils.isEmpty(this.message)) {
            this.editText.setText(this.message);
        }
        this.editText.requestFocus();
        this.handler.post(EditTextFragment$$Lambda$2.lambdaFactory$(this));
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0716R.C0720menu.fragment_edit_text, menu);
        menu.findItem(C0716R.C0718id.done).setTitle(this.menuButtonText);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0716R.C0718id.done) {
            return super.onOptionsItemSelected(item);
        }
        KeyboardUtils.dismissSoftKeyboard(getView());
        if (TextUtils.isEmpty(this.editText.getText().toString())) {
            Toast.makeText(getContext(), C0716R.string.message_required_error_title, 0).show();
        } else {
            this.listener.onMessageSaved(this.editText.getText().toString());
        }
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.navigationTag;
    }

    public void onPause() {
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
        super.onPause();
    }

    public void onDestroyView() {
        this.handler.removeCallbacksAndMessages(null);
        super.onDestroyView();
    }

    public boolean shouldShowAsDialog(Context context) {
        return false;
    }
}
