package com.airbnb.airrequest;

import com.airbnb.airrequest.Transformer.Factory;
import com.airbnb.rxgroups.ObservableManager;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observable;
import p032rx.functions.Func0;
import p032rx.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AirRequestInitializer {
    private final boolean enableDebugFeatures;
    private final ObservableManager observableManager;
    /* access modifiers changed from: private */
    public final AirRequestMapper requestToObservable;
    private final Retrofit retrofit;
    private final List<? extends Factory> transformerFactories;

    public static class Builder {
        private boolean enableDebugFeatures;
        private final List<Interceptor.Factory> interceptorFactories = new ArrayList();
        private ObservableManager observableManager;
        private AirRequestMapper requestToObservable;
        private Retrofit retrofit;
        private final List<Factory> transformerFactories = new ArrayList();

        public Builder addInterceptorFactory(Interceptor.Factory requestInterceptorFactory) {
            this.interceptorFactories.add(Utils.checkNotNull(requestInterceptorFactory, "requestInterceptorFactory"));
            return this;
        }

        public Builder addTransformerFactory(Factory transformerFactory) {
            this.transformerFactories.add(Utils.checkNotNull(transformerFactory, "transformerFactory"));
            return this;
        }

        public Builder observableManager(ObservableManager observableManager2) {
            this.observableManager = (ObservableManager) Utils.checkNotNull(observableManager2, "observableManager");
            return this;
        }

        public Builder enableDebugFeatures(boolean enableDebugFeatures2) {
            this.enableDebugFeatures = enableDebugFeatures2;
            return this;
        }

        public Builder retrofit(Retrofit retrofit3) {
            this.retrofit = (Retrofit) Utils.checkNotNull(retrofit3, "retrofit");
            return this;
        }

        public Builder airRequestToObservable(AirRequestMapper airRequestToObservable) {
            this.requestToObservable = (AirRequestMapper) Utils.checkNotNull(airRequestToObservable, "airRequestToObservable");
            return this;
        }

        public AirRequestInitializer build() {
            if (this.retrofit == null) {
                throw new IllegalStateException("Retrofit required.");
            }
            if (this.observableManager == null) {
                this.observableManager = new ObservableManager();
            }
            if (this.requestToObservable == null) {
                this.requestToObservable = new AirRequestMapperImpl(this.retrofit, new ObservableAirRequestFactory(this.retrofit, this.interceptorFactories), new Mapper(this.retrofit));
            }
            return new AirRequestInitializer(this.enableDebugFeatures, this.retrofit, this.observableManager, this.requestToObservable, this.transformerFactories);
        }
    }

    private AirRequestInitializer(boolean enableDebugFeatures2, Retrofit retrofit3, ObservableManager observableManager2, AirRequestMapper requestToObservable2, List<? extends Factory> transformerFactories2) {
        this.enableDebugFeatures = enableDebugFeatures2;
        this.retrofit = retrofit3;
        this.observableManager = observableManager2;
        this.requestToObservable = requestToObservable2;
        this.transformerFactories = transformerFactories2;
    }

    public <T> Observable<? extends AirResponse<T>> adapt(final BaseRequest<T> airRequest) {
        return Observable.defer(new Func0<Observable<AirResponse<T>>>() {
            public Observable<AirResponse<T>> call() {
                return ((Observable) AirRequestInitializer.this.requestToObservable.call(airRequest)).compose(new CompositeTransformer(AirRequestInitializer.this, airRequest));
            }
        }).subscribeOn(Schedulers.m4048io()).unsubscribeOn(Schedulers.m4048io());
    }

    public ObservableManager observableManager() {
        return this.observableManager;
    }

    public Retrofit retrofit() {
        return this.retrofit;
    }

    /* access modifiers changed from: 0000 */
    public List<? extends Factory> transformerFactories() {
        return this.transformerFactories;
    }

    public boolean isDebugFeaturesEnabled() {
        return this.enableDebugFeatures;
    }
}
