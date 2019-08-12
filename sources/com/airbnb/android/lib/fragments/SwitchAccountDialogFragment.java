package com.airbnb.android.lib.fragments;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.authentication.AuthorizedAccountHelper;
import com.airbnb.android.core.events.LoginEvent;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.intents.EntryActivityIntents;
import com.airbnb.android.core.models.AuthorizedAccount;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.GetActiveAccountRequest;
import com.airbnb.android.core.responses.AccountResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GroupedTooltip;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p032rx.Observer;

public class SwitchAccountDialogFragment extends ZenDialog {
    private static final String ACCOUNTS = "accounts";
    private static final int DIALOG_REQ_LOGOUT_ALL = 1200;
    public static final int DIALOG_REQ_SWITCH_COMPLETE = 8999;
    private static final String SELECTED_ACCOUNT = "selected_account";
    private int mActualAccountCount;
    private SwitchAccountAdapter mListAdapter;
    private SwitchAccountListener mListener;

    private enum RowTypes {
        ExistingAccount,
        AddAccount,
        LoginWithToken,
        SoftLogOut,
        LogoutAll
    }

    private final class SwitchAccountAdapter extends ArrayAdapter<AuthorizedAccount> {
        private final List<RowTypes> adapterItems = new ArrayList();
        private final long selectedUserId;

        public SwitchAccountAdapter(Context context, ArrayList<AuthorizedAccount> objects, long selectedUserId2) {
            super(context, 0, objects);
            this.selectedUserId = selectedUserId2;
            initRowItems(objects);
        }

        private void initRowItems(ArrayList<AuthorizedAccount> objects) {
            Iterator it = objects.iterator();
            while (it.hasNext()) {
                AuthorizedAccount authorizedAccount = (AuthorizedAccount) it.next();
                this.adapterItems.add(RowTypes.ExistingAccount);
            }
            this.adapterItems.add(RowTypes.AddAccount);
            if (BuildHelper.isDevelopmentBuild()) {
                this.adapterItems.add(RowTypes.LoginWithToken);
            }
            if (BuildHelper.isDebugFeaturesEnabled()) {
                this.adapterItems.add(RowTypes.SoftLogOut);
            }
            this.adapterItems.add(RowTypes.LogoutAll);
        }

        public AuthorizedAccount getItem(int position) {
            if (position < super.getCount()) {
                return (AuthorizedAccount) super.getItem(position);
            }
            return null;
        }

        public int getViewTypeCount() {
            return RowTypes.values().length;
        }

        public int getItemViewType(int position) {
            return ((RowTypes) this.adapterItems.get(position)).ordinal();
        }

