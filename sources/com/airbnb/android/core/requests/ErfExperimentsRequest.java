package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.Transformer;
import com.airbnb.airrequest.Transformer.Factory;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.ErfExperimentsResponse;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ErfExperimentsRequest extends BaseRequestV2<ErfExperimentsResponse> {
    private static final String ERF_CLIENT_ANDROID = "android";
    private static final String TAG = "ErfExperimentsRequest";
    private final boolean isBatched;
    private final long startTime = System.nanoTime();

    public static class TransformerFactory implements Factory {
        private final ExperimentsProvider experimentsProvider;

        public TransformerFactory(ExperimentsProvider experimentsProvider2) {
            this.experimentsProvider = experimentsProvider2;
        }

        public Transformer<?> transformerFor(AirRequest request, AirRequestInitializer initializer) {
            if (request instanceof ErfExperimentsRequest) {
                return instrumentErfRequest((ErfExperimentsRequest) request);
            }
            if (isErfExperimentsBatchRequest(request)) {
                return instrumentBatchRequest((AirBatchRequest) request);
            }
            return null;
        }

        private Transformer<AirBatchResponse> instrumentBatchRequest(AirBatchRequest batchRequest) {
            return ErfExperimentsRequest$TransformerFactory$$Lambda$1.lambdaFactory$(this, batchRequest);
        }

        static /* synthetic */ void lambda$null$0(TransformerFactory transformerFactory, AirBatchRequest batchRequest, AirResponse response) {
            Class<ErfExperimentsRequest> cls = ErfExperimentsRequest.class;
            ErfExperimentsRequest erfRequest = (ErfExperimentsRequest) batchRequest.findRequestByClass(cls);
            ErfExperimentsResponse responseBody = (ErfExperimentsResponse) batchRequest.observer().findInnerResponseBodyByRequestType(response, cls);
            if (responseBody != null) {
                transformerFactory.experimentsProvider.resetExperiments(erfRequest, responseBody);
            } else {
                C0715L.m1191e(ErfExperimentsRequest.TAG, "Cannot find response body for ErfExperimentsRequest in batch request");
            }
        }

        private Transformer<ErfExperimentsResponse> instrumentErfRequest(ErfExperimentsRequest request) {
            return ErfExperimentsRequest$TransformerFactory$$Lambda$2.lambdaFactory$(this, request);
        }

        private boolean isErfExperimentsBatchRequest(AirRequest request) {
            if (request instanceof AirBatchRequest) {
                return ((AirBatchRequest) request).hasRequest(ErfExperimentsRequest.class);
            }
            return false;
        }
    }

    public ErfExperimentsRequest(boolean isBatched2) {
        this.isBatched = isBatched2;
    }

    public String getPath() {
        return ErfExperimentsModel.TABLE_NAME;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("client", "android");
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 3600000;
    }

    public Type successResponseType() {
        return ErfExperimentsResponse.class;
    }

    public long startTime() {
        return this.startTime;
    }

    public boolean isBatched() {
        return this.isBatched;
    }
}
