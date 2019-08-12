package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment.ProgressDialogListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CreateReservationUserRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.InviteGuestSelectAdapter;
import com.airbnb.android.lib.adapters.InviteGuestSelectAdapter.EmailSelected;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.SimpleTextWatcher;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InviteGuestSelectFragment extends AirFragment implements EmailSelected {
    private static final String CONFIRMATION_CODE = "conf_code";
    private static final int DIALOG_REQ_SEND = 1213;
    private static final int DONE_DIALOG_DISPLAY_MS = 1500;
    private static final int REQUEST_PERMISSION_DELAY = 200;
    /* access modifiers changed from: private */
    public InviteGuestSelectAdapter adapter;
    private String confirmationCode;
    @BindView
    EditText editText;
    @BindView
    LinearLayout emailTokens;
    public final NonResubscribableRequestListener<AirBatchResponse> listener = new C0699RL().onResponse(InviteGuestSelectFragment$$Lambda$1.lambdaFactory$(this)).onError(InviteGuestSelectFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    private ProgressDialogFragment progressDialog;
    @State
    String query;
    @BindView
    RecyclerView recyclerView;
    @State
    ArrayList<String> selectedEmails = new ArrayList<>();

    public static InviteGuestSelectFragment newInstance(String confirmationCode2) {
        return (InviteGuestSelectFragment) ((FragmentBundleBuilder) FragmentBundler.make(new InviteGuestSelectFragment()).putString(CONFIRMATION_CODE, confirmationCode2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.confirmationCode = Check.notEmpty(getArguments().getString(CONFIRMATION_CODE), "need reservation confirmation code");
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_invite_guests_select, container, false);
        bindViews(view);
        this.adapter = new InviteGuestSelectAdapter(container.getContext(), this);
        this.recyclerView.setAdapter(this.adapter);
        this.editText.setOnEditorActionListener(InviteGuestSelectFragment$$Lambda$3.lambdaFactory$(this));
        this.editText.requestFocus();
        this.editText.post(InviteGuestSelectFragment$$Lambda$4.lambdaFactory$(this));
        this.editText.postDelayed(InviteGuestSelectFragment$$Lambda$5.lambdaFactory$(this), 200);
        restoreSelectedEmailTokens();
        restoreEmailSuggestions();
        return view;
    }

    static /* synthetic */ boolean lambda$onCreateView$0(InviteGuestSelectFragment inviteGuestSelectFragment, TextView editText2, int actionId, KeyEvent event) {
        String textString = editText2.getText().toString();
        if (actionId == 6) {
            if (Patterns.EMAIL_ADDRESS.matcher(textString).matches()) {
                inviteGuestSelectFragment.onEmailSelected(textString);
            } else {
                Toast.makeText(inviteGuestSelectFragment.getActivity(), C0880R.string.edit_profile_invalid_email, 0).show();
            }
        }
        return false;
    }

    private void restoreSelectedEmailTokens() {
        if (!this.selectedEmails.isEmpty()) {
            Iterator it = this.selectedEmails.iterator();
            while (it.hasNext()) {
                addEmailToken((String) it.next());
            }
        }
    }

    private void restoreEmailSuggestions() {
        if (!TextUtils.isEmpty(this.query)) {
            this.adapter.setQuery(this.query);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        InviteGuestSelectFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public void setupEmailSuggest() {
        this.editText.addTextChangedListener(new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                InviteGuestSelectFragment.this.query = s.toString();
                InviteGuestSelectFragment.this.adapter.setQuery(InviteGuestSelectFragment.this.query);
            }
        });
    }

    public void onEmailSelected(String email) {
        removeEmail(email);
        addEmail(email);
        this.editText.setText("");
    }

    /* access modifiers changed from: private */
    public void removeEmail(String email) {
        if (this.selectedEmails.remove(email)) {
            for (int i = 0; i < this.emailTokens.getChildCount(); i++) {
                View token = this.emailTokens.getChildAt(i);
                if (!this.selectedEmails.contains(token.getTag())) {
                    this.emailTokens.removeView(token);
                    return;
                }
            }
        }
    }

    private void addEmail(String email) {
        this.selectedEmails.add(email);
        addEmailToken(email);
    }

    private void addEmailToken(String email) {
        View token = LayoutInflater.from(this.emailTokens.getContext()).inflate(C0880R.layout.email_token, this.emailTokens, false);
        token.setTag(email);
        ((TextView) ButterKnife.findById(token, C0880R.C0882id.txt_email)).setText(email);
        ButterKnife.findById(token, C0880R.C0882id.close_button).setOnClickListener(InviteGuestSelectFragment$$Lambda$6.lambdaFactory$(this, email));
        this.emailTokens.addView(token);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.send, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.menu_send) {
            return super.onOptionsItemSelected(item);
        }
        String textString = this.editText.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(textString).matches()) {
            onEmailSelected(textString);
        }
        if (!this.selectedEmails.isEmpty()) {
            promptConfirmSendInvites();
        }
        return true;
    }

    private void promptConfirmSendInvites() {
        StringBuilder sb = new StringBuilder();
        sb.append(getString(C0880R.string.share_itinerary_invite_confirm));
        Iterator it = this.selectedEmails.iterator();
        while (it.hasNext()) {
            sb.append("\n" + ((String) it.next()));
        }
        ZenDialog.builder().withBodyText(sb.toString()).withDualButton(C0880R.string.cancel, 0, C0880R.string.send, (int) DIALOG_REQ_SEND, (Fragment) this).create().show(getFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_REQ_SEND) {
            sendInvites();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void sendInvites() {
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        Iterator it = this.selectedEmails.iterator();
        while (it.hasNext()) {
            requests.add(new CreateReservationUserRequest(this.confirmationCode, (String) it.next()));
        }
        this.progressDialog = ProgressDialogFragment.newInstance(getContext(), C0880R.string.sending, 0);
        this.progressDialog.setProgressDialogListener(new ProgressDialogListener() {
            public void onProgressCompleted() {
                InviteGuestSelectFragment.this.getActivity().setResult(-1);
                InviteGuestSelectFragment.this.getActivity().finish();
            }

            public void onProgressCancelled() {
            }
        });
        this.progressDialog.show(getFragmentManager(), (String) null);
        new AirBatchRequest(requests, false, this.listener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$4(InviteGuestSelectFragment inviteGuestSelectFragment, AirBatchResponse response) {
        if (inviteGuestSelectFragment.progressDialog != null) {
            inviteGuestSelectFragment.progressDialog.onProgressComplete(C0880R.string.referrals_show_referrals_sent, 0, C0880R.C0881drawable.icon_complete, 1500);
        }
    }

    static /* synthetic */ void lambda$new$5(InviteGuestSelectFragment inviteGuestSelectFragment, AirRequestNetworkException e) {
        NetworkUtil.toastGenericNetworkError(inviteGuestSelectFragment.getActivity());
        if (inviteGuestSelectFragment.progressDialog != null) {
            inviteGuestSelectFragment.progressDialog.dismissAllowingStateLoss();
        }
    }
}
