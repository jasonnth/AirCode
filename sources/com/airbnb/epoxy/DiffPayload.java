package com.airbnb.epoxy;

import android.support.p000v4.util.LongSparseArray;
import java.util.Iterator;
import java.util.List;

public class DiffPayload {
    private final LongSparseArray<EpoxyModel<?>> modelsById;
    private final EpoxyModel<?> singleModel;

    DiffPayload(List<? extends EpoxyModel<?>> models) {
        if (models.isEmpty()) {
            throw new IllegalStateException("Models must not be empty");
        }
        int modelCount = models.size();
        if (modelCount == 1) {
            this.singleModel = (EpoxyModel) models.get(0);
            this.modelsById = null;
            return;
        }
        this.singleModel = null;
        this.modelsById = new LongSparseArray<>(modelCount);
        for (EpoxyModel<?> model : models) {
            this.modelsById.put(model.mo11715id(), model);
        }
    }

    public static EpoxyModel<?> getModelFromPayload(List<Object> payloads, long modelId) {
        if (payloads.isEmpty()) {
            return null;
        }
        Iterator it = payloads.iterator();
        while (it.hasNext()) {
            DiffPayload diffPayload = (DiffPayload) it.next();
            if (diffPayload.singleModel == null) {
                EpoxyModel<?> modelForId = (EpoxyModel) diffPayload.modelsById.get(modelId);
                if (modelForId != null) {
                    return modelForId;
                }
            } else if (diffPayload.singleModel.mo11715id() == modelId) {
                return diffPayload.singleModel;
            }
        }
        return null;
    }
}
