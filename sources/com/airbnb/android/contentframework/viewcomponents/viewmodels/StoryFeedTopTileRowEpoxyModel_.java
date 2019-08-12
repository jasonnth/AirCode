package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.views.StoryFeedTopTileRow;
import com.airbnb.android.core.models.StoryFeedTopTile;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class StoryFeedTopTileRowEpoxyModel_ extends StoryFeedTopTileRowEpoxyModel implements GeneratedModel<StoryFeedTopTileRow> {
    private OnModelBoundListener<StoryFeedTopTileRowEpoxyModel_, StoryFeedTopTileRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StoryFeedTopTileRowEpoxyModel_, StoryFeedTopTileRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StoryFeedTopTileRow object, int position) {
    }

    public void handlePostBind(StoryFeedTopTileRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StoryFeedTopTileRowEpoxyModel_ onBind(OnModelBoundListener<StoryFeedTopTileRowEpoxyModel_, StoryFeedTopTileRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StoryFeedTopTileRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StoryFeedTopTileRowEpoxyModel_ onUnbind(OnModelUnboundListener<StoryFeedTopTileRowEpoxyModel_, StoryFeedTopTileRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StoryFeedTopTileRowEpoxyModel_ topTileList(List<StoryFeedTopTile> topTileList) {
        onMutation();
        this.topTileList = topTileList;
        return this;
    }

    public List<StoryFeedTopTile> topTileList() {
        return this.topTileList;
    }

    public StoryFeedTopTileRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StoryFeedTopTileRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StoryFeedTopTileRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StoryFeedTopTileRowEpoxyModel_ m4188id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StoryFeedTopTileRowEpoxyModel_ m4193id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StoryFeedTopTileRowEpoxyModel_ m4189id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StoryFeedTopTileRowEpoxyModel_ m4190id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StoryFeedTopTileRowEpoxyModel_ m4192id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StoryFeedTopTileRowEpoxyModel_ m4191id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StoryFeedTopTileRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StoryFeedTopTileRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StoryFeedTopTileRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StoryFeedTopTileRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StoryFeedTopTileRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_story_feed_top_tile_row;
    }

    public StoryFeedTopTileRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.topTileList = null;
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
        if (!(o instanceof StoryFeedTopTileRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StoryFeedTopTileRowEpoxyModel_ that = (StoryFeedTopTileRowEpoxyModel_) o;
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
        if (this.topTileList != null) {
            if (!this.topTileList.equals(that.topTileList)) {
                return false;
            }
        } else if (that.topTileList != null) {
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
        int i3 = 1;
        int i4 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i3 = 0;
        }
        int i5 = (hashCode + i3) * 31;
        if (this.topTileList != null) {
            i = this.topTileList.hashCode();
        } else {
            i = 0;
        }
        int i6 = (i5 + i) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i4 = this.numCarouselItemsShown.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "StoryFeedTopTileRowEpoxyModel_{topTileList=" + this.topTileList + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
