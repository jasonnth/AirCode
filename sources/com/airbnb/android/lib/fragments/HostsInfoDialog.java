package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.viewholders.BindableViewHolder;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.google.common.collect.ImmutableList;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class HostsInfoDialog extends ZenDialog {
    private static final String ARG_LISTING = "listing";
    private HostsAdapter hostsAdapter;
    @BindView
    RecyclerView hostsList;
    private Listing listing;
    final RequestListener<UserResponse> userRequestListener = new C0699RL().onResponse(HostsInfoDialog$$Lambda$1.lambdaFactory$(this)).build();

    static class HostsAdapter extends Adapter<BindableViewHolder<User>> {
        private static final int ADDITIONAL_HOST_VIEW_TYPE = 0;
        private static final int PRIMARY_HOST_VIEW_TYPE = 1;
        private final List<User> additionalHosts;
        private User primaryHost;

        static class AdditionalHostViewHolder extends BindableViewHolder<User> {
            @BindView
            TextView hostNameText;
            @BindView
            AirImageView iconImage;
            @BindView
            HaloImageView profileImage;

            public AdditionalHostViewHolder(ViewGroup parent) {
                super(parent, C0880R.layout.list_item_host_info);
            }

            public void bind(User user) {
                Context context = this.itemView.getContext();
                this.hostNameText.setText(user.getName());
                ImageUtils.setImageUrlForUser(this.profileImage, user);
                this.iconImage.setImageDrawable(ColorizedDrawable.forIdWithColor(context, C0880R.C0881drawable.n2_icon_right_caret, C0880R.color.c_hof));
                this.itemView.setOnClickListener(HostsInfoDialog$HostsAdapter$AdditionalHostViewHolder$$Lambda$1.lambdaFactory$(context, user));
            }
        }

        public class AdditionalHostViewHolder_ViewBinding implements Unbinder {
            private AdditionalHostViewHolder target;

            public AdditionalHostViewHolder_ViewBinding(AdditionalHostViewHolder target2, View source) {
                this.target = target2;
                target2.hostNameText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_host_name_small, "field 'hostNameText'", TextView.class);
                target2.profileImage = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.img_profile_small, "field 'profileImage'", HaloImageView.class);
                target2.iconImage = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.icon, "field 'iconImage'", AirImageView.class);
            }

            public void unbind() {
                AdditionalHostViewHolder target2 = this.target;
                if (target2 == null) {
                    throw new IllegalStateException("Bindings already cleared.");
                }
                this.target = null;
                target2.hostNameText = null;
                target2.profileImage = null;
                target2.iconImage = null;
            }
        }

        static class PrimaryHostViewHolder extends BindableViewHolder<User> {
            @BindView
            TextView hostNameText;
            @BindView
            TextView memberSinceTextView;
            private final SimpleDateFormat monthYearSdf = new SimpleDateFormat(this.context.getString(C0880R.string.month_name_format), Locale.getDefault());
            private User primaryHost;
            @BindView
            HaloImageView profileImage;
            @BindView
            TextView userLocationTextView;

            public PrimaryHostViewHolder(ViewGroup parent) {
                super(parent, C0880R.layout.list_item_primary_host_info);
            }

            public void bind(User user) {
                Context context = this.itemView.getContext();
                this.primaryHost = user;
                this.hostNameText.setText(this.primaryHost.getName());
                ImageUtils.setImageUrlForUser(this.profileImage, this.primaryHost);
                this.userLocationTextView.setText(this.primaryHost.getLocation());
                if (this.primaryHost.getCreatedAt() != null) {
                    this.memberSinceTextView.setText(context.getString(C0880R.string.member_since_date, new Object[]{this.primaryHost.getCreatedAt().format(this.monthYearSdf)}));
                    return;
                }
                this.memberSinceTextView.setText("");
            }

            @OnClick
            public void openProfile() {
                Context context = this.itemView.getContext();
                context.startActivity(UserProfileIntents.intentForUserId(context, this.primaryHost.getId()));
            }
        }

        public class PrimaryHostViewHolder_ViewBinding implements Unbinder {
            private PrimaryHostViewHolder target;
            private View view2131757093;
            private View view2131757097;

            public PrimaryHostViewHolder_ViewBinding(final PrimaryHostViewHolder target2, View source) {
                this.target = target2;
                target2.hostNameText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_host_name, "field 'hostNameText'", TextView.class);
                target2.userLocationTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_host_location, "field 'userLocationTextView'", TextView.class);
                target2.memberSinceTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_member_since, "field 'memberSinceTextView'", TextView.class);
                View view = Utils.findRequiredView(source, C0880R.C0882id.img_host_profile, "field 'profileImage' and method 'openProfile'");
                target2.profileImage = (HaloImageView) Utils.castView(view, C0880R.C0882id.img_host_profile, "field 'profileImage'", HaloImageView.class);
                this.view2131757093 = view;
                view.setOnClickListener(new DebouncingOnClickListener() {
                    public void doClick(View p0) {
                        target2.openProfile();
                    }
                });
                View view2 = Utils.findRequiredView(source, C0880R.C0882id.text_view_profile, "method 'openProfile'");
                this.view2131757097 = view2;
                view2.setOnClickListener(new DebouncingOnClickListener() {
                    public void doClick(View p0) {
                        target2.openProfile();
                    }
                });
            }

            public void unbind() {
                PrimaryHostViewHolder target2 = this.target;
                if (target2 == null) {
                    throw new IllegalStateException("Bindings already cleared.");
                }
                this.target = null;
                target2.hostNameText = null;
                target2.userLocationTextView = null;
                target2.memberSinceTextView = null;
                target2.profileImage = null;
                this.view2131757093.setOnClickListener(null);
                this.view2131757093 = null;
                this.view2131757097.setOnClickListener(null);
                this.view2131757097 = null;
            }
        }

        public HostsAdapter(User primaryHost2, List<User> additionalHosts2) {
            this.primaryHost = primaryHost2;
            this.additionalHosts = ImmutableList.copyOf((Collection<? extends E>) additionalHosts2);
        }

        public void setPrimaryHost(User primaryHost2) {
            this.primaryHost = primaryHost2;
            notifyDataSetChanged();
        }

        public BindableViewHolder<User> onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case 0:
                    return new AdditionalHostViewHolder(parent);
                case 1:
                    return new PrimaryHostViewHolder(parent);
                default:
                    throw new IllegalArgumentException("Invalid viewType: " + viewType);
            }
        }

        public int getItemViewType(int position) {
            return position == 0 ? 1 : 0;
        }

        public int getItemCount() {
            return this.additionalHosts.size() + 1;
        }

        public void onBindViewHolder(BindableViewHolder<User> holder, int position) {
            int viewType = getItemViewType(position);
            switch (viewType) {
                case 0:
                    ((AdditionalHostViewHolder) holder).bind((User) this.additionalHosts.get(position - 1));
                    return;
                case 1:
                    ((PrimaryHostViewHolder) holder).bind(this.primaryHost);
                    return;
                default:
                    throw new IllegalArgumentException("Invalid viewType: " + viewType);
            }
        }
    }

    public static HostsInfoDialog newInstance(Listing listing2) {
        Bundle args = new Bundle();
        args.putParcelable("listing", listing2);
        return (HostsInfoDialog) new ZenBuilder(new HostsInfoDialog()).withTitle(C0880R.string.host_info_dialog_your_host).withLargeHeaderDesign().withCustomLayout().withMatchParentWidth().withArguments(args).create();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View frame = inflater.inflate(C0880R.layout.dialog_hosts_info, container, false);
        ButterKnife.bind(this, frame);
        this.listing = (Listing) getArguments().getParcelable("listing");
        this.hostsAdapter = new HostsAdapter(this.listing.getPrimaryHost(), this.listing.getAdditionalHosts());
        this.hostsList.setHasFixedSize(true);
        this.hostsList.setLayoutManager(new LinearLayoutManager(getContext()));
        this.hostsList.setAdapter(this.hostsAdapter);
        updatePrimaryHost();
        setCustomView(frame);
        return view;
    }

    private void updatePrimaryHost() {
        new UserRequest(this.listing.getPrimaryHost().getId(), (BaseRequestListener<UserResponse>) this.userRequestListener).doubleResponse().execute(this.requestManager);
    }
}
