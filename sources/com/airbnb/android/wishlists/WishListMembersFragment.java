package com.airbnb.android.wishlists;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ParticipantRowModel_;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import icepick.State;

public class WishListMembersFragment extends BaseWishListDetailsFragment {
    private static final int REQUEST_CODE_REMOVE_COLLABORATOR_CANCEL = 29061;
    private static final int REQUEST_CODE_REMOVE_COLLABORATOR_OK = 29060;
    private final MembersController epoxyController = new MembersController();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    @State
    User userToRemove;

    private class MembersController extends AirEpoxyController {
        private MembersController() {
        }

        /* access modifiers changed from: protected */
        public void buildModels() {
            boolean isOwner;
            boolean removable;
            new DocumentMarqueeEpoxyModel_().m4536id((CharSequence) "marquee").titleRes(C1758R.string.friends_wishlists).captionRes(C1758R.string.wish_list_friends_sheet_caption).addTo(this);
            new LinkActionRowEpoxyModel_().m5040id((CharSequence) "invite friends").textRes(C1758R.string.wish_list_friends_sheet_invite_action).clickListener(WishListMembersFragment$MembersController$$Lambda$1.lambdaFactory$(this)).addTo(this);
            WishList wishList = WishListMembersFragment.this.getWishList();
            long currentUserId = WishListMembersFragment.this.mAccountManager.getCurrentUser().getId();
            for (User member : WishListMembersFragment.this.getWishListMembers()) {
                if (member.getId() == wishList.getUserId()) {
                    isOwner = true;
                } else {
                    isOwner = false;
                }
                if (isOwner || !(currentUserId == wishList.getUserId() || currentUserId == member.getId())) {
                    removable = false;
                } else {
                    removable = true;
                }
                new ParticipantRowModel_(member).removable(removable).removeClickListener(WishListMembersFragment$MembersController$$Lambda$4.lambdaFactory$(this, member)).addTo(this);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C1758R.layout.recyclerview_with_toolbar, container, false);
        ButterKnife.bind(this, v);
        setHasOptionsMenu(true);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setTheme(3);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Check.state(hasLoadedWishList(), "Wish list must exist before starting this fragment");
        this.epoxyController.requestModelBuild();
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
    }

    /* access modifiers changed from: private */
    public void onRemoveMemberClicked(User userToRemove2) {
        String bodyText;
        this.userToRemove = userToRemove2;
        if (this.mAccountManager.isCurrentUser(userToRemove2.getId())) {
            bodyText = getString(C1758R.string.list_leave_as_collaborator);
        } else {
            bodyText = getString(C1758R.string.list_remove_collaborator, userToRemove2.getFirstName());
        }
        ZenDialog.builder().withBodyText(bodyText).withDualButton(C1758R.string.f2612no, REQUEST_CODE_REMOVE_COLLABORATOR_CANCEL, C1758R.string.yes, REQUEST_CODE_REMOVE_COLLABORATOR_OK).withResultOnCancel(REQUEST_CODE_REMOVE_COLLABORATOR_CANCEL).withTargetFragment(this).create().show(getFragmentManager(), (String) null);
    }

    public void onMembersChanged() {
        this.epoxyController.requestModelBuild();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_REMOVE_COLLABORATOR_OK /*29060*/:
                this.parentFragment.removeMember(this.userToRemove);
                this.userToRemove = null;
                return;
            case REQUEST_CODE_REMOVE_COLLABORATOR_CANCEL /*29061*/:
                this.userToRemove = null;
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.WishListFriends;
    }
}