        public int getCount() {
            return this.adapterItems.size();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.list_item_switch_account, parent, false);
            }
            HaloImageView picture = (HaloImageView) ButterKnife.findById(convertView, C0880R.C0882id.profile_image);
            TextView text = (TextView) ButterKnife.findById(convertView, C0880R.C0882id.title);
            ImageView check = (ImageView) ButterKnife.findById(convertView, C0880R.C0882id.zen_checkmark);
            GroupedTooltip tooltip = (GroupedTooltip) ButterKnife.findById(convertView, C0880R.C0882id.grouped_cell_tooltip);
            RowTypes rowType = RowTypes.values()[getItemViewType(position)];
            AuthorizedAccount account = getItem(position);
            ViewUtils.setVisibleIf((View) picture, rowType == RowTypes.ExistingAccount);
            boolean rowIsSelectedUser = account != null && this.selectedUserId == account.getId();
            ViewUtils.setVisibleIf((View) check, rowIsSelectedUser);
            convertView.setEnabled(!rowIsSelectedUser);
            switch (rowType) {
                case ExistingAccount:
                    User fakeUser = new User();
                    fakeUser.setId(account.getId());
                    fakeUser.setPictureUrl(account.getPictureUrl());
                    ImageUtils.setImageUrlForUser(picture, fakeUser);
                    text.setText(account.getUsername());
                    break;
                case AddAccount:
                    text.setText(C0880R.string.switch_account_new_account);
                    break;
                case LoginWithToken:
                    text.setText(C0880R.string.debug_menu_become_user_with_token);
                    break;
                case SoftLogOut:
                    text.setText(C0880R.string.debug_menu_become_logged_out);
                    tooltip.setOnClickListener(SwitchAccountDialogFragment$SwitchAccountAdapter$$Lambda$1.lambdaFactory$(this));
                    break;
                case LogoutAll:
                    text.setText(C0880R.string.switch_account_logout_all);
                    break;
            }
            return convertView;
        }
    }

    public interface SwitchAccountListener {
        void onLoginWithToken();

        void onLogoutAll();

        void onNewAccount();

        void onSelectAccount(AuthorizedAccount authorizedAccount);

        void onSoftLogOut();
    }

    public static SwitchAccountDialogFragment newInstance(long userId, Context context, Fragment doneListener) {
        ArrayList<AuthorizedAccount> accounts = AuthorizedAccountHelper.get(context).getAuthorizedUsers();
        Bundle args = new Bundle();
        args.putSerializable("accounts", accounts);
        args.putLong(SELECTED_ACCOUNT, userId);
        return (SwitchAccountDialogFragment) new ZenBuilder(new SwitchAccountDialogFragment()).withTitle(C0880R.string.switch_account_title).withListView(args).withTargetFragment(doneListener).create();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mListener == null) {
            this.mListener = getAccountSelectedListener();
        }
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        if (this.mListAdapter == null) {
            ArrayList<AuthorizedAccount> accounts = (ArrayList) getArguments().getSerializable("accounts");
            this.mActualAccountCount = accounts.size();
            this.mListAdapter = new SwitchAccountAdapter(getActivity(), accounts, getArguments().getLong(SELECTED_ACCOUNT));
        }
        return this.mListAdapter;
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return SwitchAccountDialogFragment$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$getItemClickListener$0(SwitchAccountDialogFragment switchAccountDialogFragment, AdapterView parent, View view, int position, long id) {
        String string;
        switch (RowTypes.values()[switchAccountDialogFragment.mListAdapter.getItemViewType(position)]) {
            case ExistingAccount:
                switchAccountDialogFragment.mListener.onSelectAccount(switchAccountDialogFragment.mListAdapter.getItem(position));
                return;
            case AddAccount:
                switchAccountDialogFragment.mListener.onNewAccount();
                return;
            case LoginWithToken:
                switchAccountDialogFragment.mListener.onLoginWithToken();
                return;
            case SoftLogOut:
                switchAccountDialogFragment.mListener.onSoftLogOut();
                return;
            case LogoutAll:
                ZenBuilder withTitle = ZenDialog.builder().withTitle(C0880R.string.log_out);
                if (switchAccountDialogFragment.mActualAccountCount > 1) {
                    string = switchAccountDialogFragment.getString(C0880R.string.switch_account_logout_all_confirm, Integer.valueOf(switchAccountDialogFragment.mActualAccountCount));
                } else {
                    string = switchAccountDialogFragment.resourceManager.getString(C0880R.string.log_out_warning_prompt_message);
                }
                withTitle.withBodyText(string).withDualButton(C0880R.string.cancel, 0, C0880R.string.log_out, (int) DIALOG_REQ_LOGOUT_ALL, (Fragment) switchAccountDialogFragment).create().show(switchAccountDialogFragment.getFragmentManager(), (String) null);
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_REQ_LOGOUT_ALL) {
            this.mListener.onLogoutAll();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private SwitchAccountListener getAccountSelectedListener() {
        return new SwitchAccountListener() {
            final Context context = SwitchAccountDialogFragment.this.getActivity();

            public void onSelectAccount(AuthorizedAccount account) {
                loginWithToken(account.getAuthToken());
            }

            public void onNewAccount() {
                SwitchAccountDialogFragment.this.mAirbnbApi.logoutWithoutRevokingOauth();
                SwitchAccountDialogFragment.this.startActivity(EntryActivityIntents.newIntent(this.context).addFlags(335544320));
                SwitchAccountDialogFragment.this.getActivity().finish();
            }

            public void onLoginWithToken() {
                EditText tokenEditText = new EditText(this.context);
                new Builder(this.context).setTitle(C0880R.string.debug_menu_enter_token_dialog_title).setView(tokenEditText).setPositiveButton(17039370, SwitchAccountDialogFragment$1$$Lambda$1.lambdaFactory$(this, tokenEditText)).setNegativeButton(17039360, null).show();
            }

            static /* synthetic */ void lambda$onLoginWithToken$0(C69011 r2, EditText tokenEditText, DialogInterface dialog, int which) {
                String token = tokenEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(token)) {
                    r2.loginWithToken(token);
                }
            }

            public void onLogoutAll() {
                AuthorizedAccountHelper.get(this.context).removeAllUsers();
                SwitchAccountDialogFragment.this.dismissWithSuccess();
            }

            public void onSoftLogOut() {
                SwitchAccountDialogFragment.this.mAirbnbApi.logoutWithoutRevokingOauth();
                SwitchAccountDialogFragment.this.dismissWithSuccess();
            }

            private void loginWithToken(String authToken) {
                SwitchAccountDialogFragment.this.mAirbnbApi.logoutWithoutRevokingOauth();
                SwitchAccountDialogFragment.this.mAccountManager.setAccessToken(authToken);
                new GetActiveAccountRequest(SwitchAccountDialogFragment.this.getContext()).withListener((Observer) new NonResubscribableRequestListener<AccountResponse>() {
                    public void onErrorResponse(AirRequestNetworkException error) {
                        NetworkUtil.toastGenericNetworkError(C69011.this.context);
                    }

                    public void onResponse(AccountResponse response) {
                        SwitchAccountDialogFragment.this.mBus.post(new LoginEvent());
                        SwitchAccountDialogFragment.this.mAirbnbApi.enablePushNotifications();
                        Toast.makeText(C69011.this.context, C69011.this.context.getString(C0880R.string.switch_account_switch_toast, new Object[]{response.account.getUser().getName()}), 0).show();
                        SwitchAccountDialogFragment.this.dismissWithSuccess();
                    }
                }).skipCache().execute(NetworkUtil.singleFireExecutor());
            }
        };
    }

    /* access modifiers changed from: private */
    public void dismissWithSuccess() {
        if (getFragmentManager() != null) {
            dismissAllowingStateLoss();
            if (getActivity() == null) {
                return;
            }
            if (getTargetFragment() != null || (getActivity() instanceof AirActivity)) {
                sendActivityResult(DIALOG_REQ_SWITCH_COMPLETE, -1, null);
            }
        }
    }
}
