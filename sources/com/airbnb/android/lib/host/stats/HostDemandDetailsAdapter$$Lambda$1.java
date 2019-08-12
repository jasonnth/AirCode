package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.SimilarListing;
import com.google.common.base.Function;

final /* synthetic */ class HostDemandDetailsAdapter$$Lambda$1 implements Function {
    private final HostDemandDetailsAdapter arg$1;

    private HostDemandDetailsAdapter$$Lambda$1(HostDemandDetailsAdapter hostDemandDetailsAdapter) {
        this.arg$1 = hostDemandDetailsAdapter;
    }

    public static Function lambdaFactory$(HostDemandDetailsAdapter hostDemandDetailsAdapter) {
        return new HostDemandDetailsAdapter$$Lambda$1(hostDemandDetailsAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.createModel((SimilarListing) obj);
    }
}
