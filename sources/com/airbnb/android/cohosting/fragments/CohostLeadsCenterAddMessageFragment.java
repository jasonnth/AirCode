package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class CohostLeadsCenterAddMessageFragment extends CohostLeadsCenterBaseFragment {
    @BindView
    AirButton button;
    @BindView
    AirEditTextView editTextView;
    @BindView
    DocumentMarquee marquee;
    private final TextWatcher messageTextWatcher = TextWatcherUtils.createEmptyWatcher(CohostLeadsCenterAddMessageFragment$$Lambda$1.lambdaFactory$(this));
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CohostLeadsCenterAddMessageFragment cohostLeadsCenterAddMessageFragment, boolean empty) {
        cohostLeadsCenterAddMessageFragment.button.setEnabled(!empty);
    }

    public static CohostLeadsCenterAddMessageFragment create(String firstName) {
        return (CohostLeadsCenterAddMessageFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostLeadsCenterAddMessageFragment()).putString(CohostingConstants.FIRST_NAME_FIELD, firstName)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_leads_center_add_message, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(CohostLeadsCenterAddMessageFragment$$Lambda$2.lambdaFactory$(this));
        setHasOptionsMenu(true);
        setupViews();
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(C5658R.C5660id.skip).setOnMenuItemClickListener(CohostLeadsCenterAddMessageFragment$$Lambda$3.lambdaFactory$(this));
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSendMessageClicked() {
        this.button.setState(State.Loading);
        getActivity().finish();
    }

    private void setupViews() {
        String firstName = getArguments().getString(CohostingConstants.FIRST_NAME_FIELD);
        this.marquee.setCaption((CharSequence) getString(C5658R.string.cohosting_leads_center_message_prompt_description, firstName));
        this.editTextView.setHint(getString(C5658R.string.cohosting_leads_center_sample_message_to_owner, firstName));
        this.editTextView.addTextChangedListener(this.messageTextWatcher);
    }
}
