package com.airbnb.android.wishlists;

import com.airbnb.android.wishlists.WLVotingRow.WLVotingClickListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

abstract class WLVotingRowModel extends AirEpoxyModel<WLVotingRow> {
    int downVoteCount;
    boolean gridMode;
    WLVotingClickListener listener;
    int upVoteCount;
    WLItemVote vote;

    WLVotingRowModel() {
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return this.gridMode ? C1758R.layout.view_model_wl_voting_row_grid : C1758R.layout.view_model_wl_voting_row;
    }

    public void bind(WLVotingRow votingRow) {
        super.bind(votingRow);
        votingRow.setVote(this.vote);
        votingRow.setListener(this.listener);
        votingRow.setVoteCounts(this.upVoteCount, this.downVoteCount);
    }

    public void unbind(WLVotingRow votingRow) {
        super.unbind(votingRow);
        votingRow.setListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
