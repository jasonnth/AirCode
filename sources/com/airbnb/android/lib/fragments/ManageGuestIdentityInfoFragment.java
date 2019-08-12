package com.airbnb.android.lib.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.core.FragmentLauncher;
import com.airbnb.android.core.analytics.PsbAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.AirFragment.DoneClickListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.interfaces.GuestIdentityCallbacks;
import com.airbnb.android.core.models.ChineseResidentIdentity;
import com.airbnb.android.core.models.PassportInformation;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.DeleteGuestIdentityInformationRequest;
import com.airbnb.android.core.requests.GetSavedChinaIdentitesRequest;
import com.airbnb.android.core.requests.GetSavedPassportsRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.GetSavedChinaIdentitesResponse;
import com.airbnb.android.core.responses.GetSavedPassportsResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.viewholders.BindableViewHolder;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import p032rx.Observer;

public class ManageGuestIdentityInfoFragment extends AirFragment implements FragmentLauncher, DoneClickListener, GuestIdentityCallbacks {
    private static final String ARG_SELECTED_IDENTITIES = "arg_selected_identities";
    private static final int REQUEST_CODE_CREATE_NEW_IDENTITY = 2000;
    private static final int REQUEST_CODE_DELETE_IDENTITY = 2001;
    public static final String RESULT_SELECTED_IDENTITIES = "result_selected_identities";
    final RequestListener<BaseResponse> deleteIdentityRequestListener = new C0699RL().onResponse(ManageGuestIdentityInfoFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageGuestIdentityInfoFragment$$Lambda$2.lambdaFactory$(this)).build();
    private IdentitiesAdapter identitiesAdapter;
    @BindView
    RecyclerView identitiesRecycler;
    @State
    GuestIdentity identityToDelete;

    private class AddGuestIdentityHolder extends GuestIdentityItemHolder {
        public AddGuestIdentityHolder(ViewGroup parent) {
            super(parent, C0880R.layout.add_guest_identity_item);
        }

        public void bind(GuestIdentity identity) {
            this.itemView.setOnClickListener(ManageGuestIdentityInfoFragment$AddGuestIdentityHolder$$Lambda$1.lambdaFactory$(this));
        }
    }

    class GuestIdentityHolder extends GuestIdentityItemHolder {
        @BindView
        TextView fullName;
        @BindView
        TextView identificationInfo;
        @BindView
        TextView identityType;
        @BindView
        TextView selectedLabel;

        @SuppressLint({"SimpleDateFormat"})
        public GuestIdentityHolder(ViewGroup parent) {
            super(parent, C0880R.layout.guest_identity_item);
            ButterKnife.bind(this, this.itemView);
        }

        public void bind(GuestIdentity identity) {
            this.fullName.setText(ManageGuestIdentityInfoFragment.this.getString(C0880R.string.guest_identity_full_name, identity.getGivenNames(), identity.getSurname()));
            switch (identity.getType()) {
                case Passport:
                    this.identityType.setText(C0880R.string.passport);
                    this.identificationInfo.setText(((PassportInformation) identity).getLocalizedDateOfBirth());
                    break;
                case ChineseNationalID:
                    this.identityType.setText(C0880R.string.chinese_national_identification);
                    this.identificationInfo.setText(identity.getIdentityString());
                    break;
                default:
                    throw new IllegalArgumentException("unknown identity type: " + identity.getClass().getSimpleName());
            }
            ViewUtils.setVisibleIf((View) this.selectedLabel, identity.isSelected());
        }

        public void setOnLongClickListener(OnLongClickListener listener) {
            this.itemView.setOnLongClickListener(listener);
        }

        public void setOnClickListener(OnClickListener listener) {
            this.itemView.setOnClickListener(listener);
        }
    }

    public class GuestIdentityHolder_ViewBinding implements Unbinder {
        private GuestIdentityHolder target;

