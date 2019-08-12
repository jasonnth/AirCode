package com.airbnb.android.wishlists;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ParticipantRowModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.ArrayList;
import java.util.List;

public class WishListVotesFragment extends BaseWishListDetailsFragment {
    private static final String ITEM_EXTRA = "extra_listing";
    /* access modifiers changed from: private */
    public WishListItem item;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    private class VotesAdapter extends AirEpoxyController {
        public VotesAdapter() {
            requestModelBuild();
        }

        /* access modifiers changed from: protected */
        public void buildModels() {
            new DocumentMarqueeEpoxyModel_().m4536id((CharSequence) "marquee").titleRes(C1758R.string.wish_list_votes_sheet_title).addTo(this);
            List<User> likes = new ArrayList<>();
            List<User> dislikes = new ArrayList<>();
            for (User user : WishListVotesFragment.this.getWishListMembers()) {
                WLItemVote voteForUser = WLItemVote.getVote(WishListVotesFragment.this.item, user);
                if (voteForUser == WLItemVote.Up) {
                    likes.add(user);
                } else if (voteForUser == WLItemVote.Down) {
                    dislikes.add(user);
                }
            }
            if (!likes.isEmpty()) {
                new SectionHeaderEpoxyModel_().m5556id((CharSequence) "likes section").titleRes(C1758R.string.wish_list_votes_sheet_like_section_title).addTo(this);
                for (User user2 : likes) {
                    new ParticipantRowModel_(user2).addTo(this);
                }
            }
            if (!dislikes.isEmpty()) {
                new SectionHeaderEpoxyModel_().m5556id((CharSequence) "dislikes section").titleRes(C1758R.string.wish_list_votes_sheet_dislike_section_title).addTo(this);
                for (User user3 : dislikes) {
                    new ParticipantRowModel_(user3).addTo(this);
                }
            }
        }
    }

    public static WishListVotesFragment instance(WishListItem item2) {
        return (WishListVotesFragment) ((FragmentBundleBuilder) FragmentBundler.make(new WishListVotesFragment()).putParcelable("extra_listing", item2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C1758R.layout.recyclerview_with_toolbar, container, false);
        bindViews(v);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setTheme(3);
        this.item = (WishListItem) getArguments().getParcelable("extra_listing");
        this.recyclerView.setAdapter(new VotesAdapter().getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return v;
    }
}
