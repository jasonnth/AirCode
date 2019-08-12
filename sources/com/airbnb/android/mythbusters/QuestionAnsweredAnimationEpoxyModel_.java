package com.airbnb.android.mythbusters;

import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;

public class QuestionAnsweredAnimationEpoxyModel_ extends QuestionAnsweredAnimationEpoxyModel implements GeneratedModel<Holder> {
    private OnModelBoundListener<QuestionAnsweredAnimationEpoxyModel_, Holder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<QuestionAnsweredAnimationEpoxyModel_, Holder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, Holder object, int position) {
    }

    public void handlePostBind(Holder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public QuestionAnsweredAnimationEpoxyModel_ onBind(OnModelBoundListener<QuestionAnsweredAnimationEpoxyModel_, Holder> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(Holder object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public QuestionAnsweredAnimationEpoxyModel_ onUnbind(OnModelUnboundListener<QuestionAnsweredAnimationEpoxyModel_, Holder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public QuestionAnsweredAnimationEpoxyModel_ questionAnsweredCorrectly(boolean questionAnsweredCorrectly) {
        onMutation();
        this.questionAnsweredCorrectly = questionAnsweredCorrectly;
        return this;
    }

    public boolean questionAnsweredCorrectly() {
        return this.questionAnsweredCorrectly;
    }

    public QuestionAnsweredAnimationEpoxyModel_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public QuestionAnsweredAnimationEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public QuestionAnsweredAnimationEpoxyModel_ m6287id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public QuestionAnsweredAnimationEpoxyModel_ m6292id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public QuestionAnsweredAnimationEpoxyModel_ m6288id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public QuestionAnsweredAnimationEpoxyModel_ m6289id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public QuestionAnsweredAnimationEpoxyModel_ m6291id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public QuestionAnsweredAnimationEpoxyModel_ m6290id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public QuestionAnsweredAnimationEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public QuestionAnsweredAnimationEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public QuestionAnsweredAnimationEpoxyModel_ show() {
        super.show();
        return this;
    }

    public QuestionAnsweredAnimationEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public QuestionAnsweredAnimationEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public Holder createNewHolder() {
        return new Holder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7485R.layout.lottie_animation_view;
    }

    public QuestionAnsweredAnimationEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.questionAnsweredCorrectly = false;
        this.showDivider = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof QuestionAnsweredAnimationEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        QuestionAnsweredAnimationEpoxyModel_ that = (QuestionAnsweredAnimationEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.questionAnsweredCorrectly != that.questionAnsweredCorrectly) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3 = 1;
        int i4 = 0;
        int hashCode = super.hashCode() * 31;
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i5 = (hashCode + i) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i6 = (i5 + i2) * 31;
        if (!this.questionAnsweredCorrectly) {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "QuestionAnsweredAnimationEpoxyModel_{questionAnsweredCorrectly=" + this.questionAnsweredCorrectly + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
