package com.airbnb.android.wishlists;

import com.airbnb.android.wishlists.WLVotingRow.WLVotingClickListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class WLVotingRowModel_ extends WLVotingRowModel implements GeneratedModel<WLVotingRow> {
    private OnModelBoundListener<WLVotingRowModel_, WLVotingRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<WLVotingRowModel_, WLVotingRow> onModelUnboundListener_epoxyGeneratedModel;

    public /* bridge */ /* synthetic */ void bind(WLVotingRow wLVotingRow) {
        super.bind(wLVotingRow);
    }

    public /* bridge */ /* synthetic */ int getDividerViewType() {
        return super.getDividerViewType();
    }

    WLVotingRowModel_() {
    }

    public void handlePreBind(EpoxyViewHolder holder, WLVotingRow object, int position) {
    }

    public void handlePostBind(WLVotingRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public WLVotingRowModel_ onBind(OnModelBoundListener<WLVotingRowModel_, WLVotingRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(WLVotingRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public WLVotingRowModel_ onUnbind(OnModelUnboundListener<WLVotingRowModel_, WLVotingRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public WLVotingRowModel_ vote(WLItemVote vote) {
        onMutation();
        this.vote = vote;
        return this;
    }

    public WLItemVote vote() {
        return this.vote;
    }

    public WLVotingRowModel_ upVoteCount(int upVoteCount) {
        onMutation();
        this.upVoteCount = upVoteCount;
        return this;
    }

    public int upVoteCount() {
        return this.upVoteCount;
    }

    public WLVotingRowModel_ downVoteCount(int downVoteCount) {
        onMutation();
        this.downVoteCount = downVoteCount;
        return this;
    }

    public int downVoteCount() {
        return this.downVoteCount;
    }

    public WLVotingRowModel_ gridMode(boolean gridMode) {
        onMutation();
        this.gridMode = gridMode;
        return this;
    }

    public boolean gridMode() {
        return this.gridMode;
    }

    public WLVotingRowModel_ listener(WLVotingClickListener listener) {
        onMutation();
        this.listener = listener;
        return this;
    }

    public WLVotingClickListener listener() {
        return this.listener;
    }

    public WLVotingRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public WLVotingRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public WLVotingRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public WLVotingRowModel_ m1470id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public WLVotingRowModel_ m1475id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public WLVotingRowModel_ m1471id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public WLVotingRowModel_ m1472id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public WLVotingRowModel_ m1474id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public WLVotingRowModel_ m1473id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public WLVotingRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public WLVotingRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public WLVotingRowModel_ show() {
        super.show();
        return this;
    }

    public WLVotingRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public WLVotingRowModel_ hide() {
        super.hide();
        return this;
    }

    public WLVotingRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.vote = null;
        this.upVoteCount = 0;
        this.downVoteCount = 0;
        this.gridMode = false;
        this.listener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof WLVotingRowModel_) || !super.equals(o)) {
            return false;
        }
        WLVotingRowModel_ that = (WLVotingRowModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.vote != null) {
            if (!this.vote.equals(that.vote)) {
                return false;
            }
        } else if (that.vote != null) {
            return false;
        }
        if (this.upVoteCount != that.upVoteCount || this.downVoteCount != that.downVoteCount || this.gridMode != that.gridMode) {
            return false;
        }
        if ((this.listener == null) != (that.listener == null)) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
            return false;
        }
        if (this.numCarouselItemsShown != null) {
            if (!this.numCarouselItemsShown.equals(that.numCarouselItemsShown)) {
                return false;
            }
        } else if (that.numCarouselItemsShown != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.vote != null) {
            i2 = this.vote.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (((((i7 + i2) * 31) + this.upVoteCount) * 31) + this.downVoteCount) * 31;
        if (this.gridMode) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.listener == null) {
            i5 = 0;
        }
        int i10 = (i9 + i5) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.numCarouselItemsShown != null) {
            i6 = this.numCarouselItemsShown.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "WLVotingRowModel_{vote=" + this.vote + ", upVoteCount=" + this.upVoteCount + ", downVoteCount=" + this.downVoteCount + ", gridMode=" + this.gridMode + ", listener=" + this.listener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
