package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.requests.CreateWishlistMembershipRequest;
import com.airbnb.android.core.responses.WishlistMembershipResponse;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.BundleBuilder;
import p032rx.Observer;

public class JoinWishlistFragment extends AirFragment {
    private static final String ARG_INVITE_CODE = "invite_code";
    private static final String ARG_WISHLIST_ID = "wishlist_id";
    final RequestListener<WishlistMembershipResponse> listener = new C0699RL().onComplete(JoinWishlistFragment$$Lambda$1.lambdaFactory$(this)).onError(JoinWishlistFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    LoaderFrame loaderFrame;
    private long wishListId;

    public static Intent intentForWishlist(Context context, Bundle parameters) {
        long wishlistId = DeepLinkUtils.getParamAsId(parameters, ARG_WISHLIST_ID);
        String inviteCode = parameters.getString("invite_code");
        if (wishlistId != -1 && inviteCode != null) {
            return intentForWishlist(context, wishlistId, inviteCode);
        }
        BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Invalid params for join wishlist deeplink: " + parameters));
        Toast.makeText(context, C0880R.string.wishlistdetails_join_wishlist_link_error, 1).show();
        return HomeActivityIntents.intentForGuestHome(context);
    }

    public static Intent intentForWishlist(Context context, long wishlistId, String inviteCode) {
        return AutoAirActivity.intentForFragment(context, JoinWishlistFragment.class, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong(ARG_WISHLIST_ID, wishlistId)).putString("invite_code", inviteCode)).toBundle());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_join_wishlist, container, false);
        bindViews(view);
        this.loaderFrame.startAnimation();
        this.wishListId = getArguments().getLong(ARG_WISHLIST_ID);
        String inviteCode = getArguments().getString("invite_code");
        if (savedInstanceState == null) {
            new CreateWishlistMembershipRequest(this.wishListId, inviteCode).withListener((Observer) this.listener).execute(this.requestManager);
        }
        return view;
    }

    static /* synthetic */ void lambda$new$0(JoinWishlistFragment joinWishlistFragment, Boolean successful) {
        joinWishlistFragment.startActivity(HomeActivityIntents.intentForWishList(joinWishlistFragment.getContext(), joinWishlistFragment.wishListId));
        joinWishlistFragment.getActivity().finish();
    }
}
