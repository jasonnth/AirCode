package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.CollaboratorsRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class CollaboratorsRowModel extends AirEpoxyModel<CollaboratorsRow> {
    OnClickListener friendsClickListener;
    List<String> imageUrls;
    OnClickListener inviteClickListener;

    public void bind(CollaboratorsRow view) {
        super.bind(view);
        view.setButtonText(C0716R.string.wish_list_friends_sheet_invite_action);
        view.setImageUrls(this.imageUrls);
        view.setInviteClickListener(this.inviteClickListener);
        view.setFriendsClickListener(this.friendsClickListener);
    }

    public void unbind(CollaboratorsRow view) {
        super.unbind(view);
        view.setInviteClickListener(null);
        view.setFriendsClickListener(null);
    }

    public int getDividerViewType() {
        return 3;
    }
}
