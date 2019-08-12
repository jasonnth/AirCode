package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class WLVotingRow_ViewBinding implements Unbinder {
    private WLVotingRow target;
    private View view2131756166;
    private View view2131756169;

    public WLVotingRow_ViewBinding(WLVotingRow target2) {
        this(target2, target2);
    }

    public WLVotingRow_ViewBinding(final WLVotingRow target2, View source) {
        this.target = target2;
        target2.likeButton = (ImageView) Utils.findRequiredViewAsType(source, C1758R.C1760id.btn_up_vote, "field 'likeButton'", ImageView.class);
        target2.unlikeButton = (ImageView) Utils.findRequiredViewAsType(source, C1758R.C1760id.btn_down_vote, "field 'unlikeButton'", ImageView.class);
        target2.upVoteCountText = (TextView) Utils.findRequiredViewAsType(source, C1758R.C1760id.txt_up_vote_count, "field 'upVoteCountText'", TextView.class);
        target2.downVoteCountText = (TextView) Utils.findRequiredViewAsType(source, C1758R.C1760id.txt_down_vote_count, "field 'downVoteCountText'", TextView.class);
        target2.divider = Utils.findRequiredView(source, C1758R.C1760id.section_divider, "field 'divider'");
        View view = Utils.findRequiredView(source, C1758R.C1760id.up_vote_container, "method 'onVoteClicked' and method 'onVoteLongClick'");
        this.view2131756166 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onVoteClicked(p0);
            }
        });
        view.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View p0) {
                return target2.onVoteLongClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C1758R.C1760id.down_vote_container, "method 'onVoteClicked' and method 'onVoteLongClick'");
        this.view2131756169 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onVoteClicked(p0);
            }
        });
        view2.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View p0) {
                return target2.onVoteLongClick();
            }
        });
    }

    public void unbind() {
        WLVotingRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.likeButton = null;
        target2.unlikeButton = null;
        target2.upVoteCountText = null;
        target2.downVoteCountText = null;
        target2.divider = null;
        this.view2131756166.setOnClickListener(null);
        this.view2131756166.setOnLongClickListener(null);
        this.view2131756166 = null;
        this.view2131756169.setOnClickListener(null);
        this.view2131756169.setOnLongClickListener(null);
        this.view2131756169 = null;
    }
}
