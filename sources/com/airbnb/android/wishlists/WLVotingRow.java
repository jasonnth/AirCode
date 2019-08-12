package com.airbnb.android.wishlists;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class WLVotingRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    TextView downVoteCountText;
    private int downVotes;
    @BindView
    ImageView likeButton;
    private WLVotingClickListener listener;
    @BindView
    ImageView unlikeButton;
    @BindView
    TextView upVoteCountText;
    private int upVotes;
    private WLItemVote vote = WLItemVote.None;

    public interface WLVotingClickListener {
        void onVoteClicked(WLItemVote wLItemVote);

        void onVoteCountClicked();
    }

    public WLVotingRow(Context context) {
        super(context);
        init();
    }

    public WLVotingRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WLVotingRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), C1758R.layout.view_wl_voting_row, this);
        ButterKnife.bind((View) this);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onVoteClicked(View view) {
        this.listener.onVoteClicked(view.getId() == C1758R.C1760id.up_vote_container ? WLItemVote.Up : WLItemVote.Down);
    }

    /* access modifiers changed from: 0000 */
    @OnLongClick
    public boolean onVoteLongClick() {
        this.listener.onVoteCountClicked();
        return true;
    }

    public void setListener(WLVotingClickListener listener2) {
        this.listener = listener2;
    }

    public void setVote(WLItemVote vote2) {
        boolean z;
        boolean z2 = true;
        this.vote = vote2;
        ImageView imageView = this.likeButton;
        if (vote2 == WLItemVote.Up) {
            z = true;
        } else {
            z = false;
        }
        imageView.setActivated(z);
        ImageView imageView2 = this.unlikeButton;
        if (vote2 != WLItemVote.Down) {
            z2 = false;
        }
        imageView2.setActivated(z2);
    }

    public void setVoteCounts(int upVotes2, int downVotes2) {
        this.upVotes = upVotes2;
        this.downVotes = downVotes2;
        updateVoteCountText();
    }

    private void updateVoteCountText() {
        boolean z;
        boolean z2 = true;
        this.upVoteCountText.setText(String.valueOf(this.upVotes));
        TextView textView = this.upVoteCountText;
        if (this.upVotes > 0) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(textView, z);
        this.upVoteCountText.setTextColor(ContextCompat.getColor(getContext(), this.vote == WLItemVote.Up ? C1758R.color.n2_babu : C1758R.color.n2_text_color_main));
        this.downVoteCountText.setText(String.valueOf(this.downVotes));
        TextView textView2 = this.downVoteCountText;
        if (this.downVotes <= 0) {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(textView2, z2);
        this.downVoteCountText.setTextColor(ContextCompat.getColor(getContext(), this.vote == WLItemVote.Down ? C1758R.color.n2_babu : C1758R.color.n2_text_color_main));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }
}
