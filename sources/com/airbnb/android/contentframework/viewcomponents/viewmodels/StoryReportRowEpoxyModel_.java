package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.AirTextView;

public class StoryReportRowEpoxyModel_ extends StoryReportRowEpoxyModel implements GeneratedModel<AirTextView> {
    private OnModelBoundListener<StoryReportRowEpoxyModel_, AirTextView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StoryReportRowEpoxyModel_, AirTextView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, AirTextView object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(AirTextView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StoryReportRowEpoxyModel_ onBind(OnModelBoundListener<StoryReportRowEpoxyModel_, AirTextView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(AirTextView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StoryReportRowEpoxyModel_ onUnbind(OnModelUnboundListener<StoryReportRowEpoxyModel_, AirTextView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StoryReportRowEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public StoryReportRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public StoryReportRowEpoxyModel_ clickListener(OnModelClickListener<StoryReportRowEpoxyModel_, AirTextView> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public StoryReportRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public StoryReportRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StoryReportRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StoryReportRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StoryReportRowEpoxyModel_ m4224id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StoryReportRowEpoxyModel_ m4229id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StoryReportRowEpoxyModel_ m4225id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StoryReportRowEpoxyModel_ m4226id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StoryReportRowEpoxyModel_ m4228id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StoryReportRowEpoxyModel_ m4227id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StoryReportRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StoryReportRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StoryReportRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StoryReportRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StoryReportRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_story_report_row;
    }

    public StoryReportRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.textRes = 0;
        this.enabled = false;
        this.clickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof StoryReportRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StoryReportRowEpoxyModel_ that = (StoryReportRowEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.textRes != that.textRes || this.enabled != that.enabled) {
            return false;
        }
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (((hashCode + i) * 31) + this.textRes) * 31;
        if (this.enabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.clickListener == null) {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
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
        return "StoryReportRowEpoxyModel_{textRes=" + this.textRes + ", enabled=" + this.enabled + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
