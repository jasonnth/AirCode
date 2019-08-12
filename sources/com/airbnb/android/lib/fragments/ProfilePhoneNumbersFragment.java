package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.CompleteProfileType;
import com.airbnb.android.core.events.ProfileUpdatedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.core.models.ProfilePhoneNumber;
import com.airbnb.android.core.requests.DeletePhoneNumberRequest;
import com.airbnb.android.core.requests.GetActiveAccountRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CompleteProfileActivity;
import com.airbnb.android.lib.analytics.EditProfileAnalytics;
import com.airbnb.android.lib.views.LinearListView;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.android.utils.ViewUtils;
import com.squareup.otto.Subscribe;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class ProfilePhoneNumbersFragment extends AirFragment {
    private static final int DIALOG_DELETE_MSG = 6002;
    private static final int DIALOG_EDIT_MSG = 6003;
    @State
    boolean isPNRUser;
    @BindView
    StickyButton mBtnAddNumber;
    @BindView
    View mDividerUnverified;
    @BindView
    View mDividerVerified;
    @BindView
    TextView mHeaderUnverified;
    @BindView
    TextView mHeaderVerified;
    @BindView
    LinearListView mListViewUnverifiedNumbers;
    @BindView
    LinearListView mListViewVerifiedNumbers;
    /* access modifiers changed from: private */
    public ProfilePhoneNumberAdapter mUnverifiedNumberAdapter;
    /* access modifiers changed from: private */
    public final List<ProfilePhoneNumber> mUnverifiedNumbers = new ArrayList();
    /* access modifiers changed from: private */
    public ProfilePhoneNumberAdapter mVerifiedNumberAdapter;
    /* access modifiers changed from: private */
    public final List<ProfilePhoneNumber> mVerifiedNumbers = new ArrayList();
    /* access modifiers changed from: private */
    public ProfilePhoneNumber numberToDelete;
    /* access modifiers changed from: private */
    public ProfilePhoneNumber numberToEdit;

    class ProfilePhoneNumberAdapter extends ArrayAdapter<ProfilePhoneNumber> {
        ProfilePhoneNumberAdapter(Context context, int resource, List<ProfilePhoneNumber> objects) {
            super(context, resource, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.list_item_profile_phone_number, parent, false);
            }
            ProfilePhoneNumber phoneNumber = (ProfilePhoneNumber) getItem(position);
            ((TextView) ButterKnife.findById(convertView, C0880R.C0882id.txt_phone_number)).setText(phoneNumber.getNumberFormatted());
            ViewUtils.setGoneIf(ButterKnife.findById(convertView, C0880R.C0882id.btn_verify), phoneNumber.isVerified());
            if (!phoneNumber.isVerified()) {
                convertView.setBackgroundResource(C0880R.C0881drawable.c_bg_transparent);
            }
            View deleteButton = ButterKnife.findById(convertView, C0880R.C0882id.btn_delete);
            deleteButton.setOnClickListener(ProfilePhoneNumbersFragment$ProfilePhoneNumberAdapter$$Lambda$1.lambdaFactory$(this, phoneNumber));
            ButterKnife.findById(convertView, C0880R.C0882id.btn_edit).setOnClickListener(ProfilePhoneNumbersFragment$ProfilePhoneNumberAdapter$$Lambda$2.lambdaFactory$(this, phoneNumber));
            deleteButton.setVisibility(8);
            return convertView;
        }

        static /* synthetic */ void lambda$getView$0(ProfilePhoneNumberAdapter profilePhoneNumberAdapter, ProfilePhoneNumber phoneNumber, View v) {
            ProfilePhoneNumbersFragment.this.numberToDelete = phoneNumber;
            ZenDialog.builder().withBodyText(ProfilePhoneNumbersFragment.this.getString(C0880R.string.edit_profile_delete_phone_number_confirmation, ProfilePhoneNumbersFragment.this.numberToDelete.getNumberFormatted())).withDualButton(C0880R.string.cancel, 0, C0880R.string.delete, (int) ProfilePhoneNumbersFragment.DIALOG_DELETE_MSG, (Fragment) ProfilePhoneNumbersFragment.this).create().show(ProfilePhoneNumbersFragment.this.getFragmentManager(), (String) null);
        }

        static /* synthetic */ void lambda$getView$1(ProfilePhoneNumberAdapter profilePhoneNumberAdapter, ProfilePhoneNumber phoneNumber, View v) {
            ProfilePhoneNumbersFragment.this.numberToEdit = phoneNumber;
            ZenDialog.builder().withBodyText(ProfilePhoneNumbersFragment.this.getString(C0880R.string.edit_profile_phone_confirm_prompt)).withDualButton(C0880R.string.cancel, 0, C0880R.string.edit, (int) ProfilePhoneNumbersFragment.DIALOG_EDIT_MSG, (Fragment) ProfilePhoneNumbersFragment.this).create().show(ProfilePhoneNumbersFragment.this.getFragmentManager(), (String) null);
        }
    }

    public static Fragment newInstance() {
        return new ProfilePhoneNumbersFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBus.register(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_profile_phone_numbers, container, false);
        bindViews(view);
        this.isPNRUser = this.mAccountManager.getCurrentUser().isPhoneNumberRegisteredUser();
        ((AirActivity) getActivity()).setupActionBar(C0880R.string.edit_profile_title_phone_number, new Object[0]);
        populatePhoneNumbers();
        this.mBtnAddNumber.setTitle(C0880R.string.edit_profile_phone_number_add);
        this.mBtnAddNumber.setOnClickListener(ProfilePhoneNumbersFragment$$Lambda$1.lambdaFactory$(this));
        ViewUtils.setGoneIf(this.mBtnAddNumber, this.isPNRUser);
        ViewUtils.setGoneIf(this.mBtnAddNumber, hasPhoneNumber());
        setupPhoneNumbersList();
        return view;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBus.unregister(this);
    }

    private void populatePhoneNumbers() {
        List<ProfilePhoneNumber> allPhoneNumbers = this.mAccountManager.getCurrentUser().getPhoneNumbers();
        if (allPhoneNumbers != null) {
            for (ProfilePhoneNumber number : allPhoneNumbers) {
                if (number.isVerified()) {
                    this.mVerifiedNumbers.add(number);
                } else {
                    this.mUnverifiedNumbers.add(number);
                }
            }
        }
    }

    private void setupPhoneNumbersList() {
        this.mVerifiedNumberAdapter = new ProfilePhoneNumberAdapter(getActivity(), 0, this.mVerifiedNumbers);
        this.mListViewVerifiedNumbers.setAdapter(this.mVerifiedNumberAdapter);
        this.mUnverifiedNumberAdapter = new ProfilePhoneNumberAdapter(getActivity(), 0, this.mUnverifiedNumbers);
        this.mListViewUnverifiedNumbers.setAdapter(this.mUnverifiedNumberAdapter);
        this.mListViewUnverifiedNumbers.setOnItemClickListener(ProfilePhoneNumbersFragment$$Lambda$2.lambdaFactory$(this));
        setHeadersVisibility();
    }

    static /* synthetic */ void lambda$setupPhoneNumbersList$1(ProfilePhoneNumbersFragment profilePhoneNumbersFragment, LinearListView parent, View view, int position, long id) {
        if (position < profilePhoneNumbersFragment.mUnverifiedNumbers.size()) {
            profilePhoneNumbersFragment.startActivity(CompleteProfileActivity.intentForVerifyPhoneNumber(profilePhoneNumbersFragment.getActivity(), ((ProfilePhoneNumber) profilePhoneNumbersFragment.mUnverifiedNumbers.get(position)).getNumberFormatted()));
        }
    }

    /* access modifiers changed from: private */
    public void setHeadersVisibility() {
        ViewUtils.setGoneIf(this.mHeaderVerified, this.mVerifiedNumbers.isEmpty());
        ViewUtils.setGoneIf(this.mDividerVerified, this.mVerifiedNumbers.isEmpty());
        ViewUtils.setGoneIf(this.mHeaderUnverified, this.mUnverifiedNumbers.isEmpty());
        ViewUtils.setGoneIf(this.mDividerUnverified, this.mUnverifiedNumbers.isEmpty());
    }

    private void clearPhoneNumbers() {
        this.mVerifiedNumbers.clear();
        this.mUnverifiedNumbers.clear();
    }

    @Subscribe
    public void profileUpdated(ProfileUpdatedEvent ev) {
        if (ev.getSection() == ProfileSection.Phone) {
            clearPhoneNumbers();
            populatePhoneNumbers();
            this.mVerifiedNumberAdapter.notifyDataSetChanged();
            this.mUnverifiedNumberAdapter.notifyDataSetChanged();
            setHeadersVisibility();
        }
    }

    /* access modifiers changed from: private */
    public void handleAddPhoneNumber() {
        startActivity(CompleteProfileActivity.intentForType(getActivity(), CompleteProfileType.EDIT_PROFILE_ADD_PHONE));
    }

    private void handleDeletePhoneNumber() {
        if (this.numberToDelete != null) {
            EditProfileAnalytics.trackAction("delete", "phone_number", null);
            new DeletePhoneNumberRequest(this.numberToDelete.getId(), new NonResubscribableRequestListener<Object>() {
                public void onResponse(Object response) {
                    ProfilePhoneNumbersFragment.this.mVerifiedNumbers.remove(ProfilePhoneNumbersFragment.this.numberToDelete);
                    ProfilePhoneNumbersFragment.this.mUnverifiedNumbers.remove(ProfilePhoneNumbersFragment.this.numberToDelete);
                    ProfilePhoneNumbersFragment.this.mVerifiedNumberAdapter.notifyDataSetChanged();
                    ProfilePhoneNumbersFragment.this.mUnverifiedNumberAdapter.notifyDataSetChanged();
                    ProfilePhoneNumbersFragment.this.setHeadersVisibility();
                    ProfilePhoneNumbersFragment.this.mAccountManager.getCurrentUser().getPhoneNumbers().remove(ProfilePhoneNumbersFragment.this.numberToDelete);
                    ProfilePhoneNumbersFragment.this.numberToDelete = null;
                    new GetActiveAccountRequest(ProfilePhoneNumbersFragment.this.getContext()).execute(NetworkUtil.singleFireExecutor());
                }

                public void onErrorResponse(AirRequestNetworkException error) {
                    NetworkUtil.toastGenericNetworkError(ProfilePhoneNumbersFragment.this.getActivity());
                }
            }).execute(this.requestManager);
        }
    }

    private void handleEditPhoneNumber() {
        if (this.isPNRUser) {
            startActivity(CompleteProfileActivity.intentForType(getActivity(), CompleteProfileType.EDIT_PROFILE_EDIT_PHONE));
        } else {
            handleAddPhoneNumber();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DIALOG_DELETE_MSG /*6002*/:
                handleDeletePhoneNumber();
                return;
            case DIALOG_EDIT_MSG /*6003*/:
                handleEditPhoneNumber();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private boolean hasPhoneNumber() {
        return this.mVerifiedNumbers.size() + this.mUnverifiedNumbers.size() >= 1;
    }
}