        public GuestIdentityHolder_ViewBinding(GuestIdentityHolder target2, View source) {
            this.target = target2;
            target2.fullName = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.full_name, "field 'fullName'", TextView.class);
            target2.identityType = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.identity_type, "field 'identityType'", TextView.class);
            target2.identificationInfo = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.identity_info, "field 'identificationInfo'", TextView.class);
            target2.selectedLabel = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.selected_label, "field 'selectedLabel'", TextView.class);
        }

        public void unbind() {
            GuestIdentityHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.fullName = null;
            target2.identityType = null;
            target2.identificationInfo = null;
            target2.selectedLabel = null;
        }
    }

    private abstract class GuestIdentityItemHolder extends BindableViewHolder<GuestIdentity> {
        public abstract void bind(GuestIdentity guestIdentity);

        public GuestIdentityItemHolder(ViewGroup parent, int layoutResId) {
            super(parent, layoutResId);
        }
    }

    class IdentitiesAdapter extends Adapter<GuestIdentityItemHolder> {
        private static final int ITEM_TYPE_ADD_IDENTITY = 1;
        private static final int ITEM_TYPE_IDENTITY_INFO = 0;
        private final GuestIdentityCallbacks callbacks;
        private final ArrayList<GuestIdentity> items = new ArrayList<>();

        IdentitiesAdapter(GuestIdentityCallbacks callbacks2) {
            this.callbacks = callbacks2;
        }

        public GuestIdentityItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case 0:
                    return new GuestIdentityHolder(parent);
                case 1:
                    return new AddGuestIdentityHolder(parent);
                default:
                    throw new IllegalStateException("Unknown view type: " + viewType);
            }
        }

        public void onBindViewHolder(GuestIdentityItemHolder holder, int position) {
            holder.bind(position == this.items.size() ? null : (GuestIdentity) this.items.get(position));
            if (getItemViewType(position) == 0) {
                GuestIdentityHolder guestIdentityHolder = (GuestIdentityHolder) holder;
                guestIdentityHolder.setOnLongClickListener(ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$1.lambdaFactory$(this, position));
                guestIdentityHolder.setOnClickListener(ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$2.lambdaFactory$(this, position));
            }
        }

        static /* synthetic */ void lambda$onBindViewHolder$1(IdentitiesAdapter identitiesAdapter, int position, View view) {
            GuestIdentity guestIdentity = (GuestIdentity) identitiesAdapter.items.get(position);
            guestIdentity.toggleSelected();
            identitiesAdapter.notifyItemChanged(position);
            PsbAnalytics.trackManageIdentitiesToggleSelection(guestIdentity.isSelected());
        }

        public int getItemViewType(int position) {
            return position == this.items.size() ? 1 : 0;
        }

        public int getItemCount() {
            return this.items.size() + 1;
        }

        public void addIdentities(List<GuestIdentity> identities) {
            this.items.removeAll(identities);
            this.items.addAll(identities);
            notifyDataSetChanged();
        }

        public void addIdentity(GuestIdentity guestIdentity) {
            if (guestIdentity == null) {
                throw new IllegalArgumentException("tried to add null identity");
            }
            this.items.remove(guestIdentity);
            this.items.add(guestIdentity);
            notifyDataSetChanged();
        }

        public List<GuestIdentity> getItems() {
            return new LinkedList(this.items);
        }

        public void removeIdentity(GuestIdentity identityToDelete) {
            if (this.items.remove(identityToDelete) || !BuildHelper.isDevelopmentBuild()) {
                notifyDataSetChanged();
                return;
            }
            throw new RuntimeException("unable to delete identity: " + identityToDelete.getIdentityString());
        }

        public ArrayList<GuestIdentity> getSelectedIdentities() {
            return new ArrayList<>(FluentIterable.from((Iterable<E>) this.items).filter(ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$3.lambdaFactory$()).toList());
        }
    }

    public static Bundle getBundle(ArrayList<GuestIdentity> selectedIdentities) {
        return ((BundleBuilder) new BundleBuilder().putParcelableArrayList(ARG_SELECTED_IDENTITIES, selectedIdentities)).toBundle();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_guest_identities, container, false);
        bindViews(view);
        this.identitiesAdapter = new IdentitiesAdapter(this);
        this.identitiesRecycler.setAdapter(this.identitiesAdapter);
        this.identitiesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        setHasOptionsMenu(true);
        fetchIdentites(getArguments().getParcelableArrayList(ARG_SELECTED_IDENTITIES));
        if (savedInstanceState == null) {
            PsbAnalytics.trackManageIdentitiesAction("impression");
        }
        return view;
    }

    public void onStart() {
        super.onStart();
        enableCustomActionBarUpButton(C0880R.layout.editor_actionbar_layout, C0880R.C0882id.confirm_button, C0880R.string.done, this);
    }

    public void onDoneClick() {
        Activity activity = getActivity();
        Intent intent = new Intent();
        ArrayList<GuestIdentity> selectedIdentities = this.identitiesAdapter.getSelectedIdentities();
        intent.putParcelableArrayListExtra(RESULT_SELECTED_IDENTITIES, selectedIdentities);
        PsbAnalytics.trackManageIdentitiesDoneClick(selectedIdentities.size());
        activity.setResult(-1, intent);
        activity.finish();
    }

    public void deleteIdentity(GuestIdentity identity) {
        PsbAnalytics.trackManageIdentitiesAction("delete_identity_dialog_shown");
        this.identityToDelete = identity;
        ZenDialog.builder().withDualButton(C0880R.string.cancel, 0, C0880R.string.delete, 2001).withBodyText(C0880R.string.delete_saved_guest_identity_dialog_body).create().show(getFragmentManager(), (String) null);
    }

    private void fetchIdentites(final List<GuestIdentity> alreadySelectedIdentities) {
        showLoader(true);
        List<BaseRequestV2<?>> requests = new LinkedList<>();
        requests.add(new GetSavedPassportsRequest().withListener((Observer) new SimpleRequestListener<GetSavedPassportsResponse>() {
            public void onResponse(GetSavedPassportsResponse data) {
                ManageGuestIdentityInfoFragment.this.addIdentities(data.passports, alreadySelectedIdentities);
            }
        }));
        requests.add(new GetSavedChinaIdentitesRequest().withListener((Observer) new SimpleRequestListener<GetSavedChinaIdentitesResponse>() {
            public void onResponse(GetSavedChinaIdentitesResponse data) {
                ManageGuestIdentityInfoFragment.this.addIdentities(data.chinaIdentites, alreadySelectedIdentities);
            }
        }));
        new AirBatchRequest(requests, new NonResubscribableRequestListener<AirBatchResponse>() {
            public void onResponse(AirBatchResponse data) {
                ManageGuestIdentityInfoFragment.this.showLoader(false);
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                ManageGuestIdentityInfoFragment.this.showLoader(false);
                NetworkUtil.toastGenericNetworkError(ManageGuestIdentityInfoFragment.this.getActivity());
            }
        }).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void addIdentities(List<? extends GuestIdentity> identites, List<GuestIdentity> alreadySelectedIdentities) {
        List<GuestIdentity> identities = new ArrayList<>(identites.size());
        for (GuestIdentity identity : identites) {
            identity.setSelected(alreadySelectedIdentities.contains(identity));
            identities.add(identity);
        }
        this.identitiesAdapter.addIdentities(identities);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 2000:
                    PsbAnalytics.trackManageIdentitiesAction("new_identity_created");
                    this.identitiesAdapter.addIdentity((GuestIdentity) data.getParcelableExtra(CreateGuestIdentityFragment.EXTRA_GUEST_INFO));
                    break;
                case 2001:
                    PsbAnalytics.trackManageIdentitiesAction("confirm_delete_identity");
                    showLoader(true);
                    new DeleteGuestIdentityInformationRequest(this.identityToDelete).withListener((Observer) this.deleteIdentityRequestListener).execute(this.requestManager);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    static /* synthetic */ void lambda$new$0(ManageGuestIdentityInfoFragment manageGuestIdentityInfoFragment, BaseResponse data) {
        manageGuestIdentityInfoFragment.showLoader(false);
        manageGuestIdentityInfoFragment.identitiesAdapter.removeIdentity(manageGuestIdentityInfoFragment.identityToDelete);
        manageGuestIdentityInfoFragment.identityToDelete = null;
    }

    static /* synthetic */ void lambda$new$1(ManageGuestIdentityInfoFragment manageGuestIdentityInfoFragment, AirRequestNetworkException e) {
        manageGuestIdentityInfoFragment.identityToDelete = null;
        NetworkUtil.toastGenericNetworkError(manageGuestIdentityInfoFragment.getActivity());
        manageGuestIdentityInfoFragment.showLoader(false);
    }

    public Bundle getDummyArguments() {
        ArrayList<GuestIdentity> identities = new ArrayList<>();
        PassportInformation passport = new PassportInformation();
        passport.setSurname("Petzel");
        passport.setGivenNames("Eric");
        passport.setLocalizedDateOfBirth("06/27/1989");
        passport.setIdentificationNumber("12345");
        identities.add(passport);
        ChineseResidentIdentity chineseIdentity = new ChineseResidentIdentity();
        chineseIdentity.setSurname("Adams");
        chineseIdentity.setGivenNames("Nick");
        chineseIdentity.setIdentificationNumber("123423345");
        identities.add(chineseIdentity);
        PassportInformation passport2 = new PassportInformation();
        passport2.setSurname("Davis");
        passport2.setGivenNames("Alex");
        passport2.setLocalizedDateOfBirth("01/23/1995");
        passport2.setIdentificationNumber("434343");
        identities.add(passport2);
        return ((BundleBuilder) new BundleBuilder().putParcelableArrayList(ARG_SELECTED_IDENTITIES, identities)).toBundle();
    }
}
