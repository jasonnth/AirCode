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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.ActionItem;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.requests.DeletePaymentInstrumentRequest;
import com.airbnb.android.core.requests.GetExistingPayoutMethodRequest;
import com.airbnb.android.core.requests.UpdateUserRequest;
import com.airbnb.android.core.responses.GetExistingPayoutMethodResponse;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;
import com.airbnb.android.lib.views.LoaderListView;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class PayoutListFragment extends AirFragment {
    private static final String EXTRA_PAYOUT_INFO = "extra_payout_info";
    private static final int REQUEST_CODE_ACTION_ITEM_DIALOG = 2600;
    /* access modifiers changed from: private */
    public boolean mAddPayoutDisabled;
    /* access modifiers changed from: private */
    public LoaderListView mLoaderListView;
    @State
    ArrayList<PaymentInstrument> mPayoutInfo;

    private class PayoutsAdapter extends ArrayAdapter<PaymentInstrument> {
        private final int mPayoutCount;
        private final int mResource;

        public PayoutsAdapter(Context context, int resource, List<PaymentInstrument> objects) {
            super(context, resource, objects);
            this.mPayoutCount = objects.size();
            this.mResource = resource;
        }

        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(this.mResource, parent, false);
            }
            PaymentInstrument paymentInstrument = (PaymentInstrument) getItem(position);
            ((TextView) view.findViewById(C0880R.C0882id.payout_method_title)).setText(paymentInstrument.getTitleText());
            ViewUtils.setVisibleIf(ButterKnife.findById(view, C0880R.C0882id.payout_status), paymentInstrument.isDefaultPayout());
            ((TextView) view.findViewById(C0880R.C0882id.payout_method_description)).setText(paymentInstrument.getDetailText());
            ImageView editIcon = (ImageView) view.findViewById(C0880R.C0882id.edit_icon);
            if (!PayoutListFragment.this.mAddPayoutDisabled) {
                editIcon.setImageDrawable(ColorizedDrawable.forIdWithColors(PayoutListFragment.this.getActivity(), C0880R.C0881drawable.icon_edit_black, C0880R.color.c_gray_3, C0880R.color.c_gray_3, C0880R.color.c_gray_2, C0880R.color.c_gray_3));
                editIcon.setOnClickListener(PayoutListFragment$PayoutsAdapter$$Lambda$1.lambdaFactory$(this, paymentInstrument));
            } else {
                editIcon.setVisibility(8);
            }
            return view;
        }
    }

    public static PayoutListFragment newInstance() {
        return new PayoutListFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAddPayoutDisabled = Trebuchet.launch("payments", "add_payout_disabled", false);
        if (!this.mAddPayoutDisabled) {
            setHasOptionsMenu(true);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.payouts, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.menu_add) {
            return super.onOptionsItemSelected(item);
        }
        ((LegacyAddPayoutActivity) getActivity()).showFragment(PayoutWelcomeFragment.withCountryCode(getDefaultUserCOR()));
        return true;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_loader_list_view, container, false);
        this.mLoaderListView = (LoaderListView) view;
        View footer = LayoutInflater.from(getActivity()).inflate(C0880R.layout.panel_my_space_footer, null);
        TextView footerText = (TextView) footer.findViewById(C0880R.C0882id.footer_text);
        if (!this.mAddPayoutDisabled) {
            footerText.setText(C0880R.string.payout_add_method);
            footer.setOnClickListener(PayoutListFragment$$Lambda$1.lambdaFactory$(this));
        } else {
            footerText.setText(C0880R.string.payout_add_disabled);
            footer.setOnClickListener(PayoutListFragment$$Lambda$2.lambdaFactory$(this));
        }
        this.mLoaderListView.getListView().addFooterView(footer);
        if (this.mPayoutInfo != null) {
            this.mLoaderListView.finishLoaderImmediate();
            this.mLoaderListView.getListView().setAdapter(new PayoutsAdapter(getActivity(), C0880R.layout.list_item_payout_existing, this.mPayoutInfo));
        }
        loadUsersPayoutMethods();
        return view;
    }

    /* access modifiers changed from: private */
    public void loadUsersPayoutMethods() {
        GetExistingPayoutMethodRequest.forHostPayouts().withListener((Observer) new NonResubscribableRequestListener<GetExistingPayoutMethodResponse>() {
            public void onResponse(GetExistingPayoutMethodResponse response) {
                PayoutListFragment.this.mLoaderListView.getListView().setAdapter(new PayoutsAdapter(PayoutListFragment.this.getActivity(), C0880R.layout.list_item_payout_existing, response.paymentInstruments));
                PayoutListFragment.this.mLoaderListView.finishLoader();
                PayoutListFragment.this.mPayoutInfo = response.paymentInstruments;
            }

            public void onErrorResponse(AirRequestNetworkException e) {
            }
        }).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void createActionListDialog(int totalPayoutMethodCount, PaymentInstrument payoutMethod) {
        ActionItemZenDialogFragment dialogFragment;
        Bundle extras = new Bundle();
        extras.putParcelable(EXTRA_PAYOUT_INFO, payoutMethod);
        if (totalPayoutMethodCount == 1) {
            dialogFragment = ActionItemZenDialogFragment.newInstance(C0880R.string.options, C0880R.string.payout_need_to_add_before_delete, ActionItem.make(getActivity()).add(C0880R.C0881drawable.icon_additional_charges, C0880R.color.c_rausch, C0880R.string.payout_add_method).toList(), extras);
        } else {
            dialogFragment = ActionItemZenDialogFragment.newInstance(C0880R.string.options, ActionItem.make(getActivity()).add(C0880R.C0881drawable.icon_blue_check, C0880R.color.c_rausch, C0880R.string.payout_set_as_default).add(C0880R.C0881drawable.modal_close, C0880R.color.c_foggy, C0880R.string.payout_remove_payout_method).toList(), extras);
        }
        dialogFragment.setTargetFragment(this, REQUEST_CODE_ACTION_ITEM_DIALOG);
        dialogFragment.show(getActivity().getSupportFragmentManager(), (String) null);
    }

    private void handleActionListResult(Intent resultData) {
        int actionItemId = resultData.getIntExtra(ActionItemZenDialogFragment.EXTRA_ACTION_ITEM_ID, -1);
        if (actionItemId != -1) {
            PaymentInstrument paymentInstrument = (PaymentInstrument) resultData.getBundleExtra(ActionItemZenDialogFragment.ARG_EXTRAS_BUNDLE).getParcelable(EXTRA_PAYOUT_INFO);
            if (actionItemId == C0880R.string.payout_add_method) {
                startNewPayout();
            } else if (actionItemId == C0880R.string.payout_set_as_default) {
                if (!paymentInstrument.isDefaultPayout()) {
                    setDefaultPayout(paymentInstrument.getId());
                }
            } else if (actionItemId == C0880R.string.payout_remove_payout_method) {
                deletePayout(paymentInstrument.getId(), paymentInstrument.isDefaultPayout());
            } else {
                throw new UnsupportedOperationException("unsupported case: " + getString(actionItemId));
            }
        }
    }

    private void startNewPayout() {
        ((LegacyAddPayoutActivity) getActivity()).showFragment(PayoutWelcomeFragment.withCountryCode(getDefaultUserCOR()));
    }

    public void setDefaultPayout(long paymentInstrumentId) {
        this.mLoaderListView.startLoader();
        UpdateUserRequest.forSetDefaultPayout(paymentInstrumentId).withListener((Observer) new NonResubscribableRequestListener<UserResponse>() {
            public void onResponse(UserResponse response) {
                if (PayoutListFragment.this.getActivity() != null) {
                    PayoutListFragment.this.loadUsersPayoutMethods();
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                NetworkUtil.toastGenericNetworkError(PayoutListFragment.this.getActivity());
                PayoutListFragment.this.mLoaderListView.finishLoaderImmediate();
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    public void deletePayout(long paymentInstrumentId, boolean isDefault) {
        if (isDefault) {
            ZenDialog.createSingleButtonDialog(C0880R.string.payout_method_delete_default_error_title, C0880R.string.payout_method_delete_default_error_body, C0880R.string.okay).show(getFragmentManager(), (String) null);
            return;
        }
        this.mLoaderListView.startLoader();
        new DeletePaymentInstrumentRequest(paymentInstrumentId).withListener((Observer) new NonResubscribableRequestListener<PaymentInstrumentResponse>() {
            public void onResponse(PaymentInstrumentResponse response) {
                if (PayoutListFragment.this.getActivity() != null) {
                    PayoutListFragment.this.loadUsersPayoutMethods();
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                NetworkUtil.toastGenericNetworkError(PayoutListFragment.this.getActivity());
                PayoutListFragment.this.mLoaderListView.finishLoaderImmediate();
                if (PayoutListFragment.this.getActivity() != null) {
                    PayoutListFragment.this.loadUsersPayoutMethods();
                }
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ACTION_ITEM_DIALOG /*2600*/:
                if (resultCode == -1) {
                    handleActionListResult(data);
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private String getDefaultUserCOR() {
        return this.mAccountManager.getCurrentUser().getDefaultCountryOfResidence();
    }
}
