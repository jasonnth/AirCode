package com.airbnb.android.contentframework.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryDetailViewFragment_ViewBinding implements Unbinder {
    private StoryDetailViewFragment target;
    private View view2131755560;
    private View view2131755561;
    private View view2131755563;

    public StoryDetailViewFragment_ViewBinding(final StoryDetailViewFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5709R.C5711id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5709R.C5711id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.likeIcon = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.like_icon, "field 'likeIcon'", AirImageView.class);
        target2.likeCount = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.like_count, "field 'likeCount'", AirTextView.class);
        target2.commentCount = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.comment_count, "field 'commentCount'", AirTextView.class);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C5709R.C5711id.loading_overlay, "field 'loaderFrame'", LoaderFrame.class);
        View view = Utils.findRequiredView(source, C5709R.C5711id.write_comment_button, "method 'onAddCommentButtonClicked'");
        this.view2131755563 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onAddCommentButtonClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C5709R.C5711id.view_comment_button, "method 'onViewCommentButtonClicked'");
        this.view2131755561 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onViewCommentButtonClicked();
            }
        });
        View view3 = Utils.findRequiredView(source, C5709R.C5711id.like_button, "method 'onLikeButtonClicked'");
        this.view2131755560 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onLikeButtonClicked();
            }
        });
    }

    public void unbind() {
        StoryDetailViewFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.likeIcon = null;
        target2.likeCount = null;
        target2.commentCount = null;
        target2.loaderFrame = null;
        this.view2131755563.setOnClickListener(null);
        this.view2131755563 = null;
        this.view2131755561.setOnClickListener(null);
        this.view2131755561 = null;
        this.view2131755560.setOnClickListener(null);
        this.view2131755560 = null;
    }
}
