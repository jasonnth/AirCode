package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.EventScheduleInterstitial;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class EventScheduleInterstitialEpoxyModel_ extends EventScheduleInterstitialEpoxyModel implements GeneratedModel<EventScheduleInterstitial> {
    private OnModelBoundListener<EventScheduleInterstitialEpoxyModel_, EventScheduleInterstitial> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<EventScheduleInterstitialEpoxyModel_, EventScheduleInterstitial> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, EventScheduleInterstitial object, int position) {
        if (this.buttonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.buttonClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(EventScheduleInterstitial object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public EventScheduleInterstitialEpoxyModel_ onBind(OnModelBoundListener<EventScheduleInterstitialEpoxyModel_, EventScheduleInterstitial> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(EventScheduleInterstitial object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public EventScheduleInterstitialEpoxyModel_ onUnbind(OnModelUnboundListener<EventScheduleInterstitialEpoxyModel_, EventScheduleInterstitial> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public EventScheduleInterstitialEpoxyModel_ headerText(CharSequence headerText) {
        onMutation();
        this.headerText = headerText;
        return this;
    }

    public CharSequence headerText() {
        return this.headerText;
    }

    public EventScheduleInterstitialEpoxyModel_ headerTextRes(int headerTextRes) {
        onMutation();
        this.headerTextRes = headerTextRes;
        return this;
    }

    public int headerTextRes() {
        return this.headerTextRes;
    }

    public EventScheduleInterstitialEpoxyModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public EventScheduleInterstitialEpoxyModel_ titleTextRes(int titleTextRes) {
        onMutation();
        this.titleTextRes = titleTextRes;
        return this;
    }

    public int titleTextRes() {
        return this.titleTextRes;
    }

    public EventScheduleInterstitialEpoxyModel_ dateTimeText(CharSequence dateTimeText) {
        onMutation();
        this.dateTimeText = dateTimeText;
        return this;
    }

    public CharSequence dateTimeText() {
        return this.dateTimeText;
    }

    public EventScheduleInterstitialEpoxyModel_ dateTimeTextRes(int dateTimeTextRes) {
        onMutation();
        this.dateTimeTextRes = dateTimeTextRes;
        return this;
    }

    public int dateTimeTextRes() {
        return this.dateTimeTextRes;
    }

    public EventScheduleInterstitialEpoxyModel_ addressText(CharSequence addressText) {
        onMutation();
        this.addressText = addressText;
        return this;
    }

    public CharSequence addressText() {
        return this.addressText;
    }

    public EventScheduleInterstitialEpoxyModel_ addressTextRes(int addressTextRes) {
        onMutation();
        this.addressTextRes = addressTextRes;
        return this;
    }

    public int addressTextRes() {
        return this.addressTextRes;
    }

    public EventScheduleInterstitialEpoxyModel_ buttonText(CharSequence buttonText) {
        onMutation();
        this.buttonText = buttonText;
        return this;
    }

    public CharSequence buttonText() {
        return this.buttonText;
    }

    public EventScheduleInterstitialEpoxyModel_ buttonTextRes(int buttonTextRes) {
        onMutation();
        this.buttonTextRes = buttonTextRes;
        return this;
    }

    public int buttonTextRes() {
        return this.buttonTextRes;
    }

    public EventScheduleInterstitialEpoxyModel_ jellyFishPallete(int jellyFishPallete) {
        onMutation();
        this.jellyFishPallete = jellyFishPallete;
        return this;
    }

    public int jellyFishPallete() {
        return this.jellyFishPallete;
    }

    public EventScheduleInterstitialEpoxyModel_ buttonClickListener(OnModelClickListener<EventScheduleInterstitialEpoxyModel_, EventScheduleInterstitial> buttonClickListener) {
        onMutation();
        if (buttonClickListener == null) {
            this.buttonClickListener = null;
        } else {
            this.buttonClickListener = new WrappedEpoxyModelClickListener(this, buttonClickListener);
        }
        return this;
    }

    public EventScheduleInterstitialEpoxyModel_ buttonClickListener(OnClickListener buttonClickListener) {
        onMutation();
        this.buttonClickListener = buttonClickListener;
        return this;
    }

    public OnClickListener buttonClickListener() {
        return this.buttonClickListener;
    }

    public EventScheduleInterstitialEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public EventScheduleInterstitialEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public EventScheduleInterstitialEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public EventScheduleInterstitialEpoxyModel_ m4594id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public EventScheduleInterstitialEpoxyModel_ m4599id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public EventScheduleInterstitialEpoxyModel_ m4595id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public EventScheduleInterstitialEpoxyModel_ m4596id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public EventScheduleInterstitialEpoxyModel_ m4598id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public EventScheduleInterstitialEpoxyModel_ m4597id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public EventScheduleInterstitialEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public EventScheduleInterstitialEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public EventScheduleInterstitialEpoxyModel_ show() {
        super.show();
        return this;
    }

    public EventScheduleInterstitialEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public EventScheduleInterstitialEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_event_schedule_interstitial;
    }

    public EventScheduleInterstitialEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.headerText = null;
        this.headerTextRes = 0;
        this.titleText = null;
        this.titleTextRes = 0;
        this.dateTimeText = null;
        this.dateTimeTextRes = 0;
        this.addressText = null;
        this.addressTextRes = 0;
        this.buttonText = null;
        this.buttonTextRes = 0;
        this.jellyFishPallete = 0;
        this.buttonClickListener = null;
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
        if (!(o instanceof EventScheduleInterstitialEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        EventScheduleInterstitialEpoxyModel_ that = (EventScheduleInterstitialEpoxyModel_) o;
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
        if (this.headerText != null) {
            if (!this.headerText.equals(that.headerText)) {
                return false;
            }
        } else if (that.headerText != null) {
            return false;
        }
        if (this.headerTextRes != that.headerTextRes) {
            return false;
        }
        if (this.titleText != null) {
            if (!this.titleText.equals(that.titleText)) {
                return false;
            }
        } else if (that.titleText != null) {
            return false;
        }
        if (this.titleTextRes != that.titleTextRes) {
            return false;
        }
        if (this.dateTimeText != null) {
            if (!this.dateTimeText.equals(that.dateTimeText)) {
                return false;
            }
        } else if (that.dateTimeText != null) {
            return false;
        }
        if (this.dateTimeTextRes != that.dateTimeTextRes) {
            return false;
        }
        if (this.addressText != null) {
            if (!this.addressText.equals(that.addressText)) {
                return false;
            }
        } else if (that.addressText != null) {
            return false;
        }
        if (this.addressTextRes != that.addressTextRes) {
            return false;
        }
        if (this.buttonText != null) {
            if (!this.buttonText.equals(that.buttonText)) {
                return false;
            }
        } else if (that.buttonText != null) {
            return false;
        }
        if (this.buttonTextRes != that.buttonTextRes || this.jellyFishPallete != that.jellyFishPallete) {
            return false;
        }
        if (this.buttonClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.buttonClickListener == null)) {
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
        int i5;
        int i6;
        int i7;
        int i8 = 1;
        int i9 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i10 = (hashCode + i) * 31;
        if (this.headerText != null) {
            i2 = this.headerText.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (((i10 + i2) * 31) + this.headerTextRes) * 31;
        if (this.titleText != null) {
            i3 = this.titleText.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (((i11 + i3) * 31) + this.titleTextRes) * 31;
        if (this.dateTimeText != null) {
            i4 = this.dateTimeText.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (((i12 + i4) * 31) + this.dateTimeTextRes) * 31;
        if (this.addressText != null) {
            i5 = this.addressText.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (((i13 + i5) * 31) + this.addressTextRes) * 31;
        if (this.buttonText != null) {
            i6 = this.buttonText.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (((((i14 + i6) * 31) + this.buttonTextRes) * 31) + this.jellyFishPallete) * 31;
        if (this.buttonClickListener == null) {
            i8 = 0;
        }
        int i16 = (i15 + i8) * 31;
        if (this.showDivider != null) {
            i7 = this.showDivider.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.numCarouselItemsShown != null) {
            i9 = this.numCarouselItemsShown.hashCode();
        }
        return i17 + i9;
    }

    public String toString() {
        return "EventScheduleInterstitialEpoxyModel_{headerText=" + this.headerText + ", headerTextRes=" + this.headerTextRes + ", titleText=" + this.titleText + ", titleTextRes=" + this.titleTextRes + ", dateTimeText=" + this.dateTimeText + ", dateTimeTextRes=" + this.dateTimeTextRes + ", addressText=" + this.addressText + ", addressTextRes=" + this.addressTextRes + ", buttonText=" + this.buttonText + ", buttonTextRes=" + this.buttonTextRes + ", jellyFishPallete=" + this.jellyFishPallete + ", buttonClickListener=" + this.buttonClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
