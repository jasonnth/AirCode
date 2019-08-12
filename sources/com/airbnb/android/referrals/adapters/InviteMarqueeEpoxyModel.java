package com.airbnb.android.referrals.adapters;

import android.view.View;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.referrals.C1532R;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;

public abstract class InviteMarqueeEpoxyModel extends AirEpoxyModelWithHolder<Holder> {
    LinkOnClickListener subtitleLinkClickListener;
    String subtitleLinkText;
    String subtitleText;
    String titleText;

    public class Holder extends AirViewHolder {
        @BindView
        AirTextView subtitle;
        @BindView
        AirTextView title;

        public Holder() {
        }
    }

    public class Holder_ViewBinding implements Unbinder {
        private Holder target;

        public Holder_ViewBinding(Holder target2, View source) {
            this.target = target2;
            target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C1532R.C1534id.title, "field 'title'", AirTextView.class);
            target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, C1532R.C1534id.subtitle, "field 'subtitle'", AirTextView.class);
        }

        public void unbind() {
            Holder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.title = null;
            target2.subtitle = null;
        }
    }

    public void bind(Holder holder) {
        super.bind(holder);
        holder.title.setText(this.titleText);
        ClickableLinkUtils.setupClickableTextView(holder.subtitle, this.subtitleText, this.subtitleLinkText, holder.subtitle.getContext().getResources().getColor(C1532R.color.n2_white), C1532R.color.n2_white_20, this.subtitleLinkClickListener, true);
    }
}
