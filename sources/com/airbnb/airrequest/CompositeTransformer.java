package com.airbnb.airrequest;

import com.airbnb.airrequest.Transformer.Factory;
import p032rx.Observable;

class CompositeTransformer<T> implements Transformer<T> {
    private final AirRequestInitializer initializer;
    private final AirRequest request;

    CompositeTransformer(AirRequestInitializer initializer2, AirRequest request2) {
        this.initializer = initializer2;
        this.request = request2;
    }

    public Observable<AirResponse<T>> call(Observable<AirResponse<T>> observable) {
        Observable<AirResponse<T>> finalObservable = observable;
        for (Factory transformerFactory : this.initializer.transformerFactories()) {
            Transformer<T> transformer = transformerFactory.transformerFor(this.request, this.initializer);
            if (transformer != null) {
                finalObservable = (Observable) transformer.call(finalObservable);
            }
        }
        return finalObservable;
    }
}
