package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.LeftAlignedImageRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LeftAlignedImageRowEpoxyModel extends AirEpoxyModel<LeftAlignedImageRow> {
    private static final boolean DEFAULT_ENABLED = true;
    OnClickListener clickListener;
    boolean enabled = DEFAULT_ENABLED;
    int imageDrawableRes;
    CharSequence subtitle;
    int subtitleRes;
    int subtitleStyle = C0716R.C0719style.n2_SmallText;
    CharSequence title;
    int titleRes;
    int titleStyle = C0716R.C0719style.n2_LeftAlignedImageRow_title;

    public void bind(LeftAlignedImageRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualSubtitle = this.subtitleRes != 0 ? context.getString(this.subtitleRes) : this.subtitle;
        view.setTitle(actualTitle);
        view.setSubtitle(actualSubtitle);
        view.setOnClickListener(this.clickListener);
        if (this.imageDrawableRes != 0) {
            view.setImage(this.imageDrawableRes);
        } else {
            view.clearImage();
        }
    }

    public void unbind(LeftAlignedImageRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public AirEpoxyModel<LeftAlignedImageRow> reset() {
        this.enabled = DEFAULT_ENABLED;
        this.titleStyle = C0716R.C0719style.n2_LeftAlignedImageRow_title;
        this.subtitleStyle = C0716R.C0719style.n2_SmallText;
        return super.reset();
    }

    public int getDividerViewType() {
        return 0;
    }
}
