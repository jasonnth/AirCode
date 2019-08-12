package com.airbnb.p027n2.components;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.StringAttributeData;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.TagsCollectionRow.TagRowItem;
import com.airbnb.p027n2.epoxy.DefaultDividerBaseModel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.ArrayList;
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.TagsCollectionRowModel_ */
public class TagsCollectionRowModel_ extends DefaultDividerBaseModel<TagsCollectionRow> implements GeneratedModel<TagsCollectionRow> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(4);
    private OnModelBoundListener<TagsCollectionRowModel_, TagsCollectionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<TagsCollectionRowModel_, TagsCollectionRow> onModelUnboundListener_epoxyGeneratedModel;
    private ArrayList<TagRowItem> tags_ArrayList;
    private StringAttributeData title_StringAttributeData = new StringAttributeData(null);

    public void handlePreBind(EpoxyViewHolder holder, TagsCollectionRow object, int position) {
    }

    public void bind(TagsCollectionRow object) {
        super.bind(object);
        object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        object.setTags(this.tags_ArrayList);
    }

    public void bind(TagsCollectionRow object, EpoxyModel previousModel) {
        if (!(previousModel instanceof TagsCollectionRowModel_)) {
            bind(object);
            return;
        }
        TagsCollectionRowModel_ that = (TagsCollectionRowModel_) previousModel;
        super.bind(object);
        if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
            object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        }
        if (this.tags_ArrayList != null) {
            if (this.tags_ArrayList.equals(that.tags_ArrayList)) {
                return;
            }
        } else if (that.tags_ArrayList == null) {
            return;
        }
        object.setTags(this.tags_ArrayList);
    }

    public void handlePostBind(TagsCollectionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(TagsCollectionRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public TagsCollectionRowModel_ tags(ArrayList<TagRowItem> tags) {
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        onMutation();
        this.tags_ArrayList = tags;
        return this;
    }

    public TagsCollectionRowModel_ title(CharSequence title) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.title_StringAttributeData.setValue(title);
        return this;
    }

    public TagsCollectionRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public TagsCollectionRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public TagsCollectionRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public TagsCollectionRowModel_ m1578id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public TagsCollectionRowModel_ m1583id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public TagsCollectionRowModel_ m1579id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public TagsCollectionRowModel_ m1580id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public TagsCollectionRowModel_ m1582id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public TagsCollectionRowModel_ m1581id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public TagsCollectionRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public TagsCollectionRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public TagsCollectionRowModel_ show() {
        super.show();
        return this;
    }

    public TagsCollectionRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public TagsCollectionRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_tags_collection_row;
    }

    public TagsCollectionRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.tags_ArrayList = null;
        this.title_StringAttributeData = new StringAttributeData(null);
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
        if (!(o instanceof TagsCollectionRowModel_) || !super.equals(o)) {
            return false;
        }
        TagsCollectionRowModel_ that = (TagsCollectionRowModel_) o;
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
        if (this.tags_ArrayList != null) {
            if (!this.tags_ArrayList.equals(that.tags_ArrayList)) {
                return false;
            }
        } else if (that.tags_ArrayList != null) {
            return false;
        }
        if (this.title_StringAttributeData != null) {
            if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
                return false;
            }
        } else if (that.title_StringAttributeData != null) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i4 = 0;
        }
        int i6 = (hashCode + i4) * 31;
        if (this.tags_ArrayList != null) {
            i = this.tags_ArrayList.hashCode();
        } else {
            i = 0;
        }
        int i7 = (i6 + i) * 31;
        if (this.title_StringAttributeData != null) {
            i2 = this.title_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "TagsCollectionRowModel_{tags_ArrayList=" + this.tags_ArrayList + ", title_StringAttributeData=" + this.title_StringAttributeData + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
