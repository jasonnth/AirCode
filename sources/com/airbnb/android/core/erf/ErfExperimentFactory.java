package com.airbnb.android.core.erf;

import android.database.Cursor;
import com.airbnb.android.erf.p010db.ErfExperimentsModel.Factory;
import com.airbnb.android.erf.p010db.ErfExperimentsModel.Marshal;
import com.airbnb.android.utils.JacksonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErfExperimentFactory {
    private final Factory<ErfExperiment> factory;
    private final ObjectMapper objectMapper;

    public ErfExperimentFactory(ObjectMapper objectMapper2) {
        this.objectMapper = objectMapper2;
        this.factory = new Factory<>(ErfExperimentFactory$$Lambda$1.lambdaFactory$(objectMapper2));
    }

    static /* synthetic */ ErfExperiment lambda$new$0(ObjectMapper objectMapper2, String experimentName, String assignedTreatment, String subject, long version, String treatments, long timestamp, String holdoutExperimentName) {
        return new ErfExperiment(experimentName, assignedTreatment, JacksonUtils.readJsonArray(objectMapper2, treatments), subject, version, timestamp, holdoutExperimentName);
    }

    public ErfExperiment map(Cursor cursor) {
        return (ErfExperiment) this.factory.select_by_nameMapper().map(cursor);
    }

    public Marshal marshal(ErfExperiment model) {
        return this.factory.marshal().experimentName(model.experimentName()).assignedTreatment(model.assignedTreatment()).subject(model.subject()).version(model.version()).treatments(JacksonUtils.writeJsonArray(this.objectMapper, model.getTreatments())).timestamp(model.timestamp()).holdoutExperimentName(model.holdoutExperimentName());
    }
}
