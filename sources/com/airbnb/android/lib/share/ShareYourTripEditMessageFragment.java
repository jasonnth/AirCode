package com.airbnb.android.lib.share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class ShareYourTripEditMessageFragment extends AirFragment {
    private static final String ARG_MESSAGE = "arg_message";
    public static final String RESULT_EXTRA_MESSAGE = "result_extra_message";
    @BindView
    AirEditTextView editText;

    public static Intent newIntent(Context context, String message) {
        return TransparentActionBarActivity.intentForFragment(context, ((FragmentBundleBuilder) FragmentBundler.make(new ShareYourTripEditMessageFragment()).putString(ARG_MESSAGE, message)).build());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_share_your_trip_edit_message, container, false);
        bindViews(view);
        setHasOptionsMenu(true);
        ((TransparentActionBarActivity) getActivity()).getAirToolbar().setTheme(3);
        if (savedInstanceState == null && TextUtils.isEmpty(this.editText.getText()) && !TextUtils.isEmpty(getArguments().getString(ARG_MESSAGE))) {
            this.editText.append(getArguments().getString(ARG_MESSAGE));
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        KeyboardUtils.showSoftKeyboard(getView());
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard(getView());
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.save, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.save) {
            return super.onOptionsItemSelected(item);
        }
        Intent data = new Intent();
        data.putExtra(RESULT_EXTRA_MESSAGE, this.editText.getText().toString());
        getActivity().setResult(-1, data);
        getActivity().finish();
        return true;
    }
}
