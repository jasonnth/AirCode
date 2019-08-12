package com.airbnb.android.contentframework.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;

public class StoryCreationImageViewWrapper_ViewBinding implements Unbinder {
    private StoryCreationImageViewWrapper target;
    private View view2131756095;

    public StoryCreationImageViewWrapper_ViewBinding(StoryCreationImageViewWrapper target2) {
        this(target2, target2);
    }

    public StoryCreationImageViewWrapper_ViewBinding(final StoryCreationImageViewWrapper target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C5709R.C5711id.options, "field 'optionsView' and method 'onImageOptionsClicked'");
        target2.optionsView = view;
        this.view2131756095 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onImageOptionsClicked();
            }
        });
        target2.imageView = (StoryCreationImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.image, "field 'imageView'", StoryCreationImageView.class);
        target2.cardPadding = source.getContext().getResources().getDimensionPixelSize(C5709R.dimen.n2_carousel_card_padding);
    }

    public void unbind() {
        StoryCreationImageViewWrapper target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.optionsView = null;
        target2.imageView = null;
        this.view2131756095.setOnClickListener(null);
        this.view2131756095 = null;
    }
}
