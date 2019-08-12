package com.airbnb.epoxy;

import java.util.HashMap;
import java.util.Map;

class ViewTypeManager {
    private static Map<Class, Integer> viewTypeMap;
    private EpoxyModel<?> lastModelForViewTypeLookup;

    ViewTypeManager() {
    }

    /* access modifiers changed from: 0000 */
    public int getViewType(EpoxyModel<?> model) {
        this.lastModelForViewTypeLookup = model;
        return getViewTypeInternal(model);
    }

    private static int getViewTypeInternal(EpoxyModel<?> model) {
        int defaultViewType = model.getViewType();
        if (defaultViewType != 0) {
            return defaultViewType;
        }
        Class modelClass = model.getClass();
        if (viewTypeMap == null) {
            viewTypeMap = new HashMap();
        }
        Integer viewType = (Integer) viewTypeMap.get(modelClass);
        if (viewType == null) {
            viewType = Integer.valueOf((-viewTypeMap.size()) - 1);
            viewTypeMap.put(modelClass, viewType);
        }
        return viewType.intValue();
    }

    /* access modifiers changed from: 0000 */
    public EpoxyModel<?> getModelForViewType(BaseEpoxyAdapter adapter, int viewType) {
        if (this.lastModelForViewTypeLookup != null && getViewTypeInternal(this.lastModelForViewTypeLookup) == viewType) {
            return this.lastModelForViewTypeLookup;
        }
        adapter.onExceptionSwallowed(new IllegalStateException("Last model did not match expected view type"));
        for (EpoxyModel<?> model : adapter.getCurrentModels()) {
            if (model.getViewType() == viewType) {
                return model;
            }
        }
        HiddenEpoxyModel hiddenEpoxyModel = new HiddenEpoxyModel();
        if (viewType == hiddenEpoxyModel.getViewType()) {
            return hiddenEpoxyModel;
        }
        throw new IllegalStateException("Could not find model for view type: " + viewType);
    }
}
