package com.airbnb.android.core.viewcomponents.models;

import android.view.View;
import android.view.ViewStub;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.Check;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirModel;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p032rx.functions.Action2;

public class EpoxyModelMixer extends AirEpoxyModelWithHolder<Holder> {
    private static final int MAX_MODELS_SUPPORTED = 5;
    protected final ImmutableList<EpoxyModel> models;
    private final boolean shouldSaveViewState;

    protected class Holder extends AirViewHolder {
        /* access modifiers changed from: private */
        public List<View> views;

        protected Holder() {
        }

        /* access modifiers changed from: protected */
        public void bindView(View itemView) {
            super.bindView(itemView);
            int modelCount = EpoxyModelMixer.this.models.size();
            this.views = new ArrayList(modelCount);
            for (int i = 0; i < modelCount; i++) {
                EpoxyModel<?> model = (EpoxyModel) EpoxyModelMixer.this.models.get(i);
                ViewStub viewStub = getViewStub(itemView, i, model);
                viewStub.setLayoutResource(model.getLayout());
                this.views.add(viewStub.inflate());
            }
        }

        private ViewStub getViewStub(View itemView, int i, EpoxyModel<?> model) {
            View stub = itemView.findViewById(getIdForIndex(i));
            if (stub == null) {
                throw new IllegalStateException("The expected view for your model " + model + " at position " + i + " wasn't found. Are you using the correct stub id?");
            } else if (stub instanceof ViewStub) {
                return (ViewStub) stub;
            } else {
                throw new IllegalStateException("Your layout should provide a ViewStub. See the layout() method javadoc for more info.");
            }
        }

        private int getIdForIndex(int modelIndex) {
            switch (modelIndex) {
                case 0:
                    return C0716R.C0718id.model_mixer_view_stub_1;
                case 1:
                    return C0716R.C0718id.model_mixer_view_stub_2;
                case 2:
                    return C0716R.C0718id.model_mixer_view_stub_3;
                case 3:
                    return C0716R.C0718id.model_mixer_view_stub_4;
                case 4:
                    return C0716R.C0718id.model_mixer_view_stub_5;
                default:
                    throw new IllegalStateException("No support for more than 5 models");
            }
        }

        /* access modifiers changed from: protected */
        public void showDivider(boolean showDivider) {
        }
    }

    public EpoxyModelMixer(int layoutRes, Collection<EpoxyModel> models2) {
        this(layoutRes, ImmutableList.copyOf(models2));
    }

    public EpoxyModelMixer(int layoutRes, EpoxyModel... models2) {
        this(layoutRes, ImmutableList.copyOf((E[]) models2));
    }

    protected EpoxyModelMixer(int layoutRes, ImmutableList<EpoxyModel> models2) {
        boolean z;
        Check.notEmpty(models2, "Must contain at least one model");
        if (models2.size() <= 5) {
            z = true;
        } else {
            z = false;
        }
        Check.state(z, "Too many models added.");
        this.models = models2;
        layout(layoutRes);
        mo11716id(((EpoxyModel) models2.get(0)).mo11715id());
        boolean saveState = false;
        UnmodifiableIterator it = models2.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((EpoxyModel) it.next()).shouldSaveViewState()) {
                    saveState = true;
                    break;
                }
            } else {
                break;
            }
        }
        this.shouldSaveViewState = saveState;
    }

    public EpoxyModelMixer layout(int layoutRes) {
        super.layout(layoutRes);
        return this;
    }

    public final void bind(Holder holder) {
        super.bind(holder);
        iterateVisibleModels(holder, EpoxyModelMixer$$Lambda$1.lambdaFactory$());
    }

    public final void bind(Holder holder, List<Object> payloads) {
        super.bind(holder, payloads);
        iterateVisibleModels(holder, EpoxyModelMixer$$Lambda$2.lambdaFactory$(payloads));
    }

    public final void unbind(Holder holder) {
        super.unbind(holder);
        iterateVisibleModels(holder, EpoxyModelMixer$$Lambda$3.lambdaFactory$());
    }

    private void iterateVisibleModels(Holder holder, Action2<EpoxyModel, View> callback) {
        for (int i = 0; i < this.models.size(); i++) {
            View view = (View) holder.views.get(i);
            EpoxyModel model = (EpoxyModel) this.models.get(i);
            ViewLibUtils.setVisibleIf(view, model.isShown());
            if (model.isShown()) {
                callback.call(model, view);
            }
        }
    }

    public int getDividerViewType() {
        for (int i = this.models.size() - 1; i >= 0; i--) {
            EpoxyModel model = (EpoxyModel) this.models.get(i);
            if (model.isShown() && (model instanceof AirModel)) {
                return ((AirModel) model).getDividerViewType();
            }
        }
        return -1;
    }

    public EpoxyModelMixer showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        updateModelDividerVisibility(showDivider);
        return this;
    }

    /* access modifiers changed from: protected */
    public void updateModelDividerVisibility(boolean showDivider) {
        boolean hasToggledModel = false;
        for (int i = this.models.size() - 1; i >= 0; i--) {
            EpoxyModel model = (EpoxyModel) this.models.get(i);
            if (model instanceof AirModel) {
                AirModel airModel = (AirModel) model;
                if (model.isShown() && airModel.supportsDividers() && airModel.isShowingDivider() == null) {
                    airModel.showDivider(!hasToggledModel && showDivider);
                    hasToggledModel = true;
                }
            }
        }
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return ((EpoxyModel) this.models.get(0)).getSpanSize(totalSpanCount, position, itemCount);
    }

    /* access modifiers changed from: protected */
    public final int getDefaultLayout() {
        throw new UnsupportedOperationException("You should set a layout with layout(...) instead of using this.");
    }

    public boolean shouldSaveViewState() {
        return this.shouldSaveViewState;
    }

    /* access modifiers changed from: protected */
    public final Holder createNewHolder() {
        return new Holder();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EpoxyModelMixer) || !super.equals(o)) {
            return false;
        }
        return this.models.equals(((EpoxyModelMixer) o).models);
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.models.hashCode();
    }
}
